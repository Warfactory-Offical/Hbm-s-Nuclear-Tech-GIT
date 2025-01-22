package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedHashMap;

import static com.hbm.inventory.OreDictManager.*;
import static net.minecraft.item.ItemStack.areItemStacksEqual;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.util.Tuple.Pair;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.interfaces.Spaghetti;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.items.tool.ItemFluidCanister;
import com.hbm.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

//TODO: clean this shit up
//Alcater: on it
@Spaghetti("everything")
public class DiFurnaceRecipes {

	public static LinkedHashMap<Pair<AStack, AStack>, ItemStack> diRecipes = new LinkedHashMap<Pair<AStack, AStack>, ItemStack>();
	public static LinkedHashMap<AStack, Integer> diFuels = new LinkedHashMap<AStack, Integer>();

	public static void registerRecipes(){
		addRecipe(new OreDictStack(W.ingot()), new OreDictStack(COAL.gem()), ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 2));
		addRecipe(new OreDictStack(W.dust()), new OreDictStack(COAL.gem()), ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 2));

		addRecipe(new OreDictStack(CU.ingot()), new OreDictStack(PB.ingot()), ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 1));
		addRecipe(new OreDictStack(CU.dust()), new OreDictStack(PB.ingot()), ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 1));
		addRecipe(new OreDictStack(CU.ingot()), new OreDictStack(PB.dust()), ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 1));
		addRecipe(new OreDictStack(CU.dust()), new OreDictStack(PB.dust()), ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 1));

		addRecipe(new OreDictStack(CU.plate()), new OreDictStack(PB.plate()), ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 1));

		addRecipe(new OreDictStack(IRON.ingot()), new OreDictStack(COAL.gem()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL), 2));
		addRecipe(new OreDictStack(IRON.dust()), new OreDictStack(COAL.gem()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL), 2));
		addRecipe(new OreDictStack(IRON.ingot()), new OreDictStack(COAL.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL), 2));
		addRecipe(new OreDictStack(IRON.dust()), new OreDictStack(COAL.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL), 2));

		addRecipe(new OreDictStack(U238.ingot()), new OreDictStack(STEEL.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.FERROURANIUM), 1));
		addRecipe(new OreDictStack(STEEL.dust()), new OreDictStack(U238.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.FERROURANIUM), 1));
		
		addRecipe(new OreDictStack(CU.ingot()), new OreDictStack(REDSTONE.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER), 2));
		addRecipe(new OreDictStack(CU.dust()), new OreDictStack(REDSTONE.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER), 2));

		addRecipe(new NbtComparableStack(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)), ItemStackUtil.comparableStackFrom(Items.SLIME_BALL), ItemStackUtil.itemStackFrom(ModItems.canister_napalm, 1));
		
		addRecipe(new OreDictStack(MINGRADE.ingot()), new OreDictStack(STEEL.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY), 2));
		addRecipe(new OreDictStack(MINGRADE.dust()), new OreDictStack(STEEL.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY), 2));
		addRecipe(new OreDictStack(MINGRADE.ingot()), new OreDictStack(STEEL.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY), 2));
		addRecipe(new OreDictStack(MINGRADE.dust()), new OreDictStack(STEEL.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY), 2));

		addRecipe(new OreDictStack(W.ingot()), new OreDictStack(SA326.nugget()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN), 1));
		addRecipe(new OreDictStack(W.dust()), new OreDictStack(SA326.nugget()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN), 1));

		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.plate_mixed), new OreDictStack(GOLD.plate()), ItemStackUtil.itemStackFrom(ModItems.plate_paa, 2));
		
		addRecipe(new OreDictStack(STEEL.ingot()), new OreDictStack(W.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 2));
		addRecipe(new OreDictStack(STEEL.dust()), new OreDictStack(W.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 2));
		addRecipe(new OreDictStack(STEEL.ingot()), new OreDictStack(W.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 2));
		addRecipe(new OreDictStack(STEEL.dust()), new OreDictStack(W.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 2));

		addRecipe(new OreDictStack(STEEL.ingot()), new OreDictStack(CO.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 2));
		addRecipe(new OreDictStack(STEEL.dust()), new OreDictStack(CO.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 2));
		addRecipe(new OreDictStack(STEEL.ingot()), new OreDictStack(CO.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 2));
		addRecipe(new OreDictStack(STEEL.dust()), new OreDictStack(CO.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 2));

		addRecipe(new OreDictStack(BIGMT.ingot()), ItemStackUtil.comparableStackFrom(ModItems.powder_meteorite), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STARMETAL), 2));

		addRecipe(new OreDictStack(STEEL.ingot()), new OreDictStack(TC99.nugget()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TCALLOY), 1));
		addRecipe(new OreDictStack(STEEL.dust()), new OreDictStack(TC99.nugget()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TCALLOY), 1));

		addRecipe(new OreDictStack(STEEL.ingot()), new OreDictStack(CD.nugget()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CDALLOY), 1));
		addRecipe(new OreDictStack(STEEL.dust()), new OreDictStack(CD.nugget()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CDALLOY), 1));

		addRecipe(ItemStackUtil.comparableStackFrom(Item.getItemFromBlock(ModBlocks.block_meteor)), new OreDictStack(CO.ingot()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE)));
		addRecipe(ItemStackUtil.comparableStackFrom(Item.getItemFromBlock(ModBlocks.block_meteor)), new OreDictStack(CO.dust()), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE)));

		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.meteorite_sword_hardened), new OreDictStack(CO.ingot()), ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_alloyed));
		addRecipe(ItemStackUtil.comparableStackFrom(ModItems.meteorite_sword_hardened), new OreDictStack(CO.dust()), ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_alloyed));

		if(GeneralConfig.enableDebugMode) {
			addRecipe(new OreDictStack(IRON.ingot()), new OreDictStack(NETHERQUARTZ.gem()), ItemStackUtil.itemStackFrom(ModBlocks.test_render, 1));
		}
		if(GeneralConfig.enableBabyMode) {
			addRecipe(ItemStackUtil.comparableStackFrom(ModItems.canister_generic), new OreDictStack(COAL.gem()), ItemFluidCanister.getFullCanister(ModForgeFluids.oil));
		}
	}

	public static void registerFuels(){
		addFuel(new OreDictStack(COAL.gem()), 200);
		addFuel(new OreDictStack(COAL.dust()), 220);
		addFuel(new OreDictStack(COAL.block()), 2000);
		addFuel(new OreDictStack(LIGNITE.gem()), 150);
		addFuel(new OreDictStack(LIGNITE.dust()), 150);
		addFuel(new OreDictStack(LIGNITE.block()), 1500);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.briquette_lignite), 200);
		addFuel(new OreDictStack("gemCharcoal"), 150);
		addFuel(new OreDictStack("blockCharcoal"), 1500);
		addFuel(new OreDictStack("fuelCoke"), 400);
		addFuel(new OreDictStack(ANY_COKE.gem()), 400);
		addFuel(new OreDictStack(ANY_COKE.block()), 4000);
		addFuel(ItemStackUtil.comparableStackFrom(Items.LAVA_BUCKET), 12800);
		addFuel(ItemStackUtil.comparableStackFrom(Items.BLAZE_ROD), 1000);
		addFuel(ItemStackUtil.comparableStackFrom(Items.BLAZE_POWDER), 300);
		addFuel(ItemStackUtil.comparableStackFrom(Items.COAL, 1, 1), 200);
		addFuel(new OreDictStack(INFERNAL.gem()), 300);
		addFuel(new OreDictStack(INFERNAL.block()), 3000);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.solid_fuel), 400);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.solid_fuel_presto), 800);
		addFuel(ItemStackUtil.comparableStackFrom(ModItems.solid_fuel_presto_triplet), 2400);
		addFuel(ItemStackUtil.comparableStackFrom(ModBlocks.block_solid_fuel), 4000);
		addFuel(ItemStackUtil.comparableStackFrom(ModBlocks.block_solid_fuel_presto), 8000);
		addFuel(ItemStackUtil.comparableStackFrom(ModBlocks.block_solid_fuel_presto_triplet), 12800);
	}

	public static void addRecipe(AStack inputTop, AStack inputBottom, ItemStack output){
		diRecipes.put(new Pair(inputTop, inputBottom), output);
		diRecipes.put(new Pair(inputBottom, inputTop), output);
	}

	public static void removeRecipe(AStack inputTop, AStack inputBottom){
		diRecipes.remove(new Pair(inputTop, inputBottom));
		diRecipes.remove(new Pair(inputBottom, inputTop));
	}

	public static void removeRecipe(ItemStack output){
		diRecipes.values().removeIf(value -> areItemStacksEqual(value,output));;
	}
	public static void addFuel(AStack fuel, int power){
		diFuels.put(fuel, power);
	}

	public static void removeFuel(AStack fuel){
		diFuels.remove(fuel);
	}

	public static ItemStack getFurnaceProcessingResult(ItemStack stack1, ItemStack stack2) {
		if (stack1 == null || stack1.isEmpty() || stack2== null || stack2.isEmpty())
			return null;
		ItemStack item1 = stack1.copy();
		ItemStack item2 = stack2.copy();
		item1.setCount(1);
		item2.setCount(1);
		ItemStack outputItem;

		outputItem = diRecipes.get(new Pair(ItemStackUtil.comparableStackFrom(item1), ItemStackUtil.comparableStackFrom(item2)));
		if(outputItem != null)
			return outputItem;
		outputItem = diRecipes.get(new Pair(new NbtComparableStack(item1), ItemStackUtil.comparableStackFrom(item2)));
		if(outputItem != null)
			return outputItem;
		outputItem = diRecipes.get(new Pair(ItemStackUtil.comparableStackFrom(item1), new NbtComparableStack(item2)));
		if(outputItem != null)
			return outputItem;
		outputItem = diRecipes.get(new Pair(new NbtComparableStack(item1), new NbtComparableStack(item2)));
		if(outputItem != null)
			return outputItem;

		boolean haveTriedAllID2 = false;
		int[] ids1 = OreDictionary.getOreIDs(ItemStackUtil.itemStackFrom(item1.getItem(), 1, item1.getItemDamage()));
		int[] ids2 = OreDictionary.getOreIDs(ItemStackUtil.itemStackFrom(item2.getItem(), 1, item2.getItemDamage()));
		
		for(int id1 = 0; id1 < ids1.length; id1++) {

			OreDictStack oreStack1 = new OreDictStack(OreDictionary.getOreName(ids1[id1]));
			outputItem = diRecipes.get(new Pair(oreStack1, ItemStackUtil.comparableStackFrom(item2)));
			if(outputItem != null)
				return outputItem;
			outputItem = diRecipes.get(new Pair(oreStack1, new NbtComparableStack(item2)));
			if(outputItem != null)
				return outputItem;

			for(int id2 = 0; id2 < ids2.length; id2++) {
				OreDictStack oreStack2 = new OreDictStack(OreDictionary.getOreName(ids2[id2]));
				if(!haveTriedAllID2){
					outputItem = diRecipes.get(new Pair(ItemStackUtil.comparableStackFrom(item1), oreStack2));
					if(outputItem != null)
						return outputItem;
					outputItem = diRecipes.get(new Pair(new NbtComparableStack(item1), oreStack2));
					if(outputItem != null)
						return outputItem;
				}
				outputItem = diRecipes.get(new Pair(oreStack1, oreStack2));
				if(outputItem != null)
					return outputItem;
				
			}
			haveTriedAllID2 = true;
		}

		for(int id2 = 0; id2 < ids2.length; id2++) {
			OreDictStack oreStack2 = new OreDictStack(OreDictionary.getOreName(ids2[id2]));
			if(!haveTriedAllID2){
				outputItem = diRecipes.get(new Pair(ItemStackUtil.comparableStackFrom(item1), oreStack2));
				if(outputItem != null)
					return outputItem;
				outputItem = diRecipes.get(new Pair(new NbtComparableStack(item1), oreStack2));
				if(outputItem != null)
					return outputItem;
			}
		}
		return null;
	}

	public static int getItemPower(ItemStack stack) {
		if(stack == null || stack.isEmpty()){
			return 0;
		}
		ItemStack item = stack.copy();
		item.setCount(1);
		int power = 0;
		if(item.hasTagCompound()){
			power = toInt(diFuels.get(new NbtComparableStack(item)));
		}else{
			power = toInt(diFuels.get(ItemStackUtil.comparableStackFrom(item)));
		}
		if(power > 0){
			return power;
		}
		for(int id : OreDictionary.getOreIDs(ItemStackUtil.itemStackFrom(item.getItem(), 1, item.getItemDamage()))){
			power = toInt(diFuels.get(new OreDictStack(OreDictionary.getOreName(id))));
			if(power > 0){
				return power;
			}
		}
		return 0;
	}

	public static int toInt(Integer i){
		if(i == null)
			return 0;
		return i;
	}

	public static List<ItemStack> getAlloyFuels() {
		HashSet uniqueFuels = new HashSet<ItemStack>();
		ArrayList<ItemStack> fuels = new ArrayList<ItemStack>();
		for(AStack entry : DiFurnaceRecipes.diFuels.keySet()){
			fuels.addAll(entry.getStackList());
		}
		fuels.addAll(uniqueFuels);
		return fuels;
	}
}
