package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandler;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.oil.TileEntityMachineOilWell;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

public class MachineOilWell extends BlockContainer implements IMultiBlock {

	public MachineOilWell(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityMachineOilWell();
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.machine_well);
	}
	
	@Override
	public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
		return ItemStackUtil.itemStackFrom(ModBlocks.machine_well);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
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
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final TileEntityMachineOilWell entity = (TileEntityMachineOilWell) world.getTileEntity(pos);
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_machine_well, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.wellDimension)) {
			MultiblockHandler.fillUp(world, pos, MultiblockHandler.wellDimension, ModBlocks.dummy_block_well);

			DummyBlockWell.safeBreak = true;
			world.setBlockState(pos.add(0, 0, 1), ModBlocks.dummy_port_well.getDefaultState());
			final TileEntity te = world.getTileEntity(pos.add(0, 0, 1));
			if(te instanceof TileEntityDummy dummy) {
                dummy.target = pos;
			}
			world.setBlockState(pos.add(0, 0, -1), ModBlocks.dummy_port_well.getDefaultState());
			final TileEntity te2 = world.getTileEntity(pos.add(0, 0, -1));
			if(te instanceof TileEntityDummy) {
				final TileEntityDummy dummy = (TileEntityDummy)te2;
				dummy.target = pos;
			}
			world.setBlockState(pos.add(1, 0, 0), ModBlocks.dummy_port_well.getDefaultState());
			final TileEntity te3 = world.getTileEntity(pos.add(1, 0, 0));
			if(te3 instanceof TileEntityDummy dummy) {
                dummy.target = pos;
			}
			world.setBlockState(pos.add(-1, 0, 0), ModBlocks.dummy_port_well.getDefaultState());
			final TileEntity te4 = world.getTileEntity(pos.add(-1, 0, 0));
			if(te4 instanceof TileEntityDummy dummy) {
                dummy.target = pos;
			}
			DummyBlockWell.safeBreak = false;
			
		} else
			world.destroyBlock(pos, true);
	}

}
