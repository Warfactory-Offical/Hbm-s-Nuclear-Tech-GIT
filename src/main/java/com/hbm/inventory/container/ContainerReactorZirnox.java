package com.hbm.inventory.container;

import com.hbm.inventory.FluidContainerRegistry;
import com.hbm.inventory.SlotMachineOutput;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.machine.ItemZirnoxRod;
import com.hbm.tileentity.machine.TileEntityReactorZirnox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerReactorZirnox extends Container {

    private TileEntityReactorZirnox zirnox;

    public ContainerReactorZirnox(InventoryPlayer invPlayer, TileEntityReactorZirnox te) {
        zirnox = te;

        // Rods
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 0, 26, 16));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 1, 62, 16));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 2, 98, 16));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 3, 8, 34));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 4, 44, 34));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 5, 80, 34));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 6, 116, 34));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 7, 26, 52));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 8, 62, 52));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 9, 98, 52));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 10, 8, 70));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 11, 44, 70));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 12, 80, 70));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 13, 116, 70));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 14, 26, 88));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 15, 62, 88));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 16, 98, 88));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 17, 8, 106));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 18, 44, 106));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 19, 80, 106));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 20, 116, 106));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 21, 26, 124));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 22, 62, 124));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 23, 98, 124));

        // Fluid IO
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 24, 143, 124));
        this.addSlotToContainer(new SlotMachineOutput(te.inventory, 26, 143, 142));
        this.addSlotToContainer(new SlotItemHandler(te.inventory, 25, 179, 124));
        this.addSlotToContainer(new SlotMachineOutput(te.inventory, 27, 179, 142));

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + 90));
            }
        }

        for(int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 232));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {

        ItemStack var3 = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            var3 = stack.copy();

            if(index <= 27) {
                if(!this.mergeItemStack(stack, 28, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {

                if(FluidContainerRegistry.getFluidContent(stack, Fluids.CARBONDIOXIDE) > 0) {
                    if(!this.mergeItemStack(stack, 24, 26, false))
                        return ItemStack.EMPTY;

                } else if(FluidContainerRegistry.getFluidContent(stack, Fluids.WATER) > 0) {
                    if(!this.mergeItemStack(stack, 26, 28, false))
                        return ItemStack.EMPTY;

                } else {

                    if(stack.getItem() instanceof ItemZirnoxRod) {

                        if(!this.mergeItemStack(stack, 0, 24, true))
                            return ItemStack.EMPTY;
                    } else {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if(stack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return var3;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return zirnox.isUseableByPlayer(player);
    }
}
