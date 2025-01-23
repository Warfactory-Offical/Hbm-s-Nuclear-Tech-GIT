package com.hbm.blocks.bomb;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.bomb.TileEntityLaunchTable;
import com.hbm.tileentity.machine.TileEntityDummy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LaunchTable extends BlockContainer implements IMultiBlock, IBomb {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public LaunchTable(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityLaunchTable();
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
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.struct_launcher_core_large);
	}
	
	@Override
	public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
		return ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher_core_large);
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final TileEntityLaunchTable entity = (TileEntityLaunchTable) world.getTileEntity(pos);
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_launch_table, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		final EnumFacing e = placer.getHorizontalFacing().getOpposite();
		world.setBlockState(pos, state.withProperty(FACING, e));
		
		for(int k = -4; k <= 4; k++)
			for(int l = -4; l <= 4; l++)
				if(l != 0 && k != 0)
					if(!world.getBlockState(pos.add(k, 0, l)).getBlock().isReplaceable(world, pos.add(k, 0, l))) {
						world.destroyBlock(pos, true);
						return;
					}

		if (e == EnumFacing.NORTH) {
			
			for(int i = 1; i < 12; i++)
				world.setBlockState(pos.add(3, i, 0), Blocks.AIR.getDefaultState());
			
			for(int i = -4; i <= 4; i++)
				if(i != 0)
					placeDummy(world, pos.getX() + i, pos.getY(), pos.getZ(), pos, ModBlocks.dummy_plate_launch_table);
			
			for(int i = -4; i <= 4; i++)
				if(i != 0)
					placeDummy(world, pos.getX(), pos.getY(), pos.getZ() + i, pos, ModBlocks.dummy_port_launch_table);
		}
		if (e == EnumFacing.EAST) {
			for(int i = 1; i < 12; i++)
				world.setBlockState(pos.add(0, i, 3), Blocks.AIR.getDefaultState());
			
			for(int i = -4; i <= 4; i++)
				if(i != 0)
					placeDummy(world, pos.getX() + i, pos.getY(), pos.getZ(), pos, ModBlocks.dummy_port_launch_table);
			
			for(int i = -4; i <= 4; i++)
				if(i != 0)
					placeDummy(world, pos.getX(), pos.getY(), pos.getZ() + i, pos, ModBlocks.dummy_plate_launch_table);
		}
		if (e == EnumFacing.SOUTH) {
			for(int i = 1; i < 12; i++)
				world.setBlockState(pos.add(-3, i, 0), Blocks.AIR.getDefaultState());
			
			for(int i = -4; i <= 4; i++)
				if(i != 0)
					placeDummy(world, pos.getX() + i, pos.getY(), pos.getZ(), pos, ModBlocks.dummy_plate_launch_table);
			
			for(int i = -4; i <= 4; i++)
				if(i != 0)
					placeDummy(world, pos.getX(), pos.getY(), pos.getZ() + i, pos, ModBlocks.dummy_port_launch_table);
		}
		if (e == EnumFacing.WEST) {
			for(int i = 1; i < 12; i++)
				world.setBlockState(pos.add(0, i, -3), Blocks.AIR.getDefaultState());
			
			for(int i = -4; i <= 4; i++)
				if(i != 0)
					placeDummy(world, pos.getX() + i, pos.getY(), pos.getZ(), pos, ModBlocks.dummy_port_launch_table);
			
			for(int i = -4; i <= 4; i++)
				if(i != 0)
					placeDummy(world, pos.getX(), pos.getY(), pos.getZ() + i, pos, ModBlocks.dummy_plate_launch_table);
		}

		for(int i = -4; i <= 4; i++)
			for(int j = -4; j <= 4; j++)
				if(i != 0 && j != 0)
					placeDummy(world, pos.getX() + i, pos.getY(), pos.getZ() + j, pos, ModBlocks.dummy_port_launch_table);
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
	public void explode(final World world, final BlockPos pos) {
		final TileEntityLaunchTable entity = (TileEntityLaunchTable) world.getTileEntity(pos);
		
		if(entity.canLaunch())
			entity.launch();
	}

}
