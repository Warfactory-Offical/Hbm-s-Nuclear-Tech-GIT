package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.Spaghetti;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

//TODO: clean this shit up
//Alcater: on it
//Alcater: almost done yay
@Spaghetti("everything")
public class MachineRecipes {

	// return: Fluid, amount produced, amount required, HE produced
	public static Object[] getTurbineOutput(final Fluid type) {

		if (type == ModForgeFluids.steam) {
			return new Object[] { ModForgeFluids.spentsteam, 5, 500, 50 };
		} else if (type == ModForgeFluids.hotsteam) {
			return new Object[] { ModForgeFluids.steam, 50, 5, 100 };
		} else if (type == ModForgeFluids.superhotsteam) {
			return new Object[] { ModForgeFluids.hotsteam, 50, 5, 150 };
		} else if(type == ModForgeFluids.ultrahotsteam){
			return new Object[] { ModForgeFluids.superhotsteam, 50, 5, 250 };
		}

		return null;
	}

	public static List<GasCentOutput> getGasCentOutput(final Fluid fluid) {
		
		final List<GasCentOutput> list = new ArrayList<GasCentOutput>();
		if(fluid == null){
			return null;
		} else if(fluid == ModForgeFluids.uf6){
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U235)), 1));
			list.add(new GasCentOutput(19, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U238)), 2));
			list.add(new GasCentOutput(7, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL)), 3));
			list.add(new GasCentOutput(9, ItemStackUtil.itemStackFrom(ModItems.fluorite), 4));
			return list;
		} else if(fluid == ModForgeFluids.puf6){
			list.add(new GasCentOutput(3, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU238)), 1));
			list.add(new GasCentOutput(2, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU239)), 2));
			list.add(new GasCentOutput(4, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU240)), 3));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.fluorite), 4));
			return list;
		} else if(fluid == ModForgeFluids.watz){
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SOLINIUM)), 1));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.URANIUM)), 2));
			list.add(new GasCentOutput(5, ItemStackUtil.itemStackFrom(ModItems.powder_lead), 3));
			list.add(new GasCentOutput(10, ItemStackUtil.itemStackFrom(ModItems.dust), 4));
			return list;
		} else if(fluid == ModForgeFluids.sas3){
			list.add(new GasCentOutput(4, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM)), 1));
			list.add(new GasCentOutput(4, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM)), 2));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.sulfur), 3));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.sulfur), 4));
			return list;
		} else if(fluid == ModForgeFluids.coolant){
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.niter), 1));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.niter), 2));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.niter), 3));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.niter), 4));
			return list;
		} else if(fluid == ModForgeFluids.cryogel){
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.powder_ice), 1));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.powder_ice), 2));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.niter), 3));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.niter), 4));
			return list;
		} else if(fluid == ModForgeFluids.nitan){
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix), 1));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix), 2));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix), 3));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix), 4));
			return list;
		} else if(fluid == ModForgeFluids.liquid_osmiridium){
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.powder_impure_osmiridium), 1));
			list.add(new GasCentOutput(2, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite), 2));
			list.add(new GasCentOutput(4, ItemStackUtil.itemStackFrom(ModItems.powder_meteorite_tiny), 3));
			list.add(new GasCentOutput(1, ItemStackUtil.itemStackFrom(ModItems.powder_paleogenite_tiny), 4));
			return list;
		}
		
		return null;
	}
	
	public static class GasCentOutput {
		public int weight;
		public ItemStack output;
		public int slot;
		
		public GasCentOutput(final int w, final ItemStack s, final int i) {
			weight = w;
			output = s;
			slot = i;
		}
	}


	public static int getFluidConsumedGasCent(final Fluid fluid) {
		if(fluid == null)
			return 0;
		else if(fluid == FluidRegistry.LAVA)
			return 1000;
		else if(fluid == ModForgeFluids.uf6)
			return 100;
		else if(fluid == ModForgeFluids.puf6)
			return 100;
		else if(fluid == ModForgeFluids.watz)
			return 1000;
		else if(fluid == ModForgeFluids.sas3)
			return 100;
		else if(fluid == ModForgeFluids.coolant)
			return 2000;
		else if(fluid == ModForgeFluids.cryogel)
			return 1000;
		else if(fluid == ModForgeFluids.nitan)
			return 500;
		else if(fluid == ModForgeFluids.liquid_osmiridium)
			return 1000; //whose idea was 2000 heck nah
		else
			return 0;
	}
}
