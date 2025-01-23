package com.hbm.blocks.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyInventory;
import com.hbm.tileentity.machine.TileEntityMachineReactor;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MachineReactor extends BlockDummyable {

	public MachineReactor(final Material m, final String s) {
		super(m, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(meta >= 12)
			return new TileEntityMachineReactor();
		return new TileEntityProxyInventory();
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos1, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final int[] pos = this.findCore(world, pos1.getX(), pos1.getY(), pos1.getZ());

			if(pos == null)
				return false;

			final TileEntityMachineReactor entity = (TileEntityMachineReactor) world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_reactor, world, pos[0], pos[1], pos[2]);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int[] getDimensions() {
		return new int[] { 2, 0, 0, 0, 0, 0 };
	}

	@Override
	public int getOffset() {
		return 0;
	}
	
}
