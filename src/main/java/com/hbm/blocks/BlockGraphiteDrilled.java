package com.hbm.blocks;

import com.hbm.blocks.machine.pile.BlockGraphiteDrilledBase;
import com.hbm.items.ModItems;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.lib.HBMSoundHandler;

import com.hbm.util.ItemStackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGraphiteDrilled extends BlockGraphiteDrilledBase {

	public BlockGraphiteDrilled(final String s){
		super(s);
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(!player.getHeldItem(hand).isEmpty()) {

			final EnumFacing.Axis axis = state.getValue(AXIS);

			if(facing.getAxis() == axis) {
				final int x = pos.getX();
				final int y = pos.getY();
				final int z = pos.getZ();
				if(checkInteraction(world, x, y, z, axis, player, hand, ModItems.pile_rod_uranium, ModBlocks.block_graphite_fuel)) return true;
				if(checkInteraction(world, x, y, z, axis, player, hand, ModItems.pile_rod_plutonium, ModBlocks.block_graphite_plutonium)) return true;
				if(checkInteraction(world, x, y, z, axis, player, hand, ModItems.pile_rod_source, ModBlocks.block_graphite_source)) return true;
				if(checkInteraction(world, x, y, z, axis, player, hand, ModItems.pile_rod_boron, ModBlocks.block_graphite_rod)) return true;
                return checkInteraction(world, x, y, z, null, player, hand, ModItems.ingot.getItemStack(MaterialMineral.GRAPHITE), ModBlocks.block_graphite);
			}
		}

		return false;
	}

	private boolean checkInteraction(final World world, final int x, final int y, final int z, final EnumFacing.Axis meta, final EntityPlayer player, final EnumHand hand, final Item item, final Block block) {

		if(player.getHeldItem(hand).getItem() == item) {
			player.getHeldItem(hand).shrink(1);
			if(block instanceof BlockGraphiteDrilledBase){
				world.setBlockState(new BlockPos(x, y, z), block.getDefaultState().withProperty(AXIS, meta), 3);
			} else {
				world.setBlockState(new BlockPos(x, y, z), block.getDefaultState(), 3);
			}


			world.playSound(null, x + 0.5, y + 1.5, z + 0.5, HBMSoundHandler.upgradePlug, SoundCategory.BLOCKS, 1.0F, 1.0F);

			return true;
		}

		return false;
	}

	private boolean checkInteraction(final World world, final int x, final int y, final int z, final EnumFacing.Axis meta, final EntityPlayer player, final EnumHand hand, final ItemStack stack, final Block block) {

		if(ItemStackUtil.isSameMetaItem(player.getHeldItem(hand), stack)) {
			player.getHeldItem(hand).shrink(1);
			if(block instanceof BlockGraphiteDrilledBase){
				world.setBlockState(new BlockPos(x, y, z), block.getDefaultState().withProperty(AXIS, meta), 3);
			} else {
				world.setBlockState(new BlockPos(x, y, z), block.getDefaultState(), 3);
			}


			world.playSound(null, x + 0.5, y + 1.5, z + 0.5, HBMSoundHandler.upgradePlug, SoundCategory.BLOCKS, 1.0F, 1.0F);

			return true;
		}

		return false;
	}
}