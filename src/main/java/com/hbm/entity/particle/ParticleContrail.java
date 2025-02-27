package com.hbm.entity.particle;

import java.util.Random;

import com.hbm.main.ModEventHandlerClient;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleContrail extends Particle {

	private final TextureManager theRenderEngine;
	public boolean doFlames = false;
	public float flameRed;
	public float flameGreen;
	public float flameBlue;
	public float lowRed;
	public float lowGreen;
	public float lowBlue;
	private int age = 0;
	private final int maxAge;

	public ParticleContrail(final TextureManager manage, final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		theRenderEngine = manage;
		maxAge = 100 + rand.nextInt(20);

		this.particleRed = this.particleGreen = this.particleBlue = 0;
		this.particleScale = 1F;
	}

	public ParticleContrail(final TextureManager manage, final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float red, final float green, final float blue, final float scale) {
		super(worldIn, posXIn, posYIn, posZIn);
		theRenderEngine = manage;
		maxAge = 100 + rand.nextInt(20);

		this.lowRed = red;
		this.lowGreen = green;
		this.lowBlue = blue;

		this.particleScale = scale;
	}

	public ParticleContrail(final TextureManager manage, final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float flameRed, final float flameGreen, final float flameBlue, final float red, final float green, final float blue, final float scale) {
		this(manage, worldIn, posXIn, posYIn, posZIn, red, green, blue, scale);
		this.flameRed = flameRed;
		this.flameGreen = flameGreen;
		this.flameBlue = flameBlue;
		this.doFlames = true;
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
		this.setAlphaF(1F - ((float) this.age / (float) this.maxAge));

		this.age++;

		if (this.age == this.maxAge) {
			this.setExpired();
		}
		this.motionX *= 0.91;
		this.motionY *= 0.91;
		this.motionZ *= 0.91;
		
        this.move(this.motionX, this.motionY, this.motionZ);
	}

	private float clampGood(final float b, final float a, final float c){
		if(c < a){
			return MathHelper.clamp(b, c, a);
		}
		else{
			return MathHelper.clamp(b, a, c);
		}
	}

	private float getColor(final int index){
		float pColor = 0;
		if(index == 0){
			if(doFlames){
				pColor = clampGood(this.flameRed - (this.flameRed-this.lowRed)*(1-particleAlpha)*5F, this.flameRed, this.lowRed);
			} else {
				pColor = this.lowRed;
			}
		} else if(index == 1){
			if(doFlames){
				pColor = clampGood(this.flameGreen - (this.flameGreen-this.lowGreen)*(1-particleAlpha)*5F, this.flameGreen, this.lowGreen);
			} else {
				pColor = this.lowGreen;
			}
		} else if(index == 2){
			if(doFlames){
				pColor = clampGood(this.flameBlue - (this.flameBlue-this.lowBlue)*(1-particleAlpha)*5F, this.flameBlue, this.lowBlue);
			} else {
				pColor = this.lowBlue;
			}
		}
		return MathHelper.clamp(pColor, 0, 1);
	}

	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		
		this.theRenderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		this.particleTexture = ModEventHandlerClient.contrail;
		float f = (float) this.particleTextureIndexX / 16.0F;
		float f1 = f + 0.0624375F;
		float f2 = (float) this.particleTextureIndexY / 16.0F;
		float f3 = f2 + 0.0624375F;
		final float f4 = 0.25F + 3F * this.particleScale * (1-particleAlpha * particleAlpha);

		if (this.particleTexture != null) {
			f = this.particleTexture.getMinU();
			f1 = this.particleTexture.getMaxU();
			f2 = this.particleTexture.getMinV();
			f3 = this.particleTexture.getMaxV();
		}
		this.setRBGColorF(getColor(0), getColor(1), getColor(2));
		final Random urandom = new Random(this.hashCode());
		for (int ii = 0; ii < 6; ii++) {
			
			final float f5 = (float) ((this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX) + urandom.nextGaussian() * (0.2 + (1-particleAlpha)));
			final float f6 = (float) ((this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY) + urandom.nextGaussian() * (0.2 + (1-particleAlpha)));
			final float f7 = (float) ((this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ) + urandom.nextGaussian() * (0.2 + (1-particleAlpha)));
			
			final int i = this.getBrightnessForRender(partialTicks);
			final int j = i >> 16 & 65535;
			final int k = i & 65535;
			
			final Vec3d[] avec3d = new Vec3d[] { new Vec3d(-rotationX * f4 - rotationXY * f4, -rotationZ * f4, -rotationYZ * f4 - rotationXZ * f4), new Vec3d(-rotationX * f4 + rotationXY * f4, rotationZ * f4, -rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 + rotationXY * f4, rotationZ * f4, rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 - rotationXY * f4, -rotationZ * f4, rotationYZ * f4 - rotationXZ * f4) };

			if (this.particleAngle != 0.0F) {
				final float f8 = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * partialTicks;
				final float f9 = MathHelper.cos(f8 * 0.5F);
				final float f10 = MathHelper.sin(f8 * 0.5F) * (float) cameraViewDir.x;
				final float f11 = MathHelper.sin(f8 * 0.5F) * (float) cameraViewDir.y;
				final float f12 = MathHelper.sin(f8 * 0.5F) * (float) cameraViewDir.z;
				final Vec3d vec3d = new Vec3d(f10, f11, f12);

				for (int l = 0; l < 4; ++l) {
					avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double) (f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale(2.0F * f9));
				}
			}
			
			buffer.pos((double) f5 + avec3d[0].x, (double) f6 + avec3d[0].y, (double) f7 + avec3d[0].z).tex(f1, f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
			buffer.pos((double) f5 + avec3d[1].x, (double) f6 + avec3d[1].y, (double) f7 + avec3d[1].z).tex(f1, f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
			buffer.pos((double) f5 + avec3d[2].x, (double) f6 + avec3d[2].y, (double) f7 + avec3d[2].z).tex(f, f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
			buffer.pos((double) f5 + avec3d[3].x, (double) f6 + avec3d[3].y, (double) f7 + avec3d[3].z).tex(f, f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		}
	}

	@Override
	public int getBrightnessForRender(final float p_189214_1_) {
		return (int)(240 * particleAlpha);
	}

	public int getFXLayer() {
        return 1;
    }
}
