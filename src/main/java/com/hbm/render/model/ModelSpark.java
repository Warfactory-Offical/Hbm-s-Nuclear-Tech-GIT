package com.hbm.render.model;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.hbm.config.GeneralConfig;
import com.hbm.handler.HbmShaderManager;
import com.hbm.render.item.weapon.ItemRenderOverkill;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;

public class ModelSpark extends ModelBase {

	public boolean renderingInFirstPerson;
	
	ModelRenderer BarrelMain;
	ModelRenderer BarrelSide;
	ModelRenderer PlateFront;
	ModelRenderer Sight;
	ModelRenderer Grip;
	ModelRenderer Cell1;
	ModelRenderer Cell2;
	ModelRenderer Cell3;
	ModelRenderer Cell4;
	ModelRenderer Cell5;
	ModelRenderer Cell8;
	ModelRenderer Cell7;
	ModelRenderer Cell6;
	ModelRenderer PlateBack;
	ModelRenderer Body;
	ModelRenderer Handle1;
	ModelRenderer Handle2;

	public ModelSpark() {
		textureWidth = 64;
		textureHeight = 64;

		BarrelMain = new ModelRenderer(this, 0, 0);
		BarrelMain.addBox(0F, 0F, 0F, 18, 6, 6);
		BarrelMain.setRotationPoint(-18F, 0F, -3F);
		BarrelMain.setTextureSize(64, 64);
		BarrelMain.mirror = true;
		setRotation(BarrelMain, 0F, 0F, 0F);
		BarrelSide = new ModelRenderer(this, 0, 12);
		BarrelSide.addBox(0F, -3F, -3F, 14, 6, 6);
		BarrelSide.setRotationPoint(-14F, 3F, 0F);
		BarrelSide.setTextureSize(64, 64);
		BarrelSide.mirror = true;
		setRotation(BarrelSide, 0.7853982F, 0F, 0F);
		PlateFront = new ModelRenderer(this, 40, 12);
		PlateFront.addBox(0F, 0F, 0F, 2, 8, 8);
		PlateFront.setRotationPoint(0F, -1F, -4F);
		PlateFront.setTextureSize(64, 64);
		PlateFront.mirror = true;
		setRotation(PlateFront, 0F, 0F, 0F);
		Sight = new ModelRenderer(this, 48, 0);
		Sight.addBox(0F, 0F, 0F, 1, 4, 2);
		Sight.setRotationPoint(-16F, -4F, -1F);
		Sight.setTextureSize(64, 64);
		Sight.mirror = true;
		setRotation(Sight, 0F, 0F, 0F);
		Grip = new ModelRenderer(this, 0, 24);
		Grip.addBox(0F, 0F, 0F, 12, 2, 2);
		Grip.setRotationPoint(-10F, 5F, -6F);
		Grip.setTextureSize(64, 64);
		Grip.mirror = true;
		setRotation(Grip, 0F, 0F, 0F);
		Cell1 = new ModelRenderer(this, 0, 28);
		Cell1.addBox(0F, -3.5F, -1F, 3, 2, 2);
		Cell1.setRotationPoint(2F, 3F, 0F);
		Cell1.setTextureSize(64, 64);
		Cell1.mirror = true;
		setRotation(Cell1, 0F, 0F, 0F);
		Cell2 = new ModelRenderer(this, 0, 32);
		Cell2.addBox(0F, -3.5F, -1F, 3, 2, 2);
		Cell2.setRotationPoint(2F, 3F, 0F);
		Cell2.setTextureSize(64, 64);
		Cell2.mirror = true;
		setRotation(Cell2, 0.7853982F, 0F, 0F);
		Cell3 = new ModelRenderer(this, 0, 36);
		Cell3.addBox(0F, -3.5F, -1F, 3, 2, 2);
		Cell3.setRotationPoint(2F, 3F, 0F);
		Cell3.setTextureSize(64, 64);
		Cell3.mirror = true;
		setRotation(Cell3, 1.570796F, 0F, 0F);
		Cell4 = new ModelRenderer(this, 0, 40);
		Cell4.addBox(0F, -3.5F, -1F, 3, 2, 2);
		Cell4.setRotationPoint(2F, 3F, 0F);
		Cell4.setTextureSize(64, 64);
		Cell4.mirror = true;
		setRotation(Cell4, 2.356194F, 0F, 0F);
		Cell5 = new ModelRenderer(this, 0, 44);
		Cell5.addBox(0F, -3.5F, -1F, 3, 2, 2);
		Cell5.setRotationPoint(2F, 3F, 0F);
		Cell5.setTextureSize(64, 64);
		Cell5.mirror = true;
		setRotation(Cell5, 3.141593F, 0F, 0F);
		Cell8 = new ModelRenderer(this, 0, 48);
		Cell8.addBox(0F, -3.5F, -1F, 3, 2, 2);
		Cell8.setRotationPoint(2F, 3F, 0F);
		Cell8.setTextureSize(64, 64);
		Cell8.mirror = true;
		setRotation(Cell8, -0.7853982F, 0F, 0F);
		Cell7 = new ModelRenderer(this, 0, 52);
		Cell7.addBox(0F, -3.5F, -1F, 3, 2, 2);
		Cell7.setRotationPoint(2F, 3F, 0F);
		Cell7.setTextureSize(64, 64);
		Cell7.mirror = true;
		setRotation(Cell7, -1.570796F, 0F, 0F);
		Cell6 = new ModelRenderer(this, 0, 56);
		Cell6.addBox(0F, -3.5F, -1F, 3, 2, 2);
		Cell6.setRotationPoint(2F, 3F, 0F);
		Cell6.setTextureSize(64, 64);
		Cell6.mirror = true;
		setRotation(Cell6, -2.356194F, 0F, 0F);
		PlateBack = new ModelRenderer(this, 10, 28);
		PlateBack.addBox(0F, 0F, 0F, 2, 8, 8);
		PlateBack.setRotationPoint(5F, -1F, -4F);
		PlateBack.setTextureSize(64, 64);
		PlateBack.mirror = true;
		setRotation(PlateBack, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 10, 44);
		Body.addBox(0F, 0F, 0F, 8, 6, 4);
		Body.setRotationPoint(7F, 1F, -2F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Handle1 = new ModelRenderer(this, 10, 54);
		Handle1.addBox(0F, 0F, 0F, 2, 2, 2);
		Handle1.setRotationPoint(15F, 4F, -1F);
		Handle1.setTextureSize(64, 64);
		Handle1.mirror = true;
		setRotation(Handle1, 0F, 0F, 0F);
		Handle2 = new ModelRenderer(this, 18, 54);
		Handle2.addBox(0F, 0F, 0F, 1, 4, 1);
		Handle2.setRotationPoint(17F, 2F, -0.5F);
		Handle2.setTextureSize(64, 64);
		Handle2.mirror = true;
		setRotation(Handle2, 0F, 0F, 0F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		BarrelMain.render(f5);
		PlateFront.render(f5);
		GlStateManager.disableCull();
		Sight.render(f5);
		GlStateManager.enableCull();
		Grip.render(f5);

		PlateBack.render(f5);
		Body.render(f5);
		Handle1.render(f5);
		Handle2.render(f5);

		if (GeneralConfig.useShaders) {
			final FloatBuffer buf1 = BufferUtils.createFloatBuffer(16);
			GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, buf1);
			// HbmShaderManager.testBuf1 = buf1;
			final FloatBuffer buf2 = BufferUtils.createFloatBuffer(16);
			GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, buf2);
			// HbmShaderManager.testBuf2 = buf2;

			HbmShaderManager.gaussRenderers.add(() -> {
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadMatrix(buf2);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadMatrix(buf1);

				Minecraft.getMinecraft().renderEngine.bindTexture(ItemRenderOverkill.sparkLoc);
				GL11.glPushAttrib(GL11.GL_CURRENT_BIT | GL11.GL_COLOR_BUFFER_BIT);
				GlStateManager.disableBlend();
				GlStateManager.disableAlpha();
				GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
				GlStateManager.disableLighting();
				GlStateManager.depthMask(true);
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.disableLighting();

				Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
				HbmShaderManager.useShader(HbmShaderManager.gauss);
				HbmShaderManager.WORLD_TIME.assign(HbmShaderManager.gauss);
				BarrelSide.render(f5);
				Cell1.render(f5);
				Cell2.render(f5);
				Cell3.render(f5);
				Cell4.render(f5);
				Cell5.render(f5);
				Cell8.render(f5);
				Cell7.render(f5);
				Cell6.render(f5);
				HbmShaderManager.releaseShader();

				Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
				GlStateManager.depthMask(true);
				GlStateManager.disableBlend();
				GlStateManager.enableLighting();
				GlStateManager.enableAlpha();
				GL11.glPopAttrib();
			});
			//Stupid hack because minecraft doesn't have an event for rendering the hand.
			if(this.renderingInFirstPerson)
				HbmShaderManager.renderGauss();
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadMatrix(buf2);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadMatrix(buf1);

			Minecraft.getMinecraft().renderEngine.bindTexture(ItemRenderOverkill.sparkLoc);
			GL11.glPushAttrib(GL11.GL_CURRENT_BIT | GL11.GL_COLOR_BUFFER_BIT);
			GlStateManager.disableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			GlStateManager.disableLighting();
			GlStateManager.depthMask(true);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableLighting();

			Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
			HbmShaderManager.useShader(HbmShaderManager.gauss);
			HbmShaderManager.WORLD_TIME.assign(HbmShaderManager.gauss);
			BarrelSide.render(f5);
			Cell1.render(f5);
			Cell2.render(f5);
			Cell3.render(f5);
			Cell4.render(f5);
			Cell5.render(f5);
			Cell8.render(f5);
			Cell7.render(f5);
			Cell6.render(f5);
			HbmShaderManager.releaseShader();

			Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
			GlStateManager.depthMask(true);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableAlpha();
			GL11.glPopAttrib();
			
		} else {
			BarrelSide.render(f5);
			Cell1.render(f5);
			Cell2.render(f5);
			Cell3.render(f5);
			Cell4.render(f5);
			Cell5.render(f5);
			Cell8.render(f5);
			Cell7.render(f5);
			Cell6.render(f5);
		}
		this.renderingInFirstPerson = false;
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
