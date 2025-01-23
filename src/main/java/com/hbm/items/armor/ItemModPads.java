package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import com.hbm.items.ModItems;
import com.hbm.items.gear.ArmorFSB;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class ItemModPads extends ItemArmorMod {

	float damageMod;
	
	public ItemModPads(final float damageMod, final String s) {
		super(ArmorModHandler.boots_only, false, false, false, true, s);
		this.damageMod = damageMod;
	}
	
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		if(damageMod != 1F)
			list.add(TextFormatting.RED + "-" + Math.round((1F - damageMod) * 100) + "% fall damage");
		
		if(this == ModItems.pads_static)
			list.add(TextFormatting.DARK_PURPLE + "Passively charges electric armor when walking");
		
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}
	
	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		
		if(this == ModItems.pads_static)
			list.add(TextFormatting.DARK_PURPLE + "  " + stack.getDisplayName() + " (-" + Math.round((1F - damageMod) * 100) + "% fall dmg / passive charge)");
		else
			list.add(TextFormatting.DARK_PURPLE + "  " + stack.getDisplayName() + " (-" + Math.round((1F - damageMod) * 100) + "% fall dmg)");
	}

	@Override
	public void modDamage(final LivingHurtEvent event, final ItemStack armor) {
		
		if(event.getSource() == DamageSource.FALL)
			event.setAmount(event.getAmount() * damageMod);
	}
	
	@Override
	public void modUpdate(final EntityLivingBase entity, final ItemStack armor) {
		
		if(!entity.world.isRemote && this == ModItems.pads_static && entity instanceof EntityPlayer player) {

            if(player.distanceWalkedModified != player.prevDistanceWalkedModified) {
				
				if(ArmorFSB.hasFSBArmorIgnoreCharge(player)) {
					
					for(int i = 0; i < 4; i++) {
						
						final ItemStack stack = player.inventory.armorInventory.get(i);
						
						if(stack.getItem() instanceof ArmorFSBPowered powered) {

                            long charge = powered.drain / 2;
							
							if(charge == 0)
								charge = powered.consumption / 40;
							
							final long power = Math.min(powered.maxPower, powered.getCharge(stack) + charge);
							powered.setCharge(stack, power);
						}
					}
				}
			}
		}
	}
}