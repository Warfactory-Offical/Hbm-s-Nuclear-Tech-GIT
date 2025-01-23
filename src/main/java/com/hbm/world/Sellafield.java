package com.hbm.world;

import java.util.Random;

import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Sellafield {
	
	private double depthFunc(final double x, final double rad, final double depth) {
		
		return -Math.pow(x, 2) / Math.pow(rad, 2) * depth + depth;
	}
	
	public void generate(final World world, final int x, final int z, final double radius, final double depth) {
		
		if(world.isRemote)
			return;
		
		final Random rand = new Random();
		
		final int iRad = (int)Math.round(radius);
		
		for(int a = -iRad - 5; a <= iRad + 5; a++) {
			
			for(int b = -iRad - 5; b <= iRad + 5; b++) {
				
				final double r = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
				
				if(r - rand.nextInt(3) <= radius) {
					
					final int dep = (int)depthFunc(r, radius, depth);
					dig(world, x + a, z + b, dep);

					if(r + rand.nextInt(3) <= radius / 6D) {
						place(world, x + a, z + b, 3, ModBlocks.sellafield_4);
					} else if(r - rand.nextInt(3) <= radius / 6D * 2D) {
						place(world, x + a, z + b, 3, ModBlocks.sellafield_3);
					} else if(r - rand.nextInt(3) <= radius / 6D * 3D) {
						place(world, x + a, z + b, 3, ModBlocks.sellafield_2);
					} else if(r - rand.nextInt(3) <= radius / 6D * 4D) {
						place(world, x + a, z + b, 3, ModBlocks.sellafield_1);
					} else if(r - rand.nextInt(3) <= radius / 6D * 5D) {
						place(world, x + a, z + b, 3, ModBlocks.sellafield_0);
					} else {
						place(world, x + a, z + b, 3, ModBlocks.sellafield_slaked);
					}
				}
			}
		}

		placeCore(world, x, z, radius * 0.3D);
	}
	
	private void dig(final World world, final int x, final int z, final int depth) {
		
		final int y = world.getHeight(x, z) - 1;
		
		if(y < depth * 2)
			return;
		
		for(int i = 0; i < depth; i++)
			world.setBlockToAir(new BlockPos(x, y - i, z));
	}
	
	private void place(final World world, final int x, final int z, final int depth, final Block block) {
		
		final int y = world.getHeight(x, z) - 1;
		
		for(int i = 0; i < depth; i++)
			world.setBlockState(new BlockPos(x, y - i, z), block.getDefaultState());
	}
	
	private void placeCore(final World world, final int x, final int z, final double rad) {
		
		final int y = world.getHeight(x, z) - 1;
		
		world.setBlockState(new BlockPos(x, y, z), ModBlocks.sellafield_core.getDefaultState());
		//Drillgon200: This tile entity is never actually created anywhere
		/*try {
			
			TileEntitySellafield te = (TileEntitySellafield) world.getTileEntity(x, y, z);
			te.radius = rad;
			
		} catch(Exception ex) { }*/
	}
}