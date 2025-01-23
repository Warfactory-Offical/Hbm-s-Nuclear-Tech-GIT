package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMushHuge extends Block implements IItemHazard {

	ItemHazardModule module;

	public BlockMushHuge(final Material mat, final String s) {
		super(mat);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setSoundType(SoundType.PLANT);
		this.module = new ItemHazardModule();
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public ItemHazardModule getModule() {
		return module;
	}
	
	@Override
	public int quantityDropped(final IBlockState state, final int fortune, final Random rand) {
		int i = rand.nextInt(10) - 7;
		if(i < 0) {
			i = 0;
		}
		return i;
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.mush);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(final World worldIn, final BlockPos pos, final IBlockState state) {
		return ItemStackUtil.itemStackFrom(Item.getItemFromBlock(ModBlocks.mush), 1, 0);
	}
	
}
