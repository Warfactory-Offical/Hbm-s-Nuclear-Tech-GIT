package com.hbm.inventory;

import com.hbm.items.machine.ItemMachineUpgrade;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotUpgrade extends SlotItemHandler {

	public SlotUpgrade(final IItemHandler inventory, final int i, final int j, final int k) {
		super(inventory, i, j, k);
	}

	@Override
	public boolean isItemValid(final ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemMachineUpgrade;
    }

	@Override
    public void onSlotChange(final ItemStack sta1, final ItemStack sta2) {
		super.onSlotChange(sta1, sta2);
    }
}