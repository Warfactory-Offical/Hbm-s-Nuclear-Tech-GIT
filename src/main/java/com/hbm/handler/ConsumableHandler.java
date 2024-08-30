package com.hbm.handler;
import com.hbm.util.ItemStackUtil;

import com.hbm.capability.HbmLivingProps;
import com.hbm.config.VersatileConfig;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.items.ModItems;
import com.hbm.items.armor.JetpackBase;
import com.hbm.items.special.ItemSimpleConsumable;
import com.hbm.items.weapon.ItemGunBase;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.ModDamageSource;
import com.hbm.potion.HbmPotion;

import com.hbm.util.EnchantmentUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class ConsumableHandler {

    private static final Map<Item, Consumer<Context>> itemActions = new HashMap<>();

    static {
        itemActions.put(ModItems.iv_xp_empty, ConsumableHandler::handleIvXPempty);
        itemActions.put(ModItems.syringe_antidote, ConsumableHandler::handleAntidote);
        itemActions.put(ModItems.syringe_awesome, ConsumableHandler::handleAwesomeSyringe);
        itemActions.put(ModItems.syringe_poison, ConsumableHandler::handlePoisonSyringe);
        itemActions.put(ModItems.syringe_metal_stimpak, ConsumableHandler::handleMetalStimpak);
        itemActions.put(ModItems.syringe_metal_medx, ConsumableHandler::handleMetalMedX);
        itemActions.put(ModItems.syringe_metal_psycho, ConsumableHandler::handleMetalPsycho);
        itemActions.put(ModItems.syringe_metal_super, ConsumableHandler::handleMetalSuper);
        itemActions.put(ModItems.med_bag, ConsumableHandler::handleMedBag);
        itemActions.put(ModItems.syringe_taint, ConsumableHandler::handleTaintSyringe);
        itemActions.put(ModItems.jetpack_tank, ConsumableHandler::handleJetpackTank);
        itemActions.put(ModItems.gun_kit_1, ConsumableHandler::handleGunKit1);
        itemActions.put(ModItems.gun_kit_2, ConsumableHandler::handleGunKit2);
        itemActions.put(ModItems.cbt_device, ConsumableHandler::handleCbtDevice);
        itemActions.put(ModItems.syringe_mkunicorn, ConsumableHandler::handleMkUnicornSyringe);
    }

    private static void handleIvXPempty(Context context) {
        if(context.player.experienceTotal >= 100) {
        }

    }

    public static ActionResult<ItemStack> handleItemUse(World world, EntityPlayer player, EnumHand hand, Item item) {
        if (!itemActions.containsKey(item)) {
            return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
        }

        Context context = new Context(world, player, hand);
        itemActions.get(item).accept(context);
        return context.getActionResult();
    }

    //I wanted to handle this more uniformly, but that was fool's errand
    private static void handleAntidote(Context context) {
        if (!VersatileConfig.hasPotionSickness(context.player)) {
            context.player.clearActivePotions();
            VersatileConfig.applyPotionSickness(context.player, 5);
            context.shrinkAndReplaceItem(ModItems.syringe_empty);
        }
    }

    private static void handleAwesomeSyringe(Context context) {
        context.addPotionEffects(
                new PotionEffect(MobEffects.REGENERATION, 50 * 20, 9),
                new PotionEffect(MobEffects.RESISTANCE, 50 * 20, 9),
                new PotionEffect(MobEffects.FIRE_RESISTANCE, 50 * 20, 0),
                new PotionEffect(MobEffects.STRENGTH, 50 * 20, 24),
                new PotionEffect(MobEffects.HASTE, 50 * 20, 9),
                new PotionEffect(MobEffects.SPEED, 50 * 20, 6),
                new PotionEffect(MobEffects.JUMP_BOOST, 50 * 20, 9),
                new PotionEffect(MobEffects.HEALTH_BOOST, 50 * 20, 9),
                new PotionEffect(MobEffects.ABSORPTION, 50 * 20, 4),
                new PotionEffect(MobEffects.NAUSEA, 5 * 20, 4)
        );
        context.playSound(HBMSoundHandler.syringeUse);
        context.shrinkAndReplaceItem(ModItems.syringe_empty);
    }

    private static void handlePoisonSyringe(Context context) {
        if (context.rand.nextInt(2) == 0) {
            context.player.attackEntityFrom(ModDamageSource.euthanizedSelf, 30);
        } else {
            context.player.attackEntityFrom(ModDamageSource.euthanizedSelf2, 30);
        }
        context.playSound(HBMSoundHandler.syringeUse);
        context.shrinkAndReplaceItem(ModItems.syringe_empty);
    }

    private static void handleMetalStimpak(Context context) {
        context.player.heal(5);
        context.playSound(HBMSoundHandler.syringeUse);
        context.shrinkAndReplaceItem(ModItems.syringe_metal_empty);
    }

    private static void handleMetalMedX(Context context) {
        context.addPotionEffects(new PotionEffect(MobEffects.RESISTANCE, 4 * 60 * 20, 2));
        context.playSound(HBMSoundHandler.syringeUse);
        context.shrinkAndReplaceItem(ModItems.syringe_metal_empty);
    }

    private static void handleMetalPsycho(Context context) {
        context.addPotionEffects(
                new PotionEffect(MobEffects.RESISTANCE, 2 * 60 * 20, 0),
                new PotionEffect(MobEffects.STRENGTH, 2 * 60 * 20, 0)
        );
        context.playSound(HBMSoundHandler.syringeUse);
        context.shrinkAndReplaceItem(ModItems.syringe_metal_empty);
    }

    private static void handleMetalSuper(Context context) {
        context.player.heal(25);
        context.addPotionEffects(new PotionEffect(MobEffects.SLOWNESS, 10 * 20, 0));
        context.playSound(HBMSoundHandler.syringeUse);
        context.shrinkAndReplaceItem(ModItems.syringe_metal_empty);
    }

    private static void handleMedBag(Context context) {
        context.player.setHealth(context.player.getMaxHealth());
        context.removePotionEffects(
                MobEffects.BLINDNESS, MobEffects.NAUSEA, MobEffects.MINING_FATIGUE,
                MobEffects.HUNGER, MobEffects.SLOWNESS, MobEffects.POISON,
                MobEffects.WEAKNESS, MobEffects.WITHER, HbmPotion.radiation
        );
        context.shrinkCurrentItem();
    }

    private static void handleTaintSyringe(Context context) {
        context.addPotionEffects(
                new PotionEffect(HbmPotion.taint, 60 * 20, 0),
                new PotionEffect(MobEffects.NAUSEA, 5 * 20, 0)
        );
        context.playSound(HBMSoundHandler.syringeUse);
        context.shrinkAndReplaceItem(ModItems.syringe_metal_empty, ModItems.bottle2_empty);
    }

    private static void handleJetpackTank(Context context) {
        ItemStack jetpack = context.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (jetpack.getItem() instanceof JetpackBase) {
            JetpackBase jetItem = (JetpackBase) jetpack.getItem();
            if (jetItem.fuel != ModForgeFluids.kerosene) return;

            int newFuel = Math.min(JetpackBase.getFuel(jetpack) + 1000, jetItem.maxFuel);
            if (JetpackBase.getFuel(jetpack) != newFuel) {
                JetpackBase.setFuel(jetpack, newFuel);
                context.playSound(HBMSoundHandler.jetpackTank);
                context.shrinkCurrentItem();
            }
        }
    }

    private static void handleGunKit1(Context context) {
        handleGunKit(context, 0.1F, HBMSoundHandler.spray);
    }

    private static void handleGunKit2(Context context) {
        handleGunKit(context, 0.5F, HBMSoundHandler.repair);
    }

    private static void handleGunKit(Context context, float repairFactor, SoundEvent sound) {
        for (int i = 0; i < 10; i++) {
            ItemStack gun = (i == 9) ? context.player.getHeldItemOffhand() : context.player.inventory.mainInventory.get(i);
            if (gun.getItem() instanceof ItemGunBase) {
                int fullDurability = ((ItemGunBase) gun.getItem()).mainConfig.durability;
                int newWear = Math.max(ItemGunBase.getItemWear(gun) - (int) (fullDurability * repairFactor), 0);
                ItemGunBase.setItemWear(gun, newWear);
            }
        }
        context.playSound(sound);
        context.shrinkCurrentItem();
    }

    private static void handleCbtDevice(Context context) {
        context.addPotionEffects(new PotionEffect(HbmPotion.bang, 30, 0));
        context.shrinkCurrentItem();
        context.playSound(HBMSoundHandler.vice);
    }

    private static void handleMkUnicornSyringe(Context context) {
        HbmLivingProps.setContagion(context.player, 3 * 60 * 60 * 20);
        context.playSound(HBMSoundHandler.syringeUse);
        context.shrinkCurrentItem();
    }

    // Static class cause idk what other data structure to use
    public static class Context {
        public final World world;
        public final EntityPlayer player;
        public final EnumHand hand;
        public final Random rand = new Random();

        public Context(World world, EntityPlayer player, EnumHand hand) {
            this.world = world;
            this.player = player;
            this.hand = hand;
        }

        public void shrinkCurrentItem() {
            player.getHeldItem(hand).shrink(1);
        }

        public void shrinkAndReplaceItem(Item... replacements) {
            shrinkCurrentItem();
            if (player.getHeldItem(hand).isEmpty()) {
                player.setHeldItem(hand, ItemStackUtil.itemStackFrom(replacements[rand.nextInt(replacements.length)]));
            } else {
                for (Item replacement : replacements) {
                       ItemStack toReplace = ItemStackUtil.itemStackFrom(replacement);
                    if(!player.addItemStackToInventory(toReplace));
                        player.dropItem(toReplace, false);
                }
            }
        }

        public void playSound(SoundEvent sound) {
            world.playSound(null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }

        public void addPotionEffects(PotionEffect... effects) {
            for (PotionEffect effect : effects) {
                player.addPotionEffect(effect);
            }
        }

        public void removePotionEffects(Potion... potions) {
            for (Potion potion : potions) {
                player.removePotionEffect(potion);
            }
        }

        public ActionResult<ItemStack> getActionResult() {
            return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }
    }
}
