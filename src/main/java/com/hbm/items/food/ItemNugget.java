package com.hbm.items.food;

import com.hbm.items.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemNugget extends ItemFood {

	public ItemNugget(final int amount, final boolean isWolfFood, final String s) {
		super(amount, isWolfFood);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if(this == ModItems.gun_moist_nugget){
			tooltip.add("A Mosin-Na...no wait, it's");
			tooltip.add("just a moist nugget.");
		}
	}
	
}
