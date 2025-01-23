package com.hbm.packet;

import com.hbm.tileentity.machine.TileEntityMachineAssembler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TEAssemblerPacket implements IMessage {

	int x;
	int y;
	int z;
	boolean progress;

	public TEAssemblerPacket()
	{
		
	}

	public TEAssemblerPacket(final BlockPos pos, final boolean bool)
	{
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		this.progress = bool;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		progress = buf.readBoolean();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(progress);
	}

	public static class Handler implements IMessageHandler<TEAssemblerPacket, IMessage> {
		
		@Override
		public IMessage onMessage(final TEAssemblerPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final BlockPos pos = new BlockPos(m.x, m.y, m.z);
				final TileEntity te = Minecraft.getMinecraft().world.getTileEntity(pos);

				if (te != null && te instanceof TileEntityMachineAssembler gen) {

                    gen.isProgressing = m.progress;
				}
			});
			
			return null;
		}
	}
}