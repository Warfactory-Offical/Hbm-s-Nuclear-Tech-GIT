package com.hbm.util;

import com.hbm.handler.ArmorModHandler;
import com.hbm.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

public class EntityDamageUtil {
	
	public static Field lastDamage = null;
	
	@SuppressWarnings("deprecation")
	public static boolean attackEntityFromIgnoreIFrame(final Entity victim, final DamageSource src, final float damage) {

		if(!victim.attackEntityFrom(src, damage)) {
			try {
				if(lastDamage == null)
					lastDamage = ReflectionHelper.findField(EntityLivingBase.class, "lastDamage", "field_110153_bc");
				
				final float dmg = damage + lastDamage.getFloat(victim);
				
				return victim.attackEntityFrom(src, dmg);
			} catch (final Exception x) {
				return false;
			}
		} else {
			return true;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static float getLastDamage(final Entity victim) {
		try {
			if(lastDamage == null)
				lastDamage = ReflectionHelper.findField(EntityLivingBase.class, "lastDamage", "field_110153_bc");

			return lastDamage.getFloat(victim);
		} catch(final Exception x) {
			return 0F;
		}
	}
	
	public static boolean wasAttackedByV1(final DamageSource source) {

		if(source instanceof EntityDamageSource) {
			final Entity attacker = source.getImmediateSource();
			
			if(attacker instanceof EntityPlayer player) {
                final ItemStack chestplate = player.inventory.armorInventory.get(2);
				
				if(chestplate != null && ArmorModHandler.hasMods(chestplate)) {
					final ItemStack[] mods = ArmorModHandler.pryMods(chestplate);

                    return mods[ArmorModHandler.extra] != null && mods[ArmorModHandler.extra].getItem() == ModItems.v1;
				}
			}
		}
		
		return false;
	}
}
