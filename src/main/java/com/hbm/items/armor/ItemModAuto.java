package com.hbm.items.armor;

import com.hbm.capability.HbmLivingProps;
import com.hbm.handler.ArmorModHandler;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.potion.HbmPotion;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModAuto extends ItemArmorMod {

	public ItemModAuto(final String s) {
		super(ArmorModHandler.extra, false, true, false, false, s);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.BLUE + "Imported from Japsterdam.");
		
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.BLUE + "  " + stack.getDisplayName());
	}
	
	@Override
	public void modUpdate(final EntityLivingBase entity, final ItemStack armor) {
		if(!entity.world.isRemote) {
			
			if(HbmLivingProps.getDigamma(entity) >= 5F) {
				ArmorModHandler.removeMod(armor, ArmorModHandler.extra);
				entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, HBMSoundHandler.syringeUse, SoundCategory.PLAYERS, 1.0F, 1.0F);
				HbmLivingProps.setDigamma(entity, HbmLivingProps.getDigamma(entity) - 5F);
				entity.addPotionEffect(new PotionEffect(HbmPotion.stability, 60 * 20, 0));
				entity.heal(20F);
			}
		}
	}
}