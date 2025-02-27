package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGun extends ModelBase {

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

	public ModelGun() {
		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 6, 12, 4);
		Shape1.setRotationPoint(0F, -4F, -1F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 52, 0);
		Shape2.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape2.setRotationPoint(4F, -7F, -0.5F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 28, 58);
		Shape3.addBox(0F, 0F, 0F, 15, 3, 3);
		Shape3.setRotationPoint(-15F, -7F, -0.5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 61);
		Shape4.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape4.setRotationPoint(2F, -3F, 0.5F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0.715585F);
		Shape5 = new ModelRenderer(this, 0, 57);
		Shape5.addBox(0F, 0F, 0F, 2, 2, 1);
		Shape5.setRotationPoint(-13.5F, -8F, 0.5F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0.6108652F);
		Shape6 = new ModelRenderer(this, 52, 7);
		Shape6.addBox(0F, 0F, 0F, 4, 3, 2);
		Shape6.setRotationPoint(0F, -6.5F, 0F);
		Shape6.setTextureSize(64, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 46, 49);
		Shape7.addBox(0F, 0F, 0F, 6, 5, 3);
		Shape7.setRotationPoint(-15F, -3F, -0.5F);
		Shape7.setTextureSize(64, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 22, 0);
		Shape8.addBox(0F, 0F, 0F, 12, 1, 2);
		Shape8.setRotationPoint(-15F, -4F, 0F);
		Shape8.setTextureSize(64, 64);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 52, 13);
		Shape9.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape9.setRotationPoint(-3F, -4F, -0.5F);
		Shape9.setTextureSize(64, 64);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 11, 60);
		Shape10.addBox(0F, 0F, 0F, 6, 2, 2);
		Shape10.setRotationPoint(-9F, -3F, 0F);
		Shape10.setTextureSize(64, 64);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 35, 50);
		Shape11.addBox(0F, 0F, 0F, 2, 4, 3);
		Shape11.setRotationPoint(-9F, -1F, -0.5F);
		Shape11.setTextureSize(64, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 12, 57);
		Shape12.addBox(0F, 0F, 0F, 7, 1, 1);
		Shape12.setRotationPoint(-7F, 2F, 0.5F);
		Shape12.setTextureSize(64, 64);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 0, 51);
		Shape13.addBox(0F, 0F, 0F, 4, 1, 4);
		Shape13.setRotationPoint(0F, -5F, -1F);
		Shape13.setTextureSize(64, 64);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 43);
		Shape14.addBox(0F, 0F, 0F, 3, 5, 2);
		Shape14.setRotationPoint(7F, -7F, 0F);
		Shape14.setTextureSize(64, 64);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0.7853982F);
		// Shape15.mirror = true;
		Shape15 = new ModelRenderer(this, 0, 38);
		Shape15.addBox(0F, 0F, 0F, 3, 1, 3);
		Shape15.setRotationPoint(-9F, 3F, -0.5F);
		Shape15.setTextureSize(64, 64);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, -2.792527F);
		Shape15.mirror = false;
		Shape16 = new ModelRenderer(this, 0, 17);
		Shape16.addBox(0F, 0F, 0F, 2, 3, 1);
		Shape16.setRotationPoint(-1F, -2F, 0.5F);
		Shape16.setTextureSize(64, 64);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0.2617994F);
	}

	@Override
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
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
	}

	public void renderModel(final float f) {
		Shape1.render(f);
		Shape2.render(f);
		Shape3.render(f);
		Shape4.render(f);
		Shape5.render(f);
		Shape6.render(f);
		Shape7.render(f);
		Shape8.render(f);
		Shape9.render(f);
		Shape10.render(f);
		Shape11.render(f);
		Shape12.render(f);
		Shape13.render(f);
		Shape14.render(f);
		Shape15.render(f);
		Shape16.render(f);
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
