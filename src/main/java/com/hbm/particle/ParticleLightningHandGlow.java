package com.hbm.particle;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ClientProxy;
import com.hbm.main.ResourceManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleLightningHandGlow extends Particle {

	public ParticleLightningHandGlow(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float scale, final int age) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleScale = scale;
		this.particleMaxAge = age;
	}
	
	public ParticleLightningHandGlow color(final float r, final float g, final float b, final float a){
		this.particleRed = r;
		this.particleGreen = g;
		this.particleBlue = b;
		this.particleAlpha = a;
		return this;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge ++;
		if(particleAge >= particleMaxAge){
			setExpired();
        }
	}

	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return true;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		GL11.glPushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		GlStateManager.disableAlpha();
		
		final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks);
	    final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks);
	    final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks);
	    GL11.glTranslated(f5, f6, f7);
	    
	    
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
		ClientProxy.AUX_GL_BUFFER.put(0, 1);
		ClientProxy.AUX_GL_BUFFER.put(1, 0);
		ClientProxy.AUX_GL_BUFFER.put(2, 0);
			
		ClientProxy.AUX_GL_BUFFER.put(4, 0);
		ClientProxy.AUX_GL_BUFFER.put(5, 1);
		ClientProxy.AUX_GL_BUFFER.put(6, 0);
			
		ClientProxy.AUX_GL_BUFFER.put(8, 0);
		ClientProxy.AUX_GL_BUFFER.put(9, 0);
		ClientProxy.AUX_GL_BUFFER.put(10, 1);
			
		GL11.glLoadMatrix(ClientProxy.AUX_GL_BUFFER);

		
		final float ageN = (this.particleAge+partialTicks) /(float)this.particleMaxAge;
		final float scale = MathHelper.clamp(ageN*2, 0, 1)* MathHelper.clamp(2-ageN*2+0.1F, 0, 1);
		final float f4 = 0.1F * this.particleScale * scale;
        
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.fresnel_ms);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
		buffer.pos(f4, f4, 0).tex(1, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(240, 240).endVertex();
        buffer.pos(-f4, f4, 0).tex(1, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(240, 240).endVertex();
        buffer.pos(-f4, -f4, 0).tex(0, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(240, 240).endVertex();
        buffer.pos(f4, -f4, 0).tex(0, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(240, 240).endVertex();
        Tessellator.getInstance().draw();
        
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
		GlStateManager.enableDepth();
		GL11.glPopMatrix();
	}
	
}