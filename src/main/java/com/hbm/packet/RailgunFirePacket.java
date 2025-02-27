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

public class RailgunFirePacket implements IMessage {

	int x;
	int y;
	int z;

	public RailgunFirePacket() { }

	public RailgunFirePacket(final int x, final int y, final int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	public static class Handler implements IMessageHandler<RailgunFirePacket, IMessage> {
		
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final RailgunFirePacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				try {
					final TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(m.x, m.y, m.z));
		
					if (te != null && te instanceof TileEntityRailgun gun) {

                        gun.fireTime = System.currentTimeMillis();
					}
					
				} catch (final Exception x) { }
			});
			
			return null;
		}
	}
}
