package com.hbm.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class Compat {
	public static Item tryLoadItem(final String domain, final String name) {
		return Item.REGISTRY.getObject(new ResourceLocation(domain, name));
	}
	
	public static Block tryLoadBlock(final String domain, final String name){
		return Block.REGISTRY.getObject(new ResourceLocation(domain, name));
	}
	public static List<ItemStack> scrapeItemFromME(final ItemStack meDrive) {
		final List<ItemStack> stacks = new ArrayList();

		try {
			if(meDrive != null && meDrive.hasTagCompound()) {
				final NBTTagCompound nbt = meDrive.getTagCompound();
				final int types = nbt.getShort("it"); //ITEM_TYPE_TAG

				for(int i = 0; i < types; i++) {
					final NBTBase stackTag = nbt.getTag("#" + i);

					if(stackTag instanceof NBTTagCompound compound) {
                        final ItemStack stack = ItemStackUtil.itemStackFrom(compound);

						final int count = nbt.getInteger("@" + i);
						stack.setCount(count);
						stacks.add(stack);
					}
				}
			}
		} catch(final Exception ex) { }

		return stacks;
	}
}
