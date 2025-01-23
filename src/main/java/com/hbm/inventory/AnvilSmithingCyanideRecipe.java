package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AnvilSmithingCyanideRecipe extends AnvilSmithingRecipe {
	
	public AnvilSmithingCyanideRecipe() {
		super(1, ItemStackUtil.itemStackFrom(Items.BREAD), ItemStackUtil.comparableStackFrom(Items.BREAD), ItemStackUtil.comparableStackFrom(ModItems.plan_c));
		
		if(!this.output.hasTagCompound())
			this.output.setTagCompound(new NBTTagCompound());
		
		this.output.getTagCompound().setBoolean("ntmCyanide", true);
		
	}
	
	@Override
	public boolean matches(final ItemStack left, final ItemStack right) {
		return doesStackMatch(right, this.right) && left.getItem() instanceof ItemFood;
	}

	@Override
	public int matchesInt(final ItemStack left, final ItemStack right) {
		return matches(left, right) ? 0 : -1;
	}
	
	@Override
	public ItemStack getOutput(final ItemStack left, final ItemStack right) {
		
		final ItemStack out = left.copy();
		out.setCount(1);
		if(!out.hasTagCompound())
			out.setTagCompound(new NBTTagCompound());
		
		out.getTagCompound().setBoolean("ntmCyanide", true);
		
		return out;
	}
}