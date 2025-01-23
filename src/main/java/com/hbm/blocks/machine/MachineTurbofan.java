package com.hbm.blocks.machine;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.BlockDummyable;
import com.hbm.main.MainRegistry;
import com.hbm.lib.ForgeDirection;
import com.hbm.inventory.EngineRecipes.FuelGrade;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityMachineTurbofan;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MachineTurbofan extends BlockDummyable {

	public MachineTurbofan(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	
	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		if(meta >= 12) return new TileEntityMachineTurbofan();
		if(meta >= 6) return new TileEntityProxyCombo(false, true, true);
		return null;
	}
	
	@Override
	public int[] getDimensions() {
		return new int[] {2, 0, 1, 1, 3, 3};
	}

	@Override
	public int getOffset() {
		return 1;
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if (world.isRemote) {
			return true; 
		} else if(player.getHeldItemMainhand().getItem() == ModItems.linker) {
			return false;
		} else if (!player.isSneaking()) {

			final int[] pos1 = this.findCore(world, pos.getX(), pos.getY(), pos.getZ());

			if(pos1 == null)
				return true;

			final TileEntityMachineTurbofan entity = (TileEntityMachineTurbofan) world.getTileEntity(new BlockPos(pos1[0], pos1[1], pos1[2]));
			if (entity != null) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_machine_turbofan, world, pos1[0], pos1[1], pos1[2]);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void fillSpace(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		super.fillSpace(world, x, y, z, dir, o);
		
		final ForgeDirection rot = dir.getRotation(ForgeDirection.UP);

		this.makeExtra(world, x, y, z);
		this.makeExtra(world, x - rot.offsetX, y, z - rot.offsetZ);
		this.makeExtra(world, x - dir.offsetX * 2, y, z - dir.offsetZ * 2);
		this.makeExtra(world, x - dir.offsetX * 2 - rot.offsetX, y, z - dir.offsetZ * 2 - rot.offsetZ);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		list.add(I18n.format("trait.fuelefficiency"));
		list.add(" "+I18n.format("trait.fuelefficiency.desc", I18n.format(FuelGrade.AERO.getGrade()), 100));
		super.addInformation(stack, worldIn, list, flagIn);
	}
}
