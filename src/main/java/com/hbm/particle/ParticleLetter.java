package com.hbm.particle;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ParticleLetter extends Particle {

	int color;
	char c;
	
	public ParticleLetter(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final int color, final char c) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleMaxAge = 30;
		this.color = color;
		this.c = c;
	}

	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		GL11.glPushMatrix();

		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0);
		GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 0.0F);
		RenderHelper.disableStandardItemLighting();

		final Minecraft mc = Minecraft.getMinecraft();
	    final FontRenderer font = mc.fontRenderer;

	    final float pX = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
	    final float pY = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
	    final float pZ = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);

	    GL11.glTranslatef(pX, pY, pZ);
	    GL11.glRotatef(-mc.player.rotationYaw, 0.0F, 1.0F, 0.0F);
	    GL11.glRotatef(mc.player.rotationPitch, 1.0F, 0.0F, 0.0F);
	    GL11.glScalef(-1.0F, -1.0F, 1.0F);

	    final float time = (this.particleAge + partialTicks) * 4F / this.particleMaxAge;

	    final double scale = 1 - (1D / Math.pow(Math.E, time));

		this.particleAlpha = 1 - (((float)this.particleAge + partialTicks) / (float)this.particleMaxAge);

		if(particleAlpha < 0)
			particleAlpha = 0;

		int alpha = (int) (particleAlpha * 255);

		if(alpha > 255)
			alpha = 255;

		if(alpha < 10)
			alpha = 10;

		final int col = color + (alpha << 24);

	    GL11.glScaled(scale, scale, scale);

	    font.drawString(String.valueOf(c), -(int)(font.getStringWidth(String.valueOf(c)) * 0.5F), -(int)(font.FONT_HEIGHT * 0.5F), col);
	    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		GlStateManager.enableLighting();

	    GL11.glPopMatrix();
	}
}
