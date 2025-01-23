package com.hbm.items.armor;

import com.hbm.capability.HbmLivingProps;
import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class ItemModQuartz extends ItemArmorMod {

	public ItemModQuartz(final String s) {
		super(ArmorModHandler.extra, true, true, true, true, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.DARK_GRAY + "Taking damage removes 10 RAD");
		list.add("Dropped by 1:64 Smelted Uranium Ingots");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	
	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.DARK_GRAY + "  " + stack.getDisplayName() + " (-10 RAD when hit)");
	}
	
	@Override
	public void modDamage(final LivingHurtEvent event, final ItemStack armor) {
		
		if(!event.getEntityLiving().world.isRemote) {
			HbmLivingProps.incrementRadiation(event.getEntityLiving(), -10);
		}
	}
}
