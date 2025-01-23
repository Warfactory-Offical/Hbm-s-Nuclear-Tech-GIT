package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineCentrifuge;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderCentrifuge extends TileEntitySpecialRenderer<TileEntityMachineCentrifuge> {

	@Override
	public boolean isGlobalRenderer(final TileEntityMachineCentrifuge te) {
		return true;
	}
	
	@Override
	public void render(final TileEntityMachineCentrifuge te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
		switch(te.getBlockMetadata())
		{
		case 4:
			GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 3:
			GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 5:
			GL11.glRotatef(270, 0F, 1F, 0F); break;
		case 2:
			GL11.glRotatef(0, 0F, 1F, 0F); break;
		}

		bindTexture(ResourceManager.centrifuge_new_tex);
        ResourceManager.centrifuge.renderAll();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        
        GL11.glPopMatrix();
	}
}
