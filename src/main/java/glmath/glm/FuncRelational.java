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
import glmath.joou.UByte;
import glmath.joou.UInt;
import glmath.joou.ULong;
import glmath.joou.UShort;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends BasicOperatorsScalarFirst {

    // any ----------------------------------------------------------------------------------------------------------
    public static boolean any(final Vec2 v) {
        return v.x != 0 | v.y != 0;
    }

    public static boolean any(final Vec2b v) {
        return v.x != 0 | v.y != 0;
    }

    public static boolean any(final Vec2bool v) {
        return v.x | v.y;
    }

    public static boolean any(final Vec2d v) {
        return v.x != 0 | v.y != 0;
    }

    public static boolean any(final Vec2i v) {
        return v.x != 0 | v.y != 0;
    }

    public static boolean any(final Vec2l v) {
        return v.x != 0 | v.y != 0;
    }

    public static boolean any(final Vec2s v) {
        return v.x != 0 | v.y != 0;
    }

    public static boolean any(final Vec2ub v) {
        return v.x.value != 0 | v.y.value != 0;
    }

    public static boolean any(final Vec2ui v) {
        return v.x.value != 0 | v.y.value != 0;
    }

    public static boolean any(final Vec2ul v) {
        return v.x.value != 0 | v.y.value != 0;
    }

    public static boolean any(final Vec2us v) {
        return v.x.value != 0 | v.y.value != 0;
    }

    public static boolean any(final Vec3 v) {
        return v.x != 0 | v.y != 0 | v.z != 0;
    }

    public static boolean any(final Vec3b v) {
        return v.x != 0 | v.y != 0 | v.z != 0;
    }

    public static boolean any(final Vec3bool v) {
        return v.x | v.y | v.z;
    }

    public static boolean any(final Vec3d v) {
        return v.x != 0 | v.y != 0 | v.z != 0;
    }

    public static boolean any(final Vec3i v) {
        return v.x != 0 | v.y != 0 | v.z != 0;
    }

    public static boolean any(final Vec3l v) {
        return v.x != 0 | v.y != 0 | v.z != 0;
    }

    public static boolean any(final Vec3s v) {
        return v.x != 0 | v.y != 0 | v.z != 0;
    }

    public static boolean any(final Vec3ub v) {
        return v.x.value != 0 | v.y.value != 0 | v.z.value != 0;
    }

    public static boolean any(final Vec3ui v) {
        return v.x.value != 0 | v.y.value != 0 | v.z.value != 0;
    }

    public static boolean any(final Vec3ul v) {
        return v.x.value != 0 | v.y.value != 0 | v.z.value != 0;
    }

    public static boolean any(final Vec3us v) {
        return v.x.value != 0 | v.y.value != 0 | v.z.value != 0;
    }

    public static boolean any(final Vec4 v) {
        return v.x != 0 | v.y != 0 | v.z != 0;
    }

    public static boolean any(final Vec4b v) {
        return v.x != 0 | v.y != 0 | v.z != 0;
    }

    public static boolean any(final Vec4bool v) {
        return v.x | v.y | v.z | v.w;
    }

    public static boolean any(final Vec4d v) {
        return v.x != 0 | v.y != 0 | v.z != 0 | v.w != 0;
    }

    public static boolean any(final Vec4i v) {
        return v.x != 0 | v.y != 0 | v.z != 0 | v.w != 0;
    }

    public static boolean any(final Vec4l v) {
        return v.x != 0 | v.y != 0 | v.z != 0 | v.w != 0;
    }

    public static boolean any(final Vec4s v) {
        return v.x != 0 | v.y != 0 | v.z != 0 | v.w != 0;
    }

    public static boolean any(final Vec4ub v) {
        return v.x.value != 0 | v.y.value != 0 | v.z.value != 0 | v.w.value != 0;
    }

    public static boolean any(final Vec4ui v) {
        return v.x.value != 0 | v.y.value != 0 | v.z.value != 0 | v.w.value != 0;
    }

    public static boolean any(final Vec4ul v) {
        return v.x.value != 0 | v.y.value != 0 | v.z.value != 0 | v.w.value != 0;
    }

    public static boolean any(final Vec4us v) {
        return v.x.value != 0 | v.y.value != 0 | v.z.value != 0 | v.w.value != 0;
    }

    // all ----------------------------------------------------------------------------------------------------------
    public static boolean all(final Vec2 v) {
        return v.x != 0 & v.y != 0;
    }

    public static boolean all(final Vec2b v) {
        return v.x != 0 & v.y != 0;
    }

    public static boolean all(final Vec2bool v) {
        return v.x & v.y;
    }

    public static boolean all(final Vec2d v) {
        return v.x != 0 & v.y != 0;
    }

    public static boolean all(final Vec2i v) {
        return v.x != 0 & v.y != 0;
    }

    public static boolean all(final Vec2l v) {
        return v.x != 0 & v.y != 0;
    }

    public static boolean all(final Vec2s v) {
        return v.x != 0 & v.y != 0;
    }

    public static boolean all(final Vec2ub v) {
        return v.x.value != 0 & v.y.value != 0;
    }

    public static boolean all(final Vec2ui v) {
        return v.x.value != 0 & v.y.value != 0;
    }

    public static boolean all(final Vec2ul v) {
        return v.x.value != 0 & v.y.value != 0;
    }

    public static boolean all(final Vec2us v) {
        return v.x.value != 0 & v.y.value != 0;
    }

    public static boolean all(final Vec3 v) {
        return v.x != 0 & v.y != 0 & v.z != 0;
    }

    public static boolean all(final Vec3b v) {
        return v.x != 0 & v.y != 0 & v.z != 0;
    }

    public static boolean all(final Vec3bool v) {
        return v.x & v.y & v.z;
    }

    public static boolean all(final Vec3d v) {
        return v.x != 0 & v.y != 0 & v.z != 0;
    }

    public static boolean all(final Vec3i v) {
        return v.x != 0 & v.y != 0 & v.z != 0;
    }

    public static boolean all(final Vec3l v) {
        return v.x != 0 & v.y != 0 & v.z != 0;
    }

    public static boolean all(final Vec3s v) {
        return v.x != 0 & v.y != 0 & v.z != 0;
    }

    public static boolean all(final Vec3ub v) {
        return v.x.value != 0 & v.y.value != 0 & v.z.value != 0;
    }

    public static boolean all(final Vec3ui v) {
        return v.x.value != 0 & v.y.value != 0 & v.z.value != 0;
    }

    public static boolean all(final Vec3ul v) {
        return v.x.value != 0 & v.y.value != 0
                & v.z.value != 0;
    }

    public static boolean all(final Vec3us v) {
        return v.x.value != 0 & v.y.value != 0 & v.z.value != 0;
    }

    public static boolean all(final Vec4 v) {
        return v.x != 0 & v.y != 0 & v.z != 0;
    }

    public static boolean all(final Vec4b v) {
        return v.x != 0 & v.y != 0 & v.z != 0;
    }

    public static boolean all(final Vec4bool v) {
        return v.x & v.y & v.z & v.w;
    }

    public static boolean all(final Vec4d v) {
        return v.x != 0 & v.y != 0 & v.z != 0 & v.w != 0;
    }

    public static boolean all(final Vec4i v) {
        return v.x != 0 & v.y != 0 & v.z != 0 & v.w != 0;
    }

    public static boolean all(final Vec4l v) {
        return v.x != 0 & v.y != 0 & v.z != 0 & v.w != 0;
    }

    public static boolean all(final Vec4s v) {
        return v.x != 0 & v.y != 0 & v.z != 0 & v.w != 0;
    }

    public static boolean all(final Vec4ub v) {
        return v.x.value != 0 & v.y.value != 0 & v.z.value != 0 & v.w.value != 0;
    }

    public static boolean all(final Vec4ui v) {
        return v.x.value != 0 & v.y.value != 0 & v.z.value != 0 & v.w.value != 0;
    }

    public static boolean all(final Vec4ul v) {
        return v.x.value != 0 & v.y.value != 0
                & v.z.value != 0 & v.w.value != 0;
    }

    public static boolean all(final Vec4us v) {
        return v.x.value != 0 & v.y.value != 0 & v.z.value != 0 & v.w.value != 0;
    }

    // not ----------------------------------------------------------------------------------------------------------
    public static Vec2 not_(final Vec2 v) {
        return not(v, new Vec2());
    }

    public static Vec2 not(final Vec2 v, final Vec2 res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        return res;
    }

    public static Vec2b not_(final Vec2b v) {
        return not(v, new Vec2b());
    }

    public static Vec2b not(final Vec2b v, final Vec2b res) {
        res.x = (byte) (v.x == 0 ? 1 : 0);
        res.y = (byte) (v.y == 0 ? 1 : 0);
        return res;
    }

    public static Vec2bool not_(final Vec2bool v) {
        return not(v, new Vec2bool());
    }

    public static Vec2bool not(final Vec2bool v, final Vec2bool res) {
        res.x = !v.x;
        res.y = !v.y;
        return res;
    }

    public static Vec2d not_(final Vec2d v) {
        return not(v, new Vec2d());
    }

    public static Vec2d not(final Vec2d v, final Vec2d res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        return res;
    }

    public static Vec2i not_(final Vec2i v) {
        return not(v, new Vec2i());
    }

    public static Vec2i not(final Vec2i v, final Vec2i res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        return res;
    }

    public static Vec2l not_(final Vec2l v) {
        return not(v, new Vec2l());
    }

    public static Vec2l not(final Vec2l v, final Vec2l res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        return res;
    }

    public static Vec2s not_(final Vec2s v) {
        return not(v, new Vec2s());
    }

    public static Vec2s not(final Vec2s v, final Vec2s res) {
        res.x = (short) (v.x == 0 ? 1 : 0);
        res.y = (short) (v.y == 0 ? 1 : 0);
        return res;
    }

    public static Vec2ub not_(final Vec2ub v) {
        return not(v, new Vec2ub());
    }

    public static Vec2ub not(final Vec2ub v, final Vec2ub res) {
        res.x.value = (byte) (v.x.value == 0 ? 1 : 0);
        res.y.value = (byte) (v.y.value == 0 ? 1 : 0);
        return res;
    }

    public static Vec2ui not_(final Vec2ui v) {
        return not(v, new Vec2ui());
    }

    public static Vec2ui not(final Vec2ui v, final Vec2ui res) {
        res.x.value = v.x.value == 0 ? 1 : 0;
        res.y.value = v.y.value == 0 ? 1 : 0;
        return res;
    }

    public static Vec2ul not_(final Vec2ul v) {
        return not(v, new Vec2ul());
    }

    public static Vec2ul not(final Vec2ul v, final Vec2ul res) {
        res.x.value = v.x.value == 0 ? 1 : 0;
        res.y.value = v.y.value == 0 ? 1 : 0;
        return res;
    }

    public static Vec2us not_(final Vec2us v) {
        return not(v, new Vec2us());
    }

    public static Vec2us not(final Vec2us v, final Vec2us res) {
        res.x.value = (short) (v.x.value == 0 ? 1 : 0);
        res.y.value = (short) (v.y.value == 0 ? 1 : 0);
        return res;
    }

    public static Vec3 not_(final Vec3 v) {
        return not(v, new Vec3());
    }

    public static Vec3 not(final Vec3 v, final Vec3 res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        res.z = v.z == 0 ? 1 : 0;
        return res;
    }

    public static Vec3b not_(final Vec3b v) {
        return not(v, new Vec3b());
    }

    public static Vec3b not(final Vec3b v, final Vec3b res) {
        res.x = (byte) (v.x == 0 ? 1 : 0);
        res.y = (byte) (v.y == 0 ? 1 : 0);
        res.z = (byte) (v.z == 0 ? 1 : 0);
        return res;
    }

    public static Vec3bool not_(final Vec3bool v) {
        return not(v, new Vec3bool());
    }

    public static Vec3bool not(final Vec3bool v, final Vec3bool res) {
        res.x = !v.x;
        res.y = !v.y;
        res.z = !v.z;
        return res;
    }

    public static Vec3d not_(final Vec3d v) {
        return not(v, new Vec3d());
    }

    public static Vec3d not(final Vec3d v, final Vec3d res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        res.z = v.z == 0 ? 1 : 0;
        return res;
    }

    public static Vec3i not_(final Vec3i v) {
        return not(v, new Vec3i());
    }

    public static Vec3i not(final Vec3i v, final Vec3i res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        res.z = v.z == 0 ? 1 : 0;
        return res;
    }

    public static Vec3l not_(final Vec3l v) {
        return not(v, new Vec3l());
    }

    public static Vec3l not(final Vec3l v, final Vec3l res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        res.z = v.z == 0 ? 1 : 0;
        return res;
    }

    public static Vec3s not_(final Vec3s v) {
        return not(v, new Vec3s());
    }

    public static Vec3s not(final Vec3s v, final Vec3s res) {
        res.x = (short) (v.x == 0 ? 1 : 0);
        res.y = (short) (v.y == 0 ? 1 : 0);
        res.z = (short) (v.z == 0 ? 1 : 0);
        return res;
    }

    public static Vec3ub not_(final Vec3ub v) {
        return not(v, new Vec3ub());
    }

    public static Vec3ub not(final Vec3ub v, final Vec3ub res) {
        res.x.value = (byte) (v.x.value == 0 ? 1 : 0);
        res.y.value = (byte) (v.y.value == 0 ? 1 : 0);
        res.z.value = (byte) (v.z.value == 0 ? 1 : 0);
        return res;
    }

    public static Vec3ui not_(final Vec3ui v) {
        return not(v, new Vec3ui());
    }

    public static Vec3ui not(final Vec3ui v, final Vec3ui res) {
        res.x.value = v.x.value == 0 ? 1 : 0;
        res.y.value = v.y.value == 0 ? 1 : 0;
        res.z.value = v.z.value == 0 ? 1 : 0;
        return res;
    }

    public static Vec3ul not_(final Vec3ul v) {
        return not(v, new Vec3ul());
    }

    public static Vec3ul not(final Vec3ul v, final Vec3ul res) {
        res.x.value = v.x.value == 0 ? 1 : 0;
        res.y.value = v.y.value == 0 ? 1 : 0;
        res.z.value = v.z.value == 0 ? 1 : 0;
        return res;
    }

    public static Vec3us not_(final Vec3us v) {
        return not(v, new Vec3us());
    }

    public static Vec3us not(final Vec3us v, final Vec3us res) {
        res.x.value = (short) (v.x.value == 0 ? 1 : 0);
        res.y.value = (short) (v.y.value == 0 ? 1 : 0);
        res.z.value = (short) (v.z.value == 0 ? 1 : 0);
        return res;
    }

    public static Vec4 not_(final Vec4 v) {
        return not(v, new Vec4());
    }

    public static Vec4 not(final Vec4 v, final Vec4 res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        res.z = v.z == 0 ? 1 : 0;
        res.w = v.w == 0 ? 1 : 0;
        return res;
    }

    public static Vec4b not_(final Vec4b v) {
        return not(v, new Vec4b());
    }

    public static Vec4b not(final Vec4b v, final Vec4b res) {
        res.x = (byte) (v.x == 0 ? 1 : 0);
        res.y = (byte) (v.y == 0 ? 1 : 0);
        res.z = (byte) (v.z == 0 ? 1 : 0);
        res.w = (byte) (v.w == 0 ? 1 : 0);
        return res;
    }

    public static Vec4bool not_(final Vec4bool v) {
        return not(v, new Vec4bool());
    }

    public static Vec4bool not(final Vec4bool v, final Vec4bool res) {
        res.x = !v.x;
        res.y = !v.y;
        res.z = !v.z;
        res.w = !v.w;
        return res;
    }

    public static Vec4d not_(final Vec4d v) {
        return not(v, new Vec4d());
    }

    public static Vec4d not(final Vec4d v, final Vec4d res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        res.z = v.z == 0 ? 1 : 0;
        res.w = v.w == 0 ? 1 : 0;
        return res;
    }

    public static Vec4i not_(final Vec4i v) {
        return not(v, new Vec4i());
    }

    public static Vec4i not(final Vec4i v, final Vec4i res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        res.z = v.z == 0 ? 1 : 0;
        res.w = v.w == 0 ? 1 : 0;
        return res;
    }

    public static Vec4l not_(final Vec4l v) {
        return not(v, new Vec4l());
    }

    public static Vec4l not(final Vec4l v, final Vec4l res) {
        res.x = v.x == 0 ? 1 : 0;
        res.y = v.y == 0 ? 1 : 0;
        res.z = v.z == 0 ? 1 : 0;
        res.w = v.w == 0 ? 1 : 0;
        return res;
    }

    public static Vec4s not_(final Vec4s v) {
        return not(v, new Vec4s());
    }

    public static Vec4s not(final Vec4s v, final Vec4s res) {
        res.x = (short) (v.x == 0 ? 1 : 0);
        res.y = (short) (v.y == 0 ? 1 : 0);
        res.z = (short) (v.z == 0 ? 1 : 0);
        res.w = (short) (v.w == 0 ? 1 : 0);
        return res;
    }

    public static Vec4ub not_(final Vec4ub v) {
        return not(v, new Vec4ub());
    }

    public static Vec4ub not(final Vec4ub v, final Vec4ub res) {
        res.x.value = (byte) (v.x.value == 0 ? 1 : 0);
        res.y.value = (byte) (v.y.value == 0 ? 1 : 0);
        res.z.value = (byte) (v.z.value == 0 ? 1 : 0);
        res.w.value = (byte) (v.w.value == 0 ? 1 : 0);
        return res;
    }

    public static Vec4ui not_(final Vec4ui v) {
        return not(v, new Vec4ui());
    }

    public static Vec4ui not(final Vec4ui v, final Vec4ui res) {
        res.x.value = v.x.value == 0 ? 1 : 0;
        res.y.value = v.y.value == 0 ? 1 : 0;
        res.z.value = v.z.value == 0 ? 1 : 0;
        res.w.value = v.w.value == 0 ? 1 : 0;
        return res;
    }

    public static Vec4ul not_(final Vec4ul v) {
        return not(v, new Vec4ul());
    }

    public static Vec4ul not(final Vec4ul v, final Vec4ul res) {
        res.x.value = (v.x.value == 0 ? 1 : 0);
        res.y.value = (v.y.value == 0 ? 1 : 0);
        res.z.value = (v.z.value == 0 ? 1 : 0);
        res.w.value = (v.w.value == 0 ? 1 : 0);
        return res;
    }

    public static Vec4us not_(final Vec4us v) {
        return not(v, new Vec4us());
    }

    public static Vec4us not(final Vec4us v, final Vec4us res) {
        res.x.value = (short) (v.x.value == 0 ? 1 : 0);
        res.y.value = (short) (v.y.value == 0 ? 1 : 0);
        res.z.value = (short) (v.z.value == 0 ? 1 : 0);
        res.w.value = (short) (v.w.value == 0 ? 1 : 0);
        return res;
    }

    // lessThan -----------------------------------------------------------------------------------------------------
    public static boolean lessThan(final byte a, final byte b) {
        return a < b;
    }

    public static boolean lessThan(final double a, final double b) {
        return a < b;
    }

    public static boolean lessThan(final float a, final float b) {
        return a < b;
    }

    public static boolean lessThan(final int a, final int b) {
        return a < b;
    }

    public static boolean lessThan(final long a, final long b) {
        return a < b;
    }

    public static boolean lessThan(final short a, final short b) {
        return a < b;
    }

    public static boolean lessThan(final UByte a, final UByte b) {
        return a.compareTo(b) < 0;
    }

    public static boolean lessThan(final UInt a, final UInt b) {
        return a.compareTo(b) < 0;
    }

    public static boolean lessThan(final ULong a, final ULong b) {
        return a.compareTo(b) < 0;
    }

    public static boolean lessThan(final UShort a, final UShort b) {
        return a.compareTo(b) < 0;
    }

    // lessThan (vec2) ---------------------------------------------------------
    public static Vec2bool lessThan__(final Vec2b a, final Vec2b b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2b a, final Vec2b b, final Vec2bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2d a, final Vec2d b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2d a, final Vec2d b, final Vec2bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2 a, final Vec2 b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2 a, final Vec2 b, final Vec2bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2i a, final Vec2i b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2i a, final Vec2i b, final Vec2bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2l a, final Vec2l b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2l a, final Vec2l b, final Vec2bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2s a, final Vec2s b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2s a, final Vec2s b, final Vec2bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2ub a, final Vec2ub b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2ub a, final Vec2ub b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2ui a, final Vec2ui b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2ui a, final Vec2ui b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2ul a, final Vec2ul b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2ul a, final Vec2ul b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        return res;
    }

    public static Vec2bool lessThan__(final Vec2us a, final Vec2us b) {
        return lessThan(a, b, new Vec2bool());
    }

    public static Vec2bool lessThan(final Vec2us a, final Vec2us b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        return res;
    }

    public static Vec2b lessThan_(final Vec2b a, final Vec2b b) {
        return lessThan(a, b, new Vec2b());
    }

    public static Vec2b lessThan(final Vec2b a, final Vec2b b, final Vec2b res) {
        res.x = (byte) (a.x < b.x ? 1 : 0);
        res.y = (byte) (a.y < b.y ? 1 : 0);
        return res;
    }

    public static Vec2d lessThan_(final Vec2d a, final Vec2d b) {
        return lessThan(a, b, new Vec2d());
    }

    public static Vec2d lessThan(final Vec2d a, final Vec2d b, final Vec2d res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        return res;
    }

    public static Vec2 lessThan_(final Vec2 a, final Vec2 b) {
        return lessThan(a, b, new Vec2());
    }

    public static Vec2 lessThan(final Vec2 a, final Vec2 b, final Vec2 res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        return res;
    }

    public static Vec2i lessThan_(final Vec2i a, final Vec2i b) {
        return lessThan(a, b, new Vec2i());
    }

    public static Vec2i lessThan(final Vec2i a, final Vec2i b, final Vec2i res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        return res;
    }

    public static Vec2l lessThan_(final Vec2l a, final Vec2l b) {
        return lessThan(a, b, new Vec2l());
    }

    public static Vec2l lessThan(final Vec2l a, final Vec2l b, final Vec2l res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        return res;
    }

    public static Vec2s lessThan_(final Vec2s a, final Vec2s b) {
        return lessThan(a, b, new Vec2s());
    }

    public static Vec2s lessThan(final Vec2s a, final Vec2s b, final Vec2s res) {
        res.x = (short) (a.x < b.x ? 1 : 0);
        res.y = (short) (a.y < b.y ? 1 : 0);
        return res;
    }

    public static Vec2ub lessThan_(final Vec2ub a, final Vec2ub b) {
        return lessThan(a, b, new Vec2ub());
    }

    public static Vec2ub lessThan(final Vec2ub a, final Vec2ub b, final Vec2ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) < 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) < 0 ? 1 : 0);
        return res;
    }

    public static Vec2ui lessThan_(final Vec2ui a, final Vec2ui b) {
        return lessThan(a, b, new Vec2ui());
    }

    public static Vec2ui lessThan(final Vec2ui a, final Vec2ui b, final Vec2ui res) {
        res.x.value = a.x.compareTo(b.x) < 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) < 0 ? 1 : 0;
        return res;
    }

    public static Vec2ul lessThan_(final Vec2ul a, final Vec2ul b) {
        return lessThan(a, b, new Vec2ul());
    }

    public static Vec2ul lessThan(final Vec2ul a, final Vec2ul b, final Vec2ul res) {
        res.x.value = a.x.compareTo(b.x) < 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) < 0 ? 1 : 0;
        return res;
    }

    public static Vec2us lessThan_(final Vec2us a, final Vec2us b) {
        return lessThan(a, b, new Vec2us());
    }

    public static Vec2us lessThan(final Vec2us a, final Vec2us b, final Vec2us res) {
        res.x.value = (short) (a.x.compareTo(b.x) < 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) < 0 ? 1 : 0);
        return res;
    }

    // lessThan (vec3) ---------------------------------------------------------
    public static Vec3bool lessThan__(final Vec3b a, final Vec3b b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3b a, final Vec3b b, final Vec3bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3d a, final Vec3d b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3d a, final Vec3d b, final Vec3bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3 a, final Vec3 b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3 a, final Vec3 b, final Vec3bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3i a, final Vec3i b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3i a, final Vec3i b, final Vec3bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3l a, final Vec3l b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3l a, final Vec3l b, final Vec3bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3s a, final Vec3s b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3s a, final Vec3s b, final Vec3bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3ub a, final Vec3ub b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3ub a, final Vec3ub b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        res.z = a.z.compareTo(b.z) < 0;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3ui a, final Vec3ui b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3ui a, final Vec3ui b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        res.z = a.z.compareTo(b.z) < 0;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3ul a, final Vec3ul b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3ul a, final Vec3ul b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        res.z = a.z.compareTo(b.z) < 0;
        return res;
    }

    public static Vec3bool lessThan__(final Vec3us a, final Vec3us b) {
        return lessThan(a, b, new Vec3bool());
    }

    public static Vec3bool lessThan(final Vec3us a, final Vec3us b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        res.z = a.z.compareTo(b.z) < 0;
        return res;
    }

    public static Vec3b lessThan_(final Vec3b a, final Vec3b b) {
        return lessThan(a, b, new Vec3b());
    }

    public static Vec3b lessThan(final Vec3b a, final Vec3b b, final Vec3b res) {
        res.x = (byte) (a.x < b.x ? 1 : 0);
        res.y = (byte) (a.y < b.y ? 1 : 0);
        res.z = (byte) (a.z < b.z ? 1 : 0);
        return res;
    }

    public static Vec3d lessThan_(final Vec3d a, final Vec3d b) {
        return lessThan(a, b, new Vec3d());
    }

    public static Vec3d lessThan(final Vec3d a, final Vec3d b, final Vec3d res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        res.z = a.z < b.z ? 1 : 0;
        return res;
    }

    public static Vec3 lessThan_(final Vec3 a, final Vec3 b) {
        return lessThan(a, b, new Vec3());
    }

    public static Vec3 lessThan(final Vec3 a, final Vec3 b, final Vec3 res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        res.z = a.z < b.z ? 1 : 0;
        return res;
    }

    public static Vec3i lessThan_(final Vec3i a, final Vec3i b) {
        return lessThan(a, b, new Vec3i());
    }

    public static Vec3i lessThan(final Vec3i a, final Vec3i b, final Vec3i res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        res.z = a.z < b.z ? 1 : 0;
        return res;
    }

    public static Vec3l lessThan_(final Vec3l a, final Vec3l b) {
        return lessThan(a, b, new Vec3l());
    }

    public static Vec3l lessThan(final Vec3l a, final Vec3l b, final Vec3l res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        res.z = a.z < b.z ? 1 : 0;
        return res;
    }

    public static Vec3s lessThan_(final Vec3s a, final Vec3s b) {
        return lessThan(a, b, new Vec3s());
    }

    public static Vec3s lessThan(final Vec3s a, final Vec3s b, final Vec3s res) {
        res.x = (short) (a.x < b.x ? 1 : 0);
        res.y = (short) (a.y < b.y ? 1 : 0);
        res.z = (short) (a.z < b.z ? 1 : 0);
        return res;
    }

    public static Vec3ub lessThan_(final Vec3ub a, final Vec3ub b) {
        return lessThan(a, b, new Vec3ub());
    }

    public static Vec3ub lessThan(final Vec3ub a, final Vec3ub b, final Vec3ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) < 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) < 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) < 0 ? 1 : 0);
        return res;
    }

    public static Vec3ui lessThan_(final Vec3ui a, final Vec3ui b) {
        return lessThan(a, b, new Vec3ui());
    }

    public static Vec3ui lessThan(final Vec3ui a, final Vec3ui b, final Vec3ui res) {
        res.x.value = a.x.compareTo(b.x) < 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) < 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) < 0 ? 1 : 0;
        return res;
    }

    public static Vec3ul lessThan_(final Vec3ul a, final Vec3ul b) {
        return lessThan(a, b, new Vec3ul());
    }

    public static Vec3ul lessThan(final Vec3ul a, final Vec3ul b, final Vec3ul res) {
        res.x.value = a.x.compareTo(b.x) < 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) < 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) < 0 ? 1 : 0;
        return res;
    }

    public static Vec3us lessThan_(final Vec3us a, final Vec3us b) {
        return lessThan(a, b, new Vec3us());
    }

    public static Vec3us lessThan(final Vec3us a, final Vec3us b, final Vec3us res) {
        res.x.value = (short) (a.x.compareTo(b.x) < 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) < 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) < 0 ? 1 : 0);
        return res;
    }

    // lessThan (vec3) ---------------------------------------------------------
    public static Vec4bool lessThan__(final Vec4b a, final Vec4b b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4b a, final Vec4b b, final Vec4bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        res.w = a.w < b.w;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4d a, final Vec4d b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4d a, final Vec4d b, final Vec4bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        res.w = a.w < b.w;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4 a, final Vec4 b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4 a, final Vec4 b, final Vec4bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        res.w = a.w < b.w;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4i a, final Vec4i b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4i a, final Vec4i b, final Vec4bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        res.w = a.w < b.w;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4l a, final Vec4l b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4l a, final Vec4l b, final Vec4bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        res.w = a.w < b.w;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4s a, final Vec4s b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4s a, final Vec4s b, final Vec4bool res) {
        res.x = a.x < b.x;
        res.y = a.y < b.y;
        res.z = a.z < b.z;
        res.w = a.w < b.w;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4ub a, final Vec4ub b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4ub a, final Vec4ub b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        res.z = a.z.compareTo(b.z) < 0;
        res.w = a.w.compareTo(b.w) < 0;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4ui a, final Vec4ui b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4ui a, final Vec4ui b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        res.z = a.z.compareTo(b.z) < 0;
        res.w = a.w.compareTo(b.w) < 0;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4ul a, final Vec4ul b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4ul a, final Vec4ul b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        res.z = a.z.compareTo(b.z) < 0;
        res.w = a.w.compareTo(b.w) < 0;
        return res;
    }

    public static Vec4bool lessThan__(final Vec4us a, final Vec4us b) {
        return lessThan(a, b, new Vec4bool());
    }

    public static Vec4bool lessThan(final Vec4us a, final Vec4us b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) < 0;
        res.y = a.y.compareTo(b.y) < 0;
        res.z = a.z.compareTo(b.z) < 0;
        res.w = a.w.compareTo(b.w) < 0;
        return res;
    }

    public static Vec4b lessThan_(final Vec4b a, final Vec4b b) {
        return lessThan(a, b, new Vec4b());
    }

    public static Vec4b lessThan(final Vec4b a, final Vec4b b, final Vec4b res) {
        res.x = (byte) (a.x < b.x ? 1 : 0);
        res.y = (byte) (a.y < b.y ? 1 : 0);
        res.z = (byte) (a.z < b.z ? 1 : 0);
        res.w = (byte) (a.w < b.w ? 1 : 0);
        return res;
    }

    public static Vec4d lessThan_(final Vec4d a, final Vec4d b) {
        return lessThan(a, b, new Vec4d());
    }

    public static Vec4d lessThan(final Vec4d a, final Vec4d b, final Vec4d res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        res.z = a.z < b.z ? 1 : 0;
        res.w = a.w < b.w ? 1 : 0;
        return res;
    }

    public static Vec4 lessThan_(final Vec4 a, final Vec4 b) {
        return lessThan(a, b, new Vec4());
    }

    public static Vec4 lessThan(final Vec4 a, final Vec4 b, final Vec4 res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        res.z = a.z < b.z ? 1 : 0;
        res.w = a.w < b.w ? 1 : 0;
        return res;
    }

    public static Vec4i lessThan_(final Vec4i a, final Vec4i b) {
        return lessThan(a, b, new Vec4i());
    }

    public static Vec4i lessThan(final Vec4i a, final Vec4i b, final Vec4i res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        res.z = a.z < b.z ? 1 : 0;
        res.w = a.w < b.w ? 1 : 0;
        return res;
    }

    public static Vec4l lessThan_(final Vec4l a, final Vec4l b) {
        return lessThan(a, b, new Vec4l());
    }

    public static Vec4l lessThan(final Vec4l a, final Vec4l b, final Vec4l res) {
        res.x = a.x < b.x ? 1 : 0;
        res.y = a.y < b.y ? 1 : 0;
        res.z = a.z < b.z ? 1 : 0;
        res.w = a.w < b.w ? 1 : 0;
        return res;
    }

    public static Vec4s lessThan_(final Vec4s a, final Vec4s b) {
        return lessThan(a, b, new Vec4s());
    }

    public static Vec4s lessThan(final Vec4s a, final Vec4s b, final Vec4s res) {
        res.x = (short) (a.x < b.x ? 1 : 0);
        res.y = (short) (a.y < b.y ? 1 : 0);
        res.z = (short) (a.z < b.z ? 1 : 0);
        res.w = (short) (a.w < b.w ? 1 : 0);
        return res;
    }

    public static Vec4ub lessThan_(final Vec4ub a, final Vec4ub b) {
        return lessThan(a, b, new Vec4ub());
    }

    public static Vec4ub lessThan(final Vec4ub a, final Vec4ub b, final Vec4ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) < 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) < 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) < 0 ? 1 : 0);
        res.w.value = (byte) (a.w.compareTo(b.w) < 0 ? 1 : 0);
        return res;
    }

    public static Vec4ui lessThan_(final Vec4ui a, final Vec4ui b) {
        return lessThan(a, b, new Vec4ui());
    }

    public static Vec4ui lessThan(final Vec4ui a, final Vec4ui b, final Vec4ui res) {
        res.x.value = a.x.compareTo(b.x) < 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) < 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) < 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) < 0 ? 1 : 0;
        return res;
    }

    public static Vec4ul lessThan_(final Vec4ul a, final Vec4ul b) {
        return lessThan(a, b, new Vec4ul());
    }

    public static Vec4ul lessThan(final Vec4ul a, final Vec4ul b, final Vec4ul res) {
        res.x.value = a.x.compareTo(b.x) < 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) < 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) < 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) < 0 ? 1 : 0;
        return res;
    }

    public static Vec4us lessThan_(final Vec4us a, final Vec4us b) {
        return lessThan(a, b, new Vec4us());
    }

    public static Vec4us lessThan(final Vec4us a, final Vec4us b, final Vec4us res) {
        res.x.value = (short) (a.x.compareTo(b.x) < 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) < 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) < 0 ? 1 : 0);
        res.w.value = (short) (a.w.compareTo(b.w) < 0 ? 1 : 0);
        return res;
    }

    // lessThanEqual -------------------------------------------------------------------------------------------------
    public static boolean lessThanEqual(final byte a, final byte b) {
        return a <= b;
    }

    public static boolean lessThanEqual(final double a, final double b) {
        return a <= b;
    }

    public static boolean lessThanEqual(final float a, final float b) {
        return a <= b;
    }

    public static boolean lessThanEqual(final int a, final int b) {
        return a <= b;
    }

    public static boolean lessThanEqual(final long a, final long b) {
        return a <= b;
    }

    public static boolean lessThanEqual(final short a, final short b) {
        return a <= b;
    }

    public static boolean lessThanEqual(final UByte a, final UByte b) {
        return a.compareTo(b) <= 0;
    }

    public static boolean lessThanEqual(final UInt a, final UInt b) {
        return a.compareTo(b) <= 0;
    }

    public static boolean lessThanEqual(final ULong a, final ULong b) {
        return a.compareTo(b) <= 0;
    }

    public static boolean lessThanEqual(final UShort a, final UShort b) {
        return a.compareTo(b) <= 0;
    }

    // lessThanEqual (vec2) ----------------------------------------------------
    public static Vec2bool lessThanEqual__(final Vec2b a, final Vec2b b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2b a, final Vec2b b, final Vec2bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2d a, final Vec2d b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2d a, final Vec2d b, final Vec2bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2 a, final Vec2 b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2 a, final Vec2 b, final Vec2bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2i a, final Vec2i b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2i a, final Vec2i b, final Vec2bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2l a, final Vec2l b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2l a, final Vec2l b, final Vec2bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2s a, final Vec2s b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2s a, final Vec2s b, final Vec2bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2ub a, final Vec2ub b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2ub a, final Vec2ub b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2ui a, final Vec2ui b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2ui a, final Vec2ui b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2ul a, final Vec2ul b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2ul a, final Vec2ul b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        return res;
    }

    public static Vec2bool lessThanEqual__(final Vec2us a, final Vec2us b) {
        return lessThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool lessThanEqual(final Vec2us a, final Vec2us b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        return res;
    }

    public static Vec2b lessThanEqual_(final Vec2b a, final Vec2b b) {
        return lessThanEqual(a, b, new Vec2b());
    }

    public static Vec2b lessThanEqual(final Vec2b a, final Vec2b b, final Vec2b res) {
        res.x = (byte) (a.x <= b.x ? 1 : 0);
        res.y = (byte) (a.y <= b.y ? 1 : 0);
        return res;
    }

    public static Vec2d lessThanEqual_(final Vec2d a, final Vec2d b) {
        return lessThanEqual(a, b, new Vec2d());
    }

    public static Vec2d lessThanEqual(final Vec2d a, final Vec2d b, final Vec2d res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        return res;
    }

    public static Vec2 lessThanEqual_(final Vec2 a, final Vec2 b) {
        return lessThanEqual(a, b, new Vec2());
    }

    public static Vec2 lessThanEqual(final Vec2 a, final Vec2 b, final Vec2 res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        return res;
    }

    public static Vec2i lessThanEqual_(final Vec2i a, final Vec2i b) {
        return lessThanEqual(a, b, new Vec2i());
    }

    public static Vec2i lessThanEqual(final Vec2i a, final Vec2i b, final Vec2i res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        return res;
    }

    public static Vec2l lessThanEqual_(final Vec2l a, final Vec2l b) {
        return lessThanEqual(a, b, new Vec2l());
    }

    public static Vec2l lessThanEqual(final Vec2l a, final Vec2l b, final Vec2l res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        return res;
    }

    public static Vec2s lessThanEqual_(final Vec2s a, final Vec2s b) {
        return lessThanEqual(a, b, new Vec2s());
    }

    public static Vec2s lessThanEqual(final Vec2s a, final Vec2s b, final Vec2s res) {
        res.x = (short) (a.x <= b.x ? 1 : 0);
        res.y = (short) (a.y <= b.y ? 1 : 0);
        return res;
    }

    public static Vec2ub lessThanEqual_(final Vec2ub a, final Vec2ub b) {
        return lessThanEqual(a, b, new Vec2ub());
    }

    public static Vec2ub lessThanEqual(final Vec2ub a, final Vec2ub b, final Vec2ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) <= 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) <= 0 ? 1 : 0);
        return res;
    }

    public static Vec2ui lessThanEqual_(final Vec2ui a, final Vec2ui b) {
        return lessThanEqual(a, b, new Vec2ui());
    }

    public static Vec2ui lessThanEqual(final Vec2ui a, final Vec2ui b, final Vec2ui res) {
        res.x.value = a.x.compareTo(b.x) <= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) <= 0 ? 1 : 0;
        return res;
    }

    public static Vec2ul lessThanEqual_(final Vec2ul a, final Vec2ul b) {
        return lessThanEqual(a, b, new Vec2ul());
    }

    public static Vec2ul lessThanEqual(final Vec2ul a, final Vec2ul b, final Vec2ul res) {
        res.x.value = a.x.compareTo(b.x) <= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) <= 0 ? 1 : 0;
        return res;
    }

    public static Vec2us lessThanEqual_(final Vec2us a, final Vec2us b) {
        return lessThanEqual(a, b, new Vec2us());
    }

    public static Vec2us lessThanEqual(final Vec2us a, final Vec2us b, final Vec2us res) {
        res.x.value = (short) (a.x.compareTo(b.x) <= 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) <= 0 ? 1 : 0);
        return res;
    }

    // lessThan (vec3) ---------------------------------------------------------
    public static Vec3bool lessThanEqual__(final Vec3b a, final Vec3b b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3b a, final Vec3b b, final Vec3bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3d a, final Vec3d b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3d a, final Vec3d b, final Vec3bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3 a, final Vec3 b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3 a, final Vec3 b, final Vec3bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3i a, final Vec3i b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3i a, final Vec3i b, final Vec3bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3l a, final Vec3l b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3l a, final Vec3l b, final Vec3bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3s a, final Vec3s b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3s a, final Vec3s b, final Vec3bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3ub a, final Vec3ub b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3ub a, final Vec3ub b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        res.z = a.z.compareTo(b.z) <= 0;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3ui a, final Vec3ui b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3ui a, final Vec3ui b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        res.z = a.z.compareTo(b.z) <= 0;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3ul a, final Vec3ul b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3ul a, final Vec3ul b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        res.z = a.z.compareTo(b.z) <= 0;
        return res;
    }

    public static Vec3bool lessThanEqual__(final Vec3us a, final Vec3us b) {
        return lessThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool lessThanEqual(final Vec3us a, final Vec3us b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        res.z = a.z.compareTo(b.z) <= 0;
        return res;
    }

    public static Vec3b lessThanEqual_(final Vec3b a, final Vec3b b) {
        return lessThanEqual(a, b, new Vec3b());
    }

    public static Vec3b lessThanEqual(final Vec3b a, final Vec3b b, final Vec3b res) {
        res.x = (byte) (a.x <= b.x ? 1 : 0);
        res.y = (byte) (a.y <= b.y ? 1 : 0);
        res.z = (byte) (a.z <= b.z ? 1 : 0);
        return res;
    }

    public static Vec3d lessThanEqual_(final Vec3d a, final Vec3d b) {
        return lessThanEqual(a, b, new Vec3d());
    }

    public static Vec3d lessThanEqual(final Vec3d a, final Vec3d b, final Vec3d res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        res.z = a.z <= b.z ? 1 : 0;
        return res;
    }

    public static Vec3 lessThanEqual_(final Vec3 a, final Vec3 b) {
        return lessThanEqual(a, b, new Vec3());
    }

    public static Vec3 lessThanEqual(final Vec3 a, final Vec3 b, final Vec3 res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        res.z = a.z <= b.z ? 1 : 0;
        return res;
    }

    public static Vec3i lessThanEqual_(final Vec3i a, final Vec3i b) {
        return lessThanEqual(a, b, new Vec3i());
    }

    public static Vec3i lessThanEqual(final Vec3i a, final Vec3i b, final Vec3i res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        res.z = a.z <= b.z ? 1 : 0;
        return res;
    }

    public static Vec3l lessThanEqual_(final Vec3l a, final Vec3l b) {
        return lessThanEqual(a, b, new Vec3l());
    }

    public static Vec3l lessThanEqual(final Vec3l a, final Vec3l b, final Vec3l res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        res.z = a.z <= b.z ? 1 : 0;
        return res;
    }

    public static Vec3s lessThanEqual_(final Vec3s a, final Vec3s b) {
        return lessThanEqual(a, b, new Vec3s());
    }

    public static Vec3s lessThanEqual(final Vec3s a, final Vec3s b, final Vec3s res) {
        res.x = (short) (a.x <= b.x ? 1 : 0);
        res.y = (short) (a.y <= b.y ? 1 : 0);
        res.z = (short) (a.z <= b.z ? 1 : 0);
        return res;
    }

    public static Vec3ub lessThanEqual_(final Vec3ub a, final Vec3ub b) {
        return lessThanEqual(a, b, new Vec3ub());
    }

    public static Vec3ub lessThanEqual(final Vec3ub a, final Vec3ub b, final Vec3ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) <= 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) <= 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) <= 0 ? 1 : 0);
        return res;
    }

    public static Vec3ui lessThanEqual_(final Vec3ui a, final Vec3ui b) {
        return lessThanEqual(a, b, new Vec3ui());
    }

    public static Vec3ui lessThanEqual(final Vec3ui a, final Vec3ui b, final Vec3ui res) {
        res.x.value = a.x.compareTo(b.x) <= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) <= 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) <= 0 ? 1 : 0;
        return res;
    }

    public static Vec3ul lessThanEqual_(final Vec3ul a, final Vec3ul b) {
        return lessThanEqual(a, b, new Vec3ul());
    }

    public static Vec3ul lessThanEqual(final Vec3ul a, final Vec3ul b, final Vec3ul res) {
        res.x.value = a.x.compareTo(b.x) <= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) <= 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) <= 0 ? 1 : 0;
        return res;
    }

    public static Vec3us lessThanEqual_(final Vec3us a, final Vec3us b) {
        return lessThanEqual(a, b, new Vec3us());
    }

    public static Vec3us lessThanEqual(final Vec3us a, final Vec3us b, final Vec3us res) {
        res.x.value = (short) (a.x.compareTo(b.x) <= 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) <= 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) <= 0 ? 1 : 0);
        return res;
    }

    // lessThan (vec3) ---------------------------------------------------------
    public static Vec4bool lessThanEqual__(final Vec4b a, final Vec4b b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4b a, final Vec4b b, final Vec4bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        res.w = a.w <= b.w;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4d a, final Vec4d b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4d a, final Vec4d b, final Vec4bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        res.w = a.w <= b.w;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4 a, final Vec4 b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4 a, final Vec4 b, final Vec4bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        res.w = a.w <= b.w;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4i a, final Vec4i b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4i a, final Vec4i b, final Vec4bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        res.w = a.w <= b.w;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4l a, final Vec4l b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4l a, final Vec4l b, final Vec4bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        res.w = a.w <= b.w;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4s a, final Vec4s b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4s a, final Vec4s b, final Vec4bool res) {
        res.x = a.x <= b.x;
        res.y = a.y <= b.y;
        res.z = a.z <= b.z;
        res.w = a.w <= b.w;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4ub a, final Vec4ub b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4ub a, final Vec4ub b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        res.z = a.z.compareTo(b.z) <= 0;
        res.w = a.w.compareTo(b.w) <= 0;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4ui a, final Vec4ui b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4ui a, final Vec4ui b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        res.z = a.z.compareTo(b.z) <= 0;
        res.w = a.w.compareTo(b.w) <= 0;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4ul a, final Vec4ul b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4ul a, final Vec4ul b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        res.z = a.z.compareTo(b.z) <= 0;
        res.w = a.w.compareTo(b.w) <= 0;
        return res;
    }

    public static Vec4bool lessThanEqual__(final Vec4us a, final Vec4us b) {
        return lessThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool lessThanEqual(final Vec4us a, final Vec4us b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) <= 0;
        res.y = a.y.compareTo(b.y) <= 0;
        res.z = a.z.compareTo(b.z) <= 0;
        res.w = a.w.compareTo(b.w) <= 0;
        return res;
    }

    public static Vec4b lessThanEqual_(final Vec4b a, final Vec4b b) {
        return lessThanEqual(a, b, new Vec4b());
    }

    public static Vec4b lessThanEqual(final Vec4b a, final Vec4b b, final Vec4b res) {
        res.x = (byte) (a.x <= b.x ? 1 : 0);
        res.y = (byte) (a.y <= b.y ? 1 : 0);
        res.z = (byte) (a.z <= b.z ? 1 : 0);
        res.w = (byte) (a.w <= b.w ? 1 : 0);
        return res;
    }

    public static Vec4d lessThanEqual_(final Vec4d a, final Vec4d b) {
        return lessThanEqual(a, b, new Vec4d());
    }

    public static Vec4d lessThanEqual(final Vec4d a, final Vec4d b, final Vec4d res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        res.z = a.z <= b.z ? 1 : 0;
        res.w = a.w <= b.w ? 1 : 0;
        return res;
    }

    public static Vec4 lessThanEqual_(final Vec4 a, final Vec4 b) {
        return lessThanEqual(a, b, new Vec4());
    }

    public static Vec4 lessThanEqual(final Vec4 a, final Vec4 b, final Vec4 res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        res.z = a.z <= b.z ? 1 : 0;
        res.w = a.w <= b.w ? 1 : 0;
        return res;
    }

    public static Vec4i lessThanEqual_(final Vec4i a, final Vec4i b) {
        return lessThanEqual(a, b, new Vec4i());
    }

    public static Vec4i lessThanEqual(final Vec4i a, final Vec4i b, final Vec4i res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        res.z = a.z <= b.z ? 1 : 0;
        res.w = a.w <= b.w ? 1 : 0;
        return res;
    }

    public static Vec4l lessThanEqual_(final Vec4l a, final Vec4l b) {
        return lessThanEqual(a, b, new Vec4l());
    }

    public static Vec4l lessThanEqual(final Vec4l a, final Vec4l b, final Vec4l res) {
        res.x = a.x <= b.x ? 1 : 0;
        res.y = a.y <= b.y ? 1 : 0;
        res.z = a.z <= b.z ? 1 : 0;
        res.w = a.w <= b.w ? 1 : 0;
        return res;
    }

    public static Vec4s lessThanEqual_(final Vec4s a, final Vec4s b) {
        return lessThanEqual(a, b, new Vec4s());
    }

    public static Vec4s lessThanEqual(final Vec4s a, final Vec4s b, final Vec4s res) {
        res.x = (short) (a.x <= b.x ? 1 : 0);
        res.y = (short) (a.y <= b.y ? 1 : 0);
        res.z = (short) (a.z <= b.z ? 1 : 0);
        res.w = (short) (a.w <= b.w ? 1 : 0);
        return res;
    }

    public static Vec4ub lessThanEqual_(final Vec4ub a, final Vec4ub b) {
        return lessThanEqual(a, b, new Vec4ub());
    }

    public static Vec4ub lessThanEqual(final Vec4ub a, final Vec4ub b, final Vec4ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) <= 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) <= 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) <= 0 ? 1 : 0);
        res.w.value = (byte) (a.w.compareTo(b.w) <= 0 ? 1 : 0);
        return res;
    }

    public static Vec4ui lessThanEqual_(final Vec4ui a, final Vec4ui b) {
        return lessThanEqual(a, b, new Vec4ui());
    }

    public static Vec4ui lessThanEqual(final Vec4ui a, final Vec4ui b, final Vec4ui res) {
        res.x.value = a.x.compareTo(b.x) <= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) <= 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) <= 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) <= 0 ? 1 : 0;
        return res;
    }

    public static Vec4ul lessThanEqual_(final Vec4ul a, final Vec4ul b) {
        return lessThanEqual(a, b, new Vec4ul());
    }

    public static Vec4ul lessThanEqual(final Vec4ul a, final Vec4ul b, final Vec4ul res) {
        res.x.value = a.x.compareTo(b.x) <= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) <= 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) <= 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) <= 0 ? 1 : 0;
        return res;
    }

    public static Vec4us lessThanEqual_(final Vec4us a, final Vec4us b) {
        return lessThanEqual(a, b, new Vec4us());
    }

    public static Vec4us lessThanEqual(final Vec4us a, final Vec4us b, final Vec4us res) {
        res.x.value = (short) (a.x.compareTo(b.x) <= 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) <= 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) <= 0 ? 1 : 0);
        res.w.value = (short) (a.w.compareTo(b.w) <= 0 ? 1 : 0);
        return res;
    }

    // greaterThan --------------------------------------------------------------------------------------------------
    public static boolean greaterThan(final byte a, final byte b) {
        return a > b;
    }

    public static boolean greaterThan(final double a, final double b) {
        return a > b;
    }

    public static boolean greaterThan(final float a, final float b) {
        return a > b;
    }

    public static boolean greaterThan(final int a, final int b) {
        return a > b;
    }

    public static boolean greaterThan(final long a, final long b) {
        return a > b;
    }

    public static boolean greaterThan(final short a, final short b) {
        return a > b;
    }

    public static boolean greaterThan(final UByte a, final UByte b) {
        return a.compareTo(b) > 0;
    }

    public static boolean greaterThan(final UInt a, final UInt b) {
        return a.compareTo(b) > 0;
    }

    public static boolean greaterThan(final ULong a, final ULong b) {
        return a.compareTo(b) > 0;
    }

    public static boolean greaterThan(final UShort a, final UShort b) {
        return a.compareTo(b) > 0;
    }

    // greaterThan (vec2) ------------------------------------------------------
    public static Vec2bool greaterThan__(final Vec2b a, final Vec2b b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2b a, final Vec2b b, final Vec2bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2d a, final Vec2d b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2d a, final Vec2d b, final Vec2bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2 a, final Vec2 b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2 a, final Vec2 b, final Vec2bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2i a, final Vec2i b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2i a, final Vec2i b, final Vec2bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2l a, final Vec2l b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2l a, final Vec2l b, final Vec2bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2s a, final Vec2s b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2s a, final Vec2s b, final Vec2bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2ub a, final Vec2ub b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2ub a, final Vec2ub b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2ui a, final Vec2ui b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2ui a, final Vec2ui b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2ul a, final Vec2ul b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2ul a, final Vec2ul b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        return res;
    }

    public static Vec2bool greaterThan__(final Vec2us a, final Vec2us b) {
        return greaterThan(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThan(final Vec2us a, final Vec2us b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        return res;
    }

    public static Vec2b greaterThan_(final Vec2b a, final Vec2b b) {
        return greaterThan(a, b, new Vec2b());
    }

    public static Vec2b greaterThan(final Vec2b a, final Vec2b b, final Vec2b res) {
        res.x = (byte) (a.x > b.x ? 1 : 0);
        res.y = (byte) (a.y > b.y ? 1 : 0);
        return res;
    }

    public static Vec2d greaterThan_(final Vec2d a, final Vec2d b) {
        return greaterThan(a, b, new Vec2d());
    }

    public static Vec2d greaterThan(final Vec2d a, final Vec2d b, final Vec2d res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        return res;
    }

    public static Vec2 greaterThan_(final Vec2 a, final Vec2 b) {
        return greaterThan(a, b, new Vec2());
    }

    public static Vec2 greaterThan(final Vec2 a, final Vec2 b, final Vec2 res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        return res;
    }

    public static Vec2i greaterThan_(final Vec2i a, final Vec2i b) {
        return greaterThan(a, b, new Vec2i());
    }

    public static Vec2i greaterThan(final Vec2i a, final Vec2i b, final Vec2i res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        return res;
    }

    public static Vec2l greaterThan_(final Vec2l a, final Vec2l b) {
        return greaterThan(a, b, new Vec2l());
    }

    public static Vec2l greaterThan(final Vec2l a, final Vec2l b, final Vec2l res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        return res;
    }

    public static Vec2s greaterThan_(final Vec2s a, final Vec2s b) {
        return greaterThan(a, b, new Vec2s());
    }

    public static Vec2s greaterThan(final Vec2s a, final Vec2s b, final Vec2s res) {
        res.x = (short) (a.x > b.x ? 1 : 0);
        res.y = (short) (a.y > b.y ? 1 : 0);
        return res;
    }

    public static Vec2ub greaterThan_(final Vec2ub a, final Vec2ub b) {
        return greaterThan(a, b, new Vec2ub());
    }

    public static Vec2ub greaterThan(final Vec2ub a, final Vec2ub b, final Vec2ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) > 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) > 0 ? 1 : 0);
        return res;
    }

    public static Vec2ui greaterThan_(final Vec2ui a, final Vec2ui b) {
        return greaterThan(a, b, new Vec2ui());
    }

    public static Vec2ui greaterThan(final Vec2ui a, final Vec2ui b, final Vec2ui res) {
        res.x.value = a.x.compareTo(b.x) > 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) > 0 ? 1 : 0;
        return res;
    }

    public static Vec2ul greaterThan_(final Vec2ul a, final Vec2ul b) {
        return greaterThan(a, b, new Vec2ul());
    }

    public static Vec2ul greaterThan(final Vec2ul a, final Vec2ul b, final Vec2ul res) {
        res.x.value = a.x.compareTo(b.x) > 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) > 0 ? 1 : 0;
        return res;
    }

    public static Vec2us greaterThan_(final Vec2us a, final Vec2us b) {
        return greaterThan(a, b, new Vec2us());
    }

    public static Vec2us greaterThan(final Vec2us a, final Vec2us b, final Vec2us res) {
        res.x.value = (short) (a.x.compareTo(b.x) > 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) > 0 ? 1 : 0);
        return res;
    }

    // greaterThan (vec3) ------------------------------------------------------
    public static Vec3bool greaterThan__(final Vec3b a, final Vec3b b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3b a, final Vec3b b, final Vec3bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3d a, final Vec3d b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3d a, final Vec3d b, final Vec3bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3 a, final Vec3 b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3 a, final Vec3 b, final Vec3bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3i a, final Vec3i b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3i a, final Vec3i b, final Vec3bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3l a, final Vec3l b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3l a, final Vec3l b, final Vec3bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3s a, final Vec3s b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3s a, final Vec3s b, final Vec3bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3ub a, final Vec3ub b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3ub a, final Vec3ub b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        res.z = a.z.compareTo(b.z) > 0;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3ui a, final Vec3ui b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3ui a, final Vec3ui b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        res.z = a.z.compareTo(b.z) > 0;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3ul a, final Vec3ul b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3ul a, final Vec3ul b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        res.z = a.z.compareTo(b.z) > 0;
        return res;
    }

    public static Vec3bool greaterThan__(final Vec3us a, final Vec3us b) {
        return greaterThan(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThan(final Vec3us a, final Vec3us b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        res.z = a.z.compareTo(b.z) > 0;
        return res;
    }

    public static Vec3b greaterThan_(final Vec3b a, final Vec3b b) {
        return greaterThan(a, b, new Vec3b());
    }

    public static Vec3b greaterThan(final Vec3b a, final Vec3b b, final Vec3b res) {
        res.x = (byte) (a.x > b.x ? 1 : 0);
        res.y = (byte) (a.y > b.y ? 1 : 0);
        res.z = (byte) (a.z > b.z ? 1 : 0);
        return res;
    }

    public static Vec3d greaterThan_(final Vec3d a, final Vec3d b) {
        return greaterThan(a, b, new Vec3d());
    }

    public static Vec3d greaterThan(final Vec3d a, final Vec3d b, final Vec3d res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        res.z = a.z > b.z ? 1 : 0;
        return res;
    }

    public static Vec3 greaterThan_(final Vec3 a, final Vec3 b) {
        return greaterThan(a, b, new Vec3());
    }

    public static Vec3 greaterThan(final Vec3 a, final Vec3 b, final Vec3 res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        res.z = a.z > b.z ? 1 : 0;
        return res;
    }

    public static Vec3i greaterThan_(final Vec3i a, final Vec3i b) {
        return greaterThan(a, b, new Vec3i());
    }

    public static Vec3i greaterThan(final Vec3i a, final Vec3i b, final Vec3i res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        res.z = a.z > b.z ? 1 : 0;
        return res;
    }

    public static Vec3l greaterThan_(final Vec3l a, final Vec3l b) {
        return greaterThan(a, b, new Vec3l());
    }

    public static Vec3l greaterThan(final Vec3l a, final Vec3l b, final Vec3l res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        res.z = a.z > b.z ? 1 : 0;
        return res;
    }

    public static Vec3s greaterThan_(final Vec3s a, final Vec3s b) {
        return greaterThan(a, b, new Vec3s());
    }

    public static Vec3s greaterThan(final Vec3s a, final Vec3s b, final Vec3s res) {
        res.x = (short) (a.x > b.x ? 1 : 0);
        res.y = (short) (a.y > b.y ? 1 : 0);
        res.z = (short) (a.z > b.z ? 1 : 0);
        return res;
    }

    public static Vec3ub greaterThan_(final Vec3ub a, final Vec3ub b) {
        return greaterThan(a, b, new Vec3ub());
    }

    public static Vec3ub greaterThan(final Vec3ub a, final Vec3ub b, final Vec3ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) > 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) > 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) > 0 ? 1 : 0);
        return res;
    }

    public static Vec3ui greaterThan_(final Vec3ui a, final Vec3ui b) {
        return greaterThan(a, b, new Vec3ui());
    }

    public static Vec3ui greaterThan(final Vec3ui a, final Vec3ui b, final Vec3ui res) {
        res.x.value = a.x.compareTo(b.x) > 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) > 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) > 0 ? 1 : 0;
        return res;
    }

    public static Vec3ul greaterThan_(final Vec3ul a, final Vec3ul b) {
        return greaterThan(a, b, new Vec3ul());
    }

    public static Vec3ul greaterThan(final Vec3ul a, final Vec3ul b, final Vec3ul res) {
        res.x.value = a.x.compareTo(b.x) > 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) > 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) > 0 ? 1 : 0;
        return res;
    }

    public static Vec3us greaterThan_(final Vec3us a, final Vec3us b) {
        return greaterThan(a, b, new Vec3us());
    }

    public static Vec3us greaterThan(final Vec3us a, final Vec3us b, final Vec3us res) {
        res.x.value = (short) (a.x.compareTo(b.x) > 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) > 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) > 0 ? 1 : 0);
        return res;
    }

    // greaterThan (vec4) ------------------------------------------------------
    public static Vec4bool greaterThan__(final Vec4b a, final Vec4b b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4b a, final Vec4b b, final Vec4bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        res.w = a.w > b.w;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4d a, final Vec4d b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4d a, final Vec4d b, final Vec4bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        res.w = a.w > b.w;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4 a, final Vec4 b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4 a, final Vec4 b, final Vec4bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        res.w = a.w > b.w;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4i a, final Vec4i b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4i a, final Vec4i b, final Vec4bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        res.w = a.w > b.w;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4l a, final Vec4l b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4l a, final Vec4l b, final Vec4bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        res.w = a.w > b.w;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4s a, final Vec4s b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4s a, final Vec4s b, final Vec4bool res) {
        res.x = a.x > b.x;
        res.y = a.y > b.y;
        res.z = a.z > b.z;
        res.w = a.w > b.w;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4ub a, final Vec4ub b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4ub a, final Vec4ub b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        res.z = a.z.compareTo(b.z) > 0;
        res.w = a.w.compareTo(b.w) > 0;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4ui a, final Vec4ui b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4ui a, final Vec4ui b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        res.z = a.z.compareTo(b.z) > 0;
        res.w = a.w.compareTo(b.w) > 0;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4ul a, final Vec4ul b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4ul a, final Vec4ul b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        res.z = a.z.compareTo(b.z) > 0;
        res.w = a.w.compareTo(b.w) > 0;
        return res;
    }

    public static Vec4bool greaterThan__(final Vec4us a, final Vec4us b) {
        return greaterThan(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThan(final Vec4us a, final Vec4us b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) > 0;
        res.y = a.y.compareTo(b.y) > 0;
        res.z = a.z.compareTo(b.z) > 0;
        res.w = a.w.compareTo(b.w) > 0;
        return res;
    }

    public static Vec4b greaterThan_(final Vec4b a, final Vec4b b) {
        return greaterThan(a, b, new Vec4b());
    }

    public static Vec4b greaterThan(final Vec4b a, final Vec4b b, final Vec4b res) {
        res.x = (byte) (a.x > b.x ? 1 : 0);
        res.y = (byte) (a.y > b.y ? 1 : 0);
        res.z = (byte) (a.z > b.z ? 1 : 0);
        res.w = (byte) (a.w > b.w ? 1 : 0);
        return res;
    }

    public static Vec4d greaterThan_(final Vec4d a, final Vec4d b) {
        return greaterThan(a, b, new Vec4d());
    }

    public static Vec4d greaterThan(final Vec4d a, final Vec4d b, final Vec4d res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        res.z = a.z > b.z ? 1 : 0;
        res.w = a.w > b.w ? 1 : 0;
        return res;
    }

    public static Vec4 greaterThan_(final Vec4 a, final Vec4 b) {
        return greaterThan(a, b, new Vec4());
    }

    public static Vec4 greaterThan(final Vec4 a, final Vec4 b, final Vec4 res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        res.z = a.z > b.z ? 1 : 0;
        res.w = a.w > b.w ? 1 : 0;
        return res;
    }

    public static Vec4i greaterThan_(final Vec4i a, final Vec4i b) {
        return greaterThan(a, b, new Vec4i());
    }

    public static Vec4i greaterThan(final Vec4i a, final Vec4i b, final Vec4i res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        res.z = a.z > b.z ? 1 : 0;
        res.w = a.w > b.w ? 1 : 0;
        return res;
    }

    public static Vec4l greaterThan_(final Vec4l a, final Vec4l b) {
        return greaterThan(a, b, new Vec4l());
    }

    public static Vec4l greaterThan(final Vec4l a, final Vec4l b, final Vec4l res) {
        res.x = a.x > b.x ? 1 : 0;
        res.y = a.y > b.y ? 1 : 0;
        res.z = a.z > b.z ? 1 : 0;
        res.w = a.w > b.w ? 1 : 0;
        return res;
    }

    public static Vec4s greaterThan_(final Vec4s a, final Vec4s b) {
        return greaterThan(a, b, new Vec4s());
    }

    public static Vec4s greaterThan(final Vec4s a, final Vec4s b, final Vec4s res) {
        res.x = (short) (a.x > b.x ? 1 : 0);
        res.y = (short) (a.y > b.y ? 1 : 0);
        res.z = (short) (a.z > b.z ? 1 : 0);
        res.w = (short) (a.w > b.w ? 1 : 0);
        return res;
    }

    public static Vec4ub greaterThan_(final Vec4ub a, final Vec4ub b) {
        return greaterThan(a, b, new Vec4ub());
    }

    public static Vec4ub greaterThan(final Vec4ub a, final Vec4ub b, final Vec4ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) > 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) > 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) > 0 ? 1 : 0);
        res.w.value = (byte) (a.w.compareTo(b.w) > 0 ? 1 : 0);
        return res;
    }

    public static Vec4ui greaterThan_(final Vec4ui a, final Vec4ui b) {
        return greaterThan(a, b, new Vec4ui());
    }

    public static Vec4ui greaterThan(final Vec4ui a, final Vec4ui b, final Vec4ui res) {
        res.x.value = a.x.compareTo(b.x) > 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) > 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) > 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) > 0 ? 1 : 0;
        return res;
    }

    public static Vec4ul greaterThan_(final Vec4ul a, final Vec4ul b) {
        return greaterThan(a, b, new Vec4ul());
    }

    public static Vec4ul greaterThan(final Vec4ul a, final Vec4ul b, final Vec4ul res) {
        res.x.value = a.x.compareTo(b.x) > 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) > 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) > 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) > 0 ? 1 : 0;
        return res;
    }

    public static Vec4us greaterThan_(final Vec4us a, final Vec4us b) {
        return greaterThan(a, b, new Vec4us());
    }

    public static Vec4us greaterThan(final Vec4us a, final Vec4us b, final Vec4us res) {
        res.x.value = (short) (a.x.compareTo(b.x) > 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) > 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) > 0 ? 1 : 0);
        res.w.value = (short) (a.w.compareTo(b.w) > 0 ? 1 : 0);
        return res;
    }

    // greaterThanEqual ---------------------------------------------------------------------------------------------
    public static boolean greaterThanEqual(final byte a, final byte b) {
        return a >= b;
    }

    public static boolean greaterThanEqual(final double a, final double b) {
        return a >= b;
    }

    public static boolean greaterThanEqual(final float a, final float b) {
        return a >= b;
    }

    public static boolean greaterThanEqual(final int a, final int b) {
        return a >= b;
    }

    public static boolean greaterThanEqual(final long a, final long b) {
        return a >= b;
    }

    public static boolean greaterThanEqual(final short a, final short b) {
        return a >= b;
    }

    public static boolean greaterThanEqual(final UByte a, final UByte b) {
        return a.compareTo(b) >= 0;
    }

    public static boolean greaterThanEqual(final UInt a, final UInt b) {
        return a.compareTo(b) >= 0;
    }

    public static boolean greaterThanEqual(final ULong a, final ULong b) {
        return a.compareTo(b) >= 0;
    }

    public static boolean greaterThanEqual(final UShort a, final UShort b) {
        return a.compareTo(b) >= 0;
    }

    // greaterThanEqual (vec2) -------------------------------------------------
    public static Vec2bool greaterThanEqual__(final Vec2b a, final Vec2b b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2b a, final Vec2b b, final Vec2bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2d a, final Vec2d b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2d a, final Vec2d b, final Vec2bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2 a, final Vec2 b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2 a, final Vec2 b, final Vec2bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2i a, final Vec2i b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2i a, final Vec2i b, final Vec2bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2l a, final Vec2l b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2l a, final Vec2l b, final Vec2bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2s a, final Vec2s b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2s a, final Vec2s b, final Vec2bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2ub a, final Vec2ub b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2ub a, final Vec2ub b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2ui a, final Vec2ui b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2ui a, final Vec2ui b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2ul a, final Vec2ul b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2ul a, final Vec2ul b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        return res;
    }

    public static Vec2bool greaterThanEqual__(final Vec2us a, final Vec2us b) {
        return greaterThanEqual(a, b, new Vec2bool());
    }

    public static Vec2bool greaterThanEqual(final Vec2us a, final Vec2us b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        return res;
    }

    public static Vec2b greaterThanEqual_(final Vec2b a, final Vec2b b) {
        return greaterThanEqual(a, b, new Vec2b());
    }

    public static Vec2b greaterThanEqual(final Vec2b a, final Vec2b b, final Vec2b res) {
        res.x = (byte) (a.x >= b.x ? 1 : 0);
        res.y = (byte) (a.y >= b.y ? 1 : 0);
        return res;
    }

    public static Vec2d greaterThanEqual_(final Vec2d a, final Vec2d b) {
        return greaterThanEqual(a, b, new Vec2d());
    }

    public static Vec2d greaterThanEqual(final Vec2d a, final Vec2d b, final Vec2d res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        return res;
    }

    public static Vec2 greaterThanEqual_(final Vec2 a, final Vec2 b) {
        return greaterThanEqual(a, b, new Vec2());
    }

    public static Vec2 greaterThanEqual(final Vec2 a, final Vec2 b, final Vec2 res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        return res;
    }

    public static Vec2i greaterThanEqual_(final Vec2i a, final Vec2i b) {
        return greaterThanEqual(a, b, new Vec2i());
    }

    public static Vec2i greaterThanEqual(final Vec2i a, final Vec2i b, final Vec2i res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        return res;
    }

    public static Vec2l greaterThanEqual_(final Vec2l a, final Vec2l b) {
        return greaterThanEqual(a, b, new Vec2l());
    }

    public static Vec2l greaterThanEqual(final Vec2l a, final Vec2l b, final Vec2l res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        return res;
    }

    public static Vec2s greaterThanEqual_(final Vec2s a, final Vec2s b) {
        return greaterThanEqual(a, b, new Vec2s());
    }

    public static Vec2s greaterThanEqual(final Vec2s a, final Vec2s b, final Vec2s res) {
        res.x = (short) (a.x >= b.x ? 1 : 0);
        res.y = (short) (a.y >= b.y ? 1 : 0);
        return res;
    }

    public static Vec2ub greaterThanEqual_(final Vec2ub a, final Vec2ub b) {
        return greaterThanEqual(a, b, new Vec2ub());
    }

    public static Vec2ub greaterThanEqual(final Vec2ub a, final Vec2ub b, final Vec2ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) >= 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) >= 0 ? 1 : 0);
        return res;
    }

    public static Vec2ui greaterThanEqual_(final Vec2ui a, final Vec2ui b) {
        return greaterThanEqual(a, b, new Vec2ui());
    }

    public static Vec2ui greaterThanEqual(final Vec2ui a, final Vec2ui b, final Vec2ui res) {
        res.x.value = a.x.compareTo(b.x) >= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) >= 0 ? 1 : 0;
        return res;
    }

    public static Vec2ul greaterThanEqual_(final Vec2ul a, final Vec2ul b) {
        return greaterThanEqual(a, b, new Vec2ul());
    }

    public static Vec2ul greaterThanEqual(final Vec2ul a, final Vec2ul b, final Vec2ul res) {
        res.x.value = a.x.compareTo(b.x) >= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) >= 0 ? 1 : 0;
        return res;
    }

    public static Vec2us greaterThanEqual_(final Vec2us a, final Vec2us b) {
        return greaterThanEqual(a, b, new Vec2us());
    }

    public static Vec2us greaterThanEqual(final Vec2us a, final Vec2us b, final Vec2us res) {
        res.x.value = (short) (a.x.compareTo(b.x) >= 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) >= 0 ? 1 : 0);
        return res;
    }

    // greaterThanEqual (vec3) -------------------------------------------------
    public static Vec3bool greaterThanEqual__(final Vec3b a, final Vec3b b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3b a, final Vec3b b, final Vec3bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3d a, final Vec3d b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3d a, final Vec3d b, final Vec3bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3 a, final Vec3 b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3 a, final Vec3 b, final Vec3bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3i a, final Vec3i b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3i a, final Vec3i b, final Vec3bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3l a, final Vec3l b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3l a, final Vec3l b, final Vec3bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3s a, final Vec3s b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3s a, final Vec3s b, final Vec3bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3ub a, final Vec3ub b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3ub a, final Vec3ub b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        res.z = a.z.compareTo(b.z) >= 0;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3ui a, final Vec3ui b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3ui a, final Vec3ui b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        res.z = a.z.compareTo(b.z) >= 0;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3ul a, final Vec3ul b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3ul a, final Vec3ul b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        res.z = a.z.compareTo(b.z) >= 0;
        return res;
    }

    public static Vec3bool greaterThanEqual__(final Vec3us a, final Vec3us b) {
        return greaterThanEqual(a, b, new Vec3bool());
    }

    public static Vec3bool greaterThanEqual(final Vec3us a, final Vec3us b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        res.z = a.z.compareTo(b.z) >= 0;
        return res;
    }

    public static Vec3b greaterThanEqual_(final Vec3b a, final Vec3b b) {
        return greaterThanEqual(a, b, new Vec3b());
    }

    public static Vec3b greaterThanEqual(final Vec3b a, final Vec3b b, final Vec3b res) {
        res.x = (byte) (a.x >= b.x ? 1 : 0);
        res.y = (byte) (a.y >= b.y ? 1 : 0);
        res.z = (byte) (a.z >= b.z ? 1 : 0);
        return res;
    }

    public static Vec3d greaterThanEqual_(final Vec3d a, final Vec3d b) {
        return greaterThanEqual(a, b, new Vec3d());
    }

    public static Vec3d greaterThanEqual(final Vec3d a, final Vec3d b, final Vec3d res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        res.z = a.z >= b.z ? 1 : 0;
        return res;
    }

    public static Vec3 greaterThanEqual_(final Vec3 a, final Vec3 b) {
        return greaterThanEqual(a, b, new Vec3());
    }

    public static Vec3 greaterThanEqual(final Vec3 a, final Vec3 b, final Vec3 res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        res.z = a.z >= b.z ? 1 : 0;
        return res;
    }

    public static Vec3i greaterThanEqual_(final Vec3i a, final Vec3i b) {
        return greaterThanEqual(a, b, new Vec3i());
    }

    public static Vec3i greaterThanEqual(final Vec3i a, final Vec3i b, final Vec3i res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        res.z = a.z >= b.z ? 1 : 0;
        return res;
    }

    public static Vec3l greaterThanEqual_(final Vec3l a, final Vec3l b) {
        return greaterThanEqual(a, b, new Vec3l());
    }

    public static Vec3l greaterThanEqual(final Vec3l a, final Vec3l b, final Vec3l res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        res.z = a.z >= b.z ? 1 : 0;
        return res;
    }

    public static Vec3s greaterThanEqual_(final Vec3s a, final Vec3s b) {
        return greaterThanEqual(a, b, new Vec3s());
    }

    public static Vec3s greaterThanEqual(final Vec3s a, final Vec3s b, final Vec3s res) {
        res.x = (short) (a.x >= b.x ? 1 : 0);
        res.y = (short) (a.y >= b.y ? 1 : 0);
        res.z = (short) (a.z >= b.z ? 1 : 0);
        return res;
    }

    public static Vec3ub greaterThanEqual_(final Vec3ub a, final Vec3ub b) {
        return greaterThanEqual(a, b, new Vec3ub());
    }

    public static Vec3ub greaterThanEqual(final Vec3ub a, final Vec3ub b, final Vec3ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) >= 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) >= 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) >= 0 ? 1 : 0);
        return res;
    }

    public static Vec3ui greaterThanEqual_(final Vec3ui a, final Vec3ui b) {
        return greaterThanEqual(a, b, new Vec3ui());
    }

    public static Vec3ui greaterThanEqual(final Vec3ui a, final Vec3ui b, final Vec3ui res) {
        res.x.value = a.x.compareTo(b.x) >= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) >= 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) >= 0 ? 1 : 0;
        return res;
    }

    public static Vec3ul greaterThanEqual_(final Vec3ul a, final Vec3ul b) {
        return greaterThanEqual(a, b, new Vec3ul());
    }

    public static Vec3ul greaterThanEqual(final Vec3ul a, final Vec3ul b, final Vec3ul res) {
        res.x.value = a.x.compareTo(b.x) >= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) >= 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) >= 0 ? 1 : 0;
        return res;
    }

    public static Vec3us greaterThanEqual_(final Vec3us a, final Vec3us b) {
        return greaterThanEqual(a, b, new Vec3us());
    }

    public static Vec3us greaterThanEqual(final Vec3us a, final Vec3us b, final Vec3us res) {
        res.x.value = (short) (a.x.compareTo(b.x) >= 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) >= 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) >= 0 ? 1 : 0);
        return res;
    }

    // greaterThanEqual (vec4) -------------------------------------------------
    public static Vec4bool greaterThanEqual__(final Vec4b a, final Vec4b b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4b a, final Vec4b b, final Vec4bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        res.w = a.w >= b.w;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4d a, final Vec4d b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4d a, final Vec4d b, final Vec4bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        res.w = a.w >= b.w;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4 a, final Vec4 b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4 a, final Vec4 b, final Vec4bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        res.w = a.w >= b.w;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4i a, final Vec4i b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4i a, final Vec4i b, final Vec4bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        res.w = a.w >= b.w;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4l a, final Vec4l b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4l a, final Vec4l b, final Vec4bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        res.w = a.w >= b.w;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4s a, final Vec4s b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4s a, final Vec4s b, final Vec4bool res) {
        res.x = a.x >= b.x;
        res.y = a.y >= b.y;
        res.z = a.z >= b.z;
        res.w = a.w >= b.w;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4ub a, final Vec4ub b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4ub a, final Vec4ub b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        res.z = a.z.compareTo(b.z) >= 0;
        res.w = a.w.compareTo(b.w) >= 0;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4ui a, final Vec4ui b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4ui a, final Vec4ui b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        res.z = a.z.compareTo(b.z) >= 0;
        res.w = a.w.compareTo(b.w) >= 0;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4ul a, final Vec4ul b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4ul a, final Vec4ul b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        res.z = a.z.compareTo(b.z) >= 0;
        res.w = a.w.compareTo(b.w) >= 0;
        return res;
    }

    public static Vec4bool greaterThanEqual__(final Vec4us a, final Vec4us b) {
        return greaterThanEqual(a, b, new Vec4bool());
    }

    public static Vec4bool greaterThanEqual(final Vec4us a, final Vec4us b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) >= 0;
        res.y = a.y.compareTo(b.y) >= 0;
        res.z = a.z.compareTo(b.z) >= 0;
        res.w = a.w.compareTo(b.w) >= 0;
        return res;
    }

    public static Vec4b greaterThanEqual_(final Vec4b a, final Vec4b b) {
        return greaterThanEqual(a, b, new Vec4b());
    }

    public static Vec4b greaterThanEqual(final Vec4b a, final Vec4b b, final Vec4b res) {
        res.x = (byte) (a.x >= b.x ? 1 : 0);
        res.y = (byte) (a.y >= b.y ? 1 : 0);
        res.z = (byte) (a.z >= b.z ? 1 : 0);
        res.w = (byte) (a.w >= b.w ? 1 : 0);
        return res;
    }

    public static Vec4d greaterThanEqual_(final Vec4d a, final Vec4d b) {
        return greaterThanEqual(a, b, new Vec4d());
    }

    public static Vec4d greaterThanEqual(final Vec4d a, final Vec4d b, final Vec4d res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        res.z = a.z >= b.z ? 1 : 0;
        res.w = a.w >= b.w ? 1 : 0;
        return res;
    }

    public static Vec4 greaterThanEqual_(final Vec4 a, final Vec4 b) {
        return greaterThanEqual(a, b, new Vec4());
    }

    public static Vec4 greaterThanEqual(final Vec4 a, final Vec4 b, final Vec4 res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        res.z = a.z >= b.z ? 1 : 0;
        res.w = a.w >= b.w ? 1 : 0;
        return res;
    }

    public static Vec4i greaterThanEqual_(final Vec4i a, final Vec4i b) {
        return greaterThanEqual(a, b, new Vec4i());
    }

    public static Vec4i greaterThanEqual(final Vec4i a, final Vec4i b, final Vec4i res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        res.z = a.z >= b.z ? 1 : 0;
        res.w = a.w >= b.w ? 1 : 0;
        return res;
    }

    public static Vec4l greaterThanEqual_(final Vec4l a, final Vec4l b) {
        return greaterThanEqual(a, b, new Vec4l());
    }

    public static Vec4l greaterThanEqual(final Vec4l a, final Vec4l b, final Vec4l res) {
        res.x = a.x >= b.x ? 1 : 0;
        res.y = a.y >= b.y ? 1 : 0;
        res.z = a.z >= b.z ? 1 : 0;
        res.w = a.w >= b.w ? 1 : 0;
        return res;
    }

    public static Vec4s greaterThanEqual_(final Vec4s a, final Vec4s b) {
        return greaterThanEqual(a, b, new Vec4s());
    }

    public static Vec4s greaterThanEqual(final Vec4s a, final Vec4s b, final Vec4s res) {
        res.x = (short) (a.x >= b.x ? 1 : 0);
        res.y = (short) (a.y >= b.y ? 1 : 0);
        res.z = (short) (a.z >= b.z ? 1 : 0);
        res.w = (short) (a.w >= b.w ? 1 : 0);
        return res;
    }

    public static Vec4ub greaterThanEqual_(final Vec4ub a, final Vec4ub b) {
        return greaterThanEqual(a, b, new Vec4ub());
    }

    public static Vec4ub greaterThanEqual(final Vec4ub a, final Vec4ub b, final Vec4ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) >= 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) >= 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) >= 0 ? 1 : 0);
        res.w.value = (byte) (a.w.compareTo(b.w) >= 0 ? 1 : 0);
        return res;
    }

    public static Vec4ui greaterThanEqual_(final Vec4ui a, final Vec4ui b) {
        return greaterThanEqual(a, b, new Vec4ui());
    }

    public static Vec4ui greaterThanEqual(final Vec4ui a, final Vec4ui b, final Vec4ui res) {
        res.x.value = a.x.compareTo(b.x) >= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) >= 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) >= 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) >= 0 ? 1 : 0;
        return res;
    }

    public static Vec4ul greaterThanEqual_(final Vec4ul a, final Vec4ul b) {
        return greaterThanEqual(a, b, new Vec4ul());
    }

    public static Vec4ul greaterThanEqual(final Vec4ul a, final Vec4ul b, final Vec4ul res) {
        res.x.value = a.x.compareTo(b.x) >= 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) >= 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) >= 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) >= 0 ? 1 : 0;
        return res;
    }

    public static Vec4us greaterThanEqual_(final Vec4us a, final Vec4us b) {
        return greaterThanEqual(a, b, new Vec4us());
    }

    public static Vec4us greaterThanEqual(final Vec4us a, final Vec4us b, final Vec4us res) {
        res.x.value = (short) (a.x.compareTo(b.x) >= 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) >= 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) >= 0 ? 1 : 0);
        res.w.value = (short) (a.w.compareTo(b.w) >= 0 ? 1 : 0);
        return res;
    }

    // equal --------------------------------------------------------------------------------------------------------
    public static boolean equal(final byte a, final byte b) {
        return a == b;
    }

    public static boolean equal(final double a, final double b) {
        return a == b;
    }

    public static boolean equal(final float a, final float b) {
        return a == b;
    }

    public static boolean equal(final int a, final int b) {
        return a == b;
    }

    public static boolean equal(final long a, final long b) {
        return a == b;
    }

    public static boolean equal(final short a, final short b) {
        return a == b;
    }

    public static boolean equal(final UByte a, final UByte b) {
        return a.compareTo(b) == 0;
    }

    public static boolean equal(final UInt a, final UInt b) {
        return a.compareTo(b) == 0;
    }

    public static boolean equal(final ULong a, final ULong b) {
        return a.compareTo(b) == 0;
    }

    public static boolean equal(final UShort a, final UShort b) {
        return a.compareTo(b) == 0;
    }

    // equal (vec2) ------------------------------------------------------------
    public static Vec2bool equal__(final Vec2b a, final Vec2b b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2b a, final Vec2b b, final Vec2bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        return res;
    }

    public static Vec2bool equal__(final Vec2d a, final Vec2d b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2d a, final Vec2d b, final Vec2bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        return res;
    }

    public static Vec2bool equal__(final Vec2 a, final Vec2 b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2 a, final Vec2 b, final Vec2bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        return res;
    }

    public static Vec2bool equal__(final Vec2i a, final Vec2i b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2i a, final Vec2i b, final Vec2bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        return res;
    }

    public static Vec2bool equal__(final Vec2l a, final Vec2l b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2l a, final Vec2l b, final Vec2bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        return res;
    }

    public static Vec2bool equal__(final Vec2s a, final Vec2s b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2s a, final Vec2s b, final Vec2bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        return res;
    }

    public static Vec2bool equal__(final Vec2ub a, final Vec2ub b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2ub a, final Vec2ub b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        return res;
    }

    public static Vec2bool equal__(final Vec2ui a, final Vec2ui b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2ui a, final Vec2ui b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        return res;
    }

    public static Vec2bool equal__(final Vec2ul a, final Vec2ul b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2ul a, final Vec2ul b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        return res;
    }

    public static Vec2bool equal__(final Vec2us a, final Vec2us b) {
        return equal(a, b, new Vec2bool());
    }

    public static Vec2bool equal(final Vec2us a, final Vec2us b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        return res;
    }

    public static Vec2b equal_(final Vec2b a, final Vec2b b) {
        return equal(a, b, new Vec2b());
    }

    public static Vec2b equal(final Vec2b a, final Vec2b b, final Vec2b res) {
        res.x = (byte) (a.x == b.x ? 1 : 0);
        res.y = (byte) (a.y == b.y ? 1 : 0);
        return res;
    }

    public static Vec2d equal_(final Vec2d a, final Vec2d b) {
        return equal(a, b, new Vec2d());
    }

    public static Vec2d equal(final Vec2d a, final Vec2d b, final Vec2d res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        return res;
    }

    public static Vec2 equal_(final Vec2 a, final Vec2 b) {
        return equal(a, b, new Vec2());
    }

    public static Vec2 equal(final Vec2 a, final Vec2 b, final Vec2 res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        return res;
    }

    public static Vec2i equal_(final Vec2i a, final Vec2i b) {
        return equal(a, b, new Vec2i());
    }

    public static Vec2i equal(final Vec2i a, final Vec2i b, final Vec2i res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        return res;
    }

    public static Vec2l equal_(final Vec2l a, final Vec2l b) {
        return equal(a, b, new Vec2l());
    }

    public static Vec2l equal(final Vec2l a, final Vec2l b, final Vec2l res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        return res;
    }

    public static Vec2s equal_(final Vec2s a, final Vec2s b) {
        return equal(a, b, new Vec2s());
    }

    public static Vec2s equal(final Vec2s a, final Vec2s b, final Vec2s res) {
        res.x = (short) (a.x == b.x ? 1 : 0);
        res.y = (short) (a.y == b.y ? 1 : 0);
        return res;
    }

    public static Vec2ub equal_(final Vec2ub a, final Vec2ub b) {
        return equal(a, b, new Vec2ub());
    }

    public static Vec2ub equal(final Vec2ub a, final Vec2ub b, final Vec2ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) == 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) == 0 ? 1 : 0);
        return res;
    }

    public static Vec2ui equal_(final Vec2ui a, final Vec2ui b) {
        return equal(a, b, new Vec2ui());
    }

    public static Vec2ui equal(final Vec2ui a, final Vec2ui b, final Vec2ui res) {
        res.x.value = a.x.compareTo(b.x) == 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) == 0 ? 1 : 0;
        return res;
    }

    public static Vec2ul equal_(final Vec2ul a, final Vec2ul b) {
        return equal(a, b, new Vec2ul());
    }

    public static Vec2ul equal(final Vec2ul a, final Vec2ul b, final Vec2ul res) {
        res.x.value = a.x.compareTo(b.x) == 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) == 0 ? 1 : 0;
        return res;
    }

    public static Vec2us equal_(final Vec2us a, final Vec2us b) {
        return equal(a, b, new Vec2us());
    }

    public static Vec2us equal(final Vec2us a, final Vec2us b, final Vec2us res) {
        res.x.value = (short) (a.x.compareTo(b.x) == 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) == 0 ? 1 : 0);
        return res;
    }

    // equal (vec3) ------------------------------------------------------------
    public static Vec3bool equal__(final Vec3b a, final Vec3b b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3b a, final Vec3b b, final Vec3bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        return res;
    }

    public static Vec3bool equal__(final Vec3d a, final Vec3d b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3d a, final Vec3d b, final Vec3bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        return res;
    }

    public static Vec3bool equal__(final Vec3 a, final Vec3 b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3 a, final Vec3 b, final Vec3bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        return res;
    }

    public static Vec3bool equal__(final Vec3i a, final Vec3i b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3i a, final Vec3i b, final Vec3bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        return res;
    }

    public static Vec3bool equal__(final Vec3l a, final Vec3l b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3l a, final Vec3l b, final Vec3bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        return res;
    }

    public static Vec3bool equal__(final Vec3s a, final Vec3s b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3s a, final Vec3s b, final Vec3bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        return res;
    }

    public static Vec3bool equal__(final Vec3ub a, final Vec3ub b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3ub a, final Vec3ub b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        res.z = a.z.compareTo(b.z) == 0;
        return res;
    }

    public static Vec3bool equal__(final Vec3ui a, final Vec3ui b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3ui a, final Vec3ui b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        res.z = a.z.compareTo(b.z) == 0;
        return res;
    }

    public static Vec3bool equal__(final Vec3ul a, final Vec3ul b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3ul a, final Vec3ul b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        res.z = a.z.compareTo(b.z) == 0;
        return res;
    }

    public static Vec3bool equal__(final Vec3us a, final Vec3us b) {
        return equal(a, b, new Vec3bool());
    }

    public static Vec3bool equal(final Vec3us a, final Vec3us b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        res.z = a.z.compareTo(b.z) == 0;
        return res;
    }

    public static Vec3b equal_(final Vec3b a, final Vec3b b) {
        return equal(a, b, new Vec3b());
    }

    public static Vec3b equal(final Vec3b a, final Vec3b b, final Vec3b res) {
        res.x = (byte) (a.x == b.x ? 1 : 0);
        res.y = (byte) (a.y == b.y ? 1 : 0);
        res.z = (byte) (a.z == b.z ? 1 : 0);
        return res;
    }

    public static Vec3d equal_(final Vec3d a, final Vec3d b) {
        return equal(a, b, new Vec3d());
    }

    public static Vec3d equal(final Vec3d a, final Vec3d b, final Vec3d res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        res.z = a.z == b.z ? 1 : 0;
        return res;
    }

    public static Vec3 equal_(final Vec3 a, final Vec3 b) {
        return equal(a, b, new Vec3());
    }

    public static Vec3 equal(final Vec3 a, final Vec3 b, final Vec3 res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        res.z = a.z == b.z ? 1 : 0;
        return res;
    }

    public static Vec3i equal_(final Vec3i a, final Vec3i b) {
        return equal(a, b, new Vec3i());
    }

    public static Vec3i equal(final Vec3i a, final Vec3i b, final Vec3i res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        res.z = a.z == b.z ? 1 : 0;
        return res;
    }

    public static Vec3l equal_(final Vec3l a, final Vec3l b) {
        return equal(a, b, new Vec3l());
    }

    public static Vec3l equal(final Vec3l a, final Vec3l b, final Vec3l res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        res.z = a.z == b.z ? 1 : 0;
        return res;
    }

    public static Vec3s equal_(final Vec3s a, final Vec3s b) {
        return equal(a, b, new Vec3s());
    }

    public static Vec3s equal(final Vec3s a, final Vec3s b, final Vec3s res) {
        res.x = (short) (a.x == b.x ? 1 : 0);
        res.y = (short) (a.y == b.y ? 1 : 0);
        res.z = (short) (a.z == b.z ? 1 : 0);
        return res;
    }

    public static Vec3ub equal_(final Vec3ub a, final Vec3ub b) {
        return equal(a, b, new Vec3ub());
    }

    public static Vec3ub equal(final Vec3ub a, final Vec3ub b, final Vec3ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) == 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) == 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) == 0 ? 1 : 0);
        return res;
    }

    public static Vec3ui equal_(final Vec3ui a, final Vec3ui b) {
        return equal(a, b, new Vec3ui());
    }

    public static Vec3ui equal(final Vec3ui a, final Vec3ui b, final Vec3ui res) {
        res.x.value = a.x.compareTo(b.x) == 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) == 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) == 0 ? 1 : 0;
        return res;
    }

    public static Vec3ul equal_(final Vec3ul a, final Vec3ul b) {
        return equal(a, b, new Vec3ul());
    }

    public static Vec3ul equal(final Vec3ul a, final Vec3ul b, final Vec3ul res) {
        res.x.value = a.x.compareTo(b.x) == 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) == 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) == 0 ? 1 : 0;
        return res;
    }

    public static Vec3us equal_(final Vec3us a, final Vec3us b) {
        return equal(a, b, new Vec3us());
    }

    public static Vec3us equal(final Vec3us a, final Vec3us b, final Vec3us res) {
        res.x.value = (short) (a.x.compareTo(b.x) == 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) == 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) == 0 ? 1 : 0);
        return res;
    }

    // equal (vec4) ------------------------------------------------------------
    public static Vec4bool equal__(final Vec4b a, final Vec4b b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4b a, final Vec4b b, final Vec4bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        res.w = a.w == b.w;
        return res;
    }

    public static Vec4bool equal__(final Vec4d a, final Vec4d b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4d a, final Vec4d b, final Vec4bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        res.w = a.w == b.w;
        return res;
    }

    public static Vec4bool equal__(final Vec4 a, final Vec4 b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4 a, final Vec4 b, final Vec4bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        res.w = a.w == b.w;
        return res;
    }

    public static Vec4bool equal__(final Vec4i a, final Vec4i b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4i a, final Vec4i b, final Vec4bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        res.w = a.w == b.w;
        return res;
    }

    public static Vec4bool equal__(final Vec4l a, final Vec4l b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4l a, final Vec4l b, final Vec4bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        res.w = a.w == b.w;
        return res;
    }

    public static Vec4bool equal__(final Vec4s a, final Vec4s b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4s a, final Vec4s b, final Vec4bool res) {
        res.x = a.x == b.x;
        res.y = a.y == b.y;
        res.z = a.z == b.z;
        res.w = a.w == b.w;
        return res;
    }

    public static Vec4bool equal__(final Vec4ub a, final Vec4ub b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4ub a, final Vec4ub b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        res.z = a.z.compareTo(b.z) == 0;
        res.w = a.w.compareTo(b.w) == 0;
        return res;
    }

    public static Vec4bool equal__(final Vec4ui a, final Vec4ui b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4ui a, final Vec4ui b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        res.z = a.z.compareTo(b.z) == 0;
        res.w = a.w.compareTo(b.w) == 0;
        return res;
    }

    public static Vec4bool equal__(final Vec4ul a, final Vec4ul b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4ul a, final Vec4ul b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        res.z = a.z.compareTo(b.z) == 0;
        res.w = a.w.compareTo(b.w) == 0;
        return res;
    }

    public static Vec4bool equal__(final Vec4us a, final Vec4us b) {
        return equal(a, b, new Vec4bool());
    }

    public static Vec4bool equal(final Vec4us a, final Vec4us b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) == 0;
        res.y = a.y.compareTo(b.y) == 0;
        res.z = a.z.compareTo(b.z) == 0;
        res.w = a.w.compareTo(b.w) == 0;
        return res;
    }

    public static Vec4b equal_(final Vec4b a, final Vec4b b) {
        return equal(a, b, new Vec4b());
    }

    public static Vec4b equal(final Vec4b a, final Vec4b b, final Vec4b res) {
        res.x = (byte) (a.x == b.x ? 1 : 0);
        res.y = (byte) (a.y == b.y ? 1 : 0);
        res.z = (byte) (a.z == b.z ? 1 : 0);
        res.w = (byte) (a.w == b.w ? 1 : 0);
        return res;
    }

    public static Vec4d equal_(final Vec4d a, final Vec4d b) {
        return equal(a, b, new Vec4d());
    }

    public static Vec4d equal(final Vec4d a, final Vec4d b, final Vec4d res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        res.z = a.z == b.z ? 1 : 0;
        res.w = a.w == b.w ? 1 : 0;
        return res;
    }

    public static Vec4 equal_(final Vec4 a, final Vec4 b) {
        return equal(a, b, new Vec4());
    }

    public static Vec4 equal(final Vec4 a, final Vec4 b, final Vec4 res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        res.z = a.z == b.z ? 1 : 0;
        res.w = a.w == b.w ? 1 : 0;
        return res;
    }

    public static Vec4i equal_(final Vec4i a, final Vec4i b) {
        return equal(a, b, new Vec4i());
    }

    public static Vec4i equal(final Vec4i a, final Vec4i b, final Vec4i res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        res.z = a.z == b.z ? 1 : 0;
        res.w = a.w == b.w ? 1 : 0;
        return res;
    }

    public static Vec4l equal_(final Vec4l a, final Vec4l b) {
        return equal(a, b, new Vec4l());
    }

    public static Vec4l equal(final Vec4l a, final Vec4l b, final Vec4l res) {
        res.x = a.x == b.x ? 1 : 0;
        res.y = a.y == b.y ? 1 : 0;
        res.z = a.z == b.z ? 1 : 0;
        res.w = a.w == b.w ? 1 : 0;
        return res;
    }

    public static Vec4s equal_(final Vec4s a, final Vec4s b) {
        return equal(a, b, new Vec4s());
    }

    public static Vec4s equal(final Vec4s a, final Vec4s b, final Vec4s res) {
        res.x = (short) (a.x == b.x ? 1 : 0);
        res.y = (short) (a.y == b.y ? 1 : 0);
        res.z = (short) (a.z == b.z ? 1 : 0);
        res.w = (short) (a.w == b.w ? 1 : 0);
        return res;
    }

    public static Vec4ub equal_(final Vec4ub a, final Vec4ub b) {
        return equal(a, b, new Vec4ub());
    }

    public static Vec4ub equal(final Vec4ub a, final Vec4ub b, final Vec4ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) == 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) == 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) == 0 ? 1 : 0);
        res.w.value = (byte) (a.w.compareTo(b.w) == 0 ? 1 : 0);
        return res;
    }

    public static Vec4ui equal_(final Vec4ui a, final Vec4ui b) {
        return equal(a, b, new Vec4ui());
    }

    public static Vec4ui equal(final Vec4ui a, final Vec4ui b, final Vec4ui res) {
        res.x.value = a.x.compareTo(b.x) == 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) == 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) == 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) == 0 ? 1 : 0;
        return res;
    }

    public static Vec4ul equal_(final Vec4ul a, final Vec4ul b) {
        return equal(a, b, new Vec4ul());
    }

    public static Vec4ul equal(final Vec4ul a, final Vec4ul b, final Vec4ul res) {
        res.x.value = a.x.compareTo(b.x) == 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) == 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) == 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) == 0 ? 1 : 0;
        return res;
    }

    public static Vec4us equal_(final Vec4us a, final Vec4us b) {
        return equal(a, b, new Vec4us());
    }

    public static Vec4us equal(final Vec4us a, final Vec4us b, final Vec4us res) {
        res.x.value = (short) (a.x.compareTo(b.x) == 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) == 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) == 0 ? 1 : 0);
        res.w.value = (short) (a.w.compareTo(b.w) == 0 ? 1 : 0);
        return res;
    }

    // notEqual -----------------------------------------------------------------------------------------------------
    public static boolean notEqual(final byte a, final byte b) {
        return a != b;
    }

    public static boolean notEqual(final double a, final double b) {
        return a != b;
    }

    public static boolean notEqual(final float a, final float b) {
        return a != b;
    }

    public static boolean notEqual(final int a, final int b) {
        return a != b;
    }

    public static boolean notEqual(final long a, final long b) {
        return a != b;
    }

    public static boolean notEqual(final short a, final short b) {
        return a != b;
    }

    public static boolean notEqual(final UByte a, final UByte b) {
        return a.compareTo(b) != 0;
    }

    public static boolean notEqual(final UInt a, final UInt b) {
        return a.compareTo(b) != 0;
    }

    public static boolean notEqual(final ULong a, final ULong b) {
        return a.compareTo(b) != 0;
    }

    public static boolean notEqual(final UShort a, final UShort b) {
        return a.compareTo(b) != 0;
    }

    // notEqual (vec2) ---------------------------------------------------------
    public static Vec2bool notEqual__(final Vec2b a, final Vec2b b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2b a, final Vec2b b, final Vec2bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2d a, final Vec2d b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2d a, final Vec2d b, final Vec2bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2 a, final Vec2 b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2 a, final Vec2 b, final Vec2bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2i a, final Vec2i b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2i a, final Vec2i b, final Vec2bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2l a, final Vec2l b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2l a, final Vec2l b, final Vec2bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2s a, final Vec2s b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2s a, final Vec2s b, final Vec2bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2ub a, final Vec2ub b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2ub a, final Vec2ub b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2ui a, final Vec2ui b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2ui a, final Vec2ui b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2ul a, final Vec2ul b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2ul a, final Vec2ul b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        return res;
    }

    public static Vec2bool notEqual__(final Vec2us a, final Vec2us b) {
        return notEqual(a, b, new Vec2bool());
    }

    public static Vec2bool notEqual(final Vec2us a, final Vec2us b, final Vec2bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        return res;
    }

    public static Vec2b notEqual_(final Vec2b a, final Vec2b b) {
        return notEqual(a, b, new Vec2b());
    }

    public static Vec2b notEqual(final Vec2b a, final Vec2b b, final Vec2b res) {
        res.x = (byte) (a.x != b.x ? 1 : 0);
        res.y = (byte) (a.y != b.y ? 1 : 0);
        return res;
    }

    public static Vec2d notEqual_(final Vec2d a, final Vec2d b) {
        return notEqual(a, b, new Vec2d());
    }

    public static Vec2d notEqual(final Vec2d a, final Vec2d b, final Vec2d res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        return res;
    }

    public static Vec2 notEqual_(final Vec2 a, final Vec2 b) {
        return notEqual(a, b, new Vec2());
    }

    public static Vec2 notEqual(final Vec2 a, final Vec2 b, final Vec2 res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        return res;
    }

    public static Vec2i notEqual_(final Vec2i a, final Vec2i b) {
        return notEqual(a, b, new Vec2i());
    }

    public static Vec2i notEqual(final Vec2i a, final Vec2i b, final Vec2i res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        return res;
    }

    public static Vec2l notEqual_(final Vec2l a, final Vec2l b) {
        return notEqual(a, b, new Vec2l());
    }

    public static Vec2l notEqual(final Vec2l a, final Vec2l b, final Vec2l res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        return res;
    }

    public static Vec2s notEqual_(final Vec2s a, final Vec2s b) {
        return notEqual(a, b, new Vec2s());
    }

    public static Vec2s notEqual(final Vec2s a, final Vec2s b, final Vec2s res) {
        res.x = (short) (a.x != b.x ? 1 : 0);
        res.y = (short) (a.y != b.y ? 1 : 0);
        return res;
    }

    public static Vec2ub notEqual_(final Vec2ub a, final Vec2ub b) {
        return notEqual(a, b, new Vec2ub());
    }

    public static Vec2ub notEqual(final Vec2ub a, final Vec2ub b, final Vec2ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) != 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) != 0 ? 1 : 0);
        return res;
    }

    public static Vec2ui notEqual_(final Vec2ui a, final Vec2ui b) {
        return notEqual(a, b, new Vec2ui());
    }

    public static Vec2ui notEqual(final Vec2ui a, final Vec2ui b, final Vec2ui res) {
        res.x.value = a.x.compareTo(b.x) != 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) != 0 ? 1 : 0;
        return res;
    }

    public static Vec2ul notEqual_(final Vec2ul a, final Vec2ul b) {
        return notEqual(a, b, new Vec2ul());
    }

    public static Vec2ul notEqual(final Vec2ul a, final Vec2ul b, final Vec2ul res) {
        res.x.value = a.x.compareTo(b.x) != 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) != 0 ? 1 : 0;
        return res;
    }

    public static Vec2us notEqual_(final Vec2us a, final Vec2us b) {
        return notEqual(a, b, new Vec2us());
    }

    public static Vec2us notEqual(final Vec2us a, final Vec2us b, final Vec2us res) {
        res.x.value = (short) (a.x.compareTo(b.x) != 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) != 0 ? 1 : 0);
        return res;
    }

    // notEqual (vec3) ---------------------------------------------------------
    public static Vec3bool notEqual__(final Vec3b a, final Vec3b b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3b a, final Vec3b b, final Vec3bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3d a, final Vec3d b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3d a, final Vec3d b, final Vec3bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3 a, final Vec3 b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3 a, final Vec3 b, final Vec3bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3i a, final Vec3i b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3i a, final Vec3i b, final Vec3bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3l a, final Vec3l b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3l a, final Vec3l b, final Vec3bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3s a, final Vec3s b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3s a, final Vec3s b, final Vec3bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3ub a, final Vec3ub b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3ub a, final Vec3ub b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        res.z = a.z.compareTo(b.z) != 0;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3ui a, final Vec3ui b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3ui a, final Vec3ui b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        res.z = a.z.compareTo(b.z) != 0;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3ul a, final Vec3ul b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3ul a, final Vec3ul b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        res.z = a.z.compareTo(b.z) != 0;
        return res;
    }

    public static Vec3bool notEqual__(final Vec3us a, final Vec3us b) {
        return notEqual(a, b, new Vec3bool());
    }

    public static Vec3bool notEqual(final Vec3us a, final Vec3us b, final Vec3bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        res.z = a.z.compareTo(b.z) != 0;
        return res;
    }

    public static Vec3b notEqual_(final Vec3b a, final Vec3b b) {
        return notEqual(a, b, new Vec3b());
    }

    public static Vec3b notEqual(final Vec3b a, final Vec3b b, final Vec3b res) {
        res.x = (byte) (a.x != b.x ? 1 : 0);
        res.y = (byte) (a.y != b.y ? 1 : 0);
        res.z = (byte) (a.z != b.z ? 1 : 0);
        return res;
    }

    public static Vec3d notEqual_(final Vec3d a, final Vec3d b) {
        return notEqual(a, b, new Vec3d());
    }

    public static Vec3d notEqual(final Vec3d a, final Vec3d b, final Vec3d res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        res.z = a.z != b.z ? 1 : 0;
        return res;
    }

    public static Vec3 notEqual_(final Vec3 a, final Vec3 b) {
        return notEqual(a, b, new Vec3());
    }

    public static Vec3 notEqual(final Vec3 a, final Vec3 b, final Vec3 res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        res.z = a.z != b.z ? 1 : 0;
        return res;
    }

    public static Vec3i notEqual_(final Vec3i a, final Vec3i b) {
        return notEqual(a, b, new Vec3i());
    }

    public static Vec3i notEqual(final Vec3i a, final Vec3i b, final Vec3i res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        res.z = a.z != b.z ? 1 : 0;
        return res;
    }

    public static Vec3l notEqual_(final Vec3l a, final Vec3l b) {
        return notEqual(a, b, new Vec3l());
    }

    public static Vec3l notEqual(final Vec3l a, final Vec3l b, final Vec3l res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        res.z = a.z != b.z ? 1 : 0;
        return res;
    }

    public static Vec3s notEqual_(final Vec3s a, final Vec3s b) {
        return notEqual(a, b, new Vec3s());
    }

    public static Vec3s notEqual(final Vec3s a, final Vec3s b, final Vec3s res) {
        res.x = (short) (a.x != b.x ? 1 : 0);
        res.y = (short) (a.y != b.y ? 1 : 0);
        res.z = (short) (a.z != b.z ? 1 : 0);
        return res;
    }

    public static Vec3ub notEqual_(final Vec3ub a, final Vec3ub b) {
        return notEqual(a, b, new Vec3ub());
    }

    public static Vec3ub notEqual(final Vec3ub a, final Vec3ub b, final Vec3ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) != 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) != 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) != 0 ? 1 : 0);
        return res;
    }

    public static Vec3ui notEqual_(final Vec3ui a, final Vec3ui b) {
        return notEqual(a, b, new Vec3ui());
    }

    public static Vec3ui notEqual(final Vec3ui a, final Vec3ui b, final Vec3ui res) {
        res.x.value = a.x.compareTo(b.x) != 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) != 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) != 0 ? 1 : 0;
        return res;
    }

    public static Vec3ul notEqual_(final Vec3ul a, final Vec3ul b) {
        return notEqual(a, b, new Vec3ul());
    }

    public static Vec3ul notEqual(final Vec3ul a, final Vec3ul b, final Vec3ul res) {
        res.x.value = a.x.compareTo(b.x) != 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) != 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) != 0 ? 1 : 0;
        return res;
    }

    public static Vec3us notEqual_(final Vec3us a, final Vec3us b) {
        return notEqual(a, b, new Vec3us());
    }

    public static Vec3us notEqual(final Vec3us a, final Vec3us b, final Vec3us res) {
        res.x.value = (short) (a.x.compareTo(b.x) != 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) != 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) != 0 ? 1 : 0);
        return res;
    }

    // notEqual (vec4) ---------------------------------------------------------
    public static Vec4bool notEqual__(final Vec4b a, final Vec4b b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4b a, final Vec4b b, final Vec4bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        res.w = a.w != b.w;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4d a, final Vec4d b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4d a, final Vec4d b, final Vec4bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        res.w = a.w != b.w;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4 a, final Vec4 b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4 a, final Vec4 b, final Vec4bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        res.w = a.w != b.w;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4i a, final Vec4i b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4i a, final Vec4i b, final Vec4bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        res.w = a.w != b.w;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4l a, final Vec4l b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4l a, final Vec4l b, final Vec4bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        res.w = a.w != b.w;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4s a, final Vec4s b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4s a, final Vec4s b, final Vec4bool res) {
        res.x = a.x != b.x;
        res.y = a.y != b.y;
        res.z = a.z != b.z;
        res.w = a.w != b.w;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4ub a, final Vec4ub b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4ub a, final Vec4ub b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        res.z = a.z.compareTo(b.z) != 0;
        res.w = a.w.compareTo(b.w) != 0;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4ui a, final Vec4ui b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4ui a, final Vec4ui b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        res.z = a.z.compareTo(b.z) != 0;
        res.w = a.w.compareTo(b.w) != 0;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4ul a, final Vec4ul b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4ul a, final Vec4ul b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        res.z = a.z.compareTo(b.z) != 0;
        res.w = a.w.compareTo(b.w) != 0;
        return res;
    }

    public static Vec4bool notEqual__(final Vec4us a, final Vec4us b) {
        return notEqual(a, b, new Vec4bool());
    }

    public static Vec4bool notEqual(final Vec4us a, final Vec4us b, final Vec4bool res) {
        res.x = a.x.compareTo(b.x) != 0;
        res.y = a.y.compareTo(b.y) != 0;
        res.z = a.z.compareTo(b.z) != 0;
        res.w = a.w.compareTo(b.w) != 0;
        return res;
    }

    public static Vec4b notEqual_(final Vec4b a, final Vec4b b) {
        return notEqual(a, b, new Vec4b());
    }

    public static Vec4b notEqual(final Vec4b a, final Vec4b b, final Vec4b res) {
        res.x = (byte) (a.x != b.x ? 1 : 0);
        res.y = (byte) (a.y != b.y ? 1 : 0);
        res.z = (byte) (a.z != b.z ? 1 : 0);
        res.w = (byte) (a.w != b.w ? 1 : 0);
        return res;
    }

    public static Vec4d notEqual_(final Vec4d a, final Vec4d b) {
        return notEqual(a, b, new Vec4d());
    }

    public static Vec4d notEqual(final Vec4d a, final Vec4d b, final Vec4d res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        res.z = a.z != b.z ? 1 : 0;
        res.w = a.w != b.w ? 1 : 0;
        return res;
    }

    public static Vec4 notEqual_(final Vec4 a, final Vec4 b) {
        return notEqual(a, b, new Vec4());
    }

    public static Vec4 notEqual(final Vec4 a, final Vec4 b, final Vec4 res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        res.z = a.z != b.z ? 1 : 0;
        res.w = a.w != b.w ? 1 : 0;
        return res;
    }

    public static Vec4i notEqual_(final Vec4i a, final Vec4i b) {
        return notEqual(a, b, new Vec4i());
    }

    public static Vec4i notEqual(final Vec4i a, final Vec4i b, final Vec4i res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        res.z = a.z != b.z ? 1 : 0;
        res.w = a.w != b.w ? 1 : 0;
        return res;
    }

    public static Vec4l notEqual_(final Vec4l a, final Vec4l b) {
        return notEqual(a, b, new Vec4l());
    }

    public static Vec4l notEqual(final Vec4l a, final Vec4l b, final Vec4l res) {
        res.x = a.x != b.x ? 1 : 0;
        res.y = a.y != b.y ? 1 : 0;
        res.z = a.z != b.z ? 1 : 0;
        res.w = a.w != b.w ? 1 : 0;
        return res;
    }

    public static Vec4s notEqual_(final Vec4s a, final Vec4s b) {
        return notEqual(a, b, new Vec4s());
    }

    public static Vec4s notEqual(final Vec4s a, final Vec4s b, final Vec4s res) {
        res.x = (short) (a.x != b.x ? 1 : 0);
        res.y = (short) (a.y != b.y ? 1 : 0);
        res.z = (short) (a.z != b.z ? 1 : 0);
        res.w = (short) (a.w != b.w ? 1 : 0);
        return res;
    }

    public static Vec4ub notEqual_(final Vec4ub a, final Vec4ub b) {
        return notEqual(a, b, new Vec4ub());
    }

    public static Vec4ub notEqual(final Vec4ub a, final Vec4ub b, final Vec4ub res) {
        res.x.value = (byte) (a.x.compareTo(b.x) != 0 ? 1 : 0);
        res.y.value = (byte) (a.y.compareTo(b.y) != 0 ? 1 : 0);
        res.z.value = (byte) (a.z.compareTo(b.z) != 0 ? 1 : 0);
        res.w.value = (byte) (a.w.compareTo(b.w) != 0 ? 1 : 0);
        return res;
    }

    public static Vec4ui notEqual_(final Vec4ui a, final Vec4ui b) {
        return notEqual(a, b, new Vec4ui());
    }

    public static Vec4ui notEqual(final Vec4ui a, final Vec4ui b, final Vec4ui res) {
        res.x.value = a.x.compareTo(b.x) != 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) != 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) != 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) != 0 ? 1 : 0;
        return res;
    }

    public static Vec4ul notEqual_(final Vec4ul a, final Vec4ul b) {
        return notEqual(a, b, new Vec4ul());
    }

    public static Vec4ul notEqual(final Vec4ul a, final Vec4ul b, final Vec4ul res) {
        res.x.value = a.x.compareTo(b.x) != 0 ? 1 : 0;
        res.y.value = a.y.compareTo(b.y) != 0 ? 1 : 0;
        res.z.value = a.z.compareTo(b.z) != 0 ? 1 : 0;
        res.w.value = a.w.compareTo(b.w) != 0 ? 1 : 0;
        return res;
    }

    public static Vec4us notEqual_(final Vec4us a, final Vec4us b) {
        return notEqual(a, b, new Vec4us());
    }

    public static Vec4us notEqual(final Vec4us a, final Vec4us b, final Vec4us res) {
        res.x.value = (short) (a.x.compareTo(b.x) != 0 ? 1 : 0);
        res.y.value = (short) (a.y.compareTo(b.y) != 0 ? 1 : 0);
        res.z.value = (short) (a.z.compareTo(b.z) != 0 ? 1 : 0);
        res.w.value = (short) (a.w.compareTo(b.w) != 0 ? 1 : 0);
        return res;
    }
}
