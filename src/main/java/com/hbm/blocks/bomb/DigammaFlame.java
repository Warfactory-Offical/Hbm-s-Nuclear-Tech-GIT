package com.hbm.blocks.bomb;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DigammaFlame extends BlockFire {

	public DigammaFlame(final String s) {
		super();
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	public boolean canCatchFire(final IBlockAccess world, final BlockPos pos, final EnumFacing face){
		return false;
	}

	@Override 
	protected boolean canDie(final World worldIn, final BlockPos pos){
		return false;
	}

	@Override
	public int tickRate(final World worldIn){
		return 72000;
	}

	@Override
	public void onEntityCollision(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entityIn){
		if(entityIn instanceof EntityLivingBase) {
			ContaminationUtil.contaminate((EntityLivingBase) entityIn, HazardType.DIGAMMA, ContaminationType.DIGAMMA, 0.05F);
		}
	}
}
