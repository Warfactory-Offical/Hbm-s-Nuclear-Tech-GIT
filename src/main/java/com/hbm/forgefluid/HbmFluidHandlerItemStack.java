package com.hbm.forgefluid;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class HbmFluidHandlerItemStack implements IFluidHandlerItem, ICapabilityProvider {

	public static final String FLUID_NBT_KEY = "HbmFluidKey";

	private final ItemStack container;
	private final int cap;
	
	public HbmFluidHandlerItemStack(final ItemStack stack, final int cap){
		container = stack;
		this.cap = cap;
	}
	
	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{new FluidTankProperties(getFluid(), cap)};
	}

	private FluidStack getFluid(){
		if(!container.hasTagCompound()){
			container.setTagCompound(new NBTTagCompound());
		}
		final NBTTagCompound tag = container.getTagCompound();
		if(!tag.hasKey(FLUID_NBT_KEY)){
			return null;
		}
		return FluidStack.loadFluidStackFromNBT(tag.getCompoundTag(FLUID_NBT_KEY));
	}
	
	private void setFluid(final FluidStack fluid){
		if(!container.hasTagCompound()){
			container.setTagCompound(new NBTTagCompound());
		}
		final NBTTagCompound tag = container.getTagCompound();
		if(fluid == null){
			container.setItemDamage(0);
			tag.removeTag(FLUID_NBT_KEY);
			return;
		}
		container.setItemDamage(cap - fluid.amount);
		tag.setTag(FLUID_NBT_KEY, fluid.writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public int fill(final FluidStack resource, final boolean doFill) {
		if(resource == null || container.getCount() > 1)
			return 0;
		final FluidStack contained = getFluid();
		final int filled;
		if(contained == null){
			filled = Math.min(cap, resource.amount);
			if(doFill){
				setFluid(new FluidStack(resource.getFluid(), filled));
			}
			return filled;
		}
		
		if(contained.getFluid() != resource.getFluid())
			return 0;
		
		filled = Math.min(cap-contained.amount, resource.amount);
		if(doFill){
			setFluid(new FluidStack(contained.getFluid(), filled+contained.amount));
		}
		return filled;
	}

	@Override
	public FluidStack drain(final FluidStack resource, final boolean doDrain) {
		if(container.getCount() > 1 || resource == null || (getFluid() != null && getFluid().getFluid() != resource.getFluid()))
			return null;
		return drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(final int maxDrain, final boolean doDrain) {
		if(container.getCount() > 1)
			return null;
		final FluidStack contained = getFluid();
		if(contained == null)
			return null;
		final int drained = Math.min(contained.amount, maxDrain);
		if(drained <= 0)
			return null;
		if(doDrain){
			setFluid(drained >= contained.amount ? null : new FluidStack(contained.getFluid(), contained.amount - drained));
		}
		return new FluidStack(contained.getFluid(), drained);
	}

	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		if(container.getCount() > 1)
			return false;
		return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		if(container.getCount() > 1)
			return null;
		return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY ? (T)this : null;
	}

	@Override
	public ItemStack getContainer() {
		return container;
	}
}
