package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.loader.ModelRendererObj;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class ModelHat extends ModelArmorBase {

	public ModelHat(final int type) {
		super(type);

		head = new ModelRendererObj(ResourceManager.armor_hat);
		body = new ModelRendererObj(ResourceManager.armor_bj, "Body");
		leftArm = new ModelRendererObj(ResourceManager.armor_bj, "LeftArm").setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightArm = new ModelRendererObj(ResourceManager.armor_bj, "RightArm").setRotationPoint(5.0F, 2.0F, 0.0F);
		leftLeg = new ModelRendererObj(ResourceManager.armor_bj, "LeftLeg").setRotationPoint(1.9F, 12.0F, 0.0F);
		rightLeg = new ModelRendererObj(ResourceManager.armor_bj, "RightLeg").setRotationPoint(-1.9F, 12.0F, 0.0F);
		leftFoot = new ModelRendererObj(ResourceManager.armor_bj, "LeftFoot").setRotationPoint(1.9F, 12.0F, 0.0F);
		rightFoot = new ModelRendererObj(ResourceManager.armor_bj, "RightFoot").setRotationPoint(-1.9F, 12.0F, 0.0F);
	}

	@Override
	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {

		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

		GL11.glPushMatrix();

		if(type == 0) {
			Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.hat);
			head.render(par7*1.001F);
		}

		GL11.glPopMatrix();
	}
}