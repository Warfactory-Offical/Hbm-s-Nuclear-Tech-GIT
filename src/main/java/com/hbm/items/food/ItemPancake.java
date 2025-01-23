package com.hbm.items.food;

import api.hbm.energy.IBatteryItem;
import com.hbm.items.ModItems;
import com.hbm.items.gear.ArmorFSB;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemPancake extends ItemFood {

	public ItemPancake(final int amount, final float saturation, final boolean isWolfFood, final String s) {
		super(amount, saturation, isWolfFood);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setAlwaysEdible();
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	protected void onFoodEaten(final ItemStack stack, final World worldIn, final EntityPlayer player) {
		for(final ItemStack st : player.inventory.armorInventory) {

    		if(st == null)
    			continue;

    		if(st.getItem() instanceof IBatteryItem) {
    			((IBatteryItem)st.getItem()).setCharge(st, ((IBatteryItem)st.getItem()).getMaxCharge());
    		}
    	}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		if(ArmorFSB.hasFSBArmorIgnoreCharge(player) && player.inventory.armorInventory.get(3).getItem() == ModItems.bj_helmet) {
        	return super.onItemRightClick(world, player, hand);
    	}

    	if(!world.isRemote)
    		player.sendMessage(new TextComponentString(TextFormatting.YELLOW + "Your teeth are too soft to eat this."));
		return ActionResult.newResult(EnumActionResult.FAIL, player.getHeldItem(hand));
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		list.add("Can be eaten to recharge lunar cybernetic armor");
		list.add("Not for people with weak molars");
		list.add("");
		list.add("Half burnt and smells horrible");
	}
}
