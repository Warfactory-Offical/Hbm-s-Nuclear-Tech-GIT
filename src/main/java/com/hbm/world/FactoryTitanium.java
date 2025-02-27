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

public class FactoryTitanium extends WorldGenerator {

	public static String[][] array = new String[][] {
		{
			"HHH",
			"HHH",
			"HHH"
		},
		{
			"HFH",
			"FCF",
			"HFH"
		},
		{
			"HHH",
			"HHH",
			"HHH"
		}
	};
	
	Block Block1 = ModBlocks.factory_titanium_hull;
	Block Block2 = ModBlocks.factory_titanium_conductor;
	Block Block3 = ModBlocks.factory_titanium_furnace;
	Block Block4 = ModBlocks.factory_titanium_core;
	
	@Override
	public boolean generate(final World worldIn, final Random rand, final BlockPos position) {
		final int i = rand.nextInt(1);

		if(i == 0)
		{
		    generate_r0(worldIn, rand, new BlockPos.MutableBlockPos(position));
		}

       return true;
	}
	
	public boolean generate_r0(final World world, final Random rand, final MutableBlockPos pos)
	{
		final int x = pos.getX() - 1;
		final int y = pos.getY();
		final int z = pos.getZ() - 1;
		
		world.setBlockState(pos.setPos(x, y, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y, z + 1), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 1, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 1, z), Block3.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.values()[2]), 3);
		world.setBlockState(pos.setPos(x + 2, y + 1, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 1, z + 1), Block3.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.values()[4]), 3);
		world.setBlockState(pos.setPos(x + 1, y + 1, z + 1), Block4.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 1, z + 1), Block3.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.values()[5]), 3);
		world.setBlockState(pos.setPos(x, y + 1, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 1, z + 2), Block3.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.values()[3]), 3);
		world.setBlockState(pos.setPos(x + 2, y + 1, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 2, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 2, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 2, z), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 2, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 2, z + 1), Block2.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 2, z + 1), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x, y + 2, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 1, y + 2, z + 2), Block1.getDefaultState(), 3);
		world.setBlockState(pos.setPos(x + 2, y + 2, z + 2), Block1.getDefaultState(), 3);
		return true;

	}

}
