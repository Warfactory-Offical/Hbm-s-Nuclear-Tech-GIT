package com.hbm.items.gear;

import com.hbm.items.ModItems;
import net.minecraft.item.ItemSword;

public class BigSword extends ItemSword {

	public BigSword(final ToolMaterial material, final String s) {
		super(material);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}

}
