package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityStorageDrum;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderStorageDrum extends TileEntitySpecialRenderer<TileEntityStorageDrum> {

	@Override
	public void render(final TileEntityStorageDrum te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha){
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		
		bindTexture(ResourceManager.waste_drum_tex);
		ResourceManager.waste_drum.renderAll();

		GL11.glPopMatrix();
	}
}
