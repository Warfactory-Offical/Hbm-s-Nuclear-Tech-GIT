package com.hbm.world;

import java.util.Random;

import com.hbm.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class OilSandBubble {
	
	private final static Random field_149933_a = new Random();

	public static void spawnOil(final World world, final int x, final int y, final int z, final int radius) {
		final int r = radius;
		final int r2 = r * r;
		final int r22 = r2 / 2;

		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		for (int xx = -r; xx < r; xx++) {
			final int X = xx + x;
			final int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				final int Y = yy + y;
				final int YY = XX + yy * yy * 3;
				for (int zz = -r; zz < r; zz++) {
					final int Z = zz + z;
					final int ZZ = YY + zz * zz;
					if (ZZ < r22 + field_149933_a.nextInt(r22 / 3)) {
						pos.setPos(X, Y, Z);
						if(world.getBlockState(pos).getBlock() == Blocks.SAND)
							world.setBlockState(pos, ModBlocks.ore_oil_sand.getDefaultState());
					}
				}
			}
		}
	}
	
}