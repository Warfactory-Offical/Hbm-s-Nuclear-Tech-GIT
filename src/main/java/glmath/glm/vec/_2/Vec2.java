/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import glmath.glm.vec._2.i.Vec2i;
import glmath.glm.vec._3.Vec3;
import glmath.glm.vec._4.Vec4;

/**
 *
 * @author GBarbieri
 */
public class Vec2 extends FuncRelational {

    public Vec2() {
        x = 0f;
        y = 0f;
    }

    public Vec2(final float f) {
        x = f;
        y = f;
    }

    public Vec2(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(final double f) {
        x = (float) f;
        y = (float) f;
    }

    public Vec2(final double x, final double y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    public Vec2(final Vec2 v) {
        x = v.x;
        y = v.y;
    }

    public Vec2(final Vec2i v) {
        x = v.x;
        y = v.y;
    }

    public Vec2(final Vec3 v) {
        x = v.x;
        y = v.y;
    }

    public Vec2(final Vec4 v) {
        x = v.x;
        y = v.y;
    }

    public Vec2 set(final float f) {
        x = f;
        y = f;
        return this;
    }

    public Vec2 set(final double x, final double y) {
        return set((float) x, (float) y);
    }

    public Vec2 set(final float x, final float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2 set(final int[] ia) {
        x = ia[0];
        y = ia[1];
        return this;
    }

    public Vec2 set(final float[] fa) {
        x = fa[0];
        y = fa[1];
        return this;
    }

    public float[] toFA_() {
        return toFA(new float[2]);
    }

    public float[] toFA(final float[] fa) {
        fa[0] = x;
        fa[1] = y;
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
                .put(index + 1, y);
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
                .putFloat(index + Float.BYTES, y);
    }

    /**
     * Return the length of this vector.
     *
     * @return the length
     */
    public float length() {
        return (float) Math.sqrt((x * x) + (y * y));
    }

    /**
     * Normalize this vector.
     *
     * @return this
     */
    public Vec2 normalize() {
        return normalize(this);
    }

    /**
     * Normalize this vector and store the result in <code>dest</code>.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Vec2 normalize(final Vec2 dest) {
        final float invLength = (float) (1.0 / Math.sqrt(x * x + y * y));
        dest.x = x * invLength;
        dest.y = y * invLength;
        return dest;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
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
        final String res = title + "\n(" + x + ", " + y + ")";
        if (outStream) {
            System.out.print(res);
        } else {
            System.err.print(res);
        }
    }

}
