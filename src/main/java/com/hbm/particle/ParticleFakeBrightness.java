package com.hbm.particle;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ClientProxy;
import com.hbm.main.ResourceManager;
import com.hbm.render.misc.LensVisibilityHandler;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ParticleFakeBrightness extends Particle {

	int visibilityId = -1;
	boolean local;
	public float fadeInKoeff = 2;
	
	public ParticleFakeBrightness(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float scale, final int age) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleScale = scale;
		this.particleMaxAge = age;
	}
	
	public ParticleFakeBrightness color(final float r, final float g, final float b, final float a){
		this.particleRed = r;
		this.particleGreen = g;
		this.particleBlue = b;
		this.particleAlpha = a;
		return this;
	}
	
	public ParticleFakeBrightness enableLocalSpaceCorrection(){
		local = true;
		return this;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge ++;
		if(particleAge >= particleMaxAge){
			setExpired();
			LensVisibilityHandler.delete(visibilityId);
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
		
        if(local){
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
        } else {
        	final double entPosX = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX)*partialTicks;
            final double entPosY = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY)*partialTicks;
            final double entPosZ = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ)*partialTicks;
            
            interpPosX = entPosX;
            interpPosY = entPosY;
            interpPosZ = entPosZ;
        	final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
            final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
            final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
            GL11.glTranslated(f5, f6, f7);
        }
		if(visibilityId == -1){
			GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
			visibilityId = LensVisibilityHandler.generate(ClientProxy.AUX_GL_BUFFER);
		}
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
		LensVisibilityHandler.putMatrixBuf(visibilityId, ClientProxy.AUX_GL_BUFFER);
		
		float visibility = LensVisibilityHandler.getVisibility(visibilityId);
		visibility *= visibility;
		
		final float ageN = (this.particleAge+partialTicks) /(float)this.particleMaxAge;
		final float scale = MathHelper.clamp(ageN*fadeInKoeff, 0, 1)* MathHelper.clamp(2-ageN*fadeInKoeff+0.1F, 0, 1);
		final float f4 = 0.1F * this.particleScale * visibility*scale;
        
        final Vec3d[] avec3d = new Vec3d[] {new Vec3d(-rotationX * f4 - rotationXY * f4, -rotationZ * f4, -rotationYZ * f4 - rotationXZ * f4), new Vec3d(-rotationX * f4 + rotationXY * f4, rotationZ * f4, -rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 + rotationXY * f4, rotationZ * f4, rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 - rotationXY * f4, -rotationZ * f4, rotationYZ * f4 - rotationXZ * f4)};
        if(!local){
        	GlStateManager.enableBlend();
        	GlStateManager.disableAlpha();
        	GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
        }
        
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.fresnel_ms);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
		buffer.pos(avec3d[0].x, avec3d[0].y, avec3d[0].z).tex(1, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha*visibility).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[1].x, avec3d[1].y, avec3d[1].z).tex(1, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha*visibility).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[2].x, avec3d[2].y, avec3d[2].z).tex(0, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha*visibility).lightmap(240, 240).endVertex();
        buffer.pos(avec3d[3].x, avec3d[3].y, avec3d[3].z).tex(0, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha*visibility).lightmap(240, 240).endVertex();
        Tessellator.getInstance().draw();
        
        if(!local){
        	GlStateManager.disableBlend();
        	GlStateManager.enableAlpha();
        }
		GlStateManager.enableDepth();
		GL11.glPopMatrix();
	}
}
