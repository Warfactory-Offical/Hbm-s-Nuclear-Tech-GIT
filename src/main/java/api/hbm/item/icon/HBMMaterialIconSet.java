package api.hbm.item.icon;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HBMMaterialIconSet {

    private static int idCounter = 0;

    public static final Map<String, HBMMaterialIconSet> ICON_SETS = new HashMap<>();
    public static final HBMMaterialIconSet DULL = new HBMMaterialIconSet("dull", null, true);

    public final String name;
    public final int id;
    public final boolean isRootIconset;

    public final HBMMaterialIconSet parentIconSet;

    public HBMMaterialIconSet(@NotNull String name) {
        this(name, HBMMaterialIconSet.DULL);
    }

    public HBMMaterialIconSet(@NotNull String name, @NotNull HBMMaterialIconSet parentIconSet) {
        this(name, parentIconSet, false);
    }

    public HBMMaterialIconSet(@NotNull String name, @Nullable HBMMaterialIconSet parentIconSet, boolean isRootIconset) {
        this.name = name.toLowerCase(Locale.ENGLISH);
        Preconditions.checkArgument(!ICON_SETS.containsKey(this.name),
                "HBMMaterialIconSet " + this.name + " already registered!");
        this.id = idCounter++;
        this.isRootIconset = isRootIconset;
        this.parentIconSet = parentIconSet;
        ICON_SETS.put(this.name, this);
    }

    public static HBMMaterialIconSet getByName(@NotNull String name) {
        return ICON_SETS.get(name.toLowerCase(Locale.ENGLISH));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
