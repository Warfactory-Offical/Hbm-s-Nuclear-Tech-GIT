package com.hbm.inventory.gui;

import com.hbm.lib.RefStrings;
import com.hbm.tileentity.turret.TileEntityTurretBaseNT;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUITurretMaxwell extends GUITurretBase {
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/weapon/gui_turret_maxwell.png");

	public GUITurretMaxwell(final InventoryPlayer invPlayer, final TileEntityTurretBaseNT tedf) {
		super(invPlayer, tedf);
	}
	
	protected ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public void drawAmmo(final int mouseX, final int mouseY){
	}

	@Override
	public int getTurretFontColor(){
		return 0x0C0C0C;
	}
}