package com.hbm.render.entity.missile;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.missile.EntityMissileDoomsday;
import com.hbm.main.ResourceManager;
import com.hbm.render.RenderHelper;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMissileDoomsday extends Render<EntityMissileDoomsday> {

	public static final IRenderFactory<EntityMissileDoomsday> FACTORY = (RenderManager man) -> {return new RenderMissileDoomsday(man);};
	
	protected RenderMissileDoomsday(final RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(final EntityMissileDoomsday missile, double x, double y, double z, final float entityYaw, final float partialTicks) {
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		GlStateManager.enableLighting();
		final double[] renderPos = RenderHelper.getRenderPosFromMissile(missile, partialTicks);
		x = renderPos[0];
		y = renderPos[1];
		z = renderPos[2];
		GL11.glTranslated(x, y, z);
        GL11.glRotatef(missile.prevRotationYaw + (missile.rotationYaw - missile.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(missile.prevRotationPitch + (missile.rotationPitch - missile.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(2F, 2F, 2F);

        GL11.glDisable(GL11.GL_CULL_FACE);
        bindTexture(ResourceManager.missileDoomsday_tex);
        ResourceManager.missileDoomsday.renderAll();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(final EntityMissileDoomsday entity) {
		return ResourceManager.missileDoomsday_tex;
	}

}
