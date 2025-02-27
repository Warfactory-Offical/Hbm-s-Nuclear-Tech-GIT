package com.hbm.render.loader;

import org.lwjgl.opengl.GL11;

import com.hbm.render.amlfrom1710.IModelCustom;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelRendererObj {

	public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;
    public float offsetX;
    public float offsetY;
    public float offsetZ;

    String[] parts;
    IModelCustom model;

    public ModelRendererObj(final IModelCustom model, final String... parts) {
    	this.model = model;
    	this.parts = parts;
    }
    
    public ModelRendererObj setPosition(final float x, final float y, final float z) {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
        return this;
    }

    public ModelRendererObj setRotationPoint(final float x, final float y, final float z) {
        this.rotationPointX = x;
        this.rotationPointY = y;
        this.rotationPointZ = z;
        return this;
    }
    
    public void copyTo(final ModelRendererObj obj) {

    	obj.offsetX = offsetX;
    	obj.offsetY = offsetY;
    	obj.offsetZ = offsetZ;
    	obj.rotateAngleX = rotateAngleX;
    	obj.rotateAngleY = rotateAngleY;
    	obj.rotateAngleZ = rotateAngleZ;
    	obj.rotationPointX = rotationPointX;
    	obj.rotationPointY = rotationPointY;
    	obj.rotationPointZ = rotationPointZ;
    }

    @SideOnly(Side.CLIENT)
    public void render(final float scale) {
    	GL11.glPushMatrix();

        GL11.glTranslatef(this.offsetX * scale, this.offsetY * scale, this.offsetZ * scale);

        GL11.glTranslatef(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);

        if (this.rotateAngleZ != 0.0F)
        {
            GL11.glRotatef(this.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
        }

        if (this.rotateAngleY != 0.0F)
        {
            GL11.glRotatef(this.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        }

        if (this.rotateAngleX != 0.0F)
        {
            GL11.glRotatef(this.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
        }

        GL11.glTranslatef(-this.rotationPointX * scale, -this.rotationPointY * scale, -this.rotationPointZ * scale);

    	GL11.glScalef(scale, scale, scale);

    	if(parts != null && parts.length > 0)
    		for(final String part : parts)
    			model.renderPart(part);
    	else
    		model.renderAll();


    	GL11.glPopMatrix();

    }
}
