package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import com.hbm.util.ItemStackUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class ItemModInk extends ItemArmorMod {

	public ItemModInk(final String s) {
		super(ArmorModHandler.extra, true, true, true, true, s);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.LIGHT_PURPLE + "10% chance to nullify damage");
		list.add(TextFormatting.LIGHT_PURPLE + "Flowers!");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@Override
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.LIGHT_PURPLE + "  " + stack.getDisplayName() + " (10% chance to nullify damage)");
	}
	
	@Override
	public void modDamage(final LivingHurtEvent event, final ItemStack armor) {
		
		if(event.getEntity().world.rand.nextInt(10) == 0) {
			event.setAmount(0);
			
			if(!event.getEntity().world.isRemote) {
				
				if(event.getEntity().world.rand.nextInt(10) == 0)
					event.getEntity().entityDropItem(ItemStackUtil.itemStackFrom(Blocks.YELLOW_FLOWER), 1.0F);
				
				event.getEntity().entityDropItem(ItemStackUtil.itemStackFrom(Blocks.RED_FLOWER, 1, event.getEntity().world.rand.nextInt(9)), 1.0F);
			}
		}
	}
}