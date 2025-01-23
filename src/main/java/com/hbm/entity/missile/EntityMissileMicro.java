package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileMicro extends EntityMissileBaseAdvanced {

	public EntityMissileMicro(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 7F);
	}

	public EntityMissileMicro(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(1F, 7F);
	}

	@Override
	public void onImpact() {
        if (!this.world.isRemote)
        {
    	    	
    	    this.world.spawnEntity(EntityNukeExplosionMK5.statFac(world, BombConfig.fatmanRadius, posX, posY, posZ));
    	    
    	    if(MainRegistry.polaroidID == 11 || rand.nextInt(100) == 0){
                EntityNukeTorex.statFacBale(world, this.posX, this.posY, this.posZ, BombConfig.fatmanRadius);
            } else {
                EntityNukeTorex.statFac(world, this.posX, this.posY, this.posZ, BombConfig.fatmanRadius);
            }
        }
	}

	@Override
	public List<ItemStack> getDebris() {
		final List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.wire_aluminium, 4));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 4));
		list.add(ItemStackUtil.itemStackFrom(ModItems.hull_small_aluminium, 2));
		list.add(ItemStackUtil.itemStackFrom(ModItems.ducttape, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier1, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModItems.ammo_nuke, 1);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER0;
	}
}
