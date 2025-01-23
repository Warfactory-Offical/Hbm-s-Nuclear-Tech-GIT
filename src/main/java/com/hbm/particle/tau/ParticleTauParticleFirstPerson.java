package com.hbm.particle.tau;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.particle.ParticleFirstPerson;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleTauParticleFirstPerson extends ParticleFirstPerson {

	public boolean fadesIn = true;
	
	public ParticleTauParticleFirstPerson(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float scale) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleScale = scale;
	}
	
	public ParticleTauParticleFirstPerson color(final float colR, final float colG, final float colB, final float colA){
		this.particleRed = colR;
		this.particleGreen = colG;
		this.particleBlue = colB;
		this.particleAlpha = colA;
		return this;
	}
	
	public ParticleTauParticleFirstPerson lifetime(final int lifetime){
		this.particleMaxAge = lifetime;
		return this;
	}
	
	public ParticleTauParticleFirstPerson fadeIn(final boolean fadeIn){
		fadesIn = fadeIn;
		return this;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge ++;
		if(this.particleAge >= this.particleMaxAge){
			this.setExpired();
		}
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return true;
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		GL11.glPushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.fresnel_ms);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GlStateManager.disableAlpha();
		GlStateManager.disableCull();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		RenderHelper.enableStandardItemLighting();
		final float timeScale = (this.particleAge+partialTicks)/(float)this.particleMaxAge;
		float shrink = MathHelper.clamp(1-BobMathUtil.remap((float)MathHelper.clamp(timeScale, 0.5, 1), 0.5F, 1F, 0F, 1.1F), 0, 1);
		if(fadesIn)
			shrink *= MathHelper.clamp(BobMathUtil.remap((float)MathHelper.clamp(timeScale, 0, 0.5), 0F, 0.5F, 0F, 1.1F), 0, 1);
		float workingAlpha = shrink*particleAlpha;
		
		final float f4 = 0.1F * this.particleScale*(1-shrink*0.25F);
        
        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks);
        GL11.glTranslated(f5, f6, f7);
        GL11.glScaled(f4, f4, f4);
        
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        
        while(workingAlpha > 0){
        	buffer.pos(0, 0.5, 0.5).tex(1, 1).color(this.particleRed, this.particleGreen, this.particleBlue, Math.min(workingAlpha, 1)).lightmap(240, 240).endVertex();
        	buffer.pos(0, 0.5, -0.5).tex(1, 0).color(this.particleRed, this.particleGreen, this.particleBlue, Math.min(workingAlpha, 1)).lightmap(240, 240).endVertex();
        	buffer.pos(0, -0.5, -0.5).tex(0, 0).color(this.particleRed, this.particleGreen, this.particleBlue, Math.min(workingAlpha, 1)).lightmap(240, 240).endVertex();
        	buffer.pos(0, -0.5, 0.5).tex(0, 1).color(this.particleRed, this.particleGreen, this.particleBlue, Math.min(workingAlpha, 1)).lightmap(240, 240).endVertex();
        	workingAlpha -= 1;
        }
        
        Tessellator.getInstance().draw();
        
        GlStateManager.enableAlpha();
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        
        GL11.glPopMatrix();
	}
	
	@Override
	public ParticleType getType() {
		return ParticleType.TAU;
	}

}