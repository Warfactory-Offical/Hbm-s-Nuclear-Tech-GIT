package com.hbm.items.special;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemHotDusted extends ItemHot {

	public ItemHotDusted(final int heat, final String s){
		super(heat, s);
		setHasSubtypes(true);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn){
		tooltip.add("Forged " + stack.getItemDamage() + " time(s)");
	}
	
	public static int getMaxHeat(final ItemStack stack) {
		return heat - stack.getItemDamage() * 10;
	}
}
