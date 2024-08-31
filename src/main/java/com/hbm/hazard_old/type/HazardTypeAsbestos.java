package com.hbm.hazard_old.type;

import com.hbm.capability.HbmLivingProps;
import com.hbm.config.RadiationConfig;
import com.hbm.handler.ArmorUtil;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.I18nUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class HazardTypeAsbestos extends HazardTypeBase {

	@Override
	public void onUpdate(EntityLivingBase target, float level, ItemStack stack) {

		if (RadiationConfig.disableAsbestos)
			return;

		if (ArmorRegistry.hasProtection(target, EntityEquipmentSlot.HEAD, HazardClass.PARTICLE_FINE))
			ArmorUtil.damageGasMaskFilter(target, (int) level*hazardRate);
		else
			HbmLivingProps.incrementAsbestos(target, (int) Math.min(level, 10)*hazardRate);
	}


	@Override
	public void updateEntity(EntityItem item, float level) { }

	@Override
	@SideOnly(Side.CLIENT)
	public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<com.hbm.hazard_old.modifier.HazardModifier> modifiers) {
		list.add(TextFormatting.WHITE + "[" + I18nUtil.resolveKey("trait.asbestos") + "]");
	}
}
