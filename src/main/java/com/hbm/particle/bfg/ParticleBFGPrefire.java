package com.hbm.particle.bfg;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.Vec3;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleBFGPrefire extends Particle {

	float length;
	float prevAlpha;
	
	public ParticleBFGPrefire(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.canCollide = false;
		this.particleMaxAge = 60;
		this.prevAlpha = 0F;
		particleAlpha = 0;
		length = 1000;
		this.particleScale = 5;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge++;
		if(particleAge >= particleMaxAge)
			this.setExpired();
		final float timeScale = this.particleAge/(float)this.particleMaxAge;
		
		this.prevAlpha = particleAlpha;
		this.particleAlpha = MathHelper.clamp(1-BobMathUtil.remap((float)MathHelper.clamp(timeScale, 0.4, 1), 0.4F, 1F, 0F, 1F), 0, 1);
		this.particleAlpha *= MathHelper.clamp(BobMathUtil.remap((float)MathHelper.clamp(timeScale, 0, 0.4), 0F, 0.4F, 0F, 1.1F), 0, 1);
		this.particleAlpha *= 0.5;
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
		
		GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(false);
        GlStateManager.disableAlpha();
        GlStateManager.disableFog();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
        final float alpha = prevAlpha + (particleAlpha-prevAlpha)*partialTicks;
        GlStateManager.color(0.5F, 1F, 0.5F, alpha);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.bfg_prefire);
		final Tessellator tes = Tessellator.getInstance();
        final BufferBuilder buf = tes.getBuffer();
        
        final Vec3d look = entity.getPositionEyes(partialTicks).subtract(d0, d1, d2);
        final Vec3 point1 = Vec3.createVectorHelper(look.x, look.y, look.z).crossProduct(Vec3.createVectorHelper(0, 0, 1)).normalize().mult((float) (0.5*particleScale));
        final Vec3 point2 = point1.mult(-1);
        
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buf.pos(point1.xCoord, point1.yCoord, 0).tex(0.5, 1).endVertex();
        buf.pos(point2.xCoord, point2.yCoord, 0).tex(0.5, 0).endVertex();
        buf.pos(point2.xCoord, point2.yCoord, -length).tex(0.51, 0).endVertex();
        buf.pos(point1.xCoord, point1.yCoord, -length).tex(0.51, 1).endVertex();
        tes.draw();
        
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.enableFog();
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        
        GL11.glPopMatrix();
	}

}
