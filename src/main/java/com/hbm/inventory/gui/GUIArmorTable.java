package com.hbm.inventory.gui;

import com.hbm.handler.ArmorModHandler;
import com.hbm.inventory.container.ContainerArmorTable;
import com.hbm.lib.RefStrings;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GUIArmorTable extends GuiContainer {

	public static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/machine/gui_armor_modifier.png");
	public int left;
	public int top;

	public GUIArmorTable(final InventoryPlayer player) {
		super(new ContainerArmorTable(player));

		this.xSize = 176;
		this.ySize = 222;

		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;
	}

	protected void drawGuiContainerForegroundLayer(final int mX, final int mY) {
		this.fontRenderer.drawString(I18n.format("container.armorTable"), 28, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	protected void drawGuiContainerBackgroundLayer(final float inter, final int mX, final int mY) {
		super.drawDefaultBackground();
		GlStateManager.color(1, 1, 1, 1);
		this.mc.getTextureManager().bindTexture(texture);

		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);

		final ItemStack armor = this.inventorySlots.getSlot(8).getStack();

		if(armor != null) {

			if(armor.getItem() instanceof ItemArmor)
				this.drawTexturedModalRect(guiLeft + 41, guiTop + 60, 176, 74, 22, 22);
			else
				this.drawTexturedModalRect(guiLeft + 41, guiTop + 60, 176, 52, 22, 22);
		}
		
		for(int i = 0; i < 8; i++) {
			final Slot slot = this.inventorySlots.getSlot(i);
			drawIndicator(i, slot.xPos - 1, slot.yPos - 1);
		}
	}

	private void drawIndicator(final int index, final int x, final int y) {
		final ItemStack mod = this.inventorySlots.getSlot(index).getStack();
		final ItemStack armor = this.inventorySlots.getSlot(8).getStack();

		if(mod.isEmpty())
			return;

		if(ArmorModHandler.isApplicable(armor, mod)) {
			this.drawTexturedModalRect(guiLeft + x, guiTop + y, 176, 34, 18, 18);
		} else {
			this.drawTexturedModalRect(guiLeft + x, guiTop + y, 176, 16, 18, 18);
		}
	}
}