package com.hbm.render.tileentity;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.RenderSparks;
import com.hbm.tileentity.machine.TileEntityCore;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderCore extends TileEntitySpecialRenderer<TileEntityCore> {
	
	@Override
	public boolean isGlobalRenderer(final TileEntityCore te) {
		return true;
	}
	
	@Override
	public void render(final TileEntityCore core, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		if(core.heat == 0) {
        	renderStandby(core, x, y, z);
		 } else {

        	GL11.glPushMatrix();
    		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
    		//GL11.glRotatef(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
    		//GL11.glRotatef(Minecraft.getMinecraft().getRenderManager().playerViewX - 90, 1.0F, 0.0F, 0.0F);
    		GL11.glTranslated(-0.5, -0.5, -0.5);

    		renderOrb(core, 0, 0, 0);
        	GL11.glPopMatrix();
        }
	}
	
	public void renderStandby(final TileEntityCore core, final double x, final double y, final double z) {

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        GlStateManager.disableLighting();
        GlStateManager.enableCull();
        GlStateManager.disableTexture2D();
        
        GL11.glScalef(0.25F, 0.25F, 0.25F);
        GlStateManager.color(0.1F, 0.1F, 0.1F);
        ResourceManager.sphere_uv.renderAll();
        
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
        GL11.glScalef(1.25F, 1.25F, 1.25F);
        GlStateManager.color(0.1F, 0.2F, 0.4F);
        ResourceManager.sphere_uv.renderAll();
		GlStateManager.disableBlend();

        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		
        if(core.getWorld().rand.nextInt(50) == 0) {
			for(int i = 0; i < 3; i++) {
				RenderSparks.renderSpark((int) System.currentTimeMillis() / 100 + i * 10000, 0, 0, 0, 1.5F, 5, 10, 0x00FFFF, 0xFFFFFF);
				RenderSparks.renderSpark((int) System.currentTimeMillis() / 50 + i * 10000, 0, 0, 0, 3F, 5, 10, 0x00FFFF, 0xFFFFFF);
			}
        }
		GlStateManager.color(1F, 1F, 1F);
        GL11.glPopMatrix();
    }
    
    public void renderOrb(final TileEntityCore core, final double x, final double y, final double z) {

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

        final int color = core.color;
        final float r = (color >> 16 & 255)/255F;
		final float g = (color >> 8 & 255)/255F;
		final float b = (color & 255)/255F;
		GlStateManager.color(r, g, b, 1.0F);
		
		final int tot = core.tanks[0].getCapacity() + core.tanks[1].getCapacity();
		final int fill = core.tanks[0].getFluidAmount() + core.tanks[1].getFluidAmount();
		
		final float scale = (float)Math.log(core.heat+1) * ((float)fill / (float)tot) + 0.5F;
		GL11.glScalef(scale, scale, scale);

        GlStateManager.enableCull();
        GlStateManager.disableLighting();
        GlStateManager.disableTexture2D();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		ResourceManager.sphere_ruv.renderAll();
		GL11.glScalef(2F, 2F, 2F);
		
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		
		for(int i = 6; i <= 10; i++) {

	        GL11.glPushMatrix();
			GL11.glScalef(i * 0.1F, i * 0.1F, i * 0.1F);
			ResourceManager.sphere_ruv.renderAll();
	        GL11.glPopMatrix();
		}
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
        GlStateManager.disableCull();
        GL11.glPopMatrix();
    }
}
