package com.hbm.tileentity;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.Spaghetti;
import com.hbm.lib.ItemStackHandlerWrapper;
import com.hbm.packet.NBTPacket;
import com.hbm.packet.PacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@Spaghetti("Not spaghetti in itself, but for the love of god please use this base class for all machines")
public abstract class TileEntityMachineBase extends TileEntityLoadedBase implements INBTPacketReceiver {

	public ItemStackHandler inventory;

	private String customName;

	public TileEntityMachineBase(final int scount) {
		this(scount, 64);
	}

	public TileEntityMachineBase(final int scount, final int slotlimit) {
		inventory = getNewInventory(scount, slotlimit);
	}

	public ItemStackHandler getNewInventory(final int scount, final int slotlimit){
		return new ItemStackHandler(scount){
			@Override
			protected void onContentsChanged(final int slot) {
				super.onContentsChanged(slot);
				markDirty();
			}
			
			@Override
			public int getSlotLimit(final int slot) {
				return slotlimit;
			}
		};
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : getName();
	}

	public abstract String getName();

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}
	
	public void setCustomName(final String name) {
		this.customName = name;
	}
	
	public boolean isUseableByPlayer(final EntityPlayer player) {
		if(world.getTileEntity(pos) != this)
		{
			return false;
		}else{
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=128;
		}
	}
	
	public int[] getAccessibleSlotsFromSide(final EnumFacing e) {
		return new int[] {};
	}
	
	public int getGaugeScaled(final int i, final FluidTank tank) {
		return tank.getFluidAmount() * i / tank.getCapacity();
	}
	
	public void networkPack(final NBTTagCompound nbt, final int range) {

		if(!world.isRemote)
			PacketDispatcher.wrapper.sendToAllAround(new NBTPacket(nbt, pos), new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), range));
	}
	
	public void networkUnpack(final NBTTagCompound nbt) { }
	
	public void handleButtonPacket(final int value, final int meta) { }
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound compound) {
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
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
	
	public int countMufflers() {

		int count = 0;

		for(final EnumFacing dir : EnumFacing.VALUES)
			if(world.getBlockState(pos.offset(dir)).getBlock() == ModBlocks.muffler)
				count++;

		return count;
	}

	public float getVolume(final int toSilence) {

		final float volume = 1 - (countMufflers() / (float)toSilence);

		return Math.max(volume, 0);
	}
	
	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory != null){
			if(facing == null)
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemStackHandlerWrapper(inventory, getAccessibleSlotsFromSide(facing)){
				@Override
				public ItemStack extractItem(final int slot, final int amount, final boolean simulate) {
					if(canExtractItem(slot, inventory.getStackInSlot(slot), amount))
						return super.extractItem(slot, amount, simulate);
					return ItemStack.EMPTY;
				}
				
				@Override
				public ItemStack insertItem(final int slot, final ItemStack stack, final boolean simulate) {
					if(canInsertItem(slot, stack, stack.getCount()))
						return super.insertItem(slot, stack, simulate);
					return stack;
				}
			});
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory != null) || super.hasCapability(capability, facing);
	}
}
