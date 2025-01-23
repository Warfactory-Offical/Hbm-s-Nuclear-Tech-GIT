package com.hbm.items.special;

import com.hbm.items.machine.ItemBattery;
import com.hbm.lib.HBMSoundHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemPotatos extends ItemBattery {

	public ItemPotatos(final long dura, final long chargeRate, final long dischargeRate, final String s) {
		super(dura, chargeRate, dischargeRate, s);
	}

	@Override
	public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int itemSlot, final boolean isSelected) {
		if(getCharge(stack) == 0)
    		return;
    	
    	if(getTimer(stack) > 0) {
    		setTimer(stack, getTimer(stack) - 1);
    	} else {
    		if(entity instanceof EntityPlayer p) {

                if(p.getHeldItemMainhand() == stack || p.getHeldItemOffhand() == stack) {
    				
    		    	final float pitch = (float)getCharge(stack) / (float)this.getMaxCharge() * 0.5F + 0.5F;
    		    	
    				world.playSound(null, p.posX, p.posY, p.posZ, HBMSoundHandler.potatOSRandom, SoundCategory.PLAYERS, 1.0F, pitch);
    				setTimer(stack, 200 + itemRand.nextInt(100));
    			}
    		}
    	}
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(final ItemStack oldStack, final ItemStack newStack, final boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem() || oldStack.getMetadata() != newStack.getMetadata();
	}
	
	private static int getTimer(final ItemStack stack) {
		if(stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		
		return stack.getTagCompound().getInteger("timer");
		
	}
	
	private static void setTimer(final ItemStack stack, final int i) {
		if(stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setInteger("timer", i);
		
	}
}
