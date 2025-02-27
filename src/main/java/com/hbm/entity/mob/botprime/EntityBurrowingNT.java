package com.hbm.entity.mob.botprime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.MoverType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBurrowingNT extends EntityCreature {

	protected float dragInAir;
	protected float dragInGround;
	
	public EntityBurrowingNT(final World worldIn) {
		super(worldIn);
	}
	
	@Override
	public void fall(final float distance, final float damageMultiplier) {}
	
	@Override
	public float getEyeHeight() {
		return this.height * 0.5F;
	}
	
	public boolean getIsHead() {
		return false;
	}
	
	@Override
	protected void updateFallState(final double y, final boolean onGroundIn, final IBlockState state, final BlockPos pos) {}
	
	@Override
	public void travel(final float strafe, final float vertical, final float forward) {
		float drag = this.dragInGround;
		
		if((!isEntityInsideOpaqueBlock()) && (!isInWater()) && (!isInLava())) {
			drag = this.dragInAir;
		} else if(this.getRNG().nextInt(100) == 0) {
			//Block b = worldObj.getBlock((int)Math.floor(posX), (int)Math.floor(posY), (int)Math.floor(posZ));
			//this.playSound(b.stepSound.getStepResourcePath(), 5F, 1F);
		}

        if (!getIsHead()) {
        	drag *= 0.9F;
        }
        moveRelative(strafe, vertical, forward, 0.02F);

        move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= drag;
        this.motionY *= drag;
        this.motionZ *= drag;
	}
	
	@Override
	public boolean isOnLadder() {
		return false;
	}

}
