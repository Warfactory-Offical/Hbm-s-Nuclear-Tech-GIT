package com.hbm.hazard.helper;

import com.hbm.config.GeneralConfig;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class HazardHelper {
    static public boolean isHoldingReacher(EntityLivingBase target){

        if(target instanceof EntityPlayer && !GeneralConfig.enable528){
            return  Library.checkForHeld((EntityPlayer) target, ModItems.reacher);
        } else return false;

    }
}
