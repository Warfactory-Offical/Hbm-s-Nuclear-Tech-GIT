package com.hbm.blocks.test;

import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.TileEntityKeypadBase;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KeypadTest extends BlockContainer {

	public KeypadTest(final Material m, final String s) {
		super(m);
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityKeypadBase();
	}

	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite()));
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(final IBlockState state, final World worldIn, final BlockPos pos) {
		final TileEntityKeypadBase te = (TileEntityKeypadBase) worldIn.getTileEntity(pos);
		final AxisAlignedBB key = te.keypad.client().rayTrace(pos);
		if(key != null) {
			return key;
		}
		return super.getSelectedBoundingBox(state, worldIn, pos);
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		final TileEntityKeypadBase te = (TileEntityKeypadBase) worldIn.getTileEntity(pos);
		if(worldIn.isRemote) {
			if(te.keypad.client().isPlayerMouseingOver(pos)) {
				return te.keypad.client().playerClick(pos);
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHorizontal.FACING);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		if(meta < 2 || meta > 5)
			return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.byIndex(2));
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.byIndex(meta));
	}
}
