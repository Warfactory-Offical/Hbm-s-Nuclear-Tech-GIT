package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.loader.ModelRendererObj;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class ModelArmorHEV extends ModelArmorBase {

	public ModelArmorHEV(final int type) {
		super(type);

		head = new ModelRendererObj(ResourceManager.armor_hev, "Head");
		body = new ModelRendererObj(ResourceManager.armor_hev, "Body");
		leftArm = new ModelRendererObj(ResourceManager.armor_hev, "LeftArm").setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightArm = new ModelRendererObj(ResourceManager.armor_hev, "RightArm").setRotationPoint(5.0F, 2.0F, 0.0F);
		leftLeg = new ModelRendererObj(ResourceManager.armor_hev, "LeftLeg").setRotationPoint(1.9F, 12.0F, 0.0F);
		rightLeg = new ModelRendererObj(ResourceManager.armor_hev, "RightLeg").setRotationPoint(-1.9F, 12.0F, 0.0F);
		leftFoot = new ModelRendererObj(ResourceManager.armor_hev, "LeftFoot").setRotationPoint(1.9F, 12.0F, 0.0F);
		rightFoot = new ModelRendererObj(ResourceManager.armor_hev, "RightFoot").setRotationPoint(-1.9F, 12.0F, 0.0F);
	}

	@Override
	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {

		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

		GL11.glPushMatrix();
		if(this.isChild) {
			GL11.glScalef(0.75F, 0.75F, 0.75F);
			GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
		}
		if(type == 0) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.hev_helmet);
			head.render(par7*1.15F);
		}
		if(this.isChild) {
			GL11.glScalef(0.75F, 0.75F, 0.75F);
		}
		if(type == 1) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.hev_chest);
			body.render(par7);
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.hev_arm);
			leftArm.render(par7);
			rightArm.render(par7);
		}
		if(type == 2) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.hev_leg);
			leftLeg.render(par7);
			rightLeg.render(par7);
		}
		if(type == 3) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.hev_leg);
			leftFoot.render(par7);
			rightFoot.render(par7);
		}

		GL11.glPopMatrix();
	}
}