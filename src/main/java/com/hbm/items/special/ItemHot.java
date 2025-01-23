package com.hbm.items.special;

import com.hbm.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemHot extends Item {

	public static int heat;
	
	public ItemHot(final int heat, final String s) {
		ItemHot.heat = heat;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	public static ItemStack heatUp(final ItemStack stack) {

		if(!(stack.getItem() instanceof ItemHot))
			return stack;

		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		stack.getTagCompound().setInteger("heat", heat);
		return stack;
	}

	public static ItemStack heatUp(final ItemStack stack, final double d) {

		if(!(stack.getItem() instanceof ItemHot))
			return stack;

		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		stack.getTagCompound().setInteger("heat", (int)(d * heat));
		return stack;
	}
	
	public static double getHeat(final ItemStack stack) {

		if(!(stack.getItem() instanceof ItemHot))
			return 0;

		if(!stack.hasTagCompound())
			return 0;

		final int h = stack.getTagCompound().getInteger("heat");

		return (double)h / (double)heat;
	}
	
	@Override
	public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int itemSlot, final boolean isSelected) {
		if(!world.isRemote && stack.hasTagCompound()) {

    		final int h = stack.getTagCompound().getInteger("heat");

    		if(h > 0) {
    			stack.getTagCompound().setInteger("heat", h - 1);
    		} else {
    			stack.getTagCompound().removeTag("heat");
    		}
    	}
	}
	
}
