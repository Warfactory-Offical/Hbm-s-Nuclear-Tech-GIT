package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.explosion.ExplosionChaos;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileClusterStrong extends EntityMissileBaseAdvanced {

	public EntityMissileClusterStrong(World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1.5F, 11F);
	}

	public EntityMissileClusterStrong(World world, float x, float y, float z, int a, int b) {
		super(world, x, y, z, a, b);
		this.isCluster = true;
		this.setSize(1.5F, 11F);
	}

	@Override
	public void onImpact() {
        ExplosionChaos.cluster(this.world, (int)this.posX, (int)this.posY, (int)this.posZ, 50, 0.25, 7);
	}
	
	@Override
	public void cluster() {
		this.onImpact();
	}

	@Override
	public List<ItemStack> getDebris() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 10));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 6));
		list.add(ItemStackUtil.itemStackFrom(ModItems.thruster_medium, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier2, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModItems.warhead_cluster_medium);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER2;
	}
}
