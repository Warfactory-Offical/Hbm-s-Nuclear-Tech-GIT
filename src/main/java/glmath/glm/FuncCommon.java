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
 * @author elect
 */
abstract class FuncCommon extends FuncRelational {

    public static int MAX_ULPS = 2;

    // abs ----------------------------------------------------------------------------------------------------------
    public static float abs(final float s) {
        return Math.abs(s);
    }

    public static double abs(final double s) {
        return Math.abs(s);
    }

    public static int abs(final int s) {
        return Math.abs(s);
    }

    public static Vec2 abs_(final Vec2 v) {
        return abs(v, new Vec2());
    }

    public static Vec2 abs(final Vec2 v, final Vec2 res) {
        return res.set(abs(v.x), abs(v.y));
    }

    public static Vec2d abs_(final Vec2d v) {
        return abs(v, new Vec2d());
    }

    public static Vec2d abs(final Vec2d v, final Vec2d res) {
        return res.set(abs(v.x), abs(v.y));
    }

    public static Vec2i abs_(final Vec2i v) {
        return abs(v, new Vec2i());
    }

    public static Vec2i abs(final Vec2i v, final Vec2i res) {
        return res.set(abs(v.x), abs(v.y));
    }

    public static Vec3 abs_(final Vec3 v) {
        return abs(v, new Vec3());
    }

    public static Vec3 abs(final Vec3 v, final Vec3 res) {
        return res.set(abs(v.x), abs(v.y), abs(v.z));
    }

    public static Vec3d abs_(final Vec3d v) {
        return abs(v, new Vec3d());
    }

    public static Vec3d abs(final Vec3d v, final Vec3d res) {
        return res.set(abs(v.x), abs(v.y), abs(v.z));
    }

    public static Vec3i abs_(final Vec3i v) {
        return abs(v, new Vec3i());
    }

    public static Vec3i abs(final Vec3i v, final Vec3i res) {
        return res.set(abs(v.x), abs(v.y), abs(v.z));
    }

    public static Vec4 abs_(final Vec4 v) {
        return abs(v, new Vec4());
    }

    public static Vec4 abs(final Vec4 v, final Vec4 res) {
        return res.set(abs(v.x), abs(v.y), abs(v.z), abs(v.w));
    }

    public static Vec4d abs_(final Vec4d v) {
        return abs(v, new Vec4d());
    }

    public static Vec4d abs(final Vec4d v, final Vec4d res) {
        return res.set(abs(v.x), abs(v.y), abs(v.z), abs(v.w));
    }

    public static Vec4i abs_(final Vec4i v) {
        return abs(v, new Vec4i());
    }

    public static Vec4i abs(final Vec4i v, final Vec4i res) {
        return res.set(abs(v.x), abs(v.y), abs(v.z), abs(v.w));
    }

    // ceil ---------------------------------------------------------------------------------------------------------
    public static float ceil(final float s) {
        return (float) Math.ceil(s);
    }

    public static double ceil(final double s) {
        return Math.ceil(s);
    }

    public static Vec2 ceil_(final Vec2 v) {
        return ceil(v, new Vec2());
    }

    public static Vec2 ceil(final Vec2 v, final Vec2 res) {
        return res.set(ceil(v.x), ceil(v.y));
    }

    public static Vec2d ceil_(final Vec2d v) {
        return ceil(v, new Vec2d());
    }

    public static Vec2d ceil(final Vec2d v, final Vec2d res) {
        return res.set(ceil(v.x), ceil(v.y));
    }

    public static Vec3 ceil_(final Vec3 v) {
        return ceil(v, new Vec3());
    }

    public static Vec3 ceil(final Vec3 v, final Vec3 res) {
        return res.set(ceil(v.x), ceil(v.y), ceil(v.z));
    }

    public static Vec3d ceil_(final Vec3d v) {
        return ceil(v, new Vec3d());
    }

    public static Vec3d ceil(final Vec3d v, final Vec3d res) {
        return res.set(ceil(v.x), ceil(v.y), ceil(v.z));
    }

    public static Vec4 ceil_(final Vec4 v) {
        return ceil(v, new Vec4());
    }

    public static Vec4 ceil(final Vec4 v, final Vec4 res) {
        return res.set(ceil(v.x), ceil(v.y), ceil(v.z), ceil(v.w));
    }

    public static Vec4d ceil_(final Vec4d v) {
        return ceil(v, new Vec4d());
    }

    public static Vec4d ceil(final Vec4d v, final Vec4d res) {
        return res.set(ceil(v.x), ceil(v.y), ceil(v.z), ceil(v.w));
    }

    // clamp --------------------------------------------------------------------------------------------------------
    public static float clamp(final float s, final float min, final float max) {
        return min(max(s, min), max);
    }

    public static double clamp(final double s, final double min, final double max) {
        return min(max(s, min), max);
    }

    public static int clamp(final int s, final int min, final int max) {
        return min(max(s, min), max);
    }

    public static long clamp(final long s, final long min, final long max) {
        return min(max(s, min), max);
    }

    public static UByte clamp(final UByte s, final UByte min, final UByte max) {
        return min(max(s, min), max);
    }

    public static UInt clamp(final UInt s, final UInt min, final UInt max) {
        return min(max(s, min), max);
    }

    public static ULong clamp(final ULong s, final ULong min, final ULong max) {
        return min(max(s, min), max);
    }

    public static UShort clamp(final UShort s, final UShort min, final UShort max) {
        return min(max(s, min), max);
    }

    // clamp(vec2, scalar) -----------------------------------------------------
    public static Vec2 clamp_(final Vec2 v, final float min, final float max) {
        return clamp(v, min, max, new Vec2());
    }

    public static Vec2 clamp(final Vec2 v, final float min, final float max, final Vec2 res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2b clamp_(final Vec2b v, final byte min, final byte max) {
        return clamp(v, min, max, new Vec2b());
    }

    public static Vec2b clamp(final Vec2b v, final byte min, final byte max, final Vec2b res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2d clamp_(final Vec2d v, final double min, final double max) {
        return clamp(v, min, max, new Vec2d());
    }

    public static Vec2d clamp(final Vec2d v, final double min, final double max, final Vec2d res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2i clamp_(final Vec2i v, final int min, final int max) {
        return clamp(v, min, max, new Vec2i());
    }

    public static Vec2i clamp(final Vec2i v, final int min, final int max, final Vec2i res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2l clamp_(final Vec2l v, final long min, final long max) {
        return clamp(v, min, max, new Vec2l());
    }

    public static Vec2l clamp(final Vec2l v, final long min, final long max, final Vec2l res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2s clamp_(final Vec2s v, final short min, final short max) {
        return clamp(v, min, max, new Vec2s());
    }

    public static Vec2s clamp(final Vec2s v, final short min, final short max, final Vec2s res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2ub clamp_(final Vec2ub v, final UByte min, final UByte max) {
        return clamp(v, min, max, new Vec2ub());
    }

    public static Vec2ub clamp(final Vec2ub v, final UByte min, final UByte max, final Vec2ub res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2ui clamp_(final Vec2ui v, final UInt min, final UInt max) {
        return clamp(v, min, max, new Vec2ui());
    }

    public static Vec2ui clamp(final Vec2ui v, final UInt min, final UInt max, final Vec2ui res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2ul clamp_(final Vec2ul v, final ULong min, final ULong max) {
        return clamp(v, min, max, new Vec2ul());
    }

    public static Vec2ul clamp(final Vec2ul v, final ULong min, final ULong max, final Vec2ul res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    public static Vec2us clamp_(final Vec2us v, final UShort min, final UShort max) {
        return clamp(v, min, max, new Vec2us());
    }

    public static Vec2us clamp(final Vec2us v, final UShort min, final UShort max, final Vec2us res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max));
    }

    // clamp(vec2, vec2) -------------------------------------------------------
    public static Vec2 clamp_(final Vec2 v, final Vec2 min, final Vec2 max) {
        return clamp(v, min, max, new Vec2());
    }

    public static Vec2 clamp(final Vec2 v, final Vec2 min, final Vec2 max, final Vec2 res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2b clamp_(final Vec2b v, final Vec2b min, final Vec2b max) {
        return clamp(v, min, max, new Vec2b());
    }

    public static Vec2b clamp(final Vec2b v, final Vec2b min, final Vec2b max, final Vec2b res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2d clamp_(final Vec2d v, final Vec2d min, final Vec2d max) {
        return clamp(v, min, max, new Vec2d());
    }

    public static Vec2d clamp(final Vec2d v, final Vec2d min, final Vec2d max, final Vec2d res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2i clamp_(final Vec2i v, final Vec2i min, final Vec2i max) {
        return clamp(v, min, max, new Vec2i());
    }

    public static Vec2i clamp(final Vec2i v, final Vec2i min, final Vec2i max, final Vec2i res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2l clamp_(final Vec2l v, final Vec2l min, final Vec2l max) {
        return clamp(v, min, max, new Vec2l());
    }

    public static Vec2l clamp(final Vec2l v, final Vec2l min, final Vec2l max, final Vec2l res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2s clamp_(final Vec2s v, final Vec2s min, final Vec2s max) {
        return clamp(v, min, max, new Vec2s());
    }

    public static Vec2s clamp(final Vec2s v, final Vec2s min, final Vec2s max, final Vec2s res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2ub clamp_(final Vec2ub v, final Vec2ub min, final Vec2ub max) {
        return clamp(v, min, max, new Vec2ub());
    }

    public static Vec2ub clamp(final Vec2ub v, final Vec2ub min, final Vec2ub max, final Vec2ub res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2ui clamp_(final Vec2ui v, final Vec2ui min, final Vec2ui max) {
        return clamp(v, min, max, new Vec2ui());
    }

    public static Vec2ui clamp(final Vec2ui v, final Vec2ui min, final Vec2ui max, final Vec2ui res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2ul clamp_(final Vec2ul v, final Vec2ul min, final Vec2ul max) {
        return clamp(v, min, max, new Vec2ul());
    }

    public static Vec2ul clamp(final Vec2ul v, final Vec2ul min, final Vec2ul max, final Vec2ul res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    public static Vec2us clamp_(final Vec2us v, final Vec2us min, final Vec2us max) {
        return clamp(v, min, max, new Vec2us());
    }

    public static Vec2us clamp(final Vec2us v, final Vec2us min, final Vec2us max, final Vec2us res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y));
    }

    // clamp(vec3, scalar) -----------------------------------------------------
    public static Vec3 clamp_(final Vec3 v, final float min, final float max) {
        return clamp(v, min, max, new Vec3());
    }

    public static Vec3 clamp(final Vec3 v, final float min, final float max, final Vec3 res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3b clamp_(final Vec3b v, final byte min, final byte max) {
        return clamp(v, min, max, new Vec3b());
    }

    public static Vec3b clamp(final Vec3b v, final byte min, final byte max, final Vec3b res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3d clamp_(final Vec3d v, final double min, final double max) {
        return clamp(v, min, max, new Vec3d());
    }

    public static Vec3d clamp(final Vec3d v, final double min, final double max, final Vec3d res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3i clamp_(final Vec3i v, final int min, final int max) {
        return clamp(v, min, max, new Vec3i());
    }

    public static Vec3i clamp(final Vec3i v, final int min, final int max, final Vec3i res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3l clamp_(final Vec3l v, final long min, final long max) {
        return clamp(v, min, max, new Vec3l());
    }

    public static Vec3l clamp(final Vec3l v, final long min, final long max, final Vec3l res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3s clamp_(final Vec3s v, final short min, final short max) {
        return clamp(v, min, max, new Vec3s());
    }

    public static Vec3s clamp(final Vec3s v, final short min, final short max, final Vec3s res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3ub clamp_(final Vec3ub v, final UByte min, final UByte max) {
        return clamp(v, min, max, new Vec3ub());
    }

    public static Vec3ub clamp(final Vec3ub v, final UByte min, final UByte max, final Vec3ub res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3ui clamp_(final Vec3ui v, final UInt min, final UInt max) {
        return clamp(v, min, max, new Vec3ui());
    }

    public static Vec3ui clamp(final Vec3ui v, final UInt min, final UInt max, final Vec3ui res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3ul clamp_(final Vec3ul v, final ULong min, final ULong max) {
        return clamp(v, min, max, new Vec3ul());
    }

    public static Vec3ul clamp(final Vec3ul v, final ULong min, final ULong max, final Vec3ul res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    public static Vec3us clamp_(final Vec3us v, final UShort min, final UShort max) {
        return clamp(v, min, max, new Vec3us());
    }

    public static Vec3us clamp(final Vec3us v, final UShort min, final UShort max, final Vec3us res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max));
    }

    // clamp(vec3, vec3) -------------------------------------------------------
    public static Vec3 clamp_(final Vec3 v, final Vec3 min, final Vec3 max) {
        return clamp(v, min, max, new Vec3());
    }

    public static Vec3 clamp(final Vec3 v, final Vec3 min, final Vec3 max, final Vec3 res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3b clamp_(final Vec3b v, final Vec3b min, final Vec3b max) {
        return clamp(v, min, max, new Vec3b());
    }

    public static Vec3b clamp(final Vec3b v, final Vec3b min, final Vec3b max, final Vec3b res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3d clamp_(final Vec3d v, final Vec3d min, final Vec3d max) {
        return clamp(v, min, max, new Vec3d());
    }

    public static Vec3d clamp(final Vec3d v, final Vec3d min, final Vec3d max, final Vec3d res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3i clamp_(final Vec3i v, final Vec3i min, final Vec3i max) {
        return clamp(v, min, max, new Vec3i());
    }

    public static Vec3i clamp(final Vec3i v, final Vec3i min, final Vec3i max, final Vec3i res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3l clamp_(final Vec3l v, final Vec3l min, final Vec3l max) {
        return clamp(v, min, max, new Vec3l());
    }

    public static Vec3l clamp(final Vec3l v, final Vec3l min, final Vec3l max, final Vec3l res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3s clamp_(final Vec3s v, final Vec3s min, final Vec3s max) {
        return clamp(v, min, max, new Vec3s());
    }

    public static Vec3s clamp(final Vec3s v, final Vec3s min, final Vec3s max, final Vec3s res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3ub clamp_(final Vec3ub v, final Vec3ub min, final Vec3ub max) {
        return clamp(v, min, max, new Vec3ub());
    }

    public static Vec3ub clamp(final Vec3ub v, final Vec3ub min, final Vec3ub max, final Vec3ub res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3ui clamp_(final Vec3ui v, final Vec3ui min, final Vec3ui max) {
        return clamp(v, min, max, new Vec3ui());
    }

    public static Vec3ui clamp(final Vec3ui v, final Vec3ui min, final Vec3ui max, final Vec3ui res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3ul clamp_(final Vec3ul v, final Vec3ul min, final Vec3ul max) {
        return clamp(v, min, max, new Vec3ul());
    }

    public static Vec3ul clamp(final Vec3ul v, final Vec3ul min, final Vec3ul max, final Vec3ul res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    public static Vec3us clamp_(final Vec3us v, final Vec3us min, final Vec3us max) {
        return clamp(v, min, max, new Vec3us());
    }

    public static Vec3us clamp(final Vec3us v, final Vec3us min, final Vec3us max, final Vec3us res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z));
    }

    // max(vec4, scalar) -------------------------------------------------------
    public static Vec4 clamp_(final Vec4 v, final float min, final float max) {
        return clamp(v, min, max, new Vec4());
    }

    public static Vec4 clamp(final Vec4 v, final float min, final float max, final Vec4 res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4b clamp_(final Vec4b v, final byte min, final byte max) {
        return clamp(v, min, max, new Vec4b());
    }

    public static Vec4b clamp(final Vec4b v, final byte min, final byte max, final Vec4b res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4d clamp_(final Vec4d v, final double min, final double max) {
        return clamp(v, min, max, new Vec4d());
    }

    public static Vec4d clamp(final Vec4d v, final double min, final double max, final Vec4d res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4i clamp_(final Vec4i v, final int min, final int max) {
        return clamp(v, min, max, new Vec4i());
    }

    public static Vec4i clamp(final Vec4i v, final int min, final int max, final Vec4i res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4l clamp_(final Vec4l v, final long min, final long max) {
        return clamp(v, min, max, new Vec4l());
    }

    public static Vec4l clamp(final Vec4l v, final long min, final long max, final Vec4l res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4s clamp_(final Vec4s v, final short min, final short max) {
        return clamp(v, min, max, new Vec4s());
    }

    public static Vec4s clamp(final Vec4s v, final short min, final short max, final Vec4s res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4ub clamp_(final Vec4ub v, final UByte min, final UByte max) {
        return clamp(v, min, max, new Vec4ub());
    }

    public static Vec4ub clamp(final Vec4ub v, final UByte min, final UByte max, final Vec4ub res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4ui clamp_(final Vec4ui v, final UInt min, final UInt max) {
        return clamp(v, min, max, new Vec4ui());
    }

    public static Vec4ui clamp(final Vec4ui v, final UInt min, final UInt max, final Vec4ui res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4ul clamp_(final Vec4ul v, final ULong min, final ULong max) {
        return clamp(v, min, max, new Vec4ul());
    }

    public static Vec4ul clamp(final Vec4ul v, final ULong min, final ULong max, final Vec4ul res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    public static Vec4us clamp_(final Vec4us v, final UShort min, final UShort max) {
        return clamp(v, min, max, new Vec4us());
    }

    public static Vec4us clamp(final Vec4us v, final UShort min, final UShort max, final Vec4us res) {
        return res.set(clamp(v.x, min, max), clamp(v.y, min, max), clamp(v.z, min, max), clamp(v.w, min, max));
    }

    // clamp(vec4, vec4) -------------------------------------------------------
    public static Vec4 clamp_(final Vec4 v, final Vec4 min, final Vec4 max) {
        return clamp(v, min, max, new Vec4());
    }

    public static Vec4 clamp(final Vec4 v, final Vec4 min, final Vec4 max, final Vec4 res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    public static Vec4b clamp_(final Vec4b v, final Vec4b min, final Vec4b max) {
        return clamp(v, min, max, new Vec4b());
    }

    public static Vec4b clamp(final Vec4b v, final Vec4b min, final Vec4b max, final Vec4b res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    public static Vec4d clamp_(final Vec4d v, final Vec4d min, final Vec4d max) {
        return clamp(v, min, max, new Vec4d());
    }

    public static Vec4d clamp(final Vec4d v, final Vec4d min, final Vec4d max, final Vec4d res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    public static Vec4i clamp_(final Vec4i v, final Vec4i min, final Vec4i max) {
        return clamp(v, min, max, new Vec4i());
    }

    public static Vec4i clamp(final Vec4i v, final Vec4i min, final Vec4i max, final Vec4i res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    public static Vec4l clamp_(final Vec4l v, final Vec4l min, final Vec4l max) {
        return clamp(v, min, max, new Vec4l());
    }

    public static Vec4l clamp(final Vec4l v, final Vec4l mix, final Vec4l max, final Vec4l res) {
        return res.set(clamp(v.x, mix.x, max.x), clamp(v.y, mix.y, max.y), clamp(v.z, mix.z, max.z), clamp(v.w, mix.w, max.w));
    }

    public static Vec4s clamp_(final Vec4s v, final Vec4s min, final Vec4s max) {
        return clamp(v, min, max, new Vec4s());
    }

    public static Vec4s clamp(final Vec4s v, final Vec4s min, final Vec4s max, final Vec4s res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    public static Vec4ub clamp_(final Vec4ub v, final Vec4ub min, final Vec4ub max) {
        return clamp(v, min, max, new Vec4ub());
    }

    public static Vec4ub clamp(final Vec4ub v, final Vec4ub min, final Vec4ub max, final Vec4ub res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    public static Vec4ui clamp_(final Vec4ui v, final Vec4ui min, final Vec4ui max) {
        return clamp(v, min, max, new Vec4ui());
    }

    public static Vec4ui clamp(final Vec4ui v, final Vec4ui min, final Vec4ui max, final Vec4ui res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    public static Vec4ul clamp_(final Vec4ul v, final Vec4ul min, final Vec4ul max) {
        return clamp(v, min, max, new Vec4ul());
    }

    public static Vec4ul clamp(final Vec4ul v, final Vec4ul min, final Vec4ul max, final Vec4ul res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    public static Vec4us clamp_(final Vec4us v, final Vec4us min, final Vec4us max) {
        return clamp(v, min, max, new Vec4us());
    }

    public static Vec4us clamp(final Vec4us v, final Vec4us min, final Vec4us max, final Vec4us res) {
        return res.set(clamp(v.x, min.x, max.x), clamp(v.y, min.y, max.y), clamp(v.z, min.z, max.z), clamp(v.w, min.w, max.w));
    }

    // floatBitsToInt -----------------------------------------------------------------------------------------------
    public static int floatBitsToInt(final float s) {
        return Float.floatToIntBits(s);
    }

    public static Vec2i floatBitsToInt_(final Vec2 v) {
        return floatBitsToInt(v, new Vec2i());
    }

    public static Vec2i floatBitsToInt(final Vec2 v, final Vec2i res) {
        res.x = Float.floatToIntBits(v.x);
        res.y = Float.floatToIntBits(v.y);
        return res;
    }

    public static Vec3i floatBitsToInt_(final Vec3 v) {
        return floatBitsToInt(v, new Vec3i());
    }

    public static Vec3i floatBitsToInt(final Vec3 v, final Vec3i res) {
        res.x = Float.floatToIntBits(v.x);
        res.y = Float.floatToIntBits(v.y);
        res.z = Float.floatToIntBits(v.z);
        return res;
    }

    public static Vec4i floatBitsToInt_(final Vec4 v) {
        return floatBitsToInt(v, new Vec4i());
    }

    public static Vec4i floatBitsToInt(final Vec4 v, final Vec4i res) {
        res.x = Float.floatToIntBits(v.x);
        res.y = Float.floatToIntBits(v.y);
        res.z = Float.floatToIntBits(v.z);
        res.w = Float.floatToIntBits(v.w);
        return res;
    }

    // floatBitsToUInt -----------------------------------------------------------------------------------------------
    public static int floatBitsToUInt(final float s) {
        if (s < 0) {
            throw new NumberFormatException("Value is out of range : " + s);
        }
        return (int) (Double.doubleToLongBits(s) & UInt.MAX_VALUE);
    }

    public static Vec2i floatBitsToUInt_(final Vec2 v) {
        return floatBitsToUInt(v, new Vec2i());
    }

    public static Vec2i floatBitsToUInt(final Vec2 v, final Vec2i res) {
        if (v.x < 0 || v.y < 0) {
            throw new NumberFormatException("Value is out of range : ");
        }
        res.x = (int) (Double.doubleToLongBits(v.x) & UInt.MAX_VALUE);
        res.y = (int) (Double.doubleToLongBits(v.y) & UInt.MAX_VALUE);
        return res;
    }

    public static Vec3i floatBitsToUInt_(final Vec3 v) {
        return floatBitsToUInt(v, new Vec3i());
    }

    public static Vec3i floatBitsToUInt(final Vec3 v, final Vec3i res) {
        if (v.x < 0 || v.y < 0 || v.z < 0) {
            throw new NumberFormatException("Value is out of range : "); 
        }
        res.x = (int) (Double.doubleToLongBits(v.x) & UInt.MAX_VALUE);
        res.y = (int) (Double.doubleToLongBits(v.y) & UInt.MAX_VALUE);
        res.z = (int) (Double.doubleToLongBits(v.z) & UInt.MAX_VALUE);
        return res;
    }

    public static Vec4i floatBitsToUInt_(final Vec4 v) {
        return floatBitsToUInt(v, new Vec4i());
    }

    public static Vec4i floatBitsToUInt(final Vec4 v, final Vec4i res) {
        if (v.x < 0 || v.y < 0 || v.z < 0 || v.w < 0) {
            throw new NumberFormatException("Value is out of range : ");
        }
        res.x = (int) (Double.doubleToLongBits(v.x) & UInt.MAX_VALUE);
        res.y = (int) (Double.doubleToLongBits(v.y) & UInt.MAX_VALUE);
        res.z = (int) (Double.doubleToLongBits(v.z) & UInt.MAX_VALUE);
        res.w = (int) (Double.doubleToLongBits(v.w) & UInt.MAX_VALUE);
        return res;
    }

    // intBitsToFloat -----------------------------------------------------------------------------------------------
    public static float intBitsToFloat(final int s) {
        return Float.intBitsToFloat(s);
    }

    public static Vec2 intBitsToFloat_(final Vec2i v) {
        return intBitsToFloat(v, new Vec2());
    }

    public static Vec2 intBitsToFloat(final Vec2i v, final Vec2 res) {
        res.x = Float.intBitsToFloat(v.x);
        res.y = Float.intBitsToFloat(v.y);
        return res;
    }

    public static Vec3 intBitsToFloat_(final Vec3i v) {
        return intBitsToFloat(v, new Vec3());
    }

    public static Vec3 intBitsToFloat(final Vec3i v, final Vec3 res) {
        res.x = Float.intBitsToFloat(v.x);
        res.y = Float.intBitsToFloat(v.y);
        res.z = Float.intBitsToFloat(v.z);
        return res;
    }

    public static Vec4 intBitsToFloat_(final Vec4i v) {
        return intBitsToFloat(v, new Vec4());
    }

    public static Vec4 intBitsToFloat(final Vec4i v, final Vec4 res) {
        res.x = Float.intBitsToFloat(v.x);
        res.y = Float.intBitsToFloat(v.y);
        res.z = Float.intBitsToFloat(v.z);
        res.w = Float.intBitsToFloat(v.w);
        return res;
    }

    // uintBitsToFloat ----------------------------------------------------------------------------------------------
    public static float uintBitsToFloat(final UInt s) {
        return (float) Double.longBitsToDouble(s.value);
    }

    public static Vec2 uintBitsToFloat_(final Vec2ui v) {
        return uintBitsToFloat(v, new Vec2());
    }

    public static Vec2 uintBitsToFloat(final Vec2ui v, final Vec2 res) {
        res.x = (float) Double.longBitsToDouble(v.x.value);
        res.y = (float) Double.longBitsToDouble(v.y.value);
        return res;
    }

    public static Vec3 uintBitsToFloat_(final Vec3ui v) {
        return uintBitsToFloat(v, new Vec3());
    }

    public static Vec3 uintBitsToFloat(final Vec3ui v, final Vec3 res) {
        res.x = (float) Double.longBitsToDouble(v.x.value);
        res.y = (float) Double.longBitsToDouble(v.y.value);
        res.z = (float) Double.longBitsToDouble(v.z.value);
        return res;
    }

    public static Vec4 uintBitsToFloat_(final Vec4ui v) {
        return uintBitsToFloat(v, new Vec4());
    }

    public static Vec4 uintBitsToFloat(final Vec4ui v, final Vec4 res) {
        res.x = (float) Double.longBitsToDouble(v.x.value);
        res.y = (float) Double.longBitsToDouble(v.y.value);
        res.z = (float) Double.longBitsToDouble(v.z.value);
        res.w = (float) Double.longBitsToDouble(v.w.value);
        return res;
    }

    // floor --------------------------------------------------------------------------------------------------------
    public static float floor(final float s) {
        return (float) Math.floor(s);
    }

    public static double floor(final double s) {
        return Math.floor(s);
    }

    public static Vec2 floor_(final Vec2 v) {
        return floor(v, new Vec2());
    }

    public static Vec2 floor(final Vec2 v, final Vec2 res) {
        return res.set(floor(v.x), floor(v.y));
    }

    public static Vec2d floor_(final Vec2d v) {
        return floor(v, new Vec2d());
    }

    public static Vec2d floor(final Vec2d v, final Vec2d res) {
        return res.set(floor(v.x), floor(v.y));
    }

    public static Vec3 floor_(final Vec3 v) {
        return floor(v, new Vec3());
    }

    public static Vec3 floor(final Vec3 v, final Vec3 res) {
        return res.set(floor(v.x), floor(v.y), floor(v.z));
    }

    public static Vec3d floor_(final Vec3d v) {
        return floor(v, new Vec3d());
    }

    public static Vec3d floor(final Vec3d v, final Vec3d res) {
        return res.set(floor(v.x), floor(v.y), floor(v.z));
    }

    public static Vec4 floor_(final Vec4 v) {
        return floor(v, new Vec4());
    }

    public static Vec4 floor(final Vec4 v, final Vec4 res) {
        return res.set(floor(v.x), floor(v.y), floor(v.z), floor(v.w));
    }

    public static Vec4d floor_(final Vec4d v) {
        return floor(v, new Vec4d());
    }

    public static Vec4d floor(final Vec4d v, final Vec4d res) {
        return res.set(floor(v.x), floor(v.y), floor(v.z), floor(v.w));
    }

    // fma ----------------------------------------------------------------------------------------------------------
    public static float fma(final float a, final float b, final float c) {
        return a * b + c;
    }

    public static double fma(final double a, final double b, final double c) {
        return a * b + c;
    }

    public static Vec2 fma_(final Vec2 a, final Vec2 b, final Vec2 c) {
        return fma(a, b, c, new Vec2());
    }

    public static Vec2 fma(final Vec2 a, final Vec2 b, final Vec2 c, final Vec2 res) {
        res.x = a.x * b.x + c.x;
        res.y = a.y * b.y + c.y;
        return res;
    }

    public static Vec2d fma_(final Vec2d a, final Vec2d b, final Vec2d c) {
        return fma(a, b, c, new Vec2d());
    }

    public static Vec2d fma(final Vec2d a, final Vec2d b, final Vec2d c, final Vec2d res) {
        res.x = a.x * b.x + c.x;
        res.y = a.y * b.y + c.y;
        return res;
    }

    public static Vec3 fma_(final Vec3 a, final Vec3 b, final Vec3 c) {
        return fma(a, b, c, new Vec3());
    }

    public static Vec3 fma(final Vec3 a, final Vec3 b, final Vec3 c, final Vec3 res) {
        res.x = a.x * b.x + c.x;
        res.y = a.y * b.y + c.y;
        res.z = a.z * b.z + c.z;
        return res;
    }

    public static Vec3d fma_(final Vec3d a, final Vec3d b, final Vec3d c) {
        return fma(a, b, c, new Vec3d());
    }

    public static Vec3d fma(final Vec3d a, final Vec3d b, final Vec3d c, final Vec3d res) {
        res.x = a.x * b.x + c.x;
        res.y = a.y * b.y + c.y;
        res.z = a.z * b.z + c.z;
        return res;
    }

    public static Vec4 fma_(final Vec4 a, final Vec4 b, final Vec4 c) {
        return fma(a, b, c, new Vec4());
    }

    public static Vec4 fma(final Vec4 a, final Vec4 b, final Vec4 c, final Vec4 res) {
        res.x = a.x * b.x + c.x;
        res.y = a.y * b.y + c.y;
        res.z = a.z * b.z + c.z;
        res.w = a.w * b.w + c.w;
        return res;
    }

    public static Vec4d fma_(final Vec4d a, final Vec4d b, final Vec4d c) {
        return fma(a, b, c, new Vec4d());
    }

    public static Vec4d fma(final Vec4d a, final Vec4d b, final Vec4d c, final Vec4d res) {
        res.x = a.x * b.x + c.x;
        res.y = a.y * b.y + c.y;
        res.z = a.z * b.z + c.z;
        res.w = a.w * b.w + c.w;
        return res;
    }

    // fract --------------------------------------------------------------------------------------------------------
    public static float fract(final float s) {
        return s - floor(s);
    }

    public static double fract(final double s) {
        return s - floor(s);
    }

    public static Vec2 fract_(final Vec2 v) {
        return fract(v, new Vec2());
    }

    public static Vec2 fract(final Vec2 v, final Vec2 res) {
        return res.set(fract(v.x), fract(v.y));
    }

    public static Vec2d fract_(final Vec2d v) {
        return fract(v, new Vec2d());
    }

    public static Vec2d fract(final Vec2d v, final Vec2d res) {
        return res.set(fract(v.x), fract(v.y));
    }

    public static Vec3 fract_(final Vec3 v) {
        return fract(v, new Vec3());
    }

    public static Vec3 fract(final Vec3 v, final Vec3 res) {
        return res.set(fract(v.x), fract(v.y), fract(v.z));
    }

    public static Vec3d fract_(final Vec3d v) {
        return fract(v, new Vec3d());
    }

    public static Vec3d fract(final Vec3d v, final Vec3d res) {
        return res.set(fract(v.x), fract(v.y), fract(v.z));
    }

    public static Vec4 fract_(final Vec4 v) {
        return fract(v, new Vec4());
    }

    public static Vec4 fract(final Vec4 v, final Vec4 res) {
        return res.set(fract(v.x), fract(v.y), fract(v.z), fract(v.w));
    }

    public static Vec4d fract_(final Vec4d v) {
        return fract(v, new Vec4d());
    }

    public static Vec4d fract(final Vec4d v, final Vec4d res) {
        return res.set(fract(v.x), fract(v.y), fract(v.z), fract(v.w));
    }

    // isInf --------------------------------------------------------------------------------------------------------
    public static boolean isInf(final double s) {
        return Double.isInfinite(s);
    }

    public static boolean isInf(final float s) {
        return Float.isInfinite(s);
    }

    public static Vec2bool isInf(final Vec2d v) {
        return isInf(v, new Vec2bool());
    }

    public static Vec2bool isInf(final Vec2d v, final Vec2bool res) {
        res.x = Double.isInfinite(v.x);
        res.y = Double.isInfinite(v.y);
        return res;
    }

    public static Vec2bool isInf(final Vec2 v) {
        return isInf(v, new Vec2bool());
    }

    public static Vec2bool isInf(final Vec2 v, final Vec2bool res) {
        res.x = Float.isInfinite(v.x);
        res.y = Float.isInfinite(v.y);
        return res;
    }

    public static Vec3bool isInf(final Vec3d v) {
        return isInf(v, new Vec3bool());
    }

    public static Vec3bool isInf(final Vec3d v, final Vec3bool res) {
        res.x = Double.isInfinite(v.x);
        res.y = Double.isInfinite(v.y);
        res.z = Double.isInfinite(v.z);
        return res;
    }

    public static Vec3bool isInf(final Vec3 v) {
        return isInf(v, new Vec3bool());
    }

    public static Vec3bool isInf(final Vec3 v, final Vec3bool res) {
        res.x = Float.isInfinite(v.x);
        res.y = Float.isInfinite(v.y);
        res.z = Float.isInfinite(v.z);
        return res;
    }

    public static Vec4bool isInf(final Vec4d v) {
        return isInf(v, new Vec4bool());
    }

    public static Vec4bool isInf(final Vec4d v, final Vec4bool res) {
        res.x = Double.isInfinite(v.x);
        res.y = Double.isInfinite(v.y);
        res.z = Double.isInfinite(v.z);
        res.w = Double.isInfinite(v.w);
        return res;
    }

    public static Vec4bool isInf(final Vec4 v) {
        return isInf(v, new Vec4bool());
    }

    public static Vec4bool isInf(final Vec4 v, final Vec4bool res) {
        res.x = Float.isInfinite(v.x);
        res.y = Float.isInfinite(v.y);
        res.z = Float.isInfinite(v.z);
        res.w = Float.isInfinite(v.w);
        return res;
    }

    // isNan --------------------------------------------------------------------------------------------------------
    public static boolean isNan(final double s) {
        return Double.isNaN(s);
    }

    public static boolean isNan(final float s) {
        return Float.isNaN(s);
    }

    public static Vec2bool isNan(final Vec2d v) {
        return isNan(v, new Vec2bool());
    }

    public static Vec2bool isNan(final Vec2d v, final Vec2bool res) {
        res.x = Double.isNaN(v.x);
        res.y = Double.isNaN(v.y);
        return res;
    }

    public static Vec2bool isNan(final Vec2 v) {
        return isNan(v, new Vec2bool());
    }

    public static Vec2bool isNan(final Vec2 v, final Vec2bool res) {
        res.x = Float.isNaN(v.x);
        res.y = Float.isNaN(v.y);
        return res;
    }

    public static Vec3bool isNan(final Vec3d v) {
        return isNan(v, new Vec3bool());
    }

    public static Vec3bool isNan(final Vec3d v, final Vec3bool res) {
        res.x = Double.isNaN(v.x);
        res.y = Double.isNaN(v.y);
        res.z = Double.isNaN(v.z);
        return res;
    }

    public static Vec3bool isNan(final Vec3 v) {
        return isNan(v, new Vec3bool());
    }

    public static Vec3bool isNan(final Vec3 v, final Vec3bool res) {
        res.x = Float.isNaN(v.x);
        res.y = Float.isNaN(v.y);
        res.z = Float.isNaN(v.z);
        return res;
    }

    public static Vec4bool isNan(final Vec4d v) {
        return isNan(v, new Vec4bool());
    }

    public static Vec4bool isNan(final Vec4d v, final Vec4bool res) {
        res.x = Double.isNaN(v.x);
        res.y = Double.isNaN(v.y);
        res.z = Double.isNaN(v.z);
        res.w = Double.isNaN(v.w);
        return res;
    }

    public static Vec4bool isNan(final Vec4 v) {
        return isNan(v, new Vec4bool());
    }

    public static Vec4bool isNan(final Vec4 v, final Vec4bool res) {
        res.x = Float.isNaN(v.x);
        res.y = Float.isNaN(v.y);
        res.z = Float.isNaN(v.z);
        res.w = Float.isNaN(v.w);
        return res;
    }

    // max ----------------------------------------------------------------------------------------------------------
    public static float max(final float sA, final float sB) {
        return Math.max(sA, sB);
    }

    public static double max(final double sA, final double sB) {
        return Math.max(sA, sB);
    }

    public static int max(final int sA, final int sB) {
        return Math.max(sA, sB);
    }

    public static long max(final long sA, final long sB) {
        return Math.max(sA, sB);
    }

    public static UByte max(final UByte sA, final UByte sB) {
        return UMath.max(sA, sB);
    }

    public static UInt max(final UInt sA, final UInt sB) {
        return UMath.max(sA, sB);
    }

    public static ULong max(final ULong sA, final ULong sB) {
        return UMath.max(sA, sB);
    }

    public static UShort max(final UShort sA, final UShort sB) {
        return UMath.max(sA, sB);
    }

    // max(vec2, scalar) -------------------------------------------------------
    public static Vec2 max_(final Vec2 v, final float s) {
        return max(v, s, new Vec2());
    }

    public static Vec2 max(final Vec2 v, final float s, final Vec2 res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2b max_(final Vec2b v, final byte s) {
        return max(v, s, new Vec2b());
    }

    public static Vec2b max(final Vec2b v, final byte s, final Vec2b res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2d max_(final Vec2d v, final double s) {
        return max(v, s, new Vec2d());
    }

    public static Vec2d max(final Vec2d v, final double s, final Vec2d res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2i max_(final Vec2i v, final int s) {
        return max(v, s, new Vec2i());
    }

    public static Vec2i max(final Vec2i v, final int s, final Vec2i res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2l max_(final Vec2l v, final long s) {
        return max(v, s, new Vec2l());
    }

    public static Vec2l max(final Vec2l v, final long s, final Vec2l res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2s max_(final Vec2s v, final short s) {
        return max(v, s, new Vec2s());
    }

    public static Vec2s max(final Vec2s v, final short s, final Vec2s res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2ub max_(final Vec2ub v, final UByte s) {
        return max(v, s, new Vec2ub());
    }

    public static Vec2ub max(final Vec2ub v, final UByte s, final Vec2ub res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2ui max_(final Vec2ui v, final UInt s) {
        return max(v, s, new Vec2ui());
    }

    public static Vec2ui max(final Vec2ui v, final UInt s, final Vec2ui res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2ul max_(final Vec2ul v, final ULong s) {
        return max(v, s, new Vec2ul());
    }

    public static Vec2ul max(final Vec2ul v, final ULong s, final Vec2ul res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    public static Vec2us max_(final Vec2us v, final UShort s) {
        return max(v, s, new Vec2us());
    }

    public static Vec2us max(final Vec2us v, final UShort s, final Vec2us res) {
        return res.set(max(v.x, s), max(v.y, s));
    }

    // max(vec2, vec2) ---------------------------------------------------------
    public static Vec2 max_(final Vec2 vA, final Vec2 vB) {
        return max(vA, vB, new Vec2());
    }

    public static Vec2 max(final Vec2 vA, final Vec2 vB, final Vec2 res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    public static Vec2b max_(final Vec2b vA, final Vec2b vB) {
        return max(vA, vB, new Vec2b());
    }

    public static Vec2b max(final Vec2b va, final Vec2b vB, final Vec2b res) {
        return res.set(max(va.x, vB.x), max(va.y, vB.y));
    }

    public static Vec2d max_(final Vec2d vA, final Vec2d vB) {
        return max(vA, vB, new Vec2d());
    }

    public static Vec2d max(final Vec2d vA, final Vec2d vB, final Vec2d res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    public static Vec2i max_(final Vec2i vA, final Vec2i vB) {
        return max(vA, vB, new Vec2i());
    }

    public static Vec2i max(final Vec2i vA, final Vec2i vB, final Vec2i res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    public static Vec2l max_(final Vec2l vA, final Vec2l vB) {
        return max(vA, vB, new Vec2l());
    }

    public static Vec2l max(final Vec2l vA, final Vec2l vB, final Vec2l res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    public static Vec2s max_(final Vec2s vA, final Vec2s vB) {
        return max(vA, vB, new Vec2s());
    }

    public static Vec2s max(final Vec2s vA, final Vec2s vB, final Vec2s res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    public static Vec2ub max_(final Vec2ub vA, final Vec2ub vB) {
        return max(vA, vB, new Vec2ub());
    }

    public static Vec2ub max(final Vec2ub vA, final Vec2ub vB, final Vec2ub res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    public static Vec2ui max_(final Vec2ui vA, final Vec2ui vB) {
        return max(vA, vB, new Vec2ui());
    }

    public static Vec2ui max(final Vec2ui vA, final Vec2ui vB, final Vec2ui res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    public static Vec2ul max_(final Vec2ul vA, final Vec2ul vB) {
        return max(vA, vB, new Vec2ul());
    }

    public static Vec2ul max(final Vec2ul vA, final Vec2ul vB, final Vec2ul res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    public static Vec2us max_(final Vec2us vA, final Vec2us vB) {
        return max(vA, vB, new Vec2us());
    }

    public static Vec2us max(final Vec2us vA, final Vec2us vB, final Vec2us res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y));
    }

    // max(vec3, scalar) -------------------------------------------------------
    public static Vec3 max_(final Vec3 v, final float s) {
        return max(v, s, new Vec3());
    }

    public static Vec3 max(final Vec3 v, final float s, final Vec3 res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3b max_(final Vec3b v, final byte s) {
        return max(v, s, new Vec3b());
    }

    public static Vec3b max(final Vec3b v, final byte s, final Vec3b res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3d max_(final Vec3d v, final double s) {
        return max(v, s, new Vec3d());
    }

    public static Vec3d max(final Vec3d v, final double s, final Vec3d res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3i max_(final Vec3i v, final int s) {
        return max(v, s, new Vec3i());
    }

    public static Vec3i max(final Vec3i v, final int s, final Vec3i res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3l max_(final Vec3l v, final long s) {
        return max(v, s, new Vec3l());
    }

    public static Vec3l max(final Vec3l v, final long s, final Vec3l res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3s max_(final Vec3s v, final short s) {
        return max(v, s, new Vec3s());
    }

    public static Vec3s max(final Vec3s v, final short s, final Vec3s res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3ub max_(final Vec3ub v, final UByte s) {
        return max(v, s, new Vec3ub());
    }

    public static Vec3ub max(final Vec3ub v, final UByte s, final Vec3ub res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3ui max_(final Vec3ui v, final UInt s) {
        return max(v, s, new Vec3ui());
    }

    public static Vec3ui max(final Vec3ui v, final UInt s, final Vec3ui res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3ul max_(final Vec3ul v, final ULong s) {
        return max(v, s, new Vec3ul());
    }

    public static Vec3ul max(final Vec3ul v, final ULong s, final Vec3ul res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    public static Vec3us max_(final Vec3us v, final UShort s) {
        return max(v, s, new Vec3us());
    }

    public static Vec3us max(final Vec3us v, final UShort s, final Vec3us res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s));
    }

    // max(vec3, vec3) ---------------------------------------------------------
    public static Vec3 max_(final Vec3 vA, final Vec3 vB) {
        return max(vA, vB, new Vec3());
    }

    public static Vec3 max(final Vec3 vA, final Vec3 vB, final Vec3 res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3b max_(final Vec3b vA, final Vec3b vB) {
        return max(vA, vB, new Vec3b());
    }

    public static Vec3b max(final Vec3b vA, final Vec3b vB, final Vec3b res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3d max_(final Vec3d vA, final Vec3d vB) {
        return max(vA, vB, new Vec3d());
    }

    public static Vec3d max(final Vec3d vA, final Vec3d vB, final Vec3d res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3i max_(final Vec3i vA, final Vec3i vB) {
        return max(vA, vB, new Vec3i());
    }

    public static Vec3i max(final Vec3i vA, final Vec3i vB, final Vec3i res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3l max_(final Vec3l vA, final Vec3l vB) {
        return max(vA, vB, new Vec3l());
    }

    public static Vec3l max(final Vec3l vA, final Vec3l vB, final Vec3l res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3s max_(final Vec3s vA, final Vec3s vB) {
        return max(vA, vB, new Vec3s());
    }

    public static Vec3s max(final Vec3s vA, final Vec3s vB, final Vec3s res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3ub max_(final Vec3ub vA, final Vec3ub vB) {
        return max(vA, vB, new Vec3ub());
    }

    public static Vec3ub max(final Vec3ub vA, final Vec3ub vB, final Vec3ub res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3ui max_(final Vec3ui vA, final Vec3ui vB) {
        return max(vA, vB, new Vec3ui());
    }

    public static Vec3ui max(final Vec3ui vA, final Vec3ui vB, final Vec3ui res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3ul max_(final Vec3ul vA, final Vec3ul vB) {
        return max(vA, vB, new Vec3ul());
    }

    public static Vec3ul max(final Vec3ul vA, final Vec3ul vB, final Vec3ul res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    public static Vec3us max_(final Vec3us vA, final Vec3us vB) {
        return max(vA, vB, new Vec3us());
    }

    public static Vec3us max(final Vec3us vA, final Vec3us vB, final Vec3us res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z));
    }

    // max(vec4, scalar) -------------------------------------------------------
    public static Vec4 max_(final Vec4 v, final float s) {
        return max(v, s, new Vec4());
    }

    public static Vec4 max(final Vec4 v, final float s, final Vec4 res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4b max_(final Vec4b v, final byte s) {
        return max(v, s, new Vec4b());
    }

    public static Vec4b max(final Vec4b v, final byte s, final Vec4b res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4d max_(final Vec4d v, final double s) {
        return max(v, s, new Vec4d());
    }

    public static Vec4d max(final Vec4d v, final double s, final Vec4d res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4i max_(final Vec4i v, final int s) {
        return max(v, s, new Vec4i());
    }

    public static Vec4i max(final Vec4i v, final int s, final Vec4i res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4l max_(final Vec4l v, final long s) {
        return max(v, s, new Vec4l());
    }

    public static Vec4l max(final Vec4l v, final long s, final Vec4l res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4s max_(final Vec4s v, final short s) {
        return max(v, s, new Vec4s());
    }

    public static Vec4s max(final Vec4s v, final short s, final Vec4s res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4ub max_(final Vec4ub v, final UByte s) {
        return max(v, s, new Vec4ub());
    }

    public static Vec4ub max(final Vec4ub v, final UByte s, final Vec4ub res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4ui max_(final Vec4ui v, final UInt s) {
        return max(v, s, new Vec4ui());
    }

    public static Vec4ui max(final Vec4ui v, final UInt s, final Vec4ui res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4ul max_(final Vec4ul v, final ULong s) {
        return max(v, s, new Vec4ul());
    }

    public static Vec4ul max(final Vec4ul v, final ULong s, final Vec4ul res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    public static Vec4us max_(final Vec4us v, final UShort s) {
        return max(v, s, new Vec4us());
    }

    public static Vec4us max(final Vec4us v, final UShort s, final Vec4us res) {
        return res.set(max(v.x, s), max(v.y, s), max(v.z, s), max(v.w, s));
    }

    // max(vec4, vec4) ---------------------------------------------------------
    public static Vec4 max_(final Vec4 vA, final Vec4 vB) {
        return max(vA, vB, new Vec4());
    }

    public static Vec4 max(final Vec4 vA, final Vec4 vB, final Vec4 res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4b max_(final Vec4b vA, final Vec4b vB) {
        return max(vA, vB, new Vec4b());
    }

    public static Vec4b max(final Vec4b vA, final Vec4b vB, final Vec4b res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4d max_(final Vec4d vA, final Vec4d vB) {
        return max(vA, vB, new Vec4d());
    }

    public static Vec4d max(final Vec4d vA, final Vec4d vB, final Vec4d res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4i max_(final Vec4i vA, final Vec4i vB) {
        return max(vA, vB, new Vec4i());
    }

    public static Vec4i max(final Vec4i vA, final Vec4i vB, final Vec4i res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4l max_(final Vec4l vA, final Vec4l vB) {
        return max(vA, vB, new Vec4l());
    }

    public static Vec4l max(final Vec4l vA, final Vec4l vB, final Vec4l res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4s max_(final Vec4s vA, final Vec4s vB) {
        return max(vA, vB, new Vec4s());
    }

    public static Vec4s max(final Vec4s vA, final Vec4s vB, final Vec4s res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4ub max_(final Vec4ub vA, final Vec4ub vB) {
        return max(vA, vB, new Vec4ub());
    }

    public static Vec4ub max(final Vec4ub vA, final Vec4ub vB, final Vec4ub res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4ui max_(final Vec4ui vA, final Vec4ui vB) {
        return max(vA, vB, new Vec4ui());
    }

    public static Vec4ui max(final Vec4ui vA, final Vec4ui vB, final Vec4ui res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4ul max_(final Vec4ul vA, final Vec4ul vB) {
        return max(vA, vB, new Vec4ul());
    }

    public static Vec4ul max(final Vec4ul vA, final Vec4ul vB, final Vec4ul res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    public static Vec4us max_(final Vec4us vA, final Vec4us vB) {
        return max(vA, vB, new Vec4us());
    }

    public static Vec4us max(final Vec4us vA, final Vec4us vB, final Vec4us res) {
        return res.set(max(vA.x, vB.x), max(vA.y, vB.y), max(vA.z, vB.z), max(vA.w, vB.w));
    }

    // min ----------------------------------------------------------------------------------------------------------
    public static float min(final float sA, final float sB) {
        return Math.min(sA, sB);
    }

    public static double min(final double sA, final double sB) {
        return Math.min(sA, sB);
    }

    public static int min(final int sA, final int sB) {
        return Math.min(sA, sB);
    }

    public static long min(final long sA, final long sB) {
        return Math.min(sA, sB);
    }

    public static UByte min(final UByte sA, final UByte sB) {
        return UMath.min(sA, sB);
    }

    public static UInt min(final UInt sA, final UInt sB) {
        return UMath.min(sA, sB);
    }

    public static ULong min(final ULong sA, final ULong sB) {
        return UMath.min(sA, sB);
    }

    public static UShort min(final UShort sA, final UShort sB) {
        return UMath.min(sA, sB);
    }

    // min(vec2, scalar) -------------------------------------------------------
    public static Vec2 min_(final Vec2 v, final float s) {
        return min(v, s, new Vec2());
    }

    public static Vec2 min(final Vec2 v, final float s, final Vec2 res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2b min_(final Vec2b v, final byte s) {
        return min(v, s, new Vec2b());
    }

    public static Vec2b min(final Vec2b v, final byte s, final Vec2b res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2d min_(final Vec2d v, final double s) {
        return min(v, s, new Vec2d());
    }

    public static Vec2d min(final Vec2d v, final double s, final Vec2d res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2i min_(final Vec2i v, final int s) {
        return min(v, s, new Vec2i());
    }

    public static Vec2i min(final Vec2i v, final int s, final Vec2i res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2l min_(final Vec2l v, final long s) {
        return min(v, s, new Vec2l());
    }

    public static Vec2l min(final Vec2l v, final long s, final Vec2l res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2s min_(final Vec2s v, final short s) {
        return min(v, s, new Vec2s());
    }

    public static Vec2s min(final Vec2s v, final short s, final Vec2s res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2ub min_(final Vec2ub v, final UByte s) {
        return min(v, s, new Vec2ub());
    }

    public static Vec2ub min(final Vec2ub v, final UByte s, final Vec2ub res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2ui min_(final Vec2ui v, final UInt s) {
        return min(v, s, new Vec2ui());
    }

    public static Vec2ui min(final Vec2ui v, final UInt s, final Vec2ui res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2ul min_(final Vec2ul v, final ULong s) {
        return min(v, s, new Vec2ul());
    }

    public static Vec2ul min(final Vec2ul v, final ULong s, final Vec2ul res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    public static Vec2us min_(final Vec2us v, final UShort s) {
        return min(v, s, new Vec2us());
    }

    public static Vec2us min(final Vec2us v, final UShort s, final Vec2us res) {
        return res.set(min(v.x, s), min(v.y, s));
    }

    // min(vec2, vec2) ---------------------------------------------------------
    public static Vec2 min_(final Vec2 vA, final Vec2 vB) {
        return min(vA, vB, new Vec2());
    }

    public static Vec2 min(final Vec2 vA, final Vec2 vB, final Vec2 res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    public static Vec2b min_(final Vec2b vA, final Vec2b vB) {
        return min(vA, vB, new Vec2b());
    }

    public static Vec2b min(final Vec2b va, final Vec2b vB, final Vec2b res) {
        return res.set(max(va.x, vB.x), max(va.y, vB.y));
    }

    public static Vec2d min_(final Vec2d vA, final Vec2d vB) {
        return min(vA, vB, new Vec2d());
    }

    public static Vec2d min(final Vec2d vA, final Vec2d vB, final Vec2d res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    public static Vec2i min_(final Vec2i vA, final Vec2i vB) {
        return min(vA, vB, new Vec2i());
    }

    public static Vec2i min(final Vec2i vA, final Vec2i vB, final Vec2i res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    public static Vec2l min_(final Vec2l vA, final Vec2l vB) {
        return min(vA, vB, new Vec2l());
    }

    public static Vec2l min(final Vec2l vA, final Vec2l vB, final Vec2l res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    public static Vec2s min_(final Vec2s vA, final Vec2s vB) {
        return min(vA, vB, new Vec2s());
    }

    public static Vec2s min(final Vec2s vA, final Vec2s vB, final Vec2s res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    public static Vec2ub min_(final Vec2ub vA, final Vec2ub vB) {
        return min(vA, vB, new Vec2ub());
    }

    public static Vec2ub min(final Vec2ub vA, final Vec2ub vB, final Vec2ub res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    public static Vec2ui min_(final Vec2ui vA, final Vec2ui vB) {
        return min(vA, vB, new Vec2ui());
    }

    public static Vec2ui min(final Vec2ui vA, final Vec2ui vB, final Vec2ui res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    public static Vec2ul min_(final Vec2ul vA, final Vec2ul vB) {
        return min(vA, vB, new Vec2ul());
    }

    public static Vec2ul min(final Vec2ul vA, final Vec2ul vB, final Vec2ul res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    public static Vec2us min_(final Vec2us vA, final Vec2us vB) {
        return min(vA, vB, new Vec2us());
    }

    public static Vec2us min(final Vec2us vA, final Vec2us vB, final Vec2us res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y));
    }

    // min(vec3, scalar) -------------------------------------------------------
    public static Vec3 min_(final Vec3 v, final float s) {
        return min(v, s, new Vec3());
    }

    public static Vec3 min(final Vec3 v, final float s, final Vec3 res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3b min_(final Vec3b v, final byte s) {
        return min(v, s, new Vec3b());
    }

    public static Vec3b min(final Vec3b v, final byte s, final Vec3b res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3d min_(final Vec3d v, final double s) {
        return min(v, s, new Vec3d());
    }

    public static Vec3d min(final Vec3d v, final double s, final Vec3d res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3i min_(final Vec3i v, final int s) {
        return min(v, s, new Vec3i());
    }

    public static Vec3i min(final Vec3i v, final int s, final Vec3i res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3l min_(final Vec3l v, final long s) {
        return min(v, s, new Vec3l());
    }

    public static Vec3l min(final Vec3l v, final long s, final Vec3l res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3s min_(final Vec3s v, final short s) {
        return min(v, s, new Vec3s());
    }

    public static Vec3s min(final Vec3s v, final short s, final Vec3s res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3ub min_(final Vec3ub v, final UByte s) {
        return min(v, s, new Vec3ub());
    }

    public static Vec3ub min(final Vec3ub v, final UByte s, final Vec3ub res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3ui min_(final Vec3ui v, final UInt s) {
        return min(v, s, new Vec3ui());
    }

    public static Vec3ui min(final Vec3ui v, final UInt s, final Vec3ui res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3ul min_(final Vec3ul v, final ULong s) {
        return min(v, s, new Vec3ul());
    }

    public static Vec3ul min(final Vec3ul v, final ULong s, final Vec3ul res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    public static Vec3us min_(final Vec3us v, final UShort s) {
        return min(v, s, new Vec3us());
    }

    public static Vec3us min(final Vec3us v, final UShort s, final Vec3us res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s));
    }

    // min(vec3, vec3) ---------------------------------------------------------
    public static Vec3 min_(final Vec3 vA, final Vec3 vB) {
        return min(vA, vB, new Vec3());
    }

    public static Vec3 min(final Vec3 vA, final Vec3 vB, final Vec3 res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3b min_(final Vec3b vA, final Vec3b vB) {
        return min(vA, vB, new Vec3b());
    }

    public static Vec3b min(final Vec3b vA, final Vec3b vB, final Vec3b res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3d min_(final Vec3d vA, final Vec3d vB) {
        return min(vA, vB, new Vec3d());
    }

    public static Vec3d min(final Vec3d vA, final Vec3d vB, final Vec3d res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3i min_(final Vec3i vA, final Vec3i vB) {
        return min(vA, vB, new Vec3i());
    }

    public static Vec3i min(final Vec3i vA, final Vec3i vB, final Vec3i res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3l min_(final Vec3l vA, final Vec3l vB) {
        return min(vA, vB, new Vec3l());
    }

    public static Vec3l min(final Vec3l vA, final Vec3l vB, final Vec3l res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3s min_(final Vec3s vA, final Vec3s vB) {
        return min(vA, vB, new Vec3s());
    }

    public static Vec3s min(final Vec3s vA, final Vec3s vB, final Vec3s res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3ub min_(final Vec3ub vA, final Vec3ub vB) {
        return min(vA, vB, new Vec3ub());
    }

    public static Vec3ub min(final Vec3ub vA, final Vec3ub vB, final Vec3ub res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3ui min_(final Vec3ui vA, final Vec3ui vB) {
        return min(vA, vB, new Vec3ui());
    }

    public static Vec3ui min(final Vec3ui vA, final Vec3ui vB, final Vec3ui res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3ul min_(final Vec3ul vA, final Vec3ul vB) {
        return min(vA, vB, new Vec3ul());
    }

    public static Vec3ul min(final Vec3ul vA, final Vec3ul vB, final Vec3ul res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    public static Vec3us min_(final Vec3us vA, final Vec3us vB) {
        return min(vA, vB, new Vec3us());
    }

    public static Vec3us min(final Vec3us vA, final Vec3us vB, final Vec3us res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z));
    }

    // min(vec4, scalar) -------------------------------------------------------
    public static Vec4 min_(final Vec4 v, final float s) {
        return min(v, s, new Vec4());
    }

    public static Vec4 min(final Vec4 v, final float s, final Vec4 res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4b min_(final Vec4b v, final byte s) {
        return min(v, s, new Vec4b());
    }

    public static Vec4b min(final Vec4b v, final byte s, final Vec4b res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4d min_(final Vec4d v, final double s) {
        return min(v, s, new Vec4d());
    }

    public static Vec4d min(final Vec4d v, final double s, final Vec4d res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4i min_(final Vec4i v, final int s) {
        return min(v, s, new Vec4i());
    }

    public static Vec4i min(final Vec4i v, final int s, final Vec4i res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4l min_(final Vec4l v, final long s) {
        return min(v, s, new Vec4l());
    }

    public static Vec4l min(final Vec4l v, final long s, final Vec4l res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4s min_(final Vec4s v, final short s) {
        return min(v, s, new Vec4s());
    }

    public static Vec4s min(final Vec4s v, final short s, final Vec4s res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4ub min_(final Vec4ub v, final UByte s) {
        return min(v, s, new Vec4ub());
    }

    public static Vec4ub min(final Vec4ub v, final UByte s, final Vec4ub res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4ui min_(final Vec4ui v, final UInt s) {
        return min(v, s, new Vec4ui());
    }

    public static Vec4ui min(final Vec4ui v, final UInt s, final Vec4ui res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4ul min_(final Vec4ul v, final ULong s) {
        return min(v, s, new Vec4ul());
    }

    public static Vec4ul min(final Vec4ul v, final ULong s, final Vec4ul res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    public static Vec4us min_(final Vec4us v, final UShort s) {
        return min(v, s, new Vec4us());
    }

    public static Vec4us min(final Vec4us v, final UShort s, final Vec4us res) {
        return res.set(min(v.x, s), min(v.y, s), min(v.z, s), min(v.w, s));
    }

    // min(vec4, vec4) ---------------------------------------------------------
    public static Vec4 min_(final Vec4 vA, final Vec4 vB) {
        return min(vA, vB, new Vec4());
    }

    public static Vec4 min(final Vec4 vA, final Vec4 vB, final Vec4 res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4b min_(final Vec4b vA, final Vec4b vB) {
        return min(vA, vB, new Vec4b());
    }

    public static Vec4b min(final Vec4b vA, final Vec4b vB, final Vec4b res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4d min_(final Vec4d vA, final Vec4d vB) {
        return min(vA, vB, new Vec4d());
    }

    public static Vec4d min(final Vec4d vA, final Vec4d vB, final Vec4d res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4i min_(final Vec4i vA, final Vec4i vB) {
        return min(vA, vB, new Vec4i());
    }

    public static Vec4i min(final Vec4i vA, final Vec4i vB, final Vec4i res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4l min_(final Vec4l vA, final Vec4l vB) {
        return min(vA, vB, new Vec4l());
    }

    public static Vec4l min(final Vec4l vA, final Vec4l vB, final Vec4l res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4s min_(final Vec4s vA, final Vec4s vB) {
        return min(vA, vB, new Vec4s());
    }

    public static Vec4s min(final Vec4s vA, final Vec4s vB, final Vec4s res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4ub min_(final Vec4ub vA, final Vec4ub vB) {
        return min(vA, vB, new Vec4ub());
    }

    public static Vec4ub min(final Vec4ub vA, final Vec4ub vB, final Vec4ub res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4ui min_(final Vec4ui vA, final Vec4ui vB) {
        return min(vA, vB, new Vec4ui());
    }

    public static Vec4ui min(final Vec4ui vA, final Vec4ui vB, final Vec4ui res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4ul min_(final Vec4ul vA, final Vec4ul vB) {
        return min(vA, vB, new Vec4ul());
    }

    public static Vec4ul min(final Vec4ul vA, final Vec4ul vB, final Vec4ul res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    public static Vec4us min_(final Vec4us vA, final Vec4us vB) {
        return min(vA, vB, new Vec4us());
    }

    public static Vec4us min(final Vec4us vA, final Vec4us vB, final Vec4us res) {
        return res.set(min(vA.x, vB.x), min(vA.y, vB.y), min(vA.z, vB.z), min(vA.w, vB.w));
    }

    // mix (scalar - scalar) ----------------------------------------------------------------------------------------
    public static float mix(final float a, final float b, final float s) {
        return a + s * (b - a);
    }

    public static float mix(final float a, final float b, final boolean bool) {
        return bool ? a : b;
    }

    public static double mix(final double a, final double b, final double s) {
        return a + s * (b - a);
    }

    public static double mix(final double a, final double b, final boolean bool) {
        return bool ? a : b;
    }

    public static int mix(final int a, final int b, final int s) {
        return a + s * (b - a);
    }

    public static int mix(final int a, final int b, final boolean bool) {
        return bool ? a : b;
    }

    public static long mix(final long a, final long b, final long s) {
        return a + s * (b - a);
    }

    public static long mix(final long a, final long b, final boolean bool) {
        return bool ? a : b;
    }

    // mix (vec - scalar) ------------------------------------------------------
    public static Vec2 mix_(final Vec2 a, final Vec2 b, final float s) {
        return mix(a, b, s, new Vec2());
    }

    public static Vec2 mix(final Vec2 a, final Vec2 b, final float s, final Vec2 res) {
        res.x = mix(a.x, b.x, s);
        res.y = mix(a.y, b.y, s);
        return res;
    }

    public static Vec2 mix_(final Vec2 a, final Vec2 b, final boolean bool) {
        return mix(a, b, bool, new Vec2());
    }

    public static Vec2 mix(final Vec2 a, final Vec2 b, final boolean bool, final Vec2 res) {
        res.x = mix(a.x, b.x, bool);
        res.y = mix(a.y, b.y, bool);
        return res;
    }

    public static Vec2d mix_(final Vec2d a, final Vec2d b, final double s) {
        return mix(a, b, s, new Vec2d());
    }

    public static Vec2d mix(final Vec2d a, final Vec2d b, final double s, final Vec2d res) {
        res.x = mix(a.x, b.x, s);
        res.y = mix(a.y, b.y, s);
        return res;
    }

    public static Vec2d mix_(final Vec2d a, final Vec2d b, final boolean bool) {
        return mix(a, b, bool, new Vec2d());
    }

    public static Vec2d mix(final Vec2d a, final Vec2d b, final boolean bool, final Vec2d res) {
        res.x = mix(a.x, b.x, bool);
        res.y = mix(a.y, b.y, bool);
        return res;
    }

    public static Vec3 mix_(final Vec3 a, final Vec3 b, final float s) {
        return mix(a, b, s, new Vec3());
    }

    public static Vec3 mix(final Vec3 a, final Vec3 b, final float s, final Vec3 res) {
        res.x = mix(a.x, b.x, s);
        res.y = mix(a.y, b.y, s);
        res.z = mix(a.z, b.z, s);
        return res;
    }

    public static Vec3 mix_(final Vec3 a, final Vec3 b, final boolean bool) {
        return mix(a, b, bool, new Vec3());
    }

    public static Vec3 mix(final Vec3 a, final Vec3 b, final boolean bool, final Vec3 res) {
        res.x = mix(a.x, b.x, bool);
        res.y = mix(a.y, b.y, bool);
        res.z = mix(a.z, b.z, bool);
        return res;
    }

    public static Vec3d mix_(final Vec3d a, final Vec3d b, final double s) {
        return mix(a, b, s, new Vec3d());
    }

    public static Vec3d mix(final Vec3d a, final Vec3d b, final double s, final Vec3d res) {
        res.x = mix(a.x, b.x, s);
        res.y = mix(a.y, b.y, s);
        res.z = mix(a.z, b.z, s);
        return res;
    }

    public static Vec3d mix_(final Vec3d a, final Vec3d b, final boolean bool) {
        return mix(a, b, bool, new Vec3d());
    }

    public static Vec3d mix(final Vec3d a, final Vec3d b, final boolean bool, final Vec3d res) {
        res.x = mix(a.x, b.x, bool);
        res.y = mix(a.y, b.y, bool);
        res.z = mix(a.z, b.z, bool);
        return res;
    }

    public static Vec4 mix_(final Vec4 a, final Vec4 b, final float s) {
        return mix(a, b, s, new Vec4());
    }

    public static Vec4 mix(final Vec4 a, final Vec4 b, final float s, final Vec4 res) {
        res.x = mix(a.x, b.x, s);
        res.y = mix(a.y, b.y, s);
        res.z = mix(a.z, b.z, s);
        res.w = mix(a.w, b.w, s);
        return res;
    }

    public static Vec4 mix_(final Vec4 a, final Vec4 b, final boolean bool) {
        return mix(a, b, bool, new Vec4());
    }

    public static Vec4 mix(final Vec4 a, final Vec4 b, final boolean bool, final Vec4 res) {
        res.x = mix(a.x, b.x, bool);
        res.y = mix(a.y, b.y, bool);
        res.z = mix(a.z, b.z, bool);
        res.w = mix(a.w, b.w, bool);
        return res;
    }

    public static Vec4d mix_(final Vec4d a, final Vec4d b, final double s) {
        return mix(a, b, s, new Vec4d());
    }

    public static Vec4d mix(final Vec4d a, final Vec4d b, final double s, final Vec4d res) {
        res.x = mix(a.x, b.x, s);
        res.y = mix(a.y, b.y, s);
        res.z = mix(a.z, b.z, s);
        res.w = mix(a.w, b.w, s);
        return res;
    }

    public static Vec4d mix_(final Vec4d a, final Vec4d b, final boolean bool) {
        return mix(a, b, bool, new Vec4d());
    }

    public static Vec4d mix(final Vec4d a, final Vec4d b, final boolean bool, final Vec4d res) {
        res.x = mix(a.x, b.x, bool);
        res.y = mix(a.y, b.y, bool);
        res.z = mix(a.z, b.z, bool);
        res.w = mix(a.w, b.w, bool);
        return res;
    }

    // mix (vec - vec) ---------------------------------------------------------
    public static Vec2 mix_(final Vec2 a, final Vec2 b, final Vec2 v) {
        return mix(a, b, v, new Vec2());
    }

    public static Vec2 mix(final Vec2 a, final Vec2 b, final Vec2 v, final Vec2 res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        return res;
    }

    public static Vec2 mix_(final Vec2 a, final Vec2 b, final Vec2bool v) {
        return mix(a, b, v, new Vec2());
    }

    public static Vec2 mix(final Vec2 a, final Vec2 b, final Vec2bool v, final Vec2 res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        return res;
    }

    public static Vec2d mix_(final Vec2d a, final Vec2d b, final Vec2d v) {
        return mix(a, b, v, new Vec2d());
    }

    public static Vec2d mix(final Vec2d a, final Vec2d b, final Vec2d v, final Vec2d res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        return res;
    }

    public static Vec2d mix_(final Vec2d a, final Vec2d b, final Vec2bool v) {
        return mix(a, b, v, new Vec2d());
    }

    public static Vec2d mix(final Vec2d a, final Vec2d b, final Vec2bool v, final Vec2d res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        return res;
    }

    public static Vec3 mix_(final Vec3 a, final Vec3 b, final Vec3 v) {
        return mix(a, b, v, new Vec3());
    }

    public static Vec3 mix(final Vec3 a, final Vec3 b, final Vec3 v, final Vec3 res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        res.z = mix(a.z, b.z, v.z);
        return res;
    }

    public static Vec3 mix_(final Vec3 a, final Vec3 b, final Vec3bool v) {
        return mix(a, b, v, new Vec3());
    }

    public static Vec3 mix(final Vec3 a, final Vec3 b, final Vec3bool v, final Vec3 res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        res.z = mix(a.z, b.z, v.z);
        return res;
    }

    public static Vec3d mix_(final Vec3d a, final Vec3d b, final Vec3d v) {
        return mix(a, b, v, new Vec3d());
    }

    public static Vec3d mix(final Vec3d a, final Vec3d b, final Vec3d v, final Vec3d res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        res.z = mix(a.z, b.z, v.z);
        return res;
    }

    public static Vec3d mix_(final Vec3d a, final Vec3d b, final Vec3bool v) {
        return mix(a, b, v, new Vec3d());
    }

    public static Vec3d mix(final Vec3d a, final Vec3d b, final Vec3bool v, final Vec3d res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        res.z = mix(a.z, b.z, v.z);
        return res;
    }

    public static Vec4 mix_(final Vec4 a, final Vec4 b, final Vec4 v) {
        return mix(a, b, v, new Vec4());
    }

    public static Vec4 mix(final Vec4 a, final Vec4 b, final Vec4 v, final Vec4 res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        res.z = mix(a.z, b.z, v.z);
        res.w = mix(a.w, b.w, v.w);
        return res;
    }

    public static Vec4 mix_(final Vec4 a, final Vec4 b, final Vec4bool v) {
        return mix(a, b, v, new Vec4());
    }

    public static Vec4 mix(final Vec4 a, final Vec4 b, final Vec4bool v, final Vec4 res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        res.z = mix(a.z, b.z, v.z);
        res.w = mix(a.w, b.w, v.w);
        return res;
    }

    public static Vec4d mix_(final Vec4d a, final Vec4d b, final Vec4d v) {
        return mix(a, b, v, new Vec4d());
    }

    public static Vec4d mix(final Vec4d a, final Vec4d b, final Vec4d v, final Vec4d res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        res.z = mix(a.z, b.z, v.z);
        res.w = mix(a.w, b.w, v.w);
        return res;
    }

    public static Vec4d mix_(final Vec4d a, final Vec4d b, final Vec4bool v) {
        return mix(a, b, v, new Vec4d());
    }

    public static Vec4d mix(final Vec4d a, final Vec4d b, final Vec4bool v, final Vec4d res) {
        res.x = mix(a.x, b.x, v.x);
        res.y = mix(a.y, b.y, v.y);
        res.z = mix(a.z, b.z, v.z);
        res.w = mix(a.w, b.w, v.w);
        return res;
    }

    // mod ---------------------------------------------------------------------
    public static float mod(final float x, final float y) {
        return x - y * floor(x / y);
    }

    public static double mod(final double x, final double y) {
        return x - y * floor(x / y);
    }

    public static Vec2 mod_(final Vec2 v, final float f) {
        return mod(v, f, new Vec2());
    }

    public static Vec2 mod(final Vec2 v, final float f, final Vec2 res) {
        return res.set(mod(v.x, f), mod(v.y, f));
    }

    public static Vec2d mod_(final Vec2d v, final double d) {
        return mod(v, d, new Vec2d());
    }

    public static Vec2d mod(final Vec2d v, final double d, final Vec2d res) {
        return res.set(mod(v.x, d), mod(v.y, d));
    }

    public static Vec2 mod_(final Vec2 a, final Vec2 b) {
        return mod(a, b, new Vec2());
    }

    public static Vec2 mod(final Vec2 a, final Vec2 b, final Vec2 res) {
        return res.set(mod(a.x, b.x), mod(a.y, b.y));
    }

    public static Vec2d mod_(final Vec2d a, final Vec2d b) {
        return mod(a, b, new Vec2d());
    }

    public static Vec2d mod(final Vec2d a, final Vec2d b, final Vec2d res) {
        return res.set(mod(a.x, b.x), mod(a.y, b.y));
    }

    public static Vec3 mod_(final Vec3 v, final float f) {
        return mod(v, f, new Vec3());
    }

    public static Vec3 mod(final Vec3 v, final float f, final Vec3 res) {
        return res.set(mod(v.x, f), mod(v.y, f), mod(v.z, f));
    }

    public static Vec3d mod_(final Vec3d v, final double d) {
        return mod(v, d, new Vec3d());
    }

    public static Vec3d mod(final Vec3d v, final double d, final Vec3d res) {
        return res.set(mod(v.x, d), mod(v.y, d), mod(v.z, d));
    }

    public static Vec3 mod_(final Vec3 a, final Vec3 b) {
        return mod(a, b, new Vec3());
    }

    public static Vec3 mod(final Vec3 a, final Vec3 b, final Vec3 res) {
        return res.set(mod(a.x, b.x), mod(a.y, b.y), mod(a.z, b.z));
    }

    public static Vec3d mod_(final Vec3d a, final Vec3d b) {
        return mod(a, b, new Vec3d());
    }

    public static Vec3d mod(final Vec3d a, final Vec3d b, final Vec3d res) {
        return res.set(mod(a.x, b.x), mod(a.y, b.y), mod(a.z, b.z));
    }

    public static Vec4 mod_(final Vec4 v, final float f) {
        return mod(v, f, new Vec4());
    }

    public static Vec4 mod(final Vec4 v, final float f, final Vec4 res) {
        return res.set(mod(v.x, f), mod(v.y, f), mod(v.z, f), mod(v.w, f));
    }

    public static Vec4d mod_(final Vec4d v, final double d) {
        return mod(v, d, new Vec4d());
    }

    public static Vec4d mod(final Vec4d v, final double d, final Vec4d res) {
        return res.set(mod(v.x, d), mod(v.y, d), mod(v.z, d), mod(v.w, d));
    }

    public static Vec4 mod_(final Vec4 a, final Vec4 b) {
        return mod(a, b, new Vec4());
    }

    public static Vec4 mod(final Vec4 a, final Vec4 b, final Vec4 res) {
        return res.set(mod(a.x, b.x), mod(a.y, b.y), mod(a.z, b.z), mod(a.w, b.w));
    }

    public static Vec4d mod_(final Vec4d a, final Vec4d b) {
        return mod(a, b, new Vec4d());
    }

    public static Vec4d mod(final Vec4d a, final Vec4d b, final Vec4d res) {
        return res.set(mod(a.x, b.x), mod(a.y, b.y), mod(a.z, b.z), mod(a.w, b.w));
    }

    // round -------------------------------------------------------------------
    public static int round(final float f) {
        return Math.round(f);
    }

    public static long round(final double d) {
        return Math.round(d);
    }

    public static Vec2 round_(final Vec2 v) {
        return round(v, new Vec2());
    }

    public static Vec2 round(final Vec2 v, final Vec2 res) {
        return res.set(round(v.x), round(v.y));
    }

    public static Vec2d round_(final Vec2d v) {
        return round(v, new Vec2d());
    }

    public static Vec2d round(final Vec2d v, final Vec2d res) {
        return res.set(round(v.x), round(v.y));
    }

    public static Vec3 round_(final Vec3 v) {
        return round(v, new Vec3());
    }

    public static Vec3 round(final Vec3 v, final Vec3 res) {
        return res.set(round(v.x), round(v.y), round(v.z));
    }

    public static Vec3d round_(final Vec3d v) {
        return round(v, new Vec3d());
    }

    public static Vec3d round(final Vec3d v, final Vec3d res) {
        return res.set(round(v.x), round(v.y), round(v.z));
    }

    public static Vec4 round_(final Vec4 v) {
        return round(v, new Vec4());
    }

    public static Vec4 round(final Vec4 v, final Vec4 res) {
        return res.set(round(v.x), round(v.y), round(v.z), round(v.w));
    }

    public static Vec4d round_(final Vec4d v) {
        return round(v, new Vec4d());
    }

    public static Vec4d round(final Vec4d v, final Vec4d res) {
        return res.set(round(v.x), round(v.y), round(v.z), round(v.w));
    }

    // roundEven ---------------------------------------------------------------
    public static int roundEven(final float f) {
        final int integerPart = (int) f;
        final float fractionalPart = fract(f);
        if (fractionalPart > 0.5f || fractionalPart < 0.5f) {
            return round(f);
        } else if ((integerPart % 2) == 0) {
            return integerPart;
        } else if (f <= 0) {    // Work around... 
            return integerPart - 1;
        } else {
            return integerPart + 1;
        }
    }

    public static long roundEven(final double d) {
        final long integerPart = (long) d;
        final double fractionalPart = fract(d);
        if (fractionalPart > 0.5f || fractionalPart < 0.5f) {
            return round(d);
        } else if ((integerPart % 2) == 0) {
            return integerPart;
        } else if (d <= 0) {    // Work around... 
            return integerPart - 1;
        } else {
            return integerPart + 1;
        }
    }

    public static Vec2 roundEven_(final Vec2 v) {
        return roundEven(v, new Vec2());
    }

    public static Vec2 roundEven(final Vec2 v, final Vec2 res) {
        return res.set(roundEven(v.x), roundEven(v.y));
    }

    public static Vec2d roundEven_(final Vec2d v) {
        return roundEven(v, new Vec2d());
    }

    public static Vec2d roundEven(final Vec2d v, final Vec2d res) {
        return res.set(roundEven(v.x), roundEven(v.y));
    }

    public static Vec3 roundEven_(final Vec3 v) {
        return roundEven(v, new Vec3());
    }

    public static Vec3 roundEven(final Vec3 v, final Vec3 res) {
        return res.set(roundEven(v.x), roundEven(v.y), roundEven(v.z));
    }

    public static Vec3d roundEven_(final Vec3d v) {
        return roundEven(v, new Vec3d());
    }

    public static Vec3d roundEven(final Vec3d v, final Vec3d res) {
        return res.set(roundEven(v.x), roundEven(v.y), roundEven(v.z));
    }

    public static Vec4 roundEven_(final Vec4 v) {
        return roundEven(v, new Vec4());
    }

    public static Vec4 roundEven(final Vec4 v, final Vec4 res) {
        return res.set(roundEven(v.x), roundEven(v.y), roundEven(v.z), roundEven(v.w));
    }

    public static Vec4d roundEven_(final Vec4d v) {
        return roundEven(v, new Vec4d());
    }

    public static Vec4d roundEven(final Vec4d v, final Vec4d res) {
        return res.set(roundEven(v.x), roundEven(v.y), roundEven(v.z), roundEven(v.w));
    }

    // sign --------------------------------------------------------------------
    public static float sign(final float f) {
        return Math.signum(f);
    }

    public static double sign(final double d) {
        return Math.signum(d);
    }

    public static int sign(final int i) {
        return (int) Math.signum(i);
    }

    public static Vec2 sign_(final Vec2 v) {
        return sign(v, new Vec2());
    }

    public static Vec2 sign(final Vec2 v, final Vec2 res) {
        return res.set(sign(v.x), sign(v.y));
    }

    public static Vec2d sign_(final Vec2d v) {
        return sign(v, new Vec2d());
    }

    public static Vec2d sign(final Vec2d v, final Vec2d res) {
        return res.set(sign(v.x), sign(v.y));
    }

    public static Vec2i sign_(final Vec2i v) {
        return sign(v, new Vec2i());
    }

    public static Vec2i sign(final Vec2i v, final Vec2i res) {
        return res.set(sign(v.x), sign(v.y));
    }

    public static Vec3 sign_(final Vec3 v) {
        return sign(v, new Vec3());
    }

    public static Vec3 sign(final Vec3 v, final Vec3 res) {
        return res.set(sign(v.x), sign(v.y), sign(v.z));
    }

    public static Vec3d sign_(final Vec3d v) {
        return sign(v, new Vec3d());
    }

    public static Vec3d sign(final Vec3d v, final Vec3d res) {
        return res.set(sign(v.x), sign(v.y), sign(v.z));
    }

    public static Vec3i sign_(final Vec3i v) {
        return sign(v, new Vec3i());
    }

    public static Vec3i sign(final Vec3i v, final Vec3i res) {
        return res.set(sign(v.x), sign(v.y), sign(v.z));
    }

    public static Vec4 sign_(final Vec4 v) {
        return sign(v, new Vec4());
    }

    public static Vec4 sign(final Vec4 v, final Vec4 res) {
        return res.set(sign(v.x), sign(v.y), sign(v.z), sign(v.w));
    }

    public static Vec4d sign_(final Vec4d v) {
        return sign(v, new Vec4d());
    }

    public static Vec4d sign(final Vec4d v, final Vec4d res) {
        return res.set(sign(v.x), sign(v.y), sign(v.z), sign(v.w));
    }

    public static Vec4i sign_(final Vec4i v) {
        return sign(v, new Vec4i());
    }

    public static Vec4i sign(final Vec4i v, final Vec4i res) {
        return res.set(sign(v.x), sign(v.y), sign(v.z), sign(v.w));
    }

    // smoothstep ---------------------------------------------------------------------------------------------------
    public static double smoothstep(final double edge0, final double edge1, final double s) {
        final double t = clamp((s - edge0) / (edge1 - edge0), 0, 1);
        return t * t * (3 - 2 * t);
    }

    public static float smoothstep(final float edge0, final float edge1, final float s) {
        final float t = clamp((s - edge0) / (edge1 - edge0), 0, 1);
        return t * t * (3 - 2 * t);
    }

    public static Vec2d smoothstep_(final double edge0, final double edge1, final Vec2d s) {
        return smoothstep(edge0, edge1, s, new Vec2d());
    }

    public static Vec2d smoothstep(final double edge0, final double edge1, final Vec2d s, final Vec2d res) {
        final double tX = clamp((s.x - edge0) / (edge1 - edge0), 0, 1);
        final double tY = clamp((s.y - edge0) / (edge1 - edge0), 0, 1);
        res.x = tX * tX * (3 - 2 * tX);
        res.y = tY * tY * (3 - 2 * tY);
        return res;
    }

    public static Vec2 smoothstep_(final float edge0, final float edge1, final Vec2 s) {
        return smoothstep(edge0, edge1, s, new Vec2());
    }

    public static Vec2 smoothstep(final float edge0, final float edge1, final Vec2 s, final Vec2 res) {
        final float tX = clamp((s.x - edge0) / (edge1 - edge0), 0, 1);
        final float tY = clamp((s.y - edge0) / (edge1 - edge0), 0, 1);
        res.x = tX * tX * (3 - 2 * tX);
        res.y = tY * tY * (3 - 2 * tY);
        return res;
    }

    public static Vec3d smoothstep_(final double edge0, final double edge1, final Vec3d s) {
        return smoothstep(edge0, edge1, s, new Vec3d());
    }

    public static Vec3d smoothstep(final double edge0, final double edge1, final Vec3d s, final Vec3d res) {
        final double tX = clamp((s.x - edge0) / (edge1 - edge0), 0, 1);
        final double tY = clamp((s.y - edge0) / (edge1 - edge0), 0, 1);
        final double tZ = clamp((s.z - edge0) / (edge1 - edge0), 0, 1);
        res.x = tX * tX * (3 - 2 * tX);
        res.y = tY * tY * (3 - 2 * tY);
        res.z = tZ * tZ * (3 - 2 * tZ);
        return res;
    }

    public static Vec3 smoothstep_(final float edge0, final float edge1, final Vec3 s) {
        return smoothstep(edge0, edge1, s, new Vec3());
    }

    public static Vec3 smoothstep(final float edge0, final float edge1, final Vec3 s, final Vec3 res) {
        final float tX = clamp((s.x - edge0) / (edge1 - edge0), 0, 1);
        final float tY = clamp((s.y - edge0) / (edge1 - edge0), 0, 1);
        final float tZ = clamp((s.z - edge0) / (edge1 - edge0), 0, 1);
        res.x = tX * tX * (3 - 2 * tX);
        res.y = tY * tY * (3 - 2 * tY);
        res.z = tZ * tZ * (3 - 2 * tZ);
        return res;
    }

    public static Vec4d smoothstep_(final double edge0, final double edge1, final Vec4d s) {
        return smoothstep(edge0, edge1, s, new Vec4d());
    }

    public static Vec4d smoothstep(final double edge0, final double edge1, final Vec4d s, final Vec4d res) {
        final double tX = clamp((s.x - edge0) / (edge1 - edge0), 0, 1);
        final double tY = clamp((s.y - edge0) / (edge1 - edge0), 0, 1);
        final double tZ = clamp((s.z - edge0) / (edge1 - edge0), 0, 1);
        final double tW = clamp((s.w - edge0) / (edge1 - edge0), 0, 1);
        res.x = tX * tX * (3 - 2 * tX);
        res.y = tY * tY * (3 - 2 * tY);
        res.z = tZ * tZ * (3 - 2 * tZ);
        res.w = tW * tW * (3 - 2 * tW);
        return res;
    }

    public static Vec4 smoothstep_(final float edge0, final float edge1, final Vec4 s) {
        return smoothstep(edge0, edge1, s, new Vec4());
    }

    public static Vec4 smoothstep(final float edge0, final float edge1, final Vec4 s, final Vec4 res) {
        final float tX = clamp((s.x - edge0) / (edge1 - edge0), 0, 1);
        final float tY = clamp((s.y - edge0) / (edge1 - edge0), 0, 1);
        final float tZ = clamp((s.z - edge0) / (edge1 - edge0), 0, 1);
        final float tW = clamp((s.w - edge0) / (edge1 - edge0), 0, 1);
        res.x = tX * tX * (3 - 2 * tX);
        res.y = tY * tY * (3 - 2 * tY);
        res.z = tZ * tZ * (3 - 2 * tZ);
        res.w = tW * tW * (3 - 2 * tW);
        return res;
    }

    // step ---------------------------------------------------------------------------------------------------------
    public static float step(final byte edge, final byte s) {
        return s < edge ? 0 : 1;
    }

    public static float step(final double edge, final double s) {
        return s < edge ? 0 : 1;
    }

    public static float step(final float edge, final float s) {
        return s < edge ? 0 : 1;
    }

    public static float step(final int edge, final int s) {
        return s < edge ? 0 : 1;
    }

    public static float step(final long edge, final long s) {
        return s < edge ? 0 : 1;
    }

    public static float step(final short edge, final short s) {
        return s < edge ? 0 : 1;
    }

    // step (scalar, vec2) -----------------------------------------------------
    public static Vec2b step_(final byte edge, final Vec2b v) {
        return step(edge, v, new Vec2b());
    }

    public static Vec2b step(final byte edge, final Vec2b v, final Vec2b res) {
        res.x = v.x < edge ? (byte) 0 : (byte) 1;
        res.y = v.y < edge ? (byte) 0 : (byte) 1;
        return res;
    }

    public static Vec2d step_(final double edge, final Vec2d v) {
        return step(edge, v, new Vec2d());
    }

    public static Vec2d step(final double edge, final Vec2d v, final Vec2d res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        return res;
    }

    public static Vec2 step_(final float edge, final Vec2 v) {
        return step(edge, v, new Vec2());
    }

    public static Vec2 step(final float edge, final Vec2 v, final Vec2 res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        return res;
    }

    public static Vec2i step_(final int edge, final Vec2i v) {
        return step(edge, v, new Vec2i());
    }

    public static Vec2i step(final int edge, final Vec2i v, final Vec2i res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        return res;
    }

    public static Vec2l step_(final long edge, final Vec2l v) {
        return step(edge, v, new Vec2l());
    }

    public static Vec2l step(final long edge, final Vec2l v, final Vec2l res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        return res;
    }

    public static Vec2s step_(final short edge, final Vec2s v) {
        return step(edge, v, new Vec2s());
    }

    public static Vec2s step(final short edge, final Vec2s v, final Vec2s res) {
        res.x = v.x < edge ? (short) 0 : (short) 1;
        res.y = v.y < edge ? (short) 0 : (short) 1;
        return res;
    }

    public static Vec2ub step_(final UByte edge, final Vec2ub v) {
        return step(edge, v, new Vec2ub());
    }

    public static Vec2ub step(final UByte edge, final Vec2ub v, final Vec2ub res) {
        res.x.value = (byte) (v.x.compareTo(edge) < 0 ? 0 : 1);
        res.y.value = (byte) (v.y.compareTo(edge) < 0 ? 0 : 1);
        return res;
    }

    public static Vec2ui step_(final UInt edge, final Vec2ui v) {
        return step(edge, v, new Vec2ui());
    }

    public static Vec2ui step(final UInt edge, final Vec2ui v, final Vec2ui res) {
        res.x.value = v.x.compareTo(edge) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge) < 0 ? 0 : 1;
        return res;
    }

    public static Vec2ul step_(final ULong edge, final Vec2ul v) {
        return step(edge, v, new Vec2ul());
    }

    public static Vec2ul step(final ULong edge, final Vec2ul v, final Vec2ul res) {
        res.x.value = v.x.compareTo(edge) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge) < 0 ? 0 : 1;
        return res;
    }

    public static Vec2us step_(final UShort edge, final Vec2us v) {
        return step(edge, v, new Vec2us());
    }

    public static Vec2us step(final UShort edge, final Vec2us v, final Vec2us res) {
        res.x.value = (short) (v.x.compareTo(edge) < 0 ? 0 : 1);
        res.y.value = (short) (v.y.compareTo(edge) < 0 ? 0 : 1);
        return res;
    }

    // step (scalar, vec3) -----------------------------------------------------
    public static Vec3b step_(final byte edge, final Vec3b v) {
        return step(edge, v, new Vec3b());
    }

    public static Vec3b step(final byte edge, final Vec3b v, final Vec3b res) {
        res.x = v.x < edge ? (byte) 0 : (byte) 1;
        res.y = v.y < edge ? (byte) 0 : (byte) 1;
        res.z = v.z < edge ? (byte) 0 : (byte) 1;
        return res;
    }

    public static Vec3d step_(final double edge, final Vec3d v) {
        return step(edge, v, new Vec3d());
    }

    public static Vec3d step(final double edge, final Vec3d v, final Vec3d res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        res.z = v.z < edge ? 0 : 1;
        return res;
    }

    public static Vec3 step_(final float edge, final Vec3 v) {
        return step(edge, v, new Vec3());
    }

    public static Vec3 step(final float edge, final Vec3 v, final Vec3 res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        res.z = v.z < edge ? 0 : 1;
        return res;
    }

    public static Vec3i step_(final int edge, final Vec3i v) {
        return step(edge, v, new Vec3i());
    }

    public static Vec3i step(final int edge, final Vec3i v, final Vec3i res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        res.z = v.z < edge ? 0 : 1;
        return res;
    }

    public static Vec3l step_(final long edge, final Vec3l v) {
        return step(edge, v, new Vec3l());
    }

    public static Vec3l step(final long edge, final Vec3l v, final Vec3l res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        res.z = v.z < edge ? 0 : 1;
        return res;
    }

    public static Vec3s step_(final short edge, final Vec3s v) {
        return step(edge, v, new Vec3s());
    }

    public static Vec3s step(final short edge, final Vec3s v, final Vec3s res) {
        res.x = v.x < edge ? (short) 0 : (short) 1;
        res.y = v.y < edge ? (short) 0 : (short) 1;
        res.z = v.z < edge ? (short) 0 : (short) 1;
        return res;
    }

    public static Vec3ub step_(final UByte edge, final Vec3ub v) {
        return step(edge, v, new Vec3ub());
    }

    public static Vec3ub step(final UByte edge, final Vec3ub v, final Vec3ub res) {
        res.x.value = (byte) (v.x.compareTo(edge) < 0 ? 0 : 1);
        res.y.value = (byte) (v.y.compareTo(edge) < 0 ? 0 : 1);
        res.z.value = (byte) (v.z.compareTo(edge) < 0 ? 0 : 1);
        return res;
    }

    public static Vec3ui step_(final UInt edge, final Vec3ui v) {
        return step(edge, v, new Vec3ui());
    }

    public static Vec3ui step(final UInt edge, final Vec3ui v, final Vec3ui res) {
        res.x.value = v.x.compareTo(edge) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge) < 0 ? 0 : 1;
        res.z.value = v.z.compareTo(edge) < 0 ? 0 : 1;
        return res;
    }

    public static Vec3ul step_(final ULong edge, final Vec3ul v) {
        return step(edge, v, new Vec3ul());
    }

    public static Vec3ul step(final ULong edge, final Vec3ul v, final Vec3ul res) {
        res.x.value = v.x.compareTo(edge) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge) < 0 ? 0 : 1;
        res.z.value = v.z.compareTo(edge) < 0 ? 0 : 1;
        return res;
    }

    public static Vec3us step_(final UShort edge, final Vec3us v) {
        return step(edge, v, new Vec3us());
    }

    public static Vec3us step(final UShort edge, final Vec3us v, final Vec3us res) {
        res.x.value = (short) (v.x.compareTo(edge) < 0 ? 0 : 1);
        res.y.value = (short) (v.y.compareTo(edge) < 0 ? 0 : 1);
        res.z.value = (short) (v.z.compareTo(edge) < 0 ? 0 : 1);
        return res;
    }

    // step (scalara, vec4) ----------------------------------------------------
    public static Vec4b step_(final byte edge, final Vec4b v) {
        return step(edge, v, new Vec4b());
    }

    public static Vec4b step(final byte edge, final Vec4b v, final Vec4b res) {
        res.x = v.x < edge ? (byte) 0 : (byte) 1;
        res.y = v.y < edge ? (byte) 0 : (byte) 1;
        res.z = v.z < edge ? (byte) 0 : (byte) 1;
        return res;
    }

    public static Vec4d step_(final double edge, final Vec4d v) {
        return step(edge, v, new Vec4d());
    }

    public static Vec4d step(final double edge, final Vec4d v, final Vec4d res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        res.z = v.z < edge ? 0 : 1;
        res.w = v.w < edge ? 0 : 1;
        return res;
    }

    public static Vec4 step_(final float edge, final Vec4 v) {
        return step(edge, v, new Vec4());
    }

    public static Vec4 step(final float edge, final Vec4 v, final Vec4 res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        res.z = v.z < edge ? 0 : 1;
        res.w = v.w < edge ? 0 : 1;
        return res;
    }

    public static Vec4i step_(final int edge, final Vec4i v) {
        return step(edge, v, new Vec4i());
    }

    public static Vec4i step(final int edge, final Vec4i v, final Vec4i res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        res.z = v.z < edge ? 0 : 1;
        res.w = v.w < edge ? 0 : 1;
        return res;
    }

    public static Vec4l step_(final long edge, final Vec4l v) {
        return step(edge, v, new Vec4l());
    }

    public static Vec4l step(final long edge, final Vec4l v, final Vec4l res) {
        res.x = v.x < edge ? 0 : 1;
        res.y = v.y < edge ? 0 : 1;
        res.z = v.z < edge ? 0 : 1;
        res.w = v.w < edge ? 0 : 1;
        return res;
    }

    public static Vec4s step_(final short edge, final Vec4s v) {
        return step(edge, v, new Vec4s());
    }

    public static Vec4s step(final short edge, final Vec4s v, final Vec4s res) {
        res.x = v.x < edge ? (short) 0 : (short) 1;
        res.y = v.y < edge ? (short) 0 : (short) 1;
        res.z = v.z < edge ? (short) 0 : (short) 1;
        res.w = v.w < edge ? (short) 0 : (short) 1;
        return res;
    }

    public static Vec4ub step_(final UByte edge, final Vec4ub v) {
        return step(edge, v, new Vec4ub());
    }

    public static Vec4ub step(final UByte edge, final Vec4ub v, final Vec4ub res) {
        res.x.value = (byte) (v.x.compareTo(edge) < 0 ? 0 : 1);
        res.y.value = (byte) (v.y.compareTo(edge) < 0 ? 0 : 1);
        res.z.value = (byte) (v.z.compareTo(edge) < 0 ? 0 : 1);
        return res;
    }

    public static Vec4ui step_(final UInt edge, final Vec4ui v) {
        return step(edge, v, new Vec4ui());
    }

    public static Vec4ui step(final UInt edge, final Vec4ui v, final Vec4ui res) {
        res.x.value = v.x.compareTo(edge) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge) < 0 ? 0 : 1;
        res.z.value = v.z.compareTo(edge) < 0 ? 0 : 1;
        res.w.value = v.w.compareTo(edge) < 0 ? 0 : 1;
        return res;
    }

    public static Vec4ul step_(final ULong edge, final Vec4ul v) {
        return step(edge, v, new Vec4ul());
    }

    public static Vec4ul step(final ULong edge, final Vec4ul v, final Vec4ul res) {
        res.x.value = v.x.compareTo(edge) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge) < 0 ? 0 : 1;
        res.z.value = v.z.compareTo(edge) < 0 ? 0 : 1;
        res.w.value = v.w.compareTo(edge) < 0 ? 0 : 1;
        return res;
    }

    public static Vec4us step_(final UShort edge, final Vec4us v) {
        return step(edge, v, new Vec4us());
    }

    public static Vec4us step(final UShort edge, final Vec4us v, final Vec4us res) {
        res.x.value = (short) (v.x.compareTo(edge) < 0 ? 0 : 1);
        res.y.value = (short) (v.y.compareTo(edge) < 0 ? 0 : 1);
        res.z.value = (short) (v.z.compareTo(edge) < 0 ? 0 : 1);
        res.w.value = (short) (v.w.compareTo(edge) < 0 ? 0 : 1);
        return res;
    }

    // step (vec2, vec2) -----------------------------------------------------
    public static Vec2b step_(final Vec2b edge, final Vec2b v) {
        return step(edge, v, new Vec2b());
    }

    public static Vec2b step(final Vec2b edge, final Vec2b v, final Vec2b res) {
        res.x = v.x < edge.x ? (byte) 0 : (byte) 1;
        res.y = v.y < edge.y ? (byte) 0 : (byte) 1;
        return res;
    }

    public static Vec2d step_(final Vec2d edge, final Vec2d v) {
        return step(edge, v, new Vec2d());
    }

    public static Vec2d step(final Vec2d edge, final Vec2d v, final Vec2d res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        return res;
    }

    public static Vec2 step_(final Vec2 edge, final Vec2 v) {
        return step(edge, v, new Vec2());
    }

    public static Vec2 step(final Vec2 edge, final Vec2 v, final Vec2 res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        return res;
    }

    public static Vec2i step_(final Vec2i edge, final Vec2i v) {
        return step(edge, v, new Vec2i());
    }

    public static Vec2i step(final Vec2i edge, final Vec2i v, final Vec2i res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        return res;
    }

    public static Vec2l step_(final Vec2l edge, final Vec2l v) {
        return step(edge, v, new Vec2l());
    }

    public static Vec2l step(final Vec2l edge, final Vec2l v, final Vec2l res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        return res;
    }

    public static Vec2s step_(final Vec2s edge, final Vec2s v) {
        return step(edge, v, new Vec2s());
    }

    public static Vec2s step(final Vec2s edge, final Vec2s v, final Vec2s res) {
        res.x = v.x < edge.x ? (short) 0 : (short) 1;
        res.y = v.y < edge.y ? (short) 0 : (short) 1;
        return res;
    }

    public static Vec2ub step_(final Vec2ub edge, final Vec2ub v) {
        return step(edge, v, new Vec2ub());
    }

    public static Vec2ub step(final Vec2ub edge, final Vec2ub v, final Vec2ub res) {
        res.x.value = (byte) (v.x.compareTo(edge.x) < 0 ? 0 : 1);
        res.y.value = (byte) (v.y.compareTo(edge.x) < 0 ? 0 : 1);
        return res;
    }

    public static Vec2ui step_(final Vec2ui edge, final Vec2ui v) {
        return step(edge, v, new Vec2ui());
    }

    public static Vec2ui step(final Vec2ui edge, final Vec2ui v, final Vec2ui res) {
        res.x.value = v.x.compareTo(edge.x) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge.y) < 0 ? 0 : 1;
        return res;
    }

    public static Vec2ul step_(final Vec2ul edge, final Vec2ul v) {
        return step(edge, v, new Vec2ul());
    }

    public static Vec2ul step(final Vec2ul edge, final Vec2ul v, final Vec2ul res) {
        res.x.value = v.x.compareTo(edge.x) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge.y) < 0 ? 0 : 1;
        return res;
    }

    public static Vec2us step_(final Vec2us edge, final Vec2us v) {
        return step(edge, v, new Vec2us());
    }

    public static Vec2us step(final Vec2us edge, final Vec2us v, final Vec2us res) {
        res.x.value = (short) (v.x.compareTo(edge.x) < 0 ? 0 : 1);
        res.y.value = (short) (v.y.compareTo(edge.y) < 0 ? 0 : 1);
        return res;
    }

    // step (vec3, vec3) -------------------------------------------------------
    public static Vec3b step_(final Vec3b edge, final Vec3b v) {
        return step(edge, v, new Vec3b());
    }

    public static Vec3b step(final Vec3b edge, final Vec3b v, final Vec3b res) {
        res.x = v.x < edge.x ? (byte) 0 : (byte) 1;
        res.y = v.y < edge.y ? (byte) 0 : (byte) 1;
        res.z = v.z < edge.z ? (byte) 0 : (byte) 1;
        return res;
    }

    public static Vec3d step_(final Vec3d edge, final Vec3d v) {
        return step(edge, v, new Vec3d());
    }

    public static Vec3d step(final Vec3d edge, final Vec3d v, final Vec3d res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        res.z = v.z < edge.z ? 0 : 1;
        return res;
    }

    public static Vec3 step_(final Vec3 edge, final Vec3 v) {
        return step(edge, v, new Vec3());
    }

    public static Vec3 step(final Vec3 edge, final Vec3 v, final Vec3 res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        res.z = v.z < edge.z ? 0 : 1;
        return res;
    }

    public static Vec3i step_(final Vec3i edge, final Vec3i v) {
        return step(edge, v, new Vec3i());
    }

    public static Vec3i step(final Vec3i edge, final Vec3i v, final Vec3i res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        res.z = v.z < edge.z ? 0 : 1;
        return res;
    }

    public static Vec3l step_(final Vec3l edge, final Vec3l v) {
        return step(edge, v, new Vec3l());
    }

    public static Vec3l step(final Vec3l edge, final Vec3l v, final Vec3l res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        res.z = v.z < edge.z ? 0 : 1;
        return res;
    }

    public static Vec3s step_(final Vec3s edge, final Vec3s v) {
        return step(edge, v, new Vec3s());
    }

    public static Vec3s step(final Vec3s edge, final Vec3s v, final Vec3s res) {
        res.x = v.x < edge.x ? (short) 0 : (short) 1;
        res.y = v.y < edge.y ? (short) 0 : (short) 1;
        res.z = v.z < edge.z ? (short) 0 : (short) 1;
        return res;
    }

    public static Vec3ub step_(final Vec3ub edge, final Vec3ub v) {
        return step(edge, v, new Vec3ub());
    }

    public static Vec3ub step(final Vec3ub edge, final Vec3ub v, final Vec3ub res) {
        res.x.value = (byte) (v.x.compareTo(edge.x) < 0 ? 0 : 1);
        res.y.value = (byte) (v.y.compareTo(edge.y) < 0 ? 0 : 1);
        res.z.value = (byte) (v.z.compareTo(edge.z) < 0 ? 0 : 1);
        return res;
    }

    public static Vec3ui step_(final Vec3ui edge, final Vec3ui v) {
        return step(edge, v, new Vec3ui());
    }

    public static Vec3ui step(final Vec3ui edge, final Vec3ui v, final Vec3ui res) {
        res.x.value = v.x.compareTo(edge.x) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge.y) < 0 ? 0 : 1;
        res.z.value = v.z.compareTo(edge.z) < 0 ? 0 : 1;
        return res;
    }

    public static Vec3ul step_(final Vec3ul edge, final Vec3ul v) {
        return step(edge, v, new Vec3ul());
    }

    public static Vec3ul step(final Vec3ul edge, final Vec3ul v, final Vec3ul res) {
        res.x.value = v.x.compareTo(edge.x) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge.y) < 0 ? 0 : 1;
        res.z.value = v.z.compareTo(edge.z) < 0 ? 0 : 1;
        return res;
    }

    public static Vec3us step_(final Vec3us edge, final Vec3us v) {
        return step(edge, v, new Vec3us());
    }

    public static Vec3us step(final Vec3us edge, final Vec3us v, final Vec3us res) {
        res.x.value = (short) (v.x.compareTo(edge.x) < 0 ? 0 : 1);
        res.y.value = (short) (v.y.compareTo(edge.y) < 0 ? 0 : 1);
        res.z.value = (short) (v.z.compareTo(edge.z) < 0 ? 0 : 1);
        return res;
    }

    // step (vec4, vec4) -------------------------------------------------------
    public static Vec4b step_(final Vec4b edge, final Vec4b v) {
        return step(edge, v, new Vec4b());
    }

    public static Vec4b step(final Vec4b edge, final Vec4b v, final Vec4b res) {
        res.x = v.x < edge.x ? (byte) 0 : (byte) 1;
        res.y = v.y < edge.y ? (byte) 0 : (byte) 1;
        res.z = v.z < edge.z ? (byte) 0 : (byte) 1;
        res.w = v.w < edge.w ? (byte) 0 : (byte) 1;
        return res;
    }

    public static Vec4d step_(final Vec4d edge, final Vec4d v) {
        return step(edge, v, new Vec4d());
    }

    public static Vec4d step(final Vec4d edge, final Vec4d v, final Vec4d res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        res.z = v.z < edge.z ? 0 : 1;
        res.w = v.w < edge.w ? 0 : 1;
        return res;
    }

    public static Vec4 step_(final Vec4 edge, final Vec4 v) {
        return step(edge, v, new Vec4());
    }

    public static Vec4 step(final Vec4 edge, final Vec4 v, final Vec4 res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        res.z = v.z < edge.z ? 0 : 1;
        res.w = v.w < edge.w ? 0 : 1;
        return res;
    }

    public static Vec4i step_(final Vec4i edge, final Vec4i v) {
        return step(edge, v, new Vec4i());
    }

    public static Vec4i step(final Vec4i edge, final Vec4i v, final Vec4i res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        res.z = v.z < edge.z ? 0 : 1;
        res.w = v.w < edge.w ? 0 : 1;
        return res;
    }

    public static Vec4l step_(final Vec4l edge, final Vec4l v) {
        return step(edge, v, new Vec4l());
    }

    public static Vec4l step(final Vec4l edge, final Vec4l v, final Vec4l res) {
        res.x = v.x < edge.x ? 0 : 1;
        res.y = v.y < edge.y ? 0 : 1;
        res.z = v.z < edge.z ? 0 : 1;
        res.w = v.w < edge.w ? 0 : 1;
        return res;
    }

    public static Vec4s step_(final Vec4s edge, final Vec4s v) {
        return step(edge, v, new Vec4s());
    }

    public static Vec4s step(final Vec4s edge, final Vec4s v, final Vec4s res) {
        res.x = v.x < edge.x ? (short) 0 : (short) 1;
        res.y = v.y < edge.y ? (short) 0 : (short) 1;
        res.z = v.z < edge.z ? (short) 0 : (short) 1;
        res.w = v.w < edge.w ? (short) 0 : (short) 1;
        return res;
    }

    public static Vec4ub step_(final Vec4ub edge, final Vec4ub v) {
        return step(edge, v, new Vec4ub());
    }

    public static Vec4ub step(final Vec4ub edge, final Vec4ub v, final Vec4ub res) {
        res.x.value = (byte) (v.x.compareTo(edge.x) < 0 ? 0 : 1);
        res.y.value = (byte) (v.y.compareTo(edge.y) < 0 ? 0 : 1);
        res.z.value = (byte) (v.z.compareTo(edge.z) < 0 ? 0 : 1);
        res.w.value = (byte) (v.w.compareTo(edge.w) < 0 ? 0 : 1);
        return res;
    }

    public static Vec4ui step_(final Vec4ui edge, final Vec4ui v) {
        return step(edge, v, new Vec4ui());
    }

    public static Vec4ui step(final Vec4ui edge, final Vec4ui v, final Vec4ui res) {
        res.x.value = v.x.compareTo(edge.x) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge.y) < 0 ? 0 : 1;
        res.z.value = v.z.compareTo(edge.z) < 0 ? 0 : 1;
        res.w.value = v.w.compareTo(edge.w) < 0 ? 0 : 1;
        return res;
    }

    public static Vec4ul step_(final Vec4ul edge, final Vec4ul v) {
        return step(edge, v, new Vec4ul());
    }

    public static Vec4ul step(final Vec4ul edge, final Vec4ul v, final Vec4ul res) {
        res.x.value = v.x.compareTo(edge.x) < 0 ? 0 : 1;
        res.y.value = v.y.compareTo(edge.y) < 0 ? 0 : 1;
        res.z.value = v.z.compareTo(edge.z) < 0 ? 0 : 1;
        res.w.value = v.w.compareTo(edge.w) < 0 ? 0 : 1;
        return res;
    }

    public static Vec4us step_(final Vec4us edge, final Vec4us v) {
        return step(edge, v, new Vec4us());
    }

    public static Vec4us step(final Vec4us edge, final Vec4us v, final Vec4us res) {
        res.x.value = (short) (v.x.compareTo(edge.x) < 0 ? 0 : 1);
        res.y.value = (short) (v.y.compareTo(edge.y) < 0 ? 0 : 1);
        res.z.value = (short) (v.z.compareTo(edge.z) < 0 ? 0 : 1);
        res.w.value = (short) (v.w.compareTo(edge.w) < 0 ? 0 : 1);
        return res;
    }

    // trunc -------------------------------------------------------------------
    public static double trunc(final float f) {
        return f < 0 ? -floor(-f) : floor(f);
    }

    public static double trunc(final double d) {
        return d < 0 ? -floor(-d) : floor(d);
    }

    public static Vec2 trunc_(final Vec2 v) {
        return trunc(v, new Vec2());
    }

    public static Vec2 trunc(final Vec2 v, final Vec2 res) {
        return res.set(trunc(v.x), trunc(v.y));
    }

    public static Vec2d trunc_(final Vec2d v) {
        return trunc(v, new Vec2d());
    }

    public static Vec2d trunc(final Vec2d v, final Vec2d res) {
        return res.set(trunc(v.x), trunc(v.y));
    }

    public static Vec3 trunc_(final Vec3 v) {
        return trunc(v, new Vec3());
    }

    public static Vec3 trunc(final Vec3 v, final Vec3 res) {
        return res.set(trunc(v.x), trunc(v.y), trunc(v.z));
    }

    public static Vec3d trunc_(final Vec3d v) {
        return trunc(v, new Vec3d());
    }

    public static Vec3d trunc(final Vec3d v, final Vec3d res) {
        return res.set(trunc(v.x), trunc(v.y), trunc(v.z));
    }

    public static Vec4 trunc_(final Vec4 v) {
        return trunc(v, new Vec4());
    }

    public static Vec4 trunc(final Vec4 v, final Vec4 res) {
        return res.set(trunc(v.x), trunc(v.y), trunc(v.z), trunc(v.w));
    }

    public static Vec4d trunc_(final Vec4d v) {
        return trunc(v, new Vec4d());
    }

    public static Vec4d trunc(final Vec4d v, final Vec4d res) {
        return res.set(trunc(v.x), trunc(v.y), trunc(v.z), trunc(v.w));
    }

    /**
     * Compare two floating points for equality within a margin of error.
     *
     * This can be used to compensate for inequality caused by accumulated
     * floating point math errors.
     *
     * The error margin is specified in ULPs (units of least precision). A
     * one-ULP difference means there are no representable floats in between.
     * E.g. 0f and 1.4e-45f are one ULP apart. So are -6.1340704f and -6.13407f.
     * Depending on the number of calculations involved, typically a margin of
     * 1-5 ULPs should be enough.
     *
     * @param expected The expected value.
     * @param actual The actual value.
     * @param maxUlps The maximum difference in ULPs.
     * @return
     */
    public static boolean compareFloatEquals(final float expected, final float actual) {
        return compareFloatEquals(expected, actual, MAX_ULPS);
    }

    public static boolean compareFloatEquals(final float expected, final float actual, final int maxUlps) {

        final int expectedBits = Float.floatToIntBits(expected) < 0
                ? 0x80000000 - Float.floatToIntBits(expected) : Float.floatToIntBits(expected);
        final int actualBits = Float.floatToIntBits(actual) < 0
                ? 0x80000000 - Float.floatToIntBits(actual) : Float.floatToIntBits(actual);
        final int difference = expectedBits > actualBits ? expectedBits - actualBits : actualBits - expectedBits;
        if (difference > maxUlps) {
//            System.out.println("expected: " + expected + ", actual: " + actual);
//            System.out.println("diff " + difference);
        }
        return !Float.isNaN(expected) && !Float.isNaN(actual) && Math.abs(difference) <= maxUlps;
    }

    public static boolean compareDoubleEquals(final double expected, final double actual) {
        return compareDoubleEquals(expected, actual, MAX_ULPS);
    }

    public static boolean compareDoubleEquals(final double expected, final double actual, final int maxUlps) {
        final long expectedBits = Double.doubleToLongBits(expected) < 0 ? 0x8000000000000000L
                - Double.doubleToLongBits(expected) : Double.doubleToLongBits(expected);
        final long actualBits = Double.doubleToLongBits(actual) < 0
                ? 0x8000000000000000L - Double.doubleToLongBits(actual) : Double.doubleToLongBits(actual);
        final long difference = expectedBits > actualBits ? expectedBits - actualBits : actualBits - expectedBits;
        if (difference > maxUlps) {
//            System.out.println("expected: " + expected + ", actual: " + actual);
//            System.out.println("diff " + difference);
        }
        return !Double.isNaN(expected) && !Double.isNaN(actual) && Math.abs(difference) <= maxUlps;
    }
}
