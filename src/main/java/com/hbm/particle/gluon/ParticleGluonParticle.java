package com.hbm.particle.gluon;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ClientProxy;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleGluonParticle extends Particle {

	float workingAlpha;
	
	protected ParticleGluonParticle(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float scale, final int maxAge) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleMaxAge = maxAge;
		this.particleScale = scale;
		this.particleRed = 0.4F;
		this.particleGreen = 0.7F;
	}

	@Override
	public void onUpdate() {
		this.particleAge++;
		if(particleAge >= particleMaxAge){
			this.setExpired();
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
	
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ, final FloatBuffer mat) {
		GL11.glPushMatrix();
		final float timeScale = (this.particleAge+partialTicks)/(float)this.particleMaxAge;
		final float shrink = MathHelper.clamp(1-BobMathUtil.remap(MathHelper.clamp(timeScale, 0, 1), 0.6F, 1F, 0.6F, 1F), 0, 1);
		this.workingAlpha = shrink*particleAlpha*0.9F;
		
		final float f4 = 0.1F * (this.particleScale+shrink*particleScale*4);
        
        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks);
        
        GL11.glTranslated(f5, f6, f7);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER2);
		ClientProxy.AUX_GL_BUFFER2.rewind();
		final float[] trans = new float[3];
		ClientProxy.AUX_GL_BUFFER2.position(12);
		ClientProxy.AUX_GL_BUFFER2.get(trans);
		ClientProxy.AUX_GL_BUFFER2.rewind();
		mat.position(12);
		mat.put(trans);
		mat.rewind();
		GL11.glLoadMatrix(mat);
		
        final Vec3d[] avec3d = new Vec3d[] {new Vec3d(-rotationX * f4 - rotationXY * f4, -rotationZ * f4, -rotationYZ * f4 - rotationXZ * f4), new Vec3d(-rotationX * f4 + rotationXY * f4, rotationZ * f4, -rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 + rotationXY * f4, rotationZ * f4, rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 - rotationXY * f4, -rotationZ * f4, rotationYZ * f4 - rotationXZ * f4)};
        //I can't figure out a way to batch these particles without screwing up the hacky rotation fixes I'm doing.
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        buffer.pos(avec3d[0].x, avec3d[0].y, avec3d[0].z).tex(1, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.workingAlpha).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[1].x, avec3d[1].y, avec3d[1].z).tex(1, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.workingAlpha).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[2].x, avec3d[2].y, avec3d[2].z).tex(0, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.workingAlpha).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[3].x, avec3d[3].y, avec3d[3].z).tex(0, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.workingAlpha).lightmap(240, 240).endVertex();
        Tessellator.getInstance().draw();
        GL11.glPopMatrix();
	}
	
}
