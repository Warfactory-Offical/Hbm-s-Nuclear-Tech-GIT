package com.hbm.world.generator.room;

import com.hbm.blocks.ModBlocks;
import com.hbm.world.generator.CellularDungeon;
import com.hbm.world.generator.CellularDungeonRoom;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestDungeonRoom2 extends CellularDungeonRoom {

	public TestDungeonRoom2(final CellularDungeon parent) {
		super(parent);
	}

	public void generateMain(final World world, final int x, final int y, final int z) {
		
		super.generateMain(world, x, y, z);

		final int j = world.rand.nextInt(2) + 2;
		final int k = world.rand.nextInt(3) + 2;
		
		for(int i = 0; i < j; i++) {
			final int dx = world.rand.nextInt(parent.width - 6) + 3;
			final int dz = world.rand.nextInt(parent.width - 6) + 3;
			world.setBlockState(new BlockPos(x + dx, y + 1, z + dz), ModBlocks.crate_ammo.getDefaultState(), 2);
		}
		
		for(int i = 0; i < k; i++) {
			final int dx = world.rand.nextInt(parent.width - 6) + 3;
			final int dz = world.rand.nextInt(parent.width - 6) + 3;
			world.setBlockState(new BlockPos(x + dx, y + 1, z + dz), ModBlocks.crate_can.getDefaultState(), 2);
		}
	}
}