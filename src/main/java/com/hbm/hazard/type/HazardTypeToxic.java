package com.hbm.hazard.type;


import com.hbm.config.RadiationConfig;
import com.hbm.handler.ArmorUtil;
import com.hbm.hazard.helper.HazardHelper;
import com.hbm.hazard.modifier.HazardModifier;
import com.hbm.util.ArmorRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import com.hbm.util.I18nUtil;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class HazardTypeToxic extends HazardTypeBase {
	@Override
	public void onUpdate(EntityLivingBase target, float level, ItemStack stack) {

		if (RadiationConfig.disableToxic)
			return;
		boolean reacher = HazardHelper.isHoldingReacher(target);
		boolean hasToxFilter = false;
		boolean hasHazmat = false;

		if (target instanceof EntityPlayer) {
			if (ArmorRegistry.hasProtection(target, EntityEquipmentSlot.HEAD, ArmorRegistry.HazardClass.NERVE_AGENT)) {
				ArmorUtil.damageGasMaskFilter(target, 1);
				hasToxFilter = true;
			}
			hasHazmat = ArmorUtil.checkForHazmat((EntityPlayer) target);
		}

		if (!hasToxFilter && !hasHazmat && !reacher) {
			target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 110, (int) (level - 1)));

			if (level > 2)
				target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 110, (int) Math.min(4, level - 4)));
			if (level > 4)
				target.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 110, (int) level));
			if (level > 6) {
				if (target.world.rand.nextInt((int) (2000 / level)) == 0) {
					target.addPotionEffect(new PotionEffect(MobEffects.POISON, 110, (int) (level - 4)));
				}
			}
		}
		if (!(hasHazmat && hasToxFilter && reacher)) {
			if (level > 8)
				target.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 110, (int) (level - 8)));
			if (level > 16)
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 110, (int) (level - 16)));
		}
	}





    @Override
    public void updateEntity(EntityItem item, float level) { }

    @Override
	@SideOnly(Side.CLIENT)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<HazardModifier> modifiers) {
			if(level > 16)
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.extreme") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
			else if(level > 8)
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.veryhigh") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
			else if(level > 4)
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.high") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
			else if(level > 2)
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.medium") + " " + I18nUtil.resolveKey("trait.toxic") + "]");
			else
				list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("adjective.little") + " " + I18nUtil.resolveKey("trait.toxic") + "]");

    }
}
