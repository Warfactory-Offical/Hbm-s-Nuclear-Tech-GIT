package com.hbm.items.machine;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import com.hbm.items.special.ItemHazard;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;

import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemFuelRod extends ItemHazard {
	
	private final int lifeTime;
	private final int heat;
	private final float irad;
	private final boolean iblind;

	public ItemFuelRod(final float radiation, final boolean blinding, final int life, final int heat, final String s) {
		super(radiation, false, blinding, s);
		this.irad = radiation;
		this.iblind = blinding;
		this.lifeTime = life;
		this.heat = heat;
		this.setMaxDamage(100);
		this.canRepair = false;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World world, final List<String> tooltip, final ITooltipFlag flagIn) {
		//tooltip.add(TextFormatting.GREEN + "["+ I18nUtil.resolveKey("trait.radioactive") +"]");
		//tooltip.add(TextFormatting.YELLOW + "" + this.irad + " "+I18nUtil.resolveKey("desc.rads"));
		//if(this.iblind){
		//	tooltip.add(TextFormatting.DARK_AQUA + "["+I18nUtil.resolveKey("trait.blinding")+"]");
		//}
		tooltip.add(TextFormatting.GOLD + "["+I18nUtil.resolveKey("trait.reactorrod")+"]");
		
		tooltip.add(TextFormatting.DARK_AQUA + "  "+I18nUtil.resolveKey("desc.generates")+" " + heat + " "+I18nUtil.resolveKey("desc.heatpt"));
		tooltip.add(TextFormatting.DARK_AQUA + "  "+I18nUtil.resolveKey("desc.lasts")+" " + Library.getShortNumber(lifeTime) + " "+I18nUtil.resolveKey("desc.ticks"));
	}
	
	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, final NBTTagCompound nbt) {
		return super.initCapabilities(stack, nbt);
	}
	
	public static void setLifetime(final ItemStack stack, final int time){
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("life", time);
	}
	
	public static int getLifeTime(final ItemStack stack){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		return stack.getTagCompound().getInteger("life");
	}
	
	public int getMaxLifeTime() {
		return lifeTime;
	}
	
	public int getHeatPerTick(){
		return heat;
	}
	
	public boolean showDurabilityBar(final ItemStack stack) {
        return true;
    }

    public double getDurabilityForDisplay(final ItemStack stack)
    {
        return (double)getLifeTime(stack) / (double)((ItemFuelRod)stack.getItem()).lifeTime;
    }

    public ItemStack getUncrafting(){
    	if(this == ModItems.rod_uranium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL));
    	if(this == ModItems.rod_dual_uranium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), 2);
    	if(this == ModItems.rod_quad_uranium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), 4);
    	
    	if(this == ModItems.rod_thorium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL));
    	if(this == ModItems.rod_dual_thorium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), 2);
    	if(this == ModItems.rod_quad_thorium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), 4);
    	
    	if(this == ModItems.rod_plutonium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
    	if(this == ModItems.rod_dual_plutonium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 2);
    	if(this == ModItems.rod_quad_plutonium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 4);
    	
    	if(this == ModItems.rod_mox_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL));
    	if(this == ModItems.rod_dual_mox_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), 2);
    	if(this == ModItems.rod_quad_mox_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), 4);
    	
    	if(this == ModItems.rod_schrabidium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
    	if(this == ModItems.rod_dual_schrabidium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 2);
    	if(this == ModItems.rod_quad_schrabidium_fuel) return ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 4);
    	return ItemStack.EMPTY;
    }
}
