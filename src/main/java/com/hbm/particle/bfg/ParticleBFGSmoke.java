package com.hbm.particle.bfg;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleBFGSmoke extends Particle {
	
	float length;
	float prevLength;
	float prevScale;
	float prevAlpha;
	
	public ParticleBFGSmoke(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.canCollide = false;
		this.particleMaxAge = 15;
		this.prevAlpha = 1F;
		particleAlpha = 1;
		length = 30;
		this.prevLength = 30;
		this.particleScale = 10;
		this.prevScale = 10;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge++;
		if(particleAge >= particleMaxAge)
			this.setExpired();
		
		this.prevPosZ = this.posZ;
		this.posZ -= 1;
		
		this.prevLength = this.length;
		this.prevScale = this.particleScale;
		this.length -= 0.5;
		this.particleScale += 2;
		
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
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
        final float alpha = prevAlpha + (particleAlpha-prevAlpha)*partialTicks;
        GlStateManager.color(0.5F, 1F, 0.5F, alpha);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.bfg_smoke);
		final Tessellator tes = Tessellator.getInstance();
        final BufferBuilder buf = tes.getBuffer();
        
        final float timeScale = (this.particleAge+partialTicks)/(float)this.particleMaxAge;
        final int index = (int) (timeScale*64);
        final float size = 1/8F;
        final float u = (index%8)*size;
        final float v = (index/8)*size;
        
        final float scale = this.prevScale + (this.particleScale - this.prevScale)*partialTicks;
        final float length = this.prevLength + (this.length - this.prevLength)*partialTicks;
        
        final Vec3d look = entity.getPositionEyes(partialTicks).subtract(d0, d1, d2);
        final Vec3 point1 = Vec3.createVectorHelper(look.x, look.y, look.z).crossProduct(Vec3.createVectorHelper(0, 0, 1)).normalize().mult((float) (0.5*scale));
        final Vec3 point2 = point1.mult(-1);
        
        //HbmShaderManager.useShader2(HbmShaderManager.bfg_beam);
        
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buf.pos(point1.xCoord, point1.yCoord, 0).tex(u+size, v+size).endVertex();
        buf.pos(point2.xCoord, point2.yCoord, 0).tex(u, v+size).endVertex();
        buf.pos(point2.xCoord, point2.yCoord, -length).tex(u, v).endVertex();
        buf.pos(point1.xCoord, point1.yCoord, -length).tex(u+size, v).endVertex();
        tes.draw();
        
        //HbmShaderManager.releaseShader2();
        
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        
        GL11.glPopMatrix();
	}
}
