package com.hbm.blocks.bomb;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.bomb.TileEntityCompactLauncher;
import com.hbm.tileentity.machine.TileEntityDummy;

import net.minecraft.block.Block;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CompactLauncher extends BlockContainer implements IMultiBlock, IBomb {

	public static final AxisAlignedBB COMPACT_BOX = new AxisAlignedBB(0, 1, 0, 1, 1, 1);
	
	public CompactLauncher(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityCompactLauncher();
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
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.struct_launcher_core);
	}
	
	@Override
	public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
		return ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher_core);
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final TileEntityCompactLauncher entity = (TileEntityCompactLauncher) world.getTileEntity(pos);
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_compact_launcher, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		final TileEntityCompactLauncher te = (TileEntityCompactLauncher) world.getTileEntity(pos);
		
		if(!(world.getBlockState(pos.add(1, 0, 1)).getMaterial().isReplaceable() &&
				world.getBlockState(pos.add(1, 0, 0)).getMaterial().isReplaceable() &&
				world.getBlockState(pos.add(1, 0, -1)).getMaterial().isReplaceable() &&
				world.getBlockState(pos.add(0, 0, -1)).getMaterial().isReplaceable() &&
				world.getBlockState(pos.add(-1, 0, -1)).getMaterial().isReplaceable() &&
				world.getBlockState(pos.add(-1, 0, 0)).getMaterial().isReplaceable() &&
				world.getBlockState(pos.add(-1, 0, 1)).getMaterial().isReplaceable() &&
				world.getBlockState(pos.add(0, 0, 1)).getMaterial().isReplaceable())) {
			world.destroyBlock(pos, true);
			return;
		}

		final int x = pos.getX();
		final int y = pos.getY();
		final int z = pos.getZ();
		
		placeDummy(world, x + 1, y, z + 1, pos, ModBlocks.dummy_port_compact_launcher);
		placeDummy(world, x + 1, y, z, pos, ModBlocks.dummy_plate_compact_launcher);
		placeDummy(world, x + 1, y, z - 1, pos, ModBlocks.dummy_port_compact_launcher);
		placeDummy(world, x, y, z - 1, pos, ModBlocks.dummy_plate_compact_launcher);
		placeDummy(world, x - 1, y, z - 1, pos, ModBlocks.dummy_port_compact_launcher);
		placeDummy(world, x - 1, y, z, pos, ModBlocks.dummy_plate_compact_launcher);
		placeDummy(world, x - 1, y, z + 1, pos, ModBlocks.dummy_port_compact_launcher);
		placeDummy(world, x, y, z + 1, pos, ModBlocks.dummy_plate_compact_launcher);
		
		super.onBlockPlacedBy(world, pos, state, placer, stack);
	}
	
	private void placeDummy(final World world, final int x, final int y, final int z, final BlockPos target, final Block block) {
		final BlockPos pos = new BlockPos(x, y, z);
		world.setBlockState(pos, block.getDefaultState());
		
		final TileEntity te = world.getTileEntity(pos);
		
		if(te instanceof TileEntityDummy dummy) {
            dummy.target = target;
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return COMPACT_BOX;
	}
	
	@Override
	public void explode(final World world, final BlockPos pos) {
		final TileEntityCompactLauncher entity = (TileEntityCompactLauncher) world.getTileEntity(pos);
		if(entity.canLaunch())
			entity.launch();
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
		super.breakBlock(worldIn, pos, state);
	}

}
