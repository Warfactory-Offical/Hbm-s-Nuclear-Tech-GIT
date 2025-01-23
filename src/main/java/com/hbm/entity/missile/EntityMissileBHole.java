package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.entity.effect.EntityBlackHole;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileBHole extends EntityMissileBaseAdvanced {

	public EntityMissileBHole(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 7F);
	}

	public EntityMissileBHole(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(1F, 7F);
	}

	@Override
	public void onImpact() {
        if (!this.world.isRemote)
        {
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1.5F, true);

        	final EntityBlackHole bl = new EntityBlackHole(this.world, 1.5F);
        	bl.posX = this.posX;
        	bl.posY = this.posY;
        	bl.posZ = this.posZ;
        	this.world.spawnEntity(bl);
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
		return ItemStackUtil.itemStackFrom(ModItems.grenade_black_hole, 1);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER0;
	}
}
