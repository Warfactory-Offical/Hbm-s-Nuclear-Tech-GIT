package com.hbm.render.entity;
import com.hbm.util.ItemStackUtil;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.hbm.entity.particle.EntitySSmokeFX;
import com.hbm.items.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SSmokeRenderer extends Render<EntitySSmokeFX> {

	private Item item;
	private final int meta;

	public SSmokeRenderer(final RenderManager manager, final Item p_i1259_1_, final int p_i1259_2_) {
		super(manager);
		this.item = p_i1259_1_;
		this.meta = p_i1259_2_;
	}

	public SSmokeRenderer(final RenderManager manager, final Item p_i1260_1_) {
		this(manager, p_i1260_1_, 0);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void func_76986_a(T entity, double d, double d1, double d2, float f,
	 * float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(final EntitySSmokeFX fx, final double p_76986_2_, final double p_76986_4_, final double p_76986_6_, final float p_76986_8_,
                         final float p_76986_9_) {


			if (fx.particleAge <= fx.maxAge && fx.particleAge >= fx.maxAge / 8 * 7) {
				item = ModItems.smoke8;
			}

			if (fx.particleAge < fx.maxAge / 8 * 7 && fx.particleAge >= fx.maxAge / 8 * 6) {
				item = ModItems.smoke7;
			}

			if (fx.particleAge < fx.maxAge / 8 * 6 && fx.particleAge >= fx.maxAge / 8 * 5) {
				item = ModItems.smoke6;
			}

			if (fx.particleAge < fx.maxAge / 8 * 5 && fx.particleAge >= fx.maxAge / 8 * 4) {
				item = ModItems.smoke5;
			}

			if (fx.particleAge < fx.maxAge / 8 * 4 && fx.particleAge >= fx.maxAge / 8 * 3) {
				item = ModItems.smoke4;
			}

			if (fx.particleAge < fx.maxAge / 8 * 3 && fx.particleAge >= fx.maxAge / 8 * 2) {
				item = ModItems.smoke3;
			}

			if (fx.particleAge < fx.maxAge / 8 * 2 && fx.particleAge >= fx.maxAge / 8) {
				item = ModItems.smoke2;
			}

			if (fx.particleAge < fx.maxAge / 8 && fx.particleAge >= 0) {
				item = ModItems.smoke1;
			}

			final TextureAtlasSprite tex = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(ItemStackUtil.itemStackFrom(item, 1, meta), null, null).getParticleTexture();

			if (tex != null) {
				GL11.glPushMatrix();
				GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_, (float) p_76986_6_);
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glScalef(7.5F, 7.5F, 7.5F);
				//
				GL11.glScalef(0.25F, 0.25F, 0.25F);
				//
				this.bindEntityTexture(fx);
				final Tessellator tessellator = Tessellator.getInstance();

				this.func_77026_a(tessellator, tex);
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
			}
		
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(final EntitySSmokeFX e) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	private void func_77026_a(final Tessellator tes, final TextureAtlasSprite tex) {
		final BufferBuilder buf = tes.getBuffer();
		GlStateManager.disableLighting();
		final float f = tex.getMinU();
		final float f1 = tex.getMaxU();
		final float f2 = tex.getMinV();
		final float f3 = tex.getMaxV();
		final float f4 = 1.0F;
		final float f5 = 0.5F;
		final float f6 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buf.pos(0.0F - f5, 0.0F - f6, 0.0D).tex(f, f3).endVertex();
		buf.pos(f4 - f5, 0.0F - f6, 0.0D).tex(f1, f3).endVertex();
		buf.pos(f4 - f5, f4 - f6, 0.0D).tex(f1, f2).endVertex();
		buf.pos(0.0F - f5, f4 - f6, 0.0D).tex(f, f2).endVertex();
		tes.draw();
		GlStateManager.enableLighting();
	}

}
