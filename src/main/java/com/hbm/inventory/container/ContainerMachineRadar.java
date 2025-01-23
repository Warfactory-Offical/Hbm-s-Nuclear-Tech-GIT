package com.hbm.inventory.container;

import com.hbm.tileentity.machine.TileEntityMachineRadar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerMachineRadar extends Container {
	
	public ContainerMachineRadar(final InventoryPlayer invPlayer, final TileEntityMachineRadar tedf) {
	}

	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return true;
	}
}
