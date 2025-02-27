package com.hbm.render.item.weapon;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.render.item.TEISRBase;
import com.hbm.render.model.ModelUboinik;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemRenderUboinik extends TEISRBase {

	protected ModelUboinik uboinik;
	protected static ResourceLocation uboinik_rl = new ResourceLocation(RefStrings.MODID +":textures/models/weapons/ModelUboinik.png");
	
	public ItemRenderUboinik() {
		uboinik = new ModelUboinik();
	}
	
	@Override
	public void renderByItem(final ItemStack itemStackIn) {
		Minecraft.getMinecraft().renderEngine.bindTexture(uboinik_rl);
		GlStateManager.enableCull();
		switch(type){
		case FIRST_PERSON_LEFT_HAND:
			GL11.glTranslated(-0.1, -0.05, 0);
		case FIRST_PERSON_RIGHT_HAND:
			GL11.glScaled(0.55, 0.55, 0.55);
			GL11.glTranslated(0.9, 1.2, 1.3);
			if(type == TransformType.FIRST_PERSON_RIGHT_HAND){
				//GL11.glRotated(10, 0, 1, 0);
				GL11.glRotated(-25, 0, 0, 1);
				GL11.glRotated(180, 1, 0, 0);
			} else {
				GL11.glRotated(180, 0, 1, 0);
				GL11.glRotated(180, 1, 0, 0);
				GL11.glRotated(30, 0, 0, 1);
			}
			
			uboinik.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, itemStackIn);
			break;
		case THIRD_PERSON_LEFT_HAND:
		case THIRD_PERSON_RIGHT_HAND:
		case HEAD:
		case FIXED:
		case GROUND:
			GL11.glScaled(0.75, 0.75, 0.75);
			if(type == TransformType.THIRD_PERSON_LEFT_HAND)
				GL11.glTranslated(0.3, 0, 0);
			GL11.glTranslated(0.5, 0.5, -0.1);
			GL11.glRotated(-90, 0, 1, 0);
			GL11.glRotated(180, 1, 0, 0);
			if(type == TransformType.GROUND || type == TransformType.FIXED)
				GL11.glTranslated(0, 0, 0.18);
			uboinik.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, itemStackIn);
			break;
		default:
			break;
		}
	}
}
