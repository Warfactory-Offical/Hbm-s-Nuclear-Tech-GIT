package com.hbm.packet;

import com.hbm.main.MainRegistry;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerInformPacket implements IMessage {

	String dmesg = "";

	public PlayerInformPacket()
	{

	}

	public PlayerInformPacket(final String dmesg)
	{
		this.dmesg = dmesg;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {

		dmesg = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(final ByteBuf buf) {

		ByteBufUtils.writeUTF8String(buf, dmesg);
	}

	public static class Handler implements IMessageHandler<PlayerInformPacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final PlayerInformPacket m, final MessageContext ctx) {
			try {

				MainRegistry.proxy.displayTooltip(m.dmesg);

			} catch (final Exception x) { }
			return null;
		}
	}
}
