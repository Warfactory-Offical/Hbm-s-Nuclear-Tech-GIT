package com.hbm.hazard_old.type;

import com.hbm.config.RadiationConfig;
import com.hbm.hazard_old.modifier.HazardModifier;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.I18nUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class HazardTypeBlinding extends HazardTypeBase {

	@Override
	public void onUpdate(final EntityLivingBase target, final float level, final ItemStack stack) {
		
		if(RadiationConfig.disableBlinding)
			return;

		if(!ArmorRegistry.hasProtection(target, EntityEquipmentSlot.HEAD, HazardClass.LIGHT)) {
			target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (int)level, 0));
		}
		}


	@Override
	public void updateEntity(final EntityItem item, final float level) { }

	@Override
	@SideOnly(Side.CLIENT)
	public void addHazardInformation(final EntityPlayer player, final List list, final float level, final ItemStack stack, final List<HazardModifier> modifiers) {
		list.add(TextFormatting.DARK_AQUA + "[" + I18nUtil.resolveKey("trait.blinding") + "]");
	}

}
