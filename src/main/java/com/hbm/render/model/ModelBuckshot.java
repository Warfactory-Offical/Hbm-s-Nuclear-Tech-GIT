package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBuckshot extends ModelBase {

	ModelRenderer bullet;

	public ModelBuckshot() {
		textureWidth = 4;
		textureHeight = 4;

		bullet = new ModelRenderer(this, 0, 0);
		bullet.addBox(0F, 0F, 0F, 1, 1, 1);
		bullet.setRotationPoint(1F, -0.5F, -0.5F);
		bullet.setTextureSize(4, 4);
		bullet.mirror = true;
		setRotation(bullet, 0F, 0F, 0F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bullet.render(f5);
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

	public void renderAll(final float f5) {
		bullet.render(f5);
	}
}
