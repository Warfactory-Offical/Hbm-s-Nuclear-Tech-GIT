package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEuthanasia extends ModelBase {

	ModelRenderer Barrel;
	ModelRenderer Tank;
	ModelRenderer ConnectorBF;
	ModelRenderer ConnectorBB;
	ModelRenderer ConnectorBeam;
	ModelRenderer ConnectorTF;
	ModelRenderer ConnectorTB;
	ModelRenderer Body;
	ModelRenderer Plate;
	ModelRenderer Beam;
	ModelRenderer Stock;
	ModelRenderer StockBack;
	ModelRenderer StockPlate;
	ModelRenderer Handle;
	ModelRenderer Trigger;

	public ModelEuthanasia() {
		textureWidth = 64;
		textureHeight = 64;

		Barrel = new ModelRenderer(this, 0, 0);
		Barrel.addBox(0F, 0F, 0F, 18, 3, 3);
		Barrel.setRotationPoint(-11F, 0.5F, -1.5F);
		Barrel.setTextureSize(64, 64);
		Barrel.mirror = true;
		setRotation(Barrel, 0F, 0F, 0F);
		Tank = new ModelRenderer(this, 0, 6);
		Tank.addBox(0F, 0F, 0F, 9, 5, 5);
		Tank.setRotationPoint(-3F, -0.5F, -2.5F);
		Tank.setTextureSize(64, 64);
		Tank.mirror = true;
		setRotation(Tank, 0F, 0F, 0F);
		ConnectorBF = new ModelRenderer(this, 52, 0);
		ConnectorBF.addBox(0F, 0F, 0F, 2, 8, 4);
		ConnectorBF.setRotationPoint(-6F, 0F, -2F);
		ConnectorBF.setTextureSize(64, 64);
		ConnectorBF.mirror = true;
		setRotation(ConnectorBF, 0F, 0F, 0F);
		ConnectorBB = new ModelRenderer(this, 28, 6);
		ConnectorBB.addBox(0F, 0F, 0F, 2, 8, 4);
		ConnectorBB.setRotationPoint(7F, 0F, -2F);
		ConnectorBB.setTextureSize(64, 64);
		ConnectorBB.mirror = true;
		setRotation(ConnectorBB, 0F, 0F, 0F);
		ConnectorBeam = new ModelRenderer(this, 0, 18);
		ConnectorBeam.addBox(0F, 0F, 0F, 11, 2, 4);
		ConnectorBeam.setRotationPoint(-4F, 6F, -2F);
		ConnectorBeam.setTextureSize(64, 64);
		ConnectorBeam.mirror = true;
		setRotation(ConnectorBeam, 0F, 0F, 0F);
		ConnectorTF = new ModelRenderer(this, 40, 12);
		ConnectorTF.addBox(0F, 0F, 0F, 2, 8, 4);
		ConnectorTF.setRotationPoint(-9F, -4F, -2F);
		ConnectorTF.setTextureSize(64, 64);
		ConnectorTF.mirror = true;
		setRotation(ConnectorTF, 0F, 0F, 0F);
		ConnectorTB = new ModelRenderer(this, 52, 12);
		ConnectorTB.addBox(0F, 0F, 0F, 2, 8, 4);
		ConnectorTB.setRotationPoint(9F, -4F, -2F);
		ConnectorTB.setTextureSize(64, 64);
		ConnectorTB.mirror = true;
		setRotation(ConnectorTB, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 24);
		Body.addBox(0F, 0F, 0F, 4, 5, 4);
		Body.setRotationPoint(11F, -1F, -2F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Plate = new ModelRenderer(this, 16, 24);
		Plate.addBox(0F, 0F, 0F, 5, 3, 4);
		Plate.setRotationPoint(11F, -4F, -2F);
		Plate.setTextureSize(64, 64);
		Plate.mirror = true;
		setRotation(Plate, 0F, 0F, 0.6457718F);
		Beam = new ModelRenderer(this, 0, 33);
		Beam.addBox(0F, 0F, 0F, 16, 1, 1);
		Beam.setRotationPoint(-7F, -3.5F, -0.5F);
		Beam.setTextureSize(64, 64);
		Beam.mirror = true;
		setRotation(Beam, 0F, 0F, 0F);
		Stock = new ModelRenderer(this, 0, 35);
		Stock.addBox(0F, 0F, 0F, 10, 2, 2);
		Stock.setRotationPoint(15F, 1F, -1F);
		Stock.setTextureSize(64, 64);
		Stock.mirror = true;
		setRotation(Stock, 0F, 0F, 0F);
		StockBack = new ModelRenderer(this, 0, 39);
		StockBack.addBox(0F, 0F, 0F, 1, 4, 2);
		StockBack.setRotationPoint(24F, 3F, -1F);
		StockBack.setTextureSize(64, 64);
		StockBack.mirror = true;
		setRotation(StockBack, 0F, 0F, 0F);
		StockPlate = new ModelRenderer(this, 6, 39);
		StockPlate.addBox(0F, -5F, 0F, 1, 5, 2);
		StockPlate.setRotationPoint(24F, 7F, -1F);
		StockPlate.setTextureSize(64, 64);
		StockPlate.mirror = true;
		setRotation(StockPlate, 0F, 0F, -0.2094395F);
		Handle = new ModelRenderer(this, 12, 39);
		Handle.addBox(0F, 0F, 0F, 2, 6, 2);
		Handle.setRotationPoint(13F, 4F, -1F);
		Handle.setTextureSize(64, 64);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, -0.2094395F);
		Trigger = new ModelRenderer(this, 20, 39);
		Trigger.addBox(0F, 0F, 0F, 1, 4, 2);
		Trigger.setRotationPoint(12F, 4F, -1F);
		Trigger.setTextureSize(64, 64);
		Trigger.mirror = true;
		setRotation(Trigger, 0F, 0F, 0F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Barrel.render(f5);
		Tank.render(f5);
		ConnectorBF.render(f5);
		ConnectorBB.render(f5);
		ConnectorBeam.render(f5);
		ConnectorTF.render(f5);
		ConnectorTB.render(f5);
		Body.render(f5);
		Plate.render(f5);
		Beam.render(f5);
		Stock.render(f5);
		StockBack.render(f5);
		StockPlate.render(f5);
		Handle.render(f5);
		Trigger.render(f5);
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
