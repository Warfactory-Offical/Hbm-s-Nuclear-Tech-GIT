package com.hbm.entity.grenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityGrenadeGeneric extends EntityGrenadeBouncyBase {

    public EntityGrenadeGeneric(final World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityGrenadeGeneric(final World p_i1774_1_, final EntityLivingBase p_i1774_2_, final EnumHand hand)
    {
        super(p_i1774_1_, p_i1774_2_, hand);
    }

    public EntityGrenadeGeneric(final World p_i1775_1_, final double p_i1775_2_, final double p_i1775_4_, final double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    @Override
    public void explode() {
    	
        if (!this.world.isRemote)
        {
            this.setDead();
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, true);
        }
    }

	@Override
	protected int getMaxTimer() {
		return 100;
	}

	@Override
	protected double getBounceMod() {
		return 0.25D;
	}

}
