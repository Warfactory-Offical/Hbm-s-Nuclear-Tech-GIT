package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.deco.TileEntityDecoBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DecoBlock extends BlockContainer {

	public static PropertyDirection FACING = BlockHorizontal.FACING;
	
	public static final float f = 0.0625F;
	public static final AxisAlignedBB WALL_WEST_BOX = new AxisAlignedBB(14*f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	public static final AxisAlignedBB WALL_EAST_BOX = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 2*f, 1.0F, 1.0F);
	public static final AxisAlignedBB WALL_NORTH_BOX = new AxisAlignedBB(0.0F, 0.0F, 14*f, 1.0F, 1.0F, 1.0F);
	public static final AxisAlignedBB WALL_SOUTH_BOX = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2*f);
	public static final AxisAlignedBB STEEL_ROOF_BOX = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1*f, 1.0F);
	public static final AxisAlignedBB STEEL_BEAM_BOX = new AxisAlignedBB(7*f, 0.0F, 7*f, 9*f, 1.0F, 9*f);
	public static final AxisAlignedBB SCAFFOLD_EASTWEST_BOX = new AxisAlignedBB(2*f, 0.0F, 0.0F, 14*f, 1.0F, 1.0F);
	public static final AxisAlignedBB SCAFFOLD_NORTHSOUTH_BOX = new AxisAlignedBB(0.0F, 0.0F, 2*f, 1.0F, 1.0F, 14*f);
	
	Random rand = new Random();
	
	public DecoBlock(final Material materialIn, final String s) {
		super(materialIn);
		this.setRegistryName(s);
		this.setTranslationKey(s);
		this.setCreativeTab(MainRegistry.blockTab);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public Block setSoundType(final SoundType sound) {
		return super.setSoundType(sound);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(this == ModBlocks.steel_scaffold || this == ModBlocks.steel_beam)
			return null;
		return new TileEntityDecoBlock();
	}
	
	@Override
	public boolean hasTileEntity(final IBlockState state) {
        return this != ModBlocks.steel_scaffold && this != ModBlocks.steel_beam;
    }
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		if(this == ModBlocks.steel_beam || this == ModBlocks.steel_scaffold)
			return EnumBlockRenderType.MODEL;
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
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
	public boolean shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side) {
		return false;
	}
	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()));
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return super.getItemDropped(state, rand, fortune);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		final EnumFacing te = state.getValue(FACING);
		if(this == ModBlocks.steel_wall)
        {
			switch(te)
			{
			case WEST:
            	return WALL_WEST_BOX;
			case NORTH:
            	return WALL_NORTH_BOX;
			case EAST:
            	return WALL_EAST_BOX;
			case SOUTH:
            	return WALL_SOUTH_BOX;
            default:
            	return FULL_BLOCK_AABB;
			}
        } else if(this == ModBlocks.steel_roof){
        	return STEEL_ROOF_BOX;
        } else if(this == ModBlocks.steel_beam){
        	return STEEL_BEAM_BOX;
        } else if(this == ModBlocks.steel_scaffold)
        {
			switch(te)
			{
			case WEST:
				return SCAFFOLD_EASTWEST_BOX;
			case NORTH:
	            return SCAFFOLD_NORTHSOUTH_BOX;
			case EAST:
				return SCAFFOLD_EASTWEST_BOX;
			case SOUTH:
				return SCAFFOLD_NORTHSOUTH_BOX;
            default:
            	return FULL_BLOCK_AABB;
			}
        }
		return FULL_BLOCK_AABB;
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
