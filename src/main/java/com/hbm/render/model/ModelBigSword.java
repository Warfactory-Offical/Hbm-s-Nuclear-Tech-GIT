package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBigSword extends ModelBase
{
  //fields
    ModelRenderer HandleBottom;
    ModelRenderer HandleGrip;
    ModelRenderer Handle1;
    ModelRenderer Handle2;
    ModelRenderer Blade;
    ModelRenderer SBladeTip;
  
  public ModelBigSword()
  {
    textureWidth = 128;
    textureHeight = 32;
    
      HandleBottom = new ModelRenderer(this, 1, 1);
      HandleBottom.addBox(0F, 0F, 0F, 1, 3, 3);
      HandleBottom.setRotationPoint(0F, 0F, 0F);
      HandleBottom.setTextureSize(64, 32);
      HandleBottom.mirror = true;
      setRotation(HandleBottom, -0.7853982F, 0F, 0F);
      HandleGrip = new ModelRenderer(this, 17, 1);
      HandleGrip.addBox(0F, 0F, 0F, 1, 5, 2);
      HandleGrip.setRotationPoint(0F, -4F, -1F);
      HandleGrip.setTextureSize(64, 32);
      HandleGrip.mirror = true;
      setRotation(HandleGrip, 0F, 0F, 0F);
      Handle1 = new ModelRenderer(this, 25, 1);
      Handle1.addBox(0F, -1F, 0F, 2, 1, 4);
      Handle1.setRotationPoint(-0.5F, -3F, 0F);
      Handle1.setTextureSize(64, 32);
      Handle1.mirror = true;
      setRotation(Handle1, 0.2617994F, 0F, 0F);
      Handle2 = new ModelRenderer(this, 41, 1);
      Handle2.addBox(0F, -1F, -4F, 2, 1, 4);
      Handle2.setRotationPoint(-0.5F, -3F, 0F);
      Handle2.setTextureSize(64, 32);
      Handle2.mirror = true;
      setRotation(Handle2, -0.2617994F, 0F, 0F);
      Blade = new ModelRenderer(this, 57, 1);
      Blade.addBox(0F, 0F, 0F, 3, 18, 1);
      Blade.setRotationPoint(0F, -22F, 1.5F);
      Blade.setTextureSize(64, 32);
      Blade.mirror = true;
      setRotation(Blade, 0F, 1.570796F, 0F);
      SBladeTip = new ModelRenderer(this, 2, 10);
      SBladeTip.addBox(0F, 0F, 0F, 1, 2, 2);
      SBladeTip.setRotationPoint(0F, -23.5F, 0F);
      SBladeTip.setTextureSize(64, 32);
      SBladeTip.mirror = true;
      setRotation(SBladeTip, -0.7853982F, 0F, 0F);
  }
  
  @Override
public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    HandleBottom.render(f5);
    HandleGrip.render(f5);
    Handle1.render(f5);
    Handle2.render(f5);
    Blade.render(f5);
    SBladeTip.render(f5);
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

}