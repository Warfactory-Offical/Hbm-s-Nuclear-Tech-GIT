package com.hbm.blocks.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockScrap extends ItemBlock {

	public ItemBlockScrap(final Block block) {
		super(block);
	}
	
	@Override
	public int getItemBurnTime(final ItemStack itemStack) {
		return 4000;
	}

}
