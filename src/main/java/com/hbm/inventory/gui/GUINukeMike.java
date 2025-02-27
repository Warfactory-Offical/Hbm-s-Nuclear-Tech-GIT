package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.config.BombConfig;
import com.hbm.inventory.container.ContainerNukeMike;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.bomb.TileEntityNukeMike;
import com.hbm.items.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;


public class GUINukeMike extends GuiInfoContainer {
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/ivyMikeSchematic.png");
	private final TileEntityNukeMike testNuke;
	
	public GUINukeMike(final InventoryPlayer invPlayer, final TileEntityNukeMike tedf) {
		super(new ContainerNukeMike(invPlayer, tedf));
		testNuke = tedf;
		
		this.xSize = 176;
		this.ySize = 217;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.testNuke.hasCustomInventoryName() ? this.testNuke.getInventoryName() : I18n.format(this.testNuke.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		String[] info = null;
		if(testNuke.isReady() && !testNuke.isFilled())
			info = new String[] { "Nuke Radius: "+ BombConfig.manRadius +"m"};
		else if(testNuke.isReady() && testNuke.isFilled())
			info = new String[] { "Nuke Radius: "+ BombConfig.mikeRadius + "m"};
		if(info != null)
			this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 4, guiTop + 13, 168, 60, mouseX, mouseY, info);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if(testNuke.isReady() && !testNuke.isFilled())
		{
			drawTexturedModalRect(guiLeft + 5, guiTop + 35, 177, 1, 16, 16);
		}

		if(testNuke.isReady() && testNuke.isFilled())
		{
			drawTexturedModalRect(guiLeft + 5, guiTop + 35, 177, 19, 16, 16);
		}
		
		if(testNuke.inventory.getStackInSlot(5) != null && testNuke.inventory.getStackInSlot(5).getItem() == ModItems.mike_core)
			drawTexturedModalRect(guiLeft + 75, guiTop + 25, 176, 49, 80, 36);
		
		if(testNuke.inventory.getStackInSlot(6) != null && testNuke.inventory.getStackInSlot(6).getItem() == ModItems.mike_deut)
			drawTexturedModalRect(guiLeft + 79, guiTop + 30, 180, 88, 58, 26);
		
		if(testNuke.inventory.getStackInSlot(7) != null && testNuke.inventory.getStackInSlot(7).getItem() == ModItems.mike_cooling_unit)
			drawTexturedModalRect(guiLeft + 140, guiTop + 30, 240, 88, 12, 26);
		
		for(int i = 0; i < 4; i++) {
			if(testNuke.inventory.getStackInSlot(i) != null && testNuke.inventory.getStackInSlot(i).getItem() == ModItems.man_explosive8)
				switch(i) {
				case 0: drawTexturedModalRect(guiLeft + 24, guiTop + 20 , 209, 1, 23, 23); break;
				case 2: drawTexturedModalRect(guiLeft + 47, guiTop + 20 , 232, 1, 23, 23); break;
				case 1: drawTexturedModalRect(guiLeft + 24, guiTop + 43 , 209, 24, 23, 23); break;
				case 3: drawTexturedModalRect(guiLeft + 47, guiTop + 43 , 232, 24, 23, 23); break;
				}
		}
	}

}