package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.tileentity.deco.TileEntityDecoBlockAlt;

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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DecoBlockAlt extends BlockContainer {

	public static PropertyDirection FACING = BlockHorizontal.FACING;
	
	public static final AxisAlignedBB STATUE_BOX = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 8*0.0625F, 1.0F);
	
	public DecoBlockAlt(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityDecoBlockAlt();
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.statue_elb);
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
			final ItemStack stack = player.getHeldItem(hand);
			if(stack != null)
			{
				if(this == ModBlocks.statue_elb)
				{
					if(stack.getItem() == ModItems.gun_revolver_cursed)
					{
						world.setBlockState(pos, ModBlocks.statue_elb_g.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);

                        if (!player.capabilities.isCreativeMode)
                        {
                        	stack.shrink(1);
                        }
						return true;
					}
				
					if(stack.getItem() == ModItems.watch)
					{
						world.setBlockState(pos, ModBlocks.statue_elb_w.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);

                        if (!player.capabilities.isCreativeMode)
                        {
                        	stack.shrink(1);
                        }
						return true;
					}
				}
				if(this == ModBlocks.statue_elb_g)
				{
					if(stack.getItem() == ModItems.watch)
					{
						world.setBlockState(pos, ModBlocks.statue_elb_f.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);

                        if (!player.capabilities.isCreativeMode)
                        {
                            stack.shrink(1);
                        }
						return true;
					}
				}
				if(this == ModBlocks.statue_elb_w)
				{
					if(stack.getItem() == ModItems.gun_revolver_cursed)
					{
						world.setBlockState(pos, ModBlocks.statue_elb_f.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);

                        if (!player.capabilities.isCreativeMode)
                        {
                            stack.shrink(1);
                        }
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return STATUE_BOX;
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
