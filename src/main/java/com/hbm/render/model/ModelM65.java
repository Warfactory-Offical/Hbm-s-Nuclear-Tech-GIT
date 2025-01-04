// Date: 10.08.2018 10:17:16
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import com.hbm.handler.ArmorUtil;
import net.minecraft.inventory.EntityEquipmentSlot;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class ModelM65 extends ModelBiped {
	// fields
	public ModelRenderer mask;
	public ModelRenderer filter;

	public ModelM65() {
		textureWidth = 32;
		textureHeight = 32;
		
		float yOffset = 0.5F;

		mask = new ModelRenderer(this, 0, 0);
		filter = new ModelRenderer(this, 0, 0);
		ModelRenderer maskHead = new ModelRenderer(this, 0, 0);
		maskHead.addBox(0F, 0F, 0F, 8, 8, 8);
		maskHead.setRotationPoint(-4F, -8F + yOffset, -4F);
		maskHead.setTextureSize(32, 32);
		maskHead.mirror = true;
		setRotation(maskHead, 0F, 0F, 0F);
		convertToChild(mask, maskHead);
		ModelRenderer nose = new ModelRenderer(this, 0, 16);
		nose.addBox(0F, 0F, 0F, 3, 3, 1);
		nose.setRotationPoint(-1.5F, -3.5F + yOffset, -5F);
		nose.setTextureSize(32, 32);
		nose.mirror = true;
		setRotation(nose, 0F, 0F, 0F);
		convertToChild(mask, nose);
		ModelRenderer outlet = new ModelRenderer(this, 0, 20);
		outlet.addBox(0F, -2F, 0F, 2, 2, 1);
		outlet.setRotationPoint(-1F, -3.5F + yOffset, -5F);
		outlet.setTextureSize(32, 32);
		outlet.mirror = true;
		setRotation(outlet, -0.4799655F, 0F, 0F);
		convertToChild(mask, outlet);
		ModelRenderer noseSlope = new ModelRenderer(this, 8, 16);
		noseSlope.addBox(0F, 0F, -2F, 3, 2, 2);
		noseSlope.setRotationPoint(-1.5F, -2F + yOffset, -4F);
		noseSlope.setTextureSize(32, 32);
		noseSlope.mirror = true;
		setRotation(noseSlope, 0.6108652F, 0F, 0F);
		convertToChild(mask, noseSlope);
		ModelRenderer eye1 = new ModelRenderer(this, 0, 23);
		eye1.addBox(0F, 0F, 0F, 3, 3, 0);
		eye1.setRotationPoint(-3.5F, -6F + yOffset, -4.2F);
		eye1.setTextureSize(32, 32);
		eye1.mirror = true;
		setRotation(eye1, 0F, 0F, 0F);
		convertToChild(mask, eye1);
		ModelRenderer eye2 = new ModelRenderer(this, 0, 26);
		eye2.addBox(0F, 0F, 0F, 3, 3, 0);
		eye2.setRotationPoint(0.5F, -6F + yOffset, -4.2F);
		eye2.setTextureSize(32, 32);
		eye2.mirror = true;
		setRotation(eye2, 0F, 0F, 0F);
		convertToChild(mask, eye2);
		ModelRenderer iForgot = new ModelRenderer(this, 6, 20);
		iForgot.addBox(0F, 0F, 0F, 2, 2, 1);
		iForgot.setRotationPoint(-1F, -3.2F + yOffset, -6F);
		iForgot.setTextureSize(32, 32);
		iForgot.mirror = true;
		setRotation(iForgot, 0F, 0F, 0F);
		convertToChild(mask, iForgot);
		ModelRenderer filterConnector = new ModelRenderer(this, 6, 23);
		filterConnector.addBox(0F, 0F, -3F, 2, 2, 1);
		filterConnector.setRotationPoint(-1F, -2F + yOffset, -4F);
		filterConnector.setTextureSize(32, 32);
		filterConnector.mirror = true;
		setRotation(filterConnector, 0.6108652F, 0F, 0F);
		convertToChild(filter, filterConnector);
		ModelRenderer filter1 = new ModelRenderer(this, 18, 21);
		filter1.addBox(0F, -1F, -5F, 3, 4, 2);
		filter1.setRotationPoint(-1.5F, -2F + yOffset, -4F);
		filter1.setTextureSize(32, 32);
		filter1.mirror = true;
		setRotation(filter1, 0.6108652F, 0F, 0F);
		convertToChild(filter, filter1);
		ModelRenderer filter2 = new ModelRenderer(this, 18, 16);
		filter2.addBox(0F, -0.5F, -5F, 4, 3, 2);
		filter2.setRotationPoint(-2F, -2F + yOffset, -4F);
		filter2.setTextureSize(32, 32);
		filter2.mirror = true;
		setRotation(filter2, 0.6108652F, 0F, 0F);
		convertToChild(filter, filter2);
	}

	@Override
	public void setRotationAngles(float f2, float f3, float f4, float f5, float f6, float f7, Entity entity) {
		
		if(entity instanceof EntityPlayer player) {
            this.isSneak = player.isSneaking();
		}
		super.setRotationAngles(f2, f3, f4, f5, f6, f7, entity);
		this.mask.rotationPointX = this.bipedHead.rotationPointX;
		this.mask.rotationPointY = this.bipedHead.rotationPointY;
		this.mask.rotateAngleY = this.bipedHead.rotateAngleY;
		this.mask.rotateAngleX = this.bipedHead.rotateAngleX;
		this.filter.rotationPointX = this.bipedHead.rotationPointX;
		this.filter.rotationPointY = this.bipedHead.rotationPointY;
		this.filter.rotateAngleY = this.bipedHead.rotateAngleY;
		this.filter.rotateAngleX = this.bipedHead.rotateAngleX;

		if(this.isSneak) {
            this.mask.rotationPointY = 3.73F;
            this.filter.rotationPointY = 3.73F;
        }
	}

	@Override
	public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
		
		if(this.isChild) {
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
			GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
			double d = 1D / 16D * 18D;
			GL11.glScaled(d, d, d);
			GL11.glScaled(1.01D, 1.01D, 1.01D);
			this.mask.render(par7);
			
			if(!(entity instanceof EntityLivingBase) || ArmorUtil.getGasMaskFilterRecursively(((EntityLivingBase)entity).getItemStackFromSlot(EntityEquipmentSlot.HEAD)) != null)
				this.filter.render(par7);
			
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			double d = 1D / 16D * 18D;
			GL11.glScaled(d, d, d);
			GL11.glScaled(1.01D, 1.01D, 1.01D);
			this.mask.render(par7);
			
			if(!(entity instanceof EntityLivingBase) || ArmorUtil.getGasMaskFilterRecursively(((EntityLivingBase)entity).getItemStackFromSlot(EntityEquipmentSlot.HEAD)) != null)
				this.filter.render(par7);
			
			GL11.glPopMatrix();
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild) {
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
