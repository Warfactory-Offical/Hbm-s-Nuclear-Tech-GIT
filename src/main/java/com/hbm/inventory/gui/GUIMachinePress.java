package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerMachinePress;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachinePress;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachinePress extends GuiContainer {
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_press.png");
	private final TileEntityMachinePress assembler;
	
	public GUIMachinePress(final InventoryPlayer invPlayer, final TileEntityMachinePress tedf) {
		super(new ContainerMachinePress(invPlayer, tedf));
		assembler = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = I18n.format(this.assembler.getName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		this.drawDefaultBackground();
		this.mc.getTextureManager().bindTexture(texture);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		final int i = assembler.getPowerScaled(12);
		drawTexturedModalRect(guiLeft + 25, guiTop + 16, 176, 14 + 18 * i, 18, 18);
		
		final int l = assembler.getBurnScaled(13);
        this.drawTexturedModalRect(guiLeft + 27, guiTop + 49 - l, 176, 13 - l, 13, l);
		
		final int k = assembler.getProgressScaled(16);
        this.drawTexturedModalRect(guiLeft + 79, guiTop + 35, 194, 0, 18, k);
	}
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}
