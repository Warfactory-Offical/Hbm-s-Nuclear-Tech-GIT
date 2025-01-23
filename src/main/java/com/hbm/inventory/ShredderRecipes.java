package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.special.ItemBedrockOre;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ShredderRecipes {

	public static LinkedHashMap<ComparableStack, ItemStack> shredderRecipes = new LinkedHashMap<ComparableStack, ItemStack>();
	public static List<ShredderRecipe> jeiShredderRecipes = null;
	
	public static void registerShredder() {
		
		final String[] names = OreDictionary.getOreNames();
		
		for(int i = 0; i < names.length; i++) {
			
			final String name = names[i];
			
			//if the dict contains invalid names, skip
			if(name == null || name.isEmpty())
				continue;
			
			final List<ItemStack> matches = OreDictionary.getOres(name);
			
			//if the name isn't assigned to an ore, also skip
			if(matches == null || matches.isEmpty())
				continue;

			if(name.length() > 5 && name.startsWith("ingot")) {
				final ItemStack dust = getDustByName(name.substring(5));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {

					for(final ItemStack stack : matches) {
						shredderRecipes.put(ItemStackUtil.comparableStackFrom(stack), dust);
					}
				}
			} else if(name.length() > 6 && name.startsWith("nugget")) {
				final ItemStack dust = getTinyDustByName(name.substring(6));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {

					for(final ItemStack stack : matches) {
						shredderRecipes.put(ItemStackUtil.comparableStackFrom(stack), dust);
					}
				}
			} else if(name.length() > 3 && name.startsWith("ore")) {
				final ItemStack dust = getDustByName(name.substring(3));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {
					
					dust.setCount(2);

					for(final ItemStack stack : matches) {
						shredderRecipes.put(ItemStackUtil.comparableStackFrom(stack), dust);
					}
				}
			} else if(name.length() > 5 && name.startsWith("block")) {
				final ItemStack dust = getDustByName(name.substring(5));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {
					
					dust.setCount(9);

					for(final ItemStack stack : matches) {
						shredderRecipes.put(ItemStackUtil.comparableStackFrom(stack), dust);
					}
				}
			} else if(name.length() > 3 && name.startsWith("gem")) {
				final ItemStack dust = getDustByName(name.substring(3));
				
				if(dust != null && dust.getItem() != ModItems.scrap) {

					for(final ItemStack stack : matches) {
						shredderRecipes.put(ItemStackUtil.comparableStackFrom(stack), dust);
					}
				}
			} else if(name.length() > 3 && name.startsWith("dust")) {

				for(final ItemStack stack : matches) {
					if(stack != null && !stack.isEmpty() && Item.REGISTRY.getNameForObject(stack.getItem()) != null)
						shredderRecipes.put(ItemStackUtil.comparableStackFrom(stack), ItemStackUtil.itemStackFrom(ModItems.dust));
				}
			}
		}
	}
	
	public static void registerOverrides() {

		ShredderRecipes.setRecipe(ModItems.scrap, ItemStackUtil.itemStackFrom(ModItems.dust));
		ShredderRecipes.setRecipe(ModItems.dust, ItemStackUtil.itemStackFrom(ModItems.dust));
		ShredderRecipes.setRecipe(Blocks.GLOWSTONE, ItemStackUtil.itemStackFrom(Items.GLOWSTONE_DUST, 4));
		ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(Blocks.QUARTZ_BLOCK, 1, 0), ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 4));
		ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(Blocks.QUARTZ_BLOCK, 1, 1), ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 4));
		ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(Blocks.QUARTZ_BLOCK, 1, 2), ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 4));
		ShredderRecipes.setRecipe(Blocks.QUARTZ_STAIRS, ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 3));
		ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(Blocks.STONE_SLAB, 1, 7), ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 2));
		ShredderRecipes.setRecipe(Items.QUARTZ, ItemStackUtil.itemStackFrom(ModItems.powder_quartz));
		ShredderRecipes.setRecipe(Blocks.QUARTZ_ORE, ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 2));
		ShredderRecipes.setRecipe(ModBlocks.ore_nether_fire, ItemStackUtil.itemStackFrom(ModItems.powder_fire, 6));
		ShredderRecipes.setRecipe(Blocks.PACKED_ICE, ItemStackUtil.itemStackFrom(ModItems.powder_ice, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_light, ItemStackUtil.itemStackFrom(Items.CLAY_BALL, 4));
		ShredderRecipes.setRecipe(ModBlocks.concrete, ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1));
		ShredderRecipes.setRecipe(ModBlocks.concrete_smooth, ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_concrete, ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1));
		ShredderRecipes.setRecipe(ModBlocks.brick_obsidian, ItemStackUtil.itemStackFrom(ModBlocks.gravel_obsidian, 1));
		ShredderRecipes.setRecipe(Blocks.OBSIDIAN, ItemStackUtil.itemStackFrom(ModBlocks.gravel_obsidian, 1));
		ShredderRecipes.setRecipe(Blocks.STONE, ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1));
		ShredderRecipes.setRecipe(Blocks.COBBLESTONE, ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1));
		ShredderRecipes.setRecipe(Blocks.STONEBRICK, ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1));
		ShredderRecipes.setRecipe(Blocks.GRAVEL, ItemStackUtil.itemStackFrom(Blocks.SAND, 1));
		ShredderRecipes.setRecipe(Blocks.SAND, ItemStackUtil.itemStackFrom(ModItems.dust, 2));
		ShredderRecipes.setRecipe(Blocks.BRICK_BLOCK, ItemStackUtil.itemStackFrom(Items.CLAY_BALL, 4));
		ShredderRecipes.setRecipe(Blocks.BRICK_STAIRS, ItemStackUtil.itemStackFrom(Items.CLAY_BALL, 3));
		ShredderRecipes.setRecipe(Items.FLOWER_POT, ItemStackUtil.itemStackFrom(Items.CLAY_BALL, 3));
		ShredderRecipes.setRecipe(Items.BRICK, ItemStackUtil.itemStackFrom(Items.CLAY_BALL, 1));
		ShredderRecipes.setRecipe(Blocks.SANDSTONE, ItemStackUtil.itemStackFrom(Blocks.SAND, 4));
		ShredderRecipes.setRecipe(Blocks.SANDSTONE_STAIRS, ItemStackUtil.itemStackFrom(Blocks.SAND, 6));
		ShredderRecipes.setRecipe(Blocks.CLAY, ItemStackUtil.itemStackFrom(Items.CLAY_BALL, 4));
		ShredderRecipes.setRecipe(Blocks.HARDENED_CLAY, ItemStackUtil.itemStackFrom(Items.CLAY_BALL, 4));
		ShredderRecipes.setRecipe(Blocks.TNT, ItemStackUtil.itemStackFrom(Items.GUNPOWDER, 5));
		ShredderRecipes.setRecipe(Items.BONE, ItemStackUtil.itemStackFrom(Items.DYE, 5, 15));
		ShredderRecipes.setRecipe(Blocks.CACTUS, ItemStackUtil.itemStackFrom(Items.DYE, 2, 2));
		ShredderRecipes.setRecipe(ModBlocks.stone_gneiss, ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.powder_lapis, ItemStackUtil.itemStackFrom(ModItems.powder_cobalt_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_neodymium, ItemStackUtil.itemStackFrom(ModItems.powder_neodymium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_cobalt, ItemStackUtil.itemStackFrom(ModItems.powder_cobalt_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_niobium, ItemStackUtil.itemStackFrom(ModItems.powder_niobium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_cerium, ItemStackUtil.itemStackFrom(ModItems.powder_cerium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_lanthanium, ItemStackUtil.itemStackFrom(ModItems.powder_lanthanium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_actinium, ItemStackUtil.itemStackFrom(ModItems.powder_actinium_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_boron, ItemStackUtil.itemStackFrom(ModItems.powder_boron_tiny, 1));
		ShredderRecipes.setRecipe(ModItems.fragment_meteorite, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite_tiny, 1));
		ShredderRecipes.setRecipe(ModBlocks.block_meteor, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 10));
		ShredderRecipes.setRecipe(Items.ENCHANTED_BOOK, ItemStackUtil.itemStackFrom(ModItems.powder_magic, 1));
		ShredderRecipes.setRecipe(ModItems.arc_electrode_burnt, ItemStackUtil.itemStackFrom(ModItems.powder_coal, 1));
		ShredderRecipes.setRecipe(ModItems.arc_electrode_desh, ItemStackUtil.itemStackFrom(ModItems.powder_desh, 2));
		ShredderRecipes.setRecipe(ModBlocks.meteor_polished, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_brick, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_brick_mossy, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_brick_cracked, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_brick_chiseled, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.meteor_pillar, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 1));
		ShredderRecipes.setRecipe(ModBlocks.ore_tektite_osmiridium, ItemStackUtil.itemStackFrom(ModItems.powder_tektite, 1));
		ShredderRecipes.setRecipe(ModBlocks.ore_rare, ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 1));
		ShredderRecipes.setRecipe(Blocks.DIAMOND_ORE, ItemStackUtil.itemStackFrom(ModBlocks.gravel_diamond, 2));
		ShredderRecipes.setRecipe(ModBlocks.boxcar, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 32));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDATE), ItemStackUtil.itemStackFrom(ModItems.powder_schrabidate, 1));
		ShredderRecipes.setRecipe(ModBlocks.block_schrabidate, ItemStackUtil.itemStackFrom(ModItems.powder_schrabidate, 9));
		ShredderRecipes.setRecipe(ModItems.coal_infernal, ItemStackUtil.itemStackFrom(ModItems.powder_coal, 3));
		ShredderRecipes.setRecipe(Items.REEDS, ItemStackUtil.itemStackFrom(Items.SUGAR, 2));
		ShredderRecipes.setRecipe(Items.FERMENTED_SPIDER_EYE, ItemStackUtil.itemStackFrom(ModItems.powder_poison, 3));
		ShredderRecipes.setRecipe(Items.POISONOUS_POTATO, ItemStackUtil.itemStackFrom(ModItems.powder_poison, 1));

		ShredderRecipes.setRecipe(ModBlocks.dirt_dead, ItemStackUtil.itemStackFrom(ModItems.scrap_oil, 1));
		ShredderRecipes.setRecipe(ModBlocks.dirt_oily, ItemStackUtil.itemStackFrom(ModItems.scrap_oil, 1));
		ShredderRecipes.setRecipe(ModBlocks.sand_dirty, ItemStackUtil.itemStackFrom(ModItems.scrap_oil, 1));
		ShredderRecipes.setRecipe(ModBlocks.sand_dirty_red, ItemStackUtil.itemStackFrom(ModItems.scrap_oil, 1));
		ShredderRecipes.setRecipe(ModBlocks.stone_cracked, ItemStackUtil.itemStackFrom(ModItems.scrap_oil, 1));
		ShredderRecipes.setRecipe(ModBlocks.stone_porous, ItemStackUtil.itemStackFrom(ModItems.scrap_oil, 1));

		ShredderRecipes.setRecipe(ModBlocks.deco_pipe, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_rusted, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_green, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_green_rusted, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_red, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_marked, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_rim, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_rim_rusted, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_rim_green, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_rim_green_rusted, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_rim_red, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_rim_marked, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_quad, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_quad_rusted, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_quad_green, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_quad_green_rusted, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_quad_red, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_quad_marked, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_framed, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_framed_rusted, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_framed_green, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_framed_green_rusted, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_framed_red, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.deco_pipe_framed_marked, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.SCHRARANIUM), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 2));
		ShredderRecipes.setRecipe(ModItems.crystal_coal, ItemStackUtil.itemStackFrom(ModItems.powder_coal, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_iron, ItemStackUtil.itemStackFrom(ModItems.powder_iron, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_gold, ItemStackUtil.itemStackFrom(ModItems.powder_gold, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_redstone, ItemStackUtil.itemStackFrom(Items.REDSTONE, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_lapis, ItemStackUtil.itemStackFrom(ModItems.powder_lapis, 12));
		ShredderRecipes.setRecipe(ModItems.crystal_diamond, ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_uranium, ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_plutonium, ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_thorium, ItemStackUtil.itemStackFrom(ModItems.powder_thorium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_titanium, ItemStackUtil.itemStackFrom(ModItems.powder_titanium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_sulfur, ItemStackUtil.itemStackFrom(ModItems.sulfur, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_niter, ItemStackUtil.itemStackFrom(ModItems.niter, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_copper, ItemStackUtil.itemStackFrom(ModItems.powder_copper, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_tungsten, ItemStackUtil.itemStackFrom(ModItems.powder_tungsten, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_aluminium, ItemStackUtil.itemStackFrom(ModItems.powder_aluminium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_fluorite, ItemStackUtil.itemStackFrom(ModItems.fluorite, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_beryllium, ItemStackUtil.itemStackFrom(ModItems.powder_beryllium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_schraranium, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 3));
		ShredderRecipes.setRecipe(ModItems.crystal_lead, ItemStackUtil.itemStackFrom(ModItems.powder_lead, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_schrabidium, ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_rare, ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 2));
		ShredderRecipes.setRecipe(ModItems.crystal_phosphorus, ItemStackUtil.itemStackFrom(ModItems.powder_fire, 8));
		ShredderRecipes.setRecipe(ModItems.crystal_trixite, ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 6));
		ShredderRecipes.setRecipe(ModItems.crystal_lithium, ItemStackUtil.itemStackFrom(ModItems.powder_lithium, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_starmetal, ItemStackUtil.itemStackFrom(ModItems.powder_dura_steel, 6));
		ShredderRecipes.setRecipe(ModItems.crystal_cobalt, ItemStackUtil.itemStackFrom(ModItems.powder_cobalt, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_cinnebar, ItemStackUtil.itemStackFrom(ModItems.cinnebar, 3));
		ShredderRecipes.setRecipe(ModItems.crystal_asbestos, ItemStackUtil.itemStackFrom(ModItems.powder_asbestos, 3));
		
		ShredderRecipes.setRecipe(ModBlocks.steel_poles, ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 3));
		ShredderRecipes.setRecipe(ModBlocks.pole_top, ItemStackUtil.itemStackFrom(ModItems.powder_tungsten, 4));
		ShredderRecipes.setRecipe(ModBlocks.tape_recorder, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1));
		ShredderRecipes.setRecipe(ModBlocks.pole_satellite_receiver, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 5));
		ShredderRecipes.setRecipe(ModBlocks.steel_roof, ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 13));
		ShredderRecipes.setRecipe(ModBlocks.steel_wall, ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 13));
		ShredderRecipes.setRecipe(ModBlocks.steel_corner, ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 26));
		ShredderRecipes.setRecipe(ModBlocks.steel_beam, ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 3));
		ShredderRecipes.setRecipe(ModBlocks.steel_scaffold, ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 7));
		ShredderRecipes.setRecipe(ModItems.coil_copper, ItemStackUtil.itemStackFrom(ModItems.powder_red_copper, 1));
		ShredderRecipes.setRecipe(ModItems.coil_copper_torus, ItemStackUtil.itemStackFrom(ModItems.powder_red_copper, 2));
		ShredderRecipes.setRecipe(ModItems.coil_advanced_alloy, ItemStackUtil.itemStackFrom(ModItems.powder_advanced_alloy, 1));
		ShredderRecipes.setRecipe(ModItems.coil_advanced_torus, ItemStackUtil.itemStackFrom(ModItems.powder_advanced_alloy, 2));
		ShredderRecipes.setRecipe(ModItems.coil_gold, ItemStackUtil.itemStackFrom(ModItems.powder_gold, 1));
		ShredderRecipes.setRecipe(ModItems.coil_gold_torus, ItemStackUtil.itemStackFrom(ModItems.powder_gold, 2));
		ShredderRecipes.setRecipe(ModItems.coil_tungsten, ItemStackUtil.itemStackFrom(ModItems.powder_tungsten, 1));
		ShredderRecipes.setRecipe(ModItems.coil_magnetized_tungsten, ItemStackUtil.itemStackFrom(ModItems.powder_magnetized_tungsten, 1));
		ShredderRecipes.setRecipe(ModBlocks.crate_iron, ItemStackUtil.itemStackFrom(ModItems.powder_iron, 8));
		ShredderRecipes.setRecipe(ModBlocks.crate_steel, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 8));
		ShredderRecipes.setRecipe(ModBlocks.crate_tungsten, ItemStackUtil.itemStackFrom(ModItems.powder_tungsten, 36));
		ShredderRecipes.setRecipe(Blocks.ANVIL, ItemStackUtil.itemStackFrom(ModItems.powder_iron, 31));
		ShredderRecipes.setRecipe(ModBlocks.chain, ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 1));
		ShredderRecipes.setRecipe(ModBlocks.steel_grate, ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 3));
		ShredderRecipes.setRecipe(ModItems.pipes_steel, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 27));
		
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDATE), ItemStackUtil.itemStackFrom(ModItems.powder_schrabidate, 1));
		ShredderRecipes.setRecipe(ModBlocks.block_schrabidate, ItemStackUtil.itemStackFrom(ModItems.powder_schrabidate, 9));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.AC227), ItemStackUtil.itemStackFrom(ModItems.powder_ac227, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.CO60), ItemStackUtil.itemStackFrom(ModItems.powder_co60, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.RA226), ItemStackUtil.itemStackFrom(ModItems.powder_ra226, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.SR90), ItemStackUtil.itemStackFrom(ModItems.powder_sr90, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.RA226), ItemStackUtil.itemStackFrom(ModItems.powder_ra226, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.PB209), ItemStackUtil.itemStackFrom(ModItems.powder_pb209, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.ASTATINE), ItemStackUtil.itemStackFrom(ModItems.powder_astatine, 1));

		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.TENNESSINE), ItemStackUtil.itemStackFrom(ModItems.powder_tennessine, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.BROMINE), ItemStackUtil.itemStackFrom(ModItems.powder_bromine, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.CAESIUM), ItemStackUtil.itemStackFrom(ModItems.powder_caesium, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.CERIUM), ItemStackUtil.itemStackFrom(ModItems.powder_cerium, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.CO60), ItemStackUtil.itemStackFrom(ModItems.powder_co60, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.STRONTIUM), ItemStackUtil.itemStackFrom(ModItems.powder_strontium, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.SR90), ItemStackUtil.itemStackFrom(ModItems.powder_sr90, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.IODINE), ItemStackUtil.itemStackFrom(ModItems.powder_iodine, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.I131), ItemStackUtil.itemStackFrom(ModItems.powder_i131, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.RADSPICE), ItemStackUtil.itemStackFrom(ModItems.powder_radspice, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.POLYMER), ItemStackUtil.itemStackFrom(ModItems.powder_polymer, 1));
		ShredderRecipes.setRecipe(ModItems.ingot.getItemStack(MaterialMineral.BAKELITE), ItemStackUtil.itemStackFrom(ModItems.powder_bakelite, 1));

		ShredderRecipes.setRecipe(ModBlocks.turret_light, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 16));
		ShredderRecipes.setRecipe(ModBlocks.turret_heavy, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 16));
		ShredderRecipes.setRecipe(ModBlocks.turret_flamer, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 16));
		ShredderRecipes.setRecipe(ModBlocks.turret_rocket, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 16));
		ShredderRecipes.setRecipe(ModBlocks.turret_cwis, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 16));
		ShredderRecipes.setRecipe(ModBlocks.turret_tau, ItemStackUtil.itemStackFrom(ModItems.powder_steel, 16));
		ShredderRecipes.setRecipe(ModItems.turret_light_ammo, ItemStackUtil.itemStackFrom(Items.GUNPOWDER, 4));
		ShredderRecipes.setRecipe(ModItems.turret_heavy_ammo, ItemStackUtil.itemStackFrom(Items.GUNPOWDER, 4));
		ShredderRecipes.setRecipe(ModItems.turret_flamer_ammo, ItemStackUtil.itemStackFrom(Items.GUNPOWDER, 4));
		ShredderRecipes.setRecipe(ModItems.turret_rocket_ammo, ItemStackUtil.itemStackFrom(Items.GUNPOWDER, 4));
		ShredderRecipes.setRecipe(ModItems.turret_cwis_ammo, ItemStackUtil.itemStackFrom(Items.GUNPOWDER, 4));
		ShredderRecipes.setRecipe(ModItems.turret_tau_ammo, ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 4));
		ShredderRecipes.setRecipe(ModBlocks.ore_nether_coal, ItemStackUtil.itemStackFrom(ModItems.coal_infernal, 2));
		ShredderRecipes.setRecipe(ModBlocks.ore_cinnebar, ItemStackUtil.itemStackFrom(ModItems.cinnebar, 2));
		
		for(int i = 0; i < 16; i++) {
			ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(Blocks.STAINED_HARDENED_CLAY, 1, i), ItemStackUtil.itemStackFrom(Items.CLAY_BALL, 4));
			ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(Blocks.WOOL, 1, i), ItemStackUtil.itemStackFrom(Items.STRING, 4));
		}

		for(final Integer oreMeta : BedrockOreRegistry.oreIndexes.keySet()) {
			final int type = ItemBedrockOre.getOutType(oreMeta);
			if(type == 0 || type == 1){
				ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 2, oreMeta));
				ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_cleaned, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 2, oreMeta));
				ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_deepcleaned, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 2, oreMeta));
				ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_nitrated, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 2, oreMeta));
				ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_seared, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 2, oreMeta));
				ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_perfect, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 2, oreMeta));
				ShredderRecipes.setRecipe(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), ItemBedrockOre.getOut(oreMeta, 2));
			}
		}
		setRecipe("crystalEnder", "dustEnder");
		setRecipe("crystalFluix", "dustFluix");
		setRecipe("crystalCertusQuartz", "dustCertusQuartz");
		setRecipe("enderpearl", "dustEnderPearl");
	}
	
	public static ItemStack getDustByName(final String name) {
		
		return getOredictByName("dust" + name);
	}

	public static ItemStack getTinyDustByName(final String name) {
		
		return getOredictByName("dustTiny" + name);
	}

	public static ItemStack getOredictByName(final String name) {
		
		final List<ItemStack> matches = OreDictionary.getOres(name);
		if(matches != null && !matches.isEmpty())
			return matches.get(0).copy();
		
		return ItemStackUtil.itemStackFrom(ModItems.scrap);
	}
	
	public static void setRecipe(final Item in, final ItemStack out) {
		
		shredderRecipes.put(ItemStackUtil.comparableStackFrom(in), out);
	}
	
	public static void setRecipe(final Block in, final ItemStack out) {
		
		shredderRecipes.put(ItemStackUtil.comparableStackFrom(in), out);
	}
	
	public static void setRecipe(final ItemStack in, final ItemStack out) {
		
		shredderRecipes.put(ItemStackUtil.comparableStackFrom(in), out);
	}

	public static void setRecipe(final String in, final String out) {
		if(OreDictionary.doesOreNameExist(in) && OreDictionary.doesOreNameExist(out)) 
			setRecipe(getOredictByName(in), getOredictByName(out));
	}

	public static void removeRecipe(final ItemStack in) {
		
		shredderRecipes.remove(ItemStackUtil.comparableStackFrom(in));
	}
	
	public static List<ShredderRecipe> getShredderRecipes() {
		
		if(jeiShredderRecipes == null){
			jeiShredderRecipes = new ArrayList<ShredderRecipe>();
			for(final Entry<ComparableStack, ItemStack> e : shredderRecipes.entrySet()){
				jeiShredderRecipes.add(new ShredderRecipe(e.getKey().toStack(), e.getValue()));
			}
		}
		
		return jeiShredderRecipes;
	}
	
	public static ItemStack getShredderResult(final ItemStack stack) {
		
		if(stack == null || stack.getItem() == null || stack.isEmpty())
			return ItemStackUtil.itemStackFrom(ModItems.scrap);
		
		final ItemStack sta = shredderRecipes.get(ItemStackUtil.comparableStackFrom(stack).makeSingular());
		
		return sta == null ? ItemStackUtil.itemStackFrom(ModItems.scrap) : sta;
	}
	
	public static class ShredderRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final ItemStack output;
		
		public ShredderRecipe(final ItemStack input, final ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
}
