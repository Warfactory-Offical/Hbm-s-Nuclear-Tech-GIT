package com.hbm.hazard.type;

import com.hbm.config.RadiationConfig;
import com.hbm.handler.ArmorUtil;
import com.hbm.hazard.helper.HazardHelper;
import com.hbm.hazard.modifier.HazardModifier;
import com.hbm.util.I18nUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class HazardTypeCold extends HazardTypeBase {


    @Override
    public void onUpdate(EntityLivingBase target, float level, ItemStack stack) {
        boolean reacher = HazardHelper.isHoldingReacher(target);
        if(RadiationConfig.disableCold || reacher)
            return;

        boolean isProtected = target instanceof EntityPlayer && ArmorUtil.checkForHazmat((EntityPlayer) target);
        if(!isProtected){
            target.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 110, (int)level-1));
            target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 110, Math.min(4, (int)level-1)));
            target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 110,(int)level-1));
            if(level > 4){
                target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 110, (int)level-3));
            }
        }




    }

    @Override
    public void updateEntity(EntityItem item, float level) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<HazardModifier> modifiers) {
        list.add(TextFormatting.AQUA + "[" + I18nUtil.resolveKey("trait.cryogenic") + "]");

    }
}
