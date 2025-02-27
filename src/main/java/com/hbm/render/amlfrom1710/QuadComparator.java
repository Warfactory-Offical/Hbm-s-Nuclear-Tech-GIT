package com.hbm.render.amlfrom1710;

import java.util.Comparator;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class QuadComparator implements Comparator<Object>
{
    private final float field_147630_a;
    private final float field_147628_b;
    private final float field_147629_c;
    private final int[] field_147627_d;

    public QuadComparator(final int[] p_i45077_1_, final float p_i45077_2_, final float p_i45077_3_, final float p_i45077_4_)
    {
        this.field_147627_d = p_i45077_1_;
        this.field_147630_a = p_i45077_2_;
        this.field_147628_b = p_i45077_3_;
        this.field_147629_c = p_i45077_4_;
    }

    public int compare(final Integer p_compare_1_, final Integer p_compare_2_)
    {
        final float f = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue()]) - this.field_147630_a;
        final float f1 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 1]) - this.field_147628_b;
        final float f2 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 2]) - this.field_147629_c;
        final float f3 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 8]) - this.field_147630_a;
        final float f4 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 9]) - this.field_147628_b;
        final float f5 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 10]) - this.field_147629_c;
        final float f6 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 16]) - this.field_147630_a;
        final float f7 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 17]) - this.field_147628_b;
        final float f8 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 18]) - this.field_147629_c;
        final float f9 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 24]) - this.field_147630_a;
        final float f10 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 25]) - this.field_147628_b;
        final float f11 = Float.intBitsToFloat(this.field_147627_d[p_compare_1_.intValue() + 26]) - this.field_147629_c;
        final float f12 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue()]) - this.field_147630_a;
        final float f13 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 1]) - this.field_147628_b;
        final float f14 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 2]) - this.field_147629_c;
        final float f15 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 8]) - this.field_147630_a;
        final float f16 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 9]) - this.field_147628_b;
        final float f17 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 10]) - this.field_147629_c;
        final float f18 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 16]) - this.field_147630_a;
        final float f19 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 17]) - this.field_147628_b;
        final float f20 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 18]) - this.field_147629_c;
        final float f21 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 24]) - this.field_147630_a;
        final float f22 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 25]) - this.field_147628_b;
        final float f23 = Float.intBitsToFloat(this.field_147627_d[p_compare_2_.intValue() + 26]) - this.field_147629_c;
        final float f24 = (f + f3 + f6 + f9) * 0.25F;
        final float f25 = (f1 + f4 + f7 + f10) * 0.25F;
        final float f26 = (f2 + f5 + f8 + f11) * 0.25F;
        final float f27 = (f12 + f15 + f18 + f21) * 0.25F;
        final float f28 = (f13 + f16 + f19 + f22) * 0.25F;
        final float f29 = (f14 + f17 + f20 + f23) * 0.25F;
        final float f30 = f24 * f24 + f25 * f25 + f26 * f26;
        final float f31 = f27 * f27 + f28 * f28 + f29 * f29;
        return Float.compare(f31, f30);
    }

    public int compare(final Object p_compare_1_, final Object p_compare_2_)
    {
        return this.compare((Integer)p_compare_1_, (Integer)p_compare_2_);
    }
}
