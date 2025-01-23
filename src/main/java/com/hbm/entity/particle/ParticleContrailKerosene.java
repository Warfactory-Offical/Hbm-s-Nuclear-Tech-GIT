package com.hbm.entity.particle;

import com.hbm.entity.particle.ParticleContrail;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.World;

public class ParticleContrailKerosene extends ParticleContrail {

	public ParticleContrailKerosene(final TextureManager manage, final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(manage, worldIn, posXIn, posYIn, posZIn, 0.9F, 0.8F, 0.7F, 1F);
		this.flameRed = 0.5F;
		this.flameGreen = 0.9F;
		this.flameBlue = 1F;
		this.doFlames = true;
	}
}
