package com.hbm.items.gear;

import com.hbm.handler.ArmorUtil;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class ArmorEuphemium extends ItemArmor implements ISpecialArmor {

	public ArmorEuphemium(final ArmorMaterial materialIn, final int renderIndexIn, final EntityEquipmentSlot equipmentSlotIn, final String s) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(null);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public String getArmorTexture(final ItemStack stack, final Entity entity, final EntityEquipmentSlot slot, final String type) {
		if(stack.getItem().equals(ModItems.euphemium_helmet) || stack.getItem().equals(ModItems.euphemium_plate) || stack.getItem().equals(ModItems.euphemium_boots)) {
			return (RefStrings.MODID + ":textures/armor/euphemium_1.png");
		}
		if(stack.getItem().equals(ModItems.euphemium_legs)) {
			return (RefStrings.MODID + ":textures/armor/euphemium_2.png");
		}
		return null;
	}

	@Override
	public ArmorProperties getProperties(final EntityLivingBase player, final ItemStack armor, final DamageSource source, final double damage, final int slot) {
		if(player instanceof EntityPlayer && ArmorUtil.checkArmor(player, ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots))
		{
			return new ArmorProperties(1, 1, MathHelper.floor(999999999));
		}
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int getArmorDisplay(final EntityPlayer player, final ItemStack armor, final int slot) {
		if(slot == 0)
		{
			return 3;
		}
		if(slot == 1)
		{
			return 8;
		}
		if(slot == 2)
		{
			return 6;
		}
		if(slot == 3)
		{
			return 3;
		}
		return 0;
	}

	@Override
	public void damageArmor(final EntityLivingBase entity, final ItemStack stack, final DamageSource source, final int damage, final int slot) {
		stack.damageItem(0, entity);
	}
	
	@Override
	public EnumRarity getRarity(final ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
	@Override
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
		if(player instanceof EntityPlayer && ArmorUtil.checkArmor(player, ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots))
		{
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5, 127, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 127, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 5, 127, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 5, 127, true, false));
		 
			if(player.motionY < -0.25D)
			{
				player.motionY = -0.25D;
				player.fallDistance = 0;
			}
			
		}
	}
	
	@Override
	public void setDamage(final ItemStack stack, final int damage) {}
	
	@Override
	public int getDamage(final ItemStack stack) {
		return 0;
	}
	
	@Override
	public int getMaxDamage() {
		return Integer.MAX_VALUE;
	}
	
}
