package com.hbm.blocks.network.energy;

import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.tileentity.network.energy.TileEntityPylonBase;
import com.hbm.tileentity.network.energy.TileEntityPylonLarge;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PylonLarge extends BlockDummyable implements ITooltipProvider {

	public PylonLarge(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		if(meta >= 12)
			return new TileEntityPylonLarge();
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {13, 0, 1, 1, 1, 1};
	}

	@Override
	public int getOffset() {
		return 0;
	}

	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
        final TileEntity te = world.getTileEntity(pos);
        if (te != null && te instanceof TileEntityPylonBase) {
            ((TileEntityPylonBase)te).disconnectAll();
        }
        super.breakBlock(world, pos, state);
    }

    public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
        this.addStandardInfo(list);
        super.addInformation(stack, worldIn, list, flagIn);
    }
}