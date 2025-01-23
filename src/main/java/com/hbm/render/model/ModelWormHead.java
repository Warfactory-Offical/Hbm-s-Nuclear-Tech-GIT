package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.render.amlfrom1710.AdvancedModelLoader;
import com.hbm.render.amlfrom1710.IModelCustom;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelWormHead extends ModelBase {

	public static final IModelCustom head = AdvancedModelLoader.loadModel(new ResourceLocation(RefStrings.MODID, "models/mobs/bot_prime_head.obj"));

	@Override
	public void render(final Entity entity, final float x, final float y, final float z, final float f3, final float f4, final float f5) {
		super.render(entity, x, y, z, f3, f4, f5);

		GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f5 - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f5 - 90, 0.0F, 0.0F, 1.0F);

		head.renderAll();
	}

}