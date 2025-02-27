package com.hbm.entity.grenade;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemGrenade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class EntityGrenadeIFNull extends EntityGrenadeBouncyBase {

    public EntityGrenadeIFNull(final World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityGrenadeIFNull(final World p_i1774_1_, final EntityLivingBase p_i1774_2_, final EnumHand hand)
    {
        super(p_i1774_1_, p_i1774_2_, hand);
    }

    public EntityGrenadeIFNull(final World p_i1775_1_, final double p_i1775_2_, final double p_i1775_4_, final double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    @Override
    public void explode() {
    	
        if (!this.world.isRemote)
        {
            this.setDead();
            final MutableBlockPos pos = new BlockPos.MutableBlockPos();
    		for(int a = -3; a <= 3; a++)
        		for(int b = -3; b <= 3; b++)
            		for(int c = -3; c <= 3; c++)
            			world.setBlockToAir(pos.setPos((int)posX + a, (int)posY + b, (int)posZ + c));
            		
    		
    		final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB((int)posX + 0.5 - 3, (int)posY + 0.5 - 3, (int)posZ + 0.5 - 3, (int)posX + 0.5 + 3, (int)posY + 0.5 + 3, (int)posZ + 0.5 + 3));
    		
    		for(final Object o : list) {
    			if(o instanceof EntityLivingBase e) {

                    e.setHealth(0);
    			} else if(o instanceof Entity e) {
                    e.setDead();
    				e.isDead = true;
    			}
    		}
        }
    }

	@Override
	protected int getMaxTimer() {
		return ItemGrenade.getFuseTicks(ModItems.grenade_if_null);
	}

	@Override
	protected double getBounceMod() {
		return 0.25D;
	}
}
