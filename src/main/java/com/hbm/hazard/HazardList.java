package com.hbm.hazard;

import com.hbm.config.GeneralConfig;
import com.hbm.config.RadiationConfig;
import com.hbm.handler.ArmorUtil;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.BobMathUtil;

import com.hbm.util.ArmorRegistry;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.I18nUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.hbm.hazard.Hazard.Type.*;

public class HazardList {

    private final List<Hazard> hazards;

    public HazardList(final Hazard... hazards) {
        this.hazards = Arrays.asList(hazards);
    }

    public HazardList(final List<Hazard> hazards) {
        this.hazards = hazards;
    }

    public static boolean isHoldingReacher(final EntityLivingBase target) {
        if (target instanceof final EntityPlayer player && !GeneralConfig.enable528) {
            return Library.checkForHeld(player, ModItems.reacher);
        }

        return false;
    }

    public static void applyPotionEffect(final EntityLivingBase target, final Potion potion, final int duration, final int amplifier) {
        target.addPotionEffect(new PotionEffect(potion, duration, amplifier));
    }

    public void onUpdate(final EntityLivingBase target, final ItemStack stack) {
        for (final Hazard hazard : hazards) {
            float level = hazard.getStrength();

            switch (hazard.getType()) {
                case ASBESTOS -> {
                    if (ArmorRegistry.hasProtection(target, EntityEquipmentSlot.HEAD, HazardClass.PARTICLE_FINE))
                        ArmorUtil.damageGasMaskFilter(target, (int) level * RadiationConfig.hazardRate);
//                    else
//                    LivingCapabilityNTM.incrementAsbestos(target, (int) Math.min(level, 10) * RadiationConfig.hazardRate);
                }
                case BLINDING -> {
                    if (!ArmorRegistry.hasProtection(target, EntityEquipmentSlot.HEAD, HazardClass.LIGHT)) {
                       target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (int)level, 0));
                    }
                }
                case COAL -> {
                    if (!ArmorRegistry.hasProtection(target, EntityEquipmentSlot.HEAD, HazardClass.PARTICLE_COARSE)) {
//                    LivingCapabilityNTM.incrementBlackLung(target, (int) Math.min(level * stack.getCount(), 10) * RadiationConfig.hazardRate);
                    } else {
                        if(target.getRNG().nextInt(Math.max(65 - stack.getCount(), 1)) == 0) {
                            ArmorUtil.damageGasMaskFilter(target, (int) level * RadiationConfig.hazardRate);
                        }
                    }
                }
                case COLD -> {
                    final boolean reacher = isHoldingReacher(target);

                    if (reacher) return;

                    if (target instanceof EntityPlayer && ArmorUtil.checkForHazmat(target)) return; // Early return if protected

                    final int baseLevel = (int) level - 1;
                    final int witherLevel = (int) level - 3;

                    applyPotionEffect(target, MobEffects.MINING_FATIGUE, 110, baseLevel);
                    applyPotionEffect(target, MobEffects.SLOWNESS, 110, Math.min(4, baseLevel));
                    applyPotionEffect(target, MobEffects.WEAKNESS, 110, baseLevel);

                    if (level > 4) {
                        applyPotionEffect(target, MobEffects.WITHER, 110, witherLevel);
                    }
                }
                case DIGAMMA -> {
                    ContaminationUtil.applyDigammaData(target, (level / 20F)*RadiationConfig.hazardRate);
                }
                case EXPLOSIVE -> {
                    if(target.isBurning() && stack.getCount() > 0) {
                        stack.setCount(0);
                        target.world.newExplosion(null, target.posX, target.posY + target.getEyeHeight() - target.getYOffset(), target.posZ, level, false, true);
                    }
                }
                case HOT -> {
                    final boolean wetOrReacher = isHoldingReacher(target) || target.isWet() ;
                    if(!wetOrReacher) return;

                    target.setFire((int) Math.ceil(level));
                }
                case HYDROACTIVE -> {
                    final boolean playerIsWet = target.isWet() || (target.world.isRaining() && target.world.canSeeSky(target.getPosition()));

                    if(playerIsWet && stack.getCount() > 0) {
                        stack.setCount(0);
                        target.world.newExplosion(null, target.posX, target.posY + target.getEyeHeight() - target.getYOffset(), target.posZ, level, false, true);
                    }
                }
                case RADIATION -> {
                    final boolean reacher = isHoldingReacher(target);

                    level *= stack.getCount();

                    if(level > 0) {
                        float rad = (level / 20F)/2;

                        if(GeneralConfig.enable528 && reacher) {
                            rad = rad / 49F;	//More realistic function for 528: x / distance^2
                        } else if(reacher) {
                            rad = (float) BobMathUtil.squirt(rad); //Reworked radiation function: sqrt(x+1/(x+2)^2)-1/(x+2)
                        }

//                        ContaminationUtil.contaminate(target, Hazard.Type.RADIATION, ContaminationType.CREATIVE, rad*RadiationConfig.hazardRate);
                    }
                }
                case TOXIC -> {
                    final boolean reacher = isHoldingReacher(target);
                    boolean hasToxFilter = false;
                    boolean hasHazmat = false;

                    if (target instanceof EntityPlayer player) {
                        hasToxFilter = ArmorRegistry.hasProtection(player, EntityEquipmentSlot.HEAD, ArmorRegistry.HazardClass.NERVE_AGENT);

                        if (hasToxFilter) {
                            ArmorUtil.damageGasMaskFilter(player, 1);
                        }

                        hasHazmat = ArmorUtil.checkForHazmat(player);
                    }

                    final boolean isUnprotected = !(hasToxFilter || hasHazmat || reacher);

                    if (isUnprotected) {
                        applyPotionEffect(target, MobEffects.WEAKNESS, 110, (int) (level - 1));

                        if (level > 2) {
                            applyPotionEffect(target, MobEffects.SLOWNESS, 110, (int) Math.min(4, level - 4));
                        }

                        if (level > 4) {
                            applyPotionEffect(target, MobEffects.HUNGER, 110, (int) level);
                        }

                        if (level > 6 && target.world.rand.nextInt((int) (2000 / level)) == 0) {
                            applyPotionEffect(target, MobEffects.POISON, 110, (int) (level - 4));
                        }
                    }

                    if (!hasHazmat || !hasToxFilter || !reacher) {
                        if (level > 8) {
                            applyPotionEffect(target, MobEffects.MINING_FATIGUE, 110, (int) (level - 8));
                        }

                        if (level > 16) {
                            applyPotionEffect(target, MobEffects.INSTANT_DAMAGE, 110, (int) (level - 16));
                        }
                    }
                }
            }
        }
    }

    public void updateEntity(final EntityItem item, final float level) {

    }

    @SideOnly(Side.CLIENT)
    public void addHazardInformation(final EntityPlayer player, final List<String> list, final ItemStack stack) {
        for (final Hazard hazard : hazards) {
            switch (hazard.getType()) {
                case ASBESTOS -> {
                    list.add(TextFormatting.WHITE + "[" + I18nUtil.resolveKey("trait.asbestos") + "]");
                }
                case BLINDING -> {
                    list.add(TextFormatting.DARK_AQUA + "[" + I18nUtil.resolveKey("trait.blinding") + "]");
                }
                case COAL -> {
                    list.add(TextFormatting.DARK_GRAY + "[" + I18nUtil.resolveKey("trait.coal") + "]");
                }
                case COLD -> {
                    list.add(TextFormatting.AQUA + "[" + I18nUtil.resolveKey("trait.cryogenic") + "]");
                }
                case DIGAMMA -> {
                    final float level = hazard.getStrength();

                    // TODO
                    // level = HazardModifier.evalAllModifiers(stack, player, level, modifiers);

                    final float displayLevel = Math.round(level * 10000F) / 10F;
                    list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.digamma") + "]");
                    list.add(TextFormatting.DARK_RED + "" + displayLevel + "mDRX/s");

                    if (stack.getCount() > 1) {
                        final float stackLevel = displayLevel * stack.getCount();
                        list.add(TextFormatting.DARK_RED + "Stack: " + stackLevel + "mDRX/s");
                    }
                }
                case EXPLOSIVE -> {
                    list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.explosive") + "]");
                }
                case HOT -> {
                    final float level = hazard.getStrength();

                    // TODO
                    // level = HazardModifier.evalAllModifiers(stack, player, level, modifiers);

                    if(level > 0) list.add(TextFormatting.GOLD + "[" + I18nUtil.resolveKey("trait.hot") + "]");
                }
                case HYDROACTIVE -> {
                    list.add(TextFormatting.RED + "[" + I18nUtil.resolveKey("trait.hydro") + "]");
                }
                case RADIATION -> {
                    final float level = hazard.getStrength();

//                     level = HazardModifier.evalAllModifiers(stack, player, level, modifiers);

                    if(level < 1e-5)
                        return;

                    list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey("trait.radioactive") + "]");
                    final String rad = "" + (Math.floor(level* 1000) / 1000);
                    list.add(TextFormatting.YELLOW + (rad + "RAD/s"));

                    if(stack.getCount() > 1) {
                        list.add(TextFormatting.YELLOW + "Stack: " + ((Math.floor(level * 1000 * stack.getCount()) / 1000) + "RAD/s"));
                    }
                }
                case TOXIC -> {
                    final float level = hazard.getStrength();

                    final String adjectiveKey;

                    if (level > 16) {
                        adjectiveKey = "adjective.extreme";
                    } else if (level > 8) {
                        adjectiveKey = "adjective.veryhigh";
                    } else if (level > 4) {
                        adjectiveKey = "adjective.high";
                    } else if (level > 2) {
                        adjectiveKey = "adjective.medium";
                    } else {
                        adjectiveKey = "adjective.little";
                    }

                    list.add(TextFormatting.GREEN + "[" + I18nUtil.resolveKey(adjectiveKey) + " " + I18nUtil.resolveKey("trait.toxic") + "]");
                }
            }
        }
    }

    public Stream<Hazard> stream() {
        return hazards.stream();
    }

    public List<Hazard> asList() {
        return hazards;
    }

    @Override
    public String toString() {
        return hazards.toString();
    }

}
