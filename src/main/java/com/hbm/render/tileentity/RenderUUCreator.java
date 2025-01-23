package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineUUCreator;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderUUCreator extends TileEntitySpecialRenderer<TileEntityMachineUUCreator> {

	@Override
	public void render(final TileEntityMachineUUCreator te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y, z + 0.5F);

		GlStateManager.enableLighting();
        GlStateManager.disableCull();
		
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		bindTexture(ResourceManager.uu_creator_tex);
		ResourceManager.uu_creator.renderAll();
		GlStateManager.shadeModel(GL11.GL_FLAT);
		
		GL11.glPopMatrix();
	}
}
