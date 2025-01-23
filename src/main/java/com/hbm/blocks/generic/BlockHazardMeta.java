package com.hbm.blocks.generic;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.SoundType;
import net.minecraft.world.World;

public class BlockHazardMeta extends BlockHazard {
	
	public static final PropertyInteger META = PropertyInteger.create("meta", 0, 15);
	
	public BlockHazardMeta(final Material m, final String s){
		super(m, s);
	}

	public BlockHazardMeta(final Material mat, final SoundType type, final String s) {
		super(mat, type, s);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, META);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(META);
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState().withProperty(META, meta);
	}
}
