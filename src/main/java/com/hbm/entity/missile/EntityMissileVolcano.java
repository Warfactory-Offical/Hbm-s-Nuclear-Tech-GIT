package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityMissileVolcano extends EntityMissileBaseAdvanced {

	public EntityMissileVolcano(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1F, 11F);
	}

	public EntityMissileVolcano(final World world, final float x, final float y, final float z, final int a, final int b) {
		super(world, x, y, z, a, b);
		this.setSize(1F, 11F);
	}

	@Override
	public void onImpact() {
		
		ExplosionLarge.explode(world, posX, posY, posZ, 10.0F, true, true, true);
		
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++) {
				for(int z = -1; z <= 1; z++) {
					world.setBlockState(new BlockPos((int)Math.floor(posX + x), (int)Math.floor(posY + y), (int)Math.floor(posZ + z)), ModBlocks.volcanic_lava_block.getDefaultState());
				}
			}
		}
		
		world.setBlockState(new BlockPos((int)Math.floor(posX), (int)Math.floor(posY), (int)Math.floor(posZ)), ModBlocks.volcano_core.getDefaultState());
	}

	@Override
	public List<ItemStack> getDebris() {
		final List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 16));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 20));
		list.add(ItemStackUtil.itemStackFrom(ModItems.plate_aluminium, 12));
		list.add(ItemStackUtil.itemStackFrom(ModItems.thruster_large, 1));
		list.add(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier4, 1));

		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return ItemStackUtil.itemStackFrom(ModItems.warhead_volcano);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER4;
	}
}