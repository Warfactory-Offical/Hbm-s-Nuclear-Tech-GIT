package com.hbm.blocks.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.tileentity.machine.TileEntitySpacer;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class FractionSpacer extends BlockDummyable {

	public FractionSpacer(final Material mat, final String s) {
		super(mat, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World p_149915_1_, final int meta) {
		if(meta >= 12)
			return new TileEntitySpacer();
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {0, 0, 1, 1, 1, 1};
	}

	@Override
	public int getOffset() {
		return 1;
	}
}
