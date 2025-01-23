package com.hbm.particle.rocket;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ClientProxy;
import com.hbm.particle.ParticleLayerBase;
import com.hbm.particle.ParticleRenderLayer;
import com.hbm.render.misc.ColorGradient;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ParticleRocketPlasma extends ParticleLayerBase {

	public ColorGradient color;
	
	public ParticleRocketPlasma(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float scale, final ColorGradient color) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.color = color;
		this.particleMaxAge = 5;
		this.particleScale = scale;
	}

	public ParticleRocketPlasma motion(final float mX, final float mY, final float mZ){
		this.motionX = mX;
		this.motionY = mY;
		this.motionZ = mZ;
		return this;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge++;
		if(this.particleAge >= this.particleMaxAge){
			setExpired();
			return;
		}
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		GL11.glPushMatrix();
		final float timeScale = (this.particleAge+partialTicks)/(float)this.particleMaxAge;
		final float[] currentCol = color.getColor(timeScale);
		
		final float f4 = (float) (0.1F * this.particleScale * (1-Math.pow(timeScale, 2)));
        
        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks);
        GL11.glTranslated(f5, f6, f7);
        if(BobMathUtil.r_viewMat == null){
			BobMathUtil.r_viewMat = ReflectionHelper.findField(ActiveRenderInfo.class, "MODELVIEW", "field_178812_b");
		}
		try {
			final FloatBuffer view_mat = (FloatBuffer) BobMathUtil.r_viewMat.get(null);
			view_mat.rewind();
			GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
			for(int i = 0; i < 12; i ++){
				ClientProxy.AUX_GL_BUFFER.put(i, view_mat.get(i));
			}
			ClientProxy.AUX_GL_BUFFER.rewind();
			GL11.glLoadMatrix(ClientProxy.AUX_GL_BUFFER);
		} catch(final IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
        final Vec3d[] avec3d = new Vec3d[] {new Vec3d(-rotationX * f4 - rotationXY * f4, -rotationZ * f4, -rotationYZ * f4 - rotationXZ * f4), new Vec3d(-rotationX * f4 + rotationXY * f4, rotationZ * f4, -rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 + rotationXY * f4, rotationZ * f4, rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 - rotationXY * f4, -rotationZ * f4, rotationYZ * f4 - rotationXZ * f4)};
        
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        buffer.pos(avec3d[3].x, avec3d[3].y, avec3d[3].z).tex(0, 1).color(currentCol[0], currentCol[1], currentCol[2], currentCol[3]).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[2].x, avec3d[2].y, avec3d[2].z).tex(0, 0).color(currentCol[0], currentCol[1], currentCol[2], currentCol[3]).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[1].x, avec3d[1].y, avec3d[1].z).tex(1, 0).color(currentCol[0], currentCol[1], currentCol[2], currentCol[3]).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[0].x, avec3d[0].y, avec3d[0].z).tex(1, 1).color(currentCol[0], currentCol[1], currentCol[2], currentCol[3]).lightmap(240, 240).endVertex();
        
        
        Tessellator.getInstance().draw();
        GL11.glPopMatrix();
	}
	
	@Override
	public ParticleRenderLayer getRenderLayer() {
		return ParticleRenderLayer.ADDITIVE_FRESNEL;
	}

}
