package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.hbm.lib.Library;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;

import static com.hbm.inventory.OreDictManager.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class DFCRecipes {

	public static LinkedHashMap<ComparableStack, Object[]> dfcRecipes = new LinkedHashMap<ComparableStack, Object[]>();
	public static List<DFCRecipe> jeiDFCRecipes = null;
	
	public static void register() {
		
		DFCRecipes.setRecipe(100000L, ModItems.marshmallow, ItemStackUtil.itemStackFrom(ModItems.marshmallow_roasted));
		
		DFCRecipes.setRecipe(2000000L, REDSTONE.dust(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY)));
		DFCRecipes.setRecipe(2000000L, REDSTONE.block(), ItemStackUtil.itemStackFrom(ModItems.bottle_mercury));
		
		DFCRecipes.setRecipe(10000000L, W.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_magnetized_tungsten));
		DFCRecipes.setRecipe(10000000L, W.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN)));
		
		DFCRecipes.setRecipe(60000000L, MAGTUNG.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_chlorophyte));
		DFCRecipes.setRecipe(60000000L, MAGTUNG.ingot(), ItemStackUtil.itemStackFrom(ModItems.powder_chlorophyte));
		
		DFCRecipes.setRecipe(200000000L, ModItems.powder_chlorophyte, ItemStackUtil.itemStackFrom(ModItems.powder_balefire));
		
		DFCRecipes.setRecipe(600000000L, ModItems.powder_balefire, ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard));

		DFCRecipes.setRecipe(800000000L, ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.ZFB_BISMUTH)));

		DFCRecipes.setRecipe(1200000000L, Items.STICK, ItemStackUtil.itemStackFrom(Blocks.LOG));
		DFCRecipes.setRecipe(1200000000L, Blocks.STONE, ItemStackUtil.itemStackFrom(Blocks.IRON_ORE));
		DFCRecipes.setRecipe(1200000000L, Blocks.GRAVEL, ItemStackUtil.itemStackFrom(Blocks.COAL_ORE));
		DFCRecipes.setRecipe(1200000000L, Blocks.NETHERRACK, ItemStackUtil.itemStackFrom(Blocks.QUARTZ_ORE));
		
		DFCRecipes.setRecipe(1500000000L, ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM_LESSER), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM_GREATER)));
		
		DFCRecipes.setRecipe(2000000000L, U.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM)));
		DFCRecipes.setRecipe(2000000000L, U.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM)));
		DFCRecipes.setRecipe(2000000000L, U.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium));
		
		DFCRecipes.setRecipe(2500000000L, ModItems.powder_nitan_mix, ItemStackUtil.itemStackFrom(ModItems.powder_spark_mix));
		DFCRecipes.setRecipe(5000000000L, ModItems.particle_hydrogen, ItemStackUtil.itemStackFrom(ModItems.particle_amat));
		
		DFCRecipes.setRecipe(20000000000L, PU.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.EUPHEMIUM)));
		DFCRecipes.setRecipe(20000000000L, PU.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.EUPHEMIUM)));
		DFCRecipes.setRecipe(20000000000L, PU.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_euphemium));

		DFCRecipes.setRecipe(30000000000L, ModItems.particle_amat, ItemStackUtil.itemStackFrom(ModItems.particle_aschrab));

		DFCRecipes.setRecipe(40000000000L, VERTICIUM.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.RADSPICE)));
		DFCRecipes.setRecipe(40000000000L, VERTICIUM.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RADSPICE)));
		DFCRecipes.setRecipe(40000000000L, VERTICIUM.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_radspice));
		
		DFCRecipes.setRecipe(50000000000L, ModItems.billet.getItemStack(MaterialMineral.POLONIUM), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.YHARONITE)));
			
		DFCRecipes.setRecipe(100000000000L, ModItems.meteorite_sword_warped, ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_demonic));
		
		DFCRecipes.setRecipe(200000000000L, SBD.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_dineutronium));
		DFCRecipes.setRecipe(200000000000L, SBD.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DINEUTRONIUM)));
		
		DFCRecipes.setRecipe(400000000000L, U238.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot_u238m2));
		DFCRecipes.setRecipe(420000000000L, U238.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget_u238m2));
		DFCRecipes.setRecipe(690000000000L, ModItems.gun_uboinik, ItemStackUtil.itemStackFrom(ModItems.gun_supershotgun));
		
		DFCRecipes.setRecipe(8000000000000L, ModItems.undefined, ItemStackUtil.itemStackFrom(ModItems.glitch));
		DFCRecipes.setRecipe(69000000000000L, Items.WRITABLE_BOOK, ItemStackUtil.itemStackFrom(ModItems.book_of_));
	}

	public static void setRecipe(long requiredFlux, ItemStack in, ItemStack out) {
		dfcRecipes.put(ItemStackUtil.comparableStackFrom(in), new Object[] {requiredFlux, out});
	}

	public static void setRecipe(long requiredFlux, Item in, ItemStack out) {
		dfcRecipes.put(ItemStackUtil.comparableStackFrom(in), new Object[] {requiredFlux, out});
	}
	
	public static void setRecipe(long requiredFlux, Block in, ItemStack out) {
		dfcRecipes.put(ItemStackUtil.comparableStackFrom(in), new Object[] {requiredFlux, out});
	}

	public static void setRecipe(long requiredFlux, String in, ItemStack out) {
		dfcRecipes.put(ItemStackUtil.comparableStackFrom(OreDictionary.getOres(in).get(0)), new Object[] {requiredFlux, out});
	}

	public static void removeRecipe(ItemStack in) {
		dfcRecipes.remove(ItemStackUtil.comparableStackFrom(in));
	}

	public static long getRequiredFlux(ItemStack stack) {
		
		if(stack == null || stack.isEmpty())
			return -1;
		
		ComparableStack comp = ItemStackUtil.comparableStackFrom(stack).makeSingular();
		if(dfcRecipes.containsKey(comp)){
			return (long)dfcRecipes.get(comp)[0];
		}

		String[] dictKeys = comp.getDictKeys();
		
		for(String key : dictKeys) {
			if(dfcRecipes.containsKey(key)){
				return (long)dfcRecipes.get(key)[1];
			}
		}
		return -1;
	}

	public static ItemStack getOutput(ItemStack stack) {
		
		if(stack == null || stack.getItem() == null)
			return null;

		ComparableStack comp = ItemStackUtil.comparableStackFrom(stack).makeSingular();
		if(dfcRecipes.containsKey(comp)){
			return (ItemStack)dfcRecipes.get(comp)[1];
		}
		
		String[] dictKeys = comp.getDictKeys();
		
		for(String key : dictKeys) {
			
			if(dfcRecipes.containsKey(key)){
				return (ItemStack)dfcRecipes.get(key)[1];
			}
		}
		return null;
	}

	public static List<DFCRecipe> getDFCRecipes() {
		if(jeiDFCRecipes == null){
			jeiDFCRecipes = new ArrayList<DFCRecipe>();
			for(Entry<ComparableStack, Object[]> e : dfcRecipes.entrySet()){
				jeiDFCRecipes.add(new DFCRecipe(e.getKey().toStack(), (long)e.getValue()[0], (ItemStack)e.getValue()[1]));
			}
		}
		return jeiDFCRecipes;
	}
	
	public static class DFCRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final long requiredFlux;
		private final ItemStack output;
		
		public DFCRecipe(ItemStack input, long requiredFlux, ItemStack output) {
			this.input = input;
			this.requiredFlux = requiredFlux;
			this.output = output;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}

		@Override
		public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
			FontRenderer fontRenderer = minecraft.fontRenderer;
	    	
	    	fontRenderer.drawString("Spark", 8, 8, 4210752);
	    	String number = Library.getShortNumber(requiredFlux);
	    	fontRenderer.drawString(number, 80-fontRenderer.getStringWidth(number), 8, 0xa82a0e);
	    	GlStateManager.color(1, 1, 1, 1);
		}
	}
}