package com.hbm.entity.item;

import api.hbm.block.IConveyorBelt;
import api.hbm.block.IEnterableBlock;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.tileentity.network.TileEntityCraneBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityMovingConveyorObject extends Entity {
    protected int turnProgress;
    protected double syncPosX;
    protected double syncPosY;
    protected double syncPosZ;
    @SideOnly(Side.CLIENT) protected double velocityX;
    @SideOnly(Side.CLIENT) protected double velocityY;
    @SideOnly(Side.CLIENT) protected double velocityZ;

    public EntityMovingConveyorObject(final World p_i1582_1_) {
        super(p_i1582_1_);
        this.noClip = true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }


    public boolean canAttackWithItem() {
        return true;
    }

    @Override
    public boolean hitByEntity(final Entity attacker) {

        if(attacker instanceof EntityPlayer) {
            this.setDead();
        }

        return false;
    }

    @Override
    protected boolean canTriggerWalking() {
        return true;
    }

    @Override
    public void onUpdate() {
        if(world.isRemote) {
            if(this.turnProgress > 0) {
                final double interpX = this.posX + (this.syncPosX - this.posX) / (double) this.turnProgress;
                final double interpY = this.posY + (this.syncPosY - this.posY) / (double) this.turnProgress;
                final double interpZ = this.posZ + (this.syncPosZ - this.posZ) / (double) this.turnProgress;
                --this.turnProgress;
                this.setPosition(interpX, interpY, interpZ);
            } else {
                this.setPosition(this.posX, this.posY, this.posZ);
            }
        }

        if(!world.isRemote) {
            ticksExisted++;

            if(this.ticksExisted <= 5) {
                return;
            }

            final int blockX = (int) Math.floor(posX);
            final int blockY = (int) Math.floor(posY);
            final int blockZ = (int) Math.floor(posZ);
            final BlockPos blockPos = new BlockPos(blockX, blockY, blockZ);
            final Block b = world.getBlockState(blockPos).getBlock();
            final boolean isOnConveyor = b instanceof IConveyorBelt && ((IConveyorBelt) b).canItemStay(world, blockX, blockY, blockZ, new Vec3d(posX, posY, posZ));

            if(!isOnConveyor) {
                if(onLeaveConveyor()) {
                    return;
                }
            } else {
                final Vec3d target = ((IConveyorBelt) b).getTravelLocation(world, blockX, blockY, blockZ, new Vec3d(posX, posY, posZ), getMoveSpeed());
                this.motionX = target.x - posX;
                this.motionY = target.y - posY;
                this.motionZ = target.z - posZ;
            }

            final BlockPos lastPos = new BlockPos(posX, posY, posZ);
            this.move(MoverType.SELF, motionX, motionY, motionZ);
            final BlockPos newPos = new BlockPos(posX, posY, posZ);

            if(!lastPos.equals(newPos)) {
                Block newBlock = world.getBlockState(newPos).getBlock();

                if(newBlock instanceof IEnterableBlock enterable) {

                    EnumFacing dir = null;

                    if (lastPos.getX() > newPos.getX() && lastPos.getY() == newPos.getY() && lastPos.getZ() == newPos.getZ())
                        dir = EnumFacing.EAST;
                    else if (lastPos.getX() < newPos.getX() && lastPos.getY() == newPos.getY() && lastPos.getZ() == newPos.getZ())
                        dir = EnumFacing.WEST;
                    else if (lastPos.getX() == newPos.getX() && lastPos.getY() > newPos.getY() && lastPos.getZ() == newPos.getZ())
                        dir = EnumFacing.UP;
                    else if (lastPos.getX() == newPos.getX() && lastPos.getY() < newPos.getY() && lastPos.getZ() == newPos.getZ())
                        dir = EnumFacing.DOWN;
                    else if (lastPos.getX() == newPos.getX() && lastPos.getY() == newPos.getY() && lastPos.getZ() > newPos.getZ())
                        dir = EnumFacing.SOUTH;
                    else if (lastPos.getX() == newPos.getX() && lastPos.getY() == newPos.getY() && lastPos.getZ() < newPos.getZ())
                        dir = EnumFacing.NORTH;

                    final TileEntity tileEntity = world.getTileEntity(newPos);
                    if(tileEntity instanceof TileEntityCraneBase craneBase) {
                        final EnumFacing inputSide = craneBase.getInputSide();
                        if (dir == inputSide) {
                            enterBlock(enterable, newPos, dir);
                        }
                    } else {
                        enterBlock(enterable, newPos, dir);
                    }

                } else {
                    if(!newBlock.getMaterial(world.getBlockState(newPos)).isSolid()) {
                        newBlock = world.getBlockState(newPos.down()).getBlock();

                        if(newBlock instanceof IEnterableBlock enterable) {
                            enterBlockFalling(enterable, newPos);
                        }
                    }
                }
            }
        }
    }

    public abstract void enterBlock(IEnterableBlock enterable, BlockPos pos, EnumFacing dir);

    public void enterBlockFalling(final IEnterableBlock enterable, final BlockPos pos) {
        this.enterBlock(enterable, pos.add(0, -1, 0), EnumFacing.UP);
    }

    public abstract boolean onLeaveConveyor();

    public double getMoveSpeed() {
        return 0.0625D;
    }

    @SideOnly(Side.CLIENT)
    public void setVelocity(final double motionX, final double motionY, final double motionZ) {
        this.velocityX = this.motionX = motionX;
        this.velocityY = this.motionY = motionY;
        this.velocityZ = this.motionZ = motionZ;
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(final double x, final double y, final double z, final float yaw, final float pitch, final int theNumberThree) {
        this.syncPosX = x;
        this.syncPosY = y;
        this.syncPosZ = z;
        this.turnProgress = theNumberThree + 2; //use 4-ply for extra smoothness
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
    }

}
