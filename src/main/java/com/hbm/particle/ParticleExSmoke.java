package com.hbm.particle;

import java.util.Random;

import com.hbm.main.ModEventHandlerClient;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleExSmoke extends Particle {

	private int age;
	private final int maxAge;
	private final int randomSeed;
	
	public ParticleExSmoke(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		maxAge = 100 + rand.nextInt(40);
		randomSeed = worldIn.rand.nextInt();
		this.setParticleTexture(ModEventHandlerClient.contrail);
	}
	
	public void setMotion(final double x, final double y, final double z){
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
	}
	
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		particleAlpha = 1 - ((float) age / (float) maxAge);
		
		++this.age;

		if (this.age == this.maxAge) {
			this.setExpired();
		}

		this.motionX *= 0.7599999785423279D;
		this.motionY *= 0.7599999785423279D;
		this.motionZ *= 0.7599999785423279D;
		
        this.move(this.motionX, this.motionY, this.motionZ);
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return true;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		
		final Random urandom = new Random(randomSeed);
		
		for(int i = 0; i < 6; i++) {
			
	        this.particleRed = this.particleGreen = this.particleBlue = urandom.nextFloat() * 0.5F + 0.4F;
	        
	        final int j = this.getBrightnessForRender(partialTicks);
	        final int k = j >> 16 & 65535;
	        final int l = j & 65535;
	        
			final float scale = urandom.nextFloat() + 0.5F;
	        final float pX = (float) ((this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX) + (urandom.nextGaussian() - 1D) * 0.75F);
	        final float pY = (float) ((this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY) + (urandom.nextGaussian() - 1D) * 0.75F);
	        final float pZ = (float) ((this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ) + (urandom.nextGaussian() - 1D) * 0.75F);
	        
	        buffer.pos(pX - rotationX * scale - rotationXY * scale, pY - rotationZ * scale, pZ - rotationYZ * scale - rotationXZ * scale).tex(particleTexture.getMaxU(), particleTexture.getMaxV()).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(k, l).endVertex();
			buffer.pos(pX - rotationX * scale + rotationXY * scale, pY + rotationZ * scale, pZ - rotationYZ * scale + rotationXZ * scale).tex(particleTexture.getMaxU(), particleTexture.getMinV()).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(k, l).endVertex();
			buffer.pos(pX + rotationX * scale + rotationXY * scale, pY + rotationZ * scale, pZ + rotationYZ * scale + rotationXZ * scale).tex(particleTexture.getMinU(), particleTexture.getMinV()).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(k, l).endVertex();
			buffer.pos(pX + rotationX * scale - rotationXY * scale, pY - rotationZ * scale, pZ + rotationYZ * scale - rotationXZ * scale).tex(particleTexture.getMinU(), particleTexture.getMaxV()).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(k, l).endVertex();
		}
	}

	@Override
	public int getBrightnessForRender(final float p_189214_1_) {
		return 240;
	}
}
