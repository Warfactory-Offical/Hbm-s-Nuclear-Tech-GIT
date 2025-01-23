package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerMachineTeleLinker;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachineTeleLinker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachineTeleLinker extends GuiInfoContainer {
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_telelinker.png");
	private final TileEntityMachineTeleLinker siren;

	public GUIMachineTeleLinker(final InventoryPlayer invPlayer, final TileEntityMachineTeleLinker tedf) {
		super(new ContainerMachineTeleLinker(invPlayer, tedf));
		siren = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);

		final String[] text = new String[] { "The first slot will copy the turret chip's",
				"UUIDs and add them to the second slot." };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36, 16, 16, guiLeft - 8, guiTop + 36 + 16, text);
		
		final String[] text1 = new String[] { "The third slot will clear the",
				"turret chip's UUID list."};
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36 + 16, 16, 16, guiLeft - 8, guiTop + 36 + 16, text1);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.siren.hasCustomInventoryName() ? this.siren.getInventoryName() : I18n.format(this.siren.getInventoryName());
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		this.drawInfoPanel(guiLeft - 16, guiTop + 36, 16, 16, 2);
		this.drawInfoPanel(guiLeft - 16, guiTop + 36 + 16, 16, 16, 3);
	}
}
