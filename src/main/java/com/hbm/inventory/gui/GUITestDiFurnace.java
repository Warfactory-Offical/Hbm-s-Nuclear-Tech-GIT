package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerDiFurnace;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityDiFurnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUITestDiFurnace extends GuiInfoContainer {
	public static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/GUIDiFurnace.png");
	private final TileEntityDiFurnace diFurnace;

	public GUITestDiFurnace(final InventoryPlayer invPlayer, final TileEntityDiFurnace tedf) {
		super(new ContainerDiFurnace(invPlayer, tedf));
		diFurnace = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = I18n.format(this.diFurnace.getName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if(diFurnace.hasPower())
		{
			final int i1 = diFurnace.getPowerRemainingScaled(52);
			drawTexturedModalRect(guiLeft + 44, guiTop + 70 - i1, 201, 53 - i1, 16, i1);
		}
		
		final int j1 = diFurnace.getDiFurnaceProgressScaled(24);
		if(diFurnace.isProcessing())
			drawTexturedModalRect(guiLeft + 101, guiTop + 35, 176, 14, j1 + 1, 17);
		
		if(diFurnace.hasPower() && diFurnace.canProcess()) {
			drawTexturedModalRect(guiLeft + 63, guiTop + 37, 176, 0, 14, 14);
		}
		
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCustomInfoStat(mouseX, mouseY, guiLeft + 43, guiTop + 17, 18, 54, mouseX, mouseY, new String[] {diFurnace.getPowerRemainingScaled(100) + "%"});
		super.renderHoveredToolTip(mouseX, mouseY);
	}
}
