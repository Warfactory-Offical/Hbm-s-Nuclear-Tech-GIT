package com.hbm.particle.bullet_hit;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleSmokeAnim extends ParticleLayerBase {
	
	public float speed;
	public int offset;
	public float scaleOverLife;
	public float prevScale;
	public float gravity;
	public float opacity;
	
	public ParticleSmokeAnim(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float speed, final float scale, final float scaleOverLife, final int lifetime) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleScale = scale;
		prevScale = scale;
		this.particleAngle = (float) (rand.nextFloat()*Math.PI*2);
		this.prevParticleAngle = this.particleAngle;
		this.speed = speed;
		this.particleMaxAge = lifetime*2;
		this.particleGravity = 0;
		gravity = 0.04F;
		this.offset = rand.nextInt(81);
		this.scaleOverLife = scaleOverLife;
		opacity = 1;
	}
	
	public ParticleSmokeAnim color(final float r, final float g, final float b){
		return color(r, g, b, 1);
	}
	
	public ParticleSmokeAnim color(final float r, final float g, final float b, final float a){
		this.particleRed = r;
		this.particleGreen = g;
		this.particleBlue = b;
		this.opacity = a;
		return this;
	}
	
	public ParticleSmokeAnim motion(final float x, final float y, final float z){
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		return this;
	}
	
	public ParticleSmokeAnim gravity(final float g){
		this.gravity = g;
		return this;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.motionX *= 0.9F;
		this.motionY *= 0.9F;
		this.motionZ *= 0.9F;
		this.motionY -= gravity;
		prevScale = particleScale;
		particleScale += scaleOverLife;
		scaleOverLife *= 0.95F;
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
		float fade = MathHelper.clamp((particleAge+partialTicks)/particleMaxAge, 0, 1);
		fade = (float) (1-Math.log10(fade*9+1));
		this.particleAlpha = fade*0.3F*opacity;
		final int index = (int) ((this.particleAge+partialTicks)*25F*speed+offset) % 81;
		final float size = 1/9F;
        final float u = (index%9)*size;
        final float v = (index/9)*size;
        
        final float sc = prevScale + (particleScale-prevScale)*partialTicks;
        final float f4 = 0.1F * sc;

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
		return layer;
	}
	
	public static ParticleRenderLayer layer = new ParticleRenderLayer(){
		@Override
		public void preRender() {
			Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.smoke_anim0);
			//Makes it not pixelated when looking at it up close by using linear interpolation as the magnification filter.
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GlStateManager.enableColorMaterial();
			GlStateManager.enableRescaleNormal();
			net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
			GlStateManager.glNormal3f(0, 1, 0);
			GlStateManager.enableBlend();
			GlStateManager.enableLighting();
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
	};
	
}
