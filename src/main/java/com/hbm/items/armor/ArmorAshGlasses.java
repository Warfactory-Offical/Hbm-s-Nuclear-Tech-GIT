package com.hbm.items.armor;

import com.hbm.items.ModItems;
import com.hbm.render.model.ModelGlasses;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ArmorAshGlasses extends ItemArmor {

	@SideOnly(Side.CLIENT)
	private ModelGlasses modelGoggles;

	public ArmorAshGlasses(final ArmorMaterial armorMaterial, final int renderIndex, final EntityEquipmentSlot armorType, final String s) {
		super(armorMaterial, renderIndex, armorType);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}

	@SideOnly(Side.CLIENT)
	ModelGlasses model;

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack itemStack, final EntityEquipmentSlot armorSlot, final ModelBiped _default){
		if(model == null) {
			model = new ModelGlasses(0);
		}
		return model;
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, list, flagIn);
		list.add("Shows nearby Gasses");
	}
}
