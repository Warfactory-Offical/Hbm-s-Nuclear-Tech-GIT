package com.hbm.tileentity.machine.pile;

import com.hbm.blocks.ModBlocks;

public class TileEntityPileSource extends TileEntityPileBase {

	@Override
	public void update() {
		if(!world.isRemote) {
			
			final int n = this.getBlockType() == ModBlocks.block_graphite_source ? 1 : 3;
			
			for(int i = 0; i < 16; i++) {
				this.castRay(n, 5);
			}
		}
	}
}
