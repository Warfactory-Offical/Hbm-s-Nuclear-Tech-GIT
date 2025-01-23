package com.hbm.items.machine;

//import com.hbm.items.ItemEnumMulti;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.util.EnumUtil;
import com.hbm.util.function.Function;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import com.hbm.util.function.Function.*;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Locale;

//public class EnumWatzType extends ItemEnumMulti {

//    public EnumWatzType() {
//        super(EnumWatzType.class, true, true);
//        this.setMaxStackSize(16);
//        this.setCreativeTab(MainRegistry.controlTab);
//    }
//
//
//    public static enum EnumWatzType {
//
//        SCHRABIDIUM(	0x32FFFF, 0x005C5C, 2_000,	20D,	0.01D,		new FunctionLinear(1.5D), new FunctionSqrtFalling(10D), null),
//        HES(			0x66DCD6, 0x023933, 1_750,	20D,	0.005D,		new FunctionLinear(1.25D), new FunctionSqrtFalling(15D), null),
//        MES(			0xCBEADF, 0x28473C, 1_500,	15D,	0.0025D,	new FunctionLinear(1.15D), new FunctionSqrtFalling(15D), null),
//        LES(			0xABB4A8, 0x0C1105, 1_250,	15D,	0.00125D,	new FunctionLinear(1D), new FunctionSqrtFalling(20D), null),
//        HEN(			0xA6B2A6, 0x030F03, 0,		10D,	0.0005D,	new FunctionSqrt(100), new FunctionSqrtFalling(10D), null),
//        MEU(			0xC1C7BD, 0x2B3227, 0,		10D,	0.0005D,	new FunctionSqrt(75), new FunctionSqrtFalling(10D), null),
//        MEP(			0x9AA3A0, 0x111A17, 0,		15D,	0.0005D,	new FunctionSqrt(150), new FunctionSqrtFalling(10D), null),
//        LEAD(			0xA6A6B2, 0x03030F, 0,		0,		0.0025D,	null, null, new FunctionSqrt(10)), //standard absorber, negative coefficient
//        BORON(			0xBDC8D2, 0x29343E, 0,		0,		0.0025D,	null, null, new FunctionLinear(10)), //improved absorber, linear
//        DU(				0xC1C7BD, 0x2B3227, 0,		0,		0.0025D,	null, null, new FunctionQuadratic(1D, 1D).withDiv(100)), //absorber with positive coefficient
//        NQD(			0x4B4B4B, 0x121212, 2_000,	20,		0.01D,		new FunctionLinear(2D), new FunctionSqrt(1D/25D).withOff(25D * 25D), null),
//        NQR(			0x2D2D2D, 0x0B0B0B, 2_500,	30,		0.01D,		new FunctionLinear(1.5D), new FunctionSqrt(1D/25D).withOff(25D * 25D), null);
//
//        public double yield = 500_000_000;
//        public int colorLight;
//        public int colorDark;
//        public double mudContent;	//how much mud per reaction flux should be produced
//        public double passive;		//base flux emission
//        public double heatEmission;	//reactivity(1) to heat (heat per outgoing flux)
//        public Function burnFunc;	//flux to reactivity(0) (classic reactivity)
//        public Function heatDiv;	//reactivity(0) to reactivity(1) based on heat (temperature coefficient)
//        public Function absorbFunc;	//flux to heat (flux absobtion for non-active component)
//
//        private EnumWatzType(int colorLight, int colorDark, double passive, double heatEmission, double mudContent, Function burnFunction, Function heatDivisor, Function absorbFunction) {
//            this.colorLight = colorLight;
//            this.colorDark = colorDark;
//            this.passive = passive;
//            this.heatEmission = heatEmission;
//            this.mudContent = mudContent / 2D;
//            this.burnFunc = burnFunction;
//            this.heatDiv = heatDivisor;
//            this.absorbFunc = absorbFunction;
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//    public void registerModels() {
//        if (multiTexture) {
//            for (int i = 0; i < EnumWatzType.values().length; i++) {
//                EnumWatzType type = EnumWatzType.values()[i];
//                String modelName = this.getRegistryName().getPath() + "-" + type.name().toLowerCase(Locale.US);
//                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(new ResourceLocation(RefStrings.MODID, modelName), "inventory"));
//            }
//        } else {
//            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(RefStrings.MODID, getRegistryName().getPath()), "inventory"));
//        }
//    }
//
//    @Override
//    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
//        if (this != ModItems.watz_pellet) return;
//
//        EnumWatzType type = EnumUtil.grabEnumSafely(EnumWatzType.class, stack.getMetadata());
//        tooltip.add(TextFormatting.GREEN + "Depletion: " + String.format(Locale.US, "%.1f", getDurabilityForDisplay(stack) * 100D) + "%");
//
//        String color = TextFormatting.GOLD.toString();
//        String reset = TextFormatting.RESET.toString();
//
//        if (type.passive > 0) {
//            tooltip.add(color + "Base fission rate: " + reset + type.passive);
//            tooltip.add(TextFormatting.RED + "Self-igniting!");
//        }
//        if (type.heatEmission > 0) tooltip.add(color + "Heat per flux: " + reset + type.heatEmission + " TU");
//        if (type.burnFunc != null) {
//            tooltip.add(color + "Reaction function: " + reset + type.burnFunc.getLabelForFuel());
//            tooltip.add(color + "Fuel type: " + reset + type.burnFunc.getDangerFromFuel());
//        }
//        if (type.heatDiv != null) tooltip.add(color + "Thermal multiplier: " + reset + type.heatDiv.getLabelForFuel() + " TU⁻¹");
//        if (type.absorbFunc != null) tooltip.add(color + "Flux capture: " + reset + type.absorbFunc.getLabelForFuel());
//    }
//
//    @Override
//    public boolean showDurabilityBar(ItemStack stack) {
//        return this == ModItems.watz_pellet && getDurabilityForDisplay(stack) > 0D;
//    }
//
//    @Override
//    public double getDurabilityForDisplay(ItemStack stack) {
//        return 1D - getEnrichment(stack);
//    }
//
//    public static double getEnrichment(ItemStack stack) {
//        EnumWatzType type = EnumUtil.grabEnumSafely(EnumWatzType.class, stack.getMetadata());
//        return getYield(stack) / type.yield;
//    }
//
//    public static double getYield(ItemStack stack) {
//        return getDouble(stack, "yield");
//    }
//
//    public static void setYield(ItemStack stack, double yield) {
//        setDouble(stack, "yield", yield);
//    }
//
//    public static void setDouble(ItemStack stack, String key, double value) {
//        if (!stack.hasTagCompound()) setNBTDefaults(stack);
//        stack.getTagCompound().setDouble(key, value);
//    }
//
//    public static double getDouble(ItemStack stack, String key) {
//        if (!stack.hasTagCompound()) setNBTDefaults(stack);
//        return stack.getTagCompound().getDouble(key);
//    }
//
//    private static void setNBTDefaults(ItemStack stack) {
//        EnumWatzType type = EnumUtil.grabEnumSafely(EnumWatzType.class, stack.getMetadata());
//        stack.setTagCompound(new NBTTagCompound());
//        setYield(stack, type.yield);
//    }
//
//    @Override
//    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
//        if (this != ModItems.watz_pellet) return;
//        setNBTDefaults(stack);
//    }
//}

