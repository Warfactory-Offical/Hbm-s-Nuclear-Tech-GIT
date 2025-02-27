package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.Library;
import com.hbm.main.ResourceManager;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderRTG extends TileEntitySpecialRenderer<TileEntity> {

	@Override
	public void render(final TileEntity te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.disableCull();
		GL11.glRotatef(180, 0F, 1F, 0F);

        if(te.getBlockType() == ModBlocks.machine_rtg_grey){
            bindTexture(ResourceManager.rtg_tex);
        }  else if(te.getBlockType() == ModBlocks.machine_powerrtg){
            bindTexture(ResourceManager.rtg_polonium_tex);
		} else {
            bindTexture(ResourceManager.rtg_cell_tex);
        }
        //Drillgon200: This is handled by the forge model
        //ResourceManager.rtg.renderPart("Gen");

        if(Library.canConnect(te.getWorld(), te.getPos().add(1, 0, 0), Library.POS_X))
        	ResourceManager.rtg_connector.renderAll();

        if(Library.canConnect(te.getWorld(), te.getPos().add(-1, 0, 0), Library.NEG_X)) {
    		GL11.glRotatef(180, 0F, 1F, 0F);
    		ResourceManager.rtg_connector.renderAll();
    		GL11.glRotatef(-180, 0F, 1F, 0F);
        }

        if(Library.canConnect(te.getWorld(), te.getPos().add(0, 0, -1), Library.NEG_Z)) {
    		GL11.glRotatef(90, 0F, 1F, 0F);
    		ResourceManager.rtg_connector.renderAll();
    		GL11.glRotatef(-90, 0F, 1F, 0F);
        }

        if(Library.canConnect(te.getWorld(), te.getPos().add(0, 0, 1), Library.POS_Z)) {
    		GL11.glRotatef(-90, 0F, 1F, 0F);
    		ResourceManager.rtg_connector.renderAll();
    		GL11.glRotatef(90, 0F, 1F, 0F);
        }

        GL11.glPopMatrix();
	}
}
