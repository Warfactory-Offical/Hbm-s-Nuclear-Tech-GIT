package com.hbm.blocks.network;

import api.hbm.block.IConveyorItem;
import api.hbm.block.IConveyorPackage;
import api.hbm.block.IEnterableBlock;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.InventoryHelper;
import com.hbm.tileentity.network.TileEntityCraneBase;
import com.hbm.tileentity.network.TileEntityCraneBoxer;
import com.hbm.tileentity.network.TileEntityCraneInserter;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CraneBoxer extends BlockCraneBase implements IEnterableBlock {

    public CraneBoxer(final Material materialIn, final String s) {
        super(materialIn);
        this.setTranslationKey(s);
        this.setRegistryName(s);
        ModBlocks.ALL_BLOCKS.add(this);
    }

    @Override
    public TileEntityCraneBase createNewTileEntity(final World world, final int meta) {
        return new TileEntityCraneBoxer();
    }

    @Override
    public boolean canItemEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorItem entity) {
        final BlockPos pos = new BlockPos(x, y, z);
        final IBlockState state = world.getBlockState(pos);
        final EnumFacing orientation = state.getValue(BlockHorizontal.FACING);
        return dir == orientation;
    }
    @Override
    public void onItemEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorItem entity) {
        final BlockPos pos = new BlockPos(x, y, z);
        final ItemStack toAdd = entity.getItemStack().copy();
        boolean worked = false;
        final TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityCraneBoxer) {
            worked = ((TileEntityCraneBoxer)tileEntity).tryFillTeDirect(toAdd);

            if ((toAdd != null && toAdd.getCount() > 0) || !worked) {
                final EntityItem drop = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, toAdd.copy());
                world.spawnEntity(drop);
            }
        }
    }

    @Override
    public boolean canPackageEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorPackage entity) {
        return false;
    }

    @Override
    public void onPackageEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorPackage entity) { }


    @Override
    public boolean hasComparatorInputOverride(final IBlockState blockState) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(final IBlockState blockState, final World world, final BlockPos pos) {
        final int redstoneSignal = blockState.getComparatorInputOverride(world, pos);
        return redstoneSignal;
    }

    @Override
    public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
        final TileEntity tileentity = world.getTileEntity(pos);

        if(tileentity instanceof TileEntityCraneBoxer) {
            InventoryHelper.dropInventoryItems(world, pos, tileentity);
        }
        super.breakBlock(world, pos, state);
    }
}
