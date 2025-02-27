package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCalBarrel extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;

	public ModelCalBarrel() {
		textureWidth = 128;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 30, 6, 3);
		Shape1.setRotationPoint(-30F, 0F, -1.5F);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 19);
		Shape2.addBox(0F, 0F, 0F, 30, 3, 6);
		Shape2.setRotationPoint(-30F, 1.5F, -3F);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 9);
		Shape3.addBox(0F, 0F, 0F, 30, 5, 5);
		Shape3.setRotationPoint(-30F, 0.5F, -2.5F);
		Shape3.setTextureSize(128, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 28);
		Shape4.addBox(0F, 0F, 0F, 1, 2, 2);
		Shape4.setRotationPoint(-30.5F, 0.5F, -1F);
		Shape4.setTextureSize(128, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 6, 28);
		Shape5.addBox(0F, 0F, 0F, 3, 2, 2);
		Shape5.setRotationPoint(-33F, 3.5F, -1F);
		Shape5.setTextureSize(128, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 66, 0);
		Shape6.addBox(0F, 0F, 0F, 4, 2, 4);
		Shape6.setRotationPoint(-20F, -1F, -2F);
		Shape6.setTextureSize(128, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 82, 0);
		Shape7.addBox(0F, -1F, -4F, 4, 1, 4);
		Shape7.setRotationPoint(-20F, -1F, 2F);
		Shape7.setTextureSize(128, 32);
		Shape7.mirror = true;
		setRotation(Shape7, -0.3490659F, 0F, 0F);
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
	}

	public void renderAll(final float f5) {
		GL11.glPushMatrix();
		GL11.glTranslated(0, 1D / 16D * 1, 0);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		GL11.glPopMatrix();
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
