/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4;

import glmath.glm.Glm;
import glmath.glm.vec._4.b.Vec4b;
import glmath.glm.vec._4.i.Vec4i;

/**
 *
 * @author GBarbieri
 */
abstract class funcCommon extends ArithmeticOperators {

    public Vec4 abs() {
        return abs((Vec4) this);
    }

    public Vec4 abs_() {
        return abs(new Vec4());
    }

    public Vec4 abs(final Vec4 result) {
        return abs((Vec4) this, result);
    }

    public static Vec4 abs(final Vec4 x, final Vec4 result) {
        result.x = Math.abs(x.x);
        result.y = Math.abs(x.y);
        result.z = Math.abs(x.z);
        result.w = Math.abs(x.w);
        return result;
    }

    public Vec4 ceil() {
        return ceil((Vec4) this);
    }

    public Vec4 ceil_() {
        return ceil(new Vec4());
    }

    public Vec4 ceil(final Vec4 result) {
        return ceil((Vec4) this, (Vec4) this);
    }

    public static Vec4 ceil(final Vec4 x, final Vec4 result) {
        result.x = (float) Math.ceil(x.x);
        result.y = (float) Math.ceil(x.y);
        result.z = (float) Math.ceil(x.z);
        result.w = (float) Math.ceil(x.w);
        return result;
    }

    public Vec4 clamp(final float minVal, final float maxVal) {
        return clamp(minVal, maxVal, (Vec4) this);
    }

    public Vec4 clamp_(final float minVal, final float maxVal) {
        return clamp(minVal, maxVal, new Vec4());
    }

    public Vec4 clamp(final float minVal, final float maxVal, final Vec4 result) {
        return clamp((Vec4) this, minVal, maxVal, result);
    }

    public static Vec4 clamp(final Vec4 x, final float minVal, final float maxVal, final Vec4 result) {
        result.x = Math.min(Math.max(x.x, minVal), maxVal);
        result.y = Math.min(Math.max(x.y, minVal), maxVal);
        result.z = Math.min(Math.max(x.z, minVal), maxVal);
        result.w = Math.min(Math.max(x.w, minVal), maxVal);
        return result;
    }

    public Vec4 clamp(final Vec4 minVal, final Vec4 maxVal) {
        return clamp(minVal, maxVal, (Vec4) this);
    }

    public Vec4 clamp_(final Vec4 minVal, final Vec4 maxVal) {
        return clamp(minVal, maxVal, new Vec4());
    }

    public Vec4 clamp(final Vec4 minVal, final Vec4 maxVal, final Vec4 result) {
        return clamp((Vec4) this, minVal, maxVal, result);
    }

    public static Vec4 clamp(final Vec4 x, final Vec4 minVal, final Vec4 maxVal, final Vec4 result) {
        result.x = Math.min(Math.max(x.x, minVal.x), maxVal.x);
        result.y = Math.min(Math.max(x.y, minVal.y), maxVal.y);
        result.z = Math.min(Math.max(x.z, minVal.z), maxVal.z);
        result.w = Math.min(Math.max(x.w, minVal.w), maxVal.w);
        return result;
    }

    public Vec4i floatToIntBits_() {
        return floatToIntBits((Vec4) this, new Vec4i());
    }

    public Vec4i floatToIntBits(final Vec4i result) {
        return floatToIntBits((Vec4) this, result);
    }

    public static Vec4i floatToIntBits(final Vec4 x, final Vec4i result) {
        result.x = Float.floatToIntBits(x.x);
        result.y = Float.floatToIntBits(x.y);
        result.z = Float.floatToIntBits(x.z);
        result.w = Float.floatToIntBits(x.w);
        return result;
    }

    public Vec4 floor() {
        return floor((Vec4) this);
    }

    public Vec4 floor_() {
        return floor(new Vec4());
    }

    public Vec4 floor(final Vec4 result) {
        return floor((Vec4) this, result);
    }

    public static Vec4 floor(final Vec4 x, final Vec4 result) {
        result.x = (float) Math.floor(x.x);
        result.y = (float) Math.floor(x.y);
        result.z = (float) Math.floor(x.z);
        result.w = (float) Math.floor(x.w);
        return result;
    }

    public Vec4 fma(final Vec4 b, final Vec4 c) {
        return fma(b, c, (Vec4) this);
    }

    public Vec4 fma_(final Vec4 b, final Vec4 c) {
        return fma(b, c, new Vec4());
    }

    public Vec4 fma(final Vec4 b, final Vec4 c, final Vec4 result) {
        return fma((Vec4) this, b, c, result);
    }

    public static Vec4 fma(final Vec4 a, final Vec4 b, final Vec4 c, final Vec4 result) {
        result.x = a.x * b.x + c.x;
        result.y = a.y * b.y + c.y;
        result.z = a.z * b.z + c.z;
        result.w = a.w * b.w + c.w;
        return result;
    }

    public Vec4 fract() {
        return fract((Vec4) this);
    }

    public Vec4 fract_() {
        return fract(new Vec4());
    }

    public Vec4 fract(final Vec4 result) {
        return fract((Vec4) this, result);
    }

    public static Vec4 fract(final Vec4 x, final Vec4 result) {
        result.x = (float) (x.x - Math.floor(x.x));
        result.y = (float) (x.y - Math.floor(x.y));
        result.z = (float) (x.z - Math.floor(x.z));
        result.w = (float) (x.w - Math.floor(x.w));
        return result;
    }

    public Vec4b isInf_() {
        return isInf((Vec4) this, new Vec4b());
    }

    public Vec4b isInf(final Vec4b result) {
        return isInf((Vec4) this, result);
    }

    public static Vec4b isInf(final Vec4 x, final Vec4b result) {
        result.x = (byte) (Float.isInfinite(x.x) ? 1 : 0);
        result.y = (byte) (Float.isInfinite(x.y) ? 1 : 0);
        result.z = (byte) (Float.isInfinite(x.z) ? 1 : 0);
        result.w = (byte) (Float.isInfinite(x.w) ? 1 : 0);
        return result;
    }

    public Vec4b isNan() {
        return isNan((Vec4) this, new Vec4b());
    }

    public Vec4b isNan(final Vec4b result) {
        return isNan((Vec4) this, result);
    }

    public static Vec4b isNan(final Vec4 x, final Vec4b result) {
        result.x = (byte) (Float.isNaN(x.x) ? 1 : 0);
        result.y = (byte) (Float.isNaN(x.y) ? 1 : 0);
        result.z = (byte) (Float.isNaN(x.z) ? 1 : 0);
        result.w = (byte) (Float.isNaN(x.z) ? 1 : 0);
        return result;
    }

    public Vec4 max(final Vec4 y) {
        return max(y, (Vec4) this);
    }

    public Vec4 max_(final Vec4 y) {
        return max((Vec4) this, y, new Vec4());
    }

    public Vec4 max(final Vec4 y, final Vec4 result) {
        return max((Vec4) this, y, result);
    }

    public static Vec4 max(final Vec4 x, final Vec4 y, final Vec4 result) {
        result.x = Math.max(x.x, y.x);
        result.y = Math.max(x.y, y.y);
        result.z = Math.max(x.z, y.z);
        result.w = Math.max(x.z, y.z);
        return result;
    }

    public Vec4 min(final Vec4 y) {
        return min(y, (Vec4) this);
    }

    public Vec4 min_(final Vec4 y) {
        return min(y, new Vec4());
    }

    public Vec4 min(final Vec4 y, final Vec4 result) {
        return min((Vec4) this, y, result);
    }

    public static Vec4 min(final Vec4 x, final Vec4 y, final Vec4 result) {
        result.x = Math.min(x.x, y.x);
        result.y = Math.min(x.y, y.y);
        result.z = Math.min(x.z, y.z);
        result.w = Math.min(x.w, y.w);
        return result;
    }

    public Vec4 mix(final Vec4 y, final boolean a) {
        return mix(y, a, (Vec4) this);
    }

    public Vec4 mix_(final Vec4 y, final boolean a) {
        return mix(y, a, new Vec4());
    }

    public Vec4 mix(final Vec4 y, final boolean a, final Vec4 result) {
        return mix((Vec4) this, y, a, result);
    }

    public static Vec4 mix(final Vec4 x, final Vec4 y, final boolean a, final Vec4 result) {
        return mix(x, y, a, a, a, a, result);
    }

    public Vec4 mix(final Vec4 y, final Vec4b a) {
        return mix(y, a, (Vec4) this);
    }

    public Vec4 mix_(final Vec4 y, final Vec4b a) {
        return mix(y, a, new Vec4());
    }

    public Vec4 mix(final Vec4 y, final Vec4b a, final Vec4 result) {
        return mix((Vec4) this, y, a, result);
    }

    public static Vec4 mix(final Vec4 x, final Vec4 y, final Vec4b a, final Vec4 result) {
        return mix(x, y, a.x, a.y, a.z, a.w, result);
    }

    public static Vec4 mix(final Vec4 x, final Vec4 y, final boolean a0, final boolean a1, final boolean a2, final boolean a3, final Vec4 result) {
        result.x = a0 ? y.x : x.x;
        result.y = a1 ? y.y : x.y;
        result.z = a2 ? y.z : x.z;
        result.w = a3 ? y.w : x.w;
        return result;
    }

    public Vec4 mix(final Vec4 y, final float a) {
        return mix(y, a, (Vec4) this);
    }

    public Vec4 mix_(final Vec4 y, final float a) {
        return mix(y, a, new Vec4());
    }

    public Vec4 mix(final Vec4 y, final float a, final Vec4 result) {
        return mix((Vec4) this, y, a, result);
    }

    public static Vec4 mix(final Vec4 x, final Vec4 y, final float a, final Vec4 result) {
        return mix(x, y, a, a, a, a, result);
    }

    public Vec4 mix(final Vec4 y, final Vec4 a) {
        return mix(y, a, (Vec4) this);
    }

    public Vec4 mix_(final Vec4 y, final Vec4 a) {
        return mix(y, a, new Vec4());
    }

    public Vec4 mix(final Vec4 y, final Vec4 a, final Vec4 result) {
        return mix((Vec4) this, y, a, result);
    }

    public static Vec4 mix(final Vec4 x, final Vec4 y, final Vec4 a, final Vec4 result) {
        return mix(x, y, a.x, a.y, a.z, a.w, result);
    }

    public static Vec4 mix(final Vec4 x, final Vec4 y, final float a0, final float a1, final float a2, final float a3, final Vec4 result) {
        result.x = x.x + a0 * (y.x - x.x);
        result.y = x.y + a1 * (y.y - x.y);
        result.z = x.z + a2 * (y.z - x.z);
        result.w = x.w + a3 * (y.w - x.w);
        return result;
    }

    public Vec4 mod(final Vec4 y) {
        return mod(y, (Vec4) this);
    }

    public Vec4 mod_(final Vec4 y) {
        return mod(y, new Vec4());
    }

    public Vec4 mod(final Vec4 y, final Vec4 result) {
        return mod((Vec4) this, y, result);
    }

    public static Vec4 mod(final Vec4 x, final Vec4 y, final Vec4 result) {
        result.x = x.x - y.x * Glm.floor(x.x / y.x);
        result.y = x.y - y.y * Glm.floor(x.y / y.y);
        result.z = x.z - y.z * Glm.floor(x.z / y.z);
        result.w = x.w - y.w * Glm.floor(x.w / y.w);
        return result;
    }

    public Vec4i round_() {
        return round(new Vec4i());
    }

    public Vec4i round(final Vec4i result) {
        return round((Vec4) this, result);
    }

    public static Vec4i round(final Vec4 x, final Vec4i result) {
        result.x = Math.round(x.x);
        result.y = Math.round(x.y);
        result.z = Math.round(x.z);
        result.w = Math.round(x.z);
        return result;
    }

    public Vec4 sign() {
        return sign((Vec4) this, (Vec4) this);
    }

    public Vec4 sign_() {
        return sign(new Vec4());
    }

    public Vec4 sign(final Vec4 result) {
        return sign((Vec4) this, result);
    }

    public static Vec4 sign(final Vec4 x, final Vec4 result) {
        result.x = Math.signum(x.x);
        result.y = Math.signum(x.y);
        result.z = Math.signum(x.z);
        result.w = Math.signum(x.w);
        return result;
    }

    public Vec4 smoothStep(final Vec4 edge0, final Vec4 edge1) {
        return smoothStep(edge0, edge1, (Vec4) this);
    }

    public Vec4 smoothStep_(final Vec4 edge0, final Vec4 edge1) {
        return smoothStep(edge0, edge1, new Vec4());
    }

    public Vec4 smoothStep(final Vec4 edge0, final Vec4 edge1, final Vec4 result) {
        return smoothStep(edge0, edge1, (Vec4) this, result);
    }

    public static Vec4 smoothStep(final Vec4 edge0, final Vec4 edge1, final Vec4 x, final Vec4 result) {
        // clamp
        final float tmpX = Math.min(Math.max((x.x - edge0.x) / (edge1.x - edge0.x), 0), 1);
        final float tmpY = Math.min(Math.max((x.y - edge0.y) / (edge1.y - edge0.y), 0), 1);
        final float tmpZ = Math.min(Math.max((x.z - edge0.z) / (edge1.z - edge0.z), 0), 1);
        final float tmpW = Math.min(Math.max((x.w - edge0.w) / (edge1.w - edge0.w), 0), 1);
        result.x = tmpX * tmpX * (3 - 2 * tmpX);
        result.y = tmpY * tmpY * (3 - 2 * tmpY);
        result.z = tmpZ * tmpZ * (3 - 2 * tmpZ);
        result.w = tmpW * tmpW * (3 - 2 * tmpW);
        return result;
    }

    public Vec4 step(final Vec4 edge) {
        return step(edge, (Vec4) this);
    }

    public Vec4 step_(final Vec4 edge) {
        return step(edge, new Vec4());
    }

    public Vec4 step(final Vec4 edge, final Vec4 result) {
        return step(edge, (Vec4) this, result);
    }

    public static Vec4 step(final Vec4 edge, final Vec4 x, final Vec4 result) {
        result.x = x.x < edge.x ? 0f : 1f;
        result.y = x.y < edge.y ? 0f : 1f;
        result.z = x.z < edge.z ? 0f : 1f;
        result.w = x.w < edge.w ? 0f : 1f;
        return result;
    }

    public Vec4 toUnsignedFloat() {
        return toUnsignedFloat((Vec4) this);
    }

    public Vec4 toUnsignedFloat_() {
        return toUnsignedFloat(new Vec4());
    }

    public Vec4 toUnsignedFloat(final Vec4 result) {
        return toUnsignedFloat((Vec4) this, result);
    }

    public static Vec4 toUnsignedFloat(final Vec4 x, final Vec4 result) {
        result.x = Float.intBitsToFloat((int) x.x);
        result.y = Float.intBitsToFloat((int) x.y);
        result.z = Float.intBitsToFloat((int) x.z);
        result.w = Float.intBitsToFloat((int) x.w);
        return result;
    }
}
