package com.hbm.inventory.gui;

import com.hbm.tileentity.turret.TileEntityTurretBaseNT;

import net.minecraft.entity.player.InventoryPlayer;

public class GUITurretChekhov extends GUITurretBase {

	public GUITurretChekhov(final InventoryPlayer invPlayer, final TileEntityTurretBaseNT tedf) {
		super(invPlayer, tedf);
	}

	@Override
	public int getTurretFontColor(){
		return 0x373737;
	}
}
