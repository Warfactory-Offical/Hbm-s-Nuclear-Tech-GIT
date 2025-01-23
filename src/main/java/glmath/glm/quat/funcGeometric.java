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
abstract class funcGeometric extends funcCommon {

    public Quat angleAxis(final float angle, final Vec3 v) {
        return Quat.angleAxis(angle, v, (Quat) this);
    }

    public static Quat angleAxis_(final float degAngle, final Vec3 v) {
        return Quat.angleAxis(degAngle, v, new Quat());
    }

    public static Quat angleAxis(final float degAngle, final Vec3 v, final Quat res) {

        final float a = degAngle;
        final float s = (float) Math.sin(Math.toRadians(a) * 0.5f);

        res.w = (float) Math.cos(Math.toRadians(a) * 0.5f);
        res.x = v.x * s;
        res.y = v.y * s;
        res.z = v.z * s;

        return res;
    }

    public float dot(final Quat q) {
        return w * q.w + x * q.x + y * q.y + z * q.z;
    }

    public static float dot(final Quat q1, final Quat q2) {
        return q1.x * q2.x + q1.y * q2.y + q1.z * q2.z + q1.w * q2.w;
    }

    public float length() {
        return length((Quat) this);
    }

    public static float length(final Quat q) {
        return (float) Math.sqrt(dot(q, q));
    }

    public Quat normalize() {
        return Quat.normalize((Quat) this, (Quat) this);
    }

    public Quat normalize_() {
        return Quat.normalize((Quat) this, new Quat());
    }

    public Quat normalize(final Quat res) {
        return Quat.normalize((Quat) this, res);
    }

    public static Quat normalize(final Quat q, final Quat res) {
        final float len = length(q);
        if (len <= 0) { // Problem
            return new Quat(1, 0, 0, 0);
        }
        final float oneOverLen = 1 / len;
        return res.set(q.w * oneOverLen, q.x * oneOverLen, q.y * oneOverLen, q.z * oneOverLen);
    }
}
