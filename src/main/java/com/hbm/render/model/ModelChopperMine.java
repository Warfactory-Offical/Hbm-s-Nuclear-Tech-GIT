package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChopperMine extends ModelBase {

	ModelRenderer bullet;

	public ModelChopperMine() {
		textureWidth = 32;
		textureHeight = 16;

		bullet = new ModelRenderer(this, 0, 0);
		bullet.addBox(0F, 0F, 0F, 8, 8, 8);
		bullet.setRotationPoint(-4F, -4F, -4F);
		bullet.setTextureSize(32, 16);
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