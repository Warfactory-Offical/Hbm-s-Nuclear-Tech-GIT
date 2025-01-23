package com.hbm.saveddata.satellites;

import net.minecraft.nbt.NBTTagCompound;

public class SatelliteMiner extends Satellite {
	
	public long lastOp;
	
	public SatelliteMiner() {
		this.satIface = Interfaces.NONE;
	}
	
	public void writeToNBT(final NBTTagCompound nbt) {
		nbt.setLong("lastOp", lastOp);
	}
	
	public void readFromNBT(final NBTTagCompound nbt) {
		lastOp = nbt.getLong("lastOp");
	}
}
