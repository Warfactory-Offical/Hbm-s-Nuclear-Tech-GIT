package com.hbm.packet;

import com.hbm.capability.HbmCapability;
import com.hbm.capability.HbmCapability.IHBMData;
import com.hbm.handler.HbmKeybinds.EnumKeybind;
import com.hbm.items.gear.ArmorFSB;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeybindPacket implements IMessage {

	int id;
	int key;
	boolean pressed;

	public KeybindPacket() { }

	public KeybindPacket(final EnumKeybind key, final boolean pressed) {
		this.key = key.ordinal();
		this.pressed = pressed;
		this.id = 0;
	}
	
	public KeybindPacket(final int id) {
		this.id = id;
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		key = buf.readInt();
		pressed = buf.readBoolean();
		id = buf.readInt();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(key);
		buf.writeBoolean(pressed);
		buf.writeInt(id);
	}

	public static class Handler implements IMessageHandler<KeybindPacket, IMessage> {

		@Override
		public IMessage onMessage(final KeybindPacket m, final MessageContext ctx) {
			if(ctx.side == Side.SERVER){
				ctx.getServerHandler().player.getServer().addScheduledTask(() -> {
					switch(m.id){
					case 0:
						final EntityPlayer p = ctx.getServerHandler().player;
						final IHBMData props = HbmCapability.getData(p);

						props.setKeyPressed(EnumKeybind.values()[m.key], m.pressed);
						break;
					case 1:
						final EntityPlayer player = ctx.getServerHandler().player;
						if(ArmorFSB.hasFSBArmor(player)){
							final ItemStack stack = player.inventory.armorInventory.get(2);
							final ArmorFSB fsbarmor = (ArmorFSB)stack.getItem();
							if(fsbarmor.flashlightPosition != null){
								if(!stack.hasTagCompound()){
									stack.setTagCompound(new NBTTagCompound());
								}
								player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 0.5F, 1);
								stack.getTagCompound().setBoolean("flActive", !stack.getTagCompound().getBoolean("flActive"));
							}
						}
						break;
					}
				});
			} else {
				handleClient(ctx, m);
			}
			return null;
		}
		
		@SideOnly(Side.CLIENT)
		public void handleClient(final MessageContext ctx, final KeybindPacket m){
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final IHBMData props = HbmCapability.getData(Minecraft.getMinecraft().player);
				if(EnumKeybind.values()[m.key] == EnumKeybind.TOGGLE_JETPACK) {
					props.setEnableBackpack(m.pressed);
				}
				if(EnumKeybind.values()[m.key] == EnumKeybind.TOGGLE_HEAD) {
					props.setEnableHUD(m.pressed);
				}
			});
		}
	}
}