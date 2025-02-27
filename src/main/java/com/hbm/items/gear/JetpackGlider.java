package com.hbm.items.gear;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.handler.ArmorModHandler;
import com.hbm.handler.JetpackHandler;
import com.hbm.interfaces.IItemFluidHandler;
import com.hbm.items.armor.ItemArmorMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class JetpackGlider extends ItemArmorMod implements IItemFluidHandler {

	public int capacity;
	
	public JetpackGlider(final ArmorMaterial enumArmorMaterialSteel, final int i, final EntityEquipmentSlot chest, final int capacity, final String s) {
		super(ArmorModHandler.plate_only, false, true, false, false, s);
		this.capacity = capacity;
	}

	public FluidTank getTank(final ItemStack stack){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
			return new FluidTank(capacity);
		}
		return new FluidTank(capacity).readFromNBT(stack.getTagCompound().getCompoundTag("fuelTank"));
	}
	
	public void setTank(final ItemStack stack, final FluidTank tank){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setTag("fuelTank", tank.writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		final FluidTank tank = getTank(stack);
		if(tank.getFluid() == null){
			list.add(TextFormatting.RED + "    Fuel Type: None");
			list.add(TextFormatting.RED + "    Fuel Speed: " + JetpackHandler.getSpeed(null));
		} else {
			list.add(TextFormatting.RED + "    Fuel Type: " + I18n.format(tank.getFluid().getUnlocalizedName()));
			list.add(TextFormatting.RED + "    Fuel Speed: " + JetpackHandler.getSpeed(tank.getFluid().getFluid()));
		}
		final int percent = (int)(((float)tank.getFluidAmount()/tank.getCapacity())*100);
		list.add(TextFormatting.RED + "    Fuel Amount: " + tank.getFluidAmount() + "/" + tank.getCapacity() + " (" + percent + "%)");
	}
	
	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		super.addDesc(list, stack, armor);
		addInformation(stack, null, list, null);
	}
	
	@Override
	public int fill(final ItemStack stack, final FluidStack fluid, final boolean doFill) {
		if(fluid == null)
			return 0;
		if(fluid.getFluid() == ModForgeFluids.kerosene || fluid.getFluid() == ModForgeFluids.balefire || fluid.getFluid() == ModForgeFluids.nitan){
			final FluidTank tank = getTank(stack);
			final int fill = tank.fill(fluid, doFill);
			if(doFill)
				setTank(stack, tank);
			return fill;
		}
		return 0;
	}

	@Override
	public FluidStack drain(final ItemStack stack, final FluidStack resource, final boolean doDrain) {
		final FluidTank tank = getTank(stack);
		final FluidStack drain = tank.drain(resource, doDrain);
		if(doDrain)
			setTank(stack, tank);
		return drain;
	}

	@Override
	public FluidStack drain(final ItemStack stack, final int maxDrain, final boolean doDrain) {
		final FluidTank tank = getTank(stack);
		final FluidStack drain = tank.drain(maxDrain, doDrain);
		if(doDrain)
			setTank(stack, tank);
		return drain;
	}
}
