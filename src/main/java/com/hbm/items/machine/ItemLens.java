package com.hbm.items.machine;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.util.BobMathUtil;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemLens extends Item {

	public long maxDamage;
	public float fieldMod;
	public float drainMod;
	
	public ItemLens(final long maxDamage, final float fieldMod, final float drainMod, final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.maxDamage = maxDamage;
		this.fieldMod = fieldMod;
		this.drainMod = drainMod;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		final long damage = getLensDamage(stack);
		final double percent = (int)((maxDamage - damage) * 100000000D / maxDamage) / 1000000D;


		tooltip.add(TextFormatting.DARK_AQUA+I18nUtil.resolveKey("desc.durticks")+" " + (maxDamage - damage) + " / " + maxDamage);
		tooltip.add(TextFormatting.DARK_AQUA+I18nUtil.resolveKey("desc.durpercents")+" " + percent + "%");

		tooltip.add(TextFormatting.YELLOW+I18nUtil.resolveKey("desc.fieldmodifier")+" " + (fieldMod >= 1 ? "§a+" : "§c") + (Math.round(fieldMod * 1000) * .10 - 100) + "%");
		tooltip.add(TextFormatting.YELLOW+I18nUtil.resolveKey("desc.powdrainmodifier")+" " + (drainMod >= 1 ? "§c+" : "§a") + (Math.round(drainMod * 1000) * .10 - 100) + "%");
	}
	
	@Override
    public double getDurabilityForDisplay(final ItemStack stack){
        return (double)getLensDamage(stack) / (double)maxDamage;
    }
    
    @Override
    public boolean showDurabilityBar(final ItemStack stack){
        return getDurabilityForDisplay(stack) != 0;
    }
	
	public static long getLensDamage(final ItemStack stack) {
		
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		
		return stack.getTagCompound().getLong("damage");
	}
	
	public static void setLensDamage(final ItemStack stack, final long damage) {
		
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setLong("damage", damage);
	}
	
	
}
