package com.hbm.render.entity;

import org.lwjgl.opengl.GL11;
import com.hbm.entity.projectile.EntityFire;
import com.hbm.items.ModItems;
import com.hbm.render.RenderHelper;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFireProjectile extends Render<EntityFire> {

	public static final IRenderFactory<EntityFire> FACTORY = (RenderManager man) -> {return new RenderFireProjectile(man, ModItems.flame_1, 0);};
	
	Item item;
	int meta;
	
	protected RenderFireProjectile(final RenderManager renderManager, final Item item, final int meta) {
		super(renderManager);
		this.item = item;
		this.meta = meta;
	}
	
	@Override
	public void doRender(final EntityFire fx, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {

		if(fx.ticksExisted <= fx.maxAge && fx.ticksExisted >= fx.maxAge / 10 * 9)
		{
			item = ModItems.flame_10;
		}

		if(fx.ticksExisted < fx.maxAge / 10 * 9 && fx.ticksExisted >= fx.maxAge / 10 * 8)
		{
			item = ModItems.flame_9;
		}

		if(fx.ticksExisted < fx.maxAge / 10 * 8 && fx.ticksExisted >= fx.maxAge / 10 * 7)
		{
			item = ModItems.flame_8;
		}

		if(fx.ticksExisted < fx.maxAge / 10 * 7 && fx.ticksExisted >= fx.maxAge / 10 * 6)
		{
			item = ModItems.flame_7;
		}

		if(fx.ticksExisted < fx.maxAge / 10 * 6 && fx.ticksExisted >= fx.maxAge / 10 * 5)
		{
			item = ModItems.flame_6;
		}

		if(fx.ticksExisted < fx.maxAge / 10 * 5 && fx.ticksExisted >= fx.maxAge / 10 * 4)
		{
			item = ModItems.flame_5;
		}

		if(fx.ticksExisted < fx.maxAge / 10 * 4 && fx.ticksExisted >= fx.maxAge / 10 * 3)
		{
			item = ModItems.flame_4;
		}

		if(fx.ticksExisted < fx.maxAge / 10 * 3 && fx.ticksExisted >= fx.maxAge / 10 * 2)
		{
			item = ModItems.flame_3;
		}

		if(fx.ticksExisted < fx.maxAge / 10 * 2 && fx.ticksExisted >= fx.maxAge / 10)
		{
			item = ModItems.flame_2;
		}
		
		if(fx.ticksExisted < fx.maxAge / 10 && fx.ticksExisted >= 0 && !fx.isDead)
		{
			item = ModItems.flame_1;
		}
		
		final TextureAtlasSprite iicon = RenderHelper.getItemTexture(item);

        if (iicon != null)
        {
            GL11.glPushMatrix();
            GlStateManager.disableLighting();
            GL11.glTranslatef((float)x, (float)y, (float)z);
            GlStateManager.enableRescaleNormal();
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glScalef(7.5F, 7.5F, 7.5F);
            GL11.glTranslatef(0.0F, -0.25F, 0.0F);
            this.bindEntityTexture(fx);
            final Tessellator tessellator = Tessellator.getInstance();

            this.func_77026_a(tessellator, iicon);
            GlStateManager.disableRescaleNormal();
            GlStateManager.enableLighting();
            GL11.glPopMatrix();
        }
	}
	
	private void func_77026_a(final Tessellator tes, final TextureAtlasSprite p_77026_2_)
    {
        final float f = p_77026_2_.getMinU();
        final float f1 = p_77026_2_.getMaxU();
        final float f2 = p_77026_2_.getMinV();
        final float f3 = p_77026_2_.getMaxV();
        final float f4 = 1.0F;
        final float f5 = 0.5F;
        final float f6 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        RenderHelper.startDrawingTexturedQuads(tes);
        //Drillgon200: I hope this setNormal isn't needed for anything
        //p_77026_1_.setNormal(0.0F, 1.0F, 0.0F);
        RenderHelper.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3, tes);
        RenderHelper.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3, tes);
        RenderHelper.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2, tes);
        RenderHelper.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2, tes);
        tes.draw();
    }
	
	@Override
	public void doRenderShadowAndFire(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks) {}

	@Override
	protected ResourceLocation getEntityTexture(final EntityFire entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
