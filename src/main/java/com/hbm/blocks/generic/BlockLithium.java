package com.hbm.blocks.generic;

import java.util.List;
import java.util.Random;

import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;
import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLithium extends Block implements IItemHazard {

	ItemHazardModule module;

	public BlockLithium(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.module = new ItemHazardModule();
		this.addHydroReactivity();

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public ItemHazardModule getModule() {
		return module;
	}

	private boolean touchesWater(final World world, final int x, final int y, final int z) {

		if(world.isRemote)
			return false;

		return world.getBlockState(new BlockPos(x + 1, y, z)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x - 1, y, z)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x, y + 1, z)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x, y - 1, z)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x, y, z + 1)).getMaterial() == Material.WATER || 
				world.getBlockState(new BlockPos(x, y, z - 1)).getMaterial() == Material.WATER;
	}

	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if(touchesWater(world, pos.getX(), pos.getY(), pos.getZ())) {
			world.destroyBlock(pos, false);
			world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 15, false, true);
		}
	}

	@Override
	public void onBlockAdded(final World world, final BlockPos pos, final IBlockState state) {
		if(touchesWater(world, pos.getX(), pos.getY(), pos.getZ())) {
			world.destroyBlock(pos, false);
			world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 15, false, true);
		}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		tooltip.add("It's not my fault you didn't pay");
		tooltip.add("attention in chemistry class.");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final IBlockState stateIn, final World world, final BlockPos pos, final Random rand) {
		if(world.isRainingAt(pos.up())) {

			final float ox = rand.nextFloat();
			final float oz = rand.nextFloat();

			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + ox, pos.getY() + 1, pos.getZ() + oz, 0.0D, 0.0D, 0.0D);
		}
	}
}
