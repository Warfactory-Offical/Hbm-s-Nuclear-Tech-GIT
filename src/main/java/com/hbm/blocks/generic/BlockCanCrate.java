package com.hbm.blocks.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCanCrate extends Block {

	public BlockCanCrate(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public Block setSoundType(final SoundType sound) {
		return super.setSoundType(sound);
	}
	
	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(worldIn.isRemote)
		{
			playerIn.sendMessage(new TextComponentTranslation("chat.crate.cansmash"));
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		final List<Item> items = new ArrayList<Item>();
    	items.add(ModItems.canned_beef);
    	items.add(ModItems.canned_tuna);
    	items.add(ModItems.canned_mystery);
    	items.add(ModItems.canned_pashtet);
    	items.add(ModItems.canned_cheese);
    	items.add(ModItems.canned_jizz);
    	items.add(ModItems.canned_milk);
    	items.add(ModItems.canned_ass);
    	items.add(ModItems.canned_pizza);
    	items.add(ModItems.canned_tomato);
    	items.add(ModItems.canned_tube);
    	items.add(ModItems.canned_asbestos);
    	items.add(ModItems.canned_bhole);
    	items.add(ModItems.canned_hotdogs);
    	items.add(ModItems.canned_leftovers);
    	items.add(ModItems.canned_yogurt);
    	items.add(ModItems.canned_stew);
    	items.add(ModItems.canned_chinese);
    	items.add(ModItems.canned_oil);
    	items.add(ModItems.canned_fist);
    	items.add(ModItems.canned_spam);
    	items.add(ModItems.canned_fried);
    	items.add(ModItems.canned_napalm);
    	items.add(ModItems.canned_diesel);
    	items.add(ModItems.canned_kerosene);
    	items.add(ModItems.canned_recursion);
    	items.add(ModItems.canned_bark);
    	items.add(ModItems.can_smart);
    	items.add(ModItems.can_creature);
    	items.add(ModItems.can_redbomb);
    	items.add(ModItems.can_mrsugar);
    	items.add(ModItems.can_overcharge);
    	items.add(ModItems.can_luna);
    	items.add(ModItems.can_breen);
    	items.add(ModItems.can_bepis);
    	items.add(ModItems.pudding);
    	
        return items.get(rand.nextInt(items.size()));
	}
	
	@Override
	public int quantityDropped(final IBlockState state, final int fortune, final Random random) {
		return 5 + random.nextInt(4);
	}
	
	@Override
	public boolean canRenderInLayer(final IBlockState state, final BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}
	
}
