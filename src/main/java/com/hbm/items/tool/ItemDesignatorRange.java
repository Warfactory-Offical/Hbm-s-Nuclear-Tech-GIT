package com.hbm.items.tool;

import com.hbm.blocks.bomb.LaunchPad;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemDesignatorRange extends Item {

	public ItemDesignatorRange(final String s) {
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
			tooltip.add("§aX: " + stack.getTagCompound().getInteger("xCoord") +"§r");
			tooltip.add("§aZ: " + stack.getTagCompound().getInteger("zCoord") +"§r");
		} else {
			tooltip.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.choosetarget3"));
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		final RayTraceResult mpos = Library.rayTrace(player, 300, 1);
		if(mpos.typeOfHit != Type.BLOCK)
			return super.onItemRightClick(world, player, hand);
		final int x = mpos.getBlockPos().getX();
		final int z = mpos.getBlockPos().getZ();
		final BlockPos pos = mpos.getBlockPos();
		final ItemStack stack = player.getHeldItem(hand);
		
		if(!(world.getBlockState(pos) instanceof LaunchPad))
		{
			if(stack.getTagCompound() == null)
				stack.setTagCompound(new NBTTagCompound());
			
			stack.getTagCompound().setInteger("xCoord", x);
			stack.getTagCompound().setInteger("zCoord", z);
			
	        if(world.isRemote)
			{
	        	player.sendMessage(new TextComponentTranslation(TextFormatting.GREEN + I18nUtil.resolveKey("chat.possetxz", x, z)));
			}
	        
        	world.playSound(player.posX, player.posY, player.posZ, HBMSoundHandler.techBleep, SoundCategory.PLAYERS, 1.0F, 1.0F, true);
        	
	        return super.onItemRightClick(world, player, hand);
		}
    	
        return super.onItemRightClick(world, player, hand);
	}
}
