package com.hbm.util;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.hbm.inventory.RecipesCommon.ComparableStack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

public class ItemStackUtil {

	public static ItemStack carefulCopy(ItemStack stack) {
		if(stack == null)
			return null;
		else
			return stack.copy();
	}

	/**
	 * Creates a new array that only contains the copied range.
	 * @param inv
	 * @param start
	 * @param end
	 * @return copied items
	 */
	@Nonnull
	public static ItemStack[] carefulCopyArrayTruncate(@Nonnull IItemHandler inv, int start, int end) {
		if (end < start) {
			throw new IllegalArgumentException("end must be >= start");
		}

		int length = end - start + 1;
		ItemStack[] copy = new ItemStack[length];
		for (int idx = 0; idx < length; idx++) {
			copy[idx] = carefulCopy(inv.getStackInSlot(start + idx));
		}

		return copy;
	}

	public static ItemStack carefulCopyWithSize(ItemStack stack, int size) {
		if(stack == null)
			return null;

		ItemStack copy = stack.copy();
		copy.setCount(size);
		return copy;
	}

	/**
	 * Runs carefulCopy over the entire ItemStack array.
	 * @param array
	 * @return
	 */
	public static ItemStack[] carefulCopyArray(ItemStack[] array) {
		return carefulCopyArray(array, 0, array.length - 1);
	}

	/**
	 * Recreates the ItemStack array and only runs carefulCopy over the supplied range. All other fields remain null.
	 * @param array
	 * @param start
	 * @param end
	 * @return
	 */
	public static ItemStack[] carefulCopyArray(ItemStack[] array, int start, int end) {
		if(array == null)
			return null;

		ItemStack[] copy = new ItemStack[array.length];

		for(int i = start; i <= end; i++) {
			copy[i] = carefulCopy(array[i]);
		}

		return copy;
	}

	/**
	 * Creates a new array that only contains the copied range.
	 * @param array
	 * @param start
	 * @param end
	 * @return
	 */
	public static ItemStack[] carefulCopyArrayTruncate(ItemStack[] array, int start, int end) {
		if(array == null)
			return null;

		int length = end - start + 1;
		ItemStack[] copy = new ItemStack[length];

		for(int i = 0; i < length; i++) {
			copy[i] = carefulCopy(array[start + i]);
		}

		return copy;
	}

	/**
	 * UNSAFE! Will ignore all existing display tags and override them! In its current state, only fit for items we know don't have any display tags!
	 * Will, however, respect existing NBT tags
	 * @param stack
	 * @param lines
	 */
	public static void addTooltipToStack(ItemStack stack, String... lines) {

		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		NBTTagCompound display = new NBTTagCompound();
		NBTTagList lore = new NBTTagList();

		for(String line : lines) {
			lore.appendTag(new NBTTagString(TextFormatting.RESET + "" + TextFormatting.GRAY + line));
		}

		display.setTag("Lore", lore);
		stack.getTagCompound().setTag("display", display);
	}

	public static void addStacksToNBT(ItemStack stack, ItemStack... stacks) {

		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		NBTTagList tags = new NBTTagList();

		for(int i = 0; i < stacks.length; i++) {
			if(stacks[i] != null) {
				NBTTagCompound slotNBT = new NBTTagCompound();
				slotNBT.setByte("slot", (byte) i);
				stacks[i].writeToNBT(slotNBT);
				tags.appendTag(slotNBT);
			}
		}
		stack.getTagCompound().setTag("items", tags);
	}

	public static ItemStack[] readStacksFromNBT(ItemStack stack) {

		if(!stack.hasTagCompound())
			return null;

		NBTTagList list = stack.getTagCompound().getTagList("items", 10);
		int count = list.tagCount();

		ItemStack[] stacks = new ItemStack[count];

		for(int i = 0; i < count; i++) {
			NBTTagCompound slotNBT = list.getCompoundTagAt(i);
			byte slot = slotNBT.getByte("slot");
			if(slot >= 0 && slot < stacks.length) {
				stacks[slot] = new ItemStack (slotNBT);
			}
		}

		return stacks;
	}

	/**
	 * Returns a List<String> of all ore dict names for this stack. Stack cannot be null, list is empty when there are no ore dict entries.
	 * @param stack
	 * @return
	 */
	public static List<String> getOreDictNames(ItemStack stack) {
		List<String> list = new ArrayList();

		int[] ids = OreDictionary.getOreIDs(stack);
		for(int i : ids) {
			list.add(OreDictionary.getOreName(i));
		}

		return list;
	}

	public static boolean isSameMetaItem(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() == stack2.getItem() && stack1.getMetadata() == stack2.getMetadata();
	}

	public static boolean isSameMetaItem(ItemStack stack, Item item) {
		return stack.getItem() == item;
	}

	// ItemStack from Item

	public static ItemStack itemStackFrom(Item item) {
		return new ItemStack(item);
	}

	public static ItemStack itemStackFrom(Item item, int amount) {
		return new ItemStack(item, amount);
	}

	public static ItemStack itemStackFrom(Item item, int amount, int meta) {
		return new ItemStack(item, amount, meta);
	}

	// ItemStack from Block

	public static ItemStack itemStackFrom(Block block) {
		return new ItemStack(block);
	}

	public static ItemStack itemStackFrom(Block block, int amount) {
		return new ItemStack(block, amount);
	}

	public static ItemStack itemStackFrom(Block block, int amount, int meta) {
		return new ItemStack(block, amount, meta);
	}

	// ItemStack from ItemStack, required for MetaItems

	@Deprecated
	public static ItemStack itemStackFrom(ItemStack stack) {
		return stack;
	}

	public static ItemStack itemStackFrom(ItemStack stack, int amount) {
		return new ItemStack(stack.getItem(), amount, stack.getMetadata());
	}

	// ComparableStack from Item

	public static ComparableStack comparableStackFrom(Item item) {
		return new ComparableStack(item);
	}

	public static ComparableStack comparableStackFrom(Item item, int amount) {
		return new ComparableStack(item, amount);
	}

	public static ComparableStack comparableStackFrom(Item item, int amount, int meta) {
		return new ComparableStack(item, amount, meta);
	}

	// ComparableStack from Block

	public static ComparableStack comparableStackFrom(Block block) {
		return new ComparableStack(block);
	}

	public static ComparableStack comparableStackFrom(Block block, int amount) {
		return new ComparableStack(block, amount);
	}

	public static ComparableStack comparableStackFrom(Block block, int amount, int meta) {
		return new ComparableStack(block, amount, meta);
	}

	// ComparableStack from ItemStack, required for MetaItems

	public static ComparableStack comparableStackFrom(ItemStack stack) {
		return new ComparableStack(stack);
	}

	public static ComparableStack comparableStackFrom(ItemStack stack, int amount) {
		return new ComparableStack(stack.getItem(), amount, stack.getMetadata());
	}

	// ItemStack from NBTTagCompound

	public static ItemStack itemStackFrom(NBTTagCompound compoundTag) {
		return new ItemStack(compoundTag);
	}

}
