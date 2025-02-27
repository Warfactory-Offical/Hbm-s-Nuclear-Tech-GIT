package com.hbm.packet;

import com.hbm.interfaces.IFluidPipe;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TEFluidTypePacketTest implements IMessage {

	int x, y, z;
	Fluid type;
	
	public TEFluidTypePacketTest() {
	}
	
	public TEFluidTypePacketTest(final int x, final int y, final int z, final Fluid type) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}
	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		final int len = buf.readInt();
		final byte[] bytes = new byte[len];
		buf.readBytes(bytes);
		final String name = new String(bytes);
		if(name.equals("HBM_NULL")){
			type = null;
			
		} else {
			type = FluidRegistry.getFluid(name);
		}
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		final byte[] bytes = type == null ? "HBM_NULL".getBytes() : type.getName().getBytes();
		buf.writeInt(bytes.length);
		buf.writeBytes(bytes);
	}
	
	public static class Handler implements IMessageHandler<TEFluidTypePacketTest, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final TEFluidTypePacketTest m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(m.x, m.y, m.z));

				if (te != null && te instanceof IFluidPipe duct) {

                    if(m.type == null)
						duct.setTypeTrue(null);
					else if(!m.type.equals(duct.getType()))
						duct.setTypeTrue(m.type);
				}
			});
			return null;
		}
		
	}

}
