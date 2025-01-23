package com.hbm.packet;

import com.hbm.items.weapon.ItemGunBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SetGunAnimPacket implements IMessage {

	long time;
	int animIndex;
	EnumHand hand;
	float speedMultiplier = 1;
	
	public SetGunAnimPacket() {
	}
	
	public SetGunAnimPacket(final long time, final int animIndex, final EnumHand hand) {
		this.time = time;
		this.animIndex = animIndex;
		this.hand = hand;
	}
	
	public SetGunAnimPacket(final long time, final int animIndex, final float speedMultiplier, final EnumHand hand) {
		this.time = time;
		this.animIndex = animIndex;
		this.speedMultiplier = speedMultiplier;
		this.hand = hand;
	}
	
	@Override
	public void fromBytes(final ByteBuf buf) {
		time = buf.readLong();
		animIndex = buf.readInt();
		this.speedMultiplier = buf.readFloat();
		hand = buf.readBoolean() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeLong(time);
		buf.writeInt(animIndex);
		buf.writeFloat(speedMultiplier);
		buf.writeBoolean(hand == EnumHand.MAIN_HAND);
	}
	
	public static class Handler implements IMessageHandler<SetGunAnimPacket, IMessage> {

		@Override
		public IMessage onMessage(final SetGunAnimPacket m, final MessageContext ctx) {
			final EntityPlayerMP player = ctx.getServerHandler().player;
			final ItemStack stack = player.getHeldItem(m.hand);
			if(stack.getItem() instanceof ItemGunBase){
				final NBTTagCompound anim = new NBTTagCompound();
				anim.setLong("time", m.time);
				anim.setFloat("mult", m.speedMultiplier);
				anim.setInteger("id", m.animIndex);
				
				if(stack.getTagCompound() == null)
					stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setTag("animation", anim);
			}
			return null;
		}
		
	}

}
