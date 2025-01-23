package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntitySolarMirror;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class RenderSolarMirror extends TileEntitySpecialRenderer<TileEntitySolarMirror> {

	@Override
	public boolean isGlobalRenderer(final TileEntitySolarMirror te) {
		return true;
	}
	
	@Override
	public void render(final TileEntitySolarMirror te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.disableCull();
        
        final TileEntitySolarMirror mirror = te;

        bindTexture(ResourceManager.solar_mirror_tex);
        ResourceManager.solar_mirror.renderPart("Base");

        GL11.glTranslated(0, 1, 0);

    	final int dx = mirror.tX - mirror.getPos().getX();
    	final int dy = mirror.tY - mirror.getPos().getY();
    	final int dz = mirror.tZ - mirror.getPos().getZ();

    	final double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

        if(mirror.tY >= mirror.getPos().getY()) {

        	final double pitch = Math.toDegrees(-Math.asin((dy + 0.5) / dist)) + 90;
        	final double yaw = Math.toDegrees(-Math.atan2(dz, dx)) + 180;

        	GL11.glRotated(yaw, 0, 1, 0);
        	GL11.glRotated(pitch, 0, 0, 1);
        }

        GL11.glTranslated(0, -1, 0);
        ResourceManager.solar_mirror.renderPart("Mirror");

        if(mirror.isOn) {
			final float min = 0.008F;
	        final float max = 0.008F;

	        final Tessellator tess = Tessellator.getInstance();
	        final BufferBuilder buf = tess.getBuffer();
	        GlStateManager.disableTexture2D();
	        GlStateManager.disableLighting();
	        GlStateManager.enableBlend();
	        GlStateManager.shadeModel(GL11.GL_SMOOTH);
	        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0);
			GlStateManager.depthMask(false);


	        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
	        buf.pos(0.5, 1.0625, 0.5).color(1F, 1F, 1F, max).endVertex();
	        buf.pos(0.5, 1.0625, -0.5).color(1F, 1F, 1F, max).endVertex();
	        buf.pos(0.5, dist, -0.5).color(1F, 1F, 1F, min).endVertex();
	        buf.pos(0.5, dist, 0.5).color(1F, 1F, 1F, min).endVertex();

	        buf.pos(-0.5, 1.0625, 0.5).color(1F, 1F, 1F, max).endVertex();
	        buf.pos(-0.5, 1.0625, -0.5).color(1F, 1F, 1F, max).endVertex();
	        buf.pos(-0.5, dist, -0.5).color(1F, 1F, 1F, min).endVertex();
	        buf.pos(-0.5, dist, 0.5).color(1F, 1F, 1F, min).endVertex();

	        buf.pos(0.5, 1.0625, 0.5).color(1F, 1F, 1F, max).endVertex();
	        buf.pos(-0.5, 1.0625, 0.5).color(1F, 1F, 1F, max).endVertex();
	        buf.pos(-0.5, dist, 0.5).color(1F, 1F, 1F, min).endVertex();
	        buf.pos(0.5, dist, 0.5).color(1F, 1F, 1F, min).endVertex();

	        buf.pos(0.5, 1.0625, -0.5).color(1F, 1F, 1F, max).endVertex();
	        buf.pos(-0.5, 1.0625, -0.5).color(1F, 1F, 1F, max).endVertex();
	        buf.pos(-0.5, dist, -0.5).color(1F, 1F, 1F, min).endVertex();
	        buf.pos(0.5, dist, -0.5).color(1F, 1F, 1F, min).endVertex();
	        tess.draw();

	        GlStateManager.depthMask(true);
	        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
	        GlStateManager.shadeModel(GL11.GL_FLAT);
	        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
	        GlStateManager.disableBlend();
	        GlStateManager.enableLighting();
	        GlStateManager.enableTexture2D();
        }

        GL11.glPopMatrix();
	}
}
