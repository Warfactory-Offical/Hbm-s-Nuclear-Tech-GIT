package com.hbm.inventory;

import java.util.Arrays;
import java.util.List;

import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.items.special.ItemHot;

import net.minecraft.item.ItemStack;

public class AnvilSmithingHotRecipe extends AnvilSmithingRecipe {

	public AnvilSmithingHotRecipe(final int tier, final ItemStack out, final AStack left, final AStack right) {
		super(tier, out, left, right);
	}
	
	public boolean doesStackMatch(final ItemStack input, final AStack recipe) {
		
		if(input != null && input.getItem() instanceof ItemHot) {
			final double heat = ItemHot.getHeat(input);
			
			if(heat < 0.5D)
				return false;
		}
		
		return recipe.matchesRecipe(input, false);
	}
	
	public ItemStack getOutput(final ItemStack left, final ItemStack right) {
		
		if(left.getItem() instanceof ItemHot && right.getItem() instanceof ItemHot && output.getItem() instanceof ItemHot) {
			
			final double h1 = ItemHot.getHeat(left);
			final double h2 = ItemHot.getHeat(right);
			
			final ItemStack out = output.copy();
			ItemHot.heatUp(out, (h1 + h2) / 2D);
			
			return out;
		}
		
		return output.copy();
	}
	
	public List<ItemStack> getLeft() {
		return Arrays.asList(getHot(left));
	}
	
	public List<ItemStack> getRight() {
		return Arrays.asList(getHot(right));
	}
	
	private ItemStack getHot(final AStack stack) {
		final ItemStack first = stack.getStackList().get(0);
		
		if(first.getItem() instanceof ItemHot) {
			ItemHot.heatUp(first);
		}
		
		return first;
	}
}