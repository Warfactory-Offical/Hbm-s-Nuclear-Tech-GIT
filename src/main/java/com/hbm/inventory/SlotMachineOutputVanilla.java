package com.hbm.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMachineOutputVanilla extends Slot {

	public SlotMachineOutputVanilla(final IInventory inventory, final int i, final int j, final int k) {
		super(inventory, i, j, k);
	}
	
	@Override
	public boolean isItemValid(final ItemStack p_75214_1_)
    {
        return false;
    }
}
