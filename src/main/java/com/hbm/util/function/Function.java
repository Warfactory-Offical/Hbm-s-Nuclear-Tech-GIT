package com.hbm.util.function;

import com.hbm.util.BobMathUtil;
import net.minecraft.util.text.TextFormatting;

import java.util.Locale;

/**
 * A simple class for defining function types (linear, log, sqrt, etc.) for things like fuel reactivity.
 * Unlike current RBMK functions, constants used in the calculations have been removed
 *
 * @author hbm
 */
public abstract class Function {

    protected double div = 1D;
    protected double off = 0;

    //the german prononciation of f(x) - "F von X", tee hee
    public abstract double effonix(double x);
    public abstract String getLabelForFuel();
    public abstract String getDangerFromFuel();

    public Function withDiv(final double div) { this.div = div; return this; }

    public Function withOff(final double off) { this.off = off; return this; }

    public double getX(final double x) { return x / div + off; }
    public String getXName() { return getXName(true); }
    public String getXName(final boolean brackets) {
        String x = "x";
        final boolean mod = false;
        if(div != 1D) x += " / " + String.format(Locale.US, "%,.1f", div);
        if(off != 0D) x += " + " + String.format(Locale.US, "%,.1f", off);
        if(mod && brackets) x = "(" + x + ")";
        return x;
    }

    public static abstract class FunctionSingleArg extends Function {
        protected double level;
        public FunctionSingleArg(final double level) { this.level = level; }
    }

    public static abstract class FunctionDoubleArg extends Function {
        protected double level, vOff;
        public FunctionDoubleArg(final double level, final double vOff) { this.level = level; this.vOff = vOff; }
    }

    public static class FunctionLogarithmic extends FunctionSingleArg {
        public FunctionLogarithmic(final double level) { super(level); this.withOff(1D); }
        @Override public double effonix(final double x) { return Math.log10(getX(x)) * level; }
        @Override public String getLabelForFuel() { return "log10(" + getXName(false) + ") * " + String.format(Locale.US, "%,.1f", this.level); }
        @Override public String getDangerFromFuel() { return TextFormatting.YELLOW + "MEDIUM / LOGARITHMIC"; }
    }

    public static class FunctionPassive extends FunctionSingleArg {
        public FunctionPassive(final double level) { super(level); }
        @Override public double effonix(final double x) { return this.level; }
        @Override public String getLabelForFuel() { return String.format(Locale.US, "%,.1f", this.level); }
        @Override public String getDangerFromFuel() { return TextFormatting.DARK_GREEN + "SAFE / PASSIVE"; }
    }

    public static class FunctionSqrt extends FunctionSingleArg {
        public FunctionSqrt(final double level) { super(level); }
        @Override public double effonix(final double x) { return BobMathUtil.squirt(getX(x)) * this.level; }
        @Override public String getLabelForFuel() { return "sqrt(" + getXName(false) + ") * " + String.format(Locale.US, "%,.3f", this.level); } //not entirely correct but good enough
        @Override public String getDangerFromFuel() { return TextFormatting.YELLOW + "MEDIUM / SQUARE ROOT"; }
    }

    public static class FunctionSqrtFalling extends FunctionSqrt {
        public FunctionSqrtFalling(final double fallFactor) {
            super(1D / fallFactor);
            this.withOff(fallFactor * fallFactor);
        }
    }

    public static class FunctionLinear extends FunctionSingleArg {
        public FunctionLinear(final double level) { super(level); }
        @Override public double effonix(final double x) { return getX(x) * this.level; }
        @Override public String getLabelForFuel() { return getXName(true) + " * " + String.format(Locale.US, "%,.1f", this.level); }
        @Override public String getDangerFromFuel() { return TextFormatting.RED + "DANGEROUS / LINEAR"; }
    }

    public static class FunctionQuadratic extends FunctionDoubleArg {
        public FunctionQuadratic(final double level) { super(level, 0D); }
        public FunctionQuadratic(final double level, final double vOff) { super(level, vOff); }
        @Override public double effonix(final double x) { return getX(x) * getX(x) * this.level + this.vOff; }
        @Override public String getLabelForFuel() { return getXName(true) + "² * " + String.format(Locale.US, "%,.1f", this.level) + (vOff != 0 ? (" + " + String.format(Locale.US, "%,.1f", vOff)) : ""); }
        @Override public String getDangerFromFuel() { return TextFormatting.RED + "DANGEROUS / QUADRATIC"; }
    }
}