package com.hbm.packet;

import com.hbm.main.MainRegistry;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AuxParticlePacket implements IMessage {

	double x;
	double y;
	double z;
	int type;

	public AuxParticlePacket()
	{
		
	}

	public AuxParticlePacket(final double x, final double y, final double z, final int type)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
		type = buf.readInt();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeInt(type);
	}

	public static class Handler implements IMessageHandler<AuxParticlePacket, IMessage> {
		
		@Override
		public IMessage onMessage(final AuxParticlePacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				try {
					
					MainRegistry.proxy.particleControl(m.x, m.y, m.z, m.type);
					
				} catch(final Exception x) { }
			});
			
			
			return null;
		}
	}
}
