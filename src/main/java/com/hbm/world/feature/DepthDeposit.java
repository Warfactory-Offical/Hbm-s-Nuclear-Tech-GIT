package com.hbm.world.feature;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DepthDeposit {

public static void generateConditionOverworld(final World world, final int x, final int yMin, final int yDev, final int z, final int size, final double fill, final Block block, final Random rand, final int chance) {
		
		if(rand.nextInt(chance) == 0)
			generate(world, x + rand.nextInt(16), yMin + rand.nextInt(yDev), z + rand.nextInt(16), size, fill, block, rand, Blocks.STONE, ModBlocks.stone_depth);
	}

	public static void generateConditionNether(final World world, final int x, final int yMin, final int yDev, final int z, final int size, final double fill, final Block block, final Random rand, final int chance) {
		
		if(rand.nextInt(chance) == 0)
			generate(world, x + rand.nextInt(16), yMin + rand.nextInt(yDev), z + rand.nextInt(16), size, fill, block, rand, Blocks.NETHERRACK, ModBlocks.stone_depth_nether);
	}

	public static void generateCondition(final World world, final int x, final int yMin, final int yDev, final int z, final int size, final double fill, final Block block, final Random rand, final int chance, final Block genTarget, final Block filler) {
		
		if(rand.nextInt(chance) == 0)
			generate(world, x + rand.nextInt(16), yMin + rand.nextInt(yDev), z + rand.nextInt(16), size, fill, block, rand, genTarget, filler);
	}

	public static void generate(final World world, final int x, final int y, final int z, final int size, final double fill, final Block block, final Random rand, final Block genTarget, final Block filler) {
		
		for(int i = x - size; i <= x + size; i++) {
			for(int j = y - size; j <= y + size; j++) {
				for(int k = z - size; k <= z + size; k++) {
					
					if(j < 1 || j > 126)
						continue;
					
					final BlockPos pos = new BlockPos(i, j, k);
					final double len = Vec3.createVectorHelper(x - i, y - j, z - k).length();
					final Block target = world.getBlockState(pos).getBlock();
					
					if(target.isReplaceableOreGen(world.getBlockState(pos), world, pos, BlockMatcher.forBlock(genTarget)) || target.isReplaceableOreGen(world.getBlockState(pos), world, pos, BlockMatcher.forBlock(Blocks.BEDROCK))) { //yes you've heard right, bedrock
						
						if(len + rand.nextInt(2) < size * fill) {
							world.setBlockState(pos, block.getDefaultState());
							
						} else  if(len + rand.nextInt(2) <= size) {
							world.setBlockState(pos, filler.getDefaultState());
						}
					}
				}
			}
		}
	}
}
