package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileDrill extends EntityMissileBaseAdvanced {

	public EntityMissileDrill(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(2F, 18F);
	}

	public EntityMissileDrill(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(2F, 18F);
	}

	@Override
	public void onImpact() {
		ExplosionLarge.buster(world, this.posX, this.posY, this.posZ, Vec3.createVectorHelper(motionX, motionY, motionZ), 30, 30);
	}

	@Override
	public List<ItemStack> getDebris() {
		final List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 16));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 10));
		list.add(ItemStackUtil.itemStackFrom(ModItems.thruster_large, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier3, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModItems.warhead_buster_large);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER3;
	}
}
