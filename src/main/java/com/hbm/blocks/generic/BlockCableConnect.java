package com.hbm.blocks.generic;

import com.hbm.blocks.BlockBase;
import com.hbm.lib.ForgeDirection;

import api.hbm.energy.IEnergyConnectorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;

public class BlockCableConnect extends BlockBase implements IEnergyConnectorBlock {

	public BlockCableConnect(final Material material, final String s) {
		super(material, s);
	}

	@Override 
	public boolean canConnect(final IBlockAccess world, final BlockPos pos, final ForgeDirection dir){
		return true; 
	}
}
