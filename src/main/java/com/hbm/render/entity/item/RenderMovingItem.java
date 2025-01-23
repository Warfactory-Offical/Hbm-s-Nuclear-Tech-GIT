package com.hbm.render.entity.item;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.item.EntityMovingItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMovingItem extends Render<EntityMovingItem> {

	public static final IRenderFactory<EntityMovingItem> FACTORY = man -> new RenderMovingItem(man);
	
	protected RenderMovingItem(final RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(final EntityMovingItem item, final double x, final double y, final double z, final float f1, final float f2) {

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		final ItemStack stack = item.getItemStack();
		GL11.glScaled(0.5, 0.5, 0.5);
		if(!(stack.getItem() instanceof ItemBlock)) {
			GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslated(0.0, 0, -0.03);
		} else {
			GL11.glTranslated(0, 0.25, 0);
		}

        IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, item.world, null);
		model = ForgeHooksClient.handleCameraTransforms(model, TransformType.FIXED, false);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(final EntityMovingItem p_110775_1_) {
		return null;
	}

}