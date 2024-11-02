package api.hbm.item.material;

import api.hbm.item.base.StandardHBMItem;
import api.hbm.material.MaterialPrefix;
import api.hbm.material.NTMMaterial;
import api.hbm.util.registry.HBMRegistry;
import com.hbm.main.MainRegistry;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class HBMMaterialItem extends StandardHBMItem {
    private final HBMRegistry<String, NTMMaterial> materialRegistry;
    private final MaterialPrefix prefix;

    public HBMMaterialItem(@NotNull HBMRegistry<String, NTMMaterial> registry, @NotNull MaterialPrefix prefix) {
        this.materialRegistry = registry;
        this.prefix = prefix;
        this.setCreativeTab(MainRegistry.resourceTab);
    }

    @Override
    public void registerSubItems() {
        for (NTMMaterial material : materialRegistry) {
            short i = (short) materialRegistry.getIDForObject(material);
            if (prefix != null && canGenerate(prefix, material)) {
                addItem(i, prefix.getName() + material.name);
            }
        }
    }

    public void registerOreDict() {
        for (short metaitem : items.keySet()) {
            NTMMaterial material = getMaterial(metaitem);
            ItemStack item = new ItemStack(this, 1, metaitem);
            if (item.isEmpty()) continue;
            OreDictionary.registerOre(prefix.getName() + material.toCamelCaseStrng(), item);
            registerAlternativeOredict(item, material, prefix);
        }
    }

    @Override
    public @NotNull String getItemStackDisplayName(ItemStack stack) {
        NTMMaterial material = getMaterial(stack);
        if (material == null || prefix == null) return "";
        return prefix.getLocalNameForItem(material);
    }

    @Override
    protected int getColorForItemStack(ItemStack stack, int tintIndex) {
        if (tintIndex == 0) {
            NTMMaterial material = getMaterial(stack);
            if (material == null) {
                return 0xFFFFFF;
            }
            return material.solidColorLight;
        }
        return super.getColorForItemStack(stack, tintIndex);
    }

    @Override
    public void registerModels() {
        Map<Short, ModelResourceLocation> alreadyRegistered = new Short2ObjectOpenHashMap<>();
        for (short item : items.keySet()) {
            // TODO
        }
    }

    protected static boolean canGenerate(MaterialPrefix orePrefix, NTMMaterial material) {
        return orePrefix.canGenerateItem(material);
    }

    private static void registerAlternativeOredict(ItemStack item, NTMMaterial material, MaterialPrefix prefix) {
        //TODO add ur own stuff
        /*
        if (material == Mats.MAT_SILICON && prefix == MaterialPrefix.INGOT) {
            OreDictionary.registerOre("item" + material.name, item);
        }
         */
    }
    public NTMMaterial getMaterial(@NotNull ItemStack stack) {
        return materialRegistry.getObjectById(stack.getMetadata());
    }

    public NTMMaterial getMaterial(int metadata) {
        return Objects.requireNonNull(materialRegistry.getObjectById(metadata));
    }
}
