package com.hbm.items.machine;
import com.hbm.util.ItemStackUtil;

import java.util.List;
import java.util.Map.Entry;

import com.hbm.forgefluid.HbmFluidHandlerItemStack;
import com.hbm.interfaces.IHasCustomModel;
import com.hbm.config.GeneralConfig;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.forgefluid.FluidTypeHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFluidTank extends Item implements IHasCustomModel {

	public static final ModelResourceLocation fluidTankModel = new ModelResourceLocation(
			RefStrings.MODID + ":fluid_tank_full", "inventory");
	
	public static final ModelResourceLocation fluidBarrelModel = new ModelResourceLocation(
			RefStrings.MODID + ":fluid_barrel_full", "inventory");

	private final int cap;

	public ItemFluidTank(final String s, final int cap) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setHasSubtypes(true);
		this.setMaxDamage(cap);
		this.setMaxStackSize(1);
		this.cap = cap;

		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public int getItemStackLimit(final ItemStack stack) {
		return isFullOrEmpty(stack) ? 64 : 1;
	}

	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> items) {
		if(GeneralConfig.registerTanks){
			if (tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH) {
				final ItemStack empty = ItemStackUtil.itemStackFrom(this, 1, 0);
				empty.setTagCompound(new NBTTagCompound());
				items.add(empty);
				for (final Entry<String, Fluid> entry : FluidRegistry.getRegisteredFluids().entrySet()) {
					if(FluidTypeHandler.noContainer(entry.getValue())) continue;
					final ItemStack stack = ItemStackUtil.itemStackFrom(this, 1, 0);
					stack.setTagCompound(new NBTTagCompound());
					stack.getTagCompound().setTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY,
							new FluidStack(entry.getValue(), cap).writeToNBT(new NBTTagCompound()));
					items.add(stack);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(final ItemStack stack) {
		String s = (I18n.format(this.getTranslationKey() + ".name")).trim();
		String s1 = null;// ("" +
							// StatCollector.translateToLocal(FluidType.getEnum(stack.getItemDamage()).getTranslationKey()))
		// .trim();
		if (FluidUtil.getFluidContained(stack) != null) {
			s1 = (I18n.format(FluidUtil.getFluidContained(stack).getLocalizedName()).trim());
		} else {
			if(this == ModItems.fluid_tank_full)
				return "Empty Universal Fluid Tank";
			else if(this == ModItems.fluid_barrel_full)
				return "Empty Fluid Barrel";
		}

		if (s1 != null) {
			s = s + " " + s1;
		}

		return s;
	}

	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, final NBTTagCompound nbt) {
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		return new HbmFluidHandlerItemStack(stack, cap);
	}

	@Override
	public ModelResourceLocation getResourceLocation() {
		if(this == ModItems.fluid_tank_full)
			return fluidTankModel;
		else if(this == ModItems.fluid_barrel_full)
			return fluidBarrelModel;
		return null;
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		
		final FluidStack f = FluidUtil.getFluidContained(stack);
		String s = (f == null ? "0" : f.amount) + "/" + cap + " mB";
		if(stack.getCount() > 1)
			s = stack.getCount() + "x " + s;
		list.add(s);

	}
	
	public static ItemStack getFullBarrel(final Fluid f, final int amount){
		final ItemStack stack = ItemStackUtil.itemStackFrom(ModItems.fluid_barrel_full, amount, 0);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY, new FluidStack(f, 16000).writeToNBT(new NBTTagCompound()));
		return stack;
	}
	
	public static ItemStack getFullBarrel(final Fluid f){
		return getFullBarrel(f, 1);
	}
	
	public static ItemStack getFullTank(final Fluid f, final int amount){
		final ItemStack stack = ItemStackUtil.itemStackFrom(ModItems.fluid_tank_full, amount, 0);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY, new FluidStack(f, 1000).writeToNBT(new NBTTagCompound()));
		return stack;
	}
	
	public static ItemStack getFullTank(final Fluid f){
		return getFullTank(f, 1);
	}
	
	public static boolean isFullOrEmpty(final ItemStack stack){
		if(stack.hasTagCompound() && stack.getItem() == ModItems.fluid_barrel_full){
			final FluidStack f = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY));
			if(f == null)
				return true;
			return f.amount == 16000 || f.amount == 0;
			
		} else if(stack.hasTagCompound() && stack.getItem() == ModItems.fluid_tank_full){
			final FluidStack f = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY));
			if(f == null)
				return true;
			return f.amount == 1000 || f.amount == 0;
		} else return stack.getItem() == ModItems.fluid_barrel_full || stack.getItem() == ModItems.fluid_tank_full;
    }

	public static boolean isEmptyTank(final ItemStack out) {
        return out.getItem() == ModItems.fluid_tank_full && FluidUtil.getFluidContained(out) == null;
    }

	public static boolean isFullTank(final ItemStack stack, final Fluid fluid) {
		final FluidStack f = FluidUtil.getFluidContained(stack);
        return stack.getItem() == ModItems.fluid_tank_full && f != null && f.getFluid() == fluid && f.amount == 1000;
    }
	
	public static boolean isEmptyBarrel(final ItemStack out) {
        return out.getItem() == ModItems.fluid_barrel_full && FluidUtil.getFluidContained(out) == null;
    }

	public static boolean isFullBarrel(final ItemStack stack, final Fluid fluid) {
		final FluidStack f = FluidUtil.getFluidContained(stack);
        return stack.getItem() == ModItems.fluid_barrel_full && f != null && f.getFluid() == fluid && f.amount == 16000;
    }
}
