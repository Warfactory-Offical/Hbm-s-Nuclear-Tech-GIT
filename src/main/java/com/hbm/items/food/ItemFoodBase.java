package com.hbm.items.food;

import com.hbm.items.ModItems;
import net.minecraft.item.ItemFood;

public class ItemFoodBase extends ItemFood {

	public ItemFoodBase(final int amount, final float saturation, final boolean isWolfFood, final String s){
		super(amount, saturation, isWolfFood);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}

}
