package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.tileentity.TileEntityKeypadBase;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderKeypadBase extends TileEntitySpecialRenderer<TileEntityKeypadBase> {

	@Override
	public void render(final TileEntityKeypadBase te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5, y, z+0.5);
		te.keypad.client().render();
		GL11.glPopMatrix();
	}
}
