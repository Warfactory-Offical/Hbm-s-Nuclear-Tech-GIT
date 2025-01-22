package com.hbm.handler;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.gui.GUIScreenBobmazon.Offer;
import com.hbm.inventory.gui.GUIScreenBobmazon.Requirement;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemBattery;
import com.hbm.items.tool.ItemFluidCanister;
import com.hbm.items.tool.ItemGasCanister;

import net.minecraft.item.ItemStack;

public class BobmazonOfferFactory {

	public static List<Offer> materials = new ArrayList<Offer>();
	public static List<Offer> machines = new ArrayList<Offer>();
	public static List<Offer> weapons = new ArrayList<Offer>();
	public static List<Offer> tools = new ArrayList<Offer>();
	public static List<Offer> special = new ArrayList<Offer>();
	
	public static void reset(){
		materials.clear();
		machines.clear();
		weapons.clear();
		tools.clear();
		special.clear();
	}
	
	public static void init() {

		int inflation = 5;
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM)), Requirement.NUCLEAR, 6 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U233)), Requirement.NUCLEAR, 20 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.U238)), Requirement.NUCLEAR, 15 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TH232)), Requirement.NUCLEAR, 4 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.PLUTONIUM)), Requirement.NUCLEAR, 25 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM)), Requirement.STEEL, 2 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER)), Requirement.STEEL, 2 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER)), Requirement.STEEL, 4 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN)), Requirement.STEEL, 3 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM)), Requirement.STEEL, 2 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL)), Requirement.STEEL, 4 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.LEAD)), Requirement.STEEL, 2 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.POLYMER)), Requirement.OIL, 8 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.URANIUM_FUEL)), Requirement.NUCLEAR, 18 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.THORIUM_FUEL)), Requirement.NUCLEAR, 16 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.DESH)), Requirement.OIL, 16 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.SATURNITE)), Requirement.STEEL, 8 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.lithium), Requirement.CHEMICS, 6 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.solid_fuel), Requirement.OIL, 4 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.lignite), Requirement.STEEL, 2 * inflation));
		materials.add(new Offer(ItemFluidCanister.getFullCanister(ModForgeFluids.oil), Requirement.OIL, 4 * inflation));
		materials.add(new Offer(ItemFluidCanister.getFullCanister(ModForgeFluids.diesel), Requirement.OIL, 16 * inflation));
		materials.add(new Offer(ItemFluidCanister.getFullCanister(ModForgeFluids.petroil), Requirement.OIL, 12 * inflation));
		materials.add(new Offer(ItemFluidCanister.getFullCanister(ModForgeFluids.kerosene), Requirement.OIL, 20 * inflation));
		materials.add(new Offer(ItemFluidCanister.getFullCanister(ModForgeFluids.nitan), Requirement.OIL, 100 * inflation));
		materials.add(new Offer(ItemGasCanister.getFullCanister(ModForgeFluids.gas), Requirement.OIL, 8 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.motor), Requirement.ASSEMBLY, 12 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.rtg_unit), Requirement.NUCLEAR, 25 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.circuit_aluminium), Requirement.ASSEMBLY, 4 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.circuit_copper), Requirement.ASSEMBLY, 6 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.circuit_red_copper), Requirement.ASSEMBLY, 10 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.circuit_gold), Requirement.CHEMICS, 16 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.pellet_gas), Requirement.CHEMICS, 4 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.magnetron), Requirement.ASSEMBLY, 10 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.pellet_rtg), Requirement.NUCLEAR, 27 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.piston_selenium), Requirement.ASSEMBLY, 12 * inflation));
		materials.add(new Offer(ItemBattery.getFullBattery(ModItems.battery_advanced), Requirement.ASSEMBLY, 15 * inflation));
		materials.add(new Offer(ItemBattery.getFullBattery(ModItems.battery_lithium), Requirement.CHEMICS, 30 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.arc_electrode), Requirement.ASSEMBLY, 15 * inflation));
		materials.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.fuse), Requirement.ASSEMBLY, 5 * inflation));

		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth, 16), Requirement.CHEMICS, 32 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.brick_compound, 8), Requirement.CHEMICS, 48 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.barbed_wire, 16), Requirement.ASSEMBLY, 12 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_siren), Requirement.ASSEMBLY, 12 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.vault_door), Requirement.CHEMICS, 250 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.blast_door), Requirement.CHEMICS, 120 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_epress), Requirement.OIL, 60 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_difurnace_off), Requirement.STEEL, 26 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_gascent), Requirement.OIL, 100 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_diesel), Requirement.CHEMICS, 65 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_geo), Requirement.CHEMICS, 30 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_battery), Requirement.ASSEMBLY, 30 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_lithium_battery), Requirement.CHEMICS, 60 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_assembler), Requirement.ASSEMBLY, 30 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_chemplant), Requirement.CHEMICS, 50 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_off), Requirement.CHEMICS, 25 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_electric_off), Requirement.OIL, 60 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_shredder), Requirement.ASSEMBLY, 45 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_well), Requirement.OIL, 40 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_refinery), Requirement.OIL, 80 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.absorber), Requirement.CHEMICS, 10 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.absorber_green), Requirement.OIL, 25 * inflation));
		machines.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.decon), Requirement.CHEMICS, 15 * inflation));

		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.loot_10), Requirement.OIL, 50 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.loot_15), Requirement.OIL, 65 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.loot_misc), Requirement.NUCLEAR, 65 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.launch_pad), Requirement.OIL, 95 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_radar), Requirement.OIL, 90 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.designator), Requirement.CHEMICS, 35 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.designator_range), Requirement.CHEMICS, 50 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.sat_chip), Requirement.CHEMICS, 35 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.turret_cheapo), Requirement.CHEMICS, 70 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.turret_cheapo_ammo), Requirement.ASSEMBLY, 20 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.turret_control), Requirement.CHEMICS, 35 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.turret_chip), Requirement.CHEMICS, 80 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.turret_biometry), Requirement.CHEMICS, 15 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.mine_ap, 4), Requirement.ASSEMBLY, 25 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.emp_bomb), Requirement.CHEMICS, 90 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.det_cord, 16), Requirement.ASSEMBLY, 50 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.det_charge), Requirement.CHEMICS, 25 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.detonator), Requirement.ASSEMBLY, 15 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.detonator_laser), Requirement.CHEMICS, 60 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.defuser), Requirement.OIL, 5 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_revolver), Requirement.ASSEMBLY, 15 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_nopip), Requirement.ASSEMBLY, 20 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_minigun), Requirement.OIL, 100 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_panzerschreck), Requirement.ASSEMBLY, 95 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_hk69), Requirement.ASSEMBLY, 60 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_uzi), Requirement.OIL, 80 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_lever_action), Requirement.ASSEMBLY, 60 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_bolt_action), Requirement.ASSEMBLY, 35 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_ammo, 6), Requirement.OIL, 12 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_357_desh, 6), Requirement.OIL, 36 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_44, 6), Requirement.OIL, 12 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_44_ap, 6), Requirement.OIL, 18 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_5mm, 50), Requirement.OIL, 50 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_5mm_du, 50), Requirement.OIL, 75 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket), Requirement.OIL, 5 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_incendiary), Requirement.OIL, 8 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_sleek), Requirement.OIL, 12 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade), Requirement.OIL, 4 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_incendiary), Requirement.OIL, 6 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_sleek), Requirement.OIL, 10 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr, 32), Requirement.OIL, 24 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr_ap, 32), Requirement.OIL, 32 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge, 6), Requirement.OIL, 18 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_slug, 6), Requirement.OIL, 20 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_flechette, 6), Requirement.OIL, 22 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.grenade_if_generic, 3), Requirement.CHEMICS, 15 * inflation));
		weapons.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.grenade_if_he, 3), Requirement.CHEMICS, 25 * inflation));

		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.crate_can, 1), Requirement.STEEL, 20 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_keyforge), Requirement.STEEL, 10 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_telelinker), Requirement.CHEMICS, 35 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_satlinker), Requirement.CHEMICS, 50 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.oil_detector), Requirement.CHEMICS, 45 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.geiger_counter), Requirement.CHEMICS, 10 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.key), Requirement.STEEL, 2 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.padlock), Requirement.STEEL, 5 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.padlock_reinforced), Requirement.OIL, 15 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.syringe_antidote, 6), Requirement.STEEL, 10 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_stimpak, 4), Requirement.STEEL, 10 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_medx, 4), Requirement.STEEL, 10 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.radaway, 6), Requirement.ASSEMBLY, 30 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.radaway_strong, 3), Requirement.ASSEMBLY, 35 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.radx, 4), Requirement.ASSEMBLY, 20 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.pill_iodine, 6), Requirement.ASSEMBLY, 20 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gas_mask_filter, 1), Requirement.ASSEMBLY, 5 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_kit_1, 4), Requirement.OIL, 20 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_kit_2, 2), Requirement.OIL, 45 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.hazmat_kit), Requirement.ASSEMBLY, 40 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.hazmat_red_kit), Requirement.CHEMICS, 100 * inflation));
		tools.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.hazmat_grey_kit), Requirement.OIL, 160 * inflation));

		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.nuke_starter_kit), Requirement.HIDDEN, 20 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.nuke_advanced_kit), Requirement.HIDDEN, 30 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.boy_kit), Requirement.HIDDEN, 35 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.missile_kit), Requirement.HIDDEN, 60 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.grenade_kit), Requirement.HIDDEN, 20 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.jetpack_vector), Requirement.HIDDEN, 10 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.jetpack_tank), Requirement.HIDDEN, 3 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_lever_action), Requirement.HIDDEN, 5 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge, 24), Requirement.HIDDEN, 5 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_incendiary, 24), Requirement.HIDDEN, 7 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_uzi_silencer, 1), Requirement.HIDDEN, 5 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr, 64), Requirement.HIDDEN, 3 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr_ap, 64), Requirement.HIDDEN, 4 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_hk69, 24), Requirement.HIDDEN, 8 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade, 6), Requirement.HIDDEN, 4 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_sleek, 6), Requirement.HIDDEN, 6 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_kit_1, 10), Requirement.HIDDEN, 1 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.gun_kit_2, 5), Requirement.HIDDEN, 3 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_epress, 2), Requirement.HIDDEN, 5 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_assembler, 1), Requirement.HIDDEN, 5 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_chemplant, 1), Requirement.HIDDEN, 5 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_boiler_off, 1), Requirement.HIDDEN, 5 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_well, 1), Requirement.HIDDEN, 5 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_refinery, 1), Requirement.HIDDEN, 8 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.machine_fluidtank, 4), Requirement.HIDDEN, 3 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.barrel_antimatter, 4), Requirement.HIDDEN, 3 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.STEEL), 64), Requirement.HIDDEN, 2 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.COPPER), 64), Requirement.HIDDEN, 2 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER), 64), Requirement.HIDDEN, 2 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TITANIUM), 64), Requirement.HIDDEN, 2 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN), 64), Requirement.HIDDEN, 2 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher_core, 1), Requirement.HIDDEN, 3 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher_core_large, 1), Requirement.HIDDEN, 3 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher, 40), Requirement.HIDDEN, 7 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.struct_scaffold, 11), Requirement.HIDDEN, 7 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.loot_10, 1), Requirement.HIDDEN, 2 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.loot_15, 1), Requirement.HIDDEN, 2 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.loot_misc, 1), Requirement.HIDDEN, 2 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.crate_can, 1), Requirement.HIDDEN, 1 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.crate_ammo, 1), Requirement.HIDDEN, 15 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.crucible, 1, 3), Requirement.STEEL, 10 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.spawn_chopper, 1), Requirement.STEEL, 100 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.spawn_worm, 1), Requirement.STEEL, 100 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.spawn_ufo, 1), Requirement.STEEL, 100 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.sat_laser, 1), Requirement.HIDDEN, 8 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.sat_gerald, 1), Requirement.HIDDEN, 320 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.billet.getItemStack(MaterialMineral.YHARONITE), 4), Requirement.HIDDEN, 160 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.ELECTRONIUM), 1), Requirement.HIDDEN, 160 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.book_of_, 1), Requirement.HIDDEN, 160 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.mysteryshovel, 1), Requirement.HIDDEN, 160 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModBlocks.ntm_dirt, 1), Requirement.HIDDEN, 160 * inflation));
		special.add(new Offer(ItemStackUtil.itemStackFrom(ModItems.euphemium_kit, 1), Requirement.HIDDEN, 640 * inflation));
	}
	
	public static List<Offer> getOffers(ItemStack stack) {
		
		if(stack != null) {
			if(stack.getItem() == ModItems.bobmazon_materials)
				return materials;
			if(stack.getItem() == ModItems.bobmazon_machines)
				return machines;
			if(stack.getItem() == ModItems.bobmazon_weapons)
				return weapons;
			if(stack.getItem() == ModItems.bobmazon_tools)
				return tools;
			if(stack.getItem() == ModItems.bobmazon_hidden)
				return special;
		}
		
		return null;
	}

}
