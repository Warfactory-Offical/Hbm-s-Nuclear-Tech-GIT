package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRocket extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;

	public ModelRocket() {
		textureWidth = 32;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 7, 2, 2);
		Shape1.setRotationPoint(0F, -1F, -1F);
		Shape1.setTextureSize(32, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 4);
		Shape2.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape2.setRotationPoint(-3F, -0.5F, -0.5F);
		Shape2.setTextureSize(32, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 8, 4);
		Shape3.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape3.setRotationPoint(-3F, -0.5F, -0.5F);
		Shape3.setTextureSize(32, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0.1745329F, 0F);
		Shape4 = new ModelRenderer(this, 0, 6);
		Shape4.addBox(0F, 0F, -1F, 3, 1, 1);
		Shape4.setRotationPoint(-3F, -0.5F, 0.5F);
		Shape4.setTextureSize(32, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, -0.1745329F, 0F);
		Shape5 = new ModelRenderer(this, 8, 6);
		Shape5.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape5.setRotationPoint(-3F, -0.5F, -0.5F);
		Shape5.setTextureSize(32, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, -0.1745329F);
		Shape6 = new ModelRenderer(this, 0, 8);
		Shape6.addBox(0F, -1F, 0F, 3, 1, 1);
		Shape6.setRotationPoint(-3F, 0.5F, -0.5F);
		Shape6.setTextureSize(32, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0.1745329F);
		Shape7 = new ModelRenderer(this, 0, 10);
		Shape7.addBox(0F, 0F, 0F, 4, 4, 0);
		Shape7.setRotationPoint(4F, 0F, 0F);
		Shape7.setTextureSize(32, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, -0.7853982F);
		Shape8 = new ModelRenderer(this, 0, 14);
		Shape8.addBox(0F, 0F, 0F, 4, 0, 4);
		Shape8.setRotationPoint(4F, 0F, 0F);
		Shape8.setTextureSize(32, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0.7853982F, 0F);
		Shape9 = new ModelRenderer(this, 8, 8);
		Shape9.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape9.setRotationPoint(7F, -0.5F, -0.5F);
		Shape9.setTextureSize(32, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
	}

	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
	}
	
	public void renderAll(final float f5) {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
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
