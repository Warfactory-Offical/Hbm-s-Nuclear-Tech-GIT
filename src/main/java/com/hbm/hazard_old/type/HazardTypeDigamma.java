package com.hbm.hazard_old.type;

import com.hbm.hazard_old.modifier.HazardModifier;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.I18nUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class HazardTypeDigamma extends HazardTypeBase {

	@Override
	public void onUpdate(EntityLivingBase target, float level, ItemStack stack) {
		ContaminationUtil.applyDigammaData(target, (level / 20F)*hazardRate);
	}

	@Override
	public void updateEntity(EntityItem item, float level) { }

	@Override
	public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<HazardModifier> modifiers) {
		level = HazardModifier.evalAllModifiers(stack, player, level, modifiers);

		float displayLevel = Math.round(level * 10000F) / 10F;
		list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.digamma") + "]");
		list.add(TextFormatting.DARK_RED + "" + displayLevel + "mDRX/s");

		if (stack.getCount() > 1) {
			float stackLevel = displayLevel * stack.getCount();
			list.add(TextFormatting.DARK_RED + "Stack: " + stackLevel + "mDRX/s");
		}
	}

}
