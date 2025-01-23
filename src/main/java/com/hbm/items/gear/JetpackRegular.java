package com.hbm.items.gear;

import com.hbm.capability.HbmCapability;
import com.hbm.capability.HbmCapability.IHBMData;
import com.hbm.handler.HbmKeybinds.EnumKeybind;
import com.hbm.items.armor.JetpackBase;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.KeybindPacket;
import com.hbm.packet.PacketDispatcher;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import java.util.List;

public class JetpackRegular extends JetpackBase {

	public JetpackRegular(final ArmorMaterial materialIn, final int renderIndexIn, final EntityEquipmentSlot equipmentSlotIn, final Fluid fuel, final int maxFuel, final String s) {
		super(materialIn, renderIndexIn, equipmentSlotIn, fuel, maxFuel, s);
	}

	@Override
	public String getArmorTexture(final ItemStack stack, final Entity entity, final EntityEquipmentSlot slot, final String type) {
		return "hbm:textures/armor/JetPackRed.png";
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn){
		tooltip.add("Regular jetpack for simple upwards momentum.");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack stack) {
		final IHBMData props = HbmCapability.getData(player);
		if(world.isRemote) {

			if(player == MainRegistry.proxy.me() && props.isJetpackActive()) {

				final boolean last = props.getKeyPressed(EnumKeybind.JETPACK);
				final boolean current = MainRegistry.proxy.getIsKeyPressed(EnumKeybind.JETPACK);

				if(last != current) {
					PacketDispatcher.wrapper.sendToServer(new KeybindPacket(EnumKeybind.JETPACK, current));
					props.setKeyPressed(EnumKeybind.JETPACK, current);
				}
			}

		} else {

			if(getFuel(stack) > 0 && props.getKeyPressed(EnumKeybind.JETPACK) && props.isJetpackActive()) {

				final NBTTagCompound data = new NBTTagCompound();
				data.setString("type", "jetpack");
				data.setInteger("player", player.getEntityId());
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, player.posX, player.posY, player.posZ), new TargetPoint(world.provider.getDimension(), player.posX, player.posY, player.posZ, 100));
			}
		}
		if(getFuel(stack) > 0 && props.getKeyPressed(EnumKeybind.JETPACK) && props.isJetpackActive()) {
			player.fallDistance = 0;

			if(player.motionY < 0.4D)
				player.motionY += 0.1D;

			world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.flamethrowerShoot, SoundCategory.PLAYERS, 0.25F, 1.5F);
			this.useUpFuel(player, stack, 5);
		}
	}
}
