package com.hbm.blocks.network;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IDummy;
import com.hbm.tileentity.machine.TileEntityDummy;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class DummyBlockCraneSplitter extends BlockContainer implements IDummy {

        public static boolean safeBreak = false;

        public DummyBlockCraneSplitter(final Material materialIn, final String s) {
            super(materialIn);
            this.setTranslationKey(s);
            this.setRegistryName(s);

            ModBlocks.ALL_BLOCKS.add(this);
        }

        @Override
        public TileEntity createNewTileEntity(final World worldIn, final int meta) {
            return new TileEntityDummy();
        }

        @Override
        public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
            if(!safeBreak) {
                final TileEntity te = world.getTileEntity(pos);
                if(te != null && te instanceof TileEntityDummy) {
                    if(!world.isRemote)
                        world.destroyBlock(((TileEntityDummy)te).target, true);
                }
            }
            world.removeTileEntity(pos);
        }

        @Override
        public EnumBlockRenderType getRenderType(final IBlockState state) {
            return EnumBlockRenderType.INVISIBLE;
        }

        @Override
        public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
            return Items.AIR;
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
        public ItemStack getItem(final World worldIn, final BlockPos pos, final IBlockState state) {
            return ItemStackUtil.itemStackFrom(ModBlocks.machine_centrifuge);
        }
}
