package com.hbm.blocks.bomb;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class CheaterVirusSeed extends Block {

	public CheaterVirusSeed(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setTickRandomly(true);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public void breakBlock(final World world, final BlockPos pos1, final IBlockState state) {
		super.breakBlock(world, pos1, state);
		if(!GeneralConfig.enableVirus)
			return;
		final int x = pos1.getX();
		final int y = pos1.getY();
		final int z = pos1.getZ();
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();

    	if((world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			!world.isRemote) {
			world.setBlockState(pos.setPos(x, y, z), Blocks.AIR.getDefaultState());
    	} else {
    		world.setBlockState(pos.setPos(x, y, z), ModBlocks.cheater_virus.getDefaultState());
    	}
	}
	
	@Override
	public void updateTick(final World world, final BlockPos pos1, final IBlockState state, final Random rand) {
		if(!GeneralConfig.enableVirus)
			return;
		final int x = pos1.getX();
		final int y = pos1.getY();
		final int z = pos1.getZ();
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		if((world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			!world.isRemote) {
			world.setBlockState(pos.setPos(x, y, z), Blocks.AIR.getDefaultState());
    	} else {
    		world.setBlockState(pos.setPos(x, y, z), ModBlocks.cheater_virus.getDefaultState());
    	}
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos1, final Block blockIn, final BlockPos fromPos) {
		final int x = pos1.getX();
		final int y = pos1.getY();
		final int z = pos1.getZ();
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		if((world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x + 1, y, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x - 1, y, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y + 1, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y, z + 1)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			(world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == Blocks.AIR || world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == ModBlocks.cheater_virus || world.getBlockState(pos.setPos(x, y, z - 1)).getBlock() == ModBlocks.cheater_virus_seed) && 
    			!world.isRemote) {
			world.setBlockState(pos.setPos(x, y, z), Blocks.AIR.getDefaultState());
    	}
	}

}
