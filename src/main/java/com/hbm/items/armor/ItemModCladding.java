package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModCladding extends ItemArmorMod {
	
	public double rad;
	
	public ItemModCladding(final double rad, final String s) {
		super(ArmorModHandler.cladding, true, true, true, true, s);
		this.rad = rad;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.YELLOW + "+" + rad + " rad-resistance");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.YELLOW + "  " + stack.getDisplayName() + " (+" + rad + " radiation resistence)");
	}
}
