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
import net.minecraft.util.text.TextFormatting;

public class BreederRecipes {

	private static LinkedHashMap<ComparableStack, BreederRecipe> recipes = new LinkedHashMap<>();
	private static LinkedHashMap<ComparableStack, int[]> fuels = new LinkedHashMap<>();
	//for the int array: [0] => level (1-4) [1] => amount of operations
	
	public static void registerRecipes() {

		//lithium and impure rods
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_lithium), ModItems.rod_tritium, 1);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_lithium), ModItems.rod_dual_tritium, 1);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_lithium), ModItems.rod_quad_tritium, 1);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_uranium), ModItems.rod_plutonium, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_uranium), ModItems.rod_dual_plutonium, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_uranium), ModItems.rod_quad_plutonium, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_plutonium), ModItems.rod_waste, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_plutonium), ModItems.rod_dual_waste, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_plutonium), ModItems.rod_quad_waste, 4);
		
		//isotopes
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_th232), ModItems.rod_thorium_fuel, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_th232), ModItems.rod_dual_thorium_fuel, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_th232), ModItems.rod_quad_thorium_fuel, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_u233), ModItems.rod_u235, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_u233), ModItems.rod_dual_u235, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_u233), ModItems.rod_quad_u235, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_u235), ModItems.rod_neptunium, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_u235), ModItems.rod_dual_neptunium, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_u235), ModItems.rod_quad_neptunium, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_u238), ModItems.rod_pu239, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_u238), ModItems.rod_dual_pu239, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_u238), ModItems.rod_quad_pu239, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_neptunium), ModItems.rod_pu238, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_neptunium), ModItems.rod_dual_pu238, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_neptunium), ModItems.rod_quad_pu238, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_pu238), ModItems.rod_pu239, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_pu238), ModItems.rod_dual_pu239, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_pu238), ModItems.rod_quad_pu239, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_pu239), ModItems.rod_pu240, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_pu239), ModItems.rod_dual_pu240, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_pu239), ModItems.rod_quad_pu240, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_pu240), ModItems.rod_waste, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_pu240), ModItems.rod_dual_waste, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_pu240), ModItems.rod_quad_waste, 3);

		//NEW
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_cobalt), ModItems.rod_co60, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_cobalt), ModItems.rod_dual_co60, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_cobalt), ModItems.rod_quad_co60, 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_ra226), ModItems.rod_ac227, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_ra226), ModItems.rod_dual_ac227, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_ra226), ModItems.rod_quad_ac227, 4);

		//advanced
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_schrabidium), ModItems.rod_solinium, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_schrabidium), ModItems.rod_dual_solinium, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_schrabidium), ModItems.rod_quad_solinium, 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_balefire), ModItems.rod_balefire_blazing, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_balefire), ModItems.rod_dual_balefire_blazing, 4);
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_balefire), ModItems.rod_quad_balefire_blazing, 4);

		//rocks
		addRecipe(ItemStackUtil.comparableStackFrom(Blocks.STONE), ItemStackUtil.itemStackFrom(ModBlocks.sellafield_slaked), 1);
		addRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.sellafield_slaked), ItemStackUtil.itemStackFrom(ModBlocks.sellafield_0), 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.sellafield_0), ItemStackUtil.itemStackFrom(ModBlocks.sellafield_1), 2);
		addRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.sellafield_1), ItemStackUtil.itemStackFrom(ModBlocks.sellafield_2), 3);
		addRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.sellafield_2), ItemStackUtil.itemStackFrom(ModBlocks.sellafield_3), 3);
		
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.meteorite_sword_etched), ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_bred), 4);
	}
	
	public static void registerFuels() {
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_u233), 2, 2);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_u233), 2, 4);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_u233), 2, 8);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_u235), 2, 3);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_u235), 2, 6);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_u235), 2, 12);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_u238), 1, 1);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_u238), 1, 2);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_u238), 1, 4);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_neptunium), 2, 3);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_neptunium), 2, 6);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_neptunium), 2, 12);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_pu238), 1, 2);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_pu238), 1, 4);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_pu238), 1, 8);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_pu239), 3, 5);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_pu239), 3, 10);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_pu239), 3, 20);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_pu240), 1, 2);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_pu240), 1, 4);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_pu240), 1, 8);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_schrabidium), 3, 10);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_schrabidium), 3, 20);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_schrabidium), 3, 40);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_solinium), 3, 15);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_solinium), 3, 30);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_solinium), 3, 60);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_polonium), 4, 2);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_polonium), 4, 4);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_polonium), 4, 8);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_tritium), 1, 1);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_tritium), 1, 2);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_tritium), 1, 4);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_balefire), 2, 150);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_balefire), 2, 300);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_balefire), 2, 600);
		
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_balefire_blazing), 4, 75);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_dual_balefire_blazing), 4, 150);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.rod_quad_balefire_blazing), 4, 300);
	}

	public static void addRecipe(ComparableStack input, Item output, int heatLvl){
		addRecipe(input, ItemStackUtil.itemStackFrom(output), heatLvl);
	}
	public static void addRecipe(ComparableStack input, ItemStack output, int heatLvl){
		recipes.put(input, new BreederRecipe(output, heatLvl));
	}

	public static void removeRecipe(ComparableStack input){
		recipes.remove(input);
	}

	public static void addFuel(ComparableStack input, int heatLvl, int usesInNuclearFurnace){
		fuels.put(input, new int[] {heatLvl, usesInNuclearFurnace});
	}

	public static void removeFuel(ComparableStack input){
		fuels.remove(input);
	}
	
	public static LinkedHashMap<ItemStack, BreederRecipe> getAllRecipes() {
		
		LinkedHashMap<ItemStack, BreederRecipe> map = new LinkedHashMap<>();
		
		for(Map.Entry<ComparableStack, BreederRecipe> recipe : recipes.entrySet()) {
			map.put(recipe.getKey().toStack(), recipe.getValue());
		}
		
		return map;
	}
	
	public static List<ItemStack> getAllFuelsFromHEAT(int heat) {
		
		List<ItemStack> list = new ArrayList<>();
		
		for(Map.Entry<ComparableStack, int[]> fuel : fuels.entrySet()) {
			
			if(fuel.getValue()[0] >= heat) {
				list.add(fuel.getKey().toStack());
			}
		}
		
		return list;
	}
	
	public static BreederRecipe getOutput(ItemStack stack) {
		
		if(stack == null)
			return null;
		
		ComparableStack sta = ItemStackUtil.comparableStackFrom(stack.getItem(), 1, stack.getItemDamage());
		return BreederRecipes.recipes.get(sta);
	}
	
	/**
	 * Returns an integer array of the fuel value of a certain stack
	 * @param stack
	 * @return an integer array (possibly null) with two fields, the HEAT value and the amount of operations
	 */
	public static int[] getFuelValue(ItemStack stack) {
		
		if(stack == null)
			return null;
		
		ComparableStack sta = ItemStackUtil.comparableStackFrom(stack.getItem(), 1, stack.getItemDamage());
		int[] ret = BreederRecipes.fuels.get(sta);
		
		return ret;
	}
	
	public static String getHEATString(String string, int heat) {

		if(heat == 1)
			string =  TextFormatting.GREEN + string;
		if(heat == 2)
			string = TextFormatting.YELLOW + string;
		if(heat == 3)
			string = TextFormatting.GOLD + string;
		if(heat == 4)
			string = TextFormatting.RED + string;
		
		return string; //strings are reference types I GET IT
	}
	
	//nicer than opaque object arrays
	public static class BreederRecipe {
		
		public ItemStack output;
		public int heat;
		
		public BreederRecipe() { }
		
		public BreederRecipe(Item output, int heat) {
			this(ItemStackUtil.itemStackFrom(output), heat);
		}
		
		public BreederRecipe(ItemStack output, int heat) {
			this.output = output;
			this.heat = heat;
		}
	}
}
