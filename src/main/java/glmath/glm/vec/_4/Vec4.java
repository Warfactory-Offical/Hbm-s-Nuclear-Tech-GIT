/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import glmath.glm.vec._2.Vec2;
import glmath.glm.vec._3.Vec3;

/**
 *
 * @author GBarbieri
 */
public class Vec4 extends colorSpace {

    public Vec4() {
        this(0);
    }

    public Vec4(final Vec4 v) {
        this(v.x, v.y, v.z, v.w);
    }

    public Vec4(final float f) {
        this(f, f, f, f);
    }

    public Vec4(final float[] fa) {
        this(fa, 0);
    }

    public Vec4(final float[] fa, final int i) {
        this(fa[i], fa[i + 1], fa[i + 2], fa[i + 3]);
    }

    public Vec4(final double d) {
        this((float) d);
    }

    public Vec4(final double[] da) {
        this(da, 0);
    }

    public Vec4(final double[] da, final int i) {
        this(da[i], da[i + 1], da[i + 2], da[i + 3]);
    }

    public Vec4(final float f, final Vec3 v) {
        this(f, v.x, v.y, v.z);
    }

    public Vec4(final Vec3 v, final float f) {
        this(v.x, v.y, v.z, f);
    }

    public Vec4(final Vec2 v0, final Vec2 v1) {
        this(v0.x, v0.y, v1.x, v1.y);
    }

    public Vec4(final Vec2 v, final float f0, final float f1) {
        this(v.x, v.y, f0, f1);
    }

    public Vec4(final double x, final double y, final double z, final double w) {
        this((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4(final float x, final float y, final float z, final float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4 set() {
        return set(0);
    }

    public Vec4 set(final Vec4 v) {
        return set(v.x, v.y, v.z, v.w);
    }

    public Vec4 set(final float f) {
        return set(f, f, f, f);
    }

    public Vec4 set(final float[] fa) {
        return set(fa, 0);
    }

    public Vec4 set(final float[] fa, final int i) {
        return set(fa[i], fa[i + 1], fa[i + 2], fa[i + 3]);
    }

    public Vec4 set(final double d) {
        return set(d, d, d, d);
    }

    public Vec4 set(final double[] da) {
        return set(da, 0);
    }

    public Vec4 set(final double[] da, final int i) {
        return set(da[i], da[i + 1], da[i + 2], da[i + 3]);
    }

    public Vec4 set(final float f, final Vec3 v) {
        return set(f, v.x, v.y, v.z);
    }

    public Vec4 set(final Vec3 v, final float f) {
        return set(v.x, v.y, v.z, f);
    }

    public Vec4 set(final Vec2 v0, final Vec2 v1) {
        return set(v0.x, v0.y, v1.x, v1.y);
    }

    public Vec4 set(final Vec2 v, final float f0, final float f1) {
        return set(v.x, v.y, f0, f1);
    }

    public Vec4 set(final double x, final double y, final double z, final double w) {
        return set((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4 set(final float x, final float y, final float z, final float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Vec3 toVec3_() {
        return toVec3(new Vec3());
    }

    public Vec3 toVec3(final Vec3 res) {
        return res.set(x, y, z);
    }
    
    public float[] toFA_() {
        return toFA(new float[4]);
    }

    public float[] toFA(final float[] fa) {
        fa[0] = x;
        fa[1] = y;
        fa[2] = z;
        fa[3] = w;
        return fa;
    }

    public FloatBuffer toDfb_() {
        return toDfb(ByteBuffer.allocateDirect(SIZE).order(ByteOrder.nativeOrder()).asFloatBuffer());
    }

    public FloatBuffer toDfb(final FloatBuffer fb) {
        return toDfb(fb, 0);
    }

    public FloatBuffer toDfb(final FloatBuffer fb, final int index) {
        return fb
                .put(index, x)
                .put(index + 1, y)
                .put(index + 2, z)
                .put(index + 3, w);
    }

    public ByteBuffer toDbb_() {
        return toDbb(ByteBuffer.allocateDirect(SIZE).order(ByteOrder.nativeOrder()));
    }

    public ByteBuffer toDbb(final ByteBuffer bb) {
        return toDbb(bb, 0);
    }

    public ByteBuffer toDbb(final ByteBuffer bb, final int index) {
        return bb
                .putFloat(index, x)
                .putFloat(index + Float.BYTES, y)
                .putFloat(index + 2 * Float.BYTES, z)
                .putFloat(index + 3 * Float.BYTES, w);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
    
    public void print() {
        print("", true);
    }

    public void print(final String title) {
        print(title, true);
    }

    public void print(final boolean outStream) {
        print("", outStream);
    }

    public void print(final String title, final boolean outStream) {
        final String res = title + "\n(" + x + ", " + y + ", " + z + ", " + w + ")";
        if (outStream) {
            System.out.print(res);
        } else {
            System.err.print(res);
        }
    }
}
