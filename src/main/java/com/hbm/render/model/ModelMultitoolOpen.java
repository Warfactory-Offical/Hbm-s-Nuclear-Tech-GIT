package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMultitoolOpen extends ModelBase {
	// fields
	ModelRenderer Base;
	ModelRenderer BTop;
	ModelRenderer BBottom;
	ModelRenderer BLeft;
	ModelRenderer BRight;
	ModelRenderer RTop;
	ModelRenderer RBottom;
	ModelRenderer RLeft;
	ModelRenderer RRight;
	ModelRenderer GPivot;
	ModelRenderer GBase;
	ModelRenderer F31;
	ModelRenderer F21;
	ModelRenderer F41;
	ModelRenderer F51;
	ModelRenderer F11;
	ModelRenderer F22;
	ModelRenderer F32;
	ModelRenderer F42;
	ModelRenderer F52;
	ModelRenderer F12;
	ModelRenderer F23;
	ModelRenderer F33;
	ModelRenderer F43;
	ModelRenderer F53;
	ModelRenderer F13;
	ModelRenderer WireL;
	ModelRenderer WireR;
	ModelRenderer Gauge1;
	ModelRenderer Gauge2;
	ModelRenderer WireB;

	public ModelMultitoolOpen() {
		textureWidth = 64;
		textureHeight = 64;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 3, 8, 8);
		Base.setRotationPoint(-3F, -4F, -4F);
		Base.setTextureSize(64, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		BTop = new ModelRenderer(this, 0, 16);
		BTop.addBox(0F, 0F, 0F, 4, 2, 8);
		BTop.setRotationPoint(-3F, -4F, -4F);
		BTop.setTextureSize(64, 64);
		BTop.mirror = true;
		setRotation(BTop, 0F, 0F, -0.2617994F);
		BBottom = new ModelRenderer(this, 0, 26);
		BBottom.addBox(0F, -2F, 0F, 4, 2, 8);
		BBottom.setRotationPoint(-3F, 4F, -4F);
		BBottom.setTextureSize(64, 64);
		BBottom.mirror = true;
		setRotation(BBottom, 0F, 0F, 0.2617994F);
		BLeft = new ModelRenderer(this, 0, 36);
		BLeft.addBox(0F, 0F, 0F, 4, 8, 2);
		BLeft.setRotationPoint(-3F, -4F, -4F);
		BLeft.setTextureSize(64, 64);
		BLeft.mirror = true;
		setRotation(BLeft, 0F, 0.2617994F, 0F);
		BRight = new ModelRenderer(this, 12, 36);
		BRight.addBox(0F, 0F, -2F, 4, 8, 2);
		BRight.setRotationPoint(-3F, -4F, 4F);
		BRight.setTextureSize(64, 64);
		BRight.mirror = true;
		setRotation(BRight, 0F, -0.2617994F, 0F);
		RTop = new ModelRenderer(this, 24, 0);
		RTop.addBox(0F, 0F, 0F, 3, 2, 10);
		RTop.setRotationPoint(4F, -6F, -6F);
		RTop.setTextureSize(64, 64);
		RTop.mirror = true;
		setRotation(RTop, 0F, 0F, 0F);
		RBottom = new ModelRenderer(this, 24, 12);
		RBottom.addBox(0F, 0F, 0F, 3, 2, 10);
		RBottom.setRotationPoint(4F, 4F, -4F);
		RBottom.setTextureSize(64, 64);
		RBottom.mirror = true;
		setRotation(RBottom, 0F, 0F, 0F);
		RLeft = new ModelRenderer(this, 0, 46);
		RLeft.addBox(0F, 0F, 0F, 3, 10, 2);
		RLeft.setRotationPoint(4F, -4F, -6F);
		RLeft.setTextureSize(64, 64);
		RLeft.mirror = true;
		setRotation(RLeft, 0F, 0F, 0F);
		RRight = new ModelRenderer(this, 10, 46);
		RRight.addBox(0F, 0F, 0F, 3, 10, 2);
		RRight.setRotationPoint(4F, -6F, 4F);
		RRight.setTextureSize(64, 64);
		RRight.mirror = true;
		setRotation(RRight, 0F, 0F, 0F);
		GPivot = new ModelRenderer(this, 24, 24);
		GPivot.addBox(0F, 0F, 0F, 3, 4, 4);
		GPivot.setRotationPoint(-6F, -2F, -2F);
		GPivot.setTextureSize(64, 64);
		GPivot.mirror = true;
		setRotation(GPivot, 0F, 0F, 0F);
		GBase = new ModelRenderer(this, 24, 32);
		GBase.addBox(-2F, -3F, -4F, 4, 3, 8);
		GBase.setRotationPoint(-6F, 0F, 1F);
		GBase.setTextureSize(64, 64);
		GBase.mirror = true;
		setRotation(GBase, 0F, 0F, 1.047198F);
		F31 = new ModelRenderer(this, 20, 52);
		F31.addBox(-3F, -1F, 0F, 3, 2, 2);
		F31.setRotationPoint(-5.5F, -2F, -1F);
		F31.setTextureSize(64, 64);
		F31.mirror = true;
		setRotation(F31, 0F, 0F, 1.48353F);
		F21 = new ModelRenderer(this, 30, 52);
		F21.addBox(-3F, -1F, -2F, 3, 2, 2);
		F21.setRotationPoint(-5.5F, -2F, -1.2F);
		F21.setTextureSize(64, 64);
		F21.mirror = true;
		setRotation(F21, 0F, 0F, 1.48353F);
		F41 = new ModelRenderer(this, 40, 52);
		F41.addBox(-3F, -1F, 0F, 3, 2, 2);
		F41.setRotationPoint(-5.5F, -2F, 1.2F);
		F41.setTextureSize(64, 64);
		F41.mirror = true;
		setRotation(F41, 0F, 0F, 1.48353F);
		F51 = new ModelRenderer(this, 50, 52);
		F51.addBox(-3F, -1F, 0F, 3, 2, 2);
		F51.setRotationPoint(-5.5F, -2F, 3.4F);
		F51.setTextureSize(64, 64);
		F51.mirror = true;
		setRotation(F51, 0F, 0F, 1.48353F);
		F11 = new ModelRenderer(this, 48, 38);
		F11.addBox(0F, -1F, -3F, 2, 2, 3);
		F11.setRotationPoint(-5.5F, -2F, -3F);
		F11.setTextureSize(64, 64);
		F11.mirror = true;
		setRotation(F11, 0F, 0F, 1.047198F);
		F22 = new ModelRenderer(this, 20, 56);
		F22.addBox(-3F, -1F, -1F, 3, 2, 2);
		F22.setRotationPoint(-5.6F, -4.5F, -2.2F);
		F22.setTextureSize(64, 64);
		F22.mirror = true;
		setRotation(F22, 0F, 0F, 1.134464F);
		F32 = new ModelRenderer(this, 30, 56);
		F32.addBox(-3F, -1F, -1F, 3, 2, 2);
		F32.setRotationPoint(-5.6F, -4.5F, 0F);
		F32.setTextureSize(64, 64);
		F32.mirror = true;
		setRotation(F32, 0F, 0F, 1.134464F);
		F42 = new ModelRenderer(this, 40, 56);
		F42.addBox(-3F, -1F, -1F, 3, 2, 2);
		F42.setRotationPoint(-5.6F, -4.5F, 2.2F);
		F42.setTextureSize(64, 64);
		F42.mirror = true;
		setRotation(F42, 0F, 0F, 1.134464F);
		F52 = new ModelRenderer(this, 50, 56);
		F52.addBox(-3F, -1F, -1F, 3, 2, 2);
		F52.setRotationPoint(-5.6F, -4.5F, 4.4F);
		F52.setTextureSize(64, 64);
		F52.mirror = true;
		setRotation(F52, 0F, 0F, 1.134464F);
		F12 = new ModelRenderer(this, 48, 34);
		F12.addBox(-1F, -1F, -2F, 2, 2, 2);
		F12.setRotationPoint(-5F, -1F, -5.8F);
		F12.setTextureSize(64, 64);
		F12.mirror = true;
		setRotation(F12, 0F, 0.3490659F, 1.047198F);
		F23 = new ModelRenderer(this, 20, 60);
		F23.addBox(-3F, -1F, -1F, 3, 2, 2);
		F23.setRotationPoint(-6.6F, -6.8F, -2.2F);
		F23.setTextureSize(64, 64);
		F23.mirror = true;
		setRotation(F23, 0F, 0F, 0.5235988F);
		F33 = new ModelRenderer(this, 30, 60);
		F33.addBox(-3F, -1F, -1F, 3, 2, 2);
		F33.setRotationPoint(-6.6F, -6.8F, 0F);
		F33.setTextureSize(64, 64);
		F33.mirror = true;
		setRotation(F33, 0F, 0F, 0.5235988F);
		F43 = new ModelRenderer(this, 40, 60);
		F43.addBox(-3F, -1F, -1F, 3, 2, 2);
		F43.setRotationPoint(-6.6F, -6.8F, 2.2F);
		F43.setTextureSize(64, 64);
		F43.mirror = true;
		setRotation(F43, 0F, 0F, 0.5235988F);
		F53 = new ModelRenderer(this, 50, 60);
		F53.addBox(-3F, -1F, -1F, 3, 2, 2);
		F53.setRotationPoint(-6.6F, -6.8F, 4.4F);
		F53.setTextureSize(64, 64);
		F53.mirror = true;
		setRotation(F53, 0F, 0F, 0.5235988F);
		F13 = new ModelRenderer(this, 48, 30);
		F13.addBox(-1F, -1F, -2F, 2, 2, 2);
		F13.setRotationPoint(-5.5F, -1F, -7.2F);
		F13.setTextureSize(64, 64);
		F13.mirror = true;
		setRotation(F13, 0F, 1.047198F, 1.047198F);
		WireL = new ModelRenderer(this, 38, 30);
		WireL.addBox(0F, 0F, 0F, 4, 1, 1);
		WireL.setRotationPoint(0F, -5.5F, 0F);
		WireL.setTextureSize(64, 64);
		WireL.mirror = true;
		setRotation(WireL, 0F, 0F, 0F);
		WireR = new ModelRenderer(this, 38, 28);
		WireR.addBox(0F, 0F, 0F, 4, 1, 1);
		WireR.setRotationPoint(0F, -5.5F, 2F);
		WireR.setTextureSize(64, 64);
		WireR.mirror = true;
		setRotation(WireR, 0F, 0F, 0F);
		Gauge1 = new ModelRenderer(this, 20, 47);
		Gauge1.addBox(-1.5F, -1F, -2F, 3, 1, 4);
		Gauge1.setRotationPoint(-1F, -4F, 4F);
		Gauge1.setTextureSize(64, 64);
		Gauge1.mirror = true;
		setRotation(Gauge1, -0.7853982F, 0F, 0F);
		Gauge2 = new ModelRenderer(this, 34, 48);
		Gauge2.addBox(-2F, -1F, -1.5F, 4, 1, 3);
		Gauge2.setRotationPoint(-1F, -4F, 4F);
		Gauge2.setTextureSize(64, 64);
		Gauge2.mirror = true;
		setRotation(Gauge2, -0.7853982F, 0F, 0F);
		WireB = new ModelRenderer(this, 48, 49);
		WireB.addBox(0F, 0F, 0F, 4, 2, 1);
		WireB.setRotationPoint(0F, -1F, -5.5F);
		WireB.setTextureSize(64, 64);
		WireB.mirror = true;
		setRotation(WireB, 0F, 0F, 0F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Base.render(f5);
		BTop.render(f5);
		BBottom.render(f5);
		BLeft.render(f5);
		BRight.render(f5);
		RTop.render(f5);
		RBottom.render(f5);
		RLeft.render(f5);
		RRight.render(f5);
		GPivot.render(f5);
		GBase.render(f5);
		F31.render(f5);
		F21.render(f5);
		F41.render(f5);
		F51.render(f5);
		F11.render(f5);
		F22.render(f5);
		F32.render(f5);
		F42.render(f5);
		F52.render(f5);
		F12.render(f5);
		F23.render(f5);
		F33.render(f5);
		F43.render(f5);
		F53.render(f5);
		F13.render(f5);
		WireL.render(f5);
		WireR.render(f5);
		Gauge1.render(f5);
		Gauge2.render(f5);
		WireB.render(f5);
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
