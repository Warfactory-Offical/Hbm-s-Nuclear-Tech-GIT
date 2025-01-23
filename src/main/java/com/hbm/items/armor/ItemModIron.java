package com.hbm.items.armor;

import com.google.common.collect.Multimap;
import com.hbm.handler.ArmorModHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModIron extends ItemArmorMod {

	public ItemModIron(final String s) {
		super(ArmorModHandler.cladding, true, true, true, true, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.WHITE + "+0.5 knockback resistance");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.WHITE + "  " + stack.getDisplayName() + " (+0.5 knockback resistence)");
	}
	
	@Override
	public Multimap<String, AttributeModifier> getModifiers(final EntityEquipmentSlot slot, final ItemStack armor){
		final Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, armor);
		multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(),
				new AttributeModifier(ArmorModHandler.UUIDs[((ItemArmor)armor.getItem()).armorType.getIndex()], "NTM Armor Mod Knockback", 0.5, 0));
		return multimap;
	}
	
}
