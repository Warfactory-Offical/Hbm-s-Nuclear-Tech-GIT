package com.hbm.handler;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.potion.HbmPotion;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.Compat;

import api.hbm.item.IGasMask;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ArmorUtil {

	public static void register() {
		ArmorRegistry.registerHazard(ModItems.gas_mask_filter, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.NERVE_AGENT);
		ArmorRegistry.registerHazard(ModItems.gas_mask_filter_mono, HazardClass.PARTICLE_COARSE, HazardClass.GAS_MONOXIDE);
		ArmorRegistry.registerHazard(ModItems.gas_mask_filter_combo, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.NERVE_AGENT);
		ArmorRegistry.registerHazard(ModItems.gas_mask_filter_radon, HazardClass.RAD_GAS, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.NERVE_AGENT);
		ArmorRegistry.registerHazard(ModItems.gas_mask_filter_rag, HazardClass.PARTICLE_COARSE);
		ArmorRegistry.registerHazard(ModItems.gas_mask_filter_piss, HazardClass.PARTICLE_COARSE, HazardClass.GAS_CHLORINE);

		ArmorRegistry.registerHazard(ModItems.gas_mask, HazardClass.SAND, HazardClass.LIGHT);
		ArmorRegistry.registerHazard(ModItems.gas_mask_m65, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.mask_damp, HazardClass.PARTICLE_COARSE);
		ArmorRegistry.registerHazard(ModItems.mask_piss, HazardClass.PARTICLE_COARSE, HazardClass.GAS_CHLORINE);
		
		ArmorRegistry.registerHazard(ModItems.goggles, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.ashglasses, HazardClass.LIGHT, HazardClass.SAND);

		ArmorRegistry.registerHazard(ModItems.attachment_mask, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.spider_milk, HazardClass.LIGHT);
		
		ArmorRegistry.registerHazard(ModItems.asbestos_helmet, HazardClass.SAND, HazardClass.LIGHT);
		ArmorRegistry.registerHazard(ModItems.hazmat_helmet, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.hazmat_helmet_red, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.hazmat_helmet_grey, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.liquidator_helmet, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.hazmat_paa_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.paa_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.t45_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.ajr_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.ajro_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.rpa_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.hev_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.fau_helmet, HazardClass.RAD_GAS, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.dns_helmet, HazardClass.RAD_GAS, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND, HazardClass.GAS_CORROSIVE);
		ArmorRegistry.registerHazard(ModItems.schrabidium_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		ArmorRegistry.registerHazard(ModItems.euphemium_helmet, HazardClass.RAD_GAS, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		
		//Ob ihr wirklich richtig steht, seht ihr wenn das Licht angeht!
		registerIfExists("gregtech", "gt.armor.hazmat.universal.head", HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		registerIfExists("gregtech", "gt.armor.hazmat.biochemgas.head", HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
		registerIfExists("gregtech", "gt.armor.hazmat.radiation.head", HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_CHLORINE, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
	}
	
	private static void registerIfExists(final String domain, final String name, final HazardClass... classes) {
		final Item item = Compat.tryLoadItem(domain, name);
		if(item != null)
			ArmorRegistry.registerHazard(item, classes);
	}
	
	public static boolean checkForFaraday(final EntityPlayer player) {
		
		final NonNullList<ItemStack> armor = player.inventory.armorInventory;
		
		if(armor.get(0).isEmpty() || armor.get(1).isEmpty() || armor.get(2).isEmpty() || armor.get(3).isEmpty()) return false;

        return ArmorUtil.isFaradayArmor(armor.get(0)) &&
                ArmorUtil.isFaradayArmor(armor.get(1)) &&
                ArmorUtil.isFaradayArmor(armor.get(2)) &&
                ArmorUtil.isFaradayArmor(armor.get(3));
    }

	public static boolean isFaradayArmor(final ItemStack item) {
		
		final String name = item.getTranslationKey();
		
		if(HazmatRegistry.getCladding(item) > 0)
			return true;
		
		for(final String metal : metals) {
			
			if(name.toLowerCase().contains(metal))
				return true;
		}
		
		return false;
	}
	
	public static boolean checkArmorNull(final EntityLivingBase player, final EntityEquipmentSlot slot) {
		return player.getItemStackFromSlot(slot) == null || player.getItemStackFromSlot(slot).isEmpty();
	}

	public static final String[] metals = new String[] {
			"chainmail",
			"iron",
			"silver",
			"gold",
			"platinum",
			"tin",
			"lead",
			"liquidator",
			"schrabidium",
			"euphemium",
			"steel",
			"cmb",
			"titanium",
			"alloy",
			"copper",
			"bronze",
			"electrum",
			"t45",
			"bj",
			"starmetal",
			"hazmat", //also count because rubber is insulating
			"rubber",
			"hev",
			"ajr",
			"rpa",
			"spacesuit"
	};

	public static void damageSuit(final EntityPlayer player, final int slot, final int amount) {
	
		if(player.inventory.armorInventory.get(slot) == ItemStack.EMPTY)
			return;
	
		int j = player.inventory.armorInventory.get(slot).getItemDamage();
		player.inventory.armorInventory.get(slot).setItemDamage(j += amount);
	
		if(player.inventory.armorInventory.get(slot).getItemDamage() >= player.inventory.armorInventory.get(slot).getMaxDamage()) {
			player.inventory.armorInventory.set(slot, ItemStack.EMPTY);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void resetFlightTime(final EntityPlayer player) {
		if(player instanceof EntityPlayerMP mp) {
            ReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, mp.connection, 0, "floatingTickCount", "field_147365_f");
		}
	}

	public static boolean checkForFiend2(final EntityPlayer player) {
		
		return ArmorUtil.checkArmorPiece(player, ModItems.jackt2, 2) && Library.checkForHeld(player, ModItems.shimmer_axe);
	}

	public static boolean checkForFiend(final EntityPlayer player) {
		
		return ArmorUtil.checkArmorPiece(player, ModItems.jackt, 2) && Library.checkForHeld(player, ModItems.shimmer_sledge);
	}

	public static boolean checkPAA(final EntityLivingBase player){
		return checkArmor(player, ModItems.paa_helmet, ModItems.paa_plate, ModItems.paa_legs, ModItems.paa_boots);
	}

	// Drillgon200: Is there a reason for this method? I don't know and I don't
	// care to find out.
	// Alcater: Looks like some kind of hazmat tier 2 check
	public static boolean checkForHaz2(final EntityLivingBase player) {

        return checkArmor(player, ModItems.hazmat_paa_helmet, ModItems.hazmat_paa_plate, ModItems.hazmat_paa_legs, ModItems.hazmat_paa_boots) ||
                checkArmor(player, ModItems.paa_helmet, ModItems.paa_plate, ModItems.paa_legs, ModItems.paa_boots) ||
                checkArmor(player, ModItems.liquidator_helmet, ModItems.liquidator_plate, ModItems.liquidator_legs, ModItems.liquidator_boots) ||
                checkArmor(player, ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots) ||
                checkArmor(player, ModItems.hev_helmet, ModItems.hev_plate, ModItems.hev_legs, ModItems.hev_boots) ||
                checkArmor(player, ModItems.ajr_helmet, ModItems.ajr_plate, ModItems.ajr_legs, ModItems.ajr_boots) ||
                checkArmor(player, ModItems.ajro_helmet, ModItems.ajro_plate, ModItems.ajro_legs, ModItems.ajro_boots) ||
                checkArmor(player, ModItems.rpa_helmet, ModItems.rpa_plate, ModItems.rpa_legs, ModItems.rpa_boots) ||
                checkArmor(player, ModItems.fau_helmet, ModItems.fau_plate, ModItems.fau_legs, ModItems.fau_boots) ||
                checkArmor(player, ModItems.dns_helmet, ModItems.dns_plate, ModItems.dns_legs, ModItems.dns_boots);
    }

	public static boolean checkForHazmatOnly(final EntityLivingBase player) {
        return checkArmor(player, ModItems.hazmat_helmet, ModItems.hazmat_plate, ModItems.hazmat_legs, ModItems.hazmat_boots) ||
                checkArmor(player, ModItems.hazmat_helmet_red, ModItems.hazmat_plate_red, ModItems.hazmat_legs_red, ModItems.hazmat_boots_red) ||
                checkArmor(player, ModItems.hazmat_helmet_grey, ModItems.hazmat_plate_grey, ModItems.hazmat_legs_grey, ModItems.hazmat_boots_grey) ||
                checkArmor(player, ModItems.hazmat_paa_helmet, ModItems.hazmat_paa_plate, ModItems.hazmat_paa_legs, ModItems.hazmat_paa_boots) ||
                checkArmor(player, ModItems.paa_helmet, ModItems.paa_plate, ModItems.paa_legs, ModItems.paa_boots) ||
                checkArmor(player, ModItems.liquidator_helmet, ModItems.liquidator_plate, ModItems.liquidator_legs, ModItems.liquidator_boots);
    }

	public static boolean checkForHazmat(final EntityLivingBase player) {
		if(ArmorUtil.checkArmor(player, ModItems.hazmat_helmet, ModItems.hazmat_plate, ModItems.hazmat_legs, ModItems.hazmat_boots) || 
			ArmorUtil.checkArmor(player, ModItems.hazmat_helmet_red, ModItems.hazmat_plate_red, ModItems.hazmat_legs_red, ModItems.hazmat_boots_red) || 
			ArmorUtil.checkArmor(player, ModItems.hazmat_helmet_grey, ModItems.hazmat_plate_grey, ModItems.hazmat_legs_grey, ModItems.hazmat_boots_grey) || 
			ArmorUtil.checkArmor(player, ModItems.t45_helmet, ModItems.t45_plate, ModItems.t45_legs, ModItems.t45_boots) || 
			ArmorUtil.checkArmor(player, ModItems.schrabidium_helmet, ModItems.schrabidium_plate, ModItems.schrabidium_legs, ModItems.schrabidium_boots) || 
			checkForHaz2(player)) {
	
			return true;
		}

        return player.isPotionActive(HbmPotion.mutation);
    }

	public static boolean checkForAsbestos(final EntityLivingBase player) {

        return ArmorUtil.checkArmor(player, ModItems.asbestos_helmet, ModItems.asbestos_plate, ModItems.asbestos_legs, ModItems.asbestos_boots);
    }

	public static boolean checkArmor(final EntityLivingBase player, final Item helm, final Item chest, final Item leg, final Item shoe) {
        return player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == shoe &&
                player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == leg &&
                player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == chest &&
                player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == helm;
    }

	public static boolean checkArmorPiece(final EntityPlayer player, final Item armor, final int slot) {
        return player.inventory.armorInventory.get(slot) != null && player.inventory.armorInventory.get(slot).getItem() == armor;
    }
	
	/*
	 * Default implementations for IGasMask items
	 */
	public static final String FILTERK_KEY = "hfrFilter";
	
	public static void damageGasMaskFilter(final EntityLivingBase entity, final int damage) {
		
		ItemStack mask = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		
		if(mask == null)
			return;
		
		if(!(mask.getItem() instanceof IGasMask)) {
			
			if(ArmorModHandler.hasMods(mask)) {
				
				final ItemStack[] mods = ArmorModHandler.pryMods(mask);
				
				if(mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
					mask = mods[ArmorModHandler.helmet_only];
			}
		}
		
		if(mask != null)
			damageGasMaskFilter(mask, damage);
	}
	
	public static void damageGasMaskFilter(final ItemStack mask, final int damage) {
		ItemStack filter = getGasMaskFilter(mask);
		
		if(filter == null) {
			if(ArmorModHandler.hasMods(mask)) {
				final ItemStack[] mods = ArmorModHandler.pryMods(mask);
				
				if(mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
					filter = getGasMaskFilter(mods[ArmorModHandler.helmet_only]);
			}
		}
		
		if(filter == null || filter.getMaxDamage() == 0)
			return;
		
		filter.setItemDamage(filter.getItemDamage() + damage);
		
		if(filter.getItemDamage() > filter.getMaxDamage()){
			removeFilter(mask);
		}
		else{
			installGasMaskFilter(mask, filter);
		}
	}
	
	public static void installGasMaskFilter(final ItemStack mask, final ItemStack filter) {
		
		if(mask == null || filter == null)
			return;
		
		if(!mask.hasTagCompound())
			mask.setTagCompound(new NBTTagCompound());
		
		final NBTTagCompound attach = new NBTTagCompound();
		filter.writeToNBT(attach);
		
		mask.getTagCompound().setTag(FILTERK_KEY, attach);
	}
	
	public static void removeFilter(final ItemStack mask) {
		
		if(mask == null)
			return;
		
		if(!mask.hasTagCompound())
			return;
		
		mask.getTagCompound().removeTag(FILTERK_KEY);
		if(mask.getTagCompound().isEmpty())
			mask.setTagCompound(null);
	}
	
	public static ItemStack getGasMaskFilter(final ItemStack mask) {
		
		if(mask == null)
			return null;
		
		if(!mask.hasTagCompound())
			return null;
		
		final NBTTagCompound attach = mask.getTagCompound().getCompoundTag(FILTERK_KEY);
		final ItemStack filter = ItemStackUtil.itemStackFrom(attach);
		if(filter.isEmpty())
			return null;
		return filter;
	}
	
	public static boolean checkForDigamma(final EntityPlayer player) {
		
		if(checkArmor(player, ModItems.fau_helmet, ModItems.fau_plate, ModItems.fau_legs, ModItems.fau_boots))
			return true;

		if(checkArmor(player, ModItems.dns_helmet, ModItems.dns_plate, ModItems.dns_legs, ModItems.dns_boots))
			return true;

        return player.isPotionActive(HbmPotion.stability);
    }
	
	public static boolean checkForMonoMask(final EntityPlayer player) {

		if(checkArmorPiece(player, ModItems.gas_mask, 3)) {
			return true;
		}
		if(checkArmorPiece(player, ModItems.gas_mask_m65, 3)) {
			return true;
		}

		if(checkArmorPiece(player, ModItems.gas_mask_mono, 3))
			return true;
		
		if(checkArmorPiece(player, ModItems.liquidator_helmet, 3))
			return true;

		if(player.isPotionActive(HbmPotion.mutation))
			return true;
		
		final ItemStack helmet = player.inventory.armorInventory.get(3);
		if(helmet != null && ArmorModHandler.hasMods(helmet)) {
			
			final ItemStack[] mods = ArmorModHandler.pryMods(helmet);

            return mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() == ModItems.attachment_mask_mono;
		}
		
		return false;
	}
	
	public static boolean checkForGoggles(final EntityPlayer player) {

		if(checkArmorPiece(player, ModItems.goggles, 3))
		{
			return true;
		}
		if(checkArmorPiece(player, ModItems.ashglasses, 3))
		{
			return true;
		}
		if(checkArmorPiece(player, ModItems.t45_helmet, 3))
		{
			return true;
		}
		if(checkArmorPiece(player, ModItems.ajr_helmet, 3))
		{
			return true;
		}
		if(checkArmorPiece(player, ModItems.rpa_helmet, 3))
		{
			return true;
		}
		if(checkArmorPiece(player, ModItems.bj_helmet, 3))
		{
			return true;
		}
		if(checkArmorPiece(player, ModItems.hev_helmet, 3))
		{
			return true;
		}
		if(checkArmorPiece(player, ModItems.hazmat_paa_helmet, 3))
		{
			return true;
		}
        return checkArmorPiece(player, ModItems.paa_helmet, 3);
    }

	/**
	 * Grabs the installed filter or the filter of the attachment, used for attachment rendering
	 * @param mask
	 * @param entity
	 * @return
	 */
	public static ItemStack getGasMaskFilterRecursively(final ItemStack mask) {
		
		ItemStack filter = getGasMaskFilter(mask);
		
		if((filter == null || filter.isEmpty()) && ArmorModHandler.hasMods(mask)) {
			
			final ItemStack[] mods = ArmorModHandler.pryMods(mask);
			
			if(mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
				filter = ((IGasMask)mods[ArmorModHandler.helmet_only].getItem()).getFilter(mods[ArmorModHandler.helmet_only]);
		}
		
		return filter;
	}

	public static void addGasMaskTooltip(final ItemStack mask, final World world, final List<String> list, final ITooltipFlag flagIn){
		
		if(mask == null || !(mask.getItem() instanceof IGasMask))
			return;
		
		final ItemStack filter = ((IGasMask)mask.getItem()).getFilter(mask);
		
		if(filter == null) {
			list.add("§c" + I18nUtil.resolveKey("desc.nofilter"));
			return;
		}
		
		list.add("§6" + I18nUtil.resolveKey("desc.infilter"));
		
		final int meta = filter.getItemDamage();
		final int max = filter.getMaxDamage();
		
		String append = "";
		
		if(max > 0) {
			append = " (" + Library.getPercentage((max - meta) / (double)max) + "%) "+(max-meta)+"/"+max;
		}
		
		final List<String> lore = new ArrayList();
		list.add("  " + filter.getDisplayName() + append);
		filter.getItem().addInformation(filter, world, lore, flagIn);
		ForgeEventFactory.onItemTooltip(filter, null, lore, flagIn);
		lore.forEach(x -> list.add("§e  " + x));
	}
	
	// public static boolean isWearingEmptyMask(EntityPlayer player) {
		
	// 	ItemStack mask = player.getEquipmentInSlot(4);
		
	// 	if(mask == null)
	// 		return false;
		
	// 	if(mask.getItem() instanceof IGasMask) {
	// 		return getGasMaskFilter(mask) == null;
	// 	}
		
	// 	ItemStack mod = ArmorModHandler.pryMods(mask)[ArmorModHandler.helmet_only];
		
	// 	if(mod != null && mod.getItem() instanceof IGasMask) {
	// 		return getGasMaskFilter(mod) == null;
	// 	}
		
	// 	return false;
	// }
}