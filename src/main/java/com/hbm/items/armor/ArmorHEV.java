package com.hbm.items.armor;

import com.hbm.lib.Library;
import com.hbm.render.model.ModelArmorHEV;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class ArmorHEV extends ArmorFSBPowered {

	@SideOnly(Side.CLIENT)
	ModelArmorHEV[] models;
	
	public ArmorHEV(final ArmorMaterial material, final int layer, final EntityEquipmentSlot slot, final String texture, final long maxPower, final long chargeRate, final long consumption, final long drain, final String s) {
		super(material, layer, slot, texture, maxPower, chargeRate, consumption, drain, s);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack itemStack, final EntityEquipmentSlot armorSlot, final ModelBiped _default) {
		if(models == null) {
			models = new ModelArmorHEV[4];

			for(int i = 0; i < 4; i++)
				models[i] = new ModelArmorHEV(i);
		}
		return models[3-armorSlot.getIndex()];
	}
	
	@SideOnly(Side.CLIENT)
    @Override
	public void handleOverlay(final RenderGameOverlayEvent.Pre event, final EntityPlayer player) {
    	if(hasFSBArmorIgnoreCharge(player)) {
    		if(event.getType() == ElementType.ARMOR) {
    			event.setCanceled(true);
    			return;
    		}
    		if(event.getType() == ElementType.HEALTH) {
    			event.setCanceled(true);
    			renderOverlay(event, player);
            }
    	}
    }

	private static long lastSurvey;
	private static float prevResult;
	private static float lastResult;

    private void renderOverlay(final RenderGameOverlayEvent.Pre event, final EntityPlayer player) {
		float in = 0;
		in = Library.getEntRadCap(player).getRads();

        float radiation = 0;

        radiation = lastResult - prevResult;

        if(System.currentTimeMillis() >= lastSurvey + 1000) {
        	lastSurvey = System.currentTimeMillis();
        	prevResult = lastResult;
        	lastResult = in;
        }

		GL11.glPushMatrix();

		GlStateManager.enableBlend();
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();

        final ScaledResolution res = event.getResolution();

        double scale = 2D;

        GL11.glScaled(scale, scale, scale);

		final int hX = (int)(8 / scale);
		final int hY = (int)((res.getScaledHeight() - 18 - 2) / scale);

		final int healthColor = player.getHealth() * 5 > 15 ? 0xff8000 : 0xff0000;

		Minecraft.getMinecraft().fontRenderer.drawString("+" + (int)(player.getHealth() * 5), hX, hY, healthColor);

		double c = 0D;

		for(int i = 0; i < 4; i++) {

			final ItemStack armor = player.inventory.armorInventory.get(i);
			final ArmorFSBPowered item = ((ArmorFSBPowered)player.inventory.armorInventory.get(i).getItem());

			c += (double)item.getCharge(armor) / (double)item.getMaxCharge();
		}

		final int aX = (int)(70 / scale);
		final int aY = (int)((res.getScaledHeight() - 18 - 2) / scale);

		final int armorColor = c * 25 > 15 ? 0xff8000 : 0xff0000;

		Minecraft.getMinecraft().fontRenderer.drawString("||" + (int)(c * 25), aX, aY, armorColor);

		String rad = "☢ [";

		for(int i = 0; i < 10; i++) {

			if(in / 100 > i) {

				final int mid = (int)(in - i * 100);

				if(mid < 33)
					rad += "..";
				else if(mid < 67)
					rad += "|.";
				else
					rad += "||";
			} else {
				rad += " ";
			}
		}

		rad += "]";

		final int rX = (int)(8 / scale);
		final int rY = (int)((res.getScaledHeight() - 40) / scale);

		final int radColor = in < 800 ? 0xff8000 : 0xff0000;

		Minecraft.getMinecraft().fontRenderer.drawString(rad, rX, rY, radColor);

        GL11.glScaled(1/scale, 1/scale, 1/scale);

        scale = 1D;

        GL11.glScaled(scale, scale, scale);

        if(radiation > 0) {

			final int dX = (int)(32 / scale);
			final int dY = (int)((res.getScaledHeight() - 55) / scale);

			String delta = "" + Math.round(radiation);

			if(radiation > 1000)
				delta = ">1000";
			else if(radiation < 1)
				delta = "<1";

			Minecraft.getMinecraft().fontRenderer.drawString(delta + " RAD/s", dX, dY, 0xFF0000);
        }

		GlStateManager.color(1, 1, 1, 1);

        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GL11.glPopMatrix();

		Minecraft.getMinecraft().renderEngine.bindTexture(Gui.ICONS);
    }

}
