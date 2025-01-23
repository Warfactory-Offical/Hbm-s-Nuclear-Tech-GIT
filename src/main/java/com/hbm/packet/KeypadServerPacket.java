package com.hbm.packet;

import com.hbm.interfaces.IKeypadHandler;
import com.hbm.util.Keypad;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class KeypadServerPacket implements IMessage {

	public int x, y, z;
	//0 is button clicked, with data as the button id.
	public int type;
	public int data;
	
	public KeypadServerPacket() {
	}
	
	public KeypadServerPacket(final BlockPos pos, final int type, final int data) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		this.type = type;
		this.data = data;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		type = buf.readInt();
		data = buf.readInt();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(type);
		buf.writeInt(data);
	}
	
	public static class Handler implements IMessageHandler<KeypadServerPacket, IMessage> {

		@Override
		public IMessage onMessage(final KeypadServerPacket m, final MessageContext ctx) {
			final BlockPos pos = new BlockPos(m.x, m.y, m.z);
			final World world = ctx.getServerHandler().player.world;
			if(world.isBlockLoaded(pos)){
				final TileEntity te = world.getTileEntity(pos);
				if(te instanceof IKeypadHandler){
					final Keypad pad = ((IKeypadHandler) te).getKeypad();
					if(m.type == 0){
						pad.buttonClicked(m.data);
					}
				}
			}
			return null;
		}
		
	}
	
}
