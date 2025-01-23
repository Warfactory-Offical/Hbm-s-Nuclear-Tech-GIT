package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerMachineRTG;
import com.hbm.lib.RefStrings;
import com.hbm.lib.Library;
import com.hbm.tileentity.machine.TileEntityMachineRTG;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachineRTG extends GuiInfoContainer {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_rtg.png");
	private final TileEntityMachineRTG rtg;
	
	public GUIMachineRTG(final InventoryPlayer invPlayer, final TileEntityMachineRTG tedf) {
		super(new ContainerMachineRTG(invPlayer, tedf));
		rtg = tedf;
		
		this.xSize = 176;
		this.ySize = 176;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);

		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 152, guiTop + 69 - 52, 16, 52, rtg.power, rtg.maxPower);
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 134, guiTop + 69 - 52, 16, 52, mouseX+8, mouseY-8, new String[] {"RTG Heat " + rtg.heat + "/" + rtg.heatMax, "RTG Power " + Library.getShortNumber(rtg.heat* 100L)+"HE/s"});
		
		final String[] text = new String[] { "Heat to Power Conversion 1:5" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36, 16, 16, guiLeft - 8, guiTop + 36 + 16, text);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.rtg.hasCustomInventoryName() ? this.rtg.getInventoryName() : I18n.format(this.rtg.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if(rtg.hasHeat())
		{
			final int i = rtg.getHeatScaled(52);
			drawTexturedModalRect(guiLeft + 134, guiTop + 74 - i, 176, 52 - i, 16, i);
		}
		if(rtg.hasPower())
		{
			final int i = (int)rtg.getPowerScaled(52);
			drawTexturedModalRect(guiLeft + 152, guiTop + 74 - i, 192, 52 - i, 16, i);
		}
		
		this.drawInfoPanel(guiLeft - 16, guiTop + 36, 16, 16, 2);
	}
}
