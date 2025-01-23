package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ILookOverlay;
import com.hbm.items.ModItems;
import com.hbm.util.I18nUtil;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityMachineTeleporter;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class MachineTeleporter extends BlockContainer implements ILookOverlay {

	public MachineTeleporter(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityMachineTeleporter();
	}
	
	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
		
		final TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		
		if(!(tile instanceof TileEntityMachineTeleporter tele)) return;

        final List<String> text = new ArrayList();
		
		text.add((tele.power >= TileEntityMachineTeleporter.consumption ? "§a" : "§c") + String.format("%,d", tele.power) + " / " + String.format("%,d", TileEntityMachineTeleporter.maxPower));
		if(tele.target == null) {
			text.add("§cNo destination set!");
		} else {
			text.add("Destination: " + tele.target.getX() + " / " + tele.target.getY() + " / " + tele.target.getZ());
		}
		
		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}
