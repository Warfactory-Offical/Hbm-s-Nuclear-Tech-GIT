package com.hbm.render.entity.effect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.effect.EntityNukeTorex.Cloudlet;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;


public class RenderTorex extends Render<EntityNukeTorex> {

	public static final IRenderFactory<EntityNukeTorex> FACTORY = man -> new RenderTorex(man);
	
	private static final ResourceLocation cloudlet = new ResourceLocation(RefStrings.MODID + ":textures/particle/particle_base.png");
	private static final ResourceLocation flare = new ResourceLocation(RefStrings.MODID + ":textures/particle/flare.png");

	public static final int flashBaseDuration = 30;
	public static final int flareBaseDuration = 100;

	protected RenderTorex(final RenderManager renderManager){
		super(renderManager);
	}

	@Override
	public void doRender(final EntityNukeTorex cloud, final double x, final double y, final double z, final float entityYaw, final float partialTicks){
		final float scale = (float)cloud.getScale();
		final float flashDuration = scale * flashBaseDuration;
		final float flareDuration = scale * flareBaseDuration;

		doScreenShake(cloud, x, y, z, scale * 100);
		
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		final boolean fog = GL11.glIsEnabled(GL11.GL_FOG);
		if(fog)
			GL11.glDisable(GL11.GL_FOG);

		cloudletWrapper(cloud, partialTicks);

		if(cloud.ticksExisted < flareDuration+1)
			flareWrapper(cloud, partialTicks, flareDuration);
		
		if(cloud.ticksExisted < flashDuration+1)
			flashWrapper(cloud, partialTicks, flashDuration);

		if(fog)
			GL11.glEnable(GL11.GL_FOG);

		GL11.glPopMatrix();
	}

	private void doScreenShake(final EntityNukeTorex cloud, final double x, final double y, final double z, float amplitude){
		if(cloud.ticksExisted > 300) return;
		final EntityPlayer player = MainRegistry.proxy.me();

		final double dist = player.getDistance(cloud);
		final double shockwaveDistance = dist - cloud.ticksExisted * 1.5;
		if(shockwaveDistance > 10 || shockwaveDistance < 0) return;
		amplitude = Math.min(amplitude, 125);
		final int duration = ((int)(amplitude * Math.min(1, (amplitude * amplitude)/(dist * dist))));
		final int swingTimer = duration<<1;
		cloud.world.playSound(player, cloud.posX, cloud.posY, cloud.posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.AMBIENT, amplitude * 10F, 0.8F + cloud.world.rand.nextFloat() * 0.2F);
		
		if(player.getDisplayName().equals("Vic4Games")) {
			player.hurtTime = swingTimer<<1;
			player.maxHurtTime = duration<<1;
		} else {
			player.hurtTime = swingTimer;
			player.maxHurtTime = duration;
		}
		player.attackedAtYaw = 0F;
	}
	
	private final Comparator cloudSorter = new Comparator() {

		@Override
		public int compare(final Object arg0, final Object arg1) {
			final Cloudlet first = (Cloudlet) arg0;
			final Cloudlet second = (Cloudlet) arg1;
			final EntityPlayer player = MainRegistry.proxy.me();
			final double dist1 = player.getDistanceSq(first.posX, first.posY, first.posZ);
			final double dist2 = player.getDistanceSq(second.posX, second.posY, second.posZ);
			
			return dist1 > dist2 ? -1 : dist1 == dist2 ? 0 : 1;
		}
	};

	private void cloudletWrapper(final EntityNukeTorex cloud, final float partialTicks) {

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		// To prevent particles cutting off before fully fading out
		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDepthMask(false);
		RenderHelper.disableStandardItemLighting();
		
		bindTexture(cloudlet);

		final Tessellator tess = Tessellator.getInstance();
        final BufferBuilder buf = tess.getBuffer();
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
		
		final ArrayList<Cloudlet> cloudlets = new ArrayList(cloud.cloudlets);
		cloudlets.sort(cloudSorter);
		
		for(final Cloudlet cloudlet : cloudlets) {
			final Vec3 vec = cloudlet.getInterpPos(partialTicks);
			tessellateCloudlet(buf, vec.xCoord - cloud.posX, vec.yCoord - cloud.posY, vec.zCoord - cloud.posZ, cloudlet, partialTicks);
		}

		tess.draw();

		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		RenderHelper.enableStandardItemLighting();
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	private void flareWrapper(final EntityNukeTorex cloud, final float partialTicks, final float flareDuration) {

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDepthMask(false);
		RenderHelper.disableStandardItemLighting();
			
		bindTexture(flare);

		final Tessellator tess = Tessellator.getInstance();
        final BufferBuilder buf = tess.getBuffer();
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
		
		final double age = Math.min(cloud.ticksExisted + partialTicks, flareDuration);
		final float alpha = (float) Math.min(1, (flareDuration - age) / flareDuration);
		
		final Random rand = new Random(cloud.getEntityId());
		
		for(int i = 0; i < 3; i++) {
			final float x = (float) (rand.nextGaussian() * 0.5F * cloud.rollerSize);
			final float y = (float) (rand.nextGaussian() * 0.5F * cloud.rollerSize);
			final float z = (float) (rand.nextGaussian() * 0.5F * cloud.rollerSize);
			tessellateFlare(buf, x, y + cloud.coreHeight, z, (float) (10 * cloud.rollerSize), alpha, partialTicks);
		}

		tess.draw();

		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		RenderHelper.enableStandardItemLighting();
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	private void tessellateCloudlet(final BufferBuilder buf, final double posX, final double posY, final double posZ, final Cloudlet cloud, final float partialTicks) {

		final float a = cloud.getAlpha();
		final float scale = cloud.getScale();

		final float f1 = ActiveRenderInfo.getRotationX();
		final float f2 = ActiveRenderInfo.getRotationZ();
		final float f3 = ActiveRenderInfo.getRotationYZ();
		final float f4 = ActiveRenderInfo.getRotationXY();
		final float f5 = ActiveRenderInfo.getRotationXZ();

		final float brightness = cloud.type == EntityNukeTorex.TorexType.CONDENSATION ? 0.9F : 0.75F * cloud.colorMod;
		final Vec3 color = cloud.getInterpColor(partialTicks);
		float r, g, b;
		r =  Math.max(0.15F, (float)color.xCoord * brightness);
		g =  Math.max(0.15F, (float)color.yCoord * brightness);
		b =  Math.max(0.15F, (float)color.zCoord * brightness);

		final int br = (int)Math.max(48, (Math.min((r+g+b) / 3D, 1) * 240));
		r = Math.min(1F, r);
		g = Math.min(1F, g);
		b = Math.min(1F, b);

		buf.pos(posX - f1 * scale - f3 * scale, posY - f5 * scale, posZ - f2 * scale - f4 * scale).tex(1, 1).color(r, g, b, a).lightmap(br, br).endVertex();
		buf.pos(posX - f1 * scale + f3 * scale, posY + f5 * scale, posZ - f2 * scale + f4 * scale).tex(1, 0).color(r, g, b, a).lightmap(br, br).endVertex();
		buf.pos(posX + f1 * scale + f3 * scale, posY + f5 * scale, posZ + f2 * scale + f4 * scale).tex(0, 0).color(r, g, b, a).lightmap(br, br).endVertex();
		buf.pos(posX + f1 * scale - f3 * scale, posY - f5 * scale, posZ + f2 * scale - f4 * scale).tex(0, 1).color(r, g, b, a).lightmap(br, br).endVertex();
	}

	private void tessellateFlare(final BufferBuilder buf, final double posX, final double posY, final double posZ, final float scale, final float a, final float partialTicks) {

		final float f1 = ActiveRenderInfo.getRotationX();
		final float f2 = ActiveRenderInfo.getRotationZ();
		final float f3 = ActiveRenderInfo.getRotationYZ();
		final float f4 = ActiveRenderInfo.getRotationXY();
		final float f5 = ActiveRenderInfo.getRotationXZ();
		final int br = (int)(a * 240);
		buf.pos(posX - f1 * scale - f3 * scale, posY - f5 * scale, posZ - f2 * scale - f4 * scale).tex(1, 1).color(1F, 1F, 1F, a).lightmap(br, br).endVertex();
		buf.pos(posX - f1 * scale + f3 * scale, posY + f5 * scale, posZ - f2 * scale + f4 * scale).tex(1, 0).color(1F, 1F, 1F, a).lightmap(br, br).endVertex();
		buf.pos(posX + f1 * scale + f3 * scale, posY + f5 * scale, posZ + f2 * scale + f4 * scale).tex(0, 0).color(1F, 1F, 1F, a).lightmap(br, br).endVertex();
		buf.pos(posX + f1 * scale - f3 * scale, posY - f5 * scale, posZ + f2 * scale - f4 * scale).tex(0, 1).color(1F, 1F, 1F, a).lightmap(br, br).endVertex();

	}

	private void flashWrapper(final EntityNukeTorex cloud, final float interp, final float flashDuration) {

        if(cloud.ticksExisted < flashDuration) {

    		GL11.glPushMatrix();
    		//Function [0, 1] that determines the scale and intensity (inverse!) of the flash
        	double intensity = (cloud.ticksExisted + interp) / flashDuration;
        	GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);

        	//Euler function to slow down the scale as it progresses
        	//Makes it start fast and the fade-out is nice and smooth
        	intensity = intensity * Math.pow(Math.E, -intensity) * 2.717391304D;

        	renderFlash(50F * flashDuration /(float)flashBaseDuration, intensity, cloud.coreHeight);
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
    		GL11.glPopMatrix();
        }
	}

	private void renderFlash(final float scale, final double intensity, final double height) {

    	GL11.glScalef(0.2F, 0.2F, 0.2F);
    	GL11.glTranslated(0, height * 4, 0);

    	final double inverse = 1.0D - intensity;

        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buf = tessellator.getBuffer();
		RenderHelper.disableStandardItemLighting();

        final Random random = new Random(432L);
        GlStateManager.disableTexture2D();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
        GlStateManager.disableAlpha();
        GlStateManager.enableCull();
        GlStateManager.depthMask(false);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		
        GL11.glPushMatrix();

        for(int i = 0; i < 300; i++) {

            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);

            final float vert1 = (random.nextFloat() * 20.0F + 5.0F + 1 * 10.0F) * (float)(intensity * scale);
            final float vert2 = (random.nextFloat() * 2.0F + 1.0F + 1 * 2.0F) * (float)(intensity * scale);

            buf.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
            buf.pos(0D, 0D, 0D).color(1.0F, 1.0F, 1.0F, (float) inverse).endVertex();
            buf.pos(-0.866D * vert2, vert1, -0.5D * vert2).color(1.0F, 1.0F, 1.0F, 0.0F).endVertex();
            buf.pos(0.866D * vert2, vert1, -0.5D * vert2).color(1.0F, 1.0F, 1.0F, 0.0F).endVertex();
            buf.pos(0.0D, vert1, 1.0D * vert2).color(1.0F, 1.0F, 1.0F, 0.0F).endVertex();
            buf.pos(-0.866D * vert2, vert1, -0.5D * vert2).color(1.0F, 1.0F, 1.0F, 0.0F).endVertex();
            tessellator.draw();
        }

        GL11.glPopMatrix();

        GlStateManager.depthMask(true);
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        RenderHelper.enableStandardItemLighting();
	}

	@Override
	protected ResourceLocation getEntityTexture(final EntityNukeTorex entity) {
		return null;
	}
}
