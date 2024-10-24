package com.hbm.items.special;
import com.hbm.interfaces.Spaghetti;
import com.hbm.util.ItemStackUtil;

import java.util.List;
import java.util.Random;

import com.hbm.config.BombConfig;
import com.hbm.config.GeneralConfig;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.handler.ArmorUtil;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Spaghetti("i'm going to refactor this class soon")
public class ItemCustomLore extends Item {

	EnumRarity rarity;
	
	public ItemCustomLore(String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		ModItems.ALL_ITEMS.add(this);
	}

	static int setSize = 0;

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if(this == ModItems.plate_euphemium ||
//			this == ModItems.ingot_euphemium ||
//			this == ModItems.ingot_osmiridium ||
//			this == ModItems.ingot_astatine ||
//			this == ModItems.ingot_iodine ||
//			this == ModItems.ingot_i131 ||
//			this == ModItems.ingot_strontium ||
//			this == ModItems.ingot_sr90 ||
//			this == ModItems.ingot_cobalt ||
//			this == ModItems.ingot_co60 ||
//			this == ModItems.ingot_bromine ||
//			this == ModItems.ingot_tennessine ||
//			this == ModItems.ingot_cerium ||
//			this == ModItems.ingot_caesium ||
//			this == ModItems.ingot_niobium ||
//			this == ModItems.ingot_neodymium ||
//			this == ModItems.ingot_gh336 ||
			this == ModItems.euphemium_capacitor ||

//			this == ModItems.nugget_euphemium ||
//			this == ModItems.nugget_osmiridium ||
//			this == ModItems.nugget_strontium ||
//			this == ModItems.nugget_sr90 ||
//			this == ModItems.nugget_cobalt ||
//			this == ModItems.nugget_co60 ||
//			this == ModItems.nugget_gh336 ||

//			this == ModItems.billet_gh336 ||
//			this == ModItems.billet_co60 ||
//			this == ModItems.billet_sr90 ||

			this == ModItems.powder_neptunium ||
			this == ModItems.powder_euphemium ||
			this == ModItems.powder_osmiridium ||
			this == ModItems.powder_iodine ||
			this == ModItems.powder_i131 ||
			this == ModItems.powder_strontium ||
			this == ModItems.powder_sr90 ||
			this == ModItems.powder_astatine ||
			this == ModItems.powder_at209 ||
			this == ModItems.powder_cobalt ||
			this == ModItems.powder_co60 ||
			this == ModItems.powder_bromine ||
			this == ModItems.powder_niobium ||
			this == ModItems.powder_cerium ||
			this == ModItems.powder_neodymium ||
			this == ModItems.powder_tennessine ||
			this == ModItems.powder_xe135 ||
			this == ModItems.powder_caesium ||
			this == ModItems.powder_cs137 ||
			this == ModItems.powder_nitan_mix ||
			this == ModItems.powder_spark_mix ||
			this == ModItems.powder_magic ||


			this == ModItems.powder_sr90_tiny ||
			this == ModItems.powder_iodine_tiny ||
			this == ModItems.powder_i131_tiny ||
			this == ModItems.powder_co60_tiny ||
			this == ModItems.powder_cobalt_tiny ||
			this == ModItems.powder_niobium_tiny ||
			this == ModItems.powder_cerium_tiny ||
			this == ModItems.powder_neodymium_tiny ||
			this == ModItems.powder_xe135_tiny ||
			this == ModItems.powder_cs137_tiny ||
//			this == ModItems.nugget_daffergon ||
			this == ModItems.powder_daffergon ||
//			this == ModItems.ingot_daffergon ||

			this == ModItems.bathwater_mk3 ||
			this == ModItems.rod_euphemium ||
			this == ModItems.rod_quad_euphemium ||
			this == ModItems.rod_daffergon ||
			this == ModItems.watch ||
			this == ModItems.undefined) {
			return EnumRarity.EPIC;
		}

		if(
//		if(this == ModItems.ingot_schrabidium ||
//			this == ModItems.ingot_schraranium ||
//			this == ModItems.ingot_schrabidate ||
//			this == ModItems.ingot_saturnite ||
//			this == ModItems.ingot_solinium ||
//			this == ModItems.nugget_schrabidium ||
//			this == ModItems.nugget_solinium ||
//			this == ModItems.ingot_electronium ||
//			this == ModItems.billet_solinium ||
//			this == ModItems.billet_schrabidium ||

			this == ModItems.powder_schrabidate ||
			this == ModItems.powder_schrabidium ||

			this == ModItems.wire_schrabidium ||

			this == ModItems.plate_schrabidium ||
			this == ModItems.plate_saturnite ||

			this == ModItems.circuit_schrabidium ||
			this == ModItems.gun_revolver_schrabidium_ammo ||
			this == ModItems.powder_unobtainium ||
//			this == ModItems.nugget_unobtainium ||
//			this == ModItems.ingot_unobtainium ||
//			this == ModItems.nugget_unobtainium_greater ||
//			this == ModItems.nugget_unobtainium_lesser ||
//			this == ModItems.billet_unobtainium ||

			this == ModItems.solinium_core ||
			this == ModItems.powder_impure_osmiridium ||
			this == ModItems.crystal_osmiridium ||
			this == ModItems.crystal_schrabidium ||
    		this == ModItems.crystal_schraranium ||
    		this == ModItems.crystal_trixite ||
    		ItemCell.hasFluid(stack, ModForgeFluids.sas3) ||
    		this == ModItems.rod_unobtainium ||
    		this == ModItems.rod_schrabidium ||
			this == ModItems.rod_dual_schrabidium ||
			this == ModItems.rod_quad_schrabidium ||
			this == ModItems.rod_dual_solinium ||
			this == ModItems.rod_quad_solinium) {
			return EnumRarity.RARE;
		}

		if(this == ModItems.bathwater_mk2 ||
			this == ModItems.plate_paa ||
			this == ModItems.cladding_paa ||
			this == ModItems.ammo_566_gold ||
			this == ModItems.gun_revolver_cursed_ammo ||
			this == ModItems.powder_power ||
			this == ModItems.powder_yellowcake ||
//			this == ModItems.billet_australium ||
//			this == ModItems.billet_australium_greater ||
//			this == ModItems.billet_australium_lesser ||

//			this == ModItems.ingot_australium ||
//			this == ModItems.ingot_weidanium ||
//			this == ModItems.ingot_reiium ||
//			this == ModItems.ingot_verticium ||
			this == ModItems.powder_paleogenite ||
			this == ModItems.powder_paleogenite_tiny ||

//			this == ModItems.nugget_australium ||
//			this == ModItems.nugget_australium_greater ||
//			this == ModItems.nugget_australium_lesser ||
//			this == ModItems.nugget_weidanium ||
//			this == ModItems.nugget_reiium ||
//			this == ModItems.nugget_verticium ||

			this == ModItems.powder_australium ||
			this == ModItems.powder_weidanium ||
			this == ModItems.powder_reiium ||
			this == ModItems.powder_verticium) {
			return EnumRarity.UNCOMMON;
		}

		return this.rarity != null ? rarity : EnumRarity.COMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		if(this == ModItems.rune_isa ||
    			this == ModItems.rune_dagaz ||
    			this == ModItems.rune_hagalaz ||
    			this == ModItems.rune_jera ||
    			this == ModItems.rune_thurisaz ||
    			this == ModItems.egg_balefire_shard ||
    			this == ModItems.egg_balefire ||
    			this == ModItems.coin_maskman || 
    			this == ModItems.coin_radiation || 
    			this == ModItems.coin_worm || 
    			this == ModItems.coin_ufo || 
    			this == ModItems.coin_creeper) 
		{
    		return true;
    	}
		return super.hasEffect(stack);
	}
	
	public ItemCustomLore setRarity(EnumRarity rarity) {
    	this.rarity = rarity;
		return this;
    }

}
