package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModSerum extends ItemArmorMod {

	public ItemModSerum(final String s) {
		super(ArmorModHandler.extra, true, true, true, true, s);
	}
	
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.GREEN + "Cures poison and gives strength");
		list.add("Dropped by 1:100 Cave Spiders");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	
	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.BLUE + "  " + stack.getDisplayName() + " (replaces poison with strength)");
	}
	
	@Override
	public void modUpdate(final EntityLivingBase entity, final ItemStack armor) {
		if(!entity.world.isRemote && entity.isPotionActive(MobEffects.POISON)) {
			entity.removePotionEffect(MobEffects.POISON);
			entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100, 4));
		}
	}
}
