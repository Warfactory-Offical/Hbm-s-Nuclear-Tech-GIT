package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import com.hbm.items.weapon.ItemGunBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ModelUboinik extends ModelBase {

	ModelRenderer Barrel;
	ModelRenderer ConnectorFront;
	ModelRenderer ConnectorBack;
	ModelRenderer Grip;
	ModelRenderer Coil;
	ModelRenderer FrontBase;
	ModelRenderer FrontTip;
	ModelRenderer DrumCenter;
	ModelRenderer DrumBack;
	ModelRenderer DrumBottom;
	ModelRenderer Shell1;
	ModelRenderer Shell2;
	ModelRenderer Shell3;
	ModelRenderer Shell4;
	ModelRenderer Shell5;
	ModelRenderer Shell6;
	ModelRenderer Clip1;
	ModelRenderer Clip2;
	ModelRenderer Clip3;
	ModelRenderer ChamberBack;
	ModelRenderer ChamberBackConnector;
	ModelRenderer ChamberTop;
	ModelRenderer Chamber;
	ModelRenderer Stock;
	ModelRenderer TriggerFrame;
	ModelRenderer Trigger;
	ModelRenderer Handle;
	ModelRenderer StockBottom;

	public ModelUboinik() {
		textureWidth = 64;
		textureHeight = 64;

		Barrel = new ModelRenderer(this, 0, 11);
		Barrel.addBox(0F, 0F, 0F, 24, 2, 2);
		Barrel.setRotationPoint(-24F, 0F, -1F);
		Barrel.setTextureSize(64, 64);
		Barrel.mirror = true;
		setRotation(Barrel, 0F, 0F, 0F);
		ConnectorFront = new ModelRenderer(this, 0, 53);
		ConnectorFront.addBox(0F, 0F, 0F, 1, 8, 3);
		ConnectorFront.setRotationPoint(-16F, -0.5F, -1.5F);
		ConnectorFront.setTextureSize(64, 64);
		ConnectorFront.mirror = true;
		setRotation(ConnectorFront, 0F, 0F, 0F);
		ConnectorBack = new ModelRenderer(this, 8, 53);
		ConnectorBack.addBox(0F, 0F, 0F, 1, 8, 3);
		ConnectorBack.setRotationPoint(0F, -0.5F, -1.5F);
		ConnectorBack.setTextureSize(64, 64);
		ConnectorBack.mirror = true;
		setRotation(ConnectorBack, 0F, 0F, 0F);
		Grip = new ModelRenderer(this, 16, 57);
		Grip.addBox(0F, 0F, 0F, 15, 4, 3);
		Grip.setRotationPoint(-15F, 3.5F, -1.5F);
		Grip.setTextureSize(64, 64);
		Grip.mirror = true;
		setRotation(Grip, 0F, 0F, 0F);
		Coil = new ModelRenderer(this, 16, 51);
		Coil.addBox(0F, 0F, 0F, 15, 3, 3);
		Coil.setRotationPoint(-15F, -0.5F, -1.5F);
		Coil.setTextureSize(64, 64);
		Coil.mirror = true;
		setRotation(Coil, 0F, 0F, 0F);
		FrontBase = new ModelRenderer(this, 0, 49);
		FrontBase.addBox(0F, 0F, 0F, 1, 2, 2);
		FrontBase.setRotationPoint(-17F, 4.5F, -1F);
		FrontBase.setTextureSize(64, 64);
		FrontBase.mirror = true;
		setRotation(FrontBase, 0F, 0F, 0F);
		FrontTip = new ModelRenderer(this, 6, 51);
		FrontTip.addBox(0F, 0F, 0F, 4, 1, 1);
		FrontTip.setRotationPoint(-21F, 5F, -0.5F);
		FrontTip.setTextureSize(64, 64);
		FrontTip.mirror = true;
		setRotation(FrontTip, 0F, 0F, 0F);
		DrumCenter = new ModelRenderer(this, 6, 49);
		DrumCenter.addBox(0F, 0F, 0F, 6, 1, 1);
		DrumCenter.setRotationPoint(1F, 3F, -0.5F);
		DrumCenter.setTextureSize(64, 64);
		DrumCenter.mirror = true;
		setRotation(DrumCenter, 0F, 0F, 0F);
		DrumBack = new ModelRenderer(this, 0, 40);
		DrumBack.addBox(0F, 0F, 0F, 1, 7, 2);
		DrumBack.setRotationPoint(7F, 1F, -1F);
		DrumBack.setTextureSize(64, 64);
		DrumBack.mirror = true;
		setRotation(DrumBack, 0F, 0F, 0F);
		DrumBottom = new ModelRenderer(this, 6, 46);
		DrumBottom.addBox(0F, 0F, 0F, 7, 1, 2);
		DrumBottom.setRotationPoint(0F, 7.5F, -1F);
		DrumBottom.setTextureSize(64, 64);
		DrumBottom.mirror = true;
		setRotation(DrumBottom, 0F, 0F, 0F);
		Shell1 = new ModelRenderer(this, 0, 34);
		Shell1.addBox(0F, -3.5F, -1F, 5, 2, 2);
		Shell1.setRotationPoint(1.5F, 3.5F, 0F);
		Shell1.setTextureSize(64, 64);
		Shell1.mirror = true;
		setRotation(Shell1, 1.047198F, 0F, 0F);
		Shell2 = new ModelRenderer(this, 0, 34);
		Shell2.addBox(0F, -3.5F, -1F, 5, 2, 2);
		Shell2.setRotationPoint(1.5F, 3.5F, 0F);
		Shell2.setTextureSize(64, 64);
		Shell2.mirror = true;
		setRotation(Shell2, 2.094395F, 0F, 0F);
		Shell3 = new ModelRenderer(this, 0, 34);
		Shell3.addBox(0F, -3.5F, -1F, 5, 2, 2);
		Shell3.setRotationPoint(1.5F, 3.5F, 0F);
		Shell3.setTextureSize(64, 64);
		Shell3.mirror = true;
		setRotation(Shell3, 3.141593F, 0F, 0F);
		Shell4 = new ModelRenderer(this, 0, 34);
		Shell4.addBox(0F, -3.5F, -1F, 5, 2, 2);
		Shell4.setRotationPoint(1.5F, 3.5F, 0F);
		Shell4.setTextureSize(64, 64);
		Shell4.mirror = true;
		setRotation(Shell4, -2.094395F, 0F, 0F);
		Shell5 = new ModelRenderer(this, 0, 34);
		Shell5.addBox(0F, -3.5F, -1F, 5, 2, 2);
		Shell5.setRotationPoint(1.5F, 3.5F, 0F);
		Shell5.setTextureSize(64, 64);
		Shell5.mirror = true;
		setRotation(Shell5, -1.047198F, 0F, 0F);
		Shell6 = new ModelRenderer(this, 0, 34);
		Shell6.addBox(0F, -3.5F, -1F, 5, 2, 2);
		Shell6.setRotationPoint(1.5F, 3.5F, 0F);
		Shell6.setTextureSize(64, 64);
		Shell6.mirror = true;
		setRotation(Shell6, 0F, 0F, 0F);
		Clip1 = new ModelRenderer(this, 6, 42);
		Clip1.addBox(0F, -1.5F, -0.5F, 2, 3, 1);
		Clip1.setRotationPoint(4F, 3.5F, 0F);
		Clip1.setTextureSize(64, 64);
		Clip1.mirror = true;
		setRotation(Clip1, 0F, 0F, 0F);
		Clip2 = new ModelRenderer(this, 6, 42);
		Clip2.addBox(0F, -1.5F, -0.5F, 2, 3, 1);
		Clip2.setRotationPoint(4F, 3.5F, 0F);
		Clip2.setTextureSize(64, 64);
		Clip2.mirror = true;
		setRotation(Clip2, 1.047198F, 0F, 0F);
		Clip3 = new ModelRenderer(this, 6, 42);
		Clip3.addBox(0F, -1.5F, -0.5F, 2, 3, 1);
		Clip3.setRotationPoint(4F, 3.5F, 0F);
		Clip3.setTextureSize(64, 64);
		Clip3.mirror = true;
		setRotation(Clip3, 2.094395F, 0F, 0F);
		ChamberBack = new ModelRenderer(this, 0, 28);
		ChamberBack.addBox(0F, 0F, 0F, 8, 2, 2);
		ChamberBack.setRotationPoint(8F, 1F, -1F);
		ChamberBack.setTextureSize(64, 64);
		ChamberBack.mirror = true;
		setRotation(ChamberBack, 0F, 0F, 0F);
		ChamberBackConnector = new ModelRenderer(this, 12, 43);
		ChamberBackConnector.addBox(0F, 0F, 0F, 4, 1, 2);
		ChamberBackConnector.setRotationPoint(12F, 0F, -1F);
		ChamberBackConnector.setTextureSize(64, 64);
		ChamberBackConnector.mirror = true;
		setRotation(ChamberBackConnector, 0F, 0F, 0F);
		ChamberTop = new ModelRenderer(this, 0, 25);
		ChamberTop.addBox(0F, 0F, 0F, 9, 1, 2);
		ChamberTop.setRotationPoint(7F, -1F, -1F);
		ChamberTop.setTextureSize(64, 64);
		ChamberTop.mirror = true;
		setRotation(ChamberTop, 0F, 0F, 0F);
		Chamber = new ModelRenderer(this, 0, 20);
		Chamber.addBox(0F, 0F, 0F, 6, 2, 3);
		Chamber.setRotationPoint(1F, -1F, -1.5F);
		Chamber.setTextureSize(64, 64);
		Chamber.mirror = true;
		setRotation(Chamber, 0F, 0F, 0F);
		Stock = new ModelRenderer(this, 0, 15);
		Stock.addBox(0F, 0F, 0F, 23, 3, 2);
		Stock.setRotationPoint(13F, 3F, -1F);
		Stock.setTextureSize(64, 64);
		Stock.mirror = true;
		setRotation(Stock, 0F, 0F, 0F);
		TriggerFrame = new ModelRenderer(this, 24, 45);
		TriggerFrame.addBox(0F, 0F, 0F, 5, 5, 1);
		TriggerFrame.setRotationPoint(8F, 3F, -0.5F);
		TriggerFrame.setTextureSize(64, 64);
		TriggerFrame.mirror = true;
		setRotation(TriggerFrame, 0F, 0F, 0F);
		Trigger = new ModelRenderer(this, 36, 46);
		Trigger.addBox(-1F, 0F, 0F, 1, 4, 1);
		Trigger.setRotationPoint(12.5F, 3F, -0.5F);
		Trigger.setTextureSize(64, 64);
		Trigger.mirror = true;
		setRotation(Trigger, 0F, 0F, 0.4363323F);
		Handle = new ModelRenderer(this, 40, 43);
		Handle.addBox(0F, 0F, 0F, 3, 6, 2);
		Handle.setRotationPoint(13F, 6F, -1F);
		Handle.setTextureSize(64, 64);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, -0.1745329F);
		StockBottom = new ModelRenderer(this, 26, 36);
		StockBottom.addBox(0F, 0F, 0F, 10, 5, 2);
		StockBottom.setRotationPoint(26F, 6F, -1F);
		StockBottom.setTextureSize(64, 64);
		StockBottom.mirror = true;
		setRotation(StockBottom, 0F, 0F, 0F);
	}

	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final ItemStack item) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Barrel.render(f5);
		ConnectorFront.render(f5);
		ConnectorBack.render(f5);
		Grip.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		Coil.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		FrontBase.render(f5);
		FrontTip.render(f5);
		DrumCenter.render(f5);
		DrumBack.render(f5);
		DrumBottom.render(f5);
		
		final int ammo = ItemGunBase.getMag(item);
		
		if(ammo > 5)
			Shell5.render(f5);
		if(ammo > 4)
			Shell4.render(f5);
		if(ammo > 3)
			Shell3.render(f5);
		if(ammo > 2)
			Shell2.render(f5);
		if(ammo > 1)
			Shell1.render(f5);
		if(ammo > 0)
			Shell6.render(f5);
		
		Clip1.render(f5);
		Clip2.render(f5);
		Clip3.render(f5);
		ChamberBack.render(f5);
		ChamberBackConnector.render(f5);
		ChamberTop.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		Chamber.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		Stock.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		TriggerFrame.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		Trigger.render(f5);
		Handle.render(f5);
		StockBottom.render(f5);
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
