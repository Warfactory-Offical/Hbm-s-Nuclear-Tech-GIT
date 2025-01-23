package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.oil.TileEntityMachineOilWell;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderDerrick extends TileEntitySpecialRenderer<TileEntityMachineOilWell> {
    
    @Override
    public boolean isGlobalRenderer(final TileEntityMachineOilWell te) {
    	return true;
    }
	
	@Override
	public void render(final TileEntityMachineOilWell te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
		GL11.glRotatef(180, 0F, 1F, 0F);

		bindTexture(ResourceManager.derrick_tex);
        ResourceManager.derrick.renderAll();

        GL11.glPopMatrix();
	}
}
