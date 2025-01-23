package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerRBMKStorage;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIRBMKStorage extends GuiContainer {
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/reactors/gui_rbmk_storage.png");
	private final TileEntityRBMKStorage rod;

	public GUIRBMKStorage(final InventoryPlayer invPlayer, final TileEntityRBMKStorage tedf) {
		super(new ContainerRBMKStorage(invPlayer, tedf));
		rod = tedf;
		
		this.xSize = 176;
		this.ySize = 186;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = I18n.format(this.rod.getName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
