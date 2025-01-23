package com.hbm.render.util;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.hbm.render.amlfrom1710.Tessellator;
import net.minecraft.util.math.MathHelper;
import com.hbm.render.amlfrom1710.Vec3;

public class BeamPronter {

	public static enum EnumWaveType {
		RANDOM, SPIRAL
	}

	public static enum EnumBeamType {
		SOLID, LINE
	}
	
	private static boolean depthMask = false;
	public static void prontBeamwithDepth(final Vec3 skeleton, final EnumWaveType wave, final EnumBeamType beam, final int outerColor, final int innerColor, final int start, final int segments, final float size, final int layers, final float thickness) {
		depthMask = true;
		prontBeam(skeleton, wave, beam, outerColor, innerColor, start, segments, size, layers, thickness);
		depthMask = false;
	}

	public static void prontBeam(final Vec3 skeleton, final EnumWaveType wave, final EnumBeamType beam, final int outerColor, final int innerColor, final int start, final int segments, final float size, final int layers, final float thickness) {

		GL11.glPushMatrix();
		GL11.glDepthMask(depthMask);

		final float sYaw = (float) (Math.atan2(skeleton.xCoord, skeleton.zCoord) * 180F / Math.PI);
		final float sqrt = MathHelper.sqrt(skeleton.xCoord * skeleton.xCoord + skeleton.zCoord * skeleton.zCoord);
		final float sPitch = (float) (Math.atan2(skeleton.yCoord, sqrt) * 180F / Math.PI);

		GL11.glRotatef(180, 0, 1F, 0);
		GL11.glRotatef(sYaw, 0, 1F, 0);
		GL11.glRotatef(sPitch - 90, 1F, 0, 0);

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);

		if(beam == EnumBeamType.SOLID) {
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		}

		final Tessellator tessellator = Tessellator.instance;

		final Vec3 unit = Vec3.createVectorHelper(0, 1, 0);
		final Random rand = new Random(start);
		final double length = skeleton.length();
		final double segLength = length / segments;
		double lastX = 0;
		double lastY = 0;
		double lastZ = 0;

		for(int i = 0; i <= segments; i++) {

			final Vec3 spinner = Vec3.createVectorHelper(size, 0, 0);

			if(wave == EnumWaveType.SPIRAL) {
				spinner.rotateAroundY((float) Math.PI * (float) start / 180F);
				spinner.rotateAroundY((float) Math.PI * 45F / 180F * i);
			} else if(wave == EnumWaveType.RANDOM) {
				spinner.rotateAroundY((float) Math.PI * 2 * rand.nextFloat());
				spinner.rotateAroundY((float) Math.PI * 2 * rand.nextFloat());
			}

			final double pX = unit.xCoord * segLength * i + spinner.xCoord;
			final double pY = unit.yCoord * segLength * i + spinner.yCoord;
			final double pZ = unit.zCoord * segLength * i + spinner.zCoord;

			if(beam == EnumBeamType.LINE && i > 0) {

				tessellator.startDrawing(3);
				tessellator.setColorOpaque_I(outerColor);
				tessellator.addVertex(pX, pY, pZ);
				tessellator.addVertex(lastX, lastY, lastZ);
				tessellator.draw();
			}

			if(beam == EnumBeamType.SOLID && i > 0) {

				final float radius = thickness / layers;

				for(int j = 1; j <= layers; j++) {

					final float inter = (float) (j - 1) / (float) (layers - 1);

					final int r1 = ((outerColor & 0xFF0000) >> 16);
					final int g1 = ((outerColor & 0x00FF00) >> 8);
					final int b1 = ((outerColor & 0x0000FF) >> 0);
					
					final int r2 = ((innerColor & 0xFF0000) >> 16);
					final int g2 = ((innerColor & 0x00FF00) >> 8);
					final int b2 = ((innerColor & 0x0000FF) >> 0);

					final int r = ((int)(r1 + (r2 - r1) * inter)) << 16;
					final int g = ((int)(g1 + (g2 - g1) * inter)) << 8;
					final int b = ((int)(b1 + (b2 - b1) * inter)) << 0;
					
					final int color = r | g | b;

					tessellator.startDrawingQuads();
					tessellator.setColorOpaque_I(color);
					tessellator.addVertex(lastX + (radius * j), lastY, lastZ + (radius * j));
					tessellator.addVertex(lastX + (radius * j), lastY, lastZ - (radius * j));
					tessellator.addVertex(pX + (radius * j), pY, pZ - (radius * j));
					tessellator.addVertex(pX + (radius * j), pY, pZ + (radius * j));
					tessellator.draw();
					tessellator.startDrawingQuads();
					tessellator.setColorOpaque_I(color);
					tessellator.addVertex(lastX - (radius * j), lastY, lastZ + (radius * j));
					tessellator.addVertex(lastX - (radius * j), lastY, lastZ - (radius * j));
					tessellator.addVertex(pX - (radius * j), pY, pZ - (radius * j));
					tessellator.addVertex(pX - (radius * j), pY, pZ + (radius * j));
					tessellator.draw();
					tessellator.startDrawingQuads();
					tessellator.setColorOpaque_I(color);
					tessellator.addVertex(lastX + (radius * j), lastY, lastZ + (radius * j));
					tessellator.addVertex(lastX - (radius * j), lastY, lastZ + (radius * j));
					tessellator.addVertex(pX - (radius * j), pY, pZ + (radius * j));
					tessellator.addVertex(pX + (radius * j), pY, pZ + (radius * j));
					tessellator.draw();
					tessellator.startDrawingQuads();
					tessellator.setColorOpaque_I(color);
					tessellator.addVertex(lastX + (radius * j), lastY, lastZ - (radius * j));
					tessellator.addVertex(lastX - (radius * j), lastY, lastZ - (radius * j));
					tessellator.addVertex(pX - (radius * j), pY, pZ - (radius * j));
					tessellator.addVertex(pX + (radius * j), pY, pZ - (radius * j));
					tessellator.draw();
				}
			}

			lastX = pX;
			lastY = pY;
			lastZ = pZ;
		}

		if(beam == EnumBeamType.LINE) {

			tessellator.startDrawing(3);
			tessellator.setColorOpaque_I(innerColor);
			tessellator.addVertex(0, 0, 0);
			tessellator.addVertex(0, skeleton.length(), 0);
			tessellator.draw();
		}

		if(beam == EnumBeamType.SOLID) {
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		GL11.glDepthMask(true);

		GL11.glPopMatrix();
	}

}
