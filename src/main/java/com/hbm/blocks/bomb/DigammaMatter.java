package com.hbm.blocks.bomb;

import java.util.Random;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DigammaMatter extends Block {
	
	public static final PropertyInteger META = BlockDummyable.META;
	
	private static final Random rand = new Random();

	public DigammaMatter(final String s) {
		super(Material.PORTAL);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess worldIn, final BlockPos pos){
		return NULL_AABB;
	}

	@Override
	public boolean isBlockNormalCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state){
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		final float pixel = 0.0625F;
		return new AxisAlignedBB(rand.nextInt(9) * pixel, rand.nextInt(9) * pixel, rand.nextInt(9) * pixel, 1.0F - rand.nextInt(9) * pixel, 1.0F - rand.nextInt(9) * pixel, 1.0F - rand.nextInt(9) * pixel);
	}
	
	@Override
	public void onBlockAdded(final World world, final BlockPos pos, final IBlockState state){
		if(!world.isRemote)
			world.scheduleUpdate(pos, this, 10 + rand.nextInt(40));
	}
	
	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand){
		if(!world.isRemote) {
			
			final int meta = state.getValue(META);
			
			if(meta >= 7) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			} else {

				world.setBlockState(pos, Blocks.AIR.getDefaultState());
				//world.setBlock(x, y, z, this, meta + 1, 3);
				
				for(int i = -1; i <= 1; i++) {
					for(int j = -1; j <= 1; j++) {
						for(int k = -1; k <= 1; k++) {
							
							final int dist = Math.abs(i) + Math.abs(j) + Math.abs(k);
							
							if(dist > 0 && dist < 3) {
								if(world.getBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k)).getBlock() != this)
									world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k), state.withProperty(META, meta+1), 3);
							}
						}
					}
				}
			}
		}
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