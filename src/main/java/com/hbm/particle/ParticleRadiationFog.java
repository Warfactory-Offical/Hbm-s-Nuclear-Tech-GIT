package com.hbm.particle;

import java.util.Random;

import com.hbm.main.ModEventHandlerClient;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleRadiationFog extends Particle {

	private int maxAge;
	
	public ParticleRadiationFog(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		maxAge = 100 + rand.nextInt(40);
		
        this.particleRed = 0.8F;
        this.particleGreen = 0.8F;
        this.particleBlue = 0.8F;
        this.particleScale = 7.5F;
        this.particleTexture = ModEventHandlerClient.fog;
	}
	
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        if(maxAge < 400)
        {
        	maxAge = 400;
        }

        this.particleAge++;
        
        if (this.particleAge >= maxAge)
        {
            this.setExpired();
        }

        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
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
		float alpha = 0;
		
		alpha = (float) Math.sin(particleAge * Math.PI / (400F)) * 0.25F;

        this.particleAlpha = MathHelper.clamp(alpha, 0, 1);
		
		final Random rand = new Random(50);
		
		for(int i = 0; i < 25; i++) {

			final double dX = (rand.nextGaussian() - 1D) * 2.5D;
			final double dY = (rand.nextGaussian() - 1D) * 0.15D;
			final double dZ = (rand.nextGaussian() - 1D) * 2.5D;
			final double size = (0.75D+rand.nextDouble() * 0.5D) * particleScale;
			
			final int j = this.getBrightnessForRender(partialTicks);
			final int k = j >> 16 & 65535;
			final int l = j & 65535;
	        
	        final float pX = (float) ((this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX) + rand.nextGaussian() * 0.5);
	        final float pY = (float) ((this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY) + rand.nextGaussian() * 0.5);
	        final float pZ = (float) ((this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ) + rand.nextGaussian() * 0.5);
			
	        buffer.pos((pX - rotationX * size - rotationXY * size) + dX, (pY - rotationZ * size) + dY, (pZ - rotationYZ * size - rotationXZ * size) + dZ).tex(particleTexture.getMaxU(), particleTexture.getMaxV()).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(k, l).endVertex();
	        buffer.pos(pX - rotationX * size + rotationXY * size + dX, pY + rotationZ * size + dY, pZ - rotationYZ * size + rotationXZ * size + dZ).tex(particleTexture.getMaxU(), particleTexture.getMinV()).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(k, l).endVertex();
	        buffer.pos(pX + rotationX * size + rotationXY * size + dX, pY + rotationZ * size + dY, pZ + rotationYZ * size + rotationXZ * size + dZ).tex(particleTexture.getMinU(), particleTexture.getMinV()).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(k, l).endVertex();
	        buffer.pos((pX + rotationX * size - rotationXY * size) + dX, (pY - rotationZ * size) + dY, (pZ + rotationYZ * size - rotationXZ * size) + dZ).tex(particleTexture.getMinU(), particleTexture.getMaxV()).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(k, l).endVertex();
		}
		
	}
	
	@Override
	public int getBrightnessForRender(final float p_189214_1_) {
		return 70;
	}
}
