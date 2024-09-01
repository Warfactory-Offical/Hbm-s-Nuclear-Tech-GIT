package com.hbm.hazard_old.type;


import com.hbm.config.RadiationConfig;
import com.hbm.handler.ArmorUtil;
import com.hbm.hazard_old.helper.HazardHelper;
import com.hbm.hazard_old.modifier.HazardModifier;
import com.hbm.util.ArmorRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import com.hbm.util.I18nUtil;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.hbm.hazard_old.helper.HazardHelper.applyPotionEffect;

public class HazardTypeToxic extends HazardTypeBase {
	@Override
	public void onUpdate(EntityLivingBase target, float level, ItemStack stack) {

		if (RadiationConfig.disableToxic) return;

		boolean reacher = HazardHelper.isHoldingReacher(target);
		boolean hasToxFilter = false;
		boolean hasHazmat = false;

		if (target instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) target;
			hasToxFilter = ArmorRegistry.hasProtection(player, EntityEquipmentSlot.HEAD, ArmorRegistry.HazardClass.NERVE_AGENT);

			if (hasToxFilter) {
				ArmorUtil.damageGasMaskFilter(player, 1);
			}

			hasHazmat = ArmorUtil.checkForHazmat(player);
		}

		boolean isUnprotected = !(hasToxFilter || hasHazmat || reacher);

		if (isUnprotected) {
			applyPotionEffect(target, MobEffects.WEAKNESS, 110, (int) (level - 1));

			if (level > 2) {
				applyPotionEffect(target, MobEffects.SLOWNESS, 110, (int) Math.min(4, level - 4));
			}

			if (level > 4) {
				applyPotionEffect(target, MobEffects.HUNGER, 110, (int) level);
			}

			if (level > 6 && target.world.rand.nextInt((int) (2000 / level)) == 0) {
				applyPotionEffect(target, MobEffects.POISON, 110, (int) (level - 4));
			}
		}

		if (!hasHazmat || !hasToxFilter || !reacher) {
			if (level > 8) {
				applyPotionEffect(target, MobEffects.MINING_FATIGUE, 110, (int) (level - 8));
			}

			if (level > 16) {
				applyPotionEffect(target, MobEffects.INSTANT_DAMAGE, 110, (int) (level - 16));
			}
		}
	}





    @Override
    public void updateEntity(EntityItem item, float level) { }

    @Override
	@SideOnly(Side.CLIENT)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<HazardModifier> modifiers) {
		String adjectiveKey;

		if (level > 16) {
			adjectiveKey = "adjective.extreme";
		} else if (level > 8) {
			adjectiveKey = "adjective.veryhigh";
		} else if (level > 4) {
			adjectiveKey = "adjective.high";
		} else if (level > 2) {
			adjectiveKey = "adjective.medium";
		} else {
			adjectiveKey = "adjective.little";
		}

		list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey(adjectiveKey) + " " + I18nUtil.resolveKey("trait.toxic") + "]");
    }
}