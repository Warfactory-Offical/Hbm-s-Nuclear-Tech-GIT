package com.hbm.particle;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleRBMKMush extends Particle {

	private static final ResourceLocation texture = new ResourceLocation(com.hbm.lib.RefStrings.MODID + ":textures/particle/rbmk_mush.png");
	
	public ParticleRBMKMush(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float scale){
		super(worldIn, posXIn, posYIn, posZIn);
		particleMaxAge = 80;

		this.particleRed = this.particleGreen = this.particleBlue = 0;
		
		this.particleScale = scale;
	}
	
	@Override
	public void onUpdate(){
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		++this.particleAge;

		if(this.particleMaxAge == this.particleAge) {
			this.setExpired();
		}
	}
	
	@Override
	public int getFXLayer(){
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ){
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		com.hbm.render.RenderHelper.resetParticleInterpPos(entityIn, partialTicks);

		final int segs = 30;

		// the size of one frame
		final double frame = 1D / (double) segs;
		// how many frames we're in
		final int prog = particleAge * segs / particleMaxAge;

		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.glNormal3f(0, 1, 0);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.depthMask(false);
		RenderHelper.disableStandardItemLighting();

		final Tessellator tes = Tessellator.getInstance();
		final BufferBuilder buf = tes.getBuffer();
		
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		final float scale = this.particleScale;
		final float pX = (float) ((this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX));
		final float pY = (float) ((this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY)) + particleScale;
		final float pZ = (float) ((this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ));

		buf.pos(pX - rotationX * scale - rotationXY * scale, pY - rotationZ * scale, pZ - rotationYZ * scale - rotationXZ * scale).tex(1, (prog + 1) * frame).endVertex();
		buf.pos(pX - rotationX * scale + rotationXY * scale, pY + rotationZ * scale, pZ - rotationYZ * scale + rotationXZ * scale).tex(1, prog * frame).endVertex();
		buf.pos(pX + rotationX * scale + rotationXY * scale, pY + rotationZ * scale, pZ + rotationYZ * scale + rotationXZ * scale).tex(0, prog * frame).endVertex();
		buf.pos(pX + rotationX * scale - rotationXY * scale, pY - rotationZ * scale, pZ + rotationYZ * scale - rotationXZ * scale).tex(0, (prog + 1) * frame).endVertex();
		tes.draw();

		GlStateManager.doPolygonOffset(0, 0);
		GlStateManager.enableLighting();
	}

}
