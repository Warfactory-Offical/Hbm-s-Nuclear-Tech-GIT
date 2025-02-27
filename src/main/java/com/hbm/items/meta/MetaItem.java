package com.hbm.items.meta;

import com.hbm.items.ModItems;
import com.hbm.util.ItemStackUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class MetaItem<
        EShape extends Enum<?> & IMetaItemShape,
        EMaterial extends Enum<?> & IMetaItemMaterial
> extends Item {

    private final EMaterial[] materials;

    @SafeVarargs
    public MetaItem(final EShape shape, final EMaterial... materials) {
        this.materials = materials;

        final String name = shape.getResultingItemName();

        setTranslationKey(name);
        setRegistryName(name);
        setHasSubtypes(true);

//         for (EMaterial material : materials) {
//             OreDictManager.queueRegisterOre(shape.getOreDictionaryPrefix() + material.getNamePascalCase() + shape.getOreDictionarySuffix(), getItemStack(material));
//         }

        ModItems.ALL_ITEMS.add(this);
    }


    public ItemStack getItemStack(final EMaterial material, final int amount) {
        return ItemStackUtil.itemStackFrom(this, amount, Arrays.asList(materials).indexOf(material));
    }

    public ItemStack getItemStack(final EMaterial material) { return getItemStack(material, 1); }

    @Override
    public void getSubItems(@NotNull final CreativeTabs tab, @NotNull final NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (int i = 0; i < materials.length; ++i) {
                items.add(ItemStackUtil.itemStackFrom(this, 1, i));
            }
        }
    }

    @Override
    @NotNull
    public String getTranslationKey(final ItemStack stack) {
        final int meta = stack.getMetadata();
        if (meta < materials.length) return super.getTranslationKey() + "_" + materials[meta].getNameSnakeCase();
        return super.getTranslationKey() + "_error";
    }

    @SideOnly(Side.CLIENT)
    public void registerModels() {
        for (int i = 0; i < materials.length; i++) {
            final String materialName = materials[i].getNameSnakeCase();

            ModelLoader
                    .setCustomModelResourceLocation(this, i,
                            new ModelResourceLocation(
                                    Objects.requireNonNull(getRegistryName()) + "_" + materialName,
                                    "inventory"
                            )
                    );
        }
    }

    public MetaItem<EShape, EMaterial> setCreativeTab(final CreativeTabs creativeTab) {
        return (MetaItem<EShape, EMaterial>) super.setCreativeTab(creativeTab);
    }

}
