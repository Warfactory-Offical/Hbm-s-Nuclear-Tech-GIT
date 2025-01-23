package com.hbm.world.generator;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class CellularDungeonRoom {
	
	protected CellularDungeon parent;
	protected CellularDungeonRoom daisyChain = null;
	protected EnumFacing daisyDirection = null;
	
	public CellularDungeonRoom(final CellularDungeon parent) {
		this.parent = parent;
	}
	
	//per generation, only one door can be made. rooms having multiple doors will be the consequence of daisychaining.
	//the initial room will use an invalid type to not spawn any doors.
	public void generate(final World world, final int x, final int y, final int z, final EnumFacing door) {
		generateMain(world, x, y, z);
		for(int i = 2; i < 6; i++) {
			final EnumFacing dir = EnumFacing.byIndex(i);
			generateWall(world, x, y, z, dir, dir == door);
		}
	}
	
	public void generateMain(final World world, final int x, final int y, final int z) {
		
		DungeonToolbox.generateBox(world, x, y, z, parent.width, 1, parent.width, parent.floor);
		DungeonToolbox.generateBox(world, x, y + 1, z, parent.width, parent.height - 1, parent.width, Blocks.AIR.getDefaultState());
		DungeonToolbox.generateBox(world, x, y + parent.height - 1, z, parent.width, 1, parent.width, parent.ceiling);
	}
	
	public void generateWall(final World world, final int x, final int y, final int z, final EnumFacing wall, final boolean door) {
		
		if(wall == EnumFacing.NORTH) {
			DungeonToolbox.generateBox(world, x, y + 1, z, parent.width, parent.height - 2, 1, parent.wall);
			
			if(door)
				DungeonToolbox.generateBox(world, x + parent.width / 2, y + 1, z, 1, 2, 1, Blocks.AIR.getDefaultState());
		}
		
		if(wall == EnumFacing.SOUTH) {
			DungeonToolbox.generateBox(world, x, y + 1, z + parent.width - 1, parent.width, parent.height - 2, 1, parent.wall);
			
			if(door)
				DungeonToolbox.generateBox(world, x + parent.width / 2, y + 1, z + parent.width - 1, 1, 2, 1, Blocks.AIR.getDefaultState());
		}
		
		if(wall == EnumFacing.WEST) {
			DungeonToolbox.generateBox(world, x, y + 1, z, 1, parent.height - 2, parent.width, parent.wall);
			
			if(door)
				DungeonToolbox.generateBox(world, x, y + 1, z + parent.width / 2, 1, 2, 1, Blocks.AIR.getDefaultState());
		}
		
		if(wall == EnumFacing.EAST) {
			DungeonToolbox.generateBox(world, x + parent.width - 1, y + 1, z, 1, parent.height - 2, parent.width, parent.wall);
			
			if(door)
				DungeonToolbox.generateBox(world, x + parent.width - 1, y + 1, z + parent.width / 2, 1, 2, 1, Blocks.AIR.getDefaultState());
		}
	}

}