package com.hbm.items.weapon;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import com.hbm.handler.MissileStruct;
import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemMissile.FuelType;
import com.hbm.items.weapon.ItemMissile.WarheadType;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCustomMissile extends Item {

	public ItemCustomMissile(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModItems.ALL_ITEMS.add(this);
	}

	public static ItemStack buildMissile(final Item chip, final Item warhead, final Item fuselage, final Item stability, final Item thruster) {

		if(stability == null) {
			return buildMissile(ItemStackUtil.itemStackFrom(chip), ItemStackUtil.itemStackFrom(warhead), ItemStackUtil.itemStackFrom(fuselage), null, ItemStackUtil.itemStackFrom(thruster));
		} else {
			return buildMissile(ItemStackUtil.itemStackFrom(chip), ItemStackUtil.itemStackFrom(warhead), ItemStackUtil.itemStackFrom(fuselage), ItemStackUtil.itemStackFrom(stability), ItemStackUtil.itemStackFrom(thruster));
		}
	}

	public static ItemStack buildMissile(final ItemStack chip, final ItemStack warhead, final ItemStack fuselage, final ItemStack stability, final ItemStack thruster) {

		final ItemStack missile = ItemStackUtil.itemStackFrom(ModItems.missile_custom);

		writeToNBT(missile, "chip", Item.getIdFromItem(chip.getItem()));
		writeToNBT(missile, "warhead", Item.getIdFromItem(warhead.getItem()));
		writeToNBT(missile, "fuselage", Item.getIdFromItem(fuselage.getItem()));
		writeToNBT(missile, "thruster", Item.getIdFromItem(thruster.getItem()));

		if(stability != null)
			writeToNBT(missile, "stability", Item.getIdFromItem(stability.getItem()));

		return missile;
	}

	private static void writeToNBT(final ItemStack stack, final String key, final int value) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger(key, value);
	}

	public static int readFromNBT(final ItemStack stack, final String key) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound().getInteger(key);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		try {
			final ItemMissile chip = (ItemMissile) Item.getItemById(readFromNBT(stack, "chip"));
			final ItemMissile warhead = (ItemMissile) Item.getItemById(readFromNBT(stack, "warhead"));
			final ItemMissile fuselage = (ItemMissile) Item.getItemById(readFromNBT(stack, "fuselage"));
			ItemMissile stability = null;
			final Item item = Item.getItemById(readFromNBT(stack, "stability"));
			if(item instanceof ItemMissile)
				stability = (ItemMissile) item;
			final ItemMissile thruster = (ItemMissile) Item.getItemById(readFromNBT(stack, "thruster"));

			tooltip.add(TextFormatting.BOLD + "Warhead: " + TextFormatting.GRAY + warhead.getWarhead((WarheadType) warhead.attributes[0]));
			tooltip.add(TextFormatting.BOLD + "Strength: " + TextFormatting.RED + warhead.attributes[1]);
			tooltip.add(TextFormatting.BOLD + "Fuel Type: " + TextFormatting.GRAY + fuselage.getFuel((FuelType) fuselage.attributes[0]));
			tooltip.add(TextFormatting.BOLD + "Fuel amount: " + TextFormatting.GRAY + fuselage.attributes[1] + "l");
			tooltip.add(TextFormatting.BOLD + "Chip inaccuracy: " + TextFormatting.GRAY + (Float) chip.attributes[0] * 100 + "%");

			if(stability != null)
				tooltip.add(TextFormatting.BOLD + "Fin inaccuracy: " + TextFormatting.GRAY + (Float) stability.attributes[0] * 100 + "%");
			else
				tooltip.add(TextFormatting.BOLD + "Fin inaccuracy: " + TextFormatting.GRAY + "100%");

			tooltip.add(TextFormatting.BOLD + "Size: " + TextFormatting.GRAY + fuselage.getSize(fuselage.top) + "/" + fuselage.getSize(fuselage.bottom));

			float health = warhead.health + fuselage.health + thruster.health;
			if(stability != null)
				health += stability.health;

			tooltip.add(TextFormatting.BOLD + "Health: " + TextFormatting.GREEN + health + "HP");
		} catch(final ClassCastException x) {
			//Drillgon200: Why is this even necessary, JEI?
        }
	}

	public static MissileStruct getStruct(final ItemStack stack) {

		if(stack == null || stack.isEmpty() || stack.getTagCompound() == null || !(stack.getItem() instanceof ItemCustomMissile))
			return null;
		// This is a stupid fix
		try {
			final ItemMissile warhead = (ItemMissile) Item.getItemById(readFromNBT(stack, "warhead"));
			final ItemMissile fuselage = (ItemMissile) Item.getItemById(readFromNBT(stack, "fuselage"));
			ItemMissile stability = null;
			final Item item = Item.getItemById(readFromNBT(stack, "stability"));
			if(item instanceof ItemMissile)
				stability = (ItemMissile) item;
			final ItemMissile thruster = (ItemMissile) Item.getItemById(readFromNBT(stack, "thruster"));

			final MissileStruct missile = new MissileStruct(warhead, fuselage, stability, thruster);

			return missile;
		} catch(final ClassCastException x) {
			return null;
		}

	}
}
