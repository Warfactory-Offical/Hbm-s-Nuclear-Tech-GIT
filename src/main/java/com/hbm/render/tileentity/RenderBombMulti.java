package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.bomb.TileEntityBombMulti;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderBombMulti extends TileEntitySpecialRenderer<TileEntityBombMulti> {
    
    @Override
    public boolean isGlobalRenderer(final TileEntityBombMulti te) {
    	return true;
    }
    
    @Override
    public void render(final TileEntityBombMulti te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
    	GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.disableCull();
        GL11.glRotatef(180, 1F, 0F, 0F);
        
		switch(te.getBlockMetadata())
		{
		case 5:
			GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 2:
			GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 4:
			GL11.glRotatef(0, 0F, 1F, 0F); break;
		case 3:
			GL11.glRotatef(270, 0F, 1F, 0F); break;
		}

		bindTexture(ResourceManager.bomb_multi_tex);
        ResourceManager.bomb_multi.renderAll();

        GlStateManager.enableCull();
        GL11.glPopMatrix();
    }
}
