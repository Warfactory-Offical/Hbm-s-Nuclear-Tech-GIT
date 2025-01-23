package com.hbm.main;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.crafting.handlers.MKUCraftingHandler;
import com.hbm.crafting.handlers.RBMKFuelCraftingHandler;
import com.hbm.crafting.handlers.SmallReactorFuelCraftingHandler;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.BedrockOreRegistry;
import com.hbm.inventory.OreDictManager;
import com.hbm.items.ModItems;
import com.hbm.items.machine.*;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.items.special.*;
import com.hbm.items.tool.ItemBombCaller;
import com.hbm.items.tool.ItemBombCaller.EnumCallerType;
import com.hbm.items.tool.ItemFluidCanister;
import com.hbm.items.weapon.GunB92Cell;
import com.hbm.lib.Library;
import com.hbm.lib.RefStrings;
import com.hbm.util.EnchantmentUtil;
import com.hbm.util.ItemStackUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IngredientNBT;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Map.Entry;

import static com.hbm.inventory.OreDictManager.*;

public class CraftingManager {

    public static RegistryEvent.Register<IRecipe> hack;
    public static boolean registeringFluids = false;

    public static void init() {
        if (!GeneralConfig.recipes) {
            return;
        }
        addCrafting();
        addSmelting();

        hack.getRegistry().register(new SmallReactorFuelCraftingHandler().setRegistryName(new ResourceLocation(RefStrings.MODID, "reactor_fuel_crafting_handler")));
        hack.getRegistry().register(new RBMKFuelCraftingHandler().setRegistryName(new ResourceLocation(RefStrings.MODID, "rbmk_fuel_crafting_handler")));
        hack.getRegistry().register(new MKUCraftingHandler().setRegistryName(new ResourceLocation(RefStrings.MODID, "mku_crafting_handler")));
    }

    public static void addCrafting() {

        //addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.redstone_sword, 1), new Object[] { "R", "R", "S", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STICK });
        //addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.big_sword, 1), new Object[] { "QIQ", "QIQ", "GSG", 'G', Items.GOLD_INGOT, 'S', Items.STICK, 'I', Items.IRON_INGOT, 'Q', Items.QUARTZ });

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard, 1), "##", "##", '#', ModItems.powder_balefire);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.board_copper, 1), "TTT", "TTT", 'T', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_cloth_red, 1), "C", "R", "C", 'C', ModItems.hazmat_cloth, 'R', BAKELITE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_cloth_grey, 1), "DPD", "ICI", "DLD", 'C', ModItems.hazmat_cloth_red, 'P', IRON.plate(), 'L', PB.plate(), 'I', ANY_RUBBER.ingot(), 'D', I.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.asbestos_cloth, 8), "SCS", "CPC", "SCS", 'S', Items.STRING, 'P', BR.dust(), 'C', Blocks.WOOL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bolt_dura_steel, 4), "D", "D", 'D', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pipes_steel, 1), "B", "B", "B", 'B', STEEL.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bolt_tungsten, 4), "D", "D", 'D', W.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bolt_compound, 1), "PDP", "PTP", "PDP", 'D', ModItems.bolt_dura_steel, 'T', ModItems.bolt_tungsten, 'P', TI.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_coal, 1), "PFP", "FOF", "PFP", 'P', COAL.dust(), 'F', Items.FLINT, 'O', ModBlocks.gravel_obsidian);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.marker_structure, 1), "L", "G", "R", 'L', LAPIS.dust(), 'G', Items.GLOWSTONE_DUST, 'R', Blocks.REDSTONE_TORCH);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.conveyor, 16), "LLL", "I I", "LLL", 'L', Items.LEATHER, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.conveyor, 64), "LLL", "I I", "LLL", 'L', RUBBER.ingot(), 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.conveyor_express, 8), "CCC", "CLC", "CCC", 'C', ModBlocks.conveyor, 'L', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.lubricant)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.conveyor_double, 3), "CPC", "CPC", "CPC", 'C', ModBlocks.conveyor, 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.conveyor_triple, 3), "CPC", "CPC", "CPC", 'C', ModBlocks.conveyor_double, 'P', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.conveyor_chute, 3), "IGI", "IGI", "ICI", 'I', IRON.ingot(), 'G', ModBlocks.steel_grate, 'C', ModBlocks.conveyor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.conveyor_lift, 3), "IGI", "IGI", "ICI", 'I', IRON.ingot(), 'G', ModBlocks.chain, 'C', ModBlocks.conveyor);

        final Object[] craneCasing = new Object[]{
                Blocks.STONEBRICK, 1,
                IRON.ingot(), 2,
                STEEL.ingot(), 4
        };

        for (int i = 0; i < craneCasing.length / 2; i++) {
            final Object casing = craneCasing[i * 2];
            final int amount = (int) craneCasing[i * 2 + 1];
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crane_inserter, amount), "CCC", "C C", "CBC", 'C', casing, 'B', ModBlocks.conveyor);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crane_ejector, amount), "CCC", "CPC", "CBC", 'C', casing, 'B', ModBlocks.conveyor, 'P', ModItems.piston_pneumatic);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crane_grabber, amount), "C C", "P P", "CBC", 'C', casing, 'B', ModBlocks.conveyor, 'P', ModItems.piston_pneumatic);
        }

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crane_boxer, 1), "WWW", "WPW", "CCC", 'W', KEY_PLANKS, 'P', ModItems.piston_pneumatic, 'C', ModBlocks.conveyor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crane_unboxer, 1), "WWW", "WPW", "CCC", 'W', KEY_STICK, 'P', Items.SHEARS, 'C', ModBlocks.conveyor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crane_splitter), "III", "PCP", "III", 'P', ModItems.piston_pneumatic, 'I', STEEL.ingot(), 'C', ModItems.circuit_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crane_router), "PIP", "ICI", "PIP", 'P', ModItems.piston_pneumatic, 'I', ModItems.plate_polymer, 'C', ModItems.circuit_copper);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_raw, 1), "A", "R", "S", 'S', STEEL.plate(), 'R', REDSTONE.dust(), 'A', ModItems.wire_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier1, 1), "CPC", 'C', ModItems.circuit_aluminium, 'P', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier2, 1), "CPC", 'C', ModItems.circuit_copper, 'P', NETHERQUARTZ.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier3, 1), "CPC", 'C', ModItems.circuit_red_copper, 'P', GOLD.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier4, 1), "CPC", 'C', ModItems.circuit_gold, 'P', LAPIS.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier5, 1), "CPC", 'C', ModItems.circuit_schrabidium, 'P', DIAMOND.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_targeting_tier6, 1), "P", "D", "C", 'C', ModItems.circuit_targeting_tier5, 'D', ModItems.battery_potatos, 'P', ModItems.powder_spark_mix);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_aluminium, 2), ModItems.circuit_targeting_tier1);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_copper, 2), ModItems.circuit_targeting_tier2);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_red_copper, 2), ModItems.circuit_targeting_tier3);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_gold, 2), ModItems.circuit_targeting_tier4);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_schrabidium, 2), ModItems.circuit_targeting_tier5);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bio_wafer, 1), ModItems.biomass_compressed, ModItems.biomass_compressed);

        addShapelessAuto(ItemStackUtil.itemStackFrom(Items.GUNPOWDER, 3), S.dust(), KNO.dust(), Items.COAL);
        addShapelessAuto(ItemStackUtil.itemStackFrom(Items.GUNPOWDER, 3), S.dust(), KNO.dust(), ItemStackUtil.itemStackFrom(Items.COAL, 1, 1));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cell, 6), "SSS", "G G", "SSS", 'S', STEEL.plate(), 'G', KEY_ANYPANE);
        addRecipeAuto(ItemCell.getFullCell(ModForgeFluids.deuterium, 8), "DDD", "DTD", "DDD", 'D', new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)), 'T', ModItems.mike_deut);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.canister_generic, 2), "S ", "AA", "AA", 'S', STEEL.plate(), 'A', AL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.yellow_barrel, 1), " D ", "LTL", 'D', ModBlocks.block_waste, 'T', ModItems.tank_steel, 'L', PB.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nuclear_waste, 9), ModBlocks.yellow_barrel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_canister, 2), "S ", "AA", "AA", 'A', STEEL.plate(), 'S', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.vitrified_barrel, 1), "LSL", "PWP", "LSL", 'L', PB.plate(), 'S', Blocks.SAND, 'P', ANY_PLASTIC.ingot(), 'W', ModBlocks.block_waste);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_waste_painted, 1), "dyeYellow", ModBlocks.block_waste);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.field_disturber), "ABA", "CDC", "ABA", 'A', STAR.ingot(), 'B', ModItems.circuit_bismuth, 'C', ModBlocks.hadron_coil_magtung, 'D', ModItems.pellet_rtg_gold);

        addRecipeAuto(ItemStackUtil.itemStackFrom(Blocks.TNT, 4), " S ", "S#S", " S ", '#', ModItems.ball_tnt, 'S', "sand");

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_aluminium, 1), "###", "###", "###", '#', AL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_copper, 1), "###", "###", "###", '#', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_fluorite, 1), "###", "###", "###", '#', F.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_niter, 1), "###", "###", "###", '#', KNO.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_red_copper, 1), "###", "###", "###", '#', MINGRADE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_steel, 1), "###", "###", "###", '#', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_sulfur, 1), "###", "###", "###", '#', S.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_titanium, 1), "###", "###", "###", '#', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_tungsten, 1), "###", "###", "###", '#', W.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_uranium, 1), "###", "###", "###", '#', U.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_thorium, 1), "###", "###", "###", '#', TH232.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_lead, 1), "###", "###", "###", '#', PB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_trinitite, 1), "###", "###", "###", '#', ModItems.trinitite);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_waste, 1), "###", "###", "###", '#', ModItems.nuclear_waste);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_scrap, 1), "##", "##", '#', ModItems.scrap);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_scrap, 1), "###", "###", "###", '#', ModItems.dust);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_beryllium, 1), "###", "###", "###", '#', BE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_schrabidium, 1), "###", "###", "###", '#', SA326.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_schrabidium_cluster, 1), "#S#", "SAS", "#S#", '#', SA326.ingot(), 'A', SBD.ingot(), 'S', STAR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_euphemium, 1), "###", "###", "###", '#', EUPH.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_advanced_alloy, 1), "###", "###", "###", '#', ALLOY.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_magnetized_tungsten, 1), "###", "###", "###", '#', MAGTUNG.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_combine_steel, 1), "###", "###", "###", '#', CMB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_australium, 1), "###", "###", "###", '#', AUSTRALIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_weidanium, 1), "###", "###", "###", '#', WEIDANIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_reiium, 1), "###", "###", "###", '#', REIIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_unobtainium, 1), "###", "###", "###", '#', UNOBTAINIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_daffergon, 1), "###", "###", "###", '#', DAFFERGON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_verticium, 1), "###", "###", "###", '#', VERTICIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_desh, 1), "###", "###", "###", '#', DESH.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_dura_steel, 1), "###", "###", "###", '#', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_saturnite, 1), "###", "###", "###", '#', BIGMT.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_ra226, 1), "###", "###", "###", '#', RA226.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_radspice, 1), "###", "###", "###", '#', ModItems.ingot.getItemStack(MaterialMineral.RADSPICE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_polymer, 1), "###", "###", "###", '#', POLYMER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_bakelite, 1), "###", "###", "###", '#', BAKELITE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_rubber, 1), "###", "###", "###", '#', RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_meteor_cobble, 1), "##", "##", '#', ModItems.fragment_meteorite);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_meteor_broken, 1), "###", "###", "###", '#', ModItems.fragment_meteorite);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_yellowcake, 1), "###", "###", "###", '#', ModItems.powder_yellowcake);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_au198, 1), "###", "###", "###", '#', AU198.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_starmetal, 1), "###", "###", "###", '#', STAR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_niter_reinforced, 1), "TCT", "CNC", "TCT", 'T', TCALLOY.ingot(), 'C', ModBlocks.concrete, 'N', KNO.block());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hazmat, 8), "###", "# #", "###", '#', ModItems.hazmat_cloth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_cloth, 1), "#", '#', ModBlocks.hazmat);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), 1), U235.nugget(), U235.nugget(), U235.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), 1), U233.nugget(), U233.nugget(), U233.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL), 1), U233.nugget(), U233.nugget(), U233.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 1), PU238.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU240.nugget(), PU240.nugget(), PU240.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), 1), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), 1), U235.nugget(), U235.nugget(), U235.nugget(), U238.nugget(), U238.nugget(), PU238.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 1), SA326.nugget(), SA326.nugget(), SA326.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), BE.nugget(), BE.nugget(), BE.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.HES), 1), SA326.nugget(), SA326.nugget(), SA326.nugget(), SA326.nugget(), SA326.nugget(), NP237.nugget(), NP237.nugget(), BE.nugget(), BE.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LES), 1), SA326.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), BE.nugget(), BE.nugget(), BE.nugget(), BE.nugget());

        //AMMO CLIP UNCRAFTING
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_iron_ammo, 24), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_iron));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_ammo, 12), ItemStackUtil.itemStackFrom(ModItems.clip_revolver));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_gold_ammo, 12), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_gold));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_lead_ammo, 12), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_lead));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_schrabidium_ammo, 12), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_schrabidium));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_cursed_ammo, 17), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_cursed));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_nightmare_ammo, 6), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_nightmare));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_nightmare2_ammo, 6), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_nightmare2));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44_pip, 6), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_pip));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44, 12), ItemStackUtil.itemStackFrom(ModItems.clip_revolver_nopip));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket, 6), ItemStackUtil.itemStackFrom(ModItems.clip_rpg));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_stinger_ammo, 6), ItemStackUtil.itemStackFrom(ModItems.clip_stinger));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke, 6), ItemStackUtil.itemStackFrom(ModItems.clip_fatman));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_mirv, 3), ItemStackUtil.itemStackFrom(ModItems.clip_mirv));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_bf_ammo, 2), ItemStackUtil.itemStackFrom(ModItems.clip_bf));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_9mm, 32), ItemStackUtil.itemStackFrom(ModItems.clip_mp40));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr, 32), ItemStackUtil.itemStackFrom(ModItems.clip_uzi));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge, 24), ItemStackUtil.itemStackFrom(ModItems.clip_uboinik));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge, 24), ItemStackUtil.itemStackFrom(ModItems.clip_lever_action));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_slug, 24), ItemStackUtil.itemStackFrom(ModItems.clip_bolt_action));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_osipr_ammo, 30), ItemStackUtil.itemStackFrom(ModItems.clip_osipr));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_immolator_ammo, 64), ItemStackUtil.itemStackFrom(ModItems.clip_immolator));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_cryolator_ammo, 64), ItemStackUtil.itemStackFrom(ModItems.clip_cryolator));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_566_gold, 40), ItemStackUtil.itemStackFrom(ModItems.clip_mp));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_xvl1456_ammo, 64), ItemStackUtil.itemStackFrom(ModItems.clip_xvl1456));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_emp_ammo, 6), ItemStackUtil.itemStackFrom(ModItems.clip_emp));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_jack_ammo, 12), ItemStackUtil.itemStackFrom(ModItems.clip_jack));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_spark_ammo, 4), ItemStackUtil.itemStackFrom(ModItems.clip_spark));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_hp_ammo, 12), ItemStackUtil.itemStackFrom(ModItems.clip_hp));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_euthanasia_ammo, 16), ItemStackUtil.itemStackFrom(ModItems.clip_euthanasia));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_defabricator_ammo, 16), ItemStackUtil.itemStackFrom(ModItems.clip_defabricator));


        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_copper));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fluorite, 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_fluorite));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.niter, 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_niter));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_red_copper));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_steel));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sulfur, 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_sulfur));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_titanium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_tungsten));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_uranium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TH232), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_thorium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_lead));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.trinitite, 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_trinitite));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nuclear_waste, 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_waste));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nuclear_waste, 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_waste_painted));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BERYLLIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_beryllium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_schrabidium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.EUPHEMIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_euphemium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_advanced_alloy));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_magnetized_tungsten));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COMBINE_STEEL), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_combine_steel));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AUSTRALIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_australium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.WEIDANIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_weidanium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.REIIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_reiium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.UNOBTAINIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_unobtainium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DAFFERGON), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_daffergon));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.VERTICIUM), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_verticium));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DESH), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_desh));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_dura_steel));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SATURNITE), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_saturnite));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RA226), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_ra226));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RADSPICE), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_radspice));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLYMER), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_polymer));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BAKELITE), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_bakelite));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RUBBER), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_rubber));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_yellowcake, 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_yellowcake));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AU198), 9), "#", '#', Item.getItemFromBlock(ModBlocks.block_au198));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STARMETAL), 9), "#", '#', ModBlocks.block_starmetal);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM), 1), "###", "###", "###", '#', PU.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM), 9), "#", '#', PU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU238), 1), "###", "###", "###", '#', PU238.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU238), 9), "#", '#', PU238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU239), 1), "###", "###", "###", '#', PU239.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU239), 9), "#", '#', PU239.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU240), 1), "###", "###", "###", '#', PU240.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PU240), 9), "#", '#', PU240.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TH232), 1), "###", "###", "###", '#', TH232.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.TH232), 9), "#", '#', TH232.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM), 1), "###", "###", "###", '#', U.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.URANIUM), 9), "#", '#', U.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U233), 1), "###", "###", "###", '#', U233.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U233), 9), "#", '#', U233.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U235), 1), "###", "###", "###", '#', U235.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U235), 9), "#", '#', U235.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U238), 1), "###", "###", "###", '#', U238.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.U238), 9), "#", '#', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM), 1), "###", "###", "###", '#', NP237.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.NEPTUNIUM), 9), "#", '#', NP237.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLONIUM), 1), "###", "###", "###", '#', PO210.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.POLONIUM), 9), "#", '#', PO210.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD), 1), "###", "###", "###", '#', PB.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.LEAD), 9), "#", '#', PB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BERYLLIUM), 1), "###", "###", "###", '#', BE.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.BERYLLIUM), 9), "#", '#', BE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM), 1), "###", "###", "###", '#', SA326.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 9), "#", '#', SA326.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), 1), "###", "###", "###", '#', ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), 9), "#", '#', ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL), 1), "###", "###", "###", '#', ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), 9), "#", '#', ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 1), "###", "###", "###", '#', ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 9), "#", '#', ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), 1), "###", "###", "###", '#', ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), 9), "#", '#', ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 1), "###", "###", "###", '#', ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 9), "#", '#', ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.HES), 1), "###", "###", "###", '#', ModItems.nugget.getItemStack(MaterialMineral.HES));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.HES), 9), "#", '#', ModItems.ingot.getItemStack(MaterialMineral.HES));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LES), 1), "###", "###", "###", '#', ModItems.nugget.getItemStack(MaterialMineral.LES));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.LES), 9), "#", '#', ModItems.ingot.getItemStack(MaterialMineral.LES));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AUSTRALIUM), 1), "###", "###", "###", '#', AUSTRALIUM.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AUSTRALIUM), 9), "#", '#', AUSTRALIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.WEIDANIUM), 1), "###", "###", "###", '#', WEIDANIUM.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.WEIDANIUM), 9), "#", '#', WEIDANIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.REIIUM), 1), "###", "###", "###", '#', REIIUM.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.REIIUM), 9), "#", '#', REIIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.UNOBTAINIUM), 1), "###", "###", "###", '#', UNOBTAINIUM.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM), 9), "#", '#', UNOBTAINIUM.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DAFFERGON), 1), "###", "###", "###", '#', DAFFERGON.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.DAFFERGON), 9), "#", '#', DAFFERGON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.VERTICIUM), 1), "###", "###", "###", '#', VERTICIUM.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.VERTICIUM), 9), "#", '#', VERTICIUM.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AC227), 1), "###", "###", "###", '#', AC227.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AC227), 9), "#", '#', AC227.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ACTINIUM), 1), "###", "###", "###", '#', AC.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.ACTINIUM), 9), "#", '#', AC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COBALT), 1), "###", "###", "###", '#', CO.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.COBALT), 9), "#", '#', CO.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SR90), 1), "###", "###", "###", '#', SR90.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SR90), 9), "#", '#', SR90.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STRONTIUM), 1), "###", "###", "###", '#', SR.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.STRONTIUM), 9), "#", '#', SR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PB209), 1), "###", "###", "###", '#', PB209.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PB209), 9), "#", '#', PB209.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.GH336), 1), "###", "###", "###", '#', GH336.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.GH336), 9), "#", '#', GH336.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot_u238m2, 1), "###", "###", "###", '#', ModItems.nugget_u238m2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget_u238m2, 9), "#", '#', ModItems.ingot_u238m2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RA226), 1), "###", "###", "###", '#', RA226.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.RA226), 9), "#", '#', RA226.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DESH), 1), "###", "###", "###", '#', DESH.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.DESH), 9), "#", '#', DESH.ingot());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_radspice, 1), CO60.dust(), SR90.dust(), I131.dust(), CS137.dust(), XE135.dust(), AU198.dust(), PB209.dust(), AT209.dust(), AC227.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_radspice_tiny, 1), CO60.dustTiny(), SR90.dustTiny(), I131.dustTiny(), CS137.dustTiny(), XE135.dustTiny(), AU198.dustTiny(), PB209.dustTiny(), AT209.dustTiny(), AC227.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_steel, 1), "###", "###", "###", '#', STEEL.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_steel_tiny, 9), "#", '#', STEEL.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_lithium, 1), "###", "###", "###", '#', LI.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_lithium_tiny, 9), "#", '#', LI.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_cobalt, 1), "###", "###", "###", '#', CO.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_cobalt_tiny, 9), "#", '#', CO.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_neodymium, 1), "###", "###", "###", '#', ND.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_neodymium_tiny, 9), "#", '#', ND.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_niobium, 1), "###", "###", "###", '#', NB.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_niobium_tiny, 9), "#", '#', NB.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_cerium, 1), "###", "###", "###", '#', CE.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_cerium_tiny, 9), "#", '#', CE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_lanthanium, 1), "###", "###", "###", '#', LA.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_lanthanium_tiny, 9), "#", '#', LA.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_actinium, 1), "###", "###", "###", '#', AC.dustTiny());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_actinium_tiny, 9), "#", '#', AC.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 1), "###", "###", "###", '#', ModItems.powder_meteorite_tiny);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_meteorite_tiny, 9), "#", '#', ModItems.powder_meteorite);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SOLINIUM), 1), "###", "###", "###", '#', SA327.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SOLINIUM), 9), "#", '#', SA327.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nuclear_waste, 1), "###", "###", "###", '#', ModItems.nuclear_waste_tiny);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_tiny, 9), "#", '#', ModItems.nuclear_waste);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rod_empty, 16), "SSS", "L L", "SSS", 'S', STEEL.plate(), 'L', PB.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_th232, 1), ModItems.rod_empty, TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_uranium, 1), ModItems.rod_empty, U.nugget(), U.nugget(), U.nugget(), U.nugget(), U.nugget(), U.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_u233, 1), ModItems.rod_empty, U233.nugget(), U233.nugget(), U233.nugget(), U233.nugget(), U233.nugget(), U233.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_u235, 1), ModItems.rod_empty, U235.nugget(), U235.nugget(), U235.nugget(), U235.nugget(), U235.nugget(), U235.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_u238, 1), ModItems.rod_empty, U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_plutonium, 1), ModItems.rod_empty, PU.nugget(), PU.nugget(), PU.nugget(), PU.nugget(), PU.nugget(), PU.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_pu238, 1), ModItems.rod_empty, PU238.nugget(), PU238.nugget(), PU238.nugget(), PU238.nugget(), PU238.nugget(), PU238.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_pu239, 1), ModItems.rod_empty, PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_pu240, 1), ModItems.rod_empty, PU240.nugget(), PU240.nugget(), PU240.nugget(), PU240.nugget(), PU240.nugget(), PU240.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_neptunium, 1), ModItems.rod_empty, NP237.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_polonium, 1), ModItems.rod_empty, PO210.nugget(), PO210.nugget(), PO210.nugget(), PO210.nugget(), PO210.nugget(), PO210.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_lead, 1), ModItems.rod_empty, PB.nugget(), PB.nugget(), PB.nugget(), PB.nugget(), PB.nugget(), PB.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_schrabidium, 1), ModItems.rod_empty, SA326.nugget(), SA326.nugget(), SA326.nugget(), SA326.nugget(), SA326.nugget(), SA326.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_solinium, 1), ModItems.rod_empty, SA327.nugget(), SA327.nugget(), SA327.nugget(), SA327.nugget(), SA327.nugget(), SA327.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_uranium_fuel, 1), ModItems.rod_empty, ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_thorium_fuel, 1), ModItems.rod_empty, ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_plutonium_fuel, 1), ModItems.rod_empty, ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_mox_fuel, 1), ModItems.rod_empty, ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_schrabidium_fuel, 1), ModItems.rod_empty, ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_euphemium, 1), ModItems.rod_empty, EUPH.nugget(), EUPH.nugget(), EUPH.nugget(), EUPH.nugget(), EUPH.nugget(), EUPH.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_australium, 1), ModItems.rod_empty, AUSTRALIUM.nugget(), AUSTRALIUM.nugget(), AUSTRALIUM.nugget(), AUSTRALIUM.nugget(), AUSTRALIUM.nugget(), AUSTRALIUM.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_weidanium, 1), ModItems.rod_empty, WEIDANIUM.nugget(), WEIDANIUM.nugget(), WEIDANIUM.nugget(), WEIDANIUM.nugget(), WEIDANIUM.nugget(), WEIDANIUM.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_reiium, 1), ModItems.rod_empty, REIIUM.nugget(), REIIUM.nugget(), REIIUM.nugget(), REIIUM.nugget(), REIIUM.nugget(), REIIUM.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_unobtainium, 1), ModItems.rod_empty, UNOBTAINIUM.nugget(), UNOBTAINIUM.nugget(), UNOBTAINIUM.nugget(), UNOBTAINIUM.nugget(), UNOBTAINIUM.nugget(), UNOBTAINIUM.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_daffergon, 1), ModItems.rod_empty, DAFFERGON.nugget(), DAFFERGON.nugget(), DAFFERGON.nugget(), DAFFERGON.nugget(), DAFFERGON.nugget(), DAFFERGON.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_verticium, 1), ModItems.rod_empty, VERTICIUM.nugget(), VERTICIUM.nugget(), VERTICIUM.nugget(), VERTICIUM.nugget(), VERTICIUM.nugget(), VERTICIUM.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_balefire, 1), ModItems.rod_empty, ModItems.egg_balefire_shard);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_ac227, 1), ModItems.rod_empty, AC227.nugget(), AC227.nugget(), AC227.nugget(), AC227.nugget(), AC227.nugget(), AC227.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_cobalt, 1), ModItems.rod_empty, CO.nugget(), CO.nugget(), CO.nugget(), CO.nugget(), CO.nugget(), CO.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_co60, 1), ModItems.rod_empty, CO60.nugget(), CO60.nugget(), CO60.nugget(), CO60.nugget(), CO60.nugget(), CO60.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_ra226, 1), ModItems.rod_empty, RA226.nugget(), RA226.nugget(), RA226.nugget(), RA226.nugget(), RA226.nugget(), RA226.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_rgp, 1), ModItems.rod_empty, PURG.nugget(), PURG.nugget(), PURG.nugget(), PURG.nugget(), PURG.nugget(), PURG.nugget());


        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.URANIUM), ModItems.rod_uranium);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U233), ModItems.rod_u233);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U235), ModItems.rod_u235);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U238), ModItems.rod_u238);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.TH232), ModItems.rod_th232);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM), ModItems.rod_plutonium);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU238), ModItems.rod_pu238);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU239), ModItems.rod_pu239);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU240), ModItems.rod_pu240);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM), ModItems.rod_neptunium);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.POLONIUM), ModItems.rod_polonium);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM), ModItems.rod_schrabidium);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.SOLINIUM), ModItems.rod_solinium);
        addRodBillet(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.rod_uranium_fuel);
        addRodBillet(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.rod_thorium_fuel);
        addRodBillet(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.rod_plutonium_fuel);
        addRodBillet(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), ModItems.rod_mox_fuel);
        addRodBillet(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.rod_schrabidium_fuel);
        addRodBilletUnload(ItemStackUtil.itemStackFrom(ModItems.billet_nuclear_waste), ModItems.rod_waste);


        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.AC227), ModItems.rod_ac227);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.RA226), ModItems.rod_ra226);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU_MIX), ModItems.rod_rgp);
        addRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.CO60), ModItems.rod_co60);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_empty, 2), ModItems.rod_dual_empty);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_empty, 1), ModItems.rod_empty, ModItems.rod_empty);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_th232, 1), ModItems.rod_dual_empty, TH232.ingot(), TH232.nugget(), TH232.nugget(), TH232.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_uranium, 1), ModItems.rod_dual_empty, U.ingot(), U.nugget(), U.nugget(), U.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_u233, 1), ModItems.rod_dual_empty, U233.ingot(), U233.nugget(), U233.nugget(), U233.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_u235, 1), ModItems.rod_dual_empty, U235.ingot(), U235.nugget(), U235.nugget(), U235.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_u238, 1), ModItems.rod_dual_empty, U238.ingot(), U238.nugget(), U238.nugget(), U238.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_plutonium, 1), ModItems.rod_dual_empty, PU.ingot(), PU.nugget(), PU.nugget(), PU.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_pu238, 1), ModItems.rod_dual_empty, PU238.ingot(), PU238.nugget(), PU238.nugget(), PU238.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_pu239, 1), ModItems.rod_dual_empty, PU239.ingot(), PU239.nugget(), PU239.nugget(), PU239.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_pu240, 1), ModItems.rod_dual_empty, PU240.ingot(), PU240.nugget(), PU240.nugget(), PU240.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_neptunium, 1), ModItems.rod_dual_empty, NP237.ingot(), NP237.nugget(), NP237.nugget(), NP237.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_polonium, 1), ModItems.rod_dual_empty, PO210.ingot(), PO210.nugget(), PO210.nugget(), PO210.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_lead, 1), ModItems.rod_dual_empty, PB.ingot(), PB.nugget(), PB.nugget(), PB.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_schrabidium, 1), ModItems.rod_dual_empty, SA326.ingot(), SA326.nugget(), SA326.nugget(), SA326.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_solinium, 1), ModItems.rod_dual_empty, SA327.ingot(), SA327.nugget(), SA327.nugget(), SA327.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_uranium_fuel, 1), ModItems.rod_dual_empty, ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_thorium_fuel, 1), ModItems.rod_dual_empty, ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_plutonium_fuel, 1), ModItems.rod_dual_empty, ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_mox_fuel, 1), ModItems.rod_dual_empty, ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_schrabidium_fuel, 1), ModItems.rod_dual_empty, ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_balefire, 1), ModItems.rod_dual_empty, ModItems.egg_balefire_shard, ModItems.egg_balefire_shard);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_euphemium, 1), ModItems.rod_quad_empty, EUPH.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_ac227, 1), ModItems.rod_dual_empty, AC227.ingot(), AC227.nugget(), AC227.nugget(), AC227.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_cobalt, 1), ModItems.rod_dual_empty, CO.ingot(), CO.nugget(), CO.nugget(), CO.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_co60, 1), ModItems.rod_dual_empty, CO60.ingot(), CO60.nugget(), CO60.nugget(), CO60.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_ra226, 1), ModItems.rod_dual_empty, RA226.ingot(), RA226.nugget(), RA226.nugget(), RA226.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_rgp, 1), ModItems.rod_dual_empty, PURG.ingot(), PURG.nugget(), PURG.nugget(), PURG.nugget());


        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.URANIUM), ModItems.rod_dual_uranium);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U233), ModItems.rod_dual_u233);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U235), ModItems.rod_dual_u235);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U238), ModItems.rod_dual_u238);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.TH232), ModItems.rod_dual_th232);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM), ModItems.rod_dual_plutonium);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU238), ModItems.rod_dual_pu238);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU239), ModItems.rod_dual_pu239);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU240), ModItems.rod_dual_pu240);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM), ModItems.rod_dual_neptunium);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.POLONIUM), ModItems.rod_dual_polonium);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM), ModItems.rod_dual_schrabidium);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.SOLINIUM), ModItems.rod_dual_solinium);
        addDualRodBillet(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.rod_dual_uranium_fuel);
        addDualRodBillet(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.rod_dual_thorium_fuel);
        addDualRodBillet(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.rod_dual_plutonium_fuel);
        addDualRodBillet(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), ModItems.rod_dual_mox_fuel);
        addDualRodBillet(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.rod_dual_schrabidium_fuel);
        addDualRodBilletUnload(ItemStackUtil.itemStackFrom(ModItems.billet_nuclear_waste), ModItems.rod_dual_waste);

        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.AC227), ModItems.rod_dual_ac227);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.RA226), ModItems.rod_dual_ra226);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU_MIX), ModItems.rod_dual_rgp);
        addDualRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.CO60), ModItems.rod_dual_co60);


        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_lithium, 1), ModItems.rod_empty, LI.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_lithium, 1), ModItems.rod_dual_empty, LI.ingot(), LI.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_lithium, 1), ModItems.rod_quad_empty, LI.ingot(), LI.ingot(), LI.ingot(), LI.ingot());
        addShapelessAuto(ItemCell.getFullCell(ModForgeFluids.tritium, 1), ModItems.rod_tritium, new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)));
        addShapelessAuto(ItemCell.getFullCell(ModForgeFluids.tritium, 2), ModItems.rod_dual_tritium, new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)), new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)));
        addShapelessAuto(ItemCell.getFullCell(ModForgeFluids.tritium, 4), ModItems.rod_quad_tritium, new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)), new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)), new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)), new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)));

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_empty, 4), ModItems.rod_quad_empty);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_empty, 1), ModItems.rod_empty, ModItems.rod_empty, ModItems.rod_empty, ModItems.rod_empty);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_empty, 1), ModItems.rod_dual_empty, ModItems.rod_dual_empty);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_th232, 1), ModItems.rod_quad_empty, TH232.ingot(), TH232.ingot(), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_uranium, 1), ModItems.rod_quad_empty, U.ingot(), U.ingot(), U.nugget(), U.nugget(), U.nugget(), U.nugget(), U.nugget(), U.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_u233, 1), ModItems.rod_quad_empty, U233.ingot(), U233.ingot(), U233.nugget(), U233.nugget(), U233.nugget(), U233.nugget(), U233.nugget(), U233.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_u235, 1), ModItems.rod_quad_empty, U235.ingot(), U235.ingot(), U235.nugget(), U235.nugget(), U235.nugget(), U235.nugget(), U235.nugget(), U235.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_u238, 1), ModItems.rod_quad_empty, U238.ingot(), U238.ingot(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_plutonium, 1), ModItems.rod_quad_empty, PU.ingot(), PU.ingot(), PU.nugget(), PU.nugget(), PU.nugget(), PU.nugget(), PU.nugget(), PU.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_pu238, 1), ModItems.rod_quad_empty, PU238.ingot(), PU238.ingot(), PU238.nugget(), PU238.nugget(), PU238.nugget(), PU238.nugget(), PU238.nugget(), PU238.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_pu239, 1), ModItems.rod_quad_empty, PU239.ingot(), PU239.ingot(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_pu240, 1), ModItems.rod_quad_empty, PU240.ingot(), PU240.ingot(), PU240.nugget(), PU240.nugget(), PU240.nugget(), PU240.nugget(), PU240.nugget(), PU240.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_neptunium, 1), ModItems.rod_quad_empty, NP237.ingot(), NP237.ingot(), NP237.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_polonium, 1), ModItems.rod_quad_empty, PO210.ingot(), PO210.ingot(), PO210.nugget(), PO210.nugget(), PO210.nugget(), PO210.nugget(), PO210.nugget(), PO210.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_lead, 1), ModItems.rod_quad_empty, PB.ingot(), PB.ingot(), PB.nugget(), PB.nugget(), PB.nugget(), PB.nugget(), PB.nugget(), PB.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_schrabidium, 1), ModItems.rod_quad_empty, SA326.ingot(), SA326.ingot(), SA326.nugget(), SA326.nugget(), SA326.nugget(), SA326.nugget(), SA326.nugget(), SA326.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_solinium, 1), ModItems.rod_quad_empty, SA327.ingot(), SA327.ingot(), SA327.nugget(), SA327.nugget(), SA327.nugget(), SA327.nugget(), SA327.nugget(), SA327.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_uranium_fuel, 1), ModItems.rod_quad_empty, ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_thorium_fuel, 1), ModItems.rod_quad_empty, ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_plutonium_fuel, 1), ModItems.rod_quad_empty, ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_mox_fuel, 1), ModItems.rod_quad_empty, ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_schrabidium_fuel, 1), ModItems.rod_quad_empty, ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_balefire, 1), ModItems.rod_quad_empty, ModItems.egg_balefire_shard, ModItems.egg_balefire_shard, ModItems.egg_balefire_shard, ModItems.egg_balefire_shard);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_ac227, 1), ModItems.rod_quad_empty, AC227.ingot(), AC227.ingot(), AC227.nugget(), AC227.nugget(), AC227.nugget(), AC227.nugget(), AC227.nugget(), AC227.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_cobalt, 1), ModItems.rod_quad_empty, CO.ingot(), CO.ingot(), CO.nugget(), CO.nugget(), CO.nugget(), CO.nugget(), CO.nugget(), CO.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_co60, 1), ModItems.rod_quad_empty, CO60.ingot(), CO60.ingot(), CO60.nugget(), CO60.nugget(), CO60.nugget(), CO60.nugget(), CO60.nugget(), CO60.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_ra226, 1), ModItems.rod_quad_empty, RA226.ingot(), RA226.ingot(), RA226.nugget(), RA226.nugget(), RA226.nugget(), RA226.nugget(), RA226.nugget(), RA226.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_rgp, 1), ModItems.rod_quad_empty, PURG.ingot(), PURG.ingot(), PURG.nugget(), PURG.nugget(), PURG.nugget(), PURG.nugget(), PURG.nugget(), PURG.nugget());


        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.URANIUM), ModItems.rod_quad_uranium);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U233), ModItems.rod_quad_u233);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U235), ModItems.rod_quad_u235);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.U238), ModItems.rod_quad_u238);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.TH232), ModItems.rod_quad_th232);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM), ModItems.rod_quad_plutonium);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU238), ModItems.rod_quad_pu238);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU239), ModItems.rod_quad_pu239);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU240), ModItems.rod_quad_pu240);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM), ModItems.rod_quad_neptunium);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.POLONIUM), ModItems.rod_quad_polonium);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM), ModItems.rod_quad_schrabidium);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.SOLINIUM), ModItems.rod_quad_solinium);
        addQuadRodBillet(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.rod_quad_uranium_fuel);
        addQuadRodBillet(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.rod_quad_thorium_fuel);
        addQuadRodBillet(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.rod_quad_plutonium_fuel);
        addQuadRodBillet(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), ModItems.rod_quad_mox_fuel);
        addQuadRodBillet(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.rod_quad_schrabidium_fuel);
        addQuadRodBilletUnload(ItemStackUtil.itemStackFrom(ModItems.billet_nuclear_waste), ModItems.rod_quad_waste);

        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.AC227), ModItems.rod_quad_ac227);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.RA226), ModItems.rod_quad_ra226);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.PU_MIX), ModItems.rod_quad_rgp);
        addQuadRodBilletUnload(ModItems.billet.getItemStack(MaterialMineral.CO60), ModItems.rod_quad_co60);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.ZFB_BISMUTH)), ZR.nugget(), ZR.nugget(), ZR.nugget(), U.nugget(), PU241.nugget(), ANY_BISMOID.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.ZFB_PU241)), ZR.nugget(), ZR.nugget(), ZR.nugget(), U235.nugget(), PU240.nugget(), PU241.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.ZFB_AM_MIX)), ZR.nugget(), ZR.nugget(), ZR.nugget(), PU241.nugget(), PU241.nugget(), AMRG.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.ZFB_BISMUTH), 6), ZR.billet(), ZR.billet(), ZR.billet(), U.billet(), PU241.billet(), ANY_BISMOID.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.ZFB_PU241), 6), ZR.billet(), ZR.billet(), ZR.billet(), U235.billet(), PU240.billet(), PU241.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.ZFB_AM_MIX), 6), ZR.billet(), ZR.billet(), ZR.billet(), PU241.billet(), PU241.billet(), AMRG.billet());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.UNOBTAINIUM)), ModItems.nugget.getItemStack(MaterialMineral.RADSPICE), AMRG.nugget(), ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM_LESSER), ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM_GREATER), ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM_GREATER), ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM_GREATER));

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_water, 1), ModItems.rod_empty, Items.WATER_BUCKET);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_dual_water, 1), ModItems.rod_dual_empty, Items.WATER_BUCKET, Items.WATER_BUCKET);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rod_quad_water, 1), ModItems.rod_quad_empty, Items.WATER_BUCKET, Items.WATER_BUCKET, Items.WATER_BUCKET, Items.WATER_BUCKET);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.LEAD), 6), ModItems.rod_lead);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.lithium, 1), ModItems.rod_lithium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.COBALT), 6), ModItems.rod_cobalt);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AUSTRALIUM), 6), ModItems.rod_australium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.WEIDANIUM), 6), ModItems.rod_weidanium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.REIIUM), 6), ModItems.rod_reiium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM), 6), ModItems.rod_unobtainium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.DAFFERGON), 6), ModItems.rod_daffergon);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.VERTICIUM), 6), ModItems.rod_verticium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.EUPHEMIUM), 6), ModItems.rod_euphemium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard, 1), ModItems.rod_balefire);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard, 1), ModItems.rod_balefire_blazing);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.LEAD), 12), ModItems.rod_dual_lead);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.lithium, 2), ModItems.rod_dual_lithium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.COBALT), 12), ModItems.rod_dual_cobalt);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard, 2), ModItems.rod_dual_balefire);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard, 2), ModItems.rod_dual_balefire_blazing);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.LEAD), 24), ModItems.rod_quad_lead);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.lithium, 4), ModItems.rod_quad_lithium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.COBALT), 24), ModItems.rod_quad_cobalt);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard, 4), ModItems.rod_quad_balefire);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard, 4), ModItems.rod_quad_balefire_blazing);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_uranium_hot, 1), ModItems.rod_uranium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_uranium_hot, 2), ModItems.rod_dual_uranium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_uranium_hot, 4), ModItems.rod_quad_uranium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_thorium_hot, 1), ModItems.rod_thorium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_thorium_hot, 2), ModItems.rod_dual_thorium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_thorium_hot, 4), ModItems.rod_quad_thorium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_plutonium_hot, 1), ModItems.rod_plutonium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_plutonium_hot, 2), ModItems.rod_dual_plutonium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_plutonium_hot, 4), ModItems.rod_quad_plutonium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_mox_hot, 1), ModItems.rod_mox_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_mox_hot, 2), ModItems.rod_dual_mox_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_mox_hot, 4), ModItems.rod_quad_mox_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_schrabidium_hot, 1), ModItems.rod_schrabidium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_schrabidium_hot, 2), ModItems.rod_dual_schrabidium_fuel_depleted);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.waste_schrabidium_hot, 4), ModItems.rod_quad_schrabidium_fuel_depleted);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), Items.MELON, Items.MELON, Items.MELON, Items.MELON, Items.MELON, Items.MELON, Items.MELON);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), Items.APPLE, Items.APPLE, Items.APPLE, Items.APPLE, Items.APPLE, Items.APPLE, Items.APPLE, Items.APPLE, Items.APPLE);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), Items.REEDS, Items.REEDS, Items.REEDS, Items.REEDS, Items.REEDS, Items.REEDS, Items.REEDS, Items.REEDS, Items.REEDS);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), Items.CARROT, Items.CARROT, Items.CARROT, Items.CARROT, Items.CARROT, Items.CARROT, Items.CARROT, Items.CARROT, Items.CARROT);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), Items.POTATO, Items.POTATO, Items.POTATO, Items.POTATO, Items.POTATO, Items.POTATO, Items.POTATO, Items.POTATO, Items.POTATO);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), Items.POISONOUS_POTATO, Items.POISONOUS_POTATO, Items.POISONOUS_POTATO, Items.POISONOUS_POTATO, Items.POISONOUS_POTATO, Items.POISONOUS_POTATO, Items.POISONOUS_POTATO, Items.POISONOUS_POTATO, Items.POISONOUS_POTATO);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), Items.BEETROOT, Items.BEETROOT, Items.BEETROOT, Items.BEETROOT, Items.BEETROOT, Items.BEETROOT, Items.BEETROOT);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), "treeSapling", "treeSapling", "treeSapling", "treeSapling", "treeSapling", "treeSapling", "treeSapling");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), "treeLeaves", "treeLeaves", "treeLeaves", "treeLeaves", "treeLeaves");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 8), Blocks.PUMPKIN, Blocks.PUMPKIN, Blocks.PUMPKIN, Blocks.PUMPKIN, Blocks.PUMPKIN, Blocks.PUMPKIN);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 6), KEY_LOG, KEY_LOG, KEY_LOG);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 4), KEY_PLANKS, KEY_PLANKS, KEY_PLANKS, KEY_PLANKS, KEY_PLANKS, KEY_PLANKS, KEY_PLANKS, KEY_PLANKS, KEY_PLANKS);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 8), Blocks.HAY_BLOCK, Blocks.HAY_BLOCK);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 1), Items.WHEAT_SEEDS, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 2), Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 2), Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS, Items.MELON_SEEDS);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.biomass, 2), Items.BEETROOT_SEEDS, Items.BEETROOT_SEEDS, Items.BEETROOT_SEEDS, Items.BEETROOT_SEEDS, Items.BEETROOT_SEEDS, Items.BEETROOT_SEEDS, Items.BEETROOT_SEEDS, Items.BEETROOT_SEEDS, Items.BEETROOT_SEEDS);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_aluminium), AL.ingot(), ModBlocks.steel_scaffold);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_beryllium), BE.ingot(), ModBlocks.steel_scaffold);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_lead), PB.ingot(), ModBlocks.steel_scaffold);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_red_copper), MINGRADE.ingot(), ModBlocks.steel_scaffold);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_steel), STEEL.ingot(), ModBlocks.steel_scaffold);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_titanium), TI.ingot(), ModBlocks.steel_scaffold);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_tungsten), W.ingot(), ModBlocks.steel_scaffold);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.EUPHEMIUM), 1), "#", '#', ModItems.rod_quad_euphemium);
        //Drillgon200: Gone, reduced.
        //addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot_euphemium, 1), new Object[] { "###", "###", "###", '#', ModItems.rod_quad_euphemium });
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.EUPHEMIUM), 1), "###", "###", "###", '#', EUPH.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.EUPHEMIUM), 9), "#", '#', EUPH.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_copper, 1), "WWW", "WIW", "WWW", 'W', ModItems.wire_red_copper, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_advanced_alloy, 1), "WWW", "WIW", "WWW", 'W', ModItems.wire_advanced_alloy, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_gold, 1), "WWW", "WIW", "WWW", 'W', ModItems.wire_gold, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_copper_torus, 2), " C ", "CPC", " C ", 'P', IRON.plate(), 'C', ModItems.coil_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_advanced_torus, 2), " C ", "CPC", " C ", 'P', IRON.plate(), 'C', ModItems.coil_advanced_alloy);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_gold_torus, 2), " C ", "CPC", " C ", 'P', IRON.plate(), 'C', ModItems.coil_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_tungsten, 1), "WWW", "WIW", "WWW", 'W', ModItems.wire_tungsten, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_magnetized_tungsten, 1), "WWW", "WIW", "WWW", 'W', ModItems.wire_magnetized_tungsten, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.tank_steel, 2), "STS", "S S", "STS", 'S', STEEL.plate(), 'T', TI.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.motor, 2), " R ", "ICI", "ITI", 'R', ModItems.wire_red_copper, 'T', ModItems.coil_copper_torus, 'I', IRON.plate(), 'C', ModItems.coil_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.motor, 2), " R ", "ICI", " T ", 'R', ModItems.wire_red_copper, 'T', ModItems.coil_copper_torus, 'I', STEEL.plate(), 'C', ModItems.coil_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.motor_desh, 1), "PCP", "DMD", "PCP", 'P', ANY_PLASTIC.ingot(), 'C', ModItems.coil_gold_torus, 'D', DESH.ingot(), 'M', ModItems.motor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.deuterium_filter, 1), "TST", "SCS", "TST", 'T', ANY_RESISTANTALLOY.ingot(), 'S', S.dust(), 'C', ModItems.catalyst_clay);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.thermo_unit_endo, 1), "EEE", "ETE", "EEE", 'E', Item.getItemFromBlock(Blocks.ICE), 'T', ModItems.thermo_unit_empty);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.thermo_unit_exo, 1), "LLL", "LTL", "LLL", 'L', Items.LAVA_BUCKET, 'T', ModItems.thermo_unit_empty);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cap_aluminium, 1), "PIP", 'P', AL.plate(), 'I', AL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hull_small_steel, 1), "PPP", "   ", "PPP", 'P', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hull_small_aluminium, 1), "PPP", "   ", "PPP", 'P', AL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hull_big_steel, 1), "III", "   ", "III", 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hull_big_aluminium, 1), "III", "   ", "III", 'I', AL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hull_big_titanium, 1), "III", "   ", "III", 'I', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fins_flat, 1), "IP", "PP", "IP", 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fins_small_steel, 1), " PP", "PII", " PP", 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fins_big_steel, 1), " PI", "III", " PI", 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fins_tri_steel, 1), " PI", "IIB", " PI", 'P', STEEL.plate(), 'I', STEEL.ingot(), 'B', STEEL.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fins_quad_titanium, 1), " PP", "III", " PP", 'P', TI.plate(), 'I', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sphere_steel, 1), "PIP", "I I", "PIP", 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pedestal_steel, 1), "P P", "P P", "III", 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.lemon, 1), " D ", "DSD", " D ", 'D', ItemStackUtil.itemStackFrom(Items.DYE, 1, 11), 'S', "stone");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blade_titanium, 2), "TP", "TP", "TT", 'P', TI.plate(), 'T', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turbine_titanium, 1), "BBB", "BSB", "BBB", 'B', ModItems.blade_titanium, 'S', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rotor_steel, 3), "CCC", "SSS", "CCC", 'C', ModItems.coil_gold, 'S', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.generator_steel, 1), "RRR", "CCC", "SSS", 'C', ModItems.coil_gold_torus, 'S', STEEL.ingot(), 'R', ModItems.rotor_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.shimmer_head, 1), "SSS", "DTD", "SSS", 'S', STEEL.ingot(), 'D', DESH.block(), 'T', W.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.shimmer_axe_head, 1), "PII", "PBB", "PII", 'P', STEEL.plate(), 'B', DESH.block(), 'I', W.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.shimmer_handle, 1), "GP", "GP", "GP", 'G', GOLD.plate(), 'P', ANY_PLASTIC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.shimmer_sledge, 1), "H", "G", "G", 'G', ModItems.shimmer_handle, 'H', ModItems.shimmer_head);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.shimmer_axe, 1), "H", "G", "G", 'G', ModItems.shimmer_handle, 'H', ModItems.shimmer_axe_head);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.definitelyfood, 1), "DDD", "SDS", "DDD", 'D', Blocks.DIRT, 'S', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blade_tungsten, 2), "IP", "TP", "TI", 'P', TI.plate(), 'T', TI.ingot(), 'I', W.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turbine_tungsten, 1), "BBB", "BSB", "BBB", 'B', ModItems.blade_tungsten, 'S', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ring_starmetal, 1), " S ", "S S", " S ", 'S', STAR.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wrench, 1), " S ", " IS", "I  ", 'S', STEEL.ingot(), 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wrench_flipped, 1), "S", "D", "W", 'S', Items.IRON_SWORD, 'D', ModItems.ducttape, 'W', ModItems.wrench);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.memespoon, 1), "CGC", "PSP", "IAI", 'C', ModItems.powder_cloud, 'G', ModBlocks.block_thorium, 'P', ModItems.photo_panel, 'S', ModItems.steel_shovel, 'I', ModItems.plate_polymer, 'A', AUSTRALIUM.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.cbt_device, 1), ModItems.bolt_tungsten, ModItems.wrench);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.toothpicks, 3), Items.STICK, Items.STICK, Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ducttape, 6), "FSF", "SPS", "FSF", 'F', Items.STRING, 'S', Items.SLIME_BALL, 'P', Items.PAPER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ball_resin), "DD", "DD", 'D', Blocks.YELLOW_FLOWER);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.missile_taint, 1), ModItems.missile_assembly, new IngredientContainsTag(FluidUtil.getFilledBucket(new FluidStack(ModForgeFluids.mud_fluid, 1000))), ModItems.powder_spark_mix, ModItems.powder_magic);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.missile_micro, 1), ModItems.missile_assembly, ModItems.ducttape, ModItems.ammo_nuke);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.missile_bhole, 1), ModItems.missile_assembly, ModItems.ducttape, ModItems.grenade_black_hole);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.missile_schrabidium, 1), ModItems.missile_assembly, ModItems.ducttape, ModItems.grenade_aschrab);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.missile_schrabidium, 1), ModItems.missile_assembly, ModItems.ducttape, new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.sas3)), ModItems.circuit_targeting_tier4);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.missile_emp, 1), ModItems.missile_assembly, ModItems.ducttape, ModBlocks.emp_bomb, ModItems.circuit_targeting_tier3);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.missile_anti_ballistic, 1), ModItems.missile_generic, ModItems.circuit_targeting_tier3);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_difurnace_ext, 1), " C ", "BGB", "BGB", 'C', CU.plate(), 'B', ModItems.ingot.getItemStack(MaterialMineral.FIREBRICK), 'G', ModBlocks.steel_grate);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_uf6_tank, 1), "WTW", "WTW", "SRS", 'S', IRON.plate(), 'W', ModItems.coil_tungsten, 'T', ModItems.tank_steel, 'W', ModItems.coil_tungsten, 'R', MINGRADE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_puf6_tank, 1), "WTW", "WTW", "SRS", 'S', STEEL.plate(), 'W', ModItems.coil_tungsten, 'T', ModItems.tank_steel, 'W', ModItems.coil_tungsten, 'R', MINGRADE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_nuke_furnace_off, 1), "SSS", "LFL", "CCC", 'S', STEEL.plate(), 'C', ModItems.board_copper, 'L', PB.plate(), 'F', Item.getItemFromBlock(Blocks.FURNACE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_electric_furnace_off, 1), "BBB", "WFW", "RRR", 'B', BE.ingot(), 'R', ModItems.coil_tungsten, 'W', ModItems.board_copper, 'F', Item.getItemFromBlock(Blocks.FURNACE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_arc_furnace_off, 1), "ITI", "PFP", "ITI", 'I', W.ingot(), 'T', ModBlocks.machine_transformer, 'P', ModItems.board_copper, 'F', Blocks.FURNACE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.red_wire_coated, 16), "WRW", "RIR", "WRW", 'W', ModItems.plate_polymer, 'I', MINGRADE.ingot(), 'R', ModItems.wire_red_copper);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.red_wire_sealed, 1), ModBlocks.red_wire_coated, ModBlocks.brick_compound);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.cable_switch, 1), "S", "W", 'S', Blocks.LEVER, 'W', ModBlocks.red_wire_coated);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.cable_detector, 1), "S", "W", 'S', REDSTONE.dust(), 'W', ModBlocks.red_wire_coated);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.cable_diode, 1), " Q ", "CAC", " Q ", 'Q', NETHERQUARTZ.gem(), 'C', ModBlocks.red_cable, 'A', AL.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.red_cable_gauge), ModBlocks.red_cable, ModBlocks.concrete, ModItems.circuit_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.radio_torch_sender, 8), "PMP", "RTR", "PMP", 'P', TI.plate(), 'M', ModItems.magnetron, 'R', Items.COMPARATOR, 'T', ModItems.pellet_rtg);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.radio_torch_receiver, 8), "PMP", "RTR", "PMP", 'P', STEEL.plate(), 'M', ModItems.magnetron, 'R', Items.REPEATER, 'T', ModItems.pellet_rtg);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.red_cable, 16), " W ", "RRR", " W ", 'W', ModItems.plate_polymer, 'R', ModItems.wire_red_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.red_pylon, 4), "CWC", "PWP", " T ", 'C', ModItems.coil_copper_torus, 'W', KEY_PLANKS, 'P', ModItems.plate_polymer, 'T', ModBlocks.red_wire_coated);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_battery_potato, 1), "PCP", "WRW", "PCP", 'P', ItemBattery.getEmptyBattery(ModItems.battery_potato), 'C', CU.ingot(), 'R', Blocks.REDSTONE_BLOCK, 'W', KEY_PLANKS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_coal_off, 1), "STS", "SCS", "SFS", 'S', STEEL.ingot(), 'T', ModItems.tank_steel, 'C', MINGRADE.ingot(), 'F', Blocks.FURNACE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_off, 1), "SPS", "TFT", "SPS", 'S', STEEL.ingot(), 'P', ModItems.board_copper, 'T', ModItems.tank_steel, 'F', Blocks.FURNACE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_electric_off, 1), "SPS", "TFT", "SPS", 'S', DESH.ingot(), 'P', ModItems.board_copper, 'T', ModItems.tank_steel, 'F', ModBlocks.machine_electric_furnace_off);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_rtg_off, 1), "SPS", "TFT", "SPS", 'S', STAR.ingot(), 'P', ModItems.board_copper, 'T', ModItems.tank_steel, 'F', ModBlocks.machine_rtg_furnace_off);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_turbine, 1), "PTP", "BMB", "PTP", 'P', TI.plate(), 'T', ModItems.turbine_titanium, 'B', ModItems.tank_steel, 'M', ModItems.motor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_converter_he_rf, 1), "SSS", "CRB", "SSS", 'S', STEEL.ingot(), 'C', ModItems.coil_copper, 'R', ModItems.coil_copper_torus, 'B', REDSTONE.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_converter_rf_he, 1), "SSS", "BRC", "SSS", 'S', BE.ingot(), 'C', ModItems.coil_copper, 'R', ModItems.coil_copper_torus, 'B', REDSTONE.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crate_iron, 1), "PPP", "I I", "III", 'P', IRON.plate(), 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crate_steel, 1), "PPP", "I I", "III", 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crate_desh, 1), " P ", "PAP", " P ", 'P', ModItems.plate_desh, 'A', ModBlocks.crate_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crate_tungsten, 1), "BPB", "PCP", "BPB", 'B', W.block(), 'P', ModItems.board_copper, 'C', ModBlocks.crate_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.safe, 1), "LAL", "ACA", "LAL", 'L', PB.plate(), 'A', ALLOY.plate(), 'C', ModBlocks.crate_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_waste_drum, 1), "LRL", "BRB", "LRL", 'L', PB.ingot(), 'B', Blocks.IRON_BARS, 'R', ModItems.rod_quad_empty);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_press, 1), "IRI", "IPI", "IBI", 'I', IRON.ingot(), 'R', Blocks.FURNACE, 'B', IRON.block(), 'P', Blocks.PISTON);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_siren, 1), "SIS", "ICI", "SRS", 'S', STEEL.plate(), 'I', ANY_RUBBER.ingot(), 'C', ModItems.circuit_copper, 'R', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_microwave, 1), "III", "SGM", "IDI", 'I', ModItems.plate_polymer, 'S', STEEL.plate(), 'G', KEY_ANYPANE, 'M', ModItems.magnetron, 'D', ModItems.motor);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.muffler, 1), "III", "IWI", "III", 'I', ANY_RUBBER.ingot(), 'W', Blocks.WOOL);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.factory_titanium_hull, 1), "PIP", "I I", "PIP", 'P', TI.plate(), 'I', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.factory_titanium_furnace, 1), "HMH", "MFM", "HMH", 'H', Item.getItemFromBlock(ModBlocks.factory_titanium_hull), 'M', ModItems.motor, 'F', Item.getItemFromBlock(Blocks.FURNACE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.factory_titanium_conductor, 1), "SWS", "FFF", "SWS", 'S', TI.ingot(), 'W', Item.getItemFromBlock(ModBlocks.red_wire_coated), 'F', ModItems.fuse);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.factory_titanium_core, 1), "HPH", "PCP", "HPH", 'H', Item.getItemFromBlock(ModBlocks.factory_titanium_hull), 'C', ModItems.circuit_aluminium, 'P', Item.getItemFromBlock(Blocks.PISTON));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.factory_core_titanium, 1), "BRB", "RHR", "BRB", 'B', ItemBattery.getEmptyBattery(ModItems.battery_generic), 'R', Item.getItemFromBlock(Blocks.REDSTONE_BLOCK), 'H', Item.getItemFromBlock(ModBlocks.factory_titanium_hull));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.factory_core_advanced, 1), "BLB", "SHS", "BLB", 'B', ItemBattery.getEmptyBattery(ModItems.battery_advanced), 'S', S.block(), 'L', PB.block(), 'H', Item.getItemFromBlock(ModBlocks.factory_advanced_hull));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.factory_core_advanced, 1), "BSB", "LHL", "BSB", 'B', ItemBattery.getEmptyBattery(ModItems.battery_advanced), 'S', S.block(), 'L', PB.block(), 'H', Item.getItemFromBlock(ModBlocks.factory_advanced_hull));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.arc_electrode, 1), "C", "T", "C", 'C', COAL.dust(), 'T', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.arc_electrode_desh, 1), "C", "T", "C", 'C', DESH.dust(), 'T', ModItems.arc_electrode);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.detonator, 1), " W", "SC", "CE", 'S', STEEL.plate(), 'W', ModItems.wire_red_copper, 'C', ModItems.circuit_red_copper, 'E', STEEL.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.detonator_multi, 1), ModItems.detonator, ModItems.circuit_targeting_tier3);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.detonator_laser, 1), "RRD", "PIC", "  P", 'P', STEEL.plate(), 'R', Items.REDSTONE, 'C', ModItems.circuit_targeting_tier3, 'D', DIAMOND.gem(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.detonator_laser, 1), "RRD", "PIC", "  P", 'P', STEEL.plate(), 'R', Items.REDSTONE, 'C', ModItems.circuit_targeting_tier3, 'D', EMERALD.gem(), 'I', STEEL.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.detonator_deadman, 1), ModItems.detonator, ModItems.defuser, ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.detonator_de, 1), "T", "D", "T", 'T', Blocks.TNT, 'D', ModItems.detonator_deadman);

        addRecipeAuto(ItemBombCaller.getStack(EnumCallerType.CARPET), "TTT", "TRT", "TTT", 'T', Blocks.TNT, 'R', ModItems.detonator_laser);
        addRecipeAuto(ItemBombCaller.getStack(EnumCallerType.NAPALM), "TTT", "TRT", "TTT", 'T', ModItems.grenade_gascan, 'R', ModItems.detonator_laser);
        addRecipeAuto(ItemBombCaller.getStack(EnumCallerType.POISON), "TTT", "TRT", "TTT", 'T', ModItems.pellet_gas, 'R', ModItems.detonator_laser);
        addRecipeAuto(ItemBombCaller.getStack(EnumCallerType.ORANGE), "TRT", 'T', ModItems.grenade_cloud, 'R', ModItems.detonator_laser);
        addRecipeAuto(ItemBombCaller.getStack(EnumCallerType.ATOMIC), "TRT", 'T', ModItems.ammo_nuke, 'R', ModItems.detonator_laser);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.crystal_xen, 1), "EEE", "EIE", "EEE", 'E', ModItems.powder_power, 'I', EUPH.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.screwdriver, 1), "  I", " I ", "S  ", 'S', STEEL.ingot(), 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.screwdriver_desh, 1), "  I", " I ", "S  ", 'S', ANY_PLASTIC.ingot(), 'I', DESH.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.overfuse, 1), ModItems.screwdriver, NP237.dust(), I.dust(), TH232.dust(), AT.dust(), ND.dust(), ModItems.board_copper, ModItems.singularity_spark, CS.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.overfuse, 1), ModItems.screwdriver, SR.dust(), BR.dust(), CO.dust(), TS.dust(), NB.dust(), ModItems.board_copper, ModItems.singularity_spark, CE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.crystal_energy, 1), "EEE", "EGE", "EEE", 'E', ModItems.powder_power, 'G', Items.GLOWSTONE_DUST);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_coolant, 1), "CRC", "RBR", "CRC", 'C', ModItems.powder_ice, 'R', ModItems.rod_quad_coolant, 'B', KNO.block());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_aluminum, 1), " P ", "PIP", " P ", 'P', AL.plate(), 'I', AL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_gold, 1), " P ", "PIP", " P ", 'P', GOLD.plate(), 'I', GOLD.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_iron, 1), " P ", "PIP", " P ", 'P', IRON.plate(), 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_steel, 1), " P ", "PIP", " P ", 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_titanium, 1), " P ", "PIP", " P ", 'P', TI.plate(), 'I', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_advanced_alloy, 1), " P ", "PIP", " P ", 'P', ALLOY.plate(), 'I', ALLOY.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_combine_steel, 1), " P ", "PIP", " P ", 'P', CMB.plate(), 'I', CMB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_schrabidium, 1), " P ", "PIP", " P ", 'P', SA326.plate(), 'I', SA326.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_desh, 1), "NPN", "PBP", "NPN", 'N', SA326.nugget(), 'P', ModItems.plate_desh, 'B', ModItems.blades_combine_steel);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_aluminum, 1), "PIP", 'P', AL.plate(), 'I', ItemStackUtil.itemStackFrom(ModItems.blades_aluminum, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_gold, 1), "PIP", 'P', GOLD.plate(), 'I', ItemStackUtil.itemStackFrom(ModItems.blades_gold, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_iron, 1), "PIP", 'P', IRON.plate(), 'I', ItemStackUtil.itemStackFrom(ModItems.blades_iron, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_steel, 1), "PIP", 'P', STEEL.plate(), 'I', ItemStackUtil.itemStackFrom(ModItems.blades_steel, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_titanium, 1), "PIP", 'P', TI.plate(), 'I', ItemStackUtil.itemStackFrom(ModItems.blades_titanium, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_advanced_alloy, 1), "PIP", 'P', ALLOY.plate(), 'I', ItemStackUtil.itemStackFrom(ModItems.blades_advanced_alloy, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_combine_steel, 1), "PIP", 'P', CMB.plate(), 'I', ItemStackUtil.itemStackFrom(ModItems.blades_combine_steel, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.blades_schrabidium, 1), "PIP", 'P', SA326.plate(), 'I', ItemStackUtil.itemStackFrom(ModItems.blades_schrabidium, 1, OreDictionary.WILDCARD_VALUE));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_nano, 1), "QPQ", "ACA", "QPQ", 'Q', ModBlocks.glass_quartz, 'P', GRAPHITE.ingot(), 'A', ANY_PLASTIC.dust(), 'C', ModItems.filter_coal);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_pentacene, 1), "QSQ", "AEA", "QSQ", 'Q', ModBlocks.glass_quartz, 'A', ALLOY.dust(), 'S', CE.ingot(), 'E', new IngredientContainsTag(ItemFluidTank.getFullTank(ModForgeFluids.biogas)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_co2, 1), "QDQ", "NCN", "QDQ", 'Q', ModBlocks.glass_quartz, 'D', DESH.ingot(), 'N', NB.ingot(), 'C', ModItems.part_carbon);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_bismuth, 1), "QUQ", "BCB", "QTQ", 'Q', ModBlocks.glass_quartz, 'U', U.ingot(), 'T', TH232.ingot(), 'B', ANY_BISMOID.nugget(), 'C', ModItems.crystal_rare);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_cmb, 1), "QBQ", "CSC", "QBQ", 'Q', ModBlocks.glass_quartz, 'B', STAR.ingot(), 'C', PB209.nugget(), 'S', XE135.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_dem, 1), "QDQ", "SBS", "QDQ", 'Q', ModBlocks.glass_quartz, 'D', CMB.ingot(), 'B', ModItems.demon_core_open, 'S', GH336.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_bale, 1), "QDQ", "SBS", "QDQ", 'Q', ModBlocks.glass_trinitite, 'D', ModItems.ingot.getItemStack(MaterialMineral.VERTICIUM), 'B', ModItems.rbmk_pellet_balefire, 'S', ModItems.nugget.getItemStack(MaterialMineral.RADSPICE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.laser_crystal_digamma, 1), "QUQ", "UEU", "QUQ", 'Q', ModBlocks.glass_ash, 'U', ModItems.undefined, 'E', ModItems.ingot.getItemStack(MaterialMineral.ELECTRONIUM));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_stone_flat, 1), " R ", "III", "SSS", 'R', REDSTONE.dust(), 'I', "ingotBrick", 'S', "stone");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_iron_flat, 1), " R ", "III", "SSS", 'R', REDSTONE.dust(), 'I', "ingotBrick", 'S', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_steel_flat, 1), " R ", "III", "SSS", 'R', REDSTONE.dust(), 'I', "ingotBrick", 'S', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_titanium_flat, 1), " R ", "III", "SSS", 'R', REDSTONE.dust(), 'I', "ingotBrick", 'S', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_obsidian_flat, 1), " R ", "III", "SSS", 'R', REDSTONE.dust(), 'I', "ingotBrick", 'S', Blocks.OBSIDIAN);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_schrabidium_flat, 1), " R ", "III", "SSS", 'R', REDSTONE.dust(), 'I', "ingotBrick", 'S', SA326.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_desh_flat, 1), "RIR", "ISI", "RIR", 'S', ModItems.stamp_schrabidium_flat, 'I', ModItems.plate_desh, 'R', ModBlocks.cmb_brick_reinforced);


        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mechanism_revolver_1, 1), " II", "ICA", "IKW", 'I', IRON.plate(), 'C', CU.ingot(), 'A', AL.ingot(), 'K', ModItems.wire_copper, 'W', ModItems.wire_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mechanism_revolver_2, 1), " II", "ICA", "IKW", 'I', ALLOY.plate(), 'C', DURA.ingot(), 'A', W.ingot(), 'K', ModItems.bolt_dura_steel, 'W', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mechanism_rifle_1, 1), "ICI", "CMA", "IAM", 'I', IRON.plate(), 'C', CU.ingot(), 'A', AL.ingot(), 'M', ModItems.mechanism_revolver_1);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mechanism_rifle_2, 1), "ICI", "CMA", "IAM", 'I', ALLOY.plate(), 'C', DURA.ingot(), 'A', W.ingot(), 'M', ModItems.mechanism_revolver_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mechanism_launcher_1, 1), "TTT", "SSS", "BBI", 'T', TI.plate(), 'S', STEEL.ingot(), 'B', ModItems.bolt_tungsten, 'I', MINGRADE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mechanism_launcher_2, 1), "TTT", "SSS", "BBI", 'T', ALLOY.plate(), 'S', ANY_PLASTIC.ingot(), 'B', ModItems.bolt_dura_steel, 'I', DESH.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fwatz_cooler, 1), "IPI", "IPI", "IPI", 'I', SBD.ingot(), 'P', ModItems.thermo_unit_endo);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fwatz_tank, 1), "CGC", "GGG", "CGC", 'C', CMB.plate(), 'G', KEY_ANYPANE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fwatz_scaffold, 1), "IPI", "P P", "IPI", 'I', W.ingot(), 'P', OreDictManager.getReflector());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fwatz_conductor, 1), "IPI", "PFP", "IPI", 'I', TI.plate(), 'P', ModItems.coil_magnetized_tungsten, 'F', ModBlocks.hadron_coil_neodymium);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.reinforced_stone, 4), "FBF", "BFB", "FBF", 'F', Blocks.COBBLESTONE, 'B', Blocks.STONE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_light, 4), "FBF", "BFB", "FBF", 'F', "fenceWood", 'B', Blocks.BRICK_BLOCK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_asbestos, 2), " A ", "ABA", " A ", 'B', ModBlocks.brick_light, 'A', ASBESTOS.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete, 4), "CC", "CC", 'C', ModBlocks.concrete_smooth);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_white, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeWhite");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_white);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_orange, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeOrange");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_orange);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_black, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeBlack");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_black);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_blue, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeBlue");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_blue);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_brown, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeBrown");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_brown);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_cyan, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeCyan");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_cyan);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_gray, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeGray");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_gray);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_green, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeGreen");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_green);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_light_blue, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeLightBlue");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_light_blue);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_lime, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeLime");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_lime);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_magenta, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeMagenta");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_magenta);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_pink, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyePink");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_pink);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_purple, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyePurple");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_purple);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_silver, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeLightGray");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_silver);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_red, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeRed");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_red);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_yellow, 8), ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, ModBlocks.concrete_smooth, "dyeYellow");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth), ModBlocks.concrete_yellow);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_hazard, 6), "CCC", "1 2", "CCC", 'C', ModBlocks.concrete_smooth, '1', "dyeYellow", '2', "dyeBlack");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_hazard, 6), "CCC", "2 1", "CCC", 'C', ModBlocks.concrete_smooth, '1', "dyeYellow", '2', "dyeBlack");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth, 6), ModBlocks.concrete_hazard, ModBlocks.concrete_hazard, ModBlocks.concrete_hazard, ModBlocks.concrete_hazard, ModBlocks.concrete_hazard, ModBlocks.concrete_hazard);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.concrete_pillar, 8), "CBC", "CBC", "CBC", 'C', ModBlocks.concrete_smooth, 'B', Blocks.IRON_BARS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_concrete, 4), " C ", "CBC", " C ", 'C', ModBlocks.concrete_smooth, 'B', Items.CLAY_BALL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_concrete, 4), " C ", "CBC", " C ", 'C', ModBlocks.concrete, 'B', Items.CLAY_BALL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_concrete_mossy, 8), "CCC", "CVC", "CCC", 'C', ModBlocks.brick_concrete, 'V', Blocks.VINE);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_concrete_cracked, 6), ModBlocks.brick_concrete, ModBlocks.brick_concrete, ModBlocks.brick_concrete, ModBlocks.brick_concrete);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_concrete_broken, 6), ModBlocks.brick_concrete_cracked, ModBlocks.brick_concrete_cracked, ModBlocks.brick_concrete_cracked, ModBlocks.brick_concrete_cracked);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_concrete, 4), ModBlocks.brick_concrete_cracked, ModBlocks.brick_concrete_cracked, ModBlocks.brick_concrete_cracked, ModBlocks.brick_concrete_cracked, ModBlocks.brick_concrete_cracked, ModBlocks.brick_concrete_cracked);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_concrete_cracked, 4), ModBlocks.brick_concrete_broken, ModBlocks.brick_concrete_broken, ModBlocks.brick_concrete_broken, ModBlocks.brick_concrete_broken, ModBlocks.brick_concrete_broken, ModBlocks.brick_concrete_broken);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_obsidian, 4), "FBF", "BFB", "FBF", 'F', Blocks.IRON_BARS, 'B', Blocks.OBSIDIAN);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.meteor_polished, 4), "CC", "CC", 'C', ModBlocks.block_meteor_broken);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.meteor_pillar, 2), "C", "C", 'C', ModBlocks.meteor_polished);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.meteor_brick, 4), "CC", "CC", 'C', ModBlocks.meteor_polished);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.meteor_brick_mossy, 8), "CCC", "CVC", "CCC", 'C', ModBlocks.meteor_brick, 'V', Blocks.VINE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.meteor_brick_cracked, 6), " C ", "C C", " C ", 'C', ModBlocks.meteor_brick);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.meteor_battery, 1), "MSM", "MWM", "MSM", 'M', ModBlocks.meteor_polished, 'S', STAR.block(), 'W', ModItems.wire_schrabidium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.tile_lab, 4), "CBC", "CBC", "CBC", 'C', Items.BRICK, 'B', ASBESTOS.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.tile_lab_cracked, 6), " C ", "C C", " C ", 'C', ModBlocks.tile_lab);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.tile_lab_broken, 6), " C ", "C C", " C ", 'C', ModBlocks.tile_lab_cracked);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ducrete_smooth, 4), "DD", "DD", 'D', ModBlocks.ducrete);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ducrete_brick, 4), "CDC", "DLD", "CDC", 'D', ModBlocks.ducrete_smooth, 'C', Items.CLAY_BALL, 'L', PB.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ducrete_brick, 4), "CDC", "DLD", "CDC", 'D', ModBlocks.ducrete, 'C', Items.CLAY_BALL, 'L', PB.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ducrete_reinforced, 4), "DSD", "SUS", "DSD", 'D', ModBlocks.ducrete_brick, 'S', STEEL.plate(), 'U', U238.billet());

        addSlabStair(ModBlocks.reinforced_brick_slab, ModBlocks.reinforced_brick_stairs, ModBlocks.reinforced_brick);
        addSlabStair(ModBlocks.reinforced_sand_slab, ModBlocks.reinforced_sand_stairs, ModBlocks.reinforced_sand);
        addSlabStair(ModBlocks.reinforced_stone_slab, ModBlocks.reinforced_stone_stairs, ModBlocks.reinforced_stone);
        addSlabStair(ModBlocks.brick_concrete_slab, ModBlocks.brick_concrete_stairs, ModBlocks.brick_concrete);
        addSlabStair(ModBlocks.brick_concrete_mossy_slab, ModBlocks.brick_concrete_mossy_stairs, ModBlocks.brick_concrete_mossy);
        addSlabStair(ModBlocks.brick_concrete_cracked_slab, ModBlocks.brick_concrete_cracked_stairs, ModBlocks.brick_concrete_cracked);
        addSlabStair(ModBlocks.brick_concrete_broken_slab, ModBlocks.brick_concrete_broken_stairs, ModBlocks.brick_concrete_broken);
        addSlabStair(ModBlocks.brick_compound_slab, ModBlocks.brick_compound_stairs, ModBlocks.brick_compound);
        addSlabStair(ModBlocks.brick_asbestos_slab, ModBlocks.brick_asbestos_stairs, ModBlocks.brick_asbestos);
        addSlabStair(ModBlocks.brick_obsidian_slab, ModBlocks.brick_obsidian_stairs, ModBlocks.brick_obsidian);
        addSlabStair(ModBlocks.cmb_brick_reinforced_slab, ModBlocks.cmb_brick_reinforced_stairs, ModBlocks.cmb_brick_reinforced);
        addSlabStair(ModBlocks.concrete_slab, ModBlocks.concrete_stairs, ModBlocks.concrete);
        addSlabStair(ModBlocks.concrete_smooth_slab, ModBlocks.concrete_smooth_stairs, ModBlocks.concrete_smooth);
        addSlabStair(ModBlocks.concrete_white_slab, ModBlocks.concrete_white_stairs, ModBlocks.concrete_white);
        addSlabStair(ModBlocks.concrete_orange_slab, ModBlocks.concrete_orange_stairs, ModBlocks.concrete_orange);
        addSlabStair(ModBlocks.concrete_magenta_slab, ModBlocks.concrete_magenta_stairs, ModBlocks.concrete_magenta);
        addSlabStair(ModBlocks.concrete_light_blue_slab, ModBlocks.concrete_light_blue_stairs, ModBlocks.concrete_light_blue);
        addSlabStair(ModBlocks.concrete_yellow_slab, ModBlocks.concrete_yellow_stairs, ModBlocks.concrete_yellow);
        addSlabStair(ModBlocks.concrete_lime_slab, ModBlocks.concrete_lime_stairs, ModBlocks.concrete_lime);
        addSlabStair(ModBlocks.concrete_pink_slab, ModBlocks.concrete_pink_stairs, ModBlocks.concrete_pink);
        addSlabStair(ModBlocks.concrete_gray_slab, ModBlocks.concrete_gray_stairs, ModBlocks.concrete_gray);
        addSlabStair(ModBlocks.concrete_silver_slab, ModBlocks.concrete_silver_stairs, ModBlocks.concrete_silver);
        addSlabStair(ModBlocks.concrete_cyan_slab, ModBlocks.concrete_cyan_stairs, ModBlocks.concrete_cyan);
        addSlabStair(ModBlocks.concrete_purple_slab, ModBlocks.concrete_purple_stairs, ModBlocks.concrete_purple);
        addSlabStair(ModBlocks.concrete_blue_slab, ModBlocks.concrete_blue_stairs, ModBlocks.concrete_blue);
        addSlabStair(ModBlocks.concrete_brown_slab, ModBlocks.concrete_brown_stairs, ModBlocks.concrete_brown);
        addSlabStair(ModBlocks.concrete_green_slab, ModBlocks.concrete_green_stairs, ModBlocks.concrete_green);
        addSlabStair(ModBlocks.concrete_red_slab, ModBlocks.concrete_red_stairs, ModBlocks.concrete_red);
        addSlabStair(ModBlocks.concrete_black_slab, ModBlocks.concrete_black_stairs, ModBlocks.concrete_black);
        addSlabStair(ModBlocks.concrete_asbestos_slab, ModBlocks.concrete_asbestos_stairs, ModBlocks.concrete_asbestos);
        addSlabStair(ModBlocks.ducrete_smooth_slab, ModBlocks.ducrete_smooth_stairs, ModBlocks.ducrete_smooth);
        addSlabStair(ModBlocks.ducrete_slab, ModBlocks.ducrete_stairs, ModBlocks.ducrete);
        addSlabStair(ModBlocks.ducrete_brick_slab, ModBlocks.ducrete_brick_stairs, ModBlocks.ducrete_brick);
        addSlabStair(ModBlocks.ducrete_reinforced_slab, ModBlocks.ducrete_reinforced_stairs, ModBlocks.ducrete_reinforced);
        addSlabStair(ModBlocks.tile_lab_slab, ModBlocks.tile_lab_stairs, ModBlocks.tile_lab);
        addSlabStair(ModBlocks.tile_lab_cracked_slab, ModBlocks.tile_lab_cracked_stairs, ModBlocks.tile_lab_cracked);
        addSlabStair(ModBlocks.tile_lab_broken_slab, ModBlocks.tile_lab_broken_stairs, ModBlocks.tile_lab_broken);

        addSlabStair(ModBlocks.pink_slab, ModBlocks.pink_stairs, ModBlocks.pink_planks);


        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.reinforced_brick, 8), "FBF", "BFB", "FBF", 'F', Blocks.IRON_BARS, 'B', ModBlocks.brick_concrete);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.brick_compound, 8), "FBF", "BFB", "FBF", 'F', ModItems.bolt_tungsten, 'B', ModBlocks.reinforced_brick);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.reinforced_glass, 8), "FBF", "BFB", "FBF", 'F', Blocks.IRON_BARS, 'B', "blockGlassColorless");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.reinforced_light, 1), "FFF", "FBF", "FFF", 'F', Blocks.IRON_BARS, 'B', "glowstone");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.reinforced_lamp_off, 1), "FFF", "FBF", "FFF", 'F', Blocks.IRON_BARS, 'B', Blocks.REDSTONE_LAMP);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.reinforced_sand, 8), "FBF", "BFB", "FBF", 'F', Blocks.IRON_BARS, 'B', Blocks.SANDSTONE);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barbed_wire, 16), "AIA", "I I", "AIA", 'A', ModItems.wire_aluminium, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barbed_wire_fire, 8), "BBB", "BIB", "BBB", 'B', ModBlocks.barbed_wire, 'I', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barbed_wire_poison, 8), "BBB", "BIB", "BBB", 'B', ModBlocks.barbed_wire, 'I', ModItems.powder_poison);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barbed_wire_acid, 8), "BBB", "BIB", "BBB", 'B', ModBlocks.barbed_wire, 'I', new IngredientContainsTag(ItemFluidTank.getFullTank(ModForgeFluids.acid)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barbed_wire_wither, 8), "BBB", "BIB", "BBB", 'B', ModBlocks.barbed_wire, 'I', ItemStackUtil.itemStackFrom(Items.SKULL, 1, 1));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barbed_wire_ultradeath, 4), "BCB", "CIC", "BCB", 'B', ModBlocks.barbed_wire, 'C', ModItems.powder_cloud, 'I', ModItems.nuclear_waste);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.tape_recorder, 4), "TST", "SSS", 'T', W.ingot(), 'S', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.steel_poles, 16), "S S", "SSS", "S S", 'S', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.pole_top, 1), "T T", "TRT", "BBB", 'T', W.ingot(), 'B', BE.ingot(), 'R', MINGRADE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.pole_satellite_receiver, 1), "SS ", "SCR", "SS ", 'S', STEEL.ingot(), 'C', ModItems.circuit_red_copper, 'R', ModItems.wire_red_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.steel_beam, 8), "S", "S", "S", 'S', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.steel_wall, 4), "SSS", "SSS", 'S', STEEL.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.steel_corner), Item.getItemFromBlock(ModBlocks.steel_wall), Item.getItemFromBlock(ModBlocks.steel_wall));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.steel_roof, 2), "SSS", 'S', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.steel_scaffold, 8), "SSS", " S ", "SSS", 'S', STEEL.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_rpg, 1), "SSW", " MW", 'S', ModItems.hull_small_steel, 'W', IRON.plate(), 'M', ModItems.mechanism_launcher_1);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_panzerschreck, 1), "SSS", " MW", 'S', ModItems.hull_small_steel, 'W', CU.plate(), 'M', ModItems.mechanism_launcher_1);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_karl, 1), "SSW", " MW", 'S', ModItems.hull_small_steel, 'W', ALLOY.plate(), 'M', ModItems.mechanism_launcher_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_quadro, 1), "SSS", "SSS", "CM ", 'S', ModItems.hull_small_steel, 'C', ModItems.circuit_targeting_tier3, 'M', ModItems.mechanism_launcher_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_hk69, 1), "SSI", " MB", 'S', ModItems.hull_small_steel, 'I', IRON.ingot(), 'M', ModItems.mechanism_launcher_1, 'B', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_stinger, 1), "SSW", "CMW", 'S', STEEL.plate(), 'W', TI.plate(), 'C', ModItems.circuit_red_copper, 'M', ModItems.mechanism_launcher_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_stinger_ammo, 4), "SS ", "STI", " IR", 'S', STEEL.plate(), 'T', Item.getItemFromBlock(Blocks.TNT), 'I', AL.plate(), 'R', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver, 1), "SSM", " RW", 'S', STEEL.plate(), 'W', KEY_PLANKS, 'R', ModItems.wire_aluminium, 'M', ModItems.mechanism_revolver_1);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_saturnite, 1), "SSM", " RW", 'S', BIGMT.plate(), 'W', KEY_PLANKS, 'R', ModItems.wire_tungsten, 'M', ModItems.mechanism_revolver_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_iron, 1), "SSM", " RW", 'S', IRON.plate(), 'W', KEY_PLANKS, 'R', ModItems.wire_aluminium, 'M', ModItems.mechanism_revolver_1);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_gold, 1), "SSM", " RW", 'S', GOLD.plate(), 'W', GOLD.ingot(), 'R', ModItems.wire_gold, 'M', ModItems.mechanism_revolver_1);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_lead, 1), "SSM", " RW", 'S', PB.plate(), 'W', W.ingot(), 'R', ModItems.wire_tungsten, 'M', ModItems.mechanism_revolver_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_schrabidium, 1), "SSM", " RW", 'S', SA326.block(), 'W', W.ingot(), 'R', ModItems.wire_schrabidium, 'M', ModItems.mechanism_special);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_deagle, 1), "PPM", " BI", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'I', ANY_PLASTIC.ingot(), 'M', ModItems.mechanism_rifle_1);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_cursed, 1), "TTM", "SRI", 'S', STEEL.plate(), 'I', STEEL.ingot(), 'R', ModItems.wire_red_copper, 'T', TI.plate(), 'M', ModItems.mechanism_revolver_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_nightmare, 1), "SEM", " RW", 'S', STEEL.plate(), 'W', KEY_PLANKS, 'R', ModItems.wire_aluminium, 'E', ModItems.powder_power, 'M', ModItems.mechanism_revolver_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_nightmare2, 1), "SSM", "RRW", 'S', OreDictManager.getReflector(), 'W', W.ingot(), 'R', ModItems.wire_gold, 'M', ModItems.mechanism_special);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_fatman, 1), "SSI", "IIM", "WPH", 'S', STEEL.plate(), 'I', STEEL.ingot(), 'W', ModItems.wire_aluminium, 'H', ModItems.hull_small_steel, 'P', Item.getItemFromBlock(Blocks.PISTON), 'M', ModItems.mechanism_launcher_2);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_mirv, 1), "LLL", "WFW", "SSS", 'S', STEEL.plate(), 'L', PB.plate(), 'W', ModItems.wire_gold, 'F', ModItems.gun_fatman);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_proto, 1), "LLL", "WFW", "SSS", 'S', ANY_RUBBER.ingot(), 'L', ModItems.plate_desh, 'W', ModItems.wire_tungsten, 'F', ModItems.gun_fatman);
        //addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_bf, 1), new Object[] { "LLL", "WFW", "SSS", 'S', ModItems.plate_paa, 'L', OreDictManager.getReflector(), 'W', ModItems.wire_advanced_alloy, 'F', ModItems.gun_mirv });
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_bf_ammo, 1), " S ", "EBE", " S ", 'S', ModItems.hull_small_steel, 'E', ModItems.powder_power, 'B', ModItems.egg_balefire_shard);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_mp40, 1), "IIM", " SW", " S ", 'S', STEEL.plate(), 'I', STEEL.ingot(), 'W', KEY_PLANKS, 'M', ModItems.mechanism_rifle_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_thompson, 1), "IIM", " SW", " S ", 'S', IRON.plate(), 'I', STEEL.plate(), 'W', KEY_PLANKS, 'M', ModItems.mechanism_rifle_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_flechette, 1), "PPM", "TIS", "G  ", 'P', STEEL.plate(), 'M', ModItems.mechanism_rifle_2, 'T', ModItems.hull_small_steel, 'I', STEEL.ingot(), 'S', ANY_PLASTIC.ingot(), 'G', ModItems.mechanism_launcher_1);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_uboinik, 1), "IIM", "SPW", 'P', STEEL.plate(), 'I', STEEL.ingot(), 'W', KEY_PLANKS, 'S', Items.STICK, 'M', ModItems.mechanism_revolver_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_xvl1456, 1), "PBB", "ACC", "PRY", 'P', STEEL.plate(), 'R', ModItems.redcoil_capacitor, 'A', ModItems.coil_advanced_alloy, 'B', ModItems.battery_generic, 'C', ModItems.coil_advanced_torus, 'Y', ModItems.mechanism_special);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_xvl1456_ammo, 64), "SSS", "SRS", "SSS", 'S', STEEL.plate(), 'R', ModItems.rod_quad_uranium_fuel_depleted);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_xvl1456_ammo, 32), " S ", "SRS", " S ", 'S', STEEL.plate(), 'R', ModItems.rod_dual_uranium_fuel_depleted);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_xvl1456_ammo, 16), " S ", " R ", " S ", 'S', STEEL.plate(), 'R', ModItems.rod_uranium_fuel_depleted);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_xvl1456_ammo, 16), "SRS", 'S', STEEL.plate(), 'R', ModItems.rod_uranium_fuel_depleted);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_xvl1456_ammo, 16), " S ", " R ", " S ", 'S', STEEL.plate(), 'R', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_xvl1456_ammo, 16), "SRS", 'S', STEEL.plate(), 'R', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_osipr, 1), "CCT", "WWI", "MCC", 'C', CMB.plate(), 'T', W.ingot(), 'W', ModItems.wire_magnetized_tungsten, 'I', ModItems.mechanism_rifle_2, 'M', ModItems.coil_magnetized_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_immolator, 1), "WCC", "PMT", "WAA", 'W', ModItems.wire_gold, 'C', CU.plate(), 'P', ALLOY.plate(), 'M', ModItems.mechanism_launcher_1, 'T', ModItems.tank_steel, 'A', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_immolator_ammo, 16), "SPS", "PCP", "SPS", 'S', STEEL.plate(), 'C', COAL.dust(), 'P', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_immolator_ammo, 16), " F ", "SFS", " F ", 'S', STEEL.plate(), 'F', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_immolator_ammo, 16), " F ", "SFS", " F ", 'S', STEEL.plate(), 'F', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.kerosene)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_immolator_ammo, 24), " F ", "SFS", " F ", 'S', STEEL.plate(), 'F', ModItems.canister_napalm);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_immolator_ammo, 32), " F ", "SFS", " F ", 'S', STEEL.plate(), 'F', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.nitan)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_cryolator, 1), "SSS", "IWL", "LMI", 'S', STEEL.plate(), 'I', IRON.plate(), 'L', Items.LEATHER, 'M', ModItems.mechanism_launcher_1, 'W', ModItems.wire_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_cryolator_ammo, 16), "SPS", "PCP", "SPS", 'S', STEEL.plate(), 'C', KNO.dust(), 'P', Items.SNOWBALL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_cryolator_ammo, 16), " F ", "SFS", " F ", 'S', STEEL.plate(), 'F', ModItems.powder_ice);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_mp, 1), "EEE", "SSM", "III", 'E', EUPH.ingot(), 'S', STEEL.plate(), 'I', STEEL.ingot(), 'M', ModItems.mechanism_rifle_2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_emp, 1), "CPG", "CMF", "CPI", 'C', ModItems.coil_copper, 'P', PB.plate(), 'G', ModItems.circuit_gold, 'M', ModItems.magnetron, 'I', W.ingot(), 'F', ModItems.mechanism_special);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_emp_ammo, 8), "IGI", "IPI", "IPI", 'G', GOLD.plate(), 'I', IRON.plate(), 'P', ModItems.powder_power);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_jack, 1), "WW ", "TSD", " TT", 'W', WEIDANIUM.ingot(), 'T', ModItems.toothpicks, 'S', ModItems.gun_uboinik, 'D', ModItems.ducttape);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_jack_ammo, 1), ModItems.ammo_12gauge, ModItems.ammo_12gauge, ModItems.ammo_12gauge, ModItems.ammo_12gauge);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_euthanasia, 1), "TDT", "AAS", " T ", 'A', AUSTRALIUM.ingot(), 'T', ModItems.toothpicks, 'S', ModItems.gun_mp40, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_euthanasia_ammo, 12), "P", "S", "N", 'P', ModItems.powder_poison, 'N', KNO.dust(), 'S', ModItems.syringe_metal_empty);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_spark, 1), "TTD", "AAS", "  T", 'A', DAFFERGON.ingot(), 'T', ModItems.toothpicks, 'S', ModItems.gun_rpg, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_spark_ammo, 4), "PCP", "DDD", "PCP", 'P', PB.plate(), 'C', ModItems.coil_gold, 'D', ModItems.powder_power);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_skystinger, 1), "TTT", "AAS", " D ", 'A', UNOBTAINIUM.ingot(), 'T', ModItems.toothpicks, 'S', ModItems.gun_stinger, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_hp, 1), "TDT", "ASA", " T ", 'A', REIIUM.ingot(), 'T', ModItems.toothpicks, 'S', ModItems.gun_xvl1456, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_hp_ammo, 8), " R ", "BSK", " Y ", 'S', STEEL.plate(), 'K', ItemStackUtil.itemStackFrom(Items.DYE, 1, 0), 'R', ItemStackUtil.itemStackFrom(Items.DYE, 1, 1), 'B', ItemStackUtil.itemStackFrom(Items.DYE, 1, 4), 'Y', ItemStackUtil.itemStackFrom(Items.DYE, 1, 11));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_defabricator_ammo, 16), "PCP", "DDD", "PCP", 'P', STEEL.plate(), 'C', ModItems.coil_copper, 'D', LI.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_lever_action, 1), "PPI", "SWD", 'P', IRON.plate(), 'I', ModItems.mechanism_rifle_1, 'S', Items.STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_lever_action_dark, 1), "PPI", "SWD", 'P', STEEL.plate(), 'I', ModItems.mechanism_rifle_1, 'S', Items.STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_bolt_action, 1), "PPI", "SWD", 'P', STEEL.plate(), 'I', ModItems.mechanism_rifle_1, 'S', Items.STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_bolt_action_green, 1), "PPI", "SWD", 'P', IRON.plate(), 'I', ModItems.mechanism_rifle_1, 'S', Items.STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_bolt_action_saturnite, 1), "PPI", "SWD", 'P', BIGMT.plate(), 'I', ModItems.mechanism_rifle_1, 'S', Items.STICK, 'D', KEY_PLANKS, 'W', ModItems.wire_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_b92, 1), "DDD", "SSC", "  R", 'D', ModItems.plate_dineutronium, 'S', STAR.ingot(), 'C', ModItems.circuit_targeting_tier6, 'R', ModItems.gun_revolver_schrabidium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_b92_ammo, 1), "PSP", "ESE", "PSP", 'P', STEEL.plate(), 'S', STAR.ingot(), 'E', ModItems.powder_spark_mix);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.weaponized_starblaster_cell, 1), new IngredientContainsTag(ItemFluidTank.getFullTank(ModForgeFluids.acid)), new IngredientContainsTag(GunB92Cell.getFullCell()), ModItems.wire_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_uzi, 1), "SMS", " PB", " P ", 'S', STEEL.ingot(), 'M', ModItems.mechanism_rifle_2, 'P', STEEL.plate(), 'B', ModItems.bolt_dura_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_uzi_silencer, 1), "P  ", " P ", "  U", 'P', ANY_PLASTIC.ingot(), 'U', ModItems.gun_uzi);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_uzi_saturnite, 1), "SMS", " PB", " P ", 'S', BIGMT.ingot(), 'M', ModItems.mechanism_rifle_2, 'P', BIGMT.plate(), 'B', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_uzi_saturnite_silencer, 1), "P  ", " P ", "  U", 'P', ANY_PLASTIC.ingot(), 'U', ModItems.gun_uzi_saturnite);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_bolter, 1), "SSM", "PIP", " I ", 'S', BIGMT.plate(), 'I', BIGMT.ingot(), 'M', ModItems.mechanism_special, 'P', ANY_PLASTIC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_vortex, 1), "AS ", "SIP", " SC", 'S', ModItems.plate_armor_lunar, 'I', ModItems.gun_xvl1456, 'A', ModItems.levitation_unit, 'P', ModItems.circuit_tantalium, 'C', ModItems.crystal_trixite);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44, 1), ModItems.gun_revolver_nopip_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44_pip, 1), ModItems.gun_revolver_pip_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg, 1), ModItems.gun_calamity_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_5mm, 1), ModItems.gun_lacunae_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket, 1), ModItems.gun_rpg_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_9mm, 1), ModItems.gun_mp40_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr, 1), ModItems.gun_uzi_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge, 1), ModItems.gun_uboinik_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge, 1), ModItems.gun_lever_action_ammo);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_slug, 1), ModItems.gun_bolt_action_ammo);

        reg2();

    }

    public static void reg2() {

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_flechette, 1), " L ", " L ", "LLL", 'L', PB.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_iron, 24), " I", "GC", " P", 'I', IRON.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_iron, 24), " I", "GC", " P", 'I', IRON.ingot(), 'G', ModItems.ballistite, 'C', ModItems.casing_357, 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_steel, 24), " I", "GC", " P", 'I', PB.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_steel, 24), " I", "GC", " P", 'I', PB.ingot(), 'G', ModItems.ballistite, 'C', ModItems.casing_357, 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_lead, 24), " I", "GC", " P", 'I', U235.ingot(), 'G', ModItems.cordite, 'C', "paneGlassColorless", 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_lead, 24), " I", "GC", " P", 'I', PU239.ingot(), 'G', ModItems.cordite, 'C', "paneGlassColorless", 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_lead, 24), " I", "GC", " P", 'I', ModItems.trinitite, 'G', ModItems.cordite, 'C', "paneGlassColorless", 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_lead, 24), " I", "GC", " P", 'I', ModItems.nuclear_waste_tiny, 'G', ModItems.cordite, 'C', "paneGlassColorless", 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_gold, 24), " I", "GC", " P", 'I', GOLD.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_schrabidium, 6), " I ", "GCN", " P ", 'I', SA326.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357, 'N', "netherStar");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_nightmare, 24), " I", "GC", " P", 'I', W.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_desh, 24), " I", "GC", " P", 'I', DESH.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_357, 'P', ModItems.primer_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_smg, 32), " I", "GC", " P", 'I', PB.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_9, 'P', ModItems.primer_9);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_556, 32), " I", "GC", " P", 'I', STEEL.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_9, 'P', ModItems.primer_9);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_k, 32), "G", "C", "P", 'G', ANY_GUNPOWDER.dust(), 'C', ModItems.casing_9, 'P', ModItems.primer_9);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_uzi, 32), " I", "GC", " P", 'I', IRON.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_9, 'P', ModItems.primer_9);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_lacunae, 32), " I", "GC", " P", 'I', CU.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_9, 'P', ModItems.primer_9);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_nopip, 24), " I", "GC", " P", 'I', PB.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_44, 'P', ModItems.primer_44);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge, 12), " I ", "GCL", " P ", 'I', ModItems.pellet_buckshot, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge, 12), " I ", "GCL", " P ", 'I', ModItems.pellet_buckshot, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_slug, 12), " I ", "GCL", " P ", 'I', PB.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_explosive, 12), " I ", "GCL", " P ", 'I', ModItems.pellet_cluster, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_flechette, 12), " I ", "GCL", " P ", 'I', ModItems.pellet_flechette, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot, 'L', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_nightmare2_ammo, 12), "I", "C", "P", 'I', ModItems.powder_power, 'C', ModItems.casing_buckshot, 'P', ModItems.primer_buckshot);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_calamity, 12), " I ", "GCG", " P ", 'I', PB.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_50, 'P', ModItems.primer_50);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_actionexpress, 12), " I", "GC", " P", 'I', PB.ingot(), 'G', ModItems.cordite, 'C', ModItems.casing_50, 'P', ModItems.primer_50);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_dart, 16), "IPI", "ICI", "IPI", 'I', ModItems.plate_polymer, 'P', IRON.plate(), 'C', new IngredientContainsTag(ItemFluidTank.getFullBarrel(ModForgeFluids.watz)));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge_incendiary, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_12gauge, 'A', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge_shrapnel, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_12gauge, 'A', ModBlocks.gravel_obsidian);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge_du, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_12gauge, 'A', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge_sleek, 64), "BBB", "BAB", "BBB", 'B', ModItems.ammo_12gauge, 'A', ModItems.coin_maskman);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_incendiary, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_20gauge, 'A', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_shrapnel, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_20gauge, 'A', ModBlocks.gravel_obsidian);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_caustic, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_20gauge, 'A', ModItems.powder_poison);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_shock, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_20gauge, 'A', DIAMOND.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_wither, 4), "BCB", "CAC", "BCB", 'B', ModItems.ammo_20gauge, 'A', Blocks.SOUL_SAND, 'C', COAL.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_sleek, 64), "BBB", "BAB", "BBB", 'B', ModItems.ammo_20gauge, 'A', ModItems.coin_maskman);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_sleek, 64), "BBB", "BAB", "BBB", 'B', ModItems.ammo_4gauge, 'A', ModItems.coin_maskman);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_flechette_phosphorus, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_4gauge_flechette, 'A', P_WHITE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_balefire, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_4gauge_explosive, 'A', ModItems.egg_balefire_shard);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_kampf, 2), "G", "R", 'G', ModItems.ammo_rocket, 'R', ModItems.ammo_4gauge_explosive);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44_ap, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_44, 'A', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44_du, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_44, 'A', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44_star, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_44_du, 'A', STAR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_5mm_explosive, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_5mm, 'A', ANY_PLASTICEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_5mm_du, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_5mm, 'A', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_5mm_star, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_5mm_du, 'A', STAR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_9mm_ap, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_9mm, 'A', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_9mm_du, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_9mm, 'A', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr_ap, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_22lr, 'A', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg_incendiary, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50bmg, 'A', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg_explosive, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50bmg, 'A', ANY_PLASTICEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg_ap, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50bmg, 'A', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg_du, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50bmg, 'A', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg_star, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_50bmg_du, 'A', STAR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg_sleek, 64), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50bmg, 'A', ModItems.coin_maskman);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50ae_ap, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50ae, 'A', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50ae_du, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50ae, 'A', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50ae_star, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_50ae_du, 'A', STAR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_phosphorus, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556, 'A', P_WHITE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_ap, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556, 'A', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_du, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556, 'A', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_star, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_556_du, 'A', STAR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_sleek, 64), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556, 'A', ModItems.coin_maskman);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_tracer, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556, 'A', Items.REDSTONE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_flechette, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_556, 'A', ModItems.pellet_flechette);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_flechette_incendiary, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556_flechette, 'A', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_flechette_phosphorus, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556_flechette, 'A', P_WHITE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_flechette_du, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556_flechette, 'A', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_flechette_sleek, 64), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556_flechette, 'A', ModItems.coin_maskman);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.folly_bullet, 1), " S ", "STS", "SMS", 'S', STAR.ingot(), 'T', ModItems.powder_magic, 'M', ModBlocks.block_meteor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.folly_bullet_nuclear, 1), " N ", "UTU", "UTU", 'N', ModItems.ammo_nuke, 'U', IRON.ingot(), 'T', W.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.folly_bullet_du, 1), " U ", "UDU", "UTU", 'U', U238.block(), 'D', DESH.block(), 'T', W.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.folly_shell, 1), "IPI", "IPI", "IMI", 'I', IRON.ingot(), 'P', IRON.plate(), 'M', ModItems.primer_50);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_folly, 1), " B ", "MEM", " S ", 'B', ModItems.folly_bullet, 'M', ModItems.powder_magic, 'E', ModItems.powder_power, 'S', ModItems.folly_shell);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_folly_nuclear, 1), " B ", "EEE", " S ", 'B', ModItems.folly_bullet_nuclear, 'E', ModBlocks.det_charge, 'S', ModItems.folly_shell);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_folly_du, 1), " B ", "EEE", " S ", 'B', ModItems.folly_bullet_du, 'E', ModBlocks.det_charge, 'S', ModItems.folly_shell);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket, 1), " T ", "GCG", " P ", 'T', Blocks.TNT, 'G', ModItems.rocket_fuel, 'C', ModItems.casing_50, 'P', ModItems.primer_50);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket, 2), " T ", "GCG", " P ", 'T', ANY_PLASTICEXPLOSIVE.ingot(), 'G', ModItems.rocket_fuel, 'C', ModItems.casing_50, 'P', ModItems.primer_50);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_he, 1), "G", "R", 'G', ANY_PLASTICEXPLOSIVE.ingot(), 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_incendiary, 1), "G", "R", 'G', P_RED.dust(), 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_emp, 1), "G", "R", 'G', DIAMOND.dust(), 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_shrapnel, 1), "G", "R", 'G', ModItems.pellet_buckshot, 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_glare, 1), "GGG", "GRG", "GGG", 'G', Items.REDSTONE, 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_toxic, 1), "G", "R", 'G', ModItems.pellet_gas, 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_nuclear, 1), " P ", "NRN", " P ", 'P', PU239.nugget(), 'N', OreDictManager.getReflector(), 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_rpc, 2), "BP ", "CBH", " DR", 'B', ModItems.blades_steel, 'P', STEEL.plate(), 'C', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)), 'H', ModItems.hull_small_steel, 'D', ModItems.piston_selenium, 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_rpc, 2), "BP ", "CBH", " DR", 'B', ModItems.blades_steel, 'P', STEEL.plate(), 'C', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.petroil)), 'H', ModItems.hull_small_steel, 'D', ModItems.piston_selenium, 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_rpc, 2), "BP ", "CBH", " DR", 'B', ModItems.blades_steel, 'P', STEEL.plate(), 'C', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.biofuel)), 'H', ModItems.hull_small_steel, 'D', ModItems.piston_selenium, 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_sleek, 64), "GGG", "GRG", "GGG", 'G', ModItems.ammo_rocket, 'R', ModItems.coin_maskman);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade, 2), " T ", "GCI", 'T', ANY_HIGHEXPLOSIVE.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'I', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_tracer, 2), " T ", "GCI", " P ", 'T', LAPIS.dust(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'I', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_he, 2), "GIG", 'G', ModItems.ammo_grenade, 'I', ANY_PLASTICEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_incendiary, 2), "GIG", 'G', ModItems.ammo_grenade, 'I', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_toxic, 2), "GIG", 'G', ModItems.ammo_grenade, 'I', ModItems.powder_poison);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_concussion, 2), "GIG", 'G', ModItems.ammo_grenade, 'I', Items.GLOWSTONE_DUST);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_nuclear, 2), " P ", "GIG", " P ", 'G', ModItems.ammo_grenade, 'I', ModItems.neutron_reflector, 'P', PU239.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_finned, 1), "G", "R", 'G', Items.FEATHER, 'R', ModItems.ammo_grenade);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_kampf, 2), "G", "R", 'G', ModItems.ammo_rocket, 'R', ModItems.ammo_grenade);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_sleek, 64), "GGG", "GRG", "GGG", 'G', ModItems.ammo_grenade, 'R', ModItems.coin_maskman);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_357, 1), "RSR", "III", " C ", 'R', REDSTONE.dust(), 'S', ModItems.stamp_iron_flat, 'I', ModItems.plate_polymer, 'C', ModItems.casing_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_44, 1), "RSR", "III", " C ", 'R', REDSTONE.dust(), 'S', ModItems.stamp_iron_flat, 'I', ModItems.plate_polymer, 'C', ModItems.casing_44);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_9, 1), "RSR", "III", " C ", 'R', REDSTONE.dust(), 'S', ModItems.stamp_iron_flat, 'I', ModItems.plate_polymer, 'C', ModItems.casing_9);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_50, 1), "RSR", "III", " C ", 'R', REDSTONE.dust(), 'S', ModItems.stamp_iron_flat, 'I', ModItems.plate_polymer, 'C', ModItems.casing_50);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_desh_357, 1), "RSR", "III", " C ", 'R', REDSTONE.dust(), 'S', ModItems.stamp_desh_flat, 'I', ModItems.plate_polymer, 'C', ModItems.casing_357);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_desh_44, 1), "RSR", "III", " C ", 'R', REDSTONE.dust(), 'S', ModItems.stamp_desh_flat, 'I', ModItems.plate_polymer, 'C', ModItems.casing_44);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_desh_9, 1), "RSR", "III", " C ", 'R', REDSTONE.dust(), 'S', ModItems.stamp_desh_flat, 'I', ModItems.plate_polymer, 'C', ModItems.casing_9);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stamp_desh_50, 1), "RSR", "III", " C ", 'R', REDSTONE.dust(), 'S', ModItems.stamp_desh_flat, 'I', ModItems.plate_polymer, 'C', ModItems.casing_50);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_357, 1), " P ", "   ", "P P", 'P', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_44, 1), "P", " ", "P", 'P', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_9, 1), "P", "P", 'P', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_50, 1), " P ", " P ", "PPP", 'P', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_buckshot, 1), "P P", "PPP", 'P', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_357, 1), " P ", "   ", "P P", 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_44, 1), "P", " ", "P", 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_9, 1), "P", "P", 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_50, 1), " P ", " P ", "PPP", 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.casing_buckshot, 1), "P P", "PPP", 'P', IRON.plate());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.primer_357, 1), "R", "P", 'P', IRON.plate(), 'R', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.primer_44, 1), "P", "R", 'P', IRON.plate(), 'R', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.primer_9, 1), "R", "P", 'P', AL.plate(), 'R', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.primer_50, 1), "P", "R", 'P', AL.plate(), 'R', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.primer_buckshot, 1), "R", "P", 'P', CU.plate(), 'R', REDSTONE.dust());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_light_ammo, 1), " L ", "IGI", "ICI", 'L', PB.plate(), 'I', IRON.plate(), 'C', CU.plate(), 'G', Items.GUNPOWDER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_heavy_ammo, 1), "LGC", "LGC", "LGC", 'L', PB.plate(), 'C', CU.plate(), 'G', Items.GUNPOWDER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_rocket_ammo, 1), "TS ", "SGS", " SR", 'T', Blocks.TNT, 'S', STEEL.plate(), 'G', Items.GUNPOWDER, 'R', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_flamer_ammo, 1), "FSF", "FPF", "FPF", 'F', ModItems.gun_immolator_ammo, 'S', ModItems.pipes_steel, 'P', CU.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_tau_ammo, 1), "AAA", "AUA", "AAA", 'A', ModItems.gun_xvl1456_ammo, 'U', U238.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_spitfire_ammo, 1), "CP ", "PTP", " PR", 'P', STEEL.plate(), 'C', ModItems.circuit_copper, 'T', Blocks.TNT, 'R', REDSTONE.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_cwis_ammo, 1), "LLL", "GGG", "IGI", 'L', PB.plate(), 'I', IRON.plate(), 'G', Items.GUNPOWDER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_cheapo_ammo, 1), "ILI", "IGI", "ICI", 'L', PB.plate(), 'I', STEEL.plate(), 'C', CU.plate(), 'G', Items.GUNPOWDER);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_generic, 4), "RS ", "ITI", " I ", 'I', IRON.plate(), 'R', ModItems.wire_red_copper, 'S', STEEL.plate(), 'T', Item.getItemFromBlock(Blocks.TNT));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_strong, 2), " G ", "SGS", " S ", 'G', ModItems.grenade_generic, 'S', Items.GUNPOWDER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_frag, 2), " G ", "WGW", " K ", 'G', ModItems.grenade_generic, 'W', Item.getItemFromBlock(Blocks.PLANKS), 'K', Item.getItemFromBlock(Blocks.GRAVEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_poison, 2), " G ", "PGP", " P ", 'G', ModItems.grenade_generic, 'P', ModItems.powder_poison);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_gas, 2), " G ", "CGC", " C ", 'G', ModItems.grenade_generic, 'C', ModItems.pellet_gas);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_aschrab, 1), "RS ", "ITI", " S ", 'I', "paneGlassColorless", 'R', ModItems.wire_red_copper, 'S', STEEL.plate(), 'T', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.aschrab)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_mk2, 2), " G ", "SGS", " S ", 'G', ModItems.grenade_strong, 'S', Items.GUNPOWDER);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_gascan, 1), new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)), Items.FLINT);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_gascan, 1), new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.biofuel)), Items.FLINT);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_gascan, 1), new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.petroil)), Items.FLINT);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_gascan, 1), new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.kerosene)), Items.FLINT);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_lemon, 1), ModItems.lemon, ModItems.grenade_strong);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_moist_nugget, 12), Items.BREAD, Items.WHEAT, Items.COOKED_CHICKEN, Items.EGG);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_smart, 4), " A ", "ACA", " A ", 'A', ModItems.grenade_strong, 'C', ModItems.circuit_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_mirv, 1), "GGG", "GCG", "GGG", 'G', ModItems.grenade_smart, 'C', ModItems.grenade_generic);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_breach, 1), "G", "G", "P", 'G', ModItems.grenade_smart, 'P', BIGMT.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_burst, 1), "GGG", "GCG", "GGG", 'G', ModItems.grenade_breach, 'C', ModItems.grenade_generic);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_generic, 1), " C ", "PTP", " P ", 'C', ModItems.coil_tungsten, 'P', STEEL.plate(), 'T', Blocks.TNT);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_he, 1), "A", "G", "A", 'G', ModItems.grenade_if_generic, 'A', Items.GUNPOWDER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_bouncy, 1), "G", "A", 'G', ModItems.grenade_if_generic, 'A', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_sticky, 1), "G", "A", 'G', ModItems.grenade_if_generic, 'A', Items.SLIME_BALL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_impact, 1), "G", "A", 'G', ModItems.grenade_if_generic, 'A', Items.REDSTONE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_concussion, 1), "G", "A", 'G', ModItems.grenade_if_generic, 'A', Items.GLOWSTONE_DUST);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_toxic, 1), "G", "A", 'G', ModItems.grenade_if_generic, 'A', ModItems.powder_poison);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_incendiary, 1), "G", "A", 'G', ModItems.grenade_if_generic, 'A', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_brimstone, 1), "R", "G", "A", 'G', ModItems.grenade_if_generic, 'R', REDSTONE.dust(), 'A', ModItems.powder_power);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_mystery, 1), " A ", "BGB", " A ", 'G', ModItems.grenade_if_generic, 'A', ModItems.powder_magic, 'B', ModItems.powder_cloud);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_hopwire, 1), ModItems.grenade_if_generic, ModItems.singularity_counter_resonant);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_spark, 1), ModItems.grenade_if_generic, ModItems.singularity_spark);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_if_null, 1), ModItems.grenade_if_generic, ModItems.undefined);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bomb_waffle, 1), "WEW", "MPM", "WEW", 'W', Items.WHEAT, 'E', Items.EGG, 'M', Items.MILK_BUCKET, 'P', ModItems.demon_core_open);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schnitzel_vegan, 3), "RWR", "WPW", "RWR", 'W', ModItems.nuclear_waste, 'R', Items.REEDS, 'P', Items.PUMPKIN_SEEDS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cotton_candy, 2), " S ", "SPS", " H ", 'P', PU239.nugget(), 'S', Items.SUGAR, 'H', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.apple_schrabidium, 1, 0), "SSS", "SAS", "SSS", 'S', SA326.nugget(), 'A', Items.APPLE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.apple_schrabidium1, 1, 0), "SSS", "SAS", "SSS", 'S', SA326.ingot(), 'A', Items.APPLE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.apple_schrabidium2, 1, 0), "SSS", "SAS", "SSS", 'S', SA326.block(), 'A', Items.APPLE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.apple_lead, 1, 0), "SSS", "SAS", "SSS", 'S', PB.nugget(), 'A', Items.APPLE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.apple_lead1, 1, 0), "SSS", "SAS", "SSS", 'S', PB.ingot(), 'A', Items.APPLE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.apple_lead2, 1, 0), "SSS", "SAS", "SSS", 'S', PB.block(), 'A', Items.APPLE);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.tem_flakes, 1, 0), GOLD.nugget(), Items.PAPER);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.tem_flakes1, 1, 0), GOLD.nugget(), GOLD.nugget(), GOLD.nugget(), Items.PAPER);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.tem_flakes2, 1, 0), Items.GOLD_INGOT, Items.GOLD_INGOT, GOLD.nugget(), GOLD.nugget(), Items.PAPER);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.glowing_stew, 1), Items.BOWL, Item.getItemFromBlock(ModBlocks.mush), Item.getItemFromBlock(ModBlocks.mush));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.balefire_scrambled, 1), Items.BOWL, ModItems.egg_balefire, ModItems.powder_radspice);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.balefire_and_ham, 1), ModItems.balefire_scrambled, Items.COOKED_BEEF, ModItems.powder_radspice);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.med_ipecac, 1), Items.GLASS_BOTTLE, Items.NETHER_WART);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.med_ptsd, 1), ModItems.med_ipecac);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pancake, 1), REDSTONE.dust(), DIAMOND.dust(), Items.WHEAT, ModItems.bolt_tungsten, ModItems.wire_copper, STEEL.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pancake, 1), REDSTONE.dust(), EMERALD.dust(), Items.WHEAT, ModItems.bolt_tungsten, ModItems.wire_copper, STEEL.plate());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.can_empty, 1), "P", "P", 'P', AL.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.can_smart, 1), ModItems.can_empty, Items.POTIONITEM, Items.SUGAR, KNO.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.can_creature, 1), ModItems.can_empty, Items.POTIONITEM, Items.SUGAR, new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.can_redbomb, 1), ModItems.can_empty, Items.POTIONITEM, Items.SUGAR, ModItems.pellet_cluster);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.can_mrsugar, 1), ModItems.can_empty, Items.POTIONITEM, Items.SUGAR, F.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.can_overcharge, 1), ModItems.can_empty, Items.POTIONITEM, Items.SUGAR, S.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.can_luna, 1), ModItems.can_empty, Items.POTIONITEM, Items.SUGAR, ModItems.powder_meteorite_tiny);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.canteen_13, 1), "O", "P", 'O', Items.POTIONITEM, 'P', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.canteen_vodka, 1), "O", "P", 'O', Items.POTATO, 'P', STEEL.plate());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bottle_empty, 6), " G ", "G G", "GGG", 'G', KEY_ANYPANE);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle_nuka, 1), ModItems.bottle_empty, Items.POTIONITEM, Items.SUGAR, COAL.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle_cherry, 1), ModItems.bottle_empty, Items.POTIONITEM, Items.SUGAR, Items.REDSTONE);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle_quantum, 1), ModItems.bottle_empty, Items.POTIONITEM, Items.SUGAR, ModItems.trinitite);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bottle2_empty, 6), " G ", "G G", "G G", 'G', KEY_ANYPANE);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle2_korl, 1), ModItems.bottle2_empty, Items.POTIONITEM, Items.SUGAR, CU.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle2_fritz, 1), ModItems.bottle2_empty, Items.POTIONITEM, Items.SUGAR, W.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle2_korl_special, 1), ModItems.bottle2_empty, Items.POTIONITEM, Items.SUGAR, CU.dust(), SR.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle2_fritz_special, 1), ModItems.bottle2_empty, Items.POTIONITEM, Items.SUGAR, W.dust(), TH232.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle2_sunset, 1), ModItems.bottle2_empty, Items.POTIONITEM, Items.SUGAR, GOLD.dust());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_empty, 6), "P", "C", "B", 'B', Item.getItemFromBlock(Blocks.IRON_BARS), 'C', new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)), 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_antidote, 6), "SSS", "PMP", "SSS", 'S', ModItems.syringe_empty, 'P', Items.PUMPKIN_SEEDS, 'M', Items.MILK_BUCKET);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_antidote, 6), "SPS", "SMS", "SPS", 'S', ModItems.syringe_empty, 'P', Items.PUMPKIN_SEEDS, 'M', Items.MILK_BUCKET);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_antidote, 6), "SSS", "PMP", "SSS", 'S', ModItems.syringe_empty, 'P', Items.PUMPKIN_SEEDS, 'M', Items.REEDS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_antidote, 6), "SPS", "SMS", "SPS", 'S', ModItems.syringe_empty, 'P', Items.PUMPKIN_SEEDS, 'M', Items.REEDS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_poison, 1), "SLS", "LCL", "SLS", 'C', ModItems.syringe_empty, 'S', Items.SPIDER_EYE, 'L', PB.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_poison, 1), "SLS", "LCL", "SLS", 'C', ModItems.syringe_empty, 'S', Items.SPIDER_EYE, 'L', ModItems.powder_poison);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_awesome, 1), "SPS", "NCN", "SPS", 'C', ModItems.syringe_empty, 'S', S.dust(), 'P', PU239.nugget(), 'N', PU238.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_awesome, 1), "SNS", "PCP", "SNS", 'C', ModItems.syringe_empty, 'S', S.dust(), 'P', PU239.nugget(), 'N', PU238.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_empty, 6), "P", "C", "B", 'B', Item.getItemFromBlock(Blocks.IRON_BARS), 'C', ModItems.rod_empty, 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_stimpak, 1), " N ", "NSN", " N ", 'N', Items.NETHER_WART, 'S', ModItems.syringe_metal_empty);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_medx, 1), " N ", "NSN", " N ", 'N', Items.QUARTZ, 'S', ModItems.syringe_metal_empty);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_psycho, 1), " N ", "NSN", " N ", 'N', Items.GLOWSTONE_DUST, 'S', ModItems.syringe_metal_empty);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pill_iodine, 8), "IF", 'I', I.dust(), 'F', F.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plan_c, 1), "PFP", 'P', ModItems.powder_poison, 'F', F.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.radx, 1), COAL.dust(), COAL.dust(), F.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.med_bag, 1), "LLL", "SIS", "LLL", 'L', Items.LEATHER, 'S', ModItems.syringe_metal_stimpak, 'I', ModItems.syringe_antidote);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.med_bag, 1), "LLL", "SIS", "LLL", 'L', Items.LEATHER, 'S', ModItems.syringe_metal_stimpak, 'I', ModItems.pill_iodine);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.med_bag, 1), "LL", "SI", "LL", 'L', Items.LEATHER, 'S', ModItems.syringe_metal_super, 'I', ModItems.radaway);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.med_bag, 1), "LLL", "SIS", "LLL", 'L', ANY_RUBBER.ingot(), 'S', ModItems.syringe_metal_stimpak, 'I', ModItems.syringe_antidote);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.med_bag, 1), "LLL", "SIS", "LLL", 'L', ANY_RUBBER.ingot(), 'S', ModItems.syringe_metal_stimpak, 'I', ModItems.pill_iodine);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.med_bag, 1), "LL", "SI", "LL", 'L', ANY_RUBBER.ingot(), 'S', ModItems.syringe_metal_super, 'I', ModItems.radaway);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_super, 1), " N ", "PSP", "L L", 'N', ModItems.bottle_nuka, 'P', STEEL.plate(), 'S', ModItems.syringe_metal_stimpak, 'L', Items.LEATHER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_super, 1), " N ", "PSP", "L L", 'N', ModItems.bottle_nuka, 'P', STEEL.plate(), 'S', ModItems.syringe_metal_stimpak, 'L', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_super, 1), " N ", "PSP", "L L", 'N', ModItems.bottle_cherry, 'P', STEEL.plate(), 'S', ModItems.syringe_metal_stimpak, 'L', Items.LEATHER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_super, 1), " N ", "PSP", "L L", 'N', ModItems.bottle_cherry, 'P', STEEL.plate(), 'S', ModItems.syringe_metal_stimpak, 'L', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.iv_empty, 4), "A", "B", "A", 'A', ANY_RUBBER.ingot(), 'B', IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.iv_xp_empty, 1), ModItems.iv_empty, ModItems.powder_magic);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.radaway, 1), ModItems.iv_blood, COAL.dust(), Items.PUMPKIN_SEEDS);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.radaway_strong, 1), ModItems.radaway, ModBlocks.mush);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.radaway_flush, 1), ModItems.radaway_strong, I.dust());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.stealth_boy, 1), " B", "LI", "LC", 'B', Item.getItemFromBlock(Blocks.STONE_BUTTON), 'L', Items.LEATHER, 'I', STEEL.ingot(), 'C', ModItems.circuit_red_copper);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.sat_dock, 1), "SSS", "PCP", 'S', STEEL.ingot(), 'P', ANY_PLASTIC.ingot(), 'C', ModBlocks.crate_iron);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.book_guide, 1), "IBI", "LBL", "IBI", 'B', Items.BOOK, 'I', ItemStackUtil.itemStackFrom(Items.DYE, 1, 0), 'L', ItemStackUtil.itemStackFrom(Items.DYE, 1, 4));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.book_guide, 1, 1), "G", "B", "C", 'B', Items.BOOK, 'G', ModItems.rbmk_lid_glass, 'C', ModItems.rbmk_lid);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.book_guide, 1, 2), Items.BOOK, ModItems.powder_meteorite);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.book_guide, 1, 3), Items.BOOK, ModItems.fuse);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rail_highspeed, 16), "S S", "SIS", "S S", 'S', STEEL.ingot(), 'I', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rail_booster, 6), "S S", "CIC", "SRS", 'S', STEEL.ingot(), 'I', IRON.plate(), 'R', MINGRADE.ingot(), 'C', ModItems.coil_copper);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.bomb_multi, 1), "AAD", "CHF", "AAD", 'A', ModItems.wire_aluminium, 'C', ModItems.circuit_aluminium, 'H', ModItems.hull_small_aluminium, 'F', ModItems.fins_quad_titanium, 'D', ItemStackUtil.itemStackFrom(Items.DYE, 1, 15));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_ice, 4), Items.SNOWBALL, KNO.dust(), REDSTONE.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_poison, 4), Items.SPIDER_EYE, REDSTONE.dust(), "gemQuartz");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pill_herbal), COAL.dust(), Items.POISONOUS_POTATO, Items.NETHER_WART, Items.BEETROOT);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_gas, 2), Items.WATER_BUCKET, "dustGlowstone", STEEL.plate(), ModItems.ingot.getItemStack(MaterialMineral.IODINE));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.flame_pony, 1), " O ", "DPD", " O ", 'D', ItemStackUtil.itemStackFrom(Items.DYE, 1, 11), 'O', ItemStackUtil.itemStackFrom(Items.DYE, 1, 9), 'P', Items.PAPER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.flame_conspiracy, 1), " S ", "STS", " S ", 'S', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)), 'T', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.flame_politics, 1), " I ", "IPI", " I ", 'P', Items.PAPER, 'I', ItemStackUtil.itemStackFrom(Items.DYE, 1, 0));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.flame_opinion, 1), " R ", "RPR", " R ", 'P', Items.PAPER, 'R', ItemStackUtil.itemStackFrom(Items.DYE, 1, 1));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.solid_fuel_presto, 1), " P ", "SRS", " P ", 'P', Items.PAPER, 'S', ModItems.solid_fuel, 'R', REDSTONE.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.solid_fuel_presto_triplet, 1), ModItems.solid_fuel_presto, ModItems.solid_fuel_presto, ModItems.solid_fuel_presto, ModItems.ball_dynamite);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.flame_war, 1), "WHW", "CTP", "WOW", 'W', Item.getItemFromBlock(Blocks.PLANKS), 'T', Item.getItemFromBlock(Blocks.TNT), 'H', ModItems.flame_pony, 'C', ModItems.flame_conspiracy, 'P', ModItems.flame_politics, 'O', ModItems.flame_opinion);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.emp_bomb, 1), "LML", "LCL", "LML", 'L', PB.plate(), 'M', ModItems.magnetron, 'C', ModItems.circuit_gold);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gadget_explosive8, 1), "EEE", "EPE", "EEE", 'E', ModItems.gadget_explosive, 'P', AL.plate());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.man_explosive8, 1), "EEE", "ESE", "EEE", 'E', ModItems.man_explosive, 'S', STEEL.plate());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.n2_charge, 1), " D ", "ERE", " D ", 'D', ModItems.ducttape, 'E', ModBlocks.det_charge, 'R', REDSTONE.block());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_tnt, 1), " C ", "TIT", "TIT", 'C', CU.plate(), 'I', IRON.plate(), 'T', ANY_HIGHEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_nuke, 1), " C ", "LUL", "LUL", 'C', CU.plate(), 'L', PB.plate(), 'U', U235.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_hydro, 1), " C ", "LTL", "LIL", 'C', CU.plate(), 'L', PB.plate(), 'I', IRON.plate(), 'T', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_amat, 1), " C ", "MMM", "AAA", 'C', CU.plate(), 'A', AL.plate(), 'M', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.amat)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_dirty, 1), " C ", "WLW", "WLW", 'C', CU.plate(), 'L', PB.plate(), 'W', ModItems.nuclear_waste);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_schrab, 1), " C ", "LUL", "LUL", 'C', CU.plate(), 'L', PB.plate(), 'U', SA326.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_sol, 1), " C ", "LUL", "LUL", 'C', CU.plate(), 'L', PB.plate(), 'U', SA327.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_euph, 1), " C ", "LUL", "LUL", 'C', CU.plate(), 'L', PB.plate(), 'U', EUPH.ingot());

        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_generic), " A ", "PRP", "PRP", 'A', ModItems.wire_aluminium, 'P', AL.plate(), 'R', REDSTONE.dust());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_advanced), " A ", "PSP", "PLP", 'A', ModItems.wire_red_copper, 'P', CU.plate(), 'S', S.dust(), 'L', PB.dust());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_advanced), " A ", "PLP", "PSP", 'A', ModItems.wire_red_copper, 'P', CU.plate(), 'S', S.dust(), 'L', PB.dust());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_lithium), "A A", "PSP", "PLP", 'A', ModItems.wire_gold, 'P', TI.plate(), 'S', LI.dust(), 'L', CO.dust());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_lithium), "A A", "PLP", "PSP", 'A', ModItems.wire_gold, 'P', TI.plate(), 'S', LI.dust(), 'L', CO.dust());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_schrabidium), " A ", "PNP", "PSP", 'A', ModItems.wire_schrabidium, 'P', SA326.plate(), 'S', SA326.dust(), 'N', NP237.dust());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_schrabidium), " A ", "PSP", "PNP", 'A', ModItems.wire_schrabidium, 'P', SA326.plate(), 'S', SA326.dust(), 'N', NP237.dust());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_trixite), " A ", "PSP", "PTP", 'A', ModItems.wire_aluminium, 'P', AL.plate(), 'S', ModItems.powder_power, 'T', ModItems.crystal_trixite);
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_trixite), " A ", "PTP", "PSP", 'A', ModItems.wire_aluminium, 'P', AL.plate(), 'S', ModItems.powder_power, 'T', ModItems.crystal_trixite);
        addRecipeAuto(ItemBattery.getFullBattery(ModItems.energy_core), "PCW", "TRD", "PCW", 'P', ALLOY.plate(), 'C', ModItems.coil_advanced_alloy, 'W', ModItems.wire_advanced_alloy, 'R', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), 'D', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.deuterium)), 'T', W.ingot());
        addRecipeAuto(ItemBattery.getFullBattery(ModItems.energy_core), "PCW", "TDR", "PCW", 'P', ALLOY.plate(), 'C', ModItems.coil_advanced_alloy, 'W', ModItems.wire_advanced_alloy, 'R', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), 'D', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.deuterium)), 'T', W.ingot());

        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_red_cell), "WBW", "PBP", "WBW", 'W', ModItems.wire_aluminium, 'P', AL.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_generic));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_advanced_cell), "WBW", "PBP", "WBW", 'W', ModItems.wire_red_copper, 'P', CU.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_advanced));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_lithium_cell), "WBW", "PBP", "WBW", 'W', ModItems.wire_gold, 'P', TI.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_lithium));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_schrabidium_cell), "WBW", "PBP", "WBW", 'W', ModItems.wire_schrabidium, 'P', SA326.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_schrabidium));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_red_cell_6), "BBB", "WPW", "BBB", 'W', ModItems.wire_aluminium, 'P', AL.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_red_cell));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_advanced_cell_4), "BWB", "WPW", "BWB", 'W', ModItems.wire_red_copper, 'P', CU.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_advanced_cell));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_lithium_cell_3), "WPW", "BBB", "WPW", 'W', ModItems.wire_gold, 'P', TI.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_lithium_cell));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_schrabidium_cell_2), "WPW", "BWB", "WPW", 'W', ModItems.wire_schrabidium, 'P', SA326.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_schrabidium_cell));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_red_cell_24), "BWB", "WPW", "BWB", 'W', ModItems.wire_aluminium, 'P', AL.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_red_cell_6));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_advanced_cell_12), "WPW", "BBB", "WPW", 'W', ModItems.wire_red_copper, 'P', CU.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_advanced_cell_4));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_lithium_cell_6), "WPW", "BWB", "WPW", 'W', ModItems.wire_gold, 'P', TI.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_lithium_cell_3));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_schrabidium_cell_4), "WPW", "BWB", "WPW", 'W', ModItems.wire_schrabidium, 'P', SA326.plate(), 'B', ItemBattery.getEmptyBattery(ModItems.battery_schrabidium_cell_2));

        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_spark), "P", "S", "S", 'P', ModItems.plate_dineutronium, 'S', ModItems.powder_spark_mix);
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_6), "BW", "PW", "BW", 'W', ModItems.wire_magnetized_tungsten, 'P', ModItems.powder_spark_mix, 'B', ItemBattery.getEmptyBattery(ModItems.battery_spark));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_25), "W W", "SCS", "PSP", 'W', ModItems.wire_magnetized_tungsten, 'P', ModItems.plate_dineutronium, 'S', ModItems.powder_spark_mix, 'C', ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_6));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_100), "W W", "BPB", "SSS", 'W', ModItems.wire_magnetized_tungsten, 'P', ModItems.plate_dineutronium, 'S', ModItems.powder_spark_mix, 'B', ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_25));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_1000), "PCP", "CSC", "PCP", 'S', ModItems.singularity_spark, 'P', ModItems.powder_spark_mix, 'C', ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_100));
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_2500), "SCS", "CVC", "SCS", 'C', ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_100), 'V', ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_1000), 'S', ModItems.powder_spark_mix);
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_10000), "OPO", "VSV", "OPO", 'S', ModItems.singularity_spark, 'V', ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_2500), 'O', ModItems.ingot.getItemStack(MaterialMineral.OSMIRIDIUM), 'P', ModItems.plate_dineutronium);
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_power), "YPY", "CSC", "YPY", 'S', ModItems.singularity_spark, 'C', ItemBattery.getEmptyBattery(ModItems.battery_spark_cell_10000), 'Y', ModItems.billet.getItemStack(MaterialMineral.YHARONITE), 'P', ModItems.plate_dineutronium);

        addRecipeAuto(ItemBattery.getFullBattery(ModItems.battery_su), "P", "R", "C", 'P', Items.PAPER, 'R', REDSTONE.dust(), 'C', COAL.dust());
        addRecipeAuto(ItemBattery.getFullBattery(ModItems.battery_su), "P", "C", "R", 'P', Items.PAPER, 'R', REDSTONE.dust(), 'C', COAL.dust());
        addRecipeAuto(ItemBattery.getFullBattery(ModItems.battery_su_l), " W ", "CPC", "RPR", 'W', ModItems.wire_aluminium, 'P', Items.PAPER, 'R', REDSTONE.dust(), 'C', COAL.dust());
        addRecipeAuto(ItemBattery.getFullBattery(ModItems.battery_su_l), " W ", "RPR", "CPC", 'W', ModItems.wire_aluminium, 'P', Items.PAPER, 'R', REDSTONE.dust(), 'C', COAL.dust());
        addRecipeAuto(ItemBattery.getFullBattery(ModItems.battery_su_l), " W ", "CPC", "RPR", 'W', ModItems.wire_copper, 'P', Items.PAPER, 'R', REDSTONE.dust(), 'C', COAL.dust());
        addRecipeAuto(ItemBattery.getFullBattery(ModItems.battery_su_l), " W ", "RPR", "CPC", 'W', ModItems.wire_copper, 'P', Items.PAPER, 'R', REDSTONE.dust(), 'C', COAL.dust());
        addShapelessAuto(ItemBattery.getFullBattery(ModItems.battery_potato), Items.POTATO, ModItems.wire_aluminium, ModItems.wire_copper);
        addShapelessAuto(ItemBattery.getFullBattery(ModItems.battery_potatos), ItemBattery.getFullBattery(ModItems.battery_potato), ModItems.turret_chip, REDSTONE.dust());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_steam), "PMP", "ISI", "PCP", 'P', CU.plate(), 'M', ModItems.motor, 'C', ModItems.coil_tungsten, 'S', new IngredientContainsTag(ItemFluidTank.getFullTank(FluidRegistry.WATER)), 'I', ANY_RUBBER.ingot());
        addRecipeAuto(ItemBattery.getEmptyBattery(ModItems.battery_steam_large), "MPM", "ISI", "CPC", 'P', ModItems.board_copper, 'M', ModItems.motor, 'C', ModItems.coil_tungsten, 'S', new IngredientContainsTag(ItemFluidTank.getFullBarrel(FluidRegistry.WATER)), 'I', ANY_PLASTIC.ingot());

        if (GeneralConfig.enableBabyMode) {
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_helmet, 1), "EEE", "E E", 'E', STAR.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_plate, 1), "E E", "EEE", "EEE", 'E', STAR.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_legs, 1), "EEE", "E E", "E E", 'E', STAR.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_boots, 1), "E E", "E E", 'E', STAR.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_helmet, 1), "EEE", "E E", 'E', SA326.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_plate, 1), "E E", "EEE", "EEE", 'E', SA326.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_legs, 1), "EEE", "E E", "E E", 'E', SA326.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_boots, 1), "E E", "E E", 'E', SA326.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_sword, 1), "I", "I", "S", 'I', SA326.ingot(), 'S', Items.STICK);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_pickaxe, 1), "III", " S ", " S ", 'I', SA326.ingot(), 'S', Items.STICK);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_axe, 1), "II", "IS", " S", 'I', SA326.ingot(), 'S', Items.STICK);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_shovel, 1), "I", "S", "S", 'I', SA326.ingot(), 'S', Items.STICK);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_hoe, 1), "II", " S", " S", 'I', SA326.ingot(), 'S', Items.STICK);
        } else {
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_helmet, 1), "EEE", "ECE", 'E', STAR.ingot(), 'C', ModItems.cobalt_helmet);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_plate, 1), "ECE", "EEE", "EEE", 'E', STAR.ingot(), 'C', ModItems.cobalt_plate);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_legs, 1), "EEE", "ECE", "E E", 'E', STAR.ingot(), 'C', ModItems.cobalt_legs);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_boots, 1), "E E", "ECE", 'E', STAR.ingot(), 'C', ModItems.cobalt_boots);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_helmet, 1), "EEE", "ESE", " P ", 'E', SA326.ingot(), 'S', ModItems.starmetal_helmet, 'P', ModItems.pellet_charged);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_plate, 1), "ESE", "EPE", "EEE", 'E', SA326.ingot(), 'S', ModItems.starmetal_plate, 'P', ModItems.pellet_charged);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_legs, 1), "EEE", "ESE", "EPE", 'E', SA326.ingot(), 'S', ModItems.starmetal_legs, 'P', ModItems.pellet_charged);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_boots, 1), "EPE", "ESE", 'E', SA326.ingot(), 'S', ModItems.starmetal_boots, 'P', ModItems.pellet_charged);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_sword, 1), "I", "W", "S", 'I', SA326.block(), 'W', ModItems.desh_sword, 'S', POLYMER.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_pickaxe, 1), "SWS", " P ", " P ", 'S', ModItems.blades_schrabidium, 'W', ModItems.desh_pickaxe, 'P', POLYMER.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_axe, 1), "SW", "SP", " P", 'S', ModItems.blades_schrabidium, 'W', ModItems.desh_axe, 'P', POLYMER.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_shovel, 1), "S", "W", "P", 'S', ModItems.blades_schrabidium, 'W', ModItems.desh_shovel, 'P', POLYMER.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.schrabidium_hoe, 1), "IW", " S", " S", 'I', SA326.ingot(), 'W', ModItems.desh_hoe, 'S', POLYMER.ingot());
        }

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_helmet, 1), "EEE", "E E", 'E', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_plate, 1), "E E", "EEE", "EEE", 'E', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_legs, 1), "EEE", "E E", "E E", 'E', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_boots, 1), "E E", "E E", 'E', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_sword, 1), "I", "I", "S", 'I', STEEL.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_pickaxe, 1), "III", " S ", " S ", 'I', STEEL.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_axe, 1), "II", "IS", " S", 'I', STEEL.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_shovel, 1), "I", "S", "S", 'I', STEEL.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.steel_hoe, 1), "II", " S", " S", 'I', STEEL.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_helmet, 1), "EEE", "E E", 'E', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_plate, 1), "E E", "EEE", "EEE", 'E', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_legs, 1), "EEE", "E E", "E E", 'E', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_boots, 1), "E E", "E E", 'E', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_sword, 1), "I", "I", "S", 'I', TI.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_pickaxe, 1), "III", " S ", " S ", 'I', TI.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_axe, 1), "II", "IS", " S", 'I', TI.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_shovel, 1), "I", "S", "S", 'I', TI.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.titanium_hoe, 1), "II", " S", " S", 'I', TI.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_sword, 1), "I", "I", "S", 'I', CO.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_pickaxe, 1), "III", " S ", " S ", 'I', CO.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_axe, 1), "II", "IS", " S", 'I', CO.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_shovel, 1), "I", "S", "S", 'I', CO.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_hoe, 1), "II", " S", " S", 'I', CO.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_helmet, 1), "EEE", "E E", 'E', ALLOY.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_plate, 1), "E E", "EEE", "EEE", 'E', ALLOY.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_legs, 1), "EEE", "E E", "E E", 'E', ALLOY.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_boots, 1), "E E", "E E", 'E', ALLOY.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_sword, 1), "I", "I", "S", 'I', ALLOY.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_pickaxe, 1), "III", " S ", " S ", 'I', ALLOY.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_axe, 1), "II", "IS", " S", 'I', ALLOY.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_shovel, 1), "I", "S", "S", 'I', ALLOY.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.alloy_hoe, 1), "II", " S", " S", 'I', ALLOY.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_helmet, 1), "EEE", "E E", 'E', CMB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_plate, 1), "E E", "EEE", "EEE", 'E', CMB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_legs, 1), "EEE", "E E", "E E", 'E', CMB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_boots, 1), "E E", "E E", 'E', CMB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_sword, 1), "I", "I", "S", 'I', CMB.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_pickaxe, 1), "III", " S ", " S ", 'I', CMB.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_axe, 1), "II", "IS", " S", 'I', CMB.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_shovel, 1), "I", "S", "S", 'I', CMB.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cmb_hoe, 1), "II", " S", " S", 'I', CMB.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.elec_sword, 1), "RPR", "RPR", " B ", 'P', POLYMER.ingot(), 'R', ModItems.bolt_dura_steel, 'B', ModItems.battery_lithium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.elec_pickaxe, 1), "RDM", " PB", " P ", 'P', POLYMER.ingot(), 'D', DURA.ingot(), 'R', ModItems.bolt_dura_steel, 'M', ModItems.motor, 'B', ModItems.battery_lithium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.elec_axe, 1), " DP", "RRM", " PB", 'P', POLYMER.ingot(), 'D', DURA.ingot(), 'R', ModItems.bolt_dura_steel, 'M', ModItems.motor, 'B', ModItems.battery_lithium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.elec_shovel, 1), "  P", "RRM", "  B", 'P', POLYMER.ingot(), 'R', ModItems.bolt_dura_steel, 'M', ModItems.motor, 'B', ModItems.battery_lithium);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.centri_stick, 1), ModItems.centrifuge_element, ModItems.energy_core, Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.smashing_hammer, 1), "STS", "SPS", " P ", 'S', STEEL.block(), 'T', W.block(), 'P', POLYMER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.desh_sword, 1), "I", "I", "S", 'I', DESH.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.desh_pickaxe, 1), "III", " S ", " S ", 'I', DESH.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.desh_axe, 1), "II", "IS", " S", 'I', DESH.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.desh_shovel, 1), "I", "S", "S", 'I', DESH.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.desh_hoe, 1), "II", " S", " S", 'I', DESH.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.security_helmet, 1), "SSS", "IGI", 'S', STEEL.plate(), 'I', ModItems.plate_kevlar, 'G', KEY_ANYPANE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.security_plate, 1), "KWK", "IKI", "WKW", 'K', ModItems.plate_kevlar, 'I', POLYMER.ingot(), 'W', ItemStackUtil.itemStackFrom(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.security_legs, 1), "IWI", "K K", "W W", 'K', ModItems.plate_kevlar, 'I', POLYMER.ingot(), 'W', ItemStackUtil.itemStackFrom(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.security_boots, 1), "P P", "I I", 'P', STEEL.plate(), 'I', ModItems.plate_kevlar);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jetpack_boost, 1), "PTP", "SLS", "W W", 'P', STEEL.plate(), 'T', ModItems.tank_steel, 'S', ModItems.pipes_steel, 'L', Items.LEATHER, 'W', ModItems.thruster_small);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jetpack_fly, 1), "PTP", "SLS", "W W", 'P', STEEL.plate(), 'T', ModItems.cap_aluminium, 'S', ModItems.pipes_steel, 'L', ModItems.jetpack_boost, 'W', ModItems.thruster_small);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jetpack_break, 1), "PTP", "SLS", "P P", 'P', STEEL.plate(), 'T', ModItems.cap_aluminium, 'S', ModItems.coil_tungsten, 'L', ModItems.jetpack_boost);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jetpack_vector, 1), "PTP", "SLS", "W W", 'P', TI.plate(), 'T', ModItems.circuit_copper, 'S', ModItems.motor, 'L', ModItems.jetpack_fly, 'W', ModItems.thruster_small);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.chainsaw, 1), "  H", "BBP", "  C", 'H', ModItems.hull_small_steel, 'B', ModItems.blades_steel, 'P', ModItems.piston_selenium, 'C', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.chainsaw, 1), "  H", "BBP", "  C", 'H', ModItems.hull_small_steel, 'B', ModItems.blades_steel, 'P', ModItems.piston_selenium, 'C', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.petroil)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.chainsaw, 1), "  H", "BBP", "  C", 'H', ModItems.hull_small_steel, 'B', ModItems.blades_steel, 'P', ModItems.piston_selenium, 'C', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.biofuel)));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wiring_red_copper, 1), "PPP", "PIP", "PPP", 'P', STEEL.plate(), 'I', STEEL.ingot());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gadget_kit, 1), ModBlocks.nuke_gadget, ModItems.gadget_explosive8, ModItems.gadget_explosive8, ModItems.gadget_explosive8, ModItems.gadget_explosive8, ModItems.gadget_wireing, ModItems.gadget_core, ModItems.hazmat_kit);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.boy_kit, 1), ModBlocks.nuke_boy, ModItems.boy_shielding, ModItems.boy_target, ModItems.boy_bullet, ModItems.boy_propellant, ModItems.boy_igniter, ModItems.hazmat_kit);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.man_kit, 1), ModBlocks.nuke_man, ModBlocks.det_nuke, ModItems.man_igniter, ModItems.hazmat_kit);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.mike_kit, 1), ModBlocks.nuke_mike, ModBlocks.det_nuke, ModItems.mike_core, ModItems.mike_deut, ModItems.mike_cooling_unit, ModItems.hazmat_red_kit);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.tsar_kit, 1), ModBlocks.nuke_tsar, ModBlocks.det_nuke, ModItems.mike_core, ModItems.mike_deut, ModItems.mike_core, ModItems.mike_deut, ModItems.hazmat_grey_kit);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_helmet, 1), "EEE", "EIE", "FPF", 'E', ModItems.hazmat_cloth, 'I', KEY_ANYPANE, 'P', STEEL.plate(), 'F', ModItems.filter_coal);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_plate, 1), "E E", "EEE", "EEE", 'E', ModItems.hazmat_cloth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_legs, 1), "EEE", "E E", "E E", 'E', ModItems.hazmat_cloth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_boots, 1), "E E", "E E", 'E', ModItems.hazmat_cloth);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_kit, 1), ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_helmet, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_plate, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_legs, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_boots, 1, 0));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_helmet_red, 1), "EEE", "IEI", "EFE", 'E', ModItems.hazmat_cloth_red, 'I', KEY_ANYPANE, 'F', ModItems.gas_mask_filter);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_plate_red, 1), "E E", "EEE", "EEE", 'E', ModItems.hazmat_cloth_red);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_legs_red, 1), "EEE", "E E", "E E", 'E', ModItems.hazmat_cloth_red);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_boots_red, 1), "E E", "E E", 'E', ModItems.hazmat_cloth_red);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_red_kit, 1), ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_helmet_red, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_plate_red, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_legs_red, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_boots_red, 1, 0));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_helmet_grey, 1), "EEE", "IEI", "EFE", 'E', ModItems.hazmat_cloth_grey, 'I', KEY_ANYPANE, 'F', ModItems.gas_mask_filter);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_plate_grey, 1), "E E", "EEE", "EEE", 'E', ModItems.hazmat_cloth_grey);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_legs_grey, 1), "EEE", "E E", "E E", 'E', ModItems.hazmat_cloth_grey);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_boots_grey, 1), "E E", "E E", 'E', ModItems.hazmat_cloth_grey);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_grey_kit, 1), ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter_combo, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_helmet_grey, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_plate_grey, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_legs_grey, 1, 0), ItemStackUtil.itemStackFrom(ModItems.hazmat_boots_grey, 1, 0));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.asbestos_helmet, 1), "EEE", "EIE", 'E', ModItems.asbestos_cloth, 'I', GOLD.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.asbestos_plate, 1), "E E", "EEE", "EEE", 'E', ModItems.asbestos_cloth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.asbestos_legs, 1), "EEE", "E E", "E E", 'E', ModItems.asbestos_cloth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.asbestos_boots, 1), "E E", "E E", 'E', ModItems.asbestos_cloth);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_paa_helmet, 1), "EEE", "EIE", "FPF", 'E', ModItems.plate_paa, 'I', ModItems.liquidator_helmet, 'P', STEEL.plate(), 'F', ModItems.filter_coal);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_paa_plate, 1), "E E", "EIE", "EEE", 'E', ModItems.plate_paa, 'I', ModItems.liquidator_plate);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_paa_legs, 1), "EEE", "EIE", "E E", 'E', ModItems.plate_paa, 'I', ModItems.liquidator_legs);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hazmat_paa_boots, 1), "E E", "EIE", 'E', ModItems.plate_paa, 'I', ModItems.liquidator_boots);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.paa_helmet, 1), "XGX", "NEN", 'E', ModItems.hazmat_paa_helmet, 'N', OreDictManager.getReflector(), 'G', ModItems.billet.getItemStack(MaterialMineral.GH336), 'X', ModItems.powder_verticium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.paa_plate, 1), "XGX", "NEN", 'E', ModItems.hazmat_paa_plate, 'N', OreDictManager.getReflector(), 'G', ModItems.billet.getItemStack(MaterialMineral.GH336), 'X', ModItems.powder_verticium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.paa_legs, 1), "XGX", "NEN", 'E', ModItems.hazmat_paa_legs, 'N', OreDictManager.getReflector(), 'G', ModItems.billet.getItemStack(MaterialMineral.GH336), 'X', ModItems.powder_verticium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.paa_boots, 1), "XGX", "NEN", 'E', ModItems.hazmat_paa_boots, 'N', OreDictManager.getReflector(), 'G', ModItems.billet.getItemStack(MaterialMineral.GH336), 'X', ModItems.powder_verticium);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.goggles, 1), "P P", "GPG", 'G', KEY_ANYPANE, 'P', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask, 1), "PPP", "GPG", " F ", 'G', KEY_ANYPANE, 'P', STEEL.plate(), 'F', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask_m65, 1), "PPP", "GPG", " F ", 'G', KEY_ANYPANE, 'P', ANY_RUBBER.ingot(), 'F', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask_mono, 1), " P ", "PPP", " F ", 'P', ANY_RUBBER.ingot(), 'F', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mask_damp, 1), "RRR", 'R', ModItems.rag_damp);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mask_piss, 1), "RRR", 'R', ModItems.rag_piss);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mask_rag, 1), "RRR", 'R', ModItems.rag);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jetpack_tank, 1), " S ", "BKB", " S ", 'S', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'K', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.kerosene)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_kit_1, 4), "I ", "LB", "P ", 'I', ANY_RUBBER.ingot(), 'L', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.lubricant)), 'B', ModItems.bolt_tungsten, 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_kit_2, 1), "III", "GLG", "PPP", 'I', ANY_RUBBER.ingot(), 'L', ModItems.ducttape, 'G', ModItems.gun_kit_1, 'P', IRON.plate());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.igniter, 1), " W", "SC", "CE", 'S', STEEL.plate(), 'W', ModItems.wire_schrabidium, 'C', ModItems.circuit_schrabidium, 'E', EUPH.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.euphemium_helmet, 1), "EEE", "E E", 'E', ModItems.plate_euphemium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.euphemium_plate, 1), "EWE", "EEE", "EEE", 'E', ModItems.plate_euphemium, 'W', ModItems.watch);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.euphemium_legs, 1), "EEE", "E E", "E E", 'E', ModItems.plate_euphemium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.euphemium_boots, 1), "E E", "E E", 'E', ModItems.plate_euphemium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.watch, 1), "LEL", "EWE", "LEL", 'E', EUPH.ingot(), 'L', ItemStackUtil.itemStackFrom(Items.DYE, 1, 4), 'W', Items.CLOCK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.apple_euphemium, 1), "EEE", "EAE", "EEE", 'E', EUPH.nugget(), 'A', Items.APPLE);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mask_of_infamy, 1), "III", "III", " I ", 'I', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.designator, 1), "  A", "#B#", "#B#", '#', IRON.plate(), 'A', STEEL.plate(), 'B', ModItems.circuit_red_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.designator_range, 1), "RRD", "PIC", "  P", 'P', STEEL.plate(), 'R', Items.REDSTONE, 'C', ModItems.circuit_gold, 'D', ModItems.designator, 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.designator_manual, 1), "  A", "#C#", "#B#", '#', ANY_PLASTIC.ingot(), 'A', PB.plate(), 'B', ModItems.circuit_gold, 'C', ModItems.designator);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.linker, 1), "I I", "ICI", "GGG", 'I', IRON.plate(), 'G', GOLD.plate(), 'C', ModItems.circuit_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.oil_detector, 1), "W I", "WCI", "PPP", 'W', ModItems.wire_gold, 'I', CU.ingot(), 'C', ModItems.circuit_red_copper, 'P', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_chip, 1), "WWW", "CPC", "WWW", 'W', ModItems.wire_gold, 'P', ANY_PLASTIC.ingot(), 'C', ModItems.circuit_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_biometry, 1), "CC ", "GGS", "SSS", 'C', ModItems.circuit_copper, 'S', STEEL.plate(), 'G', GOLD.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.survey_scanner, 1), "SWS", " G ", "PCP", 'W', ModItems.wire_gold, 'P', ANY_PLASTIC.ingot(), 'C', ModItems.circuit_gold, 'S', STEEL.plate(), 'G', GOLD.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.geiger_counter, 1), "GPP", "WCS", "WBB", 'W', ModItems.wire_gold, 'P', ANY_PLASTIC.ingot(), 'C', ModItems.circuit_copper, 'G', GOLD.ingot(), 'S', STEEL.plate(), 'B', BE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.dosimeter, 1), "WGW", "WCW", "WBW", 'W', KEY_PLANKS, 'G', KEY_ANYPANE, 'C', ModItems.circuit_aluminium, 'B', BE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.digamma_diagnostic), "GPP", "WCS", "WBB", 'W', ModItems.wire_tungsten, 'P', REIIUM.ingot(), 'C', ModItems.circuit_schrabidium, 'G', CS.ingot(), 'S', ModItems.plate_desh, 'B', I131.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.lung_diagnostic, 1), "WGW", "WCW", "WBW", 'W', AL.ingot(), 'G', ModItems.gas_mask_filter, 'C', ASBESTOS.ingot(), 'B', ModItems.circuit_red_copper);


        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.key, 1), "  B", " B ", "P  ", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.key_kit, 1), "PKP", "DTD", "PKP", 'P', GOLD.plate(), 'K', ModItems.key, 'D', DIAMOND.dust(), 'T', ModItems.screwdriver);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.key_red, 1), "DSC", "SMS", "KSD", 'C', ModItems.circuit_targeting_tier4, 'M', "netherStar", 'K', ModItems.key, 'D', DESH.dust(), 'S', BIGMT.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pin, 1), "W ", " W", " W", 'W', ModItems.wire_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.padlock_rusty, 1), "I", "B", "I", 'I', IRON.ingot(), 'B', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.padlock, 1), " P ", "PBP", "PPP", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.padlock_reinforced, 1), " P ", "PBP", "PDP", 'P', ALLOY.plate(), 'D', ModItems.plate_desh, 'B', ModItems.bolt_dura_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.padlock_unbreakable, 1), " P ", "PBP", "PDP", 'P', BIGMT.plate(), 'D', DIAMOND.gem(), 'B', ModItems.bolt_dura_steel);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.euphemium_stopper, 1), "I", "S", "S", 'I', EUPH.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.matchstick, 16), "I", "S", 'I', S.dust(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.matchstick, 16), "I", "S", 'I', S.dust(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.matchstick, 24), "I", "S", 'I', P_RED.dust(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.crowbar, 1), "II", " I", " I", 'I', STEEL.ingot());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_power, 5), REDSTONE.dust(), "dustGlowstone", DIAMOND.dust(), NP237.dust(), MAGTUNG.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ballistite, 3), Items.GUNPOWDER, KNO.dust(), Items.SUGAR);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix, 6), NP237.dust(), I.dust(), TH232.dust(), AT.dust(), ND.dust(), CS.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix, 6), SR.dust(), CO.dust(), BR.dust(), TS.dust(), NB.dust(), CE.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_spark_mix, 5), DESH.dust(), EUPH.dust(), ModItems.powder_meteorite, ModItems.powder_power, ModItems.powder_nitan_mix);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 5), IRON.dust(), CU.dust(), LI.dust(), W.dust(), U.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_thermite, 4), IRON.dust(), IRON.dust(), IRON.dust(), AL.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_semtex_mix, 3), ModItems.solid_fuel, ModItems.cordite, KNO.dust());

        addRecipeAuto(ItemFluidCanister.getFullCanister(ModForgeFluids.petroil, 9), "RRR", "RLR", "RRR", 'R', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.reclaimed)), 'L', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.lubricant)));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.record_lc, 1), " S ", "SDS", " S ", 'S', POLYMER.ingot(), 'D', LAPIS.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.record_ss, 1), " S ", "SDS", " S ", 'S', POLYMER.ingot(), 'D', ALLOY.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.record_vc, 1), " S ", "SDS", " S ", 'S', POLYMER.ingot(), 'D', CMB.dust());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_red_copper, 2), REDSTONE.dust(), CU.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_steel, 2), IRON.dust(), COAL.dust());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.saw, 1), "IIL", "PP ", 'P', STEEL.plate(), 'I', STEEL.ingot(), 'L', Items.LEATHER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bat, 1), "P", "P", "S", 'S', STEEL.plate(), 'P', KEY_PLANKS);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bat_nail, 1), ModItems.bat, STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.golf_club, 1), "IP", " P", " P", 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pipe_rusty, 1), "II", " I", " I", 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pipe_lead, 1), "II", " I", " I", 'I', PB.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bottle_opener, 1), "S", "P", 'S', STEEL.plate(), 'P', KEY_PLANKS);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.polaroid, 1), " C ", "RPY", " B ", 'B', LAPIS.dust(), 'C', COAL.dust(), 'R', ALLOY.dust(), 'Y', GOLD.dust(), 'P', Items.PAPER);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ullapool_caber, 1), "ITI", " S ", " S ", 'I', IRON.plate(), 'T', Blocks.TNT, 'S', Items.STICK);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.chocolate_milk, 1), KEY_ANYPANE, ItemStackUtil.itemStackFrom(Items.DYE, 1, 3), Items.MILK_BUCKET, KNO.block(), S.dust(), S.dust(), S.dust(), P_RED.dust());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.crystal_horn, 1), NP237.dust(), I.dust(), TH232.dust(), AT.dust(), ND.dust(), CS.dust(), ModBlocks.block_meteor, ModBlocks.gravel_obsidian, Items.WATER_BUCKET);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.crystal_charred, 1), SR.dust(), CO.dust(), BR.dust(), NB.dust(), TS.dust(), CE.dust(), ModBlocks.block_meteor, AL.block(), Items.WATER_BUCKET);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crystal_virus, 1), "STS", "THT", "STS", 'S', ModItems.particle_strange, 'T', W.dust(), 'H', ModItems.crystal_horn);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.crystal_pulsar, 32), "STS", "THT", "STS", 'S', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.uf6)), 'T', AL.dust(), 'H', ModItems.crystal_charred);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), "SAS", "   ", "SAS", 'S', STEEL.plate(), 'A', AL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_solid, 8), "SAS", "ADA", "SAS", 'S', STEEL.ingot(), 'A', AL.plate(), 'D', ModItems.ducttape);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_solid_sealed, 1), ModBlocks.fluid_duct_solid, ModBlocks.brick_compound);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_assembler, 1), "WWW", "MCM", "ISI", 'W', KEY_ANYPANE, 'M', ModItems.motor, 'C', ModItems.circuit_aluminium, 'I', CU.block(), 'S', STEEL.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.template_folder, 1), "LPL", "BPB", "LPL", 'P', Items.PAPER, 'L', "dyeBlue", 'B', "dyeWhite");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.turret_control, 1), "R12", "PPI", "  I", 'R', Items.REDSTONE, '1', ModItems.circuit_aluminium, '2', ModItems.circuit_red_copper, 'P', STEEL.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_antimatter, 1), "###", "###", "###", '#', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.amat)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fluid_tank_full, 8), "121", "1 1", "121", '1', AL.plate(), '2', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fluid_barrel_full, 2), "121", "1 1", "121", '1', STEEL.plate(), '2', AL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.inf_water, 1), "222", "131", "222", '1', Items.WATER_BUCKET, '2', AL.plate(), '3', Items.DIAMOND);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 1), B.dustTiny(), B.dustTiny(), AC.dustTiny(), LA.dustTiny(), CE.dustTiny(), CO.dustTiny(), LI.dustTiny(), ND.dustTiny(), NB.dustTiny());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 9), B.dust(), B.dust(), AC.dust(), LA.dust(), CE.dust(), CO.dust(), LI.dust(), ND.dust(), NB.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_desh_ready, 1), ModItems.powder_desh_mix, ModItems.nugget.getItemStack(MaterialMineral.MERCURY), ModItems.nugget.getItemStack(MaterialMineral.MERCURY), COAL.dust());

        //not so Temporary Crappy Recipes
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_pip, 1), " G ", "SSP", " TI", 'G', KEY_ANYPANE, 'S', STEEL.plate(), 'P', ModItems.mechanism_revolver_2, 'T', ModItems.wire_tungsten, 'I', ANY_PLASTIC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_nopip, 1), "SSP", " TI", 'S', STEEL.plate(), 'P', ModItems.mechanism_revolver_2, 'T', ModItems.wire_tungsten, 'I', ANY_PLASTIC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_silver, 1), "SSP", " TI", 'S', AL.plate(), 'P', ModItems.mechanism_revolver_2, 'T', ModItems.wire_tungsten, 'I', KEY_PLANKS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_blackjack, 1), "SSP", " TI", 'S', STEEL.plate(), 'P', ModItems.mechanism_revolver_2, 'T', ModItems.wire_tungsten, 'I', KEY_PLANKS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_red, 1), "R ", " B", 'R', ModItems.key_red, 'B', ModItems.gun_revolver_blackjack);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_dineutronium, 4), "PIP", "IDI", "PIP", 'P', ModItems.powder_spark_mix, 'I', DNT.ingot(), 'D', DESH.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_desh, 4), "PIP", "IDI", "PIP", 'P', ANY_PLASTIC.dust(), 'I', DESH.ingot(), 'D', DURA.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.piston_selenium, 1), "SSS", "STS", " D ", 'S', STEEL.plate(), 'T', W.ingot(), 'D', ModItems.bolt_dura_steel);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.catalyst_clay), IRON.dust(), Items.CLAY_BALL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_core_sing, 1), "EAE", "ASA", "EAE", 'E', ModItems.plate_euphemium, 'A', new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.aschrab)), 'S', ModItems.singularity);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_core_wormhole, 1), "DPD", "PSP", "DPD", 'D', ModItems.plate_dineutronium, 'P', ModItems.powder_spark_mix, 'S', ModItems.singularity);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_core_eyeofharmony, 1), "ALA", "LSL", "ALA", 'A', ModItems.plate_dalekanium, 'L', new IngredientContainsTag(ItemFluidTank.getFullBarrel(FluidRegistry.LAVA)), 'S', ModItems.black_hole);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_core_thingy), "GGG", "N N", " S ", 'N', GOLD.nugget(), 'G', GOLD.ingot(), 'S', ModItems.battery_spark_cell_10000);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.photo_panel), " G ", "IPI", " C ", 'G', KEY_ANYPANE, 'I', ModItems.plate_polymer, 'P', NETHERQUARTZ.dust(), 'C', ModItems.circuit_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_satlinker), "PSP", "SCS", "PSP", 'P', STEEL.plate(), 'S', STAR.ingot(), 'C', ModItems.sat_chip);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_telelinker), "PSP", "SCS", "PSP", 'P', STEEL.plate(), 'S', ALLOY.ingot(), 'C', ModItems.turret_biometry);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_keyforge), "PCP", "WSW", "WSW", 'P', STEEL.plate(), 'S', W.ingot(), 'C', ModItems.padlock, 'W', KEY_PLANKS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sat_chip), "WWW", "CIC", "WWW", 'W', ModItems.wire_red_copper, 'C', ModItems.circuit_red_copper, 'I', ANY_PLASTIC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sat_mapper), "H", "B", 'H', ModItems.sat_head_mapper, 'B', ModItems.sat_base);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sat_scanner), "H", "B", 'H', ModItems.sat_head_scanner, 'B', ModItems.sat_base);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sat_radar), "H", "B", 'H', ModItems.sat_head_radar, 'B', ModItems.sat_base);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sat_laser), "H", "B", 'H', ModItems.sat_head_laser, 'B', ModItems.sat_base);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sat_resonator), "H", "B", 'H', ModItems.sat_head_resonator, 'B', ModItems.sat_base);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sat_mapper), ModBlocks.sat_mapper);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sat_scanner), ModBlocks.sat_scanner);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sat_radar), ModBlocks.sat_radar);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sat_laser), ModBlocks.sat_laser);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sat_resonator), ModBlocks.sat_resonator);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sat_foeq), ModBlocks.sat_foeq);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.geiger_counter), ModBlocks.geiger);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sat_mapper), ModItems.sat_mapper);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sat_scanner), ModItems.sat_scanner);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sat_radar), ModItems.sat_radar);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sat_laser), ModItems.sat_laser);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sat_resonator), ModItems.sat_resonator);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sat_foeq), ModItems.sat_foeq);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.geiger), ModItems.geiger_counter);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sat_interface), "ISI", "PCP", "PAP", 'I', STEEL.ingot(), 'S', STAR.ingot(), 'P', ModItems.plate_polymer, 'C', ModItems.sat_chip, 'A', ModItems.circuit_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sat_coord), "SII", "SCA", "SPP", 'I', STEEL.ingot(), 'S', STAR.ingot(), 'P', ModItems.plate_polymer, 'C', ModItems.sat_chip, 'A', ModItems.circuit_red_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_spp_bottom), "MDM", "LCL", "LWL", 'M', MAGTUNG.ingot(), 'D', ModItems.plate_desh, 'L', PB.plate(), 'C', ModItems.circuit_gold, 'W', ModItems.coil_magnetized_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_spp_top), "LWL", "LCL", "MDM", 'M', MAGTUNG.ingot(), 'D', ModItems.plate_desh, 'L', PB.plate(), 'C', ModItems.circuit_gold, 'W', ModItems.coil_magnetized_tungsten);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_spp_bottom), ModBlocks.machine_spp_top);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_spp_top), ModBlocks.machine_spp_bottom);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_b93), "PCE", "SEB", "PCE", 'P', ModItems.plate_dineutronium, 'C', ModItems.weaponized_starblaster_cell, 'E', ModItems.component_emitter, 'B', ModItems.gun_b92, 'S', ModItems.singularity_spark);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_transformer), "SDS", "MCM", "MCM", 'S', IRON.ingot(), 'D', MINGRADE.ingot(), 'M', ModItems.coil_advanced_alloy, 'C', ModItems.circuit_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_transformer_20), "SDS", "MCM", "MCM", 'S', IRON.ingot(), 'D', MINGRADE.ingot(), 'M', ModItems.coil_copper, 'C', ModItems.circuit_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_transformer_dnt), "SDS", "MCM", "MCM", 'S', STAR.ingot(), 'D', DESH.ingot(), 'M', ModBlocks.fwatz_conductor, 'C', ModItems.circuit_targeting_tier6);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_transformer_dnt_20), "SDS", "MCM", "MCM", 'S', STAR.ingot(), 'D', DESH.ingot(), 'M', ModBlocks.fusion_conductor, 'C', ModItems.circuit_targeting_tier6);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle_sparkle), ModItems.bottle_nuka, Items.CARROT, GOLD.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bottle_rad), ModItems.bottle_quantum, Items.CARROT, GOLD.nugget(), ModItems.powder_radspice);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_nuke), "CGC", "CGC", "PAP", 'C', ModBlocks.det_charge, 'G', ModItems.grenade_mk2, 'P', ALLOY.plate(), 'A', Blocks.ANVIL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.radiobox), "PLP", "PSP", "PLP", 'P', STEEL.plate(), 'S', ModItems.ring_starmetal, 'L', OreDictManager.getReflector());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.radiorec), "  W", "PCP", "PIP", 'W', ModItems.wire_copper, 'P', STEEL.plate(), 'C', ModItems.circuit_red_copper, 'I', ANY_PLASTIC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jackt), "S S", "LIL", "LIL", 'S', STEEL.plate(), 'L', Items.LEATHER, 'I', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jackt2), "S S", "LIL", "III", 'S', STEEL.plate(), 'L', Items.LEATHER, 'I', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_cloud), "SPS", "CAC", "SPS", 'S', S.dust(), 'P', ModItems.powder_poison, 'C', CU.dust(), 'A', new IngredientContainsTag(ItemFluidTank.getFullTank(ModForgeFluids.acid)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.grenade_pink_cloud), " S ", "ECE", " E ", 'S', ModItems.powder_spark_mix, 'E', ModItems.powder_magic, 'C', ModItems.grenade_cloud);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.vent_chlorine), "IGI", "ICI", "IDI", 'I', IRON.plate(), 'G', Blocks.IRON_BARS, 'C', ModItems.pellet_gas, 'D', Blocks.DISPENSER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.vent_chlorine_seal), "ISI", "SCS", "ISI", 'I', BIGMT.ingot(), 'S', STAR.ingot(), 'C', ModItems.chlorine_pinwheel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.vent_cloud), "IGI", "ICI", "IDI", 'I', IRON.plate(), 'G', Blocks.IRON_BARS, 'C', ModItems.grenade_cloud, 'D', Blocks.DISPENSER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.vent_pink_cloud), "IGI", "ICI", "IDI", 'I', IRON.plate(), 'G', Blocks.IRON_BARS, 'C', ModItems.grenade_pink_cloud, 'D', Blocks.DISPENSER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.spikes, 4), "FFF", "BBB", "TTT", 'F', Items.FLINT, 'B', ModItems.bolt_tungsten, 'T', W.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.mine_ap, 4), "C", "P", "T", 'C', ModItems.circuit_targeting_tier2, 'P', IRON.plate(), 'T', ANY_PLASTICEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.mine_he, 1), " C ", "PTP", 'C', ModItems.circuit_targeting_tier2, 'P', STEEL.plate(), 'T', ANY_HIGHEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.mine_shrap, 2), "LLL", " C ", "PTP", 'C', ModItems.circuit_targeting_tier2, 'P', STEEL.plate(), 'T', ModBlocks.det_cord, 'L', ModItems.pellet_buckshot);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.mine_fat, 1), "CDN", 'C', ModItems.circuit_targeting_tier2, 'D', ModItems.ducttape, 'N', ModItems.ammo_nuke);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.defuser, 1), " PS", "P P", " P ", 'P', ANY_PLASTIC.ingot(), 'S', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.defuser_desh), " SD", "S S", " S ", 'D', DESH.ingot(), 'S', POLYMER.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.syringe_taint), ModItems.bottle2_empty, ModItems.syringe_metal_empty, ModItems.ducttape, ModItems.powder_magic, SA326.nugget(), Items.POTIONITEM);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.loops), ModItems.flame_pony, Items.WHEAT, Items.SUGAR);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.loop_stew), ModItems.loops, ModItems.can_smart, Items.BOWL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_calamity, 1), " PI", "BBM", " PI", 'P', IRON.plate(), 'B', ModItems.pipes_steel, 'M', ModItems.mechanism_rifle_1, 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_calamity_dual, 1), "BBM", " PI", "BBM", 'P', IRON.plate(), 'B', ModItems.pipes_steel, 'M', ModItems.mechanism_rifle_1, 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.custom_fall, 1), "IIP", "CHW", "IIP", 'I', ANY_RUBBER.ingot(), 'P', BIGMT.plate(), 'C', ModItems.circuit_red_copper, 'H', ModItems.hull_small_steel, 'W', ModItems.coil_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_controller, 1), "PGP", "ICI", "PRP", 'P', ModItems.plate_desh, 'G', KEY_ANYPANE, 'I', ANY_PLASTIC.ingot(), 'R', REDSTONE.block(), 'C', ModItems.circuit_targeting_tier4);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_minigun, 1), "PIB", "PCM", "PIB", 'P', ModItems.pipes_steel, 'B', STEEL.block(), 'I', ANY_PLASTIC.ingot(), 'C', ModItems.mechanism_rifle_2, 'M', ModItems.motor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_avenger, 1), "PIB", "PCM", "PIB", 'P', ModItems.pipes_steel, 'B', BE.block(), 'I', DESH.ingot(), 'C', ModItems.mechanism_rifle_2, 'M', ModItems.motor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_lacunae, 1), "TIT", "ILI", "PRP", 'T', ModItems.syringe_taint, 'I', STAR.ingot(), 'L', ModItems.gun_minigun, 'P', ModItems.pellet_rtg, 'R', ModBlocks.machine_rtg_grey);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.containment_box, 1), "LLL", "LCL", "LLL", 'L', PB.plate(), 'C', Blocks.CHEST);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.absorber, 1), "ICI", "CPC", "ICI", 'I', CU.ingot(), 'C', COAL.dust(), 'P', PB.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.absorber_red, 1), "ICI", "CPC", "ICI", 'I', TI.ingot(), 'C', COAL.dust(), 'P', ModBlocks.absorber);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.absorber_green, 1), "ICI", "CPC", "ICI", 'I', ANY_PLASTIC.ingot(), 'C', ModItems.powder_desh_mix, 'P', ModBlocks.absorber_red);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.absorber_pink, 1), "ICI", "CPC", "ICI", 'I', BIGMT.ingot(), 'C', ModItems.powder_nitan_mix, 'P', ModBlocks.absorber_green);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.decon, 1), "BGB", "SAS", "BSB", 'B', BE.ingot(), 'G', Blocks.IRON_BARS, 'S', STEEL.ingot(), 'A', ModBlocks.absorber);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.decon_digamma, 1), "BGB", "SAS", "BTB", 'S', ModItems.billet.getItemStack(MaterialMineral.FLASHLEAD), 'G', ModItems.fmn, 'B', ModItems.plate_desh, 'A', ModBlocks.decon, 'T', ModItems.xanax);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.radsensor, 1), "IGI", "LCL", "IRI", 'I', CE.ingot(), 'L', PB.plate(), 'G', ModItems.geiger_counter, 'C', Items.COMPARATOR, 'R', Items.REDSTONE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_geo, 1), "ITI", "PCP", "ITI", 'I', DURA.ingot(), 'T', ModItems.thermo_element, 'P', ModItems.board_copper, 'C', ModBlocks.red_wire_coated);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_amgen, 1), "ITI", "TAT", "ITI", 'I', ALLOY.ingot(), 'T', ModItems.thermo_element, 'A', ModBlocks.absorber);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_minirtg, 1), "LLL", "PPP", "TRT", 'L', PB.plate(), 'P', PU238.billet(), 'T', ModItems.thermo_element, 'R', ModItems.rtg_unit);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_powerrtg, 1), "SRS", "PTP", "SRS", 'S', STAR.ingot(), 'R', ModItems.rtg_unit, 'P', PO210.ingot(), 'T', TS.dust());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg), PU238.billet(), PU238.billet(), PU238.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_radium), RA226.billet(), RA226.billet(), RA226.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_weak), U238.billet(), U238.billet(), PU238.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_strontium), SR90.billet(), SR90.billet(), SR90.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_cobalt), CO60.billet(), CO60.billet(), CO60.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_actinium), AC227.billet(), AC227.billet(), AC227.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_polonium), PO210.billet(), PO210.billet(), PO210.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_lead), PB209.billet(), PB209.billet(), PB209.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_gold), AU198.billet(), AU198.billet(), AU198.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_americium), AM241.billet(), AM241.billet(), AM241.billet(), IRON.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_balefire), ModItems.egg_balefire, ModItems.egg_balefire, ModItems.egg_balefire, IRON.plate());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.BISMUTH), 3), ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_depleted_bismuth));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD), 2), ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_depleted_lead));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 12), ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_depleted_mercury));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM), 3), ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_depleted_neptunium));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.ZIRCONIUM), 3), ItemStackUtil.itemStackFrom(ModItems.pellet_rtg_depleted_zirconium));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.pink_planks, 4), "W", 'W', ModBlocks.pink_log);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.decontamination_module, 1), "GAG", "WTW", "GAG", 'W', AC.ingot(), 'T', ModBlocks.decon, 'G', RA226.nugget(), 'A', TCALLOY.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.door_metal, 1), "II", "SS", "II", 'I', IRON.plate(), 'S', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.door_office, 1), "II", "SS", "II", 'I', KEY_PLANKS, 'S', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.door_bunker, 1), "II", "SS", "II", 'I', STEEL.plate(), 'S', PB.plate());

        addShapelessAuto(ItemStackUtil.itemStackFrom(Items.PAPER, 1), ItemStackUtil.itemStackFrom(ModItems.assembly_template, 1, OreDictionary.WILDCARD_VALUE));
        addShapelessAuto(ItemStackUtil.itemStackFrom(Items.PAPER, 1), ItemStackUtil.itemStackFrom(ModItems.chemistry_template, 1, OreDictionary.WILDCARD_VALUE));

        for (final Entry<String, Fluid> entry : FluidRegistry.getRegisteredFluids().entrySet()) {
            final Fluid fluid = entry.getValue();
            addShapelessAuto(ItemFFFluidDuct.getStackFromFluid(fluid), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 1), new IngredientContainsTag(ItemForgeFluidIdentifier.getStackFromFluid(entry.getValue())));

            addShapelessAuto(ItemFFFluidDuct.getStackFromFluid(fluid, 8), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 8), new IngredientContainsTag(ItemForgeFluidIdentifier.getStackFromFluid(entry.getValue())));
            // No more old pipe crafting
            // 	addShapelessAuto(ItemFFFluidDuct.getStackFromFluid(fluid), new Object[] { ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 1, OreDictionary.WILDCARD_VALUE), new IngredientContainsTag(ItemForgeFluidIdentifier.getStackFromFluid(entry.getValue())) });
            // 	addShapelessAuto(ItemFFFluidDuct.getStackFromFluid(fluid, 8), new Object[] { ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 8, OreDictionary.WILDCARD_VALUE), ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 8, OreDictionary.WILDCARD_VALUE), ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 8, OreDictionary.WILDCARD_VALUE), ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 8, OreDictionary.WILDCARD_VALUE), ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 8, OreDictionary.WILDCARD_VALUE), ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 8, OreDictionary.WILDCARD_VALUE), ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 8, OreDictionary.WILDCARD_VALUE), ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 8, OreDictionary.WILDCARD_VALUE), new IngredientContainsTag(ItemForgeFluidIdentifier.getStackFromFluid(entry.getValue())) });
        }

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.fluid_duct_mk2, 1), ItemStackUtil.itemStackFrom(ModItems.ff_fluid_duct, 1, OreDictionary.WILDCARD_VALUE));

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.redstone_depleted, 1), ItemStackUtil.itemStackFrom(ModItems.battery_su, 1, OreDictionary.WILDCARD_VALUE));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.redstone_depleted, 2), ItemStackUtil.itemStackFrom(ModItems.battery_su_l, 1, OreDictionary.WILDCARD_VALUE));
        addShapelessAuto(ItemStackUtil.itemStackFrom(Items.REDSTONE, 1), ModItems.redstone_depleted, ModItems.redstone_depleted);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bobmazon_materials), Items.BOOK, GOLD.nugget(), Items.STRING);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bobmazon_machines), Items.BOOK, GOLD.nugget(), ItemStackUtil.itemStackFrom(Items.DYE, 1, 1));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bobmazon_weapons), Items.BOOK, GOLD.nugget(), ItemStackUtil.itemStackFrom(Items.DYE, 1, 8));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.bobmazon_tools), Items.BOOK, GOLD.nugget(), ItemStackUtil.itemStackFrom(Items.DYE, 1, 2));

        addRecipeAuto(ItemStackUtil.itemStackFrom(Blocks.TORCH, 3), "L", "S", 'L', ModItems.lignite, 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(Blocks.TORCH, 6), "L", "S", 'L', ModItems.briquette_lignite, 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(Blocks.TORCH, 8), "L", "S", 'L', ANY_COKE.gem(), 'S', Items.STICK);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_missile_assembly, 1), "PWP", "SSS", "CCC", 'P', ModItems.pedestal_steel, 'W', ModItems.wrench, 'S', STEEL.plate(), 'C', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher, 1), "PPP", "SDS", "CCC", 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold, 'D', ModItems.pipes_steel, 'C', ModBlocks.concrete_smooth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher, 1), "PPP", "SDS", "CCC", 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold, 'D', ModItems.pipes_steel, 'C', ModBlocks.concrete);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_scaffold, 1), "SSS", "DCD", "SSS", 'S', ModBlocks.steel_scaffold, 'D', ModBlocks.fluid_duct_mk2, 'C', ModBlocks.red_cable);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_stability_10_flat, 1), "PSP", "P P", 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_stability_10_cruise, 1), "ASA", " S ", "PSP", 'A', TI.plate(), 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_stability_10_space, 1), "ASA", "PSP", 'A', AL.plate(), 'P', STEEL.ingot(), 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_stability_15_flat, 1), "ASA", "PSP", 'A', AL.plate(), 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_stability_15_thin, 1), "A A", "PSP", "PSP", 'A', AL.plate(), 'P', STEEL.plate(), 'S', ModBlocks.steel_scaffold);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_thruster_15_balefire_large_rad, 1), "CCC", "CTC", "CCC", 'C', ModItems.board_copper, 'T', ModItems.mp_thruster_15_balefire_large);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_10_kerosene_insulation, 1), "CCC", "CTC", "CCC", 'C', ANY_RUBBER.ingot(), 'T', ModItems.mp_fuselage_10_kerosene);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_10_long_kerosene_insulation, 1), "CCC", "CTC", "CCC", 'C', ANY_RUBBER.ingot(), 'T', ModItems.mp_fuselage_10_long_kerosene);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_15_kerosene_insulation, 1), "CCC", "CTC", "CCC", 'C', ANY_RUBBER.ingot(), 'T', ModItems.mp_fuselage_15_kerosene);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_10_solid_insulation, 1), "CCC", "CTC", "CCC", 'C', ANY_RUBBER.ingot(), 'T', ModItems.mp_fuselage_10_solid);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_10_long_solid_insulation, 1), "CCC", "CTC", "CCC", 'C', ANY_RUBBER.ingot(), 'T', ModItems.mp_fuselage_10_long_solid);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_15_solid_insulation, 1), "CCC", "CTC", "CCC", 'C', ANY_RUBBER.ingot(), 'T', ModItems.mp_fuselage_15_solid);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_15_solid_desh, 1), "CCC", "CTC", "CCC", 'C', DESH.ingot(), 'T', ModItems.mp_fuselage_15_solid);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_10_kerosene_metal, 1), "ICI", "CTC", "ICI", 'C', STEEL.plate(), 'I', IRON.plate(), 'T', ModItems.mp_fuselage_10_kerosene);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_10_long_kerosene_metal, 1), "ICI", "CTC", "ICI", 'C', STEEL.plate(), 'I', IRON.plate(), 'T', ModItems.mp_fuselage_10_long_kerosene);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_fuselage_15_kerosene_metal, 1), "ICI", "CTC", "ICI", 'C', STEEL.plate(), 'I', IRON.plate(), 'T', ModItems.mp_fuselage_15_kerosene);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_warhead_15_boxcar, 1), "SNS", "CBC", "SFS", 'S', STAR.ingot(), 'N', ModBlocks.det_nuke, 'C', ModItems.circuit_targeting_tier4, 'B', ModBlocks.boxcar, 'F', ModItems.tritium_deuterium_cake);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_chip_1, 1), "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier1, 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_chip_2, 1), "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier2, 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_chip_3, 1), "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier3, 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_chip_4, 1), "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier4, 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mp_chip_5, 1), "P", "C", "S", 'P', ModItems.plate_polymer, 'C', ModItems.circuit_targeting_tier5, 'S', ModBlocks.steel_scaffold);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.seg_10, 1), "P", "S", "B", 'P', AL.plate(), 'S', ModBlocks.steel_scaffold, 'B', ModBlocks.steel_beam);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.seg_15, 1), "PP", "SS", "BB", 'P', TI.plate(), 'S', ModBlocks.steel_scaffold, 'B', ModBlocks.steel_beam);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.seg_20, 1), "PGP", "SSS", "BBB", 'P', STEEL.plate(), 'G', GOLD.plate(), 'S', ModBlocks.steel_scaffold, 'B', ModBlocks.steel_beam);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher_core, 1), "SCS", "SIS", "BEB", 'S', ModBlocks.steel_scaffold, 'I', Blocks.IRON_BARS, 'C', ModItems.circuit_targeting_tier3, 'B', ModBlocks.struct_launcher, 'E', ModBlocks.machine_battery);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher_core_large, 1), "SIS", "ICI", "BEB", 'S', ModItems.circuit_red_copper, 'I', Blocks.IRON_BARS, 'C', ModItems.circuit_targeting_tier4, 'B', ModBlocks.struct_launcher, 'E', ModBlocks.machine_battery);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_soyuz_core, 1), "CUC", "TST", "TBT", 'C', ModItems.circuit_targeting_tier4, 'U', ModItems.upgrade_power_3, 'T', ModBlocks.barrel_steel, 'S', ModBlocks.steel_scaffold, 'B', ModBlocks.machine_lithium_battery);

        //addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.obj_tester, 1), new Object[] { "P", "I", "S", 'P', ModItems.polaroid, 'I', ModItems.flame_pony, 'S', STEEL.plate() });

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fence_metal, 6), "BIB", "BIB", 'B', Blocks.IRON_BARS, 'I', Items.IRON_INGOT);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.waste_trinitite), ItemStackUtil.itemStackFrom(Blocks.SAND, 1, 0), ModItems.trinitite);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.waste_trinitite_red), ItemStackUtil.itemStackFrom(Blocks.SAND, 1, 1), ModItems.trinitite);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sand_uranium), "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", U.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sand_polonium), "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", PO210.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sand_boron, 8), "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", B.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sand_lead, 8), "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", PB.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.sand_quartz, 1), "sand", "sand", NETHERQUARTZ.dust(), NETHERQUARTZ.dust());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rune_blank, 1), "PSP", "SDS", "PSP", 'P', ModItems.powder_magic, 'S', STAR.ingot(), 'D', KEY_CIRCUIT_BISMUTH);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rune_isa, 1), ModItems.rune_blank, ModItems.powder_spark_mix, ModItems.singularity_counter_resonant);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rune_dagaz, 1), ModItems.rune_blank, ModItems.powder_spark_mix, ModItems.black_hole);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rune_hagalaz, 1), ModItems.rune_blank, ModItems.powder_spark_mix, ModItems.singularity_super_heated);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rune_jera, 1), ModItems.rune_blank, ModItems.powder_spark_mix, ModItems.singularity_spark);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rune_thurisaz, 1), ModItems.rune_blank, ModItems.powder_spark_mix, ModItems.singularity);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_focus_blank, 1), "PAP", "GBG", "PCP", 'P', ModItems.plate_dineutronium, 'G', ModBlocks.reinforced_glass, 'A', ModItems.rune_thurisaz, 'B', ModItems.hull_big_aluminium, 'C', ModItems.rune_jera);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_lens, 1), "PFP", "GEG", "PFP", 'P', ModItems.rune_dagaz, 'G', ModItems.ams_focus_blank, 'E', ModItems.upgrade_overdrive_3, 'F', ModItems.fusion_shield_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_focus_omega, 1), "PFP", "REG", "PFP", 'P', ModBlocks.dfc_stabilizer, 'R', ModItems.ams_focus_limiter, 'G', ModItems.ams_focus_booster, 'E', ModItems.laser_crystal_digamma, 'F', ModItems.fusion_shield_vaporwave);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_focus_booster, 1), "PFP", "GEG", "PFP", 'P', ModItems.rune_hagalaz, 'G', ModItems.ams_lens, 'E', ModItems.upgrade_screm, 'F', ModItems.fusion_shield_desh);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_focus_limiter, 1), "PFP", "GEG", "PFP", 'P', ModItems.rune_isa, 'G', ModItems.ams_focus_blank, 'E', ModItems.upgrade_power_3, 'F', ModItems.inf_water_mk4);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_blank, 1), "TET", "ETE", "TET", 'T', TS.dust(), 'E', EUPH.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_iron, 1), ModItems.ams_catalyst_blank, ModItems.rune_thurisaz, ModItems.rune_thurisaz, ModItems.rune_thurisaz, ModItems.rune_thurisaz, IRON.dust(), IRON.dust(), IRON.dust(), IRON.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_copper, 1), ModItems.ams_catalyst_blank, ModItems.rune_thurisaz, ModItems.rune_thurisaz, ModItems.rune_thurisaz, ModItems.rune_isa, CU.dust(), CU.dust(), CU.dust(), CU.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_aluminium, 1), ModItems.ams_catalyst_blank, ModItems.rune_thurisaz, ModItems.rune_thurisaz, ModItems.rune_isa, ModItems.rune_isa, AL.dust(), AL.dust(), AL.dust(), AL.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_lithium, 1), ModItems.ams_catalyst_blank, ModItems.rune_thurisaz, ModItems.rune_isa, ModItems.rune_isa, ModItems.rune_isa, LI.dust(), LI.dust(), LI.dust(), LI.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_beryllium, 1), ModItems.ams_catalyst_blank, ModItems.rune_isa, ModItems.rune_isa, ModItems.rune_isa, ModItems.rune_dagaz, BE.dust(), BE.dust(), BE.dust(), BE.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_tungsten, 1), ModItems.ams_catalyst_blank, ModItems.rune_isa, ModItems.rune_isa, ModItems.rune_dagaz, ModItems.rune_dagaz, W.dust(), W.dust(), W.dust(), W.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_cobalt, 1), ModItems.ams_catalyst_blank, ModItems.rune_isa, ModItems.rune_dagaz, ModItems.rune_dagaz, ModItems.rune_dagaz, CO.dust(), CO.dust(), CO.dust(), CO.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_niobium, 1), ModItems.ams_catalyst_blank, ModItems.rune_dagaz, ModItems.rune_dagaz, ModItems.rune_dagaz, ModItems.rune_hagalaz, NB.dust(), NB.dust(), NB.dust(), NB.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_cerium, 1), ModItems.ams_catalyst_blank, ModItems.rune_dagaz, ModItems.rune_dagaz, ModItems.rune_hagalaz, ModItems.rune_hagalaz, CE.dust(), CE.dust(), CE.dust(), CE.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_thorium, 1), ModItems.ams_catalyst_blank, ModItems.rune_dagaz, ModItems.rune_hagalaz, ModItems.rune_hagalaz, ModItems.rune_hagalaz, TH232.dust(), TH232.dust(), TH232.dust(), TH232.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_strontium, 1), ModItems.ams_catalyst_blank, ModItems.rune_hagalaz, ModItems.rune_hagalaz, ModItems.rune_hagalaz, ModItems.rune_hagalaz, SR.dust(), SR.dust(), SR.dust(), SR.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_caesium, 1), ModItems.ams_catalyst_blank, ModItems.rune_hagalaz, ModItems.rune_hagalaz, ModItems.rune_hagalaz, ModItems.rune_jera, CS.dust(), CS.dust(), CS.dust(), CS.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_schrabidium, 1), ModItems.ams_catalyst_blank, ModItems.rune_hagalaz, ModItems.rune_hagalaz, ModItems.rune_jera, ModItems.rune_jera, SA326.dust(), SA326.dust(), SA326.dust(), SA326.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_euphemium, 1), ModItems.ams_catalyst_blank, ModItems.rune_hagalaz, ModItems.rune_jera, ModItems.rune_jera, ModItems.rune_jera, EUPH.dust(), EUPH.dust(), EUPH.dust(), EUPH.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ams_catalyst_dineutronium, 1), ModItems.ams_catalyst_blank, ModItems.rune_jera, ModItems.rune_jera, ModItems.rune_jera, ModItems.rune_jera, DNT.dust(), DNT.dust(), DNT.dust(), DNT.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.dfc_core, 1), "DLD", "LML", "DLD", 'D', ModItems.ingot.getItemStack(MaterialMineral.BISMUTH), 'L', DNT.block(), 'M', KEY_CIRCUIT_BISMUTH);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.dfc_emitter, 1), "SDS", "TXL", "SDS", 'S', OSMIRIDIUM.ingot(), 'D', ModItems.plate_desh, 'T', ModBlocks.machine_transformer_dnt, 'X', ModItems.crystal_xen, 'L', ModItems.sat_head_laser);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.dfc_receiver, 1), "SDS", "TXL", "SDS", 'S', OSMIRIDIUM.ingot(), 'D', ModItems.plate_desh, 'T', ModBlocks.machine_transformer_dnt, 'X', ItemStackUtil.itemStackFrom(ModBlocks.sellafield_core), 'L', ModItems.hull_small_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.dfc_injector, 1), "SDS", "TXL", "SDS", 'S', OSMIRIDIUM.ingot(), 'D', CMB.plate(), 'T', ModBlocks.machine_fluidtank, 'X', ModItems.motor, 'L', ModItems.pipes_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.dfc_stabilizer, 1), "SDS", "TXL", "SDS", 'S', OSMIRIDIUM.ingot(), 'D', ModItems.plate_desh, 'T', ModItems.singularity_spark, 'X', ModItems.magnet_circular, 'L', ModItems.crystal_xen);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barrel_plastic, 1), "IPI", "I I", "IPI", 'I', ModItems.plate_polymer, 'P', AL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barrel_iron, 1), "IPI", "I I", "IPI", 'I', IRON.plate(), 'P', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barrel_steel, 1), "IPI", "IOI", "IPI", 'I', STEEL.plate(), 'P', STEEL.ingot(), 'O', ModItems.oil_tar);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barrel_antimatter, 1), "IPI", "IPI", "IPI", 'I', BIGMT.plate(), 'P', ModItems.coil_advanced_torus);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.tesla, 1), "CCC", "PIP", "WTW", 'C', ModItems.coil_copper, 'I', IRON.ingot(), 'P', ANY_PLASTIC.ingot(), 'T', ModBlocks.machine_transformer, 'W', KEY_PLANKS);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bottle_mercury, 1), "###", "#B#", "###", '#', ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 'B', Items.GLASS_BOTTLE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY), 8), "#", '#', ModItems.bottle_mercury);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire, 1), "###", "###", "###", '#', ModItems.egg_balefire_shard);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.egg_balefire_shard, 9), "#", '#', ModItems.egg_balefire);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44_phosphorus, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_44, 'A', P_WHITE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg_phosphorus, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50bmg, 'A', P_WHITE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_phosphorus, 1), "G", "R", 'G', P_WHITE.ingot(), 'R', ModItems.ammo_rocket);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_phosphorus, 2), "GIG", 'G', ModItems.ammo_grenade, 'I', P_WHITE.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_u233, 1), "###", "###", "###", '#', U233.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_u235, 1), "###", "###", "###", '#', U235.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_u238, 1), "###", "###", "###", '#', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_uranium_fuel, 1), "###", "###", "###", '#', ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_neptunium, 1), "###", "###", "###", '#', NP237.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_polonium, 1), "###", "###", "###", '#', PO210.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_plutonium, 1), "###", "###", "###", '#', PU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_pu238, 1), "###", "###", "###", '#', PU238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_pu239, 1), "###", "###", "###", '#', PU239.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_pu240, 1), "###", "###", "###", '#', PU240.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_plutonium_fuel, 1), "###", "###", "###", '#', ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_thorium_fuel, 1), "###", "###", "###", '#', ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_solinium, 1), "###", "###", "###", '#', SA327.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_schrabidium_fuel, 1), "###", "###", "###", '#', ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U233), 9), "#", '#', U233.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U235), 9), "#", '#', U235.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U238), 9), "#", '#', U238.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), 9), "#", '#', ModBlocks.block_uranium_fuel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM), 9), "#", '#', NP237.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLONIUM), 9), "#", '#', PO210.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM), 9), "#", '#', PU.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU238), 9), "#", '#', PU238.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU239), 9), "#", '#', PU239.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU240), 9), "#", '#', PU240.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 9), "#", '#', ModBlocks.block_plutonium_fuel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL), 9), "#", '#', ModBlocks.block_thorium_fuel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SOLINIUM), 9), "#", '#', SA327.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 9), "#", '#', ModBlocks.block_schrabidium_fuel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge, 12), " I ", "GCL", " P ", 'I', ModItems.pellet_buckshot, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_slug, 12), " I ", "GCL", " P ", 'I', PB.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_flechette, 12), " I ", "GCL", " P ", 'I', ModItems.pellet_flechette, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_explosive, 4), " I ", "GCL", " P ", 'I', Blocks.TNT, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_explosive, 6), " I ", "GCL", " P ", 'I', ANY_PLASTICEXPLOSIVE.ingot(), 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_semtex, 4), " I ", "GCL", " P ", 'I', ModBlocks.det_miner, 'G', ANY_SMOKELESS.dust(), 'C', ModItems.casing_50, 'P', ModItems.primer_50, 'L', ANY_RUBBER.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_mox_fuel, 1), "###", "###", "###", '#', ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_lithium, 1), "###", "###", "###", '#', LI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_white_phosphorus, 1), "###", "###", "###", '#', P_WHITE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_red_phosphorus, 1), "###", "###", "###", '#', P_RED.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_insulator, 1), "###", "###", "###", '#', ModItems.plate_polymer);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_asbestos, 1), "###", "###", "###", '#', ASBESTOS.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_fiberglass, 1), "###", "###", "###", '#', FIBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_cobalt, 1), "###", "###", "###", '#', CO.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM), 1), "###", "###", "###", '#', ModItems.wire_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER), 1), "###", "###", "###", '#', ModItems.wire_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN), 1), "###", "###", "###", '#', ModItems.wire_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER), 1), "###", "###", "###", '#', ModItems.wire_red_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY), 1), "###", "###", "###", '#', ModItems.wire_advanced_alloy);
        addRecipeAuto(ItemStackUtil.itemStackFrom(Items.GOLD_INGOT, 1), "###", "###", "###", '#', ModItems.wire_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM), 1), "###", "###", "###", '#', ModItems.wire_schrabidium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN), 1), "###", "###", "###", '#', ModItems.wire_magnetized_tungsten);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), 9), "#", '#', ModBlocks.block_mox_fuel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.lithium, 9), "#", '#', LI.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PHOSPHORUS), 9), "#", '#', P_WHITE.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.powder_fire, 9), "#", '#', P_RED.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 9), "#", '#', ModBlocks.block_insulator);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 8), "DD", 'D', ANY_PLASTIC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 8), "DD", 'D', ANY_RUBBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 16), "DD", 'D', FIBER.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 16), "DD", 'D', ASBESTOS.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 4), "SWS", 'S', Items.STRING, 'W', Blocks.WOOL);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 4), "BB", 'B', "ingotBrick");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_polymer, 4), "BB", 'B', "ingotNetherBrick");

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ASBESTOS), 9), "#", '#', ASBESTOS.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.FIBERGLASS), 9), "#", '#', FIBER.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COBALT), 9), "#", '#', CO.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mechanism_special, 1), "PCI", "ISS", "PCI", 'P', ModItems.plate_desh, 'C', ModItems.coil_advanced_alloy, 'I', STAR.ingot(), 'S', ModItems.circuit_targeting_tier3);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_ks23, 1), "PPM", "SWL", 'P', STEEL.plate(), 'M', ModItems.mechanism_rifle_1, 'S', Items.STICK, 'W', ModItems.wire_tungsten, 'L', KEY_LOG);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.gun_sauer, 1), ModItems.ducttape, ModItems.gun_ks23, Blocks.LEVER, ModItems.gun_ks23);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gun_flamer, 1), "WPP", "SCT", "WMI", 'W', ModItems.wire_gold, 'P', ModItems.pipes_steel, 'S', ModItems.hull_small_steel, 'C', ModItems.coil_tungsten, 'T', ModItems.tank_steel, 'M', ModItems.mechanism_launcher_1, 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_fuel, 1), " P ", "BDB", " P ", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'D', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_fuel_napalm, 1), " P ", "BDB", " P ", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'D', ModItems.canister_napalm);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_fuel_phosphorus, 1), "CPC", "CDC", "CPC", 'C', COAL.dust(), 'P', P_WHITE.ingot(), 'D', ModItems.ammo_fuel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_fuel_gas, 1), "PDP", "BDB", "PDP", 'P', STEEL.plate(), 'B', ModItems.bolt_tungsten, 'D', ModItems.pellet_gas);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_fuel_vaporizer, 1), "PSP", "SNS", "PSP", 'P', P_WHITE.ingot(), 'S', ModItems.crystal_sulfur, 'N', ModItems.ammo_fuel_napalm);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.det_cord, 8), "TNT", "NGN", "TNT", 'T', STEEL.plate(), 'N', KNO.dust(), 'G', ANY_GUNPOWDER.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.det_charge, 1), "PDP", "DTD", "PDP", 'P', STEEL.plate(), 'D', ModBlocks.det_cord, 'T', ANY_PLASTICEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.det_n2, 1), "PDT", "DDD", "PDP", 'P', ModItems.plate_steel, 'D', ModItems.n2_charge, 'T', ModItems.circuit_targeting_tier3);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.det_nuke, 1), "PDP", "DCD", "PDP", 'P', ModItems.plate_desh, 'D', ModItems.man_explosive8, 'C', ModItems.man_core);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.det_bale, 1), "DAP", "DCD", "DBD", 'D', ModItems.plate_titanium, 'A', ModItems.powder_power, 'B', ModItems.powder_magic, 'C', ModItems.egg_balefire, 'P', ModItems.circuit_targeting_tier4);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.det_miner, 3), "FFF", "ITI", "ITI", 'F', Items.FLINT, 'I', IRON.plate(), 'T', Blocks.TNT);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.det_miner, 12), "FFF", "ITI", "ITI", 'F', Items.FLINT, 'I', STEEL.plate(), 'T', ANY_PLASTICEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_helmet, 1), "EEE", "E E", 'E', CO.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_plate, 1), "E E", "EEE", "EEE", 'E', CO.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_legs, 1), "EEE", "E E", "E E", 'E', CO.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_boots, 1), "E E", "E E", 'E', CO.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.t45_helmet, 1), "PPC", "PBP", "IXI", 'P', ModItems.plate_armor_titanium, 'C', ModItems.circuit_targeting_tier3, 'I', ANY_RUBBER.ingot(), 'X', ModItems.gas_mask_m65, 'B', ModItems.titanium_helmet);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.t45_plate, 1), "MPM", "TBT", "PPP", 'M', ModItems.motor, 'P', ModItems.plate_armor_titanium, 'T', ModItems.gas_canister, 'B', ModItems.titanium_plate);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.t45_legs, 1), "MPM", "PBP", "P P", 'M', ModItems.motor, 'P', ModItems.plate_armor_titanium, 'B', ModItems.titanium_legs);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.t45_boots, 1), "P P", "PBP", 'P', ModItems.plate_armor_titanium, 'B', ModItems.titanium_boots);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bj_helmet, 1), "SBS", " C ", " I ", 'S', Items.STRING, 'B', ItemStackUtil.itemStackFrom(Blocks.WOOL, 1, 15), 'C', ModItems.circuit_targeting_tier4, 'I', STAR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bj_plate, 1), "N N", "MSM", "NCN", 'N', ModItems.plate_armor_lunar, 'M', ModItems.motor_desh, 'S', ModItems.starmetal_plate, 'C', ModItems.circuit_targeting_tier5);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bj_plate_jetpack, 1), "NFN", "TPT", "ICI", 'N', ModItems.plate_armor_lunar, 'F', ModItems.fins_quad_titanium, 'T', new IngredientContainsTag(ItemFluidTank.getFullTank(ModForgeFluids.xenon)), 'P', ModItems.bj_plate, 'I', ModItems.mp_thruster_10_xenon, 'C', P_RED.crystal());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bj_legs, 1), "NBN", "MSM", "N N", 'N', ModItems.plate_armor_lunar, 'M', ModItems.motor_desh, 'S', ModItems.starmetal_legs, 'B', STAR.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bj_boots, 1), "N N", "BSB", 'N', ModItems.plate_armor_lunar, 'S', ModItems.starmetal_boots, 'B', STAR.block());

        reg3();
    }

    public static void reg3() {
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_sturdy, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', KEY_PLANKS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_iron, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_gold, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', GOLD.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_aluminium, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', AL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_copper, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_titanium, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', TI.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_lead, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', PB.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_cobalt, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', CO.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_steel, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_tungsten, 8), "LLL", "L#L", "LLL", 'L', Blocks.LADDER, '#', W.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe, 6), "PPP", 'P', ModItems.hull_small_steel);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe, 1), ModBlocks.deco_pipe_rim);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe, 1), ModBlocks.deco_pipe_framed);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe, 1), ModBlocks.deco_pipe_quad);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_rbmk, 8), ModBlocks.rbmk_blank);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_rbmk_smooth, 1), ModBlocks.deco_rbmk);


        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_rim, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe, 'C', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_quad, 4), "PP", "PP", 'P', ModBlocks.deco_pipe);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_framed, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe, 'C', Blocks.IRON_BARS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_framed, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_rim, 'C', Blocks.IRON_BARS);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_rusted, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe, 'C', IRON.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_rim_rusted, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_rim, 'C', IRON.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_quad_rusted, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_quad, 'C', IRON.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_framed_rusted, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_framed, 'C', IRON.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_green, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe, 'C', "dyeGreen");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_rim_green, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_rim, 'C', "dyeGreen");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_quad_green, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_quad, 'C', "dyeGreen");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_framed_green, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_framed, 'C', "dyeGreen");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_green_rusted, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_green, 'C', IRON.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_rim_green_rusted, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_rim_green, 'C', IRON.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_quad_green_rusted, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_quad_green, 'C', IRON.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_framed_green_rusted, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_framed_green, 'C', IRON.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_red, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe, 'C', "dyeRed");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_rim_red, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_rim, 'C', "dyeRed");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_quad_red, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_quad, 'C', "dyeRed");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_framed_red, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_framed, 'C', "dyeRed");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_marked, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_green, 'C', "dyeGreen");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_rim_marked, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_rim_green, 'C', "dyeGreen");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_quad_marked, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_quad_green, 'C', "dyeGreen");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.deco_pipe_framed_marked, 8), "PPP", "PCP", "PPP", 'P', ModBlocks.deco_pipe_framed_green, 'C', "dyeGreen");

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rpa_helmet, 1), "PXP", "PLP", "KFK", 'L', ModItems.cmb_helmet, 'K', ModItems.plate_kevlar, 'P', ModItems.plate_armor_lunar, 'F', ModItems.gas_mask_m65, 'X', ModItems.circuit_targeting_tier5);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rpa_plate, 1), "MXM", "KLK", "PPP", 'L', ModItems.cmb_plate, 'K', ModItems.plate_kevlar, 'P', ModItems.plate_armor_lunar, 'M', ModItems.motor_desh, 'X', ModItems.circuit_bismuth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rpa_legs, 1), "MXM", "KLK", "P P", 'L', ModItems.cmb_legs, 'K', ModItems.plate_kevlar, 'P', ModItems.plate_armor_lunar, 'M', ModItems.motor_desh, 'X', ModItems.circuit_tantalium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rpa_boots, 1), "P P", "PLP", 'L', ModItems.cmb_boots, 'P', ModItems.plate_armor_lunar);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ajr_helmet, 1), "PPC", "PBP", "IXI", 'P', ModItems.plate_armor_ajr, 'C', ModItems.circuit_targeting_tier4, 'I', ANY_PLASTIC.ingot(), 'X', ModItems.gas_mask_m65, 'B', ModItems.alloy_helmet);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ajr_plate, 1), "MPM", "TBT", "PPP", 'M', ModItems.motor_desh, 'P', ModItems.plate_armor_ajr, 'T', ModItems.gas_canister, 'B', ModItems.alloy_plate);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ajr_legs, 1), "MPM", "PBP", "P P", 'M', ModItems.motor_desh, 'P', ModItems.plate_armor_ajr, 'B', ModItems.alloy_legs);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ajr_boots, 1), "P P", "PBP", 'P', ModItems.plate_armor_ajr, 'B', ModItems.alloy_boots);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ajro_helmet, 1), ModItems.ajr_helmet, "dyeRed", "dyeBlack");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ajro_plate, 1), ModItems.ajr_plate, "dyeRed", "dyeBlack");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ajro_legs, 1), ModItems.ajr_legs, "dyeRed", "dyeBlack");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ajro_boots, 1), ModItems.ajr_boots, "dyeRed", "dyeBlack");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fau_helmet, 1), "PWP", "PBP", "FSF", 'P', ModItems.plate_armor_fau, 'W', ItemStackUtil.itemStackFrom(Blocks.WOOL, 1, 14), 'B', ModItems.schrabidium_helmet, 'F', ModItems.gas_mask_filter, 'S', ModItems.pipes_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fau_plate, 1), "MCM", "PBP", "PSP", 'M', ModItems.motor_desh, 'C', ModItems.demon_core_closed, 'P', ModItems.plate_armor_fau, 'B', ModItems.schrabidium_plate, 'S', ModBlocks.ancient_scrap);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fau_legs, 1), "MPM", "PBP", "PDP", 'M', ModItems.motor_desh, 'P', ModItems.plate_armor_fau, 'B', ModItems.schrabidium_legs, 'D', PO210.billet());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.fau_boots, 1), "PDP", "PBP", 'P', ModItems.plate_armor_fau, 'D', PO210.billet(), 'B', ModItems.schrabidium_boots);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.dns_helmet, 1), "PCP", "PBP", "PSP", 'P', ModItems.plate_armor_dnt, 'S', ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL), 'B', ModItems.bj_helmet, 'C', ModItems.circuit_targeting_tier6);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.dns_plate, 1), "PCP", "PBP", "PSP", 'P', ModItems.plate_armor_dnt, 'S', ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL), 'B', ModItems.bj_plate_jetpack, 'C', ModItems.singularity_spark);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.dns_legs, 1), "PCP", "PBP", "PSP", 'P', ModItems.plate_armor_dnt, 'S', ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL), 'B', ModItems.bj_legs, 'C', ModItems.coin_worm);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.dns_boots, 1), "PCP", "PBP", "PSP", 'P', ModItems.plate_armor_dnt, 'S', ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL), 'B', ModItems.bj_boots, 'C', ModItems.demon_core_closed);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell, 4), " T ", "GHG", "CCC", 'T', Blocks.TNT, 'G', Items.GUNPOWDER, 'H', ModItems.hull_small_steel, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell, 5), " T ", "GHG", "CCC", 'T', Blocks.TNT, 'G', ModItems.ballistite, 'H', ModItems.hull_small_steel, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell, 6), " T ", "GHG", "CCC", 'T', Blocks.TNT, 'G', ModItems.cordite, 'H', ModItems.hull_small_steel, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_explosive, 4), " T ", "GHG", "CCC", 'T', ANY_PLASTICEXPLOSIVE.ingot(), 'G', Items.GUNPOWDER, 'H', ModItems.hull_small_steel, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_explosive, 5), " T ", "GHG", "CCC", 'T', ANY_PLASTICEXPLOSIVE.ingot(), 'G', ModItems.ballistite, 'H', ModItems.hull_small_steel, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_explosive, 6), " T ", "GHG", "CCC", 'T', ANY_PLASTICEXPLOSIVE.ingot(), 'G', ModItems.cordite, 'H', ModItems.hull_small_steel, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_apfsds_t, 4), " I ", "GIG", "CCC", 'I', W.ingot(), 'G', Items.GUNPOWDER, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_apfsds_t, 5), " I ", "GIG", "CCC", 'I', W.ingot(), 'G', ModItems.ballistite, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_apfsds_t, 6), " I ", "GIG", "CCC", 'I', W.ingot(), 'G', ModItems.cordite, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_apfsds_du, 4), " I ", "GIG", "CCC", 'I', U238.ingot(), 'G', Items.GUNPOWDER, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_apfsds_du, 5), " I ", "GIG", "CCC", 'I', U238.ingot(), 'G', ModItems.ballistite, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_apfsds_du, 6), " I ", "GIG", "CCC", 'I', U238.ingot(), 'G', ModItems.cordite, 'C', CU.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_shell_w9, 1), " P ", "NSN", " P ", 'P', PU239.nugget(), 'N', OreDictManager.getReflector(), 'S', ModItems.ammo_shell_explosive);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SMORE)), Items.WHEAT, ItemStackUtil.itemStackFrom(ModItems.marshmallow_roasted, 1), ItemStackUtil.itemStackFrom(Items.DYE, 1, 3));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.marshmallow), Items.STICK, Items.SUGAR, Items.WHEAT_SEEDS);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coltass, 1), "ACA", "CXC", "ACA", 'A', ALLOY.ingot(), 'C', ModItems.cinnebar, 'X', Items.COMPASS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bismuth_tool, 1), "TBT", "SRS", "SCS", 'T', TA.nugget(), 'B', ANY_BISMOID.nugget(), 'S', TCALLOY.ingot(), 'R', ModItems.reacher, 'C', ModItems.circuit_aluminium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.reacher, 1), "BIB", "P P", "B B", 'B', ModItems.bolt_tungsten, 'I', W.ingot(), 'P', ANY_RUBBER.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_tcalloy, 1), STEEL.dust(), TC99.nugget());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.depth_brick, 4), "CC", "CC", 'C', ModBlocks.stone_depth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.depth_tiles, 4), "CC", "CC", 'C', ModBlocks.depth_brick);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.depth_nether_brick, 4), "CC", "CC", 'C', ModBlocks.stone_depth_nether);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.depth_nether_tiles, 4), "CC", "CC", 'C', ModBlocks.depth_nether_brick);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.basalt_polished, 4), "CC", "CC", 'C', ModBlocks.basalt_smooth);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.basalt_brick, 4), "CC", "CC", 'C', ModBlocks.basalt_polished);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.basalt_tiles, 4), "CC", "CC", 'C', ModBlocks.basalt_brick);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.barrel_tcalloy, 1), "IPI", "I I", "IPI", 'I', TCALLOY.ingot(), 'P', TI.plate());

        addMineralSet(ModItems.nugget.getItemStack(MaterialMineral.CADMIUM), ModItems.ingot.getItemStack(MaterialMineral.CADMIUM), ModBlocks.block_cadmium);
        addMineralSet(ModItems.nugget.getItemStack(MaterialMineral.BISMUTH), ModItems.ingot.getItemStack(MaterialMineral.BISMUTH), ModBlocks.block_bismuth);
        addMineralSet(ModItems.nugget.getItemStack(MaterialMineral.TANTALIUM), ModItems.ingot.getItemStack(MaterialMineral.TANTALIUM), ModBlocks.block_tantalium);
        addMineralSet(ModItems.nugget.getItemStack(MaterialMineral.ZIRCONIUM), ModItems.ingot.getItemStack(MaterialMineral.ZIRCONIUM), ModBlocks.block_zirconium);
        addMineralSet(ModItems.nugget.getItemStack(MaterialMineral.DINEUTRONIUM), ModItems.ingot.getItemStack(MaterialMineral.DINEUTRONIUM), ModBlocks.block_dineutronium);

        add1To9Pair(ModItems.powder_xe135, ModItems.powder_xe135_tiny);
        add1To9Pair(ModItems.powder_cs137, ModItems.powder_cs137_tiny);
        add1To9Pair(ModItems.powder_i131, ModItems.powder_i131_tiny);
        add1To9Pair(ModItems.powder_iodine, ModItems.powder_iodine_tiny);
        add1To9Pair(ModItems.powder_sr90, ModItems.powder_sr90_tiny);
        add1To9Pair(ModItems.powder_co60, ModItems.powder_co60_tiny);
        add1To9Pair(ModItems.powder_au198, ModItems.powder_au198_tiny);
        add1To9Pair(ModItems.powder_pb209, ModItems.powder_pb209_tiny);
        add1To9Pair(ModItems.powder_at209, ModItems.powder_at209_tiny);
        add1To9Pair(ModItems.powder_ac227, ModItems.powder_ac227_tiny);
        add1To9Pair(ModItems.powder_radspice, ModItems.powder_radspice_tiny);

        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.TECHNETIUM), ModItems.nugget.getItemStack(MaterialMineral.TECHNETIUM));
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.ARSENIC), ModItems.nugget.getItemStack(MaterialMineral.ARSENIC));
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.CO60), ModItems.nugget.getItemStack(MaterialMineral.CO60));
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.AU198), ModItems.nugget.getItemStack(MaterialMineral.AU198));

        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.PU241), ModItems.nugget.getItemStack(MaterialMineral.PU241));
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.AM241), ModItems.nugget.getItemStack(MaterialMineral.AM241));
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.AM242), ModItems.nugget.getItemStack(MaterialMineral.AM242));
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.AM_MIX), ModItems.nugget.getItemStack(MaterialMineral.AM_MIX));
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.AMERICIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.AMERICIUM_FUEL));

        add1To9Pair(ModItems.powder_coal, ModItems.powder_coal_tiny);

        add1To9Pair(ModBlocks.sand_gold, ModItems.powder_gold);
        add1To9Pair(ModBlocks.sand_gold198, ModItems.powder_au198);
        add1To9Pair(ModBlocks.block_coltan, ModItems.fragment_coltan);
        add1To9Pair(ModBlocks.block_smore, ModItems.ingot.getItemStack(MaterialMineral.SMORE));
        add1To9Pair(ModItems.nuclear_waste_vitrified, ModItems.nuclear_waste_vitrified_tiny);
        add1To9Pair(ModBlocks.block_waste_vitrified, ModItems.nuclear_waste_vitrified);
        add1To9Pair(ModBlocks.block_niobium, ModItems.ingot.getItemStack(MaterialMineral.NIOBIUM));
        add1To9Pair(ModBlocks.block_boron, ModItems.ingot.getItemStack(MaterialMineral.BORON));
        add1To9Pair(ModItems.powder_boron, ModItems.powder_boron_tiny);
        add1To9Pair(ModBlocks.block_graphite, ModItems.ingot.getItemStack(MaterialMineral.GRAPHITE));
        add1To9Pair(ModBlocks.block_coke, ModItems.coke);
        add1To9Pair(ModBlocks.block_lignite, ModItems.lignite);
        add1To9Pair(ModBlocks.block_coal_infernal, ModItems.coal_infernal);
        add1To9Pair(ModBlocks.block_solid_fuel, ModItems.solid_fuel);
        add1To9Pair(ModBlocks.block_solid_fuel_presto, ModItems.solid_fuel_presto);
        add1To9Pair(ModBlocks.block_solid_fuel_presto_triplet, ModItems.solid_fuel_presto_triplet);

        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.OSMIRIDIUM), ModItems.nugget.getItemStack(MaterialMineral.OSMIRIDIUM));
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.RADSPICE), ModItems.nugget.getItemStack(MaterialMineral.RADSPICE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_bismuth_raw, 1), "RPR", "ABA", "RPR", 'R', REDSTONE.dust(), 'P', ANY_PLASTIC.ingot(), 'A', (GeneralConfig.enable528 ? ModItems.circuit_tantalium : ASBESTOS.ingot()), 'B', ANY_BISMOID.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_arsenic_raw, 1), "RPR", "ABA", "RPR", 'R', REDSTONE.dust(), 'P', ANY_PLASTIC.ingot(), 'A', (GeneralConfig.enable528 ? ModItems.circuit_tantalium : ASBESTOS.ingot()), 'B', AS.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_tantalium_raw, 1), "RWR", "PTP", "RWR", 'R', REDSTONE.dust(), 'W', ModItems.wire_gold, 'P', CU.plate(), 'T', TA.nugget());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.inf_water_mk2, 1), "BPB", "PTP", "BPB", 'B', ModItems.inf_water, 'P', ModBlocks.fluid_duct_mk2, 'T', ModBlocks.barrel_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.inf_water_mk3, 1), "BPB", "PTP", "BPB", 'B', ModItems.inf_water_mk2, 'P', ModBlocks.fluid_duct_mk2, 'T', ModBlocks.machine_fluidtank);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.inf_water_mk4, 1), "BPB", "PTP", "BPB", 'B', ModItems.inf_water_mk3, 'P', ModBlocks.fluid_duct_mk2, 'T', ModBlocks.machine_bat9000);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_condenser), "SIS", "ICI", "SIS", 'S', STEEL.ingot(), 'I', IRON.plate(), 'C', ModItems.board_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_storage_drum), "LLL", "L#L", "LLL", 'L', PB.plate(), '#', ModItems.tank_steel);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_uranium), "NBN", "PCP", "NBN", 'N', GOLD.nugget(), 'B', U238.billet(), 'P', PB.plate(), 'C', ModItems.thermo_element);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_technetium), "NBN", "PCP", "NBN", 'N', GOLD.nugget(), 'B', TC99.billet(), 'P', PB.plate(), 'C', ModItems.battery_sc_uranium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_plutonium), "NBN", "PCP", "NBN", 'N', TC99.nugget(), 'B', PU238.billet(), 'P', PB.plate(), 'C', ModItems.battery_sc_technetium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_polonium), "NBN", "PCP", "NBN", 'N', TC99.nugget(), 'B', PO210.billet(), 'P', ANY_PLASTIC.ingot(), 'C', ModItems.battery_sc_plutonium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_gold), "NBN", "PCP", "NBN", 'N', TA.nugget(), 'B', AU198.billet(), 'P', ANY_PLASTIC.ingot(), 'C', ModItems.battery_sc_polonium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_lead), "NBN", "PCP", "NBN", 'N', TA.nugget(), 'B', PB209.billet(), 'P', ANY_PLASTIC.ingot(), 'C', ModItems.battery_sc_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_americium), "NBN", "PCP", "NBN", 'N', TA.nugget(), 'B', AM241.billet(), 'P', ANY_PLASTIC.ingot(), 'C', ModItems.battery_sc_lead);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_balefire), "NBN", "PCP", "NBN", 'N', ModItems.nugget.getItemStack(MaterialMineral.RADSPICE), 'B', ModItems.pellet_rtg_balefire, 'P', ANY_PLASTIC.ingot(), 'C', ModItems.battery_sc_americium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_schrabidium), "NBN", "PCP", "NBN", 'N', ModItems.nugget.getItemStack(MaterialMineral.UNOBTAINIUM_GREATER), 'B', SA326.billet(), 'P', ANY_PLASTIC.ingot(), 'C', ModItems.battery_sc_balefire);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_yharonite), "NBN", "PCP", "NBN", 'N', DNT.nugget(), 'B', ModItems.billet.getItemStack(MaterialMineral.YHARONITE), 'P', ANY_PLASTIC.ingot(), 'C', ModItems.battery_sc_schrabidium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.battery_sc_electronium), "NBN", "PCP", "NBN", 'N', ModItems.nugget_u238m2, 'B', ModItems.glitch, 'P', ModItems.ingot.getItemStack(MaterialMineral.ELECTRONIUM), 'C', ModItems.battery_sc_yharonite);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_alloy, 1), "WWW", "WCW", "WWW", 'W', ModItems.wire_advanced_alloy, 'C', ModBlocks.fusion_conductor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_gold, 1), "PGP", "PCP", "PGP", 'G', GOLD.dust(), 'C', ModBlocks.hadron_coil_alloy, 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_neodymium, 1), "G", "C", "G", 'G', ND.dust(), 'C', ModBlocks.hadron_coil_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_magtung, 1), "WWW", "WCW", "WWW", 'W', ModItems.wire_magnetized_tungsten, 'C', ModBlocks.fwatz_conductor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_schrabidium, 1), "WWW", "WCW", "WWW", 'W', ModItems.wire_schrabidium, 'C', ModBlocks.hadron_coil_magtung);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_schrabidate, 1), " S ", "SCS", " S ", 'S', SBD.dust(), 'C', ModBlocks.hadron_coil_schrabidium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_starmetal, 1), "SNS", "SCS", "SNS", 'S', STAR.ingot(), 'N', ModBlocks.hadron_coil_neodymium, 'C', ModBlocks.hadron_coil_schrabidate);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_coil_chlorophyte, 1), "TCT", "TST", "TCT", 'T', ModItems.coil_tungsten, 'C', ModItems.powder_chlorophyte, 'S', ModBlocks.hadron_coil_starmetal);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.flywheel_beryllium, 1), "BBB", "BTB", "BBB", 'B', BE.block(), 'T', ModItems.bolt_compound);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.siox, 8), COAL.dust(), ASBESTOS.dust(), ANY_BISMOID.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.xanax, 1), COAL.dust(), KNO.dust(), BR.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.fmn, 1), COAL.dust(), PO210.dust(), SR.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.five_htp, 1), COAL.dust(), EUPH.dust(), ModItems.canteen_fab);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.steel_grate, 4), "SS", "SS", 'S', ModBlocks.steel_beam);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pile_rod_uranium, 1), " U ", "PUP", " U ", 'P', IRON.plate(), 'U', U.billet());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pile_rod_source, 1), " U ", "PUP", " U ", 'P', IRON.plate(), 'U', ModItems.billet.getItemStack(MaterialMineral.RA226BE));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pile_rod_boron, 1), "B", "W", "B", 'B', B.ingot(), 'W', KEY_PLANKS);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rbmk_fuel_empty, 1), "ZRZ", "Z Z", "ZRZ", 'Z', ZR.ingot(), 'R', ModItems.rod_quad_empty);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.URANIUM), ModItems.rbmk_fuel_ueu);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.rbmk_fuel_meu);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.U233), ModItems.rbmk_fuel_heu233);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.U235), ModItems.rbmk_fuel_heu235);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.rbmk_fuel_thmeu);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), ModItems.rbmk_fuel_mox);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.rbmk_fuel_lep);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.PU_MIX), ModItems.rbmk_fuel_mep);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.PU239), ModItems.rbmk_fuel_hep239);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.PU241), ModItems.rbmk_fuel_hep241);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.AMERICIUM_FUEL), ModItems.rbmk_fuel_lea);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.AM_MIX), ModItems.rbmk_fuel_mea);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.AM241), ModItems.rbmk_fuel_hea241);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.AM242), ModItems.rbmk_fuel_hea242);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), ModItems.rbmk_fuel_men);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM), ModItems.rbmk_fuel_hen);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.PO210BE), ModItems.rbmk_fuel_po210be);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.RA226BE), ModItems.rbmk_fuel_ra226be);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.PU238BE), ModItems.rbmk_fuel_pu238be);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.AUSTRALIUM_LESSER), ModItems.rbmk_fuel_leaus);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.AUSTRALIUM_GREATER), ModItems.rbmk_fuel_heaus);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.UNOBTAINIUM), ModItems.rbmk_fuel_unobtainium);
//		addRBMKRod(ModItems.egg_balefire_shard, ModItems.rbmk_fuel_balefire);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.LES), ModItems.rbmk_fuel_les);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.rbmk_fuel_mes);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.HES), ModItems.rbmk_fuel_hes);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.BALEFIRE_GOLD), ModItems.rbmk_fuel_balefire_gold);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.FLASHLEAD), ModItems.rbmk_fuel_flashlead);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.ZFB_BISMUTH), ModItems.rbmk_fuel_zfb_bismuth);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.ZFB_PU241), ModItems.rbmk_fuel_zfb_pu241);
        addRBMKRod(ModItems.billet.getItemStack(MaterialMineral.ZFB_AM_MIX), ModItems.rbmk_fuel_zfb_am_mix);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.rbmk_fuel_drx, 1), ModItems.rbmk_fuel_balefire, ModItems.particle_digamma);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rbmk_lid, 4), "PPP", "CCC", "PPP", 'P', STEEL.plate(), 'C', ModBlocks.concrete_asbestos);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rbmk_lid_glass, 4), "LLL", "BBB", "P P", 'P', STEEL.plate(), 'L', ModBlocks.glass_lead, 'B', ModBlocks.glass_boron);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rbmk_lid_glass, 4), "BBB", "LLL", "P P", 'P', STEEL.plate(), 'L', ModBlocks.glass_lead, 'B', ModBlocks.glass_boron);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_moderator, 1), " G ", "GRG", " G ", 'G', ModBlocks.block_graphite, 'R', ModBlocks.rbmk_blank);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_absorber, 1), "GGG", "GRG", "GGG", 'G', B.ingot(), 'R', ModBlocks.rbmk_blank);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_reflector, 1), "GGG", "GRG", "GGG", 'G', ModItems.neutron_reflector, 'R', ModBlocks.rbmk_blank);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_control, 1), " B ", "GRG", " B ", 'G', GRAPHITE.ingot(), 'B', ModItems.motor, 'R', ModBlocks.rbmk_absorber);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_control_mod, 1), "BGB", "GRG", "BGB", 'G', ModBlocks.block_graphite, 'R', ModBlocks.rbmk_control, 'B', ANY_BISMOID.nugget());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_control_auto, 1), "C", "R", "C", 'C', ModItems.circuit_targeting_tier1, 'R', ModBlocks.rbmk_control);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_rod_reasim, 1), "ZCZ", "ZRZ", "ZCZ", 'C', ModItems.hull_small_steel, 'R', ModBlocks.rbmk_blank, 'Z', ZR.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_rod_reasim_mod, 1), "BGB", "GRG", "BGB", 'G', ModBlocks.block_graphite, 'R', ModBlocks.rbmk_rod_reasim, 'B', TCALLOY.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_outgasser, 1), "GHG", "GRG", "GTG", 'G', ModBlocks.steel_grate, 'H', Blocks.HOPPER, 'T', ModItems.tank_steel, 'R', ModBlocks.rbmk_blank);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_storage, 1), "C", "R", "C", 'C', ModBlocks.crate_steel, 'R', ModBlocks.rbmk_blank);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_loader, 1), "SCS", "CBC", "SCS", 'S', STEEL.plate(), 'C', CU.ingot(), 'B', ModItems.tank_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_steam_inlet, 1), "SCS", "CBC", "SCS", 'S', STEEL.ingot(), 'C', IRON.plate(), 'B', ModItems.tank_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_steam_outlet, 1), "SCS", "CBC", "SCS", 'S', STEEL.ingot(), 'C', CU.plate(), 'B', ModItems.tank_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_heater, 1), "CIC", "PRP", "CIC", 'C', ModItems.board_copper, 'P', ModItems.pipes_steel, 'R', ModBlocks.rbmk_blank, 'I', ANY_PLASTIC.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_cooler, 1), "IGI", "GCG", "IGI", 'C', ModBlocks.rbmk_blank, 'I', ANY_RUBBER.ingot(), 'G', ModBlocks.steel_grate);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.anvil_iron, 1), "III", " B ", "III", 'I', IRON.ingot(), 'B', IRON.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.anvil_lead, 1), "III", " B ", "III", 'I', PB.ingot(), 'B', PB.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.anvil_murky, 1), "UUU", "UAU", "UUU", 'U', ModItems.undefined, 'A', ModBlocks.anvil_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_fraction_tower), "SHS", "SGS", "SHS", 'S', STEEL.plate(), 'H', ModItems.hull_big_steel, 'G', ModBlocks.steel_grate);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fraction_spacer), "BHB", 'H', ModItems.hull_big_steel, 'B', Blocks.IRON_BARS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.furnace_iron), "III", "IFI", "BBB", 'I', IRON.ingot(), 'F', Blocks.FURNACE, 'B', Blocks.STONEBRICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_mixer), "PIP", "GCG", "PMP", 'P', STEEL.plate(), 'I', DURA.ingot(), 'G', KEY_ANYPANE, 'C', ModItems.circuit_copper, 'M', ModItems.motor);

        //Cladding
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_paint, 1), PB.nugget(), PB.nugget(), PB.nugget(), PB.nugget(), Items.CLAY_BALL, Items.GLASS_BOTTLE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_rubber, 1), "RCR", "CDC", "RCR", 'R', ANY_RUBBER.ingot(), 'C', COAL.dust(), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_lead, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_rubber, 'P', PB.plate(), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_desh, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_lead, 'P', ModItems.plate_desh, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_paa, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_desh, 'P', ModItems.plate_paa, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_ghiorsium, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_paa, 'P', GH336.ingot(), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_euphemium, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_ghiorsium, 'P', ModItems.plate_euphemium, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_di, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_euphemium, 'P', ModItems.plate_dineutronium, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_electronium, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_di, 'P', ModItems.ingot.getItemStack(MaterialMineral.ELECTRONIUM), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_obsidian, 1), "OOO", "PDP", "OOO", 'O', Blocks.OBSIDIAN, 'P', STEEL.plate(), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_iron, 1), "OOO", "PDP", "OOO", 'O', IRON.plate(), 'P', ANY_RUBBER.ingot(), 'D', ModItems.ducttape);

        //Inserts
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_steel, 1), "DPD", "PSP", "DPD", 'D', ModItems.ducttape, 'P', IRON.plate(), 'S', STEEL.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_du, 1), "DPD", "PSP", "DPD", 'D', ModItems.ducttape, 'P', IRON.plate(), 'S', U238.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_ferrouranium, 1), "PIP", "IDI", "PIP", 'D', ModItems.insert_kevlar, 'P', ModItems.ducttape, 'I', FERRO.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_polonium, 1), "DPD", "PSP", "DPD", 'D', ModItems.ducttape, 'P', IRON.plate(), 'S', PO210.block());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_era, 1), "DPD", "PSP", "DPD", 'D', ModItems.ducttape, 'P', IRON.plate(), 'S', ANY_PLASTICEXPLOSIVE.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_kevlar, 1), "KIK", "IDI", "KIK", 'K', ModItems.plate_kevlar, 'I', ANY_RUBBER.ingot(), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_sapi, 1), "PKP", "DPD", "PKP", 'P', ANY_PLASTIC.ingot(), 'K', ModItems.insert_kevlar, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_esapi, 1), "PKP", "DSD", "PKP", 'P', ANY_PLASTIC.ingot(), 'K', ModItems.insert_sapi, 'D', ModItems.ducttape, 'S', BIGMT.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_xsapi, 1), "PKP", "DSD", "PKP", 'P', ASBESTOS.ingot(), 'K', ModItems.insert_esapi, 'D', ModItems.ducttape, 'S', ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_ghiorsium, 1), "PKP", "KSK", "PKP", 'P', ModItems.ducttape, 'K', GH336.ingot(), 'S', U238.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_di, 1), "PKP", "KSK", "PKP", 'P', ModItems.ducttape, 'K', ModItems.plate_dineutronium, 'S', ModItems.insert_ghiorsium);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.insert_yharonite, 1), "YIY", "IYI", "YIY", 'Y', ModItems.billet.getItemStack(MaterialMineral.YHARONITE), 'I', ModItems.insert_du);

        //Servos
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.servo_set, 1), "MBM", "PBP", "MBM", 'M', ModItems.motor, 'B', ModItems.bolt_tungsten, 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.servo_set_desh, 1), "MBM", "PSP", "MBM", 'M', ModItems.motor_desh, 'B', ModItems.bolt_dura_steel, 'P', ALLOY.plate(), 'S', ModItems.servo_set);

        //Helmet Mods
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.attachment_mask, 1), "DID", "IGI", " F ", 'D', ModItems.ducttape, 'I', ANY_RUBBER.ingot(), 'G', KEY_ANYPANE, 'F', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.attachment_mask_mono, 1), " D ", "DID", " F ", 'D', ModItems.ducttape, 'I', ANY_RUBBER.ingot(), 'F', IRON.plate());

        //Boot Mods
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pads_rubber, 1), "P P", "IDI", "P P", 'P', ANY_RUBBER.ingot(), 'I', IRON.plate(), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pads_slime, 1), "SPS", "DSD", "SPS", 'S', KEY_SLIME, 'P', ModItems.pads_rubber, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pads_static, 1), "CDC", "ISI", "CDC", 'C', CU.plate(), 'D', ModItems.ducttape, 'I', ModItems.plate_polymer, 'S', ModItems.pads_slime);

        //Special Mods
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.horseshoe_magnet, 1), "L L", "I I", "ILI", 'L', ModItems.lodestone, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.industrial_magnet, 1), "SMS", " B ", "SMS", 'S', STEEL.ingot(), 'M', ModItems.horseshoe_magnet, 'B', ModBlocks.fusion_conductor);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.heart_container, 1), "HAH", "ACA", "HAH", 'H', ModItems.heart_piece, 'A', AL.ingot(), 'C', ModItems.coin_creeper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.heart_booster, 1), "GHG", "MCM", "GHG", 'G', GOLD.ingot(), 'H', ModItems.heart_container, 'M', ModItems.morning_glory, 'C', ModItems.coin_maskman);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.heart_fab, 1), "GHG", "MCM", "GHG", 'G', PO210.billet(), 'H', ModItems.heart_booster, 'M', ModItems.canteen_fab, 'C', ModItems.coin_worm);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ink, 1), "FPF", "PIP", "FPF", 'F', ItemStackUtil.itemStackFrom(Blocks.RED_FLOWER, 1, OreDictionary.WILDCARD_VALUE), 'P', ModItems.armor_polish, 'I', "dyeBlack");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bathwater_mk2, 1), "MWM", "WBW", "MWM", 'M', ModItems.bottle_mercury, 'W', ModItems.nuclear_waste, 'B', ModItems.bathwater);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bathwater_mk3, 1), "MWM", "WBW", "MWM", 'M', ModBlocks.block_corium_cobble, 'W', ModItems.powder_radspice, 'B', ModItems.bathwater_mk2);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.back_tesla, 1), "DGD", "GTG", "DGD", 'D', ModItems.ducttape, 'G', ModItems.wire_gold, 'T', ModBlocks.tesla);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.medal_liquidator, 1), "GBG", "BFB", "GBG", 'G', AU198.nugget(), 'B', B.ingot(), 'F', ModItems.debris_fuel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.medal_ghoul, 1), "GEG", "BFB", "GEG", 'G', ModItems.nugget_u238m2, 'B', ModBlocks.pribris_digamma, 'E', ModItems.glitch, 'F', ModItems.medal_liquidator);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.injector_5htp, 1), ModItems.five_htp, ModItems.circuit_targeting_tier1, BIGMT.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.injector_knife, 1), ModItems.injector_5htp, Items.IRON_SWORD);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.shackles, 1), "CIC", "C C", "I I", 'I', ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL), 'C', ModBlocks.chain);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.black_diamond, 1), "NIN", "IGI", "NIN", 'N', AU198.nugget(), 'I', ModItems.ink, 'G', VOLCANIC.gem());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.protection_charm, 1), " M ", "MDM", " M ", 'M', ModItems.fragment_meteorite, 'D', DIAMOND.gem());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.meteor_charm, 1), " M ", "MDM", " M ", 'M', ModItems.fragment_meteorite, 'D', VOLCANIC.gem());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pocket_ptsd, 1), " R ", "PBP", "PSP", 'R', ModBlocks.machine_radar, 'P', ANY_PLASTIC.ingot(), 'B', ModItems.battery_sc_polonium, 'S', ModBlocks.machine_siren);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_paint, 1), PB.dust(), Items.CLAY_BALL, Items.GLASS_BOTTLE);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_rubber, 1), "RCR", "CDC", "RCR", 'R', ANY_RUBBER.ingot(), 'C', COAL.dust(), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_lead, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_rubber, 'P', PB.plate(), 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cladding_desh, 1), "DPD", "PRP", "DPD", 'R', ModItems.cladding_lead, 'P', ModItems.plate_desh, 'D', ModItems.ducttape);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_plasma_core, 1), "CBC", "BHB", "CBC", 'C', ModItems.circuit_gold, 'B', ModBlocks.machine_desh_battery, 'H', ModBlocks.fusion_heater);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.drax, 1), "BDS", "CDC", "FMF", 'B', ModItems.starmetal_pickaxe, 'S', ModItems.starmetal_shovel, 'C', CO.ingot(), 'F', ModItems.fusion_core, 'D', DESH.ingot(), 'M', ModItems.motor_desh);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.drax_mk2, 1), "SCS", "IDI", "FEF", 'S', STAR.ingot(), 'C', ModItems.crystal_trixite, 'I', BIGMT.ingot(), 'D', ModItems.drax, 'F', ModItems.fusion_core, 'E', ModItems.circuit_targeting_tier5);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.drax_mk3, 1), "ECE", "CDC", "SBS", 'E', ModBlocks.block_euphemium_cluster, 'C', SA326.crystal(), 'D', ModItems.drax_mk2, 'S', ModItems.circuit_targeting_tier6, 'B', ItemBattery.getFullBattery(ModItems.battery_spark));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_decorated_sword, 1), " I ", " I ", "SBS", 'I', CO.ingot(), 'S', ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 'B', ModItems.cobalt_sword);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_decorated_pickaxe, 1), "III", " B ", " S ", 'I', CO.ingot(), 'S', ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 'B', ModItems.cobalt_pickaxe);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_decorated_axe, 1), "II", "IB", " S", 'I', CO.ingot(), 'S', ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 'B', ModItems.cobalt_axe);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_decorated_shovel, 1), "I", "B", "S", 'I', CO.ingot(), 'S', ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 'B', ModItems.cobalt_shovel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.cobalt_decorated_hoe, 1), "II", " B", " S", 'I', CO.ingot(), 'S', ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 'B', ModItems.cobalt_hoe);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_sword, 1), " I ", " I ", "SBS", 'I', STAR.ingot(), 'S', CO.ingot(), 'B', ModItems.cobalt_decorated_sword);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_pickaxe, 1), "III", " B ", " S ", 'I', STAR.ingot(), 'S', CO.ingot(), 'B', ModItems.cobalt_decorated_pickaxe);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_axe, 1), "II", "IB", " S", 'I', STAR.ingot(), 'S', CO.ingot(), 'B', ModItems.cobalt_decorated_axe);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_shovel, 1), "I", "B", "S", 'I', STAR.ingot(), 'S', CO.ingot(), 'B', ModItems.cobalt_decorated_shovel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.starmetal_hoe, 1), "II", " B", " S", 'I', STAR.ingot(), 'S', CO.ingot(), 'B', ModItems.cobalt_decorated_hoe);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.chlorophyte_pickaxe, 1), " SD", "APS", "FA ", 'S', ModItems.blades_steel, 'D', ModItems.powder_chlorophyte, 'A', FIBER.ingot(), 'P', ModItems.bismuth_pickaxe, 'F', ModItems.bolt_dura_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.chlorophyte_pickaxe, 1), " SD", "APS", "FA ", 'S', ModItems.blades_steel, 'D', ModItems.powder_chlorophyte, 'A', FIBER.ingot(), 'P', ModItems.volcanic_pickaxe, 'F', ModItems.bolt_dura_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.bismuth_pickaxe, 1), " BM", "BPB", "TB ", 'B', ANY_BISMOID.ingot(), 'M', ModItems.ingot.getItemStack(MaterialMineral.METEORITE), 'P', ModItems.starmetal_pickaxe, 'T', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.volcanic_pickaxe, 1), " BM", "BPB", "TB ", 'B', VOLCANIC.gem(), 'M', ModItems.ingot.getItemStack(MaterialMineral.METEORITE), 'P', ModItems.starmetal_pickaxe, 'T', ModItems.bolt_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mese_pickaxe, 1), " SD", "APS", "FA ", 'S', ModItems.blades_desh, 'D', DNT.dust(), 'A', ModItems.plate_paa, 'P', ModItems.chlorophyte_pickaxe, 'F', ModItems.shimmer_handle);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_nullifier, 1), "SPS", "PUP", "SPS", 'S', STEEL.plate(), 'P', P_RED.dust(), 'U', ModItems.upgrade_template);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_smelter, 1), "PHP", "CUC", "DTD", 'P', CU.plate(), 'H', Blocks.HOPPER, 'C', ModItems.coil_tungsten, 'U', ModItems.upgrade_template, 'D', ModItems.coil_copper, 'T', ModBlocks.machine_transformer);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_shredder, 1), "PHP", "CUC", "DTD", 'P', ModItems.motor, 'H', Blocks.HOPPER, 'C', ModItems.blades_advanced_alloy, 'U', ModItems.upgrade_smelter, 'D', TI.plate(), 'T', ModBlocks.machine_transformer);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_centrifuge, 1), "PHP", "PUP", "DTD", 'P', ModItems.centrifuge_element, 'H', Blocks.HOPPER, 'U', ModItems.upgrade_shredder, 'D', ANY_PLASTIC.ingot(), 'T', ModBlocks.machine_transformer);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_crystallizer, 1), "PHP", "CUC", "DTD", 'P', new IngredientContainsTag(ItemFluidTank.getFullBarrel(ModForgeFluids.acid)), 'H', ModItems.circuit_targeting_tier4, 'C', ModBlocks.barrel_steel, 'U', ModItems.upgrade_centrifuge, 'D', ModItems.motor, 'T', ModBlocks.machine_transformer);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_screm, 1), "SUS", "SCS", "SUS", 'S', STEEL.plate(), 'U', ModItems.upgrade_overdrive_3, 'C', ModItems.crystal_xen);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_stack_1, 1), " C ", "PUP", " C ", 'C', ModItems.circuit_aluminium, 'P', ModItems.piston_pneumatic, 'U', ModItems.upgrade_template);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_stack_2, 1), " C ", "PUP", " C ", 'C', ModItems.circuit_copper, 'P', ModItems.piston_hydraulic, 'U', ItemStackUtil.itemStackFrom(ModItems.upgrade_stack_1));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_stack_3, 1), " C ", "PUP", " C ", 'C', ModItems.circuit_red_copper, 'P', ModItems.piston_electro, 'U', ItemStackUtil.itemStackFrom(ModItems.upgrade_stack_2));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_ejector_1, 1), " C ", "PUP", " C ", 'C', ModItems.plate_copper, 'P', ModItems.motor, 'U', ModItems.upgrade_template);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_ejector_2, 1), " C ", "PUP", " C ", 'C', ModItems.plate_gold, 'P', ModItems.motor, 'U', ItemStackUtil.itemStackFrom(ModItems.upgrade_ejector_1));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.upgrade_ejector_3, 1), " C ", "PUP", " C ", 'C', ModItems.plate_saturnite, 'P', ModItems.motor, 'U', ItemStackUtil.itemStackFrom(ModItems.upgrade_ejector_2));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.piston_pneumatic, 4), " I ", "CPC", " I ", 'I', IRON.ingot(), 'C', CU.ingot(), 'P', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.piston_hydraulic, 4), " I ", "CPC", " I ", 'I', STEEL.ingot(), 'C', TI.ingot(), 'P', new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.lubricant)));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.piston_electro, 4), " I ", "CPC", " I ", 'I', ANY_RESISTANTALLOY.ingot(), 'C', ANY_PLASTIC.ingot(), 'P', ModItems.motor);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.charge_railgun), "PDP", "DDD", "PDP", 'P', STEEL.plate(), 'D', new IngredientContainsTag(ItemFluidTank.getFullTank(ModForgeFluids.deuterium)));

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_canister, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_4gauge_kampf, 'A', ModItems.pellet_canister);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_44_chlorophyte, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_44, 'A', ModItems.pellet_chlorophyte);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_5mm_chlorophyte, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_5mm, 'A', ModItems.pellet_chlorophyte);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_9mm_chlorophyte, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_9mm, 'A', ModItems.pellet_chlorophyte);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr_chlorophyte, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_22lr, 'A', ModItems.pellet_chlorophyte);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg_chlorophyte, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50bmg, 'A', ModItems.pellet_chlorophyte);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_50ae_chlorophyte, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_50ae, 'A', ModItems.pellet_chlorophyte);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_chlorophyte, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556, 'A', ModItems.pellet_chlorophyte);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_556_flechette_chlorophyte, 8), "BBB", "BAB", "BBB", 'B', ModItems.ammo_556_flechette, 'A', ModItems.pellet_chlorophyte);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_canister, 1), "G", "R", 'G', ModItems.pellet_canister, 'R', ModItems.ammo_rocket);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.circuit_red_copper, 48), ModBlocks.fusion_core);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.fusion_heater), ModBlocks.fusion_hatch);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.energy_core), ModItems.fusion_core, ModItems.fuse);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_armor_titanium, 1), "NPN", "PIP", "NPN", 'N', ModItems.bolt_tungsten, 'P', TI.plate(), 'I', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_armor_lunar, 1), "NPN", "PIP", "NPN", 'N', ModItems.wire_magnetized_tungsten, 'P', OreDictManager.getReflector(), 'I', ModItems.plate_armor_hev);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wood_gavel, 1), "SWS", " R ", " R ", 'S', KEY_SLAB, 'W', KEY_LOG, 'R', "stickWood");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.lead_gavel, 1), "PIP", "IGI", "PIP", 'P', ModItems.pellet_buckshot, 'I', PB.ingot(), 'G', ModItems.wood_gavel);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mech_key, 1), "MCM", "MKM", "MMM", 'M', ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 'C', ModItems.coin_maskman, 'K', ModItems.key);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.spawn_ufo, 1), "MMM", "DCD", "MMM", 'M', ModItems.ingot.getItemStack(MaterialMineral.METEORITE), 'D', DNT.ingot(), 'C', ModItems.coin_worm);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.particle_empty, 2), "STS", "G G", "STS", 'S', STEEL.plate(), 'T', W.ingot(), 'G', KEY_ANYPANE);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.particle_hydrogen, 1), ModItems.particle_empty, new IngredientContainsTag(ItemFluidTank.getFullTank(ModForgeFluids.hydrogen)));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.particle_copper, 1), ModItems.particle_empty, CU.dust(), ModItems.pellet_charged);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.particle_lead, 1), ModItems.particle_empty, PB.dust(), ModItems.pellet_charged);
        addShapelessAuto(ItemCell.getFullCell(ModForgeFluids.amat), ModItems.particle_aproton, ModItems.particle_aelectron, new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.particle_amat, 1), ModItems.particle_aproton, ModItems.particle_aelectron, ModItems.particle_empty);
        addShapelessAuto(ItemCell.getFullCell(ModForgeFluids.aschrab), ModItems.particle_aschrab, new IngredientNBT2(ItemStackUtil.itemStackFrom(ModItems.cell)));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.particle_aschrab), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.aschrab)), ModItems.particle_empty);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.particle_amat), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.amat)), ModItems.particle_empty);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.capsule_empty, 1), "STS", "GXG", "STS", 'S', ModItems.plate_armor_lunar, 'T', ModItems.coil_advanced_torus, 'G', GH336.ingot(), 'X', ModItems.particle_empty);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.capsule_xen), ModItems.capsule_empty, ModItems.crystal_xen);

        final ItemStack infinity = ItemStackUtil.itemStackFrom(Items.ENCHANTED_BOOK);
        EnchantmentUtil.addEnchantment(infinity, Enchantments.INFINITY, 1);
        addRecipeAuto(infinity, "SBS", "BDB", "SBS", 'S', ModItems.ammo_50bmg_star, 'B', ModItems.ammo_5mm_star, 'D', ModItems.powder_magic);
        final ItemStack unbreaking = ItemStackUtil.itemStackFrom(Items.ENCHANTED_BOOK);
        EnchantmentUtil.addEnchantment(unbreaking, Enchantments.UNBREAKING, 3);
        addRecipeAuto(unbreaking, "SBS", "BDB", "SBS", 'S', BIGMT.ingot(), 'B', ModItems.plate_armor_lunar, 'D', ModItems.powder_magic);
        final ItemStack thorns = ItemStackUtil.itemStackFrom(Items.ENCHANTED_BOOK);
        EnchantmentUtil.addEnchantment(thorns, Enchantments.THORNS, 3);
        addRecipeAuto(thorns, "SBS", "BDB", "SBS", 'S', ModBlocks.barbed_wire, 'B', ModBlocks.spikes, 'D', ModItems.powder_magic);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_diode, 1), "CIC", "ISI", "CIC", 'C', ModBlocks.hadron_coil_alloy, 'I', STEEL.ingot(), 'S', ModItems.circuit_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_plating, 1), "IPI", "P P", "IPI", 'I', STEEL.ingot(), 'P', STEEL.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_plating_blue, 1), ModBlocks.hadron_plating, "dyeBlue");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_plating_black, 1), ModBlocks.hadron_plating, "dyeBlack");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_plating_yellow, 1), ModBlocks.hadron_plating, "dyeYellow");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_plating_striped, 1), ModBlocks.hadron_plating, "dyeBlack", "dyeYellow");
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_plating_glass, 1), ModBlocks.hadron_plating, KEY_ANYGLASS);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_plating_voltz, 1), ModBlocks.hadron_plating, "dyeRed");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_power, 1), "STS", "CPC", "STS", 'S', BIGMT.ingot(), 'T', ModBlocks.machine_transformer, 'C', ModItems.circuit_targeting_tier3, 'P', ModBlocks.hadron_plating_blue);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_analysis, 1), "IPI", "PCP", "IPI", 'I', TI.ingot(), 'P', OreDictManager.getReflector(), 'C', ModItems.circuit_gold);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_analysis_glass, 1), ModBlocks.hadron_analysis, KEY_ANYGLASS);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_access, 1), "IGI", "CRC", "IPI", 'I', ModItems.plate_polymer, 'G', KEY_ANYPANE, 'C', ModItems.circuit_aluminium, 'R', REDSTONE.block(), 'P', ModBlocks.hadron_plating_blue);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_copper_torus, 2), " C ", "CPC", " C ", 'P', STEEL.plate(), 'C', ModItems.coil_copper);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_advanced_torus, 2), " C ", "CPC", " C ", 'P', STEEL.plate(), 'C', ModItems.coil_advanced_alloy);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.coil_gold_torus, 2), " C ", "CPC", " C ", 'P', STEEL.plate(), 'C', ModItems.coil_gold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_solar_boiler), "SHS", "DHD", "SHS", 'S', STEEL.ingot(), 'H', ModItems.hull_big_steel, 'D', "dyeBlack");
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.solar_mirror, 3), "AAA", " B ", "SSS", 'A', AL.plate(), 'B', ModBlocks.steel_beam, 'S', STEEL.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.steel_beam, 8), "S", "S", "S", 'S', ModBlocks.steel_scaffold);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.mirror_tool), " A ", " IA", "I  ", 'A', AL.ingot(), 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rbmk_tool), " A ", " IA", "I  ", 'A', PB.ingot(), 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hand_drill), " D", "S ", " S", 'D', DURA.ingot(), 'S', Items.STICK);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hand_drill_desh), " D", "S ", " S", 'D', DESH.ingot(), 'S', ANY_PLASTIC.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hev_helmet, 1), "PCP", "PBP", "IFI", 'P', ModItems.plate_armor_hev, 'C', ModItems.circuit_targeting_tier4, 'B', ModItems.cobalt_helmet, 'I', ANY_RUBBER.ingot(), 'F', ModItems.gas_mask_filter);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hev_plate, 1), "MPM", "IBI", "PPP", 'P', ModItems.plate_armor_hev, 'B', ModItems.cobalt_plate, 'I', ANY_PLASTIC.ingot(), 'M', ModItems.motor_desh);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hev_legs, 1), "MPM", "IBI", "P P", 'P', ModItems.plate_armor_hev, 'B', ModItems.cobalt_legs, 'I', ANY_PLASTIC.ingot(), 'M', ModItems.motor_desh);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hev_boots, 1), "P P", "PBP", 'P', ModItems.plate_armor_hev, 'B', ModItems.cobalt_boots);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_armor_hev, 1), "NPN", "AIA", "NPN", 'N', ModItems.wire_tungsten, 'P', ALLOY.plate(), 'I', ModItems.plate_armor_ajr, 'A', ModItems.plate_paa);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_detector, 1), "IRI", "CTC", "IRI", 'I', ModItems.plate_polymer, 'R', Items.REDSTONE, 'C', ModItems.wire_red_copper, 'T', ModItems.coil_tungsten);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.meteorite_sword, 1), "  B", "GB ", "SG ", 'B', ModItems.blade_meteorite, 'G', GOLD.plate(), 'S', Items.STICK);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.powder_semtex_mix, 1), ModItems.solid_fuel, ModItems.ballistite, KNO.dust());
        add9To1(ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM), ModBlocks.block_aluminium);
        add1To9(ModBlocks.block_aluminium, ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM));

        add9To1(ModItems.ingot.getItemStack(MaterialMineral.SCHRARANIUM), ModBlocks.block_schraranium);
        add1To9(ModBlocks.block_schraranium, ModItems.ingot.getItemStack(MaterialMineral.SCHRARANIUM));

        add1To9Pair(ModItems.powder_paleogenite, ModItems.powder_paleogenite_tiny);
        add1To9Pair(ModBlocks.block_lanthanium, ModItems.ingot.getItemStack(MaterialMineral.LANTHANIUM));
        add1To9Pair(ModBlocks.block_actinium, ModItems.ingot.getItemStack(MaterialMineral.ACTINIUM));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_tritium), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.tritium)));
        addShapelessAuto(ItemCell.getFullCell(ModForgeFluids.tritium, 9), ModBlocks.block_tritium);
        add1To9Pair(ModBlocks.block_schrabidate, ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDATE));

        add9To1(ModItems.ingot.getItemStack(MaterialMineral.DINEUTRONIUM), ModBlocks.block_dineutronium);
        add1To9(ModBlocks.block_dineutronium, ModItems.ingot.getItemStack(MaterialMineral.DINEUTRONIUM));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.canteen_fab, 1), "VMV", "MVM", "VMV", 'V', ModItems.canteen_vodka, 'M', ModItems.powder_magic);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fireworks, 1), "PPP", "PPP", "WIW", 'P', Items.PAPER, 'W', KEY_PLANKS, 'I', IRON.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.pellet_claws, 1), " X ", "X X", " XX", 'X', STEEL.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_claw, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_4gauge, 'A', ModItems.pellet_claws);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_vampire, 4), "ABA", "BAB", "ABA", 'B', ModItems.ammo_4gauge, 'A', ModItems.toothpicks);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_4gauge_void, 4), " B ", "BAB", " B ", 'B', ModItems.ammo_4gauge, 'A', ModItems.pellet_charged);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hev_battery, 4), " W ", "IEI", "ICI", 'W', ModItems.wire_gold, 'I', ModItems.plate_polymer, 'E', ModItems.powder_power, 'C', CO.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hev_battery, 4), " W ", "ICI", "IEI", 'W', ModItems.wire_gold, 'I', ModItems.plate_polymer, 'E', ModItems.powder_power, 'C', CO.dust());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.chain, 8), "S", "S", "S", 'S', ModBlocks.steel_beam);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.spinny_light), " G ", "GFG", "SRS", 'G', KEY_ANYGLASS, 'F', ModItems.fuse, 'S', ItemStackUtil.itemStackFrom(Blocks.STONE_SLAB, 1, 0), 'R', REDSTONE.dust());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jetpack_glider), "CSC", "DJD", "T T", 'J', ModItems.jetpack_boost, 'C', ModItems.circuit_targeting_tier5, 'D', ModItems.plate_desh, 'T', ModItems.thruster_nuclear, 'S', ModItems.motor);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.sliding_blast_door_skin0), "SPS", "DPD", "SPS", 'P', Items.PAPER, 'D', "dye", 'S', STEEL.plate());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sliding_blast_door_skin1), ModItems.sliding_blast_door_skin0);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sliding_blast_door_skin2), ModItems.sliding_blast_door_skin1);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.sliding_blast_door_skin0), ModItems.sliding_blast_door_skin2);

        addRecipeAuto(ItemStackUtil.itemStackFrom(Items.NAME_TAG), "SB ", "BPB", " BP", 'S', Items.STRING, 'B', KEY_SLIME, 'P', Items.PAPER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(Items.NAME_TAG), "SB ", "BPB", " BP", 'S', Items.STRING, 'B', ANY_TAR.any(), 'P', Items.PAPER);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.rag, 4), "SW", "WS", 'S', Items.STRING, 'W', Blocks.WOOL);


        //Peas
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.peas), " S ", "SNS", " S ", 'S', Items.WHEAT_SEEDS, 'N', GOLD.nugget());

        //Liquidator Suit
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.liquidator_helmet, 1), "III", "CBC", "IFI", 'I', ANY_RUBBER.ingot(), 'C', ModItems.cladding_lead, 'B', ModItems.hazmat_helmet_grey, 'F', ModItems.gas_mask_filter_mono);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.liquidator_plate, 1), "ICI", "TBT", "ICI", 'I', ANY_RUBBER.ingot(), 'C', ModItems.cladding_lead, 'B', ModItems.hazmat_plate_grey, 'T', ModItems.gas_canister);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.liquidator_legs, 1), "III", "CBC", "I I", 'I', ANY_RUBBER.ingot(), 'C', ModItems.cladding_lead, 'B', ModItems.hazmat_legs_grey);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.liquidator_boots, 1), "ICI", "IBI", 'I', ANY_RUBBER.ingot(), 'C', ModItems.cladding_lead, 'B', ModItems.hazmat_boots_grey);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter, 1), "I", "F", 'F', ModItems.filter_coal, 'I', IRON.plate());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter_mono, 1), "ZZZ", "ZCZ", "ZZZ", 'Z', ZR.nugget(), 'C', ModItems.catalyst_clay);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter_combo, 1), "ZCZ", "CFC", "ZCZ", 'Z', ZR.ingot(), 'C', ModItems.catalyst_clay, 'F', ModItems.gas_mask_filter);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter_radon, 1), "ZCZ", "NFN", "ZCZ", 'Z', ModItems.nugget.getItemStack(MaterialMineral.VERTICIUM), 'N', ModItems.powder_radspice, 'C', ModItems.insert_xsapi, 'F', ModItems.gas_mask_filter_combo);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter_rag, 1), "I", "F", 'F', ModItems.rag_damp, 'I', IRON.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter_piss, 1), "I", "F", 'F', ModItems.rag_piss, 'I', IRON.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.jshotgun, 1), "LPP", "SSW", "PPD", 'S', ModItems.gun_uboinik, 'P', STEEL.plate(), 'D', ItemStackUtil.itemStackFrom(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), 'L', ModBlocks.spinny_light, 'W', ModItems.mechanism_rifle_2);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.machine_armor_table, 1), "PPP", "TCT", "TST", 'P', STEEL.plate(), 'T', W.ingot(), 'C', Blocks.CRAFTING_TABLE, 'S', STEEL.block());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot_steel_dusted, 1), STEEL.ingot(), COAL.dust());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.demon_core_open, 1), "PRP", " CS", "PRP", 'P', TI.plate(), 'R', OreDictManager.getReflector(), 'C', ModItems.man_core, 'S', ModItems.screwdriver);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.demon_core_open, 1), ModItems.demon_core_closed, ModItems.screwdriver);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.lamp_demon, 1), " D ", "S S", 'D', ModItems.demon_core_closed, 'S', STEEL.ingot());

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.crucible, 1, 3), "MEM", "YDY", "YCY", 'M', ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), 'E', EUPH.ingot(), 'Y', ModItems.billet.getItemStack(MaterialMineral.YHARONITE), 'D', ModItems.demon_core_closed, 'C', ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hf_sword), "MEM", "YDY", "YCY", 'M', ModItems.blade_meteorite, 'E', ModItems.ingot.getItemStack(MaterialMineral.RADSPICE), 'Y', UNOBTAINIUM.billet(), 'D', ModItems.particle_strange, 'C', ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL));
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.hs_sword), "MEM", "YDY", "YCY", 'M', ModItems.blade_meteorite, 'E', GH336.ingot(), 'Y', ModItems.billet.getItemStack(MaterialMineral.GH336), 'D', ModItems.particle_dark, 'C', ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL));

        for (int i = 0; i < ItemWasteLong.WasteClass.values().length; i++) {
            add1To9PairSameMeta(ModItems.nuclear_waste_long, ModItems.nuclear_waste_long_tiny, i);
            add1To9PairSameMeta(ModItems.nuclear_waste_long_depleted, ModItems.nuclear_waste_long_depleted_tiny, i);
        }

        for (int i = 0; i < ItemWasteShort.WasteClass.values().length; i++) {
            add1To9PairSameMeta(ModItems.nuclear_waste_short, ModItems.nuclear_waste_short_tiny, i);
            add1To9PairSameMeta(ModItems.nuclear_waste_short_depleted, ModItems.nuclear_waste_short_depleted_tiny, i);
        }

        add1To9Pair(ModBlocks.block_fallout, ModItems.fallout);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.fallout, 2), "##", '#', ModItems.fallout);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ashglasses, 1), "I I", "GPG", 'I', ModItems.plate_polymer, 'G', ModBlocks.glass_ash, 'P', ANY_PLASTIC.ingot());

        addMineralSet(ModItems.nugget.getItemStack(MaterialMineral.PU_MIX), ModItems.ingot.getItemStack(MaterialMineral.PU_MIX), ModBlocks.block_pu_mix);
        add1To9Pair(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.NEPTUNIUM_FUEL));

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM), 2), ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), U238.billet());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM), 2), "ABC", "BB ", "BB ", 'A', U238.billet(), 'B', U238.nugget(), 'C', U235.nugget());

        addBillet(ModItems.billet.getItemStack(MaterialMineral.URANIUM), ModItems.nugget.getItemStack(MaterialMineral.URANIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.U233), ModItems.nugget.getItemStack(MaterialMineral.U233));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.U235), ModItems.nugget.getItemStack(MaterialMineral.U235));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.U238), ModItems.nugget.getItemStack(MaterialMineral.U238));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.TH232), ModItems.nugget.getItemStack(MaterialMineral.TH232));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.PU238), ModItems.nugget.getItemStack(MaterialMineral.PU238));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.PU239), ModItems.nugget.getItemStack(MaterialMineral.PU239));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.PU240), ModItems.nugget.getItemStack(MaterialMineral.PU240));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.PU241), ModItems.nugget.getItemStack(MaterialMineral.PU241));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.PU_MIX), ModItems.nugget.getItemStack(MaterialMineral.PU_MIX));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AM241), ModItems.nugget.getItemStack(MaterialMineral.AM241));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AM242), ModItems.nugget.getItemStack(MaterialMineral.AM242));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AM_MIX), ModItems.nugget.getItemStack(MaterialMineral.AM_MIX));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AMERICIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.AMERICIUM_FUEL));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM), ModItems.nugget.getItemStack(MaterialMineral.NEPTUNIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.POLONIUM), ModItems.nugget.getItemStack(MaterialMineral.POLONIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.TECHNETIUM), ModItems.nugget.getItemStack(MaterialMineral.TECHNETIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AU198), ModItems.nugget.getItemStack(MaterialMineral.AU198));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.SOLINIUM), ModItems.nugget.getItemStack(MaterialMineral.SOLINIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.URANIUM_FUEL));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.THORIUM_FUEL));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.NEPTUNIUM_FUEL));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), ModItems.nugget.getItemStack(MaterialMineral.MOX_FUEL));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.LES), ModItems.nugget.getItemStack(MaterialMineral.LES));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.HES), ModItems.nugget.getItemStack(MaterialMineral.HES));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AUSTRALIUM), ModItems.nugget.getItemStack(MaterialMineral.AUSTRALIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AUSTRALIUM_LESSER), ModItems.nugget.getItemStack(MaterialMineral.AUSTRALIUM_LESSER));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AUSTRALIUM_GREATER), ModItems.nugget.getItemStack(MaterialMineral.AUSTRALIUM_GREATER));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.AC227), ModItems.nugget.getItemStack(MaterialMineral.AC227));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.BISMUTH), ModItems.nugget.getItemStack(MaterialMineral.BISMUTH));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.PB209), ModItems.nugget.getItemStack(MaterialMineral.PB209));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.RA226), ModItems.nugget.getItemStack(MaterialMineral.RA226));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.SR90), ModItems.nugget.getItemStack(MaterialMineral.SR90));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.CO60), ModItems.nugget.getItemStack(MaterialMineral.CO60));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.RA226), ModItems.nugget.getItemStack(MaterialMineral.RA226));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.GH336), ModItems.nugget.getItemStack(MaterialMineral.GH336));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.BERYLLIUM), ModItems.nugget.getItemStack(MaterialMineral.BERYLLIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.ZIRCONIUM), ModItems.nugget.getItemStack(MaterialMineral.ZIRCONIUM));
        addBillet(ModItems.billet.getItemStack(MaterialMineral.BISMUTH), ModItems.nugget.getItemStack(MaterialMineral.BISMUTH));
        addBillet(ItemStackUtil.itemStackFrom(ModItems.billet_nuclear_waste), ItemStackUtil.itemStackFrom(ModItems.nuclear_waste_tiny));

        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.URANIUM), ModItems.ingot.getItemStack(MaterialMineral.URANIUM), U.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.U233), ModItems.ingot.getItemStack(MaterialMineral.U233), U233.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.U235), ModItems.ingot.getItemStack(MaterialMineral.U235), U235.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.U238), ModItems.ingot.getItemStack(MaterialMineral.U238), U238.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.TH232), ModItems.ingot.getItemStack(MaterialMineral.TH232), TH232.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM), ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM), PU.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.PU238), ModItems.ingot.getItemStack(MaterialMineral.PU238), PU238.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.PU239), ModItems.ingot.getItemStack(MaterialMineral.PU239), PU239.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.PU240), ModItems.ingot.getItemStack(MaterialMineral.PU240), PU240.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.PU241), ModItems.ingot.getItemStack(MaterialMineral.PU241), PU241.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.PU_MIX), ModItems.ingot.getItemStack(MaterialMineral.PU_MIX), PURG.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.AM241), ModItems.ingot.getItemStack(MaterialMineral.AM241), AM241.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.AM242), ModItems.ingot.getItemStack(MaterialMineral.AM242), AM242.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.AM_MIX), ModItems.ingot.getItemStack(MaterialMineral.AM_MIX), AMRG.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.AMERICIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.AMERICIUM_FUEL));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM), ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM), NP237.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.POLONIUM), ModItems.ingot.getItemStack(MaterialMineral.POLONIUM), PO210.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.TECHNETIUM), ModItems.ingot.getItemStack(MaterialMineral.TECHNETIUM), TC99.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.AU198), ModItems.ingot.getItemStack(MaterialMineral.AU198), AU198.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM), ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM), SA326.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.SOLINIUM), ModItems.ingot.getItemStack(MaterialMineral.SOLINIUM), SA327.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM_FUEL));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.LES), ModItems.ingot.getItemStack(MaterialMineral.LES));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.HES), ModItems.ingot.getItemStack(MaterialMineral.HES));
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.AUSTRALIUM), ModItems.ingot.getItemStack(MaterialMineral.AUSTRALIUM), AUSTRALIUM.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.AC227), ModItems.ingot.getItemStack(MaterialMineral.AC227), AC227.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.BISMUTH), ModItems.ingot.getItemStack(MaterialMineral.BISMUTH), ANY_BISMOID.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.PB209), ModItems.ingot.getItemStack(MaterialMineral.PB209), PB209.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.RA226), ModItems.ingot.getItemStack(MaterialMineral.RA226), RA226.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.SR90), ModItems.ingot.getItemStack(MaterialMineral.SR90), SR90.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.CO60), ModItems.ingot.getItemStack(MaterialMineral.CO60), CO60.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.RA226), ModItems.ingot.getItemStack(MaterialMineral.RA226), RA226.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.GH336), ModItems.ingot.getItemStack(MaterialMineral.GH336), GH336.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.BERYLLIUM), ModItems.ingot.getItemStack(MaterialMineral.BERYLLIUM), BE.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.ZIRCONIUM), ModItems.ingot.getItemStack(MaterialMineral.ZIRCONIUM), ZR.ingot());
        addBilletByIngot(ModItems.billet.getItemStack(MaterialMineral.BISMUTH), ModItems.ingot.getItemStack(MaterialMineral.BISMUTH), ANY_BISMOID.ingot());
        addBilletByIngot(ModItems.billet_nuclear_waste, ModItems.nuclear_waste);


        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_thorium_fuel, 3), TH232.block(), TH232.block(), U233.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL), 3), TH232.ingot(), TH232.ingot(), U233.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), 3), TH232.billet(), TH232.billet(), U233.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.THORIUM_FUEL), 1), TH232.nugget(), TH232.nugget(), TH232.nugget(), TH232.nugget(), U233.nugget(), U233.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_uranium_fuel, 6), U238.block(), U238.block(), U238.block(), U238.block(), U238.block(), U235.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), 6), U238.ingot(), U238.ingot(), U238.ingot(), U238.ingot(), U238.ingot(), U235.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), 6), U238.billet(), U238.billet(), U238.billet(), U238.billet(), U238.billet(), U235.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), 1), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), U235.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_plutonium_fuel, 3), U238.block(), PURG.block(), PURG.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 3), U238.ingot(), PURG.ingot(), PURG.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 3), U238.billet(), PURG.billet(), PURG.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 1), PURG.nugget(), PURG.nugget(), PURG.nugget(), PURG.nugget(), U238.nugget(), U238.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_pu_mix, 3), PU239.block(), PU239.block(), PU240.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU_MIX), 3), PU239.ingot(), PU239.ingot(), PU240.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PU_MIX), 3), PU239.billet(), PU239.billet(), PU240.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PU_MIX), 1), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU239.nugget(), PU240.nugget(), PU240.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AMERICIUM_FUEL), 3), U238.ingot(), U238.ingot(), AMRG.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.AMERICIUM_FUEL), 3), U238.billet(), U238.billet(), AMRG.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.AMERICIUM_FUEL), 1), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), AMRG.nugget(), AMRG.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AM_MIX), 3), AM241.ingot(), AM242.ingot(), AM242.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.AM_MIX), 3), AM241.billet(), AM242.billet(), AM242.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.AM_MIX), 1), AM241.nugget(), AM241.nugget(), AM242.nugget(), AM242.nugget(), AM242.nugget(), AM242.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), 27), U238.block(), U238.block(), NP237.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), 3), U238.ingot(), U238.ingot(), NP237.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), 3), U238.billet(), U238.billet(), NP237.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), 1), U238.nugget(), U238.nugget(), U238.nugget(), U238.nugget(), NP237.nugget(), NP237.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_mox_fuel, 3), U238.block(), U235.block(), PURG.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), 3), U238.ingot(), U235.ingot(), PURG.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), 3), U238.billet(), U235.billet(), PURG.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), 1), U238.nugget(), U238.nugget(), U235.nugget(), U235.nugget(), PURG.nugget(), PURG.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.LES), 54), SA326.block(), NP237.block(), NP237.block(), NP237.block(), NP237.block(), BE.block(), BE.block(), BE.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LES), 9), SA326.ingot(), NP237.ingot(), NP237.ingot(), NP237.ingot(), NP237.ingot(), BE.ingot(), BE.ingot(), BE.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.LES), 9), SA326.billet(), NP237.billet(), NP237.billet(), NP237.billet(), NP237.billet(), BE.billet(), BE.billet(), BE.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.LES), 9), SA326.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), NP237.nugget(), BE.nugget(), BE.nugget(), BE.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.block_schrabidium_fuel, 3), SA326.block(), NP237.block(), BE.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 3), SA326.ingot(), NP237.ingot(), BE.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 3), SA326.billet(), NP237.billet(), BE.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 3), SA326.nugget(), NP237.nugget(), BE.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.HES), 54), SA326.block(), SA326.block(), NP237.block(), BE.block());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.HES), 4), SA326.ingot(), SA326.ingot(), NP237.ingot(), BE.ingot());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.HES), 4), SA326.billet(), SA326.billet(), NP237.billet(), BE.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.HES), 4), SA326.nugget(), SA326.nugget(), NP237.nugget(), BE.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PO210BE), 1), PO210.nugget(), PO210.nugget(), PO210.nugget(), BE.nugget(), BE.nugget(), BE.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.PU238BE), 1), PU238.nugget(), PU238.nugget(), PU238.nugget(), BE.nugget(), BE.nugget(), BE.nugget());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.RA226BE), 1), RA226.nugget(), RA226.nugget(), RA226.nugget(), BE.nugget(), BE.nugget(), BE.nugget());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), 9), U238.ingot(), U238.ingot(), U238.ingot(), U238.ingot(), U238.ingot(), U235.ingot());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM), 2), U.billet(), U.billet(), U.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U233), 2), U233.billet(), U233.billet(), U233.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U235), 2), U235.billet(), U235.billet(), U235.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U238), 2), U238.billet(), U238.billet(), U238.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM), 2), PU.billet(), PU.billet(), PU.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU238), 2), PU238.billet(), PU238.billet(), PU238.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU239), 2), PU239.billet(), PU239.billet(), PU239.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU240), 2), PU240.billet(), PU240.billet(), PU240.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU241), 2), PU241.billet(), PU241.billet(), PU241.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PU_MIX), 2), PURG.billet(), PURG.billet(), PURG.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AM241), 2), AM241.billet(), AM241.billet(), AM241.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AM242), 2), AM242.billet(), AM242.billet(), AM242.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AM_MIX), 2), AMRG.billet(), AMRG.billet(), AMRG.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL), 2), ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.URANIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM_FUEL), 2), ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.PLUTONIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AMERICIUM_FUEL), 2), ModItems.billet.getItemStack(MaterialMineral.AMERICIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.AMERICIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.AMERICIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MOX_FUEL), 2), ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL), ModItems.billet.getItemStack(MaterialMineral.MOX_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM), 2), NP237.billet(), NP237.billet(), NP237.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), 2), ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.NEPTUNIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLONIUM), 2), PO210.billet(), PO210.billet(), PO210.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TECHNETIUM), 2), TC99.billet(), TC99.billet(), TC99.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 2), SA326.billet(), SA326.billet(), SA326.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SOLINIUM), 2), SA327.billet(), SA327.billet(), SA327.billet());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LES), 2), ModItems.billet.getItemStack(MaterialMineral.LES), ModItems.billet.getItemStack(MaterialMineral.LES), ModItems.billet.getItemStack(MaterialMineral.LES));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), 2), ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL), ModItems.billet.getItemStack(MaterialMineral.SCHRABIDIUM_FUEL));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.HES), 2), ModItems.billet.getItemStack(MaterialMineral.HES), ModItems.billet.getItemStack(MaterialMineral.HES), ModItems.billet.getItemStack(MaterialMineral.HES));

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.BALEFIRE_GOLD), 1), AU198.billet(), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.amat)), ModItems.pellet_charged);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.FLASHLEAD), 2), ModItems.billet.getItemStack(MaterialMineral.BALEFIRE_GOLD), PB209.billet(), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.amat)));
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.FLASHLEAD), 2), AU198.billet(), PB209.billet(), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.amat)), new IngredientContainsTag(ItemCell.getFullCell(ModForgeFluids.amat)), ModItems.pellet_charged);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_dgk, 1), "LLL", "GGG", "CCC", 'L', PB.plate(), 'G', ANY_SMOKELESS.dust(), 'C', CU.ingot());

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_red), "dyeRed", ModBlocks.ladder_steel);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.ladder_red_top), ModBlocks.ladder_red);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.railing_normal), "   ", "SSS", "S S", 'S', ModBlocks.steel_beam);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.railing_bend), "   ", "S  ", " S ", 'S', ModBlocks.railing_normal);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.railing_end_self, 2), "   ", " SS", "   ", 'S', ModBlocks.railing_normal);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.railing_end_floor, 2), "   ", " S ", " S ", 'S', ModBlocks.railing_normal);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.railing_end_flipped_self), ModBlocks.railing_end_self);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.railing_end_flipped_floor), ModBlocks.railing_end_floor);

        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.assembly_nuke, 1), " WP", "SEP", " WP", 'W', ModItems.wire_aluminium, 'P', STEEL.plate(), 'S', ModItems.hull_small_steel, 'E', ANY_HIGHEXPLOSIVE.ingot());

        //Mini Nuke
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke, 1), "P", "S", "P", 'P', PU239.nugget(), 'S', ModItems.assembly_nuke);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke_low, 1), "P", "S", 'P', PU239.nugget(), 'S', ModItems.assembly_nuke);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke_high, 1), "PPP", "PSP", "PPP", 'P', PU239.nugget(), 'S', ModItems.assembly_nuke);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke_tots, 1), "PPP", "PIP", "PPP", 'P', ModItems.pellet_cluster, 'I', PU239.ingot());
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke_safe, 1), "G", "N", 'G', Items.GLOWSTONE_DUST, 'N', ModItems.ammo_nuke_low);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke_pumpkin, 1), " T ", "TST", " T ", 'T', Blocks.TNT, 'S', ModItems.assembly_nuke);

        //MIRV recycling
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke, 6), ModItems.ammo_mirv);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke_low, 6), ModItems.ammo_mirv_low);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke_high, 6), ModItems.ammo_mirv_high);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_nuke_safe, 6), ModItems.ammo_mirv_safe);

        //MIRV
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_mirv, 1), "NNN", "CDS", "NNN", 'N', ModItems.ammo_nuke, 'C', ModItems.cap_aluminium, 'D', ModBlocks.det_cord, 'S', ModItems.hull_small_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_mirv_low, 1), "NNN", "CDS", "NNN", 'N', ModItems.ammo_nuke_low, 'C', ModItems.cap_aluminium, 'D', ModBlocks.det_cord, 'S', ModItems.hull_small_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_mirv_high, 1), "NNN", "CDS", "NNN", 'N', ModItems.ammo_nuke_high, 'C', ModItems.cap_aluminium, 'D', ModBlocks.det_cord, 'S', ModItems.hull_small_steel);
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_mirv_safe, 1), "NNN", "CDS", "NNN", 'N', ModItems.ammo_nuke_safe, 'C', ModItems.cap_aluminium, 'D', ModBlocks.det_cord, 'S', ModItems.hull_small_steel);
        //since the milk part of the recipe isn't realy present in the MIRV's effect, it might as well be replaced with something more sensible, i.e. duct tape
        addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.ammo_mirv_special, 1), "CBC", "MCM", "CBC", 'C', ModItems.canned_jizz, 'B', ModItems.gun_bf_ammo, 'M', ModItems.ammo_mirv);

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ball_fireclay, 4), Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, AL.dust());
        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ball_fireclay, 4), Items.CLAY_BALL, Items.CLAY_BALL, Items.CLAY_BALL, AL.ore());

        add1To9Pair(ModBlocks.block_semtex, ModItems.ingot_semtex);

        if (!GeneralConfig.enable528) {
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher_core, 1), "SCS", "SIS", "BEB", 'S', ModBlocks.steel_scaffold, 'I', Blocks.IRON_BARS, 'C', ModItems.circuit_targeting_tier3, 'B', ModBlocks.struct_launcher, 'E', ModBlocks.machine_battery);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.struct_soyuz_core, 1), "CUC", "TST", "TBT", 'C', ModItems.circuit_targeting_tier4, 'U', ModItems.upgrade_power_3, 'T', ModBlocks.barrel_steel, 'S', ModBlocks.steel_scaffold, 'B', ModBlocks.machine_lithium_battery);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.reactor_sensor, 1), "WPW", "CMC", "PPP", 'W', ModItems.wire_tungsten, 'P', PB.plate(), 'C', ModItems.circuit_targeting_tier3, 'M', ModItems.magnetron);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.reactor_ejector, 1), "CLC", "MHM", "CLC", 'C', ModBlocks.brick_concrete, 'L', PB.plate(), 'M', ModItems.motor, 'H', ModBlocks.reactor_hatch);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.reactor_inserter, 1), "CLC", "MHM", "CLC", 'C', ModBlocks.brick_concrete, 'L', CU.plate(), 'M', ModItems.motor, 'H', ModBlocks.reactor_hatch);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_console, 1), "BBB", "DGD", "DCD", 'B', B.ingot(), 'D', ModBlocks.deco_rbmk, 'G', KEY_ANYPANE, 'C', ModItems.circuit_targeting_tier3);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_crane_console, 1), "BCN", "DDD", 'B', B.ingot(), 'D', ModBlocks.deco_rbmk, 'C', ModItems.circuit_targeting_tier3, 'N', ModItems.ammo_nuke);

            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.hadron_core, 1), "CCC", "DSD", "CCC", 'C', ModBlocks.hadron_coil_alloy, 'D', ModBlocks.hadron_diode, 'S', ModItems.circuit_schrabidium);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_rod, 1), "C", "R", "C", 'C', ModItems.hull_small_steel, 'R', ModBlocks.rbmk_blank);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_rod_mod, 1), "BGB", "GRG", "BGB", 'G', ModBlocks.block_graphite, 'R', ModBlocks.rbmk_rod, 'B', ANY_BISMOID.nugget());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.rbmk_boiler, 1), "CPC", "CRC", "CPC", 'C', ModItems.board_copper, 'P', ModItems.pipes_steel, 'R', ModBlocks.rbmk_blank);
        }

        if (GeneralConfig.enableBabyMode) {
            addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.cordite, 3), ModItems.ballistite, Items.GUNPOWDER, ItemStackUtil.itemStackFrom(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE));
            addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.ingot_semtex, 3), Items.SLIME_BALL, Blocks.TNT, KNO.dust());
            addShapelessAuto(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel), new IngredientContainsTag(ItemFluidCanister.getFullCanister(ModForgeFluids.oil)), Items.REDSTONE);

            addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.ore_uranium, 1), ModBlocks.ore_uranium_scorched, Items.WATER_BUCKET);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ore_uranium, 8), "OOO", "OBO", "OOO", 'O', ModBlocks.ore_uranium_scorched, 'B', Items.WATER_BUCKET);
            addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.ore_nether_uranium, 1), ModBlocks.ore_nether_uranium_scorched, Items.WATER_BUCKET);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ore_nether_uranium, 8), "OOO", "OBO", "OOO", 'O', ModBlocks.ore_nether_uranium_scorched, 'B', Items.WATER_BUCKET);
            addShapelessAuto(ItemStackUtil.itemStackFrom(ModBlocks.ore_gneiss_uranium, 1), ModBlocks.ore_gneiss_uranium_scorched, Items.WATER_BUCKET);
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModBlocks.ore_gneiss_uranium, 8), "OOO", "OBO", "OOO", 'O', ModBlocks.ore_gneiss_uranium_scorched, 'B', Items.WATER_BUCKET);

            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_iron, 4), "##", "##", '#', IRON.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_gold, 4), "##", "##", '#', GOLD.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_aluminium, 4), "##", "##", '#', AL.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_titanium, 4), "##", "##", '#', TI.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_copper, 4), "##", "##", '#', CU.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_lead, 4), "##", "##", '#', PB.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_steel, 4), "##", "##", '#', STEEL.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_schrabidium, 4), "##", "##", '#', SA326.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_advanced_alloy, 4), "##", "##", '#', ALLOY.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_saturnite, 4), "##", "##", '#', BIGMT.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.plate_combine_steel, 4), "##", "##", '#', CMB.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.neutron_reflector, 4), "##", "##", '#', W.ingot());

            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wire_aluminium, 16), "###", '#', AL.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wire_copper, 16), "###", '#', CU.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wire_tungsten, 16), "###", '#', W.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wire_red_copper, 16), "###", '#', MINGRADE.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wire_advanced_alloy, 16), "###", '#', ALLOY.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wire_gold, 16), "###", '#', GOLD.ingot());
            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.wire_schrabidium, 16), "###", '#', SA326.ingot());

            addRecipeAuto(ItemStackUtil.itemStackFrom(ModItems.book_of_), "BGB", "GAG", "BGB", 'B', ModItems.egg_balefire_shard, 'G', GOLD.ingot(), 'A', Items.BOOK);
        }

        addShapelessAuto(ItemStackUtil.itemStackFrom(ModItems.drillbit_dnt_diamond, 1), ModItems.drillbit_dnt, ItemStackUtil.itemStackFrom(ModItems.ore_bedrock, 1, 3));
    }

    public static void addSmelting() {
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_thorium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TH232)), 3.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_uranium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM)), 6.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_uranium_scorched), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM)), 6.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_nether_uranium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM)), 12.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_nether_uranium_scorched), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM)), 12.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_nether_plutonium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM)), 24.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_titanium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM)), 3.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_copper), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), 2.5F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_tungsten), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN)), 6.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_nether_tungsten), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN)), 12.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_aluminium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM)), 2.5F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_lead), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD)), 3.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_beryllium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BERYLLIUM)), 2.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_schrabidium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM)), 128.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_nether_schrabidium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM)), 256.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_uranium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM), 2), 12.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_thorium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TH232), 2), 6.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_titanium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM), 3), 6.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_copper), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER), 3), 5.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_tungsten), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN), 3), 12.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_aluminium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM), 3), 5.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_lead), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD), 3), 6.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_lithium), ItemStackUtil.itemStackFrom(ModItems.lithium), 20.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_meteor_starmetal), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STARMETAL)), 50.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_cobalt), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COBALT)), 2.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_nether_cobalt), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COBALT)), 2.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_cinnebar), ItemStackUtil.itemStackFrom(ModItems.cinnebar), 1.0F);

        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_gneiss_iron), ItemStackUtil.itemStackFrom(Items.IRON_INGOT), 5.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_gneiss_gold), ItemStackUtil.itemStackFrom(Items.GOLD_INGOT), 5.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_gneiss_uranium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM)), 12.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_gneiss_uranium_scorched), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM)), 12.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_gneiss_copper), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), 5F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_gneiss_lithium), ItemStackUtil.itemStackFrom(ModItems.lithium), 10F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_gneiss_schrabidium), ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM)), 256.0F);

        GameRegistry.addSmelting(ModItems.casing_357, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), 0.1F);
        GameRegistry.addSmelting(ModItems.casing_44, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), 0.1F);
        GameRegistry.addSmelting(ModItems.casing_9, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), 0.1F);
        GameRegistry.addSmelting(ModItems.casing_50, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), 0.1F);
        GameRegistry.addSmelting(ModItems.casing_buckshot, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), 0.1F);

        GameRegistry.addSmelting(ModItems.circuit_schrabidium, ItemStackUtil.itemStackFrom(ModItems.circuit_gold, 1), 1.0F);
        GameRegistry.addSmelting(ModItems.circuit_gold, ItemStackUtil.itemStackFrom(ModItems.circuit_red_copper, 1), 1.0F);
        GameRegistry.addSmelting(ModItems.circuit_red_copper, ItemStackUtil.itemStackFrom(ModItems.circuit_copper, 1), 1.0F);
        GameRegistry.addSmelting(ModItems.circuit_copper, ItemStackUtil.itemStackFrom(ModItems.circuit_aluminium, 1), 1.0F);

        GameRegistry.addSmelting(ModItems.powder_australium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AUSTRALIUM)), 5.0F);
        GameRegistry.addSmelting(ModItems.powder_weidanium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.WEIDANIUM)), 5.0F);
        GameRegistry.addSmelting(ModItems.powder_reiium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.REIIUM)), 5.0F);
        GameRegistry.addSmelting(ModItems.powder_unobtainium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.UNOBTAINIUM)), 5.0F);
        GameRegistry.addSmelting(ModItems.powder_daffergon, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DAFFERGON)), 5.0F);
        GameRegistry.addSmelting(ModItems.powder_verticium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.VERTICIUM)), 5.0F);
        GameRegistry.addSmelting(Item.getItemFromBlock(ModBlocks.ore_australium), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AUSTRALIUM)), 2.5F);

        GameRegistry.addSmelting(ModItems.powder_radspice, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RADSPICE)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_lead, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_neptunium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEPTUNIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_polonium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLONIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_schrabidium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM)), 5.0F);
        GameRegistry.addSmelting(ModItems.powder_aluminium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_beryllium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BERYLLIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_copper, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_gold, ItemStackUtil.itemStackFrom(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_iron, ItemStackUtil.itemStackFrom(Items.IRON_INGOT), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_titanium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_tungsten, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_uranium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_thorium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TH232)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_plutonium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_advanced_alloy, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ADVANCED_ALLOY)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_combine_steel, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COMBINE_STEEL)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_magnetized_tungsten, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.MAGNETIZED_TUNGSTEN)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_red_copper, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_steel, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_lithium, ItemStackUtil.itemStackFrom(ModItems.lithium), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_dura_steel, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DURA_STEEL)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_polymer, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLYMER)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_bakelite, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BAKELITE)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_lanthanium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LANTHANIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_actinium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ACTINIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_boron, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BORON)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_desh, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DESH)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_cobalt, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COBALT)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_schrabidate, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDATE)), 5.0F);
        GameRegistry.addSmelting(ModItems.powder_asbestos, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ASBESTOS)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_cadmium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CADMIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_bismuth, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BISMUTH)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_zirconium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ZIRCONIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_tcalloy, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TCALLOY)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_cdalloy, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CDALLOY)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_au198, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AU198)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_tantalium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TANTALIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_astatine, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ASTATINE)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_ac227, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.AC227)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_co60, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CO60)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_bromine, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BROMINE)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_cerium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CERIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_caesium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CAESIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_iodine, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.IODINE)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_i131, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.I131)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_neodymium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NEODYMIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_pb209, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PB209)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_ra226, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RA226)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_strontium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STRONTIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_sr90, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SR90)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_tennessine, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TENNESSINE)), 1.0F);
        GameRegistry.addSmelting(ModItems.powder_niobium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.NIOBIUM)), 1.0F);
        GameRegistry.addSmelting(ModItems.ball_resin, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BIORUBBER)), 0.1F);

        GameRegistry.addSmelting(ModItems.powder_cobalt_tiny, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.COBALT)), 0.1F);
        GameRegistry.addSmelting(ModItems.powder_co60_tiny, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.CO60)), 0.1F);
        GameRegistry.addSmelting(ModItems.powder_pb209_tiny, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.PB209)), 0.1F);
        GameRegistry.addSmelting(ModItems.powder_sr90_tiny, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SR90)), 0.1F);
        GameRegistry.addSmelting(ModItems.powder_au198_tiny, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.AU198)), 0.1F);
        GameRegistry.addSmelting(ModItems.powder_radspice_tiny, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.RADSPICE)), 0.1F);

        GameRegistry.addSmelting(ModItems.rag_damp, ItemStackUtil.itemStackFrom(ModItems.rag), 0.1F);
        GameRegistry.addSmelting(ModItems.rag_piss, ItemStackUtil.itemStackFrom(ModItems.rag), 0.1F);
        GameRegistry.addSmelting(ModItems.mask_damp, ItemStackUtil.itemStackFrom(ModItems.mask_rag), 0.3F);
        GameRegistry.addSmelting(ModItems.mask_piss, ItemStackUtil.itemStackFrom(ModItems.mask_rag), 0.3F);

        GameRegistry.addSmelting(ModItems.powder_coal, ItemStackUtil.itemStackFrom(ModItems.coke), 1.0F);
        GameRegistry.addSmelting(ModItems.briquette_lignite, ItemStackUtil.itemStackFrom(ModItems.coke), 1.0F);
        GameRegistry.addSmelting(ModItems.oil_tar, ItemStackUtil.itemStackFrom(ModItems.powder_coal), 1.0F);

        GameRegistry.addSmelting(ModItems.combine_scrap, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COMBINE_STEEL)), 1.0F);

        GameRegistry.addSmelting(Items.BONE, ItemStackUtil.itemStackFrom(Items.SLIME_BALL, 3), 0.0F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(Items.DYE, 1, 15), ItemStackUtil.itemStackFrom(Items.SLIME_BALL, 1), 0.0F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(Blocks.GRAVEL, 1), ItemStackUtil.itemStackFrom(Blocks.COBBLESTONE, 1), 0.0F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.gravel_obsidian), ItemStackUtil.itemStackFrom(Blocks.OBSIDIAN), 0.0F);

        GameRegistry.addSmelting(ModItems.powder_euphemium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.EUPHEMIUM)), 10.0F);
        GameRegistry.addSmelting(ModItems.powder_dineutronium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DINEUTRONIUM)), 5.0F);
        GameRegistry.addSmelting(ModItems.powder_osmiridium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.OSMIRIDIUM)), 10.0F);

        GameRegistry.addSmelting(ModItems.lodestone, ItemStackUtil.itemStackFrom(ModItems.crystal_iron, 1), 5.0F);
        GameRegistry.addSmelting(ModItems.crystal_iron, ItemStackUtil.itemStackFrom(Items.IRON_INGOT, 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_gold, ItemStackUtil.itemStackFrom(Items.GOLD_INGOT, 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_redstone, ItemStackUtil.itemStackFrom(Items.REDSTONE, 6), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_uranium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_thorium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TH232), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_plutonium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_titanium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_sulfur, ItemStackUtil.itemStackFrom(ModItems.sulfur, 6), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_niter, ItemStackUtil.itemStackFrom(ModItems.niter, 6), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_copper, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_tungsten, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_aluminium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_fluorite, ItemStackUtil.itemStackFrom(ModItems.fluorite, 6), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_beryllium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.BERYLLIUM), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_lead, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_schrabidium, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SCHRABIDIUM), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_rare, ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 1), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_phosphorus, ItemStackUtil.itemStackFrom(ModItems.powder_fire, 6), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_lithium, ItemStackUtil.itemStackFrom(ModItems.lithium, 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_starmetal, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STARMETAL), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_trixite, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM), 4), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_cinnebar, ItemStackUtil.itemStackFrom(ModItems.cinnebar, 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_cobalt, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COBALT), 2), 2.0F);

        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.gravel_diamond), ItemStackUtil.itemStackFrom(Items.DIAMOND), 3.0F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.sand_uranium), ItemStackUtil.itemStackFrom(ModBlocks.glass_uranium), 0.25F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.sand_polonium), ItemStackUtil.itemStackFrom(ModBlocks.glass_polonium), 0.75F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.waste_trinitite), ItemStackUtil.itemStackFrom(ModBlocks.glass_trinitite), 0.25F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.waste_trinitite_red), ItemStackUtil.itemStackFrom(ModBlocks.glass_trinitite), 0.25F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.sand_quartz), ItemStackUtil.itemStackFrom(ModBlocks.glass_quartz), 0.75F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.sand_boron), ItemStackUtil.itemStackFrom(ModBlocks.glass_boron), 0.25F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.sand_lead), ItemStackUtil.itemStackFrom(ModBlocks.glass_lead), 0.25F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.ash_digamma), ItemStackUtil.itemStackFrom(ModBlocks.glass_ash), 10F);
        GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModBlocks.basalt), ItemStackUtil.itemStackFrom(ModBlocks.basalt_smooth), 0.1F);
        GameRegistry.addSmelting(ModItems.crystal_diamond, ItemStackUtil.itemStackFrom(Items.DIAMOND, 2), 2.0F);
        GameRegistry.addSmelting(ModItems.crystal_schraranium, ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 2), 2.0F);
        GameRegistry.addSmelting(ModItems.ingot.getItemStack(MaterialMineral.SCHRARANIUM), ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.SCHRABIDIUM), 1), 2.0F);

        GameRegistry.addSmelting(ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL), ItemHot.heatUp(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL))), 1.0F);
        for (int i = 0; i < 10; i++)
            GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModItems.ingot_steel_dusted, 1, i), ItemHot.heatUp(ItemStackUtil.itemStackFrom(ModItems.ingot_steel_dusted, 1, i)), 1.0F);
        GameRegistry.addSmelting(ModItems.ingot.getItemStack(MaterialMineral.METEORITE), ItemHot.heatUp(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE))), 1.0F);
        GameRegistry.addSmelting(ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED), ItemHot.heatUp(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED))), 1.0F);
        GameRegistry.addSmelting(ModItems.blade_meteorite, ItemHot.heatUp(ItemStackUtil.itemStackFrom(ModItems.blade_meteorite)), 1.0F);
        GameRegistry.addSmelting(ModItems.meteorite_sword, ItemHot.heatUp(ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_seared)), 1.0F);

        GameRegistry.addSmelting(ModItems.ball_fireclay, ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.FIREBRICK), 1), 0.1F);
    }

    public static void addBedrockOreSmelting() {
        for (final Integer oreMeta : BedrockOreRegistry.oreIndexes.keySet()) {
            GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), 2F);
            GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_cleaned, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), 2F);
            GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_deepcleaned, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), 2F);
            GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_nitrated, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), 2F);
            GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_seared, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), 2F);
            GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_perfect, 1, oreMeta), ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), 2F);
            GameRegistry.addSmelting(ItemStackUtil.itemStackFrom(ModItems.ore_bedrock_enriched, 1, oreMeta), ItemBedrockOre.getOut(oreMeta, ItemBedrockOre.getOutType(oreMeta) == 2 ? 2 : 1), 2F);
        }
    }

    public static void addSlabStair(final Block slab, final Block stair, final Block block) {
        addRecipeAuto(ItemStackUtil.itemStackFrom(slab, 6), "###", '#', block);
        addRecipeAuto(ItemStackUtil.itemStackFrom(stair, 8), "#  ", "## ", "###", '#', block);
        addShapelessAuto(ItemStackUtil.itemStackFrom(block, 3), stair, stair, stair, stair);
        addRecipeAuto(ItemStackUtil.itemStackFrom(stair, 4), "#  ", "## ", "###", '#', slab);
        addShapelessAuto(ItemStackUtil.itemStackFrom(block, 1), slab, slab);
    }

    public static void addBillet(final ItemStack billet, final ItemStack nugget, final String... ore) {
        for (final String o : ore)
            addRecipeAuto(ItemStackUtil.itemStackFrom(billet), "###", "###", '#', o);

        addBillet(billet, nugget);
    }

    public static void addBillet(final ItemStack billet, final ItemStack nugget) {
        addRecipeAuto(ItemStackUtil.itemStackFrom(billet), "###", "###", '#', nugget);
        addShapelessAuto(ItemStackUtil.itemStackFrom(nugget, 6), billet);
    }

    public static void addBilletByIngot(final ItemStack billet, final ItemStack ingot, final String ore) {
        for (final char o : ore.toCharArray()) addShapelessAuto(ItemStackUtil.itemStackFrom(billet, 3), o, o);
        addShapelessAuto(ItemStackUtil.itemStackFrom(billet, 3), ingot, ingot);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ingot, 2), billet, billet, billet);
    }

    public static void addBilletByIngot(final Item billet, final Item ingot) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(billet, 3), ingot, ingot);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ingot, 2), billet, billet, billet);
    }

    public static void addBilletByIngot(final ItemStack billet, final ItemStack ingot) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(billet, 3), ingot, ingot);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ingot, 2), billet, billet, billet);
    }

    public static void addBilletByIngot(final Item billet, final ItemStack ingot) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(billet, 3), ingot, ingot);
        addShapelessAuto(ItemStackUtil.itemStackFrom(ingot, 2), billet, billet, billet);
    }

    //Fill rods with one billet
    public static void addRodBillet(final ItemStack billet, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_empty, billet);
    }

    //Fill rods with two billets
    public static void addDualRodBillet(final ItemStack billet, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_dual_empty, billet, billet);
    }

    //Fill rods with three billets
    public static void addQuadRodBillet(final ItemStack billet, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_quad_empty, billet, billet, billet, billet);
    }

    //Fill rods with one billet + unload
    public static void addRodBilletUnload(final ItemStack billet, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_empty, billet);
        addShapelessAuto(ItemStackUtil.itemStackFrom(billet, 1), ItemStackUtil.itemStackFrom(out, 1, 0));
    }

    //Fill rods with two billets + unload
    public static void addDualRodBilletUnload(final ItemStack billet, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_dual_empty, billet, billet);
        addShapelessAuto(ItemStackUtil.itemStackFrom(billet, 2), ItemStackUtil.itemStackFrom(out, 1, 0));
    }

    //Fill rods with three billets + unload
    public static void addQuadRodBilletUnload(final ItemStack billet, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_quad_empty, billet, billet, billet, billet);
        addShapelessAuto(ItemStackUtil.itemStackFrom(billet, 4), ItemStackUtil.itemStackFrom(out, 1, 0));
    }

    //Fill rods with 6 nuggets
    public static void addRBMKRod(final ItemStack billet, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rbmk_fuel_empty, billet, billet, billet, billet, billet, billet, billet, billet);
    }

    //Bundled 1/9 recipes
    public static void add1To9Pair(final Item one, final Item nine) {
        add1To9(ItemStackUtil.itemStackFrom(one), ItemStackUtil.itemStackFrom(nine, 9));
        add9To1(ItemStackUtil.itemStackFrom(nine), ItemStackUtil.itemStackFrom(one));
    }

    public static void add1To9Pair(final ItemStack one, final Item nine) {
        add1To9(ItemStackUtil.itemStackFrom(one), ItemStackUtil.itemStackFrom(nine, 9));
        add9To1(ItemStackUtil.itemStackFrom(nine), ItemStackUtil.itemStackFrom(one));
    }

    public static void add1To9Pair(final ItemStack one, final ItemStack nine) {
        add1To9(ItemStackUtil.itemStackFrom(one), ItemStackUtil.itemStackFrom(nine, 9));
        add9To1(ItemStackUtil.itemStackFrom(nine), ItemStackUtil.itemStackFrom(one));
    }

    public static void add1To9Pair(final Block one, final Item nine) {
        add1To9(ItemStackUtil.itemStackFrom(one), ItemStackUtil.itemStackFrom(nine, 9));
        add9To1(ItemStackUtil.itemStackFrom(nine), ItemStackUtil.itemStackFrom(one));
    }

    public static void add1To9Pair(final Block one, final ItemStack nine) {
        add1To9(ItemStackUtil.itemStackFrom(one), ItemStackUtil.itemStackFrom(nine, 9));
        add9To1(ItemStackUtil.itemStackFrom(nine), ItemStackUtil.itemStackFrom(one));
    }

    public static void add1To9PairSameMeta(final Item one, final Item nine, final int meta) {
        add1To9SameMeta(one, nine, meta);
        add9To1SameMeta(nine, one, meta);
    }

    public static void add1To9SameMeta(final Item one, final Item nine, final int meta) {
        add1To9(ItemStackUtil.itemStackFrom(one, 1, meta), ItemStackUtil.itemStackFrom(nine, 9, meta));
    }

    //Full set of nugget, ingot and block
    public static void addMineralSet(final Item nugget, final Item ingot, final Block block) {
        add1To9(ItemStackUtil.itemStackFrom(ingot), ItemStackUtil.itemStackFrom(nugget, 9));
        add9To1(ItemStackUtil.itemStackFrom(nugget), ItemStackUtil.itemStackFrom(ingot));
        add1To9(ItemStackUtil.itemStackFrom(block), ItemStackUtil.itemStackFrom(ingot, 9));
        add9To1(ItemStackUtil.itemStackFrom(ingot), ItemStackUtil.itemStackFrom(block));
    }

    private static void addMineralSet(final Item nugget, final ItemStack ingot, final Block block) {
        add1To9(ItemStackUtil.itemStackFrom(ingot), ItemStackUtil.itemStackFrom(nugget, 9));
        add9To1(ItemStackUtil.itemStackFrom(nugget), ItemStackUtil.itemStackFrom(ingot));
        add1To9(ItemStackUtil.itemStackFrom(block), ItemStackUtil.itemStackFrom(ingot, 9));
        add9To1(ItemStackUtil.itemStackFrom(ingot), ItemStackUtil.itemStackFrom(block));
    }

    private static void addMineralSet(final ItemStack nugget, final ItemStack ingot, final Block block) {
        add1To9(ItemStackUtil.itemStackFrom(ingot), ItemStackUtil.itemStackFrom(nugget, 9));
        add9To1(ItemStackUtil.itemStackFrom(nugget), ItemStackUtil.itemStackFrom(ingot));
        add1To9(ItemStackUtil.itemStackFrom(block), ItemStackUtil.itemStackFrom(ingot, 9));
        add9To1(ItemStackUtil.itemStackFrom(ingot), ItemStackUtil.itemStackFrom(block));
    }

    public static void add9To1SameMeta(final Item nine, final Item one, final int meta) {
        add9To1(ItemStackUtil.itemStackFrom(nine, 1, meta), ItemStackUtil.itemStackFrom(one, 1, meta));
    }

    //Decompress one item into nine
    public static void add1To9(final Block one, final Item nine) {
        add1To9(ItemStackUtil.itemStackFrom(one), ItemStackUtil.itemStackFrom(nine, 9));
    }

    public static void add1To9(final Item one, final Item nine) {
        add1To9(ItemStackUtil.itemStackFrom(one), ItemStackUtil.itemStackFrom(nine, 9));
    }

    public static void add1To9(final ItemStack one, final ItemStack nine) {
        addShapelessAuto(nine, one);
    }

    public static void add1To9(final Block one, final ItemStack nine) {
        add1To9(ItemStackUtil.itemStackFrom(one), ItemStackUtil.itemStackFrom(nine));
    }

    //Compress nine items into one

    public static void add9To1(final Item nine, final Block one) {
        add9To1(ItemStackUtil.itemStackFrom(nine), ItemStackUtil.itemStackFrom(one));
    }

    public static void add9To1(final Item nine, final Item one) {
        add9To1(ItemStackUtil.itemStackFrom(nine), ItemStackUtil.itemStackFrom(one));
    }

    public static void add9To1(final ItemStack nine, final Block one) {
        add9To1(ItemStackUtil.itemStackFrom(nine), ItemStackUtil.itemStackFrom(one));
    }

    public static void add9To1(final ItemStack nine, final ItemStack one) {
        addRecipeAuto(one, "###", "###", "###", '#', nine);
    }

    //Fill rods with 6 nuggets
    public static void addRod(final Item nugget, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_empty, nugget, nugget, nugget, nugget, nugget, nugget);
    }

    //Fill rods with 12 nuggets
    public static void addDualRod(final Item ingot, final Item nugget, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_dual_empty, ingot, nugget, nugget, nugget);
    }

    //Fill rods with 24 nuggets
    public static void addQuadRod(final Item ingot, final Item nugget, final Item out) {
        addShapelessAuto(ItemStackUtil.itemStackFrom(out), ModItems.rod_quad_empty, ingot, ingot, nugget, nugget, nugget, nugget, nugget, nugget);
    }

    public static void addSword(final Item ingot, final Item sword) {
        addRecipeAuto(ItemStackUtil.itemStackFrom(sword), "I", "I", "S", 'I', ingot, 'S', Items.STICK);
    }

    public static void addPickaxe(final Item ingot, final Item pick) {
        addRecipeAuto(ItemStackUtil.itemStackFrom(pick), "III", " S ", " S ", 'I', ingot, 'S', Items.STICK);
    }

    public static void addAxe(final Item ingot, final Item axe) {
        addRecipeAuto(ItemStackUtil.itemStackFrom(axe), "II", "IS", " S", 'I', ingot, 'S', Items.STICK);
    }

    public static void addShovel(final Item ingot, final Item shovel) {
        addRecipeAuto(ItemStackUtil.itemStackFrom(shovel), "I", "S", "S", 'I', ingot, 'S', Items.STICK);
    }

    public static void addHoe(final Item ingot, final Item hoe) {
        addRecipeAuto(ItemStackUtil.itemStackFrom(hoe), "II", " S", " S", 'I', ingot, 'S', Items.STICK);
    }

    public static void addRecipeAuto(final ItemStack output, final Object... args) {

        boolean shouldUseOD = false;
        boolean patternEnded = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                if (patternEnded) {
                    shouldUseOD = true;
                    break;
                }
            } else {
                patternEnded = true;
            }
        }

        final ResourceLocation loc = getRecipeName(output);
        final IRecipe recipe;
        if (shouldUseOD) {
            recipe = new ShapedOreRecipe(loc, output, args);
        } else {
            final CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(args);
            recipe = new ShapedRecipes(output.getItem().getRegistryName().toString(), primer.width, primer.height, primer.input, output);
        }
        recipe.setRegistryName(loc);
        hack.getRegistry().register(recipe);
    }

    public static void addShapelessAuto(final ItemStack output, final Object... args) {

        boolean shouldUseOD = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                shouldUseOD = true;
                break;
            }
        }

        final ResourceLocation loc = getRecipeName(output);
        final IRecipe recipe;
        if (shouldUseOD) {
            recipe = new ShapelessOreRecipe(loc, output, args);
        } else {
            recipe = new ShapelessRecipes(loc.getNamespace(), output, buildInput(args));
        }
        recipe.setRegistryName(loc);
        hack.getRegistry().register(recipe);
    }

    public static ResourceLocation getRecipeName(final ItemStack output) {
        final ResourceLocation loc = new ResourceLocation(RefStrings.MODID, output.getItem().getRegistryName().getPath());
        int i = 0;
        ResourceLocation r_loc = loc;
        while (net.minecraft.item.crafting.CraftingManager.REGISTRY.containsKey(r_loc)) {
            i++;
            r_loc = new ResourceLocation(RefStrings.MODID, loc.getPath() + "_" + i);
        }
        return r_loc;
    }

    public static NonNullList<Ingredient> buildInput(final Object[] args) {
        final NonNullList<Ingredient> list = NonNullList.create();
        for (Object obj : args) {
            if (obj instanceof ItemFuelRod) {
                obj = ItemStackUtil.itemStackFrom((Item) obj);
            }
            if (obj instanceof Ingredient) {
                list.add((Ingredient) obj);
            } else {
                Ingredient i = CraftingHelper.getIngredient(obj);
                if (i == null) {
                    i = Ingredient.EMPTY;
                }
                list.add(i);
            }
        }
        return list;
    }

    public static class IngredientContainsTag extends Ingredient {

        private final ItemStack stack;

        public IngredientContainsTag(final ItemStack stack) {
            super(stack);
            this.stack = stack;
        }

        @Override
        public boolean apply(final ItemStack p_apply_1_) {
            if (p_apply_1_ == null) {
                return false;
            } else {
                return Library.areItemStacksCompatible(stack, p_apply_1_, false);
            }
        }

        @Override
        public boolean isSimple() {
            return false;
        }
    }

    //B r u h why wasn't the constructor visible in the first place?
    public static class IngredientNBT2 extends IngredientNBT {

        public IngredientNBT2(final ItemStack stack) {
            super(stack);
        }

    }
}
