package com.hbm.blocks.machine;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.lib.Library;

import api.hbm.energy.IBatteryItem;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSelfcharger extends Item implements IBatteryItem {
	
	long charge;
	
	public ItemSelfcharger(final long charge, final String s) {
		this.charge = charge;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn){
		if(charge == Long.MAX_VALUE)
			tooltip.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.infinitehe"));
		else
			tooltip.add(TextFormatting.YELLOW + Library.getShortNumber(charge*20) + "HE/s");
	}
	
	@Override
	public void chargeBattery(final ItemStack stack, final long i) { }

	@Override
	public void setCharge(final ItemStack stack, final long i) { }

	@Override
	public void dischargeBattery(final ItemStack stack, final long i) { }

	@Override
	public long getCharge(final ItemStack stack) {
		return charge;
	}

	@Override
	public long getMaxCharge() {
		return charge;
	}

	@Override
	public long getChargeRate() {
		return 0;
	}

	@Override
	public long getDischargeRate() {
		return charge;
	}

}