package com.hbm.inventory.material;

import java.util.ArrayList;
import java.util.List;

import com.hbm.util.Compat;

public class MaterialShapes {
	
	public static final List<MaterialShapes> allShapes = new ArrayList();
	
	public static final MaterialShapes QUANTUM = new MaterialShapes(1); // 1/72 of an ingot, allows the ingot to be divisible through 2, 4, 6, 8, 9, 12, 24 and 36
	public static final MaterialShapes NUGGET = new MaterialShapes(8, "nugget");
	public static final MaterialShapes DUSTTINY = new MaterialShapes(NUGGET.quantity, "dustTiny");
	public static final MaterialShapes WIRE = new MaterialShapes(9);
	public static final MaterialShapes BILLET = new MaterialShapes(NUGGET.quantity * 6, "billet");
	public static final MaterialShapes INGOT = new MaterialShapes(NUGGET.quantity * 9, "ingot");
	public static final MaterialShapes GEM = new MaterialShapes(INGOT.quantity, "gem");
	public static final MaterialShapes CRYSTAL = new MaterialShapes(INGOT.quantity, "crystal");
	public static final MaterialShapes DUST = new MaterialShapes(INGOT.quantity, "dust");
	public static final MaterialShapes PLATE = new MaterialShapes(INGOT.quantity, "plate");
	public static final MaterialShapes QUART = new MaterialShapes(162);
	public static final MaterialShapes BLOCK = new MaterialShapes(INGOT.quantity * 9, "block");
	/*
	 Sorry gregorius technologisticus enjoyers
	 
	public static void registerCompatShapes() {

		if(Compat.isModLoaded(Compat.MOD_GT6)) {
			new MaterialShapes(INGOT.q(9, 8), "crushed");
			new MaterialShapes(INGOT.q(9, 72), "crushedTiny");
			new MaterialShapes(INGOT.q(10, 8), "crushedPurified");
			new MaterialShapes(INGOT.q(10, 72), "crushedPurifiedTiny");
			new MaterialShapes(INGOT.q(11, 8), "crushedCentrifuged");
			new MaterialShapes(INGOT.q(11, 72), "crushedCentrifugedTiny");
			new MaterialShapes(INGOT.q(1, 4), "dustSmall");
			new MaterialShapes(INGOT.q(1, 72), "dustDiv72");
			new MaterialShapes(INGOT.q(10, 9), "dustImpure");
			new MaterialShapes(INGOT.q(11, 9), "dustPure");
			new MaterialShapes(INGOT.q(12, 9), "dustRefined");
		}
	}
	*/
	
	private final int quantity;
	public final String[] prefixes;
	
	private MaterialShapes(final int quantity, final String... prefixes) {
		this.quantity = quantity;
		this.prefixes = prefixes;
		
		for(final String prefix : prefixes) {
			Mats.prefixByName.put(prefix, this);
		}
		
		allShapes.add(this);
	}
	
	public int q(final int amount) {
		return this.quantity * amount;
	}
	
	public int q(final int unitsUsed, final int itemsProduced) { //eg rails: INOGT.q(6, 16) since the recipe uses 6 ton ingots producing 16 individual rail blocks
		return this.quantity * unitsUsed / itemsProduced;
	}

	public String name() {
		return (prefixes != null && prefixes.length > 0) ? prefixes[0] : "unknown";
	}
}
