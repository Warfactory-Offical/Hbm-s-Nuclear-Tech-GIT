package com.hbm.particle;

import org.lwjgl.opengl.GL11;
import com.hbm.lib.RefStrings;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleExp5 extends Particle {

	public static final ResourceLocation tex3 = new ResourceLocation(RefStrings.MODID, "textures/particle/explosion.png");
	
	public ParticleExp5(final World worldIn, final double posXIn, final double posYIn, final double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.particleScale = 25 + worldIn.rand.nextInt(5);
		this.particleAngle = (float) (rand.nextFloat()*Math.PI*2);
		this.prevParticleAngle = this.particleAngle;
	}
	
	@Override
	public void onUpdate() {
		if(this.particleAge >= this.particleMaxAge){
			this.setExpired();
		}
		final float timeScale = this.particleAge/(float)this.particleMaxAge;
		//Fades it out at the end.
		this.particleAlpha = MathHelper.clamp(1-BobMathUtil.remap((float)MathHelper.clamp(timeScale, 0.6, 1), 0.6F, 1F, 0F, 1.1F), 0, 1);
		this.particleAge++;
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(tex3);
		//Makes it not pixelated when looking at it up close by using linear interpolation as the magnification filter.
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GlStateManager.enableBlend();
		GlStateManager.disableLighting();
		GlStateManager.disableAlpha();
		GlStateManager.depthMask(false);
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		final int index = MathHelper.clamp((int) (((this.particleAge+partialTicks)/(float)this.particleMaxAge)*25), 0, 24);
		final float size = 1/5F;
        final float u = (index%5)*size;
        final float v = (index/5)*size;
        
        final float f4 = 0.1F * this.particleScale;

        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        final int j = 240;
        final int k = 240;
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

        Tessellator.getInstance().getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        buffer.pos((double)f5 + avec3d[0].x, (double)f6 + avec3d[0].y, (double)f7 + avec3d[0].z).tex((double)u+size, (double)v+size).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[1].x, (double)f6 + avec3d[1].y, (double)f7 + avec3d[1].z).tex((double)u+size, v).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[2].x, (double)f6 + avec3d[2].y, (double)f7 + avec3d[2].z).tex(u, v).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[3].x, (double)f6 + avec3d[3].y, (double)f7 + avec3d[3].z).tex(u, (double)v+size).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        Tessellator.getInstance().draw();
       
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.enableLighting();
        GlStateManager.enableAlpha();
	}
	
}
