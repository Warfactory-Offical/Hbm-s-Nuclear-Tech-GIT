package com.hbm.blocks.gas;

import java.util.Random;

import com.hbm.interfaces.Untested;
import com.hbm.lib.ForgeDirection;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGasFlammable extends BlockGasBase {

	public BlockGasFlammable(final String s) {
		super(0.8F, 0.8F, 0.2F, s);
	}

	@Override
	public ForgeDirection getFirstDirection(final World world, final int x, final int y, final int z) {
		
		if(world.rand.nextInt(3) == 0)
			return ForgeDirection.getOrientation(world.rand.nextInt(2));
		
		return this.randomHorizontal(world);
	}

	@Override
	public ForgeDirection getSecondDirection(final World world, final int x, final int y, final int z) {
		return this.randomHorizontal(world);
	}

	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand){
		super.updateTick(world, pos, state, rand);
		if(!world.isRemote) {
			if(!world.isChunkGeneratedAt(pos.getX() >> 4, pos.getZ() >> 4)) return;
			final MutableBlockPos posN = new BlockPos.MutableBlockPos();
			for(final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				posN.setPos(pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ);
				if(!world.isBlockLoaded(posN)) return;
				final IBlockState b = world.getBlockState(posN);
				
				if(isFireSource(b)) {
					combust(world, pos);
					return;
				}
			}

			if(rand.nextInt(20) == 0 && world.isAirBlock(pos.down())) {
				world.setBlockToAir(pos);
            }
		}
	}
	
	@Untested
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos){
		final MutableBlockPos posN = new BlockPos.MutableBlockPos();
		for(final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			posN.setPos(pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ);
			if(!world.isBlockLoaded(posN)) return;
			final IBlockState b = world.getBlockState(posN);
			
			if(isFireSource(b)) {
				world.scheduleUpdate(pos, this, 2);
				return;
			}
		}
	}
	
	protected void combust(final World world, final BlockPos p) {
		world.setBlockState(p, Blocks.FIRE.getDefaultState());
	}
	
	public boolean isFireSource(final IBlockState b) {
		return b.getMaterial() == Material.FIRE || b.getMaterial() == Material.LAVA || b.getBlock() == Blocks.TORCH;
	}

	@Override
	public boolean isFlammable(final IBlockAccess world, final BlockPos pos, final EnumFacing face){
		return true;
	}
	
	@Override
	public int getDelay(final World world) {
		return world.rand.nextInt(5) + 16;
	}
}