package com.hbm.tileentity.machine.rbmk;

import com.hbm.entity.projectile.EntityRBMKDebris.DebrisType;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.ColumnType;

public class TileEntityRBMKModerator extends TileEntityRBMKBase {
	
	@Override
	public void onMelt(final int reduce) {
		
		final int count = 2 + world.rand.nextInt(2);
		
		for(int i = 0; i < count; i++) {
			spawnDebris(DebrisType.GRAPHITE);
		}
		
		super.onMelt(reduce);
	}

	@Override
	public ColumnType getConsoleType() {
		return ColumnType.MODERATOR;
	}
}
