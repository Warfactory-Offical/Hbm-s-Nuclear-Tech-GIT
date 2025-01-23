package com.hbm.blocks.generic;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.control_panel.ControlEvent;
import com.hbm.inventory.control_panel.ControlEventSystem;
import com.hbm.inventory.control_panel.DataValueFloat;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.tileentity.machine.TileEntityBMPowerBox;

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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BMPowerBox extends BlockContainer {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool IS_ON = PropertyBool.create("is_on");
	
	public BMPowerBox(final Material materialIn, final String s){
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta){
		return new TileEntityBMPowerBox();
	}
	
	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state){
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		return Library.rotateAABB(new AxisAlignedBB(0.253, 0.17, 0, 0.747, 0.765, 0.12), state.getValue(FACING));
	}
	
	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		TileEntityBMPowerBox box = (TileEntityBMPowerBox)worldIn.getTileEntity(pos);
		if(playerIn.isSneaking() || box == null || (worldIn.getTotalWorldTime()-box.ticksPlaced) < 12){
			return false;
		}
		if(!worldIn.isRemote){
			final boolean oldIsOn = state.getValue(IS_ON);
			worldIn.playSound(null, pos.getX(),  pos.getY(),  pos.getZ(), HBMSoundHandler.reactorStart, SoundCategory.BLOCKS, 1, oldIsOn ? 0.9F : 1);
			worldIn.setBlockState(pos, state.withProperty(IS_ON, !oldIsOn));
			worldIn.notifyNeighborsOfStateChange(pos, this, false);
	        worldIn.notifyNeighborsOfStateChange(pos.offset(state.getValue(FACING).getOpposite()), this, false);
			box = (TileEntityBMPowerBox)worldIn.getTileEntity(pos);
			box.ticksPlaced = worldIn.getTotalWorldTime();
			ControlEventSystem.get(worldIn).broadcastToSubscribed(box, ControlEvent.newEvent("lever_toggle").setVar("isOn", !oldIsOn));
			playerIn.swingArm(hand);
			return true;
		}
		
		return true;
	}
	
	@Override
	public boolean canProvidePower(final IBlockState state){
		return true;
	}
	
	@Override
	public int getStrongPower(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side){
		return blockState.getValue(IS_ON) ? 15 : 0;
	}

	@Override
	public int getWeakPower(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side){
		return blockState.getValue(IS_ON) ? 15 : 0;
	}
	
	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(IS_ON, false);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, IS_ON);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		int meta = state.getValue(FACING).getIndex() << 1;
		meta += state.getValue(IS_ON) ? 1 : 0;
		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		final boolean on = (meta & 1) == 1;
		meta = meta >> 1;
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

		if(enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(IS_ON, on);
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
