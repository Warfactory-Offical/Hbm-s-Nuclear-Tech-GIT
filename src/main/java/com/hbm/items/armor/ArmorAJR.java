package com.hbm.items.armor;

import com.hbm.render.model.ModelArmorAJR;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorAJR extends ArmorFSBPowered {

	public ArmorAJR(final ArmorMaterial material, final int layer, final EntityEquipmentSlot slot, final String texture, final long maxPower, final long chargeRate, final long consumption, final long drain, final String s) {
		super(material, layer, slot, texture, maxPower, chargeRate, consumption, drain, s);
	}

	@SideOnly(Side.CLIENT)
	ModelArmorAJR[] models;

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack itemStack, final EntityEquipmentSlot armorSlot, final ModelBiped _default) {
		if(models == null) {
			models = new ModelArmorAJR[4];

			for(int i = 0; i < 4; i++)
				models[i] = new ModelArmorAJR(i);
		}

		return models[3-armorSlot.getIndex()];
	}
	
}
