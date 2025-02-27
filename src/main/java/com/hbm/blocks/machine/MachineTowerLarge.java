package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.util.I18nUtil;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.BlockDummyable;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityTowerLarge;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class MachineTowerLarge extends BlockDummyable implements ILookOverlay {

	public MachineTowerLarge(final Material mat, final String s) {
		super(mat, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World p_149915_1_, final int meta) {
		
		if(meta >= 12)
			return new TileEntityTowerLarge();
		
		if(meta >= 8)
			return new TileEntityProxyCombo(false, false, true);
		
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {12, 0, 4, 4, 4, 4};
	}

	@Override
	public int getOffset() {
		return 4;
	}

	@Override
	public void fillSpace(final World world, int x, final int y, int z, final ForgeDirection dir, final int o) {
		super.fillSpace(world, x, y, z, dir, o);
		
		x = x + dir.offsetX * o;
		z = z + dir.offsetZ * o;
		
		for(int i = 2; i <= 6; i++) {
			final ForgeDirection dr2 = ForgeDirection.getOrientation(i);
			final ForgeDirection rot = dr2.getRotation(ForgeDirection.UP);
			this.makeExtra(world, x + dr2.offsetX * 4, y, z + dr2.offsetZ * 4);
			this.makeExtra(world, x + dr2.offsetX * 4 + rot.offsetX * 3, y, z + dr2.offsetZ * 4 + rot.offsetZ * 3);
			this.makeExtra(world, x + dr2.offsetX * 4 + rot.offsetX * -3, y, z + dr2.offsetZ * 4 + rot.offsetZ * -3);
		}
	}

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
		final int[] pos = this.findCore(world, x, y, z);
		
		if(pos == null)
			return;
		
		final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		
		if(!(te instanceof TileEntityTowerLarge chungus))
			return;

        final List<String> text = new ArrayList();

		text.add("§a-> §r" + ModForgeFluids.spentsteam.getLocalizedName(new FluidStack(ModForgeFluids.spentsteam, 1)) + ": " + chungus.tanks[0].getFluidAmount() + "/" + chungus.tanks[0].getCapacity() + "mB");
		text.add("§c<- §r" + FluidRegistry.WATER.getLocalizedName(new FluidStack(FluidRegistry.WATER, 1)) + ": " + chungus.tanks[1].getFluidAmount() + "/" + chungus.tanks[1].getCapacity() + "mB");

		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
	}
}