package com.hbm.blocks.network;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.InventoryHelper;
import com.hbm.tileentity.network.TileEntityCraneBase;
import com.hbm.tileentity.network.TileEntityCraneExtractor;
import com.hbm.tileentity.network.TileEntityCraneInserter;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CraneExtractor extends BlockCraneBase {
    public CraneExtractor(final Material materialIn, final String s) {
        super(materialIn);
        this.setTranslationKey(s);
        this.setRegistryName(s);
        ModBlocks.ALL_BLOCKS.add(this);
    }

    @Override
    public TileEntityCraneBase createNewTileEntity(final World world, final int meta) {
        return new TileEntityCraneExtractor();
    }

    @Override
    public boolean canConnectRedstone(final IBlockState state, final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
        return true;
    }

    @Override
    public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
        final TileEntity tileentity = world.getTileEntity(pos);

        if(tileentity instanceof TileEntityCraneExtractor) {
            InventoryHelper.dropInventoryItems(world, pos, tileentity);
        }
        super.breakBlock(world, pos, state);
    }

}
