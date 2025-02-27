package com.hbm.render.entity;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.hbm.entity.effect.EntityFalloutRain;
import com.hbm.lib.RefStrings;
import com.hbm.render.RenderHelper;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;

public class RenderFallout extends Render<EntityFalloutRain> {

	private final Minecraft mc;
	private final Random random = new Random();
	float[] rainXCoords;
	float[] rainYCoords;
	private int rendererUpdateCount;
	long lastTime = System.nanoTime();
	private static final ResourceLocation falloutTexture = new ResourceLocation(RefStrings.MODID, "textures/entity/fallout.png");
	
	public RenderFallout(final RenderManager renderManager) {
        super(renderManager);
        this.mc = Minecraft.getMinecraft();
        this.rainXCoords = new float[1024];
        this.rainYCoords = new float[1024];
        for (int i = 0; i < 32; ++i)
        {
            for (int j = 0; j < 32; ++j)
            {
                final float f = (float)(j - 16);
                final float f1 = (float)(i - 16);
                final float f2 = MathHelper.sqrt(f * f + f1 * f1);
                this.rainXCoords[i << 5 | j] = -f1 / f2;
                this.rainYCoords[i << 5 | j] = f / f2;
            }
        }
    }
   
	@Override
	public boolean shouldRender(final EntityFalloutRain livingEntity, final ICamera camera, final double camX, final double camY, final double camZ) {
		return true;
	}
    
    @Override
    public void doRender(final EntityFalloutRain entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
        GL11.glPushMatrix();
        GlStateManager.disableCull();
        //Drillgon200: It doesn't work when I use GLStateManager...
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderHelper.resetColor();
        
        GlStateManager.disableLighting();
        final Entity ent = this.mc.getRenderViewEntity();
        final Vec3 vector = Vec3.createVectorHelper(ent.posX - entity.posX,
                ent.posY - entity.posY, ent.posZ - entity.posZ);
       
        final double d = vector.length();
       
        if (d <= entity.getScale()) {
            rendererUpdateCount++;
            final long time = System.nanoTime();
            final float t = (time - lastTime) / 50000000;
            if (t <= 1.0F)
                renderRainSnow(t);
            else
                renderRainSnow(1.0F);
 
            lastTime = time;
        }
        GlStateManager.enableCull();
        GL11.glPopMatrix();
    }
   
    protected void renderRainSnow(final float partialTick) {
        final MutableBlockPos pos = new BlockPos.MutableBlockPos();
        final float f1 = 1;
 
        if (f1 > 0.0F) {
 
            if (this.rainXCoords == null) {
                this.rainXCoords = new float[1024];
                this.rainYCoords = new float[1024];
 
                for (int i = 0; i < 32; ++i) {
                    for (int j = 0; j < 32; ++j) {
                        final float f2 = j - 16;
                        final float f3 = i - 16;
                        final float f4 = MathHelper.sqrt(f2 * f2 + f3 * f3);
                        this.rainXCoords[i << 5 | j] = -f3 / f4;
                        this.rainYCoords[i << 5 | j] = f2 / f4;
                    }
                }
            }
 
            final Entity entitylivingbase = this.mc.getRenderViewEntity();
            final WorldClient worldclient = this.mc.world;
            final int k2 = MathHelper.floor(entitylivingbase.posX);
            final int l2 = MathHelper.floor(entitylivingbase.posY);
            final int i3 = MathHelper.floor(entitylivingbase.posZ);
            final Tessellator tessellator = Tessellator.getInstance();
            GlStateManager.disableCull();
            GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
            final double d0 = entitylivingbase.lastTickPosX
                    + (entitylivingbase.posX - entitylivingbase.lastTickPosX) * partialTick;
            final double d1 = entitylivingbase.lastTickPosY
                    + (entitylivingbase.posY - entitylivingbase.lastTickPosY) * partialTick;
            final double d2 = entitylivingbase.lastTickPosZ
                    + (entitylivingbase.posZ - entitylivingbase.lastTickPosZ) * partialTick;
            final int k = MathHelper.floor(d1);
            byte b0 = 5;
 
            if (this.mc.gameSettings.fancyGraphics) {
                b0 = 10;
            }
 
            byte b1 = -1;
            final float f5 = this.rendererUpdateCount + partialTick;
 
            if (this.mc.gameSettings.fancyGraphics) {
                b0 = 10;
            }
 
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderHelper.resetColor();
            for (int l = i3 - b0; l <= i3 + b0; ++l) {
                for (int i1 = k2 - b0; i1 <= k2 + b0; ++i1) {
                    final int j1 = (l - i3 + 16) * 32 + i1 - k2 + 16;
                    final float f6 = this.rainXCoords[j1] * 0.5F;
                    final float f7 = this.rainYCoords[j1] * 0.5F;
                    pos.setPos(i1, 50, l);
                    final Biome biomegenbase = worldclient.getBiomeForCoordsBody(pos);
 
                    if (true) {
                        final int k1 = worldclient.getPrecipitationHeight(pos).getY();
                        int l1 = l2 - b0;
                        int i2 = l2 + b0;
 
                        if (l1 < k1) {
                            l1 = k1;
                        }
 
                        if (i2 < k1) {
                            i2 = k1;
                        }
 
                        final float f8 = 1.0F;
                        int j2 = k1;
 
                        if (k1 < k) {
                            j2 = k;
                        }
 
                        if (l1 != i2) {
                            pos.setY(l1);
                            this.random.setSeed((long) i1 * i1 * 3121 + i1 * 45238971L ^ (long) l * l * 418711 + l * 13761L);
                            biomegenbase.getTemperature(pos);
                            final float f10;
                            final double d4;
 
                            {
                                if (b1 != 1) {
                                    if (b1 >= 0) {
                                        tessellator.draw();
                                    }
                                    b1 = 1;
                                    this.mc.getTextureManager().bindTexture(RenderFallout.falloutTexture);
                                    tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                                }
 
                                f10 = ((this.rendererUpdateCount & 511) + partialTick) / 512.0F;
                                final float f16 = this.random.nextFloat() + f5 * 0.01F * (float) this.random.nextGaussian();
                                final float f11 = this.random.nextFloat() + f5 * (float) this.random.nextGaussian() * 0.001F;
                                d4 = i1 + 0.5F - entitylivingbase.posX;
                                final double d5 = l + 0.5F - entitylivingbase.posZ;
                                final float f14 = MathHelper.sqrt(d4 * d4 + d5 * d5) / b0;
                                final float f15 = 1.0F;
                                final BufferBuilder buf = tessellator.getBuffer();
                                worldclient.getLightBrightness(pos.setPos(i1, j2, l));
                            //  buf.putBrightness4(bright, bright, bright, bright);
                                buf.setTranslation(-d0, -d1, -d2);
                               
                                buf.pos(i1 - f6 + 0.5D, l1, l - f7 + 0.5D).tex(0.0F * f8 + f16, l1 * f8 / 4.0F + f10 * f8 + f11).color(f15, f15, f15, ((1.0F - f14 * f14) * 0.3F + 0.5F) * f1).endVertex();
                                buf.pos(i1 + f6 + 0.5D, l1, l + f7 + 0.5D).tex(f8 + f16, l1 * f8 / 4.0F + f10 * f8 + f11).color(f15, f15, f15, ((1.0F - f14 * f14) * 0.3F + 0.5F) * f1).endVertex();
                                buf.pos(i1 + f6 + 0.5D, i2, l + f7 + 0.5D).tex(f8 + f16, i2 * f8 / 4.0F + f10 * f8 + f11).color(f15, f15, f15, ((1.0F - f14 * f14) * 0.3F + 0.5F) * f1).endVertex();
                                buf.pos(i1 - f6 + 0.5D, i2, l - f7 + 0.5D).tex(0.0F * f8 + f16, i2 * f8 / 4.0F + f10 * f8 + f11).color(f15, f15, f15, ((1.0F - f14 * f14) * 0.3F + 0.5F) * f1).endVertex();
                                buf.setTranslation(0.0D, 0.0D, 0.0D);
                            }
                        }
                    }
                }
            }
 
            if (b1 >= 0) {
                tessellator.draw();
                // System.out.println("Fired!");
            }
 
            GlStateManager.enableCull();
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
            // this.disableLightmap((double)partialTick);
        }
    }
 
    @Override
    protected ResourceLocation getEntityTexture(final EntityFalloutRain entity) {
        return null;
    }

}
