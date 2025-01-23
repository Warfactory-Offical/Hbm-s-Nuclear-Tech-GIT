package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.ModBlocks;
import com.hbm.render.RenderHelper;
import com.hbm.render.util.IconUtil;
import com.hbm.render.util.SmallBlockPronter;
import com.hbm.tileentity.machine.TileEntityITERStruct;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderITERMultiblock extends TileEntitySpecialRenderer<TileEntityITERStruct> {

	@Override
	public boolean isGlobalRenderer(final TileEntityITERStruct te) {
		return true;
	}

	@Override
	public void render(final TileEntityITERStruct te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();

		GL11.glTranslatef((float)x-1, (float)y-1, (float)z);

		GlStateManager.enableBlend();
		GlStateManager.disableLighting();
		GlStateManager.enableCull();
		GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
        GlStateManager.disableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(false);

        final TextureAtlasSprite magnet = RenderStructureMarker.fusion[0][1];
        final TextureAtlasSprite solenoid = RenderStructureMarker.fusion[4][1];
        final TextureAtlasSprite motor = RenderStructureMarker.fusion[3][0];
        final TextureAtlasSprite glass = IconUtil.getTextureFromBlock(ModBlocks.reinforced_glass);
        
        TextureAtlasSprite active = magnet;
        
        RenderHelper.bindBlockTexture();
        RenderHelper.startDrawingTexturedQuads();

        final int[][][] layout = TileEntityITERStruct.layout;

        for(int iy = -2; iy <= 2; iy ++) {

        	final int iny = 2 - Math.abs(iy);

	        for(int ix = 0; ix < layout[0].length; ix++) {

	            for(int iz = 0; iz < layout[0][0].length; iz++) {

	            	final int block = layout[iny][ix][iz];

	            	switch(block) {
	            	case 0: continue;
	            	case 1: active = magnet; break;
	            	case 2: active = solenoid; break;
	            	case 3: active = motor; break;
	            	case 4: active = glass; break;
	            	}

	            	SmallBlockPronter.renderSmolBlockAt(active, ix - 6F, iy + 3, iz - 7F);
	            }
	        }
        }

        RenderHelper.draw();
        
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.depthMask(true);

		GlStateManager.enableLighting();
		GL11.glPopMatrix();
	}
}
