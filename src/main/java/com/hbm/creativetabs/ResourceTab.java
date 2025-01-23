package com.hbm.creativetabs;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ResourceTab extends CreativeTabs {

	public ResourceTab(final int index, final String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		if(ModBlocks.ore_uranium != null){
			return ItemStackUtil.itemStackFrom(Item.getItemFromBlock(ModBlocks.ore_uranium));
		}
		return ItemStackUtil.itemStackFrom(Items.IRON_PICKAXE);
	}

}
