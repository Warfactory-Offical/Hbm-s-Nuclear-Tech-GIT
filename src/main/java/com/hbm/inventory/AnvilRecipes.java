package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import static com.hbm.inventory.OreDictManager.CU;
import static com.hbm.inventory.OreDictManager.IRON;
import static com.hbm.inventory.OreDictManager.STEEL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.hbm.inventory.OreDictManager.*;
import static net.minecraft.item.ItemStack.areItemStacksEqual;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.items.ModItems;

import crafttweaker.CraftTweakerAPI;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AnvilRecipes {

	private static final List<AnvilSmithingRecipe> smithingRecipes = new ArrayList<>();
	private static final List<AnvilConstructionRecipe> constructionRecipes = new ArrayList<>();
	
	public static void register() {
		registerSmithing();
		registerConstruction();
	}
	
	/*
	 *      //////  //      //  //  //////  //  //  //  //    //  //////
	 *     //      ////  ////  //    //    //  //  //  ////  //  //
	 *    //////  //  //  //  //    //    //////  //  //  ////  //  //
	 *       //  //      //  //    //    //  //  //  //    //  //  //
	 *  //////  //      //  //    //    //  //  //  //    //  //////
	 */
	public static void registerSmithing() {
		
		final Block[] anvils = new Block[]{ModBlocks.anvil_iron, ModBlocks.anvil_lead};
		
		for(final Block anvil : anvils) {
			smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModBlocks.anvil_bismuth, 1), ItemStackUtil.comparableStackFrom(anvil), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BISMUTH), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModBlocks.anvil_dnt, 1), ItemStackUtil.comparableStackFrom(anvil), new OreDictStack(DNT.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModBlocks.anvil_osmiridium, 1), ItemStackUtil.comparableStackFrom(anvil), new OreDictStack(OSMIRIDIUM.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModBlocks.anvil_ferrouranium, 1), ItemStackUtil.comparableStackFrom(anvil), new OreDictStack(FERRO.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModBlocks.anvil_meteorite, 1), ItemStackUtil.comparableStackFrom(anvil), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModBlocks.anvil_schrabidate, 1), ItemStackUtil.comparableStackFrom(anvil), new OreDictStack(SBD.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModBlocks.anvil_starmetal, 1), ItemStackUtil.comparableStackFrom(anvil), new OreDictStack(STAR.ingot(), 10)));
			smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModBlocks.anvil_steel, 1), ItemStackUtil.comparableStackFrom(anvil), new OreDictStack(STEEL.ingot(), 10)));
		}
		
		for(int i = 0; i < 9; i++)
			smithingRecipes.add(new AnvilSmithingHotRecipe(3, ItemStackUtil.itemStackFrom(ModItems.ingot_steel_dusted, 1, i + 1),
					ItemStackUtil.comparableStackFrom(ModItems.ingot_steel_dusted, 1, i), ItemStackUtil.comparableStackFrom(ModItems.ingot_steel_dusted, 1, i)));
		
		smithingRecipes.add(new AnvilSmithingHotRecipe(3, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL), 1),
				ItemStackUtil.comparableStackFrom(ModItems.ingot_steel_dusted, 1, 9), ItemStackUtil.comparableStackFrom(ModItems.ingot_steel_dusted, 1, 9)));
		
		smithingRecipes.add(new AnvilSmithingHotRecipe(3, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 1), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE)), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE))));
		smithingRecipes.add(new AnvilSmithingHotRecipe(3, ItemStackUtil.itemStackFrom(ModItems.blade_meteorite, 1), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED)), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED))));
		smithingRecipes.add(new AnvilSmithingHotRecipe(3, ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_reforged, 1), ItemStackUtil.comparableStackFrom(ModItems.meteorite_sword_seared), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED))));
		smithingRecipes.add(new AnvilSmithingRecipe(1, ItemStackUtil.itemStackFrom(ModItems.gun_ar15, 1), ItemStackUtil.comparableStackFrom(ModItems.gun_thompson), ItemStackUtil.comparableStackFrom(ModItems.pipe_lead)));
		smithingRecipes.add(new AnvilSmithingRecipe(1916169, ItemStackUtil.itemStackFrom(ModItems.wings_murk, 1), ItemStackUtil.comparableStackFrom(ModItems.wings_limp), ItemStackUtil.comparableStackFrom(ModItems.particle_tachyon)));

		smithingRecipes.add(new AnvilSmithingCyanideRecipe());
		smithingRecipes.add(new AnvilSmithingRenameRecipe());
	}
	
	/*
	 *      //////  //////  //    //  //////  //////  ////    //  //  //////  //////  //  //////  //    //
	 *     //      //  //  ////  //  //        //    //  //  //  //  //        //    //  //  //  ////  //
	 *    //      //  //  //  ////  //////    //    ////    //  //  //        //    //  //  //  //  ////
	 *   //      //  //  //    //      //    //    //  //  //  //  //        //    //  //  //  //    //
	 *  //////  //////  //    //  //////    //    //  //  //////  //////    //    //  //////  //    //
	 */
	public static void registerConstruction() {
		
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(IRON.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_iron))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(GOLD.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_gold))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(TI.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_titanium))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(AL.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_aluminium))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(STEEL.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_steel))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(PB.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_lead))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_copper))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(ALLOY.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_advanced_alloy))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(SA326.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_schrabidium))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CMB.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_combine_steel))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(BIGMT.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_saturnite))).setTier(3));

		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(AL.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.ALUMINIUM), 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.COPPER), 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(W.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.TUNGSTEN), 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(MINGRADE.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.RED_COPPER), 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(ALLOY.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.ADVANCED_ALLOY), 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(GOLD.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(SA326.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.SCHRABIDIUM), 8))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(MAGTUNG.ingot()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN), 8))).setTier(4));

		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(COAL.dust()), new AnvilOutput(ItemStackUtil.itemStackFrom(Items.COAL))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(NETHERQUARTZ.dust()), new AnvilOutput(ItemStackUtil.itemStackFrom(Items.QUARTZ))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(LAPIS.dust()), new AnvilOutput(ItemStackUtil.itemStackFrom(Items.DYE, 1, 4))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(DIAMOND.dust()), new AnvilOutput(ItemStackUtil.itemStackFrom(Items.DIAMOND))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(EMERALD.dust()), new AnvilOutput(ItemStackUtil.itemStackFrom(Items.EMERALD))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new OreDictStack(RAREEARTH.gem()),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_boron)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_boron), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_cobalt)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_cobalt), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_neodymium), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_niobium), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_cerium), 0.4F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_lanthanium), 0.3F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.fragment_actinium), 0.3F)
						
				}
		).setTier(2));

		registerConstructionRecipes();
		registerConstructionAmmo();
		registerConstructionUpgrades();
		registerConstructionRecycling();
	}
	
	public static void registerConstructionRecipes() {

		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(AL.ingot(), 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.deco_aluminium))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(BE.ingot(), 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.deco_beryllium))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(PB.ingot(), 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.deco_lead))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(MINGRADE.ingot(), 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.deco_red_copper))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(STEEL.ingot(), 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.deco_steel))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(TI.ingot(), 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.deco_titanium))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(W.ingot(), 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.deco_tungsten))).setTier(3).setOverlay(OverlayType.CONSTRUCTION));

		constructionRecipes.add(new AnvilConstructionRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.deco_aluminium, 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM)))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.deco_beryllium, 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BERYLLIUM)))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.deco_lead, 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD)))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.deco_red_copper, 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER)))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.deco_steel, 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL)))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.deco_titanium, 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM)))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(ItemStackUtil.comparableStackFrom(ModBlocks.deco_tungsten, 1), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN)))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(DNT.ingot(), 4), ItemStackUtil.comparableStackFrom(ModBlocks.depth_brick)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.depth_dnt))).setTier(1916169));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new OreDictStack(CU.plate(), 4),
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.board_copper))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.coil_copper, 2),
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.coil_copper_torus))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.coil_advanced_alloy, 2),
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.coil_advanced_torus))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.coil_gold, 2),
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.coil_gold_torus))).setTier(1).setOverlay(OverlayType.CONSTRUCTION));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(IRON.plate(), 2), ItemStackUtil.comparableStackFrom(ModItems.coil_copper), ItemStackUtil.comparableStackFrom(ModItems.coil_copper_torus)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.motor, 2))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.motor), new OreDictStack(ANY_PLASTIC.ingot(), 2), new OreDictStack(DESH.ingot(), 2), ItemStackUtil.comparableStackFrom(ModItems.coil_gold_torus)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.motor_desh, 1))).setTier(3));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(Blocks.STONEBRICK, 4),
						ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.FIREBRICK), 4),
						ItemStackUtil.comparableStackFrom(ModItems.board_copper, 2)
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.machine_difurnace_off))).setTier(1));
		
		final int ukModifier = 1;
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack("blockGlassColorless", 4 * ukModifier),
						new OreDictStack(STEEL.ingot(), 8 * ukModifier),
						new OreDictStack(CU.ingot(), 8 * ukModifier),
						ItemStackUtil.comparableStackFrom(ModItems.motor, 2 * ukModifier),
						ItemStackUtil.comparableStackFrom(ModItems.circuit_aluminium, ukModifier)
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.machine_assembler))).setTier(2));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(Blocks.FURNACE),
						new OreDictStack(STEEL.plate(), 8),
						new OreDictStack(CU.ingot(), 8)
				}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.heater_firebox))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[]{
						ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.FIREBRICK), 16),
						new OreDictStack(STEEL.plate(), 4),
						new OreDictStack(CU.ingot(), 8),
				} ,new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.heater_oven))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[]{
						ItemStackUtil.comparableStackFrom(ModItems.tank_steel, 4),
						ItemStackUtil.comparableStackFrom(ModItems.pipes_steel, 1),
						new OreDictStack(TI.ingot(), 12),
						new OreDictStack(CU.ingot(), 8)
				} ,new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.heater_oilburner))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(ANY_PLASTIC.ingot(), 4),
						new OreDictStack(CU.ingot(), 8),
						new OreDictStack(STEEL.plate(), 8),
						ItemStackUtil.comparableStackFrom(ModItems.coil_tungsten, 8),
						ItemStackUtil.comparableStackFrom(ModItems.circuit_copper, 1)
				}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.heater_electric))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(RUBBER.ingot(), 4),
						new OreDictStack(CU.ingot(), 16),
						new OreDictStack(STEEL.plate(), 16),
						ItemStackUtil.comparableStackFrom(ModItems.pipes_steel, 1),
				}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.heater_heatex))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(ModItems.rtg_unit, 5),
						new OreDictStack(getReflector(), 8),
						new OreDictStack(CU.ingot(), 16),
						new OreDictStack(TCALLOY.ingot(), 6),
						new OreDictStack(STEEL.plate(), 8),
				}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.heater_rt))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(Blocks.STONEBRICK, 16),
						new OreDictStack(IRON.ingot(), 4),
						new OreDictStack(STEEL.plate(), 16),
						new OreDictStack(CU.ingot(), 8),
						ItemStackUtil.comparableStackFrom(ModBlocks.steel_grate, 16)
				}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.furnace_steel))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(STEEL.ingot(), 4),
						new OreDictStack(CU.plate(), 16),
						ItemStackUtil.comparableStackFrom(ModItems.plate_polymer, 8)
				}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.heat_boiler))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(ModBlocks.brick_concrete, 64),
						ItemStackUtil.comparableStackFrom(Blocks.IRON_BARS, 128),
						ItemStackUtil.comparableStackFrom(ModBlocks.machine_condenser, 5),
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.machine_tower_small))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(ModBlocks.concrete_smooth, 128),
						ItemStackUtil.comparableStackFrom(ModBlocks.steel_scaffold, 32),
						ItemStackUtil.comparableStackFrom(ModBlocks.machine_condenser, 25),
						ItemStackUtil.comparableStackFrom(ModItems.pipes_steel, 2)
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.machine_tower_large))).setTier(4));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(ANY_CONCRETE.any(), 2),
						ItemStackUtil.comparableStackFrom(ModBlocks.steel_scaffold, 8),
						ItemStackUtil.comparableStackFrom(ModItems.plate_polymer, 8),
						ItemStackUtil.comparableStackFrom(ModItems.coil_copper, 4)
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.red_pylon_large))).setTier(2));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(ANY_CONCRETE.any(), 6),
						new OreDictStack(STEEL.ingot(), 4),
						ItemStackUtil.comparableStackFrom(ModBlocks.steel_scaffold, 2),
						ItemStackUtil.comparableStackFrom(ModItems.plate_polymer, 8),
						ItemStackUtil.comparableStackFrom(ModItems.coil_copper, 2),
						ItemStackUtil.comparableStackFrom(ModItems.coil_copper_torus, 2)
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.substation))).setTier(2));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(ModBlocks.steel_wall, 2),
						new OreDictStack(REDSTONE.dust(), 4),
						ItemStackUtil.comparableStackFrom(Blocks.LEVER, 2),
						ItemStackUtil.comparableStackFrom(ModItems.wire.getItemStack(MaterialMineral.ADVANCED_ALLOY), 3)
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.bm_power_box))).setTier(5));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(Items.BONE, 16),
						ItemStackUtil.comparableStackFrom(Items.LEATHER, 4),
						ItemStackUtil.comparableStackFrom(Items.FEATHER, 24)
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wings_limp))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						new OreDictStack(S.dust(), 8),
						new OreDictStack(STEEL.plate(), 4),
						new OreDictStack(AL.plate(), 2),
						ItemStackUtil.comparableStackFrom(ModItems.hull_small_steel, 4),
						ItemStackUtil.comparableStackFrom(ModItems.board_copper, 1),
						ItemStackUtil.comparableStackFrom(ModItems.turbine_titanium, 1),
						ItemStackUtil.comparableStackFrom(ModItems.circuit_aluminium, 1)
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.machine_deuterium_extractor))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {
						ItemStackUtil.comparableStackFrom(ModItems.deuterium_filter, 2),
						ItemStackUtil.comparableStackFrom(ModItems.hull_big_steel, 5),
						ItemStackUtil.comparableStackFrom(ModBlocks.concrete_smooth, 8),
						ItemStackUtil.comparableStackFrom(ModBlocks.concrete_asbestos, 4),
						ItemStackUtil.comparableStackFrom(ModBlocks.steel_scaffold, 16),
						ItemStackUtil.comparableStackFrom(ModBlocks.deco_pipe_quad, 12),
						new OreDictStack(S.dust(), 32),
				},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.machine_deuterium_tower))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(DESH.ingot(), 4), new OreDictStack(ANY_PLASTIC.dust(), 2), new OreDictStack(DURA.ingot(), 1)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_desh, 4))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(EUPH.ingot(), 4), new OreDictStack(AT.ingot(), 3), new OreDictStack(ANY_BISMOID.dust(), 1), new OreDictStack(VOLCANIC.gem(), 1), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.OSMIRIDIUM))},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_euphemium, 4))).setTier(6));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(DNT.ingot(), 4), ItemStackUtil.comparableStackFrom(ModItems.powder_spark_mix, 2), new OreDictStack(DESH.ingot(), 1)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_dineutronium, 4))).setTier(7));

		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack("plateTitanium", 2), new OreDictStack(STEEL.ingot(), 1), ItemStackUtil.comparableStackFrom(ModItems.bolt_tungsten, 2)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_armor_titanium))).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(IRON.plate(), 4), new OreDictStack(BIGMT.plate(), 2), ItemStackUtil.comparableStackFrom(ModItems.plate_armor_titanium, 1)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_armor_ajr))).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.plate_paa, 2), ItemStackUtil.comparableStackFrom(ModItems.plate_armor_ajr, 1), ItemStackUtil.comparableStackFrom(ModItems.wire.getItemStack(MaterialMineral.TUNGSTEN), 4)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_armor_hev))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {new OreDictStack(OreDictManager.getReflector(), 2), ItemStackUtil.comparableStackFrom(ModItems.plate_armor_hev, 1), ItemStackUtil.comparableStackFrom(ModItems.wire.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN), 4)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_armor_lunar))).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 4), new OreDictStack(DESH.ingot(), 1), ItemStackUtil.comparableStackFrom(ModItems.billet.getItemStack(MaterialMineral.YHARONITE), 1)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_armor_fau))).setTier(6));
		constructionRecipes.add(new AnvilConstructionRecipe(
				new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.plate_dineutronium, 4), ItemStackUtil.comparableStackFrom(ModItems.particle_sparkticle, 1), ItemStackUtil.comparableStackFrom(ModItems.plate_armor_fau, 6)},
				new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_armor_dnt))).setTier(7));
		
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.plate_mixed, 4), 3);
		
	}
	
	public static void registerConstructionAmmo() {
		
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.casing_357))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.casing_44))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.casing_9))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.casing_50))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new OreDictStack(CU.plate()), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.casing_buckshot))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), ItemStackUtil.comparableStackFrom(Items.REDSTONE)}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.primer_357))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), ItemStackUtil.comparableStackFrom(Items.REDSTONE)}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.primer_44))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), ItemStackUtil.comparableStackFrom(Items.REDSTONE)}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.primer_9))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), ItemStackUtil.comparableStackFrom(Items.REDSTONE)}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.primer_50))).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] {new OreDictStack(IRON.plate()), ItemStackUtil.comparableStackFrom(Items.REDSTONE)}, new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.primer_buckshot))).setTier(1));
		
		final Object[][] recs = new Object[][] {
			{ModItems.ammo_12gauge,	ModItems.powder_fire,								ModItems.ammo_12gauge_incendiary,	20,		2},
			{ModItems.ammo_12gauge,	Item.getItemFromBlock(ModBlocks.gravel_obsidian),	ModItems.ammo_12gauge_shrapnel,		20,		2},
			{ModItems.ammo_12gauge, ModItems.ingot.getItemStack(MaterialMineral.U238),								ModItems.ammo_12gauge_du,			20,		3},
			{ModItems.ammo_12gauge,	ModItems.coin_maskman,								ModItems.ammo_12gauge_sleek,		100,	4},
			
			{ModItems.ammo_20gauge,	ModItems.powder_fire,								ModItems.ammo_20gauge_incendiary,	20,		2},
			{ModItems.ammo_20gauge,	Item.getItemFromBlock(ModBlocks.gravel_obsidian),	ModItems.ammo_20gauge_shrapnel,		20,		2},
			{ModItems.ammo_20gauge,	ModItems.powder_poison,								ModItems.ammo_20gauge_caustic,		20,		2},
			{ModItems.ammo_20gauge,	DIAMOND.dust(),										ModItems.ammo_20gauge_shock,		20,		2},
			{ModItems.ammo_20gauge,	Item.getItemFromBlock(Blocks.SOUL_SAND),			ModItems.ammo_20gauge_wither,		10,		3},
			{ModItems.ammo_20gauge,	ModItems.coin_maskman,								ModItems.ammo_20gauge_sleek,		100,	4},

			{ModItems.ammo_4gauge_flechette, ModItems.ingot.getItemStack(MaterialMineral.PHOSPHORUS),		ModItems.ammo_4gauge_flechette_phosphorus,	20,		2},
			{ModItems.ammo_4gauge_explosive,	ModItems.egg_balefire_shard,	ModItems.ammo_4gauge_balefire,				10,		4},
			{ModItems.ammo_4gauge_explosive,	ModItems.ammo_rocket,			ModItems.ammo_4gauge_kampf,					4,		2},
			{ModItems.ammo_4gauge_kampf,		ModItems.pellet_canister,		ModItems.ammo_4gauge_canister,				10,		3},
			{ModItems.ammo_4gauge,				ModItems.pellet_claws,			ModItems.ammo_4gauge_claw,					4,		5},
			{ModItems.ammo_4gauge,				ModItems.toothpicks,			ModItems.ammo_4gauge_vampire,				4,		5},
			{ModItems.ammo_4gauge,				ModItems.pellet_charged,		ModItems.ammo_4gauge_void,					1,		5},
			{ModItems.ammo_4gauge,				ModItems.coin_maskman,			ModItems.ammo_4gauge_sleek,					100,	4},

			{ModItems.ammo_44, ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL),		ModItems.ammo_44_ap,			20,	2},
			{ModItems.ammo_44, ModItems.ingot.getItemStack(MaterialMineral.U238),			ModItems.ammo_44_du,			20,	2},
			{ModItems.ammo_44, ModItems.ingot.getItemStack(MaterialMineral.PHOSPHORUS),		ModItems.ammo_44_phosphorus,	20,	2},
			{ModItems.ammo_44_du, ModItems.ingot.getItemStack(MaterialMineral.STARMETAL),		ModItems.ammo_44_star,			10,	3},
			{ModItems.ammo_44,		ModItems.pellet_chlorophyte,	ModItems.ammo_44_chlorophyte,	10,	3},

			{ModItems.ammo_5mm,	ModItems.ingot_semtex,			ModItems.ammo_5mm_explosive,	20,	2},
			{ModItems.ammo_5mm, ModItems.ingot.getItemStack(MaterialMineral.U238),			ModItems.ammo_5mm_du,			20,	2},
			{ModItems.ammo_5mm, ModItems.ingot.getItemStack(MaterialMineral.STARMETAL),		ModItems.ammo_5mm_star,			10,	3},
			{ModItems.ammo_5mm,	ModItems.pellet_chlorophyte,	ModItems.ammo_5mm_chlorophyte,	10,	3},

			{ModItems.ammo_9mm, ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL),		ModItems.ammo_9mm_ap,			20,	2},
			{ModItems.ammo_9mm, ModItems.ingot.getItemStack(MaterialMineral.U238),			ModItems.ammo_9mm_du,			20,	2},
			{ModItems.ammo_9mm,	ModItems.pellet_chlorophyte,	ModItems.ammo_9mm_chlorophyte,	10,	3},
			
			{ModItems.ammo_22lr, ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL),		ModItems.ammo_22lr_ap,			20,	2},
			{ModItems.ammo_22lr,	ModItems.pellet_chlorophyte,	ModItems.ammo_22lr_chlorophyte,	10,	3},

			{ModItems.ammo_50bmg,			ModItems.powder_fire,			ModItems.ammo_50bmg_incendiary,		20,		2},
			{ModItems.ammo_50bmg, ModItems.ingot.getItemStack(MaterialMineral.PHOSPHORUS),		ModItems.ammo_50bmg_phosphorus,		20,		2},
			{ModItems.ammo_50bmg,			ModItems.ingot_semtex,			ModItems.ammo_50bmg_explosive,		20,		2},
			{ModItems.ammo_50bmg, ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL),		ModItems.ammo_50bmg_ap,				20,		2},
			{ModItems.ammo_50bmg, ModItems.ingot.getItemStack(MaterialMineral.U238),			ModItems.ammo_50bmg_du,				20,		2},
			{ModItems.ammo_50bmg_du, ModItems.ingot.getItemStack(MaterialMineral.STARMETAL),		ModItems.ammo_50bmg_star,			10,		3},
			{ModItems.ammo_50bmg,			ModItems.pellet_chlorophyte,	ModItems.ammo_50bmg_chlorophyte,	10,		3},
			{ModItems.ammo_50bmg,			ModItems.coin_maskman,			ModItems.ammo_50bmg_sleek,			100,	4},
			{ModItems.ammo_50bmg,			ModItems.pellet_flechette,		ModItems.ammo_50bmg_flechette,		20,		2},
			{ModItems.ammo_50bmg_flechette, ModItems.nugget.getItemStack(MaterialMineral.AM_MIX),			ModItems.ammo_50bmg_flechette_am,	10,		3},
			{ModItems.ammo_50bmg_flechette,	ModItems.powder_polonium,		ModItems.ammo_50bmg_flechette_po,	20,		3},

			{ModItems.ammo_50ae, ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL),		ModItems.ammo_50ae_ap,			20,	2},
			{ModItems.ammo_50ae, ModItems.ingot.getItemStack(MaterialMineral.U238),			ModItems.ammo_50ae_du,			20,	2},
			{ModItems.ammo_50ae_du, ModItems.ingot.getItemStack(MaterialMineral.STARMETAL),		ModItems.ammo_50ae_star,		10,	3},
			{ModItems.ammo_50ae,	ModItems.pellet_chlorophyte,	ModItems.ammo_50ae_chlorophyte,	10,	3},

			{ModItems.ammo_556, ModItems.ingot.getItemStack(MaterialMineral.PHOSPHORUS),		ModItems.ammo_556_phosphorus,				20,		2},
			{ModItems.ammo_556, ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL),		ModItems.ammo_556_ap,						20,		2},
			{ModItems.ammo_556, ModItems.ingot.getItemStack(MaterialMineral.U238),			ModItems.ammo_556_du,						20,		2},
			{ModItems.ammo_556_du, ModItems.ingot.getItemStack(MaterialMineral.STARMETAL),		ModItems.ammo_556_star,						10,		3},
			{ModItems.ammo_556,				ModItems.pellet_chlorophyte,	ModItems.ammo_556_chlorophyte,				10,		3},
			{ModItems.ammo_556,				ModItems.coin_maskman,			ModItems.ammo_556_sleek,					100,	4},
			{ModItems.ammo_556,				Items.REDSTONE,					ModItems.ammo_556_tracer,					20,		2},
			{ModItems.ammo_556,				ModItems.pellet_flechette,		ModItems.ammo_556_flechette,				20,		2},
			{ModItems.ammo_556_flechette,	ModItems.powder_fire,			ModItems.ammo_556_flechette_incendiary,		20,		2},
			{ModItems.ammo_556_flechette, ModItems.ingot.getItemStack(MaterialMineral.PHOSPHORUS),		ModItems.ammo_556_flechette_phosphorus,		20,		2},
			{ModItems.ammo_556_flechette, ModItems.ingot.getItemStack(MaterialMineral.U238),			ModItems.ammo_556_flechette_du,				20,		2},
			{ModItems.ammo_556_flechette,	ModItems.coin_maskman,			ModItems.ammo_556_flechette_sleek,			100,	4},
			{ModItems.ammo_556_flechette,	ModItems.pellet_chlorophyte,	ModItems.ammo_556_flechette_chlorophyte,	10,		3},
		};
		
		for(final Object[] objs : recs) {
			
			if(objs[1] instanceof Item) {
				constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] { ItemStackUtil.comparableStackFrom((Item)objs[0], (int)objs[3]), ItemStackUtil.comparableStackFrom((Item)objs[1], 1) },
						new AnvilOutput(ItemStackUtil.itemStackFrom((Item)objs[2], (int)objs[3]))).setTier((int)objs[4]));
				
			} else if(objs[1] instanceof String) {
				constructionRecipes.add(new AnvilConstructionRecipe(new AStack[] { ItemStackUtil.comparableStackFrom((Item)objs[0], (int)objs[3]), new OreDictStack((String)objs[1], 1) },
						new AnvilOutput(ItemStackUtil.itemStackFrom((Item)objs[2], (int)objs[3]))).setTier((int)objs[4]));
			}
		}
	}
	
	public static void registerConstructionUpgrades() {
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_template), 2);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_speed_1), 2);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_speed_2), 3);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_speed_3), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_power_1), 2);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_power_2), 3);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_power_3), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_effect_1), 2);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_effect_2), 3);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_effect_3), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_fortune_1), 2);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_fortune_2), 3);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_fortune_3), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_afterburn_1), 2);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_afterburn_2), 3);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_afterburn_3), 4);

		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_radius), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_health), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_smelter), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_shredder), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_centrifuge), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_crystallizer), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_nullifier), 4);
		pullFromAssembler(ItemStackUtil.comparableStackFrom(ModItems.upgrade_screm), 4);
	}

	public static void registerConstructionRecycling() {
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.solar_mirror),
				new AnvilOutput[] {new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL), 1)), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_aluminium, 1))}).setTier(3));

		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.barrel_tcalloy),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TCALLOY), 4)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TCALLOY), 1), 0.50F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TCALLOY), 1), 0.25F)
				}
		).setTier(3));

		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_raw),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.ALUMINIUM), 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(Items.REDSTONE, 1))
				}
		).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_aluminium),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.ALUMINIUM), 1), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(Items.REDSTONE, 1), 0.25F)
				}
		).setTier(1));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_copper),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.circuit_aluminium, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.COPPER), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.COPPER), 1), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.COPPER), 1), 0.25F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.powder_quartz, 1), 0.25F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_copper, 1), 0.5F)
				}
		).setTier(2));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_red_copper),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.circuit_copper, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.RED_COPPER), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.RED_COPPER), 1), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.RED_COPPER), 1), 0.25F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.powder_gold, 1), 0.25F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 1), 0.5F)
				}
		).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_gold),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.circuit_red_copper, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 1), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 1), 0.25F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.powder_lapis, 1), 0.25F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLYMER), 1), 0.5F)
				}
		).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_schrabidium),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.circuit_gold, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.SCHRABIDIUM), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.SCHRABIDIUM), 1), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.SCHRABIDIUM), 1), 0.25F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 1), 0.25F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DESH), 1), 0.5F)
				}
		).setTier(6));

		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_tantalium_raw),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(Items.REDSTONE, 4)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_copper, 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TANTALIUM), 1))
				}
		).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_tantalium),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(Items.REDSTONE, 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 1), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_copper, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TANTALIUM), 1), 0.75F)
				}
		).setTier(4));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_bismuth_raw),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(Items.REDSTONE, 4)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLYMER), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ASBESTOS), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BISMUTH), 1))
				}
		).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.circuit_bismuth),
				new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(Items.REDSTONE, 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLYMER), 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLYMER), 1), 0.5F),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ASBESTOS), 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BISMUTH), 1), 0.75F)
				}
		).setTier(4));

		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.pile_rod_uranium),
				new AnvilOutput[] {new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM), 3)), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_iron, 2))}).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.pile_rod_plutonium),
				new AnvilOutput[] {new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM), 3)), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_iron, 2))}).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.pile_rod_source),
				new AnvilOutput[] {new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.RA226BE), 3)), new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_iron, 2))}).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModItems.pile_rod_boron),
				new AnvilOutput[] {new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BORON), 2)), new AnvilOutput(ItemStackUtil.itemStackFrom(Items.STICK, 2))}).setTier(3));

		//RBMK
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_moderator), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.block_graphite, 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_absorber), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BORON), 8))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_reflector), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 8))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_control), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_absorber, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.GRAPHITE), 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.motor, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_control_mod), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_control, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.block_graphite, 4)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.BISMUTH), 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_control_auto), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_control, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier1, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_rod_reasim), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ZIRCONIUM), 4)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.hull_small_steel, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_rod_reasim_mod), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_rod_reasim, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.block_graphite, 4)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TCALLOY), 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_outgasser), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.steel_grate, 6)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.tank_steel, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(Blocks.HOPPER, 1))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_storage), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.crate_steel, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
					ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_rod), new AnvilOutput[] {
							new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
							new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.hull_small_steel, 2))
					}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_rod_mod), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_rod, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.block_graphite, 4)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.BISMUTH), 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_boiler), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.board_copper, 6)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.pipes_steel, 2))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_cooler), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.steel_grate, 4)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 4))
				}).setTier(4));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.rbmk_heater), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_blank, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.pipes_steel, 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 2))
				}).setTier(4));

		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.machine_turbine), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.turbine_titanium, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.coil_copper, 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL), 4))
						}).setTier(3));
		
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.yellow_barrel), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.tank_steel, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_lead, 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.nuclear_waste, 9))
				}).setTier(3));
		constructionRecipes.add(new AnvilConstructionRecipe(
				ItemStackUtil.comparableStackFrom(ModBlocks.vitrified_barrel), new AnvilOutput[] {
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.tank_steel, 1)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.plate_lead, 2)),
						new AnvilOutput(ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_vitrified, 9))
				}).setTier(3));
	}
	
	public static void pullFromAssembler(final ComparableStack result, final int tier) {
		
		final AStack[] ingredients = AssemblerRecipes.recipes.get(result);
		
		if(ingredients != null) {
			constructionRecipes.add(new AnvilConstructionRecipe(ingredients, new AnvilOutput(result.toStack())).setTier(tier));
		}
	}
	
	public static List<AnvilSmithingRecipe> getSmithing() {
		return smithingRecipes;
	}
	
	public static List<AnvilConstructionRecipe> getConstruction() {
		return constructionRecipes;
	}

	public static boolean removeConstructionRecipe(final ItemStack[] outputs) {
		start:
		for(final AnvilConstructionRecipe constructionRecipe : constructionRecipes) {
			// check length same
			if(constructionRecipe.output.size() != outputs.length) continue;
			// check outputs same
			for(int i = 0; i < outputs.length; i++) {
				if(!areItemStacksEqual(constructionRecipe.output.get(i).stack,outputs[i])){
					continue start;
				}
			}
			CraftTweakerAPI.logInfo("remove anvil recipe"+ constructionRecipe );
			constructionRecipes.remove(constructionRecipe);
			return true;

		}
		return false;
	}

	public static boolean removeConstructionRecipeByInput(final AStack[] inputs) {
		start:
		for(final AnvilConstructionRecipe constructionRecipe : constructionRecipes) {
			// check length same
			if(constructionRecipe.input.size() != inputs.length) continue;
			// check outputs same
			for(int i = 0; i < inputs.length; i++) {
				if(!inputs[i].equals(constructionRecipe.input.get(i))){
					continue start;
				}
			}
			CraftTweakerAPI.logInfo("remove anvil recipe"+ constructionRecipe );
			constructionRecipes.remove(constructionRecipe);
			return true;
		}
		return false;
	}

	public static void addConstructionRecipe(final AStack[] inputs, final ItemStack[] output, final int tier) {
		final AnvilOutput[] anvilOutputs = new AnvilOutput[output.length];
		for(int i = 0; i < output.length; i++) {
			anvilOutputs[i] = new AnvilOutput(output[i]);
		}
		constructionRecipes.add(new AnvilConstructionRecipe(inputs, anvilOutputs).setTier(tier));
	}

	public static class AnvilConstructionRecipe {
		public List<AStack> input = new ArrayList<>();
		public List<AnvilOutput> output = new ArrayList<>();
		public int tierLower = 0;
		public int tierUpper = -1;
		OverlayType overlay = OverlayType.NONE;
		
		public AnvilConstructionRecipe(final AStack input, final AnvilOutput output) {
			this.input.add(input);
			this.output.add(output);
			this.setOverlay(OverlayType.SMITHING); //preferred overlay for 1:1 conversions is smithing
		}
		
		public AnvilConstructionRecipe(final AStack[] input, final AnvilOutput output) {
            Collections.addAll(this.input, input);
			this.output.add(output);
			this.setOverlay(OverlayType.CONSTRUCTION); //preferred overlay for many:1 conversions is construction
		}
		
		public AnvilConstructionRecipe(final AStack input, final AnvilOutput[] output) {
			this.input.add(input);
            Collections.addAll(this.output, output);
			this.setOverlay(OverlayType.RECYCLING); //preferred overlay for 1:many conversions is recycling
		}
		
		public AnvilConstructionRecipe(final AStack[] input, final AnvilOutput[] output) {
            Collections.addAll(this.input, input);
            Collections.addAll(this.output, output);
			this.setOverlay(OverlayType.NONE); //no preferred overlay for many:many conversions
		}
		
		public AnvilConstructionRecipe setTier(final int tier) {
			this.tierLower = tier;
			if(GeneralConfig.enableBabyMode) this.tierLower = 1;
			return this;
		}
		
		public AnvilConstructionRecipe setTierRange(final int lower, final int upper) {
			this.tierLower = lower;
			this.tierUpper = upper;
			if(GeneralConfig.enableBabyMode) this.tierLower = this.tierUpper = 1;
			return this;
		}
		
		public boolean isTierValid(final int tier) {
			
			if(this.tierUpper == -1)
				return tier >= this.tierLower;
			
			return tier >= this.tierLower && tier <= this.tierUpper;
		}
		
		public AnvilConstructionRecipe setOverlay(final OverlayType overlay) {
			this.overlay = overlay;
			return this;
		}
		
		public OverlayType getOverlay() {
			return this.overlay;
		}
		
		public ItemStack getDisplay() {
			switch(this.overlay) {
			case NONE: return this.output.get(0).stack.copy();
			case CONSTRUCTION: return this.output.get(0).stack.copy();
			case SMITHING: return this.output.get(0).stack.copy();
			case RECYCLING:
				for(final AStack stack : this.input) {
					if(stack instanceof ComparableStack)
						return ((ComparableStack)stack).toStack();
				}
				return this.output.get(0).stack.copy();
			default: return ItemStackUtil.itemStackFrom(Items.IRON_PICKAXE);
			}
		}
	}
	
	public static class AnvilOutput {
		public ItemStack stack;
		public float chance;
		
		public AnvilOutput(final ItemStack stack) {
			this(stack, 1F);
		}
		
		public AnvilOutput(final ItemStack stack, final float chance) {
			this.stack = stack;
			this.chance = chance;
		}
	}
	
	public static enum OverlayType {
		NONE,
		CONSTRUCTION,
		RECYCLING,
		SMITHING
    }
}