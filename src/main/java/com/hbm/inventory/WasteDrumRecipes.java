package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.HashSet;

import com.hbm.items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WasteDrumRecipes {

	public static LinkedHashMap<Item, ItemStack> recipes = new LinkedHashMap<>();
	private static final HashSet<Item> outputs = new HashSet<>();
	
	public static void registerRecipes() {

		//input, output
		addRecipe(ModItems.waste_uranium_hot, ItemStackUtil.itemStackFrom(ModItems.waste_uranium, 1));
		addRecipe(ModItems.waste_thorium_hot, ItemStackUtil.itemStackFrom(ModItems.waste_thorium, 1));
		addRecipe(ModItems.waste_plutonium_hot, ItemStackUtil.itemStackFrom(ModItems.waste_plutonium, 1));
		addRecipe(ModItems.waste_mox_hot, ItemStackUtil.itemStackFrom(ModItems.waste_mox, 1));
		addRecipe(ModItems.waste_schrabidium_hot, ItemStackUtil.itemStackFrom(ModItems.waste_schrabidium, 1));
	}

	public static void addRecipe(final ItemStack input, final ItemStack output){
		recipes.put(input.getItem(), output);
		outputs.add(output.getItem());
	}

	public static void addRecipe(final Item input, final ItemStack output){
		recipes.put(input, output);
		outputs.add(output.getItem());
	}
	
	public static ItemStack getOutput(final Item item) {
		
		if(item == null)
			return null;
		
		return recipes.get(item);
	}

	public static boolean hasRecipe(final Item item){
		return recipes.containsKey(item);
	}

	public static boolean isCold(final Item item){
		return outputs.contains(item);
	}
}
