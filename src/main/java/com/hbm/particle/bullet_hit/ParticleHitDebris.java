package com.hbm.particle.bullet_hit;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.hbm.particle.ParticleLayerBase;
import com.hbm.particle.ParticleRenderLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleHitDebris extends ParticleLayerBase {

	public ResourceLocation tex;
	public float rotationOverLifetime;
	public int texIdx;
	
	public ParticleHitDebris(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final ResourceLocation tex, final int idx, final float scale, final int lifetime) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleScale = 0.5F + worldIn.rand.nextFloat();
		particleScale *= scale;
		this.particleAngle = (float) (rand.nextFloat()*Math.PI*2);
		rotationOverLifetime = world.rand.nextFloat()*3-1.5F;
		this.prevParticleAngle = this.particleAngle;
		this.tex = tex;
		this.texIdx = idx;
		this.particleMaxAge = lifetime;
		this.particleGravity = 1F;
	}
	
	public ParticleHitDebris color(final float r, final float g, final float b){
		this.particleRed = r;
		this.particleGreen = g;
		this.particleBlue = b;
		return this;
	}
	
	public ParticleHitDebris motion(final float x, final float y, final float z){
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		return this;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.prevParticleAngle = this.particleAngle;
		this.particleAngle += rotationOverLifetime;
		this.rotationOverLifetime *= onGround ? 0.7F : 0.95F;
		
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return true;
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		final float size = 0.25F;
        final float u = (texIdx%4)*size;
        final float v = (texIdx/4)*size;
        
        //Fades it out at the end.
      	this.particleAlpha = 1-MathHelper.clamp((particleAge+partialTicks)-(particleMaxAge-10), 0, 10)*0.1F;
        
        final float f4 = 0.1F * this.particleScale;

        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        final int i = this.getBrightnessForRender(partialTicks);
        final int j = i >> 16 & 65535;
        final int k = i & 65535;
        final Vec3d[] avec3d = new Vec3d[] {new Vec3d(-rotationX * f4 - rotationXY * f4, -rotationZ * f4, -rotationYZ * f4 - rotationXZ * f4), new Vec3d(-rotationX * f4 + rotationXY * f4, rotationZ * f4, -rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 + rotationXY * f4, rotationZ * f4, rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 - rotationXY * f4, -rotationZ * f4, rotationYZ * f4 - rotationXZ * f4)};

        if (this.particleAngle != 0.0F)
        {
            final float f8 = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * partialTicks;
            final float f9 = MathHelper.cos(f8 * 0.5F);
            final float f10 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.x;
            final float f11 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.y;
            final float f12 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.z;
            final Vec3d vec3d = new Vec3d(f10, f11, f12);

            for (int l = 0; l < 4; ++l)
            {
                avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double)(f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale(2.0F * f9));
            }
        }

        buffer.pos((double)f5 + avec3d[0].x, (double)f6 + avec3d[0].y, (double)f7 + avec3d[0].z).tex((double)u+size, (double)v+size).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[1].x, (double)f6 + avec3d[1].y, (double)f7 + avec3d[1].z).tex((double)u+size, v).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[2].x, (double)f6 + avec3d[2].y, (double)f7 + avec3d[2].z).tex(u, v).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[3].x, (double)f6 + avec3d[3].y, (double)f7 + avec3d[3].z).tex(u, (double)v+size).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
	}

	@Override
	public ParticleRenderLayer getRenderLayer() {
		ParticleRenderLayer layer = layers.get(tex);
		if(layer == null){
			layer = new ParticleRenderLayerDebris(tex);
			layers.put(tex, layer);
		}
		return layer;
	}
	
	public static Map<ResourceLocation, ParticleRenderLayer> layers = new HashMap<>();
	
	public static class ParticleRenderLayerDebris extends ParticleRenderLayer {
		
		public ResourceLocation texture;
		
		public ParticleRenderLayerDebris(final ResourceLocation tex) {
			this.texture = tex;
		}
		
		@Override
		public void preRender() {
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			//Makes it not pixelated when looking at it up close by using linear interpolation as the magnification filter.
			//GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GlStateManager.enableBlend();
			GlStateManager.enableColorMaterial();
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableLighting();
			net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
			GlStateManager.glNormal3f(0, 1, 0);
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0);
			GlStateManager.depthMask(false);
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
			
			Tessellator.getInstance().getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
		}
		
		@Override
		public void postRender() {
			Tessellator.getInstance().draw();
		       
	        GlStateManager.disableBlend();
	        GlStateManager.depthMask(true);
	        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		}
	}
}
