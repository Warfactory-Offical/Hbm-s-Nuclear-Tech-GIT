package com.hbm.hazard;

import akka.japi.Pair;
import com.hbm.items.meta.materials.MaterialMineral;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("UnnecessaryLocalVariable")
public class HazardRegistry {

    private static final Map<String, Float> shapeMultipliers = new LinkedHashMap<>();

    public static void postInit() {
        populate();
    }

    static {
        final float nugget = 0.1F;
        final float wire = 0.2F;
        final float rod = 0.5F;
        final float stick = 0.5F;
        final float billet = 0.5F;
        final float ingot = 1.0F;
        final float gem = 1.0F;
        final float powderMult = 3.0F;
        final float block = 10.0F;
        final float plate = ingot;
//        final float plateCast = plate * 3;
        final float powder = ingot * powderMult;
        final float powderTiny = nugget * powderMult;
        final float ore = ingot * 3;
        final float crystal = block;
        final float rtg = billet * 3;
        final float rodDual = rod * 2;
        final float rodQuad = rod * 4;
        final float rodRbmk = rod * 8;

        shapeMultipliers.put("billet",      billet);
        shapeMultipliers.put("block",       block);
        shapeMultipliers.put("crystal",     crystal);
        shapeMultipliers.put("gem",         gem);
        shapeMultipliers.put("ingot",       ingot);
        shapeMultipliers.put("nugget",      nugget);
        shapeMultipliers.put("ore",         ore);
        shapeMultipliers.put("plate",       plate);
        shapeMultipliers.put("dust",        powder);
        shapeMultipliers.put("dustTiny",    powderTiny);
        shapeMultipliers.put("rod",         rod);
        shapeMultipliers.put("rodDual",     rodDual);
        shapeMultipliers.put("rodQuad",     rodQuad);
        shapeMultipliers.put("rodRbmk",     rodRbmk);
        shapeMultipliers.put("rtg",         rtg);
        shapeMultipliers.put("stick",       stick);
        shapeMultipliers.put("wire",        wire);
    }

    private static final Map<MaterialMineral, HazardList> materialHazards = new LinkedHashMap<>();

    static {
        materialHazards.put(MaterialMineral.LEAD, new HazardList(
                new Hazard(Hazard.Type.TOXIC, 2.f)
        ));
        materialHazards.put(MaterialMineral.ASBESTOS, new HazardList(
                new Hazard(Hazard.Type.ASBESTOS, 1f)
        ));
        materialHazards.put(MaterialMineral.PB209, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 10_000.f),
                new Hazard(Hazard.Type.TOXIC, 2.f),
                new Hazard(Hazard.Type.BLINDING, 50.f),
                new Hazard(Hazard.Type.HOT, 3.f)
        ));
        materialHazards.put(MaterialMineral.ARSENIC, new HazardList(
                new Hazard(Hazard.Type.TOXIC, 10.f)
        ));
        materialHazards.put(MaterialMineral.C4, new HazardList(
                new Hazard(Hazard.Type.EXPLOSIVE, 10.f)
        ));
        materialHazards.put(MaterialMineral.PHOSPHORUS, new HazardList(
                new Hazard(Hazard.Type.EXPLOSIVE, 4.f)
        ));
        materialHazards.put(MaterialMineral.LITHIUM, new HazardList(
                new Hazard(Hazard.Type.HYDROACTIVE, 2.f)
        ));
        materialHazards.put(MaterialMineral.CERIUM, new HazardList(
                new Hazard(Hazard.Type.HYDROACTIVE, 4.f)
        ));
        materialHazards.put(MaterialMineral.TH232, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 0.1f)
        ));
        materialHazards.put(MaterialMineral.THORIUM_FUEL, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 1.75f)
        ));
        materialHazards.put(MaterialMineral.FERROURANIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 0.175f)
        ));
        materialHazards.put(MaterialMineral.URANIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 0.35f)
        ));
        materialHazards.put(MaterialMineral.U233, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 5.f)
        ));
        materialHazards.put(MaterialMineral.U238, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 5.f)
        ));
        materialHazards.put(MaterialMineral.U235, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 1.f)
        ));
        materialHazards.put(MaterialMineral.URANIUM_FUEL, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 0.5f)
        ));
        materialHazards.put(MaterialMineral.SCHRABIDATE, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 3.f),
                new Hazard(Hazard.Type.BLINDING,  50.f)
        ));
        materialHazards.put(MaterialMineral.SCHRABIDIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 15.f),
                new Hazard(Hazard.Type.BLINDING,  50.f)
        ));
        materialHazards.put(MaterialMineral.SCHRARANIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 1.5f),
                new Hazard(Hazard.Type.BLINDING,  50.f)
        ));
        materialHazards.put(MaterialMineral.SOLINIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 17.5f),
                new Hazard(Hazard.Type.BLINDING,  50.f)
        ));
        materialHazards.put(MaterialMineral.SCHRABIDIUM_FUEL, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 5.85f),
                new Hazard(Hazard.Type.BLINDING,  50.f)
        ));
        materialHazards.put(MaterialMineral.LES, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 2.52f),
                new Hazard(Hazard.Type.BLINDING,  50.f)
        ));
        materialHazards.put(MaterialMineral.HES, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 8.8f),
                new Hazard(Hazard.Type.BLINDING,  50.f)
        ));
        materialHazards.put(MaterialMineral.NEPTUNIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 2.5f),
                new Hazard(Hazard.Type.HOT, 3.f)
        ));
        materialHazards.put(MaterialMineral.NEPTUNIUM_FUEL, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 1.5f),
                new Hazard(Hazard.Type.HOT, 3.f)
        ));
        materialHazards.put(MaterialMineral.PLUTONIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 7.5f)
        ));
        materialHazards.put(MaterialMineral.PU238, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 10.f),
                new Hazard(Hazard.Type.HOT,  6.f)
        ));
        materialHazards.put(MaterialMineral.PU239, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 5.f)
        ));
        materialHazards.put(MaterialMineral.PU240, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 7.5f)
        ));
        materialHazards.put(MaterialMineral.PU241, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 25.f)
        ));
        materialHazards.put(MaterialMineral.PU_MIX, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 6.25f)
        ));
        materialHazards.put(MaterialMineral.PLUTONIUM_FUEL, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 4.25f)
        ));

        materialHazards.put(MaterialMineral.AM241, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 8.5f)
        ));
        materialHazards.put(MaterialMineral.AM242, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 9.5f)
        ));
        materialHazards.put(MaterialMineral.AM_MIX, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 9.f)
        ));
        materialHazards.put(MaterialMineral.AMERICIUM_FUEL, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 4.75f)
        ));
        materialHazards.put(MaterialMineral.CO60, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 30.f),
                new Hazard(Hazard.Type.HOT, 6.f)
        ));
        materialHazards.put(MaterialMineral.TENNESSINE, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 120.f)
        ));
        materialHazards.put(MaterialMineral.MAGNETIZED_TUNGSTEN, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 0.75f)
        ));
        materialHazards.put(MaterialMineral.TECHNETIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 2.75f)
        ));
        materialHazards.put(MaterialMineral.TCALLOY, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 0.075f)
        ));
        materialHazards.put(MaterialMineral.POLONIUM, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 75.f)
        ));
        materialHazards.put(MaterialMineral.STRONTIUM, new HazardList(
                new Hazard(Hazard.Type.HYDROACTIVE, 8f)
        ));
        materialHazards.put(MaterialMineral.SR90, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 15f),
                new Hazard(Hazard.Type.HYDROACTIVE, 8f),
                new Hazard(Hazard.Type.HOT, 8f)
        ));
        materialHazards.put(MaterialMineral.I131, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 150.f)
        ));
        materialHazards.put(MaterialMineral.RA226, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 7.5f),
                new Hazard(Hazard.Type.HYDROACTIVE, 10f)
        ));
//        materialHazards.put(MaterialMineral.RA226, new HazardList( // TODO: figure out which one is correct
//                new Hazard(Hazard.Type.RADIATION, 500.f),
//                new Hazard(Hazard.Type.HOT, 8f)
//        ));
        materialHazards.put(MaterialMineral.AC227, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 30.f)
        ));
        materialHazards.put(MaterialMineral.RADSPICE, new HazardList(
                new Hazard(Hazard.Type.RADIATION, 20_000.f)
        ));
    }

    private static final Map<Pair<Item, Integer>, HazardList> hazardMap = new LinkedHashMap<>();

    private static void populate() {
        for (final Map.Entry<String, Float> shapeEntry : shapeMultipliers.entrySet()) {
            for (final MaterialMineral material : MaterialMineral.values()) {
                if (!materialHazards.containsKey(material)) continue; // TODO: remove

                final String oreDictEntryName = shapeEntry.getKey() + material.getNamePascalCase();

                final HazardList hazards = new HazardList(
                        materialHazards
                                .get(material)
                                .stream()
                                .map(hazard -> hazard.multiply(shapeEntry.getValue()))
                                .collect(Collectors.toList())
                );

                for (final ItemStack is : OreDictionary.getOres(oreDictEntryName)) {
                    final Pair<Item, Integer> key = new Pair<>(is.getItem(), is.getMetadata());
                    hazardMap.put(key, hazards);
                }
            }
        }

        // Quick fix. Will be rewritten in the near future
        hazardMap.put(new Pair<>(Items.COAL, 0), new HazardList(
                new Hazard(Hazard.Type.COAL, 1.f)
        ));
    }

    public static HazardList getHazardsForItemStack(final ItemStack is) {
        final Pair<Item, Integer> key = new Pair<>(is.getItem(), is.getMetadata());

        return new HazardList(
                hazardMap.containsKey(key)
                        ? hazardMap
                        .get(key)
                        .stream()
                        .map(hazard -> hazard.multiply(is.getCount()))
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );
    }

    @SideOnly(Side.CLIENT)
    public static void addFullTooltip(final ItemStack stack, final EntityPlayer player, final List<String> list) {
        final HazardList hazards = getHazardsForItemStack(stack);
        hazards.addHazardInformation(player, list, stack);
    }

    private static void applyHazards(final ItemStack stack, final EntityPlayer target) {
        final HazardList hazards = getHazardsForItemStack(stack);
        hazards.onUpdate(target, stack);
    }

    public static void updatePlayerInventory(final EntityPlayer player) {
        final NonNullList<ItemStack> mainInventory = player.inventory.mainInventory;
        final NonNullList<ItemStack> armorInventory = player.inventory.armorInventory;

        for (int i = 0; i < mainInventory.size(); i++) {
            final ItemStack stack = mainInventory.get(i);

            if (!stack.isEmpty()) {
                applyHazards(stack, player);

                if (stack.getCount() == 0) {
                    mainInventory.set(i, ItemStack.EMPTY);
                }
            }
        }

        for (final ItemStack stack : armorInventory) {
            if (!stack.isEmpty()) {
                applyHazards(stack, player);
            }
        }
    }

}