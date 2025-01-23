package com.hbm.items.tool;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.util.ItemStackUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemMS extends Item {

	public ItemMS(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		tooltip.add("Lost but not forgotten");
	}
	
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(!world.isRemote) {
    		if(world.getBlockState(pos).getBlock() == ModBlocks.ntm_dirt) {
    			
				world.destroyBlock(pos, false);

    	    	final Random rand = new Random();
    	    	final List<ItemStack> list = new ArrayList<ItemStack>();

    	    	list.add(ItemStackUtil.itemStackFrom(ModItems.ingot_u238m2, 1, 1));
    	    	list.add(ItemStackUtil.itemStackFrom(ModItems.ingot_u238m2, 1, 2));
    	    	list.add(ItemStackUtil.itemStackFrom(ModItems.ingot_u238m2, 1, 3));
    	    	
    	    	for(final ItemStack sta : list) {
    	            final float f = rand.nextFloat() * 0.8F + 0.1F;
    	            final float f1 = rand.nextFloat() * 0.8F + 0.1F;
    	            final float f2 = rand.nextFloat() * 0.8F + 0.1F;
    	            final EntityItem entityitem = new EntityItem(world, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2, sta);

    	            final float f3 = 0.05F;
    	            entityitem.motionX = (float)rand.nextGaussian() * f3;
    	            entityitem.motionY = (float)rand.nextGaussian() * f3 + 0.2F;
    	            entityitem.motionZ = (float)rand.nextGaussian() * f3;
    	            
    	            world.spawnEntity(entityitem);
    	    	}
    			return EnumActionResult.SUCCESS;
    		}
    	}
    	
        return EnumActionResult.PASS;
	}
}
