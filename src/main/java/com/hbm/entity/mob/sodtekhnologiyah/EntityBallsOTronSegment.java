package com.hbm.entity.mob.sodtekhnologiyah;

import com.hbm.entity.mob.EntityAINearestAttackableTargetNT;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityBallsOTronSegment extends EntityBallsOTronBase {

	public static final DataParameter<Boolean> SHIELD = EntityDataManager.createKey(EntityBallsOTronSegment.class, DataSerializers.BOOLEAN);
	
	private final WormMovementBody movement = new WormMovementBody(this);
	
	public EntityBallsOTronSegment(final World world) {
		super(world);
		this.bodySpeed = 0.6D;
		this.rangeForParts = 70.0D;
		this.segmentDistance = 1.9D;
	    this.maxBodySpeed = 1.4D;
	    this.targetTasks.addTask(1, new EntityAINearestAttackableTargetNT(this, EntityLivingBase.class, 0, true, false, this.selector, 128.0D));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(SHIELD, false);
	}
	
	@Override
	public float getAttackStrength(final Entity target) {
		if(target instanceof EntityLivingBase) {
			return ((EntityLivingBase) target).getHealth() * 0.75F;
		}

		return 100;
	}
	
	@Override
	protected void updateAITasks() {
		this.movement.updateMovement();

		if((this.followed != null) && (getPartID() == 0)) {
			//this.dataWatcher.updateObject(17, Byte.valueOf((byte) (((EntityBallsOTronHead) this.followed).isArmored() ? 1 : 0)));
		} else if(this.targetedEntity != null) {
			//this.dataWatcher.updateObject(17, Byte.valueOf(this.targetedEntity.getDataWatcher().getWatchableObjectByte(17)));
		}
		if(this.didCheck) {
			if(this.targetedEntity == null || !this.targetedEntity.isEntityAlive()) {
				setHealth(getHealth() - 1999.0F);
			}
			if(((this.followed == null) || (!this.followed.isEntityAlive())) && (this.rand.nextInt(60) == 0)) {
				this.world.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, false);
			}
		}
		if((this.followed != null) && (getAttackTarget() != null)) {
			if(canEntityBeSeen(getAttackTarget())) {
				this.attackCounter += 1;
				if(this.attackCounter == 10) {
					//useLaser(o(), false);

					this.attackCounter = -20;
				}
			} else if(this.attackCounter > 0) {
				this.attackCounter -= 1;
			}
		} else if(this.attackCounter > 0) {
			this.attackCounter -= 1;
		}
		final float f3 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(motionY, f3) * 180.0D / Math.PI);
	}
	
	@Override
	public void writeEntityToNBT(final NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("partID", this.getPartID());
	}
	
	@Override
	public void readEntityFromNBT(final NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		setPartID(compound.getInteger("partID"));
	}

}