package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.forgefluid.FFUtils;
import com.hbm.inventory.container.ContainerPuF6Tank;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachinePuF6Tank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachinePuF6Tank extends GuiInfoContainer {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/puf6Tank.png");
	private final TileEntityMachinePuF6Tank tank;
	
	public GUIMachinePuF6Tank(final InventoryPlayer invPlayer, final TileEntityMachinePuF6Tank tedf) {
		super(new ContainerPuF6Tank(invPlayer, tedf));
		tank = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);
		
		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 80, guiTop + 69 - 52, 16, 52, tank.tank, tank.tankType);
		super.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.tank.hasCustomInventoryName() ? this.tank.getInventoryName() : I18n.format(this.tank.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		FFUtils.drawLiquid(tank.tank, guiLeft, guiTop, zLevel, 16, 52, 80, 97);
	}
}
