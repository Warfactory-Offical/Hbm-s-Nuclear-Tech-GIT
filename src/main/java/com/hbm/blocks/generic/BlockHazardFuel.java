package com.hbm.blocks.generic;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHazardFuel extends BlockHazard {

	private final int burntime;
	public int encouragement;
	public int flammability;
	
	public BlockHazardFuel(final Material m, final String s, final int en, final int flam, final int burntime){
		super(m, s);
		this.encouragement = en;
		this.flammability = flam;
		this.burntime = burntime;
	}

	public int getBurnTime(){
		return burntime;
	}
	
	@Override
	public int getFlammability(final IBlockAccess world, final BlockPos pos, final EnumFacing face){
		return flammability;
	}
	
	@Override
	public int getFireSpreadSpeed(final IBlockAccess world, final BlockPos pos, final EnumFacing face){
		return encouragement;
	}

	@Override
	public boolean isFlammable(final IBlockAccess world, final BlockPos pos, final EnumFacing face){
		return true;
	}

	@Override
	public boolean isFireSource(final World world, final BlockPos pos, final EnumFacing side){
		return true;
	}
}
