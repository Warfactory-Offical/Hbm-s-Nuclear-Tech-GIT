package com.hbm.blocks.bomb;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCloudResidue extends Block {

	public BlockCloudResidue(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(null);

		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	public static boolean hasPosNeightbour(final World world, final BlockPos pos) {
		final Block b0 = world.getBlockState(pos.add(1, 0, 0)).getBlock();
		final Block b1 = world.getBlockState(pos.add(0, 1, 0)).getBlock();
		final Block b2 = world.getBlockState(pos.add(0, 0, 1)).getBlock();
		final Block b3 = world.getBlockState(pos.add(-1, 0, 0)).getBlock();
		final Block b4 = world.getBlockState(pos.add(0, -1, 0)).getBlock();
		final Block b5 = world.getBlockState(pos.add(0, 0, -1)).getBlock();
		final boolean b = b0.isNormalCube(world.getBlockState(pos.add(1, 0, 0)), world, pos)
				|| b1.isNormalCube(world.getBlockState(pos.add(0, 1, 0)), world, pos)
				|| b2.isNormalCube(world.getBlockState(pos.add(0, 0, 1)), world, pos)
				|| b3.isNormalCube(world.getBlockState(pos.add(-1, 0, 0)), world, pos)
				|| b4.isNormalCube(world.getBlockState(pos.add(0, -1, 0)), world, pos)
				|| b5.isNormalCube(world.getBlockState(pos.add(0, 0, -1)), world, pos);
		return b;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(final IBlockState state, final World worldIn, final BlockPos pos) {
		return new AxisAlignedBB(pos, pos);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess worldIn, final BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isCollidable(){
		return true;
	}

	@Override
	public boolean isNormalCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean canPlaceBlockAt(final World worldIn, final BlockPos pos) {
		return hasPosNeightbour(worldIn, pos);
	}

	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if (!BlockCloudResidue.hasPosNeightbour(world, pos) && !world.isRemote) {
			world.setBlockToAir(pos);
		}
	}

	@Override
	public MapColor getMapColor(final IBlockState state, final IBlockAccess worldIn, final BlockPos pos) {
		return MapColor.RED;
	}
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		if(rand.nextInt(25) == 1){
			return ModItems.powder_cloud;
		}
		return Items.AIR;
	}
}
