package com.hbm.items.tool;

import com.hbm.forgefluid.HbmFluidHandlerCanister;
import com.hbm.forgefluid.HbmFluidHandlerItemStack;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumCanister;
import com.hbm.interfaces.IHasCustomModel;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import com.hbm.util.ItemStackUtil;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFluidCanister extends Item implements IHasCustomModel {

	public static final ModelResourceLocation fluidCanisterModel = new ModelResourceLocation(RefStrings.MODID + ":canister_empty", "inventory");
	public int cap;
	
	
	public ItemFluidCanister(final String s, final int cap){
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setMaxStackSize(1);
		this.setMaxDamage(cap);
		this.cap = cap;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public int getItemStackLimit(final ItemStack stack) {
		return isFullOrEmpty(stack) ? 64 : 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(final ItemStack stack) {
		final FluidStack f = FluidUtil.getFluidContained(stack);
		if(f == null) {
			return I18n.format("item.canister_empty.name");
		} else {
			//Drillgon200: I don't feel like figuring out this crash so time to slap on a try/catch and call it good enough I guess.
			try {
				return I18n.format(EnumCanister.getEnumFromFluid(f.getFluid()).getTranslateKey());
			} catch (final Exception x){
				return I18n.format("item.canister_empty.name");
			}
		}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		final FluidStack f = FluidUtil.getFluidContained(stack);
		if (this == ModItems.canister_generic && f != null && f.getFluid() == ModForgeFluids.diesel) {
			tooltip.add(I18nUtil.resolveKey("desc.canisterdiesel"));
		}
		String s = (f == null ? "0" : f.amount) + "/" + cap + " mB";
		if(stack.getCount() > 1)
			s = stack.getCount() + "x " + s;
		tooltip.add(s);
	}
	
	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> items) {
		if(tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH){
			for(final Fluid f : EnumCanister.getFluids()){
				final ItemStack stack = ItemStackUtil.itemStackFrom(this, 1, 0);
				stack.setTagCompound(new NBTTagCompound());
				if(f != null)
					stack.getTagCompound().setTag(HbmFluidHandlerCanister.FLUID_NBT_KEY, new FluidStack(f, cap).writeToNBT(new NBTTagCompound()));
				items.add(stack);
			}
		}
	}
	
	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, final NBTTagCompound nbt) {
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		return new HbmFluidHandlerCanister(stack, cap);
	}


	@Override
	public ModelResourceLocation getResourceLocation() {
		return fluidCanisterModel;
	}
	
	public static boolean isFullCanister(final ItemStack stack, final Fluid fluid){
		if(stack != null){
			final FluidStack f = FluidUtil.getFluidContained(stack);
            return stack.getItem() instanceof ItemFluidCanister && f != null && f.getFluid() == fluid && f.amount == ((ItemFluidCanister) stack.getItem()).cap;
		}
		return false;
	}
	
	public static ItemStack getFullCanister(final Fluid f, final int amount){
		final ItemStack stack = ItemStackUtil.itemStackFrom(ModItems.canister_generic, amount, 0);
		stack.setTagCompound(new NBTTagCompound());
		if(EnumCanister.contains(f))
			stack.getTagCompound().setTag(HbmFluidHandlerCanister.FLUID_NBT_KEY, new FluidStack(f, 1000).writeToNBT(new NBTTagCompound()));
		return stack;
	}
	
	public static ItemStack getFullCanister(final Fluid f){
		return getFullCanister(f, 1);
	}
	
	public static boolean isFullOrEmpty(final ItemStack stack){
		if(stack.hasTagCompound() && stack.getItem() == ModItems.canister_generic){
			final FluidStack f = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY));
			if(f == null)
				return true;
			return f.amount == 1000 || f.amount == 0;
			
		} else return stack.getItem() == ModItems.canister_generic;
    }

	public static boolean isEmptyCanister(final ItemStack out) {
        return out.getItem() == ModItems.canister_generic && FluidUtil.getFluidContained(out) == null;
    }
}
