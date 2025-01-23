package com.hbm.render.entity.mob;

import com.hbm.entity.mob.EntityFBI;
import com.hbm.lib.RefStrings;
import com.hbm.render.model.ModelFBI;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFBI extends RenderBiped<EntityFBI> {

	public static final IRenderFactory<EntityFBI> FACTORY = man -> new RenderFBI(man);
	
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":" + "textures/entity/fbi.png");
	
	public RenderFBI(final RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelFBI(), 1.0F);
	}
	
	@Override
	public void doRender(final EntityFBI entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(final EntityFBI entity) {
		return texture;
	}

}
