package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.main.MainRegistry;

import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WasteGrassTall extends BlockBush {
	
	public static final PropertyInteger META = PropertyInteger.create("meta", 0, 15);
	
	public WasteGrassTall(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setSoundType(SoundType.PLANT);
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	public boolean canBlockStay(final World world, final BlockPos pos, final IBlockState state){
		if (pos.getY() >= 0 && pos.getY() < 256){
            final Block block = world.getBlockState(pos.down()).getBlock();
            return block == ModBlocks.waste_earth || block == ModBlocks.waste_mycelium || block == ModBlocks.waste_dirt;
        }
        return false;
	}
	
	@Override
	public boolean canPlaceBlockAt(final World world, final BlockPos pos) {
		return this.canBlockStay(world, pos, world.getBlockState(pos));
	}
	
	@Override
	public boolean canPlaceTorchOnTop(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(final World world, final BlockPos pos, final EnumFacing side) {
		return this.canBlockStay(world, pos, world.getBlockState(pos));
	}

	@Override
    public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
        return Items.AIR;
    }

    @Override
    public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
        return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    }

	@Override
    public MapColor getMapColor(final IBlockState state, final IBlockAccess worldIn, final BlockPos pos)
    {
    	return MapColor.GRASS;
    }

    @Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, META);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(META);
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState().withProperty(META, meta);
	}
}
