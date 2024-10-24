package com.hbm.creativetabs;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MachineTab extends CreativeTabs {

	public MachineTab(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		if(ModBlocks.reactor_element != null)
			return ItemStackUtil.itemStackFrom(Item.getItemFromBlock(ModBlocks.reactor_element));
		return ItemStackUtil.itemStackFrom(Items.IRON_PICKAXE);
	}

}
