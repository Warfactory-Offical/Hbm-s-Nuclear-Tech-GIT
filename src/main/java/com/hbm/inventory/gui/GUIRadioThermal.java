package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerRadioThermal;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityHeaterRadioThermal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIRadioThermal extends GuiInfoContainer {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/machine/gui_heater_rtg.png");
	private final TileEntityHeaterRadioThermal heater;
	
	public GUIRadioThermal(final InventoryPlayer invPlayer, final TileEntityHeaterRadioThermal tedf) {
		super(new ContainerRadioThermal(invPlayer, tedf));
		heater = tedf;
		
		this.xSize = 176;
		this.ySize = 176;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);

		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 152, guiTop + 69 + 4 - 52, 16, 52, mouseX, mouseY, new String[]{String.format("%,d", Math.min(heater.heatEnergy, TileEntityHeaterRadioThermal.maxHeatEnergy)) + " / " + String.format("%,d", TileEntityHeaterRadioThermal.maxHeatEnergy) + " TU"});
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 134, guiTop + 69 + 4 - 52, 16, 52, mouseX, mouseY, new String[]{String.format("%,d", heater.heatGen) + " TU/t"});
		
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.heater.hasCustomInventoryName() ? this.heater.getInventoryName() : I18n.format(this.heater.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if(heater.hasHeatGen()){
			final int i = heater.getHeatGenScaled(52);
			drawTexturedModalRect(guiLeft + 134, guiTop + 74 - i, 176, 52 - i, 16, i);
		}
		if(heater.hasHeat()){
			final int i = heater.getHeatScaled(52);
			drawTexturedModalRect(guiLeft + 152, guiTop + 74 - i, 192, 52 - i, 16, i);
		}
	}
}
