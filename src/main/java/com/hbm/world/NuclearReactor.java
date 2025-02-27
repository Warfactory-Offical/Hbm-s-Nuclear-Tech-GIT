package com.hbm.world;

import java.util.Random;

import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class NuclearReactor extends WorldGenerator {

	public static String[][] array2 = new String[][] {
		{
			"     ",
			" BBB ",
			" B B ",
			" BBB ",
			"     "
		},
		{
			" BBB ",
			"BRCRB",
			"BCICB",
			"BRCRB",
			" BBB "
		},
		{
			" BAB ",
			"BRCRB",
			"AC#CA",
			"BRCRB",
			" BAB "
		},
		{
			" BBB ",
			"BRCRB",
			"BCICB",
			"BRCRB",
			" BBB "
		},
		{
			"     ",
			" BBB ",
			" B B ",
			" BBB ",
			"     "
		}
	};
	
	Block Block1 = ModBlocks.brick_concrete;
	Block Block2 = ModBlocks.reactor_element;
	Block Block3 = ModBlocks.reactor_control;
	Block Block4 = ModBlocks.reactor_conductor;
	Block Block5 = ModBlocks.reactor_hatch;
	Block Block6 = ModBlocks.reactor_computer;
	
	@Override
	public boolean generate(final World world, final Random rand, final BlockPos position) {
		final int i = rand.nextInt(1);

		if(i == 0)
		{
		    generate_r0(world, rand, new BlockPos.MutableBlockPos(position));
		}

       return true;
	}
	
	public boolean generate_r0(final World world, final Random rand, final MutableBlockPos pos)
	{
		final int x = pos.getX() -2;
		final int y = pos.getY();
		final int z = pos.getZ() - 2;
		
		world.setBlockState(pos.setPos(x, y, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y, z + 2), ModBlocks.fluid_duct_mk2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 1, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 1, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 1, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 1, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 1, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 1, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 1, z + 1), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 1, z + 1), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 1, z + 1), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 1, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 1, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 1, z + 2), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 1, z + 2), Block4.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 1, z + 2), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 1, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 1, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 1, z + 3), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 1, z + 3), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 1, z + 3), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 1, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 1, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 1, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 1, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 1, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 1, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 2, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 2, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 2, z), Block5.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.values()[2]), 3);
		world.setBlockState(pos.setPos(x + 3, y + 2, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 2, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 2, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 2, z + 1), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 2, z + 1), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 2, z + 1), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 2, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 2, z + 2), Block5.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.values()[4]), 3);
		world.setBlockState(pos.setPos(x + 1, y + 2, z + 2), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 2, z + 2), Block6.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 2, z + 2), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 2, z + 2), Block5.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.values()[5]), 3);
		world.setBlockState(pos.setPos(x, y + 2, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 2, z + 3), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 2, z + 3), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 2, z + 3), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 2, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 2, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 2, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 2, z + 4), Block5.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.values()[3]), 3);
		world.setBlockState(pos.setPos(x + 3, y + 2, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 2, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 3, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 3, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 3, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 3, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 3, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 3, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 3, z + 1), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 3, z + 1), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 3, z + 1), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 3, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 3, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 3, z + 2), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 3, z + 2), Block4.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 3, z + 2), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 3, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 3, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 3, z + 3), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 3, z + 3), Block3.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 3, z + 3), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 3, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 3, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 3, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 3, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 3, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 3, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 4, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 4, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 4, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 4, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 4, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 4, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 4, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 4, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 4, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 4, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 4, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 4, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 4, z + 2), ModBlocks.fluid_duct_mk2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 4, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 4, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 4, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 4, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 4, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 4, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 4, z + 3), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 4, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 4, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 4, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 3, y + 4, z + 4), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 4, y + 4, z + 4), Block1.getDefaultState(), 3);
		return true;

	}
}
