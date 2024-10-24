package api.hbm.item.component.behavior;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IItemSubHandler {
    String getItemSubType(ItemStack stack);

    void getSubItems(ItemStack stack, CreativeTabs tab, NonNullList<ItemStack> subItems);
}
