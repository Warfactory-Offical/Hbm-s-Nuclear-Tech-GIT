package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModV1 extends ItemArmorMod {

	public ItemModV1(final String s){
		super(ArmorModHandler.extra, false, true, false, false, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.RED + "BLOOD IS FUEL");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	
	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor){
		list.add(TextFormatting.RED + "  " + stack.getDisplayName() + " (BLOOD IS FUEL)");
	}
}
