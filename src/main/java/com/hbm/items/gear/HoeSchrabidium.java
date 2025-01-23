package com.hbm.items.gear;

import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class HoeSchrabidium extends ItemHoe {

	public HoeSchrabidium(final ToolMaterial t, final String s){
		super(t);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public EnumRarity getRarity(final ItemStack stack) {
		return EnumRarity.RARE;
	}
}
