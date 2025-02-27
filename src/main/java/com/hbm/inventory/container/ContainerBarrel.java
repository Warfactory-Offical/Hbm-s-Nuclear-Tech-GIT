package com.hbm.inventory.container;

import com.hbm.inventory.SlotMachineOutput;
import com.hbm.tileentity.machine.TileEntityBarrel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBarrel extends Container {

	private final TileEntityBarrel diFurnace;
	private int mode;

	public ContainerBarrel(final InventoryPlayer invPlayer, final TileEntityBarrel tedf) {
		mode = 0;

		diFurnace = tedf;

		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 0, 53 - 18, 17));
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 1, 53 - 18, 53));
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 2, 125, 17));
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 3, 125, 53));

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void addListener(final IContainerListener listener) {
		super.addListener(listener);
		listener.sendWindowProperty(this, 0, diFurnace.mode);
	}

	@Override
	public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int par2) {
		ItemStack var3 = ItemStack.EMPTY;
		final Slot var4 = this.inventorySlots.get(par2);

		if(var4 != null && var4.getHasStack()) {
			final ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if(par2 <= 5) {
				if(!this.mergeItemStack(var5, 7, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(!this.mergeItemStack(var5, 0, 6, false)) {
				return ItemStack.EMPTY;
			}

			if(var5.isEmpty()) {
				var4.putStack(ItemStack.EMPTY);
			} else {
				var4.onSlotChanged();
			}
		}

		return var3;
	}

	@Override
	public void detectAndSendChanges() {
		for(final IContainerListener listener : this.listeners) {
			if(this.mode != diFurnace.mode) {
				listener.sendWindowProperty(this, 0, diFurnace.mode);
				this.mode = diFurnace.mode;
			}
		}
		super.detectAndSendChanges();
	}
	
	@Override
	public void updateProgressBar(final int id, final int data) {
		if(id == 0)
			diFurnace.mode = (short) data;
		super.updateProgressBar(id, data);
	}

	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return diFurnace.isUseableByPlayer(player);
	}
}
