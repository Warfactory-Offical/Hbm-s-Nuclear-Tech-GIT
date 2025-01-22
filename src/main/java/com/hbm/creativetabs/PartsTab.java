package com.hbm.creativetabs;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import com.hbm.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PartsTab extends CreativeTabs {

	public PartsTab(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		if(ModItems.ingot.getItemStack(MaterialMineral.URANIUM) != null){
			return ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM));
		}
		return ItemStackUtil.itemStackFrom(Items.IRON_PICKAXE);
	}

}
