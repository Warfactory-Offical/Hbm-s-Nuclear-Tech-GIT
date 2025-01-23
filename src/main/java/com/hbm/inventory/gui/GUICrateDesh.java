package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerCrateDesh;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityCrateDesh;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUICrateDesh extends GuiContainer {
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/storage/gui_crate_desh.png");
	private final TileEntityCrateDesh diFurnace;

	public GUICrateDesh(final InventoryPlayer invPlayer, final TileEntityCrateDesh tedf) {
		super(new ContainerCrateDesh(invPlayer, tedf));
		diFurnace = tedf;
		
		this.xSize = 248;
		this.ySize = 256;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.diFurnace.hasCustomInventoryName() ? this.diFurnace.getInventoryName() : I18n.format(this.diFurnace.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 0x3F1515);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 44, this.ySize - 96 + 2, 0x3F1515);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}