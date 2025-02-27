package com.hbm.items.machine;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.items.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSatChip extends Item {

	public ItemSatChip(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		tooltip.add(I18nUtil.resolveKey("desc.satellitefr", getFreq(stack)));
	}
	
	public static int getFreq(final ItemStack stack) {
		if(stack.getTagCompound() == null) {
			return 0;
		}
		return stack.getTagCompound().getInteger("freq");
	}
	
	public static void setFreq(final ItemStack stack, final int i) {
		if(stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setInteger("freq", i);
	}
}
