package com.hbm.packet;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.AnvilRecipes;
import com.hbm.inventory.AnvilRecipes.AnvilConstructionRecipe;
import com.hbm.inventory.container.ContainerAnvil;
import com.hbm.main.AdvancementManager;
import com.hbm.main.MainRegistry;
import com.hbm.util.InventoryUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AnvilCraftPacket implements IMessage {

	int recipeIndex;
	int mode;

	public AnvilCraftPacket() { }

	public AnvilCraftPacket(final AnvilConstructionRecipe recipe, final int mode) {
		this.recipeIndex = AnvilRecipes.getConstruction().indexOf(recipe);
		this.mode = mode;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		this.recipeIndex = buf.readInt();
		this.mode = buf.readInt();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(this.recipeIndex);
		buf.writeInt(this.mode);
	}

	public static class Handler implements IMessageHandler<AnvilCraftPacket, IMessage> {
		
		@Override
		public IMessage onMessage(final AnvilCraftPacket m, final MessageContext ctx) {

			ctx.getServerHandler().player.server.addScheduledTask(() -> {
				if(m.recipeIndex < 0 || m.recipeIndex >= AnvilRecipes.getConstruction().size()) //recipe is out of range -> bad
					return;

				final EntityPlayer p = ctx.getServerHandler().player;
				
				if(!(p.openContainer instanceof ContainerAnvil anvil)) //player isn't even using an anvil -> bad
					return;

                final AnvilConstructionRecipe recipe = AnvilRecipes.getConstruction().get(m.recipeIndex);

				if(!recipe.isTierValid(anvil.tier)) //player is using the wrong type of anvil -> bad
					return;

				final int count = m.mode == 1 ? 64 : 1;
				
				for(int i = 0; i < count; i++) {
					
					if(InventoryUtil.doesPlayerHaveAStacks(p, recipe.input, true)) {
						InventoryUtil.giveChanceStacksToPlayer(p, recipe.output);

						if(recipe.output.get(0).stack.getItem() == Item.getItemFromBlock(ModBlocks.machine_difurnace_off))
							AdvancementManager.grantAchievement(p, AdvancementManager.bobMetalworks);
						if(recipe.output.get(0).stack.getItem() == Item.getItemFromBlock(ModBlocks.machine_assembler))
							AdvancementManager.grantAchievement(p, AdvancementManager.bobAssembly);
						
					} else {
						break;
					}
				}
				
				p.inventoryContainer.detectAndSendChanges();
			});
			
			return null;
		}
	}
}