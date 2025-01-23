/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;
import glmath.glm.vec._2.i.Vec2i;

/**
 *
 * @author GBarbieri
 */
abstract class funcCommon extends BooleanOperators {

    public Vec2 abs() {
        return abs((Vec2) this);
    }

    public Vec2 abs_() {
        return abs(new Vec2());
    }

    public Vec2 abs(final Vec2 result) {
        return abs((Vec2) this, result);
    }

    public static Vec2 abs(final Vec2 x, final Vec2 result) {
        result.x = Math.abs(x.x);
        result.y = Math.abs(x.y);
        return result;
    }

    public Vec2 ceil() {
        return ceil((Vec2) this);
    }

    public Vec2 ceil_() {
        return ceil(new Vec2());
    }

    public Vec2 ceil(final Vec2 result) {
        return ceil((Vec2) this, (Vec2) this);
    }

    public static Vec2 ceil(final Vec2 x, final Vec2 result) {
        result.x = (float) Math.ceil(x.x);
        result.y = (float) Math.ceil(x.y);
        return result;
    }

    public Vec2 clamp(final float minVal, final float maxVal) {
        return clamp(minVal, maxVal, (Vec2) this);
    }

    public Vec2 clamp_(final float minVal, final float maxVal) {
        return clamp(minVal, maxVal, new Vec2());
    }

    public Vec2 clamp(final float minVal, final float maxVal, final Vec2 result) {
        return clamp((Vec2) this, minVal, maxVal, result);
    }

    public static Vec2 clamp(final Vec2 x, final float minVal, final float maxVal, final Vec2 result) {
        result.x = Math.min(Math.max(x.x, minVal), maxVal);
        result.y = Math.min(Math.max(x.y, minVal), maxVal);
        return result;
    }

    public Vec2 clamp(final Vec2 minVal, final Vec2 maxVal) {
        return clamp(minVal, maxVal, (Vec2) this);
    }

    public Vec2 clamp_(final Vec2 minVal, final Vec2 maxVal) {
        return clamp(minVal, maxVal, new Vec2());
    }

    public Vec2 clamp(final Vec2 minVal, final Vec2 maxVal, final Vec2 result) {
        return clamp((Vec2) this, minVal, maxVal, result);
    }

    public static Vec2 clamp(final Vec2 x, final Vec2 minVal, final Vec2 maxVal, final Vec2 result) {
        result.x = Math.min(Math.max(x.x, minVal.x), maxVal.x);
        result.y = Math.min(Math.max(x.y, minVal.y), maxVal.y);
        return result;
    }

    public Vec2i floatToIntBits_() {
        return floatToIntBits((Vec2) this, new Vec2i());
    }

    public Vec2i floatToIntBits(final Vec2i result) {
        return floatToIntBits((Vec2) this, result);
    }

    public static Vec2i floatToIntBits(final Vec2 x, final Vec2i result) {
        result.x = Float.floatToIntBits(x.x);
        result.y = Float.floatToIntBits(x.y);
        return result;
    }

    public Vec2 floor() {
        return floor((Vec2) this);
    }

    public Vec2 floor_() {
        return floor(new Vec2());
    }

    public Vec2 floor(final Vec2 result) {
        return floor((Vec2) this, result);
    }

    public static Vec2 floor(final Vec2 x, final Vec2 result) {
        result.x = (float) Math.floor(x.x);
        result.y = (float) Math.floor(x.y);
        return result;
    }

    public Vec2 fma(final Vec2 b, final Vec2 c) {
        return fma(b, c, (Vec2) this);
    }

    public Vec2 fma_(final Vec2 b, final Vec2 c) {
        return fma(b, c, new Vec2());
    }

    public Vec2 fma(final Vec2 b, final Vec2 c, final Vec2 result) {
        return fma((Vec2) this, b, c, result);
    }

    public static Vec2 fma(final Vec2 a, final Vec2 b, final Vec2 c, final Vec2 result) {
        result.x = a.x * b.x + c.x;
        result.y = a.y * b.y + c.y;
        return result;
    }

    public Vec2 fract() {
        return fract((Vec2) this);
    }

    public Vec2 fract_() {
        return fract(new Vec2());
    }

    public Vec2 fract(final Vec2 result) {
        return fract((Vec2) this, result);
    }

    public static Vec2 fract(final Vec2 x, final Vec2 result) {
        result.x = (float) (x.x - Math.floor(x.x));
        result.y = (float) (x.y - Math.floor(x.y));
        return result;
    }

    public Vec2bool isInf_() {
        return isInf((Vec2) this, new Vec2bool());
    }

    public Vec2bool isInf(final Vec2bool result) {
        return isInf((Vec2) this, result);
    }

    public static Vec2bool isInf(final Vec2 x, final Vec2bool result) {
        result.x = Float.isInfinite(x.x);
        result.y = Float.isInfinite(x.y);
        return result;
    }

    public Vec2bool isNan() {
        return isNan((Vec2) this, new Vec2bool());
    }

    public Vec2bool isNan(final Vec2bool result) {
        return isNan((Vec2) this, result);
    }

    public static Vec2bool isNan(final Vec2 x, final Vec2bool result) {
        result.x = Float.isNaN(x.x);
        result.y = Float.isNaN(x.y);
        return result;
    }

    public Vec2 max(final Vec2 y) {
        return max(y, (Vec2) this);
    }

    public Vec2 max_(final Vec2 y) {
        return max((Vec2) this, y, new Vec2());
    }

    public Vec2 max(final Vec2 y, final Vec2 result) {
        return max((Vec2) this, y, result);
    }

    public static Vec2 max(final Vec2 x, final Vec2 y, final Vec2 result) {
        result.x = Math.max(x.x, y.x);
        result.y = Math.max(x.y, y.y);
        return result;
    }

    public Vec2 min(final Vec2 y) {
        return min(y, (Vec2) this);
    }

    public Vec2 min_(final Vec2 y) {
        return min(y, new Vec2());
    }

    public Vec2 min(final Vec2 y, final Vec2 result) {
        return min((Vec2) this, y, result);
    }

    public static Vec2 min(final Vec2 x, final Vec2 y, final Vec2 result) {
        result.x = Math.min(x.x, y.x);
        result.y = Math.min(x.y, y.y);
        return result;
    }

    public Vec2 mix(final Vec2 y, final boolean a) {
        return mix(y, a, (Vec2) this);
    }

    public Vec2 mix_(final Vec2 y, final boolean a) {
        return mix(y, a, new Vec2());
    }

    public Vec2 mix(final Vec2 y, final boolean a, final Vec2 result) {
        return mix((Vec2) this, y, a, result);
    }

    public static Vec2 mix(final Vec2 x, final Vec2 y, final boolean a, final Vec2 result) {
        result.x = a ? y.x : x.x;
        result.y = a ? y.y : x.y;
        return result;
    }

    public Vec2 mix(final Vec2 y, final Vec2bool a) {
        return mix(y, a, (Vec2) this);
    }

    public Vec2 mix_(final Vec2 y, final Vec2bool a) {
        return mix(y, a, new Vec2());
    }

    public Vec2 mix(final Vec2 y, final Vec2bool a, final Vec2 result) {
        return mix((Vec2) this, y, a, result);
    }

    public static Vec2 mix(final Vec2 x, final Vec2 y, final Vec2bool a, final Vec2 result) {
        result.x = a.x ? y.x : x.x;
        result.y = a.y ? y.y : x.y;
        return result;
    }

    public Vec2 mix(final Vec2 y, final float a) {
        return mix(y, a, (Vec2) this);
    }

    public Vec2 mix_(final Vec2 y, final float a) {
        return mix(y, a, new Vec2());
    }

    public Vec2 mix(final Vec2 y, final float a, final Vec2 result) {
        return mix((Vec2) this, y, a, result);
    }

    public static Vec2 mix(final Vec2 x, final Vec2 y, final float a, final Vec2 result) {
        result.x = x.x + a * (y.x - x.x);
        result.y = x.y + a * (y.y - x.y);
        return result;
    }

    public Vec2 mix(final Vec2 y, final Vec2 a) {
        return mix(y, a, (Vec2) this);
    }

    public Vec2 mix_(final Vec2 y, final Vec2 a) {
        return mix(y, a, new Vec2());
    }

    public Vec2 mix(final Vec2 y, final Vec2 a, final Vec2 result) {
        return mix((Vec2) this, y, a, result);
    }

    public static Vec2 mix(final Vec2 x, final Vec2 y, final Vec2 a, final Vec2 result) {
        result.x = x.x + a.x * (y.x - x.x);
        result.y = x.y + a.y * (y.y - x.y);
        return result;
    }

    public Vec2 mod(final Vec2 y) {
        return mod(y, (Vec2) this);
    }

    public Vec2 mod_(final Vec2 y) {
        return mod(y, new Vec2());
    }

    public Vec2 mod(final Vec2 y, final Vec2 result) {
        return mod((Vec2) this, y, result);
    }

    public static Vec2 mod(final Vec2 x, final Vec2 y, final Vec2 result) {
        result.x = x.x - y.x * Glm.floor(x.x / y.x);
        result.y = x.y - y.y * Glm.floor(x.y / y.y);
        return result;
    }

    public Vec2i round_() {
        return round(new Vec2i());
    }

    public Vec2i round(final Vec2i result) {
        return round((Vec2) this, result);
    }

    public static Vec2i round(final Vec2 x, final Vec2i result) {
        result.x = Math.round(x.x);
        result.y = Math.round(x.y);
        return result;
    }

    public Vec2 sign() {
        return sign((Vec2) this, (Vec2) this);
    }

    public Vec2 sign_() {
        return sign(new Vec2());
    }

    public Vec2 sign(final Vec2 result) {
        return sign((Vec2) this, result);
    }

    public static Vec2 sign(final Vec2 x, final Vec2 result) {
        result.x = Math.signum(x.x);
        result.y = Math.signum(x.y);
        return result;
    }

    public Vec2 smoothStep(final Vec2 edge0, final Vec2 edge1) {
        return smoothStep(edge0, edge1, (Vec2) this);
    }

    public Vec2 smoothStep_(final Vec2 edge0, final Vec2 edge1) {
        return smoothStep(edge0, edge1, new Vec2());
    }

    public Vec2 smoothStep(final Vec2 edge0, final Vec2 edge1, final Vec2 result) {
        return smoothStep(edge0, edge1, (Vec2) this, result);
    }

    public static Vec2 smoothStep(final Vec2 edge0, final Vec2 edge1, final Vec2 x, final Vec2 result) {
        // clamp
        final float tmpX = Math.min(Math.max((x.x - edge0.x) / (edge1.x - edge0.x), 0), 1);
        final float tmpY = Math.min(Math.max((x.y - edge0.y) / (edge1.y - edge0.y), 0), 1);
        result.x = tmpX * tmpX * (3 - 2 * tmpX);
        result.y = tmpY * tmpY * (3 - 2 * tmpY);
        return result;
    }

    public Vec2 step(final Vec2 edge) {
        return step(edge, (Vec2) this);
    }

    public Vec2 step_(final Vec2 edge) {
        return step(edge, new Vec2());
    }

    public Vec2 step(final Vec2 edge, final Vec2 result) {
        return step(edge, (Vec2) this, result);
    }

    public static Vec2 step(final Vec2 edge, final Vec2 x, final Vec2 result) {
        result.x = x.x < edge.x ? 0f : 1f;
        result.y = x.y < edge.y ? 0f : 1f;
        return result;
    }

    public Vec2 toUnsignedFloat() {
        return toUnsignedFloat((Vec2) this);
    }

    public Vec2 toUnsignedFloat_() {
        return toUnsignedFloat(new Vec2());
    }

    public Vec2 toUnsignedFloat(final Vec2 result) {
        return toUnsignedFloat((Vec2) this, result);
    }

    public static Vec2 toUnsignedFloat(final Vec2 x, final Vec2 result) {
        result.x = Float.intBitsToFloat((int) x.x);
        result.y = Float.intBitsToFloat((int) x.y);
        return result;
    }
}
