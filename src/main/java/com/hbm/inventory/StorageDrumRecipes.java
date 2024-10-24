package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.HashSet;

import com.hbm.config.VersatileConfig;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import com.hbm.items.special.ItemWasteLong;
import com.hbm.items.special.ItemWasteShort;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StorageDrumRecipes {

	public static LinkedHashMap<ComparableStack, ItemStack> recipeOutputs = new LinkedHashMap<>();
	public static HashSet<ItemStack> outputs = new HashSet<>();
	public static HashMap<ComparableStack, int[]> recipeWastes = new HashMap<>();
	
	public static void registerRecipes() {

		//input, output

		for(int i = 0; i < ItemWasteLong.WasteClass.values().length; i++){
			ItemWasteLong.WasteClass waste = ItemWasteLong.WasteClass.values()[i];
			addRecipe(ItemStackUtil.comparableStackFrom(ModItems.nuclear_waste_long, 1, i), 
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_long_depleted, 1, i), 
				VersatileConfig.getLongDecayChance(), 
				waste.liquid, 
				waste.gas
			);
			addRecipe(ItemStackUtil.comparableStackFrom(ModItems.nuclear_waste_long_tiny, 1, i), 
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_long_depleted_tiny, 1, i), 
				(int)(VersatileConfig.getLongDecayChance()*0.1), 
				(int)(waste.liquid*0.1), 
				(int)(waste.gas*0.1)
			);
		}

		for(int i = 0; i < ItemWasteShort.WasteClass.values().length; i++){
			ItemWasteShort.WasteClass waste = ItemWasteShort.WasteClass.values()[i];
			addRecipe(ItemStackUtil.comparableStackFrom(ModItems.nuclear_waste_short, 1, i), 
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_short_depleted, 1, i), 
				VersatileConfig.getShortDecayChance(), 
				waste.liquid, 
				waste.gas
			);
			addRecipe(ItemStackUtil.comparableStackFrom(ModItems.nuclear_waste_short_tiny, 1, i), 
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_short_depleted_tiny, 1, i), 
				(int)(VersatileConfig.getShortDecayChance()*0.1), 
				(int)(waste.liquid*0.1), 
				(int)(waste.gas*0.1)
			);
		}
		
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.ingot_au198, 1), ItemStackUtil.itemStackFrom(ModItems.bottle_mercury, 1), (int)(VersatileConfig.getShortDecayChance()*0.01), 500, 500);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.nugget_au198, 1), ItemStackUtil.itemStackFrom(ModItems.nugget_mercury, 1), (int)(VersatileConfig.getShortDecayChance()*0.001), 50, 50);
	}

	public static void addRecipe(ComparableStack input, ItemStack output, int chance, int wasteLiquid, int wasteGas){
		recipeOutputs.put(input, output);
		recipeWastes.put(input, new int[]{chance, wasteLiquid, wasteGas});
		outputs.add(output);
	}
	
	public static ItemStack getOutput(ItemStack stack) {
		if(stack == null)
			return null;
		return recipeOutputs.get(ItemStackUtil.comparableStackFrom(stack));
	}

	public static int[] getWaste(ItemStack stack) {
		if(stack == null)
			return null;
		return recipeWastes.get(ItemStackUtil.comparableStackFrom(stack));
	}

	public static boolean isOutputItem(ItemStack stack){
		return outputs.contains(ItemStackUtil.comparableStackFrom(stack));
	}
}
