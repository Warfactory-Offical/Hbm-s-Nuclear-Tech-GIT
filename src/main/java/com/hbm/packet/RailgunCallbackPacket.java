package com.hbm.packet;

import com.hbm.tileentity.bomb.TileEntityRailgun;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RailgunCallbackPacket implements IMessage {

	int x;
	int y;
	int z;
	float pitch;
	float yaw;

	public RailgunCallbackPacket() { }

	public RailgunCallbackPacket(final int x, final int y, final int z, final float pitch, final float yaw)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		pitch = buf.readFloat();
		yaw = buf.readFloat();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeFloat(pitch);
		buf.writeFloat(yaw);
	}

	public static class Handler implements IMessageHandler<RailgunCallbackPacket, IMessage> {
		
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final RailgunCallbackPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				try {
					final TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(m.x, m.y, m.z));

					if (te != null && te instanceof TileEntityRailgun gun) {

                        gun.startTime = System.currentTimeMillis();
						gun.lastPitch = gun.pitch;
						gun.lastYaw = gun.yaw;
						gun.pitch = m.pitch;
						gun.yaw = m.yaw;
					}
					
				} catch (final Exception x) { }
			});
			
			return null;
		}
	}
}