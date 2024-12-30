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

    static int idCounter;
    public final static MaterialPrefix INGOT = new MaterialPrefix("ingot", 9, HBMMaterialIconType.ingot, Predicates.alwaysTrue(), null);


    private final Set<NTMMaterial> generatedMaterials = new HashSet<>();
    private final Set<NTMMaterial> ignoredMaterials = new HashSet<>();

    private final ArrayList<IMaterialProcessor> processors = new ArrayList<>();
    private final String name;
    private long materialAmount;
    private Predicate<NTMMaterial> genCondition;

    public final HBMMaterialIconType iconType;

    public final int id;

    @Nullable
    private Function<NTMMaterial, List<String>> tooltipFunc;

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

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
