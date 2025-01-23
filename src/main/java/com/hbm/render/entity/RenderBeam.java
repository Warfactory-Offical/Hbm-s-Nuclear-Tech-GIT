package com.hbm.render.entity;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.projectile.EntityPlasmaBeam;
import com.hbm.lib.RefStrings;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBeam extends Render<EntityPlasmaBeam> {

	public static final IRenderFactory<EntityPlasmaBeam> FACTORY = (RenderManager man) -> {return new RenderBeam(man);};
	
	protected ResourceLocation beam_rl = new ResourceLocation(RefStrings.MODID + ":textures/models/projectiles/PlasmaBeam.png");
	
	protected RenderBeam(final RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(final EntityPlasmaBeam rocket, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		final float radius = 0.12F;
		//float radius = 0.06F;
		final int distance = 4;
		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder buf = tessellator.getBuffer();

		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glTranslatef((float) x, (float) y, (float) z);

		GL11.glRotatef(rocket.rotationYaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-rocket.rotationPitch, 1.0F, 0.0F, 0.0F);

		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
		
		for (float o = 0; o <= radius; o += radius / 8) {
			float color = 1f - (o * 8.333f);
			if (color < 0)
				color = 0;
			buf.pos(0 + o, 0 - o, 0).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 + o, 0 + o, 0).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 + o, 0 + o, distance).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 + o, 0 - o, distance).color(color, 1.0F, color, 1.0F).endVertex();
			
			buf.pos(0 - o, 0 - o, 0).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 + o, 0 - o, 0).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 + o, 0 - o, distance).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 - o, 0 - o, distance).color(color, 1.0F, color, 1.0F).endVertex();
			
			buf.pos(0 - o, 0 + o, 0).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 - o, 0 - o, 0).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 - o, 0 - o, distance).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 - o, 0 + o, distance).color(color, 1.0F, color, 1.0F).endVertex();
			
			buf.pos(0 + o, 0 + o, 0).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 - o, 0 + o, 0).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 - o, 0 + o, distance).color(color, 1.0F, color, 1.0F).endVertex();
			buf.pos(0 + o, 0 + o, distance).color(color, 1.0F, color, 1.0F).endVertex();
		}
		tessellator.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRenderShadowAndFire(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks) {}

	@Override
	protected ResourceLocation getEntityTexture(final EntityPlasmaBeam entity) {
		return beam_rl;
	}

}
