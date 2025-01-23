package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class ItemModPolish extends ItemArmorMod {

	public ItemModPolish(final String s) {
		super(ArmorModHandler.extra, true, true, true, true, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.BLUE + "5% chance to nullify damage");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.BLUE + "  " + stack.getDisplayName() + " (5% chance to nullify damage)");
	}
	
	@Override
	public void modDamage(final LivingHurtEvent event, final ItemStack armor) {
		if(event.getEntity().world.rand.nextInt(20) == 0)
			event.setAmount(0);
	}
}