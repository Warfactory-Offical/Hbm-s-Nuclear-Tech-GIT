package api.hbm.item.component.behavior;

import net.minecraft.item.ItemStack;

public interface IItemNameProvider {
    String getItemStackDisplayName(ItemStack stack, String unlocalizedName);
}
