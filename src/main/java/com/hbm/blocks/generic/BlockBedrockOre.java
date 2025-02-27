package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import api.hbm.block.IDrillInteraction;
import api.hbm.block.IMiningDrill;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockBedrockOre extends Block implements IDrillInteraction {

	public BlockBedrockOre(final String s) {
		super(Material.ROCK);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public boolean canBreak(final World world, final int x, final int y, final int z, final IBlockState state, final IMiningDrill drill) {
		return drill.getDrillRating() > 70;
	}

	@Override
	public ItemStack extractResource(final World world, final int x, final int y, final int z, final IBlockState state, final IMiningDrill drill) {
		
		if(drill.getDrillRating() > 70)
			return null;
		
		final Item drop = this.getDrop();
		
		if(drop == null)
			return null;
		
		return world.rand.nextInt(50) == 0 ? ItemStackUtil.itemStackFrom(drop) : null;
	}

	@Override
	public float getRelativeHardness(final World world, final int x, final int y, final int z, final IBlockState state, final IMiningDrill drill) {
		return 30;
	}
	
	private Item getDrop() {

		if(this == ModBlocks.ore_bedrock_coltan)
			return ModItems.fragment_coltan;
		
		return null;
	}
}