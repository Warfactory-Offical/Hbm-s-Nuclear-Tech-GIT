package com.hbm.manager;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ExplosionResistanceTooltipManager {

    public static void addTooltip(ItemStack stack, List<String> list) {
        if (stack != null && stack.getItem() instanceof ItemBlock) {
            Block block = Block.getBlockFromItem(stack.getItem());

            float resistance = block.getExplosionResistance(null);

            String resistanceLabel = null;

            if (resistance == Float.POSITIVE_INFINITY) {
                resistanceLabel = "INFINITE EXPLOSION RESISTANCE";
            } else if (resistance >= 1_000_000.0F) {
                resistanceLabel = "Extreme Explosion Resistance (%s)";
            } else if (resistance >= 10_000.0F) {
                resistanceLabel = "Very High Explosion Resistance (%s)";
            } else if (resistance >= 1000.0F) {
                resistanceLabel = "High Explosion Resistance (%s)";
            } else if (resistance >= 500.0F) {
                resistanceLabel = "Medium Explosion Resistance (%s)";
            } else if (resistance >= 50.0F) {
                resistanceLabel = "Low Explosion Resistance (%s)";
            }

            if (resistanceLabel != null) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                symbols.setGroupingSeparator(' ');
                DecimalFormat df = new DecimalFormat("#,###", symbols);

                list.add(TextFormatting.GOLD + "[" + String.format(resistanceLabel, df.format(resistance)) + "]");
            }
        }
    }

}
