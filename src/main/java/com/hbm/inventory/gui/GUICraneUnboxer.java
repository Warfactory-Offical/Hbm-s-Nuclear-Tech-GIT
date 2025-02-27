package com.hbm.inventory.gui;

import com.hbm.inventory.container.ContainerCraneUnboxer;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.network.TileEntityCraneUnboxer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GUICraneUnboxer extends GuiInfoContainer {

    private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/storage/gui_crane_unboxer.png");
    private final TileEntityCraneUnboxer unboxer;

    public GUICraneUnboxer(final InventoryPlayer invPlayer, final TileEntityCraneUnboxer tedf) {
        super(new ContainerCraneUnboxer(invPlayer, tedf));
        unboxer = tedf;

        this.xSize = 176;
        this.ySize = 185;
    }

    @Override
    public void drawScreen(final int x, final int y, final float interp) {
        super.drawScreen(x, y, interp);
        super.renderHoveredToolTip(x, y);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int i, final int j) {
        final String name = this.unboxer.hasCustomInventoryName() ? this.unboxer.getInventoryName() : I18n.format(this.unboxer.getInventoryName());
        this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float interp, final int i, final int j) {
        super.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
