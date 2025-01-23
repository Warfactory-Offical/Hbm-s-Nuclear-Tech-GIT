package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandler;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.oil.TileEntityMachinePumpjack;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MachinePumpjack extends BlockContainer implements IMultiBlock {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public MachinePumpjack(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityMachinePumpjack();
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.machine_pumpjack);
	}

	@Override
	public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
		return ItemStackUtil.itemStackFrom(ModBlocks.machine_pumpjack);
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
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			final TileEntity te = world.getTileEntity(pos);
			if(te instanceof TileEntityMachinePumpjack) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_machine_pumpjack, world, pos.getX(), pos.getY(), pos.getZ());

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
		final int i = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		final EnumFacing e = placer.getHorizontalFacing().getOpposite();
		world.setBlockState(pos, state.withProperty(FACING, e));

		if(i == 0) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.pumpjackDimensionEast)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.pumpjackDimensionEast, ModBlocks.dummy_block_pumpjack);

				//
				DummyBlockPumpjack.safeBreak = true;
				world.setBlockState(pos.add(-2, 0, 1), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(-2, 0, 1));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-2, 0, -1), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(-2, 0, -1));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-4, 0, 1), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te3 = world.getTileEntity(pos.add(-4, 0, 1));
				if(te3 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-4, 0, -1), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te4 = world.getTileEntity(pos.add(-4, 0, -1));
				if(te4 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockPumpjack.safeBreak = false;
				//

			} else
				world.destroyBlock(pos, true);
		}
		if(i == 1) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.pumpjackDimensionSouth)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.pumpjackDimensionSouth, ModBlocks.dummy_block_pumpjack);

				//
				DummyBlockPumpjack.safeBreak = true;
				world.setBlockState(pos.add(1, 0, -2), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(1, 0, -2));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-1, 0, -2), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(-1, 0, -2));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(1, 0, -4), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te3 = world.getTileEntity(pos.add(1, 0, -4));
				if(te3 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-1, 0, -4), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te4 = world.getTileEntity(pos.add(-1, 0, -4));
				if(te4 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockPumpjack.safeBreak = false;
				//

			} else
				world.destroyBlock(pos, true);
		}
		if(i == 2) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.pumpjackDimensionWest)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.pumpjackDimensionWest, ModBlocks.dummy_block_pumpjack);

				//
				DummyBlockPumpjack.safeBreak = true;
				world.setBlockState(pos.add(2, 0, 1), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(2, 0, 1));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(2, 0, -1), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(2, 0, -1));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(4, 0, 1), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te3 = world.getTileEntity(pos.add(4, 0, 1));
				if(te3 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(4, 0, -1), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te4 = world.getTileEntity(pos.add(4, 0, -1));
				if(te4 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockPumpjack.safeBreak = false;
				//

			} else
				world.destroyBlock(pos, true);
		}
		if(i == 3) {
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.pumpjackDimensionNorth)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.pumpjackDimensionNorth, ModBlocks.dummy_block_pumpjack);

				//
				DummyBlockPumpjack.safeBreak = true;
				world.setBlockState(pos.add(1, 0, 2), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(1, 0, 2));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-1, 0, 2), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(-1, 0, 2));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(1, 0, 4), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te3 = world.getTileEntity(pos.add(1, 0, 4));
				if(te3 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-1, 0, 4), ModBlocks.dummy_port_pumpjack.getDefaultState());
				final TileEntity te4 = world.getTileEntity(pos.add(-1, 0, 4));
				if(te4 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockPumpjack.safeBreak = false;
				//

			} else
				world.destroyBlock(pos, true);
		}
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

		if(enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

}
