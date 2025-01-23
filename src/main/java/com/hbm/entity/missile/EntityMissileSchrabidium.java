package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileSchrabidium extends EntityMissileBaseAdvanced {

	public EntityMissileSchrabidium(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 7F);
	}

	public EntityMissileSchrabidium(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(1F, 7F);
	}

	@Override
	public void onImpact() {
        if (!this.world.isRemote)
        {
			final EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(this.world);
			entity.posX = this.posX;
			entity.posY = this.posY;
			entity.posZ = this.posZ;
			if(!EntityNukeExplosionMK3.isJammed(this.world, entity)){
				entity.destructionRange = BombConfig.aSchrabRadius;
				entity.speed = 25;
				entity.coefficient = 1.0F;
				entity.waste = false;

				this.world.spawnEntity(entity);

				final EntityCloudFleija cloud = new EntityCloudFleija(this.world, BombConfig.aSchrabRadius);
				cloud.posX = this.posX;
				cloud.posY = this.posY;
				cloud.posZ = this.posZ;
				this.world.spawnEntity(cloud);
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
		return ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium, 1);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER0;
	}
}
