package com.hbm.packet;

import com.hbm.particle.EnumHbmParticles;
import com.hbm.particle.ParticleManager;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnumParticlePacket implements IMessage {

	EnumHbmParticles particle;
	double x, y, z, strength;
	int count;
	
	public EnumParticlePacket() {
	}
	
	public EnumParticlePacket(final EnumHbmParticles particle, final double x, final double y, final double z, final int count, final double strength) {
		this.particle = particle;
		this.count = count;
		this.strength = strength;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public EnumParticlePacket(final EnumHbmParticles particle, final double x, final double y, final double z, final int count) {
		this(particle, x, y, z, count, 0);
	}
	
	@Override
	public void fromBytes(final ByteBuf buf) {
		particle = EnumHbmParticles.values()[buf.readInt()];
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
		strength = buf.readDouble();
		count = buf.readInt();
		
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(particle.ordinal());
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeDouble(strength);
		buf.writeInt(count);
		
	}
	
	public static class Handler implements IMessageHandler<EnumParticlePacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final EnumParticlePacket message, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				switch(message.particle){
				case PARTICLES:
					ParticleManager.spawnParticles(message.x, message.y, message.z, message.count);
					break;
				default:
					break;
				}
			});
			return null;
		}
		
	}

}
