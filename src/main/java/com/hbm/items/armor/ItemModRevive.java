package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import com.hbm.items.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModRevive extends ItemArmorMod {

	public ItemModRevive(final int durability, final String s) {
		super(ArmorModHandler.extra, false, false, true, false, s);
		this.setMaxDamage(durability);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		if(this == ModItems.scrumpy) {
			list.add(TextFormatting.GOLD + "But how did you survive?");
			list.add(TextFormatting.RED + "I was drunk.");
		}
		if(this == ModItems.wild_p) {
			list.add(TextFormatting.DARK_GRAY + "Explosive " + TextFormatting.RED + "Reactive " + TextFormatting.DARK_GRAY + "Plot " + TextFormatting.RED + "Armor");
		}
		if(this == ModItems.fabsols_vodka) {
			list.add(TextFormatting.ITALIC + "In the news:");
			list.add(TextFormatting.RED + "" + TextFormatting.BOLD + "Man literally too angry to die.");
			list.add("");
			list.add(TextFormatting.ITALIC + "\"I ain't got time to die\" says local");
			list.add(TextFormatting.ITALIC + "man after ripping the physical manifestation");
			list.add(TextFormatting.ITALIC + "of disaster itself in half.");
		}
		
		list.add("");
		list.add(TextFormatting.GOLD + "" + (stack.getMaxDamage() - stack.getItemDamage()) + " revives left");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.GOLD + "  " + stack.getDisplayName() + " (" + (stack.getMaxDamage() - stack.getItemDamage()) + " revives left)");
	}
}