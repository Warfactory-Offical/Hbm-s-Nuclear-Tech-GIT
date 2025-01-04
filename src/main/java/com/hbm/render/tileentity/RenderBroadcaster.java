package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.render.model.ModelBroadcaster;
import com.hbm.tileentity.machine.TileEntityBroadcaster;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderBroadcaster extends TileEntitySpecialRenderer<TileEntityBroadcaster> {

	private static final ResourceLocation texture6 = new ResourceLocation(RefStrings.MODID + ":" + "textures/models/deco/ModelBroadcaster.png");
	private final ModelBroadcaster model6;
	
	public RenderBroadcaster() {
		this.model6 = new ModelBroadcaster();
	}
	
	@Override
	public void render(TileEntityBroadcaster te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);
		GL11.glRotated(180, 0, 1, 0);
		
		GlStateManager.enableLighting();
		this.bindTexture(texture6);
		switch(te.getBlockMetadata())
		{
		case 4:
			GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 2:
			GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 5:
			GL11.glRotatef(270, 0F, 1F, 0F); break;
		case 3:
			GL11.glRotatef(0, 0F, 1F, 0F); break;
		}
		this.model6.renderModel(0.0625F);
		GL11.glPopMatrix();
	}
}
