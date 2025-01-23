package com.hbm.blocks.turret;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.turret.TileEntityTurretFritz;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TurretFritz extends TurretBaseNT {

	public TurretFritz(final Material materialIn, final String s){
		super(materialIn, s);
	}
	
	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta){
		if(meta >= 12)
			return new TileEntityTurretFritz();
		return new TileEntityProxyCombo(true, true, false);
	}
	
	@Override
	public void openGUI(final World world, final EntityPlayer player, final int x, final int y, final int z){
		player.openGui(MainRegistry.instance, ModBlocks.guiID_fritz, world, x, y, z);
	}
}
