package com.hbm.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Queues;
import com.hbm.lib.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//Drillgon200: Like the vanilla particle manager, but supports more gl states.
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = RefStrings.MODID)
public class ParticleBatchRenderer {
	
	public static List<ParticleRenderLayer> layers = new ArrayList<>();

	private static final Queue<ParticleLayerBase> queue = Queues.newArrayDeque();

	public static void registerRenderLayer(final ParticleRenderLayer r){
		layers.add(r);
		r.isRegistered = true;
	}
	
	public static void addParticle(final ParticleLayerBase p) {
		if(p != null)
			queue.add(p);
	}

	public static void updateParticles() {
		if(Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().isGamePaused())
			return;
		
		for(final ParticleRenderLayer layer : layers){
			final Iterator<ParticleLayerBase> itr = layer.particles.iterator();
			while(itr.hasNext()) {
				final ParticleLayerBase p = itr.next();
				p.onUpdate();
				if(!p.isAlive()) {
					itr.remove();
				}
			}
		}
		
		if(!queue.isEmpty()) {
			for(ParticleLayerBase particle = queue.poll(); particle != null; particle = queue.poll()) {
				final ParticleRenderLayer layer = particle.getRenderLayer();
				
				if(layer == null){
					throw new RuntimeException("Particle " + particle + " does not use a custom render layer!");
				}
				if(!layer.isRegistered){
					registerRenderLayer(layer);
				}
				if(layer.particles.size() > 16384)
					layer.particles.removeFirst();
				
				layer.particles.add(particle);
			}
		}
	}

	public static void renderParticles(final Entity entityIn, final float partialTicks) {
		final float f = ActiveRenderInfo.getRotationX();
		final float f1 = ActiveRenderInfo.getRotationZ();
		final float f2 = ActiveRenderInfo.getRotationYZ();
		final float f3 = ActiveRenderInfo.getRotationXY();
		final float f4 = ActiveRenderInfo.getRotationXZ();
		Particle.interpPosX = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double) partialTicks;
		Particle.interpPosY = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double) partialTicks;
		Particle.interpPosZ = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double) partialTicks;
		Particle.cameraViewDir = entityIn.getLook(partialTicks);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		//Note: The constant 0.003921569F is actually 1/255, so particle fragments will only be cut if they have no alpha anyway.
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
		GlStateManager.depthMask(false);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		for(final ParticleRenderLayer layer : layers) {
			if(layer.particles.isEmpty())
				continue;
			layer.preRender();
			for(final ParticleLayerBase particle : layer.particles){
				particle.renderParticle(Tessellator.getInstance().getBuffer(), entityIn, partialTicks, f, f4, f1, f2, f3);
			}
			layer.postRender();
		}

		GlStateManager.depthMask(true);
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.disableBlend();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
	}
	
	public static void renderLast(final RenderWorldLastEvent event) {
		renderParticles(Minecraft.getMinecraft().getRenderViewEntity(), event.getPartialTicks());
	}

	@SubscribeEvent
	public static void clientTick(final ClientTickEvent event) {
		if(event.phase == Phase.START) {
			updateParticles();
		}
	}

}