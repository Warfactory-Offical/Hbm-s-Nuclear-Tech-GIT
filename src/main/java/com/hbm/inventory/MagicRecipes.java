package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hbm.inventory.OreDictManager.*;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class MagicRecipes {

	//Drillgon200: I hate these warnings. 
	//All it takes are these two characters                 ||
	//But bob evidently just hates not having warnings      VV
	private static final List<MagicRecipe> recipes = new ArrayList<>();

	public static ItemStack getRecipe(InventoryCrafting matrix) {

		List<ComparableStack> comps = new ArrayList<>();

		for(int i = 0; i < 4; i++) {
			if(!matrix.getStackInSlot(i).isEmpty())
				comps.add(ItemStackUtil.comparableStackFrom(matrix.getStackInSlot(i)).makeSingular());
		}

		//Collections.sort(comps);

		for(MagicRecipe recipe : recipes) {
			if(recipe.matches(comps))
				return recipe.getResult();
		}
		
		return ItemStack.EMPTY;
	}

	public static void register() {
		//FIXME: Had to getItem on these, though it worked fine before..?
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.ingot_u238m2), ItemStackUtil.comparableStackFrom(ModItems.ingot_u238m2.getItem(), 1, 1), ItemStackUtil.comparableStackFrom(ModItems.ingot_u238m2.getItem(), 1, 2), ItemStackUtil.comparableStackFrom(ModItems.ingot_u238m2.getItem(), 1, 3)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.rod_of_discord), ItemStackUtil.comparableStackFrom(Items.ENDER_PEARL), ItemStackUtil.comparableStackFrom(ModItems.nugget_euphemium), ItemStackUtil.comparableStackFrom(Items.BLAZE_ROD)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.balefire_and_steel), new OreDictStack(STEEL.ingot()), ItemStackUtil.comparableStackFrom(ModItems.egg_balefire_shard)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.mysteryshovel), ItemStackUtil.comparableStackFrom(Items.IRON_SHOVEL), ItemStackUtil.comparableStackFrom(Items.BONE), ItemStackUtil.comparableStackFrom(ModItems.ingot_starmetal), ItemStackUtil.comparableStackFrom(ModItems.ducttape)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.ingot_electronium), ItemStackUtil.comparableStackFrom(ModItems.pellet_charged), ItemStackUtil.comparableStackFrom(ModItems.pellet_charged), new OreDictStack(DNT.ingot()), new OreDictStack(DNT.ingot())));

		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.ammo_44_pip),
				ItemStackUtil.comparableStackFrom(ModItems.ammo_44),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.ammo_44_bj),
				ItemStackUtil.comparableStackFrom(ModItems.ammo_44),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic),
				ItemStackUtil.comparableStackFrom(ModItems.powder_desh)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.ammo_44_silver),
				ItemStackUtil.comparableStackFrom(ModItems.ammo_44),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic),
				ItemStackUtil.comparableStackFrom(ModItems.ingot_starmetal)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.gun_bf),
				ItemStackUtil.comparableStackFrom(ModItems.gun_fatman),
				ItemStackUtil.comparableStackFrom(ModItems.egg_balefire_shard),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic),
				ItemStackUtil.comparableStackFrom(ModItems.powder_magic)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.diamond_gavel),
				ItemStackUtil.comparableStackFrom(ModBlocks.gravel_diamond),
				ItemStackUtil.comparableStackFrom(ModBlocks.gravel_diamond),
				ItemStackUtil.comparableStackFrom(ModBlocks.gravel_diamond),
				ItemStackUtil.comparableStackFrom(ModItems.lead_gavel)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.mese_gavel),
				ItemStackUtil.comparableStackFrom(ModItems.shimmer_handle),
				ItemStackUtil.comparableStackFrom(ModItems.powder_dineutronium),
				ItemStackUtil.comparableStackFrom(ModItems.blades_desh),
				ItemStackUtil.comparableStackFrom(ModItems.diamond_gavel)));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_mese),
				ItemStackUtil.comparableStackFrom(ModBlocks.hadron_coil_chlorophyte),
				ItemStackUtil.comparableStackFrom(ModItems.powder_dineutronium),
				ItemStackUtil.comparableStackFrom(ModItems.plate_desh),
				new OreDictStack(GOLD.ingot())));
		recipes.add(new MagicRecipe(ItemStackUtil.itemStackFrom(ModItems.gun_darter),
				new OreDictStack(STEEL.plate()),
				new OreDictStack(STEEL.plate()),
				ItemStackUtil.comparableStackFrom(ModItems.ingot_polymer),
				new OreDictStack(GOLD.plate())));
	}

	public static List<MagicRecipe> getRecipes() {
		return recipes;
	}

	public static class MagicRecipe {

		public List<AStack> in;
		public ItemStack out;

		public MagicRecipe(ItemStack out, AStack... in) {
			this.out = out;
			this.in = Arrays.asList(in);
			//Collections.sort(this.in);
		}

		public boolean matches(List<ComparableStack> comps) {
			if(comps.size() != in.size())
				return false;

			for(int i = 0; i < in.size(); i++) {

				if(!in.get(i).isApplicable(comps.get(i)))
					return false;
			}

			return true;
		}

		public ItemStack getResult() {
			return out.copy();
		}
	}
}