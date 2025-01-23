package com.hbm.blocks.generic;

import java.util.ArrayList;
import java.util.Random;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.capability.HbmLivingProps;
import com.hbm.capability.HbmLivingProps.ContaminationEffect;
import com.hbm.interfaces.IItemHazard;
import com.hbm.util.ContaminationUtil;
import com.hbm.items.ModItems;
import com.hbm.modules.ItemHazardModule;
import com.hbm.potion.HbmPotion;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPowder extends Block implements IItemHazard {
	
	public static final PropertyInteger META = PropertyInteger.create("meta", 0, 6);
	
	ItemHazardModule module;

	public BlockPowder(final Material mat, final SoundType soundType, final String s) {
		super(mat);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setSoundType(soundType);
		this.setHarvestLevel("shovel", 0);
		this.module = new ItemHazardModule();
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		return new AxisAlignedBB(0, 0, 0, 1, 0.125, 1);
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isFullBlock(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean canEntitySpawn(final IBlockState state, final Entity entityIn){
		return ContaminationUtil.isRadImmune(entityIn);
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
		if(this == ModBlocks.fallout){
			return ModItems.fallout;
		}
		return null;
	}
	
	@Override
	public boolean canPlaceBlockAt(final World world, final BlockPos pos){
		final IBlockState state = world.getBlockState(pos.down());
		final Block block = state.getBlock();
		return block != Blocks.ICE && block != Blocks.PACKED_ICE && (block.isLeaves(state, world, pos.down()) || (block == this && (state.getValue(META) & 7) == 7 || state.isOpaqueCube() && state.getMaterial().blocksMovement()));
	}
	
	@Override
	public void onEntityWalk(final World world, final BlockPos pos, final Entity entity){
		if(!world.isRemote && entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(HbmPotion.radiation, 2 * 60 * 20, 14));
		}
	}

	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos){
		if(!this.canPlaceBlockAt(world, pos)) {
			world.setBlockToAir(pos);
		}
	}

	@Override
	public boolean isReplaceable(final IBlockAccess worldIn, final BlockPos pos){
		return true;
	}
	
	@Override
	public ItemHazardModule getModule() {
		return module;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, META);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(META);
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState().withProperty(META, meta);
	}
}