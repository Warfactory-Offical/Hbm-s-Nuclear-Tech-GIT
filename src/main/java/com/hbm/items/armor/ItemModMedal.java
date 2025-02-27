package com.hbm.items.armor;

import com.hbm.capability.HbmLivingProps;
import com.hbm.handler.ArmorModHandler;
import com.hbm.util.ContaminationUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemModMedal extends ItemArmorMod {
	private final float minusRads;
	private final float decayRate;

	public ItemModMedal(final String s, final float minusRads) {
		super(ArmorModHandler.extra, false, true, false, false, s);
		this.minusRads = minusRads;
		this.decayRate = (float)Math.pow(0.5, minusRads/6000);

	}
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.GOLD + "-"+minusRads*20+" RAD/s");
		if(15/minusRads < 60)
			list.add(TextFormatting.YELLOW + " "+15/minusRads+"s Item Decontamination Halflife");
		else
			list.add(TextFormatting.YELLOW + " "+15/(minusRads*60)+"min Item Decontamination Halflife");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.GOLD + "  " + stack.getDisplayName() + " (-"+minusRads*20+" RAD/s)"+TextFormatting.YELLOW + " ("+15/minusRads+"s Halflife)");
	}
	
	@Override
	public void modUpdate(final EntityLivingBase entity, final ItemStack armor) {
		if(!entity.world.isRemote) {
			float rad = HbmLivingProps.getRadiation(entity);
			rad -= minusRads;
			HbmLivingProps.setRadiation(entity, Math.max(rad, 0));

			if(entity instanceof EntityPlayer){
				ContaminationUtil.neutronActivateInventory((EntityPlayer)entity, 0.0F, this.decayRate);
				((EntityPlayer)entity).inventoryContainer.detectAndSendChanges();
			}
		}
	}
}
