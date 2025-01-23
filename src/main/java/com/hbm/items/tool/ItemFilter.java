package com.hbm.items.tool;


import api.hbm.item.IGasMask;
import com.hbm.handler.ArmorModHandler;
import com.hbm.handler.ArmorUtil;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemFilter extends Item {
	
	public ItemFilter(final String s, final int durability) {
		this.setMaxDamage(durability);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		
		final ItemStack helmet = player.inventory.armorInventory.get(3);
		final ItemStack stack = player.getHeldItem(hand);
		if(helmet == null || helmet.isEmpty())
			return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
		
		if(!(helmet.getItem() instanceof IGasMask)) {
			
			if(ArmorModHandler.hasMods(helmet)) {
				final ItemStack[] mods = ArmorModHandler.pryMods(helmet);
				
				if(mods[ArmorModHandler.helmet_only] != null) {
					final ItemStack mask = mods[ArmorModHandler.helmet_only];
					
					final ItemStack ret = installFilterOn(mask, stack, world, player);
					ArmorModHandler.applyMod(helmet, mask);
					return ActionResult.newResult(EnumActionResult.SUCCESS, ret);
				}
			}
		}
		
		return ActionResult.newResult(EnumActionResult.SUCCESS, installFilterOn(helmet, stack, world, player));
	}
	
	private ItemStack installFilterOn(final ItemStack helmet, ItemStack filter, final World world, final EntityPlayer player) {
		
		if(!(helmet.getItem() instanceof IGasMask mask)) {
			return filter;
		}

        if(!mask.isFilterApplicable(helmet, filter))
			return filter;
		
		final ItemStack copy = filter.copy();
		final ItemStack current = ArmorUtil.getGasMaskFilter(helmet);
		
		if(current != null) {
			filter = current;
		} else {
			filter.shrink(1);
		}
		
		ArmorUtil.installGasMaskFilter(helmet, copy);
		
		world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.gasmaskScrew, SoundCategory.PLAYERS, 1.0F, 1.0F);
				
		return filter;
	}
}
