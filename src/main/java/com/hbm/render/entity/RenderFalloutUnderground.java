package com.hbm.render.entity;

import com.hbm.entity.effect.EntityFalloutUnderGround;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFalloutUnderground extends Render<EntityFalloutUnderGround> {

	public static final IRenderFactory<EntityFalloutUnderGround> FACTORY = (RenderManager man) -> {return new RenderFalloutUnderground(man);};
	
	protected RenderFalloutUnderground(final RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(final EntityFalloutUnderGround entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {}

	@Override
	public void doRenderShadowAndFire(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks) {}
	
	@Override
	protected ResourceLocation getEntityTexture(final EntityFalloutUnderGround entity) {
		return null;
	}

}
