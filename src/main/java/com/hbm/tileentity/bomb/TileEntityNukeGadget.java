package com.hbm.tileentity.bomb;

import com.hbm.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityNukeGadget extends TileEntity {

	public ItemStackHandler inventory;
	private String customName;
	
	public TileEntityNukeGadget() {
		inventory = new ItemStackHandler(6){
			@Override
			protected void onContentsChanged(final int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
		};
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.nukeGadget";
	}

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
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=64;
		}
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound compound) {
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	public boolean exp1() {
        return inventory.getStackInSlot(1).getItem() == ModItems.gadget_explosive8;
    }
	
	public boolean exp2() {
        return inventory.getStackInSlot(2).getItem() == ModItems.gadget_explosive8;
    }
	
	public boolean exp3() {
        return inventory.getStackInSlot(3).getItem() == ModItems.gadget_explosive8;
    }
	
	public boolean exp4() {
        return inventory.getStackInSlot(4).getItem() == ModItems.gadget_explosive8;
    }
	
	public boolean isReady() {
		if(this.exp1() && this.exp2() && this.exp3() && this.exp4())
		{
            return inventory.getStackInSlot(0).getItem() == ModItems.gadget_wireing && inventory.getStackInSlot(5).getItem() == ModItems.gadget_core;
		}
		
		return false;
	}
	
	public void clearSlots() {
		for(int i = 0; i < inventory.getSlots(); i++)
		{
			inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}
	
	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory) : super.getCapability(capability, facing);
	}
}
