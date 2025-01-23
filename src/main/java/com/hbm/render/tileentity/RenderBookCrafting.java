package com.hbm.render.tileentity;

import com.hbm.tileentity.machine.TileEntityBlackBook;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderBookCrafting extends TileEntitySpecialRenderer<TileEntityBlackBook> {

	@Override
	public boolean isGlobalRenderer(final TileEntityBlackBook te) {
		return true;
	}
	
	@Override
	public void render(final TileEntityBlackBook te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
	}
}
