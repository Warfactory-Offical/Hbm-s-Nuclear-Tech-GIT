package com.hbm.blocks.machine.pile;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.RefStrings;

import api.hbm.block.IToolable;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGraphiteRod extends BlockGraphiteDrilledBase implements IToolable {

	public static final PropertyBool OUT = PropertyBool.create("out");
	
	public BlockGraphiteRod(final String s){
		super(s);
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(player.isSneaking())
			return false;
		
		final EnumFacing.Axis axis = state.getValue(AXIS);
		final boolean out = state.getValue(OUT);

		if(facing.getAxis() == axis) {
			
			if(world.isRemote)
				return true;
			
			world.setBlockState(pos, state.withProperty(AXIS, axis).withProperty(OUT, !out));
			
			world.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, !out ? 0.75F : 0.65F);
			
			final int oX = axis == EnumFacing.Axis.X ? 1 : 0;
			final int oY = axis == EnumFacing.Axis.Y ? 1 : 0;
			final int oZ = axis == EnumFacing.Axis.Z ? 1 : 0;
			for(int i = -1; i <= 1; i += 1) {
				
				int ix = pos.getX() + oX * i;
				int iy = pos.getY() + oY * i;
				int iz = pos.getZ() + oZ * i;
				
				final IBlockState state2 = world.getBlockState(new BlockPos(ix, iy, iz));
				while(state2.getBlock() == this && state2.getValue(AXIS) == axis && state2.getValue(OUT) == out) {
					
					world.setBlockState(new BlockPos(ix, iy, iz), state2.withProperty(OUT, !out));
					
					ix += oX * i;
					iy += oY * i;
					iz += oZ * i;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand e, final ToolType tool) {
		
		if(tool != ToolType.SCREWDRIVER)
			return false;
		
		if(!world.isRemote) {

			final EnumFacing.Axis axis = world.getBlockState(new BlockPos(x, y, z)).getValue(AXIS);
			
			if(side.getAxis() == axis) {
				world.setBlockState(new BlockPos(x, y, z), ModBlocks.block_graphite_drilled.getDefaultState().withProperty(AXIS, axis), 3);
				ejectItem(world, x, y, z, side, ItemStackUtil.itemStackFrom(ModItems.pile_rod_boron));
			}
		}
		
		return true;
	}
	
	@Override
	public void getDrops(final NonNullList<ItemStack> drops, final IBlockAccess world, final BlockPos pos, final IBlockState state, final int fortune){
		super.getDrops(drops, world, pos, state, fortune);
		drops.add(ItemStackUtil.itemStackFrom(ModItems.pile_rod_boron));
	}
	
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, AXIS, OUT);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state){
		return super.getMetaFromState(state) | (state.getValue(OUT) ? 4 : 0);
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta){
		return this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.values()[meta&3]).withProperty(OUT, (meta&4) > 0);
	}
}