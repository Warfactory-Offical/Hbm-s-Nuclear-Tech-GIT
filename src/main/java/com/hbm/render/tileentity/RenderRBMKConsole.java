package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.hbm.blocks.BlockDummyable;
import com.hbm.main.ResourceManager;
import com.hbm.util.I18nUtil;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.RBMKColumn;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.RBMKScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderRBMKConsole extends TileEntitySpecialRenderer<TileEntityRBMKConsole> {

	@Override
	public boolean isGlobalRenderer(final TileEntityRBMKConsole te){
		return true;
	}
	
	@Override
	public void render(final TileEntityRBMKConsole te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha){
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
		
		GlStateManager.enableCull();
		GlStateManager.enableLighting();
		
		switch(te.getBlockMetadata() - BlockDummyable.offset) {
		case 2: GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 4: GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 3: GL11.glRotatef(270, 0F, 1F, 0F); break;
		case 5: GL11.glRotatef(0, 0F, 1F, 0F); break;
		}
		
		GL11.glTranslated(0.5, 0, 0);

		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		bindTexture(ResourceManager.rbmk_console_tex);
		ResourceManager.rbmk_console.renderAll();
		GlStateManager.shadeModel(GL11.GL_FLAT);

		///New part
		final TileEntityRBMKConsole console = te;
		
		final Tessellator tess = Tessellator.getInstance();
		final BufferBuilder buf = tess.getBuffer();
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		for(int i = 0; i < console.columns.length; i++) {
			
			final RBMKColumn col = console.columns[i];
			
			if(col == null)
				continue;

			final double kx = -0.37D;
			final double ky = -(i / 15) * 0.125 + 3.625;
			final double kz = -(i % 15) * 0.125 + 0.125D * 7;
			
			drawColumn(buf, kx, ky, kz, (float)(0.75D + (i % 2) * 0.05D), col.data.getDouble("heat") / col.data.getDouble("maxHeat"));
			
			switch(col.type) {
			case FUEL:
			case FUEL_SIM:		drawFuel(buf, kx + 0.01, ky, kz, col.data.getDouble("enrichment")); break;
			case CONTROL:		drawControl(buf, kx + 0.01, ky, kz, col.data.getDouble("level")); break;
			case CONTROL_AUTO:	drawControlAuto(buf, kx + 0.01, ky, kz, col.data.getDouble("level")); break;
			default:
			}
		}
		
		tess.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		final FontRenderer font = Minecraft.getMinecraft().fontRenderer;
		GL11.glTranslatef(-0.42F, 3.5F, 1.75F);
        GlStateManager.depthMask(false);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GlStateManager.color(1, 1, 1, 1);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		
		for(int i = 0; i < console.screens.length; i++) {
			
			GL11.glPushMatrix();
			
			if(i % 2 == 1)
				GL11.glTranslatef(0, 0, 1.75F * -2);
			
			GL11.glTranslatef(0, -0.75F * (i / 2), 0);
			
			final RBMKScreen screen = console.screens[i];
			String text = screen.display;
			
			if(text != null && ! text.isEmpty()) {
				
				final String[] parts = text.split("=");
				
				if(parts.length == 2) {
					text = I18nUtil.resolveKey(parts[0], parts[1]);
				}

				final int width = font.getStringWidth(text);
				final int height = font.FONT_HEIGHT;
				
				final float f3 = Math.min(0.03F, 0.8F / Math.max(width, 1));
				GL11.glScalef(f3, -f3, f3);
				GL11.glNormal3f(0.0F, 0.0F, -1.0F);
				GL11.glRotatef(90, 0, 1, 0);
				
				font.drawString(text, - width / 2, - height / 2, 0x00ff00);
			}
			GL11.glPopMatrix();
		}
		
        GlStateManager.depthMask(true);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		///

		GL11.glPopMatrix();
	}

	private void drawColumn(final BufferBuilder buf, final double x, final double y, final double z, final float color, final double heat) {
		
		final double width = 0.0625D * 0.75;
		final float r = (float) (color + ((1 - color) * Math.max(0, heat-0.4)));
		final float d = (float) (color - (color * Math.max(0, heat-0.6)));
		buf.pos(x, y + width, z - width).color(r, d, d, 1F).endVertex();
		buf.pos(x, y + width, z + width).color(r, d, d, 1F).endVertex();
		buf.pos(x, y - width, z + width).color(r, d, d, 1F).endVertex();
		buf.pos(x, y - width, z - width).color(r, d, d, 1F).endVertex();
	}
	
	private void drawFuel(final BufferBuilder buf, final double x, final double y, final double z, final double enrichment) {
		this.drawDot(buf, x, y, z, 0F, 0.25F + (float) (enrichment * 0.75D), 0F);
	}
	
	private void drawControl(final BufferBuilder buf, final double x, final double y, final double z, final double level) {
		this.drawDot(buf, x, y, z, (float) level, (float) level, 0F);
	}
	
	private void drawControlAuto(final BufferBuilder buf, final double x, final double y, final double z, final double level) {
		this.drawDot(buf, x, y, z, (float) level, 0F, (float) level);
	}
	
	private void drawDot(final BufferBuilder buf, final double x, final double y, final double z, final float r, final float g, final float b) {
		
		final double width = 0.03125D;
		final double edge = 0.022097D;
		
		buf.pos(x, y + width, z).color(r, g, b, 1F).endVertex();
		buf.pos(x, y + edge, z + edge).color(r, g, b, 1F).endVertex();
		buf.pos(x, y, z + width).color(r, g, b, 1F).endVertex();
		buf.pos(x, y - edge, z + edge).color(r, g, b, 1F).endVertex();

		buf.pos(x, y + edge, z - edge).color(r, g, b, 1F).endVertex();
		buf.pos(x, y + width, z).color(r, g, b, 1F).endVertex();
		buf.pos(x, y - edge, z - edge).color(r, g, b, 1F).endVertex();
		buf.pos(x, y, z - width).color(r, g, b, 1F).endVertex();
		
		buf.pos(x, y + width, z).color(r, g, b, 1F).endVertex();
		buf.pos(x, y - edge, z + edge).color(r, g, b, 1F).endVertex();
		buf.pos(x, y - width, z).color(r, g, b, 1F).endVertex();
		buf.pos(x, y - edge, z - edge).color(r, g, b, 1F).endVertex();
	}
}
