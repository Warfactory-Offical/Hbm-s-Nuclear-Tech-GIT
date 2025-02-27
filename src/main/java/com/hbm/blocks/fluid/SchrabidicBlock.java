package com.hbm.blocks.fluid;

import com.hbm.blocks.ModBlocks;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class SchrabidicBlock extends BlockFluidClassic {

	public static DamageSource damageSource;
	
	public SchrabidicBlock(final Fluid fluid, final Material material, final DamageSource source, final String s) {
		super(fluid, material);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		damageSource = source;
		setQuantaPerBlock(4);
		setCreativeTab(null);
		displacements.put(this, false);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public boolean canDisplace(final IBlockAccess world, final BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.canDisplace(world, pos);
	}
	
	@Override
	public boolean displaceIfPossible(final World world, final BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, pos);
	}
	
	@Override
	public void onEntityCollision(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entity) {
		entity.setInWeb();
		if(entity instanceof EntityLivingBase)
			ContaminationUtil.contaminate((EntityLivingBase)entity, HazardType.RADIATION, ContaminationType.CREATIVE, 10.0F);
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block neighborBlock, final BlockPos neighbourPos) {
		super.neighborChanged(state, world, pos, neighborBlock, neighbourPos);
		final int x = pos.getX();
		final int y = pos.getY();
		final int z = pos.getZ();
		if(reactToBlocks(world, x + 1, y, z))
			world.setBlockState(pos, ModBlocks.sellafield_slaked.getDefaultState());
		if(reactToBlocks(world, x - 1, y, z))
			world.setBlockState(pos, ModBlocks.sellafield_slaked.getDefaultState());
		if(reactToBlocks(world, x, y + 1, z))
			world.setBlockState(pos, ModBlocks.sellafield_slaked.getDefaultState());
		if(reactToBlocks(world, x, y - 1, z))
			world.setBlockState(pos, ModBlocks.sellafield_slaked.getDefaultState());
		if(reactToBlocks(world, x, y, z + 1))
			world.setBlockState(pos, ModBlocks.sellafield_slaked.getDefaultState());
		if(reactToBlocks(world, x, y, z - 1))
			world.setBlockState(pos, ModBlocks.sellafield_slaked.getDefaultState());
	}
	
	public boolean reactToBlocks(final World world, final int x, final int y, final int z) {
		if(world.getBlockState(new BlockPos(x, y, z)).getMaterial() != ModBlocks.fluidschrabidic) {
            return world.getBlockState(new BlockPos(x, y, z)).getMaterial().isLiquid();
		}
		return false;
	}
	
	@Override
	public int tickRate(final World world) {
		return 15;
	}

}
