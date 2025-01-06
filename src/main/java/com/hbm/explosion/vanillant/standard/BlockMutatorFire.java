package com.hbm.explosion.vanillant.standard;

import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.interfaces.IBlockMutator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BlockMutatorFire implements IBlockMutator {

	@Override public void mutatePre(ExplosionVNT explosion, Block block, int meta, int x, int y, int z) { }

	@Override
	public void mutatePost(ExplosionVNT explosion, int x, int y, int z) {

		Block block = explosion.world.getBlockState(new BlockPos(x, y, z)).getBlock();
		Block block1 = explosion.world.getBlockState(new BlockPos(x, y-1, z)).getBlock();
		if(block.getBlockState().getBaseState().getMaterial() == Material.AIR && block1.isOpaqueCube(block1.getDefaultState()) && explosion.world.rand.nextInt(3) == 0) {
			explosion.world.setBlockState(new BlockPos(x, y, z), Blocks.FIRE.getDefaultState());
		}
	}
}
