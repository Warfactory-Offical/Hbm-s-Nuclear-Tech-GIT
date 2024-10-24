package api.hbm.item.material;

import api.hbm.item.base.StandardHBMItem;
import api.hbm.material.MaterialPrefix;
import api.hbm.material.NTMMaterial;
import api.hbm.util.registry.HBMRegistry;
import com.hbm.main.MainRegistry;
import net.minecraft.item.ItemStack;
    import org.jetbrains.annotations.NotNull;

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
            if (prefix != null && prefix.canGenerateItem(material)) {
                addItem(i, prefix.getName() + material.name);
            }
        }
    }

    public NTMMaterial getMaterial(@NotNull ItemStack stack) {
        return materialRegistry.getObjectById(stack.getMetadata());
    }
}
