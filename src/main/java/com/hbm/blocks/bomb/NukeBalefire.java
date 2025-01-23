package com.hbm.blocks.bomb;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.machine.BlockMachineBase;
import com.hbm.interfaces.IBomb;
import com.hbm.tileentity.bomb.TileEntityNukeBalefire;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NukeBalefire extends BlockMachineBase implements IBomb {

	public NukeBalefire(final Material materialIn, final int guiID, final String s) {
		super(materialIn, guiID, s);
	}
	
	@Override
	protected boolean rotatable() {
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityNukeBalefire();
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
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if (world.isBlockPowered(pos)) {
			explode(world, pos);
		}
	}

	@Override
	public void explode(final World world, final BlockPos pos) {
		if(!world.isRemote) {
			final TileEntityNukeBalefire bomb = (TileEntityNukeBalefire) world.getTileEntity(pos);

			if(bomb.isLoaded())
				bomb.explode();
		}
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		tooltip.add("§a["+ I18nUtil.resolveKey("trait.balefirebomb")+"]"+"§r");
		tooltip.add(" §e"+I18nUtil.resolveKey("desc.radius", 250)+"§r");
	}
}
