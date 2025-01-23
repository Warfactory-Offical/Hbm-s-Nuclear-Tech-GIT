package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;

import com.hbm.lib.Library;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.machine.TileEntityCharger;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class MachineCharger extends BlockContainer implements ITooltipProvider, ILookOverlay {
	
	public final long maxThroughput;
	public final boolean pointingUp;

	public MachineCharger(final Material mat, final String s, final long max, final boolean pointingUp) {
		super(mat);
		this.maxThroughput = max / 20L;
		this.pointingUp = pointingUp;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		return new TileEntityCharger();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
    	if(maxThroughput == Long.MAX_VALUE / 20L)
    		tooltip.add("§aMax Chargerate: Infinite HE/s");
    	else
    		tooltip.add("§aMax Chargerate: "+Library.getShortNumber(20 * maxThroughput)+"HE/s");
        this.addStandardInfo(tooltip);
        super.addInformation(stack, player, tooltip, advanced);
    }

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
		final TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		if(!(te instanceof TileEntityCharger charger))
			return;

        final List<String> text = new ArrayList();

		if(charger.totalCapacity > 0){
			text.add(Library.getShortNumber(charger.totalEnergy) + "/" + Library.getShortNumber(charger.totalCapacity) + " HE");
			text.add("§a-> §r" + Library.getShortNumber(20 * charger.actualCharge) + "/" + Library.getShortNumber(20 * charger.charge) + "HE/s");
			text.add("&["+Library.getColorProgress((double)charger.totalEnergy/(double)charger.totalCapacity)+"&]    "+Library.getPercentage((double)charger.totalEnergy/(double)charger.totalCapacity)+"%");
		} else {
			text.add("Nothing to charge");
		}
		ILookOverlay.printGeneric(event, getLocalizedName(), 0xffff00, 0x404000, text);
	}
}
