package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityHeaterElectric;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderHeaterElectric extends TileEntitySpecialRenderer<TileEntityHeaterElectric> {

	@Override
    public boolean isGlobalRenderer(final TileEntityHeaterElectric te) {
        return true;
    }

	@Override
    public void render(final TileEntityHeaterElectric tile, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
    	GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		switch(tile.getBlockMetadata() - BlockDummyable.offset) {
		case 3: GL11.glRotatef(270, 0F, 1F, 0F); break;
		case 5: GL11.glRotatef(0, 0F, 1F, 0F); break;
		case 2: GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 4: GL11.glRotatef(180, 0F, 1F, 0F); break;
		}

		bindTexture(ResourceManager.heater_electric_tex);
		ResourceManager.heater_electric.renderAll();
		
		GL11.glPopMatrix();
	}
}
