package com.hbm.blocks.fluid;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ModDamageSource;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CoriumBlock extends BlockFluidClassic {

	public Random rand = new Random();

	public CoriumBlock(final Fluid fluid, final Material material, final String s) {
		super(fluid, material);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		setQuantaPerBlock(5);
		setCreativeTab(null);
		displacements.put(this, false);
		this.tickRate = 30;
		
		this.setTickRandomly(true);
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public boolean canDisplace(final IBlockAccess world, final BlockPos pos){
		final IBlockState b = world.getBlockState(pos);
		@SuppressWarnings("deprecation") final float res = (float) (Math.sqrt(b.getBlock().getExplosionResistance(null)) * 2);
		
		if(res < 1)
			return true;
		final Random rand = new Random();
		
		return b.getMaterial().isLiquid() || rand.nextInt((int) res) == 0;
	}
	
	@Override
	public boolean displaceIfPossible(final World world, final BlockPos pos){
		if(world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return canDisplace(world, pos);
	}

	@Override
	public void onEntityCollision(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entity){
		entity.setInWeb();
		entity.setFire(3);
		entity.attackEntityFrom(ModDamageSource.radiation, 200F);
		
		if(entity instanceof EntityLivingBase)
			ContaminationUtil.contaminate((EntityLivingBase)entity, HazardType.RADIATION, ContaminationType.CREATIVE, 500F);
	}
	
	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand){
		super.updateTick(world, pos, state, rand);
		
		if(!world.isRemote && rand.nextInt(10) == 0) {
			
			if(this.isSourceBlock(world, pos))
				world.setBlockState(pos, ModBlocks.block_corium.getDefaultState());
			else
				world.setBlockState(pos, ModBlocks.block_corium_cobble.getDefaultState());
		}
	}
	
	@Override
	public boolean isReplaceable(final IBlockAccess worldIn, final BlockPos pos){
		return false;
	}
}