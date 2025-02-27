package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.hbm.items.ModItems;
import com.hbm.blocks.ModBlocks;
import static com.hbm.inventory.OreDictManager.*;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class NuclearTransmutationRecipes {

	public static LinkedHashMap<AStack, ItemStack> recipesOutput = new LinkedHashMap<>();
	public static HashMap<AStack, Long> recipesEnergy = new HashMap<>();
	
	public static void registerRecipes() {

		//input, output
		addRecipe(new OreDictStack(U.crystal()), ItemStackUtil.itemStackFrom(ModItems.crystal_schraranium, 1), 5_000_000L);
		addRecipe(new OreDictStack(U.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRARANIUM), 1), 5_000_000L);
		addRecipe(new OreDictStack(U.block()), ItemStackUtil.itemStackFrom(ModBlocks.block_schraranium, 1), 50_000_000L);
	}

	public static void addRecipe(final AStack input, final ItemStack output, final long energy){
		recipesOutput.put(input, output);
		recipesEnergy.put(input, energy);
	}
	
	public static ItemStack getOutput(final ItemStack stack) {
		if(stack == null || stack.isEmpty())
			return null;
		
		ItemStack outputItem = recipesOutput.get(ItemStackUtil.comparableStackFrom(stack));
		if(outputItem != null)
			return outputItem;
		outputItem = recipesOutput.get(new NbtComparableStack(stack));
		if(outputItem != null)
			return outputItem;
		final int[] ids = OreDictionary.getOreIDs(ItemStackUtil.itemStackFrom(stack.getItem(), 1, stack.getItemDamage()));
		for(int id = 0; id < ids.length; id++) {
			final OreDictStack oreStack = new OreDictStack(OreDictionary.getOreName(ids[id]));
			outputItem = recipesOutput.get(oreStack);
			if(outputItem != null)
				return outputItem;
		}
		return null;
	}

	public static long getEnergy(final ItemStack stack) {
		if(stack == null || stack.isEmpty())
			return -1;

		Long outputItem = recipesEnergy.get(ItemStackUtil.comparableStackFrom(stack));
		if(outputItem != null)
			return outputItem;
		outputItem = recipesEnergy.get(new NbtComparableStack(stack));
		if(outputItem != null)
			return outputItem;
		final int[] ids = OreDictionary.getOreIDs(ItemStackUtil.itemStackFrom(stack.getItem(), 1, stack.getItemDamage()));
		for(int id = 0; id < ids.length; id++) {
			final OreDictStack oreStack = new OreDictStack(OreDictionary.getOreName(ids[id]));
			outputItem = recipesEnergy.get(oreStack);
			if(outputItem != null)
				return outputItem;
		}
		return -1;
	}
}
