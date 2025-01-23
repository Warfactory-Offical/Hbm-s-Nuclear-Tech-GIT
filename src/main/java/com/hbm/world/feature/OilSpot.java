package com.hbm.world.feature;

import com.hbm.blocks.ModBlocks;

import com.hbm.blocks.generic.BlockDeadPlant;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class OilSpot {

    public static void generateOilSpot(final World world, final int x, final int z, final int width, final int count) {

        for(int i = 0; i < count; i++) {
            final int rX = x + (int)(world.rand.nextGaussian() * width);
            final int rZ = z + (int)(world.rand.nextGaussian() * width);
            final int rY = world.getHeight(rX, rZ);

            final BlockPos pos = new BlockPos(rX, rY - 1, rZ);
            final BlockPos plantPos = new BlockPos(rX, rY, rZ);

            for(int y = rY; y > rY - 4; y--) {

                final Block below = world.getBlockState(pos).getBlock();
                final Block ground = world.getBlockState(plantPos).getBlock();

                if(below.isNormalCube(below.getDefaultState(), world, pos) && !(ground instanceof BlockDeadPlant)) {
                    if(ground instanceof BlockTallGrass) {
                        if(world.rand.nextInt(10) == 0) {
                            final Block block = world.getBlockState(new BlockPos(rX, rY + 1, rZ)).getBlock();
                            if (block.getMetaFromState(block.getBlockState().getBaseState()) == 2) {
                                world.setBlockState(plantPos, ModBlocks.plant_dead_fern.getDefaultState());
                            } else {
                                world.setBlockState(plantPos, ModBlocks.plant_dead_grass.getDefaultState());
                            }
                        } else {
                            world.setBlockState(plantPos, Blocks.AIR.getDefaultState());
                        }
                    } else if(ground instanceof BlockFlower) {
                        world.setBlockState(plantPos, ModBlocks.plant_dead_flower.getDefaultState());
                    } else if(ground instanceof BlockDoublePlant) {
                        world.setBlockState(plantPos, ModBlocks.plant_dead_big_flower.getDefaultState());
                    } else if(ground instanceof BlockBush) {
                        world.setBlockState(plantPos, ModBlocks.plant_dead_generic.getDefaultState());
                    } else if(ground instanceof IPlantable) {
                        world.setBlockState(plantPos, ModBlocks.plant_dead_generic.getDefaultState());
                    }
                }

                if(below == Blocks.GRASS || below == Blocks.DIRT) {
                    world.setBlockState(pos, world.rand.nextInt(10) == 0 ? ModBlocks.dirt_oily.getDefaultState() : ModBlocks.dirt_dead.getDefaultState());
                    break;

                } else if(below == Blocks.SAND || below == ModBlocks.ore_oil_sand) {

                    final IBlockState blockState = world.getBlockState(pos);
                    if(blockState.getBlock() == Blocks.SAND && blockState.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND)
                        world.setBlockState(pos, ModBlocks.sand_dirty_red.getDefaultState());
                    else
                        world.setBlockState(pos, ModBlocks.sand_dirty.getDefaultState());
                    break;

                } else if(below == Blocks.STONE) {
                    world.setBlockState(pos, ModBlocks.stone_cracked.getDefaultState());
                    break;

                } else if(below.getDefaultState().getMaterial() == Material.LEAVES) {
                    world.setBlockToAir(pos);
                    break;
                }
            }
        }
    }
}