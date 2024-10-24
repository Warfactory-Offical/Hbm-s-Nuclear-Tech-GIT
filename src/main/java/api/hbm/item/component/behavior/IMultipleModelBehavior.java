package api.hbm.item.component.behavior;

import api.hbm.item.component.IItemComponent;
import net.minecraft.item.ItemStack;

public interface IMultipleModelBehavior extends IItemComponent {
    int getModelIndex(ItemStack stack);
}
