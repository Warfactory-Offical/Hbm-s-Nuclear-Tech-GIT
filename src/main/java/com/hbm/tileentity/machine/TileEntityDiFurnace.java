package com.hbm.tileentity.machine;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.MachineDiFurnace;
import com.hbm.inventory.DiFurnaceRecipes;
import com.hbm.items.ModItems;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class TileEntityDiFurnace extends TileEntityMachineBase implements ITickable, ICapabilityProvider {

	
	public int dualCookTime;
	public int dualPower;
	public static final int maxPower = 12800;
	public static final int processingSpeed = 400;
	
	private static final int[] slots_top = new int[] {0, 1};
	private static final int[] slots_bottom = new int[] {3};
	private static final int[] slots_side = new int[] {2};
	
	public TileEntityDiFurnace() {
		super(4);
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound compound) {
		this.dualPower = compound.getInteger("dualPower");
		this.dualCookTime = compound.getInteger("cookTime");
		this.detectDualCookTime = this.dualCookTime;
		this.detectDualPower = this.dualPower;
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT((NBTTagCompound) compound.getTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound compound) {
		compound.setInteger("dualPower", this.dualPower);
		compound.setInteger("cookTime", this.dualCookTime);
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update() {
		final boolean flag = this.hasPower();
        final boolean extension = world.getBlockState(pos.up()).getBlock() == ModBlocks.machine_difurnace_ext;

		if(this.dualPower > 0) {
			this.dualPower--;
		}
		final int itemPower = DiFurnaceRecipes.getItemPower(inventory.getStackInSlot(2));
		if (this.hasItemPower(inventory.getStackInSlot(2)) && this.dualPower <= (TileEntityDiFurnace.maxPower - itemPower)) {
			this.dualPower += itemPower;
			if (!inventory.getStackInSlot(2).isEmpty()) {
				final ItemStack copy = inventory.getStackInSlot(2).copy();
				inventory.getStackInSlot(2).shrink(1);
				if (inventory.getStackInSlot(2).isEmpty()) {
					inventory.setStackInSlot(2, copy.getItem().getContainerItem(copy));
				}
			}
		}
		if (flag && canProcess()) {
			dualCookTime += extension ? 3 : 1;
			if (this.dualCookTime >= TileEntityDiFurnace.processingSpeed) {
				this.dualCookTime = 0;
				this.processItem();
			}
		} else {
			dualCookTime = 0;
		}

		if(!world.isRemote)
		{
			boolean trigger = !flag || !canProcess() || this.dualCookTime != 0;

            if(trigger)
            {
                MachineDiFurnace.updateBlockState(this.dualCookTime > 0, extension, this.world, pos);
            }
		}
		
		this.detectAndSendChanges();
	}
	
	public boolean hasItemPower(final ItemStack itemStack) {
		return DiFurnaceRecipes.getItemPower(itemStack) > 0;
	}
	
	@Override
	public String getName(){
		return "container.diFurnace";
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(final EnumFacing e) {
		final int i = e.ordinal();
		return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
	}
	
	@Override
	public boolean isItemValidForSlot(final int i, final ItemStack stack) {
		if(i == 3)
			return false;
		if(i == 2)
			return hasItemPower(stack);
		return true;
	}
	
	@Override
	public boolean canInsertItem(final int slot, final ItemStack itemStack, final int amount) {
		if(slot == 0 && isItemValidForSlot(slot, itemStack)) return inventory.getStackInSlot(1).getItem() != itemStack.getItem();
		if(slot == 1 && isItemValidForSlot(slot, itemStack)) return inventory.getStackInSlot(0).getItem() != itemStack.getItem();
		return isItemValidForSlot(slot, itemStack);
	}
	
	@Override
	public boolean canExtractItem(final int slot, final ItemStack itemStack, final int amount) {
        return slot == 3;
    }
	
	public boolean isUsableByPlayer(final EntityPlayer player){
		if(world.getTileEntity(pos) != this)
		{
			return false;
		}else{
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=64;
		}
	}
	
	public int getDiFurnaceProgressScaled(final int i) {
		return (dualCookTime * i) / processingSpeed;
	}
	
	public int getPowerRemainingScaled(final int i) {
		return (dualPower * i) / maxPower;
	}
	
	public boolean canProcess() {
		if(inventory.getStackInSlot(0) == null || inventory.getStackInSlot(1) == null)
		{
			return false;
		}
		final ItemStack itemStack = DiFurnaceRecipes.getFurnaceProcessingResult(inventory.getStackInSlot(0), inventory.getStackInSlot(1));
		if(itemStack == null)
		{	
			return false;
		}
		
		if(inventory.getStackInSlot(3) == ItemStack.EMPTY)
		{
			return true;
		}
		if(inventory.getStackInSlot(3).getItem() != ItemStack.EMPTY.getItem() && !inventory.getStackInSlot(3).isItemEqual(itemStack)) {
			return false;
		}
		
		if(inventory.getStackInSlot(3).getCount() < inventory.getSlotLimit(3) && inventory.getStackInSlot(3).getCount() < inventory.getStackInSlot(3).getMaxStackSize()) {
			return true;
		}else{
			return inventory.getStackInSlot(3).getCount() < itemStack.getMaxStackSize();
		}
	}
	
	private void processItem() {
		if(canProcess()) {
			final ItemStack itemStack = DiFurnaceRecipes.getFurnaceProcessingResult(inventory.getStackInSlot(0), inventory.getStackInSlot(1));
			
			if(inventory.getStackInSlot(3).isEmpty())
			{
				inventory.setStackInSlot(3, itemStack.copy());
			}else if(inventory.getStackInSlot(3).isItemEqual(itemStack)) {
				inventory.getStackInSlot(3).grow(itemStack.getCount());
			}
			
			for(int i = 0; i < 2; i++)
			{
				if(inventory.getStackInSlot(i).getCount() <= 0)
				{
					inventory.setStackInSlot(i, ItemStackUtil.itemStackFrom(inventory.getStackInSlot(i).getItem().setFull3D()));
				}else{
					inventory.getStackInSlot(i).shrink(1);
				}
				if(inventory.getStackInSlot(i).getCount() <= 0)
				{
					inventory.setStackInSlot(i, ItemStack.EMPTY);
				}
			}
		}
	}
	
	public boolean hasPower() {
		return dualPower > 0;
	}
	
	public boolean isProcessing() {
		return this.dualCookTime > 0;
	}
	
	private int detectDualCookTime;
	private int detectDualPower;
	
	private void detectAndSendChanges() {
		boolean mark = false;
		if(detectDualCookTime != dualCookTime){
			mark = true;
			detectDualCookTime = dualCookTime;
		}
		if(detectDualPower != dualPower){
			mark = true;
			detectDualPower = dualPower;
		}
		if(mark)
			markDirty();
	}
}
