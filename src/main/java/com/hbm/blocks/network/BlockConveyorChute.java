package com.hbm.blocks.network;

import api.hbm.block.IConveyorBelt;
import api.hbm.block.IEnterableBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConveyorChute extends BlockConveyor {
    public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 2); //Bottom 0, Middle 1, Input 2
    
    public BlockConveyorChute(final Material materialIn, final String s) {
        super(materialIn, s);
    }

    @Override
    public void onEntityCollision(final World world, final BlockPos pos, final IBlockState state, final Entity entity) {
        super.onEntityCollision(world, pos, state, entity);

        final Block belowBlock = world.getBlockState(pos.down()).getBlock();

        if (belowBlock instanceof IConveyorBelt || belowBlock instanceof IEnterableBlock) {
            entity.motionX *= 4.0;
            entity.motionY *= 4.0;
            entity.motionZ *= 4.0;
        } else if (entity.posY > pos.getY() + 0.25) {
            entity.motionX *= 3.0;
            entity.motionY *= 3.0;
            entity.motionZ *= 3.0;
        }
    }

    @Override
    public Vec3d getTravelLocation(final World world, final int x, final int y, final int z, final Vec3d itemPos, double speed) {
        final BlockPos pos = new BlockPos(x, y, z);
        final Block belowBlock = world.getBlockState(pos.down()).getBlock();

        if (belowBlock instanceof IConveyorBelt || belowBlock instanceof IEnterableBlock) {
            speed *= 4.0;
        } else if (itemPos.y > pos.getY() + 0.25) {
            speed *= 3.0;
        }

        return super.getTravelLocation(world, x, y, z, itemPos, speed);
    }

    @Override
    public EnumFacing getTravelDirection(final World world, final BlockPos pos, final Vec3d itemPos) {
        final Block belowBlock = world.getBlockState(pos.down()).getBlock();

        if (belowBlock instanceof IConveyorBelt || belowBlock instanceof IEnterableBlock || itemPos.y > pos.getY() + 0.25) {
            return EnumFacing.UP;
        }

        return world.getBlockState(pos).getValue(FACING);
    }

    @Override
    public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(TYPE, getUpdatedType(worldIn, pos, placer.getHorizontalFacing().getOpposite())));
    }

    @Override
    public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos){
        world.setBlockState(pos, state.withProperty(TYPE, getUpdatedType(world, pos)));
    }

    public int getUpdatedType(final World world, final BlockPos pos){
        return getUpdatedType(world, pos, world.getBlockState(pos).getValue(FACING));
    }

    public int getUpdatedType(final World world, final BlockPos pos, final EnumFacing side){
        final boolean hasChuteBelow = world.getBlockState(pos.down()).getBlock() instanceof BlockConveyorChute;
        boolean hasInputBelt = false;
        final Block inputBlock = world.getBlockState(pos.offset(side, 1)).getBlock();
        if (inputBlock instanceof IConveyorBelt || inputBlock instanceof IEnterableBlock) {
            hasInputBelt = true;
        }
        if(hasChuteBelow){
            return hasInputBelt ? 2 : 1;
        }
        return 0;
    }

    @Override
    public boolean isOpaqueCube(final IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(final IBlockState state) {
        return false;
    }

    @Override
    public boolean doesSideBlockRendering(final IBlockState state, final IBlockAccess world, final BlockPos pos, final EnumFacing face) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, TYPE);
    }

    @Override //0-3 NSEW (T0) 4-7 NSEW (T1) 8-11 NSEW (T2)
    public int getMetaFromState(final IBlockState state) {
        return state.getValue(FACING).getIndex() - 2 + (state.getValue(TYPE)<<2);
    }
    
    @Override
    public IBlockState getStateFromMeta(final int meta) {
        final EnumFacing enumfacing = EnumFacing.values()[((meta % 4)+2)];
        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(TYPE, meta>>2);
    }
}
