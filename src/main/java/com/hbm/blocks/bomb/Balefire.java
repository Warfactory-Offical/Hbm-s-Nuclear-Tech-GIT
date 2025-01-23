package com.hbm.blocks.bomb;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.potion.HbmPotion;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Balefire extends BlockFire {

	public Balefire(final String s) {
		super();
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(null);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override 
	protected boolean canDie(final World worldIn, final BlockPos pos){
		final Block b = worldIn.getBlockState(pos.down()).getBlock();

		return (b != ModBlocks.baleonitite_slaked && 
			b != ModBlocks.baleonitite_1 && 
			b != ModBlocks.baleonitite_2 && 
			b != ModBlocks.baleonitite_3 && 
			b != ModBlocks.baleonitite_4 && 
			b != ModBlocks.baleonitite_core);
	}

	@Override 
	public int getFlammability(final Block b){
		if(b != ModBlocks.baleonitite_slaked && 
			b != ModBlocks.baleonitite_1 && 
			b != ModBlocks.baleonitite_2 && 
			b != ModBlocks.baleonitite_3 && 
			b != ModBlocks.baleonitite_4 && 
			b != ModBlocks.baleonitite_core){
			return 20000;
		}
		return super.getEncouragement(b);
	}

	@Override 
	public int getEncouragement(final Block b){
		if(b != ModBlocks.baleonitite_slaked && 
			b != ModBlocks.baleonitite_1 && 
			b != ModBlocks.baleonitite_2 && 
			b != ModBlocks.baleonitite_3 && 
			b != ModBlocks.baleonitite_4 && 
			b != ModBlocks.baleonitite_core){
			return 20000;
		}
		return super.getEncouragement(b);
	}

	@Override
	public void onEntityCollision(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entityIn) {
		entityIn.setFire(10);

		if (entityIn instanceof EntityLivingBase)
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(HbmPotion.radiation, 5 * 20, 9));
	}
}
