package com.hbm.entity.particle;

import com.hbm.entity.particle.ParticleContrail;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.World;

public class ParticleContrailHydrogen extends ParticleContrail {

	public ParticleContrailHydrogen(final TextureManager manage, final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(manage, worldIn, posXIn, posYIn, posZIn, 0.9F, 0.9F, 0.9F, 1F);
		this.flameRed = 0.9F;
		this.flameGreen = 0.6F;
		this.flameBlue = 1F;
		this.doFlames = true;
	}
}
