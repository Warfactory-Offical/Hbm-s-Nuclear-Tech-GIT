package com.hbm.handler.jei;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.inventory.CentrifugeRecipes;
import com.hbm.inventory.CrystallizerRecipes;
import com.hbm.inventory.ShredderRecipes;
import com.hbm.inventory.RBMKOutgasserRecipes;
import com.hbm.inventory.DFCRecipes;
import com.hbm.inventory.CrackRecipes;
import com.hbm.inventory.gui.GUIAnvil;
import com.hbm.inventory.gui.GUIBook;
import com.hbm.inventory.gui.GUICrystallizer;
import com.hbm.inventory.gui.GUIHadron;
import com.hbm.inventory.gui.GUIMixer;
import com.hbm.inventory.gui.GUIMachineAssembler;
import com.hbm.inventory.gui.GUIMachineBoiler;
import com.hbm.inventory.gui.GUIMachineBoilerElectric;
import com.hbm.inventory.gui.GUIMachineBoilerRTG;
import com.hbm.inventory.gui.GUIMachineCMBFactory;
import com.hbm.inventory.gui.GUIMachineCentrifuge;
import com.hbm.inventory.gui.GUIMachineChemplant;
import com.hbm.inventory.gui.GUIMachineCyclotron;
import com.hbm.inventory.gui.GUIMachineEPress;
import com.hbm.inventory.gui.GUIMachineGasCent;
import com.hbm.inventory.gui.GUIMachinePress;
import com.hbm.inventory.gui.GUIMachineReactor;
import com.hbm.inventory.gui.GUIMachineRefinery;
import com.hbm.inventory.gui.GUIMachineShredder;
import com.hbm.inventory.gui.GUISILEX;
import com.hbm.inventory.gui.GUITestDiFurnace;
import com.hbm.inventory.gui.GUIDiFurnaceRTG;
import com.hbm.inventory.gui.GUIRBMKOutgasser;
import com.hbm.inventory.gui.GUIFWatzCore;
import com.hbm.inventory.gui.GUIMachineSchrabidiumTransmutator;
import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemCustomMissile;
import com.hbm.items.machine.ItemFELCrystal.EnumWavelengths;
import com.hbm.main.MainRegistry;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

@JEIPlugin
public class JEIConfig implements IModPlugin {

	public static final String ASSEMBLY = "hbm.assembly";
	public static final String CHEMPLANT = "hbm.chemplant";
	public static final String MIXER = "hbm.mixer";
	public static final String CYCLOTRON = "hbm.cyclotron";
	public static final String PRESS = "hbm.press";
	public static final String ALLOY = "hbm.alloy";
	public static final String BOILER = "hbm.boiler";
	public static final String CENTRIFUGE = "hbm.centrifuge";
	public static final String CMB = "hbm.cmb_furnace";
	public static final String GAS_CENT = "hbm.gas_centrifuge";
	public static final String REACTOR = "hbm.reactor";
	public static final String REFINERY = "hbm.refinery";
	public static final String CRACKING = "hbm.cracking";
	public static final String FRACTIONING = "hbm.fracturing";
	public static final String SHREDDER = "hbm.shredder";
	public static final String FLUIDS = "hbm.fluids";
	public static final String CRYSTALLIZER = "hbm.crystallizer";
	public static final String BOOK = "hbm.book_of";
	public static final String FUSION_BYPRODUCT = "hbm.fusionbyproduct";
	public static final String HADRON = "hbm.hadron";
	public static final String SILEX = "hbm.silex";
	public static final String SILEX_RADIO = "hbm.silexradio";
	public static final String SILEX_MICRO = "hbm.silexmicro";
	public static final String SILEX_IR = "hbm.silexir";
	public static final String SILEX_VISIBLE = "hbm.silexvisible";
	public static final String SILEX_UV = "hbm.silexuv";
	public static final String SILEX_XRAY = "hbm.silexray";
	public static final String SILEX_GAMMA = "hbm.silexgamma";
	public static final String SILEX_DIGAMMA = "hbm.silexdigamma";
	public static final String WASTEDRUM = "hbm.waste_drum";
	public static final String STORAGEDRUM = "hbm.storage_drum";
	public static final String SMITHING = "hbm.smithing";
	public static final String ANVIL = "hbm.anvil";
	public static final String RBMKOUTGASSER = "hbm.rbmk_outgasser";
	public static final String RBMKFUEL = "hbm.rbmkfueluncrafting";
	public static final String SAFE_REACTOR = "hbm.safe_reactor";
	public static final String DFC = "hbm.dfc";
	public static final String TRANSMUTATION = "hbm.transmutation";

	@Override
	public void register(final IModRegistry registry) {
		if(!GeneralConfig.jei)
			return;
		registry.addRecipeRegistryPlugin(new HbmJeiRegistryPlugin());

		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_electric_furnace_off), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.furnace_iron), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.furnace_steel), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_arc_furnace_off), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.factory_titanium_furnace), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.factory_advanced_furnace), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_microwave), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_nuke_furnace_off), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_rtg_furnace_off), VanillaRecipeCategoryUid.SMELTING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.crate_tungsten), VanillaRecipeCategoryUid.SMELTING);
		
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_assembler), ASSEMBLY);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_chemplant), CHEMPLANT);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_chemfac), CHEMPLANT);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_mixer), MIXER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_cyclotron), CYCLOTRON);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_schrabidium_transmutator), TRANSMUTATION);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_press), PRESS);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_epress), PRESS);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_difurnace_off), ALLOY);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_difurnace_rtg_off), ALLOY);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_off), BOILER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_electric_off), BOILER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_rtg_off), BOILER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_solar_boiler), BOILER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.heat_boiler), BOILER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_heater), BOILER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_centrifuge), CENTRIFUGE);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_combine_factory), CMB);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_gascent), GAS_CENT);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_reactor), REACTOR);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_waste_drum), WASTEDRUM);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_storage_drum), STORAGEDRUM);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_refinery), REFINERY);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_catalytic_cracker), CRACKING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_fraction_tower), FRACTIONING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_shredder), SHREDDER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_fluidtank), FLUIDS);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_crystallizer), CRYSTALLIZER);
		//This recipe catalyst doesn't work, since the book of is blacklisted.
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.book_of_), BOOK);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.iter), FUSION_BYPRODUCT);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.hadron_core), HADRON);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.machine_silex), SILEX);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_nano), SILEX_RADIO);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_pentacene), SILEX_MICRO);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_co2), SILEX_IR);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_bismuth), SILEX_VISIBLE);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_cmb), SILEX_UV);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_dem), SILEX_XRAY);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_bale), SILEX_GAMMA);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_digamma), SILEX_DIGAMMA);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.anvil_iron), SMITHING);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.anvil_steel), ANVIL);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_outgasser), RBMKOUTGASSER);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(Blocks.CRAFTING_TABLE), RBMKFUEL);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.fwatz_core), SAFE_REACTOR);
		registry.addRecipeCatalyst(ItemStackUtil.itemStackFrom(ModBlocks.crate_tungsten), DFC);
		
		// registry.addRecipes(ItemAssemblyTemplate.recipes, ASSEMBLY);
		registry.addRecipes(JeiRecipes.getChemistryRecipes(), CHEMPLANT);
		registry.addRecipes(JeiRecipes.getMixerRecipes(), MIXER);
		registry.addRecipes(JeiRecipes.getCyclotronRecipes(), CYCLOTRON);
		registry.addRecipes(JeiRecipes.getTransmutationRecipes(), TRANSMUTATION);
		registry.addRecipes(JeiRecipes.getPressRecipes(), PRESS);
		registry.addRecipes(JeiRecipes.getAlloyRecipes(), ALLOY);
		registry.addRecipes(JeiRecipes.getBoilerRecipes(), BOILER);
		registry.addRecipes(CentrifugeRecipes.getCentrifugeRecipes(), CENTRIFUGE);
		registry.addRecipes(JeiRecipes.getCMBRecipes(), CMB);
		registry.addRecipes(JeiRecipes.getGasCentrifugeRecipes(), GAS_CENT);
		registry.addRecipes(JeiRecipes.getReactorRecipes(), REACTOR);
		registry.addRecipes(JeiRecipes.getWasteDrumRecipes(), WASTEDRUM);
		registry.addRecipes(JeiRecipes.getStorageDrumRecipes(), STORAGEDRUM);
		registry.addRecipes(JeiRecipes.getRefineryRecipe(), REFINERY);
		registry.addRecipes(JeiRecipes.getCrackingRecipe(), CRACKING);
		registry.addRecipes(JeiRecipes.getFractioningRecipe(), FRACTIONING);
		registry.addRecipes(ShredderRecipes.getShredderRecipes(), SHREDDER);
		registry.addRecipes(JeiRecipes.getFluidEquivalences(), FLUIDS);
		registry.addRecipes(CrystallizerRecipes.getRecipes(), CRYSTALLIZER);
		registry.addRecipes(JeiRecipes.getBookRecipes(), BOOK);
		registry.addRecipes(JeiRecipes.getFusionByproducts(), FUSION_BYPRODUCT);
		registry.addRecipes(JeiRecipes.getHadronRecipes(), HADRON);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(), SILEX);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(EnumWavelengths.RADIO), SILEX_RADIO);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(EnumWavelengths.MICRO), SILEX_MICRO);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(EnumWavelengths.IR), SILEX_IR);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(EnumWavelengths.VISIBLE), SILEX_VISIBLE);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(EnumWavelengths.UV), SILEX_UV);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(EnumWavelengths.XRAY), SILEX_XRAY);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(EnumWavelengths.GAMMA), SILEX_GAMMA);
		registry.addRecipes(JeiRecipes.getSILEXRecipes(EnumWavelengths.DRX), SILEX_DIGAMMA);
		registry.addRecipes(JeiRecipes.getSmithingRecipes(), SMITHING);
		registry.addRecipes(JeiRecipes.getAnvilRecipes(), ANVIL);
		registry.addRecipes(RBMKOutgasserRecipes.getRBMKOutgasserRecipes(), RBMKOUTGASSER);
		registry.addRecipes(JeiRecipes.getRBMKFuelRecipes(), RBMKFUEL);
		registry.addRecipes(JeiRecipes.getSAFERecipes(), SAFE_REACTOR);
		registry.addRecipes(DFCRecipes.getDFCRecipes(), DFC);

		registry.addRecipeClickArea(GUIMachineAssembler.class, 45, 83, 82, 30, ASSEMBLY);
		registry.addRecipeClickArea(GUIMachineChemplant.class, 45, 90, 85, 15, CHEMPLANT);
		registry.addRecipeClickArea(GUIMixer.class, 62, 36, 52, 44, MIXER);
		registry.addRecipeClickArea(GUIMachineCyclotron.class, 50, 24, 40, 40, CYCLOTRON);
		registry.addRecipeClickArea(GUIMachinePress.class, 80, 35, 15, 15, PRESS);
		registry.addRecipeClickArea(GUIMachineEPress.class, 80, 35, 15, 15, PRESS);
		registry.addRecipeClickArea(GUITestDiFurnace.class, 102, 36, 21, 14, ALLOY);
		registry.addRecipeClickArea(GUIDiFurnaceRTG.class, 102, 36, 21, 14, ALLOY);
		registry.addRecipeClickArea(GUIMachineBoiler.class, 61, 34, 17, 35, BOILER);
		registry.addRecipeClickArea(GUIMachineBoilerElectric.class, 61, 34, 17, 35, BOILER);
		registry.addRecipeClickArea(GUIMachineBoilerRTG.class, 61, 34, 17, 17, BOILER);
		registry.addRecipeClickArea(GUIMachineCentrifuge.class, 35, 9, 106, 40, CENTRIFUGE);
		registry.addRecipeClickArea(GUIMachineCMBFactory.class, 111, 35, 21, 14, CMB);
		registry.addRecipeClickArea(GUIMachineGasCent.class, 118, 36, 51, 13, GAS_CENT);
		registry.addRecipeClickArea(GUIMachineReactor.class, 80, 35, 21, 14, REACTOR);
		registry.addRecipeClickArea(GUIMachineRefinery.class, 79, 71, 71, 17, REFINERY);
		registry.addRecipeClickArea(GUIMachineShredder.class, 43, 89, 53, 17, SHREDDER);
		registry.addRecipeClickArea(GUICrystallizer.class, 79, 40, 29, 26, CRYSTALLIZER);
		registry.addRecipeClickArea(GUIBook.class, 89, 34, 23, 16, BOOK);
		registry.addRecipeClickArea(GUIHadron.class, 71, 28, 32, 32, HADRON);
		registry.addRecipeClickArea(GUISILEX.class, 45, 82, 113-45, 125-82, SILEX);
		registry.addRecipeClickArea(GUIAnvil.class, 34, 26, 52-34, 44-26, SMITHING);
		registry.addRecipeClickArea(GUIAnvil.class, 12, 50, 48-12, 66-50, ANVIL);
		registry.addRecipeClickArea(GUIFWatzCore.class, 52, 64, 72, 19, SAFE_REACTOR);
		registry.addRecipeClickArea(GUIRBMKOutgasser.class, 64, 53, 48, 16, RBMKOUTGASSER);
		registry.addRecipeClickArea(GUIMachineSchrabidiumTransmutator.class, 64, 56, 66, 31, TRANSMUTATION);

		final IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

		// Some things are even beyond my control...or are they?
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.memory));

		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.machine_coal_on));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.machine_electric_furnace_on));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.machine_arc_furnace_on));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.machine_difurnace_on));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.machine_nuke_furnace_on));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.machine_rtg_furnace_on));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.reinforced_lamp_on));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.statue_elb));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.statue_elb_g));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.statue_elb_w));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.statue_elb_f));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.cheater_virus));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.cheater_virus_seed));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.euphemium_helmet));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.euphemium_plate));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.euphemium_legs));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.euphemium_boots));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.apple_euphemium));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.ingot_euphemium));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.nugget_euphemium));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.euphemium_kit));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.euphemium_stopper));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.watch));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.rod_quad_euphemium));
		// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.rod_euphemium));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.bobmazon_hidden));
		if(MainRegistry.polaroidID != 11) {
			blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.book_secret));
			// blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.book_of_));
			blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModItems.ams_core_thingy));
		}
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_assembler));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_centrifuge));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_chemplant));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_cyclotron));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_flare));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_fluidtank));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_pumpjack));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_refinery));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_turbofan));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_well));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_ams_base));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_ams_emitter));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_ams_limiter));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_reactor_small));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_assembler));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_chemplant));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_cyclotron));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_flare));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_fluidtank));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_pumpjack));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_refinery));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_turbofan));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_well));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_ams_base));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_ams_emitter));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_ams_limiter));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_port_reactor_small));
		blacklist.addIngredientToBlacklist(ItemStackUtil.itemStackFrom(ModBlocks.dummy_block_silo_hatch));
	}

	@Override
	public void registerCategories(final IRecipeCategoryRegistration registry) {
		if(!GeneralConfig.jei)
			return;
		final IGuiHelper help = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(new AnvilRecipeHandler(help),
				new SmithingRecipeHandler(help),
				new PressRecipeHandler(help),
				new AlloyFurnaceRecipeHandler(help),
				new ShredderRecipeHandler(help),
				new AssemblerRecipeHandler(help),
				new ChemplantRecipeHandler(help),
				new MixerRecipeHandler(help),
				new BoilerRecipeHandler(help),
				new RefineryRecipeHandler(help),
				new CrackingRecipeHandler(help),
				new FractioningRecipeHandler(help),
				new CrystallizerRecipeHandler(help),
				new CentrifugeRecipeHandler(help),
				new GasCentrifugeRecipeHandler(help),
				new CyclotronRecipeHandler(help),
				new TransmutationRecipeHandler(help),
				new CMBFurnaceRecipeHandler(help),
				new ReactorRecipeHandler(help),
				new WasteDrumRecipeHandler(help),
				new StorageDrumRecipeHandler(help),
				new FluidRecipeHandler(help),
				new SILEXRecipeHandler(help),
				new SILEXRadioRecipeHandler(help),
				new SILEXMicroRecipeHandler(help),
				new SILEXIrRecipeHandler(help),
				new SILEXVisibleRecipeHandler(help),
				new SILEXUVRecipeHandler(help),
				new SILEXXRayRecipeHandler(help),
				new SILEXGammaRecipeHandler(help),
				new SILEXDigammaRecipeHandler(help),
				new RBMKOutgasserRecipeHandler(help),
				new RBMKFuelRecipeHandler(help),
				new FusionRecipeHandler(help),
				new HadronRecipeHandler(help),
				new SAFERecipeHandler(help),
				new DFCRecipeHandler(help),
				new BookRecipeHandler(help));
	}

	@Override
	public void registerItemSubtypes(final ISubtypeRegistry subtypeRegistry) {
		if(!GeneralConfig.jei)
			return;
		subtypeRegistry.registerSubtypeInterpreter(ModItems.cell, (final ItemStack stack) -> {
			final FluidStack fluid = FluidUtil.getFluidContained(stack);
			return ModItems.cell.getTranslationKey() + (fluid == null ? "empty" : fluid.getFluid().getUnlocalizedName() + fluid.amount);
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.fluid_barrel_full, (final ItemStack stack) -> {
			final FluidStack fluid = FluidUtil.getFluidContained(stack);
			return ModItems.fluid_barrel_full.getTranslationKey() + (fluid == null ? "empty" : fluid.getFluid().getUnlocalizedName() + fluid.amount);
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.fluid_tank_full, (final ItemStack stack) -> {
			final FluidStack fluid = FluidUtil.getFluidContained(stack);
			return ModItems.fluid_tank_full.getTranslationKey() + (fluid == null ? "empty" : fluid.getFluid().getUnlocalizedName() + fluid.amount);
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.canister_generic, (final ItemStack stack) -> {
			final FluidStack fluid = FluidUtil.getFluidContained(stack);
			return ModItems.canister_generic.getTranslationKey() + (fluid == null ? "empty" : fluid.getFluid().getUnlocalizedName() + fluid.amount);
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.missile_custom, (final ItemStack stack) -> {
			return ModItems.missile_custom.getTranslationKey() + "w" + ItemCustomMissile.readFromNBT(stack, "warhead") + "f" + ItemCustomMissile.readFromNBT(stack, "fuselage") + "s" + ItemCustomMissile.readFromNBT(stack, "stability") + "t" + ItemCustomMissile.readFromNBT(stack, "thruster");
		});
		subtypeRegistry.registerSubtypeInterpreter(ModItems.fluid_icon, (final ItemStack stack) -> {
			if(stack.hasTagCompound()) {
				String s = "";
				if(stack.getTagCompound().hasKey("type"))
					s = s + stack.getTagCompound().getString("type");
				return s;
			}
			return "";
		});
	}

	@Override
	public void onRuntimeAvailable(final IJeiRuntime jeiRuntime) {
	}
}
