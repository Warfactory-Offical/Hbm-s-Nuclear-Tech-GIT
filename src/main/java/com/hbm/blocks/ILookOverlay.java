package com.hbm.blocks;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.world.World;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ILookOverlay {

	@SideOnly(Side.CLIENT)
	public void printHook(RenderGameOverlayEvent.Pre event, World world, int x, int y, int z);

	@SideOnly(Side.CLIENT)
	public static void printGeneric(final RenderGameOverlayEvent.Pre event, final String title, final int titleCol, final int bgCol, final List<String> text) {

		final Minecraft mc = Minecraft.getMinecraft();
		final ScaledResolution resolution = event.getResolution();

		GL11.glPushMatrix();

		final int pX = resolution.getScaledWidth() / 2 + 8;
		int pZ = resolution.getScaledHeight() / 2;

		mc.fontRenderer.drawString(title, pX + 1, pZ - 9, bgCol);
		mc.fontRenderer.drawString(title, pX, pZ - 10, titleCol);

		try {
			for(String line : text) {
	
				int color = 0xFFFFFF;
				if(line.startsWith("&[")) {
					final int end = line.lastIndexOf("&]");
					color = Integer.parseInt(line.substring(2, end));
					line = line.substring(end + 2);
				}
				
				mc.fontRenderer.drawStringWithShadow(line, pX, pZ, color);
				pZ += 10;
			}
		} catch(final Exception ex) {
			mc.fontRenderer.drawStringWithShadow(ex.getClass().getSimpleName(), pX, pZ + 10, 0xff0000);
		}

		GlStateManager.disableBlend();

		GL11.glPopMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(Gui.ICONS);
	}
}
