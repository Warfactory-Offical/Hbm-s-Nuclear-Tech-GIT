package com.hbm.blocks.generic;

import com.hbm.blocks.BlockFallingBase;
import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;
import com.hbm.capability.HbmLivingProps;
import com.hbm.capability.HbmLivingProps.ContaminationEffect;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGoldSand extends BlockFallingBase implements IItemHazard {

	ItemHazardModule module;

	public BlockGoldSand(final Material m, final String s, final SoundType type){
		super(m, s, type);
		this.module = new ItemHazardModule();
	}
	
	@Override
	public void onEntityWalk(final World worldIn, final BlockPos pos, final Entity entity) {
		if(entity instanceof EntityLivingBase)
			this.module.applyEffects((EntityLivingBase)entity, 0.5F, 0, false, EnumHand.MAIN_HAND);
	}

	@Override
	public void onEntityCollision(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entity){
		if(entity instanceof EntityLivingBase)
			this.module.applyEffects((EntityLivingBase)entity, 0.5F, 0, false, EnumHand.MAIN_HAND);
	}

	@Override
	public ItemHazardModule getModule() {
		return module;
	}
}
