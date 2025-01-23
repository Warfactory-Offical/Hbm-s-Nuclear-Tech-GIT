package com.hbm.blocks.bomb;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.explosion.ExplosionMining;
import com.hbm.explosion.ExplosionNT;
import com.hbm.explosion.ExplosionNT.ExAttrib;
import com.hbm.interfaces.IBomb;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class DetMiner extends Block implements IBomb {

	public DetMiner(final Material m, final String s) {
		super(m);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.AIR;
	}
	
	@Override
	public void explode(final World world, final BlockPos pos) {
		if(!world.isRemote) {

			world.destroyBlock(pos, false);
			final ExplosionMining explosion = new ExplosionMining(world, null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8);
			explosion.explode();

			ExplosionLarge.spawnParticles(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 30);
		}
	}
	
	@Override
	public void onExplosionDestroy(final World worldIn, final BlockPos pos, final Explosion explosionIn) {
		this.explode(worldIn, pos);
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if (world.isBlockPowered(pos))
        {
        	this.explode(world, pos);
        }
	}

}
