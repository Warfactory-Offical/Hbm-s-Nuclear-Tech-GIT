package com.hbm.blocks.turret;

import com.hbm.blocks.BlockDummyable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class TurretBaseNT extends BlockDummyable {

	public TurretBaseNT(final Material materialIn, final String s){
		super(materialIn, s);
	}

	@Override
	public int[] getDimensions() {
		return new int[] { 0, 0, 1, 0, 1, 0 };
	}

	@Override
	public int getOffset() {
		return 0;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos bpos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			final int[] pos = this.findCore(world, bpos.getX(), bpos.getY(), bpos.getZ());

			if(pos == null)
				return false;
			
			openGUI(world, player, pos[0], pos[1], pos[2]);
			return true;
		} else {
			return false;
		}
	}
	
	public abstract void openGUI(World world, EntityPlayer player, int x, int y, int z);
	
}
