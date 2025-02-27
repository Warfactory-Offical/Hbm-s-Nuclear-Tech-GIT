package com.hbm.inventory.container;

import com.hbm.tileentity.machine.rbmk.TileEntityRBMKBoiler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRBMKBoiler extends Container {

	private final TileEntityRBMKBoiler rbmk;

	public ContainerRBMKBoiler(final InventoryPlayer invPlayer, final TileEntityRBMKBoiler tedf) {
		rbmk = tedf;

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + 20));
			}
		}

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142 + 20));
		}
	}

	@Override
	public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int par2) {
		final ItemStack var3 = ItemStack.EMPTY;
		final Slot var4 = this.inventorySlots.get(par2);

		if(var4 != null && var4.getHasStack()) {
			return ItemStack.EMPTY;
		}

		return var3;
	}

	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return true;
	}
}