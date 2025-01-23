package com.hbm.render.entity;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.projectile.EntityLaser;
import com.hbm.lib.Library;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.render.misc.BeamPronter;
import com.hbm.render.misc.BeamPronter.EnumBeamType;
import com.hbm.render.misc.BeamPronter.EnumWaveType;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderLaser extends Render<EntityLaser> {

	public static final IRenderFactory<EntityLaser> FACTORY = man -> new RenderLaser(man);
	
	protected RenderLaser(final RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(final EntityLaser laser, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		GL11.glPushMatrix();
		
		final EntityPlayer player = laser.world.getPlayerEntityByName(laser.getDataManager().get(EntityLaser.PLAYER_NAME));
		
		if(player != null) {

			
			
			//GL11.glTranslated(x - dX, y - dY, z - dZ);
			
			GL11.glTranslated(x, y, z);
			
			final RayTraceResult pos = Library.rayTrace(player, 100, 1);
			
			final Vec3 skeleton = Vec3.createVectorHelper(pos.hitVec.x - player.posX, pos.hitVec.y - player.posY - player.getEyeHeight(), pos.hitVec.z - player.posZ);
			final int init = (int) -(System.currentTimeMillis() % 360);
			
			//BeamPronter.prontHelix(skeleton, 0, 0, 0, EnumWaveType.SPIRAL, EnumBeamType.LINE, 0x0000ff, 0x8080ff, 0, (int)(skeleton.length() * 5), 0.2F);
	        BeamPronter.prontBeam(skeleton, EnumWaveType.SPIRAL, EnumBeamType.SOLID, 0xff5000, 0xff5000, init, (int) skeleton.length() + 1, 0.1F, 4, 0.05F);
	        BeamPronter.prontBeam(skeleton, EnumWaveType.SPIRAL, EnumBeamType.SOLID, 0xff3000, 0xff3000, init, 1, 0F, 4, 0.05F);
		}
		
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRenderShadowAndFire(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks) {}
	
	@Override
	protected ResourceLocation getEntityTexture(final EntityLaser entity) {
		return null;
	}

}
