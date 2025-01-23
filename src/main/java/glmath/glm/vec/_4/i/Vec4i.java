/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.i;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import glmath.glm.vec._2.i.Vec2i;
import glmath.glm.vec._3.i.Vec3i;

/**
 *
 * @author GBarbieri
 */
public class Vec4i extends FuncRelational {

    public Vec4i(final Vec4i v) {
        this(v.x, v.y, v.z, v.w);
    }

    public Vec4i() {
        this(0);
    }

    public Vec4i(final int i) {
        this(i, i, i, i);
    }

    public Vec4i(final int x, final int y, final int z, final int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4i(final int[] ia) {
        this(ia, 0);
    }

    public Vec4i(final int[] ia, final int i) {
        this(ia[i], ia[i + 1], ia[i + 2], ia[i + 3]);
    }

    public Vec4i(final long l) {
        this((int) l);
    }

    public Vec4i(final long[] la) {
        this(la, 0);
    }

    public Vec4i(final long[] la, final int i) {
        this(la[i], la[i + 1], la[i + 2], la[i + 3]);
    }

    public Vec4i(final int i, final Vec3i v) {
        this(i, v.x, v.y, v.z);
    }

    public Vec4i(final Vec3i v, final int i) {
        this(v.x, v.y, v.z, i);
    }

    public Vec4i(final Vec2i v0, final Vec2i v1) {
        this(v0.x, v0.y, v1.x, v1.y);
    }

    public Vec4i(final Vec2i v, final int i0, final int i1) {
        this(v.x, v.y, i0, i1);
    }

    public Vec4i(final long x, final long y, final long z, final long w) {
        this((int) x, (int) y, (int) z, (int) w);
    }

    public Vec4i set() {
        return set(0);
    }

    public Vec4i set(final Vec4i v) {
        return set(v.x, v.y, v.z, v.w);
    }

    public Vec4i set(final int i) {
        return set(i, i, i, i);
    }

    public Vec4i set(final int[] ia) {
        return set(ia, 0);
    }

    public Vec4i set(final int[] ia, final int i) {
        return set(ia[i], ia[i + 1], ia[i + 2], ia[i + 3]);
    }

    public Vec4i set(final long l) {
        return set(l, l, l, l);
    }

    public Vec4i set(final long[] la) {
        return set(la, 0);
    }

    public Vec4i set(final long[] la, final int i) {
        return set(la[i], la[i + 1], la[i + 2], la[i + 3]);
    }

    public Vec4i set(final int i, final Vec3i v) {
        return set(i, v.x, v.y, v.z);
    }

    public Vec4i set(final Vec3i v, final int i) {
        return set(v.x, v.y, v.z, i);
    }

    public Vec4i set(final Vec2i v0, final Vec2i v1) {
        return set(v0.x, v0.y, v1.x, v1.y);
    }

    public Vec4i set(final Vec2i v, final int i0, final int i1) {
        return set(v.x, v.y, i0, i1);
    }

    public Vec4i set(final long x, final long y, final long z, final long w) {
        return set((int) x, (int) y, (int) z, (int) w);
    }

    public Vec4i set(final int x, final int y, final int z, final int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public int[] toIA_() {
        return toIA_(new int[4]);
    }

    public int[] toIA_(final int[] ia) {
        ia[0] = x;
        ia[1] = y;
        ia[2] = z;
        ia[3] = w;
        return ia;
    }

    public IntBuffer toDib_() {
        return toDib(ByteBuffer.allocateDirect(SIZE).order(ByteOrder.nativeOrder()).asIntBuffer());
    }

    public IntBuffer toDib(final IntBuffer ib) {
        return toDib(ib, 0);
    }

    public IntBuffer toDib(final IntBuffer ib, final int index) {
        return ib
                .put(index, x)
                .put(index + 1, y)
                .put(index + 2, z)
                .put(index + 3, w);
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
                .putInt(index, x)
                .putInt(index + Integer.BYTES, y)
                .putInt(index + 2 * Integer.BYTES, z)
                .putInt(index + 3 * Integer.BYTES, w);
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
