package com.hbm.inventory;
import com.hbm.util.ItemStackUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class RecipesCommon {
	
	public static ItemStack[] copyStackArray(final ItemStack[] array) {
		
		if(array == null)
			return null;
		
		final ItemStack[] clone = new ItemStack[array.length];
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[i] != null)
				clone[i] = array[i].copy();
		}
		
		return clone;
	}
	
	public static ItemStack[] objectToStackArray(final Object[] array) {

		if(array == null)
			return null;
		
		final ItemStack[] clone = new ItemStack[array.length];
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[i] instanceof ItemStack)
				clone[i] = (ItemStack)array[i];
		}
		
		return clone;
	}
	
	public static abstract class AStack implements Comparable<AStack> {

		protected int stacksize;

		public boolean isApplicable(final ItemStack stack) {
			return isApplicable(new NbtComparableStack(stack));
		}

		public AStack singulize(){
			stacksize = 1;
			return this;
		}
		
		public int count(){
			return stacksize;
		}
		
		public void setCount(final int c){
			stacksize = c;
		}
		
		/*
		 * Is it unprofessional to pool around in child classes from an abstract superclass? Do I look like I give a shit?
		 */
		public boolean isApplicable(final ComparableStack comp) {
			
			if(this instanceof ComparableStack) {
				return this.equals(comp);
			}

			if(this instanceof OreDictStack) {

				final List<ItemStack> ores = OreDictionary.getOres(((OreDictStack)this).name);

				for(final ItemStack stack : ores) {
					if(stack.getItem() == comp.item && stack.getItemDamage() == comp.meta)
						return true;
				}
			}

			return false;
		}
		
		/**
		 * Whether the supplied itemstack is applicable for a recipe (e.g. anvils). Slightly different from {@code isApplicable}.
		 * @param stack the ItemStack to check
		 * @param ignoreSize whether size should be ignored entirely or if the ItemStack needs to be >at least< the same size as this' size
		 * @return
		 */
		public abstract boolean matchesRecipe(ItemStack stack, boolean ignoreSize);

		public abstract AStack copy();
		public abstract ItemStack getStack();
		public abstract List<ItemStack> getStackList();
		
		@Override
		public String toString() {
			return "AStack: size, " + stacksize;
		}
	}

	public static class ComparableStack extends AStack {

		public Item item;
		public int meta;
		
		public ComparableStack(final ItemStack stack) {
			this.item = stack.getItem();
			this.stacksize = stack.getCount();
			this.meta = stack.getItemDamage();
		}
		
		public ComparableStack makeSingular() {
			stacksize = 1;
			return this;
		}
		
		@Override
		public AStack singulize() {
			stacksize = 1;
			return this;
		}
		
		public ComparableStack(final Item item) {
			this.item = item;
			this.stacksize = 1;
			this.meta = 0;
		}
		
		public ComparableStack(final Block item) {
			this.item = Item.getItemFromBlock(item);
			this.stacksize = 1;
			this.meta = 0;
		}
		
		public ComparableStack(final Item item, final int stacksize) {
			this(item);
			this.stacksize = stacksize;
		}
		
		public ComparableStack(final Item item, final int stacksize, final int meta) {
			this(item, stacksize);
			this.meta = meta;
		}
		
		public ComparableStack(final Block item, final int stacksize) {
			this.item = Item.getItemFromBlock(item);
			this.stacksize = stacksize;
			this.meta = 0;
		}

		public ComparableStack(final Block item, final int stacksize, final int meta) {
			this.item = Item.getItemFromBlock(item);
			this.stacksize = stacksize;
			this.meta = meta;
		}
		
		public ItemStack toStack() {
			
			return ItemStackUtil.itemStackFrom(item, stacksize, meta);
		}
		
		@Override
		public ItemStack getStack() {
			return toStack();
		}
		
		@Override
		public List<ItemStack> getStackList(){
			return Collections.singletonList(getStack());
		}
		
		public String[] getDictKeys() {
			
			final int[] ids = OreDictionary.getOreIDs(toStack());
			
			if(ids == null || ids.length == 0)
				return new String[0];
			
			final String[] entries = new String[ids.length];
			
			for(int i = 0; i < ids.length; i++) {
				
				entries[i] = OreDictionary.getOreName(ids[i]);
			}
			
			return entries;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			if(item == null) {
				MainRegistry.logger.error("ComparableStack has a null item! This is a serious issue!");
				Thread.dumpStack();
				item = Items.STICK;
			}
			
			final ResourceLocation name = Item.REGISTRY.getNameForObject(item);
			
			if(name == null) {
				MainRegistry.logger.error("ComparableStack holds an item that does not seem to be registered. How does that even happen?");
				Thread.dumpStack();
				item = Items.STICK; //we know sticks have a name, so sure, why not
			}
			
			if(name != null)
				result = prime * result + Item.REGISTRY.getNameForObject(item).hashCode(); //using the int ID will cause fucky-wuckys if IDs are scrambled
			result = prime * result + meta;
			result = prime * result + stacksize;
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof ComparableStack other))
				return false;
            if (item == null) {
				if (other.item != null)
					return false;
			} else if (!item.equals(other.item))
				return false;
			if (meta != OreDictionary.WILDCARD_VALUE && other.meta != OreDictionary.WILDCARD_VALUE && meta != other.meta)
				return false;
            return stacksize == other.stacksize;
        }

		@Override
		public int compareTo(final AStack stack) {

			if(stack instanceof ComparableStack comp) {

                final int thisID = Item.getIdFromItem(item);
				final int thatID = Item.getIdFromItem(comp.item);

				if(thisID > thatID)
					return 1;
				if(thatID > thisID)
					return -1;

				if(meta > comp.meta)
					return 1;
				if(comp.meta > meta)
					return -1;

				return 0;
			}

			//if compared with an ODStack, the CStack will take priority
			if(stack instanceof OreDictStack)
				return 1;

			return 0;
		}
		
		@Override
		public boolean matchesRecipe(final ItemStack stack, final boolean ignoreSize) {
			
			if(stack == null)
				return false;
			
			if(stack.getItem() != this.item)
				return false;
			
			if(this.meta != OreDictionary.WILDCARD_VALUE && stack.getItemDamage() != this.meta)
				return false;

            return ignoreSize || stack.getCount() >= this.stacksize;
        }
		
		@Override
		public AStack copy() {
			return ItemStackUtil.comparableStackFrom(item, stacksize, meta);
		}
		
		@Override
		public String toString() {
			return "ComparableStack: { "+stacksize+" x "+item.getRegistryName()+"@"+meta+" }";
		}
	}
	
	public static class NbtComparableStack extends ComparableStack {
		ItemStack stack;
		public NbtComparableStack(final ItemStack stack) {
			super(stack);
			this.stack = stack.copy();
		}
		
		@Override
		public ComparableStack makeSingular() {
			final ItemStack st = stack.copy();
			st.setCount(1);
			return new NbtComparableStack(st);
		}
		
		@Override
		public AStack singulize() {
			stack.setCount(1);
			this.stacksize = 1;
			return this;
		}
		
		@Override
		public ItemStack toStack() {
			return stack.copy();
		}
		
		@Override
		public ItemStack getStack() {
			return toStack();
		}
		
		@Override
		public int hashCode() {
			if(!stack.hasTagCompound())
				return super.hashCode();
			else
				return super.hashCode() * 31 + stack.getTagCompound().hashCode();
		}
		
		@Override
		public AStack copy() {
			return new NbtComparableStack(stack);
		}
		
		@Override
		public boolean equals(final Object obj) {
			if(!stack.hasTagCompound() || !(obj instanceof NbtComparableStack)) {
				return super.equals(obj);
			} else {
				return super.equals(obj) && Library.tagContainsOther(stack.getTagCompound(), ((NbtComparableStack)obj).stack.getTagCompound());
			}
		}
		
		@Override
		public boolean matchesRecipe(final ItemStack stack, final boolean ignoreSize){
			return super.matchesRecipe(stack, ignoreSize) && Library.tagContainsOther(this.stack.getTagCompound(), stack.getTagCompound());
		}
		
		@Override
		public String toString() {
			return "NbtComparableStack: " + stack.toString();
		}
		
	}
	
	public static class OreDictStack extends AStack {

		public String name;

		public OreDictStack(final String name) {
			this.name = name;
			this.stacksize = 1;
		}

		public OreDictStack(final String name, final int stacksize) {
			this(name);
			this.stacksize = stacksize;
		}
		
		public List<ItemStack> toStacks() {
			return OreDictionary.getOres(name);
		}
		
		@Override
		public ItemStack getStack() {
			final ItemStack stack = toStacks().get(0);
			return ItemStackUtil.itemStackFrom(stack.getItem(), stacksize, stack.getMetadata());
		}
		
		@Override
		public List<ItemStack> getStackList(){
			final List<ItemStack> list = Library.copyItemStackList(toStacks());
			for(final ItemStack stack : list){
				stack.setCount(this.stacksize);
			}
			return list;
		}

		@Override
		public int hashCode() {
			return (name+this.stacksize).hashCode();
		}
		
		@Override
		public AStack singulize() {
			stacksize = 1;
			return this;
		}

		@Override
		public int compareTo(final AStack stack) {

			if(stack instanceof OreDictStack comp) {

                return name.compareTo(comp.name);
			}

			//if compared with a CStack, the ODStack will yield
			if(stack instanceof ComparableStack)
				return -1;

			return 0;
		}
		
		@Override
		public boolean matchesRecipe(final ItemStack stack, final boolean ignoreSize) {
			
			if(stack == null || stack.isEmpty())
				return false;
			
			if(!ignoreSize && stack.getCount() < this.stacksize)
				return false;
			
			final int[] ids = OreDictionary.getOreIDs(stack);
			
			if(ids == null || ids.length == 0)
				return false;
			
			for(int i = 0; i < ids.length; i++) {
				if(this.name.equals(OreDictionary.getOreName(ids[i])))
					return true;
			}
			
			return false;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof OreDictStack other))
				return false;
            if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
            return stacksize == other.stacksize;
        }

		@Override
		public AStack copy() {
			return new OreDictStack(name, stacksize);
		}
		
		@Override
		public String toString() {
			return "OreDictStack: name, " + name + ", stacksize, " + stacksize;
		}
	}
}
