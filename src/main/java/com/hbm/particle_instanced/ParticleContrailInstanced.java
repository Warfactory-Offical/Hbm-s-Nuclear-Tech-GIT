package com.hbm.particle_instanced;

import java.nio.ByteBuffer;
import java.util.Random;

import com.hbm.main.ModEventHandlerClient;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleContrailInstanced extends ParticleInstanced {

	private int age = 0;
	private final int maxAge;
	private final float scale;
	private final float[] vals = new float[4*6];
	private boolean doFlames = false;
	private static float flameRed;
	private static float flameGreen;
	private static float flameBlue;
	private static float lowRed;
	private static float lowGreen;
	private static float lowBlue;

	
	public ParticleContrailInstanced(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleTexture = ModEventHandlerClient.contrail;
		maxAge = 100 + rand.nextInt(20);

		this.particleRed = this.particleGreen = this.particleBlue = 0;
		this.scale = 1F;
		initVals();
	}
	
	public ParticleContrailInstanced(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float red, final float green, final float blue, final float scale) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleTexture = ModEventHandlerClient.contrail;
		maxAge = 100 + rand.nextInt(20);

		lowRed = red;
		lowGreen = green;
		lowBlue = blue;

		this.scale = scale;
		initVals();
	}

	public ParticleContrailInstanced(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float flameRed, final float flameGreen, final float flameBlue, final float red, final float green, final float blue, final float scale) {
		this(worldIn, posXIn, posYIn, posZIn, red, green, blue, scale);
		ParticleContrailInstanced.flameRed = flameRed;
		ParticleContrailInstanced.flameGreen = flameGreen;
		ParticleContrailInstanced.flameBlue = flameBlue;

		this.doFlames = true;
	}
	
	public void initVals(){
		final Random urandom = new Random(this.hashCode());
		for(int i = 0; i < 6; i ++){
			//The three random values that are added to the position when rendering
			vals[i*4] = (float) (urandom.nextGaussian()*0.5F);
			vals[i*4+1] = (float) (urandom.nextGaussian()*0.5F);
			vals[i*4+2] = (float) (urandom.nextGaussian()*0.5F);
		}
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
		this.particleAlpha = 1F - ((float) this.age / (float) this.maxAge);

		this.age++;

		if (this.age == this.maxAge) {
			this.setExpired();
		}
		this.motionX *= 0.91D;
		this.motionY *= 0.91D;
		this.motionZ *= 0.91D;
		
        this.move(this.motionX, this.motionY, this.motionZ);
	}

	private byte getColor(final int index){
		float pColor = 0;
		if(index == 0){
			if(doFlames){
				pColor = lowRed + (flameRed- lowRed)*particleAlpha*0.1F;
			} else {
				pColor = lowRed;
			}
			this.particleRed = pColor;
		} else if(index == 1){
			if(doFlames){
				pColor = lowGreen + (flameGreen- lowGreen)*particleAlpha*0.1F;
			} else {
				pColor = lowGreen;
			}
			this.particleGreen = pColor;
		} else if(index == 2){
			if(doFlames){
				pColor = lowBlue + (flameBlue- lowBlue)*particleAlpha*0.1F;
			} else {
				pColor = lowBlue;
			}
			this.particleBlue = pColor;
		}
		return (byte)(MathHelper.clamp(pColor, 0, 1)*255);
	}
	
	@Override
	public void addDataToBuffer(final ByteBuffer buf, final float partialTicks) {
		final float x = (float) ((this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX));
		final float y = (float) ((this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY));
		final float z = (float) ((this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ));
		this.particleScale = (1-particleAlpha * particleAlpha)*4F * this.scale + 0.25F;
		for(int ii = 0; ii < 6; ii++){
			
			buf.putFloat(x+vals[ii*4]);
			buf.putFloat(y+vals[ii*4+1]);
			buf.putFloat(z+vals[ii*4+2]);
			
			buf.putFloat(this.particleScale);
			
			buf.putFloat(this.particleTexture.getMinU());
			buf.putFloat(this.particleTexture.getMinV());
			buf.putFloat(this.particleTexture.getMaxU()-this.particleTexture.getMinU());
			buf.putFloat(this.particleTexture.getMaxV()-this.particleTexture.getMinV());
			
			final byte r = getColor(0);
			final byte g = getColor(1);
			final byte b = getColor(2);
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
		return 6;
	}
	
	@Override
	public int getBrightnessForRender(final float p_189214_1_) {
		return (int)(240 * particleAlpha);
	}

	public int getFXLayer() {
        return 1;
    }
}
