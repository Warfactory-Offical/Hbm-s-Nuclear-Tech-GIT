package com.hbm.portals;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class DummyRenderEntity extends Entity {

	public DummyRenderEntity(final World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(final NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(final NBTTagCompound compound) {
	}

}
