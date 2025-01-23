package com.hbm.items.tool;

import com.hbm.items.ModItems;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemKeyPin extends Item {

	public ItemKeyPin(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if(getPins(stack) != 0)
			tooltip.add(I18nUtil.resolveKey("desc.keypin1", getPins(stack)));
		else
			tooltip.add(I18nUtil.resolveKey("desc.keypin2"));
		
		if(this == ModItems.key_fake) {

			tooltip.add("");
			tooltip.add(I18nUtil.resolveKey("desc.keypin3"));
		}
	}
	
	public static int getPins(final ItemStack stack) {
		if(stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		return stack.getTagCompound().getInteger("pins");
	}
	
	public static void setPins(final ItemStack stack, final int i) {
		if(stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setInteger("pins", i);
	}
	
	public boolean canTransfer() {
		return this != ModItems.key_fake;
	}
}
