package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTaintCrab extends ModelBase {

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		GL11.glPushMatrix();

		GL11.glRotatef(90, 0, -1, 0);
		GL11.glRotatef(180, 0, 0, 1);
		GL11.glTranslatef(0, -1.5F, 0);

		final float rot = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1 * 57.3F;

		ResourceManager.taintcrab.renderPart("Body");

		GL11.glPushMatrix();
		GL11.glRotatef(rot, 0, 1, 0);
		ResourceManager.taintcrab.renderPart("Legs1");
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glRotatef(rot, 0, -1, 0);
		ResourceManager.taintcrab.renderPart("Legs2");
		GL11.glPopMatrix();

		GL11.glPopMatrix();
	}
}