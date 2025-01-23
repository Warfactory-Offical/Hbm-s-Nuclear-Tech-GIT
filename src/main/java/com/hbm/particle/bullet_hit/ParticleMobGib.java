package com.hbm.particle.bullet_hit;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.particle.ParticleBatchRenderer;
import com.hbm.physics.RigidBody;
import com.hbm.render.RenderHelper;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleMobGib extends Particle {

	public RigidBody body;
	public ResourceLocation texture;
	public ResourceLocation blood;
	public int displayList;
	
	public ParticleMobGib(final World worldIn, final RigidBody body, final ResourceLocation texture, final int dl) {
		super(worldIn, body.globalCentroid.xCoord, body.globalCentroid.yCoord, body.globalCentroid.zCoord);
		this.body = body;
		this.texture = texture;
		this.blood = ResourceManager.blood_decals[rand.nextInt(ResourceManager.blood_decals.length)];
		this.displayList = dl;
		this.particleMaxAge = 60+rand.nextInt(20);
	}

	@Override
	public void onUpdate() {
		body.minecraftTimestep();
		this.posX = body.globalCentroid.xCoord;
		this.posY = body.globalCentroid.yCoord;
		this.posZ = body.globalCentroid.zCoord;
		if(rand.nextFloat() < 0.4){
			final float randScale = 0.5F;
			final float randPosX = (rand.nextFloat()-0.5F)*randScale;
			final float randPosY = (rand.nextFloat()-0.5F)*randScale;
			final float randPosZ = (rand.nextFloat()-0.5F)*randScale;
			final ParticleBloodParticle blood = new ParticleBloodParticle(world, posX+randPosX, posY+randPosY, posZ+randPosZ, rand.nextInt(9), 2+rand.nextFloat()*1, 0.25F+rand.nextFloat()*0.25F, 10+rand.nextInt(5));
			Vec3d mot = BobMathUtil.randVecInCone(new Vec3d(0, 1, 0), 45, rand);
			mot = mot.scale(0.2F);
			blood.color(0.5F, 0F, 0F);
			blood.motion((float)mot.z, (float)mot.y, (float)mot.z);
			blood.doDecal(rand.nextFloat() < 0.4F);
			blood.doFlow(false);
			ParticleBatchRenderer.addParticle(blood);
		}
		this.particleAge ++;
		if(particleAge >= particleMaxAge){
			setExpired();
			GL11.glDeleteLists(displayList, 1);
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
		GlStateManager.color(0.9F, 0.6F, 0.6F, 1F);
		GL11.glCallList(displayList);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.enablePolygonOffset();
		GlStateManager.doPolygonOffset(-1, -1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(blood);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GlStateManager.color(0.5F, 0, 0, 0.8F);
		GL11.glCallList(displayList);
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.disablePolygonOffset();
		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();
		net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
	}
}
