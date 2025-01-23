package com.hbm.packet;

import com.hbm.tileentity.turret.TileEntityTurretBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TETurretPacket implements IMessage {

	public int x, y, z;
	public boolean isAI;
	
	public TETurretPacket() {
	}
	
	public TETurretPacket(final int x, final int y, final int z, final boolean isAI) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.isAI = isAI;
	}
	
	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		isAI = buf.readBoolean();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(isAI);
	}
	
	public static class Handler implements IMessageHandler<TETurretPacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final TETurretPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final BlockPos pos = new BlockPos(m.x, m.y, m.z);
				final World world = Minecraft.getMinecraft().world;
				if(world.isBlockLoaded(pos)){
					final TileEntity te = world.getTileEntity(pos);
					if(te instanceof TileEntityTurretBase){
						((TileEntityTurretBase)te).isAI = m.isAI;
					}
				}
			});
			return null;
		}
		
	}

}
