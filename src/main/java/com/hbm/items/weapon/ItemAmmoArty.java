package com.hbm.items.weapon;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.effect.EntityMist;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.entity.projectile.EntityArtilleryShell;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.standard.*;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;
import com.hbm.particle.SpentCasing;
import com.hbm.potion.HbmPotion;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class ItemAmmoArty extends Item {

    public static Random rand = new Random();
    public static ArtilleryShell[] itemTypes =	new ArtilleryShell[ /* >>> */ 10 /* <<< */ ];
    /* item types */
    public final int NORMAL = 0;
    public final int CLASSIC = 1;
    public final int EXPLOSIVE = 2;
    public final int MINI_NUKE = 3;
    public final int NUKE = 4;
    public final int PHOSPHORUS = 5;
    public final int MINI_NUKE_MULTI = 6;
    public final int PHOSPHORUS_MULTI = 7;
    public final int CARGO = 8;
    public final int CHLORINE = 9;
//    public final int PHOSGENE = 10;
//    public final int MUSTARD = 11;


    public ItemAmmoArty(String s) {
        this.setTranslationKey(s);
        this.setRegistryName(s);
        this.setHasSubtypes(true);
        this.setCreativeTab(MainRegistry.weaponTab);

        init();
        ModItems.ALL_ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < itemTypes.length; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {

        switch(stack.getItemDamage()) {
            case NORMAL:
                list.add(TextFormatting.YELLOW + "Strength: 10");
                list.add(TextFormatting.YELLOW + "Damage modifier: 3x");
                list.add(TextFormatting.BLUE + "Does not destroy blocks");
                break;
            case CLASSIC:
                list.add(TextFormatting.YELLOW + "Strength: 15");
                list.add(TextFormatting.YELLOW + "Damage modifier: 5x");
                list.add(TextFormatting.BLUE + "Does not destroy blocks");
                break;
            case EXPLOSIVE:
                list.add(TextFormatting.YELLOW + "Strength: 15");
                list.add(TextFormatting.YELLOW + "Damage modifier: 3x");
                list.add(TextFormatting.RED + "Destroys blocks");
                break;
            case PHOSPHORUS:
                list.add(TextFormatting.YELLOW + "Strength: 10");
                list.add(TextFormatting.YELLOW + "Damage modifier: 3x");
                list.add(TextFormatting.RED + "Phosphorus splash");
                list.add(TextFormatting.BLUE + "Does not destroy blocks");
                break;
            case PHOSPHORUS_MULTI:
                list.add(TextFormatting.RED + "Splits x10");
                break;
            case MINI_NUKE:
                list.add(TextFormatting.YELLOW + "Strength: 20");
                list.add(TextFormatting.RED + "Deals nuclear damage");
                list.add(TextFormatting.RED + "Destroys blocks");
                break;
            case MINI_NUKE_MULTI:
                list.add(TextFormatting.RED + "Splits x5");
                break;
            case NUKE:
                list.add(TextFormatting.RED + "☠");
                list.add(TextFormatting.RED + "(that is the best skull and crossbones");
                list.add(TextFormatting.RED + "minecraft's unicode has to offer)");
                break;
            case CARGO:
                NBTTagCompound nbt = stack.getTagCompound();
                if (nbt != null && nbt.hasKey("cargo", Constants.NBT.TAG_COMPOUND)) {
                    NBTTagCompound cargoNBT = nbt.getCompoundTag("cargo");
                    ItemStack cargo = new ItemStack(cargoNBT);
                    if (!cargo.isEmpty()) {
                        list.add(TextFormatting.YELLOW + cargo.getDisplayName());
                    } else {
                        list.add(TextFormatting.RED + "Empty");
                    }
                } else {
                    list.add(TextFormatting.RED + "Empty");
                }
                break;
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return "item." + itemTypes[Math.abs(stack.getItemDamage()) % itemTypes.length].name;
    }

    protected static SpentCasing SIXTEEN_INCH_CASE = new SpentCasing(SpentCasing.CasingType.STRAIGHT).setScale(15F, 15F, 10F).setupSmoke(1F, 1D, 200, 60).setMaxAge(300);

    public abstract class ArtilleryShell {

        String name;
        public SpentCasing casing;

        public ArtilleryShell(String name, int casingColor) {
            this.name = name;
            this.casing = SIXTEEN_INCH_CASE.clone().register(name).setColor(casingColor);
        }

        public abstract void onImpact(EntityArtilleryShell shell, RayTraceResult mop);
        public void onUpdate(EntityArtilleryShell shell) { }
    }

    public static void standardExplosion(EntityArtilleryShell shell, RayTraceResult mop, float size, float rangeMod, boolean breaksBlocks) {
        shell.world.playSound(null, shell.posX, shell.posY, shell.posZ, HBMSoundHandler.explosion_medium, SoundCategory.PLAYERS, 20.0F, 0.9F + rand.nextFloat() * 0.2F);
        Vec3d vec = new Vec3d(shell.motionX, shell.motionY, shell.motionZ).normalize();
        ExplosionVNT xnt = new ExplosionVNT(shell.world, mop.hitVec.x - vec.x, mop.hitVec.y - vec.y, mop.hitVec.z - vec.z, size);
        if(breaksBlocks) {
            xnt.setBlockAllocator(new BlockAllocatorStandard(48));
            xnt.setBlockProcessor(new BlockProcessorStandard().setNoDrop().withBlockEffect(new BlockMutatorDebris(ModBlocks.block_slag, 1)));
        }
        xnt.setEntityProcessor(new EntityProcessorCross(7.5D).withRangeMod(rangeMod));
        xnt.setPlayerProcessor(new PlayerProcessorStandard());
        xnt.setSFX(new ExplosionEffectStandard());
        xnt.explode();
        shell.killAndClear();
    }

    public static void standardCluster(EntityArtilleryShell shell, int clusterType, int amount, double splitHeight, double deviation) {
        if(!shell.getWhistle() || shell.motionY > 0) return;
        if(shell.getTargetHeight() + splitHeight < shell.posY) return;

        shell.killAndClear();

        NBTTagCompound data = new NBTTagCompound();
        data.setString("type", "plasmablast");
        data.setFloat("r", 1.0F);
        data.setFloat("g", 1.0F);
        data.setFloat("b", 1.0F);
        data.setFloat("scale", 50F);
        PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, shell.posX, shell.posY, shell.posZ),
                new NetworkRegistry.TargetPoint(shell.dimension, shell.posX, shell.posY, shell.posZ, 500));

        for(int i = 0; i < amount; i++) {
            EntityArtilleryShell cluster = new EntityArtilleryShell(shell.world);
            cluster.setType(clusterType);
            cluster.motionX = i == 0 ? shell.motionX : (shell.motionX + rand.nextGaussian() * deviation);
            cluster.motionY = shell.motionY;
            cluster.motionZ = i == 0 ? shell.motionZ : (shell.motionZ + rand.nextGaussian() * deviation);
            cluster.setPositionAndRotation(shell.posX, shell.posY, shell.posZ, shell.rotationYaw, shell.rotationPitch);
            double[] target = shell.getTarget();
            cluster.setTarget(target[0], target[1], target[2]);
            cluster.setWhistle(shell.getWhistle() && !shell.didWhistle());
            shell.world.spawnEntity(cluster);
        }
    }

    private void init() {
        /* STANDARD SHELLS */
        this.itemTypes[NORMAL] = new ArtilleryShell("ammo_arty", SpentCasing.COLOR_CASE_16INCH) { public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) { standardExplosion(shell, mop, 10F, 3F, false); }};
        this.itemTypes[CLASSIC] = new ArtilleryShell("ammo_arty_classic", SpentCasing.COLOR_CASE_16INCH) { public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) { standardExplosion(shell, mop, 15F, 5F, false); }};
        this.itemTypes[EXPLOSIVE] = new ArtilleryShell("ammo_arty_he", SpentCasing.COLOR_CASE_16INCH) { public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) { standardExplosion(shell, mop, 15F, 3F, true); }};

        /* MINI NUKE */
        this.itemTypes[MINI_NUKE] = new ArtilleryShell("ammo_arty_mini_nuke", SpentCasing.COLOR_CASE_16INCH_NUKE) {
            public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) {
                shell.world.spawnEntity(EntityNukeExplosionMK5.statFac(shell.world, 20, mop.hitVec.x, mop.hitVec.y, mop.hitVec.z));
                EntityNukeTorex.statFac(shell.world, mop.hitVec.x, mop.hitVec.y, mop.hitVec.z, 20);
                shell.setDead();
            }
        };

        /* FULL NUKE */
        this.itemTypes[NUKE] = new ArtilleryShell("ammo_arty_nuke", SpentCasing.COLOR_CASE_16INCH_NUKE) {
            public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) {
                shell.world.spawnEntity(EntityNukeExplosionMK5.statFac(shell.world, 100, mop.hitVec.x, mop.hitVec.y, mop.hitVec.z));
                EntityNukeTorex.statFac(shell.world, mop.hitVec.x, mop.hitVec.y, mop.hitVec.z, 100);
                shell.setDead();
            }
        };

        /* PHOSPHORUS */
        this.itemTypes[PHOSPHORUS] = new ArtilleryShell("ammo_arty_phosphorus", SpentCasing.COLOR_CASE_16INCH_PHOS) {
            public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) {
                standardExplosion(shell, mop, 10F, 3F, false);
                ExplosionLarge.spawnShrapnels(shell.world, (int) mop.hitVec.x, (int) mop.hitVec.y, (int) mop.hitVec.z, 15);
                ExplosionChaos.burn(shell.world, new BlockPos(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z), 12);
                int radius = 15;
                List<Entity> hit = shell.world.getEntitiesWithinAABBExcludingEntity(shell, new AxisAlignedBB(shell.posX - radius, shell.posY - radius, shell.posZ - radius, shell.posX + radius, shell.posY + radius, shell.posZ + radius));
                for(Entity e : hit) {
                    e.setFire(5);
                    if(e instanceof EntityLivingBase) {
                        PotionEffect eff = new PotionEffect(HbmPotion.phosphorus.delegate.get(), 30 * 20, 0, true, false);
                        eff.getCurativeItems().clear();
                        ((EntityLivingBase)e).addPotionEffect(eff);
                    }
                }
                for(int i = 0; i < 5; i++) {
                    NBTTagCompound haze = new NBTTagCompound();
                    haze.setString("type", "haze");
                    PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(haze, mop.hitVec.x + shell.world.rand.nextGaussian() * 10, mop.hitVec.y, mop.hitVec.z + shell.world.rand.nextGaussian() * 10), new NetworkRegistry.TargetPoint(shell.dimension, shell.posX, shell.posY, shell.posZ, 150));
                }
                NBTTagCompound data = new NBTTagCompound();
                data.setString("type", "rbmkmush");
                data.setFloat("scale", 10);
                PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, mop.hitVec.x, mop.hitVec.y, mop.hitVec.z), new NetworkRegistry.TargetPoint(shell.dimension, shell.posX, shell.posY, shell.posZ, 250));
            }
        };

        /* THIS DOOFUS */
        this.itemTypes[CARGO] = new ArtilleryShell("ammo_arty_cargo", SpentCasing.COLOR_CASE_16INCH) { public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) {
            if(mop.typeOfHit == RayTraceResult.Type.BLOCK) {
                shell.setPosition(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z);
                shell.getStuck(mop.getBlockPos(), mop.sideHit.ordinal());
            }
        }};

        /* GAS */
        this.itemTypes[CHLORINE] = new ArtilleryShell("ammo_arty_chlorine", SpentCasing.COLOR_CASE_16INCH) {
            public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) {
                shell.killAndClear();
                Vec3d vec = new Vec3d(shell.motionX, shell.motionY, shell.motionZ).normalize();
                shell.world.createExplosion(shell, mop.hitVec.x - vec.x, mop.hitVec.y - vec.y, mop.hitVec.z - vec.z, 0F, false);
                //ExplosionChaos.spawnChlorine(shell.world, mop.hitVec.x - vec.x, mop.hitVec.y - vec.y, mop.hitVec.z - vec.z, 15, 1.25, 0);
                EntityMist mist = new EntityMist(shell.world);
                //Norwood: The entity works, it spawns in correct spot, but particles are not visible. Don't know why. I am not that good at it.
                mist.setType(Fluids.CHLORINE);
                mist.setPosition(mop.hitVec.x - vec.x -7.5 , mop.hitVec.y - vec.y - 3, mop.hitVec.z - vec.z -7.5);
                mist.setArea(15, 7.5F);
                shell.world.spawnEntity(mist);
            }
        };

        /* CLUSTER SHELLS */
        this.itemTypes[PHOSPHORUS_MULTI] = new ArtilleryShell("ammo_arty_phosphorus_multi", SpentCasing.COLOR_CASE_16INCH_PHOS) {
            public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) { ItemAmmoArty.this.itemTypes[PHOSPHORUS].onImpact(shell, mop); }
            public void onUpdate(EntityArtilleryShell shell) { standardCluster(shell, PHOSPHORUS, 10, 300, 5); }
        };
        this.itemTypes[MINI_NUKE_MULTI] = new ArtilleryShell("ammo_arty_mini_nuke_multi", SpentCasing.COLOR_CASE_16INCH_NUKE) {
            public void onImpact(EntityArtilleryShell shell, RayTraceResult mop) { ItemAmmoArty.this.itemTypes[MINI_NUKE].onImpact(shell, mop); }
            public void onUpdate(EntityArtilleryShell shell) { standardCluster(shell, MINI_NUKE, 5, 300, 5); }
        };
    }
}
