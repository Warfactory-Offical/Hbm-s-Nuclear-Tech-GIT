package com.hbm.items.special;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.util.I18nUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemDemonCore extends ItemHazard {

	public ItemDemonCore(final String s){
		super(s);
	}
	
	@Override
	public boolean onEntityItemUpdate(final EntityItem entityItem){
		if(entityItem != null && !entityItem.world.isRemote && entityItem.onGround) {
			entityItem.setItem(ItemStackUtil.itemStackFrom(ModItems.demon_core_closed));
			entityItem.world.spawnEntity(new EntityItem(entityItem.world, entityItem.posX, entityItem.posY, entityItem.posZ, ItemStackUtil.itemStackFrom(ModItems.screwdriver)));
			return true;
		}
		return false;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World world, final List<String> list, final ITooltipFlag flagIn){
		//super.addInformation(stack, world, list, flagIn);
		list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.drop") + "]");
	}

}
