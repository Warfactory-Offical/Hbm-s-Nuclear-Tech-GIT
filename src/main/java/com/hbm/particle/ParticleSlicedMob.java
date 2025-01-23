package com.hbm.particle;

import org.lwjgl.opengl.GL11;

import com.hbm.config.GeneralConfig;
import com.hbm.handler.HbmShaderManager2;
import com.hbm.main.ClientProxy;
import com.hbm.physics.RigidBody;
import com.hbm.render.RenderHelper;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleSlicedMob extends Particle {

	public ResourceLocation capTex;
	public ResourceLocation texture;
	public float capBloom;
	public int cutMob;
	public int cap;
	public RigidBody body;
	
	public ParticleSlicedMob(final World worldIn, final RigidBody body, final int cutMob, final int cap, final ResourceLocation tex, final ResourceLocation capTex, final float capBloom) {
		super(worldIn, body.globalCentroid.xCoord, body.globalCentroid.yCoord, body.globalCentroid.zCoord);
		this.body = body;
		this.cutMob = cutMob;
		this.cap = cap;
		this.capTex = capTex;
		this.capBloom = capBloom;
		this.texture = tex;
		this.particleMaxAge = 80 + worldIn.rand.nextInt(20);
	}
	
	@Override
	public void onUpdate() {
		body.minecraftTimestep();
		this.posX = body.globalCentroid.xCoord;
		this.posY = body.globalCentroid.yCoord;
		this.posZ = body.globalCentroid.zCoord;
		this.particleAge ++;
		if(particleAge >= particleMaxAge){
			setExpired();
			GL11.glDeleteLists(cutMob, 1);
			GL11.glDeleteLists(cap, 1);
		}
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		GL11.glPushMatrix();
		GlStateManager.enableCull();
		GlStateManager.enableLighting();
		GlStateManager.enableColorMaterial();
		GlStateManager.enableRescaleNormal();
		net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
		final int i = this.getBrightnessForRender(partialTicks);
        final int j = i >> 16 & 65535;
        final int k = i & 65535;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k, j);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		RenderHelper.resetParticleInterpPos(entityIn, partialTicks);
		RenderHelper.resetColor();
		body.doGlTransform(new Vec3(interpPosX, interpPosY, interpPosZ), partialTicks);
		GL11.glCallList(cutMob);
		Minecraft.getMinecraft().getTextureManager().bindTexture(capTex);
		GlStateManager.enablePolygonOffset();
		GlStateManager.doPolygonOffset(-1, -1);
		final float lx = OpenGlHelper.lastBrightnessX;
		final float ly = OpenGlHelper.lastBrightnessY;
		if(capBloom > 0){
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		}
		GL11.glCallList(cap);
		
		
		if(capBloom > 0 && GeneralConfig.bloom){
			final float[] matrix = new float[16];
			GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
			ClientProxy.AUX_GL_BUFFER.get(matrix);
			ClientProxy.AUX_GL_BUFFER.rewind();
			ClientProxy.deferredRenderers.add(() -> {
				GL11.glPushMatrix();
				ClientProxy.AUX_GL_BUFFER.put(matrix);
				ClientProxy.AUX_GL_BUFFER.rewind();
				GL11.glLoadMatrix(ClientProxy.AUX_GL_BUFFER);
				HbmShaderManager2.bloomData.bindFramebuffer(false);
				
				Minecraft.getMinecraft().getTextureManager().bindTexture(capTex);
				GlStateManager.enablePolygonOffset();
				GlStateManager.disableLighting();
				final float x = OpenGlHelper.lastBrightnessX;
				final float y = OpenGlHelper.lastBrightnessY;
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
				GlStateManager.doPolygonOffset(-1, -1);
				GL11.glCallList(cap);
				GlStateManager.disablePolygonOffset();
				GlStateManager.enableLighting();
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, x, y);
				
				Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(false);
				GL11.glPopMatrix();
			});
		}
		
		if(capBloom > 0){
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lx, ly);
		}
		GlStateManager.disablePolygonOffset();
		GlStateManager.disableRescaleNormal();
		net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
	}

}
