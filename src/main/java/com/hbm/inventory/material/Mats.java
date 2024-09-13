package com.hbm.inventory.material;

import static com.hbm.inventory.OreDictManager.*;
import static com.hbm.inventory.material.MaterialShapes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.hbm.inventory.OreDictManager.DictFrame;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.material.NTMMaterial.SmeltingBehavior;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import com.hbm.util.ItemStackUtil;

import net.minecraft.item.ItemStack;

/* with every new rewrite, optimization and improvement, the code becomes more gregian */

/**
 * Defines materials that wrap around DictFrames to more accurately describe that material.
 * Direct uses are the crucible and possibly item auto-gen, depending on what traits are set.
 * @author hbm
 */
public class Mats {

	public static List<NTMMaterial> orderedList = new ArrayList();
	public static HashMap<String, MaterialShapes> prefixByName = new HashMap();
	public static HashMap<String, NTMMaterial> matByName = new HashMap();
	public static HashMap<ComparableStack, List<MaterialStack>> materialEntries = new HashMap();
	public static HashMap<String, List<MaterialStack>> materialOreEntries = new HashMap();
	
	/*
	 * ItemStacks are saved with their metadata being truncated to a short, so the max meta is 32767
	 * Format for elements: Atomic number *100, plus the last two digits of the mass number. Mass number is 0 for generic/undefined/mixed materials.
	 * Vanilla numbers are in vanilla space (0-29), basic alloys use alloy space (30-99)
	 */
	
//	/* Vanilla Space, up to 30 materials, */
//	public static final int _VS = 0;
//	/* Alloy Space, up to 70 materials. Use >20_000 as an extension.*/
//	public static final int _AS = 30;

	//Vanilla and vanilla-like
	public static final NTMMaterial MAT_STONE		= makeSmeltable("stone",	df("Stone"), 0x4D2F23).omitAutoGen();
	public static final NTMMaterial MAT_CARBON		= makeAdditive("carbon", 		df("Carbon"), 0x404040).omitAutoGen();
	public static final NTMMaterial MAT_COAL		= make("coal", 		COAL)		.setConversion(MAT_CARBON,  2, 1).omitAutoGen();
	public static final NTMMaterial MAT_LIGNITE		= make("lignite", 		LIGNITE)	.setConversion(MAT_CARBON,  3, 1);
	public static final NTMMaterial MAT_COALCOKE	= make("coalcoke", 		COALCOKE)	.setConversion(MAT_CARBON,  4, 3);
	public static final NTMMaterial MAT_PETCOKE		= make("petcoke", 		PETCOKE)	.setConversion(MAT_CARBON,  4, 3);
	public static final NTMMaterial MAT_LIGCOKE		= make("ligcoke", 		LIGCOKE)	.setConversion(MAT_CARBON,  4, 3);
	public static final NTMMaterial MAT_GRAPHITE	= make("graphite", 		GRAPHITE)	.setConversion(MAT_CARBON,  1, 1);
	public static final NTMMaterial MAT_IRON		= makeSmeltable("iron",		IRON,		0xFFA259).omitAutoGen();
	public static final NTMMaterial MAT_GOLD		= makeSmeltable("gold",		GOLD,		0xE8D754).omitAutoGen();
	public static final NTMMaterial MAT_REDSTONE	= makeSmeltable("redstone",	REDSTONE,	0xFF1000).omitAutoGen();
	public static final NTMMaterial MAT_OBSIDIAN	= makeSmeltable("obsidian",	df("Obsidian"), 0x3D234D).omitAutoGen();
	public static final NTMMaterial MAT_HEMATITE	= makeAdditive("hematite", 		HEMATITE,	0x6E463D).omitAutoGen();
	public static final NTMMaterial MAT_MALACHITE	= makeAdditive("malachite", 		MALACHITE,	0x61AF87).omitAutoGen();

	//Radioactive
	public static final NTMMaterial MAT_URANIUM		= makeSmeltable("uranium",		U,			0x9AA196).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_U233		= makeSmeltable("u233",		U233,		0x9AA196).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_U235		= makeSmeltable("u235",		U235,		0x9AA196).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_U238		= makeSmeltable("u238",		U238,		0x9AA196).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_THORIUM		= makeSmeltable("thorium",		TH232,		0xBF825F).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_PLUTONIUM	= makeSmeltable("plutonium",		PU,			0x78817E).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_RGP			= makeSmeltable("rgp",		PURG,		0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_PU238		= makeSmeltable("pu238",		PU238,		0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_PU239		= makeSmeltable("pu239",		PU239,		0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_PU240		= makeSmeltable("pu240",		PU240,		0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_PU241		= makeSmeltable("pu241",		PU241,		0x78817E).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_RGA			= makeSmeltable("rga",		AMRG,		0x93767B).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_AM241		= makeSmeltable("am241",		AM241,		0x93767B).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_AM242		= makeSmeltable("am242",		AM242,		0x93767B).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_NEPTUNIUM	= makeSmeltable("neptunium",		NP237,		0x647064).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_POLONIUM	= makeSmeltable("polonium",		PO210,		0x563A26).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_TECHNIETIUM	= makeSmeltable("technietium",		TC99,		0xCADFDF).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_RADIUM		= makeSmeltable("radium",		RA226,		0xE9FAF6).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_ACTINIUM	= makeSmeltable("actinium",		AC227,		0x958989).setShapes(NUGGET, BILLET, INGOT);
	public static final NTMMaterial MAT_CO60		= makeSmeltable("co60",		CO60,		0x8F72AE).setShapes(NUGGET, BILLET, INGOT, POWDER);
	public static final NTMMaterial MAT_AU198		= makeSmeltable("au198",		AU198,		0xE8D754).setShapes(NUGGET, BILLET, INGOT, POWDER);
	public static final NTMMaterial MAT_PB209		= makeSmeltable("pb209",		PB209,		0x7B535D).setShapes(NUGGET, BILLET, INGOT, POWDER);
	public static final NTMMaterial MAT_SCHRABIDIUM	= makeSmeltable("schrabidium",		SA326,		0x32FFFF).setShapes(NUGGET, WIRE, BILLET, INGOT, POWDER, PLATE, BLOCK);
	public static final NTMMaterial MAT_SOLINIUM	= makeSmeltable("solinium",		SA327,		0x72B6B0).setShapes(NUGGET, BILLET, INGOT, BLOCK);
	public static final NTMMaterial MAT_SCHRABIDATE	= makeSmeltable("schrabidate",		SBD,		0x6589B4).setShapes(INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_SCHRARANIUM	= makeSmeltable("schraranium",		SRN,		0x24AFAC).setShapes(INGOT, BLOCK);
	public static final NTMMaterial MAT_GHIORSIUM	= makeSmeltable("ghiorsium",		GH336,		0xC6C6A1).setShapes(NUGGET, BILLET, INGOT, BLOCK);

	//Base metals
	public static final NTMMaterial MAT_TITANIUM	= makeSmeltable("titanium",		TI,			0xA99E79).setShapes(INGOT, POWDER, PLATE, BLOCK);
	public static final NTMMaterial MAT_COPPER		= makeSmeltable("copper",		CU,			0xC18336).setShapes(WIRE, INGOT, POWDER, PLATE, BLOCK);
	public static final NTMMaterial MAT_TUNGSTEN	= makeSmeltable("tungsten",		W,			0x977474).setShapes(WIRE, INGOT, POWDER, BLOCK, BOLT);
	public static final NTMMaterial MAT_ALUMINIUM	= makeSmeltable("aluminium",		AL,			0xD0B8EB).setShapes(WIRE, INGOT, POWDER, PLATE, BLOCK);
	public static final NTMMaterial MAT_LEAD		= makeSmeltable("lead",		PB,			0x646470).setShapes(NUGGET, INGOT, POWDER, PLATE, BLOCK);
	public static final NTMMaterial MAT_BISMUTH		= makeSmeltable("bismuth",		df("Bismuth"), 0xB200FF).setShapes(NUGGET, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_ARSENIC		= makeSmeltable("arsenic",		AS,			0x558080).setShapes(NUGGET, INGOT);
	public static final NTMMaterial MAT_TANTALIUM	= makeSmeltable("tantalium",		TA,			0xA89B74).setShapes(NUGGET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_NIOBIUM		= makeSmeltable("niobium",		NB,			0xD576B1).setShapes(NUGGET, POWDER_TINY, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_BERYLLIUM	= makeSmeltable("beryllium",		BE,			0xAE9572).setShapes(NUGGET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_COBALT		= makeSmeltable("cobalt",		CO,			0x8F72AE).setShapes(NUGGET, POWDER_TINY, BILLET, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_BORON		= makeSmeltable("boron",		B,			0xAD72AE).setShapes(POWDER_TINY, INGOT, POWDER, BLOCK);

	//Alloys
	public static final NTMMaterial MAT_STEEL		= makeSmeltable("steel",	STEEL,		0x4A4A4A).setShapes(POWDER_TINY, INGOT, POWDER, PLATE, BLOCK);
	public static final NTMMaterial MAT_MINGRADE	= makeSmeltable("mingrade",	MINGRADE,	0xE44C0F).setShapes(WIRE, INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_ALLOY		= makeSmeltable("alloy",	ALLOY,		0xFF7318).setShapes(WIRE, INGOT, POWDER, PLATE, BLOCK);
	public static final NTMMaterial MAT_DURA		= makeSmeltable("dura_steel",	DURA,		0x376373).setShapes(INGOT, POWDER, BLOCK, BOLT);
	public static final NTMMaterial MAT_SATURN		= makeSmeltable("saturn",	BIGMT,		0x4DA3AF).setShapes(INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_STAR		= makeSmeltable("star",	STAR,		0xA5A5D3).setShapes(INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_TCALLOY		= makeSmeltable("tcalloy",	TCALLOY,	0x9CA6A6).setShapes(INGOT, POWDER);
	public static final NTMMaterial MAT_FERRO		= makeSmeltable("ferro",	FERRO,		0x6B6B8B).setShapes(INGOT);
	public static final NTMMaterial MAT_MAGTUNG		= makeSmeltable("magtung",	MAGTUNG,	0x22A2A2).setShapes(INGOT, POWDER, BLOCK);
	public static final NTMMaterial MAT_CMB			= makeSmeltable("cmb",	CMB,		0x6F6FB4).setShapes(INGOT, POWDER, PLATE, BLOCK);
	public static final NTMMaterial MAT_FLUX		= makeAdditive("flux",	df("Flux"),	0xDECCAD).setShapes(POWDER);
	public static final NTMMaterial MAT_SLAG		= makeSmeltable("slag",	SLAG,		0x6C6562).setShapes(BLOCK);
	public static final NTMMaterial MAT_COMPOUND    = make("compound", COMPOUND).setShapes(BOLT);

	public static NTMMaterial make(String name, DictFrame dict) {
		return new NTMMaterial(name, dict);
	}
	
	public static NTMMaterial makeSmeltable(String name, DictFrame dict, int color) {
		return new NTMMaterial(name, dict).smeltable(SmeltingBehavior.SMELTABLE).setMoltenColor(color);
	}
	
	public static NTMMaterial makeAdditive(String name, DictFrame dict, int color) {
		return new NTMMaterial(name, dict).smeltable(SmeltingBehavior.ADDITIVE).setMoltenColor(color);
	}
	
	public static DictFrame df(String string) {
		return new DictFrame(string);
	}
	
	/** will not respect stacksizes - all stacks will be treated as a singular */
	public static List<MaterialStack> getMaterialsFromItem(ItemStack stack) {
		List<MaterialStack> list = new ArrayList();
		List<String> names = ItemStackUtil.getOreDictNames(stack);
		
		if(!names.isEmpty()) {
			outer:
			for(String name : names) {
				
				List<MaterialStack> oreEntries = materialOreEntries.get(name);
				
				if(oreEntries != null) {
					list.addAll(oreEntries);
					break outer;
				}
				
				for(Entry<String, MaterialShapes> prefixEntry : prefixByName.entrySet()) {
					String prefix = prefixEntry.getKey();
						
					if(name.startsWith(prefix)) {
						String materialName = name.substring(prefix.length());
						NTMMaterial material = matByName.get(materialName);
						
						if(material != null) {
							list.add(new MaterialStack(material, prefixEntry.getValue().q(1)));
							break outer;
						}
					}
				}
			}
		}
		
		List<MaterialStack> entries = materialEntries.get(ItemStackUtil.comparableStackFrom(stack).makeSingular());
		
		if(entries != null) {
			list.addAll(entries);
		}
		/*
		  for when crucible becomes real
		 
		if(stack.getItem() == ModItems.scraps) {
			list.add(ItemScraps.getMats(stack));
		}*/
		
		return list;
	}

	public static List<MaterialStack> getSmeltingMaterialsFromItem(ItemStack stack) {
		List<MaterialStack> baseMats = getMaterialsFromItem(stack);
		List<MaterialStack> smelting = new ArrayList();
		baseMats.forEach(x -> smelting.add(new MaterialStack(x.material.smeltsInto, (int) (x.amount * x.material.convOut / x.material.convIn))));
		return smelting;
	}

	public static void init() {
		MainRegistry.logger.info("test2");
	}

	public static class MaterialStack {
		//final fields to prevent accidental changing
		public final NTMMaterial material;
		public int amount;
		
		public MaterialStack(NTMMaterial material, int amount) {
			this.material = material;
			this.amount = amount;
		}
		
		public MaterialStack copy() {
			return new MaterialStack(material, amount);
		}
	}
	
	public static String formatAmount(int amount, boolean showInMb) {
		
		if(showInMb) {
			return (amount * 2) + "mB";
		}
		
		String format = "";
		
		int blocks = amount / BLOCK.q(1);
		amount -= BLOCK.q(blocks);
		int ingots = amount / INGOT.q(1);
		amount -= INGOT.q(ingots);
		int nuggets = amount / NUGGET.q(1);
		amount -= NUGGET.q(nuggets);
		int quanta = amount;
		
		if(blocks > 0) format += (blocks == 1 ? I18nUtil.resolveKey("matshape.block", blocks) : I18nUtil.resolveKey("matshape.blocks", blocks)) + " ";
		if(ingots > 0) format += (ingots == 1 ? I18nUtil.resolveKey("matshape.ingot", ingots) : I18nUtil.resolveKey("matshape.ingots", ingots)) + " ";
		if(nuggets > 0) format += (nuggets == 1 ? I18nUtil.resolveKey("matshape.nugget", nuggets) : I18nUtil.resolveKey("matshape.nuggets", nuggets)) + " ";
		if(quanta > 0) format += (quanta == 1 ? I18nUtil.resolveKey("matshape.quantum", quanta) : I18nUtil.resolveKey("matshape.quanta", quanta)) + " ";
		
		return format.trim();
	}
}
