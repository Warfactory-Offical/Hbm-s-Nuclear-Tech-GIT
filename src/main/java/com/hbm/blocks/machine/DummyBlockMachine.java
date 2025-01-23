package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.TileEntityDummyPort;

import net.minecraft.block.Block;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DummyBlockMachine extends DummyOldBase {

	public static boolean safeBreak = false;
	
	private int id;
	private Block drop;
	float oX = 0;
	float oY = 0;
	float oZ = 0;
	float dX = 1;
	float dY = 1;
	float dZ = 1;
	public AxisAlignedBB dummy_BB = FULL_BLOCK_AABB;
	
	public DummyBlockMachine(final Material materialIn, final String s, final boolean port) {
		super(materialIn, s, port);
	}
	
	public DummyBlockMachine(final Material materialIn, final String s, final boolean port, final int id, final Block drop) {
		super(materialIn, s, port);
		this.id = id;
		this.drop = drop;
	}
	
	public DummyBlockMachine setBounds(float oX, float oY, float oZ, float dX, float dY, float dZ) {

		oX *= 0.0625F;
		oY *= 0.0625F;
		oZ *= 0.0625F;
		dX *= 0.0625F;
		dY *= 0.0625F;
		dZ *= 0.0625F;
		this.dummy_BB = new AxisAlignedBB(oX, oY, oZ, dX, dY, dZ);
		return this;
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return port ? new TileEntityDummyPort() : new TileEntityDummy();
	}
	
	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
    		final TileEntity te = world.getTileEntity(pos);
    		if(te != null && te instanceof TileEntityDummy) {
    		
    			if(!world.isRemote)
    				world.destroyBlock(((TileEntityDummy)te).target, true);
    		}
    	world.removeTileEntity(pos);
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state) {
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
		return ItemStackUtil.itemStackFrom(Item.getItemFromBlock(drop));
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
    			final int a = ((TileEntityDummy)te).target.getX();
    			final int b = ((TileEntityDummy)te).target.getY();
    			final int c = ((TileEntityDummy)te).target.getZ();
    			
    			if(te != null)
    			{
    				player.openGui(MainRegistry.instance, id, world, a, b, c);
    			}
    		}
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return this.dummy_BB;
	}

}
