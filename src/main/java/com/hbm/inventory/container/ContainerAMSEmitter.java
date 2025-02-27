package com.hbm.inventory.container;

import com.hbm.tileentity.machine.TileEntityAMSEmitter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAMSEmitter extends Container {

	private final TileEntityAMSEmitter amsEmitter;

	private int heat;
	private int warning;
	
	public ContainerAMSEmitter(final InventoryPlayer invPlayer, final TileEntityAMSEmitter tedf) {
		amsEmitter = tedf;

		//Fluid In
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 0, 44, 17));
		//Fluid Out
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 1, 44, 53));
		//Focus
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 2, 80, 53));
		//Battery
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 3, 116, 53));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
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
			
            if (par2 <= 3) {
				if (!this.mergeItemStack(var5, 4, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else
				return ItemStack.EMPTY;
			
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
		return amsEmitter.isUseableByPlayer(player);
	}
	
	@Override
	public void addListener(final IContainerListener listener) {
		super.addListener(listener);
		listener.sendWindowProperty(this, 0, this.amsEmitter.heat);
		listener.sendWindowProperty(this, 2, this.amsEmitter.warning);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++)
		{
			final IContainerListener par1 = this.listeners.get(i);
			
			if(this.heat != this.amsEmitter.heat)
			{
				par1.sendWindowProperty(this, 0, this.amsEmitter.heat);
			}
			
			if(this.warning != this.amsEmitter.warning)
			{
				par1.sendWindowProperty(this, 2, this.amsEmitter.warning);
			}
		}

		this.heat = this.amsEmitter.heat;
		this.warning = this.amsEmitter.warning;
	}
	
	@Override
	public void updateProgressBar(final int i, final int j) {
		if(i == 0)
		{
			amsEmitter.heat = j;
		}
		if(i == 2)
		{
			amsEmitter.warning = j;
		}
	}
}
