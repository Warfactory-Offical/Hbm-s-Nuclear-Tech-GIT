package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.main.MainRegistry;
import com.hbm.world.HugeMush;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMush extends BlockBush implements IGrowable, IItemHazard {
	
	ItemHazardModule module;
	protected static final AxisAlignedBB MUSHROOM_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.4000000059604645D, 0.699999988079071D);
	
	public BlockMush(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setTickRandomly(true);
		this.setSoundType(SoundType.PLANT);
		this.module = new ItemHazardModule();
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public ItemHazardModule getModule() {
		return module;
	}

	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return MUSHROOM_AABB;
	}
	
	public boolean canBlockStay(final World world, final BlockPos pos, final IBlockState state){
		if (pos.getY() >= 0 && pos.getY() < 256)
        {
            final Block block = world.getBlockState(pos.down()).getBlock();
            return block == ModBlocks.waste_earth || block == ModBlocks.waste_mycelium || block == ModBlocks.waste_dirt;
        }
        else
        {
            return false;
        }
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
	public void updateTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand) {
		this.checkAndDropBlock(worldIn, pos, state);
		if(GeneralConfig.enableMycelium && (worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.waste_mycelium || worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.waste_earth) && rand.nextInt(5) == 0){
			worldIn.setBlockState(pos.down(), ModBlocks.waste_mycelium.getDefaultState());
		}
		
	}

	@Override
	public boolean canGrow(final World worldIn, final BlockPos pos, final IBlockState state, final boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(final World worldIn, final Random rand, final BlockPos pos, final IBlockState state) {
		return (double)rand.nextFloat() < 0.4D;
	}

	@Override
	public void grow(final World worldIn, final Random rand, final BlockPos pos, final IBlockState state) {
		this.generateBigMushroom(worldIn, pos, state, rand);
	}

	private boolean generateBigMushroom(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand) {
		worldIn.setBlockToAir(pos);
        HugeMush worldgenbigmushroom = null;

        worldgenbigmushroom = new HugeMush();
        worldgenbigmushroom.generate(worldIn, rand, pos);
        
        return true;
	}
	
}
