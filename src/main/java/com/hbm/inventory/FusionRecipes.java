package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class FusionRecipes {

	public static int getByproductChance(final Fluid plasma) {
		if(plasma == ModForgeFluids.plasma_dt){
			return 1200; 
		} else if(plasma == ModForgeFluids.plasma_hd){
			return 1200;
		} else if(plasma == ModForgeFluids.plasma_ht){
			return 1200;
		} else if(plasma == ModForgeFluids.plasma_xm){
			return 2400;
		} else if(plasma == ModForgeFluids.plasma_put){
			return 2400;
		} else if(plasma == ModForgeFluids.plasma_bf){
			return 150;
		}
		return 0;
	}

	public static ItemStack getByproduct(final Fluid plasma) {
		if(plasma == ModForgeFluids.plasma_dt){
			return ItemStackUtil.itemStackFrom(ModItems.pellet_charged); 
		} else if(plasma == ModForgeFluids.plasma_hd){
			return ItemStackUtil.itemStackFrom(ModItems.pellet_charged);
		} else if(plasma == ModForgeFluids.plasma_ht){
			return ItemStackUtil.itemStackFrom(ModItems.pellet_charged);
		} else if(plasma == ModForgeFluids.plasma_xm){
			return ItemStackUtil.itemStackFrom(ModItems.powder_chlorophyte);
		} else if(plasma == ModForgeFluids.plasma_put){
			return ItemStackUtil.itemStackFrom(ModItems.powder_xe135);
		} else if(plasma == ModForgeFluids.plasma_bf){
			return ItemStackUtil.itemStackFrom(ModItems.powder_balefire);
		}
		return ItemStack.EMPTY;
	}
	
	public static int getBreedingLevel(final Fluid plasma) {
		if(plasma == ModForgeFluids.plasma_dt){
			return 2;
		} else if(plasma == ModForgeFluids.plasma_hd){
			return 1;
		} else if(plasma == ModForgeFluids.plasma_ht){
			return 1;
		} else if(plasma == ModForgeFluids.plasma_xm){
			return 3;
		} else if(plasma == ModForgeFluids.plasma_put){
			return 4;
		} else if(plasma == ModForgeFluids.plasma_bf){
			return 5;
		}
		return 0;
	}
	
	public static int getSteamProduction(final Fluid plasma) {
		if(plasma == ModForgeFluids.plasma_dt){
			return 225;
		} else if(plasma == ModForgeFluids.plasma_hd){
			return 150;
		} else if(plasma == ModForgeFluids.plasma_ht){
			return 188;
		} else if(plasma == ModForgeFluids.plasma_xm){
			return 450;
		} else if(plasma == ModForgeFluids.plasma_put){
			return 600;
		} else if(plasma == ModForgeFluids.plasma_bf){
			return 1200;
		}
		return 0;
	}

}
