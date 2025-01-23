package com.hbm.entity.grenade;

import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemGrenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityGrenadeLemon extends EntityGrenadeBouncyBase
{

    public EntityGrenadeLemon(final World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityGrenadeLemon(final World p_i1774_1_, final EntityLivingBase p_i1774_2_, final EnumHand hand)
    {
        super(p_i1774_1_, p_i1774_2_, hand);
    }

    public EntityGrenadeLemon(final World p_i1775_1_, final double p_i1775_2_, final double p_i1775_4_, final double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    @Override
    public void explode() {
    	
        if (!this.world.isRemote)
        {
            this.setDead();
			this.world.newExplosion(null, (float)this.posX, (float)this.posY, (float)this.posZ, 5.0F, true, true);
        }
    }

	@Override
	protected int getMaxTimer() {
		return ItemGrenade.getFuseTicks(ModItems.grenade_lemon);
	}

	@Override
	protected double getBounceMod() {
		return 0.25D;
	}
}
