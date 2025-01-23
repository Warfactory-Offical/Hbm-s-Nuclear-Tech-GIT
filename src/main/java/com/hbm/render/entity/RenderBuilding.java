package com.hbm.render.entity;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.projectile.EntityBuilding;
import com.hbm.main.ResourceManager;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBuilding extends Render<EntityBuilding> {

	public static final IRenderFactory<EntityBuilding> FACTORY = (RenderManager man) -> {return new RenderBuilding(man);};
	
	protected RenderBuilding(final RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(final EntityBuilding entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GlStateManager.disableCull();
        
        bindTexture(ResourceManager.building_tex);
        ResourceManager.building.renderAll();
        
        GlStateManager.enableCull();
        GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(final EntityBuilding entity) {
		return ResourceManager.building_tex;
	}

}
