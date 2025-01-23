package com.hbm.items.tool;

import com.hbm.entity.mob.EntityQuackos;
import com.hbm.items.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemPeas extends Item {

	public ItemPeas(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		if (!player.capabilities.isCreativeMode) {
			player.getHeldItem(hand).shrink(1);
		}
		
		final List<EntityQuackos> quacc = world.getEntitiesWithinAABB(EntityQuackos.class, player.getEntityBoundingBox().grow(50, 50, 50));
		
		for(final EntityQuackos ducc : quacc) {
			ducc.despawn();
		}
		
		return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		tooltip.add("He accepts your offering.");
	}
}
