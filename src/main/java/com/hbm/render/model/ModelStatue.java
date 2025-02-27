package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelStatue extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
  
  public ModelStatue()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 16, 8, 16);
      Shape1.setRotationPoint(-8F, 16F, -8F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 24);
      Shape2.addBox(0F, 0F, 0F, 4, 12, 4);
      Shape2.setRotationPoint(-4F, 4F, -2F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 16, 24);
      Shape3.addBox(0F, 0F, 0F, 4, 12, 4);
      Shape3.setRotationPoint(0F, 4F, -2F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 32, 40);
      Shape4.addBox(0F, 0F, 0F, 8, 12, 4);
      Shape4.setRotationPoint(-4F, -8F, -2F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 40);
      Shape6.addBox(0F, 0F, -2F, 4, 8, 4);
      Shape6.setRotationPoint(4F, -8F, 0F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0.5235988F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 16, 40);
      Shape7.addBox(-4F, 0F, -2F, 4, 8, 4);
      Shape7.setRotationPoint(-4F, -8F, 0F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, -0.0872665F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 52);
      Shape8.addBox(-2F, 0F, -2F, 4, 8, 4);
      Shape8.setRotationPoint(6F, -2F, 3F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 1.22173F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 16, 52);
      Shape9.addBox(0F, 0F, -2F, 4, 8, 4);
      Shape9.setRotationPoint(-8F, -1F, -0.5F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0.2617994F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 32, 24);
      Shape10.addBox(-4F, -8F, -4F, 8, 8, 8);
      Shape10.setRotationPoint(0F, -8F, 0F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, -0.1745329F, 0F, 0F);
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
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
  }
  
  private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

  public void renderModel(final float f) {
	    Shape1.render(f);
	    Shape2.render(f);
	    Shape3.render(f);
	    Shape4.render(f);
	    Shape6.render(f);
	    Shape7.render(f);
	    Shape8.render(f);
	    Shape9.render(f);
	    Shape10.render(f);
  }

}
