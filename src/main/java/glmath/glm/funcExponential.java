/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

/**
 *
 * @author elect
 */
abstract class funcExponential extends FuncCommon {

    public static float exp(final float x) {
        return (float) Math.exp(x);
    }

    public static float exp2(final float x) {
        return (float) Math.pow(2, x);
    }

    public static float inverseSqrt(float x) {
        final float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x = x * (1.5f - xhalf * x * x);
        return x;
    }

    public static float log(final float x) {
        return (float) Math.log(x);
    }

    public static int log2(final int value) {
        return (int) (Math.log(value) / Math.log(2));
    }

    public static float pow(final float x, final float y) {
        return (float) Math.pow(x, y);
    }

    public static float sqrt(final float x) {
        return (float) Math.sqrt(x);
    }
}
