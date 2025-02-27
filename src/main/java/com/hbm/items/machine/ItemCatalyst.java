package com.hbm.items.machine;

import java.util.List;

import com.hbm.lib.Library;
import com.hbm.items.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCatalyst extends Item {

	int color;
	long powerAbs;
	float powerMod;
	float heatMod;
	float fuelMod;
	
	public ItemCatalyst(final int color, final String s) {
		this.color = color;
		this.powerAbs = 0;
		this.powerMod = 1.0F;
		this.heatMod = 1.0F;
		this.fuelMod = 1.0F;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	public ItemCatalyst(final int color, final long powerAbs, final float powerMod, final float heatMod, final float fuelMod, final String s) {
		this.color = color;
		this.powerAbs = powerAbs;
		this.powerMod = powerMod;
		this.heatMod = heatMod;
		this.fuelMod = fuelMod;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	public int getColor() {
		return this.color;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		tooltip.add("Absolute Energy Bonus: " + (powerAbs >= 0 ? "§a+" : "§c") + Library.getShortNumber(powerAbs) + "HE");
		tooltip.add("Energy Modifier:           " + (powerMod >= 1 ? "§a+" : "§c") + (Math.round(powerMod * 1000) * .10 - 100) + "%");
		tooltip.add("Heat Modifier:               " + (heatMod > 1 ? "§c+" : "§a") + (Math.round(heatMod * 1000) * .10 - 100) + "%");
		tooltip.add("Fuel Modifier:               " + (fuelMod > 1 ? "§c+" : "§a") + (Math.round(fuelMod * 1000) * .10 - 100) + "%");
	}
	
	public static long getPowerAbs(final ItemStack stack) {
		if(stack == null || !(stack.getItem() instanceof ItemCatalyst))
			return 0;
		return ((ItemCatalyst)stack.getItem()).powerAbs;
	}
	
	public static float getPowerMod(final ItemStack stack) {
		if(stack == null || !(stack.getItem() instanceof ItemCatalyst))
			return 1F;
		return ((ItemCatalyst)stack.getItem()).powerMod;
	}
	
	public static float getHeatMod(final ItemStack stack) {
		if(stack == null || !(stack.getItem() instanceof ItemCatalyst))
			return 1F;
		return ((ItemCatalyst)stack.getItem()).heatMod;
	}
	
	public static float getFuelMod(final ItemStack stack) {
		if(stack == null || !(stack.getItem() instanceof ItemCatalyst))
			return 1F;
		return ((ItemCatalyst)stack.getItem()).fuelMod;
	}
}
