package com.hbm.packet;

import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemGunShotty;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MeathookResetStrafePacket implements IMessage {

	public MeathookResetStrafePacket() {
	}
	
	@Override
	public void fromBytes(final ByteBuf buf) {
	}

	@Override
	public void toBytes(final ByteBuf buf) {
	}
	
	public static class Handler implements IMessageHandler<MeathookResetStrafePacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final MeathookResetStrafePacket message, final MessageContext ctx) {
			final EntityPlayer p = Minecraft.getMinecraft().player;
			if(p.getHeldItemMainhand().getItem() == ModItems.gun_supershotgun){
				ItemGunShotty.motionStrafe = 0;
			}
			return null;
		}
		
	}

}
