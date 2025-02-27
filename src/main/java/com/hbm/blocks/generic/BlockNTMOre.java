package com.hbm.blocks.generic;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.main.MainRegistry;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;

public class BlockNTMOre extends BlockOre implements IItemHazard {
	
	ItemHazardModule module;
	public static int xp;

	public BlockNTMOre(final String name, final int harvestLvl, final int xp) {
		super();
		BlockNTMOre.xp = xp;
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setTickRandomly(false);
		this.setHarvestLevel("pickaxe", harvestLvl);
		this.module = new ItemHazardModule();
		ModBlocks.ALL_BLOCKS.add(this);
	}

	public BlockNTMOre(final String name, final int harvestLvl) {
		this(name, harvestLvl, 2);
	}
	
	public BlockNTMOre(final SoundType sound, final String name, final int harvestLvl){
		this(name, harvestLvl);
		super.setSoundType(sound);
	}

	@Override
	public ItemHazardModule getModule() {
		return module;
	}

	@Override
	public int getExpDrop(final IBlockState state, final IBlockAccess world, final BlockPos pos, final int fortune){
		if(this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
			return xp;
		return 0;
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		if(this == ModBlocks.ore_asbestos || this == ModBlocks.ore_gneiss_asbestos || this == ModBlocks.basalt_asbestos){
			return ModItems.ingot;
		}
		if(this == ModBlocks.ore_nether_fire){
			// TODO
			// return rand.nextInt(10) == 0 ? ModItems.ingot_phosphorus : ModItems.powder_fire;
			return ModItems.powder_fire;
		}
		if(this == ModBlocks.ore_sulfur || this == ModBlocks.ore_nether_sulfur || this == ModBlocks.ore_meteor_sulfur || this == ModBlocks.basalt_sulfur){
			return ModItems.sulfur;
		}
		if(this == ModBlocks.ore_niter){
			return ModItems.niter;
		}
		if(this == ModBlocks.ore_fluorite){
			return ModItems.fluorite;
		}
		if(this == ModBlocks.ore_lignite){
			return ModItems.lignite;
		}
		if(this == ModBlocks.ore_rare || this == ModBlocks.ore_gneiss_rare){	
			return ModItems.rare_earth_chunk;
		}
		if(this == ModBlocks.block_meteor)
		{
			return rand.nextInt(10) == 0 ? ModItems.plate_dalekanium : Item.getItemFromBlock(ModBlocks.block_meteor);
		}
		if(this == ModBlocks.block_meteor_cobble)
		{
			return ModItems.fragment_meteorite;
		}
		if(this == ModBlocks.block_meteor_broken)
		{
			return ModItems.fragment_meteorite;
		}
		if(this == ModBlocks.block_meteor_treasure)
		{
			switch(rand.nextInt(35)) {
			case 0: return ModItems.coil_advanced_alloy;
			case 1: return ModItems.plate_advanced_alloy;
			case 2: return ModItems.powder_desh_mix;
//			case 3: return ModItems.ingot_desh;
			case 4: return ModItems.battery_advanced;
			case 5: return ModItems.battery_lithium_cell;
			case 6: return ModItems.battery_advanced_cell;
//			case 7: return ModItems.nugget_schrabidium;
//			case 8: return ModItems.ingot_plutonium;
//			case 9: return ModItems.ingot_thorium_fuel;
//			case 10: return ModItems.ingot_u233;
			case 11: return ModItems.turbine_tungsten;
//			case 12: return ModItems.ingot_dura_steel;
//			case 13: return ModItems.ingot_polymer;
//			case 14: return ModItems.ingot_tungsten;
//			case 15: return ModItems.ingot_combine_steel;
//			case 16: return ModItems.ingot_lanthanium;
//			case 17: return ModItems.ingot_actinium;
			case 18: return Item.getItemFromBlock(ModBlocks.block_meteor);
			case 19: return Item.getItemFromBlock(ModBlocks.fusion_heater);
			case 20: return Item.getItemFromBlock(ModBlocks.fusion_core);
			case 21: return Item.getItemFromBlock(ModBlocks.watz_element);
			case 22: return Item.getItemFromBlock(ModBlocks.ore_rare);
			case 23: return Item.getItemFromBlock(ModBlocks.fusion_conductor);
			case 24: return Item.getItemFromBlock(ModBlocks.reactor_computer);
			case 25: return Item.getItemFromBlock(ModBlocks.machine_diesel);
			case 26: return Item.getItemFromBlock(ModBlocks.machine_rtg_grey);
			case 27: return ModItems.pellet_rtg;
			case 28: return ModItems.pellet_rtg_weak;
			case 29: return ModItems.rtg_unit;
			case 30: return ModItems.gun_spark_ammo;
			case 31: return ModItems.ammo_nuke;
			case 32: return ModItems.ammo_mirv;
			case 33: return ModItems.gun_defabricator_ammo;
			case 34: return ModItems.gun_osipr_ammo2;
			default: return ModItems.ammo_nuke; // TODO remove
			}
		}
		if(this == ModBlocks.deco_aluminium)
		{
			return ModItems.ingot;
		}
		if(this == ModBlocks.deco_beryllium)
		{
			return ModItems.ingot;
		}
		if(this == ModBlocks.deco_lead)
		{
			return ModItems.ingot;
		}
		if(this == ModBlocks.deco_red_copper)
		{
			return ModItems.ingot;
		}
		if(this == ModBlocks.deco_steel)
		{
			return ModItems.ingot;
		}
		if(this == ModBlocks.deco_titanium)
		{
			return ModItems.ingot;
		}
		if(this == ModBlocks.deco_tungsten)
		{
			return ModItems.ingot;
		}
		if(this == ModBlocks.deco_asbestos)
		{
			return ModItems.ingot;
		}
		if(this == ModBlocks.ore_cinnebar) {
			return ModItems.cinnebar;
		}
		if(this == ModBlocks.ore_coltan) {
			return ModItems.fragment_coltan;
		}
		if(this == ModBlocks.ore_cobalt || this == ModBlocks.ore_nether_cobalt) {
			return ModItems.fragment_cobalt;
		}
		return super.getItemDropped(state, rand, fortune);
	}
	
	@Override
	public int quantityDropped(final IBlockState state, final int fortune, final Random rand) {
		if(this == ModBlocks.ore_sulfur || this == ModBlocks.ore_nether_sulfur || this == ModBlocks.ore_meteor_sulfur || this == ModBlocks.basalt_sulfur){
			return 2 + rand.nextInt(3) * fortune;
		}
		if(this == ModBlocks.ore_niter){
			return 1 + rand.nextInt(2) * fortune;
		}
		if(this == ModBlocks.ore_fluorite){
			return 2 + rand.nextInt(3) * fortune;
		}
		if(this == ModBlocks.block_meteor_broken)
		{
			return 1 + rand.nextInt(3);
		}
		if(this == ModBlocks.block_meteor_treasure)
		{
			return 1 + rand.nextInt(3);
		}
		if(this == ModBlocks.ore_cobalt) {
			return 4 + rand.nextInt(6);
		}
		if(this == ModBlocks.ore_nether_cobalt) {
			return 5 + rand.nextInt(8);
		}
		return super.quantityDropped(state, fortune, rand);
	}
	
	@Override
	public int damageDropped(final IBlockState state) {
		if(this == ModBlocks.ore_asbestos || this == ModBlocks.ore_gneiss_asbestos || this == ModBlocks.basalt_asbestos){
			return ModItems.ingot.getItemStack(MaterialMineral.ASBESTOS).getMetadata();
		}
		if(this == ModBlocks.deco_aluminium)
		{
			return ModItems.ingot.getItemStack(MaterialMineral.ALUMINIUM).getMetadata();
		}
		if(this == ModBlocks.deco_beryllium)
		{
			return ModItems.ingot.getItemStack(MaterialMineral.BERYLLIUM).getMetadata();
		}
		if(this == ModBlocks.deco_lead)
		{
			return ModItems.ingot.getItemStack(MaterialMineral.LEAD).getMetadata();
		}
		if(this == ModBlocks.deco_red_copper)
		{
			return ModItems.ingot.getItemStack(MaterialMineral.RED_COPPER).getMetadata();
		}
		if(this == ModBlocks.deco_steel)
		{
			return ModItems.ingot.getItemStack(MaterialMineral.STEEL).getMetadata();
		}
		if(this == ModBlocks.deco_titanium)
		{
			return ModItems.ingot.getItemStack(MaterialMineral.TITANIUM).getMetadata();
		}
		if(this == ModBlocks.deco_tungsten)
		{
			return ModItems.ingot.getItemStack(MaterialMineral.TUNGSTEN).getMetadata();
		}
		if(this == ModBlocks.deco_asbestos)
		{
			return ModItems.ingot.getItemStack(MaterialMineral.ASBESTOS).getMetadata();
		}
		return 0;
	}
	
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if (world.getBlockState(pos.down()).getBlock() == ModBlocks.ore_oil_empty) {
        	world.setBlockState(pos, ModBlocks.ore_oil_empty.getDefaultState());
        	world.setBlockState(pos.down(), ModBlocks.ore_oil.getDefaultState());
        }
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_uranium) || stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_gneiss_uranium) || stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_nether_uranium)){
			tooltip.add("High-Radiation creates medium amounts of schrabidium inside this block");
		}
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_schrabidium) || stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_gneiss_schrabidium) || stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_nether_schrabidium)){
			tooltip.add("High-Radiation has created medium amounts of schrabidium inside this block");
		}
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.ore_oil)){
			tooltip.add("You weren't supposed to mine that.");
			tooltip.add("Come on, get a derrick you doofus.");
		}
	}

	@Override
	public void onEntityWalk(final World worldIn, final BlockPos pos, final Entity entity) {
		if(entity instanceof EntityLivingBase)
			this.module.applyEffects((EntityLivingBase)entity, 0.5F, 0, false, EnumHand.MAIN_HAND);
	}

	@Override
	public void onEntityCollision(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entity){
		if(entity instanceof EntityLivingBase)
			this.module.applyEffects((EntityLivingBase)entity, 0.5F, 0, false, EnumHand.MAIN_HAND);
	}

	@Override
	public Block setSoundType(final SoundType sound) {
		return super.setSoundType(sound);
	}
}
