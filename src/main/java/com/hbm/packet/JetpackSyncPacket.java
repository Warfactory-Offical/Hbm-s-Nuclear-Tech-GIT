package com.hbm.packet;

import com.hbm.handler.JetpackHandler;
import com.hbm.handler.JetpackHandler.JetpackInfo;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JetpackSyncPacket implements IMessage {

	int playerId;
	JetpackInfo info;

	public JetpackSyncPacket() {
	}

	public JetpackSyncPacket(final EntityPlayer player) {
		playerId = player.getEntityId();
		info = JetpackHandler.get(player);
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		playerId = buf.readInt();
		info = new JetpackInfo(false);
		info.read(buf);
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(playerId);
		info.write(buf);
	}

	public static class Handler implements IMessageHandler<JetpackSyncPacket, IMessage> {

		@Override
		public IMessage onMessage(final JetpackSyncPacket message, final MessageContext ctx) {
			if(ctx.side == Side.SERVER) {
				ctx.getServerHandler().player.server.addScheduledTask(() -> {
					final EntityPlayer player = ctx.getServerHandler().player;
					JetpackInfo info = JetpackHandler.get(player);
					if(info == null) {
						JetpackHandler.put(player, info = new JetpackInfo(false));
					}
					JetpackHandler.put(player, message.info);
				});
			} else {
				handleMessageClient(message, ctx);
			}
			return null;
		}

		@SideOnly(Side.CLIENT)
		public void handleMessageClient(final JetpackSyncPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final World world = Minecraft.getMinecraft().world;
				final Entity ent = world.getEntityByID(m.playerId);
				if(ent instanceof EntityPlayer player) {
                    JetpackInfo info = JetpackHandler.get(player);
					if(info == null) {
						info = new JetpackInfo(true);
						JetpackHandler.put(player, info);
					}
					info.setFromServer(m.info);
				}
			});
		}
	}
}
