package com.hbm.world;

import java.util.Random;

import com.hbm.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class Geyser extends WorldGenerator {

	@Override
	public boolean generate(final World world, final Random rand, final BlockPos pos) {
		final int i = rand.nextInt(1);

		if (i == 0) {
			generate_r0(world, rand, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;

	}

	public boolean generate_r0(final World world, final Random rand, int x, int y, int z) {

		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		x -= 2;
		y -= 11;
		z -= 2;
		world.setBlockState(pos.setPos(x + 1, y + 5, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 5, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 5, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 5, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 5, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 5, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 5, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 5, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 5, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 5, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 5, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 5, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 5, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 5, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 5, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 5, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 5, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 5, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 5, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 5, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 5, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 6, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 6, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 6, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 6, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 6, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 6, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 6, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 6, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 6, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 6, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 6, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 6, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 6, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 6, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 6, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 6, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 6, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 6, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 6, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 6, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 6, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 7, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 7, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 7, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 7, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 7, z + 1), Blocks.WATER.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 7, z + 1), ModBlocks.block_yellowcake.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 7, z + 1), Blocks.WATER.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 7, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 7, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 7, z + 2), ModBlocks.block_yellowcake.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 7, z + 2), Blocks.WATER.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 7, z + 2), Blocks.WATER.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 7, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 7, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 7, z + 3), Blocks.WATER.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 7, z + 3), ModBlocks.block_yellowcake.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 7, z + 3), ModBlocks.block_yellowcake.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 7, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 7, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 7, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 7, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 8, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 8, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 8, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 8, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 8, z + 1), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 8, z + 1), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 8, z + 1), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 8, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 8, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 8, z + 2), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 8, z + 2), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 8, z + 2), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 8, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 8, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 8, z + 3), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 8, z + 3), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 8, z + 3), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 8, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 8, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 8, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 8, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 9, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 9, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 9, z), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 9, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 9, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 9, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 9, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 9, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 9, z + 2), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 9, z + 2), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 9, z + 2), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 9, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 9, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 9, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 9, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 9, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 9, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 9, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 9, z + 4), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 10, z), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 10, z), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 10, z), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 10, z + 1), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 10, z + 1), Blocks.GRAVEL.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 10, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 10, z + 1), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 10, z + 1), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 10, z + 2), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 10, z + 2), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 10, z + 2), ModBlocks.geysir_chlorine.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 10, z + 2), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 10, z + 2), Blocks.GRAVEL.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 10, z + 3), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 10, z + 3), Blocks.STONE.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 10, z + 3), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 10, z + 3), Blocks.GRAVEL.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 10, z + 3), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 10, z + 4), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 10, z + 4), Blocks.GRASS.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 10, z + 4), Blocks.GRASS.getDefaultState(), 3);
		return true;

	}

}
