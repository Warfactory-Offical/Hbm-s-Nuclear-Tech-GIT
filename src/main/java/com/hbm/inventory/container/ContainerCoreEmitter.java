package com.hbm.inventory.container;

import com.hbm.forgefluid.FFUtils;
import com.hbm.inventory.gui.GUICoreEmitter;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.AuxGaugePacket;
import com.hbm.packet.AuxLongPacket;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.machine.TileEntityCoreEmitter;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCoreEmitter extends Container {

	private final TileEntityCoreEmitter nukeBoy;
	private EntityPlayerMP player;
	
	public ContainerCoreEmitter(final EntityPlayer player, final TileEntityCoreEmitter tedf) {
		final InventoryPlayer invPlayer = player.inventory;
		if(player instanceof EntityPlayerMP)
			this.player = (EntityPlayerMP) player;
		nukeBoy = tedf;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 88 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 146));
		}
	}
	
	@Override
	public void addListener(final IContainerListener listener) {
		super.addListener(listener);
		PacketDispatcher.sendTo(new AuxElectricityPacket(nukeBoy.getPos(), nukeBoy.power), player);
		PacketDispatcher.sendTo(new AuxGaugePacket(nukeBoy.getPos(), nukeBoy.watts, 1), player);
		PacketDispatcher.sendTo(new AuxLongPacket(nukeBoy.getPos(), nukeBoy.prev, 0), player);
		listener.sendWindowProperty(this, 3, nukeBoy.isOn ? 1 : 0);
		PacketDispatcher.sendTo(new FluidTankPacket(nukeBoy.getPos(), tank), player);
	}
	
	int power;
	int watts;
	int prev;
	boolean isOn;
	FluidTank tank;
	
	@Override
	public void detectAndSendChanges() {
		for(final IContainerListener l : listeners){
			if(nukeBoy.isOn != isOn){
				l.sendWindowProperty(this, 3, nukeBoy.isOn ? 1 : 0);
				isOn = nukeBoy.isOn;
			}
		}
		if(nukeBoy.power != power){
			PacketDispatcher.sendTo(new AuxElectricityPacket(nukeBoy.getPos(), nukeBoy.power), player);
			power = (int) nukeBoy.power;
		}
		if(nukeBoy.watts != watts){
			PacketDispatcher.sendTo(new AuxGaugePacket(nukeBoy.getPos(), nukeBoy.watts, 1), player);
			watts = nukeBoy.watts;
		}
		if(nukeBoy.prev != prev){
			PacketDispatcher.sendTo(new AuxLongPacket(nukeBoy.getPos(), nukeBoy.prev, 0), player);
			prev = (int) nukeBoy.prev;
		}
		if(!FFUtils.areTanksEqual(tank, nukeBoy.tank)){
			tank = FFUtils.copyTank(nukeBoy.tank);
			PacketDispatcher.sendTo(new FluidTankPacket(nukeBoy.getPos(), tank), player);
		}
		super.detectAndSendChanges();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(final int id, final int data) {
		if(id == 3)
			nukeBoy.isOn = data > 0;
		if(id == 1){
			if(Minecraft.getMinecraft().currentScreen instanceof GUICoreEmitter){
				((GUICoreEmitter)Minecraft.getMinecraft().currentScreen).syncTextField(watts);
			}
		}
		super.updateProgressBar(id, data);
	}

	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return nukeBoy.isUseableByPlayer(player);
	}
	
	@Override
    public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int par2)
    {
		final ItemStack var3 = ItemStack.EMPTY;
		final Slot var4 = this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			return ItemStack.EMPTY;
		}
		
		return var3;
    }
}