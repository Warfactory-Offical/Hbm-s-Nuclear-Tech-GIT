package net.minecraft.client.particle;

//Stupid minecraft particles have all their fields protected. Solution? Make a class in the same package!
public class HbmParticleUtility {

	public static void setNoClip(final Particle p){
		p.canCollide = false;
	}
	
	public static void setMotion(final Particle p, final double mX, final double mY, final double mZ){
		p.motionX = mX;
		p.motionY = mY;
		p.motionZ = mZ;
	}
	
	public static void setMaxAge(final Particle p, final int maxAge){
		p.particleMaxAge = maxAge;
	}
	
	public static void setSmokeScale(final ParticleSmokeNormal p, final float scale){
		p.smokeParticleScale = scale;
	}
	
	public static void resetSmokeScaleWithMult(final ParticleSmokeNormal p, final float mult){
		p.particleScale *= mult;
		p.smokeParticleScale = p.particleScale;
	}
	
}
