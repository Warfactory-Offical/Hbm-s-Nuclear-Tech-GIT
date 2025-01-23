package com.hbm.render.entity;

import com.hbm.entity.logic.EntityNukeExplosionMK5;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderNukeMK5 extends Render<EntityNukeExplosionMK5> {

	public static final IRenderFactory<EntityNukeExplosionMK5> FACTORY = (RenderManager man) -> {return new RenderNukeMK5(man);};
	
	protected RenderNukeMK5(final RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(final EntityNukeExplosionMK5 entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {}

	@Override
	public void doRenderShadowAndFire(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks) {}
	
	@Override
	protected ResourceLocation getEntityTexture(final EntityNukeExplosionMK5 entity) {
		return null;
	}

}
