package com.hbm.items.weapon;

import com.hbm.handler.GunConfiguration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGunCCPlasmaCannon extends ItemGunBase {

	public ItemGunCCPlasmaCannon(final GunConfiguration config, final String s) {
		super(config, s);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onFireClient(final ItemStack stack, final EntityPlayer player, final boolean shouldDoThirdPerson) {
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasCustomHudElement() {
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderHUD(final Pre event, final ElementType type, final EntityPlayer player, final ItemStack stack, final EnumHand hand) {
		
	}

}
