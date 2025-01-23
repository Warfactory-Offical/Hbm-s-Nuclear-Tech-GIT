package com.hbm.items.food;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityBalefire;
import com.hbm.interfaces.IItemHazard;
import com.hbm.items.ModItems;
import com.hbm.modules.ItemHazardModule;
import com.hbm.potion.HbmPotion;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemHazardSoup extends ItemSoup implements IItemHazard {

	ItemHazardModule module;

	public ItemHazardSoup(final int i, final String s) {
		super(i);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.module = new ItemHazardModule();
		
		ModItems.ALL_ITEMS.add(this);
	}
	

	@Override
	public ItemHazardModule getModule() {
		return this.module;
	}
	
	@Override
	public void onUpdate(final ItemStack stack, final World worldIn, final Entity entity, final int itemSlot, final boolean isSelected){
		if(entity instanceof EntityLivingBase)
			this.module.applyEffects((EntityLivingBase) entity, stack.getCount(), itemSlot, isSelected, ((EntityLivingBase)entity).getHeldItem(EnumHand.MAIN_HAND) == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
	}
	
	@Override
	public boolean onEntityItemUpdate(final EntityItem item){
		super.onEntityItemUpdate(item);
		return super.onEntityItemUpdate(item);
	}

	@Override
	public void addInformation(final ItemStack stack, final World world, final List<String> list, final ITooltipFlag flagIn){
		if(this == ModItems.glowing_stew) {
            list.add("Removes 80 RAD");
    	}
    	this.module.addInformation(stack, list, flagIn);
		//super.addInformation(stack, world, list, flagIn);
	}

	@Override
	protected void onFoodEaten(final ItemStack stack, final World worldIn, final EntityPlayer player) {
		if(stack.getItem() == ModItems.glowing_stew){
			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2 * 20, 0));
			player.addPotionEffect(new PotionEffect(HbmPotion.radaway, 4 * 20, 0));
		}
		if(stack.getItem() == ModItems.balefire_scrambled){
			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 5 * 20, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 15 * 60 * 20, 10));
			player.addPotionEffect(new PotionEffect(HbmPotion.radaway, 15 * 60 * 20, 4));

			final EntityBalefire bf = new EntityBalefire(worldIn);
			bf.posX = player.posX;
			bf.posY = player.posX;
			bf.posZ = player.posZ;
			bf.destructionRange = 25;
			worldIn.spawnEntity(bf);
			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFac(worldIn, player.posX, player.posY, player.posZ, 25);
			}
		}
		if(stack.getItem() == ModItems.balefire_and_ham){
			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 5 * 20, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60 * 60 * 20, 10));
			player.addPotionEffect(new PotionEffect(HbmPotion.radaway, 60 * 60 * 20, 16));

			final EntityBalefire bf = new EntityBalefire(worldIn);
			bf.posX = player.posX;
			bf.posY = player.posX;
			bf.posZ = player.posZ;
			bf.destructionRange = 50;
			worldIn.spawnEntity(bf);
			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFac(worldIn, player.posX, player.posY, player.posZ, 50);
			}
		}
	}
}
