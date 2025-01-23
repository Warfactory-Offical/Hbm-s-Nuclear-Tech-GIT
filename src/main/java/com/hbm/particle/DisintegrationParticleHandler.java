package com.hbm.particle;

import java.lang.reflect.InvocationTargetException;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ClientProxy;
import com.hbm.main.MainRegistry;
import com.hbm.main.ResourceManager;
import com.hbm.particle.gluon.ParticleGluonDisintegration;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.render.util.ModelRendererUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class DisintegrationParticleHandler {

	public static void spawnGluonDisintegrateParticles(final Entity e) {
		final Render<Entity> eRenderer = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(e);
		if(eRenderer instanceof RenderLivingBase && e instanceof EntityLivingBase) {
			spawnGluonDisintegrateParticles((EntityLivingBase) e, ((RenderLivingBase) eRenderer), MainRegistry.proxy.partialTicks());
		}
	}

	@SuppressWarnings({ "deprecation" })
	public static void spawnGluonDisintegrateParticles(final EntityLivingBase e, final RenderLivingBase render, final float partialTicks) {
		final ModelBase model = render.getMainModel();
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GlStateManager.disableCull();
		GlStateManager.enableRescaleNormal();
		//So basically we're just going to copy vanialla methods so the 
		model.swingProgress = e.getSwingProgress(partialTicks);
		final boolean shouldSit = e.isRiding() && (e.getRidingEntity() != null && e.getRidingEntity().shouldRiderSit());
		model.isRiding = shouldSit;
		model.isChild = e.isChild();
		float f = interpolateRotation(e.prevRenderYawOffset, e.renderYawOffset, partialTicks);
		final float f1 = interpolateRotation(e.prevRotationYawHead, e.rotationYawHead, partialTicks);
		float f2 = f1 - f;
		if(shouldSit && e.getRidingEntity() instanceof EntityLivingBase elivingbase) {
            f = interpolateRotation(elivingbase.prevRenderYawOffset, elivingbase.renderYawOffset, partialTicks);
			f2 = f1 - f;
			float f3 = MathHelper.wrapDegrees(f2);

			if(f3 < -85.0F) {
				f3 = -85.0F;
			}

			if(f3 >= 85.0F) {
				f3 = 85.0F;
			}

			f = f1 - f3;

			if(f3 * f3 > 2500.0F) {
				f += f3 * 0.2F;
			}

			f2 = f1 - f;
		}

		final float f7 = e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * partialTicks;
		//renderLivingAt(e, x, y, z);
		final float f8 = e.ticksExisted + partialTicks;
		GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
		//if(rPreRenderCallback == null){
		//	rPreRenderCallback = ReflectionHelper.findMethod(RenderLivingBase.class, "preRenderCallback", "func_77041_b", EntityLivingBase.class, float.class);
		//}
		if(ModelRendererUtil.rPrepareScale == null){
			ModelRendererUtil.rPrepareScale = ReflectionHelper.findMethod(RenderLivingBase.class, "prepareScale", "func_188322_c", EntityLivingBase.class, float.class);
		}
		//float f4 = prepareScale(e, partialTicks, render);
		float f4 = 0.0625F;
		try {
			f4 = (float) ModelRendererUtil.rPrepareScale.invoke(render, e, partialTicks);
		} catch(final IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
			e2.printStackTrace();
		}
		float f5 = 0.0F;
		float f6 = 0.0F;
		if(!e.isRiding()) {
			f5 = e.prevLimbSwingAmount + (e.limbSwingAmount - e.prevLimbSwingAmount) * partialTicks;
			f6 = e.limbSwing - e.limbSwingAmount * (1.0F - partialTicks);

			if(e.isChild()) {
				f6 *= 3.0F;
			}

			if(f5 > 1.0F) {
				f5 = 1.0F;
			}
			f2 = f1 - f; // Forge: Fix MC-1207
		}
		model.setLivingAnimations(e, f6, f5, partialTicks);
		model.setRotationAngles(f6, f5, f8, f2, f7, f4, e);

		if(ModelRendererUtil.rGetEntityTexture == null){
			ModelRendererUtil.rGetEntityTexture = ReflectionHelper.findMethod(Render.class, "getEntityTexture", "func_110775_a", Entity.class);
		}
		ResourceLocation r = ResourceManager.turbofan_blades_tex;
		try {
			r = (ResourceLocation) ModelRendererUtil.rGetEntityTexture.invoke(render, e);
			if(r == null)
				r = ResourceManager.turbofan_blades_tex;
		} catch(final IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		for(final ModelRenderer renderer : model.boxList) {
			spawnParticles(e.world, e, e.posX, e.posY, e.posZ, f4, renderer, r);
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.enableCull();
		GL11.glPopMatrix();
	}
	
	public static void spawnLightningDisintegrateParticles(final Entity e, final Vec3 hitPos) {
		final Render<Entity> eRenderer = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(e);
		if(eRenderer instanceof RenderLivingBase && e instanceof EntityLivingBase) {
			spawnLightningDisintegrateParticles((EntityLivingBase) e, ((RenderLivingBase) eRenderer), hitPos, MainRegistry.proxy.partialTicks());
		}
	}
	
	@SuppressWarnings({ "deprecation" })
	public static void spawnLightningDisintegrateParticles(final EntityLivingBase e, final RenderLivingBase render, final Vec3 hitPos, final float partialTicks) {
		final ModelBase model = render.getMainModel();
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GlStateManager.disableCull();
		GlStateManager.enableRescaleNormal();
		//So basically we're just going to copy vanialla methods so the 
		model.swingProgress = e.getSwingProgress(partialTicks);
		final boolean shouldSit = e.isRiding() && (e.getRidingEntity() != null && e.getRidingEntity().shouldRiderSit());
		model.isRiding = shouldSit;
		model.isChild = e.isChild();
		float f = interpolateRotation(e.prevRenderYawOffset, e.renderYawOffset, partialTicks);
		final float f1 = interpolateRotation(e.prevRotationYawHead, e.rotationYawHead, partialTicks);
		float f2 = f1 - f;
		if(shouldSit && e.getRidingEntity() instanceof EntityLivingBase elivingbase) {
            f = interpolateRotation(elivingbase.prevRenderYawOffset, elivingbase.renderYawOffset, partialTicks);
			f2 = f1 - f;
			float f3 = MathHelper.wrapDegrees(f2);

			if(f3 < -85.0F) {
				f3 = -85.0F;
			}

			if(f3 >= 85.0F) {
				f3 = 85.0F;
			}

			f = f1 - f3;

			if(f3 * f3 > 2500.0F) {
				f += f3 * 0.2F;
			}

			f2 = f1 - f;
		}

		final float f7 = e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * partialTicks;
		//renderLivingAt(e, x, y, z);
		final float f8 = e.ticksExisted + partialTicks;
		GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
		//if(rPreRenderCallback == null){
		//	rPreRenderCallback = ReflectionHelper.findMethod(RenderLivingBase.class, "preRenderCallback", "func_77041_b", EntityLivingBase.class, float.class);
		//}
		if(ModelRendererUtil.rPrepareScale == null){
			ModelRendererUtil.rPrepareScale = ReflectionHelper.findMethod(RenderLivingBase.class, "prepareScale", "func_188322_c", EntityLivingBase.class, float.class);
		}
		//float f4 = prepareScale(e, partialTicks, render);
		float f4 = 0.0625F;
		try {
			f4 = (float) ModelRendererUtil.rPrepareScale.invoke(render, e, partialTicks);
		} catch(final IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
			e2.printStackTrace();
		}
		float f5 = 0.0F;
		float f6 = 0.0F;
		if(!e.isRiding()) {
			f5 = e.prevLimbSwingAmount + (e.limbSwingAmount - e.prevLimbSwingAmount) * partialTicks;
			f6 = e.limbSwing - e.limbSwingAmount * (1.0F - partialTicks);

			if(e.isChild()) {
				f6 *= 3.0F;
			}

			if(f5 > 1.0F) {
				f5 = 1.0F;
			}
			f2 = f1 - f; // Forge: Fix MC-1207
		}
		model.setLivingAnimations(e, f6, f5, partialTicks);
		model.setRotationAngles(f6, f5, f8, f2, f7, f4, e);

		if(ModelRendererUtil.rGetEntityTexture == null){
			ModelRendererUtil.rGetEntityTexture = ReflectionHelper.findMethod(Render.class, "getEntityTexture", "func_110775_a", Entity.class);
		}
		ResourceLocation r = ResourceManager.turbofan_blades_tex;
		try {
			r = (ResourceLocation) ModelRendererUtil.rGetEntityTexture.invoke(render, e);
			if(r == null)
				r = ResourceManager.turbofan_blades_tex;
		} catch(final IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		int trailCount = 10;
		for(final ModelRenderer renderer : model.boxList) {
			trailCount = spawnLightningParticles(e.world, e, e.posX, e.posY, e.posZ, f4, renderer, r, hitPos, trailCount);
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.enableCull();
		GL11.glPopMatrix();
	}

	public static void spawnParticles(final World world, final EntityLivingBase ent, final double x, final double y, final double z, final float scale, final ModelRenderer render, final ResourceLocation tex) {
		if(render.isHidden || !render.showModel)
			return;
		GL11.glPushMatrix();
		doTransforms(render, scale);
		if(render.childModels != null)
			for(final ModelRenderer renderer : render.childModels) {
				spawnParticles(world, ent, x, y, z, scale, renderer, tex);
			}
		for(final ModelBox cube : render.cubeList) {
			GL11.glPushMatrix();
			final float cubeMidX = (cube.posX1 + (cube.posX2-cube.posX1)*0.5F)*scale;
			final float cubeMidY = (cube.posY1 + (cube.posY2-cube.posY1)*0.5F)*scale;
			final float cubeMidZ = (cube.posZ1 + (cube.posZ2-cube.posZ1)*0.5F)*scale;
			GL11.glTranslated(cubeMidX, cubeMidY, cubeMidZ);
			GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
			final float[] matrix = new float[16];
			ClientProxy.AUX_GL_BUFFER.get(matrix);
			ClientProxy.AUX_GL_BUFFER.rewind();
			GL11.glPopMatrix();
			
			final double pX = x + matrix[12];
			final double pY = y + matrix[13];
			final double pZ = z + matrix[14];
			matrix[12] = 0;
			matrix[13] = 0;
			matrix[14] = 0;
			final ParticleGluonDisintegration p = new ParticleGluonDisintegration(world, pX, pY, pZ, cube, matrix, tex, cubeMidX, cubeMidY, cubeMidZ, scale);
			final Vec3d motion = new Vec3d(x, y + ent.getEyeHeight()/2, z).subtract(new Vec3d(pX, pY, pZ)).normalize().scale(0.25)
					.add(new Vec3d(world.rand.nextFloat() -0.5F, world.rand.nextFloat() -0.5F, world.rand.nextFloat() -0.5F));
			p.motion(motion);
			Minecraft.getMinecraft().effectRenderer.addEffect(p);
		}

		GL11.glPopMatrix();
	}
	
	public static int spawnLightningParticles(final World world, final EntityLivingBase ent, final double x, final double y, final double z, final float scale, final ModelRenderer render, final ResourceLocation tex, final Vec3 hitPos, int trailCount) {
		if(render.isHidden || !render.showModel)
			return trailCount;
		GL11.glPushMatrix();
		doTransforms(render, scale);
		if(render.childModels != null)
			for(final ModelRenderer renderer : render.childModels) {
				trailCount = spawnLightningParticles(world, ent, x, y, z, scale, renderer, tex, hitPos, trailCount);
			}
		for(final ModelBox cube : render.cubeList) {
			GL11.glPushMatrix();
			final float cubeMidX = (cube.posX1 + (cube.posX2-cube.posX1)*0.5F)*scale;
			final float cubeMidY = (cube.posY1 + (cube.posY2-cube.posY1)*0.5F)*scale;
			final float cubeMidZ = (cube.posZ1 + (cube.posZ2-cube.posZ1)*0.5F)*scale;
			GL11.glTranslated(cubeMidX, cubeMidY, cubeMidZ);
			GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
			final float[] matrix = new float[16];
			ClientProxy.AUX_GL_BUFFER.get(matrix);
			ClientProxy.AUX_GL_BUFFER.rewind();
			GL11.glPopMatrix();
			
			final double pX = x + matrix[12];
			final double pY = y + matrix[13];
			final double pZ = z + matrix[14];
			matrix[12] = 0;
			matrix[13] = 0;
			matrix[14] = 0;
			final int numLTrails = MathHelper.clamp(trailCount, 0, 2);
			final ParticleLightningGib p = new ParticleLightningGib(world, pX, pY, pZ, cube, matrix, tex, cubeMidX, cubeMidY, cubeMidZ, scale, numLTrails);
			trailCount -= numLTrails;
			final Vec3d motion = hitPos.toVec3d().normalize().add(0, 0.2, 0).scale(1.2)
					.add(new Vec3d(world.rand.nextFloat()-0.5F, world.rand.nextFloat()-0.5F, world.rand.nextFloat()-0.5F).scale(0.5));
			p.motion(motion);
			Minecraft.getMinecraft().effectRenderer.addEffect(p);
		}

		GL11.glPopMatrix();
		return trailCount;
	}

	public static void doTransforms(final ModelRenderer m, final float scale) {
		GlStateManager.translate(m.offsetX, m.offsetY, m.offsetZ);
		if(m.rotateAngleX == 0.0F && m.rotateAngleY == 0.0F && m.rotateAngleZ == 0.0F) {
			if(m.rotationPointX == 0.0F && m.rotationPointY == 0.0F && m.rotationPointZ == 0.0F) {
			} else {
				GlStateManager.translate(m.rotationPointX * scale, m.rotationPointY * scale, m.rotationPointZ * scale);
			}
		} else {
			GlStateManager.translate(m.rotationPointX * scale, m.rotationPointY * scale, m.rotationPointZ * scale);
			if(m.rotateAngleZ != 0.0F) {
				GlStateManager.rotate(m.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
			}
			if(m.rotateAngleY != 0.0F) {
				GlStateManager.rotate(m.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
			}
			if(m.rotateAngleX != 0.0F) {
				GlStateManager.rotate(m.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
			}
		}
	}

	protected static float interpolateRotation(final float prevYawOffset, final float yawOffset, final float partialTicks) {
		float f;
		
		for(f = yawOffset - prevYawOffset; f < -180.0F; f += 360.0F) {
        }

		while(f >= 180.0F) {
			f -= 360.0F;
		}

		return prevYawOffset + partialTicks * f;
	}
}
