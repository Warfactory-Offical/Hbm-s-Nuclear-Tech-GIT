package com.hbm.items.gear;

import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import net.minecraft.item.ItemPickaxe;

public class ModPickaxe extends ItemPickaxe {
	public ModPickaxe(final ToolMaterial t, final String s){
		super(t);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		ModItems.ALL_ITEMS.add(this);
	}
}
