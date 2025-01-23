package com.hbm.blocks.generic;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.ModDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Spikes extends Block {

	public Spikes(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBox(final IBlockState state, final World worldIn, final BlockPos pos) {
		return new AxisAlignedBB(pos, pos);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess worldIn, final BlockPos pos) {
		return NULL_AABB;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state) {
		return false;
	}
	@Override
	public boolean isNormalCube(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		return false;
	}
	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}
	@Override
	public boolean isCollidable() {
		return true;
	}
	@Override
	public boolean isBlockNormalCube(final IBlockState state) {
		return false;
	}
	@Override
	public boolean isFullBlock(final IBlockState state) {
		return false;
	}
	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}
	@Override
	public boolean isSideSolid(final IBlockState base_state, final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
		return false;
	}
	@Override
	public boolean isTranslucent(final IBlockState state) {
		return true;
	}
	
	@Override
	public boolean causesSuffocation(final IBlockState state) {
		return false;
	}
	
	@Override
	public void onEntityCollision(final World world, final BlockPos pos, final IBlockState state, final Entity ent) {
		if(ent instanceof EntityLivingBase && ent.motionY < -0.1) {
    		if(ent.attackEntityFrom(ModDamageSource.spikes, 100))
    			world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, HBMSoundHandler.slicer, SoundCategory.HOSTILE, 1.0F, 1.0F);
    	}
	}

}
