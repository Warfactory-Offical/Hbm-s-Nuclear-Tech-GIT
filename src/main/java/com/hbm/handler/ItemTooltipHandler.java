package com.hbm.handler;

import com.hbm.hazard.HazardRegistry;
import com.hbm.manager.CustomLoreManager;
import com.hbm.manager.ExplosionResistanceTooltipManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

import static com.hbm.lib.RefStrings.MODID;

@Mod.EventBusSubscriber(modid=MODID)
public class ItemTooltipHandler {

    @SubscribeEvent
    public static void drawTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        EntityPlayer entityPlayer = event.getEntityPlayer();
        List<String> list = event.getToolTip();

        // TODO: HAZMAT INFO

        // TODO: CLADDING

        // TODO: ARMOR MODS

        // TODO: CUSTOM NUKE

        // TODO: NEUTRON RADS

        // TODO: MKU

        // HAZARD
//        HazardRegistry.addFullTooltip(stack, entityPlayer, list);

        // CUSTOM LORE
        CustomLoreManager.addCustomLore(stack, list);

        // EXPLOSION RESISTANCE
        ExplosionResistanceTooltipManager.addTooltip(stack, list);
    }


}