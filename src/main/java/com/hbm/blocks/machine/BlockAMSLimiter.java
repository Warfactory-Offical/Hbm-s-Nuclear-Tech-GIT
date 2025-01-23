package com.hbm.blocks.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandler;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityAMSLimiter;
import com.hbm.tileentity.machine.TileEntityDummy;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

public class BlockAMSLimiter extends BlockContainer implements IMultiBlock {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockAMSLimiter(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityAMSLimiter();
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final TileEntityAMSLimiter entity = (TileEntityAMSLimiter) world.getTileEntity(pos);
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_ams_limiter, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		final EnumFacing e = placer.getHorizontalFacing().getOpposite().rotateY();

		world.setBlockState(pos, state.withProperty(FACING, e));
		if (e == EnumFacing.EAST) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.AMSLimiterDimensionEast)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.AMSLimiterDimensionEast, ModBlocks.dummy_block_ams_limiter);
				
				//
				DummyBlockAMSLimiter.safeBreak = true;
				world.setBlockState(pos.add(2, 0, 0), ModBlocks.dummy_port_ams_limiter.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(2, 0, 0));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-2, 0, 0), ModBlocks.dummy_port_ams_limiter.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(-2, 0, 0));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockAMSLimiter.safeBreak = false;
				//
				
			} else
				world.destroyBlock(pos, true);
		}
		if (e == EnumFacing.SOUTH) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.AMSLimiterDimensionSouth)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.AMSLimiterDimensionSouth, ModBlocks.dummy_block_ams_limiter);
				
				//
				DummyBlockAMSLimiter.safeBreak = true;
				world.setBlockState(pos.add(0, 0, 2), ModBlocks.dummy_port_ams_limiter.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(0, 0, 2));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(0, 0, -2), ModBlocks.dummy_port_ams_limiter.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(0, 0, -2));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockAMSLimiter.safeBreak = false;
				//
				
			} else
				world.destroyBlock(pos, true);
		}
		if (e == EnumFacing.WEST) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.AMSLimiterDimensionWest)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.AMSLimiterDimensionWest, ModBlocks.dummy_block_ams_limiter);
				
				//
				DummyBlockAMSLimiter.safeBreak = true;
				world.setBlockState(pos.add(2, 0, 0), ModBlocks.dummy_port_ams_limiter.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(2, 0, 0));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-2, 0, 0), ModBlocks.dummy_port_ams_limiter.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(-2, 0, 0));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockAMSLimiter.safeBreak = false;
				//
				
			} else
				world.destroyBlock(pos, true);
		}
		if (e == EnumFacing.NORTH) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.AMSLimiterDimensionNorth)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.AMSLimiterDimensionNorth, ModBlocks.dummy_block_ams_limiter);
				
				//
				DummyBlockAMSLimiter.safeBreak = true;
				world.setBlockState(pos.add(0, 0, 2), ModBlocks.dummy_port_ams_limiter.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(0, 0, 2));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(0, 0, -2), ModBlocks.dummy_port_ams_limiter.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(0, 0, -2));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockAMSLimiter.safeBreak = false;
				//
				
			} else
				world.destroyBlock(pos, true);
		}
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
	public boolean isFullCube(final IBlockState state) {
		return false;
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
