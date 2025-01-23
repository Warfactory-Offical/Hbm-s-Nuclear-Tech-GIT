package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.TileEntityHadron.EnumHadronState;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HadronRecipes {

	/*
	 * Since we're dealing with like 10 or so recipes, using a HashMap (or to combine two keys, a HashMap *in* a HashMap)
	 * would be less performant than those few steps through a good old Array list, and it's much easier to implement too.
	 */
	private static final List<HadronRecipe> recipes = new ArrayList<>();

	/*
	 * We CAN actually implement recipes with the same input items but different momentum requirements.
	 * Just be sure to register the higher requirement BEFORE the lower one since those need to be checked first.
	 * 
	 * It's important to remember that, ok?
	 * 
	 * Update, T+6 minutes: I went for coffee and already forgot what I was doing, good thing I keep these notes, hehe.
	 * Having multiple recipes with different momentum requirements (at most I would expect 2) isn't exactly necessary
	 * since the thing differentiates between ring and line accelerator mode, and line accelerators are by design always shorter anyway.
	 */
	public static void register() {

		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_hydrogen),
				ItemStackUtil.itemStackFrom(ModItems.particle_copper),
				900,
				ItemStackUtil.itemStackFrom(ModItems.particle_aproton),
				ItemStackUtil.itemStackFrom(ModItems.particle_aelectron),
				true
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_amat),
				ItemStackUtil.itemStackFrom(ModItems.particle_amat),
				900,
				ItemStackUtil.itemStackFrom(ModItems.particle_aschrab),
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_hydrogen),
				ItemStackUtil.itemStackFrom(ModItems.particle_amat),
				2000,
				ItemStackUtil.itemStackFrom(ModItems.particle_muon),
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				true
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_aschrab),
				ItemStackUtil.itemStackFrom(ModItems.capsule_xen),
				4000,
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				ItemStackUtil.itemStackFrom(ModItems.tiny_singularity),
				true
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_hydrogen),
				ItemStackUtil.itemStackFrom(ModItems.particle_lead),
				5000,
				ItemStackUtil.itemStackFrom(ModItems.particle_higgs),
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_muon),
				ItemStackUtil.itemStackFrom(ModItems.singularity),
				8000,
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				ItemStackUtil.itemStackFrom(ModItems.tiny_singularity_counter_resonant),
				true
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_higgs),
				ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AUSTRALIUM)),
				10000,
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.VERTICIUM)),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_muon),
				ItemStackUtil.itemStackFrom(ModItems.particle_higgs),
				10000,
				ItemStackUtil.itemStackFrom(ModItems.particle_tachyon),
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				true
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_tachyon),
				ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.VERTICIUM)),
				40000,
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.UNOBTAINIUM)),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_tachyon),
				ItemStackUtil.itemStackFrom(ModItems.singularity_counter_resonant),
				20000,
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				ItemStackUtil.itemStackFrom(ModItems.tiny_singularity_super_heated),
				true
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_aschrab),
				ItemStackUtil.itemStackFrom(ModItems.particle_aschrab),
				100000,
				ItemStackUtil.itemStackFrom(ModItems.particle_dark),
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_dark),
				ItemStackUtil.itemStackFrom(ModItems.singularity_super_heated),
				100000,
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				ItemStackUtil.itemStackFrom(ModItems.tiny_black_hole),
				true
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_muon),
				ItemStackUtil.itemStackFrom(ModItems.particle_dark),
				200000,
				ItemStackUtil.itemStackFrom(ModItems.particle_strange),
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_strange),
				ItemStackUtil.itemStackFrom(ModItems.powder_magic),
				500000,
				ItemStackUtil.itemStackFrom(ModItems.particle_sparkticle),
				ItemStackUtil.itemStackFrom(ModItems.dust),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_sparkticle),
				ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.UNOBTAINIUM)),
				600000,
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DAFFERGON)),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_sparkticle),
				ItemStackUtil.itemStackFrom(ModItems.black_hole),
				750000,
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				ItemStackUtil.itemStackFrom(ModItems.tiny_singularity_spark),
				true
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(ModItems.particle_sparkticle),
				ItemStackUtil.itemStackFrom(ModItems.particle_higgs),
				1000000,
				ItemStackUtil.itemStackFrom(ModItems.particle_digamma),
				ItemStackUtil.itemStackFrom(ModItems.particle_empty),
				false
				));
		recipes.add(new HadronRecipe(
				ItemStackUtil.itemStackFrom(Items.CHICKEN),
				ItemStackUtil.itemStackFrom(Items.CHICKEN),
				100,
				ItemStackUtil.itemStackFrom(ModItems.chicken_nugget),
				ItemStackUtil.itemStackFrom(ModItems.chicken_nugget),
				false
				));
	}

	public static EnumHadronState returnCode = EnumHadronState.NORESULT;

	/**
	 * Resolves recipes, simple enough.
	 * @param in1
	 * @param in2
	 * @param momentum
	 * @param analysisOnly true == line accelerator mode
	 * @return either null (no recipe) or an ItemStack array with 2 non-null instances
	 */
	public static ItemStack[] getOutput(final ItemStack in1, final ItemStack in2, final int momentum, final boolean analysisOnly) {

		returnCode = EnumHadronState.NORESULT_WRONG_INGREDIENT;

		for(final HadronRecipe r : recipes) {

			if((r.in1.isApplicable(in1) && r.in2.isApplicable(in2)) ||
					(r.in1.isApplicable(in2) && r.in2.isApplicable(in1))) {

				if(analysisOnly && !r.analysisOnly)	returnCode = EnumHadronState.NORESULT_WRONG_MODE;
				if(momentum < r.momentum)			returnCode = EnumHadronState.NORESULT_TOO_SLOW;

				if(momentum >= r.momentum && analysisOnly == r.analysisOnly)
					return new ItemStack[] {r.out1, r.out2};
			}
		}

		return null;
	}

	public static List<HadronRecipe> getRecipes() {
		return recipes;
	}
	
	public static class HadronRecipe {

		public ComparableStack in1;
		public ComparableStack in2;
		public int momentum;
		public ItemStack out1;
		public ItemStack out2;
		public boolean analysisOnly;

		public HadronRecipe(final ItemStack in1, final ItemStack in2, final int momentum, final ItemStack out1, final ItemStack out2, final boolean analysisOnly) {
			this.in1 = ItemStackUtil.comparableStackFrom(in1);
			this.in2 = ItemStackUtil.comparableStackFrom(in2);
			this.momentum = momentum;
			this.out1 = out1;
			this.out2 = out2;
			this.analysisOnly = analysisOnly;
		}
	}
}
