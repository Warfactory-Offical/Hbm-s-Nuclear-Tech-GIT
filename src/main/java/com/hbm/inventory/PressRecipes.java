package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.hbm.inventory.OreDictManager.*;
import com.hbm.util.Tuple.Pair;
import com.hbm.items.ModItems;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

//TODO: clean this shit up
//Alcater: on it

public class PressRecipes {

	public static enum PressType {
		NONE,
		FLAT,
		PLATE,
		WIRE,
		CIRCUIT,
		THREEFIFESEVEN,
		FOURFOUR,
		NINE,
		FIVEZERO
    }

	public static LinkedHashMap<Pair<PressType, AStack>, ItemStack> pressRecipes = new LinkedHashMap<Pair<PressType, AStack>, ItemStack>();

	public static void addRecipe(final PressType stamp, final AStack input, final ItemStack output){
		if(!input.getStackList().isEmpty())
			pressRecipes.put(new Pair(stamp, input), output);
	}

	public static void registerOverrides() {
		addRecipe(PressType.FLAT, new OreDictStack(COAL.dust()), ItemStackUtil.itemStackFrom(Items.COAL));
		addRecipe(PressType.FLAT, new OreDictStack("dustQuartz"), ItemStackUtil.itemStackFrom(Items.QUARTZ));
		addRecipe(PressType.FLAT, new OreDictStack(NETHERQUARTZ.dust()), ItemStackUtil.itemStackFrom(Items.QUARTZ)); 
		addRecipe(PressType.FLAT, new OreDictStack(LAPIS.dust()), ItemStackUtil.itemStackFrom(Items.DYE, 1, 4)); 
		addRecipe(PressType.FLAT, new OreDictStack(DIAMOND.dust()), ItemStackUtil.itemStackFrom(Items.DIAMOND)); 
		addRecipe(PressType.FLAT, new OreDictStack(EMERALD.dust()), ItemStackUtil.itemStackFrom(Items.EMERALD)); 
		addRecipe(PressType.FLAT, ItemStackUtil.comparableStackFrom(ModItems.pellet_coal), ItemStackUtil.itemStackFrom(Items.DIAMOND)); 
		addRecipe(PressType.FLAT, ItemStackUtil.comparableStackFrom(ModItems.biomass), ItemStackUtil.itemStackFrom(ModItems.biomass_compressed)); 
		addRecipe(PressType.FLAT, ItemStackUtil.comparableStackFrom(ModItems.powder_lignite), ItemStackUtil.itemStackFrom(ModItems.briquette_lignite)); 
		addRecipe(PressType.FLAT, ItemStackUtil.comparableStackFrom(ModItems.meteorite_sword_reforged), ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_hardened)); 
		addRecipe(PressType.FLAT, new OreDictStack("fuelCoke"), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.GRAPHITE)));
		addRecipe(PressType.FLAT, new OreDictStack("sugarcane"), ItemStackUtil.itemStackFrom(Items.PAPER, 2));
		addRecipe(PressType.FLAT, ItemStackUtil.comparableStackFrom(Blocks.LOG, 1, 3), ItemStackUtil.itemStackFrom(ModItems.ball_resin, 1));

		addRecipe(PressType.PLATE, new OreDictStack(IRON.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_iron));
		addRecipe(PressType.PLATE, new OreDictStack(GOLD.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_gold));
		addRecipe(PressType.PLATE, new OreDictStack(TI.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_titanium));
		addRecipe(PressType.PLATE, new OreDictStack(AL.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_aluminium));
		addRecipe(PressType.PLATE, new OreDictStack(STEEL.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_steel));
		addRecipe(PressType.PLATE, new OreDictStack(PB.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_lead));
		addRecipe(PressType.PLATE, new OreDictStack(CU.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_copper));
		addRecipe(PressType.PLATE, new OreDictStack("ingotAdvanced"), ItemStackUtil.itemStackFrom(ModItems.plate_advanced_alloy));
		addRecipe(PressType.PLATE, new OreDictStack(ALLOY.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_advanced_alloy));
		addRecipe(PressType.PLATE, new OreDictStack(SA326.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_schrabidium));
		addRecipe(PressType.PLATE, new OreDictStack(CMB.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_combine_steel));
		addRecipe(PressType.PLATE, new OreDictStack(BIGMT.ingot()), ItemStackUtil.itemStackFrom(ModItems.plate_saturnite));

		addRecipe(PressType.WIRE, new OreDictStack(AL.ingot()), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.ALUMINIUM), 8));
		addRecipe(PressType.WIRE, new OreDictStack(CU.ingot()), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.COPPER), 8));
		addRecipe(PressType.WIRE, new OreDictStack(W.ingot()), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.TUNGSTEN), 8));
		addRecipe(PressType.WIRE, new OreDictStack(MINGRADE.ingot()), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.RED_COPPER), 8));
		addRecipe(PressType.WIRE, new OreDictStack(GOLD.ingot()), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.GOLD), 8));
		addRecipe(PressType.WIRE, new OreDictStack(SA326.ingot()), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.SCHRABIDIUM), 8));
		addRecipe(PressType.WIRE, new OreDictStack("ingotAdvanced"), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.ADVANCED_ALLOY), 8));
		addRecipe(PressType.WIRE, new OreDictStack(ALLOY.ingot()), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.ADVANCED_ALLOY), 8));
		addRecipe(PressType.WIRE, new OreDictStack(MAGTUNG.ingot()), ItemStackUtil.itemStackFrom(ModItems.wire.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN), 8));

		addRecipe(PressType.CIRCUIT, ItemStackUtil.comparableStackFrom(ModItems.circuit_raw), ItemStackUtil.itemStackFrom(ModItems.circuit_aluminium));
		addRecipe(PressType.CIRCUIT, ItemStackUtil.comparableStackFrom(ModItems.circuit_bismuth_raw), ItemStackUtil.itemStackFrom(ModItems.circuit_bismuth));
		addRecipe(PressType.CIRCUIT, ItemStackUtil.comparableStackFrom(ModItems.circuit_arsenic_raw), ItemStackUtil.itemStackFrom(ModItems.circuit_arsenic));
		addRecipe(PressType.CIRCUIT, ItemStackUtil.comparableStackFrom(ModItems.circuit_tantalium_raw), ItemStackUtil.itemStackFrom(ModItems.circuit_tantalium));

		addRecipe(PressType.THREEFIFESEVEN, ItemStackUtil.comparableStackFrom(ModItems.assembly_iron), ItemStackUtil.itemStackFrom(ModItems.gun_revolver_iron_ammo));
		addRecipe(PressType.THREEFIFESEVEN, ItemStackUtil.comparableStackFrom(ModItems.assembly_steel), ItemStackUtil.itemStackFrom(ModItems.gun_revolver_ammo));
		addRecipe(PressType.THREEFIFESEVEN, ItemStackUtil.comparableStackFrom(ModItems.assembly_lead), ItemStackUtil.itemStackFrom(ModItems.gun_revolver_lead_ammo));
		addRecipe(PressType.THREEFIFESEVEN, ItemStackUtil.comparableStackFrom(ModItems.assembly_gold), ItemStackUtil.itemStackFrom(ModItems.gun_revolver_gold_ammo));
		addRecipe(PressType.THREEFIFESEVEN, ItemStackUtil.comparableStackFrom(ModItems.assembly_schrabidium), ItemStackUtil.itemStackFrom(ModItems.gun_revolver_schrabidium_ammo));
		addRecipe(PressType.THREEFIFESEVEN, ItemStackUtil.comparableStackFrom(ModItems.assembly_nightmare), ItemStackUtil.itemStackFrom(ModItems.gun_revolver_nightmare_ammo));
		addRecipe(PressType.THREEFIFESEVEN, ItemStackUtil.comparableStackFrom(ModItems.assembly_desh), ItemStackUtil.itemStackFrom(ModItems.ammo_357_desh));
		addRecipe(PressType.THREEFIFESEVEN, new OreDictStack(STEEL.ingot()), ItemStackUtil.itemStackFrom(ModItems.gun_revolver_cursed_ammo));

		addRecipe(PressType.FOURFOUR, ItemStackUtil.comparableStackFrom(ModItems.assembly_nopip), ItemStackUtil.itemStackFrom(ModItems.ammo_44));

		addRecipe(PressType.NINE, ItemStackUtil.comparableStackFrom(ModItems.assembly_smg), ItemStackUtil.itemStackFrom(ModItems.ammo_9mm));
		addRecipe(PressType.NINE, ItemStackUtil.comparableStackFrom(ModItems.assembly_uzi), ItemStackUtil.itemStackFrom(ModItems.ammo_22lr));
		addRecipe(PressType.NINE, new OreDictStack(GOLD.ingot()), ItemStackUtil.itemStackFrom(ModItems.ammo_566_gold));
		addRecipe(PressType.NINE, ItemStackUtil.comparableStackFrom(ModItems.assembly_lacunae), ItemStackUtil.itemStackFrom(ModItems.ammo_5mm));
		addRecipe(PressType.NINE, ItemStackUtil.comparableStackFrom(ModItems.assembly_556), ItemStackUtil.itemStackFrom(ModItems.ammo_556));

		addRecipe(PressType.FIVEZERO, ItemStackUtil.comparableStackFrom(ModItems.assembly_calamity), ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg));
		addRecipe(PressType.FIVEZERO, ItemStackUtil.comparableStackFrom(ModItems.assembly_actionexpress), ItemStackUtil.itemStackFrom(ModItems.ammo_50ae));
	}


	public static PressType getStampType(final Item stamp){
		if (stamps_flat.contains(stamp)) {
			return PressType.FLAT;
		}
		if (stamps_plate.contains(stamp)) {
			return PressType.PLATE;
		}
		if (stamps_wire.contains(stamp)) {
			return PressType.WIRE;
		}
		if (stamps_circuit.contains(stamp)) {
			return PressType.CIRCUIT;
		}
		if (stamps_357.contains(stamp)) {
			return PressType.THREEFIFESEVEN;
		}
		if (stamps_44.contains(stamp)) {
			return PressType.FOURFOUR;
		}
		if (stamps_9.contains(stamp)) {
			return PressType.NINE;
		}
		if (stamps_50.contains(stamp)) {
			return PressType.FIVEZERO;
		}
		return PressType.NONE;
	}

	public static List<ItemStack> toStack(final List<Item> iList){
		final List<ItemStack> i_stamps = new ArrayList<ItemStack>();
		for(final Item i : iList){
			i_stamps.add(ItemStackUtil.itemStackFrom(i));
		}
		return i_stamps;
	}

	public static List<ItemStack> getStampList(final PressType pType){
		if (pType == PressType.FLAT) {
			return toStack(stamps_flat);
		}
		if (pType == PressType.PLATE) {
			return toStack(stamps_plate);
		}
		if (pType == PressType.WIRE) {
			return toStack(stamps_wire);
		}
		if (pType == PressType.CIRCUIT) {
			return toStack(stamps_circuit);
		}
		if (pType == PressType.THREEFIFESEVEN) {
			return toStack(stamps_357);
		}
		if (pType == PressType.FOURFOUR) {
			return toStack(stamps_44);
		}
		if (pType == PressType.NINE) {
			return toStack(stamps_9);
		}
		if (pType == PressType.FIVEZERO) {
			return toStack(stamps_50);
		}
		return new ArrayList();
	}
	
	
	public static ItemStack getPressResult(final ItemStack input, final ItemStack stamp) {
		if (input == null || stamp == null)
			return null;

		final PressType pType = getStampType(stamp.getItem());
		if(pType == PressType.NONE) return null;

		return getPressOutput(pType, input);
	}

	public static ItemStack getPressOutput(final PressType pType, final ItemStack input){
		ItemStack outputItem = pressRecipes.get(new Pair(pType, ItemStackUtil.comparableStackFrom(input.getItem(), 1, input.getItemDamage())));
		if(outputItem != null)
			return outputItem;

		final int[] ids = OreDictionary.getOreIDs(ItemStackUtil.itemStackFrom(input.getItem(), 1, input.getItemDamage()));
		for(final int id : ids) {

			final OreDictStack oreStack = new OreDictStack(OreDictionary.getOreName(id));
			outputItem = pressRecipes.get(new Pair(pType, oreStack));
			if(outputItem != null)
				return outputItem;
		}
		return null;
	}

	public static List<Item> stamps_flat = new ArrayList<Item>() {
		{
			add(ModItems.stamp_stone_flat);
			add(ModItems.stamp_iron_flat);
			add(ModItems.stamp_steel_flat);
			add(ModItems.stamp_titanium_flat);
			add(ModItems.stamp_obsidian_flat);
			add(ModItems.stamp_schrabidium_flat);
			add(ModItems.stamp_desh_flat);
		}
	};

	public static List<Item> stamps_plate = new ArrayList<Item>() {
		{
			add(ModItems.stamp_stone_plate);
			add(ModItems.stamp_iron_plate);
			add(ModItems.stamp_steel_plate);
			add(ModItems.stamp_titanium_plate);
			add(ModItems.stamp_obsidian_plate);
			add(ModItems.stamp_schrabidium_plate);
			add(ModItems.stamp_desh_plate);
		}
	};

	public static List<Item> stamps_wire = new ArrayList<Item>() {
		{
			add(ModItems.stamp_stone_wire);
			add(ModItems.stamp_iron_wire);
			add(ModItems.stamp_steel_wire);
			add(ModItems.stamp_titanium_wire);
			add(ModItems.stamp_obsidian_wire);
			add(ModItems.stamp_schrabidium_wire);
			add(ModItems.stamp_desh_wire);
		}
	};

	public static List<Item> stamps_circuit = new ArrayList<Item>() {
		{
			add(ModItems.stamp_stone_circuit);
			add(ModItems.stamp_iron_circuit);
			add(ModItems.stamp_steel_circuit);
			add(ModItems.stamp_titanium_circuit);
			add(ModItems.stamp_obsidian_circuit);
			add(ModItems.stamp_schrabidium_circuit);
			add(ModItems.stamp_desh_circuit);
		}
	};

	public static List<Item> stamps_357 = new ArrayList<Item>() {
		{
			add(ModItems.stamp_357);
			add(ModItems.stamp_desh_357);
		}
	};

	public static List<Item> stamps_44 = new ArrayList<Item>() {
		{
			add(ModItems.stamp_44);
			add(ModItems.stamp_desh_44);
		}
	};

	public static List<Item> stamps_9 = new ArrayList<Item>() {
		{
			add(ModItems.stamp_9);
			add(ModItems.stamp_desh_9);
		}
	};

	public static List<Item> stamps_50 = new ArrayList<Item>() {
		{
			add(ModItems.stamp_50);
			add(ModItems.stamp_desh_50);
		}
	};
}
