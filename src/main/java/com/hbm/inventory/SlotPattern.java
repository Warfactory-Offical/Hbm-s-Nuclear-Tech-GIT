package com.hbm.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotPattern extends SlotItemHandler {

    protected boolean canHover = true;

    public SlotPattern(final IItemHandler inv, final int index, final int x, final int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean canTakeStack(final EntityPlayer player) {
        return true;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public int getItemStackLimit(final ItemStack stack) {
        return 1;
    }

    public SlotPattern disableHover() {
        this.canHover = false;
        return this;
    }

    @Override
    public boolean isItemValid(final ItemStack stack) {
        return stack != null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isEnabled() {
        return canHover;
    }
}
