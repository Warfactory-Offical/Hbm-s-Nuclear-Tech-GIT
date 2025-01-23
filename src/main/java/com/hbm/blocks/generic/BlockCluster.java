package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import api.hbm.block.IDrillInteraction;
import api.hbm.block.IMiningDrill;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class BlockCluster extends BlockOre implements IDrillInteraction {

	public BlockCluster(final String s) {
		super();
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHarvestLevel("pickaxe", 1);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
		return Items.AIR;
	}
	
	@Override
	public void harvestBlock(final World world, final EntityPlayer player, final BlockPos pos, final IBlockState state, final TileEntity te, final ItemStack stack){
		if(player instanceof FakePlayer || player == null) {
			return;
		}

		if(!world.isRemote && world.getGameRules().getBoolean("doTileDrops") && !world.restoringBlockSnapshots) {
			
			final Item drop = getDrop();
			
			if(drop == null)
				return;
			
			final float f = 0.7F;
			final double mX = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			final double mY = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			final double mZ = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			
			final EntityItem entityitem = new EntityItem(world, (double) pos.getX() + mX, (double) pos.getY() + mY, (double) pos.getZ() + mZ, ItemStackUtil.itemStackFrom(drop));
			entityitem.setPickupDelay(10);
			world.spawnEntity(entityitem);
		}
	}
	
	private Item getDrop() {

		if(this == ModBlocks.cluster_iron)
			return ModItems.crystal_iron;
		if(this == ModBlocks.cluster_titanium)
			return ModItems.crystal_titanium;
		if(this == ModBlocks.cluster_aluminium)
			return ModItems.crystal_aluminium;
		if(this == ModBlocks.cluster_copper)
			return ModItems.crystal_copper;
		if(this == ModBlocks.basalt_gem)
			return ModItems.gem_volcanic;
		
		return null;
	}

	@Override
	public boolean canBreak(final World world, final int x, final int y, final int z, final IBlockState state, final IMiningDrill drill) {
		return drill.getDrillRating() >= 30;
	}

	@Override
	public ItemStack extractResource(final World world, final int x, final int y, final int z, final IBlockState state, final IMiningDrill drill) {
		return drill.getDrillRating() >= 30 ? ItemStackUtil.itemStackFrom(this.getDrop()) : null;
	}

	@Override
	public float getRelativeHardness(final World world, final int x, final int y, final int z, final IBlockState state, final IMiningDrill drill) {
		return state.getBlockHardness(world, new BlockPos(x, y, z));
	}
}