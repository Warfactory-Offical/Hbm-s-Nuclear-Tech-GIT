package com.hbm.items.special;

import java.util.List;

import com.hbm.handler.HazmatRegistry;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCladding extends Item {

	float rad;

	public ItemCladding(final float rad, final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.rad = rad;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		if(!world.isRemote) {
			final ItemStack stack = player.getHeldItem(hand);

			boolean used = false;

			for(final ItemStack armor : player.inventory.armorInventory) {

				if(armor != null && HazmatRegistry.getCladding(armor) < rad) {

					if(!armor.hasTagCompound())
						armor.setTagCompound(new NBTTagCompound());

					armor.getTagCompound().setFloat("hfr_cladding", rad);

					used = true;
				}
			}

			if(used) {
				world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.repair, SoundCategory.PLAYERS, 1.0F, 1.0F);
				stack.shrink(1);
			}
		}
		return super.onItemRightClick(world, player, hand);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.YELLOW + "Adds " + rad + " rad-resistance to all armor pieces.");
	}
}