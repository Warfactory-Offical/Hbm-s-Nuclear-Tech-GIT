package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNTMDirt extends BlockDirt {

	public BlockNTMDirt(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public Block setSoundType(final SoundType sound) {
		return super.setSoundType(sound);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final CreativeTabs itemIn, final NonNullList<ItemStack> items) {
		items.add(ItemStackUtil.itemStackFrom(this, 1, 0));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getLocalizedName() {
		return (I18n.format(Blocks.DIRT.getTranslationKey()) + ".name").trim();
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(Blocks.DIRT);
	}
}
