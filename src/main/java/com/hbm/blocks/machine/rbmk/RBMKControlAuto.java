package com.hbm.blocks.machine.rbmk;

import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKControlAuto;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RBMKControlAuto extends RBMKBase {

	public RBMKControlAuto(final String s, final String c){
		super(s, c);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		
		if(meta >= offset)
			return new TileEntityRBMKControlAuto();
		return null;
	}
	
	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		return openInv(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, ModBlocks.guiID_rbmk_control_auto, hand);
	}
	
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state){
		return EnumBlockRenderType.MODEL;
	}
}
