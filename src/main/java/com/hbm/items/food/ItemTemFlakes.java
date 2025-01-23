package com.hbm.items.food;

import com.hbm.items.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemTemFlakes extends ItemFood {

	public ItemTemFlakes(final int amount, final float saturation, final boolean isWolfFood, final String s) {
		super(amount, saturation, isWolfFood);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setAlwaysEdible();
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	protected void onFoodEaten(final ItemStack stack, final World worldIn, final EntityPlayer player) {
		player.heal(2.0F);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if(stack.getItem() == ModItems.tem_flakes)
		{
			tooltip.add("Heals 2HP DISCOUNT FOOD OF TEM!!!");
		}
		if(stack.getItem() == ModItems.tem_flakes1)
		{
			tooltip.add("Heals 2HP food of tem");
		}
		if(stack.getItem() == ModItems.tem_flakes2)
		{
			tooltip.add("Heals food of tem (expensiv)");
		}
	}
}
