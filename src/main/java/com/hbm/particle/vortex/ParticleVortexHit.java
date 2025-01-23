package com.hbm.particle.vortex;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.util.BobMathUtil;

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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleVortexHit extends Particle {

	public float workingAlpha;
	public float rotationOverLife;
	
	public ParticleVortexHit(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float scale, final float rot) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleScale = scale;
		this.rotationOverLife = (float) Math.toRadians(rot);
	}
	
	public ParticleVortexHit color(final float colR, final float colG, final float colB, final float colA){
		this.particleRed = colR;
		this.particleGreen = colG;
		this.particleBlue = colB;
		this.particleAlpha = colA;
		workingAlpha = colA;
		return this;
	}
	
	public ParticleVortexHit lifetime(final int lifetime){
		this.particleMaxAge = lifetime;
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.vortex_hit);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GlStateManager.disableAlpha();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		final float timeScale = (this.particleAge+partialTicks)/(float)this.particleMaxAge;
		this.workingAlpha = MathHelper.clamp(1-BobMathUtil.remap(MathHelper.clamp(timeScale, 0, 1), 0.6F, 1F, 0.6F, 1F), 0, 1)*particleAlpha;
		workingAlpha *= MathHelper.clamp(BobMathUtil.remap((float)MathHelper.clamp(timeScale, 0, 0.2), 0F, 0.2F, 0F, 1F), 0, 1);
		this.particleAngle = timeScale*rotationOverLife;
		
		final float f4 = 0.1F * (this.particleScale+timeScale*6F);
        
        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        final Vec3d[] avec3d = new Vec3d[] {new Vec3d(-rotationX * f4 - rotationXY * f4, -rotationZ * f4, -rotationYZ * f4 - rotationXZ * f4), new Vec3d(-rotationX * f4 + rotationXY * f4, rotationZ * f4, -rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 + rotationXY * f4, rotationZ * f4, rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 - rotationXY * f4, -rotationZ * f4, rotationYZ * f4 - rotationXZ * f4)};

        if (this.particleAngle != 0.0F)
        {
            final float f9 = MathHelper.cos(particleAngle * 0.5F);
            final float f10 = MathHelper.sin(particleAngle * 0.5F) * (float)cameraViewDir.x;
            final float f11 = MathHelper.sin(particleAngle * 0.5F) * (float)cameraViewDir.y;
            final float f12 = MathHelper.sin(particleAngle * 0.5F) * (float)cameraViewDir.z;
            final Vec3d vec3d = new Vec3d(f10, f11, f12);

            for (int l = 0; l < 4; ++l)
            {
                avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double)(f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale(2.0F * f9));
            }
        }
        
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        
        buffer.pos((double)f5 + avec3d[0].x, (double)f6 + avec3d[0].y, (double)f7 + avec3d[0].z).tex(1, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.workingAlpha).lightmap(240, 240).endVertex();
        buffer.pos((double)f5 + avec3d[1].x, (double)f6 + avec3d[1].y, (double)f7 + avec3d[1].z).tex(1, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.workingAlpha).lightmap(240, 240).endVertex();
        buffer.pos((double)f5 + avec3d[2].x, (double)f6 + avec3d[2].y, (double)f7 + avec3d[2].z).tex(0, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.workingAlpha).lightmap(240, 240).endVertex();
        buffer.pos((double)f5 + avec3d[3].x, (double)f6 + avec3d[3].y, (double)f7 + avec3d[3].z).tex(0, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.workingAlpha).lightmap(240, 240).endVertex();
	
        Tessellator.getInstance().draw();
        
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
	}

}
