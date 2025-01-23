package com.hbm.blocks.generic;

import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.Library;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRailing extends Block {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public int type;
	
	public BlockRailing(final Material materialIn, final int type, final String s){
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.type = type;
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public boolean isFullCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state){
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		switch(type){
		case 0:
		case 1:
			return Library.rotateAABB(new AxisAlignedBB(0, 0, 0, 1, 1, 0.125), state.getValue(FACING));
		case 2:
		default:
			return super.getBoundingBox(state, source, pos);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void addCollisionBoxToList(final IBlockState state, final World worldIn, final BlockPos pos, final AxisAlignedBB entityBox, final List<AxisAlignedBB> collidingBoxes, final Entity entityIn, final boolean isActualState){
		switch(type){
		case 0:
		case 1:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(worldIn, pos));
			return;
		case 2:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, Library.rotateAABB(new AxisAlignedBB(0, 0, 0, 1, 1, 0.125), state.getValue(FACING)));
			addCollisionBoxToList(pos, entityBox, collidingBoxes, Library.rotateAABB(new AxisAlignedBB(0, 0, 0, 1, 1, 0.125), state.getValue(FACING).rotateY()));
			return;
		default:
			super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, isActualState);
		}
	}
	
	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(FACING).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	
	
	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn)
	{
	   return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}
	
	
}
