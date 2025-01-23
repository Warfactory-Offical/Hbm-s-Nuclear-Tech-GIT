package com.hbm.particle;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.util.BakedModelUtil;
import com.hbm.render.util.BakedModelUtil.DecalType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class ParticleBlood extends Particle {

	public float stretch;
	public float gravity;
	public Vec3d originalAxis;
	
	public ParticleBlood(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float stretch, final float scale, final int lifetime, final float gravity) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.stretch = stretch;
		this.particleScale = scale;
		this.particleMaxAge = lifetime;
		this.gravity = gravity;
	}
	
	public ParticleBlood motion(final float mX, final float mY, final float mZ){
		this.motionX = mX;
		this.motionY = mY-gravity;
		this.motionZ = mZ;
		this.posX += mX;
		this.posY += mY-gravity;
		this.posZ += mZ;
		originalAxis = new Vec3d(mX, mY, mZ).normalize();
		return this;
	}
	
	public ParticleBlood color(final float colR, final float colG, final float colB, final float colA){
		this.particleRed = colR;
		this.particleGreen = colG;
		this.particleBlue = colB;
		this.particleAlpha = colA;
		return this;
	}
	
	@Override
	public void onUpdate() {
		this.particleAge ++;
		if(this.particleAge > this.particleMaxAge){
			this.setExpired();
			return;
		}
		
		final RayTraceResult r = world.rayTraceBlocks(new Vec3d(posX, posY, posZ), new Vec3d(posX+motionX, posY+motionY, posZ+motionZ));
		if(r != null && r.typeOfHit == Type.BLOCK){
			final Vec3d hit = r.hitVec;
			Vec3d direction = new Vec3d(motionX, motionY, motionZ).normalize();
			final int[] dl = BakedModelUtil.generateDecalMesh(world, direction, 1, (float)hit.x, (float)hit.y, (float)hit.z, DecalType.REGULAR);
			direction = direction.scale(0.001F);
			Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleDecal(world, dl[0], ResourceManager.blood_dec1, 120, (float)hit.x-direction.x, (float)hit.y-direction.y, (float)hit.z-direction.z));
			setExpired();
			return;
		}
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.move(motionX, motionY, motionZ);
		final float airResistance = 0.9F;
		this.motionX *= airResistance;
		this.motionY = motionY*airResistance-gravity;
		this.motionZ *= airResistance;
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.blood0);
		final float fade = ((float)this.particleAge+partialTicks)/(float)particleMaxAge;
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0);
		GlStateManager.disableCull();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		
        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        
        Vec3d particleAxis = originalAxis;
        //Is it right to subtract the eye height here? No idea!
        final Vec3d toPlayer = new Vec3d(f5, f6-Minecraft.getMinecraft().player.getEyeHeight(), f7);
        Vec3d point1 = particleAxis.crossProduct(toPlayer).normalize().scale(0.5*particleScale*(fade*fade*1.5F+1));
        Vec3d point2 = point1.scale(-1);
        point1 = point1.add(f5, f6, f7);
        point2 = point2.add(f5, f6, f7);
        particleAxis = particleAxis.scale(stretch*(fade*fade*1.5F+1));
        
        final int i = this.getBrightnessForRender(partialTicks);
		final int j = i >> 16 & 65535;
        final int k = i & 65535;
        
        final float a = MathHelper.clamp(this.particleAlpha*(1-fade*fade), 0, 1);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        buffer.pos(point2.x, point2.y, point2.z).tex(1, 0).color(particleRed, particleGreen, particleBlue, a).lightmap(j, k).endVertex();
        buffer.pos(point1.x, point1.y, point1.z).tex(1, 1).color(particleRed, particleGreen, particleBlue, a).lightmap(j, k).endVertex();
        	
        buffer.pos(point1.x+particleAxis.x, point1.y+particleAxis.y, point1.z+particleAxis.z).tex(0, 1).color(particleRed, particleGreen, particleBlue, a).lightmap(j, k).endVertex();
        buffer.pos(point2.x+particleAxis.x, point2.y+particleAxis.y, point2.z+particleAxis.z).tex(0, 0).color(particleRed, particleGreen, particleBlue, a).lightmap(j, k).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
	}

}
