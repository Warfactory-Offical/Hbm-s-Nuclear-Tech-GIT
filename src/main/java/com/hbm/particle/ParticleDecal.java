package com.hbm.particle;

import org.lwjgl.opengl.GL11;

import com.hbm.handler.HbmShaderManager2;
import com.hbm.handler.HbmShaderManager2.Shader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleDecal extends Particle {

	public ResourceLocation tex;
	public int displayList;
	public int texIdx = -1;
	public int rows;
	public Shader shader;
	
	public ParticleDecal(final World worldIn, final int dl, final ResourceLocation texture, final int maxAge, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.displayList = dl;
		this.tex = texture;
		this.particleMaxAge = maxAge;
	}
	
	public ParticleDecal textureIndex(final int idx, final int rows){
		this.rows = rows;
		this.texIdx = idx;
		return this;
	}
	
	public ParticleDecal shader(final Shader shader){
		this.shader = shader;
		return this;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge ++;
		if(this.particleAge > this.particleMaxAge){
			setExpired();
			GL11.glDeleteLists(displayList, 1);
		}
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		
		if(texIdx != -1){
			GlStateManager.matrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			final float size = 1F/rows;
	        final float u = (texIdx%rows)*size;
	        final float v = (texIdx/4)*size;
	        GL11.glTranslated(u, v, 0);
	        GL11.glScaled(size, size, 1);
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		}
		
		if(shader != null){
			shader.use();
		}
		
		
		final int i = this.getBrightnessForRender(partialTicks);
		final int j = i >> 16 & 65535;
        final int k = i & 65535;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k, j);
		GL11.glPushMatrix();
		final double entPosX = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX)*partialTicks;
        final double entPosY = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY)*partialTicks;
        final double entPosZ = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ)*partialTicks;
		GL11.glTranslated(posX-entPosX, posY-entPosY, posZ-entPosZ);
		Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0);
		GlStateManager.enableBlend();
		GlStateManager.depthMask(false);
		GlStateManager.enableLighting();
		GlStateManager.enableColorMaterial();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.enablePolygonOffset();
		final float fade = (this.particleAge-particleMaxAge+30+partialTicks) /30F;
		GlStateManager.color(0.5F, 0.1F, 0.1F, 1-fade);
		GlStateManager.doPolygonOffset(-5, -5);
		GL11.glCallList(displayList);
		GlStateManager.disablePolygonOffset();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
		GL11.glPopMatrix();
		
		if(texIdx != -1){
			GlStateManager.matrixMode(GL11.GL_TEXTURE);
			GL11.glPopMatrix();
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		}
		if(shader != null){
			HbmShaderManager2.releaseShader();
		}
	}

}
