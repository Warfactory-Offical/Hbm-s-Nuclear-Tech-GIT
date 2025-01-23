package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModObsidian extends ItemArmorMod {
	
	public ItemModObsidian(final String s) {
		super(ArmorModHandler.cladding, true, true, true, true, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.DARK_PURPLE + "Makes dropped armor indestructible");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.DARK_PURPLE + "  " + stack.getDisplayName() + " (Item indestructible)");
	}
}
