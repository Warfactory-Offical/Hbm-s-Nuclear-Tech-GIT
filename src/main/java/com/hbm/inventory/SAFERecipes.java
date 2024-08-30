package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SAFERecipes {

	private static LinkedHashMap<ComparableStack, ItemStack> recipes = new LinkedHashMap<>();
	
	public static void registerRecipes() {
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.tiny_singularity), ItemStackUtil.itemStackFrom(ModItems.singularity));
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.tiny_singularity_counter_resonant), ItemStackUtil.itemStackFrom(ModItems.singularity_counter_resonant));
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.tiny_singularity_super_heated), ItemStackUtil.itemStackFrom(ModItems.singularity_super_heated));
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.tiny_black_hole), ItemStackUtil.itemStackFrom(ModItems.black_hole));
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.tiny_singularity_spark), ItemStackUtil.itemStackFrom(ModItems.singularity_spark));
	}

	public static void addRecipe(ComparableStack input, ItemStack output){
		recipes.put(input, output);
	}

	public static void removeRecipe(ComparableStack input){
		recipes.remove(input);
	}
	
	public static LinkedHashMap<ItemStack, ItemStack> getAllRecipes() {
		
		LinkedHashMap<ItemStack, ItemStack> map = new LinkedHashMap<>();
		for(Map.Entry<ComparableStack, ItemStack> recipe : recipes.entrySet()) {
			map.put(recipe.getKey().toStack(), recipe.getValue());
		}
		
		return map;
	}
	
	public static ItemStack getOutput(ItemStack stack) {
		if(stack == null)
			return null;
		return recipes.get(ItemStackUtil.comparableStackFrom(stack));
	}
}
