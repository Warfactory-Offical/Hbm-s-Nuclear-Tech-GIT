package com.hbm.blocks.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityMachineLargeTurbine;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class MachineLargeTurbine extends BlockDummyable {

	public MachineLargeTurbine(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(meta >= 12)
			return new TileEntityMachineLargeTurbine();

		if(meta >= 6)
			return new TileEntityProxyCombo(false, true, true);

		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] { 1, 0, 3, 1, 1, 1 };
	}

	@Override
	public int getOffset() {
		return 1;
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

			FMLNetworkHandler.openGui(player, MainRegistry.instance, ModBlocks.guiID_machine_large_turbine, world, pos[0], pos[1], pos[2]);
			return true;
		} else {
			return true;
		}
	}
	
	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase player, final ItemStack itemStack) {
		super.onBlockPlacedBy(world, pos, state, player, itemStack);
		
		if(world.isRemote)
			return;

		final int k = MathHelper.floor(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		ForgeDirection dir = ForgeDirection.NORTH;

		if(k == 0)
			dir = ForgeDirection.getOrientation(2);
		if(k == 1)
			dir = ForgeDirection.getOrientation(5);
		if(k == 2)
			dir = ForgeDirection.getOrientation(3);
		if(k == 3)
			dir = ForgeDirection.getOrientation(4);

		final ForgeDirection dir2 = dir.getRotation(ForgeDirection.UP);

		//back connector
		this.makeExtra(world, pos.getX() + dir.offsetX * -4, pos.getY(), pos.getZ() + dir.offsetZ * -4);
		//front connector
		this.makeExtra(world, pos.getX(), pos.getY(), pos.getZ());

		final int xc = pos.getX() - dir.offsetX;
		final int zc = pos.getZ() - dir.offsetZ;

		//side connectors
		this.makeExtra(world, xc + dir2.offsetX, pos.getY(), zc + dir2.offsetZ);
		this.makeExtra(world, xc - dir2.offsetX, pos.getY(), zc - dir2.offsetZ);
	}
	
}
