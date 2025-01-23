package com.hbm.entity.mob.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAI_MLPF extends EntityAIBase {

	private final Class targetClass;
    private EntityLivingBase target;
	private final EntityLiving mover;
    private final Sorter theNearestAttackableTargetSorter;
	private final int range;
	private final int distance;
	private static final int vertical = 10;
    private final double speed;

	public EntityAI_MLPF(final EntityLiving entity, final Class targetClass, final int range, final double speed, final int distance)
	{
		this.mover = entity;
		this.targetClass = targetClass;
		this.range = range;
		this.speed = speed;
		this.distance = distance;
        this.theNearestAttackableTargetSorter = new Sorter(entity);
	}

	@Override
	public boolean shouldExecute() {

		//roll the dice for targetiing if there's nothing to track
		if(mover.getRNG().nextInt(100) < 5 && mover.getAttackTarget() == null) {
			//System.out.println("Randomizer fired!");
			//load potential targets
			calculateTarget();

			//start if there is a valid target
			return target != null;
		}

		return false;
	}

    public void startExecuting() {

    	//create a path line from mover to target
    	Vec3 vec = Vec3.createVectorHelper(
    			target.posX - mover.posX,
    			target.posY - mover.posY,
    			target.posZ - mover.posZ);


    	//line length is capped so the pathfinder can manage it
    	final int range = distance;
    	
    	if(vec.length() < 16)
    		mover.setAttackTarget(target);

    	vec = vec.normalize();
    	vec.xCoord *= range;
    	vec.yCoord *= range;
    	vec.zCoord *= range;

    	//target positions are set (with randomized Y-offset)
    	final double x = mover.posX + vec.xCoord;
    	final double y = mover.posY + vec.yCoord - 5 + mover.getRNG().nextInt(11);
    	final double z = mover.posZ + vec.zCoord;

    	//System.out.println("Routing to " + x + "/" + y + "/" + z);

		//this is where the magic happens
    	this.mover.getNavigator().tryMoveToXYZ(x, y, z, this.speed);

        //System.out.println("Start successful? " + success);
    }

	@Override
	public boolean shouldContinueExecuting() {

		//only continue if the path is valid
        return !this.mover.getNavigator().noPath();
	}

	@Override
	public void resetTask()
	{
		//once the task is complete, remove target
		target = null;
	}

	//scans the area and determines a new target entity
	private void calculateTarget() {

		final List list = mover.world.getEntitiesWithinAABB(targetClass, new AxisAlignedBB(
				mover.posX - range,
				mover.posY - vertical,
				mover.posZ - range,
				mover.posX + range,
				mover.posY + vertical,
				mover.posZ + range));

		Collections.sort(list, theNearestAttackableTargetSorter);

		if (!list.isEmpty())
        {
			target = (EntityLivingBase)list.get(0);
        }
	}

	public static class Sorter implements Comparator {
		private final Entity theEntity;

		public Sorter(final Entity p_i1662_1_) {
			this.theEntity = p_i1662_1_;
		}

		public int compare(final Entity p_compare_1_, final Entity p_compare_2_) {
			final double d0 = this.theEntity.getDistanceSq(p_compare_1_);
			final double d1 = this.theEntity.getDistanceSq(p_compare_2_);
			return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
		}

		public int compare(final Object p_compare_1_, final Object p_compare_2_) {
			return this.compare((Entity) p_compare_1_, (Entity) p_compare_2_);
		}
	}
}