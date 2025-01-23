package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.List;

import com.hbm.tileentity.machine.TileEntityControlPanel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerControlEdit extends Container {

	public TileEntityControlPanel control;
	public Slot input;
	public List<SlotDisableable> invSlots = new ArrayList<>();
	
	public ContainerControlEdit(final InventoryPlayer invPlayer, final TileEntityControlPanel te) {
		control = te;
		input = this.addSlotToContainer(new SlotItemHandlerDisableable(te.inventory, 0, 8, 13));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				invSlots.add((SlotDisableable)this.addSlotToContainer(new SlotDisableable(invPlayer, j + i * 9 + 9, 73 + j * 18, 162 + i * 18)));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			invSlots.add((SlotDisableable)this.addSlotToContainer(new SlotDisableable(invPlayer, i, 73 + i * 18, 220)));
		}
	}
	
	@Override
	public boolean canInteractWith(final EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(final EntityPlayer playerIn, final int index){
		ItemStack var3 = ItemStack.EMPTY;
		final Slot var4 = this.inventorySlots.get(index);
		
		if (var4 != null && var4.getHasStack())
		{
			final ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
            if (index == 0) {
				if (!this.mergeItemStack(var5, 1, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 1, false))
			{
				return ItemStack.EMPTY;
			}
			
			if (var5.isEmpty())
			{
				var4.putStack(ItemStack.EMPTY);
			}
			else
			{
				var4.onSlotChanged();
			}
		}
		
		return var3;
	}
	
	@Override
	public void onContainerClosed(final EntityPlayer playerIn){
		for(int i = 0; i < control.inventory.getSlots(); i ++){
			final ItemStack stack = control.inventory.getStackInSlot(i);
			if(!playerIn.addItemStackToInventory(stack))
				playerIn.dropItem(stack, false);
			control.inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
		super.onContainerClosed(playerIn);
	}
	
	public static class SlotItemHandlerDisableable extends SlotItemHandler {
		public boolean isEnabled = false;
		public SlotItemHandlerDisableable(final IItemHandler itemHandler, final int index, final int xPosition, final int yPosition){
			super(itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public boolean isEnabled(){
			return isEnabled;
		}
		
		@Override
		public boolean canTakeStack(final EntityPlayer playerIn){
			return super.canTakeStack(playerIn);
		}
		
	}
	
	public static class SlotDisableable extends Slot {
		public boolean isEnabled = false;
		public SlotDisableable(final IInventory inventoryIn, final int index, final int xPosition, final int yPosition){
			super(inventoryIn, index, xPosition, yPosition);
		}
		@Override
		@SideOnly(Side.CLIENT)
		public boolean isEnabled(){
			return isEnabled;
		}
		@Override
		public boolean canTakeStack(final EntityPlayer playerIn){
			return super.canTakeStack(playerIn);
		}
	}

}
