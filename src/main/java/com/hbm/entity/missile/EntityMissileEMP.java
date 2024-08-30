package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.effect.EntityEMPBlast;
import com.hbm.explosion.ExplosionNukeGeneric;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileEMP extends EntityMissileBaseAdvanced {

	public EntityMissileEMP(World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 7F);
	}

	public EntityMissileEMP(World world, float x, float y, float z, int a, int b) {
		super(world, x, y, z, a, b);
		this.setSize(1F, 7F);
	}

	@Override
	public void onImpact() {
        if (!this.world.isRemote)
        {
			ExplosionNukeGeneric.empBlast(world, (int)posX, (int)posY, (int)posZ, 50);
			EntityEMPBlast wave = new EntityEMPBlast(world, 50);
			wave.posX = posX;
			wave.posY = posY;
			wave.posZ = posZ;
			world.spawnEntity(wave);
        }
	}

	@Override
	public List<ItemStack> getDebris() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.wire_aluminium, 4));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 4));
		list.add(ItemStackUtil.itemStackFrom(ModItems.hull_small_aluminium, 2));
		list.add(ItemStackUtil.itemStackFrom(ModItems.ducttape, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier1, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModBlocks.emp_bomb, 1);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER0;
	}
}
