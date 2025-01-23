package com.hbm.blocks.machine;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandler;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.TileEntityMachineAssembler;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MachineAssembler extends BlockContainer implements IMultiBlock {

	public static final PropertyInteger FACING = PropertyInteger.create("facing", 2, 5);

	private static boolean keepInventory;

	public MachineAssembler(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, 2));
		this.setCreativeTab(MainRegistry.machineTab);
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityMachineAssembler();
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.machine_assembler);
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
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase player, final ItemStack stack) {
		final int i = MathHelper.floor(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if(i == 0) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING, 5), 2);
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.assemblerDimensionEast)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.assemblerDimensionEast, ModBlocks.dummy_block_assembler);

				//
				DummyBlockAssembler.safeBreak = true;
				world.setBlockState(pos.add(-1, 0, 0), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(-1, 0, 0));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-1, 0, 1), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(-1, 0, 1));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(2, 0, 0), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te3 = world.getTileEntity(pos.add(2, 0, 0));
				if(te3 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(2, 0, 1), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te4 = world.getTileEntity(pos.add(2, 0, 1));
				if(te4 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockAssembler.safeBreak = false;
				//

			} else
				world.destroyBlock(pos, true);
		}
		if(i == 1) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING, 3), 2);
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.assemblerDimensionSouth)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.assemblerDimensionSouth, ModBlocks.dummy_block_assembler);

				//
				DummyBlockAssembler.safeBreak = true;
				world.setBlockState(pos.add(0, 0, -1), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(0, 0, -1));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-1, 0, -1), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(-1, 0, -1));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(0, 0, 2), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te3 = world.getTileEntity(pos.add(0, 0, 2));
				if(te3 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-1, 0, 2), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te4 = world.getTileEntity(pos.add(-1, 0, 2));
				if(te4 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockAssembler.safeBreak = false;
				//

			} else
				world.destroyBlock(pos, true);
		}
		if(i == 2) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING, 4), 2);
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.assemblerDimensionWest)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.assemblerDimensionWest, ModBlocks.dummy_block_assembler);

				//
				DummyBlockAssembler.safeBreak = true;
				world.setBlockState(pos.add(1, 0, 0), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(1, 0, 0));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(1, 0, -1), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(1, 0, -1));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-2, 0, 0), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te3 = world.getTileEntity(pos.add(-2, 0, 0));
				if(te3 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(-2, 0, -1), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te4 = world.getTileEntity(pos.add(-2, 0, -1));
				if(te4 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockAssembler.safeBreak = false;
				//

			} else
				world.destroyBlock(pos, true);
		}
		if(i == 3) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING, 2), 2);
			if(MultiblockHandler.checkSpace(world, pos, MultiblockHandler.assemblerDimensionNorth)) {
				MultiblockHandler.fillUp(world, pos, MultiblockHandler.assemblerDimensionNorth, ModBlocks.dummy_block_assembler);

				//
				DummyBlockAssembler.safeBreak = true;
				world.setBlockState(pos.add(0, 0, 1), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te = world.getTileEntity(pos.add(0, 0, 1));
				if(te instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(1, 0, 1), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te2 = world.getTileEntity(pos.add(1, 0, 1));
				if(te2 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(0, 0, -2), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te3 = world.getTileEntity(pos.add(0, 0, -2));
				if(te3 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				world.setBlockState(pos.add(1, 0, -2), ModBlocks.dummy_port_assembler.getDefaultState());
				final TileEntity te4 = world.getTileEntity(pos.add(1, 0, -2));
				if(te4 instanceof TileEntityDummy dummy) {
                    dummy.target = pos;
				}
				DummyBlockAssembler.safeBreak = false;
				//

			} else
				world.destroyBlock(pos, true);
		}
	}

	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
		if(!keepInventory) {
			final TileEntity tileentity = world.getTileEntity(pos);

			if(tileentity instanceof TileEntityMachineAssembler) {
				InventoryHelper.dropInventoryItems(world, pos, tileentity);

				world.updateComparatorOutputLevel(pos, this);
			}
		}

		super.breakBlock(world, pos, state);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(FACING);
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		if(meta >= 2 && meta <= 5)
			return this.getDefaultState().withProperty(FACING, meta);
		return this.getDefaultState().withProperty(FACING, 2);
	}
}
