package com.hbm.blocks.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.lib.ForgeDirection;
import com.hbm.interfaces.IDummy;

import api.hbm.energy.IEnergyConnectorBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;

public abstract class DummyOldBase extends BlockContainer implements IDummy, IEnergyConnectorBlock {

	public boolean port = false;

	public DummyOldBase(final Material mat, final String s, final boolean port) {
		super(mat);
		this.port = port;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(null);
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override 
	public boolean canConnect(final IBlockAccess world, final BlockPos pos, final ForgeDirection dir){
		return port; 
	}
}
