package com.hbm.tileentity.machine.rbmk;

import com.hbm.blocks.machine.rbmk.RBMKBase;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.lib.ForgeDirection;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityRBMKOutlet extends TileEntity implements IFluidHandler, ITickable {
	
	public FluidTank steam;
	public Fluid steamType;
	
	public TileEntityRBMKOutlet() {
		steam = new FluidTank(32000);
		steamType = ModForgeFluids.superhotsteam;
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

                            final int prov = Math.min(steam.getCapacity() - steam.getFluidAmount(), rbmk.steam);
							rbmk.steam -= prov;
							steam.fill(new FluidStack(steamType, prov), true);
						}
					}
				}
			}
			
			fillFluidInit(this.steam);
		}
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.steam.readFromNBT(nbt.getCompoundTag("tank"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("tank", this.steam.writeToNBT(new NBTTagCompound()));
		return nbt;
	}


	public void fillFluidInit(final FluidTank tank) {
		for(final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			fillFluid(this.pos.getX() + dir.offsetX, this.pos.getY() + dir.offsetY, this.pos.getZ() + dir.offsetZ, tank);
	}

	public void fillFluid(final int x, final int y, final int z, final FluidTank tank) {
		FFUtils.fillFluid(this, tank, world, new BlockPos(x, y, z), tank.getCapacity());
	}

	@Override
	public IFluidTankProperties[] getTankProperties(){
		return steam.getTankProperties();
	}

	@Override
	public int fill(final FluidStack resource, final boolean doFill){
		return 0;
	}

	@Override
	public FluidStack drain(final FluidStack resource, final boolean doDrain){
		return steam.drain(resource, doDrain);
	}

	@Override
	public FluidStack drain(final int maxDrain, final boolean doDrain){
		return steam.drain(maxDrain, doDrain);
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