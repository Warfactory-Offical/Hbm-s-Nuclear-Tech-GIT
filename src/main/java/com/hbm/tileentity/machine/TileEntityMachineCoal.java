package com.hbm.tileentity.machine;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.machine.MachineCoal;
import com.hbm.forgefluid.FFUtils;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.lib.Library;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.AuxGaugePacket;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityMachineBase;

import api.hbm.energy.IBatteryItem;
import api.hbm.energy.IEnergyGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityMachineCoal extends TileEntityMachineBase implements ITickable, ITankPacketAcceptor, IEnergyGenerator, IFluidHandler {
	
	public long power;
	public int burnTime;
	public static final long maxPower = 100000;

	public FluidTank tank;
	public Fluid tankType = FluidRegistry.WATER;
	public boolean needsUpdate = false;
	
	String customName = null;
	
	public TileEntityMachineCoal() {
		super(4);
		tank = new FluidTank(5000);
	}
	
	public String getName() {
		return "container.machineCoal";
	}

	@Override
	public int[] getAccessibleSlotsFromSide(final EnumFacing e) {
		return new int[]{ 0, 1, 2, 3 };
	}

	@Override
	public boolean isItemValidForSlot(final int i, final ItemStack stack) {
		if(i == 0)
			return isValidFluid(FluidUtil.getFluidContained(stack));
		if(i == 1)
			if(TileEntityFurnace.getItemBurnTime(stack) > 0)
				return true;
		if(i == 2)
			return (stack.getItem() instanceof IBatteryItem);
		return true;
	}
	
	@Override
	public boolean canInsertItem(final int slot, final ItemStack itemStack, final int amount) {
		return isItemValidForSlot(slot, itemStack);
	}
	
	@Override
	public boolean canExtractItem(final int slot, final ItemStack itemStack, final int amount) {
        return slot == 3;
    }
	
	@Override
	public void update() {
		if(!world.isRemote) {
			this.sendPower(world, pos);
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos.getX(), pos.getY(), pos.getZ(), new FluidTank[] {tank}), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			if (needsUpdate) {
				needsUpdate = false;
			}
			//Water
			if(this.inputValidForTank(-1, 0))
				FFUtils.fillFromFluidContainer(inventory, tank, 0, 3);

			//Battery Item
			power = Library.chargeItemsFromTE(inventory, 2, power, maxPower);
			
			boolean trigger = !isItemValid() || this.burnTime != 0;

            if(trigger)
            {
                MachineCoal.updateBlockState(this.burnTime > 0, this.world, this.pos);
            }
			
			generate();
			detectAndSendChanges();
		}
	}
	
	

	public void generate() {
		
		if(inventory.getStackInSlot(1) != ItemStack.EMPTY && TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(1)) > 0 && burnTime <= 0)
		{
			burnTime = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(1)) / 2;
			final Item containerItem = inventory.getStackInSlot(1).getItem().getContainerItem();
			inventory.getStackInSlot(1).shrink(1);
			if(inventory.getStackInSlot(1).isEmpty())
			{
				if(containerItem != null)
					inventory.setStackInSlot(1, ItemStackUtil.itemStackFrom(containerItem));
				else
					inventory.setStackInSlot(1, ItemStack.EMPTY);
			}
		}
		
		if(burnTime > 0)
		{
			burnTime--;
			
			if(tank.getFluidAmount() > 0)
			{
				tank.drain(1, true);
				
				if(power + 25 <= maxPower)
				{
					power += 25;
				} else {
					power = maxPower;
				}
			}
		}
	}
	
	public boolean isItemValid() {

        return inventory.getStackInSlot(1) != ItemStack.EMPTY && TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(1)) > 0;
    }
	
	protected boolean inputValidForTank(final int tank, final int slot){
		if(inventory.getStackInSlot(slot) != null && !inventory.getStackInSlot(slot).isEmpty()){
            return isValidFluid(FluidUtil.getFluidContained(inventory.getStackInSlot(slot)));
		}
		return false;
	}
	
	public long getPowerScaled(final long i) {
		return (power * i) / maxPower;
	}
	
	public boolean isUseableByPlayer(final EntityPlayer player) {
		if(world.getTileEntity(pos) != this)
		{
			return false;
		}else{
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=64;
		}
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if(nbt.hasKey("inventory"))
			inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
		
		this.power = nbt.getLong("powerTime");
		detectPower = power + 1;
		this.burnTime = nbt.getInteger("burnTime");
		detectBurnTime = burnTime + 1;
		
		tank.readFromNBT(nbt);
		
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
		nbt.setLong("powerTime", power);
		nbt.setInteger("burnTime", burnTime);
		
		tank.writeToNBT(nbt);
		final NBTTagCompound tag = inventory.serializeNBT();
		nbt.setTag("inventory", tag);
		return super.writeToNBT(nbt);
	}

	@Override
	public void recievePacket(final NBTTagCompound[] tags) {
		if(tags.length != 1){
        } else {
			tank.readFromNBT(tags[0]);
		}
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return super.getUpdatePacket();
	}
	@Override
	public void onDataPacket(final NetworkManager net, final SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
	}
	
	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{tank.getTankProperties()[0]};
	}

	@Override
	public int fill(final FluidStack resource, final boolean doFill) {
		if (isValidFluid(resource)) {
			return tank.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(final FluidStack resource, final boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(final int maxDrain, final boolean doDrain) {
		return null;
	}
	
	private boolean isValidFluid(final FluidStack stack) {
		if(stack == null)
			return false;
		return stack.getFluid() == FluidRegistry.WATER;
	}

	private long detectPower;
	private int detectBurnTime;
	private FluidTank detectTank = null;
	
	private void detectAndSendChanges() {
		boolean mark = false;
		
		if(detectPower != power){
			mark = true;
			detectPower = power;
		}
		PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos.getX(), pos.getY(), pos.getZ(), power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
		if(detectBurnTime != burnTime){
			mark = true;
			detectBurnTime = burnTime;
		}
		PacketDispatcher.wrapper.sendToAllAround(new AuxGaugePacket(pos.getX(), pos.getY(), pos.getZ(), burnTime, 0), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
		if(!FFUtils.areTanksEqual(tank, detectTank)){
			mark = true;
			detectTank = FFUtils.copyTank(tank);
			needsUpdate = true;
		}
		if(mark)
			markDirty();
	}
	
	@Override
	public long getPower() {
		return power;
	}

	@Override
	public void setPower(final long i) {
		power = i;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}
}
