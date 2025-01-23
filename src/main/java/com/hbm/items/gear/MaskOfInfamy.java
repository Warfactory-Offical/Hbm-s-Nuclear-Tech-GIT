package com.hbm.items.gear;

import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class MaskOfInfamy extends ItemArmor {

	public MaskOfInfamy(final ArmorMaterial materialIn, final int renderIndexIn, final EntityEquipmentSlot equipmentSlotIn, final String s) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public String getArmorTexture(final ItemStack stack, final Entity entity, final EntityEquipmentSlot slot, final String type) {
		return (RefStrings.MODID + ":textures/armor/MaskOfInfamy.png");
	}

}
