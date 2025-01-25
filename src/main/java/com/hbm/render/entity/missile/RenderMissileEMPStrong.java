package com.hbm.render.entity.missile;

import com.hbm.entity.missile.EntityMissileTier2;
import com.hbm.main.ResourceManager;
import com.hbm.render.RenderHelper;
import com.hbm.render.tileentity.RenderLaunchPadTier1;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

public class RenderMissileEMPStrong extends Render<EntityMissileTier2.EntityMissileEMPStrong> {
	
	public static final IRenderFactory<EntityMissileTier2.EntityMissileEMPStrong> FACTORY = (RenderManager man) -> {return new RenderMissileEMPStrong(man);};
	
	protected RenderMissileEMPStrong(RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(EntityMissileTier2.EntityMissileEMPStrong missile, double x, double y, double z, float entityYaw, float partialTicks) {
		GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        GlStateManager.enableLighting();
        double[] renderPos = RenderHelper.getRenderPosFromMissile(missile, partialTicks);
        x = renderPos[0];
        y = renderPos[1];
        z = renderPos[2];
        GL11.glTranslated(x, y, z);
        GL11.glScalef(RenderLaunchPadTier1.w_2, RenderLaunchPadTier1.h_2, RenderLaunchPadTier1.w_2);
        GL11.glRotatef(missile.prevRotationYaw + (missile.rotationYaw - missile.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(missile.prevRotationPitch + (missile.rotationPitch - missile.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        bindTexture(ResourceManager.missileStrong_EMP_tex);
        ResourceManager.missileStrong.renderAll();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMissileTier2.EntityMissileEMPStrong entity) {
		return ResourceManager.missileStrong_EMP_tex;
	}
}
