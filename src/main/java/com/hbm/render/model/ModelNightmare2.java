package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import com.hbm.items.weapon.ItemGunBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ModelNightmare2 extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Bullet1;
	ModelRenderer Bullet2;
	ModelRenderer Bullet3;
	ModelRenderer Bullet4;
	ModelRenderer Bullet5;
	ModelRenderer Bullet6;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;
	ModelRenderer Shape16;
	ModelRenderer Shape17;
	ModelRenderer Shape18;
	ModelRenderer Shape19;

	public ModelNightmare2() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 3, 8, 2);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, -0.3490659F);
		Shape2 = new ModelRenderer(this, 42, 0);
		Shape2.addBox(0F, 0F, 0F, 9, 6, 2);
		Shape2.setRotationPoint(-8F, -5F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 14);
		Shape3.addBox(0F, 0F, 0F, 4, 2, 1);
		Shape3.setRotationPoint(-0.03333334F, -3F, 0.5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0.715585F);
		Shape4 = new ModelRenderer(this, 22, 0);
		Shape4.addBox(0F, 0F, 0F, 6, 4, 3);
		Shape4.setRotationPoint(-7F, -4F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 34, 8);
		Shape6.addBox(0F, 0F, 0F, 13, 2, 2);
		Shape6.setRotationPoint(-21F, -4F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 17);
		Shape7.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape7.setRotationPoint(2F, -3F, 0.5F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0.715585F);
		Shape8 = new ModelRenderer(this, 4, 17);
		Shape8.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape8.setRotationPoint(2F, -4F, 0.5F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0.715585F);
		Shape9 = new ModelRenderer(this, 0, 20);
		Shape9.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape9.setRotationPoint(-14F, -2F, 0.5F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 26, 8);
		Shape10.addBox(0F, 0F, 0F, 2, 2, 2);
		Shape10.setRotationPoint(-19F, -5F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0.6108652F);
		Shape11 = new ModelRenderer(this, 0, 10);
		Shape11.addBox(0F, 0F, 0F, 4, 3, 1);
		Shape11.setRotationPoint(-2F, 1F, 0.5F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 10, 0);
		Shape12.addBox(0F, 0F, 0F, 1, 3, 1);
		Shape12.setRotationPoint(0F, 0F, 0.5F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0.5235988F);
		Bullet1 = new ModelRenderer(this, 6, 22);
		Bullet1.addBox(0F, 0F, 0F, 1, 2, 1);
		Bullet1.setRotationPoint(-7.5F, -4F, -1F);
		Bullet1.setTextureSize(64, 32);
		Bullet1.mirror = true;
		setRotation(Bullet1, 0F, 0F, 0F);
		Bullet2 = new ModelRenderer(this, 0, 22);
		Bullet2.addBox(0F, 0F, 0F, 1, 2, 1);
		Bullet2.setRotationPoint(-6.5F, -4F, -1F);
		Bullet2.setTextureSize(64, 32);
		Bullet2.mirror = true;
		setRotation(Bullet2, 0F, 0F, 0F);
		Bullet3 = new ModelRenderer(this, 6, 22);
		Bullet3.addBox(0F, 0F, 0F, 1, 2, 1);
		Bullet3.setRotationPoint(-4.5F, -4F, -1F);
		Bullet3.setTextureSize(64, 32);
		Bullet3.mirror = true;
		setRotation(Bullet3, 0F, 0F, 0F);
		Bullet4 = new ModelRenderer(this, 0, 22);
		Bullet4.addBox(0F, 0F, 0F, 1, 2, 1);
		Bullet4.setRotationPoint(-3.5F, -4F, -1F);
		Bullet4.setTextureSize(64, 32);
		Bullet4.mirror = true;
		setRotation(Bullet4, 0F, 0F, 0F);
		Bullet5 = new ModelRenderer(this, 6, 22);
		Bullet5.addBox(0F, 0F, 0F, 1, 2, 1);
		Bullet5.setRotationPoint(-1.5F, -4F, -1F);
		Bullet5.setTextureSize(64, 32);
		Bullet5.mirror = true;
		setRotation(Bullet5, 0F, 0F, 0F);
		Bullet6 = new ModelRenderer(this, 0, 22);
		Bullet6.addBox(0F, 0F, 0F, 1, 2, 1);
		Bullet6.setRotationPoint(-0.5F, -4F, -1F);
		Bullet6.setTextureSize(64, 32);
		Bullet6.mirror = true;
		setRotation(Bullet6, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 14, 0);
		Shape13.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape13.setRotationPoint(-13F, -3.5F, -2F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 34, 12);
		Shape14.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape14.setRotationPoint(-19F, -3.5F, -1.533333F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 10, 4);
		Shape15.addBox(0F, 0F, 0F, 1, 5, 1);
		Shape15.setRotationPoint(-4F, -5F, 3F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 10, 10);
		Shape16.addBox(-1F, -4F, 0F, 1, 4, 1);
		Shape16.setRotationPoint(-3F, -5F, 3F);
		Shape16.setTextureSize(64, 32);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, -0.5235988F);
		Shape17 = new ModelRenderer(this, 14, 8);
		Shape17.addBox(0F, 0F, 0F, 5, 1, 1);
		Shape17.setRotationPoint(-10F, -8.5F, 3F);
		Shape17.setTextureSize(64, 32);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);
		Shape18 = new ModelRenderer(this, 14, 12);
		Shape18.addBox(-9F, 0F, 0F, 9, 1, 1);
		Shape18.setRotationPoint(-10F, -8.5F, 3F);
		Shape18.setTextureSize(64, 32);
		Shape18.mirror = true;
		setRotation(Shape18, 0F, 0F, -0.6108652F);
		Shape19 = new ModelRenderer(this, 14, 10);
		Shape19.addBox(-5F, 0F, -1F, 5, 1, 1);
		Shape19.setRotationPoint(-16F, -3.5F, 4F);
		Shape19.setTextureSize(64, 32);
		Shape19.mirror = true;
		setRotation(Shape19, 0F, -0.4461433F, 0F);
	}

	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final ItemStack item) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GlStateManager.disableCull();
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		GlStateManager.enableCull();
		
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
		Shape18.render(f5);
		Shape19.render(f5);
		
		final int ammo = ItemGunBase.getMag(item);
		
		if(ammo > 0)
			Bullet1.render(f5);
		if(ammo > 1)
			Bullet2.render(f5);
		if(ammo > 2)
			Bullet3.render(f5);
		if(ammo > 3)
			Bullet4.render(f5);
		if(ammo > 4)
			Bullet5.render(f5);
		if(ammo > 5)
			Bullet6.render(f5);
		GL11.glPushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();

        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buf = tessellator.getBuffer();

        buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        buf.pos(-19F / 16F, -3F / 16F, -1F / 16F).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex();
        buf.pos(-150, 0, 0).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex();
        tessellator.draw();
        
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
		GL11.glPopMatrix();
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
