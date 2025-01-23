package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.explosion.ExplosionChaos;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileCluster extends EntityMissileBaseAdvanced {

	public EntityMissileCluster(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 6F);
	}

	public EntityMissileCluster(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.isCluster = true;
		this.setSize(1F, 6F);
	}

	@Override
	public void onImpact() {
        ExplosionChaos.cluster(this.world, (int)this.posX, (int)this.posY, (int)this.posZ, 25, 0.25);
	}
	
	@Override
	public void cluster() {
		this.onImpact();
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
		return ItemStackUtil.itemStackFrom(ModItems.warhead_cluster_small);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER1;
	}

}
