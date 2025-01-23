package com.hbm.render.item;

import org.lwjgl.opengl.GL11;

import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.render.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class ItemRenderFluidIcon extends TEISRBase {

	private static final double HALF_A_PIXEL = 0.03125;
	private static final double PIX = 0.0625;

	@Override
	public void renderByItem(final ItemStack stack) {
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		RenderHelper.bindBlockTexture();

		final Tessellator tes = Tessellator.getInstance();
		final BufferBuilder buf = Tessellator.getInstance().getBuffer();
		GL11.glPushMatrix();
		GL11.glTranslated(0.5, 0.5, 0.5);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, itemModel);
		GL11.glPopMatrix();

		final Fluid f = ItemFluidIcon.getFluid(stack);
		TextureAtlasSprite lava = null;
		if (f != null)
			lava = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(f.getStill().toString());

		if (lava != null) {
			RenderHelper.setColor(f.getColor(new FluidStack(f, 1000)));
			GlStateManager.disableLighting();

			GL11.glTranslated(0, 0, 0.5 + HALF_A_PIXEL);
			buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

			drawRect(buf, lava, 5, 2, 11, 9);
			drawRect(buf, lava, 6, 1, 10, 2);
			drawRect(buf, lava, 4, 3, 5, 7);
			drawRect(buf, lava, 11, 3, 12, 7);
			drawRect(buf, lava, 6, 9, 10, 12);
			drawRect(buf, lava, 7, 12, 9, 15);

			tes.draw();
			GlStateManager.enableLighting();
			
		}
		GL11.glPopAttrib();
		GL11.glPopMatrix();
		super.renderByItem(stack);
	}

	private void drawRect(final BufferBuilder buf, final TextureAtlasSprite texture, final int x1, final int y1, final int x2, final int y2){
		final float maxU = texture.getInterpolatedU(x2);
		final float minU = texture.getInterpolatedU(x1);
		final float maxV = texture.getInterpolatedV(y2);
		final float minV = texture.getInterpolatedV(y1);
		
		buf.pos(x1 * PIX, y1 * PIX, 0).tex(minU, minV).endVertex();
		buf.pos(x2 * PIX, y1 * PIX, 0).tex(maxU, minV).endVertex();
		buf.pos(x2 * PIX, y2 * PIX, 0).tex(maxU, maxV).endVertex();
		buf.pos(x1 * PIX, y2 * PIX, 0).tex(minU, maxV).endVertex();

		buf.pos(x2 * PIX, y1 * PIX, -PIX).tex(maxU, minV).endVertex();
		buf.pos(x1 * PIX, y1 * PIX, -PIX).tex(minU, minV).endVertex();
		buf.pos(x1 * PIX, y2 * PIX, -PIX).tex(minU, maxV).endVertex();
		buf.pos(x2 * PIX, y2 * PIX, -PIX).tex(maxU, maxV).endVertex();
	}
}
