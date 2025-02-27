package com.hbm.blocks.machine.pile;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.pile.TileEntityPileFuel;

import api.hbm.block.IToolable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGraphiteFuel extends BlockGraphiteDrilledTE implements IToolable {

	public BlockGraphiteFuel(final String s){
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int mets) {
		return new TileEntityPileFuel();
	}
	
	@Override
	public void getDrops(final NonNullList<ItemStack> drops, final IBlockAccess world, final BlockPos pos, final IBlockState state, final int fortune){
		super.getDrops(drops, world, pos, state, fortune);
		drops.add(ItemStackUtil.itemStackFrom(ModItems.pile_rod_uranium));
	}
	
	@Override
	public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand hand, final ToolType tool){
		if(!world.isRemote) {
			
			if(tool == ToolType.SCREWDRIVER) {
	
				final EnumFacing.Axis axis = world.getBlockState(new BlockPos(x, y, z)).getValue(AXIS);
				
				if(side.getAxis() == axis) {
					world.setBlockState(new BlockPos(x, y, z), ModBlocks.block_graphite_drilled.getDefaultState().withProperty(AXIS, axis), 3);
					ejectItem(world, x, y, z, side, ItemStackUtil.itemStackFrom(ModItems.pile_rod_uranium));
				}
			}
			
			if(tool == ToolType.HAND_DRILL) {
				final TileEntityPileFuel pile = (TileEntityPileFuel) world.getTileEntity(new BlockPos(x, y, z));
				player.sendMessage(new TextComponentString("CP1 FUEL ASSEMBLY " + x + " " + y + " " + z).setStyle(new Style().setColor(TextFormatting.GOLD)));
				player.sendMessage(new TextComponentString("HEAT: " + pile.heat + "/" + TileEntityPileFuel.maxHeat).setStyle(new Style().setColor(TextFormatting.YELLOW)));
				player.sendMessage(new TextComponentString("DEPLETION: " + pile.progress + "/" + TileEntityPileFuel.maxProgress).setStyle(new Style().setColor(TextFormatting.YELLOW)));
				player.sendMessage(new TextComponentString("FLUX: " + pile.lastNeutrons).setStyle(new Style().setColor(TextFormatting.YELLOW)));
			}
		}
		
		return true;
	}
}