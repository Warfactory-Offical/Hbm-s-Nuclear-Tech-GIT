package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJack extends ModelBase {

	ModelRenderer BarrelBR;
	ModelRenderer BarrelBL;
	ModelRenderer BarrelTR;
	ModelRenderer BarrelTL;
	ModelRenderer Body;
	ModelRenderer Back;
	ModelRenderer Plate;
	ModelRenderer StockMain;
	ModelRenderer StockBottom;
	ModelRenderer StockPlate;

	public ModelJack() {
		textureWidth = 64;
		textureHeight = 64;

		BarrelBR = new ModelRenderer(this, 0, 6);
		BarrelBR.addBox(0F, 0F, 0F, 21, 3, 3);
		BarrelBR.setRotationPoint(-18F, 0F, 0.2F);
		BarrelBR.setTextureSize(64, 64);
		BarrelBR.mirror = true;
		setRotation(BarrelBR, 0F, 0F, 0F);
		BarrelBL = new ModelRenderer(this, 0, 0);
		BarrelBL.addBox(0F, 0F, 0F, 21, 3, 3);
		BarrelBL.setRotationPoint(-18F, 0F, -3.2F);
		BarrelBL.setTextureSize(64, 64);
		BarrelBL.mirror = true;
		setRotation(BarrelBL, 0F, 0F, 0F);
		BarrelTR = new ModelRenderer(this, 0, 12);
		BarrelTR.addBox(0F, 0F, 0F, 21, 3, 3);
		BarrelTR.setRotationPoint(-18F, -3.2F, 0.2F);
		BarrelTR.setTextureSize(64, 64);
		BarrelTR.mirror = true;
		setRotation(BarrelTR, 0F, 0F, 0F);
		BarrelTL = new ModelRenderer(this, 0, 18);
		BarrelTL.addBox(0F, 0F, 0F, 21, 3, 3);
		BarrelTL.setRotationPoint(-18F, -3.2F, -3.2F);
		BarrelTL.setTextureSize(64, 64);
		BarrelTL.mirror = true;
		setRotation(BarrelTL, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 24);
		Body.addBox(0F, 0F, 0F, 15, 7, 4);
		Body.setRotationPoint(-9F, -2F, -2F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Back = new ModelRenderer(this, 38, 24);
		Back.addBox(0F, 0F, 0F, 3, 5, 3);
		Back.setRotationPoint(6F, 0F, -1.5F);
		Back.setTextureSize(64, 64);
		Back.mirror = true;
		setRotation(Back, 0F, 0F, 0F);
		Plate = new ModelRenderer(this, 0, 35);
		Plate.addBox(0F, 0F, 0F, 4, 2, 3);
		Plate.setRotationPoint(6F, -2F, -1.5F);
		Plate.setTextureSize(64, 64);
		Plate.mirror = true;
		setRotation(Plate, 0F, 0F, 0.4363323F);
		StockMain = new ModelRenderer(this, 0, 40);
		StockMain.addBox(0F, 0F, 0F, 13, 3, 3);
		StockMain.setRotationPoint(9F, 2F, -1.5F);
		StockMain.setTextureSize(64, 64);
		StockMain.mirror = true;
		setRotation(StockMain, 0F, 0F, 0F);
		StockBottom = new ModelRenderer(this, 0, 46);
		StockBottom.addBox(0F, 0F, 0F, 6, 3, 3);
		StockBottom.setRotationPoint(16F, 5F, -1.5F);
		StockBottom.setTextureSize(64, 64);
		StockBottom.mirror = true;
		setRotation(StockBottom, 0F, 0F, 0F);
		StockPlate = new ModelRenderer(this, 18, 46);
		StockPlate.addBox(-8F, -3F, 0F, 8, 3, 3);
		StockPlate.setRotationPoint(16F, 8F, -1.5F);
		StockPlate.setTextureSize(64, 64);
		StockPlate.mirror = true;
		setRotation(StockPlate, 0F, 0F, 0.4014257F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		BarrelBR.render(f5);
		BarrelBL.render(f5);
		BarrelTR.render(f5);
		BarrelTL.render(f5);
		Body.render(f5);
		Back.render(f5);
		Plate.render(f5);
		StockMain.render(f5);
		StockBottom.render(f5);
		StockPlate.render(f5);
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
