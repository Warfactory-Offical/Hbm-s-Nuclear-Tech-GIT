package com.hbm.inventory.container;

import com.hbm.tileentity.bomb.TileEntityNukeBoy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerNukeBoy extends Container {

private final TileEntityNukeBoy nukeBoy;
	
	public ContainerNukeBoy(final InventoryPlayer invPlayer, final TileEntityNukeBoy tedf) {
		
		nukeBoy = tedf;

		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 0, 26, 36));
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 1, 44, 36));
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 2, 62, 36));
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 3, 80, 36));
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 4, 98, 36));
		
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
    public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int par2)
    {
		ItemStack var3 = ItemStack.EMPTY;
		final Slot var4 = this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			final ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
            if (par2 <= 4) {
				if (!this.mergeItemStack(var5, 5, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			} else {
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
		return nukeBoy.isUseableByPlayer(player);
	}

}