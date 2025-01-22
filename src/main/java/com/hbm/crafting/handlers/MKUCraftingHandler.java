package com.hbm.crafting.handlers;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.hbm.items.ModItems;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.HbmWorldUtility;
import net.minecraft.world.World;

public class MKUCraftingHandler extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	
	public static ItemStack[] MKURecipe;
	private static long lastSeed;

	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		
		if(world == null || world.provider == null || world.getWorldInfo() == null || HbmWorldUtility.getProviderWorld(world.provider) == null)
			return false;
		
		if(MKURecipe == null || world.getSeed() != lastSeed)
			generateRecipe(world);
		
		for(int i = 0; i < 9; i++) {
			ItemStack stack = inventory.getStackInRowAndColumn(i % 3, i / 3);
			ItemStack recipe = MKURecipe[i];
			
			if(stack.isEmpty() && recipe == null)
				continue;
			
			if(!stack.isEmpty() && recipe != null && stack.getItem() == recipe.getItem() && stack.getItemDamage() == recipe.getItemDamage())
				continue;
			
			return false;
		}
		
		return true;
	}
	
	public static void generateRecipe(World world) {
		Random rand = new Random(world.getSeed());
		
		if(lastSeed == world.getSeed() && MKURecipe != null || world.provider == null || world.getWorldInfo() == null || HbmWorldUtility.getProviderWorld(world.provider) == null)
			return;
		
		lastSeed = world.getSeed();
		
		List<ItemStack> list = Arrays.asList(new ItemStack[] {
				ItemStackUtil.itemStackFrom(ModItems.powder_iodine),
				ItemStackUtil.itemStackFrom(ModItems.powder_fire),
				ItemStackUtil.itemStackFrom(ModItems.dust),
				ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY)),
				ItemStackUtil.itemStackFrom(ModItems.morning_glory),
				ItemStackUtil.itemStackFrom(ModItems.syringe_metal_empty),
				null,
				null,
				null
		});
		
		Collections.shuffle(list, rand);
		
		MKURecipe = list.toArray(new ItemStack[9]);
	}
	
	@Override
	public boolean isDynamic(){
		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		return getRecipeOutput();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStackUtil.itemStackFrom(ModItems.syringe_mkunicorn);
	}

	@Override
	public boolean canFit(int width, int height){
		return width >= 3 && height >= 3;
	}
}