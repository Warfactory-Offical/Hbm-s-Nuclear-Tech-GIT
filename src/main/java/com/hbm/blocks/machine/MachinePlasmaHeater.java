package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.lib.ForgeDirection;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityMachinePlasmaHeater;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class MachinePlasmaHeater extends BlockDummyable {

	public MachinePlasmaHeater(final String s) {
		super(Material.IRON, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(meta >= 12)
			return new TileEntityMachinePlasmaHeater();
		if(meta >= 6)
			return new TileEntityProxyCombo(false, true, true);
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {3, 0, 8, 1, 1, 1};
	}

	@Override
	public int getOffset() {
		return 1;
	}
	
	@Override
	public void fillSpace(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		super.fillSpace(world, x, y, z, dir, o);
		
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {4, -3, 2, 1, 1, 1}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + 2, z + dir.offsetZ * o, new int[] {0, 1, 10, -8, 0, 0}, this, dir);
		
		final ForgeDirection side = dir.getRotation(ForgeDirection.UP);

		for(int i = 1; i < 4; i++) {
			for(int j = -1; j < 2; j++) {

				this.makeExtra(world, x + side.offsetX * j, y + i, z + side.offsetZ * j);
			}
		}
	}
	
	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
		final int i = state.getValue(META);
		if(i >= 12) {

            for(int l = 0; l < 2; l++)
            	world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ItemStackUtil.itemStackFrom(ModBlocks.fusion_heater, 64)));

        	world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ItemStackUtil.itemStackFrom(ModBlocks.fusion_heater, 7)));
        	world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ItemStackUtil.itemStackFrom(ModBlocks.struct_plasma_core, 1)));
    	}

		super.breakBlock(world, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		final float f = 1/16F;
		if(state.getValue(META) == ForgeDirection.UP.ordinal() && world.getBlockState(pos.up()).getBlock() != this) {
    		return new AxisAlignedBB(0, 0, 0, 1, f * 8F, 1);
    	} else if(state.getValue(META) == ForgeDirection.DOWN.ordinal() && world.getBlockState(pos.down()).getBlock() != this) {
    		return new AxisAlignedBB(0, f * 8F, 0, 1, 1, 1);
    	} else {
    		return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
    	}
	}
	
	@Override
	protected boolean checkRequirement(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(), x, y, z, dir))
			return false;

		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {4, -3, 1, 1, 1, 1}, x, y, z, dir))
			return false;

        return MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o, y + 2, z + dir.offsetZ * o, new int[]{0, 1, 10, -8, 0, 0}, x, y, z, dir);
    }
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos1, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final int[] pos = this.findCore(world, pos1.getX(), pos1.getY(), pos1.getZ());

			if(pos == null)
				return false;

			final TileEntityMachinePlasmaHeater entity = (TileEntityMachinePlasmaHeater) world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
			if(entity != null)
			{
				FMLNetworkHandler.openGui(player, MainRegistry.instance, ModBlocks.guiID_plasma_heater, world, pos[0], pos[1], pos[2]);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.AIR;
	}
	
}
