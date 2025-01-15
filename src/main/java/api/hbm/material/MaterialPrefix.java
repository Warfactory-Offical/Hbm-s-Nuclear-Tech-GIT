package api.hbm.material;

import api.hbm.item.icon.HBMMaterialIconType;
import api.hbm.util.lang.LocaleUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class MaterialPrefix {

    private final static Map<String, MaterialPrefix> PREFIXES = new HashMap<>();

    public static final long M = 3628800;
    static int idCounter;


    public static final Predicate<NTMMaterial> hasOreProperty = mat -> mat.hasProperty(PropertyKey.ORE);
    public static final Predicate<NTMMaterial> hasGemProperty = mat -> mat.hasProperty(PropertyKey.GEM);
    public static final Predicate<NTMMaterial> hasPowderProperty = mat -> mat.hasProperty(PropertyKey.POWDER);
    public static final Predicate<NTMMaterial> hasIngotProperty = mat -> mat.hasProperty(PropertyKey.INGOT);
    public static final Predicate<NTMMaterial> hasBedrockOreProperty = mat -> mat.hasProperty(PropertyKey.BEDROCK_ORE);

    public final static MaterialPrefix INGOT = new MaterialPrefix("ingot", M, HBMMaterialIconType.ingot, Predicates.alwaysTrue(), hasOreProperty, null);
    public static final MaterialPrefix NUGGET = new MaterialPrefix("nugget", M/9, HBMMaterialIconType.nugget, Predicates.alwaysTrue(), ntmMaterial -> ntmMaterial.hasFlag(NUGGET), null );
    public static final MaterialPrefix FRAGMENT = new MaterialPrefix("fragment", M/9, HBMMaterialIconType.fragment, ntmMaterial -> ntmMaterial.hasFlag(FRAGMENT) && !ntmMaterial.hasFlag(NUGGET));
    public static final MaterialPrefix DUSTTINY = new MaterialPrefix("powder_tiny", M/9, HBMMaterialIconType.dustTiny,  hasPowderProperty);
    public static final MaterialPrefix WIRE = new MaterialPrefix("wire", M/8, HBMMaterialIconType.wire, ntmMaterial -> ntmMaterial.hasFlag(WIRE));
    public static final MaterialPrefix DENSEWIRE = new MaterialPrefix("dense_wire",  M, HBMMaterialIconType.denseWire, ntmMaterial -> ntmMaterial.hasFlag(DENSE_WIRE));
    public static final MaterialPrefix BILLET = new MaterialPrefix("billet", M/6, HBMMaterialIconType.billet, ntmMaterial -> ntmMaterial.materialFlags.hasFlag(BILLET));
    public static final MaterialPrefix GEM = new MaterialPrefix("gem", M, HBMMaterialIconType.gem, hasGemProperty);
    public static final MaterialPrefix CRYSTAL = new MaterialPrefix("crystal", 2*M, HBMMaterialIconType.crystal, hasOreProperty); //TODO: implement has ore for this one
    public static final MaterialPrefix DUST = new MaterialPrefix("powder", M, HBMMaterialIconType.powder, hasPowderProperty);
    public static final MaterialPrefix PLATE = new MaterialPrefix("plate", M, HBMMaterialIconType.plate, hasIngotProperty.and(ntmMaterial -> ntmMaterial.hasFlag(PLATE)));
    public static final MaterialPrefix CASTPLATE = new MaterialPrefix("cast_plate", 3*M, HBMMaterialIconType.castPlate, hasIngotProperty.and(ntmMaterial -> ntmMaterial.materialFlags.hasFlag(CAST_PLATE)));
    public static final MaterialPrefix WELDEDPLATE = new MaterialPrefix("welded_plate", M*6, HBMMaterialIconType.weldedPlate, hasIngotProperty.and(ntmMaterial -> ntmMaterial.hasFlag(WELDED_PLATE)));
    public static final MaterialPrefix SHELL = new MaterialPrefix("shell", M*4, HBMMaterialIconType.shell, hasIngotProperty.and(ntmMaterial -> ntmMaterial.hasFlag(SHELL)));
    public static final MaterialPrefix PIPE = new MaterialPrefix("ntmpipe", M*3, HBMMaterialIconType.pipe, hasIngotProperty.and(ntmMaterial -> ntmMaterial.hasFlag(PIPE)));
    public static final MaterialPrefix BLOCK = new MaterialPrefix("block", M*9, HBMMaterialIconType.block, hasIngotProperty.or(ntmMaterial -> ntmMaterial.hasFlag(BLOCK)));
    public static final MaterialPrefix HEAVY_COMPONENT = new MaterialPrefix("heavy_component", M*(256*3), HBMMaterialIconType.heavyComponent, hasIngotProperty.and(ntmMaterial -> ntmMaterial.hasFlag(CASTPLATE)));
    public static final MaterialPrefix BOLT = new MaterialPrefix("bolt", M/8, HBMMaterialIconType.bolt, hasIngotProperty.and(ntmMaterial -> ntmMaterial.hasFlag(BOLT)));

//    public static final MaterialPrefix QUANTUM = new MaterialPrefix("quantum", M/72, HBMMaterialIconType.quantum, Predicates.alwaysTrue(), null); // 1/72 of an ingot, allows the ingot to be divisible through 2, 4, 6, 8, 9, 12, 24 and 36 (idk why we need it)
//    public static final MaterialPrefix QUART = new MaterialPrefix("quart", null, 162, "quart"); cant find it in game?

    //TODO: when HBM gun port happens
//    public static final MaterialPrefix LIGHTBARREL = new MaterialPrefix("light_barrel", null, INGOT.quantity * 3, "barrelLight");
//    public static final MaterialPrefix HEAVYBARREL = new MaterialPrefix("heavy_barrel", null, INGOT.quantity * 6, "barrelHeavy");
//    public static final MaterialPrefix LIGHTRECEIVER = new MaterialPrefix("LIGHT_RECEIVER", null, INGOT.quantity * 4, "receiverLight");
//    public static final MaterialPrefix HEAVYRECEIVER = new MaterialPrefix("HEAVYRECEIVER", null, INGOT.quantity * 9, "receiverHeavy");
//    public static final MaterialPrefix MECHANISM = new MaterialPrefix("MECHANISM", null, INGOT.quantity * 4, "gunMechanism");
//    public static final MaterialPrefix STOCK = new MaterialPrefix("STOCK", null, INGOT.quantity * 4, "stock");
//    public static final MaterialPrefix GRIP = new MaterialPrefix("GRIP", null, INGOT.quantity * 2, "grip");


    private final Set<NTMMaterial> generatedMaterials = new HashSet<>();
    private final Set<NTMMaterial> ignoredMaterials = new HashSet<>();

    private final ArrayList<IMaterialProcessor> processors = new ArrayList<>();
    private final String name;
    private final long materialAmount;
    private final Predicate<NTMMaterial> genCondition;

    public final HBMMaterialIconType iconType;

    public final int id;

    @Nullable
    private final Function<NTMMaterial, List<String>> tooltipFunc;

    public MaterialPrefix(String name, long materialAmount, HBMMaterialIconType iconType, Predicate<NTMMaterial> condition, Function<NTMMaterial, @Nullable List<String>> tooltipFunc) {
        this.name = name;
        this.materialAmount = materialAmount;
        this.genCondition = condition;
        this.tooltipFunc = tooltipFunc;

        this.id = idCounter++;

        this.iconType = iconType;

        PREFIXES.put(name, this);
    }

    public String getName() {
        return name;
    }

    public String getLocalNameForItem(@NotNull NTMMaterial material) {
        String specifiedUnlocalized = "item." + material.getUnlocalizedName() + "." + this.name;
        if (LocaleUtils.hasKey(specifiedUnlocalized)) return LocaleUtils.format(specifiedUnlocalized);
        String unlocalized = String.format("item.hbmmat.oreprefix.%s", this.name);
        String matLocalized = material.getLocalizedName();
        String formatted = LocaleUtils.format(unlocalized, matLocalized);
        return formatted.equals(unlocalized) ? matLocalized : formatted;
    }


    public boolean addProcessingHandler(IMaterialProcessor... handler) {
        Preconditions.checkNotNull(handler);
        Validate.noNullElements(handler);
        return processors.addAll(Arrays.asList(handler));
    }

    public void addProcessingHandler(NTMMaterial.MatTraits trait, TriConsumer<MaterialPrefix, NTMMaterial, NTMMaterial.MatTraits> handler) {
        addProcessingHandler((material, prefix) -> {
           if (material.traits.contains(trait) && !material.traits.contains(NTMMaterial.MatTraits.NO_UNIFICATION)) {
               handler.accept(prefix, material, trait);
           }
        });
    }
    public boolean canGenerateItem(NTMMaterial material) {
        return !isIgnored(material) && (genCondition == null || genCondition.test(material));
    }

    private boolean isIgnored(NTMMaterial material) {
        return ignoredMaterials.contains(material);
    }

    public static class Conditions {
        public static final Predicate<Material> hasIngotProperty = mat -> mat.hasProperty(PropertyKey.INGOT);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
