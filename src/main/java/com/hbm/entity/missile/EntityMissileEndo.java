package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.explosion.ExplosionThermo;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileEndo extends EntityMissileBaseAdvanced {

	public EntityMissileEndo(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1.25F, 10F);
	}

	public EntityMissileEndo(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(1.25F, 10F);
	}

	@Override
	public void onImpact() {
		ExplosionThermo.freeze(this.world, (int)this.posX, (int)this.posY, (int)this.posZ, 30);
		ExplosionThermo.freezer(this.world, (int)this.posX, (int)this.posY, (int)this.posZ, 40);
	}

	@Override
	public List<ItemStack> getDebris() {
		final List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 10));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 14));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_aluminium, 8));
		list.add(ItemStackUtil.itemStackFrom(ModItems.thruster_large, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier4, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModItems.warhead_thermo_exo);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER3;
	}
}
