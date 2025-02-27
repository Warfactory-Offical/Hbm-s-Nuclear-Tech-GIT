package com.hbm.particle;

import com.hbm.main.ModEventHandlerClient;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ParticleDigammaSmoke extends Particle {

	public ParticleDigammaSmoke(final World worldIn, final double posXIn, final double posYIn, final double posZIn){
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleTexture = ModEventHandlerClient.particle_base;
		particleMaxAge = 100 + rand.nextInt(40);
		this.canCollide = false;
		
		this.particleScale = 5;
		
		this.particleRed = 0.5F + rand.nextFloat() * 0.2F;
		this.particleGreen = 0.0F;
		this.particleBlue = 0.0F;
	}
	
	public void motion(final float x, final float y, final float z){
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
	}
	
	@Override
	public void onUpdate(){
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		particleAlpha = 1 - ((float) particleAge / (float) particleMaxAge);

		++this.particleAge;

		if(this.particleAge == this.particleMaxAge) {
			this.setExpired();
		}

		this.motionX *= 0.99D;
		this.motionY *= 0.99D;
		this.motionZ *= 0.99D;

		this.move(this.motionX, this.motionY, this.motionZ);
	}
	
	@Override
	public int getFXLayer(){
		return 1;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buf, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ){
		final float scale = this.particleScale;
		final float pX = (float) ((this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX));
		final float pY = (float) ((this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY));
		final float pZ = (float) ((this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ));

		buf.pos(pX - rotationX * scale - rotationXY * scale, pY - rotationZ * scale, pZ - rotationYZ * scale - rotationXZ * scale).tex(particleTexture.getMaxU(), particleTexture.getMaxV()).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(240, 240).endVertex();
		buf.pos(pX - rotationX * scale + rotationXY * scale, pY + rotationZ * scale, pZ - rotationYZ * scale + rotationXZ * scale).tex(particleTexture.getMaxU(), particleTexture.getMinV()).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(240, 240).endVertex();
		buf.pos(pX + rotationX * scale + rotationXY * scale, pY + rotationZ * scale, pZ + rotationYZ * scale + rotationXZ * scale).tex(particleTexture.getMinU(), particleTexture.getMinV()).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(240, 240).endVertex();
		buf.pos(pX + rotationX * scale - rotationXY * scale, pY - rotationZ * scale, pZ + rotationYZ * scale - rotationXZ * scale).tex(particleTexture.getMinU(), particleTexture.getMaxV()).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(240, 240).endVertex();
	}

	@Override
	public int getBrightnessForRender(final float p_189214_1_){
		return 240;
	}
}
