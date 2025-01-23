package com.hbm.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleNT extends Particle {

	private final ParticleDefinition definition;
	
	protected ParticleNT(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final ParticleDefinition definition) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.definition = definition;
	}
	
	public ParticleNT(final World world, final double x, final double y, final double z, final double moX, final double moY, final double moZ, final ParticleDefinition definition) {
		this(world, x, y, z, definition);
		this.motionX = moX;
		this.motionY = moY;
		this.motionZ = moZ;
	}

}
