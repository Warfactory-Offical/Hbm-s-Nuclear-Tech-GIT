package com.hbm.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotMachineOutput extends SlotItemHandler {
	public SlotMachineOutput(final IItemHandler inventory, final int i, final int j, final int k) {
		super(inventory, i, j, k);
	}
	
	@Override
	public boolean isItemValid(final ItemStack p_75214_1_)
    {
        return false;
    }
}
