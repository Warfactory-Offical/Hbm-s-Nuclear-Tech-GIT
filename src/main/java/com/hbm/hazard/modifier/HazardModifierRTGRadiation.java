package com.hbm.hazard.modifier;

import com.hbm.hazard.HazardModifier;
import com.hbm.items.machine.ItemRTGPellet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class HazardModifierRTGRadiation extends HazardModifier {
		
	float target;
			
	public HazardModifierRTGRadiation(float target) {
			this.target = target;
	}

	@Override
	public float modify(ItemStack stack, EntityLivingBase holder, float level) {
				
		if(stack.getItem() instanceof ItemRTGPellet fuel) {
            double depletion = fuel.getDurabilityForDisplay(stack);
					
			level = (float) (level + (this.target - level) * depletion);
					
		}
				
		return level;
	}
	
}
