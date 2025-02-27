package com.hbm.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPylon extends ModelBase {

	int textureX = 64;
	int textureY = 128;

	public ModelRenderer[] pylonModel;

	public ModelPylon()
	{
	    textureWidth = 64;
	    textureHeight = 128;
		pylonModel = new ModelRenderer[4];
		pylonModel[0] = new ModelRenderer(this, 0, 96); // Box 0
		pylonModel[1] = new ModelRenderer(this, 1, 1); // Box 1
		pylonModel[2] = new ModelRenderer(this, 24, 1); // Box 2
		pylonModel[3] = new ModelRenderer(this, 25, 17); // Box 3

		pylonModel[0].addBox(0F, 0F, 0F, 16, 16, 16, 0F); // Box 0
		pylonModel[0].setRotationPoint(-8F, -6F, -8F);

		pylonModel[1].addBox(0F, 0F, 0F, 4, 73, 4, 0F); // Box 1
		pylonModel[1].setRotationPoint(-2F, -79F, -2F);

		pylonModel[2].addBox(0F, 0F, 0F, 6, 4, 6, 0F); // Box 2
		pylonModel[2].setRotationPoint(-3F, -74F, -3F);

		pylonModel[3].addBox(0F, 0F, 0F, 6, 2, 6, 0F); // Box 3
		pylonModel[3].setRotationPoint(-3F, -78F, -3F);


		for(int i = 0; i < pylonModel.length; i++) {
			pylonModel[i].setTextureSize(textureX, textureY);
			pylonModel[i].mirror = true;
		}
	}

	@Override
	public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
	{
		for(int i = 0; i < 4; i++)
		{
			pylonModel[i].render(f5);
		}
	}

	public void renderAll(final float f5)
	{
		for(int i = 0; i < 4; i++)
		{
			pylonModel[i].render(f5);
		}
	}

	@Override
	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity)
	{
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
