package com.hbm.render.entity;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.effect.EntityEMPBlast;
import com.hbm.lib.RefStrings;
import com.hbm.render.amlfrom1710.AdvancedModelLoader;
import com.hbm.render.amlfrom1710.IModelCustom;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEMPBlast extends Render<EntityEMPBlast> {

	public static final IRenderFactory<EntityEMPBlast> FACTORY = (RenderManager man) -> {return new RenderEMPBlast(man);};
	
	private static final ResourceLocation ringModelRL = new ResourceLocation(/*"/assets/" + */RefStrings.MODID, "models/Ring.obj");
	private final IModelCustom ringModel;
    private final ResourceLocation ringTexture;
	
	protected RenderEMPBlast(final RenderManager renderManager) {
		super(renderManager);
		ringModel = AdvancedModelLoader.loadModel(ringModelRL);
    	ringTexture = new ResourceLocation(RefStrings.MODID, "textures/models/explosion/EMPBlast.png");
	}
	
	@Override
	public void doRender(final EntityEMPBlast entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		GL11.glTranslated(x, y, z);
        GlStateManager.disableLighting();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glScalef(entity.scale+partialTicks, 1F, entity.scale+partialTicks);
        
        bindTexture(ringTexture);
        ringModel.renderAll();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(final EntityEMPBlast entity) {
		return ringTexture;
	}

}
