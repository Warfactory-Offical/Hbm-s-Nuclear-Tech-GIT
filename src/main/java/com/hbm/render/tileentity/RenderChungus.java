package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.BlockDummyable;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityChungus;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderChungus extends TileEntitySpecialRenderer<TileEntityChungus> {

	@Override
	public boolean isGlobalRenderer(final TileEntityChungus te){
		return true;
	}
	
	@Override
	public void render(final TileEntityChungus turbine, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha){
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableLighting();
		GlStateManager.enableCull();

		GL11.glRotatef(90, 0F, 1F, 0F);

		switch(turbine.getBlockMetadata() - BlockDummyable.offset) {
		case 2:
			GL11.glRotatef(90, 0F, 1F, 0F);
			break;
		case 4:
			GL11.glRotatef(180, 0F, 1F, 0F);
			break;
		case 3:
			GL11.glRotatef(270, 0F, 1F, 0F);
			break;
		case 5:
			GL11.glRotatef(0, 0F, 1F, 0F);
			break;
		}

		GL11.glTranslated(0, 0, -3);

		bindTexture(ResourceManager.chungus_tex);

		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		ResourceManager.chungus.renderPart("Body");
		
		GL11.glPushMatrix();
		GL11.glTranslated(0, 0, 4.5);
		int rot = 0;
		if(turbine.types[0] == ModForgeFluids.hotsteam){
			rot = 1;
		} else if(turbine.types[0] == ModForgeFluids.superhotsteam){
			rot = 2;
		} else if(turbine.types[0] == ModForgeFluids.ultrahotsteam){
			rot = 3;
		}
		GL11.glRotatef(15 - rot * 10, 1, 0, 0);
		GL11.glTranslated(0, 0, -4.5);
		ResourceManager.chungus.renderPart("Lever");
		GL11.glPopMatrix();

		GL11.glTranslated(0, 2.5, 0);
		GL11.glRotatef(turbine.lastRotor + (turbine.rotor - turbine.lastRotor) * partialTicks, 0, 0, -1);
		GL11.glTranslated(0, -2.5, 0);
		
		ResourceManager.chungus.renderPart("Blades");

		GlStateManager.shadeModel(GL11.GL_FLAT);

		GL11.glPopMatrix();
	}
}
