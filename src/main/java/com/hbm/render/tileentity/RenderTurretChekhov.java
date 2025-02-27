package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.turret.TileEntityTurretChekhov;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;

public class RenderTurretChekhov extends RenderTurretBase<TileEntityTurretChekhov> {

	@Override
	public void render(final TileEntityTurretChekhov turret, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha){
		final Vec3d pos = turret.getHorizontalOffset();
		
		GL11.glPushMatrix();
		GL11.glTranslated(x + pos.x, y, z + pos.z);
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		
		this.renderConnectors(turret, true, false, null);

		bindTexture(ResourceManager.turret_base_tex);
		ResourceManager.turret_chekhov.renderPart("Base");
		final double yaw = -Math.toDegrees(turret.lastRotationYaw + (turret.rotationYaw - turret.lastRotationYaw) * partialTicks) - 90D;
		final double pitch = Math.toDegrees(turret.lastRotationPitch + (turret.rotationPitch - turret.lastRotationPitch) * partialTicks);
		
		GL11.glRotated(yaw, 0, 1, 0);
		bindTexture(ResourceManager.turret_carriage_tex);
		ResourceManager.turret_chekhov.renderPart("Carriage");
		
		GL11.glTranslated(0, 1.5, 0);
		GL11.glRotated(pitch, 0, 0, 1);
		GL11.glTranslated(0, -1.5, 0);
		bindTexture(ResourceManager.turret_chekhov_tex);
		ResourceManager.turret_chekhov.renderPart("Body");
		
		final float rot = turret.lastSpin + (turret.spin - turret.lastSpin) * partialTicks;
		
		GL11.glTranslated(0, 1.5, 0);
		GL11.glRotated(rot, -1, 0, 0);
		GL11.glTranslated(0, -1.5, 0);
		bindTexture(ResourceManager.turret_chekhov_barrels_tex);
		ResourceManager.turret_chekhov.renderPart("Barrels");

		GlStateManager.shadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
	}
}
