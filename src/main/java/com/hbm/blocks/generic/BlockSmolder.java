package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSmolder extends Block {

	public BlockSmolder(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final IBlockState state, final World world, final BlockPos pos, final Random rand) {
		super.randomDisplayTick(state, world, pos, rand);
		if(world.getBlockState(pos.up()).getMaterial() == Material.AIR) {

    		world.spawnParticle(EnumParticleTypes.LAVA, pos.getX() + 0.25 + rand.nextDouble() * 0.5, pos.getY() + 1.1, pos.getZ() + 0.25 + rand.nextDouble() * 0.5, 0.0, 0.0, 0.0);
    		world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.25 + rand.nextDouble() * 0.5, pos.getY() + 1.1, pos.getZ() + 0.25 + rand.nextDouble() * 0.5, 0.0, 0.0, 0.0);
    	}
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return ModItems.powder_fire;
	}
	
	@Override
	public void onEntityWalk(final World worldIn, final BlockPos pos, final Entity entityIn) {
		entityIn.setFire(3);
	}

}
