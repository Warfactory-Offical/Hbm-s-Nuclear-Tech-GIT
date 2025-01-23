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
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleRBMKFlame extends Particle {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/particle/rbmk_fire.png");
	
	public ParticleRBMKFlame(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final int maxAge){
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleMaxAge = maxAge;
		this.particleScale = rand.nextFloat() + 1F;
	}
	
	@Override
	public int getFXLayer(){
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ){
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		com.hbm.render.RenderHelper.resetParticleInterpPos(entityIn, partialTicks);
		
		GL11.glPushMatrix();
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0);
		GlStateManager.depthMask(false);
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		RenderHelper.disableStandardItemLighting();
		
		if(this.particleAge > this.particleMaxAge)
			this.particleAge = this.particleMaxAge;
		
		final int texIndex = this.particleAge * 5 % 14;
		final float f0 = 1F / 14F;

		final float uMin = texIndex % 5 * f0;
		final float uMax = uMin + f0;
		final float vMin = 0;
		final float vMax = 1;
			
		final Tessellator tes = Tessellator.getInstance();
		final BufferBuilder buf = tes.getBuffer();
		
		GlStateManager.glNormal3f(0, 1, 0);
		GlStateManager.color(1.0F, 1.0F, 1.0F, this.particleAlpha);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		
		this.particleAlpha = 1F;
		
		if(this.particleAge < 20) {
			this.particleAlpha = this.particleAge / 20F;
		}
		
		if(this.particleAge > this.particleMaxAge - 20) {
			this.particleAlpha = (this.particleMaxAge - this.particleAge) / 20F;
		}

		this.particleAlpha *= 0.5F;
		
		final float pX = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
		final float pY = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
		final float pZ = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
		
		GL11.glTranslatef(pX + rotationX, pY + rotationZ, pZ + rotationYZ);
		GL11.glRotatef(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);

		buf.pos(-this.particleScale - 1, -this.particleScale * 2, 0).tex(uMax, vMax).endVertex();
		buf.pos(-this.particleScale - 1, this.particleScale * 2, 0).tex(uMax, vMin).endVertex();
		buf.pos(this.particleScale - 1, this.particleScale * 2, 0).tex(uMin, vMin).endVertex();
		buf.pos(this.particleScale - 1, -this.particleScale * 2, 0).tex(uMin, vMax).endVertex();
		
		tes.draw();
		
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.doPolygonOffset(0, 0);
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}
	
	protected ResourceLocation getTexture() {
		return texture;
	}

}
