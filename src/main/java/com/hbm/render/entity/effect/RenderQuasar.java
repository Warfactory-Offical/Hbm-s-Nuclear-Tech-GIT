package com.hbm.render.entity.effect;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.effect.EntityBlackHole;
import com.hbm.entity.effect.EntityQuasar;
import com.hbm.lib.RefStrings;
import com.hbm.main.ClientProxy;
import com.hbm.render.entity.RenderBlackHole;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderQuasar extends RenderBlackHole {

	public static final IRenderFactory<EntityQuasar> FACTORY = man -> new RenderQuasar(man);
	
	protected ResourceLocation quasar = new ResourceLocation(RefStrings.MODID, "textures/entity/bholeD.png");
	
	public RenderQuasar(final RenderManager renderManager){
		super(renderManager);
	}

	@Override
	public void doRender(final EntityBlackHole entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks){
		if(!ClientProxy.renderingConstant)
			return;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GlStateManager.disableLighting();
		GlStateManager.disableCull();

		final float size = entity.getDataManager().get(EntityBlackHole.SIZE);

		GL11.glScalef(size, size, size);

		bindTexture(hole);
		blastModel.renderAll();
		
		renderDisc(entity, partialTicks);
		renderJets(entity, partialTicks);

		GlStateManager.enableCull();
		GlStateManager.enableLighting();

		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation discTex() {
		return this.quasar;
	}

	@Override
	protected void setColorFromIteration(final int iteration, final float alpha, final float[] col) {
		final float r = 1.0F;
		final float g = (float) Math.pow(iteration / 15F, 2);
		final float b = (float) Math.pow(iteration / 15F, 2);
		
		col[0] = r;
		col[1] = g;
		col[2] = b;
		col[3] = alpha;
	}

	@Override
	protected int steps() {
		return 15;
	}

	@Override
	protected ResourceLocation getEntityTexture(final EntityBlackHole entity){
		return super.getEntityTexture(entity);
	}
}
