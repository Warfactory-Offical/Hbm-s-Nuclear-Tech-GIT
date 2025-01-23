package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.TileEntityDummyPort;
import com.hbm.tileentity.machine.TileEntityMachineChemplant;

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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DummyBlockChemplant extends DummyOldBase {

	public static boolean safeBreak = false;

	public DummyBlockChemplant(final Material materialIn, final String s, final boolean port) {
		super(materialIn, s, port);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return this == ModBlocks.dummy_port_chemplant ? new TileEntityDummyPort() : new TileEntityDummy();
	}

	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
		if(!safeBreak) {
    		final TileEntity te = world.getTileEntity(pos);
    		if(te != null && te instanceof TileEntityDummy) {
    			final BlockPos abc = ((TileEntityDummy)te).target;
    			//world.getBlock(a, b, c).breakBlock(world, a, b, c, block, i);
    			if(!world.isRemote && abc != null)
    				world.destroyBlock(abc, true);
    		}
    	}
    	world.removeTileEntity(pos);
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
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
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.AIR;
	}
    
    @Override
    public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
    	return ItemStackUtil.itemStackFrom(Item.getItemFromBlock(ModBlocks.machine_chemplant));
    }
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
    		final TileEntity te = world.getTileEntity(pos);
    		if(te != null && te instanceof TileEntityDummy) {
    			final BlockPos abc = ((TileEntityDummy)te).target;
    			if(abc == null)
    				return false;
    			final TileEntityMachineChemplant entity = (TileEntityMachineChemplant) world.getTileEntity(abc);
    			if(entity != null)
    			{
    				player.openGui(MainRegistry.instance, ModBlocks.guiID_machine_chemplant, world, abc.getX(), abc.getY(), abc.getZ());
    			}
    		}
			return true;
		} else {
			return false;
		}
	}

}
