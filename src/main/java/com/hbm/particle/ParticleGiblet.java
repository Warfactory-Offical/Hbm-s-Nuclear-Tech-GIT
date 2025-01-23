package com.hbm.particle;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.HbmParticleUtility;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleBlockDust;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleGiblet extends Particle {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/particle/meat.png");
	
	private final float momentumYaw;
	private final float momentumPitch;
	
	public ParticleGiblet(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final double mX, final double mY, final double mZ){
		super(worldIn, posXIn, posYIn, posZIn);
		this.motionX = mX;
		this.motionY = mY;
		this.motionZ = mZ;
		this.particleMaxAge = 140 + rand.nextInt(20);
		this.particleGravity = 2F;

		this.momentumYaw = (float) rand.nextGaussian() * 15F;
		this.momentumPitch = (float) rand.nextGaussian() * 15F;
	}
	
	@Override
	public int getFXLayer(){
		return 3;
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();

		//this.prevRotationPitch = this.rotationPitch;
		//this.prevRotationYaw = this.rotationYaw;
		
		if(!this.onGround) {
			//this.rotationPitch += this.momentumPitch;
			//this.rotationYaw += this.momentumYaw;
			
			final Particle fx = new ParticleBlockDust.Factory().createParticle(-1, world, posX, posY, posZ, 0, 0, 0, Block.getStateId(Blocks.REDSTONE_BLOCK.getDefaultState()));
			HbmParticleUtility.setMaxAge(fx, 20 + rand.nextInt(20));
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
		}
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ){
		GL11.glPushMatrix();
		GlStateManager.disableLighting();
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		final float f10 = this.particleScale * 0.1F;
		final float f11 = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
		final float f12 = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
		final float f13 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);

		final Tessellator tes = Tessellator.getInstance();
		final BufferBuilder buf = tes.getBuffer();
		GlStateManager.color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
		GlStateManager.glNormal3f(0, 1, 0);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buf.pos(f11 - rotationX * f10 - rotationXY * f10, f12 - rotationZ * f10, f13 - rotationYZ * f10 - rotationXZ * f10).tex(0, 0).endVertex();
		buf.pos(f11 - rotationX * f10 + rotationXY * f10, f12 + rotationZ * f10, f13 - rotationYZ * f10 + rotationXZ * f10).tex(0, 1).endVertex();
		buf.pos(f11 + rotationX * f10 + rotationXY * f10, f12 + rotationZ * f10, f13 + rotationYZ * f10 + rotationXZ * f10).tex(1, 1).endVertex();
		buf.pos(f11 + rotationX * f10 - rotationXY * f10, f12 - rotationZ * f10, f13 + rotationYZ * f10 - rotationXZ * f10).tex(1, 0).endVertex();
		tes.draw();
		GL11.glPopMatrix();
	}

}
