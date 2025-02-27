package com.hbm.entity.mob.botprime;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class WormMovementBodyNT {

	private final EntityWormBaseNT user;

	public WormMovementBodyNT(final EntityWormBaseNT user) {
		this.user = user;
	}

	protected void updateMovement() {

		final double targetingRange = 128.0D;

		if((this.user.targetedEntity != null) && (this.user.targetedEntity.getDistanceSq(this.user) < targetingRange * targetingRange)) {
			this.user.waypointX = this.user.targetedEntity.posX;
			this.user.waypointY = this.user.targetedEntity.posY;
			this.user.waypointZ = this.user.targetedEntity.posZ;
		}

		if(((this.user.ticksExisted % 60 == 0) || (this.user.ticksExisted == 1)) && ((this.user.targetedEntity == null) || (this.user.followed == null))) {
			findEntityToFollow(this.user.world.getEntitiesWithinAABB(EntityWormBaseNT.class, this.user.getEntityBoundingBox().grow(this.user.rangeForParts, this.user.rangeForParts, this.user.rangeForParts), EntityWormBaseNT.wormSelector));
		}

		final double deltaX = this.user.waypointX - this.user.posX;
		final double deltaY = this.user.waypointY - this.user.posY;
		final double deltaZ = this.user.waypointZ - this.user.posZ;
		final double deltaDist = MathHelper.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

		if(this.user.targetedEntity != null) {
			this.user.faceEntity(this.user.targetedEntity, 180.0F, 180.0F);
		}

		this.user.bodySpeed = Math.max(0.0D, Math.min(deltaDist - this.user.segmentDistance, this.user.maxBodySpeed));

		if(deltaDist < this.user.segmentDistance * 0.895D) {
			this.user.motionX *= 0.8D;
			this.user.motionY *= 0.8D;
			this.user.motionZ *= 0.8D;
		} else {
			this.user.motionX = (deltaX / deltaDist * this.user.bodySpeed);
			this.user.motionY = (deltaY / deltaDist * this.user.bodySpeed);
			this.user.motionZ = (deltaZ / deltaDist * this.user.bodySpeed);
		}
	}

	protected void findEntityToFollow(final List<EntityWormBaseNT> segments) {
		for(final EntityWormBaseNT segment : segments) {

			if(segment.getHeadID() == this.user.getHeadID()) {
				if(segment.getIsHead()) {
					if(this.user.getPartNumber() == 0) {
						this.user.targetedEntity = segment;
					}
					this.user.followed = segment;

				} else if(segment.getPartNumber() == this.user.getPartNumber() - 1) {
					this.user.targetedEntity = segment;
				}
			}
		}
		this.user.didCheck = true;
	}
}
