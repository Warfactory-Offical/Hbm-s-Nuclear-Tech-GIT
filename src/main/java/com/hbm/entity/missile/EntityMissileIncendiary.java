package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileIncendiary extends EntityMissileBaseAdvanced {

	public EntityMissileIncendiary(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 6F);
	}

	public EntityMissileIncendiary(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(1F, 6F);
	}

	@Override
	public void onImpact() {
		ExplosionLarge.explodeFire(world, this.posX + 0.5F, this.posY + 0.5F, this.posZ + 0.5F, 10.0F, true, true, true);
	}

	@Override
	public List<ItemStack> getDebris() {
		final List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 4));
		list.add(ItemStackUtil.itemStackFrom(ModItems.thruster_small, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier1, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModItems.warhead_incendiary_small);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER1;
	}
}
