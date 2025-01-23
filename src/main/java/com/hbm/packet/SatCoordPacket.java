package com.hbm.packet;

import com.hbm.items.tool.ItemSatInterface;
import com.hbm.saveddata.satellites.Satellite;
import com.hbm.saveddata.satellites.SatelliteSavedData;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SatCoordPacket implements IMessage {

	int x;
	int y;
	int z;
	int freq;

	public SatCoordPacket()
	{
		
	}

	public SatCoordPacket(final int x, final int y, final int z, final int freq)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.freq = freq;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		freq = buf.readInt();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(freq);
	}

	public static class Handler implements IMessageHandler<SatCoordPacket, IMessage> {
		
		@Override
		public IMessage onMessage(final SatCoordPacket m, final MessageContext ctx) {
			ctx.getServerHandler().player.getServer().addScheduledTask(() -> {
				final EntityPlayer p = ctx.getServerHandler().player;
				
				if(p.getHeldItemMainhand().getItem() instanceof ItemSatInterface) {
					
					final int freq = ItemSatInterface.getFreq(p.getHeldItemMainhand());
					
					if(freq == m.freq) {
					    final Satellite sat = SatelliteSavedData.getData(p.world).getSatFromFreq(m.freq);
					    
					    if(sat != null)
					    	sat.onCoordAction(p.world, p, m.x, m.y, m.z);
					}
				}
			});
			
			return null;
		}
	}
}