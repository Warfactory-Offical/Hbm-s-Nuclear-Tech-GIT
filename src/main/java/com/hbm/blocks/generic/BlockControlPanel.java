package com.hbm.blocks.generic;

import java.util.Map;
import java.util.Random;

import com.hbm.blocks.BlockControlPanelType;
import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.ICustomSelectionBox;
import com.hbm.inventory.control_panel.*;
import com.hbm.items.ModItems;
import com.hbm.main.ClientProxy;
import com.hbm.main.MainRegistry;
import com.hbm.packet.NBTControlPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.machine.TileEntityControlPanel;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

public class BlockControlPanel extends BlockContainer implements ICustomSelectionBox {

	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockControlPanel(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		final TileEntityControlPanel te = new TileEntityControlPanel();
		te.panel = new ControlPanel(te, 0.25F, (float) Math.toRadians(20), 0, 0, 0.25F, 0);
		return te;
	}
	
	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(!worldIn.isRemote){
			if(playerIn.getHeldItem(hand).getItem() == ModItems.screwdriver || playerIn.getHeldItem(hand).getItem() == ModItems.screwdriver_desh)
				playerIn.openGui(MainRegistry.instance, ModBlocks.guiID_control_panel, worldIn, pos.getX(), pos.getY(), pos.getZ());
		} else {
			final TileEntityControlPanel control = (TileEntityControlPanel)worldIn.getTileEntity(pos);
			final Control ctrl = control.panel.getSelectedControl(playerIn.getPositionEyes(1), playerIn.getLook(1));
			if(ctrl != null){
				final ControlEvent evt = ControlEvent.newEvent("ctrl_press");
				evt.setVar("isSneaking", new DataValueFloat(playerIn.isSneaking()));
				final NBTTagCompound dat = evt.writeToNBT(new NBTTagCompound());
				dat.setInteger("click_control", ctrl.panel.controls.indexOf(ctrl));
				PacketDispatcher.wrapper.sendToServer(new NBTControlPacket(dat, pos));
				return true;
			}
		}
		return true;
	}

	@Nonnull
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		final TileEntity te = source.getTileEntity(pos);
		if (te instanceof TileEntityControlPanel) {
			final AxisAlignedBB ret = ((TileEntityControlPanel) te).getBoundingBox(state.getValue(UP), state.getValue(DOWN), state.getValue(FACING));
			if (ret != null) {
				return ret;
			}
		}
		return super.getBoundingBox(state, source, pos);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(final IBlockAccess worldIn, final IBlockState state, final BlockPos pos, final EnumFacing face){
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand){
		return this.getDefaultState()
				.withProperty(FACING, placer.getHorizontalFacing().getOpposite())
				.withProperty(UP, facing.getIndex() == 1)
				.withProperty(DOWN, facing.getIndex() == 0);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.AIR;
	}

	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
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
	@SideOnly(Side.CLIENT)
	public boolean renderBox(final World world, final EntityPlayer player, final IBlockState state, final BlockPos pos, final double x, final double y, final double z, final float partialTicks){
		final TileEntityControlPanel control = (TileEntityControlPanel)world.getTileEntity(pos);
		final Control ctrl = control.panel.getSelectedControl(player.getPositionEyes(partialTicks), player.getLook(partialTicks));
		//if(control.panel.controls.size() > 0)
		//	ctrl = control.panel.controls.get(0);
		if(ctrl != null){
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			control.panel.transform.store(ClientProxy.AUX_GL_BUFFER);
			ClientProxy.AUX_GL_BUFFER.rewind();
			GL11.glMultMatrix(ClientProxy.AUX_GL_BUFFER);
			if (ctrl.getBoundingBox() != null)
				// offset to bury bottom lines
				RenderGlobal.drawSelectionBoundingBox(ctrl.getBoundingBox().offset(0, -.01F, 0), 0, 0, 0, 0.4F);
			GL11.glPopMatrix();
			return true;
		}
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, UP, DOWN, FACING);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		final int up = state.getValue(UP) ? 1 : 0;
		final int down = state.getValue(DOWN) ? 1 : 0;
		final int facing = state.getValue(FACING).getIndex();
		return (up << 3) | (down << 2) | (facing - 2);
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState()
				.withProperty(UP, ((meta >> 3) & 1) > 0)
				.withProperty(DOWN, ((meta >> 2) & 1) > 0)
				.withProperty(FACING, EnumFacing.byIndex((meta & 3) + 2));
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
