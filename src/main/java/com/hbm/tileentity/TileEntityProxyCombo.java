package com.hbm.tileentity;

import api.hbm.energymk2.IEnergyReceiverMK2;
import api.hbm.fluid.IFluidConnector;
import api.hbm.tile.IHeatSource;
import com.hbm.interfaces.IFluidAcceptor;
import com.hbm.interfaces.IFluidContainer;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.lib.ForgeDirection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityProxyCombo extends TileEntityProxyBase implements IEnergyReceiverMK2, IHeatSource, IFluidAcceptor, IFluidConnector {

	TileEntity tile;
	boolean inventory;
	boolean power;
	boolean fluid;

	boolean heat;

	public TileEntityProxyCombo() {
	}

	public TileEntityProxyCombo(boolean inventory, boolean power, boolean fluid) {
		this.inventory = inventory;
		this.power = power;
		this.fluid = fluid;
		this.heat = false;
	}

	public TileEntityProxyCombo(boolean inventory, boolean power, boolean fluid, boolean heat) {
		this.inventory = inventory;
		this.power = power;
		this.fluid = fluid;
		this.heat = heat;
	}

	// fewer messy recursive operations
	public TileEntity getTile() {

		if(tile == null) {
			tile = this.getTE();
		}

		return tile;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(tile == null) {
			tile = this.getTE();
			if(tile == null){
				return super.getCapability(capability, facing);
			}
		}
		if(inventory && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return tile.getCapability(capability, facing);
		}
		if(power && capability == CapabilityEnergy.ENERGY){
			return tile.getCapability(capability, facing);
		}
		if(fluid && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return tile.getCapability(capability, facing);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(tile == null) {
			tile = this.getTE();
			if(tile == null){
				return super.hasCapability(capability, facing);
			}
		}
		if(inventory && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return tile.hasCapability(capability, facing);
		}
		if(power && capability == CapabilityEnergy.ENERGY){
			return tile.hasCapability(capability, facing);
		}
		if(fluid && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return tile.hasCapability(capability, facing);
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public void setFillForSync(int fill, int index) {

		if(!fluid)
			return;

		if(getTile() instanceof IFluidContainer) {
			((IFluidContainer)getTile()).setFillForSync(fill, index);
		}
	}

	@Override
	public void setFluidFill(int fill, FluidType type) {

		if(!fluid)
			return;

		if(getTile() instanceof IFluidContainer) {
			((IFluidContainer)getTile()).setFluidFill(fill, type);
		}
	}

	@Override
	public int getFluidFillForReceive(FluidType type) {

		if(!fluid)
			return 0;

		if(getTile() instanceof IFluidAcceptor) {
			return ((IFluidAcceptor)getTile()).getFluidFillForReceive(type);
		}
		return 0;
	}

	@Override
	public int getMaxFluidFillForReceive(FluidType type) {

		if(!fluid)
			return 0;

		if(getTile() instanceof IFluidAcceptor) {
			return ((IFluidAcceptor)getTile()).getMaxFluidFillForReceive(type);
		}

		return 0;
	}

	@Override
	public void receiveFluid(int amount, FluidType type) {

		if(!fluid)
			return;

		if(getTile() instanceof IFluidAcceptor) {
			((IFluidAcceptor)getTile()).receiveFluid(amount, type);
		}
	}

	@Override
	public void setTypeForSync(FluidType type, int index) {

		if(!fluid)
			return;

		if(getTile() instanceof IFluidContainer) {
			((IFluidContainer)getTile()).setTypeForSync(type, index);
		}
	}

	@Override
	public int getFluidFill(FluidType type) {

		if(!fluid)
			return 0;

		if(getTile() instanceof IFluidContainer) {
			return ((IFluidContainer)getTile()).getFluidFill(type);
		}

		return 0;
	}

	@Override
	public int getMaxFluidFill(FluidType type) {

		if(!fluid)
			return 0;

		if(getTile() instanceof IFluidAcceptor) {
			return ((IFluidAcceptor)getTile()).getMaxFluidFill(type);
		}

		return 0;
	}

	@Override
	public void setPower(long i) {

		if(!power)
			return;

		if(getTile() instanceof IEnergyReceiverMK2) {
			((IEnergyReceiverMK2)getTile()).setPower(i);
		}
	}

	@Override
	public long getPower() {

		if(!power)
			return 0;

		if(getTile() instanceof IEnergyReceiverMK2) {
			return ((IEnergyReceiverMK2)getTile()).getPower();
		}

		return 0;
	}

	@Override
	public long getMaxPower() {

		if(!power)
			return 0;

		if(getTile() instanceof IEnergyReceiverMK2) {
			return ((IEnergyReceiverMK2)getTile()).getMaxPower();
		}

		return 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory = compound.getBoolean("inv");
		fluid = compound.getBoolean("flu");
		power = compound.getBoolean("pow");
		heat = compound.getBoolean("hea");

		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("inv", inventory);
		compound.setBoolean("flu", fluid);
		compound.setBoolean("pow", power);
		compound.setBoolean("hea", heat);
		return super.writeToNBT(compound);
	}

	@Override
	public long transferFluid(FluidType type, int pressure, long fluid) {

		if(!this.fluid)
			return fluid;

		if(getTile() instanceof IFluidConnector) {
			return ((IFluidConnector)getTile()).transferFluid(type, pressure, fluid);
		}
		return fluid;
	}

	@Override
	public long getDemand(FluidType type, int pressure) {

		if(!this.fluid)
			return 0;

		if(getTile() instanceof IFluidConnector) {
			return ((IFluidConnector)getTile()).getDemand(type, pressure);
		}
		return 0;
	}

	@Override
	public boolean canConnect(FluidType type, ForgeDirection dir) {

		if(!this.fluid)
			return false;

		if(getTile() instanceof IFluidConnector) {
			return ((IFluidConnector)getTile()).canConnect(type, dir);
		}
		return true;
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public int getHeatStored() {
		if (!this.heat) {
			return 0;
		}

		if (getTile() instanceof IHeatSource) {
			return ((IHeatSource) getTile()).getHeatStored();
		}
		return 0;
	}

	@Override
	public void useUpHeat(int heat) {
		if (!this.heat) {
			return;
		}

		if (getTile() instanceof IHeatSource) {
			((IHeatSource) getTile()).useUpHeat(heat);
		}
	}
}
