package com.hbm.items.tool;

import api.hbm.energy.IBatteryItem;
import com.hbm.lib.Library;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemToolAbilityPower extends ItemToolAbility implements IBatteryItem {

	public long maxPower = 1;
	public long chargeRate;
	public long consumption;
	
	public ItemToolAbilityPower(final float damage, final float attackSpeedIn, final double movement, final ToolMaterial material, final EnumToolType type, final long maxPower, final long chargeRate, final long consumption, final String s) {
		super(damage, attackSpeedIn, movement, material, type, s);
		this.maxPower = maxPower;
		this.chargeRate = chargeRate;
		this.consumption = consumption;
		this.setMaxDamage(1);
	}
	
	@Override
	public void chargeBattery(final ItemStack stack, final long i) {
    	if(stack.getItem() instanceof ItemToolAbilityPower) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") + i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", i);
    		}
    	}
    }
    
	@Override
    public void setCharge(final ItemStack stack, final long i) {
    	if(stack.getItem() instanceof ItemToolAbilityPower) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", i);
    		}
    	}
    }
    
	@Override
    public void dischargeBattery(final ItemStack stack, final long i) {
    	if(stack.getItem() instanceof ItemToolAbilityPower) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") - i);
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", this.maxPower - i);
    		}
    		
    		if(stack.getTagCompound().getLong("charge") < 0)
    			stack.getTagCompound().setLong("charge", 0);
    	}
    }
    
	@Override
    public long getCharge(final ItemStack stack) {
    	if(stack.getItem() instanceof ItemToolAbilityPower) {
    		if(stack.hasTagCompound()) {
    			return stack.getTagCompound().getLong("charge");
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", ((ItemToolAbilityPower)stack.getItem()).maxPower);
    			return stack.getTagCompound().getLong("charge");
    		}
    	}
    	
    	return 0;
    }
    
    public static String getColor(final long a, final long b){
        final float fraction = 100F * a/b;
        if(fraction > 75)
            return "§a";
        if(fraction > 25)
            return "§e";
        return "§c";
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
        final long power = getCharge(stack);
        list.add("Charge: " + getColor(power, maxPower) + Library.getShortNumber(power) + " §2/ " + Library.getShortNumber(maxPower));
        super.addInformation(stack, worldIn, list, flagIn);
    }
    
    @Override
    public boolean showDurabilityBar(final ItemStack stack) {
    	return getCharge(stack) < maxPower;
    }
    
    @Override
    public double getDurabilityForDisplay(final ItemStack stack) {
    	return 1 - (double)getCharge(stack) / (double)maxPower;
    }
    
    @Override
    protected boolean canOperate(final ItemStack stack) {
    	return getCharge(stack) >= this.consumption;
    }
    
    @Override
    public long getMaxCharge() {
    	return maxPower;
    }
    
    @Override
    public long getChargeRate() {
    	return chargeRate;
    }
    
    @Override
	public long getDischargeRate() {
		return 0;
	}
    
    @Override
    public void setDamage(final ItemStack stack, final int damage) {
    	this.dischargeBattery(stack, damage * consumption);
    }
    
    @Override
    public boolean isDamageable() {
    	return true;
    }

}
