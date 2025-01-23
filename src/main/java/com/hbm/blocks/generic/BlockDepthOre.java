package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import com.hbm.items.meta.materials.MaterialMineral;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockDepthOre extends BlockDepth {
	
	public BlockDepthOre(final String s){
		super(s);
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
		if(this == ModBlocks.cluster_depth_iron) {
			return ModItems.crystal_iron;
		}
		if(this == ModBlocks.cluster_depth_titanium) {
			return ModItems.crystal_titanium;
		}
		if(this == ModBlocks.cluster_depth_tungsten) {
			return ModItems.crystal_tungsten;
		}
		if(this == ModBlocks.ore_depth_cinnebar) {
			return ModItems.cinnebar;
		}
		if(this == ModBlocks.ore_depth_zirconium) {
			return ModItems.nugget;
		}
		if(this == ModBlocks.ore_depth_nether_neodymium) {
			return ModItems.fragment_neodymium;
		}
		if(this == ModBlocks.ore_depth_nether_nitan) {
			return ModItems.powder_nitan_mix;
		}
		
		return super.getItemDropped(state, rand, fortune);
	}
	
	@Override
	public int quantityDropped(final Random rand) {
		
		if(this == ModBlocks.ore_depth_cinnebar) {
			return 2 + rand.nextInt(3);
		}
		if(this == ModBlocks.ore_depth_zirconium) {
			return 2 + rand.nextInt(2);
		}
		if(this == ModBlocks.ore_depth_nether_neodymium) {
			return 2 + rand.nextInt(2);
		}
		if(this == ModBlocks.ore_depth_nether_nitan) {
			return 1;
		}
		
		return super.quantityDropped(rand);
	}

	@Override
	public int damageDropped(final IBlockState state) {
		if(this == ModBlocks.ore_depth_zirconium) {
			return ModItems.nugget.getItemStack(MaterialMineral.ZIRCONIUM).getMetadata();
		}
		return super.damageDropped(state);
	}
}