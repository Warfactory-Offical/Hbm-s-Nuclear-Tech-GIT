package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.explosion.ExplosionChaos;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityMissileInferno extends EntityMissileBaseAdvanced {

	public EntityMissileInferno(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(2F, 18F);
	}

	public EntityMissileInferno(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(2F, 18F);
	}

	@Override
	public void onImpact() {
		ExplosionLarge.explodeFire(world, this.posX + 0.5F, this.posY + 0.5F, this.posZ + 0.5F, 40.0F, true, true, true);
		ExplosionChaos.burn(this.world, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 10);
		ExplosionChaos.flameDeath(this.world, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 25);
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
		return ItemStackUtil.itemStackFrom(ModItems.warhead_incendiary_large);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER3;
	}
}
