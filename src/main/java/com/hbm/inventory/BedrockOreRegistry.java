package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.Spaghetti;
import com.hbm.lib.Library;
import com.hbm.config.BedrockOreJsonConfig;
import com.hbm.config.CompatibilityConfig;
import com.hbm.util.WeightedRandomObject;

import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.fluids.FluidStack;

//TODO: clean this shit up
@Spaghetti("everything")
public class BedrockOreRegistry {

	public static HashMap<Integer, String> oreIndexes = new HashMap();
	public static HashMap<String, Integer> oreToIndexes = new HashMap();

	public static HashMap<String, String> oreResults = new HashMap();
	public static HashMap<String, Integer> oreColors = new HashMap();
	public static HashMap<String, String> oreNames = new HashMap();
	public static HashMap<String, Integer> oreTiers = new HashMap();
	
	public static HashMap<Integer, List<WeightedRandomObject>> oreCasino = new HashMap();

	public static void registerBedrockOres(){
		collectBedrockOres();
		fillOreCasino();
	}

	public static boolean is3DBlock(final String ore){
		boolean isBlock = false;
		for(final ItemStack item : OreDictionary.getOres(ore))
			isBlock |= (item != null && !item.isEmpty() && item.getItem() instanceof ItemBlock);
        return isBlock;
    }

	public static boolean isActualItem(final String ore){
		boolean isActualItem = false;
		for(final ItemStack item : OreDictionary.getOres(ore))
			isActualItem |= (item != null && !item.isEmpty() && item.getItem() != Items.AIR);
        return isActualItem;
    }

	public static boolean tryRegister(final int index, final String oreName, final String output){
		if(OreDictionary.doesOreNameExist(output) && isActualItem(output)){
			oreIndexes.put(index, oreName);
			oreToIndexes.put(oreName, index);
			oreResults.put(oreName, output);
			oreTiers.put(oreName, Math.max(1, 1+getDirectOreTier(oreName)));
			return true;
		}
		return false;
	}

	public static void collectBedrockOres(){
		int index = 0;
		for(final String oreName : OreDictionary.getOreNames()){
			if(oreName.startsWith("ore") && is3DBlock(oreName) && !CompatibilityConfig.bedrockOreBlacklist.contains(oreName)){

				final String resourceName = oreName.substring(3);
				
				String oreOutput = "gem"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}

				oreOutput = "dust"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}

				oreOutput = "ingot"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}

				oreOutput = "item"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}

				oreOutput = "food"+resourceName;
				if(tryRegister(index, oreName, oreOutput)){
					index++;
					continue;
				}
			}
		}
	}

	public static int getOreIndex(final String ore){
		final Integer x = oreToIndexes.get(ore);
		if(x == null) return -1;
		return x;
	}

	public static int getOreTier(final String ore){
		final Integer x = oreTiers.get(ore);
		if(x == null) return 0;
		return x;
	}

	public static FluidStack getFluidRequirement(final int tier){
		if(tier == 1) return new FluidStack(ModForgeFluids.acid, 8000);
		if(tier == 2) return new FluidStack(ModForgeFluids.sulfuric_acid, 500);
		if(tier == 3) return new FluidStack(ModForgeFluids.nitric_acid, 500);
		if(tier == 4) return new FluidStack(ModForgeFluids.radiosolvent, 200);
		if(tier == 5) return new FluidStack(ModForgeFluids.schrabidic, 200);
		if(tier == 6) return new FluidStack(ModForgeFluids.uu_matter, 200);
		if(tier > 6) return new FluidStack(ModForgeFluids.liquid_osmiridium, 100);
		return new FluidStack(ModForgeFluids.solvent, 300);
	}

	public static int getTierWeight(final int tier){
		if(tier <= 1) return 64;
		if(tier == 2) return 48;
		if(tier == 3) return 32;
		if(tier == 4) return 8;
		if(tier == 5) return 2;
		if(tier >= 6) return 1;
		return 1;
	}

	public static void fillOreCasino(){
		for(final Integer dimID : BedrockOreJsonConfig.dimOres.keySet()){

			final List<WeightedRandomObject> oreWeights = new ArrayList();
			for(final String oreName : oreResults.keySet()){

				if(BedrockOreJsonConfig.isOreAllowed(dimID, oreName))
					oreWeights.add(new WeightedRandomObject(oreName, getTierWeight(getOreTier(oreName))));
			}
			oreCasino.put(dimID, oreWeights);
		}
	}

	public static String rollOreName(final int dimID, final Random rand){
		if(oreCasino.get(dimID).isEmpty()) return null;
		return WeightedRandom.getRandomItem(rand, oreCasino.get(dimID)).asString();
	}

	public static int getDirectOreTier(final String oreName){
		int tierCount = 0;
		int tierSum = 0;
		final List<ItemStack> outputs = OreDictionary.getOres(oreName);
		Block ore = null;
		for(final ItemStack stack : outputs){
			ore = Block.getBlockFromItem(stack.getItem());
			final int tier = ore.getHarvestLevel(ore.getDefaultState());
			if(tier > -1){
				tierSum += tier;
				tierCount++;
			}
		}
		if(tierCount > 0)
			return tierSum/tierCount;
		return 0;
	}

	public static String getOreName(final String oreName){
		return oreName.substring(3).replaceAll("([A-Z])", " $1").trim();
	}

	public static void registerOreColors(){
		for(final Map.Entry<String, String> entry : oreResults.entrySet()) {
			final List<ItemStack> oreResult = OreDictionary.getOres(entry.getValue());
			if(oreResult.size() > 0){
				final int color = Library.getColorFromItemStack(oreResult.get(0));
				oreColors.put(entry.getKey(), color);
			}
		}
		registerScannerOreColors();
	}
	//used by Resource Scanner Sat
	public static HashMap<String, Integer> oreScanColors = new HashMap<>();
	public static void registerScannerOreColors(){
		for(final String entry : OreDictionary.getOreNames()) {
			if(!entry.startsWith("ore")) continue;
			final List<ItemStack> oreResult = OreDictionary.getOres(entry);
			if(oreResult.size() > 0){
				final int color = Library.getColorFromItemStack(oreResult.get(0));
				oreScanColors.put(entry, color);
			}
		}
	}

	public static int getOreScanColor(final String ore){
		final Integer x = oreScanColors.get(ore);
		if(x == null) return 0;
		return x;
	}

	public static ItemStack getResource(final String ore){
		final List<ItemStack> outputs = OreDictionary.getOres(oreResults.get(ore));
		if(outputs.size() > 0) return outputs.get(0);
		return ItemStackUtil.itemStackFrom(Items.AIR);
	}

	public static int getOreColor(final String ore){
		final Integer x = oreColors.get(ore);
		if(x == null) return 0xFFFFFF;
		return x;
	}
}
