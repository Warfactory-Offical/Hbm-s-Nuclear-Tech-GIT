package com.hbm.blocks.network.energy;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.lib.Library;
import com.hbm.tileentity.network.energy.TileEntityCableBaseNT;

import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer {

	public BlockCable(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.blockTab);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityCableBaseNT();
	}

	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		if (world.getTileEntity(pos) instanceof TileEntityCableBaseNT cable) {

            final boolean posX = Library.canConnect(world, cable.getPos().add(1, 0, 0), Library.POS_X);
			final boolean negX = Library.canConnect(world, cable.getPos().add(-1, 0, 0), Library.NEG_X);
			final boolean posY = Library.canConnect(world, cable.getPos().add(0, 1, 0), Library.POS_Y);
			final boolean negY = Library.canConnect(world, cable.getPos().add(0, -1, 0), Library.NEG_Y);
			final boolean posZ = Library.canConnect(world, cable.getPos().add(0, 0, 1), Library.POS_Z);
			final boolean negZ = Library.canConnect(world, cable.getPos().add(0, 0, -1), Library.NEG_Z);
			

			if (cable != null) {
				final float p = 1F / 16F;
				final float minX = 11 * p / 2 - (negX ? (11 * p / 2) : 0);
				final float minY = 11 * p / 2 - (negY ? (11 * p / 2) : 0);
				final float minZ = 11 * p / 2 - (negZ ? (11 * p / 2) : 0);
				final float maxX = 1 - 11 * p / 2 + (posX ? (11 * p / 2) : 0);
				final float maxY = 1 - 11 * p / 2 + (posY ? (11 * p / 2) : 0);
				final float maxZ = 1 - 11 * p / 2 + (posZ ? (11 * p / 2) : 0);

				return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
			}
		}
		return FULL_BLOCK_AABB;
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isFullCube(final IBlockState state) {
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
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(final IBlockAccess worldIn, final IBlockState state, final BlockPos pos, final EnumFacing face){
		return BlockFaceShape.CENTER;
	}
}
