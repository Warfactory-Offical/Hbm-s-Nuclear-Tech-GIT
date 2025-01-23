package com.hbm.util;

import com.hbm.inventory.AnvilRecipes.AnvilOutput;
import com.hbm.inventory.RecipesCommon.AStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;

//'t was about time
public class InventoryUtil {

	/**
	 * Will attempt to cram a much of the given itemstack into the stack array as possible
	 * The rest will be returned
	 * @param inv the stack array, usually a TE's inventory
	 * @param start the starting index (inclusive)
	 * @param end the end index (inclusive)
	 * @param stack the stack to be added to the inventory
	 * @return the remainder of the stack that could not have been added, can return null
	 */
	public static ItemStack tryAddItemToInventory(final ItemStack[] inv, final int start, final int end, final ItemStack stack) {

		final ItemStack rem = tryAddItemToExistingStack(inv, start, end, stack);

		if(rem == null || rem.isEmpty())
			return ItemStack.EMPTY;

		final boolean didAdd = tryAddItemToNewSlot(inv, start, end, rem);

		if(didAdd)
			return ItemStack.EMPTY;
		else
			return rem;
	}

	/**
	 * Functionally equal to tryAddItemToInventory, but will not try to create new stacks in empty slots
	 * @param inv
	 * @param start
	 * @param end
	 * @param stack
	 * @return
	 */
	public static ItemStack tryAddItemToExistingStack(final ItemStack[] inv, final int start, final int end, final ItemStack stack) {

		if(stack == null || stack.isEmpty())
			return ItemStack.EMPTY;

		for(int i = start; i <= end; i++) {

			if(doesStackDataMatch(inv[i], stack)) {

				final int transfer = Math.min(stack.getCount(), inv[i].getMaxStackSize() - inv[i].getCount());

				if(transfer > 0) {
					inv[i].setCount(inv[i].getCount() + transfer);
					stack.setCount(stack.getCount() - transfer);

					if(stack.isEmpty())
						return ItemStack.EMPTY;
				}
			}
		}

		return stack;
	}

	/**
	 * Will place the stack in the first empty slot
	 * @param inv
	 * @param start
	 * @param end
	 * @param stack
	 * @return whether the stack could be added or not
	 */
	public static boolean tryAddItemToNewSlot(final ItemStack[] inv, final int start, final int end, final ItemStack stack) {

		if(stack == null || stack.isEmpty())
			return true;

		for(int i = start; i <= end; i++) {

			if(inv[i] == null || inv[i].isEmpty()) {
				inv[i] = stack;
				return true;
			}
		}

		return false;
	}

	/**
	 * Much of the same but with an ISidedInventory instance instead of a slot array
	 * @param inv
	 * @param start
	 * @param end
	 * @param stack
	 * @return
	 */
	public static ItemStack tryAddItemToInventory(final IItemHandlerModifiable inv, final int start, final int end, final ItemStack stack) {

		final ItemStack rem = tryAddItemToExistingStack(inv, start, end, stack);

		if(rem.isEmpty())
			return ItemStack.EMPTY;

		final boolean didAdd = tryAddItemToNewSlot(inv, start, end, rem);

		if(didAdd)
			return ItemStack.EMPTY;
		else
			return rem;
	}

	public static ItemStack tryAddItemToExistingStack(final IItemHandlerModifiable inv, final int start, final int end, final ItemStack stack) {

		if(stack == null || stack.isEmpty())
			return ItemStack.EMPTY;

		for(int i = start; i <= end; i++) {

			if(doesStackDataMatch(inv.getStackInSlot(i), stack)) {

				final int transfer = Math.min(stack.getCount(), inv.getStackInSlot(i).getMaxStackSize() - inv.getStackInSlot(i).getCount());

				if(transfer > 0) {
					inv.getStackInSlot(i).setCount(inv.getStackInSlot(i).getCount() + transfer);
					stack.setCount(stack.getCount() - transfer);

					if(stack.isEmpty())
						return ItemStack.EMPTY;
				}
			}
		}

		return stack;
	}

	public static boolean tryAddItemToNewSlot(final IItemHandlerModifiable inv, final int start, final int end, final ItemStack stack) {

		if(stack == null || stack.isEmpty())
			return true;

		for(int i = start; i <= end; i++) {

			if(inv.getStackInSlot(i).isEmpty()) {
				inv.setStackInSlot(i, stack);
				return true;
			}
		}

		return false;
	}

	/**
	 * Compares item, metadata and NBT data of two stacks. Also handles null values!
	 * @param stack1
	 * @param stack2
	 * @return
	 */
	public static boolean doesStackDataMatch(final ItemStack stack1, final ItemStack stack2) {

		if(stack1 == null && stack2 == null)
			return true;

		if(stack1 == null && stack2 != null)
			return false;

		if(stack1 != null && stack2 == null)
			return false;

		if(stack1.getItem() != stack2.getItem())
			return false;

		if(stack1.getItemDamage() != stack2.getItemDamage())
			return false;

		if(!stack1.hasTagCompound() && !stack2.hasTagCompound())
			return true;

		if(stack1.hasTagCompound() && !stack2.hasTagCompound())
			return false;

		if(!stack1.hasTagCompound() && stack2.hasTagCompound())
			return false;

		return stack1.getTagCompound().equals(stack2.getTagCompound());
	}
	
	/**
	 * Checks if a player has matching item stacks in his inventory and removes them if so desired
	 * @param player
	 * @param stacks the AStacks (comparable or ore-dicted)
	 * @param shouldRemove whether it should just return true or false or if a successful check should also remove all the items
	 * @return whether the player has the required item stacks or not
	 */
	public static boolean doesPlayerHaveAStacks(final EntityPlayer player, final List<AStack> stacks, final boolean shouldRemove) {
		
		final NonNullList<ItemStack> original = player.inventory.mainInventory;
		final ItemStack[] inventory = new ItemStack[original.size()];
		final AStack[] input = new AStack[stacks.size()];
		
		//first we copy the inputs into an array because 1. it's easier to deal with and 2. we can dick around with the stack sized with no repercussions
		for(int i = 0; i < input.length; i++) {
			input[i] = stacks.get(i).copy();
		}
		
		//then we copy the inventory so we can dick around with it as well without making actual modifications to the player's inventory
		for(int i = 0; i < original.size(); i++) {
			inventory[i] = original.get(i).copy();
		}
		
		//now we go through every ingredient...
		for(int i = 0; i < input.length; i++) {
			
			final AStack stack = input[i];
			
			//...and compare each ingredient to every stack in the inventory
			for(int j = 0; j < inventory.length; j++) {
				
				final ItemStack inv = inventory[j];
				
				//we check if it matches but ignore stack size for now
				if(stack.matchesRecipe(inv, true)) {
					//and NOW we care about the stack size
					final int size = Math.min(stack.count(), inv.getCount());
					stack.setCount(stack.count()-size);
					inv.setCount(inv.getCount()-size);
					
					//spent stacks are removed from the equation so that we don't cross ourselves later on
					if(stack.count() <= 0) {
						input[i] = null;
						break;
					}
					
					if(inv.getCount() <= 0) {
						inventory[j] = ItemStack.EMPTY;
					}
				}
			}
		}
		
		for(final AStack stack : input) {
			if(stack != null) {
				return false;
			}
		}
		
		if(shouldRemove) {
			for(int i = 0; i < original.size(); i++) {
				if(inventory[i] != null && inventory[i].getCount() <= 0)
					original.set(i, ItemStack.EMPTY);
				else
					original.set(i, inventory[i]);
			}
		}
		
		return true;
	}
	
	public static void giveChanceStacksToPlayer(final EntityPlayer player, final List<AnvilOutput> stacks) {
		
		for(final AnvilOutput out : stacks) {
			if(out.chance == 1.0F || player.getRNG().nextFloat() < out.chance) {
				if(!player.inventory.addItemStackToInventory(out.stack.copy())) {
					player.dropItem(out.stack.copy(), false);
				}
			}
		}
	}
	
	public static boolean hasOreDictMatches(final EntityPlayer player, final String dict, final int count) {
		return countOreDictMatches(player, dict) >= count;
	}
	
	public static int countOreDictMatches(final EntityPlayer player, final String dict) {
		
		int count = 0;
		
		for(int i = 0; i < player.inventory.mainInventory.size(); i++) {
			
			final ItemStack stack = player.inventory.mainInventory.get(i);
			
			if(stack != null) {
				
				final int[] ids = OreDictionary.getOreIDs(stack);
				
				for(final int id : ids) {
					if(OreDictionary.getOreName(id).equals(dict)) {
						count += stack.getCount();
						break;
					}
				}
			}
		}
		
		return count;
	}
	
	public static void consumeOreDictMatches(final EntityPlayer player, final String dict, int count) {
		
		for(int i = 0; i < player.inventory.mainInventory.size(); i++) {
			
			final ItemStack stack = player.inventory.mainInventory.get(i);
			
			if(stack != null) {
				
				final int[] ids = OreDictionary.getOreIDs(stack);
				
				for(final int id : ids) {
					if(OreDictionary.getOreName(id).equals(dict)) {
						
						final int toConsume = Math.min(count, stack.getCount());
						player.inventory.decrStackSize(i, toConsume);
						count -= toConsume;
						break;
					}
				}
			}
		}
	}

	public static boolean doesArrayHaveIngredients(final IItemHandler inv, final int start, final int end, final List<AStack> ingredients) {
		final ItemStack[] copy = ItemStackUtil.carefulCopyArrayTruncate(inv, start, end);
		final AStack[] req = new AStack[ingredients.size()];
		for (int idx = 0; idx < req.length; ++idx) {
			req[idx] = ingredients.get(idx) == null ? null : ingredients.get(idx).copy();
		}

		for (final AStack ingredient : req) {
			if (ingredient == null) {
				continue;
			}

			for (final ItemStack input : copy) {
				if (input == null || input.isEmpty()) {
					continue;
				}

				if (ingredient.matchesRecipe(input, true)) {
					final int size = Math.min(input.getCount(), ingredient.count());
					ingredient.setCount(ingredient.count() - size);
					input.setCount(input.getCount() - size);

					if (ingredient.count() == 0) {
						break;
					}
				}
			}

			if (ingredient.count() > 0) {
				return false;
			}
		}

		return true;
	}

	public static boolean doesArrayHaveIngredients(final IItemHandler inv, final int start, final int end, final AStack... ingredients) {
		return doesArrayHaveIngredients(inv, start, end, Arrays.asList(ingredients));
	}

	public static boolean doesArrayHaveSpace(final IItemHandler inv, final int start, final int end, final ItemStack[] items) {
		final ItemStack[] copy = ItemStackUtil.carefulCopyArrayTruncate(inv, start, end);

		for (final ItemStack item : items) {
			if (item.isEmpty()) {
				continue;
			}

			final ItemStack remainder = tryAddItemToInventory(copy, 0, copy.length - 1, item.copy());
			if (!remainder.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public static boolean tryConsumeAStack(final IItemHandlerModifiable inv, final int start, final int end, final AStack stack) {
		final AStack copy = stack.copy();
		for (int i = start; i <= end; ++i) {
			final ItemStack input = inv.getStackInSlot(i);

			if (stack.matchesRecipe(input, true)) {
				final int size = Math.min(copy.count(), input.getCount());
				inv.extractItem(i, size, false);
				copy.setCount(copy.count() - size);

				if (copy.count() == 0) {
					return true;
				}
			}
		}

		return false;
	}
}