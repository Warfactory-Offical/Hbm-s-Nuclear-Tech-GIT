package com.hbm.items.tool;

import com.hbm.blocks.machine.rbmk.RBMKBase;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDyatlov extends Item {

	public ItemDyatlov(final String s){
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(!world.isRemote) {
			
			if(world.getBlockState(pos).getBlock() instanceof RBMKBase rbmk) {

                final int[] pos1 = rbmk.findCore(world, pos.getX(), pos.getY(), pos.getZ());
				
				if(pos1 != null) {
					
					final TileEntity te = world.getTileEntity(new BlockPos(pos1[0], pos1[1], pos1[2]));
					
					if(te instanceof TileEntityRBMKBase) {

						((TileEntityRBMKBase)te).meltdown();
						//((TileEntityRBMKBase)te).heat = 100000;
					}
				}
			}
		}
		
		return EnumActionResult.PASS;
	}
	
}