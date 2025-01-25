package com.hbm.entity.projectile;

import api.hbm.entity.IRadarDetectable;
import com.hbm.entity.logic.IChunkLoader;
import com.hbm.items.weapon.ItemAmmoArty;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.MainRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class EntityArtilleryShell extends EntityThrowableNT implements IChunkLoader, IRadarDetectable {

    private Ticket loaderTicket;

    private int turnProgress;
    private double syncPosX;
    private double syncPosY;
    private double syncPosZ;
    private double syncYaw;
    private double syncPitch;
    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;

    private double targetX;
    private double targetY;
    private double targetZ;
    private boolean shouldWhistle = false;
    private boolean didWhistle = false;

    private ItemStack cargo = null;

    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityArtilleryShell.class, DataSerializers.VARINT);

    public EntityArtilleryShell(World world) {
        super(world);
        this.ignoreFrustumCheck = true;
        this.setSize(0.5F, 0.5F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        init(ForgeChunkManager.requestTicket(MainRegistry.instance, world, ForgeChunkManager.Type.ENTITY));
        this.dataManager.register(TYPE, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    public EntityArtilleryShell setType(int type) {
        this.dataManager.set(TYPE, type);
        return this;
    }

    public ItemAmmoArty.ArtilleryShell getType() {
        try {
            return ItemAmmoArty.itemTypes[this.dataManager.get(TYPE)];
        } catch(Exception ex) {
            return ItemAmmoArty.itemTypes[0];
        }
    }

    public double[] getTarget() {
        return new double[] { this.targetX, this.targetY, this.targetZ };
    }

    public void setTarget(double x, double y, double z) {
        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;
    }

    public double getTargetHeight() {
        return this.targetY;
    }

    public void setWhistle(boolean whistle) {
        this.shouldWhistle = whistle;
    }

    public boolean getWhistle() {
        return this.shouldWhistle;
    }

    public boolean didWhistle() {
        return this.didWhistle;
    }

    @Override
    public void onUpdate() {

        if(!world.isRemote) {
            super.onUpdate();

            if(!didWhistle && this.shouldWhistle) {
                double speed = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                double deltaX = this.posX - this.targetX;
                double deltaZ = this.posZ - this.targetZ;
                double dist = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

                if(speed * 18 > dist) {
                    world.playSound(null, this.targetX, this.targetY, this.targetZ, HBMSoundHandler.mortarWhistle, SoundCategory.BLOCKS, 15.0F, 0.9F + rand.nextFloat() * 0.2F);
                    this.didWhistle = true;
                }
            }

            loadNeighboringChunks((int)Math.floor(posX / 16D), (int)Math.floor(posZ / 16D));
            this.getType().onUpdate(this);

        } else {
            if(this.turnProgress > 0) {
                double interpX = this.posX + (this.syncPosX - this.posX) / (double) this.turnProgress;
                double interpY = this.posY + (this.syncPosY - this.posY) / (double) this.turnProgress;
                double interpZ = this.posZ + (this.syncPosZ - this.posZ) / (double) this.turnProgress;
                double d = MathHelper.wrapDegrees(this.syncYaw - (double) this.rotationYaw);
                this.rotationYaw = (float) ((double) this.rotationYaw + d / (double) this.turnProgress);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.syncPitch - (double)this.rotationPitch) / (double)this.turnProgress);
                --this.turnProgress;
                this.setPosition(interpX, interpY, interpZ);
            } else {
                this.setPosition(this.posX, this.posY, this.posZ);
            }

            if(new Vec3d(this.syncPosX - this.posX, this.syncPosY - this.posY, this.syncPosZ - this.posZ).length() < 0.2) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY + 0.5, posZ, 0.0, 0.1, 0.0);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
        this.velocityX = this.motionX = p_70016_1_;
        this.velocityY = this.motionY = p_70016_3_;
        this.velocityZ = this.motionZ = p_70016_5_;
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int theNumberThree) {
        this.syncPosX = x;
        this.syncPosY = y;
        this.syncPosZ = z;
        this.syncYaw = yaw;
        this.syncPitch = pitch;
        this.turnProgress = theNumberThree;
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
    }

    @Override
    protected void onImpact(RayTraceResult mop) {

        if(!world.isRemote) {

            if(mop.typeOfHit == mop.typeOfHit.ENTITY && mop.entityHit instanceof EntityArtilleryShell) return;
            this.getType().onImpact(this, mop);
        }
    }

    @Override
    public void init(Ticket ticket) {
        if(!world.isRemote && ticket != null) {
            if(loaderTicket == null) {
                loaderTicket = ticket;
                loaderTicket.bindEntity(this);
                loaderTicket.getModData();
            }
            ForgeChunkManager.forceChunk(loaderTicket, new ChunkPos(chunkCoordX, chunkCoordZ));
        }
    }

    List<ChunkPos> loadedChunks = new ArrayList<ChunkPos>();

    public void loadNeighboringChunks(int newChunkX, int newChunkZ) {
        if(!world.isRemote && loaderTicket != null) {

            clearChunkLoader();

            loadedChunks.clear();
            loadedChunks.add(new ChunkPos(newChunkX, newChunkZ));
            //loadedChunks.add(new ChunkCoordIntPair(newChunkX + (int) Math.floor((this.posX + this.motionX) / 16D), newChunkZ + (int) Math.floor((this.posZ + this.motionZ) / 16D)));

            for(ChunkPos chunk : loadedChunks) {
                ForgeChunkManager.forceChunk(loaderTicket, chunk);
            }
        }
    }

    public void killAndClear() {
        this.setDead();
        this.clearChunkLoader();
    }

    public void clearChunkLoader() {
        if(!world.isRemote && loaderTicket != null) {
            for(ChunkPos chunk : loadedChunks) {
                ForgeChunkManager.unforceChunk(loaderTicket, chunk);
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);

        nbt.setInteger("type", this.dataManager.get(TYPE));
        nbt.setBoolean("shouldWhistle", this.shouldWhistle);
        nbt.setBoolean("didWhistle", this.didWhistle);
        nbt.setDouble("targetX", this.targetX);
        nbt.setDouble("targetY", this.targetY);
        nbt.setDouble("targetZ", this.targetZ);

        if(this.cargo != null)
            nbt.setTag("cargo", this.cargo.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);

        this.dataManager.set(TYPE, nbt.getInteger("type"));
        this.shouldWhistle = nbt.getBoolean("shouldWhistle");
        this.didWhistle = nbt.getBoolean("didWhistle");
        this.targetX = nbt.getDouble("targetX");
        this.targetY = nbt.getDouble("targetY");
        this.targetZ = nbt.getDouble("targetZ");

        NBTTagCompound compound = nbt.getCompoundTag("cargo");
        this.setCargo(new ItemStack(compound));
    }

    @Override
    protected float getAirDrag() {
        return 1.0F;
    }

    @Override
    public double getGravityVelocity() {
        return 9.81 * 0.05;
    }

    @Override
    protected int groundDespawn() {
        return cargo != null ? 0 : 1200;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public void setCargo(ItemStack stack) {
        this.cargo = stack;
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {

        if(!world.isRemote) {
            if(this.cargo != null) {
                player.inventory.addItemStackToInventory(this.cargo.copy());
                player.inventoryContainer.detectAndSendChanges();
            }
            this.setDead();
        }

        return false;
    }

    @Override
    public RadarTargetType getTargetType() {
        return RadarTargetType.ARTILLERY;
    }
}
