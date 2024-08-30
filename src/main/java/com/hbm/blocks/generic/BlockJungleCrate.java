package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockJungleCrate extends Block {

	public BlockJungleCrate(Material materialIn, String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	Random rand = new Random();
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(ItemStackUtil.itemStackFrom(Items.GOLD_INGOT, 4 + rand.nextInt(4)));
		drops.add(ItemStackUtil.itemStackFrom(Items.GOLD_NUGGET, 8 + rand.nextInt(10)));
		drops.add(ItemStackUtil.itemStackFrom(ModItems.powder_gold, 2 + rand.nextInt(3)));
		drops.add(ItemStackUtil.itemStackFrom(ModItems.wire_gold, 2 + rand.nextInt(2)));

        if(rand.nextInt(2) == 0)
        	drops.add(ItemStackUtil.itemStackFrom(ModItems.plate_gold, 1 + rand.nextInt(2)));

        if(rand.nextInt(3) == 0)
        	drops.add(ItemStackUtil.itemStackFrom(ModItems.crystal_gold));
	}
	
	@Override
	public Block setSoundType(SoundType sound) {
		return super.setSoundType(sound);
	}
	

}
