package com.hbm.inventory.container;

import com.hbm.inventory.MagicRecipes;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ContainerBook extends Container {

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
    public InventoryCraftResult craftResult = new InventoryCraftResult();
    public World world;
    public EntityPlayer player;
    
    public ContainerBook(final InventoryPlayer inventory) {
    	this.world = inventory.player.world;
    	this.player = inventory.player;
    	this.addSlotToContainer(new SlotCrafting(inventory.player, this.craftMatrix, this.craftResult, 0, 124, 35){
    		@Override
    		public ItemStack onTake(final EntityPlayer thePlayer, final ItemStack stack){
    			 final NonNullList<ItemStack> nonnulllist = CraftingManager.getRemainingItems(craftMatrix, thePlayer.world);
    			 for(final ItemStack sta : nonnulllist){
    				 sta.shrink(1);
    			 }
    			 onCraftMatrixChanged(craftMatrix);
    			return stack;
    		}
    	});

        for (int l = 0; l < 2; ++l) {
            for (int i1 = 0; i1 < 2; ++i1) {
                this.addSlotToContainer(new Slot(this.craftMatrix, i1 + l * 2, 30 + i1 * 36, 17 + l * 36));
            }
        }

        for(int l = 0; l < 3; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlotToContainer(new Slot(inventory, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot(inventory, l, 8 + l * 18, 142));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
	}
    
    @Override
    public void onCraftMatrixChanged(final IInventory inventoryIn) {
    	this.craftResult.setInventorySlotContents(0, MagicRecipes.getRecipe(this.craftMatrix));
    }
    
    @Override
    public void onContainerClosed(final EntityPlayer player) {
    	super.onContainerClosed(player);

        if (!player.world.isRemote) {
        	this.clearContainer(player, player.world, this.craftMatrix);
        }
    }
    
    @Override
    public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int p_82846_2_) {
    	ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            final ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10 - 5, 46 - 5, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ >= 10 - 5 && p_82846_2_ < 37 - 5)
            {
                if (!this.mergeItemStack(itemstack1, 37 - 5, 46 - 5, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (p_82846_2_ >= 37 - 5 && p_82846_2_ < 46 - 5)
            {
                if (!this.mergeItemStack(itemstack1, 10 - 5, 37 - 5, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10 - 5, 46 - 5, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_82846_1_, itemstack1);
        }

        return itemstack;
    }
    
	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return Library.hasInventoryItem(player.inventory, ModItems.book_of_);
	}
	
	@Override
	public boolean canMergeSlot(final ItemStack stack, final Slot slot) {
		return slot.inventory != this.craftResult && super.canMergeSlot(stack, slot);
	}

}
