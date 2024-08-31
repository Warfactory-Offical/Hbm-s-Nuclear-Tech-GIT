package com.hbm.forgefluid;

import java.util.HashMap;
import java.util.Map;

import com.hbm.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

//That's it, I'm making my own fluid container registry if forge won't provide one. It won't be compatible with other mods, and it's not as
//good as the forge one, but it'll sure fix that one terrible override in FFUtils.
public class FluidContainerRegistry {
	
	private static final Map<ItemStack, FluidContainerData> containers = new HashMap<>();
	private static final Map<Pair<ItemStack, Fluid>, ItemStack> containerToItemStack = new HashMap<>();

	public static void registerContainer(Item item, Item container, FluidStack fluid){
		registerContainer(item.getDefaultInstance(), container.getDefaultInstance(), fluid);
	}

	public static void registerContainer(ItemStack stack, ItemStack container, FluidStack fluid){
		containers.put(stack, new FluidContainerData(container, fluid));
		containerToItemStack.put(Pair.of(container, fluid.getFluid()), stack);
	}
	
	public static boolean hasFluid(ItemStack stack){
		return containers.containsKey(stack);
	}
	
	public static FluidStack getFluidFromItem(ItemStack stack){
		FluidContainerData data = containers.get(stack);
		if(data == null) return null;
		return data.containedFluid.copy();
	}
	
	public static ItemStack getContainerItem(ItemStack stack){
		FluidContainerData data = containers.get(stack);
		if(data == null) return Items.AIR.getDefaultInstance();
		return data.container;
	}
	
	public static ItemStack getFullContainer(ItemStack stack, Fluid f){
		ItemStack is = containerToItemStack.get(Pair.of(stack, f));
		if(is == null) return Items.AIR.getDefaultInstance();
		return is;
	}
	
	private static class FluidContainerData {
		public ItemStack container;
		public FluidStack containedFluid;
		
		public FluidContainerData(ItemStack container, FluidStack fluid) {
			this.container = container;
			this.containedFluid = fluid;
		}
	}
}
