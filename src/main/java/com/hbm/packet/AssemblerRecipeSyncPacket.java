package com.hbm.packet;
import com.hbm.util.ItemStackUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.hbm.inventory.AssemblerRecipes;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AssemblerRecipeSyncPacket implements IMessage {

	public List<AssemblerRecipe> recipes;
	public HashSet<ComparableStack> hidden;
	
	public AssemblerRecipeSyncPacket() {
	}
	
	public AssemblerRecipeSyncPacket(final List<ComparableStack> recipes, final HashSet<ComparableStack> hidden){
		this.recipes = new ArrayList<>(recipes.size());
		for(int i = 0; i < recipes.size(); i ++){
			final ComparableStack c = recipes.get(i);
			if(AssemblerRecipes.recipes.get(c) != null)
				this.recipes.add(new AssemblerRecipe(AssemblerRecipes.recipes.get(c), c, AssemblerRecipes.time.get(c)));
		}
		this.hidden = hidden;
	}
	
	@Override
	public void fromBytes(final ByteBuf buf) {
		final int size = buf.readInt();
		recipes = new ArrayList<>(size);
		for(int i = 0; i < size; i ++){
			final int inputSize = buf.readByte();
			final AStack[] inputs = new AStack[inputSize];
			
			for(int j = 0; j < inputSize; j ++){
				final byte type = buf.readByte();
				if(type == 0){
					final int count = buf.readInt();
					final int id = buf.readInt();
					final int meta = buf.readInt();
					final ItemStack stack = ItemStackUtil.itemStackFrom(Item.getItemById(id), count, meta);
					try {
						stack.setTagCompound(CompressedStreamTools.read(new ByteBufInputStream(buf), new NBTSizeTracker(2097152L)));
					} catch(final IOException e) {
						e.printStackTrace();
					}
					inputs[j] = new NbtComparableStack(stack);
				} else if(type == 1){
					final int count = buf.readInt();
					final int len = buf.readInt();
					final byte[] bytes = new byte[len];
					buf.readBytes(bytes);
					final String name = new String(bytes, StandardCharsets.US_ASCII);
					inputs[j] = new OreDictStack(name, count);
				} else if(type == 2){
					final int count = buf.readInt();
					final int id = buf.readInt();
					final int meta = buf.readInt();
					final ItemStack stack = ItemStackUtil.itemStackFrom(Item.getItemById(id), count, meta);
					inputs[j] = ItemStackUtil.comparableStackFrom(stack);
				}
			}
			final int id = buf.readInt();
			final int meta = buf.readInt();
			final int count = buf.readInt();
			final ItemStack stack = ItemStackUtil.itemStackFrom(Item.getItemById(id), count, meta);
			final ComparableStack output;
			final byte type = buf.readByte();
			if(type == 1){
				try {
					stack.setTagCompound(CompressedStreamTools.read(new ByteBufInputStream(buf), new NBTSizeTracker(2097152L)));
				} catch(final IOException e) {
					e.printStackTrace();
				}
				output = new NbtComparableStack(stack);
			} else {
				output = ItemStackUtil.comparableStackFrom(stack);
			}
			
			final int time = buf.readInt();
			
			recipes.add(new AssemblerRecipe(inputs, output, time));
		}
		
		
		final int hiddenSize = buf.readInt();
		hidden = new HashSet<>(hiddenSize);
		for(int i = 0; i < hiddenSize; i ++){
			final int id = buf.readInt();
			final int meta = buf.readInt();
			final int count = buf.readInt();
			final ItemStack stack = ItemStackUtil.itemStackFrom(Item.getItemById(id), count, meta);
			final ComparableStack hideStack;
			final byte type = buf.readByte();
			if(type == 1){
				try {
					stack.setTagCompound(CompressedStreamTools.read(new ByteBufInputStream(buf), new NBTSizeTracker(2097152L)));
				} catch(final IOException e) {
					e.printStackTrace();
				}
				hideStack = new NbtComparableStack(stack);
			} else {
				hideStack = ItemStackUtil.comparableStackFrom(stack);
			}
			hidden.add(hideStack);
		}
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(recipes.size());
		for(int i = 0; i < recipes.size(); i ++){
			final AssemblerRecipe recipe = recipes.get(i);
			final AStack[] inputs = recipe.in;
			final ComparableStack output = recipe.out;
			buf.writeByte(inputs.length);
			for(int j = 0; j < inputs.length; j ++){
				final AStack stack = inputs[j];
				if(stack instanceof NbtComparableStack nStack){
                    buf.writeByte(0);
					buf.writeInt(nStack.count());
					buf.writeInt(Item.getIdFromItem(nStack.item));
					buf.writeInt(nStack.meta);
					try {
						CompressedStreamTools.write(nStack.getStack().getTagCompound(), new ByteBufOutputStream(buf));
					} catch(final IOException e) {
						e.printStackTrace();
					}
				} else if(stack instanceof OreDictStack oStack){
                    buf.writeByte(1);
					buf.writeInt(oStack.count());
					final byte[] bytes = oStack.name.getBytes(StandardCharsets.US_ASCII);
					buf.writeInt(bytes.length);
					buf.writeBytes(bytes);
				} else if(stack instanceof ComparableStack cStack){
                    buf.writeByte(2);
					buf.writeInt(cStack.count());
					buf.writeInt(Item.getIdFromItem(cStack.item));
					buf.writeInt(cStack.meta);
				}
			}
			buf.writeInt(Item.getIdFromItem(output.item));
			buf.writeInt(output.meta);
			buf.writeInt(output.count());
			if(output instanceof NbtComparableStack){
				buf.writeByte(1);
				try {
					CompressedStreamTools.write(output.getStack().getTagCompound(), new ByteBufOutputStream(buf));
				} catch(final IOException e) {
					e.printStackTrace();
				}
			} else {
				buf.writeByte(0);
			}
			buf.writeInt(recipes.get(i).time);
		}
		
		
		buf.writeInt(hidden.size());
		for(final ComparableStack stack : hidden){
			buf.writeInt(Item.getIdFromItem(stack.item));
			buf.writeInt(stack.meta);
			buf.writeInt(stack.count());
			if(stack instanceof NbtComparableStack){
				buf.writeByte(1);
				try {
					CompressedStreamTools.write(stack.getStack().getTagCompound(), new ByteBufOutputStream(buf));
				} catch(final IOException e) {
					e.printStackTrace();
				}
			} else {
				buf.writeByte(0);
			}
		}
	}
	
	public static class Handler implements IMessageHandler<AssemblerRecipeSyncPacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final AssemblerRecipeSyncPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				AssemblerRecipes.backupRecipes = AssemblerRecipes.recipes;
				AssemblerRecipes.backupTime = AssemblerRecipes.time;
				AssemblerRecipes.backupRecipeList = AssemblerRecipes.recipeList;
				AssemblerRecipes.backupHidden = AssemblerRecipes.hidden;
				
				AssemblerRecipes.recipes = new LinkedHashMap<>(m.recipes.size());
				AssemblerRecipes.time = new HashMap<>(m.recipes.size());
				AssemblerRecipes.recipeList = new ArrayList<>(m.recipes.size());
				AssemblerRecipes.hidden = m.hidden;
				
				for(final AssemblerRecipe r : m.recipes){
					AssemblerRecipes.recipes.put(r.out, r.in);
					AssemblerRecipes.time.put(r.out, r.time);
					AssemblerRecipes.recipeList.add(r.out);
				}
			});
			return null;
		}
	}
	
	private static class AssemblerRecipe {
		AStack[] in;
		ComparableStack out;
		int time;
		
		public AssemblerRecipe(final AStack[] in, final ComparableStack out, final int time) {
			super();
			this.in = in;
			this.out = out;
			this.time = time;
		}
	}
	
}
