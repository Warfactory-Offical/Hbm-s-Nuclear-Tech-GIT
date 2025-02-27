package com.hbm.tileentity.machine;

import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.items.ModItems;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.lib.Library;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityMachineBase;

import api.hbm.energy.IBatteryItem;
import api.hbm.energy.IEnergyUser;
import com.hbm.util.ItemStackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class TileEntityMachineCMBFactory extends TileEntityMachineBase implements ITickable, IEnergyUser, IFluidHandler, ITankPacketAcceptor {

	public long power = 0;
	public int process = 0;
	public int soundCycle = 0;
	public static final long maxPower = 100000000;
	public static final int processSpeed = 200;
	public FluidTank tank;
	public Fluid tankType = ModForgeFluids.watz;
	public boolean needsUpdate = false;

	private static final int[] slots_top = new int[] {1, 3};
	private static final int[] slots_bottom = new int[] {0, 2, 4};
	private static final int[] slots_side = new int[] {0, 2};

	public TileEntityMachineCMBFactory() {
		super(6);
		tank = new FluidTank(8000);
	}

	@Override
	public String getName(){
		return "container.machineCMB";
	}

	@Override
	public int[] getAccessibleSlotsFromSide(final EnumFacing e){
		final int i = e.ordinal();
		return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
	}

	@Override
	public boolean canInsertItem(final int slot, final ItemStack stack, final int amount){
		return this.isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(final int i, final ItemStack itemStack, final int amount){
		if(i == 4)
			return true;
		if(i == 0)
			if (itemStack.getItem() instanceof IBatteryItem && ((IBatteryItem)itemStack.getItem()).getCharge(itemStack) == 0)
				return true;
		if(i == 2)
            return FFUtils.containsFluid(itemStack, ModForgeFluids.watz);
		return false;
	}

	@Override
	public boolean isItemValidForSlot(final int i, final ItemStack stack){
		switch(i)
		{
		case 0:
			if(stack.getItem() instanceof IBatteryItem)
				return true;
			break;
		case 1:
			if(ItemStackUtil.isSameMetaItem(stack, ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN)) || stack.getItem() == ModItems.powder_magnetized_tungsten)
				return true;
			break;
		case 2:
			if(FFUtils.containsFluid(stack, ModForgeFluids.watz))
				return true;
			break;
		case 3:
			if(ItemStackUtil.isSameMetaItem(stack, ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY)) || stack.getItem() == ModItems.powder_advanced_alloy)
				return true;
			break;
		}

		return false;
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
		power = compound.getLong("power");
		tank.readFromNBT(compound);
		process = compound.getShort("process");
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound compound) {
		compound.setLong("power", power);
		tank.writeToNBT(compound);
		compound.setShort("process", (short) process);
		return super.writeToNBT(compound);
	}

	public long getPowerScaled(final long i) {
		return (power * i) / maxPower;
	}

	public int getProgressScaled(final int i) {
		return (process * i) / processSpeed;
	}

	public boolean canProcess() {

		boolean b = false;

		if(tank.getFluidAmount() >= 1 && power >= 100000 && !inventory.getStackInSlot(1).isEmpty() && !inventory.getStackInSlot(3).isEmpty() && (inventory.getStackInSlot(4).isEmpty() || inventory.getStackInSlot(4).getCount() <= 60))
		{
			final boolean flag0 = ItemStackUtil.isSameMetaItem(inventory.getStackInSlot(1), ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN)) || inventory.getStackInSlot(1).getItem() == ModItems.powder_magnetized_tungsten;
			final boolean flag1 = ItemStackUtil.isSameMetaItem(inventory.getStackInSlot(3), ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY)) || inventory.getStackInSlot(3).getItem() == ModItems.powder_advanced_alloy;

			b = flag0 && flag1;
		}

		return  b;
	}

	public boolean isProcessing() {
		return process > 0;
	}

	public void process() {
		tank.drain(1, true);
		needsUpdate = true;
		power -= 100000;

		process++;

		if(process >= processSpeed) {

			inventory.getStackInSlot(1).shrink(1);
			if (inventory.getStackInSlot(1).isEmpty()) {
				inventory.setStackInSlot(1, ItemStack.EMPTY);
			}

			inventory.getStackInSlot(3).shrink(1);
			if (inventory.getStackInSlot(3).isEmpty()) {
				inventory.setStackInSlot(3, ItemStack.EMPTY);
			}

			if(inventory.getStackInSlot(4).isEmpty())
			{
				inventory.setStackInSlot(4, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COMBINE_STEEL), 4));
			} else {
				//Possible dupe glitch? Check later.
				inventory.getStackInSlot(4).grow(4);
			}
			
			process = 0;
		}
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {

			this.updateStandardConnections(world, pos);

			final long prevPower = power;
			final int prevAmount = tank.getFluidAmount();
			if (needsUpdate) {
				needsUpdate = false;
			}
			power = Library.chargeTEFromItems(inventory, 0, power, maxPower);
			
			if(this.inputValidForTank(-1, 2))
				if(FFUtils.fillFromFluidContainer(inventory, tank, 2, 5))
					needsUpdate = true;

			if (canProcess()) {
				process();
				if(soundCycle == 0)
			        this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_MINECART_RIDING, SoundCategory.BLOCKS, 1.0F, 1.5F);
				soundCycle++;
					
				if(soundCycle >= 25)
					soundCycle = 0;
			} else {
				process = 0;
			}

			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos, power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, tank), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			if(prevPower != power || prevAmount != tank.getFluidAmount()){
				markDirty();
			}
		}
	}
	
	protected boolean inputValidForTank(final int tank, final int slot){
		if(!inventory.getStackInSlot(slot).isEmpty()){
            return isValidFluid(FluidUtil.getFluidContained(inventory.getStackInSlot(slot)));
		}
		return false;
	}
	
	private boolean isValidFluid(final FluidStack stack) {
		if(stack == null)
			return false;
		return stack.getFluid() == ModForgeFluids.watz;
	}
	
	@Override
	public void setPower(final long i) {
		power = i;
		
	}

	@Override
	public long getPower() {
		return power;
		
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{tank.getTankProperties()[0]};
	}

	@Override
	public int fill(final FluidStack resource, final boolean doFill) {
		if (isValidFluid(resource)) {
			if(tank.fill(resource, false) > 0)
				needsUpdate = true;
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

	@Override
	public void recievePacket(final NBTTagCompound[] tags) {
		if(tags.length != 1) {
        } else {
			tank.readFromNBT(tags[0]);
		}
	}
	
	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		} else {
			return super.getCapability(capability, facing);
		}
	}
	
	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return true;
		} else {
			return super.hasCapability(capability, facing);
		}
	}

}
