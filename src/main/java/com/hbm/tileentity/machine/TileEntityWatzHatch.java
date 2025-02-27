package com.hbm.tileentity.machine;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityWatzHatch extends TileEntity implements IFluidHandler {

	@Override
	public IFluidTankProperties[] getTankProperties() {
		final TileEntityWatzCore fillable = this.getReactorTE(world, pos);
		if(fillable != null)
			return fillable.getTankProperties();
		return new IFluidTankProperties[]{};
	}

	@Override
	public int fill(final FluidStack resource, final boolean doFill) {
		final TileEntityWatzCore fillable = this.getReactorTE(world, pos);
		if(fillable != null)
			return fillable.fill(resource, doFill);
		return 0;
	}

	@Override
	public FluidStack drain(final FluidStack resource, final boolean doDrain) {
		final TileEntityWatzCore fillable = this.getReactorTE(world, pos);
		if(fillable != null)
			return fillable.drain(resource, doDrain);
		return null;
	}

	@Override
	public FluidStack drain(final int maxDrain, final boolean doDrain) {
		final TileEntityWatzCore fillable = this.getReactorTE(world, pos);
		if(fillable != null)
			return fillable.drain(maxDrain, doDrain);
		return null;
	}
	
	private TileEntityWatzCore getReactorTE(final World world, final BlockPos pos) {
		final EnumFacing e = world.getBlockState(pos).getValue(BlockHorizontal.FACING);
		if(e == EnumFacing.NORTH)
		{
			final TileEntity te = world.getTileEntity(pos.add(0, 0, 3));
			if(te instanceof TileEntityWatzCore)
			{
				if(((TileEntityWatzCore)te).isStructureValid(world))
				{
					return (TileEntityWatzCore)te;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		if(e == EnumFacing.SOUTH)
		{
			final TileEntity te = world.getTileEntity(pos.add(0, 0, -3));
			if(te instanceof TileEntityWatzCore)
			{
				if(((TileEntityWatzCore)te).isStructureValid(world))
				{
					return (TileEntityWatzCore)te;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		if(e == EnumFacing.WEST)
		{
			final TileEntity te = world.getTileEntity(pos.add(3, 0, 0));
			if(te instanceof TileEntityWatzCore)
			{
				if(((TileEntityWatzCore)te).isStructureValid(world))
				{
					return (TileEntityWatzCore)te;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		if(e == EnumFacing.EAST)
		{
			final TileEntity te = world.getTileEntity(pos.add(-3, 0, 0));
			if(te instanceof TileEntityWatzCore)
			{
				if(((TileEntityWatzCore)te).isStructureValid(world))
				{
					return (TileEntityWatzCore)te;
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
