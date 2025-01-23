package com.hbm.particle_instanced;

import java.nio.ByteBuffer;
import java.util.Random;

import com.hbm.main.ModEventHandlerClient;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleRocketFlameInstanced extends ParticleInstanced {

	private int age;
	private final int maxAge;
	private final float[] vals = new float[10*5];
	
	public ParticleRocketFlameInstanced(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		maxAge = 300 + rand.nextInt(50);
		this.particleTexture = ModEventHandlerClient.particle_base;
		initVals(worldIn.rand.nextInt());
	}
	
	private void initVals(final int randSeed){
		final Random urandom = new Random(randSeed);
		for(int i = 0; i < 10; i ++){
			//The three random values that are added to the position when rendering
			vals[i*5] = (float) (urandom.nextGaussian() - 1D) * 0.2F;
			vals[i*5+1] = (float) (urandom.nextGaussian() - 1D) * 0.5F;
			vals[i*5+2] = (float) (urandom.nextGaussian() - 1D) * 0.2F;
			//Random scale
			vals[i*5+3] = (urandom.nextFloat() * 0.5F + 0.1F)*4;
			//Random color add
			vals[i*5+4] = urandom.nextFloat() * 0.3F;
		}
	}
	
	public void setMotionY(final double y){
		this.motionY = y;
	}
	
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		this.age++;

		if (this.age == this.maxAge) {
			this.setExpired();
		}

		this.motionX *= 0.9099999785423279D;
		this.motionY *= 0.9099999785423279D;
		this.motionZ *= 0.9099999785423279D;
		
        this.move(this.motionX, this.motionY, this.motionZ);
	}
	
	@Override
	public void addDataToBuffer(final ByteBuffer buf, final float partialTicks) {
		final float x = (float) ((this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX));
		final float y = (float) ((this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY));
		final float z = (float) ((this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ));
		final float spread = (float) Math.pow(((float)(age) / (float)maxAge) * 4F, 1.5) + 1F;
		final float scaleLevel = ((float)(age) / (float)maxAge) * 8F;
		this.particleAlpha = MathHelper.clamp((float) Math.pow(1 - Math.min(((float)(age) / (float)(maxAge)), 1), 0.5), 0, 1)*0.75F;
		for(int ii = 0; ii < 10; ii ++){
			buf.putFloat(x+vals[ii*5]*spread);
			buf.putFloat(y+vals[ii*5+1]*spread);
			buf.putFloat(z+vals[ii*5+2]*spread);
			
			final float scale = vals[ii*5+3]+scaleLevel;
			buf.putFloat(scale);
			
			buf.putFloat(this.particleTexture.getMinU());
			buf.putFloat(this.particleTexture.getMinV());
			buf.putFloat(this.particleTexture.getMaxU()-this.particleTexture.getMinU());
			buf.putFloat(this.particleTexture.getMaxV()-this.particleTexture.getMinV());
			
			final float add = vals[ii*5+4];
			final float dark = 1 - Math.min(((float)(age) / (maxAge * 0.25F)), 1);
			
	        this.particleRed = MathHelper.clamp(dark + add, 0, 1);
	        this.particleGreen = MathHelper.clamp(0.6F * dark + add, 0, 1);
	        this.particleBlue = add;
			
			final byte r = (byte) (this.particleRed*255);
			final byte g = (byte) (this.particleGreen*255);
			final byte b = (byte) (this.particleBlue*255);
			final byte a = (byte) (this.particleAlpha*255);
			buf.put(r);
			buf.put(g);
			buf.put(b);
			buf.put(a);
			
			//int i = this.getBrightnessForRender(partialTicks);
			//int j = i >> 16 & 65535;
			//int k = i & 65535;
			//Bruh I have no clue how these lightmap coords work. They don't seem to be like regular uvs.
			buf.put((byte) 240);
			buf.put((byte) 240);
		}
	}
	
	@Override
	public int getFaceCount() {
		return 10;
	}

}
