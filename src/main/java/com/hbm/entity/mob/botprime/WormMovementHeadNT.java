package com.hbm.entity.mob.botprime;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.MathHelper;

public class WormMovementHeadNT {

	private final EntityWormBaseNT user;

	public WormMovementHeadNT(final EntityWormBaseNT user) {
		this.user = user;
	}

	protected void updateMovement() {
		final double deltaX = this.user.waypointX - this.user.posX;
		final double deltaY = this.user.waypointY - this.user.posY;
		final double deltaZ = this.user.waypointZ - this.user.posZ;
		double deltaSq = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
		//System.out.println(this.user.waypointX + " " + this.user.waypointY + " " + this.user.waypointZ);
		
		if(this.user.courseChangeCooldown-- <= 0) {
			
			this.user.courseChangeCooldown += this.user.getRNG().nextInt(5) + 2;
			deltaSq = MathHelper.sqrt(deltaSq);
			
			if(this.user.motionX * this.user.motionX + this.user.motionY * this.user.motionY + this.user.motionZ * this.user.motionZ < this.user.maxSpeed) {
				
				if(!this.user.isCourseTraversable()) {
					deltaSq *= 8.0D;
				}
				
				final double moverSpeed = this.user.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
				this.user.motionX += deltaX / deltaSq * moverSpeed;
				this.user.motionY += deltaY / deltaSq * moverSpeed;
				this.user.motionZ += deltaZ / deltaSq * moverSpeed;
			}
		}
		
		if(!this.user.isCourseTraversable()) {
			this.user.motionY -= this.user.fallSpeed;
		}
		
		if(this.user.dmgCooldown > 0) {
			this.user.dmgCooldown -= 1;
		}
		
		this.user.aggroCooldown -= 1;
		
		if(this.user.getAttackTarget() != null) {
			
			if(this.user.aggroCooldown <= 0) {
				this.user.targetedEntity = this.user.getAttackTarget();
				this.user.aggroCooldown = 20;
			}
			
		} else if(this.user.targetedEntity == null) {
			this.user.waypointX = (this.user.spawnPoint.getX() - 30 + this.user.getRNG().nextInt(60));
			this.user.waypointY = (this.user.spawnPoint.getY() - 10 + this.user.getRNG().nextInt(20));
			this.user.waypointZ = (this.user.spawnPoint.getZ() - 30 + this.user.getRNG().nextInt(60));
		
		}
		
		this.user.rotationYaw = -(float) -(Math.atan2(this.user.motionX, this.user.motionZ) * 180.0F / Math.PI);
		this.user.rotationPitch = (float) -(Math.atan2(this.user.motionY, MathHelper.sqrt(this.user.motionX * this.user.motionX + this.user.motionZ * this.user.motionZ)) * 180.0D / Math.PI);
		
		if(this.user.targetedEntity != null && this.user.targetedEntity.getDistanceSq(this.user) < this.user.attackRange * this.user.attackRange) {
			
			if((this.user.wasNearGround) || (this.user.canFly)) {
				
				this.user.waypointX = this.user.targetedEntity.posX;
				this.user.waypointY = this.user.targetedEntity.posY;
				this.user.waypointZ = this.user.targetedEntity.posZ;
				
				if((this.user.getRNG().nextInt(80) == 0) && (this.user.posY > this.user.surfaceY) && (!this.user.isCourseTraversable())) {
					this.user.wasNearGround = false;
				}
				
			} else {
				
				this.user.waypointX = this.user.targetedEntity.posX;
				this.user.waypointY = 10.0D;
				this.user.waypointZ = this.user.targetedEntity.posZ;
				
				if(this.user.posY < 15.0D) {
					this.user.wasNearGround = true;
				}
			}
		}
	}
}
