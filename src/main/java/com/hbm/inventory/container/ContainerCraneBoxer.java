package com.hbm.inventory.container;

import com.hbm.tileentity.network.TileEntityCraneBoxer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCraneBoxer extends Container {

    protected TileEntityCraneBoxer boxer;

    public ContainerCraneBoxer(final InventoryPlayer invPlayer, final TileEntityCraneBoxer boxer) {
        this.boxer = boxer;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 7; j++) {
                this.addSlotToContainer(new SlotItemHandler(boxer.inventory, j + i * 7, 8 + j * 18, 17 + i * 18));
            }
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 103 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 161));
        }
    }

    @Override
    public ItemStack transferStackInSlot(final EntityPlayer player, final int slot) {
        ItemStack var3 = ItemStack.EMPTY;
        final Slot var4 = this.inventorySlots.get(slot);

        if(var4 != null && var4.getHasStack()) {
            final ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if(slot <= 21) {
                if(!this.mergeItemStack(var5, 21, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if(!this.mergeItemStack(var5, 0, 21, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if(var5.getCount() == 0) {
                var4.putStack(ItemStack.EMPTY);
            } else {
                var4.onSlotChanged();
            }

        }

        return var3;
    }

    @Override
    public boolean canInteractWith(final EntityPlayer player) {
        return boxer.isUseableByPlayer(player);
    }
}
