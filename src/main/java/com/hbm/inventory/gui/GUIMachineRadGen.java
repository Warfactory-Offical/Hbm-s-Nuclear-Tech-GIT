package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerMachineRadGen;
import com.hbm.lib.RefStrings;
import com.hbm.lib.Library;
import com.hbm.tileentity.machine.TileEntityMachineRadGen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachineRadGen extends GuiInfoContainer {
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_radgen.png");
	private final TileEntityMachineRadGen radgen;

	public GUIMachineRadGen(final InventoryPlayer invPlayer, final TileEntityMachineRadGen tedf) {
		super(new ContainerMachineRadGen(invPlayer, tedf));
		radgen = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);

		this.drawCustomInfo(this, mouseX, mouseY, guiLeft + 35, guiTop + 69 - 52, 16, 52, new String[] { "Fuel: " + Library.roundFloat(radgen.fuel * 100D/ TileEntityMachineRadGen.maxFuel, 3) + "%" });
		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 143, guiTop + 69 - 52, 16, 52, radgen.power, TileEntityMachineRadGen.maxPower);
		
		final String[] text = new String[] { "Accepted Fuels:",
				"  ANYTHING radioactive",
				"  so even waste like dead grass and contaminated items!",
				"  fuel amount of an item is sqrt(item radiation)" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36, 16, 16, guiLeft - 8, guiTop + 36 + 16, text);
		
		final String[] text1 = new String[] { "Power generation rate:",
				"  up to 200k HE/s",
				"(Generation rate depends on how much fuel there is)" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36 + 16, 16, 16, guiLeft - 8, guiTop + 36 + 16, text1);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.radgen.hasCustomInventoryName() ? this.radgen.getInventoryName() : I18n.format(this.radgen.getInventoryName());
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		final int i = (int)radgen.getPowerScaled(52);
		drawTexturedModalRect(guiLeft + 143, guiTop + 69 - i, 16, 218 - i, 16, i);
		
		final int j = radgen.getFuelScaled(52);
		drawTexturedModalRect(guiLeft + 35, guiTop + 69 - j, 0, 218 - j, 16, j);
		
		final int k = radgen.mode;
		if(k == 1)
			drawTexturedModalRect(guiLeft + 106, guiTop + 16, 32, 166, 18, 18);
		if(k == 2)
			drawTexturedModalRect(guiLeft + 106, guiTop + 16, 32, 184, 18, 18);
		
		final int l = radgen.getStrengthScaled(12);
		int sx = 140;
		int sy = 166;
		if(l > 0 && l < 7) {
			sx = 176;
			sy = (l - 1) * 36;
		}
		if(l > 6) {
			sx = 212;
			sy = (l - 7) * 36;
		}
		drawTexturedModalRect(guiLeft + 70, guiTop + 25, sx, sy, 36, 36);
		
		this.drawInfoPanel(guiLeft - 16, guiTop + 36, 16, 16, 2);
		this.drawInfoPanel(guiLeft - 16, guiTop + 36 + 16, 16, 16, 3);
	}
}
