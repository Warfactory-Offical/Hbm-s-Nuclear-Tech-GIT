package com.hbm.entity.projectile;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import com.hbm.config.CompatibilityConfig;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityRocket extends Entity implements IProjectile {

	private static final DataParameter<Boolean> CRITICAL = EntityDataManager.createKey(EntityBullet.class, DataSerializers.BOOLEAN);
	
	private int field_145791_d = -1;
    private int field_145792_e = -1;
    private int field_145789_f = -1;
    public double gravity = 0.0D;
    private Block field_145790_g;
    private int inData;
    private boolean inGround;
    /** 1 if the player can pick up the arrow */
    public int canBePickedUp;
    /** Seems to be some sort of timer for animating an arrow. */
    public int arrowShake;
    /** The owner of this arrow. */
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    private double damage = 2.0D;
    /** The amount of knockback an arrow applies when it hits a mob. */
    private int knockbackStrength;
	public int explosionSize = 5;
    
	public EntityRocket(final World worldIn) {
		super(worldIn);
		if(worldIn.isRemote)
			setRenderDistanceWeight(10.0D);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityRocket(final World w, final double x, final double y, final double z){
		this(w);
		this.setPosition(x, y, z);
	}
	
	public EntityRocket(final World w, final EntityLivingBase shooter, final EntityLivingBase shootingAt, final float velocity, final float inaccuracy){
		this(w);
		this.shootingEntity = shooter;

        if (shooter instanceof EntityPlayer)
        {
            this.canBePickedUp = 1;
        }

        this.posY = shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D;
        final double d0 = shootingAt.posX - shooter.posX;
        final double d1 = shootingAt.getEntityBoundingBox().minY + shootingAt.height / 3.0F - this.posY;
        final double d2 = shootingAt.posZ - shooter.posZ;
        final double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            final float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            final float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            final double d4 = d0 / d3;
            final double d5 = d2 / d3;
            this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f2, f3);
            final float f4 = (float)d3 * 0.2F;
            this.shoot(d0, d1 + f4, d2, velocity, inaccuracy);
        }
	}
	
	public EntityRocket(final World w, final EntityLivingBase shooter, final float velocity, final EnumHand hand){
		this(w);
		this.shootingEntity = shooter;

        if (shooter instanceof EntityPlayer)
        {
            this.canBePickedUp = 1;
        }

        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        if(hand == EnumHand.MAIN_HAND){
        	this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
            this.posY -= 0.10000000149011612D;
            this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        } else {
        	this.posX += MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
            this.posY -= 0.10000000149011612D;
            this.posZ += MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
        }
        
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI);
        this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.shoot(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 1.0F);
	}
	
	public EntityRocket(final World world, final int x, final int y, final int z, final double mx, final double my, final double mz, final double grav) {
        super(world);
    	this.posX = x + 0.5F;
    	this.posY = y + 0.5F;
    	this.posZ = z + 0.5F;
    	
    	this.motionX = mx;
    	this.motionY = my;
    	this.motionZ = mz;
    	
    	this.gravity = grav;
    }

	@Override
	public void shoot(double x, double y, double z, final float velocity, final float inaccuracy) {
		final float f2 = MathHelper.sqrt(x * x + y * y + z * z);
        x /= f2;
        y /= f2;
        z /= f2;
        x += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        y += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        z += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
        x *= velocity;
        y *= velocity;
        z *= velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        final float f3 = MathHelper.sqrt(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
		
	}
	
	@Override
	public void setPositionAndRotationDirect(final double x, final double y, final double z, final float yaw, final float pitch, final int posRotationIncrements, final boolean teleport) {
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}
	
	@Override
	public void setVelocity(final double x, final double y, final double z) {
		this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            final float f = MathHelper.sqrt(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            //this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
        }
        final BlockPos pos = new BlockPos(this.field_145791_d, this.field_145792_e, this.field_145789_f);
        final IBlockState blockstate = this.world.getBlockState(pos);

        if (blockstate.getMaterial() != Material.AIR)
        {
            final AxisAlignedBB axisalignedbb = blockstate.getCollisionBoundingBox(this.world, pos);

            if (axisalignedbb != null && axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(pos).contains(new Vec3d(this.posX, this.posY, this.posZ)))
            {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0)
        {
            --this.arrowShake;
        }

        if (this.inGround)
        {

            if (!this.world.isRemote)
            {
            	ExplosionLarge.explode(world, posX, posY, posZ, explosionSize, true, false, true);
            }
        	this.setDead();
        }
        else
        {
            ++this.ticksInAir;
            Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec31, vec3, false, true, false);
            vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                vec3 = new Vec3d(movingobjectposition.hitVec.x, movingobjectposition.hitVec.y, movingobjectposition.hitVec.z);
            }

            Entity entity = null;
            final List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
            double d0 = 0.0D;
            int i;
            float f1;

            for (i = 0; i < list.size(); ++i)
            {
                final Entity entity1 = list.get(i);

                if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
                {
                    f1 = 0.3F;
                    final AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().grow(f1);
                    final RayTraceResult movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);

                    if (movingobjectposition1 != null)
                    {
                        final double d1 = vec31.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new RayTraceResult(entity);
            }

            if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer entityplayer)
            {

                if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
                {
                    movingobjectposition = null;
                }
            }

            float f2;
            float f4;

            if (movingobjectposition != null && CompatibilityConfig.isWarDim(world))
            {
                if (movingobjectposition.entityHit != null)
                {
                    f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    int k = MathHelper.ceil(f2 * this.damage);

                    if (this.getIsCritical())
                    {
                        k += this.rand.nextInt(k / 2 + 2);
                    }

                    DamageSource damagesource = null;

                    if (this.shootingEntity == null)
                    {
                        damagesource = DamageSource.causeIndirectMagicDamage(this, this);
                    }
                    else
                    {
                        damagesource = DamageSource.causeIndirectMagicDamage(this, this);
                    }

                    if (this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman))
                    {
                        movingobjectposition.entityHit.setFire(5);
                    }

                    if (movingobjectposition.entityHit.attackEntityFrom(damagesource, k))
                    {
                        if (movingobjectposition.entityHit instanceof EntityLivingBase entitylivingbase)
                        {

                            if (this.knockbackStrength > 0)
                            {
                                f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

                                if (f4 > 0.0F)
                                {
                                    movingobjectposition.entityHit.addVelocity(this.motionX * this.knockbackStrength * 0.6000000238418579D / f4, 0.1D, this.motionZ * this.knockbackStrength * 0.6000000238418579D / f4);
                                }
                            }

                            if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase)
                            {
                            	EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
								EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity, entitylivingbase);
                            }

                            if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                            {
                                ((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                            }
                        }

                        if (!(movingobjectposition.entityHit instanceof EntityEnderman))
                        {
                            if (!this.world.isRemote)
                            {
                            	ExplosionLarge.explode(world, posX, posY, posZ, explosionSize, true, false, true);
                            }
                        	this.setDead();
                        }
                    }
                    else
                    {
                        if (!this.world.isRemote)
                        {
                        	ExplosionLarge.explode(world, posX, posY, posZ, explosionSize, true, false, true);
                        }
                    	this.setDead();
                    }
                }
                else
                {
                    this.field_145791_d = movingobjectposition.getBlockPos().getX();
                    this.field_145792_e = movingobjectposition.getBlockPos().getY();
                    this.field_145789_f = movingobjectposition.getBlockPos().getZ();
                    final BlockPos newPos = new BlockPos(field_145791_d, field_145792_e, field_145789_f);
                    final IBlockState newState = world.getBlockState(newPos);
                    this.field_145790_g = newState.getBlock();
                    this.inData = newState.getBlock().getMetaFromState(newState);
                    this.motionX = ((float)(movingobjectposition.hitVec.x - this.posX));
                    this.motionY = ((float)(movingobjectposition.hitVec.y - this.posY));
                    this.motionZ = ((float)(movingobjectposition.hitVec.z - this.posZ));
                    f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    this.posX -= this.motionX / f2 * 0.05000000074505806D;
                    this.posY -= this.motionY / f2 * 0.05000000074505806D;
                    this.posZ -= this.motionZ / f2 * 0.05000000074505806D;
                    this.inGround = true;
                    this.arrowShake = 7;
                    this.setIsCritical(false);

                    if (newState.getMaterial() != Material.AIR)
                    {
                        this.field_145790_g.onEntityCollision(this.world, newPos, newState, this);
                    }
                }
            }

            if (true)
            {
                for (i = 0; i < 4; ++i)
                {
                	this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, /*0, 0, 0 this.posX + this.motionX * (double)i / 4.0D, this.posY + this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D,*/ -this.motionX/4, -this.motionY/4, -this.motionZ/4);
                	//this.worldObj.spawnEntityInWorld(new EntityGasFX(worldObj, this.posX - motionX/4*i, this.posY - motionY/4*i, this.posZ - motionZ/4*i, 0, 0, 0));
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

            //for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            {
            }

            /*while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }*/

            //this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            //this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f3 = 0.99F;
            f1 = 0.05F;

            if (this.isInWater())
            {
                for (int l = 0; l < 4; ++l)
                {
                    f4 = 0.25F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f4, this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY, this.motionZ);
                }

                f3 = 0.8F;
            }

            if (this.isWet())
            {
                this.extinguish();
            }

            this.motionX *= f3;
            this.motionY *= f3;
            this.motionZ *= f3;
            this.motionY -= gravity;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }

		if (this.ticksExisted > 250)
			this.setDead();
	}

	@Override
	protected void entityInit() {
		this.getDataManager().register(CRITICAL, false);
	}

	@Override
	protected void readEntityFromNBT(final NBTTagCompound compound) {
		this.field_145791_d = compound.getShort("xTile");
        this.field_145792_e = compound.getShort("yTile");
        this.field_145789_f = compound.getShort("zTile");
        this.ticksInGround = compound.getShort("life");
        this.field_145790_g = Block.getBlockById(compound.getByte("inTile") & 255);
        this.inData = compound.getByte("inData") & 255;
        this.arrowShake = compound.getByte("shake") & 255;
        this.inGround = compound.getByte("inGround") == 1;

        if (compound.hasKey("damage", 99))
        {
            this.damage = compound.getDouble("damage");
        }

        if (compound.hasKey("pickup", 99))
        {
            this.canBePickedUp = compound.getByte("pickup");
        }
        else if (compound.hasKey("player", 99))
        {
            this.canBePickedUp = compound.getBoolean("player") ? 1 : 0;
        }
	}

	@Override
	protected void writeEntityToNBT(final NBTTagCompound compound) {
		compound.setShort("xTile", (short)this.field_145791_d);
		compound.setShort("yTile", (short)this.field_145792_e);
		compound.setShort("zTile", (short)this.field_145789_f);
		compound.setShort("life", (short)this.ticksInGround);
		compound.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145790_g));
		compound.setByte("inData", (byte)this.inData);
		compound.setByte("shake", (byte)this.arrowShake);
		compound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		compound.setByte("pickup", (byte)this.canBePickedUp);
		compound.setDouble("damage", this.damage);
		
	}
	
	@Override
	public void onCollideWithPlayer(final EntityPlayer entityIn) {
		if (!this.world.isRemote && this.inGround && this.arrowShake <= 0)
        {
            boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityIn.capabilities.isCreativeMode;

            if (this.canBePickedUp == 1 && !entityIn.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.gun_rpg_ammo, 1)))
            {
                flag = false;
            }

            if (flag)
            {
            	entityIn.onItemPickup(this, 1);
                this.setDead();
            }
        }
	}
	
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	public void setDamage(final double damage) {
		this.damage = damage;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public void setKnockbackStrength(final int knockbackStrength) {
		this.knockbackStrength = knockbackStrength;
	}
	
	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}
	
	public void setIsCritical(final boolean crit){
		this.getDataManager().set(CRITICAL, crit);
	}
	
	public boolean getIsCritical(){
		return this.getDataManager().get(CRITICAL);
	}

}
