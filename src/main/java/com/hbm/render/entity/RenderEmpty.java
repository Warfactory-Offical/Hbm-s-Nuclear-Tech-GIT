package com.hbm.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEmpty extends Render<Entity> {

	public static final IRenderFactory<Entity> FACTORY = (RenderManager man) -> {return new RenderEmpty(man);};
	
	protected RenderEmpty(final RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(final Entity entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {}

	@Override
	public void doRenderShadowAndFire(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks) {}
	
	@Override
	protected ResourceLocation getEntityTexture(final Entity entity) {
		return null;
	}

}
