/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.i;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import glmath.glm.Glm;
import glmath.glm.vec._4.i.Vec4i;

/**
 *
 * @author GBarbieri
 */
public class Vec3i extends FuncRelational {

    public Vec3i() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3i(final Vec3i v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public Vec3i(final Vec4i v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public Vec3i(final int i) {
        x = i;
        y = i;
        z = i;
    }

    public Vec3i(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3i(final int[] fa) {
        this(fa, 0);
    }

    public Vec3i(final int[] fa, final int i) {
        x = fa[i];
        y = fa[i + 1];
        z = fa[i + 2];
    }

    public Vec3i(final long x, final long y, final long z) {
        this.x = (int) x;
        this.y = (int) y;
        this.z = (int) z;
    }

    public Vec3i set(final int i) {
        x = i;
        y = i;
        z = i;
        return this;
    }

    public Vec3i set(final long x, final long y, final long z) {
        return set((int) x, (int) y, (int) z);
    }

    public Vec3i set(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3i set(final int[] fa) {
        x = fa[0];
        y = fa[1];
        z = fa[2];
        return this;
    }

    public static Vec3i linearRand_(final Vec3i min, final Vec3i max) {
        return linearRand(min, max, new Vec3i());
    }

    public static Vec3i linearRand(final Vec3i min, final Vec3i max, final Vec3i res) {
        res.x = Glm.linearRand(min.x, max.x);
        res.y = Glm.linearRand(min.y, max.y);
        res.z = Glm.linearRand(min.z, max.z);
        return res;
    }

    public Vec3i negate() {
        return negate(this);
    }

    public Vec3i negate_() {
        return negate(new Vec3i());
    }

    public Vec3i negate(final Vec3i res) {
        res.x = -x;
        res.y = -y;
        res.z = -z;
        return res;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public int[] toIA_() {
        return toIA(new int[3]);
    }

    public int[] toIA(final int[] ia) {
        ia[0] = x;
        ia[1] = y;
        ia[2] = z;
        return ia;
    }

    public IntBuffer toDib_() {
        return Vec3i.this.toDib(ByteBuffer.allocateDirect(SIZE).order(ByteOrder.nativeOrder()).asIntBuffer());
    }

    public IntBuffer toDib(final IntBuffer ib) {
        return toDib(ib, 0);
    }

    public IntBuffer toDib(final IntBuffer ib, final int index) {
        return ib
                .put(index, x)
                .put(index + 1, y)
                .put(index + 2, z);
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
                .putInt(index + 2 * Integer.BYTES, z);
    }

    public void print() {
        print("", System.out);
    }

    public void print(final String title) {
        print(title, System.out);
    }

    public void print(final String title, final PrintStream printStream) {
        printStream.println( title + "\n(" + x + ", " + y + ", " + z + ")");
    }
}
