package com.hbm.packet;

import java.io.IOException;

import com.hbm.capability.HbmLivingCapability.IEntityHbmProps;
import com.hbm.capability.HbmLivingProps;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExtPropPacket implements IMessage {

	PacketBuffer buffer;

	public ExtPropPacket(){
	}

	public ExtPropPacket(final NBTTagCompound nbt){
		this.buffer = new PacketBuffer(Unpooled.buffer());

		buffer.writeCompoundTag(nbt);
	}

	@Override
	public void fromBytes(final ByteBuf buf){
		if(buffer == null) {
			buffer = new PacketBuffer(Unpooled.buffer());
		}
		buffer.writeBytes(buf);
	}

	@Override
	public void toBytes(final ByteBuf buf){
		if(buffer == null) {
			buffer = new PacketBuffer(Unpooled.buffer());
		}
		buf.writeBytes(buffer);
	}

	public static class Handler implements IMessageHandler<ExtPropPacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final ExtPropPacket m, final MessageContext ctx){
			Minecraft.getMinecraft().addScheduledTask(() -> {
				if(Minecraft.getMinecraft().world == null)
					return;
				try {

					final NBTTagCompound nbt = m.buffer.readCompoundTag();
					final IEntityHbmProps props = HbmLivingProps.getData(Minecraft.getMinecraft().player);
					props.loadNBTData(nbt);

				} catch(final IOException e) {
					e.printStackTrace();
				}
			});
			
			return null;
		}
	}
}