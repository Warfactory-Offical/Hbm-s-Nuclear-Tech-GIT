package com.hbm.blocks.machine.rbmk;

import com.hbm.tileentity.TileEntityProxyInventory;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKRodReaSim;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class RBMKRodReaSim extends RBMKRod {

	public RBMKRodReaSim(final boolean moderated, final String s, final String c) {
		super(moderated, s, c);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		
		if(meta >= offset)
			return new TileEntityRBMKRodReaSim();
		
		if(hasExtra(meta))
			return new TileEntityProxyInventory();
		
		return null;
	}
}
