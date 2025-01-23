package com.hbm.packet;

import java.util.UUID;

import com.hbm.items.weapon.ItemGunBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GunFXPacket implements IMessage {

	public String playerUUID;
	public EnumHand hand;
	public FXType type;
	
	public GunFXPacket() {
	}
	
	public GunFXPacket(final EntityPlayer player, final EnumHand hand, final FXType type) {
		this.hand = hand;
		this.playerUUID = player.getUniqueID().toString();
		this.type = type;
	}
	
	@Override
	public void fromBytes(final ByteBuf buf) {
		hand = buf.readBoolean() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
		final byte[] bytes = new byte[buf.readInt()];
		buf.readBytes(bytes);
		playerUUID = new String(bytes);
		type = FXType.values()[buf.readInt()];
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeBoolean(hand == EnumHand.MAIN_HAND);
		final byte[] bytes = playerUUID.getBytes();
		buf.writeInt(bytes.length);
		buf.writeBytes(bytes);
		buf.writeInt(type.ordinal());
	}
	
	public static class Handler implements IMessageHandler<GunFXPacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final GunFXPacket m, final MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final EntityPlayer player = Minecraft.getMinecraft().world.getPlayerEntityByUUID(UUID.fromString(m.playerUUID));
				final ItemStack stack = player.getHeldItem(m.hand);
				if(stack.getItem() instanceof ItemGunBase){
					final boolean isThirdPerson = Minecraft.getMinecraft().gameSettings.thirdPersonView > 0 || player != Minecraft.getMinecraft().player;
					if(m.type == FXType.FIRE)
						((ItemGunBase)stack.getItem()).onFireClient(stack, player, isThirdPerson);
				}
			});
			return null;
		}
		
	}
	
	public static enum FXType {
		FIRE
    }
}
