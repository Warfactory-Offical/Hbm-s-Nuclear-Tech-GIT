package com.hbm.items.armor;

import com.hbm.items.gear.ArmorModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ArmorHat extends ArmorModel {

	public ArmorHat(final ArmorMaterial materialIn, final int renderIndexIn, final EntityEquipmentSlot equipmentSlotIn, final String s) {
		super(materialIn, renderIndexIn, equipmentSlotIn, s);
	}
	
	@Override
	public boolean onEntityItemUpdate(final EntityItem entityItem) {
		entityItem.setDead();
		return true;
	}

}
