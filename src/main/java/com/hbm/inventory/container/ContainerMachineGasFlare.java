package com.hbm.inventory.container;

import com.hbm.inventory.SlotMachineOutput;
import com.hbm.tileentity.machine.oil.TileEntityMachineGasFlare;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerMachineGasFlare extends Container {

	private final TileEntityMachineGasFlare testNuke;
	
	public ContainerMachineGasFlare(final InventoryPlayer invPlayer, final TileEntityMachineGasFlare tedf) {
		
		testNuke = tedf;

		//Battery
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 0, 143, 71));
		//Fluid in
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 1, 17, 17));
		//Fluid out
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 2, 17, 53) {
			@Override
			public boolean isItemValid(@Nonnull final ItemStack stack) {
				return false;
			}
		});
		//Fluid ID
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 3, 35, 71));
		//Upgrades
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 4, 80, 71));
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 5, 98, 71));

		final int offset = 37;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + offset));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142 + offset));
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
			
            if (par2 <= 1) {
				if (!this.mergeItemStack(var5, 3, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 3, false))
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
	public boolean canInteractWith(final EntityPlayer player) {
		return testNuke.isUseableByPlayer(player);
	}
}
