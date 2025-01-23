package com.hbm.render.misc;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.render.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class DiamondPronter {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/models/misc/danger_diamond.png");
	
	public static void pront(final int poison, final int flammability, final int reactivity, final EnumSymbol symbol) {
		
		GL11.glPushMatrix();
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		final float p = 1F/256F;
		final float s = 1F/139F;
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

		RenderHelper.startDrawingTexturedQuads();
		RenderHelper.addVertexWithUV(0.0, 0.5, -0.5, p * 144, p * 45);
		RenderHelper.addVertexWithUV(0.0, 0.5, 0.5, p * 5, p * 45);
		RenderHelper.addVertexWithUV(0.0, -0.5, 0.5, p * 5, p * 184);
		RenderHelper.addVertexWithUV(0.0, -0.5, -0.5, p * 144, p * 184);
		RenderHelper.draw();
		
		final float width = 10F * s;
		final float height = 14F * s;
		
		if(poison >= 0 && poison < 6) {
			
			final float oY = 0;
			final float oZ = 33 * s;
			
			int x = 5 + (poison - 1) * 24;
			final int y = 5;
			
			if(poison == 0) x = 125;

			RenderHelper.startDrawingTexturedQuads();
			RenderHelper.addVertexWithUV(0.01, height + oY, -width + oZ, (x + 20) * p, y * p);
			RenderHelper.addVertexWithUV(0.01, height + oY, width + oZ, x * p, y * p);
			RenderHelper.addVertexWithUV(0.01, -height + oY, width + oZ, x * p, (y + 28) * p);
			RenderHelper.addVertexWithUV(0.01, -height + oY, -width + oZ, (x + 20) * p, (y + 28) * p);
			RenderHelper.draw();
		}
		
		if(flammability >= 0 && flammability < 6) {
			
			final float oY = 33 * s;
			final float oZ = 0;
			
			int x = 5 + (flammability - 1) * 24;
			final int y = 5;
			
			if(flammability == 0) x = 125;

			RenderHelper.startDrawingTexturedQuads();
			RenderHelper.addVertexWithUV(0.01, height + oY, -width + oZ, (x + 20) * p, y * p);
			RenderHelper.addVertexWithUV(0.01, height + oY, width + oZ, x * p, y * p);
			RenderHelper.addVertexWithUV(0.01, -height + oY, width + oZ, x * p, (y + 28) * p);
			RenderHelper.addVertexWithUV(0.01, -height + oY, -width + oZ, (x + 20) * p, (y + 28) * p);
			RenderHelper.draw();
		}
		
		if(reactivity >= 0 && reactivity < 6) {
			
			final float oY = 0;
			final float oZ = -33 * s;
			
			int x = 5 + (reactivity - 1) * 24;
			final int y = 5;
			
			if(reactivity == 0) x = 125;

			RenderHelper.startDrawingTexturedQuads();
			RenderHelper.addVertexWithUV(0.01, height + oY, -width + oZ, (x + 20) * p, y * p);
			RenderHelper.addVertexWithUV(0.01, height + oY, width + oZ, x * p, y * p);
			RenderHelper.addVertexWithUV(0.01, -height + oY, width + oZ, x * p, (y + 28) * p);
			RenderHelper.addVertexWithUV(0.01, -height + oY, -width + oZ, (x + 20) * p, (y + 28) * p);
			RenderHelper.draw();
		}
		

		final float symSize = 59F/2F * s;
		
		if(symbol != EnumSymbol.NONE) {
			
			final float oY = -33 * s;
			final float oZ = 0;
			
			final int x = symbol.x;
			final int y = symbol.y;

			RenderHelper.startDrawingTexturedQuads();
			RenderHelper.addVertexWithUV(0.01, symSize + oY, -symSize + oZ, (x + 59) * p, y * p);
			RenderHelper.addVertexWithUV(0.01, symSize + oY, symSize + oZ, x * p, y * p);
			RenderHelper.addVertexWithUV(0.01, -symSize + oY, symSize + oZ, x * p, (y + 59) * p);
			RenderHelper.addVertexWithUV(0.01, -symSize + oY, -symSize + oZ, (x + 59) * p, (y + 59) * p);
			RenderHelper.draw();
		}

		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}
}
