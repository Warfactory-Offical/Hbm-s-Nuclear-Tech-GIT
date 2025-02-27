package com.hbm.entity.particle;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityModFX extends Entity {
    public int particleTextureIndexX;
    public int particleTextureIndexY;
    public float particleTextureJitterX;
    public float particleTextureJitterY;
    public int particleMaxAge;
    public float particleScale;
    public float particleGravity;
    /** The red amount of color. Used as a percentage, 1.0 = 255 and 0.0 = 0. */
    public float particleRed;
    /** The green amount of color. Used as a percentage, 1.0 = 255 and 0.0 = 0. */
    public float particleGreen;
    /** The blue amount of color. Used as a percentage, 1.0 = 255 and 0.0 = 0. */
    public float particleBlue;
    /** Particle alpha */
    public float particleAlpha;
    /** The icon field from which the given particle pulls its texture. */
    public TextureAtlasSprite particleTexture;
    public static double interpPosX;
    public static double interpPosY;
    public static double interpPosZ;
    public static final String __OBFID = "CL_00000914";
    float smokeParticleScale;
    public int particleAge;
    public int maxAge;

    public EntityModFX(final World world) {
    	super(world);
    }
    
    protected EntityModFX(final World p_i1218_1_, final double p_i1218_2_, final double p_i1218_4_, final double p_i1218_6_)
    {
        super(p_i1218_1_);
        this.particleAlpha = 1.0F;
        this.setSize(0.2F, 0.2F);
        this.setPosition(p_i1218_2_, p_i1218_4_, p_i1218_6_);
        this.lastTickPosX = p_i1218_2_;
        this.lastTickPosY = p_i1218_4_;
        this.lastTickPosZ = p_i1218_6_;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleTextureJitterX = this.rand.nextFloat() * 3.0F;
        this.particleTextureJitterY = this.rand.nextFloat() * 3.0F;
        this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
        //this.particleMaxAge = (int)(4.0F / (this.rand.nextFloat() * 0.9F + 0.1F));
        this.particleAge = 0;
        this.ignoreFrustumCheck = true;
    }

    @Override
	public double getYOffset() {
		return this.height/2.0F;
	}
    public EntityModFX(final World p_i1219_1_, final double p_i1219_2_, final double p_i1219_4_, final double p_i1219_6_, final double p_i1219_8_, final double p_i1219_10_, final double p_i1219_12_)
    {
        this(p_i1219_1_, p_i1219_2_, p_i1219_4_, p_i1219_6_);
        this.motionX = p_i1219_8_ + (float)(Math.random() * 2.0D - 1.0D) * 0.4F;
        this.motionY = p_i1219_10_ + (float)(Math.random() * 2.0D - 1.0D) * 0.4F;
        this.motionZ = p_i1219_12_ + (float)(Math.random() * 2.0D - 1.0D) * 0.4F;
        final float f = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
        final float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        this.motionX = this.motionX / f1 * f * 0.4000000059604645D;
        this.motionY = this.motionY / f1 * f * 0.4000000059604645D + 0.10000000149011612D;
        this.motionZ = this.motionZ / f1 * f * 0.4000000059604645D;
    }

    public EntityModFX multiplyVelocity(final float p_70543_1_)
    {
        this.motionX *= p_70543_1_;
        this.motionY = (this.motionY - 0.10000000149011612D) * p_70543_1_ + 0.10000000149011612D;
        this.motionZ *= p_70543_1_;
        return this;
    }

    public EntityModFX multipleParticleScaleBy(final float p_70541_1_)
    {
        this.setSize(0.2F * p_70541_1_, 0.2F * p_70541_1_);
        this.particleScale *= p_70541_1_;
        return this;
    }

    public void setRBGColorF(final float p_70538_1_, final float p_70538_2_, final float p_70538_3_)
    {
        this.particleRed = p_70538_1_;
        this.particleGreen = p_70538_2_;
        this.particleBlue = p_70538_3_;
    }

    /**
     * Sets the particle alpha (float)
     */
    public void setAlphaF(final float p_82338_1_)
    {
        this.particleAlpha = p_82338_1_;
    }

    public float getRedColorF()
    {
        return this.particleRed;
    }

    public float getGreenColorF()
    {
        return this.particleGreen;
    }

    public float getBlueColorF()
    {
        return this.particleBlue;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    @Override
	protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
	protected void entityInit() {}

    /**
     * Called to update the entity's position/logic.
     */
    @Override
	public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        //if (this.particleAge++ >= this.particleMaxAge)
        //{
        //    this.setDead();
        //}

        this.motionY -= 0.04D * this.particleGravity;
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }

    
    public void renderParticle(final BufferBuilder buffer, final float partialTicks, final float p_70539_3_, final float p_70539_4_, final float p_70539_5_, final float p_70539_6_, final float p_70539_7_)
    {
        float f6 = this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.0624375F;
        float f8 = this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.0624375F;
        final float f10 = 0.1F * this.particleScale;

        if (this.particleTexture != null)
        {
            f6 = this.particleTexture.getMinU();
            f7 = this.particleTexture.getMaxU();
            f8 = this.particleTexture.getMinV();
            f9 = this.particleTexture.getMaxV();
        }
        final int i = this.getBrightnessForRender();
        final int j = i >> 16 & 65535;
        final int k = i & 65535;
        final float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
        final float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
        final float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
        
        buffer.pos(f11 - p_70539_3_ * f10 - p_70539_6_ * f10, f12 - p_70539_4_ * f10, f13 - p_70539_5_ * f10 - p_70539_7_ * f10).tex(f7, f9).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos(f11 - p_70539_3_ * f10 + p_70539_6_ * f10, f12 + p_70539_4_ * f10, f13 - p_70539_5_ * f10 + p_70539_7_ * f10).tex(f7, f8).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos(f11 + p_70539_3_ * f10 + p_70539_6_ * f10, f12 + p_70539_4_ * f10, f13 + p_70539_5_ * f10 + p_70539_7_ * f10).tex(f6, f8).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos(f11 + p_70539_3_ * f10 - p_70539_6_ * f10, f12 - p_70539_4_ * f10, f13 + p_70539_5_ * f10 - p_70539_7_ * f10).tex(f6, f9).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
    }

    public int getFXLayer()
    {
        return 0;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
	public void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        p_70014_1_.setShort("age", (short)this.particleAge);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
	public void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        this.particleAge = p_70037_1_.getShort("age");
    }

    public void setParticleIcon(final TextureAtlasSprite p_110125_1_)
    {
        if (this.getFXLayer() == 1)
        {
            this.particleTexture = p_110125_1_;
        }
        else
        {
            if (this.getFXLayer() != 2)
            {
                throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
            }

            this.particleTexture = p_110125_1_;
        }
    }

    /**
     * Public method to set private field particleTextureIndex.
     */
    public void setParticleTextureIndex(final int p_70536_1_)
    {
        if (this.getFXLayer() != 0)
        {
            throw new RuntimeException("Invalid call to Particle.setMiscTex");
        }
        else
        {
            this.particleTextureIndexX = p_70536_1_ % 16;
            this.particleTextureIndexY = p_70536_1_ / 16;
        }
    }

    public void nextTextureIndexX()
    {
        ++this.particleTextureIndexX;
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    @Override
    public boolean canBeAttackedWithItem() {
    	return false;
    }

    @Override
	public String toString()
    {
        return this.getClass().getSimpleName() + ", Pos (" + this.posX + "," + this.posY + "," + this.posZ + "), RGBA (" + this.particleRed + "," + this.particleGreen + "," + this.particleBlue + "," + this.particleAlpha + "), Age " + this.particleAge;
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(final double distance)
    {
        return distance < 25000;
    }
}
