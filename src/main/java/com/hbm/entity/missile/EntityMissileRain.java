package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.explosion.ExplosionLarge;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileRain extends EntityMissileBaseAdvanced {

	public EntityMissileRain(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 7F);
	}

	public EntityMissileRain(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.isCluster = true;
		this.setSize(1F, 7F);
	}

	@Override
	public void onImpact() {
        ExplosionChaos.cluster(this.world, (int)this.posX, (int)this.posY, (int)this.posZ, 100, 0.25, 10);
	}
	
	@Override
	public void cluster() {
		this.onImpact();
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
		return ItemStackUtil.itemStackFrom(ModItems.warhead_cluster_large);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER3;
	}
}
