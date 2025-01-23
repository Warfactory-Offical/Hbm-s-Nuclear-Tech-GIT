package com.hbm.blocks.generic;

import com.hbm.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBeaconable extends BlockBase {

	public BlockBeaconable(final Material m, final String s){
		super(m, s);
	}
	
	@Override
	public boolean isBeaconBase(final IBlockAccess worldObj, final BlockPos pos, final BlockPos beacon){
		return true;
	}

}
