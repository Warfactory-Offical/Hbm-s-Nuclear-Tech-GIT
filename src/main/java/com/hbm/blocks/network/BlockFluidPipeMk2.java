package com.hbm.blocks.network;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ILookOverlay;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.util.I18nUtil;
import com.hbm.items.machine.ItemFFFluidDuct;
import com.hbm.tileentity.conductor.TileEntityFFDuctBaseMk2;
import com.hbm.tileentity.conductor.TileEntityFFFluidDuctMk2;
import com.hbm.tileentity.conductor.TileEntityFFFluidSuccMk2;

import api.hbm.block.IToolable;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class BlockFluidPipeMk2 extends BlockContainer implements IToolable, ILookOverlay {

	public static final PropertyBool EXTRACTS = PropertyBool.create("extracts");
	
	private static final float p = 1F / 16F;
	private static final AxisAlignedBB DUCT_BB = new AxisAlignedBB(1, 1, 1, -1, -1, -1);
	
	public BlockFluidPipeMk2(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setDefaultState(this.blockState.getBaseState().withProperty(EXTRACTS, false));
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(meta > 0){
			return new TileEntityFFFluidSuccMk2();
		} else {
			return new TileEntityFFFluidDuctMk2();
		}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		tooltip.add(I18nUtil.resolveKey("desc.extraction"));
	}
	
	@Override
	public void onNeighborChange(final IBlockAccess world, final BlockPos pos, final BlockPos neighbor) {
		final TileEntity te = world.getTileEntity(pos);
		if(te instanceof TileEntityFFDuctBaseMk2){
			((TileEntityFFDuctBaseMk2)te).onNeighborChange();
		}
	}
	@Override
	public void neighborChanged(final IBlockState state, final World worldIn, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		final TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileEntityFFDuctBaseMk2){
			((TileEntityFFDuctBaseMk2)te).onNeighborChange();
		}
	}
	
	@Override
	public IBlockState getActualState(final IBlockState state, final IBlockAccess worldIn, final BlockPos pos) {
		//getActualState appears to be called when the neighbor changes on client, so I can use this to update instead of a buggy packet.
		final TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileEntityFFDuctBaseMk2)
			((TileEntityFFDuctBaseMk2)te).onNeighborChange();
		return state;
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		final TileEntity te = worldIn.getTileEntity(pos);
		
		if(te instanceof TileEntityFFDuctBaseMk2){
			TileEntityFFDuctBaseMk2.breakBlock(worldIn, pos);
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		if (world.getTileEntity(pos) instanceof TileEntityFFDuctBaseMk2 te) {

            if (te != null) {
				final boolean pX = te.connections[3] != null;
				final boolean nX = te.connections[5] != null;
				final boolean pY = te.connections[0] != null;
				final boolean nY = te.connections[1] != null;
				final boolean pZ = te.connections[4] != null;
				final boolean nZ = te.connections[2] != null;
				
				final int mask = (pX ? 32 : 0) + (nX ? 16 : 0) + (pY ? 8 : 0) + (nY ? 4 : 0) + (pZ ? 2 : 0) + (nZ ? 1 : 0);
			
				if(mask == 0) {
					return new AxisAlignedBB(0F, 0F, 0F, 1F, 1F, 1F);
				} else if(mask == 0b100000 || mask == 0b010000 || mask == 0b110000) {
					return new AxisAlignedBB(0F, 0.3125F, 0.3125F, 1F, 0.6875F, 0.6875F);
				} else if(mask == 0b001000 || mask == 0b000100 || mask == 0b001100) {
					return new AxisAlignedBB(0.3125F, 0F, 0.3125F, 0.6875F, 1F, 0.6875F);
				} else if(mask == 0b000010 || mask == 0b000001 || mask == 0b000011) {
					return new AxisAlignedBB(0.3125F, 0.3125F, 0F, 0.6875F, 0.6875F, 1F);
				} else {
					
					return new AxisAlignedBB(
							nX ? 0F : 0.3125F,
							nY ? 0F : 0.3125F,
							nZ ? 0F : 0.3125F,
							pX ? 1F : 0.6875F,
							pY ? 1F : 0.6875F,
							pZ ? 1F : 0.6875F);
				}
			}
		}
		return DUCT_BB;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isFullBlock(final IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(final IBlockState state) {
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
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(final IBlockAccess worldIn, final IBlockState state, final BlockPos pos, final EnumFacing face){
		return BlockFaceShape.CENTER;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, EXTRACTS);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(EXTRACTS) ? 1 : 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return meta > 0 ? this.getDefaultState().withProperty(EXTRACTS, true) : this.getDefaultState().withProperty(EXTRACTS, false);
	}

	@Override
	public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand hand, final ToolType tool){
		if(tool == ToolType.SCREWDRIVER){
			Fluid type = null;
			final BlockPos pos = new BlockPos(x, y, z);
			final IBlockState state = world.getBlockState(pos);
			TileEntity te = world.getTileEntity(pos);
			if(te instanceof TileEntityFFDuctBaseMk2){
				type = ((TileEntityFFDuctBaseMk2) te).getType();
			}
			
			final boolean extracts = state.getValue(BlockFluidPipeMk2.EXTRACTS);
			world.setBlockState(pos, ModBlocks.fluid_duct_mk2.getDefaultState().withProperty(BlockFluidPipeMk2.EXTRACTS, !extracts));
			
			te = world.getTileEntity(pos);
			if(te instanceof TileEntityFFDuctBaseMk2){
				((TileEntityFFDuctBaseMk2) te).setType(type);
			}
			
			player.swingArm(hand);
			return true;
		}
		return false;
	}
	
	public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player){
		final TileEntity te = world.getTileEntity(pos);
		Fluid ductFluid = null;
		if(te instanceof TileEntityFFDuctBaseMk2){
			ductFluid = ((TileEntityFFDuctBaseMk2)te).getType();
		}
		if(ductFluid != null)
			return ItemFFFluidDuct.getStackFromFluid(ductFluid, 1);
		return super.getPickBlock(state, target, world, pos, player);
	}

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
			
		final TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		if(!(te instanceof TileEntityFFDuctBaseMk2))
			return;
		
		final Fluid ductFluid = ((TileEntityFFDuctBaseMk2) te).getType();
		
		final List<String> text = new ArrayList();
		if(ductFluid == null){
			text.add("§7" + I18nUtil.resolveKey("desc.none"));
		} else{
			final int color = ModForgeFluids.getFluidColor(ductFluid);
			text.add("&[" + color + "&]" +I18nUtil.resolveKey(ductFluid.getUnlocalizedName()));
		}
		
		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
	}
}
