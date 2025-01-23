package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityMachineArcFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MachineArcFurnace extends BlockContainer {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool RODS = PropertyBool.create("rods");

	private final boolean isActive;
	private static boolean keepInventory;

	public MachineArcFurnace(final Material materialIn, final boolean active, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		isActive = active;

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityMachineArcFurnace();
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.machine_arc_furnace_off);
	}

	@Override
	public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
		return ItemStackUtil.itemStackFrom(ModBlocks.machine_arc_furnace_off);
	}

	@Override
	public void onBlockAdded(final World worldIn, final BlockPos pos, final IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(final World worldIn, final BlockPos pos, final IBlockState state) {
		if(!worldIn.isRemote) {
			final IBlockState iblockstate = worldIn.getBlockState(pos.north());
			final IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			final IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			final IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = state.getValue(FACING);

			if(enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if(enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if(enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if(enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(RODS, false), 2);
		}
	}

	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		if(stack.hasDisplayName()) {
			final TileEntity tileentity = worldIn.getTileEntity(pos);

			if(tileentity instanceof TileEntityMachineArcFurnace) {
				((TileEntityMachineArcFurnace) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			final TileEntityMachineArcFurnace entity = (TileEntityMachineArcFurnace) world.getTileEntity(pos);
			if(entity != null) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_machine_arc, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}

	public static void updateBlockState(final boolean isProcessing, final World world, final BlockPos pos) {
		final EnumFacing e = world.getBlockState(pos).getValue(FACING);
		final boolean b = world.getBlockState(pos).getValue(RODS);
		final TileEntity entity = world.getTileEntity(pos);
		keepInventory = true;

		if(isProcessing && world.getBlockState(pos).getBlock() != ModBlocks.machine_arc_furnace_on) {
			world.setBlockState(pos, ModBlocks.machine_arc_furnace_on.getDefaultState().withProperty(FACING, e).withProperty(RODS, b));
		} else if(!isProcessing && world.getBlockState(pos).getBlock() != ModBlocks.machine_arc_furnace_off) {
			world.setBlockState(pos, ModBlocks.machine_arc_furnace_off.getDefaultState().withProperty(FACING, e).withProperty(RODS, b));
		}

		keepInventory = false;

		if(entity != null) {
			entity.validate();
			world.setTileEntity(pos, entity);
		}
	}

	//Drillgon200: Why does it work now.
	public static boolean updateBlockRods(final boolean hasRods, final World world, final BlockPos pos) {
		boolean returnVal = false;
		final boolean b = world.getBlockState(pos).getValue(RODS);
		final EnumFacing e = world.getBlockState(pos).getValue(FACING);
		final Block block = world.getBlockState(pos).getBlock();
		final TileEntity entity = world.getTileEntity(pos);
		keepInventory = true;
		
		if(hasRods && !b) {
			world.setBlockState(pos, block.getDefaultState().withProperty(FACING, e).withProperty(RODS, hasRods));
			returnVal = true;
		} else if(!hasRods && b) {
			world.setBlockState(pos, block.getDefaultState().withProperty(FACING, e).withProperty(RODS, hasRods));
			returnVal = true;
		}

		keepInventory = false;
		if(entity != null && world.getTileEntity(pos) != entity) {
			entity.validate();
			world.setTileEntity(pos, entity);
		}
		return returnVal;
	}

	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		if(!keepInventory)
			InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final IBlockState stateIn, final World worldIn, final BlockPos pos, final Random rand) {
		if(isActive) {
			final EnumFacing e = worldIn.getBlockState(pos).getValue(FACING);
			final float f = pos.getX() + 0.5F;
			final float f1 = pos.getY() + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
			final float f2 = pos.getZ() + 0.5F;
			final float f3 = 0.52F;
			final float f4 = rand.nextFloat() * 0.6F - 0.3F;

			if(e == EnumFacing.WEST) {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			} else if(e == EnumFacing.EAST) {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			} else if(e == EnumFacing.NORTH) {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			} else if(e == EnumFacing.SOUTH) {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(RODS, false);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, RODS);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		int meta = state.getValue(FACING).getIndex() << 1;
		meta += state.getValue(RODS) ? 1 : 0;
		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		final boolean rods = (meta & 1) == 1;
		meta = meta >> 1;
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

		if(enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(RODS, rods);
	}

	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

}
