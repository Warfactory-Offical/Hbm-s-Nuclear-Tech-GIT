package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerBook;
import com.hbm.lib.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIBook extends GuiContainer {

	public static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/processing/gui_book.png");
	
	public GUIBook(final InventoryPlayer player) {
		super(new ContainerBook(player));
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
		Minecraft.getMinecraft().standardGalacticFontRenderer.drawString("Extended 4-Slot Crafting", 28, 6, 4210752);
    	Minecraft.getMinecraft().standardGalacticFontRenderer.drawString("Standard Inventory", 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		super.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(texture);
        final int left = (this.width - this.xSize) / 2;
        final int top = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(left, top, 0, 0, this.xSize, this.ySize);

        if(this.inventorySlots.getSlot(0).getHasStack())
            this.drawTexturedModalRect(left + 29, top + 16, 176, 0, 54, 54);
		
	}
	
}
