package com.hbm.hazard_old.type;

import com.hbm.config.RadiationConfig;
import com.hbm.hazard_old.modifier.HazardModifier;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class HazardTypeBase {
	public static int hazardRate = RadiationConfig.hazardRate;
	
	/**
	 * Does the thing. Called by HazardEntry.applyHazard
	 * @param target the holder
	 * @param level the final level after calculating all the modifiers
	 * @param the stack that is being updated
	 */
	public abstract void onUpdate(EntityLivingBase target, float level, ItemStack stack);

	/**
	 * Updates the hazard for dropped items. Used for things like explosive and hydroactive items.
	 * @param item
	 * @param level
	 */
	public abstract void updateEntity(EntityItem item, float level);
	
	/**
	 * Adds item tooltip info. Called by Item.addInformation
	 * @param player
	 * @param list
	 * @param level the base level, mods are passed separately
	 * @param stack
	 * @param modifiers
	 */
	@SideOnly(Side.CLIENT)
	public abstract void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<HazardModifier> modifiers);
}
