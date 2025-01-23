package com.hbm.blocks.network;

import api.hbm.block.IConveyorItem;
import api.hbm.block.IConveyorPackage;
import api.hbm.block.IEnterableBlock;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.InventoryHelper;
import com.hbm.tileentity.machine.TileEntityMachineAssembler;
import com.hbm.tileentity.network.TileEntityCraneBase;
import com.hbm.tileentity.network.TileEntityCraneInserter;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class CraneInserter extends BlockCraneBase implements IEnterableBlock {
    public CraneInserter(final Material materialIn, final String s) {
        super(materialIn);
        this.setTranslationKey(s);
        this.setRegistryName(s);
        ModBlocks.ALL_BLOCKS.add(this);
    }

    @Override
    public TileEntityCraneBase createNewTileEntity(final World world, final int meta) {
        return new TileEntityCraneInserter();
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
        if (entity == null || entity.getItemStack() == ItemStack.EMPTY || entity.getItemStack().getCount() <= 0) {
            return;
        }

        final ItemStack toAdd = entity.getItemStack().copy();
        
        final TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        boolean worked = false;
        if(te instanceof TileEntityCraneInserter)
            worked = ((TileEntityCraneInserter)te).tryFillTeDirect(toAdd);

        if(!worked) {
            final EntityItem drop = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, toAdd.copy());
            world.spawnEntity(drop);
        }
    }

    @Override
    public boolean canPackageEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorPackage entity) {
        return false;
    }

    @Override
    public void onPackageEnter(final World world, final int x, final int y, final int z, final EnumFacing dir, final IConveyorPackage entity) { }

    @Override
    public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
        final TileEntity tileentity = world.getTileEntity(pos);

        if(tileentity instanceof TileEntityCraneInserter) {
            InventoryHelper.dropInventoryItems(world, pos, tileentity);
        }
        super.breakBlock(world, pos, state);
    }

}
