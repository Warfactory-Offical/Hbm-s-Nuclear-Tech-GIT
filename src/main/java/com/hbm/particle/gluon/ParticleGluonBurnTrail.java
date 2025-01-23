package com.hbm.particle.gluon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.hbm.items.weapon.ItemGunEgon;
import com.hbm.main.ResourceManager;
import com.hbm.render.RenderHelper;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleGluonBurnTrail extends Particle {

	public EntityPlayer owner;
	public List<Node> nodes = new ArrayList<>();
	
	public ParticleGluonBurnTrail(final World worldIn, final float scale, final EntityPlayer owner) {
		super(worldIn, 0, 0, 0);
		this.owner = owner;
		this.particleScale = scale;
	}
	
	public boolean tryAddNewPosition(final Vec3d pos, final Vec3d normal){
		if(nodes.isEmpty()){
			nodes.add(new Node(pos, normal, null, 0));
		}
		final float dist = (float) pos.subtract(nodes.get(nodes.size()-1).pos).length();
		if(dist < 0.1){
			return true;
		}
		if(dist > 3){
			return false;
		}
		final float uScale = 0.25F;
		if(nodes.size() == 1){
			final Vec3d tan = normal.crossProduct(nodes.get(0).pos.subtract(pos)).normalize();
			nodes.add(new Node(pos, normal, tan, dist*uScale));
			nodes.get(0).tangent = tan;
		} else {
			final Node prev = nodes.get(nodes.size()-1);
			if(!prev.normal.equals(normal) && dist < 0.2F){
				//Attempt to stop it from going through blocks in some cases.
				//Doesn't work that well, but I guess it doesn't matter that much.
				final Node prevPrev = nodes.get(nodes.size()-2);
				final Vec3d dir = prev.pos.subtract(prevPrev.pos);
				
				final Vec3d diff = prev.pos.subtract(pos);
				final float distToPlane = (float) (diff.dotProduct(normal)/dir.dotProduct(normal));
				final Vec3d interPos = prev.pos.subtract(dir.scale(distToPlane));
				final Vec3d interNormal = prev.normal.add(normal).normalize();
				final Vec3d interTan = interNormal.crossProduct(normal).scale(-1).normalize();
				nodes.add(new Node(interPos, interNormal, interTan,  prev.uCoord-distToPlane*uScale));
				
				final Vec3d tan = normal.crossProduct(interPos.subtract(pos)).normalize();
				nodes.add(new Node(pos, normal, tan, (prev.uCoord-distToPlane*uScale)+(float) pos.subtract(interPos).length()*uScale));
			} else {
				final Vec3d tan = normal.crossProduct(prev.pos.subtract(pos)).normalize();
				nodes.add(new Node(pos, normal, tan, prev.uCoord+dist*uScale));
			}
		}
		return true;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge ++;
		final Iterator<Node> itr = nodes.iterator();
		while(itr.hasNext()){
			final Node n = itr.next();
			n.age++;
			if(n.age > 60)
				itr.remove();
		}
		if(nodes.isEmpty() && ItemGunEgon.activeTrailParticles.get(owner) != this){
			this.setExpired();
		}
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return true;
	}
	
	@Override
	public void renderParticle(BufferBuilder buf, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		RenderHelper.resetParticleInterpPos(entityIn, partialTicks);
		//GL11.glTranslated(-interpPosX, -interpPosY, -interpPosZ);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.gluon_burn);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
		GlStateManager.enableColorMaterial();
		GlStateManager.enableRescaleNormal();
		final int light = this.getBrightnessForRender(partialTicks);
        final int ltex1 = light >> 16 & 65535;
        final int ltex2 = light & 65535;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, ltex1, ltex2);
		GlStateManager.disableLighting();
		GlStateManager.depthMask(false);
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		final Tessellator tes = Tessellator.getInstance();
		buf = tes.getBuffer();
		buf.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_COLOR);
		for(int i = 0; i < nodes.size()-1; i ++){
			final Node prev = nodes.get(i);
			final Node next = nodes.get(i+1);
			final float prevFade = 1-BobMathUtil.remap01_clamp(prev.age+partialTicks, 50, 60);
			final float nextFade = 1-BobMathUtil.remap01_clamp(next.age+partialTicks, 50, 60);
			final float midFade = prevFade + (nextFade-prevFade)*0.5F;
			
			final Vec3d prevPos1 = prev.pos.add(prev.tangent.scale(particleScale)).subtract(interpPosX, interpPosY, interpPosZ);
			final Vec3d prevPos2 = prev.pos.add(prev.tangent.scale(-particleScale)).subtract(interpPosX, interpPosY, interpPosZ);
			final Vec3d mid = prev.pos.add(next.pos.subtract(prev.pos).scale(0.5F)).subtract(interpPosX, interpPosY, interpPosZ);
			final Vec3d nextPos1 = next.pos.add(next.tangent.scale(particleScale)).subtract(interpPosX, interpPosY, interpPosZ);
			final Vec3d nextPos2 = next.pos.add(next.tangent.scale(-particleScale)).subtract(interpPosX, interpPosY, interpPosZ);
			final float prevU = prev.uCoord;
			final float nextU = next.uCoord;
			final float midU = prevU + (nextU-prevU)*0.5F;
			
			final float color = 0.1F;
			final float alpha = 0.8F;
			buf.pos(prevPos1.x, prevPos1.y, prevPos1.z).tex(prevU, 0).color(color, color, color, alpha*prevFade).endVertex();
			buf.pos(prevPos2.x, prevPos2.y, prevPos2.z).tex(prevU, 1).color(color, color, color, alpha*prevFade).endVertex();
			buf.pos(mid.x, mid.y, mid.z).tex(midU, 0.5F).color(color, color, color, alpha*midFade).endVertex();
			
			buf.pos(prevPos1.x, prevPos1.y, prevPos1.z).tex(prevU, 0).color(color, color, color, alpha*prevFade).endVertex();
			buf.pos(mid.x, mid.y, mid.z).tex(midU, 0.5F).color(color, color, color, alpha*midFade).endVertex();
			buf.pos(nextPos1.x, nextPos1.y, nextPos1.z).tex(nextU, 0).color(color, color, color, alpha*nextFade).endVertex();
			
			buf.pos(prevPos2.x, prevPos2.y, prevPos2.z).tex(prevU, 1).color(color, color, color, alpha*prevFade).endVertex();
			buf.pos(nextPos2.x, nextPos2.y, nextPos2.z).tex(nextU, 1).color(color, color, color, alpha*nextFade).endVertex();
			buf.pos(mid.x, mid.y, mid.z).tex(midU, 0.5F).color(color, color, color, alpha*midFade).endVertex();
			
			buf.pos(nextPos2.x, nextPos2.y, nextPos2.z).tex(nextU, 1).color(color, color, color, alpha*nextFade).endVertex();
			buf.pos(nextPos1.x, nextPos1.y, nextPos1.z).tex(nextU, 0).color(color, color, color, alpha*nextFade).endVertex();
			buf.pos(mid.x, mid.y, mid.z).tex(midU, 0.5F).color(color, color, color, alpha*midFade).endVertex();
		}
		tes.draw();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.beam_generic);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		buf.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_COLOR);
		int idx = 0;
		for(int i = 0; i < nodes.size(); i ++){
			if(nodes.get(i).age <= 15){
				idx = i;
				break;
			}
		}
		for(int i = idx; i < nodes.size()-1; i ++){
			final Node prev = nodes.get(i);
			final Node next = nodes.get(i+1);
			final float prevFade = (1-BobMathUtil.remap01_clamp(prev.age+partialTicks, 2, 12))*0.75F;
			final float nextFade = (1-BobMathUtil.remap01_clamp(next.age+partialTicks, 2, 12))*0.75F;
			
			final Vec3d prevPos1 = prev.pos.add(prev.tangent.scale(particleScale*prevFade)).subtract(interpPosX, interpPosY, interpPosZ);
			final Vec3d prevPos2 = prev.pos.add(prev.tangent.scale(-particleScale*prevFade)).subtract(interpPosX, interpPosY, interpPosZ);
			final Vec3d mid = prev.pos.add(next.pos.subtract(prev.pos).scale(0.5F)).subtract(interpPosX, interpPosY, interpPosZ);
			final Vec3d nextPos1 = next.pos.add(next.tangent.scale(particleScale*nextFade)).subtract(interpPosX, interpPosY, interpPosZ);
			final Vec3d nextPos2 = next.pos.add(next.tangent.scale(-particleScale*nextFade)).subtract(interpPosX, interpPosY, interpPosZ);
			final float prevU = prev.uCoord;
			final float nextU = next.uCoord;
			final float midU = prevU + (nextU-prevU)*0.5F;
			
			final float r = 0.4F;
			final float g = 0.6F;
			final float b = 1F;
			final float alpha = 1F;
			for(int j = 0; j < 2; j ++){
				buf.pos(prevPos1.x, prevPos1.y, prevPos1.z).tex(prevU, 0).color(r, g, b, alpha).endVertex();
				buf.pos(prevPos2.x, prevPos2.y, prevPos2.z).tex(prevU, 1).color(r, g, b, alpha).endVertex();
				buf.pos(mid.x, mid.y, mid.z).tex(midU, 0.5F).color(r, g, b, alpha).endVertex();
				
				buf.pos(prevPos1.x, prevPos1.y, prevPos1.z).tex(prevU, 0).color(r, g, b, alpha).endVertex();
				buf.pos(mid.x, mid.y, mid.z).tex(midU, 0.5F).color(r, g, b, alpha).endVertex();
				buf.pos(nextPos1.x, nextPos1.y, nextPos1.z).tex(nextU, 0).color(r, g, b, alpha).endVertex();
				
				buf.pos(prevPos2.x, prevPos2.y, prevPos2.z).tex(prevU, 1).color(r, g, b, alpha).endVertex();
				buf.pos(nextPos2.x, nextPos2.y, nextPos2.z).tex(nextU, 1).color(r, g, b, alpha).endVertex();
				buf.pos(mid.x, mid.y, mid.z).tex(midU, 0.5F).color(r, g, b, alpha).endVertex();
				
				buf.pos(nextPos2.x, nextPos2.y, nextPos2.z).tex(nextU, 1).color(r, g, b, alpha).endVertex();
				buf.pos(nextPos1.x, nextPos1.y, nextPos1.z).tex(nextU, 0).color(r, g, b, alpha).endVertex();
				buf.pos(mid.x, mid.y, mid.z).tex(midU, 0.5F).color(r, g, b, alpha).endVertex();
			}
		}
		tes.draw();	
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
	}

	private static class Node {
		public int age = 0;
		public Vec3d pos;
		public Vec3d normal;
		public Vec3d tangent;
		public float uCoord;
		
		public Node(final Vec3d pos, final Vec3d normal, final Vec3d tangent, final float u) {
			this.pos = pos;
			this.normal = normal;
			this.tangent = tangent;
			this.uCoord = u;
		}
	}
}
