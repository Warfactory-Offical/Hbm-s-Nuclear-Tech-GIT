package com.hbm.entity.mob.sodtekhnologiyah;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.MathHelper;

public class WormMovementHead {

	private final EntityWormBase user;

	public WormMovementHead(final EntityWormBase user) {
		this.user = user;
	}

	protected void updateMovement() {

		final double var1 = this.user.waypointX - this.user.posX;
	    final double var3 = this.user.waypointY - this.user.posY;
	    final double var5 = this.user.waypointZ - this.user.posZ;
	    double var7 = var1 * var1 + var3 * var3 + var5 * var5;
	    if (this.user.courseChangeCooldown-- <= 0)
	    {
	      this.user.courseChangeCooldown += this.user.getRNG().nextInt(5) + 2;
	      var7 = MathHelper.sqrt(var7);
	      if (this.user.motionX * this.user.motionX + this.user.motionY * this.user.motionY + this.user.motionZ * this.user.motionZ < this.user.maxSpeed)
	      {
	        if (!this.user.isCourseTraversable()) {
	          var7 *= 8.0D;
	        }
	        final double moverSpeed = this.user.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
	        this.user.motionX += var1 / var7 * moverSpeed;
	        this.user.motionY += var3 / var7 * moverSpeed;
	        this.user.motionZ += var5 / var7 * moverSpeed;
	      }
	    }
	    if (!this.user.isCourseTraversable()) {
	      this.user.motionY -= this.user.fallSpeed;
	    }
	    if (this.user.dmgCooldown > 0) {
	      this.user.dmgCooldown -= 1;
	    }
	    this.user.aggroCooldown -= 1;
	    if (this.user.getAttackTarget() != null)
	    {
	      if (this.user.aggroCooldown <= 0)
	      {
	        this.user.targetedEntity = this.user.getAttackTarget();
	        this.user.aggroCooldown = 20;
	      }
	    }
	    else if (this.user.targetedEntity == null)
	    {
	      this.user.waypointX = (this.user.spawnPoint.getX() - 30 + this.user.getRNG().nextInt(60));
	      this.user.waypointY = (this.user.spawnPoint.getY() - 10 + this.user.getRNG().nextInt(20));
	      this.user.waypointZ = (this.user.spawnPoint.getZ() - 30 + this.user.getRNG().nextInt(60));
	    }
	    this.user.rotationYaw = (-(float)Math.atan2(this.user.motionX, this.user.motionZ) * 180.0F / 3.1415927F);
	    this.user.rotationPitch = ((float)-(Math.atan2(this.user.motionY, MathHelper.sqrt(this.user.motionX * this.user.motionX + this.user.motionZ * this.user.motionZ)) * 180.0D / 3.141592653589793D));
	    if ((this.user.targetedEntity != null) && (this.user.targetedEntity.getDistanceSq(this.user) < this.user.attackRange * this.user.attackRange)) {
	      if ((this.user.wasNearGround) || (this.user.canFly))
	      {
	        this.user.waypointX = this.user.targetedEntity.posX;
	        this.user.waypointY = this.user.targetedEntity.posY;
	        this.user.waypointZ = this.user.targetedEntity.posZ;
	        if ((this.user.getRNG().nextInt(80) == 0) && (this.user.posY > this.user.surfaceY) && (!this.user.isCourseTraversable())) {
	          this.user.wasNearGround = false;
	        }
	      }
	      else
	      {
	        this.user.waypointX = this.user.targetedEntity.posX;
	        this.user.waypointY = 10.0D;
	        this.user.waypointZ = this.user.targetedEntity.posZ;
	        if (this.user.posY < 15.0D) {
	          this.user.wasNearGround = true;
	        }
	      }
	    }
	}
}
