package com.hbm.blocks.machine.pile;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.pile.TileEntityPileSource;

import api.hbm.block.IToolable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGraphiteSource extends BlockGraphiteDrilledTE implements IToolable {

	public BlockGraphiteSource(final String s){
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int mets) {
		return new TileEntityPileSource();
	}
	
	@Override
	public void getDrops(final NonNullList<ItemStack> drops, final IBlockAccess world, final BlockPos pos, final IBlockState state, final int fortune){
		super.getDrops(drops, world, pos, state, fortune);
		drops.add(ItemStackUtil.itemStackFrom(whoAmIAgain()));
	}
	
	@Override
	public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand hand, final ToolType tool){
		if(tool != ToolType.SCREWDRIVER)
			return false;
		
		if(!world.isRemote) {

			final EnumFacing.Axis axis = world.getBlockState(new BlockPos(x, y, z)).getValue(AXIS);
			
			if(side.getAxis() == axis) {
				world.setBlockState(new BlockPos(x, y, z), ModBlocks.block_graphite_drilled.getDefaultState().withProperty(AXIS, axis), 3);
				ejectItem(world, x, y, z, side, ItemStackUtil.itemStackFrom(whoAmIAgain()));
			}
		}
		
		return true;
	}
	
	private Item whoAmIAgain() {
		return this == ModBlocks.block_graphite_plutonium ? ModItems.pile_rod_plutonium : ModItems.pile_rod_source;
	}
}