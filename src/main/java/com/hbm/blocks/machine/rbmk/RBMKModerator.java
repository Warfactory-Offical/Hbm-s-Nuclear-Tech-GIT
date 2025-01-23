package com.hbm.blocks.machine.rbmk;

import com.hbm.tileentity.machine.rbmk.TileEntityRBMKModerator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class RBMKModerator extends RBMKBase {

	public RBMKModerator(final String s, final String c){
		super(s, c);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		if(meta >= offset)
			return new TileEntityRBMKModerator();
		return null;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state){
		return EnumBlockRenderType.MODEL;
	}
}
