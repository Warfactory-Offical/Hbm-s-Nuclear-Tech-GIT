package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.forgefluid.FFUtils;
import com.hbm.inventory.container.ContainerAMSLimiter;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityAMSLimiter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIAMSLimiter extends GuiInfoContainer {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_ams_limiter.png");
	private final TileEntityAMSLimiter limiter;
	
	public GUIAMSLimiter(final InventoryPlayer invPlayer, final TileEntityAMSLimiter tedf) {
		super(new ContainerAMSLimiter(invPlayer, tedf));
		limiter = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);

		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 26, guiTop + 69 - 52, 16, 52, limiter.tank, limiter.tankType);
		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 134, guiTop + 69 - 52, 16, 52, limiter.power, TileEntityAMSLimiter.maxPower);
		this.drawCustomInfo(this, mouseX, mouseY, guiLeft + 152, guiTop + 69 - 52, 16, 52, new String[] { "Efficiency:", limiter.efficiency + "%" });
		this.drawCustomInfo(this, mouseX, mouseY, guiLeft + 8, guiTop + 69 - 52, 16, 52, new String[] { "Heat:", limiter.heat + "/" + TileEntityAMSLimiter.maxHeat });
		super.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.limiter.hasCustomInventoryName() ? this.limiter.getInventoryName() : I18n.format(this.limiter.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		final int i = (int) limiter.getPowerScaled(52);
		drawTexturedModalRect(guiLeft + 134, guiTop + 69 - i, 192, 52 - i, 16, i);
		
		final int j = limiter.getEfficiencyScaled(52);
		drawTexturedModalRect(guiLeft + 152, guiTop + 69 - j, 208, 52 - j, 16, j);
		
		final int k = limiter.getHeatScaled(52);
		drawTexturedModalRect(guiLeft + 8, guiTop + 69 - k, 176, 52 - k, 16, k);
		
		final int l = limiter.mode;
		if(l > 0)
			drawTexturedModalRect(guiLeft + 98, guiTop + 17, 176, 84 + 16 * l, 16, 16);
		
		final int m = limiter.warning;
		if(m > 0)
			drawTexturedModalRect(guiLeft + 80, guiTop + 17, 176, 36 + 16 * m, 16, 16);
		
		FFUtils.drawLiquid(limiter.tank, guiLeft, guiTop, zLevel, 16, 52, 26, 97);
	}
}