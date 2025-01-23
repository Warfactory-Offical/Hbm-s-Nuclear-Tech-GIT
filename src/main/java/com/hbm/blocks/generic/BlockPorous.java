package com.hbm.blocks.generic;

import java.util.Random;

import com.google.common.base.Predicate;
import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.Untested;
import com.hbm.saveddata.RadiationSavedData;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPorous extends Block {

	public BlockPorous(final String s) {
		super(Material.ROCK);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHardness(1.5F); //stone tier
		this.setResistance(30.0F); //ha
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Untested
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
		//in theory this should keep the block silk-harvestable, but dropping smooth stone instead
		return Item.getItemFromBlock(Blocks.STONE);
	}
	
	@Override
	public void onBlockAdded(final World world, final BlockPos pos, final IBlockState state){
		super.onBlockAdded(world, pos, state);
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}
	
	@Override
	public int tickRate(final World world) {
		return 90 + world.rand.nextInt(20);
	}

	@Override
	public void updateTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand){
		RadiationSavedData.decrementRad(worldIn, pos, 10F);
	}
	
	@Override
	public boolean isReplaceableOreGen(final IBlockState state, final IBlockAccess world, final BlockPos pos, final Predicate<IBlockState> target){
		return target.apply(this.getDefaultState()) || target.apply(Blocks.STONE.getDefaultState());
	}
	
	@Override
	public int getMetaFromState(final IBlockState state){
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta){
		return this.getDefaultState();
	}
	
}