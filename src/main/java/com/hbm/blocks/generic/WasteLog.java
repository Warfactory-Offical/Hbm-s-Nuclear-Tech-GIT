package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;
import com.hbm.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WasteLog extends BlockRotatedPillar implements IItemHazard {

	ItemHazardModule module;
	
	public WasteLog(final Material mat, final SoundType type, final String s) {
		super(mat);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHarvestLevel("axe", 0);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setSoundType(type);
		this.module = new ItemHazardModule();
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public ItemHazardModule getModule() {
		return module;
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		if(this == ModBlocks.waste_log) {
			return Items.COAL;
		}
		if(this == ModBlocks.frozen_log) {
			return Items.SNOWBALL;
		}
		return null;
	}
	
	@Override
	public void getDrops(final NonNullList<ItemStack> drops, final IBlockAccess world, final BlockPos pos, final IBlockState state, final int fortune) {
		final Random rand = world instanceof World ? ((World)world).rand : RANDOM;
		if(this == ModBlocks.waste_log){
			if(rand.nextInt(1000) == 0) {
		        drops.add(ItemStackUtil.itemStackFrom(ModItems.burnt_bark));
		        return;
		    } else if(rand.nextInt(4) == 0) {
		    	drops.add(ItemStackUtil.itemStackFrom(Item.getItemFromBlock(ModBlocks.waste_log)));
		        return;
		    } else {
		    	drops.add(ItemStackUtil.itemStackFrom(Items.COAL, 1, 1));
		        return;
		    }
    	}
		super.getDrops(drops, world, pos, state, fortune);
	}
	
	@Override
	public int quantityDropped(final IBlockState state, final int fortune, final Random random) {
		return 2 + random.nextInt(3);
	}

	public IBlockState withSameRotationState(final IBlockState state){
		if(state == null)
			return this.getDefaultState();
		return this.getDefaultState().withProperty(AXIS, state.getValue(AXIS));
	}

	public IBlockState getSameRotationState(final IBlockState state){
		if(state == null)
			return this.getDefaultState();
		final BlockLog.EnumAxis logAxis = state.getValue(BlockLog.LOG_AXIS);
		if(logAxis == BlockLog.EnumAxis.Y) return this.getDefaultState().withProperty(AXIS, Axis.Y);
		if(logAxis == BlockLog.EnumAxis.Z) return this.getDefaultState().withProperty(AXIS, Axis.Z);
		if(logAxis == BlockLog.EnumAxis.X) return this.getDefaultState();
		if(logAxis == BlockLog.EnumAxis.NONE) return this.getDefaultState().withProperty(AXIS, Axis.Y);
		return null;
	}
}
