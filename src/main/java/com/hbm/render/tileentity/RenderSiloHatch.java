package com.hbm.render.tileentity;

import com.hbm.interfaces.IDoor;
import org.lwjgl.opengl.GL11;

import com.hbm.animloader.AnimationWrapper;
import com.hbm.animloader.AnimationWrapper.EndResult;
import com.hbm.animloader.AnimationWrapper.EndType;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntitySiloHatch;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderSiloHatch extends TileEntitySpecialRenderer<TileEntitySiloHatch> {

	@Override
	public boolean isGlobalRenderer(final TileEntitySiloHatch te) {
		return true;
	}
	
	@Override
	public void render(final TileEntitySiloHatch te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x+0.5, y+0.595, z+0.5);
        switch(te.getBlockMetadata()-2) {
		case 0: GL11.glRotatef(270, 0F, 1F, 0F); break;
		case 1: GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 2: GL11.glRotatef(0, 0F, 1F, 0F); break;
		case 3: GL11.glRotatef(180, 0F, 1F, 0F); break;
		}
        GL11.glTranslated(3, 0, 0);
        GlStateManager.enableLighting();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		bindTexture(ResourceManager.hatch_tex);
		
		final long time = System.currentTimeMillis();
        final long startTime = te.state.isMovingState() ? te.sysTime : time;
        final boolean reverse = te.state == IDoor.DoorState.OPEN || te.state == IDoor.DoorState.CLOSING;
        final AnimationWrapper w = new AnimationWrapper(startTime, ResourceManager.silo_hatch_open);
        if(reverse){
        	w.reverse();
        }
        w.onEnd(new EndResult(EndType.STAY, null));
        bindTexture(ResourceManager.hatch_tex);
        ResourceManager.silo_hatch.controller.setAnim(w);
        ResourceManager.silo_hatch.renderAnimated(time);
        
		GlStateManager.shadeModel(GL11.GL_FLAT);
        GL11.glPopMatrix();
	}
}
