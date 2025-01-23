package com.hbm.render.entity;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.hbm.entity.particle.EntityTSmokeFX;
import com.hbm.items.ModItems;
import com.hbm.render.RenderHelper;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TSmokeRenderer extends Render<EntityTSmokeFX> {

	public static final IRenderFactory<EntityTSmokeFX> FACTORY = (RenderManager man) -> {return new TSmokeRenderer(man);};
	
	private Item field_94151_a;
	private final Map<Item, TextureAtlasSprite> textures = new HashMap<Item, TextureAtlasSprite>();

	protected TSmokeRenderer(final RenderManager renderManager) {
		super(renderManager);
		this.field_94151_a = ModItems.nuclear_waste;
		textures.put(ModItems.d_smoke1, RenderHelper.getItemTexture(ModItems.d_smoke1));
		textures.put(ModItems.d_smoke2, RenderHelper.getItemTexture(ModItems.d_smoke2));
		textures.put(ModItems.d_smoke3, RenderHelper.getItemTexture(ModItems.d_smoke3));
		textures.put(ModItems.d_smoke4, RenderHelper.getItemTexture(ModItems.d_smoke4));
		textures.put(ModItems.d_smoke5, RenderHelper.getItemTexture(ModItems.d_smoke5));
		textures.put(ModItems.d_smoke6, RenderHelper.getItemTexture(ModItems.d_smoke6));
		textures.put(ModItems.d_smoke7, RenderHelper.getItemTexture(ModItems.d_smoke7));
		textures.put(ModItems.d_smoke8, RenderHelper.getItemTexture(ModItems.d_smoke8));
		textures.put(ModItems.nuclear_waste, RenderHelper.getItemTexture(ModItems.nuclear_waste));

	}

	@Override
	public void doRender(final EntityTSmokeFX fx, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {

		if (fx.particleAge <= fx.maxAge && fx.particleAge >= fx.maxAge / 8 * 7) {
			field_94151_a = ModItems.d_smoke8;
		}

		if (fx.particleAge < fx.maxAge / 8 * 7 && fx.particleAge >= fx.maxAge / 8 * 6) {
			field_94151_a = ModItems.d_smoke7;
		}

		if (fx.particleAge < fx.maxAge / 8 * 6 && fx.particleAge >= fx.maxAge / 8 * 5) {
			field_94151_a = ModItems.d_smoke6;
		}

		if (fx.particleAge < fx.maxAge / 8 * 5 && fx.particleAge >= fx.maxAge / 8 * 4) {
			field_94151_a = ModItems.d_smoke5;
		}

		if (fx.particleAge < fx.maxAge / 8 * 4 && fx.particleAge >= fx.maxAge / 8 * 3) {
			field_94151_a = ModItems.d_smoke4;
		}

		if (fx.particleAge < fx.maxAge / 8 * 3 && fx.particleAge >= fx.maxAge / 8 * 2) {
			field_94151_a = ModItems.d_smoke3;
		}

		if (fx.particleAge < fx.maxAge / 8 * 2 && fx.particleAge >= fx.maxAge / 8) {
			field_94151_a = ModItems.d_smoke2;
		}

		if (fx.particleAge < fx.maxAge / 8 && fx.particleAge >= 0) {
			field_94151_a = ModItems.d_smoke1;
		}

		final TextureAtlasSprite iicon = textures.get(this.field_94151_a);

		if (iicon != null) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			//
			// GL11.glScalef(0.5F, 0.5F, 0.5F);
			//
			this.bindEntityTexture(fx);
			this.func_77026_a(iicon);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
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
		// p_77026_1_.setNormal(0.0F, 1.0F, 0.0F);
		RenderHelper.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
		RenderHelper.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
		RenderHelper.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2);
		RenderHelper.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2);
		RenderHelper.draw();
	}

	@Override
	protected ResourceLocation getEntityTexture(final EntityTSmokeFX entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
