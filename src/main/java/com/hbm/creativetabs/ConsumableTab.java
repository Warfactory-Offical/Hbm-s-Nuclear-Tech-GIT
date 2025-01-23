package com.hbm.creativetabs;
import com.hbm.util.ItemStackUtil;

import com.hbm.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ConsumableTab extends CreativeTabs {

	public ConsumableTab(final int index, final String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		if(ModItems.bottle_nuka != null){
			return ItemStackUtil.itemStackFrom(ModItems.bottle_nuka);
		}
		return ItemStackUtil.itemStackFrom(Items.IRON_PICKAXE);
	}

}
