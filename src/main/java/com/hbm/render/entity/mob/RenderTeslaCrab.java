package com.hbm.render.entity.mob;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.mob.EntityTeslaCrab;
import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.render.misc.BeamPronter;
import com.hbm.render.misc.BeamPronter.EnumBeamType;
import com.hbm.render.misc.BeamPronter.EnumWaveType;
import com.hbm.render.model.ModelTeslaCrab;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderTeslaCrab extends RenderLiving<EntityTeslaCrab> {

	public static final IRenderFactory<EntityTeslaCrab> FACTORY = (RenderManager man) -> {
		return new RenderTeslaCrab(man);
	};

	public RenderTeslaCrab(final RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelTeslaCrab(), 1.0F);
		this.shadowOpaque = 0.0F;
	}

	@Override
	public void doRender(final EntityTeslaCrab entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y + 1, z);

		final double sx = entity.posX;
		final double sy = entity.posY + 1;
		final double sz = entity.posZ;

		for(final double[] target : entity.targets) {

			final double length = Math.sqrt(Math.pow(target[0] - sx, 2) + Math.pow(target[1] - sy, 2) + Math.pow(target[2] - sz, 2));

			BeamPronter.prontBeam(Vec3.createVectorHelper(target[0] - sx, target[1] - sy, target[2] - sz), EnumWaveType.RANDOM, EnumBeamType.SOLID, 0x0051C4, 0x606060, (int) (entity.world.getTotalWorldTime() % 1000 + 1), (int) (length * 5), 0.125F, 2, 0.03125F);
		}

		GL11.glPopMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(final EntityTeslaCrab entity) {
		return ResourceManager.teslacrab_tex;
	}

}
