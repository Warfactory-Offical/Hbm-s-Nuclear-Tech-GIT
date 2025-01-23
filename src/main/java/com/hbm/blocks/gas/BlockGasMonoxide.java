package com.hbm.blocks.gas;

import java.util.Random;

import com.hbm.handler.ArmorUtil;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.ModDamageSource;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorRegistry.HazardClass;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGasMonoxide extends BlockGasBase {

	public BlockGasMonoxide(final String s) {
		super(0.1F, 0.1F, 0.1F, s);
	}

	@Override
	public void onEntityCollision(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entity){
		if(!(entity instanceof EntityLivingBase entityLiving))
			return;

        if(ArmorRegistry.hasAllProtection(entityLiving, EntityEquipmentSlot.HEAD, HazardClass.GAS_MONOXIDE))
			ArmorUtil.damageGasMaskFilter(entityLiving, 1);
		else
			entityLiving.attackEntityFrom(ModDamageSource.monoxide, 1);
	}
	
	@Override
	public ForgeDirection getFirstDirection(final World world, final int x, final int y, final int z) {
		return ForgeDirection.DOWN;
	}

	@Override
	public ForgeDirection getSecondDirection(final World world, final int x, final int y, final int z) {
		return this.randomHorizontal(world);
	}

	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand) {

		if(!world.isRemote && rand.nextInt(100) == 0) {
			world.setBlockToAir(pos);
			return;
		}
		
		super.updateTick(world, pos, state, rand);
	}
}