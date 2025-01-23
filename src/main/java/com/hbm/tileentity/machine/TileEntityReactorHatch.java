package com.hbm.tileentity.machine;

import com.hbm.blocks.machine.ReactorHatch;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityReactorHatch extends TileEntity implements IFluidHandler {

	@Override
	public IFluidTankProperties[] getTankProperties() {
		final TileEntityMachineReactorLarge fillable = this.getReactorTE(world, pos);
		if(fillable != null)
			return fillable.getTankProperties();
		return new IFluidTankProperties[]{};
	}

	@Override
	public int fill(final FluidStack resource, final boolean doFill) {
		final TileEntityMachineReactorLarge fillable = this.getReactorTE(world, pos);
		if(fillable != null)
			return fillable.fill(resource, doFill);
		return 0;
	}

	@Override
	public FluidStack drain(final FluidStack resource, final boolean doDrain) {
		final TileEntityMachineReactorLarge fillable = this.getReactorTE(world, pos);
		if(fillable != null)
			return fillable.drain(resource, doDrain);
		return null;
	}

	@Override
	public FluidStack drain(final int maxDrain, final boolean doDrain) {
		final TileEntityMachineReactorLarge fillable = this.getReactorTE(world, pos);
		if(fillable != null)
			return fillable.drain(maxDrain, doDrain);
		return null;
	}
	
	private TileEntityMachineReactorLarge getReactorTE(final World world, final BlockPos pos) {
		final EnumFacing e = world.getBlockState(pos).getValue(ReactorHatch.FACING);
		if(e == EnumFacing.NORTH)
		{
			final TileEntity reactor = world.getTileEntity(pos.add(0, 0, 2));
			if(reactor instanceof TileEntityMachineReactorLarge)
			{
				if(((TileEntityMachineReactorLarge)reactor).checkBody())
				{
					return (TileEntityMachineReactorLarge)reactor;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		if(e == EnumFacing.SOUTH)
		{
			final TileEntity reactor = world.getTileEntity(pos.add(0, 0, -2));
			if(reactor instanceof TileEntityMachineReactorLarge)
			{
				if(((TileEntityMachineReactorLarge)reactor).checkBody())
				{
					return (TileEntityMachineReactorLarge)reactor;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		if(e == EnumFacing.WEST)
		{
			final TileEntity reactor = world.getTileEntity(pos.add(2, 0, 0));
			if(reactor instanceof TileEntityMachineReactorLarge)
			{
				if(((TileEntityMachineReactorLarge)reactor).checkBody())
				{
					return (TileEntityMachineReactorLarge)reactor;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		if(e == EnumFacing.EAST)
		{
			final TileEntity reactor = world.getTileEntity(pos.add(-2, 0, 0));
			if(reactor instanceof TileEntityMachineReactorLarge)
			{
				if(((TileEntityMachineReactorLarge)reactor).checkBody())
				{
					return (TileEntityMachineReactorLarge)reactor;
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this) : super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

}
