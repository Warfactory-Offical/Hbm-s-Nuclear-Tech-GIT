package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModelT45Helmet extends ModelBiped {
	// fields
		ModelRenderer helmet;
		ModelRenderer Shape1;
		ModelRenderer Shape2;
		ModelRenderer Shape3;
		ModelRenderer Shape4;
		ModelRenderer Shape5;
		ModelRenderer Shape6;
		ModelRenderer Shape7;
		ModelRenderer Shape8;

		public ModelT45Helmet() {
			textureWidth = 64;
			textureHeight = 32;

			helmet = new ModelRenderer(this, 0, 0);
			Shape1 = new ModelRenderer(this, 0, 0);
			Shape1.addBox(0F, 0F, 0F, 8, 8, 8);
			Shape1.setRotationPoint(-4F, 0F - 8 + 0.0625F / 2, -4F);
			Shape1.setTextureSize(64, 32);
			Shape1.mirror = true;
			setRotation(Shape1, 0F, 0F, 0F);
			convertToChild(helmet, Shape1);
			Shape2 = new ModelRenderer(this, 32, 0);
			Shape2.addBox(0F, 0F, 0F, 2, 2, 1);
			Shape2.setRotationPoint(1F, 1F - 8 + 0.0625F / 2 + 1, -5F);
			Shape2.setTextureSize(64, 32);
			Shape2.mirror = true;
			setRotation(Shape2, 0F, 0F, 0F);
			convertToChild(helmet, Shape2);
			Shape3 = new ModelRenderer(this, 40, 6);
			Shape3.addBox(0F, 0F, 0F, 1, 1, 4);
			Shape3.setRotationPoint(-5F, 1F - 8 + 0.0625F / 2, -5.466667F);
			Shape3.setTextureSize(64, 32);
			Shape3.mirror = true;
			setRotation(Shape3, 0F, 0F, 0F);
			convertToChild(helmet, Shape3);
			Shape4 = new ModelRenderer(this, 40, 0);
			Shape4.addBox(0F, 0F, 0F, 4, 2, 2);
			Shape4.setRotationPoint(-2F, 5F - 8 + 0.0625F / 2, -4F);
			Shape4.setTextureSize(64, 32);
			Shape4.mirror = true;
			setRotation(Shape4, -0.7853982F, 0F, 0F);
			convertToChild(helmet, Shape4);
			Shape5 = new ModelRenderer(this, 54, 0);
			Shape5.addBox(0F, 2F, 0F, 2, 1, 2);
			Shape5.setRotationPoint(-1F, 5F - 8 + 0.0625F / 2, -4F);
			Shape5.setTextureSize(64, 32);
			Shape5.mirror = true;
			setRotation(Shape5, -0.7853982F, 0F, 0F);
			convertToChild(helmet, Shape5);
			Shape6 = new ModelRenderer(this, 0, 16);
			Shape6.addBox(0F, 0F, 0F, 10, 1, 9);
			Shape6.setRotationPoint(-5F, 6F - 8 + 0.0625F / 2, -4.5F);
			Shape6.setTextureSize(64, 32);
			Shape6.mirror = true;
			setRotation(Shape6, 0F, 0F, 0F);
			convertToChild(helmet, Shape6);
			Shape7 = new ModelRenderer(this, 32, 7);
			Shape7.addBox(0F, 0F, 0F, 1, 1, 1);
			Shape7.setRotationPoint(-1.5F, 5F - 8 + 0.0625F / 2, -4.5F);
			Shape7.setTextureSize(64, 32);
			Shape7.mirror = true;
			setRotation(Shape7, -0.7853982F, 0F, 0F);
			convertToChild(helmet, Shape7);
			Shape8 = new ModelRenderer(this, 32, 5);
			Shape8.addBox(0F, 0F, 0F, 1, 1, 1);
			Shape8.setRotationPoint(0.5F, 5F - 8 + 0.0625F / 2, -4.5F);
			Shape8.setTextureSize(64, 32);
			Shape8.mirror = true;
			setRotation(Shape8, -0.7853982F, 0F, 0F);
			convertToChild(helmet, Shape8);
		}

		/*
		 * public void render(Entity entity, float f, float f1, float f2, float f3,
		 * float f4, float f5) { super.render(entity, f, f1, f2, f3, f4, f5);
		 * setRotationAngles(f, f1, f2, f3, f4, f5); Shape1.render(f5);
		 * Shape2.render(f5); Shape3.render(f5); Shape4.render(f5);
		 * Shape5.render(f5); Shape6.render(f5); Shape7.render(f5);
		 * Shape8.render(f5); }
		 */

		private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
			model.rotateAngleX = x;
			model.rotateAngleY = y;
			model.rotateAngleZ = z;
		}

		@Override
		public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {

			if (entity instanceof EntityPlayer player) {
                this.isSneak = player.isSneaking();
			}

			super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
			this.helmet.rotationPointX = this.bipedHead.rotationPointX;
			this.helmet.rotationPointY = this.bipedHead.rotationPointY;
			this.helmet.rotateAngleY = this.bipedHead.rotateAngleY;
			this.helmet.rotateAngleX = this.bipedHead.rotateAngleX;

			if (this.isSneak) {
	            this.helmet.rotationPointY = 3.7F;
	        }
		}

		@Override
		public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
			setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
			GL11.glPushMatrix();
			GL11.glScalef(1.13F, 1.13F, 1.13F);
			GL11.glScalef(1.0625F, 1.0625F, 1.0625F);
			if(this.isChild) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
			}
			this.helmet.render(par7);
			GL11.glPopMatrix();
		}
		
		protected void convertToChild(final ModelRenderer parParent, final ModelRenderer parChild) {
			// move child rotation point to be relative to parent
			parChild.rotationPointX -= parParent.rotationPointX;
			parChild.rotationPointY -= parParent.rotationPointY;
			parChild.rotationPointZ -= parParent.rotationPointZ;
			// make rotations relative to parent
			parChild.rotateAngleX -= parParent.rotateAngleX;
			parChild.rotateAngleY -= parParent.rotateAngleY;
			parChild.rotateAngleZ -= parParent.rotateAngleZ;
			// create relationship
			parParent.addChild(parChild);
		}
}
