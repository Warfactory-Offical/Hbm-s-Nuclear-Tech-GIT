package com.hbm.blocks.network.energy;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.tileentity.network.energy.TileEntityCableSwitch;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CableDetector extends BlockContainer {

	public static final PropertyBool STATE = PropertyBool.create("state");
	
	public CableDetector(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityCableSwitch();
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		final boolean on = world.isBlockPowered(pos);
		if(on) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(STATE, true), 2);
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.reactorStart, SoundCategory.BLOCKS, 1.0F, 0.3F);
		} else {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(STATE, false), 2);
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, STATE);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(STATE).booleanValue() ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState().withProperty(STATE, meta == 1);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}
