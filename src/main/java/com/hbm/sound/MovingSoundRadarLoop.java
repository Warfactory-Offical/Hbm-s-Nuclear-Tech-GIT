package com.hbm.sound;

import com.hbm.items.ModItems;
import com.hbm.handler.ArmorModHandler;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class MovingSoundRadarLoop extends MovingSoundPlayerLoop {

	public MovingSoundRadarLoop(final SoundEvent p_i45104_1_, final SoundCategory c, final Entity player, final EnumHbmSound type) {
		super(p_i45104_1_, c, player, type);
		this.repeat = false;
	}

	@Override
	public void update() {
		super.update();
		if(player instanceof EntityPlayer){
			final ItemStack helmet = ((EntityPlayer)player).getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			if(helmet == null || helmet.isEmpty())
				this.stop();
			if(!ArmorModHandler.hasMods(helmet))
				this.stop();
			final ItemStack radar = ArmorModHandler.pryMod(helmet, 7);
			if(radar == null || radar.isEmpty() || radar.getItem() != ModItems.pocket_ptsd)
				this.stop();
		} else {
			this.stop();
		}
	}
}
