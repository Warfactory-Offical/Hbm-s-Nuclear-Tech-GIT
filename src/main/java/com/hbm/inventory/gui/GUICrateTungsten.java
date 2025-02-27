package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerCrateTungsten;
import com.hbm.lib.RefStrings;
import com.hbm.lib.Library;
import com.hbm.tileentity.machine.TileEntityCrateTungsten;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUICrateTungsten extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/storage/gui_crate_tungsten.png");
	private static final ResourceLocation texture_hot = new ResourceLocation(RefStrings.MODID + ":textures/gui/storage/gui_crate_tungsten_hot.png");
	private final TileEntityCrateTungsten diFurnace;
	
	public GUICrateTungsten(final InventoryPlayer invPlayer, final TileEntityCrateTungsten tedf) {
		super(new ContainerCrateTungsten(invPlayer, tedf));
		diFurnace = tedf;
		
		this.xSize = 176;
		this.ySize = 168;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String title = I18n.format("container.crateTungsten");
		this.fontRenderer.drawString(title, this.xSize / 2 - this.fontRenderer.getStringWidth(title) / 2, 6, diFurnace.heatTimer == 0 ? 0xA0A0A0 : 0xFFCA53);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, diFurnace.heatTimer == 0 ? 0xA0A0A0 : 0xFFCA53);
		final String sparks = Library.getShortNumber(diFurnace.joules) + "SPK";
		this.fontRenderer.drawString(sparks, this.xSize - 8 -this.fontRenderer.getStringWidth(sparks), this.ySize - 96 + 2, diFurnace.heatTimer == 0 ? 0xA0A0A0 : 0xFFCA53);
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks){
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		if(diFurnace.heatTimer == 0)
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		else
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture_hot);
		
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
