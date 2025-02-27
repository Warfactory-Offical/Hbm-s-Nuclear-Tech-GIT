package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLeverAction extends ModelBase {

	ModelRenderer Barrel1;
	ModelRenderer Barrel2;
	ModelRenderer Barrel3;
	ModelRenderer Barrel4;
	ModelRenderer Grip;
	ModelRenderer Front1;
	ModelRenderer Front2;
	ModelRenderer BodyFront;
	ModelRenderer BodyTop;
	ModelRenderer BodyMain;
	ModelRenderer BodyPlate;
	ModelRenderer HandleMain;
	ModelRenderer HandleBottom;
	ModelRenderer HandleBack;
	ModelRenderer LeverFront;
	ModelRenderer LeverBottom;
	ModelRenderer LeverMid;
	ModelRenderer LeverFrontPlate;
	ModelRenderer LeverBackBottom;
	ModelRenderer Trigger;
	ModelRenderer LeverBackTop;
	ModelRenderer LeverBack;

	public ModelLeverAction() {
		textureWidth = 128;
		textureHeight = 64;

		Barrel1 = new ModelRenderer(this, 0, 0);
		Barrel1.addBox(0F, 0F, 0F, 60, 3, 2);
		Barrel1.setRotationPoint(-60F, 1.5F, -1F);
		Barrel1.setTextureSize(64, 32);
		Barrel1.mirror = true;
		setRotation(Barrel1, 0F, 0F, 0F);
		Barrel2 = new ModelRenderer(this, 0, 5);
		Barrel2.addBox(0F, 0F, 0F, 60, 2, 3);
		Barrel2.setRotationPoint(-60F, 2F, -1.5F);
		Barrel2.setTextureSize(64, 32);
		Barrel2.mirror = true;
		setRotation(Barrel2, 0F, 0F, 0F);
		Barrel3 = new ModelRenderer(this, 0, 10);
		Barrel3.addBox(0F, 0F, 0F, 10, 2, 3);
		Barrel3.setRotationPoint(-46F, 6F, -1.5F);
		Barrel3.setTextureSize(64, 32);
		Barrel3.mirror = true;
		setRotation(Barrel3, 0F, 0F, 0F);
		Barrel4 = new ModelRenderer(this, 26, 10);
		Barrel4.addBox(0F, 0F, 0F, 10, 3, 2);
		Barrel4.setRotationPoint(-46F, 5.5F, -1F);
		Barrel4.setTextureSize(64, 32);
		Barrel4.mirror = true;
		setRotation(Barrel4, 0F, 0F, 0F);
		Grip = new ModelRenderer(this, 0, 15);
		Grip.addBox(0F, 0F, 0F, 36, 6, 4);
		Grip.setRotationPoint(-36F, 3F, -2F);
		Grip.setTextureSize(64, 32);
		Grip.mirror = true;
		setRotation(Grip, 0F, 0F, 0F);
		Front1 = new ModelRenderer(this, 50, 10);
		Front1.addBox(0F, 0F, 0F, 3, 2, 1);
		Front1.setRotationPoint(-50F, 4.5F, -0.5F);
		Front1.setTextureSize(64, 32);
		Front1.mirror = true;
		setRotation(Front1, 0F, 0F, 0F);
		Front2 = new ModelRenderer(this, 58, 10);
		Front2.addBox(0F, 0F, 0F, 3, 1, 2);
		Front2.setRotationPoint(-50F, 6.5F, -1F);
		Front2.setTextureSize(64, 32);
		Front2.mirror = true;
		setRotation(Front2, 0F, 0F, 0F);
		BodyFront = new ModelRenderer(this, 0, 25);
		BodyFront.addBox(0F, 0F, 0F, 3, 8, 4);
		BodyFront.setRotationPoint(0F, 1.5F, -2F);
		BodyFront.setTextureSize(64, 32);
		BodyFront.mirror = true;
		setRotation(BodyFront, 0F, 0F, 0F);
		BodyTop = new ModelRenderer(this, 14, 25);
		BodyTop.addBox(0F, 0F, 0F, 3, 1, 3);
		BodyTop.setRotationPoint(0F, 1F, -1.5F);
		BodyTop.setTextureSize(64, 32);
		BodyTop.mirror = true;
		setRotation(BodyTop, 0F, 0F, 0F);
		BodyMain = new ModelRenderer(this, 0, 37);
		BodyMain.addBox(0F, 0F, 0F, 12, 7, 4);
		BodyMain.setRotationPoint(3F, 2.5F, -2F);
		BodyMain.setTextureSize(64, 32);
		BodyMain.mirror = true;
		setRotation(BodyMain, 0F, 0F, 0F);
		BodyPlate = new ModelRenderer(this, 26, 25);
		BodyPlate.addBox(0F, 0F, 0F, 10, 2, 3);
		BodyPlate.setRotationPoint(3F, 1F, -1.5F);
		BodyPlate.setTextureSize(64, 32);
		BodyPlate.mirror = true;
		setRotation(BodyPlate, 0F, 0F, 0.1570796F);
		HandleMain = new ModelRenderer(this, 0, 48);
		HandleMain.addBox(0F, 0F, 0F, 18, 5, 4);
		HandleMain.setRotationPoint(15F, 4F, -2F);
		HandleMain.setTextureSize(128, 64);
		HandleMain.mirror = true;
		setRotation(HandleMain, 0F, 0F, 0.4363323F);
		HandleBottom = new ModelRenderer(this, 0, 57);
		HandleBottom.addBox(4F, 4.5F, 0F, 13, 1, 4);
		HandleBottom.setRotationPoint(15F, 4F, -2F);
		HandleBottom.setTextureSize(128, 64);
		HandleBottom.mirror = true;
		setRotation(HandleBottom, 0F, 0F, 0.4363323F);
		HandleBack = new ModelRenderer(this, 34, 57);
		HandleBack.addBox(17.5F, 0.5F, 0F, 1, 4, 3);
		HandleBack.setRotationPoint(15F, 4F, -1.5F);
		HandleBack.setTextureSize(128, 64);
		HandleBack.mirror = true;
		setRotation(HandleBack, 0F, 0F, 0.4363323F);
		LeverFront = new ModelRenderer(this, 62, 30);
		LeverFront.addBox(-1F, 0F, 0F, 2, 4, 2);
		LeverFront.setRotationPoint(7F, 9F, -1F);
		LeverFront.setTextureSize(128, 64);
		LeverFront.mirror = true;
		setRotation(LeverFront, 0F, 0F, 0F);
		LeverBottom = new ModelRenderer(this, 70, 30);
		LeverBottom.addBox(0F, 4F, 0F, 6, 1, 2);
		LeverBottom.setRotationPoint(7F, 9F, -1F);
		LeverBottom.setTextureSize(128, 64);
		LeverBottom.mirror = true;
		setRotation(LeverBottom, 0F, 0F, 0F);
		LeverMid = new ModelRenderer(this, 62, 36);
		LeverMid.addBox(6F, 0F, 0F, 1, 5, 2);
		LeverMid.setRotationPoint(7F, 9F, -1F);
		LeverMid.setTextureSize(128, 64);
		LeverMid.mirror = true;
		setRotation(LeverMid, 0F, 0F, 0F);
		LeverFrontPlate = new ModelRenderer(this, 68, 39);
		LeverFrontPlate.addBox(7F, -1.5F, 0F, 9, 1, 2);
		LeverFrontPlate.setRotationPoint(7F, 9F, -1F);
		LeverFrontPlate.setTextureSize(128, 64);
		LeverFrontPlate.mirror = true;
		setRotation(LeverFrontPlate, 0F, 0F, 0.7853982F);
		LeverBackBottom = new ModelRenderer(this, 70, 33);
		LeverBackBottom.addBox(12F, 10F, 0F, 6, 1, 2);
		LeverBackBottom.setRotationPoint(7F, 9F, -1F);
		LeverBackBottom.setTextureSize(128, 64);
		LeverBackBottom.mirror = true;
		setRotation(LeverBackBottom, 0F, 0F, 0F);
		Trigger = new ModelRenderer(this, 88, 30);
		Trigger.addBox(-1F, 0F, 0F, 1, 3, 1);
		Trigger.setRotationPoint(12.5F, 9F, -0.5F);
		Trigger.setTextureSize(128, 64);
		Trigger.mirror = true;
		setRotation(Trigger, 0F, 0F, 0.3490659F);
		LeverBackTop = new ModelRenderer(this, 68, 36);
		LeverBackTop.addBox(8F, -2F, 0F, 11, 1, 2);
		LeverBackTop.setRotationPoint(7F, 9F, -1F);
		LeverBackTop.setTextureSize(128, 64);
		LeverBackTop.mirror = true;
		setRotation(LeverBackTop, 0F, 0F, 0.4363323F);
		LeverBack = new ModelRenderer(this, 62, 43);
		LeverBack.addBox(17F, 6F, 0F, 1, 4, 2);
		LeverBack.setRotationPoint(7F, 9F, -1F);
		LeverBack.setTextureSize(128, 64);
		LeverBack.mirror = true;
		setRotation(LeverBack, 0F, 0F, 0F);
	}

	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Barrel1.render(f5);
		Barrel2.render(f5);
		Barrel3.render(f5);
		Barrel4.render(f5);
		Grip.render(f5);
		Front1.render(f5);
		Front2.render(f5);
		BodyFront.render(f5);
		BodyTop.render(f5);
		BodyMain.render(f5);
		BodyPlate.render(f5);
		HandleMain.render(f5);
		HandleBottom.render(f5);
		HandleBack.render(f5);
		LeverFront.render(f5);
		LeverBottom.render(f5);
		LeverMid.render(f5);
		LeverFrontPlate.render(f5);
		LeverBackBottom.render(f5);
		Trigger.render(f5);
		LeverBackTop.render(f5);
		LeverBack.render(f5);
	}

	public void renderAnim(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final float anim) {
		LeverFront.rotateAngleZ += anim;
		LeverBottom.rotateAngleZ += anim;
		LeverMid.rotateAngleZ += anim;
		LeverFrontPlate.rotateAngleZ += anim;
		LeverBackBottom.rotateAngleZ += anim;
		LeverBackTop.rotateAngleZ += anim;
		LeverBack.rotateAngleZ += anim;

		render(entity, f, f1, f2, f3, f4, f5);

		setRotation(LeverFront, 0F, 0F, 0F);
		setRotation(LeverBottom, 0F, 0F, 0F);
		setRotation(LeverMid, 0F, 0F, 0F);
		setRotation(LeverFrontPlate, 0F, 0F, 0.7853982F);
		setRotation(LeverBackBottom, 0F, 0F, 0F);
		setRotation(LeverBackTop, 0F, 0F, 0.4363323F);
		setRotation(LeverBack, 0F, 0F, 0F);
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
