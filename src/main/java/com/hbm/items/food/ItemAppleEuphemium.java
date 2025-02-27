package com.hbm.items.food;

import com.hbm.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAppleEuphemium extends ItemFood {

	public ItemAppleEuphemium(final int amount, final float saturation, final boolean isWolfFood, final String s) {
		super(amount, saturation, isWolfFood);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(final ItemStack stack) {
		return true;
	}
	
	@Override
	protected void onFoodEaten(final ItemStack stack, final World worldIn, final EntityPlayer player) {
		if (!worldIn.isRemote)
        {
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2147483647, 127));
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 2147483647, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 2147483647, 127));
        }
	}
	
	@Override
	public EnumRarity getRarity(final ItemStack stack) {
		return EnumRarity.EPIC;
	}

}
