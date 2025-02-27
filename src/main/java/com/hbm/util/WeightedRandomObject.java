package com.hbm.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;

public class WeightedRandomObject extends WeightedRandom.Item {
	
	Object item;

	public WeightedRandomObject(final Object o, final int weight) {
		super(weight);
		item = o;
	}
	
	public ItemStack asStack() {
		
		if(item instanceof ItemStack)
			return ((ItemStack) item).copy();
		
		return null;
	}
	
	public Item asItem() {

		if(item instanceof Item)
			return (Item) item;

		return null;
	}

	public String asString() {

		if(item instanceof String)
			return (String) item;

		return null;
	}
}
