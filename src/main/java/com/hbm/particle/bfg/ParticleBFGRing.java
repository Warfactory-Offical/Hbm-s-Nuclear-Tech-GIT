package com.hbm.particle.bfg;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.RenderHelper;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleBFGRing extends Particle {

	float prevScale;
	float prevAlpha;
	
	public ParticleBFGRing(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.canCollide = false;
		this.particleMaxAge = 40;
		this.particleScale = 0.6F;
		this.prevScale = 0.6F;
		this.prevAlpha = 0F;
		particleAlpha = 0;
		
	}
	
	@Override
	public void onUpdate() {
		this.particleAge++;
		if(particleAge >= particleMaxAge)
			this.setExpired();
		final float timeScale = this.particleAge/(float)this.particleMaxAge;
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.posZ += 1.5;
		
		this.prevAlpha = particleAlpha;
		this.particleAlpha = MathHelper.clamp(1-BobMathUtil.remap((float)MathHelper.clamp(timeScale, 0.6, 1), 0.6F, 1F, 0F, 1.1F), 0, 1);
		this.particleAlpha *= MathHelper.clamp(BobMathUtil.remap((float)MathHelper.clamp(timeScale, 0, 0.4), 0F, 0.4F, 0F, 1.1F), 0, 1);
		
		this.prevScale = this.particleScale;
		this.particleScale = 0.6F + 1.2F*timeScale;
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entity, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		GL11.glPushMatrix();
		
		final double d0 = this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks;
		final double d1 = this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks;
		final double d2 = this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks;
		
		final double d3 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) partialTicks;
		final double d4 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks;
		final double d5 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) partialTicks;

		GL11.glTranslated(d0 - d3, d1 - d4, d2 - d5);
		final float scale = this.prevScale + (this.particleScale - this.prevScale)*partialTicks;
		GL11.glScaled(scale, scale, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.bfg_ring_4);
		final Tessellator tes = Tessellator.getInstance();
        final BufferBuilder buf = tes.getBuffer();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableAlpha();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        final float alpha = prevAlpha + (particleAlpha-prevAlpha)*partialTicks;
        GlStateManager.color(0.5F, 1F, 0.5F, alpha);
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
        
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buf.pos(-20, -20, 0).tex(0, 0).endVertex();
        buf.pos(-20, 20, 0).tex(1, 0).endVertex();
        buf.pos(20, 20, 0).tex(1, 1).endVertex();
        buf.pos(20, -20, 0).tex(0, 1).endVertex();
        tes.draw();
        
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        RenderHelper.resetColor();
        
        GL11.glPopMatrix();
	}

}
