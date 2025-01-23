package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModShackles extends ItemArmorMod {

	public ItemModShackles(final String s) {
		super(ArmorModHandler.extra, false, false, true, false, s);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.RED + "You will speak when I ask you to.");
		list.add(TextFormatting.RED + "You will eat when I tell you to.");
		list.add(TextFormatting.RED + "" + TextFormatting.BOLD + "You will die when I allow you to.");
		
		list.add("");
		list.add(TextFormatting.GOLD + "∞ revives left");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	
	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.GOLD + "  " + stack.getDisplayName() + " (∞ revives left)");
	}
}
