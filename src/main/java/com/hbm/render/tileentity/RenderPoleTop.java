package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.render.model.ModelPoleTop;
import com.hbm.tileentity.deco.TileEntityDecoPoleTop;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderPoleTop extends TileEntitySpecialRenderer<TileEntityDecoPoleTop> {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":" + "textures/models/deco/PoleTop.png");
	
	private final ModelPoleTop model;
	
	public RenderPoleTop() {
		this.model = new ModelPoleTop();
	}
	
	@Override
	public void render(final TileEntityDecoPoleTop te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GL11.glRotatef(180, 0F, 0F, 1F);
		
			this.bindTexture(texture);
		
			GL11.glPushMatrix();
				this.model.renderModel(0.0625F);
			GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
