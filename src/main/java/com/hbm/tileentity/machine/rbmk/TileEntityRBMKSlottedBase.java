package com.hbm.tileentity.machine.rbmk;

import com.hbm.lib.ItemStackHandlerWrapper;
import com.hbm.packet.NBTPacket;
import com.hbm.packet.PacketDispatcher;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityRBMKSlottedBase extends TileEntityRBMKActiveBase {

	public ItemStackHandler inventory;

	public TileEntityRBMKSlottedBase(final int scount) {
		inventory = new ItemStackHandler(scount){
			@Override
			protected void onContentsChanged(final int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
			
			@Override
			public boolean isItemValid(final int slot, final ItemStack itemStack) {
				return isItemValidForSlot(slot, itemStack);
			}

			@Override
			public ItemStack insertItem(final int slot, final ItemStack stack, final boolean simulate) {
				if(canInsertItem(slot, stack, stack.getCount()))
					return super.insertItem(slot, stack, simulate);
				return stack;
			}

			@Override
			public ItemStack extractItem(final int slot, final int amount, final boolean simulate) {
				if(canExtractItem(slot, inventory.getStackInSlot(slot), amount))
					return super.extractItem(slot, amount, simulate);
				return ItemStack.EMPTY;
			}
		};
	}

	public int getGaugeScaled(final int i, final FluidTank tank) {
		return tank.getFluidAmount() * i / tank.getCapacity();
	}

	public void networkPack(final NBTTagCompound nbt, final int range) {
		if(!world.isRemote)
			PacketDispatcher.wrapper.sendToAllAround(new NBTPacket(nbt, pos), new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), range));
	}

	public void networkUnpack(final NBTTagCompound nbt) {
		super.networkUnpack(nbt);
	}

	public void handleButtonPacket(final int value, final int meta) {
	}

	@Override
	public void readFromNBT(final NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if(!diag) {
			inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		if(!diag) {
			nbt.setTag("inventory", inventory.serializeNBT());
		}
		return nbt;
	}
	
	public boolean isItemValidForSlot(final int i, final ItemStack stack) {
		return true;
	}
	
	public boolean canInsertItem(final int slot, final ItemStack itemStack, final int amount) {
		return this.isItemValidForSlot(slot, itemStack);
	}

	public boolean canExtractItem(final int slot, final ItemStack itemStack, final int amount) {
		return true;
	}
	
	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory) : 
			super.getCapability(capability, facing);
	}
}