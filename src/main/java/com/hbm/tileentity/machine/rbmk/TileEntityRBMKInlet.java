package com.hbm.tileentity.machine.rbmk;

import com.hbm.blocks.machine.rbmk.RBMKBase;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.lib.ForgeDirection;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityRBMKInlet extends TileEntity implements IFluidHandler, ITickable {
	
	public FluidTank water;
	
	public TileEntityRBMKInlet() {
		water = new FluidTank(32000);
	}
	
	@Override
	public void update() {
		
		if(!world.isRemote) {
			
			for(int i = 2; i < 6; i++) {
				final ForgeDirection dir = ForgeDirection.getOrientation(i);
				final Block b = world.getBlockState(new BlockPos(pos.getX() + dir.offsetX, pos.getY(), pos.getZ() + dir.offsetZ)).getBlock();
				
				if(b instanceof RBMKBase) {
					final int[] pos = ((RBMKBase)b).findCore(world, this.pos.getX() + dir.offsetX, this.pos.getY(), this.pos.getZ() + dir.offsetZ);
					
					if(pos != null) {
						final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
						
						if(te instanceof TileEntityRBMKBase rbmk) {

                            final int prov = Math.min(TileEntityRBMKBase.maxWater - rbmk.water, water.getFluidAmount());
							rbmk.water += prov;
							water.drain(prov, true);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.water.readFromNBT(nbt.getCompoundTag("tank"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("tank", water.writeToNBT(new NBTTagCompound()));
		return nbt;
	}

	@Override
	public IFluidTankProperties[] getTankProperties(){
		return water.getTankProperties();
	}

	@Override
	public int fill(final FluidStack resource, final boolean doFill){
		if(resource != null && resource.getFluid() == FluidRegistry.WATER)
			return water.fill(resource, doFill);
		else
			return 0;
	}

	@Override
	public FluidStack drain(final FluidStack resource, final boolean doDrain){
		return null;
	}

	@Override
	public FluidStack drain(final int maxDrain, final boolean doDrain){
		return null;
	}

	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing){
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing){
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

}