package com.hbm.entity.grenade;

import com.hbm.entity.projectile.EntityBullet;
import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemGrenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityGrenadeIFBrimstone extends EntityGrenadeBouncyBase {

    public EntityGrenadeIFBrimstone(final World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityGrenadeIFBrimstone(final World p_i1774_1_, final EntityLivingBase p_i1774_2_, final EnumHand hand)
    {
        super(p_i1774_1_, p_i1774_2_, hand);
    }

    public EntityGrenadeIFBrimstone(final World p_i1775_1_, final double p_i1775_2_, final double p_i1775_4_, final double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
    	
    	if(this.timer > (this.getMaxTimer() * 0.65)) {
    		
    		if(!world.isRemote) {
	    		final EntityBullet fragment;
	
	    		fragment = new EntityBullet(world, this.thrower, 3.0F, 35, 45, false, "tauDay", EnumHand.MAIN_HAND);
	    		fragment.setDamage(rand.nextInt(301) + 100);
	
	    		fragment.motionX = rand.nextGaussian();
	    		fragment.motionY = rand.nextGaussian();
	    		fragment.motionZ = rand.nextGaussian();
	    		fragment.shootingEntity = this.thrower;

	    		fragment.posX = posX;
	    		fragment.posY = posY;
	    		fragment.posZ = posZ;
	
	    		fragment.setIsCritical(true);
	
	    		world.spawnEntity(fragment);
    		}
    	}
    }

    @Override
    public void explode() {
    	
        if (!this.world.isRemote)
        {
            this.setDead();
    		
    		world.newExplosion(this, posX, posY, posZ, 5, false, false);
    		
    		for(int i = 0; i < 100; i++) {
	    		final EntityBullet fragment;
	
	    		fragment = new EntityBullet(world, this.thrower, 3.0F, 35, 45, false, "tauDay", EnumHand.MAIN_HAND);
	    		fragment.setDamage(rand.nextInt(301) + 100);
	
	    		fragment.motionX = rand.nextGaussian() * 0.25;
	    		fragment.motionY = rand.nextGaussian() * 0.25;
	    		fragment.motionZ = rand.nextGaussian() * 0.25;
	    		fragment.shootingEntity = this.thrower;

	    		fragment.posX = posX;
	    		fragment.posY = posY;
	    		fragment.posZ = posZ;
	
	    		fragment.setIsCritical(true);
	
	    		world.spawnEntity(fragment);
    		}
        }
    }

	@Override
	protected int getMaxTimer() {
		return ItemGrenade.getFuseTicks(ModItems.grenade_if_brimstone);
	}

	@Override
	protected double getBounceMod() {
		return 0.25D;
	}
}
