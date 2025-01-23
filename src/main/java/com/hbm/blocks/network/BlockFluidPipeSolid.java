package com.hbm.blocks.network;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ILookOverlay;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.util.I18nUtil;
import com.hbm.tileentity.conductor.TileEntityFFDuctBaseMk2;
import com.hbm.tileentity.conductor.TileEntityFFFluidDuctMk2Solid;
import com.hbm.tileentity.conductor.TileEntityFFFluidSuccMk2Solid;

import api.hbm.block.IToolable;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class BlockFluidPipeSolid extends BlockContainer implements IToolable, ILookOverlay {

	public static final PropertyBool EXTRACTS = PropertyBool.create("extracts");
	
	
	public BlockFluidPipeSolid(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setDefaultState(this.blockState.getBaseState().withProperty(EXTRACTS, false));
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(meta > 0){
			return new TileEntityFFFluidSuccMk2Solid();
		} else {
			return new TileEntityFFFluidDuctMk2Solid();
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
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
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
			
			final boolean extracts = state.getValue(BlockFluidPipeSolid.EXTRACTS);
			world.setBlockState(pos, this.getDefaultState().withProperty(BlockFluidPipeSolid.EXTRACTS, !extracts));
			
			te = world.getTileEntity(pos);
			if(te instanceof TileEntityFFDuctBaseMk2){
				((TileEntityFFDuctBaseMk2) te).setType(type);
			}
			
			player.swingArm(hand);
			return true;
		}
		return false;
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
