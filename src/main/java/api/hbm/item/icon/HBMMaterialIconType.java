package api.hbm.item.icon;

import api.hbm.resource.ResourceHelper;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class HBMMaterialIconType {
    public static final Map<String, HBMMaterialIconType> ICON_TYPES = new HashMap<>();

    static int idCounter = 0;

    public static final HBMMaterialIconType ingot = new HBMMaterialIconType("ingot");
    public static final HBMMaterialIconType nugget = new HBMMaterialIconType("nugget");

    public final String name;
    public final int id;

    private static final Table<HBMMaterialIconType, HBMMaterialIconSet, ResourceLocation> ITEM_MODEL_CACHE = HashBasedTable
            .create();
    private static final Table<HBMMaterialIconType, HBMMaterialIconSet, ResourceLocation> BLOCK_TEXTURE_CACHE = HashBasedTable
            .create();
    private static final Table<HBMMaterialIconType, HBMMaterialIconSet, ResourceLocation> BLOCK_MODEL_CACHE = HashBasedTable
            .create();

    private static final String BLOCK_TEXTURE_PATH_FULL = "textures/blocks/material_sets/%s/%s.png";
    private static final String BLOCK_TEXTURE_PATH = "blocks/material_sets/%s/%s";

    private static final String ITEM_MODEL_PATH_FULL = "models/item/material_sets/%s/%s.json";
    private static final String ITEM_MODEL_PATH = "material_sets/%s/%s";

    private static final String BLOCK_MODEL_PATH_FULL = "models/block/material_sets/%s/%s.json";
    private static final String BLOCK_MODEL_PATH = "block/material_sets/%s/%s";


    public HBMMaterialIconType(String name) {
        this.name = name;
        Preconditions.checkArgument(!ICON_TYPES.containsKey(this.name),
                "HBMMaterialIconType " + this.name + "already registered!");
        this.id = idCounter++;
        ICON_TYPES.put(this.name, this);
    }

    @NotNull
    public ResourceLocation getBlockTexturePath(@NotNull HBMMaterialIconSet materialIconSet) {
        return recurseIconsetPath(materialIconSet, BLOCK_TEXTURE_CACHE, BLOCK_TEXTURE_PATH_FULL, BLOCK_TEXTURE_PATH);
    }

    @NotNull
    public ResourceLocation getBlockModelPath(@NotNull HBMMaterialIconSet materialIconSet) {
        return recurseIconsetPath(materialIconSet, BLOCK_MODEL_CACHE, BLOCK_MODEL_PATH_FULL, BLOCK_MODEL_PATH);
    }

    @NotNull
    public ResourceLocation getItemModelPath(@NotNull HBMMaterialIconSet materialIconSet) {
        return recurseIconsetPath(materialIconSet, ITEM_MODEL_CACHE, ITEM_MODEL_PATH_FULL, ITEM_MODEL_PATH);
    }


    @NotNull
    public ResourceLocation recurseIconsetPath(@NotNull HBMMaterialIconSet iconSet,
                                               @NotNull Table<HBMMaterialIconType, HBMMaterialIconSet, ResourceLocation> cache,
                                               @NotNull String fullPath, @NotNull String path) {
        if (cache.contains(this, iconSet)) {
            return cache.get(this, iconSet);
        }

        if (!iconSet.isRootIconset && FMLCommonHandler.instance().getSide().isClient()) {
            ResourceLocation fullLocation = new ResourceLocation("hbm", String.format(fullPath, iconSet.name, path));
            if (!ResourceHelper.doResourcePacksHaveResource(fullLocation)) {
                ResourceLocation iconSetPath = recurseIconsetPath(iconSet.parentIconSet, cache, fullPath, path);
                cache.put(this, iconSet, iconSetPath);
                return iconSetPath;
            }
        }

        ResourceLocation iconSetPath = new ResourceLocation("hbm", String.format(path, iconSet.name, this.name));
        cache.put(this, iconSet, iconSetPath);
        return iconSetPath;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @SuppressWarnings("unused") // Called from ASM-injected code
    public static void clearCache() {
        ITEM_MODEL_CACHE.clear();
        BLOCK_TEXTURE_CACHE.clear();
        BLOCK_MODEL_CACHE.clear();
    }
}
