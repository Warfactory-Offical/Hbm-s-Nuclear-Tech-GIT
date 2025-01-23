package com.hbm.util;

import com.hbm.items.machine.ItemRTGPellet;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class RTGUtil {
	
	public static short getPower(final ItemRTGPellet fuel, final ItemStack stack) {
		return ItemRTGPellet.getScaledPower(fuel, stack);
	}
	
	public static boolean hasHeat(final ItemStackHandler inventory) {
		for(int i = 0; i < inventory.getSlots(); i++){
			
			if(inventory.getStackInSlot(i) == ItemStack.EMPTY)
				continue;
			
			if(inventory.getStackInSlot(i).getItem() instanceof ItemRTGPellet)
				return true;
		}
		
		return false;
	}
	
	public static int updateRTGs(final ItemStackHandler inventory, final int[] slots) {
		int newHeat = 0;
		for(final int i: slots){
			
			if(inventory.getStackInSlot(i) == ItemStack.EMPTY)
				continue;
			
			if(!(inventory.getStackInSlot(i).getItem() instanceof ItemRTGPellet pellet))
				continue;

            newHeat += getPower(pellet, inventory.getStackInSlot(i));
			inventory.setStackInSlot(i, ItemRTGPellet.handleDecay(inventory.getStackInSlot(i), pellet));
		}
		
		return newHeat;
		
	}
}
