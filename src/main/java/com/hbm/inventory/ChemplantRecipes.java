package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.hbm.inventory.OreDictManager.*;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.config.MachineConfig;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.Spaghetti;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemChemistryTemplate;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

//TODO: clean this shit up
@Spaghetti("everything")
public class ChemplantRecipes {

	public static HashMap<Integer, AStack[]> recipeItemInputs = new HashMap<>();
	public static HashMap<Integer, FluidStack[]> recipeFluidInputs = new HashMap<>();
	public static HashMap<Integer, AStack[]> recipeItemOutputs = new HashMap<>();
	public static HashMap<Integer, FluidStack[]> recipeFluidOutputs = new HashMap<>();
	public static HashMap<Integer, Integer> recipeDurations = new HashMap<>();
	public static LinkedHashMap<Integer, String> recipeNames = new LinkedHashMap<>();

	public static void registerRecipes() {
		if(MachineConfig.chemplantKeepOilProcessing){
			makeRecipe(0, "FP_HEAVYOIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.heavyoil, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.bitumen, 300), new FluidStack(ModForgeFluids.smear, 700) }, 50);
			
			makeRecipe(10, "FP_SMEAR", null, new FluidStack[]{ new FluidStack(ModForgeFluids.smear, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.heatingoil, 600), new FluidStack(ModForgeFluids.lubricant, 400) }, 50);
			
			makeRecipe(20, "FP_NAPHTHA", null, new FluidStack[]{ new FluidStack(ModForgeFluids.naphtha, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.heatingoil, 400), new FluidStack(ModForgeFluids.diesel, 600) }, 50);
			
			makeRecipe(30, "FP_LIGHTOIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.lightoil, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.diesel, 400), new FluidStack(ModForgeFluids.kerosene, 600) }, 50);
		}
		makeRecipe(40, "FR_REOIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.smear, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.reclaimed, 800) }, 30);
		
		makeRecipe(50, "FR_PETROIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.reclaimed, 800), new FluidStack(ModForgeFluids.lubricant, 200) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.petroil, 1000) }, 30);
		
		makeRecipe(60, "FC_BITUMEN", null, new FluidStack[]{ new FluidStack(ModForgeFluids.bitumen, 1200), new FluidStack(ModForgeFluids.steam, 2400) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.oil, 1000), new FluidStack(ModForgeFluids.petroleum, 200) }, 100);
		
		makeRecipe(70, "FC_I_NAPHTHA", null, new FluidStack[]{ new FluidStack(ModForgeFluids.smear, 1400), new FluidStack(FluidRegistry.WATER, 800) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.naphtha, 800) }, 150);
		
		makeRecipe(80, "FC_GAS_PETROLEUM", null, new FluidStack[]{ new FluidStack(ModForgeFluids.gas, 1800), new FluidStack(FluidRegistry.WATER, 1200) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 800) }, 100);
		
		makeRecipe(90, "FC_DIESEL_KEROSENE", null, new FluidStack[]{ new FluidStack(ModForgeFluids.diesel, 1200), new FluidStack(ModForgeFluids.steam, 2000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.kerosene, 400) }, 150);
		
		makeRecipe(100, "FC_KEROSENE_PETROLEUM", null, new FluidStack[]{ new FluidStack(ModForgeFluids.kerosene, 1400), new FluidStack(ModForgeFluids.steam, 2000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 800) }, 150);
		
		makeRecipe(110, "CC_OIL", new AStack[] { new OreDictStack(COAL.dust(), 8), ItemStackUtil.comparableStackFrom(ModItems.oil_tar, 4) }, new FluidStack[]{ new FluidStack(ModForgeFluids.oil, 600), new FluidStack(ModForgeFluids.steam, 1400) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.oil, 2000) }, 150);
		
		makeRecipe(120, "CC_I", new AStack[] { new OreDictStack(COAL.dust(), 6), ItemStackUtil.comparableStackFrom(ModItems.oil_tar, 4) }, new FluidStack[]{ new FluidStack(ModForgeFluids.smear, 800), new FluidStack(FluidRegistry.WATER, 1800) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.smear, 1600) }, 200);
		
		makeRecipe(130, "CC_HEATING", new AStack[] { new OreDictStack(COAL.dust(), 6), ItemStackUtil.comparableStackFrom(ModItems.oil_tar, 4) }, new FluidStack[]{ new FluidStack(ModForgeFluids.heatingoil, 800), new FluidStack(ModForgeFluids.steam, 2000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.heatingoil, 1800) }, 250);
		
		makeRecipe(140, "CC_HEAVY", new AStack[] { new OreDictStack(COAL.dust(), 8), ItemStackUtil.comparableStackFrom(ModItems.oil_tar, 4) }, new FluidStack[]{ new FluidStack(ModForgeFluids.heavyoil, 600), new FluidStack(FluidRegistry.WATER, 1400) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.heavyoil, 1800) }, 200);
		
		makeRecipe(150, "CC_NAPHTHA", new AStack[] { new OreDictStack(COAL.dust(), 8), ItemStackUtil.comparableStackFrom(ModItems.oil_tar, 4) }, new FluidStack[]{ new FluidStack(ModForgeFluids.naphtha, 1200), new FluidStack(ModForgeFluids.steam, 2400) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.naphtha, 2000) }, 300);
		
		makeRecipe(160, "SF_OIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.oil, 350) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(170, "SF_HEAVYOIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.heavyoil, 250) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.oil_tar, 2) }, null, 20);
		
		makeRecipe(180, "SF_SMEAR", null, new FluidStack[]{ new FluidStack(ModForgeFluids.smear, 200) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.oil_tar, 2) }, null, 20);
		
		makeRecipe(190, "SF_HEATINGOIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.heatingoil, 100) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(200, "SF_RECLAIMED", null, new FluidStack[]{ new FluidStack(ModForgeFluids.reclaimed, 200) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(210, "SF_PETROIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.petroil, 250) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(220, "SF_LUBRICANT", null, new FluidStack[]{ new FluidStack(ModForgeFluids.lubricant, 250) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(230, "SF_NAPHTHA", null, new FluidStack[]{ new FluidStack(ModForgeFluids.naphtha, 300) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(240, "SF_DIESEL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.diesel, 400) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(250, "SF_LIGHTOIL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.lightoil, 450) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(260, "SF_KEROSENE", null, new FluidStack[]{ new FluidStack(ModForgeFluids.kerosene, 550) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(270, "SF_GAS", null, new FluidStack[]{ new FluidStack(ModForgeFluids.gas, 750) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(280, "SF_PETROLEUM", null, new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 600) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(290, "SF_BIOGAS", null, new FluidStack[]{ new FluidStack(ModForgeFluids.biogas, 400) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(300, "SF_BIOFUEL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.biofuel, 300) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2) }, null, 20);
		
		makeRecipe(310, "BP_BIOGAS", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.biomass, 16) }, null, null, new FluidStack[]{ new FluidStack(ModForgeFluids.biogas, 4000) }, 200);
		
		makeRecipe(320, "BP_BIOFUEL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.biogas, 1500), new FluidStack(ModForgeFluids.ethanol, 250) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.biofuel, 1000) }, 100);
		
		makeRecipe(330, "OIL_SAND", new AStack[] { ItemStackUtil.comparableStackFrom(ModBlocks.ore_oil_sand, 16), new OreDictStack(KEY_OIL_TAR, 1) }, null, new AStack[] { ItemStackUtil.comparableStackFrom(Blocks.SAND, 16) }, new FluidStack[]{ new FluidStack(ModForgeFluids.bitumen, 1000) }, 200);
		
		makeRecipe(340, "ASPHALT", new AStack[] { new OreDictStack(KEY_GRAVEL, 2), new OreDictStack(KEY_SAND, 6) }, new FluidStack[]{ new FluidStack(ModForgeFluids.bitumen, 8000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModBlocks.asphalt, 16) }, null, 100);
		
		makeRecipe(350, "GNEISS_GAS", new AStack[] { ItemStackUtil.comparableStackFrom(ModBlocks.ore_gneiss_gas, 2) }, new FluidStack[]{ new FluidStack(ModForgeFluids.superhotsteam, 1000) }, new AStack[]{ ItemStackUtil.comparableStackFrom(ModBlocks.stone_gneiss, 2) }, new FluidStack[]{ new FluidStack(ModForgeFluids.gas, 1000), new FluidStack(ModForgeFluids.spentsteam, 1000) }, 100);
		
		makeRecipe(360, "COOLANT", new AStack[] { new OreDictStack(KNO.dust(), 1) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 1800) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.coolant, 2000) }, 50);
		
		makeRecipe(370, "CRYOGEL", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_ice, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.coolant, 1800) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.cryogel, 2000) }, 50);
		
		if(GeneralConfig.enableBabyMode) {
			makeRecipe(380, "DESH", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_desh_mix, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.lightoil, 200) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DESH), 1) }, null, 300);
		} else {
			makeRecipe(380, "DESH", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_desh_mix, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.mercury, 200), new FluidStack(ModForgeFluids.lightoil, 200) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DESH), 1) }, null, 300);
		}
		
		makeRecipe(390, "NITAN", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_nitan_mix, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.kerosene, 600), new FluidStack(ModForgeFluids.mercury, 200) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.nitan, 1000) }, 50);
		
		makeRecipe(400, "PEROXIDE", null, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.acid, 800) }, 50);
		
		makeRecipe(410, "SULFURIC_ACID", new AStack[] { new OreDictStack(S.dust()) }, new FluidStack[]{ new FluidStack(ModForgeFluids.acid, 800) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.sulfuric_acid, 500) }, 50);
		
		makeRecipe(420, "NITRIC_ACID", new AStack[] { new OreDictStack(KNO.dust()) }, new FluidStack[]{ new FluidStack(ModForgeFluids.sulfuric_acid, 500) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.nitric_acid, 500) }, 50);
		
		makeRecipe(430, "SOLVENT", null, new FluidStack[]{ new FluidStack(ModForgeFluids.naphtha, 800), new FluidStack(ModForgeFluids.aromatics, 300) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.solvent, 1000) }, 50);
		
		makeRecipe(440, "RADIOSOLVENT", null, new FluidStack[]{ new FluidStack(ModForgeFluids.solvent, 500), new FluidStack(ModForgeFluids.watz, 500) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.radiosolvent, 1000) }, 50);
		
		makeRecipe(450, "CIRCUIT_4", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.circuit_red_copper, 1), ItemStackUtil.comparableStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 4), new OreDictStack(LAPIS.dust(), 1), new OreDictStack(ANY_PLASTIC.ingot(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.acid, 400) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.circuit_gold, 1) }, null, 200);
		
		makeRecipe(460, "CIRCUIT_5", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.circuit_gold, 1), ItemStackUtil.comparableStackFrom(ModItems.wire.getItemStack(MaterialMineral.SCHRABIDIUM), 4), new OreDictStack(DIAMOND.dust(), 1), new OreDictStack(DESH.ingot(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.acid, 800), new FluidStack(ModForgeFluids.petroleum, 400) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.circuit_schrabidium, 1) }, null, 250);
		
		makeRecipe(470, "POLYMER", new AStack[] { new OreDictStack(COAL.gem(), 2), new OreDictStack(F.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 600) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLYMER), 1) }, null, 100);
		
		makeRecipe(480, "BAKELITE", null, new FluidStack[]{ new FluidStack(ModForgeFluids.aromatics, 500), new FluidStack(ModForgeFluids.petroleum, 500) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BAKELITE), 1) }, null, 100);
		
		makeRecipe(490, "RUBBER", new AStack[] { new OreDictStack(S.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.unsaturateds, 500) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RUBBER), 1) }, null, 100);
		
		makeRecipe(500, "DYNAMITE", new AStack[] { ItemStackUtil.comparableStackFrom(Items.SUGAR), new OreDictStack(KNO.dust()), new OreDictStack(KEY_SAND) }, new FluidStack[]{ new FluidStack(ModForgeFluids.sulfuric_acid, 1000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ball_dynamite, 2) }, null, 50);
		
		makeRecipe(510, "TNT", new AStack[] { new OreDictStack(KNO.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.aromatics, 500) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ball_tnt, 4) }, null, 150);

		makeRecipe(520, "C4", new AStack[] { new OreDictStack(KNO.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.unsaturateds, 500) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ingot_c4, 4) }, null, 150);

		makeRecipe(530, "HEAVY_ELECTROLYSIS", null, new FluidStack[]{ new FluidStack(ModForgeFluids.heavywater, 8000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.deuterium, 400), new FluidStack(ModForgeFluids.oxygen, 400) }, 150);
		
		makeRecipe(540, "DEUTERIUM", new AStack[] { new OreDictStack(S.dust(), 2) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 4000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.deuterium, 500) }, 200);
		
		makeRecipe(550, "STEAM", null, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.steam, 1000) }, 20);
		
		makeRecipe(560, "ALGE", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.biomass, 4), new OreDictStack(KNO.dust(), 1), new OreDictStack(COAL.dustTiny(), 2) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 8000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.biomass, 36) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 1000) }, 20*60);
		
		makeRecipe(570, "YELLOWCAKE", new AStack[] { new OreDictStack(U.dust(), 1), new OreDictStack(S.dust(), 2) }, new FluidStack[]{ new FluidStack(ModForgeFluids.acid, 500) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_yellowcake, 1) }, null, 250);
		
		makeRecipe(580, "UF6", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_yellowcake, 1), new OreDictStack(F.dust(), 3) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.uf6, 1000) }, 100);
		
		makeRecipe(590, "PUF6", new AStack[] { new OreDictStack(PU.dust(), 1), new OreDictStack(F.dust(), 3) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.puf6, 1000) }, 150);
		
		makeRecipe(600, "SAS3", new AStack[] { new OreDictStack(SA326.dust(), 1), new OreDictStack(S.dust(), 2) }, new FluidStack[]{ new FluidStack(ModForgeFluids.acid, 2000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.sas3, 1000) }, 200);
		
		makeRecipe(610, "DYN_SCHRAB", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.particle_higgs, 1), new OreDictStack(U.ingot(), 8), ItemStackUtil.comparableStackFrom(ModItems.catalyst_clay, 8) }, new FluidStack[]{ new FluidStack(ModForgeFluids.coolant, 1000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.particle_empty, 1), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM), 8) }, new FluidStack[]{ new FluidStack(ModForgeFluids.watz, 50) }, 20*30);
		
		makeRecipe(620, "DYN_STR", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.particle_strange, 1), ItemStackUtil.comparableStackFrom(ModItems.nugget.getItemStack(MaterialMineral.RADSPICE), 8), ItemStackUtil.comparableStackFrom(ModItems.catalyst_clay, 8) }, new FluidStack[]{ new FluidStack(ModForgeFluids.cryogel, 1000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.particle_empty, 1), ItemStackUtil.comparableStackFrom(ModItems.egg_balefire, 4) }, new FluidStack[]{ new FluidStack(ModForgeFluids.watz, 200) }, 20*60);
		
		makeRecipe(630, "DYN_EUPH", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.particle_dark, 1), new OreDictStack(SA327.ingot(), 8), ItemStackUtil.comparableStackFrom(ModItems.catalyst_clay, 16) }, new FluidStack[]{ new FluidStack(ModForgeFluids.cryogel, 2000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.particle_empty, 1), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.EUPHEMIUM), 8) }, new FluidStack[]{ new FluidStack(ModForgeFluids.watz, 100) }, 20*60*2);
		
		makeRecipe(640, "DYN_DNT", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.particle_sparkticle, 1), new OreDictStack(SBD.ingot(), 8), ItemStackUtil.comparableStackFrom(ModItems.catalyst_clay, 32) }, new FluidStack[]{ new FluidStack(ModForgeFluids.cryogel, 4000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.particle_empty, 1), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DINEUTRONIUM), 8) }, new FluidStack[]{ new FluidStack(ModForgeFluids.watz, 400) }, 20*60*5);
		
		makeRecipe(650, "DYN_EL", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.particle_digamma, 1), new OreDictStack(DNT.ingot(), 16), ItemStackUtil.comparableStackFrom(ModItems.catalyst_clay, 64) }, new FluidStack[]{ new FluidStack(ModForgeFluids.cryogel, 8000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.particle_empty, 1), ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ELECTRONIUM), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.watz, 800) }, 20*60*10);
		
		makeRecipe(660, "CORDITE", new AStack[] {new OreDictStack(KNO.dust(), 2), new OreDictStack(KEY_PLANKS, 1), ItemStackUtil.comparableStackFrom(Items.SUGAR, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.heatingoil, 200) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.cordite, 4) }, null, 40);
		
		makeRecipe(670, "KEVLAR", new AStack[] {new OreDictStack(KNO.dust(), 2), new OreDictStack(KEY_BRICK, 1), new OreDictStack(COAL.gem(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 100) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.plate_kevlar, 4) }, null, 40);
		
		makeRecipe(680, "CONCRETE", new AStack[] { new OreDictStack(KEY_GRAVEL, 8), new OreDictStack(KEY_SAND, 8) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 2000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModBlocks.concrete_smooth, 16) }, null, 100);
		
		makeRecipe(690, "CONCRETE_ASBESTOS", new AStack[] { new OreDictStack(KEY_GRAVEL, 2), new OreDictStack(KEY_SAND, 2), new OreDictStack(ASBESTOS.ingot(), 4) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 2000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModBlocks.concrete_asbestos, 16) }, null, 100);
		
		makeRecipe(700, "DUCRETE", new AStack[] { new OreDictStack(KEY_SAND, 8), new OreDictStack(U238.billet(), 2), ItemStackUtil.comparableStackFrom(Items.CLAY_BALL, 4) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 2000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModBlocks.ducrete, 8) }, null, 200);
		
		makeRecipe(710, "SOLID_FUEL", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.solid_fuel, 2), new OreDictStack(KNO.dust(), 1), new OreDictStack(REDSTONE.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 200) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.rocket_fuel, 4) }, null, 200);
		
		makeRecipe(720, "ELECTROLYSIS", null, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 8000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.hydrogen, 800), new FluidStack(ModForgeFluids.oxygen, 800) }, 150);
		
		makeRecipe(730, "XENON", null, null, null, new FluidStack[]{ new FluidStack(ModForgeFluids.xenon, 50) }, 300);
		
		makeRecipe(740, "XENON_OXY", null, new FluidStack[]{ new FluidStack(ModForgeFluids.oxygen, 250) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.xenon, 50) }, 20);
		
		makeRecipe(750, "SATURN", new AStack[] {new OreDictStack(DURA.dust(), 1), new OreDictStack(P_RED.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.acid, 100), new FluidStack(ModForgeFluids.mercury, 50) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SATURNITE), 2) }, null, 60);
		
		makeRecipe(760, "BALEFIRE", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.egg_balefire_shard, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.kerosene, 6000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_balefire, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.balefire, 8000) }, 100);
		
		makeRecipe(770, "SCHRABIDIC", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.pellet_charged, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.sas3, 8000), new FluidStack(ModForgeFluids.acid, 6000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.schrabidic, 16000) }, 100);
		
		makeRecipe(780, "SCHRABIDATE", new AStack[] {new OreDictStack(IRON.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.schrabidic, 250) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_schrabidate, 1) }, null, 600);
		
		makeRecipe(790, "COLTAN_CLEANING", new AStack[] {new OreDictStack(COLTAN.dust(), 2), new OreDictStack(COAL.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.acid, 250), new FluidStack(ModForgeFluids.hydrogen, 500) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_coltan, 1), ItemStackUtil.comparableStackFrom(ModItems.powder_niobium, 1), ItemStackUtil.comparableStackFrom(ModItems.dust, 1) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 500) }, 60);
		
		makeRecipe(800, "COLTAN_PAIN", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.powder_coltan, 1), new OreDictStack(F.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.gas, 1000), new FluidStack(ModForgeFluids.oxygen, 500) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.pain, 1000) }, 120);
		
		makeRecipe(810, "COLTAN_CRYSTAL", null, new FluidStack[]{ new FluidStack(ModForgeFluids.pain, 1000), new FluidStack(ModForgeFluids.acid, 500) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.gem_tantalium, 1), ItemStackUtil.comparableStackFrom(ModItems.dust, 3) }, new FluidStack[]{ new FluidStack(FluidRegistry.WATER, 250) }, 80);
		
		makeRecipe(820, "ARSENIC", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.scrap_oil, 64) }, new FluidStack[]{ new FluidStack(ModForgeFluids.sulfuric_acid, 1000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.nugget.getItemStack(MaterialMineral.ARSENIC), 1), ItemStackUtil.comparableStackFrom(ModItems.sulfur, 2) }, new FluidStack[]{ new FluidStack(ModForgeFluids.heavyoil, 1500) }, 1200);

		makeRecipe(830, "VIT_LIQUID", new AStack[] {ItemStackUtil.comparableStackFrom(ModBlocks.sand_lead, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.wastefluid, 1000) }, new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.nuclear_waste_vitrified, 1) }, null, 100);
		
		makeRecipe(840, "VIT_GAS", new AStack[] {ItemStackUtil.comparableStackFrom(ModBlocks.sand_lead, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.wastegas, 1000) }, new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.nuclear_waste_vitrified, 1) }, null, 100);
		
		makeRecipe(850, "TEL", new AStack[] {new OreDictStack(KEY_OIL_TAR, 1), new OreDictStack(PB.dust(), 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 100), new FluidStack(ModForgeFluids.steam, 1000) }, new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.antiknock, 1) }, null, 40);
		
		makeRecipe(860, "GASOLINE", new AStack[] {ItemStackUtil.comparableStackFrom(ModItems.antiknock, 1) }, new FluidStack[]{ new FluidStack(ModForgeFluids.petroil, 10000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.gasoline, 12000) }, 40);
		
		makeRecipe(870, "FRACKSOL", new AStack[] { new OreDictStack(S.dust()) }, new FluidStack[]{ new FluidStack(ModForgeFluids.petroleum, 100), new FluidStack(FluidRegistry.WATER, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.fracksol, 1000) }, 20);
		
		makeRecipe(880, "OSMIRIDIUM_DEATH", new AStack[] { ItemStackUtil.comparableStackFrom(ModItems.powder_paleogenite), new OreDictStack(F.dust(), 8), ItemStackUtil.comparableStackFrom(ModItems.nugget.getItemStack(MaterialMineral.BISMUTH), 4) }, new FluidStack[]{ new FluidStack(ModForgeFluids.sulfuric_acid, 1000), new FluidStack(ModForgeFluids.sas3, 1000) }, null, new FluidStack[]{ new FluidStack(ModForgeFluids.liquid_osmiridium, 1000) }, 240);
		
		// makeRecipe(0, "FP_SMEAR, new AStack[] { ItemStackUtil.comparableStackFrom() }, new FluidStack[]{ new FluidStack() }, null, null, 100);
		
	}

	public static void makeRecipe(final int index, final String name, final AStack[] itemInputs, final FluidStack[] fluidInputs, final AStack[] outputItems, final FluidStack[] outputFluids, final int duration) {
		if(itemInputs != null)
			recipeItemInputs.put(index, itemInputs);
		if(fluidInputs != null)
			recipeFluidInputs.put(index, fluidInputs);
		if(outputItems != null)
			recipeItemOutputs.put(index, outputItems);
		if(outputFluids != null)
			recipeFluidOutputs.put(index, outputFluids);
		if(duration > 1)
			recipeDurations.put(index, duration);
		if(name != null)
			recipeNames.put(index, name);
	}

	public static void removeRecipe(final int index) {
		recipeItemInputs.remove(index);
		recipeFluidInputs.remove(index);
		recipeItemOutputs.remove(index);
		recipeFluidOutputs.remove(index);
		recipeDurations.remove(index);
		recipeNames.remove(index);
	}

	public static List<AStack> getChemInputFromTempate(final ItemStack stack) {
		if (stack == null || !(stack.getItem() instanceof ItemChemistryTemplate))
			return null;
		final AStack[] inputs = recipeItemInputs.get(stack.getItemDamage());
		if(inputs != null)
			return Arrays.asList(inputs);
		return null;
	}


	public static FluidStack[] getFluidInputFromTempate(final ItemStack stack) {
		if (stack == null || !(stack.getItem() instanceof ItemChemistryTemplate))
			return null;
		return recipeFluidInputs.get(stack.getItemDamage());
	}


	public static ItemStack[] getChemOutputFromTempate(final ItemStack stack) {
		if (stack == null || !(stack.getItem() instanceof ItemChemistryTemplate))
			return null;
		final AStack[] outputs = recipeItemOutputs.get(stack.getItemDamage());
		if(outputs != null){
			final ItemStack[] stackOutputs = new ItemStack[outputs.length];
			for(int i=0; i<stackOutputs.length; i++){
				stackOutputs[i] = outputs[i].getStack();
			}
			return stackOutputs;
		}
		return null;
	}


	public static FluidStack[] getFluidOutputFromTempate(final ItemStack stack) {
		if (stack == null || !(stack.getItem() instanceof ItemChemistryTemplate))
			return null;
		return recipeFluidOutputs.get(stack.getItemDamage());
	}


	public static int getProcessTime(final ItemStack stack) {
    	if(!(stack.getItem() instanceof ItemChemistryTemplate))
    		return 100;
        final Integer time = recipeDurations.get(stack.getItemDamage());
        if(time != null)
        	return time;
        return 100;
    }

    public static String getName(final ItemStack stack) {
    	if(!(stack.getItem() instanceof ItemChemistryTemplate))
    		return null;
        final String name = recipeNames.get(stack.getItemDamage());
        if(name != null)
        	return name;
        return "BAD_RECIPE";
    }

    public static String getName(final int i) {
        final String name = recipeNames.get(i);
        if(name != null)
        	return name;
        return "";
    }

    public static boolean hasRecipe(final ItemStack stack) {
    	if(stack == null || !(stack.getItem() instanceof ItemChemistryTemplate))
    		return false;
       	return recipeNames.containsKey(stack.getItemDamage());
    }
}
