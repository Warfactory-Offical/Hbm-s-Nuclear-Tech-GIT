package com.hbm.creativetabs;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.items.ModItems;

import api.hbm.energy.IBatteryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ControlTab extends CreativeTabs {

	public ControlTab(final int index, final String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		if(ModItems.pellet_rtg != null){
			return ItemStackUtil.itemStackFrom(ModItems.pellet_rtg);
		}
		return ItemStackUtil.itemStackFrom(Items.IRON_PICKAXE, 1);
	}
	
	@Override
	public void displayAllRelevantItems(final NonNullList<ItemStack> list) {
		super.displayAllRelevantItems(list);
		final List<ItemStack> batteries = new ArrayList<>();

		for(final Object o : list) {

			if(o instanceof ItemStack stack) {

                if(stack.getItem() instanceof IBatteryItem) {
					batteries.add(stack);
				}
			}
		}

		for(final ItemStack stack : batteries) {

			if(!(stack.getItem() instanceof IBatteryItem battery)) //shouldn't happen but just to make sure
				continue;

            final ItemStack empty = stack.copy();
			final ItemStack full = stack.copy();

			battery.setCharge(empty, 0);
			battery.setCharge(full, battery.getMaxCharge());

			final int index = list.indexOf(stack);

			list.remove(index);
			list.add(index, full);
			//do not list empty versions of SU batteries
			if(battery.getChargeRate() > 0)
				list.add(index, empty);
		}
	}
}
