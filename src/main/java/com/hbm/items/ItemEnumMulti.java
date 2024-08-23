//package com.hbm.items;

import java.util.List;
import java.util.Locale;

import com.hbm.lib.RefStrings;
//import com.hbm.util.EnumUtil;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//public class ItemEnumMulti extends Item {

//    protected Class<? extends Enum> theEnum;
//    protected boolean multiName;
//    protected boolean multiTexture;
//
//    public ItemEnumMulti(Class<? extends Enum> theEnum, boolean multiName, boolean multiTexture) {
//        this.setHasSubtypes(true);
//        this.theEnum = theEnum;
//        this.multiName = multiName;
//        this.multiTexture = multiTexture;
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
//        for (int i = 0; i < theEnum.getEnumConstants().length; i++) {
//            items.add(new ItemStack(this, 1, i));
//        }
//    }
//
//    @Override
//    public Item setTranslationKey(String unlocalizedName) {
//        super.setTranslationKey(unlocalizedName);
//        this.setRegistryName(new ResourceLocation(RefStrings.MODID, unlocalizedName));
//        return this;
//    }
//
//    //@Override
//    @SideOnly(Side.CLIENT)
//    public void registerModels() {
//        if (multiTexture) {
//            Enum[] enums = theEnum.getEnumConstants();
//            for (int i = 0; i < enums.length; i++) {
//                Enum num = enums[i];
//                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(new ResourceLocation(RefStrings.MODID, getTranslationKey().substring(5) + "." + num.name().toLowerCase(Locale.US)), "inventory"));
//            }
//        } else {
//            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(RefStrings.MODID, getTranslationKey().substring(5)), "inventory"));
//        }
//    }
//
//    @Override
//    public String getTranslationKey(ItemStack stack) {
//        if (multiName) {
//            Enum num = EnumUtil.grabEnumSafely(theEnum, stack.getMetadata());
//            return super.getTranslationKey() + "." + num.name().toLowerCase(Locale.US);
//        } else {
//            return super.getTranslationKey(stack);
//        }
//    }
//
//    /** Returns null when the wrong enum is passed. Only really used for recipes anyway so it's good. */
//    public ItemStack stackFromEnum(int count, Enum num) {
//        if (num.getClass() != this.theEnum)
//            return ItemStack.EMPTY;
//
//        return new ItemStack(this, count, num.ordinal());
//    }
//
//    public ItemStack stackFromEnum(Enum num) {
//        return stackFromEnum(1, num);
//    }
//}
