package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.oil.TileEntityMachineGasFlare;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderGasFlare extends TileEntitySpecialRenderer<TileEntityMachineGasFlare> {
    
	@Override
	public boolean isGlobalRenderer(final TileEntityMachineGasFlare te) {
		return true;
	}
	
	@Override
	public void render(final TileEntityMachineGasFlare te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.disableCull();
		GL11.glRotatef(180, 0F, 1F, 0F);

		bindTexture(ResourceManager.oilflare_tex);
        ResourceManager.oilflare.renderAll();

        GlStateManager.enableCull();
        GL11.glPopMatrix();
	}
}
