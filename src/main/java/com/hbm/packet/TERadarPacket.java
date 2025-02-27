package com.hbm.packet;

import java.util.Collections;
import java.util.List;

import com.hbm.tileentity.machine.TileEntityMachineRadar;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TERadarPacket implements IMessage {

	int x;
	int y;
	int z;
	List<int[]> missiles;
	int[][] missiles2;

	public TERadarPacket() {

	}

	public TERadarPacket(final BlockPos pos, final List<int[]> missiles) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		this.missiles = missiles;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		final int size = buf.readInt();
		missiles2 = new int[size][];
		for(int i = 0; i < size; i ++){
			final int mX = buf.readInt();
			final int mZ = buf.readInt();
			final int type = buf.readInt();
			final int mY = buf.readInt();
			missiles2[i] = new int[]{mX, mZ, type, mY};
		}
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(missiles.size());
		for(final int[] missile : missiles){
			buf.writeInt(missile[0]);
			buf.writeInt(missile[1]);
			buf.writeInt(missile[2]);
			buf.writeInt(missile[3]);
		}
	}

	public static class Handler implements IMessageHandler<TERadarPacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final TERadarPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(m.x, m.y, m.z));

				try {
					if (te != null && te instanceof TileEntityMachineRadar radar) {

                        radar.nearbyMissiles.clear();
                        Collections.addAll(radar.nearbyMissiles, m.missiles2);
					}
				} catch (final Exception x) {
				}
			});
			return null;
		}
	}
}
