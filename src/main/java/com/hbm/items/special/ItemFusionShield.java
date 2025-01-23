package com.hbm.items.special;

import java.util.List;

import com.hbm.items.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemFusionShield extends Item {

	public long maxDamage;
	public int maxTemp;

	public ItemFusionShield(final long maxDamage, final int maxTemp, final String s) {
		this.maxDamage = maxDamage;
		this.maxTemp = maxTemp;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}

	public static long getShieldDamage(final ItemStack stack) {

		if(!stack.hasTagCompound()) {
			return 0;
		}

		return stack.getTagCompound().getLong("damage");
	}
	
	public static void setShieldDamage(final ItemStack stack, final long damage) {

		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		stack.getTagCompound().setLong("damage", damage);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		final long damage = getShieldDamage(stack);
		final int percent = (int) ((maxDamage - damage) * 100 / maxDamage);

		tooltip.add("Durability: " + (maxDamage - damage) + "/" + maxDamage + " (" + percent + "%)");

		tooltip.add("Maximum Plasma Heat: " + TextFormatting.RED + maxTemp + "°C");
	}
	
	@Override
	public double getDurabilityForDisplay(final ItemStack stack) {
		return (double)getShieldDamage(stack) / (double)maxDamage;
	}
	
	@Override
	public boolean showDurabilityBar(final ItemStack stack) {
		return getDurabilityForDisplay(stack) != 0;
	}
}
