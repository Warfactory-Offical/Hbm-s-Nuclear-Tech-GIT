package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;

public class ModelFolly extends ModelBase {

	ModelRenderer SB1;
	ModelRenderer SB2;
	ModelRenderer SB3;
	ModelRenderer SB4;
	ModelRenderer SB5;
	ModelRenderer SB6;
	ModelRenderer SB7;
	ModelRenderer SB8;
	ModelRenderer SP;
	ModelRenderer B1;
	ModelRenderer B2;
	ModelRenderer B3;
	ModelRenderer SF1;
	ModelRenderer SF2;
	ModelRenderer SF3;
	ModelRenderer SF4;
	ModelRenderer P1;
	ModelRenderer P2;
	ModelRenderer P3;
	ModelRenderer P4;
	ModelRenderer Grip;
	ModelRenderer SPointer;

	public ModelFolly() {
		textureWidth = 64;
		textureHeight = 64;

		SB1 = new ModelRenderer(this, 0, 0);
		SB1.addBox(-16F, -1F, 0F, 16, 1, 3);
		SB1.setRotationPoint(0F, 4F, -1.5F);
		SB1.setTextureSize(64, 32);
		SB1.mirror = true;
		setRotation(SB1, 0F, 0F, 0F);
		SB2 = new ModelRenderer(this, 0, 16);
		SB2.addBox(-16F, -1.5F, 0F, 16, 1, 1);
		SB2.setRotationPoint(0F, 4F, 1F);
		SB2.setTextureSize(64, 32);
		SB2.mirror = true;
		setRotation(SB2, 0F, 0F, 0F);
		SB3 = new ModelRenderer(this, 0, 18);
		SB3.addBox(-16F, -1.5F, 0F, 16, 1, 1);
		SB3.setRotationPoint(0F, 4F, -2F);
		SB3.setTextureSize(64, 32);
		SB3.mirror = true;
		setRotation(SB3, 0F, 0F, 0F);
		SB4 = new ModelRenderer(this, 0, 8);
		SB4.addBox(-16F, -4F, 0F, 16, 3, 1);
		SB4.setRotationPoint(0F, 4F, -2.5F);
		SB4.setTextureSize(64, 32);
		SB4.mirror = true;
		setRotation(SB4, 0F, 0F, 0F);
		SB5 = new ModelRenderer(this, 0, 12);
		SB5.addBox(-16F, -4F, 0F, 16, 3, 1);
		SB5.setRotationPoint(0F, 4F, 1.5F);
		SB5.setTextureSize(64, 32);
		SB5.mirror = true;
		setRotation(SB5, 0F, 0F, 0F);
		SB6 = new ModelRenderer(this, 0, 20);
		SB6.addBox(-16F, -4.5F, 0F, 16, 1, 1);
		SB6.setRotationPoint(0F, 4F, 1F);
		SB6.setTextureSize(64, 32);
		SB6.mirror = true;
		setRotation(SB6, 0F, 0F, 0F);
		SB7 = new ModelRenderer(this, 0, 22);
		SB7.addBox(-16F, -4.5F, 0F, 16, 1, 1);
		SB7.setRotationPoint(0F, 4F, -2F);
		SB7.setTextureSize(64, 32);
		SB7.mirror = true;
		setRotation(SB7, 0F, 0F, 0F);
		SB8 = new ModelRenderer(this, 0, 4);
		SB8.addBox(-16F, -5F, 0F, 16, 1, 3);
		SB8.setRotationPoint(0F, 4F, -1.5F);
		SB8.setTextureSize(64, 32);
		SB8.mirror = true;
		setRotation(SB8, 0F, 0F, 0F);
		SP = new ModelRenderer(this, 58, 0);
		SP.addBox(0F, -4F, 0F, 0, 3, 3);
		SP.setRotationPoint(-0.5F, 4F, -1.5F);
		SP.setTextureSize(64, 32);
		SP.mirror = true;
		setRotation(SP, 0F, 0F, 0F);
		B1 = new ModelRenderer(this, 0, 40);
		B1.addBox(0F, 0F, 0F, 5, 5, 3);
		B1.setRotationPoint(0F, -1F, -1.5F);
		B1.setTextureSize(64, 32);
		B1.mirror = true;
		setRotation(B1, 0F, 0F, 0F);
		B2 = new ModelRenderer(this, 0, 48);
		B2.addBox(0F, 0F, 0F, 5, 3, 5);
		B2.setRotationPoint(0F, 0F, -2.5F);
		B2.setTextureSize(64, 32);
		B2.mirror = true;
		setRotation(B2, 0F, 0F, 0F);
		B3 = new ModelRenderer(this, 0, 56);
		B3.addBox(0F, 0F, 0F, 5, 4, 4);
		B3.setRotationPoint(0F, -0.5F, -2F);
		B3.setTextureSize(64, 32);
		B3.mirror = true;
		setRotation(B3, 0F, 0F, 0F);
		SF1 = new ModelRenderer(this, 0, 24);
		SF1.addBox(-32F, -1.5F, 0F, 16, 1, 3);
		SF1.setRotationPoint(0F, 4F, -1.5F);
		SF1.setTextureSize(64, 32);
		SF1.mirror = true;
		setRotation(SF1, 0F, 0F, 0F);
		SF2 = new ModelRenderer(this, 0, 28);
		SF2.addBox(-32F, -4.5F, 0F, 16, 1, 3);
		SF2.setRotationPoint(0F, 4F, -1.5F);
		SF2.setTextureSize(64, 32);
		SF2.mirror = true;
		setRotation(SF2, 0F, 0F, 0F);
		SF3 = new ModelRenderer(this, 0, 32);
		SF3.addBox(-32F, -4F, 0F, 16, 3, 1);
		SF3.setRotationPoint(0F, 4F, -2F);
		SF3.setTextureSize(64, 32);
		SF3.mirror = true;
		setRotation(SF3, 0F, 0F, 0F);
		SF4 = new ModelRenderer(this, 0, 36);
		SF4.addBox(-32F, -4F, 0F, 16, 3, 1);
		SF4.setRotationPoint(0F, 4F, 1F);
		SF4.setTextureSize(64, 32);
		SF4.mirror = true;
		setRotation(SF4, 0F, 0F, 0F);
		P1 = new ModelRenderer(this, 58, 6);
		P1.addBox(0F, 0F, 0F, 1, 4, 2);
		P1.setRotationPoint(5F, 1F, -1F);
		P1.setTextureSize(64, 32);
		P1.mirror = true;
		setRotation(P1, 0F, 0F, 0F);
		P2 = new ModelRenderer(this, 52, 0);
		P2.addBox(-1F, -2F, 0F, 1, 2, 2);
		P2.setRotationPoint(6F, 1F, -1F);
		P2.setTextureSize(64, 32);
		P2.mirror = true;
		setRotation(P2, 0F, 0F, -0.5235988F);
		P3 = new ModelRenderer(this, 42, 0);
		P3.addBox(0F, 0F, 0F, 1, 1, 4);
		P3.setRotationPoint(4.5F, 1F, -2F);
		P3.setTextureSize(64, 32);
		P3.mirror = true;
		setRotation(P3, 0F, 0F, 0F);
		P4 = new ModelRenderer(this, 44, 5);
		P4.addBox(0F, 0F, 0F, 5, 1, 2);
		P4.setRotationPoint(0F, 4F, -1F);
		P4.setTextureSize(64, 32);
		P4.mirror = true;
		setRotation(P4, 0F, 0F, 0F);
		Grip = new ModelRenderer(this, 52, 8);
		Grip.addBox(0F, 0F, 0F, 2, 4, 1);
		Grip.setRotationPoint(2F, 5F, -0.5F);
		Grip.setTextureSize(64, 32);
		Grip.mirror = true;
		setRotation(Grip, 0F, 0F, -0.2617994F);
		SPointer = new ModelRenderer(this, 56, 13);
		SPointer.addBox(-32F, -0.5F, 0F, 3, 1, 1);
		SPointer.setRotationPoint(0F, 4F, -0.5F);
		SPointer.setTextureSize(64, 32);
		SPointer.mirror = true;
		setRotation(SPointer, 0F, 0F, 0F);
	}

	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		SB1.render(f5);
		SB2.render(f5);
		SB3.render(f5);
		SB4.render(f5);
		SB5.render(f5);
		SB6.render(f5);
		SB7.render(f5);
		SB8.render(f5);
		SP.render(f5);
		B1.render(f5);
		B2.render(f5);
		B3.render(f5);
		SF1.render(f5);
		SF2.render(f5);
		SF3.render(f5);
		SF4.render(f5);
		P1.render(f5);
		P2.render(f5);
		P3.render(f5);
		P4.render(f5);
		Grip.render(f5);
		SPointer.render(f5);
	}

	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final int state, final int timer) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		final float rotate = (float) (Math.PI * -70D / 180D);

		if (state == 1 || state == 2) {
			SB1.rotateAngleZ = rotate;
			SB2.rotateAngleZ = rotate;
			SB3.rotateAngleZ = rotate;
			SB4.rotateAngleZ = rotate;
			SB5.rotateAngleZ = rotate;
			SB6.rotateAngleZ = rotate;
			SB7.rotateAngleZ = rotate;
			SB8.rotateAngleZ = rotate;
			SP.rotateAngleZ = rotate;
			SPointer.rotateAngleZ = rotate;
			SF1.rotateAngleZ = rotate;
			SF2.rotateAngleZ = rotate;
			SF3.rotateAngleZ = rotate;
			SF4.rotateAngleZ = rotate;
		} else {
			SB1.rotateAngleZ = 0;
			SB2.rotateAngleZ = 0;
			SB3.rotateAngleZ = 0;
			SB4.rotateAngleZ = 0;
			SB5.rotateAngleZ = 0;
			SB6.rotateAngleZ = 0;
			SB7.rotateAngleZ = 0;
			SB8.rotateAngleZ = 0;
			SP.rotateAngleZ = 0;
			SPointer.rotateAngleZ = 0;
			SF1.rotateAngleZ = 0;
			SF2.rotateAngleZ = 0;
			SF3.rotateAngleZ = 0;
			SF4.rotateAngleZ = 0;
		}

		SB1.render(f5);
		SB2.render(f5);
		SB3.render(f5);
		SB4.render(f5);
		SB5.render(f5);
		SB6.render(f5);
		SB7.render(f5);
		SB8.render(f5);

		if (state == 2 || state == 3) {
			SP.render(f5);
		}

		B1.render(f5);
		B2.render(f5);
		B3.render(f5);
		SF1.render(f5);
		SF2.render(f5);
		SF3.render(f5);
		SF4.render(f5);
		P1.render(f5);
		P2.render(f5);
		P3.render(f5);
		P4.render(f5);
		Grip.render(f5);
		SPointer.render(f5);

		if (state == 3 && timer > -1) {
			GL11.glPushMatrix();
			GL11.glPushAttrib(GL11.GL_ALPHA_BITS);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GlStateManager.disableLighting();
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
			
			final Tessellator tessellator = Tessellator.getInstance();
			final BufferBuilder buf = tessellator.getBuffer();
			boolean red = timer == 0;

            buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
			
			buf.pos(-32F / 16F, 0 + 4F / 16F, 0).color(red ? 1.0F : 0.0F, red ? 0.0F : 1.0F, 0.0F, 1.0F).endVertex();
			buf.pos(-150, timer, 0).color(red ? 1.0F : 0.0F, red ? 0.0F : 1.0F, 0.0F, 1.0F).endVertex();

			buf.pos(-32F / 16F, 0 + 4F / 16F, 0).color(red ? 1.0F : 0.0F, red ? 0.0F : 1.0F, 0.0F, 1.0F).endVertex();
			buf.pos(-150, -timer, 0).color(red ? 1.0F : 0.0F, red ? 0.0F : 1.0F, 0.0F, 1.0F).endVertex();

			buf.pos(-32F / 16F, 0 + 4F / 16F, 0).color(red ? 1.0F : 0.0F, red ? 0.0F : 1.0F, 0.0F, 1.0F).endVertex();
			buf.pos(-150, 0, timer).color(red ? 1.0F : 0.0F, red ? 0.0F : 1.0F, 0.0F, 1.0F).endVertex();

			buf.pos(-32F / 16F, 0 + 4F / 16F, 0).color(red ? 1.0F : 0.0F, red ? 0.0F : 1.0F, 0.0F, 1.0F).endVertex();
			buf.pos(-150, 0, -timer).color(red ? 1.0F : 0.0F, red ? 0.0F : 1.0F, 0.0F, 1.0F).endVertex();
			
			tessellator.draw();

			GlStateManager.enableLighting();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
			GL11.glPopMatrix();
		}
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
