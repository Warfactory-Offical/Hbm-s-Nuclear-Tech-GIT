package com.hbm.inventory.container;

import com.hbm.inventory.SlotMachineOutput;
import com.hbm.tileentity.machine.oil.TileEntityMachineOilWell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMachineOilWell extends Container {

	private final TileEntityMachineOilWell testNuke;
	private int warning;
	private int warning2;
	
	public ContainerMachineOilWell(final InventoryPlayer invPlayer, final TileEntityMachineOilWell tedf) {
		warning = 0;
		warning2 = 0;
		
		testNuke = tedf;
		
		//Battery
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 0, 44, 54));
		//Canister Input
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 1, 134, 18));
		//Canister Output
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 2, 134, 54));
		//Gas Input
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 3, 134, 72));
		//Gas Output
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 4, 134, 108));
		//Chip
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 5, 8, 90));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + 56));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142 + 56));
		}
	}
	
	@Override
	public void addListener(final IContainerListener crafting) {
		super.addListener(crafting);
		crafting.sendWindowProperty(this, 1, this.testNuke.warning);
		crafting.sendWindowProperty(this, 2, this.testNuke.warning2);
	}
	
	@Override
    public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int par2)
    {
		ItemStack var3 = ItemStack.EMPTY;
		final Slot var4 = this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			final ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
            if (par2 <= 5) {
				if (!this.mergeItemStack(var5, 6, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 2, false))
			{
				if (!this.mergeItemStack(var5, 3, 4, false))
					if (!this.mergeItemStack(var5, 5, 6, false))
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
	public boolean canInteractWith(final EntityPlayer player) {
		return testNuke.isUseableByPlayer(player);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++)
		{
			final IContainerListener par1 = this.listeners.get(i);
			if(this.warning != this.testNuke.warning)
			{
				par1.sendWindowProperty(this, 1, this.testNuke.warning);
			}
			if(this.warning2 != this.testNuke.warning2)
			{
				par1.sendWindowProperty(this, 2, this.testNuke.warning2);
			}
		}

		this.warning = this.testNuke.warning;
		this.warning2 = this.testNuke.warning2;
	}
	
	@Override
	public void updateProgressBar(final int i, final int j) {
		if(i == 1)
		{
			testNuke.warning = j;
		}
		if(i == 2)
		{
			testNuke.warning2 = j;
		}
	}
}
