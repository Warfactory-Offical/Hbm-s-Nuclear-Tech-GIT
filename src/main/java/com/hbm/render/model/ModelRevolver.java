package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRevolver extends ModelBase {

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

	public ModelRevolver() {
		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 3, 8, 2);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, -0.3490659F);
		Shape2 = new ModelRenderer(this, 42, 0);
		Shape2.addBox(0F, 0F, 0F, 9, 6, 2);
		Shape2.setRotationPoint(-8F, -5F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 14);
		Shape3.addBox(0F, 0F, 0F, 4, 2, 1);
		Shape3.setRotationPoint(-0.03333334F, -3F, 0.5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0.715585F);
		Shape4 = new ModelRenderer(this, 22, 0);
		Shape4.addBox(0F, 0F, 0F, 6, 4, 4);
		Shape4.setRotationPoint(-7F, -4F, -1F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 34, 8);
		Shape6.addBox(0F, 0F, 0F, 13, 2, 2);
		Shape6.setRotationPoint(-21F, -4F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 17);
		Shape7.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape7.setRotationPoint(2F, -3F, 0.5F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0.715585F);
		Shape8 = new ModelRenderer(this, 4, 17);
		Shape8.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape8.setRotationPoint(2F, -4F, 0.5F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0.715585F);
		Shape9 = new ModelRenderer(this, 0, 20);
		Shape9.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape9.setRotationPoint(-14F, -2F, 0.5F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 26, 8);
		Shape10.addBox(0F, 0F, 0F, 2, 2, 2);
		Shape10.setRotationPoint(-19F, -5F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0.6108652F);
		Shape11 = new ModelRenderer(this, 0, 10);
		Shape11.addBox(0F, 0F, 0F, 4, 3, 1);
		Shape11.setRotationPoint(-2F, 1F, 0.5F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 10, 0);
		Shape12.addBox(0F, 0F, 0F, 1, 3, 1);
		Shape12.setRotationPoint(0F, 0F, 0.5F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0.5235988F);
	}

	@Override
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
		GL11.glEnable(GL11.GL_CULL_FACE);
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
