package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPanzerschreck extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape16;
	ModelRenderer Shape17;
	ModelRenderer Shape18;

	public ModelPanzerschreck() {
		textureWidth = 128;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 40, 2, 3);
		Shape1.setRotationPoint(-20F, 0F, -1.5F);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 5);
		Shape2.addBox(0F, 0F, 0F, 40, 3, 2);
		Shape2.setRotationPoint(-20F, -0.5F, -1F);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 10);
		Shape3.addBox(0F, 0F, 0F, 2, 3, 1);
		Shape3.setRotationPoint(-7.5F, 2.5F, -0.5F);
		Shape3.setTextureSize(128, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 6, 10);
		Shape4.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape4.setRotationPoint(-7.5F, 5.5F, -0.5F);
		Shape4.setTextureSize(128, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 14);
		Shape5.addBox(0F, 0F, 0F, 2, 3, 1);
		Shape5.setRotationPoint(-1.5F, 2.5F, -0.5F);
		Shape5.setTextureSize(128, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 6, 12);
		Shape6.addBox(0F, 0F, 0F, 1, 3, 1);
		Shape6.setRotationPoint(0.5F, 2.5F, -0.5F);
		Shape6.setTextureSize(128, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 10, 12);
		Shape7.addBox(-1F, 0F, 0F, 1, 2, 1);
		Shape7.setRotationPoint(-2F, 2.5F, -0.5F);
		Shape7.setTextureSize(128, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0.3490659F);
		Shape8 = new ModelRenderer(this, 0, 18);
		Shape8.addBox(0F, 0F, 0F, 1, 4, 4);
		Shape8.setRotationPoint(22F, -1F, -2F);
		Shape8.setTextureSize(128, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 20, 10);
		Shape9.addBox(-3F, 0F, 0F, 3, 1, 1);
		Shape9.setRotationPoint(22F, -1F, -0.5F);
		Shape9.setTextureSize(128, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, -0.2617994F);
		Shape10 = new ModelRenderer(this, 14, 12);
		Shape10.addBox(-3F, 0F, 0F, 3, 1, 1);
		Shape10.setRotationPoint(22F, 0.5F, -2F);
		Shape10.setTextureSize(128, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0.2617994F, 0F);
		Shape11 = new ModelRenderer(this, 14, 14);
		Shape11.addBox(-3F, 0F, -1F, 3, 1, 1);
		Shape11.setRotationPoint(22F, 0.5F, 2F);
		Shape11.setTextureSize(128, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, -0.2617994F, 0F);
		Shape12 = new ModelRenderer(this, 28, 10);
		Shape12.addBox(-3F, -1F, 0F, 3, 1, 1);
		Shape12.setRotationPoint(22F, 3F, -0.5F);
		Shape12.setTextureSize(128, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0.2617994F);
		Shape13 = new ModelRenderer(this, 22, 12);
		Shape13.addBox(0F, 0F, 0F, 1, 1, 3);
		Shape13.setRotationPoint(-20F, -1.5F, -1.5F);
		Shape13.setTextureSize(128, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 30, 12);
		Shape14.addBox(0F, 0F, 0F, 1, 1, 3);
		Shape14.setRotationPoint(-20F, -2.5F, -1.5F);
		Shape14.setTextureSize(128, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 36, 10);
		Shape15.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape15.setRotationPoint(15F, -1F, -2F);
		Shape15.setTextureSize(128, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 108, 10);
		Shape16.addBox(0F, 0F, 0F, 0, 12, 10);
		Shape16.setRotationPoint(-10F, -5F, -8.5F);
		Shape16.setTextureSize(128, 32);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 38, 12);
		Shape17.addBox(0F, 0F, 0F, 1, 3, 1);
		Shape17.setRotationPoint(3.5F, 2.5F, -0.5F);
		Shape17.setTextureSize(128, 32);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, -0.4363323F);
		Shape18 = new ModelRenderer(this, 10, 15);
		Shape18.addBox(0F, 0F, 0F, 1, 3, 1);
		Shape18.setRotationPoint(5F, 2.5F, -0.5F);
		Shape18.setTextureSize(128, 32);
		Shape18.mirror = true;
		setRotation(Shape18, 0F, 0F, 0F);
	}

	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GL11.glDisable(GL11.GL_CULL_FACE);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		Shape16.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		Shape17.render(f5);
		Shape18.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
