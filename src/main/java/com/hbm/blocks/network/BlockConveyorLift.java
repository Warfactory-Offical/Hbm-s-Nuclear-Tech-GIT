package com.hbm.blocks.network;

import api.hbm.block.IConveyorBelt;
import api.hbm.block.IEnterableBlock;
import com.hbm.entity.item.EntityMovingItem;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockConveyorLift extends BlockConveyorChute {

    public BlockConveyorLift(final Material materialIn, final String s) {
        super(materialIn, s);
    }

    @Override
    public EnumFacing getTravelDirection(final World world, final BlockPos pos, final Vec3d itemPos) {

        final boolean bottom = !(world.getBlockState(pos.down()).getBlock() instanceof IConveyorBelt);
        final boolean top = !(world.getBlockState(pos.up()).getBlock() instanceof IConveyorBelt) && !bottom && !(world.getBlockState(pos.up()).getBlock() instanceof IEnterableBlock);

        if(!top) {
            return EnumFacing.DOWN;
        }

        return world.getBlockState(pos).getValue(FACING);
    }

    @Override
    public Vec3d getTravelLocation(final World world, final int x, final int y, final int z, final Vec3d itemPos, final double speed) {
        final BlockPos pos = new BlockPos(x, y, z);
        final EnumFacing dir = this.getTravelDirection(world, pos, itemPos);
        final Vec3d snap = this.getClosestSnappingPosition(world, pos, itemPos);
        final Vec3d dest = new Vec3d(
                snap.x - dir.getXOffset() * speed,
                snap.y - dir.getYOffset() * speed,
                snap.z - dir.getZOffset() * speed);
        final Vec3d motion = new Vec3d(
                dest.x - itemPos.x,
                dest.y - itemPos.y,
                dest.z - itemPos.z);
        final double len = motion.length();
        final Vec3d ret = new Vec3d(
                itemPos.x + motion.x / len * speed,
                itemPos.y + motion.y / len * speed,
                itemPos.z + motion.z / len * speed);
        return ret;
    }

    @Override
    public void onEntityCollision(final World world, final BlockPos pos, final IBlockState state, final Entity entity) {
        if(!world.isRemote) {

            if(entity instanceof EntityItem && entity.ticksExisted > 10 && !entity.isDead) {

                final EntityMovingItem item = new EntityMovingItem(world);
                item.setItemStack(((EntityItem)entity).getItem());
                final Vec3d entityPos = new Vec3d(entity.posX, entity.posY, entity.posZ);
                final Vec3d snap = this.getClosestSnappingPosition(world, pos, entityPos);
                item.setPositionAndRotation(snap.x, snap.y, snap.z, 0, 0);
                world.spawnEntity(item);
                
                entity.setDead();
            }
        }
    }

    @Override
    public Vec3d getClosestSnappingPosition(final World world, final BlockPos pos, final Vec3d itemPos) {

        final boolean bottom = !(world.getBlockState(pos.down()).getBlock() instanceof IConveyorBelt);
        final boolean top = !(world.getBlockState(pos.up()).getBlock() instanceof IConveyorBelt) && !bottom && !(world.getBlockState(pos.up()).getBlock() instanceof IEnterableBlock);

        if(!top) {
            return new Vec3d(pos.getX() + 0.5, itemPos.y, pos.getZ() + 0.5);
        } else {
            return super.getClosestSnappingPosition(world, pos, itemPos);
        }
    }

    @Override
    public int getUpdatedType(final World world, final BlockPos pos, final EnumFacing side){
        final boolean hasChuteBelow = world.getBlockState(pos.down()).getBlock() instanceof BlockConveyorChute;
        boolean hasInputBelt = false;
        final Block inputBlock = world.getBlockState(pos.offset(side.getOpposite(), 1)).getBlock();
        if (inputBlock instanceof IConveyorBelt || inputBlock instanceof IEnterableBlock) {
            hasInputBelt = true;
        }
        if(hasChuteBelow){
            return hasInputBelt ? 2 : 1;
        }
        return 0;
    }
}