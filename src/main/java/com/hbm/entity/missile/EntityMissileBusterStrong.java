package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileBusterStrong extends EntityMissileBaseAdvanced {

	public EntityMissileBusterStrong(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1.5F, 11F);
	}

	public EntityMissileBusterStrong(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(1.5F, 11F);
	}

	@Override
	public void onImpact() {
		ExplosionLarge.buster(world, this.posX, this.posY, this.posZ, Vec3.createVectorHelper(motionX, motionY, motionZ), 20, 20);
	}

	@Override
	public List<ItemStack> getDebris() {
		final List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 10));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 6));
		list.add(ItemStackUtil.itemStackFrom(ModItems.thruster_medium, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier2, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModItems.warhead_buster_medium);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER2;
	}
}
