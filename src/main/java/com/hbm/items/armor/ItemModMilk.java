package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ItemModMilk extends ItemArmorMod {
	
	public ItemModMilk(final String s) {
		super(ArmorModHandler.extra, true, true, true, true, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.WHITE + "Removes bad potion effects");
		list.add("Dropped by 1:500 Spiders");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.WHITE + "  " + stack.getDisplayName() + " (Removes bad potion effects)");
	}
	
	@Override
	public void modUpdate(final EntityLivingBase entity, final ItemStack armor) {
		
		final List<Potion> pots = new ArrayList<>();

		final Iterator<PotionEffect> iterator = entity.getActivePotionEffects().iterator();

		while(iterator.hasNext()) {
			final PotionEffect eff = iterator.next();

			if(eff.getPotion().isBadEffect()) {
				pots.add(eff.getPotion());
			}
		}

		for(final Potion p : pots) {
			entity.removePotionEffect(p);
		}
	}
}