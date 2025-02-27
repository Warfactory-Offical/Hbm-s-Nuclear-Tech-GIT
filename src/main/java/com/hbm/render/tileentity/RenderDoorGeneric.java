package com.hbm.render.tileentity;

import java.nio.DoubleBuffer;

import com.hbm.interfaces.IDoor;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import com.hbm.animloader.AnimatedModel;
import com.hbm.animloader.Animation;
import com.hbm.animloader.AnimationWrapper;
import com.hbm.animloader.AnimationWrapper.EndResult;
import com.hbm.animloader.AnimationWrapper.EndType;
import com.hbm.blocks.BlockDummyable;
import com.hbm.main.ResourceManager;
import com.hbm.render.WavefrontObjDisplayList;
import com.hbm.tileentity.DoorDecl;
import com.hbm.tileentity.TileEntityDoorGeneric;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.MathHelper;

public class RenderDoorGeneric extends TileEntitySpecialRenderer<TileEntityDoorGeneric> {

	private static DoubleBuffer buf = null;
	
	private static final float[] tran = new float[3];
	private static final float[] orig = new float[3];
	private static final float[] rot = new float[3];
	
	@Override
	public boolean isGlobalRenderer(final TileEntityDoorGeneric te){
		return true;
	}
	
	@Override
	public void render(final TileEntityDoorGeneric te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha){
		if(buf == null){
			buf = GLAllocation.createDirectByteBuffer(8*4).asDoubleBuffer();
		}
		final DoorDecl door = te.doorType;
		
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5, y, z+0.5);
		
		switch(te.getBlockMetadata() - BlockDummyable.offset) {
		case 2: GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 4: GL11.glRotatef(90+90, 0F, 1F, 0F); break;
		case 3: GL11.glRotatef(180+90, 0F, 1F, 0F); break;
		case 5: GL11.glRotatef(270+90, 0F, 1F, 0F); break;
		}
		door.doOffsetTransform();
		
		final double[][] clip = door.getClippingPlanes();
		for(int i = 0; i < clip.length; i ++){
			GL11.glEnable(GL11.GL_CLIP_PLANE0+i);
			buf.put(clip[i]);
			buf.rewind();
			GL11.glClipPlane(GL11.GL_CLIP_PLANE0+i, buf);
		}
		
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.enableLighting();
		
		final AnimatedModel animModel = door.getAnimatedModel();
		if(animModel != null){
			final Animation anim = door.getAnim();
			bindTexture(door.getTextureForPart(""));
			final long time = System.currentTimeMillis();
	        final long startTime = te.state.isMovingState() ? te.animStartTime : time;
	        final boolean reverse = te.state == IDoor.DoorState.OPEN || te.state == IDoor.DoorState.CLOSING;
			final AnimationWrapper w = new AnimationWrapper(startTime, anim).onEnd(new EndResult(EndType.STAY));
			if(reverse)
				w.reverse();
			animModel.controller.setAnim(w);
			animModel.renderAnimated(System.currentTimeMillis());
		} else {
			final WavefrontObjDisplayList model = door.getModel();
			
			final long ms = System.currentTimeMillis()-te.animStartTime;
			final float openTicks = MathHelper.clamp(te.state == IDoor.DoorState.CLOSING || te.state == IDoor.DoorState.CLOSED ? door.timeToOpen()* 50L -ms : ms, 0, door.timeToOpen()*50)*0.02F;
			for(final Pair<String, Integer> p : model.nameToCallList){
				if(!door.doesRender(p.getLeft(), false))
					continue;
				GL11.glPushMatrix();
				bindTexture(door.getTextureForPart(p.getLeft()));
				doPartTransform(door, p.getLeft(), openTicks, false);
				GL11.glCallList(p.getRight());
				for(final String name : door.getChildren(p.getLeft())){
					if(!door.doesRender(name, true))
						continue;
					GL11.glPushMatrix();
					bindTexture(door.getTextureForPart(name));
					doPartTransform(door, name, openTicks, true);
					model.renderPart(name);
					GL11.glPopMatrix();
				}
				GL11.glPopMatrix();
			}
		}
		
		for(int i = 0; i < clip.length; i ++){
			GL11.glDisable(GL11.GL_CLIP_PLANE0+i);
		}
		
		GlStateManager.disableBlend();
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
	}
	
	public void doPartTransform(final DoorDecl door, final String name, final float openTicks, final boolean child){
		door.getTranslation(name, openTicks, child, tran);
		door.getOrigin(name, orig);
		door.getRotation(name, openTicks, rot);
		GL11.glTranslated(orig[0], orig[1], orig[2]);
		if(rot[0] != 0)
			GL11.glRotated(rot[0], 1, 0, 0);
		if(rot[1] != 0)
			GL11.glRotated(rot[1], 0, 1, 0);
		if(rot[2] != 0)
			GL11.glRotated(rot[2], 0, 0, 1);
		GL11.glTranslated(-orig[0]+tran[0], -orig[1]+tran[1], -orig[2]+tran[2]);
	}
}
