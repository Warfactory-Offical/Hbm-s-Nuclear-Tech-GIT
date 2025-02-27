package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSRocket extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;

	public ModelSRocket() {
		textureWidth = 64;
		textureHeight = 16;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 24, 2, 2);
		Shape1.setRotationPoint(-12F, -1F, -1F);
		Shape1.setTextureSize(64, 16);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 4);
		Shape2.addBox(0F, 0F, 0F, 1, 6, 0);
		Shape2.setRotationPoint(7F, -3F, 0F);
		Shape2.setTextureSize(64, 16);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 2, 4);
		Shape3.addBox(0F, 0F, 0F, 1, 0, 6);
		Shape3.setRotationPoint(7F, 0F, -3F);
		Shape3.setTextureSize(64, 16);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 16, 4);
		Shape4.addBox(0F, 0F, 0F, 1, 4, 0);
		Shape4.setRotationPoint(-10F, -2F, 0F);
		Shape4.setTextureSize(64, 16);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 10);
		Shape5.addBox(0F, 0F, 0F, 1, 0, 4);
		Shape5.setRotationPoint(-10F, 0F, -2F);
		Shape5.setTextureSize(64, 16);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 18, 4);
		Shape6.addBox(0F, 0F, 0F, 2, 3, 3);
		Shape6.setRotationPoint(9F, -1.5F, -1.5F);
		Shape6.setTextureSize(64, 16);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	public void renderAll(final float f5) {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
	}
}
