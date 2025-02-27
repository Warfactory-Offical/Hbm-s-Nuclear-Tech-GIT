package com.hbm.items.armor;

import com.google.common.collect.Multimap;
import com.hbm.config.PotionConfig;
import com.hbm.handler.ArmorModHandler;
import com.hbm.items.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModServos extends ItemArmorMod {

	public ItemModServos(final String s){
		super(ArmorModHandler.servos, false, true, true, false, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		if(this == ModItems.servo_set) {
			list.add(TextFormatting.DARK_PURPLE + "Chestplate: Haste I / Damage +50%");
			list.add(TextFormatting.DARK_PURPLE + "Leggings: Speed +25% / Jump II");
		}
		if(this == ModItems.servo_set_desh) {
			list.add(TextFormatting.DARK_PURPLE + "Chestplate: Haste III / Damage +150%");
			list.add(TextFormatting.DARK_PURPLE + "Leggings: Speed +50% / Jump III");
		}
		
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	
	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor){
		final ItemArmor item = (ItemArmor)armor.getItem();
		
		if(item.armorType == EntityEquipmentSlot.CHEST) {

			if(this == ModItems.servo_set) {
				list.add(TextFormatting.DARK_PURPLE + "  " + stack.getDisplayName() + " (Haste I / Damage +50%)");
			}
			if(this == ModItems.servo_set_desh) {
				list.add(TextFormatting.DARK_PURPLE + "  " + stack.getDisplayName() + " (Haste III / Damage +150%)");
			}
		}
		
		if(item.armorType == EntityEquipmentSlot.LEGS) {

			if(this == ModItems.servo_set) {
				list.add(TextFormatting.DARK_PURPLE + "  " + stack.getDisplayName() + " (Speed +25% / Jump II)");
			}
			if(this == ModItems.servo_set_desh) {
				list.add(TextFormatting.DARK_PURPLE + "  " + stack.getDisplayName() + " (Speed +50% / Jump III)");
			}
		}
	}
	
	@Override
	public void modUpdate(final EntityLivingBase entity, final ItemStack armor){
		final ItemArmor item = (ItemArmor)armor.getItem();
		
		if(item.armorType == EntityEquipmentSlot.CHEST) {

			if(this == ModItems.servo_set) {
				entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 65, 0, false, false));
			}
			if(this == ModItems.servo_set_desh) {
				entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 65, 2, false, false));
			}
		}
		
		if(PotionConfig.doJumpBoost && item.armorType == EntityEquipmentSlot.LEGS) {

			if(this == ModItems.servo_set) {
				entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 65, 1, false, false));
			}
			if(this == ModItems.servo_set_desh) {
				entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 65, 2, false, false));
			}
		}
	}
	
	@Override
	public Multimap<String, AttributeModifier> getModifiers(final EntityEquipmentSlot slot, final ItemStack armor){
		final Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, armor);
		
		final ItemArmor item = (ItemArmor)armor.getItem();
		
		if(item.armorType == EntityEquipmentSlot.CHEST) {
			if(this == ModItems.servo_set)
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ArmorModHandler.UUIDs[((ItemArmor)armor.getItem()).armorType.getIndex()], "NTM Armor Mod Servos", 0.5, 2));
			
			if(this == ModItems.servo_set_desh)
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ArmorModHandler.UUIDs[((ItemArmor)armor.getItem()).armorType.getIndex()], "NTM Armor Mod Servos", 1.5, 2));
		}
		
		if(item.armorType == EntityEquipmentSlot.LEGS) {
			if(this == ModItems.servo_set)
				multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(),
						new AttributeModifier(ArmorModHandler.UUIDs[((ItemArmor)armor.getItem()).armorType.getIndex()], "NTM Armor Mod Servos", 0.25, 2));
			
			if(this == ModItems.servo_set_desh)
				multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(),
						new AttributeModifier(ArmorModHandler.UUIDs[((ItemArmor)armor.getItem()).armorType.getIndex()], "NTM Armor Mod Servos", 0.5, 2));
		}
		
		return multimap;
	}
}
