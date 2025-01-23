package com.hbm.items.armor;

import com.google.common.collect.Multimap;
import com.hbm.handler.ArmorModHandler;
import com.hbm.items.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModHealth extends ItemArmorMod {
	
	float health;

	public ItemModHealth(final float health, final String s) {
		super(ArmorModHandler.extra, false, true, false, false, s);
		this.health = health;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		final String color = "" + (System.currentTimeMillis() % 1000 < 500 ? TextFormatting.RED : TextFormatting.LIGHT_PURPLE);

		list.add(color + "+" + (Math.round(health * 10 / 2) * 0.1) + " health");
		if(this == ModItems.heart_piece)
			list.add("Dropped by 1:1000 Mobs");
		list.add("");
		
		if(this == ModItems.black_diamond) {
			list.add(TextFormatting.DARK_GRAY + "Nostalgia");
			list.add("");
		}
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		
		final String color = "" + (System.currentTimeMillis() % 1000 < 500 ? TextFormatting.RED : TextFormatting.LIGHT_PURPLE);
		
		list.add(color + "  " + stack.getDisplayName() + " (+" + (Math.round(health * 10 / 2) * 0.1) + " health)");
	}
	
	@Override
	public Multimap<String, AttributeModifier> getModifiers(final EntityEquipmentSlot slot, final ItemStack armor){
		final Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, armor);
		multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(),
				new AttributeModifier(ArmorModHandler.UUIDs[((ItemArmor)armor.getItem()).armorType.getIndex()], "NTM Armor Mod Health", health, 0));
		return multimap;
	}

}