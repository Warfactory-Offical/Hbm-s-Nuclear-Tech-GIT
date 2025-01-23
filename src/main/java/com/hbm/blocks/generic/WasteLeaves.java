package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.Random;
import java.util.ArrayList;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.interfaces.IItemHazard;
import com.hbm.modules.ItemHazardModule;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WasteLeaves extends BlockOldLeaf implements IItemHazard {

	ItemHazardModule module;

	public WasteLeaves(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(CHECK_DECAY, Boolean.valueOf(false)).withProperty(DECAYABLE, Boolean.valueOf(false)));
		this.setTickRandomly(false);
		this.module = new ItemHazardModule();
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, VARIANT, CHECK_DECAY, DECAYABLE);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		int i = 0;

		if (!state.getValue(DECAYABLE)) {
			i |= 4;
		}

		if (state.getValue(CHECK_DECAY)) {
			i |= 8;
		}

		return i;
	}

    @Override
    public IBlockState getStateFromMeta(final int meta) {
        return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public void updateTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand){
    }

    @Override
    public void randomTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random random){
    }

	@Override
	public ItemHazardModule getModule() {
		return module;
	}

	@Override
	public void getDrops(final NonNullList<ItemStack> drops, final IBlockAccess world, final BlockPos pos, final IBlockState state, final int fortune){
		if(RANDOM.nextInt(4) == 0)
			drops.add(ItemStackUtil.itemStackFrom(Item.getItemFromBlock(Blocks.DEADBUSH)));
		if(RANDOM.nextInt(3) == 0)
			drops.add(ItemStackUtil.itemStackFrom(Items.STICK));
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
		if(rand.nextInt(4) == 0)
			return Item.getItemFromBlock(Blocks.DEADBUSH);
		return null;
	}

	public NonNullList<ItemStack> onSheared(final ItemStack item, final IBlockAccess world, final BlockPos pos, final int fortune){
		final NonNullList<ItemStack> output = NonNullList.create();
		output.add(ItemStackUtil.itemStackFrom(ModBlocks.waste_leaves, fortune+1));
		return output;
	}

	@Override
	protected int getSaplingDropChance(final IBlockState state){
		return 0;
	}

	@Override
	public void dropBlockAsItemWithChance(final World worldIn, final BlockPos pos, final IBlockState state, final float chance, final int fortune){
    }

	@Override
	protected void dropApple(final World worldIn, final BlockPos pos, final IBlockState state, final int chance){
    }

	@Override
	public BlockPlanks.EnumType getWoodType(final int meta){
		return BlockPlanks.EnumType.OAK;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return Blocks.LEAVES.getRenderLayer();
	}

	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return Blocks.LEAVES.isOpaqueCube(state);
	}

	@Override
  	public boolean shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side) {
		this.leavesFancy = !Blocks.LEAVES.isOpaqueCube(blockState);
		return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	@Override
	public void getSubBlocks(final CreativeTabs itemIn, final NonNullList<ItemStack> items){
    }
}
