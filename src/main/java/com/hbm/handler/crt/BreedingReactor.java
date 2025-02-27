package com.hbm.handler.crt;
import com.hbm.util.ItemStackUtil;

import crafttweaker.IAction;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.hbm.inventory.BreederRecipes;
import com.hbm.inventory.RecipesCommon.ComparableStack;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@ZenRegister
@ZenClass("mods.ntm.BreedingReactor")
public class BreedingReactor {
	
	private static class ActionAddRecipe implements IAction{
		private final ItemStack input;
		private final ItemStack output;
		private int heatLvl = 0;
		public ActionAddRecipe(final IItemStack input, final IItemStack output, final int heatLvl){
			this.input = CraftTweakerMC.getItemStack(input);
			this.output = CraftTweakerMC.getItemStack(output);
			this.heatLvl = heatLvl;
		}
		@Override
		public void apply(){
			if(this.input == null || this.input.isEmpty()){
				CraftTweakerAPI.logError("ERROR Breeding input item can not be an empty/air stack!");
				return;
			}
			if(this.output == null || this.output.isEmpty()){
				CraftTweakerAPI.logError("ERROR Breeding output item can not be an empty/air stack!");
				return;
			}
			if(this.heatLvl < 1 || this.heatLvl > 4){
				CraftTweakerAPI.logError("ERROR Breeding heat needs to be between 1-4 not "+this.heatLvl+"!");
				return;
			}
			BreederRecipes.addRecipe(ItemStackUtil.comparableStackFrom(this.input), this.output, this.heatLvl);
		}
		@Override
		public String describe(){
			return "Adding NTM breeder recipe ("+this.input+" + "+this.heatLvl+" HEAT -> "+this.output+")";
		}
	}

	@ZenMethod
	public static void addRecipe(final IItemStack input, final IItemStack output, final int heatLvl){
		NTMCraftTweaker.postInitActions.add(new ActionAddRecipe(input, output, heatLvl));
	}



	public static class ActionRemoveRecipe implements IAction{
		private final ItemStack input;

		public ActionRemoveRecipe(final IItemStack input){
			this.input = CraftTweakerMC.getItemStack(input);
		}
		@Override
		public void apply(){
			if(this.input == null || this.input.isEmpty()){
				CraftTweakerAPI.logError("ERROR Breeding input item can not be an empty/air stack!");
				return;
			}
			BreederRecipes.removeRecipe(ItemStackUtil.comparableStackFrom(this.input));
		}
		@Override
		public String describe(){
			return "Removing NTM breeder recipe for input "+this.input;
		}
	}

	@ZenMethod
	public static void removeRecipe(final IItemStack input){
		NTMCraftTweaker.postInitActions.add(new ActionRemoveRecipe(input));
	}

	

	public static class ActionAddFuel implements IAction{
		private final ItemStack input;
		private int heatLvl = 0;
		private int usesInNuclearFurnace = 0;

		public ActionAddFuel(final IItemStack input, final int heatLvl, final int usesInNuclearFurnace){
			this.input = CraftTweakerMC.getItemStack(input);
			this.heatLvl = heatLvl;
			this.usesInNuclearFurnace = usesInNuclearFurnace;
		}
		@Override
		public void apply(){
			if(this.input == null || this.input.isEmpty()){
				CraftTweakerAPI.logError("ERROR Breeding input item can not be an empty/air stack!");
				return;
			}
			if(this.heatLvl < 1 || this.heatLvl > 4){
				CraftTweakerAPI.logError("ERROR Breeding heat needs to be between 1-4 not "+this.heatLvl+"!");
				return;
			}
			if(this.usesInNuclearFurnace < 1){
				CraftTweakerAPI.logError("ERROR Breeding uses in Nuclear Furnace can not be < 1 so "+this.usesInNuclearFurnace+" is invalid!");
				return;
			}
			BreederRecipes.addFuel(ItemStackUtil.comparableStackFrom(this.input), this.heatLvl, this.usesInNuclearFurnace);
		}
		@Override
		public String describe(){
			return "Adding NTM breeder fuel ("+this.input+" -> "+this.heatLvl+" HEAT + "+this.usesInNuclearFurnace+" NukeOvenUses)";
		}
	}

	@ZenMethod
	public static void addFuel(final IItemStack input, final int heatLvl, final int usesInNuclearFurnace){
		NTMCraftTweaker.postInitActions.add(new ActionAddFuel(input, heatLvl, usesInNuclearFurnace));
	}

	

	public static class ActionRemoveFuel implements IAction{
		private final ItemStack input;

		public ActionRemoveFuel(final IItemStack input){
			this.input = CraftTweakerMC.getItemStack(input);
		}
		@Override
		public void apply(){
			if(this.input == null || this.input.isEmpty()){
				CraftTweakerAPI.logError("ERROR Breeding fuel item can not be an empty/air stack!");
				return;
			}
			BreederRecipes.removeFuel(ItemStackUtil.comparableStackFrom(input));
		}
		@Override
		public String describe(){
			return "Removing NTM breeder fuel for input "+input;
		}
	}

	@ZenMethod
	public static void removeFuel(final IItemStack input){
		NTMCraftTweaker.postInitActions.add(new ActionRemoveFuel(input));
	}
	//TEMPLATE
	// public static class ActionAddFuel implements IAction{
	// 	@Override
	// 	public void apply(){
		// if(){
		// 		CraftTweakerAPI.logError();
		// 		return;
		// 	}
	// 	}
	// 	@Override
	// 	public String describe(){
		// return "";
	// }
}
