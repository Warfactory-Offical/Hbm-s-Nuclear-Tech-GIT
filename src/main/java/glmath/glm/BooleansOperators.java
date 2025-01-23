/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

import glmath.glm.vec._2.Vec2;
import glmath.glm.vec._2.b.Vec2b;
import glmath.glm.vec._2.bool.Vec2bool;
import glmath.glm.vec._2.d.Vec2d;
import glmath.glm.vec._2.i.Vec2i;
import glmath.glm.vec._2.l.Vec2l;
import glmath.glm.vec._2.s.Vec2s;
import glmath.glm.vec._2.ub.Vec2ub;
import glmath.glm.vec._2.ui.Vec2ui;
import glmath.glm.vec._2.ul.Vec2ul;
import glmath.glm.vec._2.us.Vec2us;
import glmath.glm.vec._3.Vec3;
import glmath.glm.vec._3.b.Vec3b;
import glmath.glm.vec._3.bool.Vec3bool;
import glmath.glm.vec._3.d.Vec3d;
import glmath.glm.vec._3.i.Vec3i;
import glmath.glm.vec._3.l.Vec3l;
import glmath.glm.vec._3.s.Vec3s;
import glmath.glm.vec._3.ub.Vec3ub;
import glmath.glm.vec._3.ui.Vec3ui;
import glmath.glm.vec._3.ul.Vec3ul;
import glmath.glm.vec._3.us.Vec3us;
import glmath.glm.vec._4.Vec4;
import glmath.glm.vec._4.b.Vec4b;
import glmath.glm.vec._4.bool.Vec4bool;
import glmath.glm.vec._4.d.Vec4d;
import glmath.glm.vec._4.i.Vec4i;
import glmath.glm.vec._4.l.Vec4l;
import glmath.glm.vec._4.s.Vec4s;
import glmath.glm.vec._4.ub.Vec4ub;
import glmath.glm.vec._4.ui.Vec4ui;
import glmath.glm.vec._4.ul.Vec4ul;
import glmath.glm.vec._4.us.Vec4us;

/**
 *
 * @author GBarbieri
 */
abstract class BooleansOperators {

    public static boolean equals(final Vec2 a, final Vec2 b) {
        return a.x == b.x & a.y == b.y;
    }

    public static boolean equals(final Vec2b a, final Vec2b b) {
        return a.x == b.x & a.y == b.y;
    }

    public static boolean equals(final Vec2bool a, final Vec2bool b) {
        return a.x == b.x & a.y == b.y;
    }

    public static boolean equals(final Vec2d a, final Vec2d b) {
        return a.x == b.x & a.y == b.y;
    }

    public static boolean equals(final Vec2i a, final Vec2i b) {
        return a.x == b.x & a.y == b.y;
    }

    public static boolean equals(final Vec2l a, final Vec2l b) {
        return a.x == b.x & a.y == b.y;
    }

    public static boolean equals(final Vec2s a, final Vec2s b) {
        return a.x == b.x & a.y == b.y;
    }

    public static boolean equals(final Vec2ub a, final Vec2ub b) {
        return a.x.equals(b.x) & a.y.equals(b.y);
    }

    public static boolean equals(final Vec2ui a, final Vec2ui b) {
        return a.x.equals(b.x) & a.y.equals(b.y);
    }

    public static boolean equals(final Vec2ul a, final Vec2ul b) {
        return a.x.equals(b.x) & a.y.equals(b.y);
    }

    public static boolean equals(final Vec2us a, final Vec2us b) {
        return a.x.equals(b.x) & a.y.equals(b.y);
    }

    public static boolean equals(final Vec3 a, final Vec3 b) {
        return a.x == b.x & a.y == b.y & a.z == b.z;
    }

    public static boolean equals(final Vec3b a, final Vec3b b) {
        return a.x == b.x & a.y == b.y & a.z == b.z;
    }

    public static boolean equals(final Vec3bool a, final Vec3bool b) {
        return a.x == b.x & a.y == b.y & a.z == b.z;
    }

    public static boolean equals(final Vec3d a, final Vec3d b) {
        return a.x == b.x & a.y == b.y & a.z == b.z;
    }

    public static boolean equals(final Vec3i a, final Vec3i b) {
        return a.x == b.x & a.y == b.y & a.z == b.z;
    }

    public static boolean equals(final Vec3l a, final Vec3l b) {
        return a.x == b.x & a.y == b.y & a.z == b.z;
    }

    public static boolean equals(final Vec3s a, final Vec3s b) {
        return a.x == b.x & a.y == b.y & a.z == b.z;
    }

    public static boolean equals(final Vec3ub a, final Vec3ub b) {
        return a.x.equals(b.x) & a.y.equals(b.y) & a.z.equals(b.z);
    }

    public static boolean equals(final Vec3ui a, final Vec3ui b) {
        return a.x.equals(b.x) & a.y.equals(b.y) & a.z.equals(b.z);
    }

    public static boolean equals(final Vec3ul a, final Vec3ul b) {
        return a.x.equals(b.x) & a.y.equals(b.y) & a.z.equals(b.z);
    }

    public static boolean equals(final Vec3us a, final Vec3us b) {
        return a.x.equals(b.x) & a.y.equals(b.y) & a.z.equals(b.z);
    }

    public static boolean equals(final Vec4 a, final Vec4 b) {
        return a.x == b.x & a.y == b.y & a.z == b.z & a.w == b.w;
    }

    public static boolean equals(final Vec4b a, final Vec4b b) {
        return a.x == b.x & a.y == b.y & a.z == b.z & a.w == b.w;
    }

    public static boolean equals(final Vec4bool a, final Vec4bool b) {
        return a.x == b.x & a.y == b.y & a.z == b.z & a.w == b.w;
    }

    public static boolean equals(final Vec4d a, final Vec4d b) {
        return a.x == b.x & a.y == b.y & a.z == b.z & a.w == b.w;
    }

    public static boolean equals(final Vec4i a, final Vec4i b) {
        return a.x == b.x & a.y == b.y & a.z == b.z & a.w == b.w;
    }

    public static boolean equals(final Vec4l a, final Vec4l b) {
        return a.x == b.x & a.y == b.y & a.z == b.z & a.w == b.w;
    }

    public static boolean equals(final Vec4s a, final Vec4s b) {
        return a.x == b.x & a.y == b.y & a.z == b.z & a.w == b.w;
    }

    public static boolean equals(final Vec4ub a, final Vec4ub b) {
        return a.x.equals(b.x) & a.y.equals(b.y) & a.z.equals(b.z) & a.w.equals(b.w);
    }

    public static boolean equals(final Vec4ui a, final Vec4ui b) {
        return a.x.equals(b.x) & a.y.equals(b.y) & a.z.equals(b.z) & a.w.equals(b.w);
    }

    public static boolean equals(final Vec4ul a, final Vec4ul b) {
        return a.x.equals(b.x) & a.y.equals(b.y) & a.z.equals(b.z) & a.w.equals(b.w);
    }

    public static boolean equals(final Vec4us a, final Vec4us b) {
        return a.x.equals(b.x) & a.y.equals(b.y) & a.z.equals(b.z) & a.w.equals(b.w);
    }

    public static boolean notEquals(final Vec2 a, final Vec2 b) {
        return a.x != b.x | a.y != b.y;
    }

    public static boolean notEquals(final Vec2b a, final Vec2b b) {
        return a.x != b.x | a.y != b.y;
    }

    public static boolean notEquals(final Vec2bool a, final Vec2bool b) {
        return a.x != b.x | a.y != b.y;
    }

    public static boolean notEquals(final Vec2d a, final Vec2d b) {
        return a.x != b.x | a.y != b.y;
    }

    public static boolean notEquals(final Vec2i a, final Vec2i b) {
        return a.x != b.x | a.y != b.y;
    }

    public static boolean notEquals(final Vec2l a, final Vec2l b) {
        return a.x != b.x | a.y != b.y;
    }

    public static boolean notEquals(final Vec2s a, final Vec2s b) {
        return a.x != b.x | a.y != b.y;
    }

    public static boolean notEquals(final Vec2ub a, final Vec2ub b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y);
    }

    public static boolean notEquals(final Vec2ui a, final Vec2ui b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y);
    }

    public static boolean notEquals(final Vec2ul a, final Vec2ul b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y);
    }

    public static boolean notEquals(final Vec2us a, final Vec2us b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y);
    }

    public static boolean notEquals(final Vec3 a, final Vec3 b) {
        return a.x != b.x | a.y != b.y | a.z != b.z;
    }

    public static boolean notEquals(final Vec3b a, final Vec3b b) {
        return a.x != b.x | a.y != b.y | a.z != b.z;
    }

    public static boolean notEquals(final Vec3bool a, final Vec3bool b) {
        return a.x != b.x | a.y != b.y | a.z != b.z;
    }

    public static boolean notEquals(final Vec3d a, final Vec3d b) {
        return a.x != b.x | a.y != b.y | a.z != b.z;
    }

    public static boolean notEquals(final Vec3i a, final Vec3i b) {
        return a.x != b.x | a.y != b.y | a.z != b.z;
    }

    public static boolean notEquals(final Vec3l a, final Vec3l b) {
        return a.x != b.x | a.y != b.y | a.z != b.z;
    }

    public static boolean notEquals(final Vec3s a, final Vec3s b) {
        return a.x != b.x | a.y != b.y | a.z != b.z;
    }

    public static boolean notEquals(final Vec3ub a, final Vec3ub b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y) | !a.z.equals(b.z);
    }

    public static boolean notEquals(final Vec3ui a, final Vec3ui b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y) | !a.z.equals(b.z);
    }

    public static boolean notEquals(final Vec3ul a, final Vec3ul b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y) | !a.z.equals(b.z);
    }

    public static boolean notEquals(final Vec3us a, final Vec3us b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y) | !a.z.equals(b.z);
    }

    public static boolean notEquals(final Vec4 a, final Vec4 b) {
        return a.x != b.x | a.y != b.y | a.z != b.z | a.w != b.w;
    }

    public static boolean notEquals(final Vec4b a, final Vec4b b) {
        return a.x != b.x | a.y != b.y | a.z != b.z | a.w != b.w;
    }

    public static boolean notEquals(final Vec4bool a, final Vec4bool b) {
        return a.x != b.x | a.y != b.y | a.z != b.z | a.w != b.w;
    }

    public static boolean notEquals(final Vec4d a, final Vec4d b) {
        return a.x != b.x | a.y != b.y | a.z != b.z | a.w != b.w;
    }

    public static boolean notEquals(final Vec4i a, final Vec4i b) {
        return a.x != b.x | a.y != b.y | a.z != b.z | a.w != b.w;
    }

    public static boolean notEquals(final Vec4l a, final Vec4l b) {
        return a.x != b.x | a.y != b.y | a.z != b.z | a.w != b.w;
    }

    public static boolean notEquals(final Vec4s a, final Vec4s b) {
        return a.x != b.x | a.y != b.y | a.z != b.z | a.w != b.w;
    }

    public static boolean notEquals(final Vec4ub a, final Vec4ub b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y) | !a.z.equals(b.z) | !a.w.equals(b.w);
    }

    public static boolean notEquals(final Vec4ui a, final Vec4ui b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y) | !a.z.equals(b.z) | !a.w.equals(b.w);
    }

    public static boolean notEquals(final Vec4ul a, final Vec4ul b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y) | !a.z.equals(b.z) | !a.w.equals(b.w);
    }

    public static boolean notEquals(final Vec4us a, final Vec4us b) {
        return !a.x.equals(b.x) | !a.y.equals(b.y) | !a.z.equals(b.z) | !a.w.equals(b.w);
    }

    public static Vec2bool and(final Vec2bool a, final Vec2bool b) {
        return and(new Vec2bool(), a, b);
    }

    public static Vec2bool and(final Vec2bool res, final Vec2bool a, final Vec2bool b) {
        res.x = a.x & b.x;
        res.y = a.y & b.y;
        return res;
    }

    public static Vec3bool and(final Vec3bool a, final Vec3bool b) {
        return and(new Vec3bool(), a, b);
    }

    public static Vec3bool and(final Vec3bool res, final Vec3bool a, final Vec3bool b) {
        res.x = a.x & b.x;
        res.y = a.y & b.y;
        res.z = a.z & b.z;
        return res;
    }

    public static Vec4bool and(final Vec4bool a, final Vec4bool b) {
        return and(new Vec4bool(), a, b);
    }

    public static Vec4bool and(final Vec4bool res, final Vec4bool a, final Vec4bool b) {
        res.x = a.x & b.x;
        res.y = a.y & b.y;
        res.z = a.z & b.z;
        res.w = a.w & b.w;
        return res;
    }

    public static Vec2bool or(final Vec2bool a, final Vec2bool b) {
        return or(new Vec2bool(), a, b);
    }

    public static Vec2bool or(final Vec2bool res, final Vec2bool a, final Vec2bool b) {
        res.x = a.x | b.x;
        res.y = a.y | b.y;
        return res;
    }

    public static Vec3bool or(final Vec3bool a, final Vec3bool b) {
        return or(new Vec3bool(), a, b);
    }

    public static Vec3bool or(final Vec3bool res, final Vec3bool a, final Vec3bool b) {
        res.x = a.x | b.x;
        res.y = a.y | b.y;
        res.z = a.z | b.z;
        return res;
    }

    public static Vec4bool or(final Vec4bool a, final Vec4bool b) {
        return or(new Vec4bool(), a, b);
    }

    public static Vec4bool or(final Vec4bool res, final Vec4bool a, final Vec4bool b) {
        res.x = a.x | b.x;
        res.y = a.y | b.y;
        res.z = a.z | b.z;
        res.w = a.w | b.w;
        return res;
    }
}
