package com.hbm.tileentity;

import java.util.ArrayList;
import java.util.List;

import api.hbm.energy.IEnergyConductor;
import api.hbm.energy.IPowerNet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;

public class TileEntityProxyConductor extends TileEntityProxyBase implements IEnergyConductor {

	@Override
	public long transferPower(final long power) {
		
		final TileEntity te = this.getTE();
		
		if(te instanceof IEnergyConductor) {
			return ((IEnergyConductor)te).transferPower(power);
		}
		
		return 0;
	}

	@Override
	public long getPower() {
		
		final TileEntity te = this.getTE();
		
		if(te instanceof IEnergyConductor) {
			return ((IEnergyConductor)te).getPower();
		}
		
		return 0;
	}

	@Override
	public long getMaxPower() {
		
		final TileEntity te = this.getTE();
		
		if(te instanceof IEnergyConductor) {
			return ((IEnergyConductor)te).getMaxPower();
		}
		
		return 0;
	}

	@Override
	public IPowerNet getPowerNet() {
		
		final TileEntity te = this.getTE();
		
		if(te instanceof IEnergyConductor) {
			return ((IEnergyConductor)te).getPowerNet();
		}
		
		return null;
	}

	@Override
	public void setPowerNet(final IPowerNet network) {
		
		final TileEntity te = this.getTE();
		
		if(te instanceof IEnergyConductor) {
			((IEnergyConductor)te).setPowerNet(network);
		}
	}
	
	@Override
	public List<BlockPos> getConnectionPoints() {
		
		/*TileEntity te = this.getTE();
		
		if(te instanceof IEnergyConductor) {
			return ((IEnergyConductor)te).getConnectionPoints();
		}*/
		
		/* Proxy TE doesn't need to implement proxying here because the conductor main TE already has a network-specific proxying system */
		return new ArrayList();
	}
}
