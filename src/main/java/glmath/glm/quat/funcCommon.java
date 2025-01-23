/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.quat;

import glmath.glm.vec._3.Vec3;

/**
 *
 * @author elect
 */
abstract class funcCommon {

    public static final int SIZE = 4 * Float.BYTES;

    public float w, x, y, z;

    public Quat add(final Quat y) {
        return add(y, (Quat) this);
    }

    public Quat add_(final Quat y) {
        return add(y, new Quat());
    }

    public Quat add(final Quat y, final Quat res) {
        return add((Quat) this, y, res);
    }

    public static Quat add(final Quat x, final Quat y, final Quat res) {
        res.w = x.w + y.w;
        res.x = x.x + y.x;
        res.y = x.y + y.y;
        res.z = x.z + y.z;
        return res;
    }

    public Quat mul_(final Quat q) {
        return mul(q, new Quat());
    }

    public Quat mul(final Quat q) {
        return mul(q, (Quat) this);
    }

    public Quat mul(final Quat q, final Quat res) {
        return Quat.mul((Quat) this, q, res);
    }

    public static Quat mul(final Quat x, final Quat y, final Quat res) {
        return res.set(
                x.w * y.w - x.x * y.x - x.y * y.y - x.z * y.z,
                x.w * y.x + x.x * y.w + x.y * y.z - x.z * y.y,
                x.w * y.y + x.y * y.w + x.z * y.x - x.x * y.z,
                x.w * y.z + x.z * y.w + x.x * y.y - x.y * y.x);
    }

    public Vec3 mul(final Vec3 v) {
        return Quat.mul((Quat) this, v, v);
    }

    public Vec3 mul_(final Vec3 v) {
        return Quat.mul((Quat) this, v, new Vec3());
    }

    public Vec3 mul(final Vec3 v, final Vec3 res) {
        return Quat.mul((Quat) this, v, res);
    }

    public static Vec3 mul(final Quat q, final Vec3 v, final Vec3 res) {
        final float n0 = q.x + q.x;
        final float n1 = q.y + q.y;
        final float n2 = q.z + q.z;
        final float n3 = q.x * n0;
        final float n4 = q.y * n1;
        final float n5 = q.z * n2;
        final float n6 = q.x * n1;
        final float n7 = q.x * n2;
        final float n8 = q.y * n2;
        final float n9 = q.w * n0;
        final float n10 = q.w * n1;
        final float n11 = q.w * n2;
        res.set((1.0f - (n4 + n5)) * v.x + (n6 - n11) * v.y + (n7 + n10) * v.z,
                (n6 + n11) * v.x + (1.0f - (n3 + n5)) * v.y + (n8 - n9) * v.z,
                (n7 - n10) * v.x + (n8 + n9) * v.y + (1.0f - (n3 + n4)) * v.z);
        return res;
    }

    public Quat mul(final double d) {
        return mul((float) d, (Quat) this);
    }

    public Quat mul_(final double d) {
        return mul((float) d, new Quat());
    }

    public Quat mul(final float f) {
        return mul(f, (Quat) this);
    }

    public Quat mul_(final float f) {
        return mul(f, new Quat());
    }

    public Quat mul(final float f, final Quat res) {
        return Quat.mul((Quat) this, f, res);
    }

    public static Quat mul(final Quat x, final float y, final Quat res) {
        return res.set(
                x.w * y,
                x.x * y,
                x.y * y,
                x.z * y);
    }

    public Quat sub(final Quat y) {
        return sub(y, (Quat) this);
    }

    public Quat sub_(final Quat y) {
        return sub(y, new Quat());
    }

    public Quat sub(final Quat y, final Quat res) {
        return sub((Quat) this, y, res);
    }

    public static Quat sub(final Quat x, final Quat y, final Quat res) {
        res.w = x.w - y.w;
        res.x = x.x - y.x;
        res.y = x.y - y.y;
        res.z = x.z - y.z;
        return res;
    }
}
