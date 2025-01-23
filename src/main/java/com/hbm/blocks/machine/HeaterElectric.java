package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;

import api.hbm.block.IToolable;
import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityProxyEnergy;
import com.hbm.tileentity.machine.TileEntityHeaterElectric;
import com.hbm.util.I18nUtil;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class HeaterElectric extends BlockDummyable implements ILookOverlay, ITooltipProvider, IToolable {

	public HeaterElectric(final Material mat, final String s) {
        super(mat, s);
    }

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		
		if(meta >= 12)
			return new TileEntityHeaterElectric();
		
		if(hasExtra(meta))
			return new TileEntityProxyEnergy();
		
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {0, 0, 1, 2, 1, 1};
	}

	@Override
	public int getOffset() {
		return 2;
	}

	@Override
	public void fillSpace(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		super.fillSpace(world, x, y, z, dir, o);
		this.makeExtra(world, x, y, z);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
        this.addStandardInfo(tooltip);
    }

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
		
		final int[] pos = this.findCore(world, x, y, z);
		
		if(pos == null)
			return;
		
		final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		
		if(!(te instanceof TileEntityHeaterElectric heater))
			return;

        final List<String> text = new ArrayList();
		text.add(String.format("%,d", heater.heatEnergy) + " TU");
		text.add("§a-> §r" + heater.getConsumption() + " HE/t");
		text.add("§c<- §r" + heater.getHeatGen() + " TU/t");
		
		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
	}

	@Override
    public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand hand, final ToolType tool) {
   		if(tool != ToolType.SCREWDRIVER && tool != ToolType.HAND_DRILL)
			return false;
		
		if(world.isRemote) return true;
		
		final int[] pos = this.findCore(world, x, y, z);
		
		if(pos == null) return false;
		
		final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		
		if(!(te instanceof TileEntityHeaterElectric tile)) return false;

        if(tool == ToolType.SCREWDRIVER)
            tile.toggleSettingUp();
        else
            tile.toggleSettingDown();
		tile.markDirty();
		
		return true;
	}
}
