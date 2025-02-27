package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelImmolator extends ModelBase {

	ModelRenderer CylinderBase;
	ModelRenderer CylinderFront;
	ModelRenderer PipeBBase;
	ModelRenderer PipeB;
	ModelRenderer TipBase;
	ModelRenderer PipeL;
	ModelRenderer PipeLFront;
	ModelRenderer TipFront;
	ModelRenderer ChamberH;
	ModelRenderer ChamberW;
	ModelRenderer PipeTBack;
	ModelRenderer PipeTBase;
	ModelRenderer PipeT;
	ModelRenderer PipeTFront;
	ModelRenderer Body;
	ModelRenderer Tank;
	ModelRenderer HandleBase;
	ModelRenderer HandleBar;
	ModelRenderer HandleGrip;
	ModelRenderer BodyPlate;
	ModelRenderer ValveFront;
	ModelRenderer ValveCenter;
	ModelRenderer Stock;
	ModelRenderer LatchBase;
	ModelRenderer LatchHandle;
	ModelRenderer ValveBack;

	public ModelImmolator() {
		textureWidth = 64;
		textureHeight = 32;

		CylinderBase = new ModelRenderer(this, 0, 0);
		CylinderBase.addBox(0F, 0F, 0F, 2, 4, 4);
		CylinderBase.setRotationPoint(0F, 0F, -2F);
		CylinderBase.setTextureSize(64, 32);
		CylinderBase.mirror = true;
		setRotation(CylinderBase, 0F, 0F, 0F);
		CylinderFront = new ModelRenderer(this, 0, 8);
		CylinderFront.addBox(0F, 0F, 0F, 1, 3, 3);
		CylinderFront.setRotationPoint(-1F, 0.5F, -1.5F);
		CylinderFront.setTextureSize(64, 32);
		CylinderFront.mirror = true;
		setRotation(CylinderFront, 0F, 0F, 0F);
		PipeBBase = new ModelRenderer(this, 0, 14);
		PipeBBase.addBox(0F, 0F, 0F, 2, 2, 2);
		PipeBBase.setRotationPoint(-2F, 2F, 0F);
		PipeBBase.setTextureSize(64, 32);
		PipeBBase.mirror = true;
		setRotation(PipeBBase, 0F, 0F, 0F);
		PipeB = new ModelRenderer(this, 0, 18);
		PipeB.addBox(0F, 0F, 0F, 8, 1, 1);
		PipeB.setRotationPoint(-10F, 2.5F, 0.5F);
		PipeB.setTextureSize(64, 32);
		PipeB.mirror = true;
		setRotation(PipeB, 0F, 0F, 0F);
		TipBase = new ModelRenderer(this, 8, 8);
		TipBase.addBox(0F, 0F, 0F, 2, 2, 2);
		TipBase.setRotationPoint(-12F, 1.5F, -0.5F);
		TipBase.setTextureSize(64, 32);
		TipBase.mirror = true;
		setRotation(TipBase, 0F, 0F, 0F);
		PipeL = new ModelRenderer(this, 0, 20);
		PipeL.addBox(0F, 0F, 0F, 7, 1, 1);
		PipeL.setRotationPoint(-7F, 2.5F, -1.5F);
		PipeL.setTextureSize(64, 32);
		PipeL.mirror = true;
		setRotation(PipeL, 0F, 0F, 0F);
		PipeLFront = new ModelRenderer(this, 0, 22);
		PipeLFront.addBox(-4F, 0F, 0F, 4, 1, 1);
		PipeLFront.setRotationPoint(-7F, 2.5F, -1.5F);
		PipeLFront.setTextureSize(64, 32);
		PipeLFront.mirror = true;
		setRotation(PipeLFront, 0F, 0.4363323F, 0F);
		TipFront = new ModelRenderer(this, 8, 12);
		TipFront.addBox(0F, 0F, 0F, 2, 1, 1);
		TipFront.setRotationPoint(-14F, 2F, 0F);
		TipFront.setTextureSize(64, 32);
		TipFront.mirror = true;
		setRotation(TipFront, 0F, 0F, 0F);
		ChamberH = new ModelRenderer(this, 24, 0);
		ChamberH.addBox(0F, 0F, 0F, 4, 5, 4);
		ChamberH.setRotationPoint(2F, -0.5F, -2F);
		ChamberH.setTextureSize(64, 32);
		ChamberH.mirror = true;
		setRotation(ChamberH, 0F, 0F, 0F);
		ChamberW = new ModelRenderer(this, 40, 0);
		ChamberW.addBox(0F, 0F, 0F, 4, 4, 5);
		ChamberW.setRotationPoint(2F, 0F, -2.5F);
		ChamberW.setTextureSize(64, 32);
		ChamberW.mirror = true;
		setRotation(ChamberW, 0F, 0F, 0F);
		PipeTBack = new ModelRenderer(this, 0, 24);
		PipeTBack.addBox(0F, 0F, 0F, 6, 1, 1);
		PipeTBack.setRotationPoint(0F, -2F, 2.5F);
		PipeTBack.setTextureSize(64, 32);
		PipeTBack.mirror = true;
		setRotation(PipeTBack, -0.4363323F, 0F, 0F);
		PipeTBase = new ModelRenderer(this, 8, 14);
		PipeTBase.addBox(0F, 1F, 0F, 1, 2, 1);
		PipeTBase.setRotationPoint(1F, -2F, 2.5F);
		PipeTBase.setTextureSize(64, 32);
		PipeTBase.mirror = true;
		setRotation(PipeTBase, -0.4363323F, 0F, 0F);
		PipeT = new ModelRenderer(this, 0, 26);
		PipeT.addBox(-9F, 0F, 0F, 9, 1, 1);
		PipeT.setRotationPoint(0F, -2F, 2.5F);
		PipeT.setTextureSize(64, 32);
		PipeT.mirror = true;
		setRotation(PipeT, -0.4363323F, -0.296706F, -0.4014257F);
		PipeTFront = new ModelRenderer(this, 0, 28);
		PipeTFront.addBox(0F, 0F, 0F, 3, 1, 1);
		PipeTFront.setRotationPoint(-10F, 1.5F, 0F);
		PipeTFront.setTextureSize(64, 32);
		PipeTFront.mirror = true;
		setRotation(PipeTFront, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 24, 9);
		Body.addBox(0F, 0F, 0F, 10, 4, 4);
		Body.setRotationPoint(6F, 0.5F, -1.5F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Tank = new ModelRenderer(this, 12, 0);
		Tank.addBox(0F, 0F, 0F, 3, 3, 2);
		Tank.setRotationPoint(6F, 1F, -3F);
		Tank.setTextureSize(64, 32);
		Tank.mirror = true;
		setRotation(Tank, 0F, 0F, 0F);
		HandleBase = new ModelRenderer(this, 24, 17);
		HandleBase.addBox(0F, 0F, 0F, 2, 2, 1);
		HandleBase.setRotationPoint(4F, 1F, -3F);
		HandleBase.setTextureSize(64, 32);
		HandleBase.mirror = true;
		setRotation(HandleBase, 0F, 0F, 0F);
		HandleBar = new ModelRenderer(this, 30, 17);
		HandleBar.addBox(0F, 0F, 0F, 1, 1, 2);
		HandleBar.setRotationPoint(4.5F, 1.5F, -5F);
		HandleBar.setTextureSize(64, 32);
		HandleBar.mirror = true;
		setRotation(HandleBar, 0F, 0F, 0F);
		HandleGrip = new ModelRenderer(this, 36, 17);
		HandleGrip.addBox(0F, 0F, -2F, 1, 1, 2);
		HandleGrip.setRotationPoint(4.5F, 1.5F, -5F);
		HandleGrip.setTextureSize(64, 32);
		HandleGrip.mirror = true;
		setRotation(HandleGrip, 0F, -0.3490659F, 0F);
		BodyPlate = new ModelRenderer(this, 24, 20);
		BodyPlate.addBox(0F, 0F, 0F, 10, 1, 2);
		BodyPlate.setRotationPoint(6F, 0F, 0F);
		BodyPlate.setTextureSize(64, 32);
		BodyPlate.mirror = true;
		setRotation(BodyPlate, 0F, 0F, 0F);
		ValveFront = new ModelRenderer(this, 0, 30);
		ValveFront.addBox(0F, 0F, 0F, 1, 1, 1);
		ValveFront.setRotationPoint(8F, -0.5F, 0.5F);
		ValveFront.setTextureSize(64, 32);
		ValveFront.mirror = true;
		setRotation(ValveFront, 0F, 0F, 0F);
		ValveCenter = new ModelRenderer(this, 4, 30);
		ValveCenter.addBox(0F, 0F, 0F, 1, 1, 1);
		ValveCenter.setRotationPoint(11F, -0.5F, 0.5F);
		ValveCenter.setTextureSize(64, 32);
		ValveCenter.mirror = true;
		setRotation(ValveCenter, 0F, 0F, 0F);
		Stock = new ModelRenderer(this, 24, 23);
		Stock.addBox(0F, 0F, 0F, 2, 4, 2);
		Stock.setRotationPoint(16F, 0.5F, 0F);
		Stock.setTextureSize(64, 32);
		Stock.mirror = true;
		setRotation(Stock, 0F, 0F, 0F);
		LatchBase = new ModelRenderer(this, 8, 28);
		LatchBase.addBox(0F, 0F, 0F, 2, 1, 1);
		LatchBase.setRotationPoint(16F, 0.5F, 2F);
		LatchBase.setTextureSize(64, 32);
		LatchBase.mirror = true;
		setRotation(LatchBase, 0F, 0F, 0F);
		LatchHandle = new ModelRenderer(this, 32, 23);
		LatchHandle.addBox(0F, 0F, 0F, 2, 3, 1);
		LatchHandle.setRotationPoint(16F, 0.5F, 3F);
		LatchHandle.setTextureSize(64, 32);
		LatchHandle.mirror = true;
		setRotation(LatchHandle, 0F, 0F, 0F);
		ValveBack = new ModelRenderer(this, 8, 30);
		ValveBack.addBox(0F, 0F, 0F, 1, 1, 1);
		ValveBack.setRotationPoint(17.5F, 1F, 0.5F);
		ValveBack.setTextureSize(64, 32);
		ValveBack.mirror = true;
		setRotation(ValveBack, 0F, 0F, 0F);
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		CylinderBase.render(f5);
		CylinderFront.render(f5);
		PipeBBase.render(f5);
		PipeB.render(f5);
		TipBase.render(f5);
		PipeL.render(f5);
		PipeLFront.render(f5);
		TipFront.render(f5);
		ChamberH.render(f5);
		ChamberW.render(f5);
		PipeTBack.render(f5);
		PipeTBase.render(f5);
		PipeT.render(f5);
		PipeTFront.render(f5);
		Body.render(f5);
		Tank.render(f5);
		HandleBase.render(f5);
		HandleBar.render(f5);
		HandleGrip.render(f5);
		BodyPlate.render(f5);
		ValveFront.render(f5);
		ValveCenter.render(f5);
		Stock.render(f5);
		LatchBase.render(f5);
		LatchHandle.render(f5);
		ValveBack.render(f5);
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
