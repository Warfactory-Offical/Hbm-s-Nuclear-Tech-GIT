package com.hbm.util;
import com.hbm.util.ItemStackUtil;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class Compat {
	public static Item tryLoadItem(String domain, String name) {
		return Item.REGISTRY.getObject(new ResourceLocation(domain, name));
	}
	
	public static Block tryLoadBlock(String domain, String name){
		return Block.REGISTRY.getObject(new ResourceLocation(domain, name));
	}
	public static List<ItemStack> scrapeItemFromME(ItemStack meDrive) {
		List<ItemStack> stacks = new ArrayList();

		try {
			if(meDrive != null && meDrive.hasTagCompound()) {
				NBTTagCompound nbt = meDrive.getTagCompound();
				int types = nbt.getShort("it"); //ITEM_TYPE_TAG

				for(int i = 0; i < types; i++) {
					NBTBase stackTag = nbt.getTag("#" + i);

					if(stackTag instanceof NBTTagCompound) {
						NBTTagCompound compound = (NBTTagCompound) stackTag;
						ItemStack stack = ItemStackUtil.itemStackFrom(compound);

						int count = nbt.getInteger("@" + i);
						stack.setCount(count);
						stacks.add(stack);
					}
				}
			}
		} catch(Exception ex) { }

		return stacks;
	}
}
