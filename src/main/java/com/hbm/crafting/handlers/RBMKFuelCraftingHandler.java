package com.hbm.crafting.handlers;
import com.hbm.util.ItemStackUtil;

import com.hbm.items.machine.ItemRBMKRod;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RBMKFuelCraftingHandler extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	/**
	 * The only rules for matching is that the item is fuel (meta and NBT don't matter) and that it's the only stack in the grid
	 */
	@Override
	public boolean matches(final InventoryCrafting inventory, final World world) {
		if(!hasExactlyOneStack(inventory))
			return false;
		
		final ItemStack stack = getFirstStack(inventory);
		
		return stack.getItem() instanceof ItemRBMKRod && ((ItemRBMKRod)stack.getItem()).pellet != null &&
				ItemRBMKRod.getHullHeat(stack) < 50 && ItemRBMKRod.getCoreHeat(stack) < 50;
	}

	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inventory) {
		if(!hasExactlyOneStack(inventory))
			return ItemStack.EMPTY;
		
		final ItemStack stack = getFirstStack(inventory);
		
		if(stack.getItem() instanceof ItemRBMKRod rod) {

            if(rod.pellet == null)
				return ItemStack.EMPTY;
			
			if(ItemRBMKRod.getEnrichment(stack) > 0.99D)
				return ItemStack.EMPTY;
			
			if(ItemRBMKRod.getHullHeat(stack) < 50 && ItemRBMKRod.getCoreHeat(stack) < 50) {
				final ItemStack result = ItemStackUtil.itemStackFrom(rod.pellet, 8);
				final int enrichment = 4 - MathHelper.clamp((int)Math.ceil(ItemRBMKRod.getEnrichment(stack) * 5 - 1), 0, 4);
				final int meta = enrichment + (ItemRBMKRod.getPoisonLevel(stack) >= 0.5D ? 5 : 0);
				result.setItemDamage(meta);
				return result;
			}
		}

		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}
	
	private boolean hasExactlyOneStack(final InventoryCrafting inventory) {
		
		boolean hasOne = false;

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				
				final ItemStack stack = inventory.getStackInRowAndColumn(j, i);
				
				if(!stack.isEmpty()) {
					
					if(!hasOne)
						hasOne = true;
					else
						return false;
				}
			}
		}
		
		return hasOne;
	}
	
	private ItemStack getFirstStack(final InventoryCrafting inventory) {

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				
				final ItemStack stack = inventory.getStackInRowAndColumn(j, i);
				
				if(stack != null && !stack.isEmpty()) {
					return stack;
				}
			}
		}
		
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isDynamic(){
		return true;
	}
	
	@Override
	public boolean canFit(final int width, final int height){
		return width >= 1 && height >= 1;
	}

}