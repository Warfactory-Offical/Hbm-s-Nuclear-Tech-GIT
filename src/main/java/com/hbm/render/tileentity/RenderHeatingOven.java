package com.hbm.render.tileentity;

import com.hbm.blocks.BlockDummyable;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityHeaterOven;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderHeatingOven extends TileEntitySpecialRenderer<TileEntityHeaterOven> {
    @Override
    public boolean isGlobalRenderer(final TileEntityHeaterOven te) {
        return true;
    }

    @Override
    public void render(final TileEntityHeaterOven tile, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_CULL_FACE);

        switch(tile.getBlockMetadata() - BlockDummyable.offset) {
            case 3: GL11.glRotatef(0, 0F, 1F, 0F); break;
            case 5: GL11.glRotatef(90, 0F, 1F, 0F); break;
            case 2: GL11.glRotatef(180, 0F, 1F, 0F); break;
            case 4: GL11.glRotatef(270, 0F, 1F, 0F); break;
        }
        GL11.glRotatef(-90, 0F, 1F, 0F);

        final TileEntityHeaterOven oven = tile;

        bindTexture(ResourceManager.heater_oven_tex);
        ResourceManager.heater_oven.renderPart("Main");

        GL11.glPushMatrix();
        final float door = oven.prevDoorAngle + (oven.doorAngle - oven.prevDoorAngle) * partialTicks;
        GL11.glTranslated(0, 0, door * 0.75D / 135D);
        ResourceManager.heater_oven.renderPart("Door");
        GL11.glPopMatrix();

        if(oven.wasOn) {
            GL11.glPushMatrix();
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);

            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
            ResourceManager.heater_oven.renderPart("InnerBurning");
            GL11.glEnable(GL11.GL_LIGHTING);

            GL11.glPopAttrib();
            GL11.glPopMatrix();
        } else {
            ResourceManager.heater_oven.renderPart("Inner");
        }

        GL11.glPopMatrix();
    }
}
