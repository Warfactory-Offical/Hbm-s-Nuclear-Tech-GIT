package com.hbm.blocks.gas;

import java.util.Random;

import com.hbm.lib.ForgeDirection;
import com.hbm.config.GeneralConfig;
import com.hbm.util.ContaminationUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGasAsbestos extends BlockGasBase {

	public BlockGasAsbestos(final String s) {
		super(0.6F, 0.6F, 0.5F, s);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final IBlockState stateIn, final World world, final BlockPos pos, final Random rand){
		super.randomDisplayTick(stateIn, world, pos, rand);
		if(world.rand.nextInt(5) == 0)
			world.spawnParticle(EnumParticleTypes.TOWN_AURA, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
	}
	
	@Override
	public void onEntityCollision(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entity){
		ContaminationUtil.applyAsbestos(entity, 10, 1);
	}
	

	@Override
	public ForgeDirection getFirstDirection(final World world, final int x, final int y, final int z) {
		
		if(world.rand.nextInt(5) == 0)
			return ForgeDirection.DOWN;
		
		return ForgeDirection.getOrientation(world.rand.nextInt(6));
	}

	@Override
	public ForgeDirection getSecondDirection(final World world, final int x, final int y, final int z) {
		return this.randomHorizontal(world);
	}

	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand) {

		if(!world.isRemote && (!GeneralConfig.enableAsbestos || rand.nextInt(10) == 0)) {
			world.setBlockToAir(pos);
			return;
		}
		
		super.updateTick(world, pos, state, rand);
	}
}