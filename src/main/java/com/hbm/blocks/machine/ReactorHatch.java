package com.hbm.blocks.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityMachineReactorLarge;
import com.hbm.tileentity.machine.TileEntityReactorHatch;

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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ReactorHatch extends BlockContainer {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public ReactorHatch(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityReactorHatch();
	}

	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final EnumFacing e = state.getValue(FACING);
			if(e == EnumFacing.NORTH)
			{
				if(world.getTileEntity(pos.add(0, 0, 2)) instanceof TileEntityMachineReactorLarge)
				{
					if(((TileEntityMachineReactorLarge)world.getTileEntity(pos.add(0, 0, 2))).checkBody())
					{
						player.openGui(MainRegistry.instance, ModBlocks.guiID_reactor_multiblock, world, pos.getX(), pos.getY(), pos.getZ() + 2);
					} else {
						player.sendMessage(new TextComponentTranslation("chat.bigreactor.structurebad"));
					}
				} else {
					player.sendMessage(new TextComponentTranslation("chat.bigreactor.corebad"));
				}
			}
			if(e == EnumFacing.SOUTH)
			{
				if(world.getTileEntity(pos.add(0, 0, -2)) instanceof TileEntityMachineReactorLarge)
				{
					if(((TileEntityMachineReactorLarge)world.getTileEntity(pos.add(0, 0, -2))).checkBody())
					{
						player.openGui(MainRegistry.instance, ModBlocks.guiID_reactor_multiblock, world, pos.getX(), pos.getY(), pos.getZ() - 2);
					} else {
						player.sendMessage(new TextComponentTranslation("chat.bigreactor.structurebad"));
					}
				} else {
					player.sendMessage(new TextComponentTranslation("chat.bigreactor.corebad"));
				}
			}
			if(e == EnumFacing.WEST)
			{
				if(world.getTileEntity(pos.add(2, 0, 0)) instanceof TileEntityMachineReactorLarge)
				{
					if(((TileEntityMachineReactorLarge)world.getTileEntity(pos.add(2, 0, 0))).checkBody())
					{
						player.openGui(MainRegistry.instance, ModBlocks.guiID_reactor_multiblock, world, pos.getX() + 2, pos.getY(), pos.getZ());
					} else {
						player.sendMessage(new TextComponentTranslation("chat.bigreactor.structurebad"));
					}
				} else {
					player.sendMessage(new TextComponentTranslation("chat.bigreactor.corebad"));
				}
			}
			if(e == EnumFacing.EAST)
			{
				if(world.getTileEntity(pos.add(-2, 0, 0)) instanceof TileEntityMachineReactorLarge)
				{
					if(((TileEntityMachineReactorLarge)world.getTileEntity(pos.add(-2, 0, 0))).checkBody())
					{
						player.openGui(MainRegistry.instance, ModBlocks.guiID_reactor_multiblock, world, pos.getX() - 2, pos.getY(), pos.getZ());
					} else {
						player.sendMessage(new TextComponentTranslation("chat.bigreactor.structurebad"));
					}
				} else {
					player.sendMessage(new TextComponentTranslation("chat.bigreactor.corebad"));
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
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
