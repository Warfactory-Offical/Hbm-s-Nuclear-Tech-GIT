package com.hbm.blocks.network;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ITooltipProvider;
import com.hbm.items.tool.ItemTooling;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.network.TileEntityCraneBase;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BlockCraneBase extends BlockContainer implements ITooltipProvider {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public BlockCraneBase(final Material mat) {
        super(mat);
    }

    @Override
    public abstract TileEntityCraneBase createNewTileEntity(World worldIn, int meta);

    @Override
    public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
        if(playerIn.getHeldItem(hand).getItem() instanceof ItemTooling) {
            return false;
        } else if(worldIn.isRemote) {
            return true;
        } else if(!playerIn.isSneaking()) {
            playerIn.openGui(MainRegistry.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
        final TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }

    private final Random rand = new Random();
    public void dropContents(final World world, final BlockPos pos, final IBlockState state, final int start, final int end) {
        final TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof ISidedInventory tileentityfurnace) {
            if(tileentityfurnace != null) {

                for(int i1 = start; i1 < end; ++i1) {
                    final ItemStack itemstack = tileentityfurnace.getStackInSlot(i1);

                    if(itemstack != null) {
                        final float f = this.rand.nextFloat() * 0.8F + 0.1F;
                        final float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                        final float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                        while(itemstack.getCount() > 0) {
                            int j1 = this.rand.nextInt(21) + 10;

                            if(j1 > itemstack.getCount()) {
                                j1 = itemstack.getCount();
                            }

                            itemstack.shrink(j1);
                            final EntityItem entityitem = new EntityItem(world, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2, ItemStackUtil.itemStackFrom(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if(itemstack.hasTagCompound()) {
                                entityitem.getItem().setTagCompound(itemstack.getTagCompound().copy());
                            }

                            final float f3 = 0.05F;
                            entityitem.motionX = (float) this.rand.nextGaussian() * f3;
                            entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
                            entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
                            world.spawnEntity(entityitem);
                        }
                    }
                }

                world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
            }
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public EnumBlockRenderType getRenderType(final IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    @Override
    public IBlockState withRotation(final IBlockState state, final Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateFromMeta(final int meta) {
        final EnumFacing enumfacing = EnumFacing.byHorizontalIndex(meta);
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(final IBlockState state) {
        return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
    }
}
