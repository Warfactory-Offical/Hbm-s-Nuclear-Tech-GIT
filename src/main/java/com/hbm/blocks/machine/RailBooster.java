package com.hbm.blocks.machine;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RailBooster extends RailHighspeed {

	public RailBooster(final String s) {
		super(s);
	}
	
	@Override
	public void onMinecartPass(final World world, final EntityMinecart cart, final BlockPos pos) {
		cart.motionX *= 1.15F;
		cart.motionY *= 1.15F;
		cart.motionZ *= 1.15F;
	}

}
