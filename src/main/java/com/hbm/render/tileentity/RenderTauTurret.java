package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.turret.TileEntityTurretTau;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderTauTurret extends TileEntitySpecialRenderer<TileEntityTurretTau> {

	@Override
	public void render(final TileEntityTurretTau te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(180, 0F, 1F, 0F);
		
		final double yaw = te.rotationYaw;
		final double pitch = -te.rotationPitch;
		
        
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		this.bindTexture(ResourceManager.turret_tau_tex);
        ResourceManager.turret_tau.renderPart("base");

        GlStateManager.shadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
        
        renderTileEntityAt2(te, x, y, z, partialTicks, yaw, pitch);
	}
	
	public void renderTileEntityAt2(final TileEntity tileEntity, final double x, final double y, final double z, final float f, final double yaw, final double pitch)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(180, 0F, 1F, 0F);

		GL11.glRotated(yaw + 180, 0F, -1F, 0F);

		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		this.bindTexture(ResourceManager.turret_tau_tex);
        ResourceManager.turret_tau.renderPart("rotor");

        GlStateManager.shadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
        
        renderTileEntityAt3(tileEntity, x, y, z, f, yaw, pitch);
    }
	
	public void renderTileEntityAt3(final TileEntity tileEntity, final double x, final double y, final double z, final float f, final double yaw, final double pitch)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 0.95D, z + 0.5D);
        GlStateManager.enableLighting();
        GL11.glDisable(GL11.GL_CULL_FACE);
		
		GL11.glRotated(yaw + 180, 0F, -1F, 0F);
		GL11.glRotated(pitch, 1F, 0F, 0F);
		GL11.glTranslated(0, -0.95D, 0);

		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		this.bindTexture(ResourceManager.turret_tau_tex);
        ResourceManager.turret_tau.renderPart("gun");

        GlStateManager.shadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
    }
}
