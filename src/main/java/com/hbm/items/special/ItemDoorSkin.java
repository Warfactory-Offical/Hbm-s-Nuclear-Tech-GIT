package com.hbm.items.special;

import com.hbm.interfaces.IDoor;
import com.hbm.items.ModItems;
import com.hbm.blocks.BlockDummyable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDoorSkin extends Item {

	public String tex;
	
	public ItemDoorSkin(final String s, final String tex) {
		this.tex = tex;
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World worldIn, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(!worldIn.isRemote){

			if(worldIn.getBlockState(pos).getBlock() instanceof BlockDummyable){
				final int[] pos1 = ((BlockDummyable) worldIn.getBlockState(pos).getBlock()).findCore(worldIn, pos.getX(), pos.getY(), pos.getZ());
				if(pos1 != null){
					final TileEntity te = worldIn.getTileEntity(new BlockPos(pos1[0], pos1[1], pos1[2]));
					if(te instanceof IDoor){
						if(((IDoor) te).setTexture(tex)){
							return EnumActionResult.SUCCESS;
						}
					}
				}
			}
		}
		return EnumActionResult.PASS;
	}
}
