package com.hbm.render.entity;
import com.hbm.util.ItemStackUtil;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.hbm.entity.projectile.EntityDischarge;
import com.hbm.items.ModItems;
import com.hbm.render.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ElectricityRenderer extends Render<EntityDischarge> {

	public static final IRenderFactory<EntityDischarge> FACTORY = (RenderManager man) -> {return new ElectricityRenderer(man);};
	
	protected TextureAtlasSprite tex;
	
	protected ElectricityRenderer(final RenderManager renderManager) {
		super(renderManager);
		tex = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(ItemStackUtil.itemStackFrom(ModItems.discharge, 1, 0), null, null).getParticleTexture();
	}
	
	@Override
	public void doRender(final EntityDischarge entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		if (tex != null) {
			GL11.glPushMatrix();
			GlStateManager.disableLighting();
			GL11.glTranslated(x, y, z);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glScalef(7.5F, 7.5F, 7.5F);
			this.bindEntityTexture(entity);

			this.func_77026_a(tex);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GlStateManager.enableLighting();
			GL11.glPopMatrix();
		}
	}
	
	@Override
	public void doRenderShadowAndFire(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks) {}

	@Override
	protected ResourceLocation getEntityTexture(final EntityDischarge entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	private void func_77026_a(final TextureAtlasSprite p_77026_2_) {
		final float f = p_77026_2_.getMinU();
		final float f1 = p_77026_2_.getMaxU();
		final float f2 = p_77026_2_.getMinV();
		final float f3 = p_77026_2_.getMaxV();
		final float f4 = 1.0F;
		final float f5 = 0.5F;
		final float f6 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		RenderHelper.startDrawingTexturedQuads();
		//p_77026_1_.setNormal(0.0F, 1.0F, 0.0F);
		RenderHelper.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
		RenderHelper.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
		RenderHelper.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2);
		RenderHelper.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2);
		RenderHelper.draw();
	}
	
}
