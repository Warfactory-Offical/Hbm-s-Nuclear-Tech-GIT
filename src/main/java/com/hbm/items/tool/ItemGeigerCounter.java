package com.hbm.items.tool;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.items.gear.ArmorFSB;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ItemStackUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import java.util.ArrayList;
import java.util.List;

@Optional.InterfaceList({@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")})
public class ItemGeigerCounter extends Item implements IBauble {
	
	public ItemGeigerCounter(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int itemSlot, final boolean isSelected) {

		if(!(entity instanceof EntityLivingBase) || world.isRemote)
			return;
		
		if(entity instanceof EntityPlayer) {
			
			if(ArmorFSB.hasFSBArmor((EntityPlayer)entity) && ((ArmorFSB)((EntityPlayer)entity).inventory.armorInventory.get(2).getItem()).geigerSound)
				return;
			
			playGeiger(world, (EntityPlayer)entity);
		}
	}

	public static void playGeiger(final World world, final EntityPlayer player){
		final double x = ContaminationUtil.getActualPlayerRads(player);
		
		if(world.getTotalWorldTime() % 5 == 0) {

			if(x > 0.001) {
				final List<Integer> list = new ArrayList<Integer>();

				if(x < 1){
					list.add(0);
				}
				if(x < 5){
					list.add(0);
				}
				if(2 < x && x < 10){
					list.add(1);
				}
				if(5 < x && x < 20){
					list.add(2);
				}
				if(15 < x && x < 40){
					list.add(3);
				}
				if(30 < x && x < 80){
					list.add(4);
				}
				if(60 < x && x < 160){
					list.add(5);
				}
				if(120 < x && x < 320){
					list.add(6);
				}
				if(240 < x && x < 640){
					list.add(7);
				}
				if(480 < x){
					list.add(8);
				}
				if(list.size() > 0){
					final int r = list.get(world.rand.nextInt(list.size()));
					
					if(r > 0){
						world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.geigerSounds[r-1], SoundCategory.PLAYERS, 1.0F, 1.0F);
					}
				}
			} else if(world.rand.nextInt(100) == 0) {
				world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.geigerSounds[(world.rand.nextInt(1))], SoundCategory.PLAYERS, 1.0F, 1.0F);
			}
		}
	}
	
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.getBlockState(pos).getBlock() == ModBlocks.block_red_copper) {
    		Library.consumeInventoryItem(player.inventory, ModItems.geiger_counter);
    		player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.survey_scanner));
    		return EnumActionResult.SUCCESS;
    	}
    	
    	return EnumActionResult.PASS;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand handIn) {
		if(!world.isRemote) {
	    	world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.techBoop, SoundCategory.PLAYERS, 1.0F, 1.0F);

	    	ContaminationUtil.printGeigerData(player);
		}
		
		return super.onItemRightClick(world, player, handIn);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(final ItemStack oldStack, final ItemStack newStack, final boolean slotChanged) {
		return !ItemStack.areItemsEqual(oldStack, newStack);
	}

	@Override
	public BaubleType getBaubleType(final ItemStack itemstack){
		return BaubleType.TRINKET;
	}

	@Override
	public void onWornTick(final ItemStack itemstack, final EntityLivingBase player) {
		onUpdate(itemstack, player.world, player, 0, true);
	}
}
