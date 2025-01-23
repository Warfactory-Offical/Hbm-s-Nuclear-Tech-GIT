package com.hbm.items.weapon;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import com.hbm.items.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GunB92Cell extends Item {

	public GunB92Cell(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void onUpdate(final ItemStack stack, final World worldIn, final Entity entity, final int itemSlot, final boolean isSelected) {
		if(entity instanceof EntityPlayer player && getPower(stack) < 25) {

            for(int j = 0; j < player.inventory.mainInventory.size(); j++) {
				if(player.inventory.mainInventory.get(j).getItem() == ModItems.gun_b92) {
					final int p = getPower(player.inventory.mainInventory.get(j));
					if(p > 1) {
						setPower(player.inventory.mainInventory.get(j), p - 1);
						setPower(stack, getPower(stack) + 1);
						//if(getPower(stack) == 25)
							//stack.setItemDamage(1);
						return;
					}
				}
			}
		}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		list.add("Draws energy from the B92, allowing you to");
		list.add("reload it an additional 25 times.");
		list.add("The cell will permanently hold it's charge,");
		list.add("it is not meant to be used as a battery enhancement");
		list.add("for the B92, but rather as a bomb.");
		list.add("");
		list.add("Charges: " + getPower(stack) + " / 25");
	}
	
	private static int getPower(final ItemStack stack) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}

		return stack.getTagCompound().getInteger("energy");

	}

	private static void setPower(final ItemStack stack, final int i) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		stack.getTagCompound().setInteger("energy", i);

	}
	
	public static ItemStack getFullCell() {
		final ItemStack stack = ItemStackUtil.itemStackFrom(ModItems.gun_b92_ammo, 1, 0);
		setPower(stack, 25);
		return stack.copy();
	}
}
