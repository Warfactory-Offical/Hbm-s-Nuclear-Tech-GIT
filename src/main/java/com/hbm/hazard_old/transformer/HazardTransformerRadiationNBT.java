package com.hbm.hazard_old.transformer;

import java.util.List;

import com.hbm.hazard_old.HazardEntry;
import com.hbm.hazard_old.HazardRegistry;

import net.minecraft.item.ItemStack;

public class HazardTransformerRadiationNBT extends HazardTransformerBase {
	
	public static final String RAD_KEY = "hfrHazRadiation";

	@Override
	public void transformPre(ItemStack stack, List<HazardEntry> entries) { }

	@Override
	public void transformPost(ItemStack stack, List<HazardEntry> entries) {
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(RAD_KEY)) {
			entries.add(new HazardEntry(HazardRegistry.RADIATION, stack.getTagCompound().getFloat(RAD_KEY)));
		}
	}

}
