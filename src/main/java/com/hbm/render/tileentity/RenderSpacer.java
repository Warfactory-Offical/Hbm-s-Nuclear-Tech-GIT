package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntitySpacer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderSpacer extends TileEntitySpecialRenderer<TileEntitySpacer> {

	@Override
	public boolean isGlobalRenderer(final TileEntitySpacer te){
		return true;
	}
	
	@Override
	public void render(final TileEntitySpacer te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha){
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		
		bindTexture(ResourceManager.fraction_spacer_tex);
		ResourceManager.fraction_spacer.renderAll();
		
		GL11.glPopMatrix();
	}
}
