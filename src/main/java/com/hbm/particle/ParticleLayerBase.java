package com.hbm.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public abstract class ParticleLayerBase extends Particle {
	
	public ParticleLayerBase(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
	}

	public abstract ParticleRenderLayer getRenderLayer();
}
