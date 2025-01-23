package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IDummy;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.TileEntityMachineCentrifuge;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DummyBlockCentrifuge extends BlockContainer implements IDummy {

	public static boolean safeBreak = false;
	
	public DummyBlockCentrifuge(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityDummy();
	}
	
	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
		if(!safeBreak) {
    		final TileEntity te = world.getTileEntity(pos);
    		if(te != null && te instanceof TileEntityDummy) {
    			if(!world.isRemote)
    				world.destroyBlock(((TileEntityDummy)te).target, true);
    		}
    	}
    	world.removeTileEntity(pos);
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.AIR;
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		return false;
	}
	@Override
	public boolean shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side) {
		return false;
	}
	
	@Override
	public ItemStack getItem(final World worldIn, final BlockPos pos, final IBlockState state) {
		return ItemStackUtil.itemStackFrom(ModBlocks.machine_centrifuge);
	}
	
	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(worldIn.isRemote)
		{
			return true;
		} else if(!playerIn.isSneaking())
		{
    		final TileEntity te = worldIn.getTileEntity(pos);
    		if(te != null && te instanceof TileEntityDummy) {
    			final int a = ((TileEntityDummy)te).target.getX();
    			final int b = ((TileEntityDummy)te).target.getY();
    			final int c = ((TileEntityDummy)te).target.getZ();
    			
    			final TileEntityMachineCentrifuge entity = (TileEntityMachineCentrifuge) worldIn.getTileEntity(((TileEntityDummy)te).target);
    			if(entity != null)
    			{
    				playerIn.openGui(MainRegistry.instance, ModBlocks.guiID_centrifuge, worldIn, a, b, c);
    			}
    		}
			return true;
		} else {
			return false;
		}
	}
}
