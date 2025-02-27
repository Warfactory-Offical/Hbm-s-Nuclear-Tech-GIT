package com.hbm.blocks.generic;

import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
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

public class BlockChain extends Block {

	public static final PropertyBool WALL = PropertyBool.create("wall");
	public static final PropertyBool END = PropertyBool.create("end");
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockChain(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setDefaultState(this.blockState.getBaseState().withProperty(WALL, false).withProperty(END, false).withProperty(FACING, EnumFacing.NORTH));
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public boolean isLadder(final IBlockState state, final IBlockAccess world, final BlockPos pos, final EntityLivingBase entity) {
		return true;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess worldIn, final BlockPos pos) {
		return NULL_AABB;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		final float f = 0.125F;
        float minY = 0;
        if(!source.isSideSolid(pos.down(), EnumFacing.UP, false))
        	minY = 0.25F;
        final int meta = state.getValue(FACING).ordinal();

    	if(!state.getValue(WALL)) {
    		return new AxisAlignedBB(3 * f, minY, 3 * f, 5 * f, 1, 5*f);
    	}
    	
        if (meta == 2)
        {
        	return new AxisAlignedBB(3 * f, minY, 1.0F - f, 5 * f, 1.0F, 1.0F);
        }

        if (meta == 3)
        {
        	return new AxisAlignedBB(3 * f, minY, 0.0F, 5 * f, 1.0F, f);
        }

        if (meta == 4)
        {
        	return new AxisAlignedBB(1.0F - f, minY, 3 * f, 1.0F, 1.0F, 5 * f);
        }

        if (meta == 5)
        {
            return new AxisAlignedBB(0.0F, minY, 3 * f, f, 1.0F, 5 * f);
        }
        return new AxisAlignedBB(3 * f, minY, 3 * f, 5 * f, 1, 5*f);
	}
	
	@Override
	public boolean canPlaceBlockAt(final World world, final BlockPos pos) {
		if(world.isSideSolid(pos.up(), EnumFacing.DOWN) || world.getBlockState(pos.up()).getBlock() == this)
    		return true;

    	return world.isSideSolid(pos.west(), EnumFacing.EAST ) ||
    			world.isSideSolid(pos.east(), EnumFacing.WEST ) ||
    			world.isSideSolid(pos.north(), EnumFacing.SOUTH) ||
    			world.isSideSolid(pos.south(), EnumFacing.NORTH);
	}
	
	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) {
		int j1 = meta;

        if(facing.ordinal() == 2 && world.isSideSolid(pos.south(), EnumFacing.NORTH))
            j1 = 2;

        if(facing.ordinal() == 3 && world.isSideSolid(pos.north(), EnumFacing.SOUTH))
            j1 = 3;

        if(facing.ordinal() == 4 && world.isSideSolid(pos.east(), EnumFacing.WEST))
            j1 = 4;

        if(facing.ordinal() == 5 && world.isSideSolid(pos.west(), EnumFacing.EAST))
            j1 = 5;

        boolean end = (world.getBlockState(pos.down()).getBlock() != this || (j1 != 0) != world.getBlockState(pos.down()).getValue(WALL)) && !world.isSideSolid(pos.down(), EnumFacing.UP);

        if(j1 == 0) {
        	if(world.getBlockState(pos.up()).getBlock() == this)
        		return this.getDefaultState().withProperty(FACING, world.getBlockState(pos.up()).getValue(FACING)).withProperty(WALL, world.getBlockState(pos.up()).getValue(WALL)).withProperty(END, end);

        	if(world.isSideSolid(pos.up(), EnumFacing.DOWN))
        		return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(WALL, false).withProperty(END, end);
        }

        if(j1 == 0) {
	        if(world.isSideSolid(pos.south(), EnumFacing.NORTH))
	            j1 = 2;

	        if(world.isSideSolid(pos.north(), EnumFacing.SOUTH))
	            j1 = 3;

	        if(world.isSideSolid(pos.east(), EnumFacing.WEST))
	            j1 = 4;

	        if(world.isSideSolid(pos.west(), EnumFacing.EAST))
	            j1 = 5;
        }
        return this.getDefaultState().withProperty(FACING, j1 > 0 ? EnumFacing.VALUES[j1] : EnumFacing.NORTH).withProperty(WALL, j1 != 0).withProperty(END, end);
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		final int l = state.getValue(FACING).ordinal();
        boolean flag = false;

        if(world.getBlockState(pos.down()).getBlock() == this && world.getBlockState(pos).getValue(WALL) == world.getBlockState(pos.down()).getValue(WALL) || world.isSideSolid(pos.down(), EnumFacing.UP)) {
        	world.setBlockState(pos, state.withProperty(END, false));
        } else {
        	world.setBlockState(pos, state.withProperty(END, true));
        }
        
        if(world.getBlockState(pos.up()).getBlock() == this && world.getBlockState(pos).getValue(WALL) == world.getBlockState(pos.up()).getValue(WALL)){
        	return;
        }

        if(world.isSideSolid(pos.up(), EnumFacing.DOWN) && !world.getBlockState(pos).getValue(WALL)) {
            return;
        }

        if (l == 2 && world.isSideSolid(pos.south(), EnumFacing.NORTH))
            flag = true;

        if (l == 3 && world.isSideSolid(pos.north(), EnumFacing.SOUTH))
            flag = true;

        if (l == 4 && world.isSideSolid(pos.east(), EnumFacing.WEST))
            flag = true;

        if (l == 5 && world.isSideSolid(pos.west(), EnumFacing.EAST))
            flag = true;

        if (!flag)
            world.destroyBlock(pos, true);

	}
	
	@Override
	public boolean isCollidable() {
		return true;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
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
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, WALL, END, FACING);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		final int wall = state.getValue(WALL) ? 1 : 0;
		final int end = state.getValue(END) ? 1 : 0;
		final int facing = state.getValue(FACING).ordinal() - 2;
		return (wall << 3) | (end << 2) | facing;
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState()
				.withProperty(WALL, ((meta >> 3) & 1) > 0)
				.withProperty(END, ((meta >> 2) & 1) > 0)
				.withProperty(FACING, EnumFacing.VALUES[(meta & 2) + 2]);
	}
	
	
}
