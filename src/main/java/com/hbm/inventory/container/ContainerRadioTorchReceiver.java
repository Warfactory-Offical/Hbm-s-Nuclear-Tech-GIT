package com.hbm.inventory.container;

import com.hbm.tileentity.network.TileEntityRadioTorchReceiver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class ContainerRadioTorchReceiver extends Container {
	
	private final TileEntityRadioTorchReceiver diFurnace;
	
	BlockPos detectTarget = null;
	EntityPlayerMP player;
	
	public ContainerRadioTorchReceiver(final EntityPlayer player, final TileEntityRadioTorchReceiver tedf) {
		if(player instanceof EntityPlayerMP)
			this.player = (EntityPlayerMP) player;
		diFurnace = tedf;
	}
	
	
	@Override
	public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int p_82846_2_) {
		return ItemStack.EMPTY;
    }

	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return true;
	}
}
