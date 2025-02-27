package com.hbm.blocks.machine.rbmk;

import com.hbm.blocks.BlockDummyable;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKCraneConsole;
import com.hbm.lib.ForgeDirection;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class RBMKCraneConsole extends BlockDummyable {

	public RBMKCraneConsole(final String s) {
		super(Material.IRON, s);
		this.setHardness(3F);
		this.setResistance(30F);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(meta >= offset)
			return new TileEntityRBMKCraneConsole();
		return null;
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(final IBlockState state) {
		return false;
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
	public boolean shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side) {
		return false;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {1, 0, 0, 0, 1, 1};
	}

	@Override
	public int getOffset() {
		return 1;
	}

	@Override
	public void fillSpace(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		super.fillSpace(world, x, y, z, dir, o);

		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y, z + dir.offsetZ * o, new int[] {0, 0, 0, 1, 1, 1}, this, dir);
	}

	@Override
	protected boolean checkRequirement(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {0, 0, 0, 1, 1, 1}, x, y, z, dir))
			return false;
		
		return super.checkRequirement(world, x, y, z, dir, o);
	}
}
