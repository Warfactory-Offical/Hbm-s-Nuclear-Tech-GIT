/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.ui;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import glmath.joou.UInt;

/**
 *
 * @author GBarbieri
 */
public class Vec4ui extends FuncRelational {

    public Vec4ui() {
        this(0);
    }

    public Vec4ui(final Vec4ui v) {
        this(v.x, v.y, v.z, v.w);
    }

    public Vec4ui(final int i) {
        this(i, i, i, i);
    }

    public Vec4ui(final int[] ia) {
        this(ia, 0);
    }

    public Vec4ui(final int[] ia, final int i) {
        this(ia[i], ia[i + 1], ia[i + 2], ia[i + 3]);
    }

    public Vec4ui(final long l) {
        this((int) l);
    }

    public Vec4ui(final long[] la) {
        this(la, 0);
    }

    public Vec4ui(final long[] la, final int offset) {
        this(la[offset], la[offset + 1], la[offset + 2], la[offset + 3]);
    }

    public Vec4ui(final ByteBuffer bb) {
        this(bb, 0);
    }

    public Vec4ui(final ByteBuffer bb, final int offset) {
        this(bb.getInt(offset), bb.getInt(offset + Integer.BYTES),
                bb.getInt(offset + 2 * Integer.BYTES), bb.getInt(offset + 3 * Integer.BYTES));
    }

    public Vec4ui(final IntBuffer ib) {
        this(ib, 0);
    }

    public Vec4ui(final IntBuffer ib, final int offset) {
        this(ib.get(offset), ib.get(offset + 1), ib.get(offset + 2), ib.get(offset + 3));
    }

//    public Vec4ui(int f, Vec3 v) {
//        this(f, v.x, v.y, v.z);
//    }
//
//    public Vec4ui(Vec3 v, float f) {
//        this(v.x, v.y, v.z, f);
//    }
//
//    public Vec4ui(Vec2 v0, Vec2 v1) {
//        this(v0.x, v0.y, v1.x, v1.y);
//    }
//
//    public Vec4ui(Vec2 v, float f0, float f1) {
//        this(v.x, v.y, f0, f1);
//    }
    public Vec4ui(final long x, final long y, final long z, final long w) {
        this((int) x, (int) y, (int) z, (int) w);
    }

    public Vec4ui(final UInt x, final UInt y, final UInt z, final UInt w) {
        this.x.value = x.value;
        this.y.value = y.value;
        this.z.value = z.value;
        this.w.value = w.value;
    }

    public Vec4ui(final int x, final int y, final int z, final int w) {
        this.x.value = x;
        this.y.value = y;
        this.z.value = z;
        this.w.value = w;
    }

    public Vec4ui set() {
        return set(0);
    }

    public Vec4ui set(final Vec4ui v) {
        return set(v.x, v.y, v.z, v.w);
    }

    public Vec4ui set(final int i) {
        return set(i, i, i, i);
    }

    public Vec4ui set(final int[] ia) {
        return set(ia, 0);
    }

    public Vec4ui set(final int[] ia, final int i) {
        return set(ia[i], ia[i + 1], ia[i + 2], ia[i + 3]);
    }

    public Vec4ui set(final long l) {
        return set(l, l, l, l);
    }

    public Vec4ui set(final long[] la) {
        return set(la, 0);
    }

    public Vec4ui set(final long[] la, final int i) {
        return set(la[i], la[i + 1], la[i + 2], la[i + 3]);
    }

    public Vec4ui set(final ByteBuffer bb) {
        return set(bb, 0);
    }

    public Vec4ui set(final ByteBuffer bb, final int offset) {
        return set(bb.getInt(offset), bb.getInt(offset + Integer.BYTES),
                bb.getInt(offset + 2 * Integer.BYTES), bb.getInt(offset + 3 * Integer.BYTES));
    }

    public Vec4ui set(final IntBuffer ib) {
        return set(ib, 0);
    }

    public Vec4ui set(final IntBuffer ib, final int offset) {
        return set(ib.get(offset), ib.get(offset + 1), ib.get(offset + 2), ib.get(offset + 3));
    }

//    public Vec4ui set(int i, Vec3 v) {
//        return set(i, v.x, v.y, v.z);
//    }
//
//    public Vec4ui set(Vec3 v, float f) {
//        return set(v.x, v.y, v.z, f);
//    }
//
//    public Vec4ui set(Vec2 v0, Vec2 v1) {
//        return set(v0.x, v0.y, v1.x, v1.y);
//    }
//
//    public Vec4ui set(Vec2 v, float f0, float f1) {
//        return set(v.x, v.y, f0, f1);
//    }
    public Vec4ui set(final long x, final long y, final long z, final long w) {
        return set((int) x, (int) y, (int) z, (int) w);
    }

    public Vec4ui set(final int x, final int y, final int z, final int w) {
        this.x.value = x;
        this.y.value = y;
        this.z.value = z;
        this.w.value = w;
        return this;
    }

    public Vec4ui set(final UInt x, final UInt y, final UInt z, final UInt w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

//    public Vec3 toVec3_() {
//        return toVec3(new Vec3());
//    }
//
//    public Vec3 toVec3(Vec3 res) {
//        return res.set(x, y, z);
//    }
    public int[] toIa_() {
        return toIa(new int[4]);
    }

    public int[] toIa(final int[] ia) {
        ia[0] = x.value;
        ia[1] = y.value;
        ia[2] = z.value;
        ia[3] = w.value;
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
                .put(index, x.value)
                .put(index + 1, y.value)
                .put(index + 2, z.value)
                .put(index + 3, w.value);
    }

    public ByteBuffer toDbb_() {
        return toDbb(ByteBuffer.allocateDirect(SIZE).order(ByteOrder.nativeOrder()));
    }

    public ByteBuffer toDbb(final ByteBuffer bb) {
        return toDbb(bb, 0);
    }

    public ByteBuffer toDbb(final ByteBuffer bb, final int index) {
        return bb
                .putInt(index, x.value)
                .putInt(index + Float.BYTES, y.value)
                .putInt(index + 2 * Float.BYTES, z.value)
                .putInt(index + 3 * Float.BYTES, w.value);
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
