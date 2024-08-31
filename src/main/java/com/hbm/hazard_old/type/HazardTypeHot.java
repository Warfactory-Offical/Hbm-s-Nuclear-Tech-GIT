package com.hbm.hazard_old.type;

import com.hbm.config.RadiationConfig;
import com.hbm.hazard_old.helper.HazardHelper;
import com.hbm.hazard_old.modifier.HazardModifier;
import com.hbm.util.I18nUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class HazardTypeHot extends HazardTypeBase {

	@Override
	public void onUpdate(EntityLivingBase target, float level, ItemStack stack) {

		boolean wetOrReacher = HazardHelper.isHoldingReacher(target) || target.isWet() ;
		if(RadiationConfig.disableHot && !wetOrReacher) return;

		target.setFire((int) Math.ceil(level));
	}

	@Override
	public void updateEntity(EntityItem item, float level) { }

	@Override
	@SideOnly(Side.CLIENT)
	public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<HazardModifier> modifiers) {
		
		level = HazardModifier.evalAllModifiers(stack, player, level, modifiers);
		
		if(level > 0)
			list.add(TextFormatting.GOLD + "[" + I18nUtil.resolveKey("trait.hot") + "]");
	}

}
