package com.hbm.particle;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticlePlasmaBlast extends Particle {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/particle/shockwave.png");
	
	private final float rotationPitch;
	private final float rotationYaw;
	
	public ParticlePlasmaBlast(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float r, final float g, final float b, final float pitch, final float yaw){
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleMaxAge = 20;
		this.particleRed = r;
		this.particleGreen = g;
		this.particleBlue = b;
		this.rotationPitch = pitch;
		this.rotationYaw = yaw;
	}
	
	public void setMaxAge(final int maxAge) {
		this.particleMaxAge = maxAge;
	}
	
	public void setScale(final float scale) {
		this.particleScale = scale;
	}

	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ){
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		
		final Tessellator tes = Tessellator.getInstance();
		final BufferBuilder buf = tes.getBuffer();
		
		GL11.glPushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0);
		GlStateManager.depthMask(false);
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		GlStateManager.disableCull();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.glNormal3f(0, 1, 0);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		
		final float pX = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
		final float pY = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
		final float pZ = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);

		GL11.glTranslatef(pX, pY, pZ);
		GL11.glRotated(this.rotationYaw, 0, 1, 0);
		GL11.glRotated(this.rotationPitch, 1, 0, 0);
			
		this.particleAlpha = 1 - (((float)this.particleAge + partialTicks) / (float)this.particleMaxAge);
		final float scale = (1 - (float)Math.pow(Math.E, (this.particleAge + partialTicks) * -0.125)) * this.particleScale;
		GlStateManager.color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
		
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buf.pos(- 1 * scale, - 0.25, - 1 * scale).tex(1, 1).endVertex();
		buf.pos(- 1 * scale, - 0.25, + 1 * scale).tex(1, 0).endVertex();
		buf.pos(+ 1 * scale, - 0.25, + 1 * scale).tex(0, 0).endVertex();
		buf.pos(+ 1 * scale, - 0.25, - 1 * scale).tex(0, 1).endVertex();
		tes.draw();
		
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.enableCull();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		GlStateManager.enableLighting();
		GL11.glPopMatrix();
	}

}
