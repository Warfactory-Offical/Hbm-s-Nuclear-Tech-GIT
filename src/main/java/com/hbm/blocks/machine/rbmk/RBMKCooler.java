package com.hbm.blocks.machine.rbmk;

import com.hbm.tileentity.machine.rbmk.TileEntityRBMKCooler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class RBMKCooler extends RBMKBase {

	public RBMKCooler(final String s, final String c){
		super(s, c);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		if(meta >= offset)
			return new TileEntityRBMKCooler();

		return null;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state){
		return EnumBlockRenderType.MODEL;
	}
}
