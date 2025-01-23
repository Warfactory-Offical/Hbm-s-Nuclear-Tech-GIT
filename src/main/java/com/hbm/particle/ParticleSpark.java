package com.hbm.particle;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleSpark extends Particle {

	public float stretch;
	public float gravity;
	
	public ParticleSpark(final World worldIn, final double posXIn, final double posYIn, final double posZIn, final float stretch, final float scale, final int lifetime, final float gravity) {
		super(worldIn, posXIn, posYIn, posZIn);
		this.stretch = stretch;
		this.particleScale = scale;
		this.particleMaxAge = lifetime;
		this.gravity = gravity;
	}
	
	public ParticleSpark color(final float colR, final float colG, final float colB, final float colA){
		this.particleRed = colR;
		this.particleGreen = colG;
		this.particleBlue = colB;
		this.particleAlpha = colA;
		return this;
	}
	
	public ParticleSpark motion(final float mX, final float mY, final float mZ){
		this.motionX = mX;
		this.motionY = mY-gravity;
		this.motionZ = mZ;
		this.posX += mX;
		this.posY += mY-gravity;
		this.posZ += mZ;
		return this;
	}

	@Override
	public void onUpdate() {
		this.particleAge++;
		if(this.particleAge >= this.particleMaxAge || new Vec3d(motionX, motionY, motionZ).lengthSquared() < 0.001){
			setExpired();
			return;
		}
        this.canCollide = this.particleAge >= 4;
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.move(motionX, motionY, motionZ);
		final float airResistance = 0.95F;
		this.motionX *= airResistance;
		this.motionY = motionY*airResistance-gravity;
		this.motionZ *= airResistance;
	}
	
	@Override
	public void move(double x, double y, double z) {
		final double d0 = y;
        final double origX = x;
        final double origZ = z;

        if (this.canCollide)
        {
            final List<AxisAlignedBB> list = this.world.getCollisionBoxes(null, this.getBoundingBox().expand(x, y, z));

            for (final AxisAlignedBB axisalignedbb : list)
            {
                y = axisalignedbb.calculateYOffset(this.getBoundingBox(), y);
            }

            this.setBoundingBox(this.getBoundingBox().offset(0.0D, y, 0.0D));

            for (final AxisAlignedBB axisalignedbb1 : list)
            {
                x = axisalignedbb1.calculateXOffset(this.getBoundingBox(), x);
            }

            this.setBoundingBox(this.getBoundingBox().offset(x, 0.0D, 0.0D));

            for (final AxisAlignedBB axisalignedbb2 : list)
            {
                z = axisalignedbb2.calculateZOffset(this.getBoundingBox(), z);
            }

            this.setBoundingBox(this.getBoundingBox().offset(0.0D, 0.0D, z));
        }
        else
        {
            this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        }

        this.resetPositionToBB();
        this.onGround = d0 != y && d0 < 0.0D;
        
        if(d0 != y){
        	this.motionY = -motionY*0.75*(rand.nextFloat()*0.8+0.25);
        }

        if (origX != x)
        {
            this.motionX = -motionX*0.75*(rand.nextFloat()*1.12+0.25);
        }

        if (origZ != z)
        {
            this.motionZ = -motionY*0.75*(rand.nextFloat()*1.12+0.25);
        }
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.bfg_particle);
		GlStateManager.disableAlpha();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.disableCull();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.disableLighting();
		
        final float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        final float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        final float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        final float mX = (float)(this.posX + (this.posX+this.motionX - this.posX) * (double)partialTicks - interpPosX);
        final float mY = (float)(this.posY + (this.posY+this.motionY - this.posY) * (double)partialTicks - interpPosY);
        final float mZ = (float)(this.posZ + (this.posZ+this.motionZ - this.posZ) * (double)partialTicks - interpPosZ);
        
        Vec3d particleAxis = new Vec3d(mX, mY, mZ).subtract(f5, f6, f7);
        final Vec3d toPlayer = new Vec3d(mX, mY, mZ);
        Vec3d point1 = particleAxis.crossProduct(toPlayer).normalize().scale(0.5*particleScale);
        Vec3d point2 = point1.scale(-1);
        point1 = point1.add(f5, f6, f7);
        point2 = point2.add(f5, f6, f7);
        particleAxis = particleAxis.scale(stretch);
        
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        float alpha = this.particleAlpha;
        while(alpha > 0){
        	buffer.pos(point2.x, point2.y, point2.z).tex(1, 0).color(particleRed, particleGreen, particleBlue, MathHelper.clamp(alpha, 0, 1)).lightmap(240, 240).endVertex();
        	buffer.pos(point1.x, point1.y, point1.z).tex(1, 1).color(particleRed, particleGreen, particleBlue, MathHelper.clamp(alpha, 0, 1)).lightmap(240, 240).endVertex();
        	
        	buffer.pos(point1.x+particleAxis.x, point1.y+particleAxis.y, point1.z+particleAxis.z).tex(0, 1).color(particleRed, particleGreen, particleBlue, MathHelper.clamp(alpha, 0, 1)).lightmap(240, 240).endVertex();
        	buffer.pos(point2.x+particleAxis.x, point2.y+particleAxis.y, point2.z+particleAxis.z).tex(0, 0).color(particleRed, particleGreen, particleBlue, MathHelper.clamp(alpha, 0, 1)).lightmap(240, 240).endVertex();
        	alpha -= 1;
        }
        Tessellator.getInstance().draw();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
	}
	
}
