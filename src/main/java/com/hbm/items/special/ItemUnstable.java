package com.hbm.items.special;

import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.ModDamageSource;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemUnstable extends Item {

	int radius;
	int timer;

	public ItemUnstable(final int radius, final int timer, final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.radius = radius;
		this.timer = timer;
		this.setHasSubtypes(true);

		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int itemSlot, final boolean isSelected) {
		if(stack.getItemDamage() != 0)
    		return;
		this.setTimer(stack, this.getTimer(stack) + 1);

		if(this.getTimer(stack) == timer && !world.isRemote) {
			world.spawnEntity(EntityNukeExplosionMK5.statFac(world, radius, entity.posX, entity.posY, entity.posZ));

			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFac(world, entity.posX, entity.posY, entity.posZ, radius);
			}
			world.playSound(null, entity.posX, entity.posY, entity.posZ, HBMSoundHandler.oldExplosion, SoundCategory.PLAYERS, 1.0F, 1.0F);
			entity.attackEntityFrom(ModDamageSource.nuclearBlast, 10000);
		}
	}

	@Override
	public boolean onEntityItemUpdate(final EntityItem itemEntity) {
		if(itemEntity.getItem().getItemDamage() != 0)
    		return false;
		final World world = itemEntity.world;

		this.setTimer(itemEntity.getItem(), this.getTimer(itemEntity.getItem()) + 1);

		if(this.getTimer(itemEntity.getItem()) == timer && !world.isRemote) {
			world.spawnEntity(EntityNukeExplosionMK5.statFac(world, radius, itemEntity.posX, itemEntity.posY, itemEntity.posZ));

			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFac(world, itemEntity.posX, itemEntity.posY, itemEntity.posZ, radius);
			}
			world.playSound(null, itemEntity.posX, itemEntity.posY, itemEntity.posZ, HBMSoundHandler.oldExplosion, SoundCategory.PLAYERS, 1.0F, 1.0F);
			itemEntity.attackEntityFrom(ModDamageSource.nuclearBlast, 10000);
			itemEntity.setDead();
			return true;
		}
		return false;
	}

	private void setTimer(final ItemStack stack, final int time) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		stack.getTagCompound().setInteger("timer", time);
	}

	private int getTimer(final ItemStack stack) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		return stack.getTagCompound().getInteger("timer");
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(final ItemStack oldStack, final ItemStack newStack, final boolean slotChanged) {
		return !oldStack.isItemEqual(newStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(final ItemStack stack) {
		switch(stack.getItemDamage()) {
		case 1:
			return "ELEMENTS";
		case 2:
			return "ARSENIC";
		case 3:
			return "VAULT";
		default:
			return (I18n.format(this.getTranslationKey() + ".name")).trim();
		}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if(stack.getItemDamage() != 0)
    		return;
    	tooltip.add("§4[Unstable]§r");
		tooltip.add("§cDecay Time: " + timer /20 + "s - Explosion Radius: "+ radius+"m§r");
		tooltip.add("§cDecay: " + (getTimer(stack) * 100 / timer) + "%§r");
	}

}
