package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.bomb.TileEntityLandmine;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderLandmine extends TileEntitySpecialRenderer<TileEntityLandmine> {

	@Override
	public void render(final TileEntityLandmine te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.disableCull();
        
		GL11.glRotatef(180, 0F, 1F, 0F);

		final Block block = te.getWorld().getBlockState(te.getPos()).getBlock();

		if(block == ModBlocks.mine_ap) {
	        GL11.glTranslated(0, -0.075, 0);
			GL11.glScaled(1.5D, 1.5D, 1.5D);
			bindTexture(ResourceManager.mine_ap_tex);
        	ResourceManager.mine_ap.renderAll();
		}
		if(block == ModBlocks.mine_he) {
			GL11.glRotatef(-90, 0F, 1F, 0F);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			bindTexture(ResourceManager.mine_marelet_tex);
			ResourceManager.mine_marelet.renderAll();
			GL11.glShadeModel(GL11.GL_FLAT);
		}
		if(block == ModBlocks.mine_shrap) {
			bindTexture(ResourceManager.mine_shrap_tex);
        	ResourceManager.mine_he.renderAll();
		}
		if(block == ModBlocks.mine_fat) {
			GL11.glScaled(0.25D, 0.25D, 0.25D);
			bindTexture(ResourceManager.mine_fat_tex);
        	ResourceManager.mine_fat.renderAll();
		}

		GlStateManager.enableCull();
        GL11.glPopMatrix();
	}
}
