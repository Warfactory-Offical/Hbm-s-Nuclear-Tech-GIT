package com.hbm.inventory.gui;

import com.hbm.forgefluid.FFUtils;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.turret.TileEntityTurretBaseNT;
import com.hbm.tileentity.turret.TileEntityTurretFritz;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;

public class GUITurretFritz extends GUITurretBase {
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/weapon/gui_turret_fritz.png");

	public GUITurretFritz(final InventoryPlayer invPlayer, final TileEntityTurretBaseNT tedf) {
		super(invPlayer, tedf);
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);
		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 134, guiTop + 63, 7, 52, ((TileEntityTurretFritz)this.turret).tank);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int mX, final int mY) {
		super.drawGuiContainerBackgroundLayer(p_146976_1_, mX, mY);
		
		final FluidTank tank = ((TileEntityTurretFritz)this.turret).tank;
		
		FFUtils.drawLiquid(tank, guiLeft, guiTop, zLevel, 7, 52, 134, 143);
	}
	
	protected ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public void drawAmmo(final int mouseX, final int mouseY){
	}
}
