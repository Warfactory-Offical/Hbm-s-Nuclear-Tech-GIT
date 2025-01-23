package com.hbm.particle.vortex;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
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

public class ParticleVortexFireFlash extends Particle {

	public double hitPosX;
	public double hitPosY;
	public double hitPosZ;
	public float workingAlpha;
	
	public ParticleVortexFireFlash(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final double hitPosX, final double hitPosY, final double hitPosZ) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.hitPosX = hitPosX;
		this.hitPosY = hitPosY;
		this.hitPosZ = hitPosZ;
		this.particleMaxAge = 4;
	}
	
	public ParticleVortexFireFlash width(final float width){
		this.particleScale = width;
		return this;
	}
	
	public ParticleVortexFireFlash color(final float colR, final float colG, final float colB, final float colA){
		this.particleRed = colR;
		this.particleGreen = colG;
		this.particleBlue = colB;
		this.particleAlpha = colA;
		workingAlpha = colA;
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.vortex_flash);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GlStateManager.disableAlpha();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		final float timeScale = (this.particleAge+partialTicks)/(float)this.particleMaxAge;
		this.workingAlpha = MathHelper.clamp(1-BobMathUtil.remap(MathHelper.clamp(timeScale, 0, 1), 0F, 1F, 0F, 2F), 0, 1)*particleAlpha;
		
        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        final float mX = (float) (hitPosX - interpPosX);
        final float mY = (float) (hitPosY - interpPosY);
        final float mZ = (float) (hitPosZ - interpPosZ);
        
        final Vec3d particleAxis = new Vec3d(mX, mY, mZ).subtract(f5, f6, f7).normalize().scale(5);
        final Vec3d movement = particleAxis.scale(-1+timeScale*2);
        final Vec3d toPlayer = new Vec3d(f5, f6-entityIn.getEyeHeight(), f7);
        Vec3d point1 = toPlayer.crossProduct(particleAxis).normalize().scale(0.5*particleScale+timeScale*0.2);
        Vec3d point2 = point1.scale(-1);
        point1 = point1.add(f5, f6, f7).add(movement);
        point2 = point2.add(f5, f6, f7).add(movement);
        
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        float alpha = this.workingAlpha;
        while(alpha > 0){
        	buffer.pos(point2.x, point2.y, point2.z).tex(1, 0).color(particleRed, particleGreen, particleBlue, MathHelper.clamp(alpha, 0, 1)).endVertex();
        	buffer.pos(point1.x, point1.y, point1.z).tex(1, 1).color(particleRed, particleGreen, particleBlue, MathHelper.clamp(alpha, 0, 1)).endVertex();
        	buffer.pos(point1.x+particleAxis.x, point1.y+particleAxis.y, point1.z+particleAxis.z).tex(0, 1).color(particleRed, particleGreen, particleBlue, MathHelper.clamp(alpha, 0, 1)).endVertex();
        	buffer.pos(point2.x+particleAxis.x, point2.y+particleAxis.y, point2.z+particleAxis.z).tex(0, 0).color(particleRed, particleGreen, particleBlue, MathHelper.clamp(alpha, 0, 1)).endVertex();
        	alpha -= 1;
        }
        Tessellator.getInstance().draw();
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
	}
	
}