package com.hbm.blocks.generic;

import java.util.List;

import com.hbm.blocks.ModBlocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMetalFence extends BlockFence {

	public static final AxisAlignedBB PILLAR_SHORT_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
    public static final AxisAlignedBB SOUTH_SHORT_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.625D, 0.625D, 1.0D, 1.0D);
    public static final AxisAlignedBB WEST_SHORT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.375D, 1.0D, 0.625D);
    public static final AxisAlignedBB NORTH_SHORT_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.375D);
    public static final AxisAlignedBB EAST_SHORT_AABB = new AxisAlignedBB(0.625D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
	
	public BlockMetalFence(final Material materialIn, final MapColor mapColorIn, final String s) {
		super(materialIn, mapColorIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		return false;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, final World worldIn, final BlockPos pos, final AxisAlignedBB entityBox, final List<AxisAlignedBB> collidingBoxes, final Entity entityIn, final boolean isActualState) {
		if (!isActualState)
        {
            state = state.getActualState(worldIn, pos);
        }

        addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_SHORT_AABB);

        if (state.getValue(NORTH).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_SHORT_AABB);
        }

        if (state.getValue(EAST).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_SHORT_AABB);
        }

        if (state.getValue(SOUTH).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_SHORT_AABB);
        }

        if (state.getValue(WEST).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_SHORT_AABB);
        }
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

}
