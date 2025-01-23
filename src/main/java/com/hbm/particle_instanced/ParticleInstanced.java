package com.hbm.particle_instanced;

import java.nio.ByteBuffer;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleInstanced extends Particle {

	protected ParticleInstanced(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
	}
	
	public void addDataToBuffer(final ByteBuffer buf, final float partialTicks){
		final float x = (float) ((this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX));
		final float y = (float) ((this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY));
		final float z = (float) ((this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ));
		buf.putFloat(x);
		buf.putFloat(y);
		buf.putFloat(z);
		buf.putFloat(this.particleScale);
		buf.putFloat(this.particleTexture.getMinU());
		buf.putFloat(this.particleTexture.getMinV());
		buf.putFloat(this.particleTexture.getMaxU()-this.particleTexture.getMinU());
		buf.putFloat(this.particleTexture.getMaxV()-this.particleTexture.getMinV());
		final byte r = (byte) (this.particleRed*255);
		final byte g = (byte) (this.particleGreen*255);
		final byte b = (byte) (this.particleBlue*255);
		final byte a = (byte) (this.particleAlpha*255);
		buf.put(r);
		buf.put(g);
		buf.put(b);
		buf.put(a);
		final int i = this.getBrightnessForRender(partialTicks);
		int j = i >> 16 & 65535;
		int k = i & 65535;
		j = 240;
		k = 240;
		buf.put((byte) j);
		buf.put((byte) k);
	}
	
	public int getFaceCount(){
		return 1;
	}

}
