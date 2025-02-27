package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileNuclear extends EntityMissileBaseAdvanced {

	public EntityMissileNuclear(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 11F);
	}

	public EntityMissileNuclear(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(1F, 11F);
	}

	@Override
	public void onImpact() {
   	
    	this.world.spawnEntity(EntityNukeExplosionMK5.statFac(world, BombConfig.missileRadius, posX, posY, posZ));
    	if(BombConfig.enableNukeClouds) {
			EntityNukeTorex.statFac(world, this.posX, this.posY, this.posZ, BombConfig.missileRadius);
		}
	}

	@Override
	public List<ItemStack> getDebris() {
		final List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 16));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 20));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_aluminium, 12));
		list.add(ItemStackUtil.itemStackFrom(ModItems.thruster_large, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier4, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModItems.warhead_nuclear);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER4;
	}
}
