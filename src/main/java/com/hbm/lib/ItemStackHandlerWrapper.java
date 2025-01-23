package com.hbm.lib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerWrapper implements IItemHandlerModifiable {

	private final ItemStackHandler handle;
	private final int[] validSlots;
	
	public ItemStackHandlerWrapper(final ItemStackHandler handle) {
		this.handle = handle;
		validSlots = new int[]{};
	}
	
	public ItemStackHandlerWrapper(final ItemStackHandler handle, final int[] validSlots) {
		this.handle = handle;
		this.validSlots = validSlots;
	}
	
	@Override
	public int getSlots() {
		return handle.getSlots();
	}

	@Override
	public ItemStack getStackInSlot(final int slot) {
		return handle.getStackInSlot(slot);
	}

	@Override
	public ItemStack insertItem(final int slot, final ItemStack stack, final boolean simulate) {
		for(final int i : validSlots)
			if(i == slot)
				return handle.insertItem(slot, stack, simulate);
		return stack;
	}

	@Override
	public ItemStack extractItem(final int slot, final int amount, final boolean simulate) {
		for(final int i : validSlots)
			if(i == slot)
				return handle.extractItem(slot, amount, simulate);
		return ItemStack.EMPTY;
	}

	@Override
	public int getSlotLimit(final int slot) {
		return handle.getSlotLimit(slot);
	}

	@Override
	public void setStackInSlot(final int slot, final ItemStack stack) {
		handle.setStackInSlot(slot, stack);
	}

}
