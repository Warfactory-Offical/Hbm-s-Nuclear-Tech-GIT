package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSatelliteReceiver extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
  
  public ModelSatelliteReceiver()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 12, 16, 12);
      Shape1.setRotationPoint(-6F, 8F, -6F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 10, 28);
      Shape2.addBox(3F, 9F, -8F, 8, 8, 2);
      Shape2.setRotationPoint(-3F, 6F, 0F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, -0.2617994F, -0.4363323F, 0F);
      Shape3 = new ModelRenderer(this, 0, 39);
      Shape3.addBox(3F, 7F, -10F, 8, 2, 3);
      Shape3.setRotationPoint(-3F, 6F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, -0.2617994F, -0.4363323F, 0F);
      Shape4 = new ModelRenderer(this, 0, 28);
      Shape4.addBox(1F, 9F, -10F, 2, 8, 3);
      Shape4.setRotationPoint(-3F, 6F, 0F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, -0.2617994F, -0.4363323F, 0F);
      Shape5 = new ModelRenderer(this, 0, 28);
      Shape5.addBox(11F, 9F, -10F, 2, 8, 3);
      Shape5.setRotationPoint(-3F, 6F, 0F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, -0.2617994F, -0.4363323F, 0F);
      Shape6 = new ModelRenderer(this, 0, 39);
      Shape6.addBox(3F, 17F, -10F, 8, 2, 3);
      Shape6.setRotationPoint(-3F, 6F, 0F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, -0.2617994F, -0.4363323F, 0F);
      Shape7 = new ModelRenderer(this, 0, 44);
      Shape7.addBox(6F, 12F, -11F, 2, 2, 3);
      Shape7.setRotationPoint(-3F, 6F, 0F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, -0.2617994F, -0.4363323F, 0F);
      Shape8 = new ModelRenderer(this, 0, 49);
      Shape8.addBox(6.5F, 12.5F, -14F, 1, 1, 3);
      Shape8.setRotationPoint(-3F, 6F, 0F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, -0.2617994F, -0.4363323F, 0F);
      Shape9 = new ModelRenderer(this, 0, 53);
      Shape9.addBox(6F, 12F, -16F, 2, 2, 2);
      Shape9.setRotationPoint(-3F, 6F, 0F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, -0.2617994F, -0.4363323F, 0F);
  }
  
  @Override
public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
  }
  
  private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void renderModel(final float f)
  {
	    Shape1.render(f);
	    Shape2.render(f);
	    Shape3.render(f);
	    Shape4.render(f);
	    Shape5.render(f);
	    Shape6.render(f);
	    Shape7.render(f);
	    Shape8.render(f);
	    Shape9.render(f);
  }
  
  @Override
public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
