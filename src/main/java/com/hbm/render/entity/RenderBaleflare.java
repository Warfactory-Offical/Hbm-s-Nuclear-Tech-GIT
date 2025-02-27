package com.hbm.render.entity;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.projectile.EntityBaleflare;
import com.hbm.lib.RefStrings;
import com.hbm.render.model.ModelBaleflare;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBaleflare extends Render<EntityBaleflare> {

	public static final IRenderFactory<EntityBaleflare> FACTORY = (RenderManager man) -> {return new RenderBaleflare(man);};
	
	private final ModelBaleflare miniNuke;
	private static final ResourceLocation bale_rl = new ResourceLocation(RefStrings.MODID + ":textures/models/projectiles/BaleFlare.png");
	
	protected RenderBaleflare(final RenderManager renderManager) {
		super(renderManager);
		miniNuke = new ModelBaleflare();
	}
	
	@Override
	public void doRender(final EntityBaleflare entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks + 180, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        
        bindTexture(bale_rl);
        miniNuke.renderAll(0.0625F);
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRenderShadowAndFire(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks) {}

	@Override
	protected ResourceLocation getEntityTexture(final EntityBaleflare entity) {
		return bale_rl;
	}

}
