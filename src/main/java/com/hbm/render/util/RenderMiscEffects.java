package com.hbm.render.util;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.render.amlfrom1710.IModelCustom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderMiscEffects {

	public static ResourceLocation glint = new ResourceLocation(RefStrings.MODID + ":textures/misc/glint.png");

	public static void renderClassicGlint(final World world, final float interpol, final IModelCustom model, final String part, final float colorMod, final float r, final float g, final float b, final float speed, final float scale) {

        GL11.glPushMatrix();
    	final float offset = Minecraft.getMinecraft().player.ticksExisted + interpol;
        GlStateManager.enableBlend();
        final float color = colorMod;
        GlStateManager.color(color, color, color, 1.0F);
        GlStateManager.depthFunc(GL11.GL_EQUAL);
        GlStateManager.depthMask(false);

        for (int k = 0; k < 2; ++k) {

            GlStateManager.disableLighting();

            final float glintColor = 0.76F;

            GlStateManager.color(r * glintColor, g * glintColor, b * glintColor, 1.0F);
            GlStateManager.blendFunc(SourceFactor.SRC_COLOR, DestFactor.ONE);
            GlStateManager.matrixMode(GL11.GL_TEXTURE);
            GlStateManager.loadIdentity();

            final float movement = offset * (0.001F + (float)k * 0.003F) * speed;

            GL11.glScalef(scale, scale, scale);
            GL11.glRotatef(30.0F - (float)k * 60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, movement, 0.0F);

            GlStateManager.matrixMode(GL11.GL_MODELVIEW);

            if("all".equals(part))
            	model.renderAll();
            else
            	model.renderPart(part);
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.matrixMode(GL11.GL_TEXTURE);
        GlStateManager.depthMask(true);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.depthFunc(GL11.GL_LEQUAL);
        GL11.glPopMatrix();
    }

	public static void renderClassicGlint(final World world, final float interpol, final IModelCustom model, final String part, final float r, final float g, final float b, final float speed, final float scale) {
		renderClassicGlint(world, interpol, model, part, 0.5F, r, g, b, speed, scale);
    }

	public static void renderClassicGlint(final World world, final float interpol, final IModelCustom model, final String part) {
		renderClassicGlint(world, interpol, model, part, 0.5F, 0.25F, 0.8F, 20.0F, 1F/3F);
    }
}