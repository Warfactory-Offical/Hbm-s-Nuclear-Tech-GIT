package com.hbm.render.entity;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.missile.EntityBombletSelena;
import com.hbm.lib.RefStrings;
import com.hbm.render.amlfrom1710.AdvancedModelLoader;
import com.hbm.render.amlfrom1710.IModelCustom;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBombletSelena extends Render<EntityBombletSelena> {

	public static final IRenderFactory<EntityBombletSelena> FACTORY = (RenderManager man) -> {return new RenderBombletSelena(man);};
	private static final ResourceLocation objTesterModelRL = new ResourceLocation(/*"/assets/" + */RefStrings.MODID, "models/bombletSelena.obj");
	private final IModelCustom boyModel;
    private final ResourceLocation boyTexture;
	
	protected RenderBombletSelena(final RenderManager renderManager) {
		super(renderManager);
		boyModel = AdvancedModelLoader.loadModel(objTesterModelRL);
		boyTexture = new ResourceLocation(RefStrings.MODID, "textures/models/misc/universalDark.png");
	}
	
	@Override
	public void doRender(final EntityBombletSelena entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(2, 2, 2);
        
        bindTexture(boyTexture);
        boyModel.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(final EntityBombletSelena entity) {
		return boyTexture;
	}

}
