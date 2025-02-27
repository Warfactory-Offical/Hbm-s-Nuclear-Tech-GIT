/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.quat;

import glmath.glm.mat._4.Mat4;
import glmath.glm.vec._3.Vec3;

/**
 *
 * @author GBarbieri
 */
public class Quat extends funcGeometric {

    public Quat() {
        this(0.0f, 0.0f, 0.0f);
    }

    public Quat(final float x, final float y, final float z) {
        this(1.0f, x, y, z);
    }

    public Quat(final Quat q) {
        this(q.w, q.x, q.y, q.z);
    }

    public Quat(final float f, final Vec3 v) {
        this(f, v.x, v.y, v.z);
    }

    public Quat(final float w, final float x, final float y, final float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quat set() {
        return set(0.0f, 0.0f, 0.0f);
    }

    public Quat set(final float x, final float y, final float z) {
        return set(1.0f, x, y, z);
    }

    public Quat set(final Quat q) {
        return set(q.w, q.x, q.y, q.z);
    }

    public Quat set(final float f, final Vec3 v) {
        return set(f, v.x, v.y, v.z);
    }

    public Quat set(final float w, final float x, final float y, final float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Quat conjugate_() {
        return conjugate(new Quat());
    }

    public Quat conjugate() {
        return conjugate(this);
    }

    public Quat conjugate(final Quat res) {
        return res.set(w, -x, -y, -z);
    }

    public static Quat cast_(final Mat4 m) {
        return cast(m, new Quat());
    }
    
    public static Quat cast(final Mat4 m, final Quat res) {

        final float fourXSquaredMinus1 = m.m00 - m.m11 - m.m22;
        final float fourYSquaredMinus1 = m.m11 - m.m00 - m.m22;
        final float fourZSquaredMinus1 = m.m22 - m.m00 - m.m11;
        final float fourWSquaredMinus1 = m.m00 + m.m11 + m.m22;

        int biggestIndex = 0;
        float fourBiggestSquaredMinus1 = fourWSquaredMinus1;
        if (fourXSquaredMinus1 > fourBiggestSquaredMinus1) {
            fourBiggestSquaredMinus1 = fourXSquaredMinus1;
            biggestIndex = 1;
        }
        if (fourYSquaredMinus1 > fourBiggestSquaredMinus1) {
            fourBiggestSquaredMinus1 = fourYSquaredMinus1;
            biggestIndex = 2;
        }
        if (fourZSquaredMinus1 > fourBiggestSquaredMinus1) {
            fourBiggestSquaredMinus1 = fourZSquaredMinus1;
            biggestIndex = 3;
        }

        final float biggestVal = (float) (Math.sqrt(fourBiggestSquaredMinus1 + 1) * 0.5f);
        final float mult = 0.25f / biggestVal;

        switch (biggestIndex) {
            case 0:
                res.w = biggestVal;
                res.x = (m.m12 - m.m21) * mult;
                res.y = (m.m20 - m.m02) * mult;
                res.z = (m.m01 - m.m10) * mult;
                break;
            case 1:
                res.w = (m.m12 - m.m21) * mult;
                res.x = biggestVal;
                res.y = (m.m01 + m.m10) * mult;
                res.z = (m.m20 + m.m02) * mult;
                break;
            case 2:
                res.w = (m.m20 - m.m02) * mult;
                res.x = (m.m01 + m.m10) * mult;
                res.y = biggestVal;
                res.z = (m.m12 + m.m21) * mult;
                break;
            case 3:
                res.w = (m.m01 - m.m10) * mult;
                res.x = (m.m20 + m.m02) * mult;
                res.y = (m.m12 + m.m21) * mult;
                res.z = biggestVal;
                break;
        }
        return res;
    }

    public Mat4 toMat_() {
        return toMat(new Mat4());
    }

    public Mat4 toMat(final Mat4 res) {
        final float dx = x + x;
        float dy = y + y;
        final float dz = z + z;
        final float q00 = dx * x;
        float q11 = dy * y;
        final float q22 = dz * z;
        final float q01 = dx * y;
        float q02 = dx * z;
        final float q03 = dx * w;
        final float q12 = dy * z;
        float q13 = dy * w;
        final float q23 = dz * w;
        res.m00 = 1.0f - q11 - q22;
        res.m01 = q01 + q23;
        res.m02 = q02 - q13;
        res.m03 = 0.0f;
        res.m10 = q01 - q23;
        res.m11 = 1.0f - q22 - q00;
        res.m12 = q12 + q03;
        res.m13 = 0.0f;
        res.m20 = q02 + q13;
        res.m21 = q12 - q03;
        res.m22 = 1.0f - q11 - q00;
        res.m23 = 0.0f;
        res.m30 = 0.0f;
        res.m31 = 0.0f;
        res.m32 = 0.0f;
        res.m33 = 1.0f;
        return res;
    }
}
