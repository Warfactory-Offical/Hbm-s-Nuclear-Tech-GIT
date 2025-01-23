package com.hbm.handler.jei;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumCell;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumCanister;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumGasCanister;
import com.hbm.inventory.AnvilRecipes;
import com.hbm.inventory.AnvilRecipes.AnvilConstructionRecipe;
import com.hbm.inventory.AnvilRecipes.AnvilOutput;
import com.hbm.inventory.AnvilRecipes.OverlayType;
import com.hbm.inventory.AnvilSmithingRecipe;
import com.hbm.inventory.AssemblerRecipes;
import com.hbm.inventory.ChemplantRecipes;
import com.hbm.inventory.MixerRecipes;
import com.hbm.inventory.BreederRecipes;
import com.hbm.inventory.BreederRecipes.BreederRecipe;
import com.hbm.inventory.RBMKFuelRecipes;
import com.hbm.inventory.WasteDrumRecipes;
import com.hbm.inventory.StorageDrumRecipes;
import com.hbm.inventory.CyclotronRecipes;
import com.hbm.inventory.FusionRecipes;
import com.hbm.inventory.DiFurnaceRecipes;
import com.hbm.inventory.HeatRecipes;
import com.hbm.inventory.PressRecipes;
import com.hbm.inventory.MachineRecipes;
import com.hbm.inventory.MachineRecipes.GasCentOutput;
import com.hbm.inventory.MagicRecipes;
import com.hbm.inventory.RefineryRecipes;
import com.hbm.inventory.CrackRecipes;
import com.hbm.inventory.NuclearTransmutationRecipes;
import com.hbm.inventory.MagicRecipes.MagicRecipe;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemAssemblyTemplate;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.items.machine.ItemFluidTank;
import com.hbm.items.machine.ItemFELCrystal.EnumWavelengths;
import com.hbm.items.special.ItemCell;
import com.hbm.items.tool.ItemFluidCanister;
import com.hbm.items.tool.ItemGasCanister;
import com.hbm.lib.Library;
import com.hbm.util.WeightedRandomObject;
import com.hbm.util.Tuple.Quartet;
import com.hbm.util.Tuple.Pair;
import com.hbm.util.I18nUtil;

import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraft.util.text.TextFormatting;

public class JeiRecipes {

	private static List<ChemRecipe> chemRecipes = null;
	private static List<MixerRecipe> mixerRecipes = null;
	private static List<CyclotronRecipe> cyclotronRecipes = null;
	private static List<PressRecipe> pressRecipes = null;
	private static List<AlloyFurnaceRecipe> alloyFurnaceRecipes = null;
	private static List<BoilerRecipe> boilerRecipes = null;
	private static List<CMBFurnaceRecipe> cmbRecipes = null;
	private static List<GasCentRecipe> gasCentRecipes = null;
	private static List<ReactorRecipe> reactorRecipes = null;
	private static List<WasteDrumRecipe> wasteDrumRecipes = null;
	private static List<StorageDrumRecipe> storageDrumRecipes = null;
	private static List<RBMKFuelRecipe> rbmkFuelRecipes = null;
	private static List<RefineryRecipe> refineryRecipes = null;
	private static List<CrackingRecipe> crackingRecipes = null;
	private static List<FractioningRecipe> fractioningRecipes = null;
	private static List<FluidRecipe> fluidEquivalences = null;
	private static List<BookRecipe> bookRecipes = null;
	private static List<FusionRecipe> fusionByproducts = null;
	private static List<SAFERecipe> safeRecipes = null;
	private static List<HadronRecipe> hadronRecipes = null;
	private static List<SILEXRecipe> silexRecipes = null;
	private static final Map<EnumWavelengths, List<SILEXRecipe>> waveSilexRecipes = new HashMap<EnumWavelengths, List<SILEXRecipe>>();
	private static List<SmithingRecipe> smithingRecipes = null;
	private static List<AnvilRecipe> anvilRecipes = null;
	private static List<TransmutationRecipe> transmutationRecipes = null;
	
	private static List<ItemStack> batteries = null;
	private static final Map<Integer, List<ItemStack>> reactorFuelMap = new HashMap<Integer, List<ItemStack>>();
	private static List<ItemStack> blades = null;
	private static List<ItemStack> alloyFuels = null;
	
	
	public static class ChemRecipe implements IRecipeWrapper {
		
		private final List<List<ItemStack>> inputs;
		private final List<ItemStack> outputs;
		
		public ChemRecipe(final List<AStack> inputs, final List<ItemStack> outputs) {
			final List<List<ItemStack>> list = new ArrayList<>(inputs.size());
			for(final AStack s : inputs)
				list.add(s.getStackList());
			this.inputs = list;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			final List<List<ItemStack>> in = Library.copyItemStackListList(inputs); // list of inputs and their list of possible items
			ingredients.setInputLists(VanillaTypes.ITEM, in);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
	}

	public static class MixerRecipe implements IRecipeWrapper {
		
		private final List<List<ItemStack>> inputs;
		private final ItemStack output;
		
		public MixerRecipe(final List<AStack> inputs, final ItemStack output) {
			final List<List<ItemStack>> list = new ArrayList<>(inputs.size());
			for(final AStack s : inputs)
				list.add(s.getStackList());
			this.inputs = list;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			final List<List<ItemStack>> in = Library.copyItemStackListList(inputs); // list of inputs and their list of possible items
			ingredients.setInputLists(VanillaTypes.ITEM, in);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}

		public int getInputSize(){
			return inputs.size();
		}
	}
	
	public static class CyclotronRecipe implements IRecipeWrapper {
		
		private final List<ItemStack> inputs;
		private final ItemStack output;
		
		public CyclotronRecipe(final List<ItemStack> inputs, final ItemStack output) {
			this.inputs = inputs;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class PressRecipe implements IRecipeWrapper {

		private final List<ItemStack> stamps;
		private final List<ItemStack> input;
		private final ItemStack output;
		
		public PressRecipe(final List<ItemStack> stamps, final List<ItemStack> input, final ItemStack output) {
			this.stamps = stamps;
			this.input = input;
			this.output = output; 
		}
		
		public List<ItemStack> getStamps() {
			return stamps;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class AlloyFurnaceRecipe implements IRecipeWrapper {
		
		private final List<List<ItemStack>> inputs;
		private final ItemStack output;
		
		public AlloyFurnaceRecipe(final AStack input1, final AStack input2, final ItemStack output) {
			final List<List<ItemStack>> list = new ArrayList<>(2);
			list.add(input1.getStackList());
			list.add(input2.getStackList());
			this.inputs = list;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			final List<List<ItemStack>> in = Library.copyItemStackListList(inputs);
			ingredients.setInputLists(VanillaTypes.ITEM, in);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class BoilerRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final ItemStack output;
		
		public BoilerRecipe(final ItemStack input, final ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class CMBFurnaceRecipe implements IRecipeWrapper {
		
		private final List<ItemStack> inputs;
		private final ItemStack output;
		
		public CMBFurnaceRecipe(final List<ItemStack> inputs, final ItemStack output) {
			this.inputs = inputs;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class GasCentRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final List<ItemStack> outputs;
		
		public GasCentRecipe(final ItemStack input, final List<ItemStack> outputs) {
			this.input = input;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}
	
	public static class ReactorRecipe implements IRecipeWrapper {
		
		public static IDrawableStatic heatTex;
		
		private final ItemStack input;
		private final ItemStack output;
		public final int heat;
		
		public ReactorRecipe(final ItemStack input, final ItemStack output, final int heat) {
			this.input = input;
			this.output = output; 
			this.heat = heat;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
		@Override
		public void drawInfo(final Minecraft minecraft, final int recipeWidth, final int recipeHeight, final int mouseX, final int mouseY) {
			heatTex.draw(minecraft, 1, 20, 16-heat*4, 0, 0, 0);
		}
		
	}

	public static class WasteDrumRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final ItemStack output;
		
		public WasteDrumRecipe(final ItemStack input, final ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}

	public static class StorageDrumRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final ItemStack output;
		
		public StorageDrumRecipe(final ItemStack input, final ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}

	public static class TransmutationRecipe implements IRecipeWrapper {
		
		private final List<List<ItemStack>> inputs;
		private final ItemStack output;
		
		public TransmutationRecipe(final List<ItemStack> inputs, final ItemStack output) {
			this.inputs = new ArrayList();
			this.inputs.add(inputs);
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInputLists(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}

	public static class RBMKFuelRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final ItemStack output;
		
		public RBMKFuelRecipe(final ItemStack input, final ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}
	
	public static class RefineryRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final List<ItemStack> outputs;
		
		public RefineryRecipe(final ItemStack input, final List<ItemStack> outputs) {
			this.input = input;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}

	public static class CrackingRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		public final List<ItemStack> outputs;
		
		public CrackingRecipe(final ItemStack input, final List<ItemStack> outputs) {
			this.input = input;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}

	public static class FractioningRecipe implements IRecipeWrapper {
		
		private final ItemStack input;
		private final List<ItemStack> outputs;
		
		public FractioningRecipe(final ItemStack input, final List<ItemStack> outputs) {
			this.input = input;
			this.outputs = outputs; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}
	
	public static class FluidRecipe implements IRecipeWrapper {
		
		protected final ItemStack input;
		protected final ItemStack output;
		
		public FluidRecipe(final ItemStack input, final ItemStack output) {
			this.input = input;
			this.output = output; 
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class FluidRecipeInverse extends FluidRecipe implements IRecipeWrapper {
		
		public FluidRecipeInverse(final ItemStack input, final ItemStack output) {
			super(input, output);
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, output);
			ingredients.setOutput(VanillaTypes.ITEM, input);
		}
		
	}
	
	public static class AssemblerRecipeWrapper implements IRecipeWrapper {

		ItemStack output;
		List<List<ItemStack>> inputs;
		int time;
		
		public AssemblerRecipeWrapper(final ItemStack output, final AStack[] inputs, final int time) {
			this.output = output;
			final List<List<ItemStack>> list = new ArrayList<>(inputs.length);
			for(final AStack s : inputs)
				list.add(s.getStackList());
			this.inputs = list;
			this.time = time;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			final List<List<ItemStack>> in = Library.copyItemStackListList(inputs);
			while(in.size() < 12)
				in.add(Arrays.asList(ItemStackUtil.itemStackFrom(ModItems.nothing)));
			int index = -1;
			for(int i = 0; i < AssemblerRecipes.recipeList.size(); i++){ // finding the template item
				if(AssemblerRecipes.recipeList.get(i).isApplicable(output)){
					index = i;
					break;
				}
			}
			if(index >= 0) // adding the template item
				in.add(Arrays.asList(ItemAssemblyTemplate.getTemplate(index)));
			else {
				in.add(Arrays.asList(ItemStackUtil.itemStackFrom(ModItems.nothing)));
			}
			ingredients.setInputLists(VanillaTypes.ITEM, in);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class BookRecipe implements IRecipeWrapper {

		List<ItemStack> inputs;
		ItemStack output;
		
		public BookRecipe(final MagicRecipe recipe) {
			inputs = new ArrayList<>(4);
			for(int i = 0; i < recipe.in.size(); i ++)
				inputs.add(recipe.in.get(i).getStack());
			while(inputs.size() < 4)
				inputs.add(ItemStackUtil.itemStackFrom(ModItems.nothing));
			output = recipe.getResult();
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static class FusionRecipe implements IRecipeWrapper {
		ItemStack input;
		ItemStack output;
		
		public FusionRecipe(final Fluid input, final ItemStack output) {
			this.input = ItemFluidIcon.getStack(input);
			this.output = output;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}

	public static class SAFERecipe implements IRecipeWrapper {
		ItemStack input;
		ItemStack output;
		
		public SAFERecipe(final ItemStack input, final ItemStack output) {
			this.input = input;
			this.output = output;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInput(VanillaTypes.ITEM, input);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}
	
	public static class HadronRecipe implements IRecipeWrapper {

		public ItemStack in1, in2, out1, out2;
		public int momentum;
		public boolean analysisOnly;
		
		public HadronRecipe(final ItemStack in1, final ItemStack in2, final ItemStack out1, final ItemStack out2, final int momentum, final boolean analysis) {
			this.in1 = in1;
			this.in2 = in2;
			this.out1 = out1;
			this.out2 = out2;
			this.momentum = momentum;
			this.analysisOnly = analysis;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients) {
			ingredients.setInputs(VanillaTypes.ITEM, Arrays.asList(in1, in2));
			ingredients.setOutputs(VanillaTypes.ITEM, Arrays.asList(out1, out2));
		}
		
		@Override
		public void drawInfo(final Minecraft minecraft, final int recipeWidth, final int recipeHeight, final int mouseX, final int mouseY) {
			if(analysisOnly)
				HadronRecipeHandler.analysis.draw(minecraft, 117, 17);
			final FontRenderer fontRenderer = minecraft.fontRenderer;
	    	
	    	final String mom = "" + momentum;
	    	fontRenderer.drawString(mom, -fontRenderer.getStringWidth(mom) / 2 + 19, 36, 0x404040);
	    	GlStateManager.color(1, 1, 1, 1);
		}
		
	}
	
	public static class SILEXRecipe implements IRecipeWrapper {

		List<List<ItemStack>> input;
		List<Double> chances;
		List<ItemStack> outputs;
		double produced;
		EnumWavelengths laserStrength;
		
		public SILEXRecipe(final List<ItemStack> inputs, final List<Double> chances, final List<ItemStack> outputs, final double produced, final EnumWavelengths laserStrength){
			input = new ArrayList<>(1);
			input.add(inputs);
			this.chances = chances;
			this.outputs = outputs;
			this.produced = produced;
			this.laserStrength = laserStrength;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients){
			ingredients.setInputLists(VanillaTypes.ITEM, input);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}

		@Override
		public void drawInfo(final Minecraft minecraft, final int recipeWidth, final int recipeHeight, final int mouseX, final int mouseY){
			final FontRenderer fontRenderer = minecraft.fontRenderer;

			final int output_size = this.outputs.size();
			final int sep = output_size > 6 ? 4 : output_size > 4 ? 3 : 2;
			for(int i = 0; i < output_size; i ++){
				final double chance = this.chances.get(i);
				if(i < sep) {
					fontRenderer.drawString(((int)(chance * 100D) / 100D)+"%", 90, 33 + i * 18 - 9 * ((Math.min(output_size, sep) + 1) / 2), 0x404040);
				} else {
					fontRenderer.drawString(((int)(chance * 100D) / 100D)+"%", 138, 33 + (i - sep) * 18 - 9 * ((Math.min(output_size - sep, sep) + 1)/2), 0x404040);
				}
			}
			
			final String am = ((int)(this.produced * 10D) / 10D) + "x";
			fontRenderer.drawString(am, 52 - fontRenderer.getStringWidth(am) / 2, 51, 0x404040);

			final String wavelength = (this.laserStrength == EnumWavelengths.NULL) ? TextFormatting.WHITE + "N/A" : this.laserStrength.textColor + I18nUtil.resolveKey(this.laserStrength.name);
			fontRenderer.drawString(wavelength, (35 - fontRenderer.getStringWidth(wavelength) / 2), 17, 0x404040);
		}
	}
	
	public static class AnvilRecipe implements IRecipeWrapper {

		List<List<ItemStack>> inputs;
		List<ItemStack> outputs;
		List<Float> chances;
		int tierLower;
		int tierUpper;
		OverlayType overlay;
		
		public AnvilRecipe(final List<List<ItemStack>> inp, final List<ItemStack> otp, final List<Float> chance, final int tL, final int tU, final OverlayType ovl){
			inputs = inp;
			outputs = otp;
			chances = chance;
			tierLower = tL;
			tierUpper = tU;
			overlay = ovl;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients){
			ingredients.setInputLists(VanillaTypes.ITEM, inputs);
			ingredients.setOutputs(VanillaTypes.ITEM, outputs);
		}
		
	}
	
	public static class SmithingRecipe implements IRecipeWrapper {

		List<List<ItemStack>> inputs;
		ItemStack output;
		int tier;
		
		public SmithingRecipe(final List<ItemStack> left, final List<ItemStack> right, final ItemStack out, final int tier){
			inputs = new ArrayList<>(2);
			inputs.add(left);
			inputs.add(right);
			output = out;
			this.tier = tier;
		}
		
		@Override
		public void getIngredients(final IIngredients ingredients){
			ingredients.setInputLists(VanillaTypes.ITEM, inputs);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
		
	}
	
	public static List<ChemRecipe> getChemistryRecipes() {
		if(chemRecipes != null)
			return chemRecipes;
		chemRecipes = new ArrayList<ChemRecipe>();
		
       for(final int i: ChemplantRecipes.recipeNames.keySet()){

        	final List<AStack> inputs = new ArrayList<AStack>(7);
        	for(int j = 0; j < 7; j ++)
        		inputs.add(j, ItemStackUtil.comparableStackFrom(ModItems.nothing));

        	final List<ItemStack> outputs = new ArrayList<ItemStack>(6);
        	for(int j = 0; j < 6; j ++)
        		outputs.add(j, ItemStackUtil.itemStackFrom(ModItems.nothing));
        	
        	//Adding template item
        	final ItemStack template = ItemStackUtil.itemStackFrom(ModItems.chemistry_template, 1, i);

        	final List<AStack> listIn = ChemplantRecipes.getChemInputFromTempate(template);
        	final FluidStack[] fluidIn = ChemplantRecipes.getFluidInputFromTempate(template);
        	final ItemStack[] listOut = ChemplantRecipes.getChemOutputFromTempate(template);
        	final FluidStack[] fluidOut = ChemplantRecipes.getFluidOutputFromTempate(template);

        	inputs.set(6, ItemStackUtil.comparableStackFrom(template));

        	if(listIn != null)
        		for(int j = 0; j < listIn.size(); j++)
        			if(listIn.get(j) != null)
        				inputs.set(j + 2, listIn.get(j).copy());

        	if(fluidIn != null)
	        	for(int j = 0; j < fluidIn.length; j++)
	        		if(fluidIn[j] != null)
	        			inputs.set(j, new NbtComparableStack(ItemFluidIcon.getStackWithQuantity(fluidIn[j].getFluid(), fluidIn[j].amount)));
        	
        	if(listOut != null)
	        	for(int j = 0; j < listOut.length; j++)
	        		if(listOut[j] != null)
	        			outputs.set(j + 2, listOut[j].copy());
        	
        	if(fluidOut != null)
	        	for(int j = 0; j < fluidOut.length; j++)
	        		if(fluidOut[j] != null)
	        			outputs.set(j, ItemFluidIcon.getStackWithQuantity(fluidOut[j].getFluid(), fluidOut[j].amount));
        	
        	chemRecipes.add(new ChemRecipe(inputs, outputs));
        }
		
		return chemRecipes;
	}

	public static List<MixerRecipe> getMixerRecipes() {
		if(mixerRecipes != null)
			return mixerRecipes;
		mixerRecipes = new ArrayList<MixerRecipe>();
		
        for(final Fluid f : MixerRecipes.recipesDurations.keySet()){

        	final List<AStack> inputs = new ArrayList<AStack>(3);

        	final AStack inputItem = MixerRecipes.getInputItem(f);
        	final FluidStack[] inputFluids = MixerRecipes.getInputFluidStacks(f);
        	if(inputItem != null)
        		inputs.add(inputItem);
        	if(inputFluids != null){
        		if(inputFluids.length >= 1) inputs.add(new NbtComparableStack(ItemFluidIcon.getStackWithQuantity(inputFluids[0].getFluid(), inputFluids[0].amount)));
        		if(inputFluids.length == 2) inputs.add(new NbtComparableStack(ItemFluidIcon.getStackWithQuantity(inputFluids[1].getFluid(), inputFluids[1].amount)));
        	}

        	final ItemStack output = ItemFluidIcon.getStackWithQuantity(f, MixerRecipes.getFluidOutputAmount(f));
        	
        	mixerRecipes.add(new MixerRecipe(inputs, output));
        }
		
		return mixerRecipes;
	}
	
	public static List<CyclotronRecipe> getCyclotronRecipes() {
		if(cyclotronRecipes != null)
			 return cyclotronRecipes;
		final Map<ItemStack[], ItemStack> recipes = CyclotronRecipes.getRecipes();
		cyclotronRecipes = new ArrayList<CyclotronRecipe>(recipes.size());
		for(final Entry<ItemStack[], ItemStack> e : recipes.entrySet()){
			cyclotronRecipes.add(new CyclotronRecipe(Arrays.asList(e.getKey()), e.getValue()));
		}
		
		return cyclotronRecipes;
	}
	
	@SuppressWarnings("unchecked")
	public static List<PressRecipe> getPressRecipes() {
		if(pressRecipes != null)
			return pressRecipes;

		pressRecipes = new ArrayList<PressRecipe>();
		
		for(final Map.Entry<Pair<PressRecipes.PressType, AStack>, ItemStack> entry : PressRecipes.pressRecipes.entrySet()){

			pressRecipes.add(new PressRecipe(PressRecipes.getStampList(entry.getKey().getKey()), entry.getKey().getValue().getStackList(), entry.getValue()));
		}
		
		return pressRecipes;
	}
	
	
	public static List<AlloyFurnaceRecipe> getAlloyRecipes() {
		if(alloyFurnaceRecipes != null)
			return alloyFurnaceRecipes;
		alloyFurnaceRecipes = new ArrayList<AlloyFurnaceRecipe>();

		for(final Map.Entry<Pair<AStack, AStack>, ItemStack> pairEntry : DiFurnaceRecipes.diRecipes.entrySet()){
			alloyFurnaceRecipes.add(new AlloyFurnaceRecipe(pairEntry.getKey().getKey(), pairEntry.getKey().getValue(), pairEntry.getValue()));
		}
		return alloyFurnaceRecipes;
	}

	public static List<RBMKFuelRecipe> getRBMKFuelRecipes() {
		if(rbmkFuelRecipes != null)
			return rbmkFuelRecipes;
		rbmkFuelRecipes = new ArrayList<RBMKFuelRecipe>();

		for(final Map.Entry<ItemStack, ItemStack> pairEntry : RBMKFuelRecipes.recipes.entrySet()){
			rbmkFuelRecipes.add(new RBMKFuelRecipe(pairEntry.getKey(), pairEntry.getValue()));
		}
		return rbmkFuelRecipes;
	}
	
	public static List<ItemStack> getAlloyFuels() {
		if(alloyFuels != null)
			return alloyFuels;
		alloyFuels = DiFurnaceRecipes.getAlloyFuels();
		return alloyFuels;
	}

	public static List<BoilerRecipe> getBoilerRecipes() {
		if(boilerRecipes != null)
			return boilerRecipes;
		boilerRecipes = new ArrayList<BoilerRecipe>();
		
		for(final Fluid f : FluidRegistry.getRegisteredFluids().values()){
			final Object[] outs = HeatRecipes.getBoilerOutput(f);
			if(outs != null){
				boilerRecipes.add(new BoilerRecipe(ItemFluidIcon.getStackWithQuantity(f, (Integer) outs[2]), ItemFluidIcon.getStackWithQuantity((Fluid) outs[0], (Integer) outs[1])));
			}
		}
		
		return boilerRecipes;
	}
	
	public static List<ItemStack> getBatteries() {
		if(batteries != null)
			return batteries;
		batteries = new ArrayList<ItemStack>();
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_potato));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_potatos));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_su));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_su_l));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_generic));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_red_cell));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_red_cell_6));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_red_cell_24));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_advanced));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_advanced_cell));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_advanced_cell_4));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_advanced_cell_12));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_lithium));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_lithium_cell));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_lithium_cell_3));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_lithium_cell_6));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_schrabidium));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_schrabidium_cell));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_schrabidium_cell_2));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_schrabidium_cell_4));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_spark));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_spark_cell_6));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_spark_cell_25));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_spark_cell_100));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_spark_cell_1000));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_spark_cell_10000));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.battery_spark_cell_power));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.fusion_core));
		batteries.add(ItemStackUtil.itemStackFrom(ModItems.energy_core));
		return batteries;
	}
	
	public static List<CMBFurnaceRecipe> getCMBRecipes() {
		if(cmbRecipes != null)
			return cmbRecipes;
		cmbRecipes = new ArrayList<CMBFurnaceRecipe>();
		
		cmbRecipes.add(new CMBFurnaceRecipe(Arrays.asList(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY)), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN))), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COMBINE_STEEL), 4)));
		cmbRecipes.add(new CMBFurnaceRecipe(Arrays.asList(ItemStackUtil.itemStackFrom(ModItems.powder_advanced_alloy), ItemStackUtil.itemStackFrom(ModItems.powder_magnetized_tungsten)), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COMBINE_STEEL), 4)));
		
		return cmbRecipes;
	}
	
	public static List<GasCentRecipe> getGasCentrifugeRecipes() {
		if(gasCentRecipes != null)
			return gasCentRecipes;
		gasCentRecipes = new ArrayList<GasCentRecipe>();
		
		for(final Fluid f : FluidRegistry.getRegisteredFluids().values()){
			final List<GasCentOutput> outputs = MachineRecipes.getGasCentOutput(f);
			
			if(outputs != null){
				int totalWeight = 0;
				
				for(final GasCentOutput o : outputs) {
					totalWeight += o.weight;
				}
				
				final ItemStack input = ItemFluidIcon.getStackWithQuantity(f, MachineRecipes.getFluidConsumedGasCent(f) * totalWeight);
				
				final List<ItemStack> result = new ArrayList<ItemStack>(4);
				
				for(final GasCentOutput o : outputs){
					final ItemStack stack = o.output.copy();
					stack.setCount(stack.getCount() * o.weight);
					result.add(stack);
				}
				
				gasCentRecipes.add(new GasCentRecipe(input, result));
			}
		}
		
		return gasCentRecipes;
	}
	
	public static List<BookRecipe> getBookRecipes(){
		if(bookRecipes != null)
			return bookRecipes;
		bookRecipes = new ArrayList<>();
		for(final MagicRecipe m : MagicRecipes.getRecipes()){
			bookRecipes.add(new BookRecipe(m));
		}
		return bookRecipes;
	}
	
	public static List<ReactorRecipe> getReactorRecipes(){
		if(reactorRecipes != null)
			return reactorRecipes;
		reactorRecipes = new ArrayList<ReactorRecipe>();
		
		for(final Map.Entry<ItemStack, BreederRecipe> entry : BreederRecipes.getAllRecipes().entrySet()){
			reactorRecipes.add(new ReactorRecipe(entry.getKey(), entry.getValue().output, entry.getValue().heat));
		}
		
		return reactorRecipes;
	}

	public static List<WasteDrumRecipe> getWasteDrumRecipes(){
		if(wasteDrumRecipes != null)
			return wasteDrumRecipes;
		wasteDrumRecipes = new ArrayList<WasteDrumRecipe>();
		
		for(final Map.Entry<Item, ItemStack> entry : WasteDrumRecipes.recipes.entrySet()){
			wasteDrumRecipes.add(new WasteDrumRecipe(ItemStackUtil.itemStackFrom(entry.getKey()), entry.getValue()));
		}
		
		return wasteDrumRecipes;
	}

	public static List<StorageDrumRecipe> getStorageDrumRecipes(){
		if(storageDrumRecipes != null)
			return storageDrumRecipes;
		storageDrumRecipes = new ArrayList<StorageDrumRecipe>();
		
		for(final Map.Entry<ComparableStack, ItemStack> entry : StorageDrumRecipes.recipeOutputs.entrySet()){
			storageDrumRecipes.add(new StorageDrumRecipe(entry.getKey().getStack(), entry.getValue()));
		}
		
		return storageDrumRecipes;
	}

	public static List<TransmutationRecipe> getTransmutationRecipes(){
		if(transmutationRecipes != null)
			return transmutationRecipes;
		transmutationRecipes = new ArrayList<TransmutationRecipe>();
		
		for(final Map.Entry<AStack, ItemStack> entry : NuclearTransmutationRecipes.recipesOutput.entrySet()){
			transmutationRecipes.add(new TransmutationRecipe(entry.getKey().getStackList(), entry.getValue()));
		}
		
		return transmutationRecipes;
	}
	
	public static List<ItemStack> getReactorFuels(final int heat){
		if(reactorFuelMap.containsKey(heat))
			return reactorFuelMap.get(heat);
		reactorFuelMap.put(heat, BreederRecipes.getAllFuelsFromHEAT(heat));
		return reactorFuelMap.get(heat);
	}
	

	public static List<RefineryRecipe> getRefineryRecipe() {
		if(refineryRecipes != null)
			return refineryRecipes;
		refineryRecipes = new ArrayList<RefineryRecipe>();
		
		for(final Fluid fluid : RefineryRecipes.refineryRecipesMap.keySet()){
			final FluidStack[] outputFluids = RefineryRecipes.getRecipe(fluid).getKey();
			final ItemStack outputItem = RefineryRecipes.getRecipe(fluid).getValue();
			refineryRecipes.add(new RefineryRecipe(
					ItemFluidIcon.getStackWithQuantity(fluid, 1000),
					Arrays.asList(
						ItemFluidIcon.getStackWithQuantity(outputFluids[0].getFluid(), outputFluids[0].amount * 10),
						ItemFluidIcon.getStackWithQuantity(outputFluids[1].getFluid(), outputFluids[1].amount * 10),
						ItemFluidIcon.getStackWithQuantity(outputFluids[2].getFluid(), outputFluids[2].amount * 10),
						ItemFluidIcon.getStackWithQuantity(outputFluids[3].getFluid(), outputFluids[3].amount * 10),
						outputItem.copy()
					)
				)
			);
		}
		return refineryRecipes;
	}

	public static List<CrackingRecipe> getCrackingRecipe() {
		if(crackingRecipes != null)
			return crackingRecipes;
		crackingRecipes = new ArrayList<CrackingRecipe>();

		for(final Fluid fluid : CrackRecipes.recipeFluids.keySet()){
			final FluidStack[] outputFluids = CrackRecipes.getOutputsFromFluid(fluid);
			final List<ItemStack> outputIcons = new ArrayList<ItemStack>();
			for(final FluidStack fluidStacks : outputFluids){
				outputIcons.add(ItemFluidIcon.getStackWithQuantity(fluidStacks.getFluid(), fluidStacks.amount * 10));
			}
			crackingRecipes.add(new CrackingRecipe(
					ItemFluidIcon.getStackWithQuantity(fluid, 1000),
					outputIcons
				)
			);
		}
		return crackingRecipes;
	}

	public static List<FractioningRecipe> getFractioningRecipe() {
		if(fractioningRecipes != null)
			return fractioningRecipes;
		fractioningRecipes = new ArrayList<FractioningRecipe>();

		for(final Fluid fluid : RefineryRecipes.fractions.keySet()){
			final Quartet<Fluid, Fluid, Integer, Integer> recipe = RefineryRecipes.getFractions(fluid);
			
			fractioningRecipes.add(new FractioningRecipe(
					ItemFluidIcon.getStackWithQuantity(fluid, 1000),
					Arrays.asList(
						ItemFluidIcon.getStackWithQuantity(recipe.getW(), recipe.getY() * 10),
						ItemFluidIcon.getStackWithQuantity(recipe.getX(), recipe.getZ() * 10)
					)
				)
			);
		}
		return fractioningRecipes;
	}
	
	public static List<ItemStack> getBlades() {
		if(blades != null)
			return blades;
		
		blades = new ArrayList<ItemStack>();
		blades.add(ItemStackUtil.itemStackFrom(ModItems.blades_advanced_alloy));
		blades.add(ItemStackUtil.itemStackFrom(ModItems.blades_aluminum));
		blades.add(ItemStackUtil.itemStackFrom(ModItems.blades_combine_steel));
		blades.add(ItemStackUtil.itemStackFrom(ModItems.blades_gold));
		blades.add(ItemStackUtil.itemStackFrom(ModItems.blades_iron));
		blades.add(ItemStackUtil.itemStackFrom(ModItems.blades_steel));
		blades.add(ItemStackUtil.itemStackFrom(ModItems.blades_titanium));
		blades.add(ItemStackUtil.itemStackFrom(ModItems.blades_schrabidium));
		return blades;
	}
	
	public static List<FluidRecipe> getFluidEquivalences(){
		if(fluidEquivalences != null)
			return fluidEquivalences;
		fluidEquivalences = new ArrayList<FluidRecipe>();
		
		for(final Fluid f : FluidRegistry.getRegisteredFluids().values()){
			fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.getStack(f), ItemFluidTank.getFullTank(f)));
			fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.getStack(f), ItemFluidTank.getFullTank(f)));

			fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.getStack(f), ItemFluidTank.getFullBarrel(f)));
			fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.getStack(f), ItemFluidTank.getFullBarrel(f)));

			if(EnumCanister.contains(f)){
				fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.getStack(f), ItemFluidCanister.getFullCanister(f)));
				fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.getStack(f), ItemFluidCanister.getFullCanister(f)));
			}
			if(EnumGasCanister.contains(f)){
				fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.getStack(f), ItemGasCanister.getFullCanister(f)));
				fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.getStack(f), ItemGasCanister.getFullCanister(f)));
			}
			if(EnumCell.contains(f)){
				fluidEquivalences.add(new FluidRecipe(ItemFluidIcon.getStack(f), ItemCell.getFullCell(f)));
				fluidEquivalences.add(new FluidRecipeInverse(ItemFluidIcon.getStack(f), ItemCell.getFullCell(f)));
			}
		}
		
		return fluidEquivalences;
	}
	
	public static List<FusionRecipe> getFusionByproducts(){
		if(fusionByproducts != null)
			return fusionByproducts;
		fusionByproducts = new ArrayList<>();
		fusionByproducts.add(new FusionRecipe(ModForgeFluids.plasma_dt, FusionRecipes.getByproduct(ModForgeFluids.plasma_dt)));
		fusionByproducts.add(new FusionRecipe(ModForgeFluids.plasma_hd, FusionRecipes.getByproduct(ModForgeFluids.plasma_hd)));
		fusionByproducts.add(new FusionRecipe(ModForgeFluids.plasma_ht, FusionRecipes.getByproduct(ModForgeFluids.plasma_ht)));
		fusionByproducts.add(new FusionRecipe(ModForgeFluids.plasma_xm, FusionRecipes.getByproduct(ModForgeFluids.plasma_xm)));
		fusionByproducts.add(new FusionRecipe(ModForgeFluids.plasma_put, FusionRecipes.getByproduct(ModForgeFluids.plasma_put)));
		fusionByproducts.add(new FusionRecipe(ModForgeFluids.plasma_bf, FusionRecipes.getByproduct(ModForgeFluids.plasma_bf)));
		return fusionByproducts;
	}

	public static List<SAFERecipe> getSAFERecipes(){
		if(safeRecipes != null)
			return safeRecipes;
		safeRecipes = new ArrayList<>();
		for(final Entry<ItemStack, ItemStack> recipe : com.hbm.inventory.SAFERecipes.getAllRecipes().entrySet()){
			safeRecipes.add(new SAFERecipe(recipe.getKey(), recipe.getValue()));
		}
		return safeRecipes;
	}
	
	public static List<HadronRecipe> getHadronRecipes(){
		if(hadronRecipes != null)
			return hadronRecipes;
		hadronRecipes = new ArrayList<>();
		for(final com.hbm.inventory.HadronRecipes.HadronRecipe recipe : com.hbm.inventory.HadronRecipes.getRecipes()){
			hadronRecipes.add(new HadronRecipe(recipe.in1.toStack(), recipe.in2.toStack(), recipe.out1, recipe.out2, recipe.momentum, recipe.analysisOnly));
		}
		return hadronRecipes;
	}
	

	public static List<SILEXRecipe> getSILEXRecipes(final EnumWavelengths wavelength){
		if(waveSilexRecipes.containsKey(wavelength))
			return waveSilexRecipes.get(wavelength);
		final ArrayList wSilexRecipes = new ArrayList<>();
		for(final Entry<List<ItemStack>, com.hbm.inventory.SILEXRecipes.SILEXRecipe> e : com.hbm.inventory.SILEXRecipes.getRecipes().entrySet()){
			final com.hbm.inventory.SILEXRecipes.SILEXRecipe out = e.getValue();
			if(out.laserStrength == wavelength){
				double weight = 0;
				for(final WeightedRandomObject obj : out.outputs) {
					weight += obj.itemWeight;
				}
				final List<Double> chances = new ArrayList<>(out.outputs.size());
				final List<ItemStack> outputs = new ArrayList<>(chances.size());
				for(int i = 0; i < out.outputs.size(); i++) {
					final WeightedRandomObject obj = out.outputs.get(i);
					outputs.add(obj.asStack());
					chances.add(100 * obj.itemWeight / weight);
				}
				wSilexRecipes.add(new SILEXRecipe(e.getKey(), chances, outputs, (double)out.fluidProduced/out.fluidConsumed, out.laserStrength));
			}
		}
		waveSilexRecipes.put(wavelength, wSilexRecipes);
		return wSilexRecipes;
	}


	public static List<SILEXRecipe> getSILEXRecipes(){
		if(silexRecipes != null)
			return silexRecipes;
		silexRecipes = new ArrayList<>();
		for(final Entry<List<ItemStack>, com.hbm.inventory.SILEXRecipes.SILEXRecipe> e : com.hbm.inventory.SILEXRecipes.getRecipes().entrySet()){
			final com.hbm.inventory.SILEXRecipes.SILEXRecipe out = e.getValue();
			double weight = 0;
			for(final WeightedRandomObject obj : out.outputs) {
				weight += obj.itemWeight;
			}
			final List<Double> chances = new ArrayList<>(out.outputs.size());
			final List<ItemStack> outputs = new ArrayList<>(chances.size());
			for(int i = 0; i < out.outputs.size(); i++) {
				final WeightedRandomObject obj = out.outputs.get(i);
				outputs.add(obj.asStack());
				chances.add(100 * obj.itemWeight / weight);
			}
			silexRecipes.add(new SILEXRecipe(e.getKey(), chances, outputs, (double)out.fluidProduced/out.fluidConsumed, out.laserStrength));
		}
		return silexRecipes;
	}
	
	public static List<SmithingRecipe> getSmithingRecipes(){
		if(smithingRecipes != null)
			return smithingRecipes;
		smithingRecipes = new ArrayList<>();
		for(final AnvilSmithingRecipe r : AnvilRecipes.getSmithing()){
			smithingRecipes.add(new SmithingRecipe(r.getLeft(), r.getRight(), r.getSimpleOutput(), r.tier));
		}
		return smithingRecipes;
	}
	
	public static List<AnvilRecipe> getAnvilRecipes(){
		if(anvilRecipes != null)
			return anvilRecipes;
		anvilRecipes = new ArrayList<>();
		for(final AnvilConstructionRecipe r : AnvilRecipes.getConstruction()){
			final List<List<ItemStack>> inputs = new ArrayList<>(r.input.size());
			final List<ItemStack> outputs = new ArrayList<>(r.output.size());
			final List<Float> chances = new ArrayList<>(r.output.size());
			for(final AStack sta : r.input){
				inputs.add(sta.getStackList());
			}
			for(final AnvilOutput sta : r.output){
				outputs.add(sta.stack.copy());
				chances.add(sta.chance);
			}
			anvilRecipes.add(new AnvilRecipe(inputs, outputs, chances, r.tierLower, r.tierUpper, r.getOverlay()));
 		}
		return anvilRecipes;
	}
}
