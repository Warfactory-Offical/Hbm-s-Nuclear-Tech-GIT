package com.hbm.items.armor;

import api.hbm.energy.IBatteryItem;
import com.hbm.blocks.machine.ItemSelfcharger;
import com.hbm.items.gear.ArmorFSB;
import com.hbm.lib.Library;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ArmorFSBPowered extends ArmorFSB implements IBatteryItem {

	public long maxPower = 1;
	public long chargeRate;
	public long consumption;
	public long drain;

	public ArmorFSBPowered(final ArmorMaterial material, final int layer, final EntityEquipmentSlot slot, final String texture, final long maxPower, final long chargeRate, final long consumption, final long drain, final String s) {
		super(material, layer, slot, texture, s);
		this.maxPower = maxPower;
		this.chargeRate = chargeRate;
		this.consumption = consumption;
		this.drain = drain;
		this.setMaxDamage(1);
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
	public boolean isArmorEnabled(final ItemStack stack) {
		return getCharge(stack) > 0;
	}
    
	@Override
    public void chargeBattery(final ItemStack stack, final long i) {
    	if(stack.getItem() instanceof ArmorFSBPowered) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", Math.min(this.maxPower, Math.max(0, stack.getTagCompound().getLong("charge") + i)));
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", Math.min(this.maxPower, Math.max(0, i)));
    		}
    	}
    }

	@Override
    public void setCharge(final ItemStack stack, final long i) {
    	if(stack.getItem() instanceof ArmorFSBPowered) {
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
    	if(stack.getItem() instanceof ArmorFSBPowered) {
    		if(stack.hasTagCompound()) {
    			stack.getTagCompound().setLong("charge", Math.min(this.maxPower, Math.max(0, stack.getTagCompound().getLong("charge") - i)));
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", Math.min(this.maxPower, Math.max(0, this.maxPower - i)));
    		}
    	}
    }

    private ItemSelfcharger getHeldSCBattery(final EntityLivingBase entity){
    	if(entity.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() instanceof ItemSelfcharger){
    		return (ItemSelfcharger) entity.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem();
    	}
    	if(entity.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem() instanceof ItemSelfcharger){
    		return (ItemSelfcharger) entity.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem();
    	}
    	return null;
    }

	@Override
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack stack) {
    	if(this.drain > 0 && ArmorFSB.hasFSBArmor(player)) {
    		long netto_drain = drain;
    		final ItemSelfcharger sc_battery = this.getHeldSCBattery(player);
    		if(sc_battery != null){
    			netto_drain = netto_drain - (sc_battery.getDischargeRate()/4L);
    		}
    		this.dischargeBattery(stack, netto_drain);
    	}
    }
	
	@Override
    public long getCharge(final ItemStack stack) {
    	if(stack.getItem() instanceof ArmorFSBPowered) {
    		if(stack.hasTagCompound()) {
    			return stack.getTagCompound().getLong("charge");
    		} else {
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setLong("charge", ((ArmorFSBPowered)stack.getItem()).maxPower);
    			return stack.getTagCompound().getLong("charge");
    		}
    	}

    	return 0;
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
    public void setDamage(final ItemStack stack, final int damage)
    {
        this.dischargeBattery(stack, damage * consumption);
    }
}