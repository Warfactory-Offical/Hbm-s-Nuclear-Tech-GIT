package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.hbm.inventory.OreDictManager.*;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.items.ModItems;
import com.hbm.items.special.ItemCell;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CyclotronRecipes {

	private static final LinkedHashMap<Object, ItemStack> lithium = new LinkedHashMap<>();
	private static final LinkedHashMap<Object, ItemStack> beryllium = new LinkedHashMap<>();
	private static final LinkedHashMap<Object, ItemStack> carbon = new LinkedHashMap<>();
	private static final LinkedHashMap<Object, ItemStack> copper = new LinkedHashMap<>();
	private static final LinkedHashMap<Object, ItemStack> plutonium = new LinkedHashMap<>();
	private static final HashMap<Object, Integer> liAmat = new HashMap<>();
	private static final HashMap<Object, Integer> beAmat = new HashMap<>();
	private static final HashMap<Object, Integer> caAmat = new HashMap<>();
	private static final HashMap<Object, Integer> coAmat = new HashMap<>();
	private static final HashMap<Object, Integer> plAmat = new HashMap<>();

	public static void register() {

		/// LITHIUM START ///
		final int liA = 50;

		makeRecipe(lithium, liAmat, LI.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_beryllium), liA);
		makeRecipe(lithium, liAmat, BE.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_boron), liA);
		makeRecipe(lithium, liAmat, B.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_coal), liA);
		makeRecipe(lithium, liAmat, NETHERQUARTZ.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_fire), liA);
		makeRecipe(lithium, liAmat, P_RED.dust(), ItemStackUtil.itemStackFrom(ModItems.sulfur), liA);
		makeRecipe(lithium, liAmat, IRON.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_cobalt), liA);
		makeRecipe(lithium, liAmat, SR.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_zirconium), liA);
		makeRecipe(lithium, liAmat, GOLD.dust(), ItemStackUtil.itemStackFrom(ModItems.bottle_mercury), liA);
		makeRecipe(lithium, liAmat, PO210.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_astatine), liA);
		makeRecipe(lithium, liAmat, LA.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_cerium), liA);
		makeRecipe(lithium, liAmat, AC.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_thorium), liA);
		makeRecipe(lithium, liAmat, U.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_neptunium), liA);
		makeRecipe(lithium, liAmat, NP237.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_plutonium), liA);
		makeRecipe(lithium, liAmat, REIIUM.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_weidanium), 150);
		/// LITHIUM END ///

		/// BERYLLIUM START ///
		final int beA = 25;

		makeRecipe(beryllium, beAmat, LI.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_boron), beA);
		makeRecipe(beryllium, beAmat, NETHERQUARTZ.dust(), ItemStackUtil.itemStackFrom(ModItems.sulfur), beA);
		makeRecipe(beryllium, beAmat, TI.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_iron), beA);
		makeRecipe(beryllium, beAmat, CO.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_copper), beA);
		makeRecipe(beryllium, beAmat, SR.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_niobium), beA);
		makeRecipe(beryllium, beAmat, CE.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_neodymium), beA);
		makeRecipe(beryllium, beAmat, TH232.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_uranium), beA);
		makeRecipe(beryllium, beAmat, WEIDANIUM.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_australium), 100);
		/// BERYLLIUM END ///

		/// CARBON START ///
		final int caA = 10;

		makeRecipe(carbon, caAmat, B.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_aluminium), caA);
		makeRecipe(carbon, caAmat, S.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_titanium), caA);
		makeRecipe(carbon, caAmat, TI.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_cobalt), caA);
		makeRecipe(carbon, caAmat, CS.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_lanthanium), caA);
		makeRecipe(carbon, caAmat, ND.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_gold), caA);
		makeRecipe(carbon, caAmat, ItemStackUtil.comparableStackFrom(ModItems.bottle_mercury), ItemStackUtil.itemStackFrom(ModItems.powder_polonium), caA);
		makeRecipe(carbon, caAmat, PB.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_ra226), caA);
		makeRecipe(carbon, caAmat, AT.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_actinium), caA);
		/// CARBON END ///

		/// COPPER START ///
		final int coA = 15;

		makeRecipe(copper, coAmat, BE.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_quartz), coA);
		makeRecipe(copper, coAmat, COAL.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_bromine), coA);
		makeRecipe(copper, coAmat, TI.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_strontium), coA);
		makeRecipe(copper, coAmat, IRON.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_niobium), coA);
		makeRecipe(copper, coAmat, BR.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_iodine), coA);
		makeRecipe(copper, coAmat, SR.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_neodymium), coA);
		makeRecipe(copper, coAmat, NB.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_caesium), coA);
		makeRecipe(copper, coAmat, I.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_polonium), coA);
		makeRecipe(copper, coAmat, CS.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_actinium), coA);
		makeRecipe(copper, coAmat, GOLD.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_uranium), coA);
		/// COPPER END ///

		/// PLUTONIUM START ///
		final int plA = 100;

		makeRecipe(plutonium, plAmat, PU.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_tennessine), plA);
		makeRecipe(plutonium, plAmat, ItemStackUtil.comparableStackFrom(ModItems.powder_tennessine), ItemStackUtil.itemStackFrom(ModItems.powder_reiium), plA);
		makeRecipe(plutonium, plAmat, ItemStackUtil.comparableStackFrom(ModItems.pellet_charged), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 6), 200);
		makeRecipe(plutonium, plAmat, new NbtComparableStack(ItemCell.getFullCell(ModForgeFluids.amat)), ItemCell.getFullCell(ModForgeFluids.aschrab), 0);
		/// PLUTONIUM END ///

		///TODO: fictional elements
	}
	
	private static void makeRecipe(final HashMap<Object, ItemStack> map, final HashMap<Object, Integer> aMap, final Object in, final ItemStack out, final int amat) {
		map.put(in, out);
		aMap.put(in, amat);
	}

	public static Object[] getOutput(final ItemStack stack, final ItemStack box) {

		if(stack == null || stack.getItem() == null || box == null || stack.isEmpty() || box.isEmpty())
			return null;

		HashMap<Object, ItemStack> pool = null;
		HashMap<Object, Integer> aPool = null;

		if(box.getItem() == ModItems.part_lithium) {
			pool = lithium;
			aPool = liAmat;
		} else if(box.getItem() == ModItems.part_beryllium) {
			pool = beryllium;
			aPool = beAmat;
		} else if(box.getItem() == ModItems.part_carbon) {
			pool = carbon;
			aPool = caAmat;
		} else if(box.getItem() == ModItems.part_copper) {
			pool = copper;
			aPool = coAmat;
		} else if(box.getItem() == ModItems.part_plutonium) {
			pool = plutonium;
			aPool = plAmat;
		}

		if(pool == null)
			return null;

		final ComparableStack comp = stack.hasTagCompound() ? new NbtComparableStack(stack).makeSingular() : ItemStackUtil.comparableStackFrom(stack.getItem(), 1, stack.getItemDamage());
		
		if(pool.containsKey(comp))
			return new Object[] {pool.get(comp).copy(), aPool.get(comp)};

		final String[] dictKeys = comp.getDictKeys();

		for(final String key : dictKeys) {

			if(pool.containsKey(key))
				return new Object[] {pool.get(key).copy(), aPool.get(key)};
		}

		return null;
	}
	
	public static Map<ItemStack[], ItemStack> getRecipes() {

		final Map<ItemStack[], ItemStack> recipes = new LinkedHashMap<>();

		addRecipes(recipes, lithium, ModItems.part_lithium);
		addRecipes(recipes, beryllium, ModItems.part_beryllium);
		addRecipes(recipes, carbon, ModItems.part_carbon);
		addRecipes(recipes, copper, ModItems.part_copper);
		addRecipes(recipes, plutonium, ModItems.part_plutonium);

		return recipes;
	}

	private static void addRecipes(final Map<ItemStack[], ItemStack> recipes, final HashMap<Object, ItemStack> map, final Item part) {

		for(final Entry<Object, ItemStack> entry : map.entrySet()) {

			if(entry.getKey() instanceof ComparableStack) {

				recipes.put(new ItemStack[] { ItemStackUtil.itemStackFrom(part), ((ComparableStack) entry.getKey()).toStack() }, entry.getValue());

			} else if(entry.getKey() instanceof String) {

				final List<ItemStack> ores = OreDictionary.getOres((String) entry.getKey());

				for(final ItemStack ore : ores) {
					recipes.put(new ItemStack[] { ItemStackUtil.itemStackFrom(part), ore }, entry.getValue());
				}
			}
		}
	}
}
