package com.hbm.items.machine;

import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.items.special.ItemHazard;

public class ItemBlades extends ItemHazard {
	public ItemBlades(final String s, final int i){
		super(s);
		this.setMaxStackSize(1);
		this.setTranslationKey(s);
		this.setMaxDamage(i);
	}
}
