package com.hbm.inventory.gui;

import com.hbm.inventory.container.ContainerCraneInserter;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.network.TileEntityCraneInserter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUICraneInserter extends GuiInfoContainer {
    private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/storage/gui_crane_inserter.png");
    private final TileEntityCraneInserter inserter;

    public GUICraneInserter(final InventoryPlayer invPlayer, final TileEntityCraneInserter tedf) {
        super(new ContainerCraneInserter(invPlayer, tedf));
        inserter = tedf;

        this.xSize = 176;
        this.ySize = 185;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int i, final int j) {
        final String name = this.inserter.hasCustomInventoryName() ? this.inserter.getInventoryName() : I18n.format(this.inserter.getInventoryName());
        this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    public void drawScreen(final int x, final int y, final float interp) {
        super.drawScreen(x, y, interp);
        this.renderHoveredToolTip(x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
        super.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
