package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelB92 extends ModelBase {
	// fields
	ModelRenderer Muzzle1;
	ModelRenderer Barrel1;
	ModelRenderer Barrel2;
	ModelRenderer Grip;
	ModelRenderer Front1;
	ModelRenderer Front2;
	ModelRenderer Body;
	ModelRenderer Top;
	ModelRenderer GripBottom;
	ModelRenderer Handle;
	ModelRenderer HandleBack;
	ModelRenderer Frame1;
	ModelRenderer Frame2;
	ModelRenderer Frame3;
	ModelRenderer Trigger;
	ModelRenderer BackPlate1;
	ModelRenderer Back;
	ModelRenderer BackPlate2;
	ModelRenderer Pump1;
	ModelRenderer Pump2;
	ModelRenderer BodyPlate;

	public ModelB92() {
		textureWidth = 64;
		textureHeight = 64;

		Muzzle1 = new ModelRenderer(this, 22, 36);
		Muzzle1.addBox(0F, 0F, 0F, 2, 3, 2);
		Muzzle1.setRotationPoint(-24F, 0.5F, -1F);
		Muzzle1.setTextureSize(64, 64);
		Muzzle1.mirror = true;
		setRotation(Muzzle1, 0F, 0F, 0F);
		Barrel1 = new ModelRenderer(this, 0, 0);
		Barrel1.addBox(0F, 0F, 0F, 24, 2, 3);
		Barrel1.setRotationPoint(-24F, 1F, -1.5F);
		Barrel1.setTextureSize(64, 64);
		Barrel1.mirror = true;
		setRotation(Barrel1, 0F, 0F, 0F);
		Barrel2 = new ModelRenderer(this, 0, 5);
		Barrel2.addBox(0F, 0F, 0F, 22, 1, 2);
		Barrel2.setRotationPoint(-22F, 0.5F, -1F);
		Barrel2.setTextureSize(64, 64);
		Barrel2.mirror = true;
		setRotation(Barrel2, 0F, 0F, 0F);
		Grip = new ModelRenderer(this, 0, 8);
		Grip.addBox(0F, 0F, 0F, 20, 3, 4);
		Grip.setRotationPoint(-20F, 3F, -2F);
		Grip.setTextureSize(64, 64);
		Grip.mirror = true;
		setRotation(Grip, 0F, 0F, 0F);
		Front1 = new ModelRenderer(this, 10, 36);
		Front1.addBox(0F, 0F, 0F, 2, 4, 4);
		Front1.setRotationPoint(-22F, 0.5F, -2F);
		Front1.setTextureSize(64, 64);
		Front1.mirror = true;
		setRotation(Front1, 0F, 0F, 0F);
		Front2 = new ModelRenderer(this, 0, 36);
		Front2.addBox(0F, 0F, 0F, 2, 6, 3);
		Front2.setRotationPoint(-22F, 0F, -1.5F);
		Front2.setTextureSize(64, 64);
		Front2.mirror = true;
		setRotation(Front2, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 15);
		Body.addBox(0F, 0F, 0F, 15, 7, 4);
		Body.setRotationPoint(0F, 0.5F, -2F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 28, 60);
		Top.addBox(0F, 0F, 0F, 15, 1, 3);
		Top.setRotationPoint(0F, 0F, -1.5F);
		Top.setTextureSize(64, 64);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		GripBottom = new ModelRenderer(this, 24, 43);
		GripBottom.addBox(0F, 0F, 0F, 18, 1, 2);
		GripBottom.setRotationPoint(-18F, 5.5F, -1F);
		GripBottom.setTextureSize(64, 64);
		GripBottom.mirror = true;
		setRotation(GripBottom, 0F, 0F, 0F);
		Handle = new ModelRenderer(this, 0, 45);
		Handle.addBox(0F, 0F, 0F, 6, 15, 4);
		Handle.setRotationPoint(6F, 7F, -2F);
		Handle.setTextureSize(64, 64);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, -0.2268928F);
		HandleBack = new ModelRenderer(this, 20, 46);
		HandleBack.addBox(5.5F, 0F, 0F, 1, 15, 3);
		HandleBack.setRotationPoint(6F, 7F, -1.5F);
		HandleBack.setTextureSize(64, 64);
		HandleBack.mirror = true;
		setRotation(HandleBack, 0F, 0F, -0.2268928F);
		Frame1 = new ModelRenderer(this, 28, 57);
		Frame1.addBox(0F, 0F, 0F, 7, 1, 2);
		Frame1.setRotationPoint(0.5F, 11F, -1F);
		Frame1.setTextureSize(64, 64);
		Frame1.mirror = true;
		setRotation(Frame1, 0F, 0F, 0F);
		Frame2 = new ModelRenderer(this, 28, 51);
		Frame2.addBox(0F, 0F, 0F, 2, 4, 2);
		Frame2.setRotationPoint(-2F, 6.5F, -1F);
		Frame2.setTextureSize(64, 64);
		Frame2.mirror = true;
		setRotation(Frame2, 0F, 0F, 0F);
		Frame3 = new ModelRenderer(this, 46, 57);
		Frame3.addBox(0F, -1F, 0F, 3, 1, 2);
		Frame3.setRotationPoint(-2F, 10.5F, -1F);
		Frame3.setTextureSize(64, 64);
		Frame3.mirror = true;
		setRotation(Frame3, 0F, 0F, 0.5235988F);
		Trigger = new ModelRenderer(this, 36, 53);
		Trigger.addBox(0F, 0F, 0F, 2, 3, 1);
		Trigger.setRotationPoint(4F, 7F, -0.5F);
		Trigger.setTextureSize(64, 64);
		Trigger.mirror = true;
		setRotation(Trigger, 0F, 0F, 0.1919862F);
		BackPlate1 = new ModelRenderer(this, 56, 53);
		BackPlate1.addBox(-1F, 0F, 0F, 1, 4, 3);
		BackPlate1.setRotationPoint(15F, 0F, -1.5F);
		BackPlate1.setTextureSize(64, 64);
		BackPlate1.mirror = true;
		setRotation(BackPlate1, 0F, 0F, -0.5235988F);
		Back = new ModelRenderer(this, 42, 49);
		Back.addBox(0F, 0F, 0F, 2, 4, 4);
		Back.setRotationPoint(15F, 3.5F, -2F);
		Back.setTextureSize(64, 64);
		Back.mirror = true;
		setRotation(Back, 0F, 0F, 0F);
		BackPlate2 = new ModelRenderer(this, 48, 5);
		BackPlate2.addBox(-2F, 0F, 0F, 2, 4, 4);
		BackPlate2.setRotationPoint(15F, 0.5F, -2F);
		BackPlate2.setTextureSize(64, 64);
		BackPlate2.mirror = true;
		setRotation(BackPlate2, 0F, 0F, -0.4886922F);
		Pump1 = new ModelRenderer(this, 46, 29);
		Pump1.addBox(0F, 0F, 0F, 7, 2, 2);
		Pump1.setRotationPoint(10F, 1F, -1F);
		Pump1.setTextureSize(64, 64);
		Pump1.mirror = true;
		setRotation(Pump1, 0F, 0F, 0F);
		Pump2 = new ModelRenderer(this, 44, 33);
		Pump2.addBox(0F, 0F, 0F, 3, 3, 7);
		Pump2.setRotationPoint(17F, 0.5F, -3.5F);
		Pump2.setTextureSize(64, 64);
		Pump2.mirror = true;
		setRotation(Pump2, 0F, 0F, 0F);
		BodyPlate = new ModelRenderer(this, 0, 26);
		BodyPlate.addBox(0F, 0F, 0F, 14, 5, 5);
		BodyPlate.setRotationPoint(1.5F, 2F, -2.5F);
		BodyPlate.setTextureSize(64, 64);
		BodyPlate.mirror = true;
		setRotation(BodyPlate, 0F, 0F, 0F);
	}

	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Muzzle1.render(f5);
		Barrel1.render(f5);
		Barrel2.render(f5);
		Grip.render(f5);
		Front1.render(f5);
		Front2.render(f5);
		Body.render(f5);
		Top.render(f5);
		GripBottom.render(f5);
		Handle.render(f5);
		HandleBack.render(f5);
		Frame1.render(f5);
		Frame2.render(f5);
		Frame3.render(f5);
		Trigger.render(f5);
		BackPlate1.render(f5);
		Back.render(f5);
		BackPlate2.render(f5);
		Pump1.render(f5);
		Pump2.render(f5);
		BodyPlate.render(f5);
	}

	public void renderAnim(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final float tran) {

		Pump1.offsetX += tran;
		Pump2.offsetX += tran;
		
		render(entity, f, f1, f2, f3, f4, f5);
		
		Pump1.offsetX -= tran;
		Pump2.offsetX -= tran;
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