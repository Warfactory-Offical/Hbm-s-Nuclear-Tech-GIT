package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class ItemModMorningGlory extends ItemArmorMod {

	public ItemModMorningGlory(final String s) {
		super(ArmorModHandler.extra, true, true, true, true, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.LIGHT_PURPLE + "5% chance to apply resistance when hit, wither immunity");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.LIGHT_PURPLE + "  " + stack.getDisplayName() + " (5% for resistance, wither immunity)");
	}
	
	@Override
	public void modDamage(final LivingHurtEvent event, final ItemStack armor) {
		if(!event.getEntity().world.isRemote && event.getEntity().world.rand.nextInt(20) == 0) {
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 4));
		}
	}
	
	@Override
	public void modUpdate(final EntityLivingBase entity, final ItemStack armor) {
		
		if(!entity.world.isRemote && entity.isPotionActive(MobEffects.WITHER)) {
			entity.removePotionEffect(MobEffects.WITHER);
		}
	}
}