package com.hbm.render.misc;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.main.ClientProxy;
import com.hbm.main.ResourceManager;
import com.hbm.render.GLCompat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = RefStrings.MODID)
public class LensVisibilityHandler {

	static Map<Integer, LensSpikeInfo> lensSpikes = new HashMap<>();
	static int currentId = 0;
	public static int checkSphere = -1;
	
	public static int findId(){
		while(lensSpikes.containsKey(currentId)){
			currentId ++;
		}
		return currentId;
	}
	
	public static int generate(final FloatBuffer matrix){
		final int id = findId();
		final float[] mat = new float[16];
		matrix.get(mat);
		matrix.rewind();
		final LensSpikeInfo i = new LensSpikeInfo(mat);
		lensSpikes.put(id, i);
		return id;
	}
	
	public static void delete(final int id){
		final LensSpikeInfo i = lensSpikes.get(id);
		if(i != null){
			i.cleanup();
			lensSpikes.remove(id);
		}
	}
	
	public static float getVisibility(final int id){
		final LensSpikeInfo i = lensSpikes.get(id);
		if(i != null){
			return i.visibility;
		}
		return 0.0F;
	}
	
	public static float[] getMatrixBuf(final int id){
		final LensSpikeInfo i = lensSpikes.get(id);
		if(i != null){
			return i.modelviewMatrix;
		}
		return null;
	}
	
	public static void putMatrixBuf(final int id, final FloatBuffer matrix){
		final LensSpikeInfo i = lensSpikes.get(id);
		if(i != null){
			matrix.get(i.modelviewMatrix);
			matrix.rewind();
		}
	}
	
	@SubscribeEvent
	public static void renderLast(final RenderWorldLastEvent event) {
		for(final LensSpikeInfo i : lensSpikes.values()){
			i.updateVisibility();
		}
	}
	
	public static class LensSpikeInfo {
		public float[] modelviewMatrix;
		public float visibility = 0.0F;
		private final int totalFragmentsQuery;
		private final int fragmentsPassedQuery;
		
		public LensSpikeInfo(final float[] matrix) {
			this.modelviewMatrix = matrix;
			totalFragmentsQuery = GLCompat.genQueries();
			fragmentsPassedQuery = GLCompat.genQueries();
			
			GlStateManager.colorMask(false, false, false, false);
			GlStateManager.depthMask(false);
			GlStateManager.disableCull();
			Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.turbofan_blades_tex);
			
			GL11.glPushMatrix();
			ClientProxy.AUX_GL_BUFFER.put(matrix);
			ClientProxy.AUX_GL_BUFFER.rewind();
			GL11.glLoadMatrix(ClientProxy.AUX_GL_BUFFER);
			GL11.glScaled(0.05, 0.05, 0.05);
			GlStateManager.disableDepth();
			GLCompat.beginQuery(GLCompat.GL_SAMPLES_PASSED, totalFragmentsQuery);
			GL11.glCallList(checkSphere);
			GLCompat.endQuery(GLCompat.GL_SAMPLES_PASSED);
			
			GlStateManager.enableDepth();
			GLCompat.beginQuery(GLCompat.GL_SAMPLES_PASSED, fragmentsPassedQuery);
			GL11.glCallList(checkSphere);
			GLCompat.endQuery(GLCompat.GL_SAMPLES_PASSED);
			GL11.glPopMatrix();
			
			GlStateManager.colorMask(true, true, true, true);
			GlStateManager.depthMask(true);
			GlStateManager.enableCull();
		}
		
		public void updateVisibility(){
			final int totalDone = GLCompat.getQueryObject(totalFragmentsQuery, GLCompat.GL_QUERY_RESULT_AVAILABLE);
			final int passedDone = GLCompat.getQueryObject(fragmentsPassedQuery, GLCompat.GL_QUERY_RESULT_AVAILABLE);
			if(totalDone != 0 && passedDone != 0){
				final float total = GLCompat.getQueryObject(totalFragmentsQuery, GLCompat.GL_QUERY_RESULT);
				final float passed = GLCompat.getQueryObject(fragmentsPassedQuery, GLCompat.GL_QUERY_RESULT);
				visibility = passed/total;
				
				GlStateManager.colorMask(false, false, false, false);
				GlStateManager.depthMask(false);
				GlStateManager.disableCull();
				Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.turbofan_blades_tex);
				
				GL11.glPushMatrix();
				ClientProxy.AUX_GL_BUFFER.put(modelviewMatrix);
				ClientProxy.AUX_GL_BUFFER.rewind();
				GL11.glLoadMatrix(ClientProxy.AUX_GL_BUFFER);
				GL11.glScaled(0.1, 0.1, 0.1);
				GlStateManager.disableDepth();
				GLCompat.beginQuery(GLCompat.GL_SAMPLES_PASSED, totalFragmentsQuery);
				GL11.glCallList(checkSphere);
				GLCompat.endQuery(GLCompat.GL_SAMPLES_PASSED);
				
				GlStateManager.enableDepth();
				GLCompat.beginQuery(GLCompat.GL_SAMPLES_PASSED, fragmentsPassedQuery);
				GL11.glCallList(checkSphere);
				GLCompat.endQuery(GLCompat.GL_SAMPLES_PASSED);
				GL11.glPopMatrix();
				
				GlStateManager.colorMask(true, true, true, true);
				GlStateManager.depthMask(true);
				GlStateManager.enableCull();
			}
		}
		
		public void cleanup(){
			GLCompat.deleteQueries(totalFragmentsQuery);
			GLCompat.deleteQueries(fragmentsPassedQuery);
			//That modelViewMatrix not being deleted might cause a memory leak, but if it does, I don't know what to do about it in java!
		}
	}
	
	
}
