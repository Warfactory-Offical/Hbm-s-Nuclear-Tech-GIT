package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.items.ModItems;
import com.hbm.lib.ForgeDirection;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNetherCoal extends BlockOutgas {

	public BlockNetherCoal(final boolean randomTick, final int rate, final boolean onBreak, final String s) {
		super(randomTick, rate, onBreak, s);
	}

	@Override
	public void onEntityWalk(final World world, final BlockPos pos, final Entity entity){
		entity.setFire(3);
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
		return ModItems.coal_infernal;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final IBlockState stateIn, final World worldIn, final BlockPos pos, final Random rand){
		super.randomDisplayTick(stateIn, worldIn, pos, rand);
		for(final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {

			if(dir == ForgeDirection.DOWN)
				continue;
			final int x = pos.getX();
			final int y = pos.getY();
			final int z = pos.getZ();

			if(worldIn.getBlockState(new BlockPos(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)).getMaterial() == Material.AIR) {

				double ix = x + 0.5F + dir.offsetX + rand.nextDouble() - 0.5D;
				double iy = y + 0.5F + dir.offsetY + rand.nextDouble() - 0.5D;
				double iz = z + 0.5F + dir.offsetZ + rand.nextDouble() - 0.5D;

				if(dir.offsetX != 0)
					ix = x + 0.5F + dir.offsetX * 0.5 + rand.nextDouble() * 0.125 * dir.offsetX;
				if(dir.offsetY != 0)
					iy = y + 0.5F + dir.offsetY * 0.5 + rand.nextDouble() * 0.125 * dir.offsetY;
				if(dir.offsetZ != 0)
					iz = z + 0.5F + dir.offsetZ * 0.5 + rand.nextDouble() * 0.125 * dir.offsetZ;

				worldIn.spawnParticle(EnumParticleTypes.FLAME, ix, iy, iz, 0.0, 0.0, 0.0);
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, ix, iy, iz, 0.0, 0.0, 0.0);
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, ix, iy, iz, 0.0, 0.1, 0.0);
			}
		}
	}
}