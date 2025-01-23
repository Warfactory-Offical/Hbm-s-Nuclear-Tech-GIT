package com.hbm.blocks.network;

import api.hbm.block.IConveyorItem;
import api.hbm.block.IConveyorPackage;
import api.hbm.block.IEnterableBlock;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.InventoryHelper;
import com.hbm.tileentity.network.TileEntityCraneBase;
import com.hbm.tileentity.network.TileEntityCraneUnboxer;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CraneUnboxer extends BlockCraneBase implements IEnterableBlock {
    public CraneUnboxer(final Material materialIn, final String s) {
        super(materialIn);
        this.setTranslationKey(s);
        this.setRegistryName(s);
        ModBlocks.ALL_BLOCKS.add(this);
    }

    @Override
    public TileEntityCraneBase createNewTileEntity(final World world, final int meta) {
        return new TileEntityCraneUnboxer();
    }

    @Override
    public boolean canItemEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorItem entity) {
        return false;
    }

    @Override
    public void onItemEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorItem entity) { }

    @Override
    public boolean canPackageEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorPackage entity) {
        final BlockPos pos = new BlockPos(x, y, z);
        final IBlockState state = world.getBlockState(pos);
        final EnumFacing orientation = state.getValue(BlockHorizontal.FACING);
        return dir == orientation;
    }

    @Override
    public void onPackageEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorPackage entity) {
        final BlockPos pos = new BlockPos(x, y, z);
        final TileEntity te = world.getTileEntity(pos);
        boolean worked = false;
        if (te instanceof TileEntityCraneUnboxer) {

            for (final ItemStack stack : entity.getItemStacks()) {
                if(stack == null || stack.isEmpty()) continue;
                worked = ((TileEntityCraneUnboxer)te).tryFillTeDirect(stack);

                if (stack.getCount() > 0 || !worked) {
                    final EntityItem drop = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, stack.copy());
                    world.spawnEntity(drop);
                }
            }
        }
    }

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

        if(tileentity instanceof TileEntityCraneUnboxer) {
            InventoryHelper.dropInventoryItems(world, pos, tileentity);
        }
        super.breakBlock(world, pos, state);
    }
}
