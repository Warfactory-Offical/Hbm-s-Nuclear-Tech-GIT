package com.hbm.items.tool;

import api.hbm.energy.IEnergyConnector;
import com.hbm.interfaces.IFluidPipe;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.TileEntityDummy;
import com.hbm.tileentity.machine.TileEntityLockableBase;
import com.hbm.tileentity.network.energy.TileEntityPylonBase;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;

public class ItemAnalyzer extends Item {

	public ItemAnalyzer(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		final Block block = world.getBlockState(pos).getBlock();
		TileEntity te = world.getTileEntity(pos);
		
		if(world.isRemote) {
			player.sendMessage(new TextComponentString(
					"Block: " + I18n.format(block.getTranslationKey() + ".name") + " (" + block.getTranslationKey() + ")"
					));
			
			player.sendMessage(new TextComponentString(
					"Meta: " + block.getMetaFromState(world.getBlockState(pos))
					));
		}
		
		if(!world.isRemote) {
			
			if(te == null) {
				player.sendMessage(new TextComponentString(
						"Tile Entity: none"));
			} else {
				
				if(te instanceof TileEntityDummy) {

					player.sendMessage(new TextComponentString(
							"Dummy Block, references TE at " + ((TileEntityDummy)te).target.getX() + " / " + ((TileEntityDummy)te).target.getY() + " / " + ((TileEntityDummy)te).target.getZ()));
					
					te = world.getTileEntity(((TileEntityDummy)te).target);
				}
				
				String[] parts = te.toString().split("\\.");
				
				if(parts.length == 0)
					parts = new String[]{"error"};
				
				final String post = parts[parts.length - 1];
				final String name = post.split("@")[0];

				player.sendMessage(new TextComponentString(
						"Tile Entity: " + name));
				
				if(te instanceof IInventory) {
					
					player.sendMessage(new TextComponentString(
							"Slots: " + ((IInventory)te).getSizeInventory()));
				}
				
				if(te instanceof IEnergyConnector) {
					
					player.sendMessage(new TextComponentString(
							"Electricity: " + ((IEnergyConnector)te).getPower() + " HE"));
				}
				
				if(te instanceof IFluidPipe) {
					
					player.sendMessage(new TextComponentString(
							"Duct Type: " + I18n.format(((IFluidPipe)te).getType().getUnlocalizedName())));
				}
				
				if(te instanceof TileEntityPylonBase) {
					
					player.sendMessage(new TextComponentString(
							"Connections:"));
					
					final List<BlockPos> connections = ((TileEntityPylonBase)te).connected;
					
					for(int i = 0; i < connections.size(); i++) {
						player.sendMessage(new TextComponentString(
								" *" + connections.get(i).getX() + " / " + connections.get(i).getY() + " / " + connections.get(i).getZ()));
					}
				}
				
				if(te instanceof TileEntityLockableBase) {
					
					player.sendMessage(new TextComponentString(
							"Locked: " + ((TileEntityLockableBase)te).isLocked()));
					
					if(((TileEntityLockableBase)te).isLocked()) {

						//player.sendMessage(new TextComponentString(
						//		"Pins: " + ((TileEntityLockableBase)te).getPins()));
						player.sendMessage(new TextComponentString(
								"Pick Chance: " + (((TileEntityLockableBase)te).getMod() * 100D) + "%"));
					}
				}
			}

			player.sendMessage(new TextComponentString(
					"----------------------------"
					));
		}
		
		return EnumActionResult.SUCCESS;
	}
}
