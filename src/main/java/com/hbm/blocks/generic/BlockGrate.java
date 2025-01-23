package com.hbm.blocks.generic;

import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGrate extends Block {

	public static final PropertyInteger HEIGHT = PropertyInteger.create("height", 0, 7);
	
	public BlockGrate(final Material material, final String s) {
		super(material);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public Block setSoundType(final SoundType sound){
		return super.setSoundType(sound);
	}

	@Override
	public BlockRenderLayer getRenderLayer(){
		return BlockRenderLayer.CUTOUT_MIPPED;
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
	public boolean isBlockNormalCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state, final IBlockAccess world, final BlockPos pos){
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		final int height = state.getValue(HEIGHT);
		return new AxisAlignedBB(0, height*0.125, 0, 1, height*0.125 + 0.125, 1);
	}

	@Override
	public boolean isSideSolid(final IBlockState state, final IBlockAccess world, final BlockPos pos, final EnumFacing side){
		final int height = state.getValue(HEIGHT);
		return (height == 0 && side == EnumFacing.DOWN) || (height == 7 && side == EnumFacing.UP);
	}
	
	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand){
		if(facing == EnumFacing.UP){
			return this.getDefaultState().withProperty(HEIGHT, 0);
		} else if(facing == EnumFacing.DOWN){
			return this.getDefaultState().withProperty(HEIGHT, 7);
		} else {
			return this.getDefaultState().withProperty(HEIGHT, (int)Math.floor(hitY*8));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, HEIGHT);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state){
		return state.getValue(HEIGHT);
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta){
		return this.getDefaultState().withProperty(HEIGHT, meta & 7);
	}
}