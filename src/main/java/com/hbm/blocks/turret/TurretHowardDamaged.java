package com.hbm.blocks.turret;

import com.hbm.blocks.BlockDummyable;
import com.hbm.tileentity.turret.TileEntityTurretHowardDamaged;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TurretHowardDamaged extends BlockDummyable {

	public TurretHowardDamaged(final Material materialIn, final String s){
		super(materialIn, s);
	}
	
	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta){
		if(meta >= 12)
			return new TileEntityTurretHowardDamaged();
		return null;
	}
	
	@Override
	public int[] getDimensions(){
		return new int[] { 0, 0, 1, 0, 1, 0 };
	}
	
	@Override
	public int getOffset(){
		return 0;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

}
