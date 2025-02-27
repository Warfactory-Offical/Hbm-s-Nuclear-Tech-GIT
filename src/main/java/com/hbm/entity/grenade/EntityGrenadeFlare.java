package com.hbm.entity.grenade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityGrenadeFlare extends EntityThrowable
{
    public Entity shooter;

    public EntityGrenadeFlare(final World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityGrenadeFlare(final World p_i1774_1_, final EntityLivingBase p_i1774_2_, final EnumHand hand)
    {
    	super(p_i1774_1_);
        this.thrower = p_i1774_2_;
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(p_i1774_2_.posX, p_i1774_2_.posY + (double)p_i1774_2_.getEyeHeight(), p_i1774_2_.posZ, p_i1774_2_.rotationYaw, p_i1774_2_.rotationPitch);
        if(hand == EnumHand.MAIN_HAND){
        	this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
            this.posY -= 0.10000000149011612D;
            this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        } else {
        	this.posX += MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
            this.posY -= 0.10000000149011612D;
            this.posZ += MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        }
        
        this.setPosition(this.posX, this.posY, this.posZ);
        final float f = 0.4F;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f;
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f;
        this.motionY = -MathHelper.sin((this.rotationPitch) / 180.0F * (float)Math.PI) * f;
        this.shoot(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
    }

    public EntityGrenadeFlare(final World p_i1775_1_, final double p_i1775_2_, final double p_i1775_4_, final double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
    
    @Override
	public void onUpdate() {
    	super.onUpdate();
    	if(this.ticksExisted > 250)
    	{
    		this.setDead();
    	}
    }

    @Override
	protected void onImpact(final RayTraceResult p_70184_1_)
    {
    	this.motionX = 0;
    	this.motionY = 0;
    	this.motionZ = 0;
    }

}
