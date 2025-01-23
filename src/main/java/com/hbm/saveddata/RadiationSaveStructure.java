package com.hbm.saveddata;

import net.minecraft.nbt.NBTTagCompound;

public class RadiationSaveStructure {
	public int chunkX;
	public int chunkY;
	public float radiation;
	
	public RadiationSaveStructure() { }
	
	public RadiationSaveStructure(final int x, final int y, final float rad) {
		chunkX = x;
		chunkY = y;
		radiation = rad;
	}

	public void readFromNBT(final NBTTagCompound nbt, final int index) {
		chunkX = nbt.getInteger("rad_" + index + "_x");
		chunkY = nbt.getInteger("rad_" + index + "_y");
		radiation = nbt.getFloat("rad_" + index + "_level");
	}

	public void writeToNBT(final NBTTagCompound nbt, final int index) {
		nbt.setInteger("rad_" + index + "_x", chunkX);
		nbt.setInteger("rad_" + index + "_y", chunkY);
		nbt.setFloat("rad_" + index + "_level", radiation);
	}
}
