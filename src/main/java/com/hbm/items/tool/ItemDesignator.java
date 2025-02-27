package com.hbm.items.tool;

import com.hbm.blocks.bomb.LaunchPad;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemDesignator extends Item {

	public ItemDesignator(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.missileTab);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void onCreated(final ItemStack stack, final World worldIn, final EntityPlayer playerIn) {
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("xCoord", 0);
		stack.getTagCompound().setInteger("zCoord", 0);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if(stack.getTagCompound() != null)
		{
			tooltip.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.targetcoord")+"§r");
			tooltip.add("§aX: " + stack.getTagCompound().getInteger("xCoord") + "§r");
			tooltip.add("§aZ: " + stack.getTagCompound().getInteger("zCoord") + "§r");
		} else {
			tooltip.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.choosetarget1"));
		}
	}
	
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		final ItemStack stack = player.getHeldItem(hand);
		if(!(world.getBlockState(pos).getBlock() instanceof LaunchPad))
		{
			if(stack.getTagCompound() != null)
			{
				stack.getTagCompound().setInteger("xCoord", pos.getX());
				stack.getTagCompound().setInteger("zCoord", pos.getZ());
			} else {
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setInteger("xCoord", pos.getX());
				stack.getTagCompound().setInteger("zCoord", pos.getZ());
			}
	        if(world.isRemote)
			{
	        	player.sendMessage(new TextComponentTranslation(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("chat.posset") + "]"));
			}

	        world.playSound(player.posX, player.posY, player.posZ, HBMSoundHandler.techBleep, SoundCategory.PLAYERS, 1.0F, 1.0F, true);
        	
	        return EnumActionResult.SUCCESS;
		}
    	
        return EnumActionResult.PASS;
	}
}
