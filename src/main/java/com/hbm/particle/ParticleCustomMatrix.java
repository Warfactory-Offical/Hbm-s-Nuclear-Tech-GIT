package com.hbm.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

//Drillgon200: I thought I needed a base class for this, but I guess not. Oh well.
public class ParticleCustomMatrix extends Particle {

	public ParticleCustomMatrix(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
	}

}
