package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityTowerSmall;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderSmallTower extends TileEntitySpecialRenderer<TileEntityTowerSmall> {

	@Override
	public boolean isGlobalRenderer(final TileEntityTowerSmall te){
		return true;
	}
	
	@Override
	public void render(final TileEntityTowerSmall te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha){
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableLighting();
		GlStateManager.disableCull();
		
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		bindTexture(ResourceManager.tower_small_tex);
		ResourceManager.tower_small.renderAll();
		GlStateManager.shadeModel(GL11.GL_FLAT);
		
		GlStateManager.enableCull();
		GL11.glPopMatrix();
	}
}
