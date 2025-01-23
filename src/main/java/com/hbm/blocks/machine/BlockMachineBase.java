package com.hbm.blocks.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineBase extends BlockContainer {

	int guiID = -1;
	
	public BlockMachineBase(final Material materialIn, final int guiID, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.guiID = guiID;
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return null;
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(guiID == -1)
			return false;

		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			player.openGui(MainRegistry.instance, this.guiID, world, pos.getX(), pos.getY(), pos.getZ());
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
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		if(!rotatable())
			return;
		worldIn.setBlockState(pos, state.withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite()));
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		if(rotatable()){
			return new BlockStateContainer(this, BlockHorizontal.FACING);
		}
		return super.createBlockState();
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		if(!rotatable())
			return 0;
		return state.getValue(BlockHorizontal.FACING).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		if(!rotatable())
			return this.getDefaultState();
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(BlockHorizontal.FACING, enumfacing);
	}
	
	protected boolean rotatable(){
		return false;
	}
	
}
