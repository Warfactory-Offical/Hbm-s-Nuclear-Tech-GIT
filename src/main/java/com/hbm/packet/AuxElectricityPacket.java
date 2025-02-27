package com.hbm.packet;

import api.hbm.energy.IEnergyUser;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AuxElectricityPacket implements IMessage {

	int x;
	int y;
	int z;
	long charge;

	public AuxElectricityPacket()
	{
		
	}

	public AuxElectricityPacket(final BlockPos pos, final long charge)
	{
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		this.charge = charge;
	}

	public AuxElectricityPacket(final int x2, final int y2, final int z2, final long power) {
		this.x = x2;
		this.y = y2;
		this.z = z2;
		this.charge = power;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		charge = buf.readLong();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeLong(charge);
	}

	public static class Handler implements IMessageHandler<AuxElectricityPacket, IMessage> {
		
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final AuxElectricityPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final BlockPos pos = new BlockPos(m.x, m.y, m.z);
				try {
					final TileEntity te = Minecraft.getMinecraft().world.getTileEntity(pos);

					if (te != null && te instanceof IEnergyUser gen) {

                        gen.setPower(m.charge);
					}
				} catch (final Exception x) { }
			});
			
			return null;
		}
	}
}