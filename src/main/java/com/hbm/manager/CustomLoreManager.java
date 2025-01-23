package com.hbm.manager;

import com.hbm.config.GeneralConfig;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.naming.NameNotFoundException;
import java.util.*;

public class CustomLoreManager {

    public static void addCustomLore(final ItemStack stack, final List<String> list) {
        final String itemName = stack.getTranslationKey().substring(".item".length());

        if (!customLoreEntries.containsKey(itemName)) return;

        list.addAll(customLoreEntries.get(itemName));
    }

    static Map<String, List<String>> customLoreEntries = new LinkedHashMap<>();

    private static void addCustomLoreEntry(final String itemName, final String... lines) {
        if (!I18n.hasKey("item." + itemName +  ".name")) throw new RuntimeException(new NameNotFoundException(itemName));
        customLoreEntries.put(itemName, new ArrayList<>(Arrays.asList(lines)));
    }

    private static void addCustomLoreEntry(final String[] itemNames, final String... lines) {
        for (final String itemName : itemNames) {
            addCustomLoreEntry(itemName, lines);
        }
    }

    static {
        addCustomLoreEntry("powder_asbestos", TextFormatting.ITALIC + "\"Sniffffffff- MHHHHHHMHHHHHHHHH\"");
        addCustomLoreEntry("bismuth_tool", "§eRight-click a Dud while having empty cells in inventory gives you antimatter cells.", "§8§oMight set off the Dud tho");
        addCustomLoreEntry("ingot_fiberglass", "High in fiber, high in glass. Everything the body needs.");
        addCustomLoreEntry("missile_soyuz_lander", "Doubles as a crappy lander!");
        addCustomLoreEntry("book_of_", "Denn wer den Walzer richtig tritt,", "der ist auch für den Abgang fit.");
        addCustomLoreEntry("watch", "A small blue pocket watch.", "It's glass has a few cracks in it,", "and some shards are missing.", "It stopped ticking at 2:34.");
        addCustomLoreEntry("reacher", "Holding this in main hand or off hand reduces radiation coming from items to its square-root.", "It also is useful to handle very hot or cold items.");
        addCustomLoreEntry("ingot_asbestos", TextFormatting.ITALIC + "\"Filled with life, self-doubt and asbestos. That comes with the air.\"");
        addCustomLoreEntry("entanglement_kit", "Teleporter crafting item.", "Enables dimension-shifting via", "beryllium-enhanced resource scanner.");
        addCustomLoreEntry("ams_focus_limiter", "Maximum performance for restriction field:", "Standard cooling, no energy bonus.");
        addCustomLoreEntry("ams_focus_booster", "Weaker restriction field and core energy injection:", "More heat generation, extra energy.");
        addCustomLoreEntry("ams_muzzle", "...it emits an energy-beam thingy.");
        addCustomLoreEntry("powder_poison", "Used in multi purpose bombs:", "Warning: Poisonous!");
        addCustomLoreEntry("pellet_cluster", "Used in multi purpose bombs:", "Adds some extra boom!");
        addCustomLoreEntry("powder_fire", "Used in multi purpose bombs:", "Incendiary bombs are fun!");
        addCustomLoreEntry("pellet_gas", "Used in multi purpose bombs:", "*cough cough* Halp pls!");
        addCustomLoreEntry("powder_tektite", "Collected via Geralds Miningfleet from §3outer space");
        addCustomLoreEntry("igniter", "(Used by right-clicking the Prototype)", "It's a green metal handle with a", "bright red button and a small lid.", "At the bottom, the initials N.E. are", "engraved. Whoever N.E. was, he had", "a great taste in shades of green.");
        addCustomLoreEntry("overfuse", "Say what?");
        addCustomLoreEntry("tritium_deuterium_cake", "Not actual cake, but great", "universal fusion fuel!");
        addCustomLoreEntry("flame_conspiracy", I18nUtil.resolveKey("desc.flameconspiracy"));
        addCustomLoreEntry("flame_politics", I18nUtil.resolveKey("desc.flamepolitics"));
        addCustomLoreEntry("flame_opinion", I18nUtil.resolveKey("desc.flameopinion"));
        addCustomLoreEntry("mech_key", "It pulses with power.");
        addCustomLoreEntry("ingot_lanthanium", "'Lanthanum'");
        addCustomLoreEntry("billet_flashlead", "The lattice decays, causing antimatter-matter annihilation reactions, causing the release of pions, decaying into muons, catalyzing fusion of the nuclei, creating the new element. Please try to keep up.");
        addCustomLoreEntry("euphemium_capacitor", "Permits passive dispersion of accumulated positive energy.");
        addCustomLoreEntry("iv_empty", I18nUtil.resolveKey("desc.ivempty"));
        addCustomLoreEntry("radaway", I18nUtil.resolveKey("desc.radamount", -250), I18nUtil.resolveKey("desc.radspeed", -25), I18nUtil.resolveKey("desc.duration", 10));
        addCustomLoreEntry("radaway_strong", I18nUtil.resolveKey("desc.radamount", -500), I18nUtil.resolveKey("desc.radspeed", -100), I18nUtil.resolveKey("desc.duration", 5));
        addCustomLoreEntry("radaway_flush", I18nUtil.resolveKey("desc.radamount", -1000), I18nUtil.resolveKey("desc.radspeed", -400), I18nUtil.resolveKey("desc.duration", 2.5));
        addCustomLoreEntry("pin", "Can be used with a screwdriver to pick locks.");
        addCustomLoreEntry("crystal_energy", "Densely packed energy powder.", "Not edible.");
        addCustomLoreEntry("pellet_coolant", "Required for cyclotron operation.", "Do NOT operate cyclotron without it!");
        addCustomLoreEntry("fuse", "This item is needed for every large", "nuclear reactor, as it allows the", "reactor to generate electricity and", "use up it's fuel. Removing the fuse", "from a reactor will instantly shut", "it down.");
        addCustomLoreEntry("gun_super_shotgun", "It's super broken!");
        addCustomLoreEntry("burnt_bark", "A piece of bark from an exploded golden oak tree.");
        addCustomLoreEntry("rod_lithium", "Turns into Tritium Rod");
        addCustomLoreEntry("rod_dual_lithium", "Turns into Dual Tritium Rod");
        addCustomLoreEntry("rod_quad_lithium", "Turns into Quad Tritium Rod");
        addCustomLoreEntry("ingot_euphemium", "A very special and yet strange element.");
        addCustomLoreEntry("powder_euphemium", "Pulverized pink.", "Tastes like strawberries.");
        addCustomLoreEntry("nugget_euphemium", "A small piece of a pink metal.", "It's properties are still unknown,", "DEAL WITH IT carefully.");
        addCustomLoreEntry("rod_quad_euphemium", "A quad fuel rod which contains a", "very small ammount of a strange new element.");
        addCustomLoreEntry("nugget_mox_fuel", "Moxie says: " + TextFormatting.BOLD + "TAX EVASION.");
        addCustomLoreEntry("billet_mox_fuel", TextFormatting.ITALIC + "Pocket-Moxie!");
        addCustomLoreEntry("ingot_schraranium", GeneralConfig.enableBabyMode
                ? "Peer can go die, I'm not putting any retarded niko stuff in the mod."
                : "Made from uranium in a nuclear transmutator"
        );
        addCustomLoreEntry("crystal_horn", MainRegistry.polaroidID == 11
                ? "An actual horn."
                : "Not an actual horn."
        );
        addCustomLoreEntry("crystal_charred", MainRegistry.polaroidID == 11
                ? "Also a real horn. Weird, right?"
                : "High quality silicate, slightly burned."
        );
        addCustomLoreEntry("key_red", MainRegistry.polaroidID == 11
                ? TextFormatting.DARK_RED + "" + TextFormatting.BOLD + "e"
                : "Explore the other side."
        );
        addCustomLoreEntry("ingot_neptunium", MainRegistry.polaroidID == 11
                ? "Woo, scary!"
                : "That one's my favour"
        );
        addCustomLoreEntry("pellet_rtg", MainRegistry.polaroidID == 11
                ? "Contains ~100% Pu238 oxide."
                : "RTG fuel pellet for infinite energy!"
        );
        addCustomLoreEntry("pellet_rtg_polonium", MainRegistry.polaroidID == 11
                ? "Polonium 4 U and me."
                : "Tastes nice in Tea"
        );
        addCustomLoreEntry("flame_pony", "Blue horse beats yellow horse, look it up!", I18nUtil.resolveKey("desc.flamepony"));
        addCustomLoreEntry("ingot_combine_steel", "*insert Civil Protection reference here*");
        addCustomLoreEntry(new String[] { "ingot_gh336", "billet_gh336", "nugget_gh336" }, "Seaborgium's colleague");
        addCustomLoreEntry(new String[] { "ingot_tantalium", "nugget_tantalium", "gem_tantalium", "powder_tantalium" }, "'Tantalum'");
        addCustomLoreEntry(new String[] { "factory_core_titanium", "factory_core_advanced" }, "Used in factories to make the speed change");
    }

}
