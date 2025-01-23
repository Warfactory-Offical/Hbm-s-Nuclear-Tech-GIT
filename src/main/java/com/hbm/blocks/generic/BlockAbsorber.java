package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.saveddata.RadiationSavedData;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAbsorber extends Block {

	float absorb = 0;
	
	public BlockAbsorber(final Material materialIn, final float ab, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setTickRandomly(true);
		absorb = ab;
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public int tickRate(final World worldIn) {
		return 10;
	}

	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand) {
		RadiationSavedData.decrementRad(world, pos, absorb);

    	world.scheduleUpdate(pos, this, this.tickRate(world));
	}
	
	@Override
	public void onBlockAdded(final World worldIn, final BlockPos pos, final IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
}
