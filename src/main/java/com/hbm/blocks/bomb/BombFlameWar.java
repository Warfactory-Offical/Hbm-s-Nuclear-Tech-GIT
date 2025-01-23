package com.hbm.blocks.bomb;

import com.hbm.blocks.ModBlocks;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.interfaces.IBomb;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BombFlameWar extends Block implements IBomb {

	public BombFlameWar(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World worldIn, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if(worldIn.isBlockPowered(pos)){
			explode(worldIn, pos);
		}
	}

	@Override
	public void explode(final World world, final BlockPos pos) {
		ExplosionChaos.explode(world, pos.getX(), pos.getY(), pos.getZ(), 15);
    	ExplosionChaos.spawnExplosion(world, pos.getX(), pos.getY(), pos.getZ(), 75);
    	ExplosionChaos.flameDeath(world, pos, 100);
	}

}
