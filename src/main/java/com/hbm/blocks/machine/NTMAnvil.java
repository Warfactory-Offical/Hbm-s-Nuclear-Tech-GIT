package com.hbm.blocks.machine;

import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NTMAnvil extends BlockFalling {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public final int tier;

	public NTMAnvil(final Material mat, final int tier, final String s) {
		super(mat);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setSoundType(SoundType.ANVIL);
		this.setHardness(5.0F);
		this.setResistance(100.0F);
		this.tier = tier;
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state, final IBlockAccess world, final BlockPos pos){
		return false;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isFullBlock(final IBlockState state){
		return false;
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {

			player.openGui(MainRegistry.instance, ModBlocks.guiID_anvil, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack){
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		final EnumFacing e = placer.getHorizontalFacing().getOpposite();
		worldIn.setBlockState(pos, state.withProperty(FACING, e));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		AxisAlignedBB bb = NULL_AABB;
		final EnumFacing.Axis axis = state.getValue(FACING).getAxis();
		if(axis == EnumFacing.Axis.X){
			bb = new AxisAlignedBB(0.25F, 0.0F, 0.0F, 0.75F, 0.75F, 1.0F);
		} else if(axis == EnumFacing.Axis.Z){
			bb = new AxisAlignedBB(0.0F, 0.0F, 0.25F, 1.0F, 0.75F, 0.75F);
		}
		return bb;
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

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		tooltip.add("§6Tier: "+this.tier);
		super.addInformation(stack, player, tooltip, advanced);
	}
}