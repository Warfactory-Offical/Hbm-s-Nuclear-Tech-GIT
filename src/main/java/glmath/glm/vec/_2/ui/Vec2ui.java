/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.ui;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import glmath.glm.vec._2.i.Vec2i;
import glmath.glm.vec._3.Vec3;
import glmath.glm.vec._4.Vec4;
import glmath.joou.UInt;

/**
 *
 * @author GBarbieri
 */
public class Vec2ui extends FuncRelational {

    public Vec2ui() {
        x.value = 0;
        y.value = 0;
    }

    public Vec2ui(final int i) {
        x.value = i;
        y.value = i;
    }

    public Vec2ui(final int x, final int y) {
        this.x.value = x;
        this.y.value = y;
    }

    public Vec2ui(final Vec2ui v) {
        x = v.x;
        y = v.y;
    }

    public Vec2ui(final Vec2i v) {
        x.value = v.x;
        y.value = v.y;
    }

    public Vec2ui(final Vec3 v) {
        x.value =  (int) v.x;
        y.value = (int) v.y;
    }

    public Vec2ui(final Vec4 v) {
        x.value = (int) v.x;
        y.value = (int) v.y;
    }

    public Vec2ui set(final int i) {
        x.value = i;
        y.value = i;
        return this;
    }

    public Vec2ui set(final UInt x, final UInt y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2ui set(final float x, final float y) {
        this.x.value = (int) x;
        this.y.value = (int) y;
        return this;
    }

    public Vec2ui set(final int[] ia) {
        x.value = ia[0];
        y.value = ia[1];
        return this;
    }

    public float[] toIa_() {
        return toIa(new float[2]);
    }

    public float[] toIa(final float[] fa) {
        fa[0] = x.value;
        fa[1] = y.value;
        return fa;
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
                .put(index + 1, y.value);
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
                .putInt(index + Integer.BYTES, y.value);
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
