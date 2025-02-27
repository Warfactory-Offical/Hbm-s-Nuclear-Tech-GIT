package com.hbm.items.tool;

import com.hbm.forgefluid.HbmFluidHandlerItemStackInf;
import com.hbm.items.ModItems;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.List;

public class ItemFluidContainerInfinite extends Item {

	private final int maxDrain;
	
	public ItemFluidContainerInfinite(final int maxDrain, final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.maxDrain = maxDrain;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, final NBTTagCompound nbt) {
		return new HbmFluidHandlerItemStackInf(stack, maxDrain);
	}

	@Override
	public void addInformation(final ItemStack stack, final World world, final List<String> list, final ITooltipFlag flagIn){
		super.addInformation(stack, world, list, flagIn);
		list.add(I18nUtil.resolveKey("desc.canisterinfinite", maxDrain * 0.02F));
	}
}
