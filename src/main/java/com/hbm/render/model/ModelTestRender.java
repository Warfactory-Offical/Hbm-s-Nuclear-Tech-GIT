package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTestRender extends ModelBase {
	// fields
	ModelRenderer MainBlock;

	public ModelTestRender() {
		textureWidth = 64;
		textureHeight = 32;

		MainBlock = new ModelRenderer(this, 0, 0);
		MainBlock.addBox(0F, 0F, 0F, 12, 16, 12);
		MainBlock.setRotationPoint(-6F, 8F, -6F);
		MainBlock.setTextureSize(64, 32);
		MainBlock.mirror = true;
		setRotation(MainBlock, 0F, 0F, 0F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		MainBlock.render(f5);
	}

	public void renderModel(final float f) {
		MainBlock.render(f);
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
