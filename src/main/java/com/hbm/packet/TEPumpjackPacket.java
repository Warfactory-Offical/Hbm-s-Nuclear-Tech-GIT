package com.hbm.packet;

import com.hbm.tileentity.machine.oil.TileEntityMachinePumpjack;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TEPumpjackPacket implements IMessage {

	int x;
	int y;
	int z;
	float spin;
	boolean progress;

	public TEPumpjackPacket()
	{
		
	}

	public TEPumpjackPacket(final int x, final int y, final int z, final float spin, final boolean bool)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.spin = spin;
		this.progress = bool;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		spin = buf.readFloat();
		progress = buf.readBoolean();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeFloat(spin);
		buf.writeBoolean(progress);
	}

	public static class Handler implements IMessageHandler<TEPumpjackPacket, IMessage> {
		
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final TEPumpjackPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				try {
					final TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(m.x, m.y, m.z));

					if (te != null && te instanceof TileEntityMachinePumpjack gen) {

                        gen.rotation = m.spin;
						gen.isProgressing = m.progress;
					}
				} catch(final Exception x) { }
			});
			
			return null;
		}
	}
}
