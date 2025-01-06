package com.hbm.render.item;

import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemFluidTank;
import org.lwjgl.opengl.GL11;

import com.hbm.render.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class FluidBarrelRender extends TEISRBase {
	public static final double HALF_A_PIXEL = 0.03125;
	public static final double PIX = 0.0625;
	public static final FluidBarrelRender INSTANCE = new FluidBarrelRender();
	public TextureAtlasSprite overlaySprite;
	
	@Override
	public void renderByItem(ItemStack stack) {
		GL11.glPushMatrix();
		GL11.glTranslated(0.5, 0.5, 0.5);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, itemModel);
		if(stack.getItem() instanceof ItemFluidTank){
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			int color = Fluids.fromID(stack.getMetadata()).getColor();
			if(overlaySprite == null){
				overlaySprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("hbm:items/fluid_barrel_overlay");
			}

			GL11.glTranslated(-0.5, -0.5, -HALF_A_PIXEL);
			RenderHelper.setColor(color);
			RenderHelper.startDrawingTexturedQuads();
			RenderHelper.drawFullTexture(overlaySprite, 0, 0, 1, 1, 0, false);
			RenderHelper.drawFullTexture(overlaySprite, 0, 0, 1, 1, PIX, true);
			RenderHelper.draw();
			RenderHelper.resetColor();
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		super.renderByItem(stack);
	}
}
