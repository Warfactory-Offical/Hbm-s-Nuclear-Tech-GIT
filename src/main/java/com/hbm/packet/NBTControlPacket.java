package com.hbm.packet;

import java.io.IOException;

import com.hbm.interfaces.IControlReceiver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NBTControlPacket implements IMessage {
	
	PacketBuffer buffer;
	int x;
	int y;
	int z;

	public NBTControlPacket() { }

	public NBTControlPacket(final NBTTagCompound nbt, final BlockPos pos) {
		this(nbt, pos.getX(), pos.getY(), pos.getZ());
	}
	
	public NBTControlPacket(final NBTTagCompound nbt, final int x, final int y, final int z) {
		
		this.buffer = new PacketBuffer(Unpooled.buffer());
		this.x = x;
		this.y = y;
		this.z = z;
		
		buffer.writeCompoundTag(nbt);
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		
		if (buffer == null) {
			buffer = new PacketBuffer(Unpooled.buffer());
		}
		buffer.writeBytes(buf);
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		
		if (buffer == null) {
			buffer = new PacketBuffer(Unpooled.buffer());
		}
		buf.writeBytes(buffer);
	}

	public static class Handler implements IMessageHandler<NBTControlPacket, IMessage> {
		
		@Override
		public IMessage onMessage(final NBTControlPacket m, final MessageContext ctx) {

			ctx.getServerHandler().player.server.addScheduledTask(() -> {
				final EntityPlayer p = ctx.getServerHandler().player;
				
				if(p.world == null)
					return;
				
				final TileEntity te = p.world.getTileEntity(new BlockPos(m.x, m.y, m.z));
				
				try {
					
					final NBTTagCompound nbt = m.buffer.readCompoundTag();
					
					if(nbt != null) {
						if(te instanceof IControlReceiver tile) {

                            if(tile.hasPermission(p))
								tile.receiveControl(nbt);
						}
					}
					
				} catch (final IOException e) {
					e.printStackTrace();
				}
			});
			
			return null;
		}
	}
}