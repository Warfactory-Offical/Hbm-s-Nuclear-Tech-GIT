package com.hbm.hazard_old.type;

import com.hbm.config.RadiationConfig;
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

public class HazardTypeExplosive extends HazardTypeBase {

	@Override
	public void onUpdate(final EntityLivingBase target, final float level, final ItemStack stack) {
		
		if(RadiationConfig.disableExplosive)
			return;

		if(target.isBurning() && stack.getCount() > 0) {
			stack.setCount(0);
			target.world.newExplosion(null, target.posX, target.posY + target.getEyeHeight() - target.getYOffset(), target.posZ, level, false, true);
		}
	}

	@Override
	public void updateEntity(final EntityItem item, final float level) {
		
		if(RadiationConfig.disableExplosive)
			return;
		
		if(item.isBurning()) {
			item.setDead();
			item.world.newExplosion(null, item.posX, item.posY + item.height * 0.5, item.posZ, level, false, true);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addHazardInformation(final EntityPlayer player, final List list, final float level, final ItemStack stack, final List<HazardModifier> modifiers) {
		list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.explosive") + "]");
	}
}
