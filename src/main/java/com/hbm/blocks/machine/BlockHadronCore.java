package com.hbm.blocks.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityHadron;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
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
import net.minecraft.world.World;

public class BlockHadronCore extends BlockContainer {

	public static final PropertyDirection FACING = BlockDirectional.FACING;
	
	public BlockHadronCore(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityHadron();
	}
	
	@Override
	public Block setSoundType(final SoundType sound) {
		return super.setSoundType(sound);
	}
	
	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {

			final TileEntityHadron entity = (TileEntityHadron) world.getTileEntity(pos);
			if(entity != null) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_hadron, world, pos.getX(), pos.getY(), pos.getZ());
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
		final EnumFacing enumfacing = EnumFacing.byIndex(meta);
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
