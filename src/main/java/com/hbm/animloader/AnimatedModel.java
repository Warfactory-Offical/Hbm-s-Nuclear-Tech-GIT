package com.hbm.animloader;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.hbm.util.BobMathUtil;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.util.math.MathHelper;

public class AnimatedModel {

	public static FloatBuffer auxGLMatrix = GLAllocation.createDirectFloatBuffer(16);

	//Pointless...
	public AnimationController controller;

	public String name = "";

	public float[] transform;

	boolean hasGeometry = true;
	boolean hasTransform = false;

	public String geo_name = "";
	public AnimatedModel parent;
	public List<AnimatedModel> children = new ArrayList<AnimatedModel>();
	int callList;

	public AnimatedModel() {
	}

	public void renderAnimated(final long sysTime) {
		renderAnimated(sysTime, null);
	}
	
	public void renderAnimated(final long sysTime, final IAnimatedModelCallback c) {
		if(controller.activeAnim == AnimationWrapper.EMPTY) {
			render(c);
			return;
		}

		final AnimationWrapper activeAnim = controller.activeAnim;
		final int numKeyFrames = activeAnim.anim.numKeyFrames;
		int diff = (int) (sysTime - activeAnim.startTime);
		diff *= activeAnim.speedScale;
		if(diff > activeAnim.anim.length) {
			final int diff2 = diff % activeAnim.anim.length;
			switch(activeAnim.endResult.type) {
			case END:
				controller.activeAnim = AnimationWrapper.EMPTY;
				render(c);
				return;
			case REPEAT:
				activeAnim.startTime = sysTime - diff2;
				break;
			case REPEAT_REVERSE:
				activeAnim.startTime = sysTime - diff2;
				activeAnim.reverse = !activeAnim.reverse;
				break;
			case START_NEW:
				activeAnim.cloneStats(activeAnim.endResult.next);
				activeAnim.startTime = sysTime - diff2;
				break;
			case STAY:
				activeAnim.startTime = sysTime - activeAnim.anim.length;
				break;
			}
		}
		diff = (int) (sysTime - activeAnim.startTime);
		if(activeAnim.reverse)
			diff = activeAnim.anim.length - diff;
		diff *= activeAnim.speedScale;
		final float remappedTime = MathHelper.clamp(BobMathUtil.remap(diff, 0, activeAnim.anim.length, 0, numKeyFrames - 1), 0, numKeyFrames - 1);
		final float diffN = BobMathUtil.remap01_clamp(diff, 0, activeAnim.anim.length);
		final int index = (int) remappedTime;
		final int first = index;
		final int next;
		if(index < numKeyFrames - 1) {
			next = index + 1;
		} else {
			next = first;
		}
		
		renderWithIndex(fract(remappedTime), first, next, diffN, c);
		controller.activeAnim.prevFrame = first;
	}

	protected void renderWithIndex(final float inter, final int firstIndex, final int nextIndex, final float diffN, final IAnimatedModelCallback c) {
		GL11.glPushMatrix();
		boolean hidden = false;
		if(hasTransform) {
			final Transform[] transforms = controller.activeAnim.anim.objectTransforms.get(name);
			if(transforms != null) {
				hidden = transforms[firstIndex].hidden;
				transforms[firstIndex].interpolateAndApply(transforms[nextIndex], inter);
			} else {
				auxGLMatrix.put(transform);
				auxGLMatrix.rewind();
				GL11.glMultMatrix(auxGLMatrix);
			}
		}
		if(c != null)
			hidden |= c.onRender(controller.activeAnim.prevFrame, firstIndex, callList, diffN, name);
		if(hasGeometry && !hidden) {
			GL11.glCallList(callList);
		}
		if(c != null)
			c.postRender(controller.activeAnim.prevFrame, firstIndex, callList, diffN, name);
		for(final AnimatedModel m : children) {
			m.renderWithIndex(inter, firstIndex, nextIndex, diffN, c);
		}
		GL11.glPopMatrix();
	}

	public void render() {
		render(null);
	}
	
	public void render(final IAnimatedModelCallback c) {
		GL11.glPushMatrix();
		if(hasTransform) {
			auxGLMatrix.put(transform);
			auxGLMatrix.rewind();
			GL11.glMultMatrix(auxGLMatrix);
		}
		boolean hidden = false;
		if(c != null)
			hidden = c.onRender(-1, -1, callList, -1, name);
		if(hasGeometry && !hidden) {
			GL11.glCallList(callList);
		}
		if(c != null)
			c.postRender(-1, -1, callList, -1, name);
		for(final AnimatedModel m : children) {
			m.render(c);
		}
		GL11.glPopMatrix();
	}

	private static float fract(final float number) {
		return (float) (number - Math.floor(number));
	}
	
	public static interface IAnimatedModelCallback {
		//(prevFrame, currentFrame, model, diffN, modelName)
		public boolean onRender(int prevFrame, int currentFrame, int model, float diffN, String modelName);
		public default void postRender(final int prevFrame, final int currentFrame, final int model, final float diffN, final String modelName){}
    }
}
