package com.hbm.inventory;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.hbm.blocks.ModBlocks;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.items.ModItems;
import com.hbm.items.special.ItemHazard;
import com.hbm.items.machine.ItemFluidIcon;

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

public class RBMKOutgasserRecipes {

	public static LinkedHashMap<ComparableStack, Object[]> rbmkOutgasserRecipes = new LinkedHashMap<ComparableStack, Object[]>();
	public static List<RBMKOutgasserRecipe> jeiRBMKOutgasserRecipes = null;
	
	public static void registerOverrides() {
		addRecipe(240,  LI.dustTiny(), ItemFluidIcon.getStackWithQuantity(ModForgeFluids.tritium, 120));
		addRecipe(1200, LI.dust(), ItemFluidIcon.getStackWithQuantity(ModForgeFluids.tritium, 800));
		addRecipe(1500, LI.ingot(), ItemFluidIcon.getStackWithQuantity(ModForgeFluids.tritium, 800));
		addRecipe(10000, LI.block(), ItemFluidIcon.getStackWithQuantity(ModForgeFluids.tritium, 8000));
		addRecipe(6000, Blocks.BROWN_MUSHROOM, ItemStackUtil.itemStackFrom(ModBlocks.mush));
		addRecipe(6000, Blocks.RED_MUSHROOM, ItemStackUtil.itemStackFrom(ModBlocks.mush));
		addRecipe(18000, Items.MUSHROOM_STEW, ItemStackUtil.itemStackFrom(ModItems.glowing_stew));
		
		addRecipe(360000 * ItemHazard.nugget, GOLD.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AU198)));
		addRecipe(360000 * ItemHazard.powder_tiny, GOLD.dustTiny(), ItemStackUtil.itemStackFrom(ModItems.powder_au198_tiny));
		addRecipe(360000, GOLD.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AU198)));
		addRecipe(360000 * ItemHazard.powder, GOLD.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_au198));
		addRecipe(360000 * ItemHazard.block, GOLD.block(), ItemStackUtil.itemStackFrom(ModBlocks.block_au198));
		addRecipe(360000 * ItemHazard.powder * ItemHazard.block, ModBlocks.sand_gold, ItemStackUtil.itemStackFrom(ModBlocks.sand_gold198));
		
		addRecipe(90000 * ItemHazard.nugget, TH232.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL)));
		addRecipe(90000 * ItemHazard.billet, TH232.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL)));
		addRecipe(90000, TH232.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL)));
		addRecipe(90000 * ItemHazard.block, TH232.block(), ItemStackUtil.itemStackFrom(ModBlocks.block_thorium_fuel));
		
		addRecipe(60000 * ItemHazard.nugget, U233.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U235)));
		addRecipe(60000 * ItemHazard.billet, U233.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.U235)));
		addRecipe(60000, U233.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U235)));
		addRecipe(60000 * ItemHazard.block, U233.block(), ItemStackUtil.itemStackFrom(ModBlocks.block_u235));
		
		addRecipe(100000 * ItemHazard.nugget, U235.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.NEPTUNIUM_FUEL)));
		addRecipe(100000 * ItemHazard.billet, U235.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL)));
		addRecipe(100000, U235.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM_FUEL)));
		
		addRecipe(170000 * ItemHazard.nugget, NP237.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU238)));
		addRecipe(170000 * ItemHazard.billet, NP237.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PU238)));
		addRecipe(170000, NP237.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU238)));
		addRecipe(170000 * ItemHazard.block, NP237.block(), ItemStackUtil.itemStackFrom(ModBlocks.block_pu238));
		
		addRecipe(190000 * ItemHazard.nugget, U238.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU239)));
		addRecipe(190000 * ItemHazard.billet, U238.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PU239)));
		addRecipe(190000, U238.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU239)));
		addRecipe(190000 * ItemHazard.block, U238.block(), ItemStackUtil.itemStackFrom(ModBlocks.block_pu239));
		
		addRecipe(150000 * ItemHazard.nugget, PU238.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU239)));
		addRecipe(150000 * ItemHazard.billet, PU238.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PU239)));
		addRecipe(150000, PU238.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU239)));
		addRecipe(150000 * ItemHazard.block, PU238.block(), ItemStackUtil.itemStackFrom(ModBlocks.block_pu239));
		
		addRecipe(210000 * ItemHazard.nugget, PU239.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU240)));
		addRecipe(210000 * ItemHazard.billet, PU239.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PU240)));
		addRecipe(210000, PU239.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU240)));
		addRecipe(210000 * ItemHazard.block, PU239.block(), ItemStackUtil.itemStackFrom(ModBlocks.block_pu240));
		
		addRecipe(2000000 * ItemHazard.nugget, PU240.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU241)));
		addRecipe(2000000 * ItemHazard.billet, PU240.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PU241)));
		addRecipe(2000000, PU240.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU241)));
		
		addRecipe(6000000 * ItemHazard.nugget, PU241.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AM241)));
		addRecipe(6000000 * ItemHazard.billet, PU241.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.AM241)));
		addRecipe(6000000, PU241.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AM241)));
		
		addRecipe(750000 * ItemHazard.nugget, AM241.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AM242)));
		addRecipe(750000 * ItemHazard.billet, AM241.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.AM242)));
		addRecipe(750000, AM241.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AM242)));
		
		addRecipe(690000 * ItemHazard.nugget, SA326.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SOLINIUM)));
		addRecipe(690000 * ItemHazard.billet, SA326.billet(), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.SOLINIUM)));
		addRecipe(690000, SA326.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SOLINIUM)));
		addRecipe(690000 * ItemHazard.block, SA326.block(), ItemStackUtil.itemStackFrom(ModBlocks.block_solinium));
		
		addRecipe(50000 * ItemHazard.nugget,CO.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.CO60)));
		addRecipe(50000 * ItemHazard.powder_tiny, CO.dustTiny(), ItemStackUtil.itemStackFrom(ModItems.powder_co60_tiny));
		addRecipe(50000, CO.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CO60)));
		addRecipe(50000 * ItemHazard.powder, CO.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_co60));
		
		addRecipe(55000 * ItemHazard.nugget, SR.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SR90)));
		addRecipe(55000, SR.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SR90)));
		addRecipe(55000 * ItemHazard.powder, SR.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_sr90));

		addRecipe(45000 * ItemHazard.powder_tiny, I.dustTiny(), ItemStackUtil.itemStackFrom(ModItems.powder_i131_tiny));
		addRecipe(45000, I.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.I131)));
		addRecipe(45000 * ItemHazard.powder, I.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_i131));
		
		addRecipe(450000 * ItemHazard.nugget, AC.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AC227)));
		addRecipe(450000, AC.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AC227)));
		addRecipe(450000 * ItemHazard.powder, AC.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_ac227));
		
		addRecipe(80000, CS.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_cs137));
		addRecipe(120000, AT.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_at209));
		
		addRecipe(120000 * ItemHazard.nugget, ModItems.nugget.getItemStack(MaterialMineral.AUSTRALIUM), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AUSTRALIUM_LESSER)));
		addRecipe(120000 * ItemHazard.billet, ModItems.billet.getItemStack(MaterialMineral.AUSTRALIUM), ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.AUSTRALIUM_LESSER)));
		
		addRecipe(14000000 * ItemHazard.nugget, PB.nugget(), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PB209)));
		addRecipe(14000000 * ItemHazard.powder_tiny, PB.dustTiny(), ItemStackUtil.itemStackFrom(ModItems.powder_pb209_tiny));
		addRecipe(14000000, PB.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PB209)));
		addRecipe(14000000 * ItemHazard.powder, PB.dust(), ItemStackUtil.itemStackFrom(ModItems.powder_pb209));
		
		addRecipe(1800000, NB.ingot(), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TECHNETIUM)));
		addRecipe(32000, ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM_LESSER), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM)));
		addRecipe(300000, ModItems.scrap, ItemStackUtil.itemStackFrom(ModItems.fallout));
		addRecipe(3000000, ModBlocks.block_scrap, ItemStackUtil.itemStackFrom(ModBlocks.block_fallout));
		addRecipe(2000, Blocks.STONE, ItemStackUtil.itemStackFrom(ModBlocks.sellafield_slaked));
		addRecipe(4000, ModBlocks.sellafield_slaked, ItemStackUtil.itemStackFrom(ModBlocks.sellafield_0));
		addRecipe(8000, ModBlocks.sellafield_0, ItemStackUtil.itemStackFrom(ModBlocks.sellafield_1));
		addRecipe(16000, ModBlocks.sellafield_1, ItemStackUtil.itemStackFrom(ModBlocks.sellafield_2));
		addRecipe(32000, ModBlocks.sellafield_2, ItemStackUtil.itemStackFrom(ModBlocks.sellafield_3));
		addRecipe(64000, ModBlocks.sellafield_3, ItemStackUtil.itemStackFrom(ModBlocks.sellafield_4));
		addRecipe(128000, ModBlocks.sellafield_4, ItemStackUtil.itemStackFrom(ModBlocks.sellafield_core));
		addRecipe(500000, ModBlocks.block_corium_cobble, ItemStackUtil.itemStackFrom(ModBlocks.block_corium));
		addRecipe(1000000, ModItems.meteorite_sword_bred, ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_irradiated));
	}

	public static void addRecipe(final int requiredFlux, final ItemStack in, final ItemStack out) {
		rbmkOutgasserRecipes.put(ItemStackUtil.comparableStackFrom(in), new Object[] {requiredFlux, out});
	}

	public static void addRecipe(final int requiredFlux, final Item in, final ItemStack out) {
		rbmkOutgasserRecipes.put(ItemStackUtil.comparableStackFrom(in), new Object[] {requiredFlux, out});
	}
	
	public static void addRecipe(final int requiredFlux, final Block in, final ItemStack out) {
		rbmkOutgasserRecipes.put(ItemStackUtil.comparableStackFrom(in), new Object[] {requiredFlux, out});
	}

	public static void addRecipe(final int requiredFlux, final String in, final ItemStack out) {
		if(!OreDictionary.getOres(in).isEmpty() && OreDictionary.getOres(in).get(0) != null && !OreDictionary.getOres(in).get(0).isEmpty())
			rbmkOutgasserRecipes.put(ItemStackUtil.comparableStackFrom(OreDictionary.getOres(in).get(0)), new Object[] {requiredFlux, out});
	}

	public static void addRecipe(final float requiredFlux, final String in, final ItemStack out) {
		addRecipe((int)requiredFlux, in, out);
	}

	public static void addRecipe(final float requiredFlux, final Item in, final ItemStack out) {
		addRecipe((int)requiredFlux, in, out);
	}

	public static void addRecipe(final float requiredFlux, final ItemStack in, final ItemStack out) {
		addRecipe((int)requiredFlux, in, out);
	}

	public static void addRecipe(final float requiredFlux, final Block in, final ItemStack out) {
		addRecipe((int)requiredFlux, in, out);
	}
	
	public static void removeRecipe(final ItemStack in) {
		rbmkOutgasserRecipes.remove(ItemStackUtil.comparableStackFrom(in));
	}

	public static int getRequiredFlux(final ItemStack stack) {
		
		if(stack == null || stack.isEmpty())
			return -1;
		
		final ComparableStack comp = ItemStackUtil.comparableStackFrom(stack).makeSingular();
		if(rbmkOutgasserRecipes.containsKey(comp)){
			return (int)rbmkOutgasserRecipes.get(comp)[0];
		}

		final String[] dictKeys = comp.getDictKeys();
		
		for(final String key : dictKeys) {
			if(rbmkOutgasserRecipes.containsKey(key)){
				return (int)rbmkOutgasserRecipes.get(key)[1];
			}
		}
		return -1;
	}

	public static ItemStack getOutput(final ItemStack stack) {
		
		if(stack == null || stack.getItem() == null)
			return null;

		final ComparableStack comp = ItemStackUtil.comparableStackFrom(stack).makeSingular();
		if(rbmkOutgasserRecipes.containsKey(comp)){
			return (ItemStack)rbmkOutgasserRecipes.get(comp)[1];
		}
		
		final String[] dictKeys = comp.getDictKeys();
		
		for(final String key : dictKeys) {
			
			if(rbmkOutgasserRecipes.containsKey(key)){
				return (ItemStack)rbmkOutgasserRecipes.get(key)[1];
			}
		}
		return null;
	}

	public static List<RBMKOutgasserRecipe> getRBMKOutgasserRecipes() {
		if(jeiRBMKOutgasserRecipes == null){
			jeiRBMKOutgasserRecipes = new ArrayList<RBMKOutgasserRecipe>();
			for(final Entry<ComparableStack, Object[]> e : rbmkOutgasserRecipes.entrySet()){
				jeiRBMKOutgasserRecipes.add(new RBMKOutgasserRecipe(e.getKey().toStack(), (int)e.getValue()[0], (ItemStack)e.getValue()[1]));
			}
		}
		return jeiRBMKOutgasserRecipes;
	}
	
	public static class RBMKOutgasserRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final int requiredFlux;
		private final ItemStack output;
		
		public RBMKOutgasserRecipe(final ItemStack input, final int requiredFlux, final ItemStack output) {
			this.input = input;
			this.requiredFlux = requiredFlux;
			this.output = output;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}

		@Override
		public void drawInfo(final Minecraft minecraft, final int recipeWidth, final int recipeHeight, final int mouseX, final int mouseY) {
			final FontRenderer fontRenderer = minecraft.fontRenderer;
	    	
	    	fontRenderer.drawString("Flux", 21-12, 33-17, 4210752);
	    	fontRenderer.drawString(""+requiredFlux, 123-12-fontRenderer.getStringWidth(""+requiredFlux), 34-17, 0x46EA00);
	    	GlStateManager.color(1, 1, 1, 1);
		}
	}
}