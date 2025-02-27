package com.hbm.inventory.gui;

import com.hbm.inventory.container.ContainerFirebox;
import com.hbm.tileentity.machine.TileEntityFireboxBase;
import com.hbm.tileentity.machine.TileEntityHeaterOven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIFirebox extends GuiInfoContainer {
	
	private final TileEntityFireboxBase firebox;
	private final ResourceLocation texture;

	public GUIFirebox(final InventoryPlayer invPlayer, final TileEntityFireboxBase tedf, final ResourceLocation texture) {
		super(new ContainerFirebox(invPlayer, tedf));
		firebox = tedf;
		this.texture = texture;
		
		this.xSize = 176;
		this.ySize = 168;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float interp) {
		super.drawScreen(mouseX, mouseY, interp);

		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 80, guiTop + 27, 71, 7, mouseX, mouseY, new String[] { String.format("%,d", firebox.heatEnergy) + " / " + String.format("%,d", firebox.getMaxHeat()) + "TU" });
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 80, guiTop + 36, 71, 7, mouseX, mouseY, new String[] { firebox.burnHeat + "TU/t", (firebox.burnTime / 20) + "s" });
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.firebox.hasCustomInventoryName() ? this.firebox.getInventoryName() : I18n.format(this.firebox.getInventoryName());

		final int color = firebox instanceof TileEntityHeaterOven ? 0xffffff : 4210752;

		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, color);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		final int i = firebox.heatEnergy * 69 / firebox.getMaxHeat();
		drawTexturedModalRect(guiLeft + 81, guiTop + 28, 176, 0, i, 5);
		
		final int j = firebox.burnTime * 70 / Math.max(firebox.maxBurnTime, 1);
		drawTexturedModalRect(guiLeft + 81, guiTop + 37, 176, 5, j, 5);
		
		if(firebox.wasOn) {
			drawTexturedModalRect(guiLeft + 25, guiTop + 26, 176, 10, 18, 18);
		}
	}
}
