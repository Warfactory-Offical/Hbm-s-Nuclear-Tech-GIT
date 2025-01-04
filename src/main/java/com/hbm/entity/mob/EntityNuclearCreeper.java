package com.hbm.entity.mob;

import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.entity.mob.ai.EntityAINuclearCreeperSwell;
import com.hbm.interfaces.IRadiationImmune;
import com.hbm.items.ModItems;
import com.hbm.lib.ModDamageSource;
import com.hbm.main.AdvancementManager;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ItemStackUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityNuclearCreeper extends EntityMob implements IRadiationImmune {
    public static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(EntityNuclearCreeper.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityNuclearCreeper.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(EntityNuclearCreeper.class, DataSerializers.BOOLEAN);
    /**
     * Time when this creeper was last in an active state (Messed up code here,
     * probably causes creeper animation to go weird)
     */
    private int lastActiveTime;
    /**
     * The amount of time since the creeper was close enough to the player to
     * ignite
     */
    private int timeSinceIgnited;
    private int fuseTime = 75;
    /**
     * Explosion radius for this creeper.
     */
    private int explosionRadius = 20;

    public EntityNuclearCreeper(World p_i1733_1_) {
        super(p_i1733_1_);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAINuclearCreeperSwell(this));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(4, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityOcelot.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        if (source == ModDamageSource.radiation || source == ModDamageSource.mudPoisoning) {
            this.heal(amount);
            return false;
        }

        return super.attackEntityFrom(source, amount);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        super.dropFewItems(wasRecentlyHit, lootingModifier);

        if (rand.nextInt(3) == 0)
            this.dropItem(ModItems.coin_creeper, 1);
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    @Override
    public void fall(float distance, float damageMultiplier) {
        super.fall(distance, damageMultiplier);
        this.timeSinceIgnited = (int) (this.timeSinceIgnited + distance * 1.5F);

        if (this.timeSinceIgnited > this.fuseTime - 5) {
            this.timeSinceIgnited = this.fuseTime - 5;
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(STATE, -1);
        this.dataManager.register(POWERED, Boolean.FALSE);
        this.dataManager.register(IGNITED, Boolean.FALSE);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        if (this.dataManager.get(POWERED)) {
            compound.setBoolean("powered", true);
        }

        compound.setShort("Fuse", (short) this.fuseTime);
        compound.setByte("ExplosionRadius", (byte) this.explosionRadius);
        compound.setBoolean("ignited", this.hasIgnited());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.dataManager.set(POWERED, compound.getBoolean("powered"));

        if (compound.hasKey("Fuse", 99)) {
            this.fuseTime = compound.getShort("Fuse");
        }

        if (compound.hasKey("ExplosionRadius", 99)) {
            this.explosionRadius = compound.getByte("ExplosionRadius");
        }

        if (compound.getBoolean("ignited")) {
            this.ignite();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        if (this.isDead) {
            this.isDead = false;
            this.heal(10.0F);
        }

        if (this.isEntityAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.hasIgnited()) {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0) {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F * 30 / 75, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }
        ContaminationUtil.radiate(world, posX, posY, posZ, 32, this.timeSinceIgnited + 25);

        super.onUpdate();

        if (this.getHealth() < this.getMaxHealth() && this.ticksExisted % 10 == 0) {
            this.heal(1.0F);
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_CREEPER_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CREEPER_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);

        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(10, 10, 10));
        players.forEach(player -> AdvancementManager.grantAchievement(player, AdvancementManager.bossCreeper));
        if (damageSource.getTrueSource() instanceof EntitySkeleton ||

                (damageSource.isProjectile() && damageSource.getImmediateSource() instanceof EntityArrow &&
                        ((EntityArrow) damageSource.getImmediateSource()).shootingEntity == null)) {

            int dropChance = rand.nextInt(11);
            int dropAmount = rand.nextInt(3);

            Map<Integer, Runnable> dropLogic = new HashMap<>();
            dropLogic.put(0, () -> dropNugget(ModItems.nugget_u235, dropAmount));
            dropLogic.put(1, () -> dropNugget(ModItems.nugget_pu238, dropAmount));
            dropLogic.put(2, () -> dropNugget(ModItems.nugget_pu239, dropAmount));
            dropLogic.put(3, () -> dropNugget(ModItems.nugget_neptunium, dropAmount));
            dropLogic.put(4, () -> dropItem(ModItems.man_core, 1));
            dropLogic.put(5, () -> {
                dropItem(ModItems.sulfur, dropAmount * 2);
                dropItem(ModItems.niter, dropAmount * 2);
            });
            dropLogic.put(6, () -> dropItem(ModItems.syringe_awesome, 1));
            dropLogic.put(7, () -> dropItem(ModItems.fusion_core, 1));
            dropLogic.put(8, () -> dropItem(ModItems.syringe_metal_stimpak, 1));
            dropLogic.put(9, this::dropT45Armor);
            dropLogic.put(10, () -> dropItem(ModItems.ammo_nuke, 1));
            dropLogic.getOrDefault(dropChance, () -> {
            }).run();
        }
    }

    private void dropNugget(ItemStack nugget, int amount) {
        this.entityDropItem(ItemStackUtil.itemStackFrom(nugget, amount), 0);
    }

    private void dropT45Armor() {
        final Item[] t45ArmorParts = {
                ModItems.t45_helmet,
                ModItems.t45_plate,
                ModItems.t45_legs,
                ModItems.t45_boots
        };
        int randomIndex = rand.nextInt(t45ArmorParts.length);
        dropItem(t45ArmorParts[randomIndex], 1);
        dropItem(ModItems.fusion_core, 1);
    }


    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_) {
        return true;
    }

    /**
     * Returns true if the creeper is powered by a lightning bolt.
     */
    public boolean getPowered() {
        return this.dataManager.get(POWERED);
    }

    public void setPowered(boolean power) {
        this.dataManager.set(POWERED, power);
    }

    /**
     * Params: (Float)Render tick. Returns the intensity of the creeper's flash
     * when it is ignited.
     */
    @SideOnly(Side.CLIENT)
    public float getCreeperFlashIntensity(float p_70831_1_) {
        return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (this.fuseTime - 2);
    }

    @Override
    protected Item getDropItem() {
        return Item.getItemFromBlock(Blocks.TNT);
    }

    /**
     * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
     */
    public int getCreeperState() {
        return this.dataManager.get(STATE);
    }

    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setCreeperState(int i) {
        this.dataManager.set(STATE, i);
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    @Override
    public void onStruckByLightning(EntityLightningBolt p_70077_1_) {
        super.onStruckByLightning(p_70077_1_);
        this.dataManager.set(POWERED, Boolean.TRUE);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow,
     * gets into the saddle on a pig.
     */
    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.FLINT_AND_STEEL) {
            this.world.playSound(null, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
            player.swingArm(hand);

            if (!this.world.isRemote) {
                this.ignite();
                itemstack.damageItem(1, player);
                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    private void explode() {
        if (!this.world.isRemote) {

            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");

            if (this.getPowered()) {
                EntityNukeTorex.statFac(world, posX, posY, posZ, 70);
                if (flag) {
                    world.spawnEntity(EntityNukeExplosionMK5.statFac(world, 70, posX, posY, posZ));
                } else {
                    ContaminationUtil.radiate(world, posX, posY + 0.5, posZ, 70, 1000, 0, 100, 500);
                }
            } else {
                EntityNukeTorex.statFac(world, posX, posY, posZ, 20);
                if (flag) {
                    world.spawnEntity(EntityNukeExplosionMK5.statFacNoRad(world, 20, posX, posY, posZ));
                } else {
                    ContaminationUtil.radiate(world, posX, posY + 0.5, posZ, 20, 1000, 0, 100, 500);
                }
            }

            this.setDead();
        }
    }

    public boolean hasIgnited() {
        return this.dataManager.get(IGNITED);
    }

    public void ignite() {
        this.dataManager.set(IGNITED, Boolean.TRUE);
    }
}
