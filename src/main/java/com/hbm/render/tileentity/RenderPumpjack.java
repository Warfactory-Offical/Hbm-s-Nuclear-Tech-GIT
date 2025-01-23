package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.oil.TileEntityMachinePumpjack;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class RenderPumpjack extends TileEntitySpecialRenderer<TileEntityMachinePumpjack> {
	
	float rotation;
	
	@Override
	public boolean isGlobalRenderer(final TileEntityMachinePumpjack te) {
		return true;
	}
	
	public void drawConnection(final double x, final double y, final double z, final double a, final double b, final double c) {
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buf = tessellator.getBuffer();
        buf.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        buf.pos(x + 0.05F, y, z).color(0.6F, 0.6F, 0.6F, 1.0F).endVertex();
        buf.pos(x - 0.05F, y, z).color(0.6F, 0.6F, 0.6F, 1.0F).endVertex();
        buf.pos(a + 0.05F, b, c).color(0.6F, 0.6F, 0.6F, 1.0F).endVertex();
        buf.pos(a - 0.05F, b, c).color(0.6F, 0.6F, 0.6F, 1.0F).endVertex();
        tessellator.draw();
        buf.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        buf.pos(x, y, z + 0.05F).color(0.6F, 0.6F, 0.6F, 1.0F).endVertex();
        buf.pos(x, y, z - 0.05F).color(0.6F, 0.6F, 0.6F, 1.0F).endVertex();
        buf.pos(a, b, c + 0.05F).color(0.6F, 0.6F, 0.6F, 1.0F).endVertex();
        buf.pos(a, b, c - 0.05F).color(0.6F, 0.6F, 0.6F, 1.0F).endVertex();
        tessellator.draw();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
	}

		@Override
	public void render(final TileEntityMachinePumpjack pj, final double x, final double y, final double z, final float f, final int destroyStage, final float alpha) {
		
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y, z + 0.5);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		switch(pj.getBlockMetadata()) {
		case 2: GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 4: GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 3: GL11.glRotatef(270, 0F, 1F, 0F); break;
		case 5: GL11.glRotatef(0, 0F, 1F, 0F); break;
		}
		
		final float rotation = (pj.prevRotation + (pj.rotation - pj.prevRotation) * f);

		GL11.glShadeModel(GL11.GL_SMOOTH);
		
		bindTexture(ResourceManager.pumpjack_tex);
		ResourceManager.pumpjack.renderPart("Base");

		GL11.glPushMatrix();
		GL11.glTranslated(0, 1.5, -5.5);
		GL11.glRotatef(rotation - 90, 1, 0, 0);
		GL11.glTranslated(0, -1.5, 5.5);
		ResourceManager.pumpjack.renderPart("Rotor");
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(0, 3.5, -3.5);
		GL11.glRotated(Math.toDegrees(Math.sin(Math.toRadians(rotation))) * 0.25, 1, 0, 0);
		GL11.glTranslated(0, -3.5, 3.5);
		ResourceManager.pumpjack.renderPart("Head");
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(0, -Math.sin(Math.toRadians(rotation)), 0);
		ResourceManager.pumpjack.renderPart("Carriage");
		GL11.glPopMatrix();
		
		final Vec3 backPos = Vec3.createVectorHelper(0, 0, -2);
		backPos.rotateAroundX(-(float)Math.sin(Math.toRadians(rotation)) * 0.25F);
		
		final Vec3 rot = Vec3.createVectorHelper(0, 0.5, 0);
		rot.rotateAroundX(-(float)Math.toRadians(rotation - 90));
		
		for(int i = -1; i <= 1; i += 2) {

			drawConnection(0.53125 * i, 1.5 + rot.yCoord, -5.5 + rot.zCoord + 0.0625D, 0.53125 * i, 3.5 + backPos.yCoord, -3.5 + backPos.zCoord + 0.0625D);
			drawConnection(0.53125 * i, 1.5 + rot.yCoord, -5.5 + rot.zCoord - 0.0625D, 0.53125 * i, 3.5 + backPos.yCoord, -3.5 + backPos.zCoord - 0.0625D);
		}
		
		final double pd = 0.03125D;
		final double width = 0.25D;

		final double height = -Math.sin(Math.toRadians(rotation));
		
		for(int i = -1; i <= 1; i += 2) {

			final float pRot = -(float)(Math.sin(Math.toRadians(rotation)) * 0.25);
			
			final Vec3 frontPos = Vec3.createVectorHelper(0, 0, 1);
			frontPos.rotateAroundX(pRot);

			final double dist = 0.03125D;
			final Vec3 frontRad = Vec3.createVectorHelper(0, 0, 2.5 + dist);
			final double cutlet = 360D / 32D;
			frontRad.rotateAroundX(pRot);
			frontRad.rotateAroundX(-(float)Math.toRadians(cutlet * -3));
			
			for(int j = 0; j < 4; j++) {

				final double sumY1 = frontPos.yCoord + frontRad.yCoord;
				double sumZ1 = frontPos.zCoord + frontRad.zCoord;
				if(frontRad.yCoord < 0) sumZ1 = 3.5 + dist * 0.5;
				
				
				frontRad.rotateAroundX(-(float)Math.toRadians(cutlet));

				final double sumY2 = frontPos.yCoord + frontRad.yCoord;
				double sumZ2 = frontPos.zCoord + frontRad.zCoord;
				if(frontRad.yCoord < 0) sumZ2 = 3.5 + dist * 0.5;
				drawConnection((width + pd) * i, 3.5 + sumY1, -3.5 + sumZ1, (width + pd) * i, 3.5 + sumY2, -3.5 + sumZ2);
				drawConnection((width - pd) * i, 3.5 + sumY1, -3.5 + sumZ1, (width - pd) * i, 3.5 + sumY2, -3.5 + sumZ2);
			}

			final double sumY = frontPos.yCoord + frontRad.yCoord;
			double sumZ = frontPos.zCoord + frontRad.zCoord;
			if(frontRad.yCoord < 0) sumZ = 3.5 + dist * 0.5;
			
			drawConnection((width + pd) * i, 2 + height, 0, (width + pd) * i, 3.5 + sumY, -3.5 + sumZ);
			drawConnection((width - pd) * i, 2 + height, 0, (width - pd) * i, 3.5 + sumY, -3.5 + sumZ);
		}
		
		final double p = 0.03125D;
		drawConnection(-p, 0.75, -p, -p, height + 1.5, -p);
		drawConnection(p, 0.75,  p, p, height + 1.5, p);
		drawConnection(p, 0.75, -p, p, height + 1.5, -p);
		drawConnection(-p, 0.75, p, -p, height + 1.5, p);


		GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
		GL11.glPopMatrix();
	}
}
