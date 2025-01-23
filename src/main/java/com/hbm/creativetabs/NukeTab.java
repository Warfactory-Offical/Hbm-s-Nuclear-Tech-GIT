package com.hbm.creativetabs;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NukeTab extends CreativeTabs {

	public NukeTab(final int index, final String label) {
		super(index, label);
		this.setBackgroundImageName("nuke.png");
	}

	@Override
	public ItemStack createIcon() {
		if(ModBlocks.float_bomb != null){
			return ItemStackUtil.itemStackFrom(Item.getItemFromBlock(ModBlocks.nuke_man));
		}
		return ItemStackUtil.itemStackFrom(Items.IRON_PICKAXE);
	}

}
