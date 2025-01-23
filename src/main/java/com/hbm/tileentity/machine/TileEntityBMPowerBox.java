package com.hbm.tileentity.machine;

import java.util.Arrays;
import java.util.List;

import com.hbm.inventory.control_panel.ControlEvent;
import com.hbm.inventory.control_panel.ControlEventSystem;
import com.hbm.inventory.control_panel.IControllable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBMPowerBox extends TileEntity implements IControllable {
	
	public long ticksPlaced;
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound compound){
		compound.setLong("ticksPlaced", ticksPlaced);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound compound){
		ticksPlaced = compound.getLong("ticksPlaced");
		super.readFromNBT(compound);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag(){
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(final NetworkManager net, final SPacketUpdateTileEntity pkt){
		readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public void receiveEvent(final BlockPos from, final ControlEvent e){
	}

	@Override
	public List<String> getOutEvents(){
		return Arrays.asList("lever_toggle");
	}
	
	@Override
	public BlockPos getControlPos(){
		return getPos();
	}

	@Override
	public World getControlWorld(){
		return getWorld();
	}
	
	@Override
	public void validate(){
		super.validate();
		ControlEventSystem.get(world).addControllable(this);
	}
	
	@Override
	public void invalidate(){
		super.invalidate();
		ControlEventSystem.get(world).removeControllable(this);
	}
}
