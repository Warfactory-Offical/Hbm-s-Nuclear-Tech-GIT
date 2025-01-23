package com.hbm.entity.mob;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget.Sorter;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAINearestAttackableTargetNT extends EntityAITarget {
	
	private final Class targetClass;
	private final int targetChance;
	private final Sorter theNearestAttackableTargetSorter;
	private final Predicate<Entity> targetEntitySelector;
	private EntityLivingBase targetEntity;
	private final double searchRange;

	public EntityAINearestAttackableTargetNT(final EntityCreature owner, final Class targetClass, final int targetChance, final boolean shouldCheckSight, final boolean nearbyOnly, final Predicate<Entity> selector, final double range) {
		super(owner, shouldCheckSight, nearbyOnly);
		this.targetClass = targetClass;
		this.targetChance = targetChance;
		this.theNearestAttackableTargetSorter = new Sorter(owner);
		this.searchRange = range;
		setMutexBits(1);

		this.targetEntitySelector = entity -> {
			return (selector == null || selector.apply(entity)) && entity instanceof EntityLivingBase && EntityAINearestAttackableTargetNT.this.isSuitableTarget((EntityLivingBase) entity, false);
		};
	}

	@Override
	protected double getTargetDistance() {
		return this.searchRange;
	}

	@Override
	public boolean shouldExecute() {

		if((this.targetChance > 0) && (this.taskOwner.getRNG().nextInt(this.targetChance) != 0)) {
			return false;
		}
		final double range = getTargetDistance();
		final List targets = this.taskOwner.world.getEntitiesWithinAABB(this.targetClass, this.taskOwner.getEntityBoundingBox().grow(range, range, range), this.targetEntitySelector);
		Collections.sort(targets, this.theNearestAttackableTargetSorter);

		if(targets.isEmpty()) {
			return false;
		}
		this.targetEntity = ((EntityLivingBase) targets.get(0));
		return true;
	}

	@Override
	public void resetTask() {
		this.taskOwner.setAttackTarget(targetEntity);
	}
}
