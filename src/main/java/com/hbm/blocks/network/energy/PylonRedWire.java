package com.hbm.blocks.network.energy;

import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.network.energy.TileEntityPylon;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PylonRedWire extends PylonBase {

	public PylonRedWire(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityPylon();
	}
}
