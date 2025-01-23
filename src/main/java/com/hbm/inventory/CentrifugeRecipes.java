package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import static com.hbm.inventory.OreDictManager.*;
import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import com.hbm.items.special.ItemBedrockOre;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CentrifugeRecipes {

	private static final LinkedHashMap<Object, ItemStack[]> recipes = new LinkedHashMap<Object, ItemStack[]>();
	private static List<CentrifugeRecipe> centrifugeRecipes = null;
	
	public static void register() {
		
		recipes.put(ItemStackUtil.comparableStackFrom(ModItems.waste_uranium), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU_MIX), 2),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TECHNETIUM), 1),
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_tiny, 2) });
		
		recipes.put(ItemStackUtil.comparableStackFrom(ModItems.waste_plutonium), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU_MIX), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU_MIX), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TECHNETIUM), 1),
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_tiny, 3) });
		
		recipes.put(ItemStackUtil.comparableStackFrom(ModItems.waste_mox), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU_MIX), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TECHNETIUM), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U238), 1),
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_tiny, 3) });
		
		recipes.put(ItemStackUtil.comparableStackFrom(ModItems.waste_schrabidium), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.BERYLLIUM), 2),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.LEAD), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SOLINIUM), 1),
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_tiny, 2) });
		
		recipes.put(ItemStackUtil.comparableStackFrom(ModItems.waste_thorium), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U238), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TH232), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U233), 2),
				ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_tiny, 2) });
		
		recipes.put(ItemStackUtil.comparableStackFrom(ModItems.powder_cloud), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_copper, 1),
				ItemStackUtil.itemStackFrom(ModItems.sulfur, 1),
				ItemStackUtil.itemStackFrom(ModItems.dust, 1),
				ItemStackUtil.itemStackFrom(ModItems.dust, 1) });

		recipes.put(COAL.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_coal, 2),
				ItemStackUtil.itemStackFrom(ModItems.powder_coal, 2),
				ItemStackUtil.itemStackFrom(ModItems.powder_coal, 2),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(LIGNITE.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_lignite, 2),
				ItemStackUtil.itemStackFrom(ModItems.powder_lignite, 2),
				ItemStackUtil.itemStackFrom(ModItems.powder_lignite, 2),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(IRON.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(GOLD.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_gold, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_gold, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_gold, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(DIAMOND.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(EMERALD.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_emerald, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_emerald, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_emerald, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(TI.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_titanium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_titanium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put("oreQuartz", new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1),
				ItemStackUtil.itemStackFrom(Blocks.NETHERRACK, 1) });
		
		recipes.put(W.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_tungsten, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_tungsten, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(CU.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_copper, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_copper, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_gold, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(AL.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_aluminium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_aluminium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(PB.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_lead, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_lead, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_gold, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });

		recipes.put(ASBESTOS.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_asbestos, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_asbestos, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_boron_tiny, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(SA326.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium, 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SOLINIUM), 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(RAREEARTH.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.ZIRCONIUM), 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.ZIRCONIUM), 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });

		recipes.put(CINNABAR.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.cinnebar, 2),
				ItemStackUtil.itemStackFrom(ModItems.cinnebar, 2),
				ItemStackUtil.itemStackFrom(Items.REDSTONE, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(PU.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.POLONIUM), 3),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(U.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 1),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.RA226), 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(TH232.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_thorium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_thorium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(BE.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_beryllium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_beryllium, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_emerald, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(REDSTONE.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(Items.REDSTONE, 3),
				ItemStackUtil.itemStackFrom(Items.REDSTONE, 3),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(ItemStackUtil.comparableStackFrom(ModBlocks.ore_tikite), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 2),
				ItemStackUtil.itemStackFrom(ModItems.powder_cobalt, 2),
				ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix, 1),
				ItemStackUtil.itemStackFrom(Blocks.END_STONE, 1) });
		
		recipes.put(LAPIS.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_lapis, 3),
				ItemStackUtil.itemStackFrom(ModItems.powder_lapis, 3),
				ItemStackUtil.itemStackFrom(ModItems.powder_cobalt_tiny, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		
		recipes.put(STAR.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_dura_steel, 3),
				ItemStackUtil.itemStackFrom(ModItems.powder_astatine, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_cobalt, 2),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });

		recipes.put(ItemStackUtil.comparableStackFrom(ModItems.powder_tektite), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_paleogenite_tiny, 2),
				ItemStackUtil.itemStackFrom(ModItems.powder_meteorite_tiny, 1),
				ItemStackUtil.itemStackFrom(ModItems.powder_meteorite_tiny, 1),
				ItemStackUtil.itemStackFrom(ModItems.dust, 6) });
		
		recipes.put(ItemStackUtil.comparableStackFrom(ModBlocks.block_euphemium_cluster), new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.EUPHEMIUM), 7),
				ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium, 4),
				ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STARMETAL), 2),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SOLINIUM), 2) });
		
		recipes.put(P_RED.ore(), new ItemStack[] {
				ItemStackUtil.itemStackFrom(Items.BLAZE_POWDER, 2),
				ItemStackUtil.itemStackFrom(ModItems.powder_fire, 2),
				ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PHOSPHORUS)),
				ItemStackUtil.itemStackFrom(Blocks.NETHERRACK) });
		
		recipes.put(ItemStackUtil.comparableStackFrom(Items.BLAZE_ROD), new ItemStack[] {ItemStackUtil.itemStackFrom(Items.BLAZE_POWDER, 1), ItemStackUtil.itemStackFrom(Items.BLAZE_POWDER, 1), ItemStackUtil.itemStackFrom(ModItems.powder_fire, 1), ItemStackUtil.itemStackFrom(ModItems.powder_fire, 1) });
		
		recipes.put(SRN.ingot(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 2), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 1), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.URANIUM), 3), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM), 2) });
		
		recipes.put(COAL.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_coal, 3), ItemStackUtil.itemStackFrom(ModItems.powder_coal, 3), ItemStackUtil.itemStackFrom(ModItems.powder_coal, 3), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(IRON.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_iron, 2), ItemStackUtil.itemStackFrom(ModItems.powder_iron, 2), ItemStackUtil.itemStackFrom(ModItems.powder_titanium, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(GOLD.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_gold, 2), ItemStackUtil.itemStackFrom(ModItems.powder_gold, 2), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(REDSTONE.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(Items.REDSTONE, 3), ItemStackUtil.itemStackFrom(Items.REDSTONE, 3), ItemStackUtil.itemStackFrom(Items.REDSTONE, 3), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 3) });
		recipes.put(LAPIS.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_lapis, 8), ItemStackUtil.itemStackFrom(ModItems.powder_lapis, 8), ItemStackUtil.itemStackFrom(ModItems.powder_cobalt, 1), ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
		recipes.put(DIAMOND.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 1), ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 1), ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 1), ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 1) });
		recipes.put(U.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 2), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.RA226), 2), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(TH232.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_thorium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_thorium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 1), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.RA226), 1) });
		recipes.put(PU.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_polonium, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(TI.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_titanium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_titanium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(S.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.sulfur, 4), ItemStackUtil.itemStackFrom(ModItems.sulfur, 4), ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 1) });
		recipes.put(KNO.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.niter, 3), ItemStackUtil.itemStackFrom(ModItems.niter, 3), ItemStackUtil.itemStackFrom(ModItems.niter, 3), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(CU.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_copper, 2), ItemStackUtil.itemStackFrom(ModItems.powder_copper, 2), ItemStackUtil.itemStackFrom(ModItems.sulfur, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(W.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_tungsten, 2), ItemStackUtil.itemStackFrom(ModItems.powder_tungsten, 2), ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(AL.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_aluminium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_aluminium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_iron, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(F.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.fluorite, 3), ItemStackUtil.itemStackFrom(ModItems.fluorite, 3), ItemStackUtil.itemStackFrom(ModItems.fluorite, 3), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(BE.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_beryllium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_beryllium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(PB.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_lead, 2), ItemStackUtil.itemStackFrom(ModItems.powder_lead, 2), ItemStackUtil.itemStackFrom(ModItems.powder_gold, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(SRN.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 2), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 2), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.URANIUM), 2), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM), 2) });
		recipes.put(SA326.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 1), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(RAREEARTH.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 1), ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 1), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.ZIRCONIUM), 2), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.ZIRCONIUM), 2) });
		recipes.put(CINNABAR.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.cinnebar, 3), ItemStackUtil.itemStackFrom(ModItems.cinnebar, 3), ItemStackUtil.itemStackFrom(Items.REDSTONE, 2), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(P_RED.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_fire, 3), ItemStackUtil.itemStackFrom(ModItems.powder_fire, 3), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PHOSPHORUS), 2), ItemStackUtil.itemStackFrom(Items.BLAZE_POWDER, 2) });
		recipes.put(ItemStackUtil.comparableStackFrom(ModItems.crystal_trixite), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 3), ItemStackUtil.itemStackFrom(ModItems.powder_cobalt, 3), ItemStackUtil.itemStackFrom(ModItems.powder_spark_mix, 1), ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix, 2) });
		recipes.put(LI.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_lithium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_lithium, 2), ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 1), ItemStackUtil.itemStackFrom(ModItems.fluorite, 1) });
		recipes.put(STAR.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_dura_steel, 3), ItemStackUtil.itemStackFrom(ModItems.powder_cobalt, 3), ItemStackUtil.itemStackFrom(ModItems.powder_astatine, 2), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 5) });
		recipes.put(CO.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_cobalt, 2), ItemStackUtil.itemStackFrom(ModItems.powder_iron, 3), ItemStackUtil.itemStackFrom(ModItems.powder_copper, 3), ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1) });
		recipes.put(ASBESTOS.crystal(), new ItemStack[] { ItemStackUtil.itemStackFrom(ModItems.powder_asbestos, 2), ItemStackUtil.itemStackFrom(ModItems.powder_asbestos, 2), ItemStackUtil.itemStackFrom(ModItems.powder_boron_tiny, 1), ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });

		for(final Entry<Integer, String> entry : BedrockOreRegistry.oreIndexes.entrySet()) {
			final int oreMeta = entry.getKey();
			final String oreName = entry.getValue();
 			recipes.put(ItemStackUtil.comparableStackFrom(ModItems.ore_bedrock, 1, oreMeta), new ItemStack[] { 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_centrifuged, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_centrifuged, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
			recipes.put(ItemStackUtil.comparableStackFrom(ModItems.ore_bedrock_cleaned, 1, oreMeta), new ItemStack[] { 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_separated, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_separated, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
			recipes.put(ItemStackUtil.comparableStackFrom(ModItems.ore_bedrock_deepcleaned, 1, oreMeta), new ItemStack[] { 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_purified, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_purified, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
			recipes.put(ItemStackUtil.comparableStackFrom(ModItems.ore_bedrock_nitrated, 1, oreMeta), new ItemStack[] { 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_nitrocrystalline, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_nitrocrystalline, 1, oreMeta), 
				getNugget(oreName), 
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
			recipes.put(ItemStackUtil.comparableStackFrom(ModItems.ore_bedrock_seared, 1, oreMeta), new ItemStack[] { 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_exquisite, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_exquisite, 1, oreMeta), 
				getNugget(oreName),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
			recipes.put(ItemStackUtil.comparableStackFrom(ModItems.ore_bedrock_perfect, 1, oreMeta), new ItemStack[] { 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), 
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1),
				ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1) });
			recipes.put(ItemStackUtil.comparableStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), new ItemStack[] { 
				ItemBedrockOre.getOut(oreMeta, 1), 
				ItemBedrockOre.getOut(oreMeta, 1), 
				ItemBedrockOre.getOut(oreMeta, 1), 
				ItemBedrockOre.getOut(oreMeta, 1) });
		}
	}

	public static ItemStack getNugget(final String oreName){
		if(oreName.equals("oreLead") || oreName.equals("oreCopper")) return ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.CADMIUM), 1);
		if(oreName.equals("oreGold") || oreName.equals("oreTungsten")) return ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.BISMUTH), 1);
		if(oreName.equals("oreUranium")) return ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.RA226), 1);
		if(oreName.equals("oreThorium")) return ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TECHNETIUM), 1);
		if(oreName.equals("oreStarmetal")) return ItemStackUtil.itemStackFrom(ModItems.powder_meteorite_tiny, 1);
		if(oreName.equals("oreRedstone")) return ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 1);
		if(oreName.equals("oreRedPhosphorus")) return ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.ARSENIC), 1);
		if(oreName.equals("oreNeodymium")) return ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TANTALIUM), 1);
		return ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1);
	}

	public static void addRecipe(final ItemStack in, final ItemStack[] outputs){
		recipes.put(ItemStackUtil.comparableStackFrom(in), outputs);
	}

	public static void removeRecipe(final ItemStack in){
		recipes.remove(ItemStackUtil.comparableStackFrom(in));
	}
	
	public static ItemStack[] getOutput(final ItemStack stack) {
		
		if(stack == null || stack.getItem() == null)
			return null;
	
		final ComparableStack comp = ItemStackUtil.comparableStackFrom(stack.getItem(), 1, stack.getItemDamage());
		if(recipes.containsKey(comp))
			return RecipesCommon.copyStackArray(recipes.get(comp));
		
		final String[] dictKeys = comp.getDictKeys();
		
		for(final String key : dictKeys) {

			if(recipes.containsKey(key))
				return RecipesCommon.copyStackArray(recipes.get(key));
		}
		
		return null;
	}

	public static List<CentrifugeRecipe> getCentrifugeRecipes() {
		if(centrifugeRecipes != null)
			return centrifugeRecipes;
		centrifugeRecipes = new ArrayList<CentrifugeRecipe>();
		
		for(final Entry<Object, ItemStack[]> entry : CentrifugeRecipes.recipes.entrySet()) {
			
			if(entry.getKey() instanceof String) {
				final List<ItemStack> ingredients = OreDictionary.getOres((String)entry.getKey());
				centrifugeRecipes.add(new CentrifugeRecipe(ingredients, Arrays.asList(entry.getValue())));
			} else {
				centrifugeRecipes.add(new CentrifugeRecipe(((ComparableStack)entry.getKey()).toStack(), Arrays.asList(entry.getValue())));
			}
		}
		
		return centrifugeRecipes;
	}
	
	public static class CentrifugeRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final List<ItemStack> outputs;
		public final List<ItemStack> inputs;
		
		public CentrifugeRecipe(final ItemStack input, final List<ItemStack> outputs) {
			this.input = input;
			this.inputs = null;
			this.outputs = outputs; 
		}
		
		public CentrifugeRecipe(final List<ItemStack> inputs, final List<ItemStack> outputs) {
			this.inputs = inputs;
			this.input = ItemStack.EMPTY;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			if(inputs != null){
				ingredients.setInputLists(VanillaTypes.ITEM, Arrays.asList(inputs));
			} else {
				ingredients.setInput(VanillaTypes.ITEM, input);
			}
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}
}