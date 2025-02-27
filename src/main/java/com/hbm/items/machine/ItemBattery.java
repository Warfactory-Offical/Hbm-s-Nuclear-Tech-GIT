package com.hbm.items.machine;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;

import api.hbm.energy.IBatteryItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemBattery extends Item implements IBatteryItem {

	private final long maxCharge;
	private final long chargeRate;
	private final long dischargeRate;
	
	public ItemBattery(final long dura, final long chargeRate, final long dischargeRate, final String s){
		this.maxCharge = dura;
		this.chargeRate = chargeRate;
		this.dischargeRate = dischargeRate;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		ModItems.ALL_ITEMS.add(this);
	}
	
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		if(stack.getItem() == ModItems.battery_creative)
			return;
		long charge = maxCharge;
		if(stack.hasTagCompound())
			charge = getCharge(stack);
		
		
		if(stack.getItem() != ModItems.fusion_core && 
				stack.getItem() != ModItems.factory_core_titanium && 
				stack.getItem() != ModItems.factory_core_advanced && 
				stack.getItem() != ModItems.energy_core)
				
		{
			list.add("§6"+I18nUtil.resolveKey("desc.energystore")+" " + Library.getShortNumber(charge) + "/" + Library.getShortNumber(maxCharge) + "HE§r");
		} else {
			final String charge1 = Library.getShortNumber((charge * 100) / this.maxCharge);
			list.add("§2"+I18nUtil.resolveKey("desc.energychargecur")+" " + charge1 + "%§r");
			list.add("(" + Library.getShortNumber(charge) + "/" + Library.getShortNumber(maxCharge) + "HE)");
		}
		list.add("§a"+I18nUtil.resolveKey("desc.energychargerate")+" " + Library.getShortNumber(chargeRate * 20) + "HE/s§r");
		list.add("§c"+I18nUtil.resolveKey("desc.energydchargerate")+" " + Library.getShortNumber(dischargeRate * 20) + "HE/s§r");
	}
	
	@Override
	public EnumRarity getRarity(final ItemStack p_77613_1_) {
    	
    	if(this == ModItems.battery_schrabidium)
    	{
        	return EnumRarity.RARE;
    	}

    	if(this == ModItems.fusion_core || 
    			this == ModItems.factory_core_titanium || 
    			this == ModItems.factory_core_advanced || 
    			this == ModItems.energy_core) 
    			
    	{
        	return EnumRarity.UNCOMMON;
    	}
    	
    	return EnumRarity.COMMON;
    }
	
	public void chargeBattery(final ItemStack stack, final long i) {
		if(stack.getItem() == ModItems.battery_creative)
			return;
    	if(stack.getItem() instanceof ItemBattery) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") + i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", i);
    		}
    	}
    }
    
    public void setCharge(final ItemStack stack, final long i) {
    	if(stack.getItem() == ModItems.battery_creative)
			return;
    	if(stack.getItem() instanceof ItemBattery) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setLong("charge", i);
    		}
    	}
    }
    
    public void dischargeBattery(final ItemStack stack, final long i) {
    	if(stack.getItem() == ModItems.battery_creative)
			return;
    	if(stack.getItem() instanceof ItemBattery) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") - i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setLong("charge", this.maxCharge - i);
    		}
    	}
    }
    
    public long getCharge(final ItemStack stack) {
    	if(stack.getItem() == ModItems.battery_creative)
			return Long.MAX_VALUE;
    	if(stack.getItem() instanceof ItemBattery) {
    		if(stack.hasTagCompound()) {
    			return stack.getTagCompound().getLong("charge");
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setLong("charge", ((ItemBattery)stack.getItem()).maxCharge);
    			return stack.getTagCompound().getLong("charge");
    		}
    	}
    	
    	return 0;
    }
    
    public long getMaxCharge() {
    	return maxCharge;
    }
    
    public long getChargeRate() {
    	return chargeRate;
    }
    
    public long getDischargeRate() {
    	return dischargeRate;
    }
    
    public static ItemStack getEmptyBattery(final Item item) {
    	
    	if(item instanceof ItemBattery) {
    		final ItemStack stack = ItemStackUtil.itemStackFrom(item);
    		stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setLong("charge", 0);
    		//stack.setItemDamage(100);
    		return stack.copy();
    	}
    	
    	return null;
    }
    
    public static ItemStack getFullBattery(final Item item) {
    	
    	if(item instanceof ItemBattery) {
    		final ItemStack stack = ItemStackUtil.itemStackFrom(item);
    		stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setLong("charge", ((ItemBattery)item).getMaxCharge());
    		return stack.copy();
    	}
    	
    	return ItemStackUtil.itemStackFrom(item);
    }
	
	@Override
	public boolean showDurabilityBar(final ItemStack stack) {
        return stack.getItem() != ModItems.battery_creative;
    }
	
	@Override
	public double getDurabilityForDisplay(final ItemStack stack) {
		return 1D - (double)getCharge(stack) / (double)getMaxCharge();
	}
	
}
