package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerMachineEPress;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachineEPress;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachineEPress extends GuiInfoContainer {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_epress.png");
	private final TileEntityMachineEPress press;
	
	public GUIMachineEPress(final InventoryPlayer invPlayer, final TileEntityMachineEPress tedf) {
		super(new ContainerMachineEPress(invPlayer, tedf));
		press = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);
		
		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 26, guiTop + 69 - 52, 16, 52, press.power, TileEntityMachineEPress.maxPower);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.press.hasCustomInventoryName() ? this.press.getInventoryName() : I18n.format(this.press.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		final int i = (int)press.getPowerScaled(52);
		drawTexturedModalRect(guiLeft + 26, guiTop + 69 - i, 176, 52 - i, 16, i);
		
		final int k = press.getProgressScaled(16);
        this.drawTexturedModalRect(guiLeft + 79, guiTop + 35, 192, 0, 18, k);
	}
}
