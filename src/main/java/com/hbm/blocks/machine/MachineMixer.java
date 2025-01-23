package com.hbm.blocks.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.tileentity.machine.TileEntityMachineMixer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class MachineMixer extends BlockDummyable {

	public MachineMixer(final Material mat, final String s) {
		super(mat, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		
		if(meta >= 12)
			return new TileEntityMachineMixer();
		
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {2, 0, 0, 0, 0, 0};
	}

	@Override
	public int getOffset() {
		return 0;
	}
	
	@Override
    public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
        return standardOpenBehavior(world, pos.getX(), pos.getY(), pos.getZ(), player, 0);
    }
}
