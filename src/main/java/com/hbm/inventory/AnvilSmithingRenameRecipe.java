package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import com.hbm.inventory.RecipesCommon.ComparableStack;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AnvilSmithingRenameRecipe extends AnvilSmithingRecipe {
	
	public AnvilSmithingRenameRecipe() {
		super(1, ItemStackUtil.itemStackFrom(Items.IRON_SWORD), ItemStackUtil.comparableStackFrom(Items.IRON_SWORD), ItemStackUtil.comparableStackFrom(Items.NAME_TAG, 0));
	}
	
	@Override
	public boolean matches(final ItemStack left, final ItemStack right) {
		return doesStackMatch(right, this.right) && getDisplayName(right) != null;
	}

	@Override
	public int matchesInt(final ItemStack left, final ItemStack right) {
		return matches(left, right) ? 0 : -1;
	}
	
	@Override
	public ItemStack getOutput(final ItemStack left, final ItemStack right) {
		
		final ItemStack out = left.copy();
		out.setCount(1);
		
		String name = getDisplayName(right);
				
		if(name != null) {
			name = name.replace("\\&", "§");
			out.setStackDisplayName("§r" + name);
		}
		
		return out;
	}
	
	public String getDisplayName(final ItemStack stack) {
		String s = null;

		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("display", 10)) {
			final NBTTagCompound nbttagcompound = stack.getTagCompound().getCompoundTag("display");

			if(nbttagcompound.hasKey("Name", 8)) {
				s = nbttagcompound.getString("Name");
			}
		}

		return s;
	}
}