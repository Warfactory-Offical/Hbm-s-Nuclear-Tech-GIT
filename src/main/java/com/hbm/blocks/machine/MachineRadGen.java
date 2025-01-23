package com.hbm.blocks.machine;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandler;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.TileEntityMachineRadGen;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MachineRadGen extends BlockContainer implements IMultiBlock {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public MachineRadGen(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityMachineRadGen();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.machine_radgen);
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
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		final EnumFacing e = placer.getHorizontalFacing().getOpposite();
		world.setBlockState(pos, state.withProperty(FACING, e));
		if (e == EnumFacing.EAST) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.radGenDimensionEast)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.radGenDimensionEast, ModBlocks.dummy_block_radgen);
				
				//
				DummyBlockRadGen.safeBreak = true;
				world.setBlockState(pos.add(0, 0, 4), ModBlocks.dummy_port_radgen.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(0, 0, 4));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockRadGen.safeBreak = false;
				//
				
			} else
				world.destroyBlock(pos, true);
		}
		if (e == EnumFacing.SOUTH) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.radGenDimensionSouth)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.radGenDimensionSouth, ModBlocks.dummy_block_radgen);
				
				//
				DummyBlockRadGen.safeBreak = true;
				world.setBlockState(pos.add(-4, 0, 0), ModBlocks.dummy_port_radgen.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(-4, 0, 0));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockRadGen.safeBreak = false;
				//
				
			} else
				world.destroyBlock(pos, true);
		}
		if (e == EnumFacing.WEST) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.radGenDimensionWest)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.radGenDimensionWest, ModBlocks.dummy_block_radgen);
				
				//
				DummyBlockRadGen.safeBreak = true;
				world.setBlockState(pos.add(0, 0, -4), ModBlocks.dummy_port_radgen.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(0, 0, -4));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockRadGen.safeBreak = false;
				//
				
			} else
				world.destroyBlock(pos, true);
		}
		if (e == EnumFacing.NORTH) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.radGenDimensionNorth)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.radGenDimensionNorth, ModBlocks.dummy_block_radgen);
				
				//
				DummyBlockRadGen.safeBreak = true;
				world.setBlockState(pos.add(4, 0, 0), ModBlocks.dummy_port_radgen.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(4, 0, 0));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockRadGen.safeBreak = false;
				//
				
			} else
				world.destroyBlock(pos, true);
		}
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final TileEntityMachineRadGen entity = (TileEntityMachineRadGen) world.getTileEntity(pos);
    		if(entity != null)
    		{
    			player.openGui(MainRegistry.instance, ModBlocks.guiID_radgen, world, pos.getX(), pos.getY(), pos.getZ());
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
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(FACING).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	
	
	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn)
	{
	   return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

}
