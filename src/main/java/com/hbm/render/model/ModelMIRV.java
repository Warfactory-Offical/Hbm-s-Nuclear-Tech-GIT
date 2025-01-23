package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMIRV extends ModelBase {

	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;

	public ModelMIRV() {
		textureWidth = 64;
		textureHeight = 32;

		Shape9 = new ModelRenderer(this, 0, 0);
		Shape9.addBox(0F, 0F, 0F, 10, 4, 2);
		Shape9.setRotationPoint(-3F, -2F, -1F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 0, 6);
		Shape10.addBox(0F, 0F, 0F, 10, 2, 4);
		Shape10.setRotationPoint(-3F, -1F, -2F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 0, 12);
		Shape11.addBox(0F, 0F, 0F, 10, 3, 3);
		Shape11.setRotationPoint(-3F, -1.5F, -1.5F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 0, 18);
		Shape12.addBox(0F, 0F, 0F, 4, 1, 1);
		Shape12.setRotationPoint(0F, -3F, -1F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
	}

	public void renderAll(final float f5) {
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		Shape12.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
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
