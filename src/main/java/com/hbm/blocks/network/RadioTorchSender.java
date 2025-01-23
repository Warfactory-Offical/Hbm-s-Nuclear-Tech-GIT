package com.hbm.blocks.network;

import com.hbm.main.MainRegistry;
import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.network.TileEntityRadioTorchSender;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RadioTorchSender extends BlockContainer {

	public RadioTorchSender(final String s) {
		super(Material.IRON);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityRadioTorchSender();
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking())	{
			final TileEntityRadioTorchSender entity = (TileEntityRadioTorchSender) world.getTileEntity(pos);
			if(entity != null){
				player.openGui(MainRegistry.instance, ModBlocks.guiID_radio_torch_sender, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state){
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean getWeakChanges(final IBlockAccess world, final BlockPos pos){
		return true;
	}
}
