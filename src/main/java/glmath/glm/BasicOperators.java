/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

import glmath.glm.vec._2.Vec2;
import glmath.glm.vec._2.b.Vec2b;
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
abstract class BasicOperators extends SpecialOperators {

    public static Vec2 add_(final Vec2 a, final float b) {
        return add(new Vec2(), a, b, b);
    }

    public static Vec2 add_(final Vec2 a, final Vec2 b) {
        return add(new Vec2(), a, b.x, b.y);
    }

    public static Vec2 add(final Vec2 a, final float b) {
        return add(a, a, b, b);
    }

    public static Vec2 add(final Vec2 a, final Vec2 b) {
        return add(a, a, b.x, b.y);
    }

    public static Vec2 add(final Vec2 res, final Vec2 a, final float b) {
        return add(res, a, b, b);
    }

    public static Vec2 add(final Vec2 res, final Vec2 a, final Vec2 b) {
        return add(res, a, b.x, b.y);
    }

    public static Vec2 add(final Vec2 res, final Vec2 a, final float bX, final float bY) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        return res;
    }

    public static Vec2b add_(final Vec2b a, final byte b) {
        return add(new Vec2b(), a, (int) b, b);
    }

    public static Vec2b add_(final Vec2b a, final int b) {
        return add(new Vec2b(), a, b, b);
    }

    public static Vec2b add_(final Vec2b a, final Vec2b b) {
        return add(new Vec2b(), a, (int) b.x, b.y);
    }

    public static Vec2b add(final Vec2b a, final byte b) {
        return add(a, a, (int) b, b);
    }

    public static Vec2b add(final Vec2b a, final int b) {
        return add(a, a, b, b);
    }

    public static Vec2b add(final Vec2b a, final Vec2b b) {
        return add(a, a, (int) b.x, b.y);
    }

    public static Vec2b add(final Vec2b res, final Vec2b a, final byte b) {
        return add(res, a, (int) b, b);
    }

    public static Vec2b add(final Vec2b res, final Vec2b a, final int b) {
        return add(res, a, b, b);
    }

    public static Vec2b add(final Vec2b res, final Vec2b a, final Vec2b b) {
        return add(res, a, (int) b.x, b.y);
    }

    public static Vec2b add(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return add(res, a, (int) bX, bY);
    }

    public static Vec2b add(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x + bX);
        res.y = (byte) (a.y + bY);
        return res;
    }

    public static Vec2d add_(final Vec2d a, final double b) {
        return add(new Vec2d(), a, b, b);
    }

    public static Vec2d add_(final Vec2d a, final Vec2d b) {
        return add(new Vec2d(), a, b.x, b.y);
    }

    public static Vec2d add(final Vec2d a, final double b) {
        return add(a, a, b, b);
    }

    public static Vec2d add(final Vec2d a, final Vec2d b) {
        return add(a, a, b.x, b.y);
    }

    public static Vec2d add(final Vec2d res, final Vec2d a, final double b) {
        return add(res, a, b, b);
    }

    public static Vec2d add(final Vec2d res, final Vec2d a, final Vec2d b) {
        return add(res, a, b.x, b.y);
    }

    public static Vec2d add(final Vec2d res, final Vec2d a, final double bX, final double bY) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        return res;
    }

    public static Vec2i add_(final Vec2i a, final int b) {
        return add(new Vec2i(), a, b, b);
    }

    public static Vec2i add_(final Vec2i a, final Vec2i b) {
        return add(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i add(final Vec2i a, final int b) {
        return add(a, a, b, b);
    }

    public static Vec2i add(final Vec2i a, final Vec2i b) {
        return add(a, a, b.x, b.y);
    }

    public static Vec2i add(final Vec2i res, final Vec2i a, final int b) {
        return add(res, a, b, b);
    }

    public static Vec2i add(final Vec2i res, final Vec2i a, final Vec2i b) {
        return add(res, a, b.x, b.y);
    }

    public static Vec2i add(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        return res;
    }

    public static Vec2l add_(final Vec2l a, final long b) {
        return add(new Vec2l(), a, b, b);
    }

    public static Vec2l add_(final Vec2l a, final Vec2l b) {
        return add(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l add(final Vec2l a, final long b) {
        return add(a, a, b, b);
    }

    public static Vec2l add(final Vec2l a, final Vec2l b) {
        return add(a, a, b.x, b.y);
    }

    public static Vec2l add(final Vec2l res, final Vec2l a, final long b) {
        return add(res, a, b, b);
    }

    public static Vec2l add(final Vec2l res, final Vec2l a, final Vec2l b) {
        return add(res, a, b.x, b.y);
    }

    public static Vec2l add(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        return res;
    }

    public static Vec2s add_(final Vec2s a, final short b) {
        return add(new Vec2s(), a, (int) b, b);
    }

    public static Vec2s add_(final Vec2s a, final int b) {
        return add(new Vec2s(), a, b, b);
    }

    public static Vec2s add_(final Vec2s a, final Vec2s b) {
        return add(new Vec2s(), a, (int) b.x, b.y);
    }

    public static Vec2s add(final Vec2s a, final short b) {
        return add(a, a, (int) b, b);
    }

    public static Vec2s add(final Vec2s a, final int b) {
        return add(a, a, b, b);
    }

    public static Vec2s add(final Vec2s a, final Vec2s b) {
        return add(a, a, (int) b.x, b.y);
    }

    public static Vec2s add(final Vec2s res, final Vec2s a, final short b) {
        return add(res, a, (int) b, b);
    }

    public static Vec2s add(final Vec2s res, final Vec2s a, final int b) {
        return add(res, a, b, b);
    }

    public static Vec2s add(final Vec2s res, final Vec2s a, final Vec2s b) {
        return add(res, a, (int) b.x, b.y);
    }

    public static Vec2s add(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return add(res, a, (int) bX, bY);
    }

    public static Vec2s add(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x + bX);
        res.y = (short) (a.y + bY);
        return res;
    }

    public static Vec2ub add_(final Vec2ub a, final UByte b) {
        return add(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub add_(final Vec2ub a, final byte b) {
        return add(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub add_(final Vec2ub a, final int b) {
        return add(new Vec2ub(), a, b, b);
    }

    public static Vec2ub add_(final Vec2ub a, final Vec2ub b) {
        return add(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub add(final Vec2ub a, final UByte b) {
        return add(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub add(final Vec2ub a, final byte b) {
        return add(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub add(final Vec2ub a, final int b) {
        return add(a, a, b, b);
    }

    public static Vec2ub add(final Vec2ub a, final Vec2ub b) {
        return add(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final Vec2ub a, final UByte b) {
        return add(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final Vec2ub a, final byte b) {
        return add(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final Vec2ub a, final int b) {
        return add(res, a, b, b);
    }

    public static Vec2ub add(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return add(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return add(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return add(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) ((a.x.value & 0xff) + bX);
        res.y.value = (byte) ((a.y.value & 0xff) + bY);
        return res;
    }

    public static Vec2ui add_(final Vec2ui a, final UInt b) {
        return add(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui add_(final Vec2ui a, final int b) {
        return add(new Vec2ui(), a, b, b);
    }

    public static Vec2ui add_(final Vec2ui a, final Vec2ui b) {
        return add(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui add(final Vec2ui a, final UInt b) {
        return add(a, a, b.value, b.value);
    }

    public static Vec2ui add(final Vec2ui a, final int b) {
        return add(a, a, b, b);
    }

    public static Vec2ui add(final Vec2ui a, final Vec2ui b) {
        return add(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui add(final Vec2ui res, final Vec2ui a, final UInt b) {
        return add(res, a, b.value, b.value);
    }

    public static Vec2ui add(final Vec2ui res, final Vec2ui a, final int b) {
        return add(res, a, b, b);
    }

    public static Vec2ui add(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return add(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui add(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return add(res, a, bX.value, bY.value);
    }

    public static Vec2ui add(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = a.x.value + bX;
        res.y.value = a.y.value + bY;
        return res;
    }

    public static Vec2ul add_(final Vec2ul a, final ULong b) {
        return add(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul add_(final Vec2ul a, final long b) {
        return add(new Vec2ul(), a, b, b);
    }

    public static Vec2ul add_(final Vec2ul a, final Vec2ul b) {
        return add(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul add(final Vec2ul a, final ULong b) {
        return add(a, a, b.value, b.value);
    }

    public static Vec2ul add(final Vec2ul a, final long b) {
        return add(a, a, b, b);
    }

    public static Vec2ul add(final Vec2ul a, final Vec2ul b) {
        return add(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul add(final Vec2ul res, final Vec2ul a, final ULong b) {
        return add(res, a, b.value, b.value);
    }

    public static Vec2ul add(final Vec2ul res, final Vec2ul a, final long b) {
        return add(res, a, b, b);
    }

    public static Vec2ul add(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return add(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul add(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return add(res, a, bX.value, bY.value);
    }

    public static Vec2ul add(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = a.x.value + bX;
        res.y.value = a.y.value + bY;
        return res;
    }

    public static Vec2us add_(final Vec2us a, final UShort b) {
        return add(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us add_(final Vec2us a, final short b) {
        return add(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us add_(final Vec2us a, final int b) {
        return add(new Vec2us(), a, b, b);
    }

    public static Vec2us add_(final Vec2us a, final Vec2us b) {
        return add(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us add(final Vec2us a, final UShort b) {
        return add(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us add(final Vec2us a, final short b) {
        return add(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us add(final Vec2us a, final int b) {
        return add(a, a, b, b);
    }

    public static Vec2us add(final Vec2us a, final Vec2us b) {
        return add(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final Vec2us a, final UShort b) {
        return add(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final Vec2us a, final short b) {
        return add(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final Vec2us a, final int b) {
        return add(res, a, b, b);
    }

    public static Vec2us add(final Vec2us res, final Vec2us a, final Vec2us b) {
        return add(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return add(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return add(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) ((a.x.value & 0xffff) + bX);
        res.y.value = (short) ((a.y.value & 0xffff) + bY);
        return res;
    }

    public static Vec3 add_(final Vec3 a, final float b) {
        return add(new Vec3(), a, b, b, b);
    }

    public static Vec3 add_(final Vec3 a, final Vec3 b) {
        return add(new Vec3(), a, b.x, b.y, b.z);
    }

    public static Vec3 add(final Vec3 a, final float b) {
        return add(a, a, b, b, b);
    }

    public static Vec3 add(final Vec3 a, final Vec3 b) {
        return add(a, a, b.x, b.y, b.z);
    }

    public static Vec3 add(final Vec3 res, final Vec3 a, final float b) {
        return add(res, a, b, b, b);
    }

    public static Vec3 add(final Vec3 res, final Vec3 a, final Vec3 b) {
        return add(res, a, b.x, b.y, b.z);
    }

    public static Vec3 add(final Vec3 res, final Vec3 a, final float bX, final float bY, final float bZ) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        res.z = a.z + bZ;
        return res;
    }

    public static Vec3b add_(final Vec3b a, final byte b) {
        return add(new Vec3b(), a, b, b, b);
    }

    public static Vec3b add_(final Vec3b a, final int b) {
        return add(new Vec3b(), a, b, b, b);
    }

    public static Vec3b add_(final Vec3b a, final Vec3b b) {
        return add(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b add(final Vec3b a, final byte b) {
        return add(a, a, b, b, b);
    }

    public static Vec3b add(final Vec3b a, final int b) {
        return add(a, a, b, b, b);
    }

    public static Vec3b add(final Vec3b a, final Vec3b b) {
        return add(a, a, b.x, b.y, b.z);
    }

    public static Vec3b add(final Vec3b res, final Vec3b a, final byte b) {
        return add(res, a, b, b, b);
    }

    public static Vec3b add(final Vec3b res, final Vec3b a, final int b) {
        return add(res, a, b, b, b);
    }

    public static Vec3b add(final Vec3b res, final Vec3b a, final Vec3b b) {
        return add(res, a, b.x, b.y, b.z);
    }

    public static Vec3b add(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return add(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b add(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x + bX);
        res.y = (byte) (a.y + bY);
        res.z = (byte) (a.z + bZ);
        return res;
    }

    public static Vec3d add_(final Vec3d a, final double b) {
        return add(new Vec3d(), a, b, b, b);
    }

    public static Vec3d add_(final Vec3d a, final Vec3d b) {
        return add(new Vec3d(), a, b.x, b.y, b.z);
    }

    public static Vec3d add(final Vec3d a, final double b) {
        return add(a, a, b, b, b);
    }

    public static Vec3d add(final Vec3d a, final Vec3d b) {
        return add(a, a, b.x, b.y, b.z);
    }

    public static Vec3d add(final Vec3d res, final Vec3d a, final double b) {
        return add(res, a, b, b, b);
    }

    public static Vec3d add(final Vec3d res, final Vec3d a, final Vec3d b) {
        return add(res, a, b.x, b.y, b.z);
    }

    public static Vec3d add(final Vec3d res, final Vec3d a, final double bX, final double bY, final double bZ) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        res.z = a.z + bZ;
        return res;
    }

    public static Vec3i add_(final Vec3i a, final int b) {
        return add(new Vec3i(), a, b, b, b);
    }

    public static Vec3i add_(final Vec3i a, final Vec3i b) {
        return add(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i add(final Vec3i a, final int b) {
        return add(a, a, b, b, b);
    }

    public static Vec3i add(final Vec3i a, final Vec3i b) {
        return add(a, a, b.x, b.y, b.z);
    }

    public static Vec3i add(final Vec3i res, final Vec3i a, final int b) {
        return add(res, a, b, b, b);
    }

    public static Vec3i add(final Vec3i res, final Vec3i a, final Vec3i b) {
        return add(res, a, b.x, b.y, b.z);
    }

    public static Vec3i add(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        res.z = a.z + bZ;
        return res;
    }

    public static Vec3l add_(final Vec3l a, final long b) {
        return add(new Vec3l(), a, b, b, b);
    }

    public static Vec3l add_(final Vec3l a, final Vec3l b) {
        return add(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l add(final Vec3l a, final long b) {
        return add(a, a, b, b, b);
    }

    public static Vec3l add(final Vec3l a, final Vec3l b) {
        return add(a, a, b.x, b.y, b.z);
    }

    public static Vec3l add(final Vec3l res, final Vec3l a, final long b) {
        return add(res, a, b, b, b);
    }

    public static Vec3l add(final Vec3l res, final Vec3l a, final Vec3l b) {
        return add(res, a, b.x, b.y, b.z);
    }

    public static Vec3l add(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        res.z = a.z + bZ;
        return res;
    }

    public static Vec3s add_(final Vec3s a, final short b) {
        return add(new Vec3s(), a, b, b, b);
    }

    public static Vec3s add_(final Vec3s a, final int b) {
        return add(new Vec3s(), a, b, b, b);
    }

    public static Vec3s add_(final Vec3s a, final Vec3s b) {
        return add(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s add(final Vec3s a, final short b) {
        return add(a, a, b, b, b);
    }

    public static Vec3s add(final Vec3s a, final int b) {
        return add(a, a, b, b, b);
    }

    public static Vec3s add(final Vec3s a, final Vec3s b) {
        return add(a, a, b.x, b.y, b.z);
    }

    public static Vec3s add(final Vec3s res, final Vec3s a, final short b) {
        return add(res, a, b, b, b);
    }

    public static Vec3s add(final Vec3s res, final Vec3s a, final int b) {
        return add(res, a, b, b, b);
    }

    public static Vec3s add(final Vec3s res, final Vec3s a, final Vec3s b) {
        return add(res, a, b.x, b.y, b.z);
    }

    public static Vec3s add(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return add(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s add(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x + bX);
        res.y = (short) (a.y + bY);
        res.z = (short) (a.z + bZ);
        return res;
    }

    public static Vec3ub add_(final Vec3ub a, final UByte b) {
        return add(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub add_(final Vec3ub a, final byte b) {
        return add(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub add_(final Vec3ub a, final int b) {
        return add(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub add_(final Vec3ub a, final Vec3ub b) {
        return add(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub add(final Vec3ub a, final UByte b) {
        return add(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub add(final Vec3ub a, final byte b) {
        return add(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub add(final Vec3ub a, final int b) {
        return add(a, a, b, b, b);
    }

    public static Vec3ub add(final Vec3ub a, final Vec3ub b) {
        return add(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final Vec3ub a, final UByte b) {
        return add(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final Vec3ub a, final byte b) {
        return add(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final Vec3ub a, final int b) {
        return add(res, a, b, b, b);
    }

    public static Vec3ub add(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return add(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return add(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return add(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) ((a.x.value & 0xff) + bX);
        res.y.value = (byte) ((a.y.value & 0xff) + bY);
        res.z.value = (byte) ((a.z.value & 0xff) + bZ);
        return res;
    }

    public static Vec3ui add_(final Vec3ui a, final UInt b) {
        return add(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui add_(final Vec3ui a, final int b) {
        return add(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui add_(final Vec3ui a, final Vec3ui b) {
        return add(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui add(final Vec3ui a, final UInt b) {
        return add(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui add(final Vec3ui a, final int b) {
        return add(a, a, b, b, b);
    }

    public static Vec3ui add(final Vec3ui a, final Vec3ui b) {
        return add(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui add(final Vec3ui res, final Vec3ui a, final UInt b) {
        return add(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui add(final Vec3ui res, final Vec3ui a, final int b) {
        return add(res, a, b, b, b);
    }

    public static Vec3ui add(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return add(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui add(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return add(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui add(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = a.x.value + bX;
        res.y.value = a.y.value + bY;
        res.z.value = a.z.value + bZ;
        return res;
    }

    public static Vec3ul add_(final Vec3ul a, final ULong b) {
        return add(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul add_(final Vec3ul a, final long b) {
        return add(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul add_(final Vec3ul a, final Vec3ul b) {
        return add(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul add(final Vec3ul a, final ULong b) {
        return add(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul add(final Vec3ul a, final long b) {
        return add(a, a, b, b, b);
    }

    public static Vec3ul add(final Vec3ul a, final Vec3ul b) {
        return add(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul add(final Vec3ul res, final Vec3ul a, final ULong b) {
        return add(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul add(final Vec3ul res, final Vec3ul a, final long b) {
        return add(res, a, b, b, b);
    }

    public static Vec3ul add(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return add(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul add(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return add(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul add(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = a.x.value + bX;
        res.y.value = a.y.value + bY;
        res.z.value = a.z.value + bZ;
        return res;
    }

    public static Vec3us add_(final Vec3us a, final UShort b) {
        return add(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us add_(final Vec3us a, final short b) {
        return add(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us add_(final Vec3us a, final int b) {
        return add(new Vec3us(), a, b, b, b);
    }

    public static Vec3us add_(final Vec3us a, final Vec3us b) {
        return add(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us add(final Vec3us a, final UShort b) {
        return add(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us add(final Vec3us a, final short b) {
        return add(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us add(final Vec3us a, final int b) {
        return add(a, a, b, b, b);
    }

    public static Vec3us add(final Vec3us a, final Vec3us b) {
        return add(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final Vec3us a, final UShort b) {
        return add(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final Vec3us a, final short b) {
        return add(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final Vec3us a, final int b) {
        return add(res, a, b, b, b);
    }

    public static Vec3us add(final Vec3us res, final Vec3us a, final Vec3us b) {
        return add(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return add(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return add(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) ((a.x.value & 0xffff) + bX);
        res.y.value = (short) ((a.y.value & 0xffff) + bY);
        res.z.value = (short) ((a.z.value & 0xffff) + bZ);
        return res;
    }

    public static Vec4 add_(final Vec4 a, final float b) {
        return add(new Vec4(), a, b, b, b, b);
    }

    public static Vec4 add_(final Vec4 a, final Vec4 b) {
        return add(new Vec4(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 add(final Vec4 a, final float b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4 add(final Vec4 a, final Vec4 b) {
        return add(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 add(final Vec4 res, final Vec4 a, final float b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4 add(final Vec4 res, final Vec4 a, final Vec4 b) {
        return add(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 add(final Vec4 res, final Vec4 a, final float bX, final float bY, final float bZ, final float bW) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        res.z = a.z + bZ;
        res.w = a.w + bW;
        return res;
    }

    public static Vec4b add_(final Vec4b a, final byte b) {
        return add(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b add_(final Vec4b a, final int b) {
        return add(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b add_(final Vec4b a, final Vec4b b) {
        return add(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b add(final Vec4b a, final byte b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4b add(final Vec4b a, final int b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4b add(final Vec4b a, final Vec4b b) {
        return add(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b add(final Vec4b res, final Vec4b a, final byte b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4b add(final Vec4b res, final Vec4b a, final int b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4b add(final Vec4b res, final Vec4b a, final Vec4b b) {
        return add(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b add(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return add(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b add(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x + bX);
        res.y = (byte) (a.y + bY);
        res.z = (byte) (a.z + bZ);
        res.w = (byte) (a.w + bW);
        return res;
    }

    public static Vec4d add_(final Vec4d a, final double b) {
        return add(new Vec4d(), a, b, b, b, b);
    }

    public static Vec4d add_(final Vec4d a, final Vec4d b) {
        return add(new Vec4d(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d add(final Vec4d a, final double b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4d add(final Vec4d a, final Vec4d b) {
        return add(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d add(final Vec4d res, final Vec4d a, final double b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4d add(final Vec4d res, final Vec4d a, final Vec4d b) {
        return add(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d add(final Vec4d res, final Vec4d a, final double bX, final double bY, final double bZ, final double bW) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        res.z = a.z + bZ;
        res.w = a.w + bW;
        return res;
    }

    public static Vec4i add_(final Vec4i a, final int b) {
        return add(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i add_(final Vec4i a, final Vec4i b) {
        return add(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i add(final Vec4i a, final int b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4i add(final Vec4i a, final Vec4i b) {
        return add(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i add(final Vec4i res, final Vec4i a, final int b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4i add(final Vec4i res, final Vec4i a, final Vec4i b) {
        return add(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i add(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        res.z = a.z + bZ;
        res.w = a.w + bW;
        return res;
    }

    public static Vec4l add_(final Vec4l a, final long b) {
        return add(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l add_(final Vec4l a, final Vec4l b) {
        return add(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l add(final Vec4l a, final long b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4l add(final Vec4l a, final Vec4l b) {
        return add(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l add(final Vec4l res, final Vec4l a, final long b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4l add(final Vec4l res, final Vec4l a, final Vec4l b) {
        return add(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l add(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x + bX;
        res.y = a.y + bY;
        res.z = a.z + bZ;
        res.w = a.w + bW;
        return res;
    }

    public static Vec4s add_(final Vec4s a, final short b) {
        return add(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s add_(final Vec4s a, final int b) {
        return add(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s add_(final Vec4s a, final Vec4s b) {
        return add(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s add(final Vec4s a, final short b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4s add(final Vec4s a, final int b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4s add(final Vec4s a, final Vec4s b) {
        return add(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s add(final Vec4s res, final Vec4s a, final short b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4s add(final Vec4s res, final Vec4s a, final int b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4s add(final Vec4s res, final Vec4s a, final Vec4s b) {
        return add(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s add(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return add(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s add(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x + bX);
        res.y = (short) (a.y + bY);
        res.z = (short) (a.z + bZ);
        res.w = (short) (a.w + bW);
        return res;
    }

    public static Vec4ub add_(final Vec4ub a, final UByte b) {
        return add(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub add_(final Vec4ub a, final byte b) {
        return add(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub add_(final Vec4ub a, final int b) {
        return add(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub add_(final Vec4ub a, final Vec4ub b) {
        return add(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub add(final Vec4ub a, final UByte b) {
        return add(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub add(final Vec4ub a, final byte b) {
        return add(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub add(final Vec4ub a, final int b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4ub add(final Vec4ub a, final Vec4ub b) {
        return add(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final Vec4ub a, final UByte b) {
        return add(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final Vec4ub a, final byte b) {
        return add(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final Vec4ub a, final int b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4ub add(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return add(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return add(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return add(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) ((a.x.value & 0xff) + bX);
        res.y.value = (byte) ((a.y.value & 0xff) + bY);
        res.z.value = (byte) ((a.z.value & 0xff) + bZ);
        res.w.value = (byte) ((a.w.value & 0xff) + bW);
        return res;
    }

    public static Vec4ui add_(final Vec4ui a, final UInt b) {
        return add(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui add_(final Vec4ui a, final int b) {
        return add(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui add_(final Vec4ui a, final Vec4ui b) {
        return add(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui add(final Vec4ui a, final UInt b) {
        return add(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui add(final Vec4ui a, final int b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4ui add(final Vec4ui a, final Vec4ui b) {
        return add(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui add(final Vec4ui res, final Vec4ui a, final UInt b) {
        return add(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui add(final Vec4ui res, final Vec4ui a, final int b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4ui add(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return add(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui add(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return add(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui add(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = a.x.value + bX;
        res.y.value = a.y.value + bY;
        res.z.value = a.z.value + bZ;
        res.w.value = a.w.value + bW;
        return res;
    }

    public static Vec4ul add_(final Vec4ul a, final ULong b) {
        return add(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul add_(final Vec4ul a, final long b) {
        return add(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul add_(final Vec4ul a, final Vec4ul b) {
        return add(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul add(final Vec4ul a, final ULong b) {
        return add(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul add(final Vec4ul a, final long b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4ul add(final Vec4ul a, final Vec4ul b) {
        return add(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul add(final Vec4ul res, final Vec4ul a, final ULong b) {
        return add(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul add(final Vec4ul res, final Vec4ul a, final long b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4ul add(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return add(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul add(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return add(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul add(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = a.x.value + bX;
        res.y.value = a.y.value + bY;
        res.z.value = a.z.value + bZ;
        res.w.value = a.w.value + bW;
        return res;
    }

    public static Vec4us add_(final Vec4us a, final UShort b) {
        return add(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us add_(final Vec4us a, final short b) {
        return add(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us add_(final Vec4us a, final int b) {
        return add(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us add_(final Vec4us a, final Vec4us b) {
        return add(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us add(final Vec4us a, final UShort b) {
        return add(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us add(final Vec4us a, final short b) {
        return add(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us add(final Vec4us a, final int b) {
        return add(a, a, b, b, b, b);
    }

    public static Vec4us add(final Vec4us a, final Vec4us b) {
        return add(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final Vec4us a, final UShort b) {
        return add(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final Vec4us a, final short b) {
        return add(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final Vec4us a, final int b) {
        return add(res, a, b, b, b, b);
    }

    public static Vec4us add(final Vec4us res, final Vec4us a, final Vec4us b) {
        return add(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return add(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return add(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) ((a.x.value & 0xffff) + bX);
        res.y.value = (short) ((a.y.value & 0xffff) + bY);
        res.z.value = (short) ((a.z.value & 0xffff) + bZ);
        res.w.value = (short) ((a.w.value & 0xffff) + bW);
        return res;
    }

    public static Vec2 sub_(final Vec2 a, final float b) {
        return sub(new Vec2(), a, b, b);
    }

    public static Vec2 sub_(final Vec2 a, final Vec2 b) {
        return sub(new Vec2(), a, b.x, b.y);
    }

    public static Vec2 sub(final Vec2 a, final float b) {
        return sub(a, a, b, b);
    }

    public static Vec2 sub(final Vec2 a, final Vec2 b) {
        return sub(a, a, b.x, b.y);
    }

    public static Vec2 sub(final Vec2 res, final Vec2 a, final float b) {
        return sub(res, a, b, b);
    }

    public static Vec2 sub(final Vec2 res, final Vec2 a, final Vec2 b) {
        return sub(res, a, b.x, b.y);
    }

    public static Vec2 sub(final Vec2 res, final Vec2 a, final float bX, final float bY) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        return res;
    }

    public static Vec2b sub_(final Vec2b a, final byte b) {
        return sub(new Vec2b(), a, b, b);
    }

    public static Vec2b sub_(final Vec2b a, final int b) {
        return sub(new Vec2b(), a, b, b);
    }

    public static Vec2b sub_(final Vec2b a, final Vec2b b) {
        return sub(new Vec2b(), a, b.x, b.y);
    }

    public static Vec2b sub(final Vec2b a, final byte b) {
        return sub(a, a, b, b);
    }

    public static Vec2b sub(final Vec2b a, final int b) {
        return sub(a, a, b, b);
    }

    public static Vec2b sub(final Vec2b a, final Vec2b b) {
        return sub(a, a, b.x, b.y);
    }

    public static Vec2b sub(final Vec2b res, final Vec2b a, final byte b) {
        return sub(res, a, b, b);
    }

    public static Vec2b sub(final Vec2b res, final Vec2b a, final int b) {
        return sub(res, a, b, b);
    }

    public static Vec2b sub(final Vec2b res, final Vec2b a, final Vec2b b) {
        return sub(res, a, b.x, b.y);
    }

    public static Vec2b sub(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return sub(res, a, (int) bX, bY);
    }

    public static Vec2b sub(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x - bX);
        res.y = (byte) (a.y - bY);
        return res;
    }

    public static Vec2d sub_(final Vec2d a, final double b) {
        return sub(new Vec2d(), a, b, b);
    }

    public static Vec2d sub_(final Vec2d a, final Vec2d b) {
        return sub(new Vec2d(), a, b.x, b.y);
    }

    public static Vec2d sub(final Vec2d a, final double b) {
        return sub(a, a, b, b);
    }

    public static Vec2d sub(final Vec2d a, final Vec2d b) {
        return sub(a, a, b.x, b.y);
    }

    public static Vec2d sub(final Vec2d res, final Vec2d a, final double b) {
        return sub(res, a, b, b);
    }

    public static Vec2d sub(final Vec2d res, final Vec2d a, final Vec2d b) {
        return sub(res, a, b.x, b.y);
    }

    public static Vec2d sub(final Vec2d res, final Vec2d a, final double bX, final double bY) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        return res;
    }

    public static Vec2i sub_(final Vec2i a, final int b) {
        return sub(new Vec2i(), a, b, b);
    }

    public static Vec2i sub_(final Vec2i a, final Vec2i b) {
        return sub(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i sub(final Vec2i a, final int b) {
        return sub(a, a, b, b);
    }

    public static Vec2i sub(final Vec2i a, final Vec2i b) {
        return sub(a, a, b.x, b.y);
    }

    public static Vec2i sub(final Vec2i res, final Vec2i a, final int b) {
        return sub(res, a, b, b);
    }

    public static Vec2i sub(final Vec2i res, final Vec2i a, final Vec2i b) {
        return sub(res, a, b.x, b.y);
    }

    public static Vec2i sub(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        return res;
    }

    public static Vec2l sub_(final Vec2l a, final long b) {
        return sub(new Vec2l(), a, b, b);
    }

    public static Vec2l sub_(final Vec2l a, final Vec2l b) {
        return sub(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l sub(final Vec2l a, final long b) {
        return sub(a, a, b, b);
    }

    public static Vec2l sub(final Vec2l a, final Vec2l b) {
        return sub(a, a, b.x, b.y);
    }

    public static Vec2l sub(final Vec2l res, final Vec2l a, final long b) {
        return sub(res, a, b, b);
    }

    public static Vec2l sub(final Vec2l res, final Vec2l a, final Vec2l b) {
        return sub(res, a, b.x, b.y);
    }

    public static Vec2l sub(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        return res;
    }

    public static Vec2s sub_(final Vec2s a, final short b) {
        return sub(new Vec2s(), a, b, b);
    }

    public static Vec2s sub_(final Vec2s a, final int b) {
        return sub(new Vec2s(), a, b, b);
    }

    public static Vec2s sub_(final Vec2s a, final Vec2s b) {
        return sub(new Vec2s(), a, b.x, b.y);
    }

    public static Vec2s sub(final Vec2s a, final short b) {
        return sub(a, a, b, b);
    }

    public static Vec2s sub(final Vec2s a, final int b) {
        return sub(a, a, b, b);
    }

    public static Vec2s sub(final Vec2s a, final Vec2s b) {
        return sub(a, a, b.x, b.y);
    }

    public static Vec2s sub(final Vec2s res, final Vec2s a, final short b) {
        return sub(res, a, b, b);
    }

    public static Vec2s sub(final Vec2s res, final Vec2s a, final int b) {
        return sub(res, a, b, b);
    }

    public static Vec2s sub(final Vec2s res, final Vec2s a, final Vec2s b) {
        return sub(res, a, b.x, b.y);
    }

    public static Vec2s sub(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return sub(res, a, (int) bX, bY);
    }

    public static Vec2s sub(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x - bX);
        res.y = (short) (a.y - bY);
        return res;
    }

    public static Vec2ub sub_(final Vec2ub a, final UByte b) {
        return sub(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub sub_(final Vec2ub a, final byte b) {
        return sub(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub sub_(final Vec2ub a, final int b) {
        return sub(new Vec2ub(), a, b, b);
    }

    public static Vec2ub sub_(final Vec2ub a, final Vec2ub b) {
        return sub(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub sub(final Vec2ub a, final UByte b) {
        return sub(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub sub(final Vec2ub a, final byte b) {
        return sub(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub sub(final Vec2ub a, final int b) {
        return sub(a, a, b, b);
    }

    public static Vec2ub sub(final Vec2ub a, final Vec2ub b) {
        return sub(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub sub(final Vec2ub res, final Vec2ub a, final UByte b) {
        return sub(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub sub(final Vec2ub res, final Vec2ub a, final byte b) {
        return sub(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub sub(final Vec2ub res, final Vec2ub a, final int b) {
        return sub(res, a, b, b);
    }

    public static Vec2ub sub(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return sub(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub sub(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return sub(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub sub(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return sub(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub sub(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) ((a.x.value & 0xff) - bX);
        res.y.value = (byte) ((a.y.value & 0xff) - bY);
        return res;
    }

    public static Vec2ui sub_(final Vec2ui a, final UInt b) {
        return sub(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui sub_(final Vec2ui a, final int b) {
        return sub(new Vec2ui(), a, b, b);
    }

    public static Vec2ui sub_(final Vec2ui a, final Vec2ui b) {
        return sub(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui sub(final Vec2ui a, final UInt b) {
        return sub(a, a, b.value, b.value);
    }

    public static Vec2ui sub(final Vec2ui a, final int b) {
        return sub(a, a, b, b);
    }

    public static Vec2ui sub(final Vec2ui a, final Vec2ui b) {
        return sub(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui sub(final Vec2ui res, final Vec2ui a, final UInt b) {
        return sub(res, a, b.value, b.value);
    }

    public static Vec2ui sub(final Vec2ui res, final Vec2ui a, final int b) {
        return sub(res, a, b, b);
    }

    public static Vec2ui sub(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return sub(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui sub(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return sub(res, a, bX.value, bY.value);
    }

    public static Vec2ui sub(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = a.x.value - bX;
        res.y.value = a.y.value - bY;
        return res;
    }

    public static Vec2ul sub_(final Vec2ul a, final ULong b) {
        return sub(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul sub_(final Vec2ul a, final long b) {
        return sub(new Vec2ul(), a, b, b);
    }

    public static Vec2ul sub_(final Vec2ul a, final Vec2ul b) {
        return sub(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul sub(final Vec2ul a, final ULong b) {
        return sub(a, a, b.value, b.value);
    }

    public static Vec2ul sub(final Vec2ul a, final long b) {
        return sub(a, a, b, b);
    }

    public static Vec2ul sub(final Vec2ul a, final Vec2ul b) {
        return sub(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul sub(final Vec2ul res, final Vec2ul a, final ULong b) {
        return sub(res, a, b.value, b.value);
    }

    public static Vec2ul sub(final Vec2ul res, final Vec2ul a, final long b) {
        return sub(res, a, b, b);
    }

    public static Vec2ul sub(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return sub(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul sub(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return sub(res, a, bX.value, bY.value);
    }

    public static Vec2ul sub(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = a.x.value - bX;
        res.y.value = a.y.value - bY;
        return res;
    }

    public static Vec2us sub_(final Vec2us a, final UShort b) {
        return sub(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us sub_(final Vec2us a, final short b) {
        return sub(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us sub_(final Vec2us a, final int b) {
        return sub(new Vec2us(), a, b, b);
    }

    public static Vec2us sub_(final Vec2us a, final Vec2us b) {
        return sub(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us sub(final Vec2us a, final UShort b) {
        return sub(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us sub(final Vec2us a, final short b) {
        return sub(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us sub(final Vec2us a, final int b) {
        return sub(a, a, b, b);
    }

    public static Vec2us sub(final Vec2us a, final Vec2us b) {
        return sub(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us sub(final Vec2us res, final Vec2us a, final UShort b) {
        return sub(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us sub(final Vec2us res, final Vec2us a, final short b) {
        return sub(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us sub(final Vec2us res, final Vec2us a, final int b) {
        return sub(res, a, b, b);
    }

    public static Vec2us sub(final Vec2us res, final Vec2us a, final Vec2us b) {
        return sub(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us sub(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return sub(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us sub(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return sub(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us sub(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) ((a.x.value & 0xffff) - bX);
        res.y.value = (short) ((a.y.value & 0xffff) - bY);
        return res;
    }

    public static Vec3 sub_(final Vec3 a, final float b) {
        return sub(new Vec3(), a, b, b, b);
    }

    public static Vec3 sub_(final Vec3 a, final Vec3 b) {
        return sub(new Vec3(), a, b.x, b.y, b.z);
    }

    public static Vec3 sub(final Vec3 a, final float b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3 sub(final Vec3 a, final Vec3 b) {
        return sub(a, a, b.x, b.y, b.z);
    }

    public static Vec3 sub(final Vec3 res, final Vec3 a, final float b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3 sub(final Vec3 res, final Vec3 a, final Vec3 b) {
        return sub(res, a, b.x, b.y, b.z);
    }

    public static Vec3 sub(final Vec3 res, final Vec3 a, final float bX, final float bY, final float bZ) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        res.z = a.z - bZ;
        return res;
    }

    public static Vec3b sub_(final Vec3b a, final byte b) {
        return sub(new Vec3b(), a, b, b, b);
    }

    public static Vec3b sub_(final Vec3b a, final int b) {
        return sub(new Vec3b(), a, b, b, b);
    }

    public static Vec3b sub_(final Vec3b a, final Vec3b b) {
        return sub(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b sub(final Vec3b a, final byte b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3b sub(final Vec3b a, final int b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3b sub(final Vec3b a, final Vec3b b) {
        return sub(a, a, b.x, b.y, b.z);
    }

    public static Vec3b sub(final Vec3b res, final Vec3b a, final byte b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3b sub(final Vec3b res, final Vec3b a, final int b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3b sub(final Vec3b res, final Vec3b a, final Vec3b b) {
        return sub(res, a, b.x, b.y, b.z);
    }

    public static Vec3b sub(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return sub(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b sub(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x - bX);
        res.y = (byte) (a.y - bY);
        res.z = (byte) (a.z - bZ);
        return res;
    }

    public static Vec3d sub_(final Vec3d a, final double b) {
        return sub(new Vec3d(), a, b, b, b);
    }

    public static Vec3d sub_(final Vec3d a, final Vec3d b) {
        return sub(new Vec3d(), a, b.x, b.y, b.z);
    }

    public static Vec3d sub(final Vec3d a, final double b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3d sub(final Vec3d a, final Vec3d b) {
        return sub(a, a, b.x, b.y, b.z);
    }

    public static Vec3d sub(final Vec3d res, final Vec3d a, final double b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3d sub(final Vec3d res, final Vec3d a, final Vec3d b) {
        return sub(res, a, b.x, b.y, b.z);
    }

    public static Vec3d sub(final Vec3d res, final Vec3d a, final double bX, final double bY, final double bZ) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        res.z = a.z - bZ;
        return res;
    }

    public static Vec3i sub_(final Vec3i a, final int b) {
        return sub(new Vec3i(), a, b, b, b);
    }

    public static Vec3i sub_(final Vec3i a, final Vec3i b) {
        return sub(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i sub(final Vec3i a, final int b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3i sub(final Vec3i a, final Vec3i b) {
        return sub(a, a, b.x, b.y, b.z);
    }

    public static Vec3i sub(final Vec3i res, final Vec3i a, final int b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3i sub(final Vec3i res, final Vec3i a, final Vec3i b) {
        return sub(res, a, b.x, b.y, b.z);
    }

    public static Vec3i sub(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        res.z = a.z - bZ;
        return res;
    }

    public static Vec3l sub_(final Vec3l a, final long b) {
        return sub(new Vec3l(), a, b, b, b);
    }

    public static Vec3l sub_(final Vec3l a, final Vec3l b) {
        return sub(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l sub(final Vec3l a, final long b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3l sub(final Vec3l a, final Vec3l b) {
        return sub(a, a, b.x, b.y, b.z);
    }

    public static Vec3l sub(final Vec3l res, final Vec3l a, final long b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3l sub(final Vec3l res, final Vec3l a, final Vec3l b) {
        return sub(res, a, b.x, b.y, b.z);
    }

    public static Vec3l sub(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        res.z = a.z - bZ;
        return res;
    }

    public static Vec3s sub_(final Vec3s a, final short b) {
        return sub(new Vec3s(), a, b, b, b);
    }

    public static Vec3s sub_(final Vec3s a, final int b) {
        return sub(new Vec3s(), a, b, b, b);
    }

    public static Vec3s sub_(final Vec3s a, final Vec3s b) {
        return sub(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s sub(final Vec3s a, final short b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3s sub(final Vec3s a, final int b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3s sub(final Vec3s a, final Vec3s b) {
        return sub(a, a, b.x, b.y, b.z);
    }

    public static Vec3s sub(final Vec3s res, final Vec3s a, final short b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3s sub(final Vec3s res, final Vec3s a, final int b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3s sub(final Vec3s res, final Vec3s a, final Vec3s b) {
        return sub(res, a, b.x, b.y, b.z);
    }

    public static Vec3s sub(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return sub(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s sub(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x - bX);
        res.y = (short) (a.y - bY);
        res.z = (short) (a.z - bZ);
        return res;
    }

    public static Vec3ub sub_(final Vec3ub a, final UByte b) {
        return sub(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub sub_(final Vec3ub a, final byte b) {
        return sub(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub sub_(final Vec3ub a, final int b) {
        return sub(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub sub_(final Vec3ub a, final Vec3ub b) {
        return sub(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub sub(final Vec3ub a, final UByte b) {
        return sub(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub sub(final Vec3ub a, final byte b) {
        return sub(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub sub(final Vec3ub a, final int b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3ub sub(final Vec3ub a, final Vec3ub b) {
        return sub(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub sub(final Vec3ub res, final Vec3ub a, final UByte b) {
        return sub(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub sub(final Vec3ub res, final Vec3ub a, final byte b) {
        return sub(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub sub(final Vec3ub res, final Vec3ub a, final int b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3ub sub(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return sub(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub sub(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return sub(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub sub(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return sub(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub sub(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) ((a.x.value & 0xff) - bX);
        res.y.value = (byte) ((a.y.value & 0xff) - bY);
        res.z.value = (byte) ((a.z.value & 0xff) - bZ);
        return res;
    }

    public static Vec3ui sub_(final Vec3ui a, final UInt b) {
        return sub(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui sub_(final Vec3ui a, final int b) {
        return sub(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui sub_(final Vec3ui a, final Vec3ui b) {
        return sub(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui sub(final Vec3ui a, final UInt b) {
        return sub(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui sub(final Vec3ui a, final int b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3ui sub(final Vec3ui a, final Vec3ui b) {
        return sub(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui sub(final Vec3ui res, final Vec3ui a, final UInt b) {
        return sub(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui sub(final Vec3ui res, final Vec3ui a, final int b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3ui sub(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return sub(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui sub(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return sub(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui sub(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = a.x.value - bX;
        res.y.value = a.y.value - bY;
        res.z.value = a.z.value - bZ;
        return res;
    }

    public static Vec3ul sub_(final Vec3ul a, final ULong b) {
        return sub(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul sub_(final Vec3ul a, final long b) {
        return sub(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul sub_(final Vec3ul a, final Vec3ul b) {
        return sub(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul sub(final Vec3ul a, final ULong b) {
        return sub(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul sub(final Vec3ul a, final long b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3ul sub(final Vec3ul a, final Vec3ul b) {
        return sub(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul sub(final Vec3ul res, final Vec3ul a, final ULong b) {
        return sub(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul sub(final Vec3ul res, final Vec3ul a, final long b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3ul sub(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return sub(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul sub(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return sub(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul sub(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = a.x.value - bX;
        res.y.value = a.y.value - bY;
        res.z.value = a.z.value - bZ;
        return res;
    }

    public static Vec3us sub_(final Vec3us a, final UShort b) {
        return sub(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us sub_(final Vec3us a, final short b) {
        return sub(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us sub_(final Vec3us a, final int b) {
        return sub(new Vec3us(), a, b, b, b);
    }

    public static Vec3us sub_(final Vec3us a, final Vec3us b) {
        return sub(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us sub(final Vec3us a, final UShort b) {
        return sub(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us sub(final Vec3us a, final short b) {
        return sub(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us sub(final Vec3us a, final int b) {
        return sub(a, a, b, b, b);
    }

    public static Vec3us sub(final Vec3us a, final Vec3us b) {
        return sub(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us sub(final Vec3us res, final Vec3us a, final UShort b) {
        return sub(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us sub(final Vec3us res, final Vec3us a, final short b) {
        return sub(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us sub(final Vec3us res, final Vec3us a, final int b) {
        return sub(res, a, b, b, b);
    }

    public static Vec3us sub(final Vec3us res, final Vec3us a, final Vec3us b) {
        return sub(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us sub(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return sub(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us sub(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return sub(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us sub(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) ((a.x.value & 0xffff) - bX);
        res.y.value = (short) ((a.y.value & 0xffff) - bY);
        res.z.value = (short) ((a.z.value & 0xffff) - bZ);
        return res;
    }

    public static Vec4 sub_(final Vec4 a, final float b) {
        return sub(new Vec4(), a, b, b, b, b);
    }

    public static Vec4 sub_(final Vec4 a, final Vec4 b) {
        return sub(new Vec4(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 sub(final Vec4 a, final float b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4 sub(final Vec4 a, final Vec4 b) {
        return sub(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 sub(final Vec4 res, final Vec4 a, final float b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4 sub(final Vec4 res, final Vec4 a, final Vec4 b) {
        return sub(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 sub(final Vec4 res, final Vec4 a, final float bX, final float bY, final float bZ, final float bW) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        res.z = a.z - bZ;
        res.w = a.w - bW;
        return res;
    }

    public static Vec4b sub_(final Vec4b a, final byte b) {
        return sub(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b sub_(final Vec4b a, final int b) {
        return sub(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b sub_(final Vec4b a, final Vec4b b) {
        return sub(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b sub(final Vec4b a, final byte b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4b sub(final Vec4b a, final int b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4b sub(final Vec4b a, final Vec4b b) {
        return sub(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b sub(final Vec4b res, final Vec4b a, final byte b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4b sub(final Vec4b res, final Vec4b a, final int b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4b sub(final Vec4b res, final Vec4b a, final Vec4b b) {
        return sub(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b sub(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return sub(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b sub(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x - bX);
        res.y = (byte) (a.y - bY);
        res.z = (byte) (a.z - bZ);
        res.w = (byte) (a.w - bW);
        return res;
    }

    public static Vec4d sub_(final Vec4d a, final double b) {
        return sub(new Vec4d(), a, b, b, b, b);
    }

    public static Vec4d sub_(final Vec4d a, final Vec4d b) {
        return sub(new Vec4d(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d sub(final Vec4d a, final double b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4d sub(final Vec4d a, final Vec4d b) {
        return sub(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d sub(final Vec4d res, final Vec4d a, final double b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4d sub(final Vec4d res, final Vec4d a, final Vec4d b) {
        return sub(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d sub(final Vec4d res, final Vec4d a, final double bX, final double bY, final double bZ, final double bW) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        res.z = a.z - bZ;
        res.w = a.w - bW;
        return res;
    }

    public static Vec4i sub_(final Vec4i a, final int b) {
        return sub(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i sub_(final Vec4i a, final Vec4i b) {
        return sub(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i sub(final Vec4i a, final int b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4i sub(final Vec4i a, final Vec4i b) {
        return sub(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i sub(final Vec4i res, final Vec4i a, final int b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4i sub(final Vec4i res, final Vec4i a, final Vec4i b) {
        return sub(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i sub(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        res.z = a.z - bZ;
        res.w = a.w - bW;
        return res;
    }

    public static Vec4l sub_(final Vec4l a, final long b) {
        return sub(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l sub_(final Vec4l a, final Vec4l b) {
        return sub(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l sub(final Vec4l a, final long b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4l sub(final Vec4l a, final Vec4l b) {
        return sub(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l sub(final Vec4l res, final Vec4l a, final long b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4l sub(final Vec4l res, final Vec4l a, final Vec4l b) {
        return sub(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l sub(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x - bX;
        res.y = a.y - bY;
        res.z = a.z - bZ;
        res.w = a.w - bW;
        return res;
    }

    public static Vec4s sub_(final Vec4s a, final short b) {
        return sub(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s sub_(final Vec4s a, final int b) {
        return sub(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s sub_(final Vec4s a, final Vec4s b) {
        return sub(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s sub(final Vec4s a, final short b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4s sub(final Vec4s a, final int b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4s sub(final Vec4s a, final Vec4s b) {
        return sub(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s sub(final Vec4s res, final Vec4s a, final short b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4s sub(final Vec4s res, final Vec4s a, final int b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4s sub(final Vec4s res, final Vec4s a, final Vec4s b) {
        return sub(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s sub(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return sub(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s sub(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x - bX);
        res.y = (short) (a.y - bY);
        res.z = (short) (a.z - bZ);
        res.w = (short) (a.w - bW);
        return res;
    }

    public static Vec4ub sub_(final Vec4ub a, final UByte b) {
        return sub(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub sub_(final Vec4ub a, final byte b) {
        return sub(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub sub_(final Vec4ub a, final int b) {
        return sub(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub sub_(final Vec4ub a, final Vec4ub b) {
        return sub(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub sub(final Vec4ub a, final UByte b) {
        return sub(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub sub(final Vec4ub a, final byte b) {
        return sub(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub sub(final Vec4ub a, final int b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4ub sub(final Vec4ub a, final Vec4ub b) {
        return sub(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub sub(final Vec4ub res, final Vec4ub a, final UByte b) {
        return sub(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub sub(final Vec4ub res, final Vec4ub a, final byte b) {
        return sub(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub sub(final Vec4ub res, final Vec4ub a, final int b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4ub sub(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return sub(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub sub(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return sub(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub sub(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return sub(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub sub(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) ((a.x.value & 0xff) - bX);
        res.y.value = (byte) ((a.y.value & 0xff) - bY);
        res.z.value = (byte) ((a.z.value & 0xff) - bZ);
        res.w.value = (byte) ((a.w.value & 0xff) - bW);
        return res;
    }

    public static Vec4ui sub_(final Vec4ui a, final UInt b) {
        return sub(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui sub_(final Vec4ui a, final int b) {
        return sub(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui sub_(final Vec4ui a, final Vec4ui b) {
        return sub(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui sub(final Vec4ui a, final UInt b) {
        return sub(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui sub(final Vec4ui a, final int b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4ui sub(final Vec4ui a, final Vec4ui b) {
        return sub(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui sub(final Vec4ui res, final Vec4ui a, final UInt b) {
        return sub(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui sub(final Vec4ui res, final Vec4ui a, final int b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4ui sub(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return sub(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui sub(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return sub(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui sub(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = a.x.value - bX;
        res.y.value = a.y.value - bY;
        res.z.value = a.z.value - bZ;
        res.w.value = a.w.value - bW;
        return res;
    }

    public static Vec4ul sub_(final Vec4ul a, final ULong b) {
        return sub(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul sub_(final Vec4ul a, final long b) {
        return sub(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul sub_(final Vec4ul a, final Vec4ul b) {
        return sub(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul sub(final Vec4ul a, final ULong b) {
        return sub(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul sub(final Vec4ul a, final long b) {
        return sub(a, a, b, b, b, b);
    }

    public static Vec4ul sub(final Vec4ul a, final Vec4ul b) {
        return sub(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul sub(final Vec4ul res, final Vec4ul a, final ULong b) {
        return sub(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul sub(final Vec4ul res, final Vec4ul a, final long b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4ul sub(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return sub(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul sub(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return sub(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul sub(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = a.x.value - bX;
        res.y.value = a.y.value - bY;
        res.z.value = a.z.value - bZ;
        res.w.value = a.w.value - bW;
        return res;
    }

    public static Vec4us sub_(final Vec4us a, final UShort b) {
        return sub(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us sub_(final Vec4us a, final short b) {
        return sub(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us sub_(final Vec4us a, final int b) {
        return sub(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us sub_(final Vec4us a, final Vec4us b) {
        return sub(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us sub(final Vec4us a, final UShort b) {
        return sub(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us sub(final Vec4us a, final short b) {
        return sub(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us sub(final Vec4us a, final int b) {
        return sub(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us sub(final Vec4us a, final Vec4us b) {
        return sub(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us sub(final Vec4us res, final Vec4us a, final UShort b) {
        return sub(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us sub(final Vec4us res, final Vec4us a, final short b) {
        return sub(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us sub(final Vec4us res, final Vec4us a, final int b) {
        return sub(res, a, b, b, b, b);
    }

    public static Vec4us sub(final Vec4us res, final Vec4us a, final Vec4us b) {
        return sub(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us sub(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return sub(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us sub(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return sub(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us sub(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) ((a.x.value & 0xffff) - bX);
        res.y.value = (short) ((a.y.value & 0xffff) - bY);
        res.z.value = (short) ((a.z.value & 0xffff) - bZ);
        res.w.value = (short) ((a.w.value & 0xffff) - bW);
        return res;
    }

    public static Vec2 mul_(final Vec2 a, final float b) {
        return mul(new Vec2(), a, b, b);
    }

    public static Vec2 mul_(final Vec2 a, final Vec2 b) {
        return mul(new Vec2(), a, b.x, b.y);
    }

    public static Vec2 mul(final Vec2 a, final float b) {
        return mul(a, a, b, b);
    }

    public static Vec2 mul(final Vec2 a, final Vec2 b) {
        return mul(a, a, b.x, b.y);
    }

    public static Vec2 mul(final Vec2 res, final Vec2 a, final float b) {
        return mul(res, a, b, b);
    }

    public static Vec2 mul(final Vec2 res, final Vec2 a, final Vec2 b) {
        return mul(res, a, b.x, b.y);
    }

    public static Vec2 mul(final Vec2 res, final Vec2 a, final float bX, final float bY) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        return res;
    }

    public static Vec2b mul_(final Vec2b a, final byte b) {
        return mul(new Vec2b(), a, b, b);
    }

    public static Vec2b mul_(final Vec2b a, final int b) {
        return mul(new Vec2b(), a, b, b);
    }

    public static Vec2b mul_(final Vec2b a, final Vec2b b) {
        return mul(new Vec2b(), a, b.x, b.y);
    }

    public static Vec2b mul(final Vec2b a, final byte b) {
        return mul(a, a, b, b);
    }

    public static Vec2b mul(final Vec2b a, final int b) {
        return mul(a, a, b, b);
    }

    public static Vec2b mul(final Vec2b a, final Vec2b b) {
        return mul(a, a, b.x, b.y);
    }

    public static Vec2b mul(final Vec2b res, final Vec2b a, final byte b) {
        return mul(res, a, b, b);
    }

    public static Vec2b mul(final Vec2b res, final Vec2b a, final int b) {
        return mul(res, a, b, b);
    }

    public static Vec2b mul(final Vec2b res, final Vec2b a, final Vec2b b) {
        return mul(res, a, b.x, b.y);
    }

    public static Vec2b mul(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return mul(res, a, (int) bX, bY);
    }

    public static Vec2b mul(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x * bX);
        res.y = (byte) (a.y * bY);
        return res;
    }

    public static Vec2d mul_(final Vec2d a, final double b) {
        return mul(new Vec2d(), a, b, b);
    }

    public static Vec2d mul_(final Vec2d a, final Vec2d b) {
        return mul(new Vec2d(), a, b.x, b.y);
    }

    public static Vec2d mul(final Vec2d a, final double b) {
        return mul(a, a, b, b);
    }

    public static Vec2d mul(final Vec2d a, final Vec2d b) {
        return mul(a, a, b.x, b.y);
    }

    public static Vec2d mul(final Vec2d res, final Vec2d a, final double b) {
        return mul(res, a, b, b);
    }

    public static Vec2d mul(final Vec2d res, final Vec2d a, final Vec2d b) {
        return mul(res, a, b.x, b.y);
    }

    public static Vec2d mul(final Vec2d res, final Vec2d a, final double bX, final double bY) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        return res;
    }

    public static Vec2i mul_(final Vec2i a, final int b) {
        return mul(new Vec2i(), a, b, b);
    }

    public static Vec2i mul_(final Vec2i a, final Vec2i b) {
        return mul(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i mul(final Vec2i a, final int b) {
        return mul(a, a, b, b);
    }

    public static Vec2i mul(final Vec2i a, final Vec2i b) {
        return mul(a, a, b.x, b.y);
    }

    public static Vec2i mul(final Vec2i res, final Vec2i a, final int b) {
        return mul(res, a, b, b);
    }

    public static Vec2i mul(final Vec2i res, final Vec2i a, final Vec2i b) {
        return mul(res, a, b.x, b.y);
    }

    public static Vec2i mul(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        return res;
    }

    public static Vec2l mul_(final Vec2l a, final long b) {
        return mul(new Vec2l(), a, b, b);
    }

    public static Vec2l mul_(final Vec2l a, final Vec2l b) {
        return mul(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l mul(final Vec2l a, final long b) {
        return mul(a, a, b, b);
    }

    public static Vec2l mul(final Vec2l a, final Vec2l b) {
        return mul(a, a, b.x, b.y);
    }

    public static Vec2l mul(final Vec2l res, final Vec2l a, final long b) {
        return mul(res, a, b, b);
    }

    public static Vec2l mul(final Vec2l res, final Vec2l a, final Vec2l b) {
        return mul(res, a, b.x, b.y);
    }

    public static Vec2l mul(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        return res;
    }

    public static Vec2s mul_(final Vec2s a, final short b) {
        return mul(new Vec2s(), a, b, b);
    }

    public static Vec2s mul_(final Vec2s a, final int b) {
        return mul(new Vec2s(), a, b, b);
    }

    public static Vec2s mul_(final Vec2s a, final Vec2s b) {
        return mul(new Vec2s(), a, b.x, b.y);
    }

    public static Vec2s mul(final Vec2s a, final short b) {
        return mul(a, a, b, b);
    }

    public static Vec2s mul(final Vec2s a, final int b) {
        return mul(a, a, b, b);
    }

    public static Vec2s mul(final Vec2s a, final Vec2s b) {
        return mul(a, a, b.x, b.y);
    }

    public static Vec2s mul(final Vec2s res, final Vec2s a, final short b) {
        return mul(res, a, b, b);
    }

    public static Vec2s mul(final Vec2s res, final Vec2s a, final int b) {
        return mul(res, a, b, b);
    }

    public static Vec2s mul(final Vec2s res, final Vec2s a, final Vec2s b) {
        return mul(res, a, b.x, b.y);
    }

    public static Vec2s mul(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return mul(res, a, (int) bX, bY);
    }

    public static Vec2s mul(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x * bX);
        res.y = (short) (a.y * bY);
        return res;
    }

    public static Vec2ub mul_(final Vec2ub a, final UByte b) {
        return mul(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub mul_(final Vec2ub a, final byte b) {
        return mul(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub mul_(final Vec2ub a, final int b) {
        return mul(new Vec2ub(), a, b, b);
    }

    public static Vec2ub mul_(final Vec2ub a, final Vec2ub b) {
        return mul(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub mul(final Vec2ub a, final UByte b) {
        return mul(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub mul(final Vec2ub a, final byte b) {
        return mul(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub mul(final Vec2ub a, final int b) {
        return mul(a, a, b, b);
    }

    public static Vec2ub mul(final Vec2ub a, final Vec2ub b) {
        return mul(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final Vec2ub a, final UByte b) {
        return mul(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final Vec2ub a, final byte b) {
        return mul(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final Vec2ub a, final int b) {
        return mul(res, a, b, b);
    }

    public static Vec2ub mul(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return mul(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return mul(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return mul(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) ((a.x.value & 0xff) * bX);
        res.y.value = (byte) ((a.y.value & 0xff) * bY);
        return res;
    }

    public static Vec2ui mul_(final Vec2ui a, final UInt b) {
        return mul(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui mul_(final Vec2ui a, final int b) {
        return mul(new Vec2ui(), a, b, b);
    }

    public static Vec2ui mul_(final Vec2ui a, final Vec2ui b) {
        return mul(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui mul(final Vec2ui a, final UInt b) {
        return mul(a, a, b.value, b.value);
    }

    public static Vec2ui mul(final Vec2ui a, final int b) {
        return mul(a, a, b, b);
    }

    public static Vec2ui mul(final Vec2ui a, final Vec2ui b) {
        return mul(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui mul(final Vec2ui res, final Vec2ui a, final UInt b) {
        return mul(res, a, b.value, b.value);
    }

    public static Vec2ui mul(final Vec2ui res, final Vec2ui a, final int b) {
        return mul(res, a, b, b);
    }

    public static Vec2ui mul(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return mul(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui mul(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return mul(res, a, bX.value, bY.value);
    }

    public static Vec2ui mul(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = a.x.value * bX;
        res.y.value = a.y.value * bY;
        return res;
    }

    public static Vec2ul mul_(final Vec2ul a, final ULong b) {
        return mul(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul mul_(final Vec2ul a, final long b) {
        return mul(new Vec2ul(), a, b, b);
    }

    public static Vec2ul mul_(final Vec2ul a, final Vec2ul b) {
        return mul(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul mul(final Vec2ul a, final ULong b) {
        return mul(a, a, b.value, b.value);
    }

    public static Vec2ul mul(final Vec2ul a, final long b) {
        return mul(a, a, b, b);
    }

    public static Vec2ul mul(final Vec2ul a, final Vec2ul b) {
        return mul(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul mul(final Vec2ul res, final Vec2ul a, final ULong b) {
        return mul(res, a, b.value, b.value);
    }

    public static Vec2ul mul(final Vec2ul res, final Vec2ul a, final long b) {
        return mul(res, a, b, b);
    }

    public static Vec2ul mul(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return mul(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul mul(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return mul(res, a, bX.value, bY.value);
    }

    public static Vec2ul mul(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = a.x.value * bX;
        res.y.value = a.y.value * bY;
        return res;
    }

    public static Vec2us mul_(final Vec2us a, final UShort b) {
        return mul(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us mul_(final Vec2us a, final short b) {
        return mul(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us mul_(final Vec2us a, final int b) {
        return mul(new Vec2us(), a, b, b);
    }

    public static Vec2us mul_(final Vec2us a, final Vec2us b) {
        return mul(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us mul(final Vec2us a, final UShort b) {
        return mul(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us mul(final Vec2us a, final short b) {
        return mul(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us mul(final Vec2us a, final int b) {
        return mul(a, a, b, b);
    }

    public static Vec2us mul(final Vec2us a, final Vec2us b) {
        return mul(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final Vec2us a, final UShort b) {
        return mul(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final Vec2us a, final short b) {
        return mul(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final Vec2us a, final int b) {
        return mul(res, a, b, b);
    }

    public static Vec2us mul(final Vec2us res, final Vec2us a, final Vec2us b) {
        return mul(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return mul(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return mul(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) ((a.x.value & 0xffff) * bX);
        res.y.value = (short) ((a.y.value & 0xffff) * bY);
        return res;
    }

    public static Vec3 mul_(final Vec3 a, final float b) {
        return mul(new Vec3(), a, b, b, b);
    }

    public static Vec3 mul_(final Vec3 a, final Vec3 b) {
        return mul(new Vec3(), a, b.x, b.y, b.z);
    }

    public static Vec3 mul(final Vec3 a, final float b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3 mul(final Vec3 a, final Vec3 b) {
        return mul(a, a, b.x, b.y, b.z);
    }

    public static Vec3 mul(final Vec3 res, final Vec3 a, final float b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3 mul(final Vec3 res, final Vec3 a, final Vec3 b) {
        return mul(res, a, b.x, b.y, b.z);
    }

    public static Vec3 mul(final Vec3 res, final Vec3 a, final float bX, final float bY, final float bZ) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        res.z = a.z * bZ;
        return res;
    }

    public static Vec3b mul_(final Vec3b a, final byte b) {
        return mul(new Vec3b(), a, b, b, b);
    }

    public static Vec3b mul_(final Vec3b a, final int b) {
        return mul(new Vec3b(), a, b, b, b);
    }

    public static Vec3b mul_(final Vec3b a, final Vec3b b) {
        return mul(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b mul(final Vec3b a, final byte b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3b mul(final Vec3b a, final int b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3b mul(final Vec3b a, final Vec3b b) {
        return mul(a, a, b.x, b.y, b.z);
    }

    public static Vec3b mul(final Vec3b res, final Vec3b a, final byte b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3b mul(final Vec3b res, final Vec3b a, final int b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3b mul(final Vec3b res, final Vec3b a, final Vec3b b) {
        return mul(res, a, b.x, b.y, b.z);
    }

    public static Vec3b mul(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return mul(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b mul(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x * bX);
        res.y = (byte) (a.y * bY);
        res.z = (byte) (a.z * bZ);
        return res;
    }

    public static Vec3d mul_(final Vec3d a, final double b) {
        return mul(new Vec3d(), a, b, b, b);
    }

    public static Vec3d mul_(final Vec3d a, final Vec3d b) {
        return mul(new Vec3d(), a, b.x, b.y, b.z);
    }

    public static Vec3d mul(final Vec3d a, final double b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3d mul(final Vec3d a, final Vec3d b) {
        return mul(a, a, b.x, b.y, b.z);
    }

    public static Vec3d mul(final Vec3d res, final Vec3d a, final double b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3d mul(final Vec3d res, final Vec3d a, final Vec3d b) {
        return mul(res, a, b.x, b.y, b.z);
    }

    public static Vec3d mul(final Vec3d res, final Vec3d a, final double bX, final double bY, final double bZ) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        res.z = a.z * bZ;
        return res;
    }

    public static Vec3i mul_(final Vec3i a, final int b) {
        return mul(new Vec3i(), a, b, b, b);
    }

    public static Vec3i mul_(final Vec3i a, final Vec3i b) {
        return mul(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i mul(final Vec3i a, final int b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3i mul(final Vec3i a, final Vec3i b) {
        return mul(a, a, b.x, b.y, b.z);
    }

    public static Vec3i mul(final Vec3i res, final Vec3i a, final int b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3i mul(final Vec3i res, final Vec3i a, final Vec3i b) {
        return mul(res, a, b.x, b.y, b.z);
    }

    public static Vec3i mul(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        res.z = a.z * bZ;
        return res;
    }

    public static Vec3l mul_(final Vec3l a, final long b) {
        return mul(new Vec3l(), a, b, b, b);
    }

    public static Vec3l mul_(final Vec3l a, final Vec3l b) {
        return mul(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l mul(final Vec3l a, final long b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3l mul(final Vec3l a, final Vec3l b) {
        return mul(a, a, b.x, b.y, b.z);
    }

    public static Vec3l mul(final Vec3l res, final Vec3l a, final long b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3l mul(final Vec3l res, final Vec3l a, final Vec3l b) {
        return mul(res, a, b.x, b.y, b.z);
    }

    public static Vec3l mul(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        res.z = a.z * bZ;
        return res;
    }

    public static Vec3s mul_(final Vec3s a, final short b) {
        return mul(new Vec3s(), a, b, b, b);
    }

    public static Vec3s mul_(final Vec3s a, final int b) {
        return mul(new Vec3s(), a, b, b, b);
    }

    public static Vec3s mul_(final Vec3s a, final Vec3s b) {
        return mul(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s mul(final Vec3s a, final short b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3s mul(final Vec3s a, final int b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3s mul(final Vec3s a, final Vec3s b) {
        return mul(a, a, b.x, b.y, b.z);
    }

    public static Vec3s mul(final Vec3s res, final Vec3s a, final short b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3s mul(final Vec3s res, final Vec3s a, final int b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3s mul(final Vec3s res, final Vec3s a, final Vec3s b) {
        return mul(res, a, b.x, b.y, b.z);
    }

    public static Vec3s mul(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return mul(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s mul(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x * bX);
        res.y = (short) (a.y * bY);
        res.z = (short) (a.z * bZ);
        return res;
    }

    public static Vec3ub mul_(final Vec3ub a, final UByte b) {
        return mul(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub mul_(final Vec3ub a, final byte b) {
        return mul(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub mul_(final Vec3ub a, final int b) {
        return mul(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub mul_(final Vec3ub a, final Vec3ub b) {
        return mul(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub mul(final Vec3ub a, final UByte b) {
        return mul(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub mul(final Vec3ub a, final byte b) {
        return mul(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub mul(final Vec3ub a, final int b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3ub mul(final Vec3ub a, final Vec3ub b) {
        return mul(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final Vec3ub a, final UByte b) {
        return mul(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final Vec3ub a, final byte b) {
        return mul(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final Vec3ub a, final int b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3ub mul(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return mul(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return mul(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return mul(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) ((a.x.value & 0xff) * bX);
        res.y.value = (byte) ((a.y.value & 0xff) * bY);
        res.z.value = (byte) ((a.z.value & 0xff) * bZ);
        return res;
    }

    public static Vec3ui mul_(final Vec3ui a, final UInt b) {
        return mul(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui mul_(final Vec3ui a, final int b) {
        return mul(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui mul_(final Vec3ui a, final Vec3ui b) {
        return mul(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui mul(final Vec3ui a, final UInt b) {
        return mul(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui mul(final Vec3ui a, final int b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3ui mul(final Vec3ui a, final Vec3ui b) {
        return mul(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui mul(final Vec3ui res, final Vec3ui a, final UInt b) {
        return mul(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui mul(final Vec3ui res, final Vec3ui a, final int b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3ui mul(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return mul(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui mul(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return mul(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui mul(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = a.x.value * bX;
        res.y.value = a.y.value * bY;
        res.z.value = a.z.value * bZ;
        return res;
    }

    public static Vec3ul mul_(final Vec3ul a, final ULong b) {
        return mul(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul mul_(final Vec3ul a, final long b) {
        return mul(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul mul_(final Vec3ul a, final Vec3ul b) {
        return mul(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul mul(final Vec3ul a, final ULong b) {
        return mul(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul mul(final Vec3ul a, final long b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3ul mul(final Vec3ul a, final Vec3ul b) {
        return mul(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul mul(final Vec3ul res, final Vec3ul a, final ULong b) {
        return mul(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul mul(final Vec3ul res, final Vec3ul a, final long b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3ul mul(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return mul(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul mul(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return mul(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul mul(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = a.x.value * bX;
        res.y.value = a.y.value * bY;
        res.z.value = a.z.value * bZ;
        return res;
    }

    public static Vec3us mul_(final Vec3us a, final UShort b) {
        return mul(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us mul_(final Vec3us a, final short b) {
        return mul(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us mul_(final Vec3us a, final int b) {
        return mul(new Vec3us(), a, b, b, b);
    }

    public static Vec3us mul_(final Vec3us a, final Vec3us b) {
        return mul(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us mul(final Vec3us a, final UShort b) {
        return mul(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us mul(final Vec3us a, final short b) {
        return mul(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us mul(final Vec3us a, final int b) {
        return mul(a, a, b, b, b);
    }

    public static Vec3us mul(final Vec3us a, final Vec3us b) {
        return mul(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final Vec3us a, final UShort b) {
        return mul(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final Vec3us a, final short b) {
        return mul(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final Vec3us a, final int b) {
        return mul(res, a, b, b, b);
    }

    public static Vec3us mul(final Vec3us res, final Vec3us a, final Vec3us b) {
        return mul(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return mul(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return mul(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) ((a.x.value & 0xffff) * bX);
        res.y.value = (short) ((a.y.value & 0xffff) * bY);
        res.z.value = (short) ((a.z.value & 0xffff) * bZ);
        return res;
    }

    public static Vec4 mul_(final Vec4 a, final float b) {
        return mul(new Vec4(), a, b, b, b, b);
    }

    public static Vec4 mul_(final Vec4 a, final Vec4 b) {
        return mul(new Vec4(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 mul(final Vec4 a, final float b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4 mul(final Vec4 a, final Vec4 b) {
        return mul(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 mul(final Vec4 res, final Vec4 a, final float b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4 mul(final Vec4 res, final Vec4 a, final Vec4 b) {
        return mul(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 mul(final Vec4 res, final Vec4 a, final float bX, final float bY, final float bZ, final float bW) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        res.z = a.z * bZ;
        res.w = a.w * bW;
        return res;
    }

    public static Vec4b mul_(final Vec4b a, final byte b) {
        return mul(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b mul_(final Vec4b a, final int b) {
        return mul(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b mul_(final Vec4b a, final Vec4b b) {
        return mul(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b mul(final Vec4b a, final byte b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4b mul(final Vec4b a, final int b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4b mul(final Vec4b a, final Vec4b b) {
        return mul(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b mul(final Vec4b res, final Vec4b a, final byte b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4b mul(final Vec4b res, final Vec4b a, final int b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4b mul(final Vec4b res, final Vec4b a, final Vec4b b) {
        return mul(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b mul(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return mul(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b mul(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x * bX);
        res.y = (byte) (a.y * bY);
        res.z = (byte) (a.z * bZ);
        res.w = (byte) (a.w * bW);
        return res;
    }

    public static Vec4d mul_(final Vec4d a, final double b) {
        return mul(new Vec4d(), a, b, b, b, b);
    }

    public static Vec4d mul_(final Vec4d a, final Vec4d b) {
        return mul(new Vec4d(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d mul(final Vec4d a, final double b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4d mul(final Vec4d a, final Vec4d b) {
        return mul(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d mul(final Vec4d res, final Vec4d a, final double b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4d mul(final Vec4d res, final Vec4d a, final Vec4d b) {
        return mul(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d mul(final Vec4d res, final Vec4d a, final double bX, final double bY, final double bZ, final double bW) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        res.z = a.z * bZ;
        res.w = a.w * bW;
        return res;
    }

    public static Vec4i mul_(final Vec4i a, final int b) {
        return mul(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i mul_(final Vec4i a, final Vec4i b) {
        return mul(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i mul(final Vec4i a, final int b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4i mul(final Vec4i a, final Vec4i b) {
        return mul(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i mul(final Vec4i res, final Vec4i a, final int b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4i mul(final Vec4i res, final Vec4i a, final Vec4i b) {
        return mul(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i mul(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        res.z = a.z * bZ;
        res.w = a.w * bW;
        return res;
    }

    public static Vec4l mul_(final Vec4l a, final long b) {
        return mul(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l mul_(final Vec4l a, final Vec4l b) {
        return mul(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l mul(final Vec4l a, final long b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4l mul(final Vec4l a, final Vec4l b) {
        return mul(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l mul(final Vec4l res, final Vec4l a, final long b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4l mul(final Vec4l res, final Vec4l a, final Vec4l b) {
        return mul(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l mul(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x * bX;
        res.y = a.y * bY;
        res.z = a.z * bZ;
        res.w = a.w * bW;
        return res;
    }

    public static Vec4s mul_(final Vec4s a, final short b) {
        return mul(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s mul_(final Vec4s a, final int b) {
        return mul(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s mul_(final Vec4s a, final Vec4s b) {
        return mul(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s mul(final Vec4s a, final short b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4s mul(final Vec4s a, final int b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4s mul(final Vec4s a, final Vec4s b) {
        return mul(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s mul(final Vec4s res, final Vec4s a, final short b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4s mul(final Vec4s res, final Vec4s a, final int b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4s mul(final Vec4s res, final Vec4s a, final Vec4s b) {
        return mul(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s mul(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return mul(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s mul(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x * bX);
        res.y = (short) (a.y * bY);
        res.z = (short) (a.z * bZ);
        res.w = (short) (a.w * bW);
        return res;
    }

    public static Vec4ub mul_(final Vec4ub a, final UByte b) {
        return mul(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub mul_(final Vec4ub a, final byte b) {
        return mul(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub mul_(final Vec4ub a, final int b) {
        return mul(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub mul_(final Vec4ub a, final Vec4ub b) {
        return mul(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub mul(final Vec4ub a, final UByte b) {
        return mul(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub mul(final Vec4ub a, final byte b) {
        return mul(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub mul(final Vec4ub a, final int b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4ub mul(final Vec4ub a, final Vec4ub b) {
        return mul(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final Vec4ub a, final UByte b) {
        return mul(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final Vec4ub a, final byte b) {
        return mul(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final Vec4ub a, final int b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4ub mul(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return mul(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return mul(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return mul(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) ((a.x.value & 0xff) * bX);
        res.y.value = (byte) ((a.y.value & 0xff) * bY);
        res.z.value = (byte) ((a.z.value & 0xff) * bZ);
        res.w.value = (byte) ((a.w.value & 0xff) * bW);
        return res;
    }

    public static Vec4ui mul_(final Vec4ui a, final UInt b) {
        return mul(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui mul_(final Vec4ui a, final int b) {
        return mul(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui mul_(final Vec4ui a, final Vec4ui b) {
        return mul(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui mul(final Vec4ui a, final UInt b) {
        return mul(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui mul(final Vec4ui a, final int b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4ui mul(final Vec4ui a, final Vec4ui b) {
        return mul(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui mul(final Vec4ui res, final Vec4ui a, final UInt b) {
        return mul(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui mul(final Vec4ui res, final Vec4ui a, final int b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4ui mul(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return mul(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui mul(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return mul(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui mul(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = a.x.value * bX;
        res.y.value = a.y.value * bY;
        res.z.value = a.z.value * bZ;
        res.w.value = a.w.value * bW;
        return res;
    }

    public static Vec4ul mul_(final Vec4ul a, final ULong b) {
        return mul(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul mul_(final Vec4ul a, final long b) {
        return mul(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul mul_(final Vec4ul a, final Vec4ul b) {
        return mul(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul mul(final Vec4ul a, final ULong b) {
        return mul(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul mul(final Vec4ul a, final long b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4ul mul(final Vec4ul a, final Vec4ul b) {
        return mul(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul mul(final Vec4ul res, final Vec4ul a, final ULong b) {
        return mul(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul mul(final Vec4ul res, final Vec4ul a, final long b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4ul mul(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return mul(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul mul(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return mul(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul mul(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = a.x.value * bX;
        res.y.value = a.y.value * bY;
        res.z.value = a.z.value * bZ;
        res.w.value = a.w.value * bW;
        return res;
    }

    public static Vec4us mul_(final Vec4us a, final UShort b) {
        return mul(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us mul_(final Vec4us a, final short b) {
        return mul(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us mul_(final Vec4us a, final int b) {
        return mul(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us mul_(final Vec4us a, final Vec4us b) {
        return mul(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us mul(final Vec4us a, final UShort b) {
        return mul(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us mul(final Vec4us a, final short b) {
        return mul(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us mul(final Vec4us a, final int b) {
        return mul(a, a, b, b, b, b);
    }

    public static Vec4us mul(final Vec4us a, final Vec4us b) {
        return mul(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final Vec4us a, final UShort b) {
        return mul(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final Vec4us a, final short b) {
        return mul(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final Vec4us a, final int b) {
        return mul(res, a, b, b, b, b);
    }

    public static Vec4us mul(final Vec4us res, final Vec4us a, final Vec4us b) {
        return mul(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return mul(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return mul(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) ((a.x.value & 0xffff) * bX);
        res.y.value = (short) ((a.y.value & 0xffff) * bY);
        res.z.value = (short) ((a.z.value & 0xffff) * bZ);
        res.w.value = (short) ((a.w.value & 0xffff) * bW);
        return res;
    }

    public static Vec2 div_(final Vec2 a, final float b) {
        return div(new Vec2(), a, b, b);
    }

    public static Vec2 div_(final Vec2 a, final Vec2 b) {
        return div(new Vec2(), a, b.x, b.y);
    }

    public static Vec2 div(final Vec2 a, final float b) {
        return div(a, a, b, b);
    }

    public static Vec2 div(final Vec2 a, final Vec2 b) {
        return div(a, a, b.x, b.y);
    }

    public static Vec2 div(final Vec2 res, final Vec2 a, final float b) {
        return div(res, a, b, b);
    }

    public static Vec2 div(final Vec2 res, final Vec2 a, final Vec2 b) {
        return div(res, a, b.x, b.y);
    }

    public static Vec2 div(final Vec2 res, final Vec2 a, final float bX, final float bY) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        return res;
    }

    public static Vec2b div_(final Vec2b a, final byte b) {
        return div(new Vec2b(), a, b, b);
    }

    public static Vec2b div_(final Vec2b a, final int b) {
        return div(new Vec2b(), a, b, b);
    }

    public static Vec2b div_(final Vec2b a, final Vec2b b) {
        return div(new Vec2b(), a, b.x, b.y);
    }

    public static Vec2b div(final Vec2b a, final byte b) {
        return div(a, a, b, b);
    }

    public static Vec2b div(final Vec2b a, final int b) {
        return div(a, a, b, b);
    }

    public static Vec2b div(final Vec2b a, final Vec2b b) {
        return div(a, a, b.x, b.y);
    }

    public static Vec2b div(final Vec2b res, final Vec2b a, final byte b) {
        return div(res, a, b, b);
    }

    public static Vec2b div(final Vec2b res, final Vec2b a, final int b) {
        return div(res, a, b, b);
    }

    public static Vec2b div(final Vec2b res, final Vec2b a, final Vec2b b) {
        return div(res, a, b.x, b.y);
    }

    public static Vec2b div(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return div(res, a, (int) bX, bY);
    }

    public static Vec2b div(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x / bX);
        res.y = (byte) (a.y / bY);
        return res;
    }

    public static Vec2d div_(final Vec2d a, final double b) {
        return div(new Vec2d(), a, b, b);
    }

    public static Vec2d div_(final Vec2d a, final Vec2d b) {
        return div(new Vec2d(), a, b.x, b.y);
    }

    public static Vec2d div(final Vec2d a, final double b) {
        return div(a, a, b, b);
    }

    public static Vec2d div(final Vec2d a, final Vec2d b) {
        return div(a, a, b.x, b.y);
    }

    public static Vec2d div(final Vec2d res, final Vec2d a, final double b) {
        return div(res, a, b, b);
    }

    public static Vec2d div(final Vec2d res, final Vec2d a, final Vec2d b) {
        return div(res, a, b.x, b.y);
    }

    public static Vec2d div(final Vec2d res, final Vec2d a, final double bX, final double bY) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        return res;
    }

    public static Vec2i div_(final Vec2i a, final int b) {
        return div(new Vec2i(), a, b, b);
    }

    public static Vec2i div_(final Vec2i a, final Vec2i b) {
        return div(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i div(final Vec2i a, final int b) {
        return div(a, a, b, b);
    }

    public static Vec2i div(final Vec2i a, final Vec2i b) {
        return div(a, a, b.x, b.y);
    }

    public static Vec2i div(final Vec2i res, final Vec2i a, final int b) {
        return div(res, a, b, b);
    }

    public static Vec2i div(final Vec2i res, final Vec2i a, final Vec2i b) {
        return div(res, a, b.x, b.y);
    }

    public static Vec2i div(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        return res;
    }

    public static Vec2l div_(final Vec2l a, final long b) {
        return div(new Vec2l(), a, b, b);
    }

    public static Vec2l div_(final Vec2l a, final Vec2l b) {
        return div(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l div(final Vec2l a, final long b) {
        return div(a, a, b, b);
    }

    public static Vec2l div(final Vec2l a, final Vec2l b) {
        return div(a, a, b.x, b.y);
    }

    public static Vec2l div(final Vec2l res, final Vec2l a, final long b) {
        return div(res, a, b, b);
    }

    public static Vec2l div(final Vec2l res, final Vec2l a, final Vec2l b) {
        return div(res, a, b.x, b.y);
    }

    public static Vec2l div(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        return res;
    }

    public static Vec2s div_(final Vec2s a, final short b) {
        return div(new Vec2s(), a, b, b);
    }

    public static Vec2s div_(final Vec2s a, final int b) {
        return div(new Vec2s(), a, b, b);
    }

    public static Vec2s div_(final Vec2s a, final Vec2s b) {
        return div(new Vec2s(), a, b.x, b.y);
    }

    public static Vec2s div(final Vec2s a, final short b) {
        return div(a, a, b, b);
    }

    public static Vec2s div(final Vec2s a, final int b) {
        return div(a, a, b, b);
    }

    public static Vec2s div(final Vec2s a, final Vec2s b) {
        return div(a, a, b.x, b.y);
    }

    public static Vec2s div(final Vec2s res, final Vec2s a, final short b) {
        return div(res, a, b, b);
    }

    public static Vec2s div(final Vec2s res, final Vec2s a, final int b) {
        return div(res, a, b, b);
    }

    public static Vec2s div(final Vec2s res, final Vec2s a, final Vec2s b) {
        return div(res, a, b.x, b.y);
    }

    public static Vec2s div(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return div(res, a, (int) bX, bY);
    }

    public static Vec2s div(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x / bX);
        res.y = (short) (a.y / bY);
        return res;
    }

    public static Vec2ub div_(final Vec2ub a, final UByte b) {
        return div(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub div_(final Vec2ub a, final byte b) {
        return div(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub div_(final Vec2ub a, final int b) {
        return div(new Vec2ub(), a, b, b);
    }

    public static Vec2ub div_(final Vec2ub a, final Vec2ub b) {
        return div(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub div(final Vec2ub a, final UByte b) {
        return div(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub div(final Vec2ub a, final byte b) {
        return div(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub div(final Vec2ub a, final int b) {
        return div(a, a, b, b);
    }

    public static Vec2ub div(final Vec2ub a, final Vec2ub b) {
        return div(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub div(final Vec2ub res, final Vec2ub a, final UByte b) {
        return div(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub div(final Vec2ub res, final Vec2ub a, final byte b) {
        return div(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub div(final Vec2ub res, final Vec2ub a, final int b) {
        return div(res, a, b, b);
    }

    public static Vec2ub div(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return div(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub div(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return div(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub div(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return div(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub div(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) Integer.divideUnsigned(a.x.value & 0xff, bX);
        res.y.value = (byte) Integer.divideUnsigned(a.y.value & 0xff, bY);
        return res;
    }

    public static Vec2ui div_(final Vec2ui a, final UInt b) {
        return div(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui div_(final Vec2ui a, final int b) {
        return div(new Vec2ui(), a, b, b);
    }

    public static Vec2ui div_(final Vec2ui a, final Vec2ui b) {
        return div(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui div(final Vec2ui a, final UInt b) {
        return div(a, a, b.value, b.value);
    }

    public static Vec2ui div(final Vec2ui a, final int b) {
        return div(a, a, b, b);
    }

    public static Vec2ui div(final Vec2ui a, final Vec2ui b) {
        return div(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui div(final Vec2ui res, final Vec2ui a, final UInt b) {
        return div(res, a, b.value, b.value);
    }

    public static Vec2ui div(final Vec2ui res, final Vec2ui a, final int b) {
        return div(res, a, b, b);
    }

    public static Vec2ui div(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return div(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui div(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return div(res, a, bX.value, bY.value);
    }

    public static Vec2ui div(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = Integer.divideUnsigned(a.x.value, bX);
        res.y.value = Integer.divideUnsigned(a.y.value, bY);
        return res;
    }

    public static Vec2ul div_(final Vec2ul a, final ULong b) {
        return div(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul div_(final Vec2ul a, final long b) {
        return div(new Vec2ul(), a, b, b);
    }

    public static Vec2ul div_(final Vec2ul a, final Vec2ul b) {
        return div(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul div(final Vec2ul a, final ULong b) {
        return div(a, a, b.value, b.value);
    }

    public static Vec2ul div(final Vec2ul a, final long b) {
        return div(a, a, b, b);
    }

    public static Vec2ul div(final Vec2ul a, final Vec2ul b) {
        return div(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul div(final Vec2ul res, final Vec2ul a, final ULong b) {
        return div(res, a, b.value, b.value);
    }

    public static Vec2ul div(final Vec2ul res, final Vec2ul a, final long b) {
        return div(res, a, b, b);
    }

    public static Vec2ul div(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return div(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul div(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return div(res, a, bX.value, bY.value);
    }

    public static Vec2ul div(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = Long.divideUnsigned(a.x.value, bX);
        res.y.value = Long.divideUnsigned(a.y.value, bY);
        return res;
    }

    public static Vec2us div_(final Vec2us a, final UShort b) {
        return div(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us div_(final Vec2us a, final short b) {
        return div(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us div_(final Vec2us a, final int b) {
        return div(new Vec2us(), a, b, b);
    }

    public static Vec2us div_(final Vec2us a, final Vec2us b) {
        return div(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us div(final Vec2us a, final UShort b) {
        return div(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us div(final Vec2us a, final short b) {
        return div(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us div(final Vec2us a, final int b) {
        return div(a, a, b, b);
    }

    public static Vec2us div(final Vec2us a, final Vec2us b) {
        return div(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us div(final Vec2us res, final Vec2us a, final UShort b) {
        return div(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us div(final Vec2us res, final Vec2us a, final short b) {
        return div(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us div(final Vec2us res, final Vec2us a, final int b) {
        return div(res, a, b, b);
    }

    public static Vec2us div(final Vec2us res, final Vec2us a, final Vec2us b) {
        return div(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us div(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return div(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us div(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return div(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us div(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) Integer.divideUnsigned(a.x.value & 0xffff, bX);
        res.y.value = (short) Integer.divideUnsigned(a.y.value & 0xffff, bY);
        return res;
    }

    public static Vec3 div_(final Vec3 a, final float b) {
        return div(new Vec3(), a, b, b, b);
    }

    public static Vec3 div_(final Vec3 a, final Vec3 b) {
        return div(new Vec3(), a, b.x, b.y, b.z);
    }

    public static Vec3 div(final Vec3 a, final float b) {
        return div(a, a, b, b, b);
    }

    public static Vec3 div(final Vec3 a, final Vec3 b) {
        return div(a, a, b.x, b.y, b.z);
    }

    public static Vec3 div(final Vec3 res, final Vec3 a, final float b) {
        return div(res, a, b, b, b);
    }

    public static Vec3 div(final Vec3 res, final Vec3 a, final Vec3 b) {
        return div(res, a, b.x, b.y, b.z);
    }

    public static Vec3 div(final Vec3 res, final Vec3 a, final float bX, final float bY, final float bZ) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        res.z = a.z / bZ;
        return res;
    }

    public static Vec3b div_(final Vec3b a, final byte b) {
        return div(new Vec3b(), a, b, b, b);
    }

    public static Vec3b div_(final Vec3b a, final int b) {
        return div(new Vec3b(), a, b, b, b);
    }

    public static Vec3b div_(final Vec3b a, final Vec3b b) {
        return div(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b div(final Vec3b a, final byte b) {
        return div(a, a, b, b, b);
    }

    public static Vec3b div(final Vec3b a, final int b) {
        return div(a, a, b, b, b);
    }

    public static Vec3b div(final Vec3b a, final Vec3b b) {
        return div(a, a, b.x, b.y, b.z);
    }

    public static Vec3b div(final Vec3b res, final Vec3b a, final byte b) {
        return div(res, a, b, b, b);
    }

    public static Vec3b div(final Vec3b res, final Vec3b a, final int b) {
        return div(res, a, b, b, b);
    }

    public static Vec3b div(final Vec3b res, final Vec3b a, final Vec3b b) {
        return div(res, a, b.x, b.y, b.z);
    }

    public static Vec3b div(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return div(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b div(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x / bX);
        res.y = (byte) (a.y / bY);
        res.z = (byte) (a.z / bZ);
        return res;
    }

    public static Vec3d div_(final Vec3d a, final double b) {
        return div(new Vec3d(), a, b, b, b);
    }

    public static Vec3d div_(final Vec3d a, final Vec3d b) {
        return div(new Vec3d(), a, b.x, b.y, b.z);
    }

    public static Vec3d div(final Vec3d a, final double b) {
        return div(a, a, b, b, b);
    }

    public static Vec3d div(final Vec3d a, final Vec3d b) {
        return div(a, a, b.x, b.y, b.z);
    }

    public static Vec3d div(final Vec3d res, final Vec3d a, final double b) {
        return div(res, a, b, b, b);
    }

    public static Vec3d div(final Vec3d res, final Vec3d a, final Vec3d b) {
        return div(res, a, b.x, b.y, b.z);
    }

    public static Vec3d div(final Vec3d res, final Vec3d a, final double bX, final double bY, final double bZ) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        res.z = a.z / bZ;
        return res;
    }

    public static Vec3i div_(final Vec3i a, final int b) {
        return div(new Vec3i(), a, b, b, b);
    }

    public static Vec3i div_(final Vec3i a, final Vec3i b) {
        return div(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i div(final Vec3i a, final int b) {
        return div(a, a, b, b, b);
    }

    public static Vec3i div(final Vec3i a, final Vec3i b) {
        return div(a, a, b.x, b.y, b.z);
    }

    public static Vec3i div(final Vec3i res, final Vec3i a, final int b) {
        return div(res, a, b, b, b);
    }

    public static Vec3i div(final Vec3i res, final Vec3i a, final Vec3i b) {
        return div(res, a, b.x, b.y, b.z);
    }

    public static Vec3i div(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        res.z = a.z / bZ;
        return res;
    }

    public static Vec3l div_(final Vec3l a, final long b) {
        return div(new Vec3l(), a, b, b, b);
    }

    public static Vec3l div_(final Vec3l a, final Vec3l b) {
        return div(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l div(final Vec3l a, final long b) {
        return div(a, a, b, b, b);
    }

    public static Vec3l div(final Vec3l a, final Vec3l b) {
        return div(a, a, b.x, b.y, b.z);
    }

    public static Vec3l div(final Vec3l res, final Vec3l a, final long b) {
        return div(res, a, b, b, b);
    }

    public static Vec3l div(final Vec3l res, final Vec3l a, final Vec3l b) {
        return div(res, a, b.x, b.y, b.z);
    }

    public static Vec3l div(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        res.z = a.z / bZ;
        return res;
    }

    public static Vec3s div_(final Vec3s a, final short b) {
        return div(new Vec3s(), a, b, b, b);
    }

    public static Vec3s div_(final Vec3s a, final int b) {
        return div(new Vec3s(), a, b, b, b);
    }

    public static Vec3s div_(final Vec3s a, final Vec3s b) {
        return div(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s div(final Vec3s a, final short b) {
        return div(a, a, b, b, b);
    }

    public static Vec3s div(final Vec3s a, final int b) {
        return div(a, a, b, b, b);
    }

    public static Vec3s div(final Vec3s a, final Vec3s b) {
        return div(a, a, b.x, b.y, b.z);
    }

    public static Vec3s div(final Vec3s res, final Vec3s a, final short b) {
        return div(res, a, b, b, b);
    }

    public static Vec3s div(final Vec3s res, final Vec3s a, final int b) {
        return div(res, a, b, b, b);
    }

    public static Vec3s div(final Vec3s res, final Vec3s a, final Vec3s b) {
        return div(res, a, b.x, b.y, b.z);
    }

    public static Vec3s div(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return div(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s div(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x / bX);
        res.y = (short) (a.y / bY);
        res.z = (short) (a.z / bZ);
        return res;
    }

    public static Vec3ub div_(final Vec3ub a, final UByte b) {
        return div(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub div_(final Vec3ub a, final byte b) {
        return div(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub div_(final Vec3ub a, final int b) {
        return div(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub div_(final Vec3ub a, final Vec3ub b) {
        return div(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub div(final Vec3ub a, final UByte b) {
        return div(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub div(final Vec3ub a, final byte b) {
        return div(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub div(final Vec3ub a, final int b) {
        return div(a, a, b, b, b);
    }

    public static Vec3ub div(final Vec3ub a, final Vec3ub b) {
        return div(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub div(final Vec3ub res, final Vec3ub a, final UByte b) {
        return div(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub div(final Vec3ub res, final Vec3ub a, final byte b) {
        return div(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub div(final Vec3ub res, final Vec3ub a, final int b) {
        return div(res, a, b, b, b);
    }

    public static Vec3ub div(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return div(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub div(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return div(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub div(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return div(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub div(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) Integer.divideUnsigned(a.x.value & 0xff, bX);
        res.y.value = (byte) Integer.divideUnsigned(a.y.value & 0xff, bY);
        res.z.value = (byte) Integer.divideUnsigned(a.z.value & 0xff, bZ);
        return res;
    }

    public static Vec3ui div_(final Vec3ui a, final UInt b) {
        return div(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui div_(final Vec3ui a, final int b) {
        return div(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui div_(final Vec3ui a, final Vec3ui b) {
        return div(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui div(final Vec3ui a, final UInt b) {
        return div(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui div(final Vec3ui a, final int b) {
        return div(a, a, b, b, b);
    }

    public static Vec3ui div(final Vec3ui a, final Vec3ui b) {
        return div(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui div(final Vec3ui res, final Vec3ui a, final UInt b) {
        return div(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui div(final Vec3ui res, final Vec3ui a, final int b) {
        return div(res, a, b, b, b);
    }

    public static Vec3ui div(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return div(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui div(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return div(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui div(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = Integer.divideUnsigned(a.x.value, bX);
        res.y.value = Integer.divideUnsigned(a.y.value, bY);
        res.z.value = Integer.divideUnsigned(a.z.value, bZ);
        return res;
    }

    public static Vec3ul div_(final Vec3ul a, final ULong b) {
        return div(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul div_(final Vec3ul a, final long b) {
        return div(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul div_(final Vec3ul a, final Vec3ul b) {
        return div(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul div(final Vec3ul a, final ULong b) {
        return div(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul div(final Vec3ul a, final long b) {
        return div(a, a, b, b, b);
    }

    public static Vec3ul div(final Vec3ul a, final Vec3ul b) {
        return div(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul div(final Vec3ul res, final Vec3ul a, final ULong b) {
        return div(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul div(final Vec3ul res, final Vec3ul a, final long b) {
        return div(res, a, b, b, b);
    }

    public static Vec3ul div(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return div(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul div(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return div(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul div(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = Long.divideUnsigned(a.x.value, bX);
        res.y.value = Long.divideUnsigned(a.y.value, bY);
        res.z.value = Long.divideUnsigned(a.z.value, bZ);
        return res;
    }

    public static Vec3us div_(final Vec3us a, final UShort b) {
        return div(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us div_(final Vec3us a, final short b) {
        return div(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us div_(final Vec3us a, final int b) {
        return div(new Vec3us(), a, b, b, b);
    }

    public static Vec3us div_(final Vec3us a, final Vec3us b) {
        return div(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us div(final Vec3us a, final UShort b) {
        return div(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us div(final Vec3us a, final short b) {
        return div(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us div(final Vec3us a, final int b) {
        return div(a, a, b, b, b);
    }

    public static Vec3us div(final Vec3us a, final Vec3us b) {
        return div(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us div(final Vec3us res, final Vec3us a, final UShort b) {
        return div(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us div(final Vec3us res, final Vec3us a, final short b) {
        return div(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us div(final Vec3us res, final Vec3us a, final int b) {
        return div(res, a, b, b, b);
    }

    public static Vec3us div(final Vec3us res, final Vec3us a, final Vec3us b) {
        return div(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us div(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return div(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us div(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return div(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us div(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) Integer.divideUnsigned(a.x.value & 0xffff, bX);
        res.y.value = (short) Integer.divideUnsigned(a.y.value & 0xffff, bY);
        res.z.value = (short) Integer.divideUnsigned(a.z.value & 0xffff, bZ);
        return res;
    }

    public static Vec4 div_(final Vec4 a, final float b) {
        return div(new Vec4(), a, b, b, b, b);
    }

    public static Vec4 div_(final Vec4 a, final Vec4 b) {
        return div(new Vec4(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 div(final Vec4 a, final float b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4 div(final Vec4 a, final Vec4 b) {
        return div(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 div(final Vec4 res, final Vec4 a, final float b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4 div(final Vec4 res, final Vec4 a, final Vec4 b) {
        return div(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4 div(final Vec4 res, final Vec4 a, final float bX, final float bY, final float bZ, final float bW) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        res.z = a.z / bZ;
        res.w = a.w / bW;
        return res;
    }

    public static Vec4b div_(final Vec4b a, final byte b) {
        return div(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b div_(final Vec4b a, final int b) {
        return div(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b div_(final Vec4b a, final Vec4b b) {
        return div(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b div(final Vec4b a, final byte b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4b div(final Vec4b a, final int b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4b div(final Vec4b a, final Vec4b b) {
        return div(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b div(final Vec4b res, final Vec4b a, final byte b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4b div(final Vec4b res, final Vec4b a, final int b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4b div(final Vec4b res, final Vec4b a, final Vec4b b) {
        return div(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b div(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return div(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b div(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x / bX);
        res.y = (byte) (a.y / bY);
        res.z = (byte) (a.z / bZ);
        res.w = (byte) (a.w / bW);
        return res;
    }

    public static Vec4d div_(final Vec4d a, final double b) {
        return div(new Vec4d(), a, b, b, b, b);
    }

    public static Vec4d div_(final Vec4d a, final Vec4d b) {
        return div(new Vec4d(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d div(final Vec4d a, final double b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4d div(final Vec4d a, final Vec4d b) {
        return div(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d div(final Vec4d res, final Vec4d a, final double b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4d div(final Vec4d res, final Vec4d a, final Vec4d b) {
        return div(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4d div(final Vec4d res, final Vec4d a, final double bX, final double bY, final double bZ, final double bW) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        res.z = a.z / bZ;
        res.w = a.w / bW;
        return res;
    }

    public static Vec4i div_(final Vec4i a, final int b) {
        return div(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i div_(final Vec4i a, final Vec4i b) {
        return div(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i div(final Vec4i a, final int b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4i div(final Vec4i a, final Vec4i b) {
        return div(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i div(final Vec4i res, final Vec4i a, final int b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4i div(final Vec4i res, final Vec4i a, final Vec4i b) {
        return div(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i div(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        res.z = a.z / bZ;
        res.w = a.w / bW;
        return res;
    }

    public static Vec4l div_(final Vec4l a, final long b) {
        return div(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l div_(final Vec4l a, final Vec4l b) {
        return div(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l div(final Vec4l a, final long b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4l div(final Vec4l a, final Vec4l b) {
        return div(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l div(final Vec4l res, final Vec4l a, final long b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4l div(final Vec4l res, final Vec4l a, final Vec4l b) {
        return div(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l div(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x / bX;
        res.y = a.y / bY;
        res.z = a.z / bZ;
        res.w = a.w / bW;
        return res;
    }

    public static Vec4s div_(final Vec4s a, final short b) {
        return div(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s div_(final Vec4s a, final int b) {
        return div(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s div_(final Vec4s a, final Vec4s b) {
        return div(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s div(final Vec4s a, final short b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4s div(final Vec4s a, final int b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4s div(final Vec4s a, final Vec4s b) {
        return div(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s div(final Vec4s res, final Vec4s a, final short b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4s div(final Vec4s res, final Vec4s a, final int b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4s div(final Vec4s res, final Vec4s a, final Vec4s b) {
        return div(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s div(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return div(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s div(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x / bX);
        res.y = (short) (a.y / bY);
        res.z = (short) (a.z / bZ);
        res.w = (short) (a.w / bW);
        return res;
    }

    public static Vec4ub div_(final Vec4ub a, final UByte b) {
        return div(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub div_(final Vec4ub a, final byte b) {
        return div(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub div_(final Vec4ub a, final int b) {
        return div(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub div_(final Vec4ub a, final Vec4ub b) {
        return div(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub div(final Vec4ub a, final UByte b) {
        return div(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub div(final Vec4ub a, final byte b) {
        return div(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub div(final Vec4ub a, final int b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4ub div(final Vec4ub a, final Vec4ub b) {
        return div(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final Vec4ub a, final UByte b) {
        return div(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final Vec4ub a, final byte b) {
        return div(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final Vec4ub a, final int b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4ub div(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return div(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return div(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return div(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) Integer.divideUnsigned(a.x.value & 0xff, bX);
        res.y.value = (byte) Integer.divideUnsigned(a.y.value & 0xff, bY);
        res.z.value = (byte) Integer.divideUnsigned(a.z.value & 0xff, bZ);
        res.w.value = (byte) Integer.divideUnsigned(a.w.value & 0xff, bW);
        return res;
    }

    public static Vec4ui div_(final Vec4ui a, final UInt b) {
        return div(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui div_(final Vec4ui a, final int b) {
        return div(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui div_(final Vec4ui a, final Vec4ui b) {
        return div(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui div(final Vec4ui a, final UInt b) {
        return div(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui div(final Vec4ui a, final int b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4ui div(final Vec4ui a, final Vec4ui b) {
        return div(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui div(final Vec4ui res, final Vec4ui a, final UInt b) {
        return div(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui div(final Vec4ui res, final Vec4ui a, final int b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4ui div(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return div(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui div(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return div(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui div(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = Integer.divideUnsigned(a.x.value, bX);
        res.y.value = Integer.divideUnsigned(a.y.value, bY);
        res.z.value = Integer.divideUnsigned(a.z.value, bZ);
        res.w.value = Integer.divideUnsigned(a.w.value, bW);
        return res;
    }

    public static Vec4ul div_(final Vec4ul a, final ULong b) {
        return div(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul div_(final Vec4ul a, final long b) {
        return div(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul div_(final Vec4ul a, final Vec4ul b) {
        return div(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul div(final Vec4ul a, final ULong b) {
        return div(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul div(final Vec4ul a, final long b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4ul div(final Vec4ul a, final Vec4ul b) {
        return div(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul div(final Vec4ul res, final Vec4ul a, final ULong b) {
        return div(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul div(final Vec4ul res, final Vec4ul a, final long b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4ul div(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return div(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul div(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return div(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul div(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = Long.divideUnsigned(a.x.value, bX);
        res.y.value = Long.divideUnsigned(a.y.value, bY);
        res.z.value = Long.divideUnsigned(a.z.value, bZ);
        res.w.value = Long.divideUnsigned(a.w.value, bW);
        return res;
    }

    public static Vec4us div_(final Vec4us a, final UShort b) {
        return div(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us div_(final Vec4us a, final short b) {
        return div(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us div_(final Vec4us a, final int b) {
        return div(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us div_(final Vec4us a, final Vec4us b) {
        return div(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us div(final Vec4us a, final UShort b) {
        return div(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us div(final Vec4us a, final short b) {
        return div(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us div(final Vec4us a, final int b) {
        return div(a, a, b, b, b, b);
    }

    public static Vec4us div(final Vec4us a, final Vec4us b) {
        return div(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us div(final Vec4us res, final Vec4us a, final UShort b) {
        return div(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us div(final Vec4us res, final Vec4us a, final short b) {
        return div(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us div(final Vec4us res, final Vec4us a, final int b) {
        return div(res, a, b, b, b, b);
    }

    public static Vec4us div(final Vec4us res, final Vec4us a, final Vec4us b) {
        return div(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us div(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return div(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us div(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return div(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us div(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) Integer.divideUnsigned(a.x.value & 0xffff, bX);
        res.y.value = (short) Integer.divideUnsigned(a.y.value & 0xffff, bY);
        res.z.value = (short) Integer.divideUnsigned(a.z.value & 0xffff, bZ);
        res.w.value = (short) Integer.divideUnsigned(a.w.value & 0xffff, bW);
        return res;
    }

    public static Vec2 incr_(final Vec2 v) {
        return incr(new Vec2(), v);
    }

    public static Vec2 incr(final Vec2 v) {
        return incr(v, v);
    }

    public static Vec2 incr(final Vec2 res, final Vec2 a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        return res;
    }

    public static Vec2b incr_(final Vec2b v) {
        return incr(new Vec2b(), v);
    }

    public static Vec2b incr(final Vec2b v) {
        return incr(v, v);
    }

    public static Vec2b incr(final Vec2b res, final Vec2b a) {
        res.x = (byte) (a.x + 1);
        res.y = (byte) (a.y + 1);
        return res;
    }

    public static Vec2d incr_(final Vec2d v) {
        return incr(new Vec2d(), v);
    }

    public static Vec2d incr(final Vec2d v) {
        return incr(v, v);
    }

    public static Vec2d incr(final Vec2d res, final Vec2d a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        return res;
    }

    public static Vec2i incr_(final Vec2i v) {
        return incr(new Vec2i(), v);
    }

    public static Vec2i incr(final Vec2i v) {
        return incr(v, v);
    }

    public static Vec2i incr(final Vec2i res, final Vec2i a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        return res;
    }

    public static Vec2l incr_(final Vec2l v) {
        return incr(new Vec2l(), v);
    }

    public static Vec2l incr(final Vec2l v) {
        return incr(v, v);
    }

    public static Vec2l incr(final Vec2l res, final Vec2l a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        return res;
    }

    public static Vec2s incr_(final Vec2s v) {
        return incr(new Vec2s(), v);
    }

    public static Vec2s incr(final Vec2s v) {
        return incr(v, v);
    }

    public static Vec2s incr(final Vec2s res, final Vec2s a) {
        res.x = (short) (a.x + 1);
        res.y = (short) (a.y + 1);
        return res;
    }

    public static Vec2ub incr_(final Vec2ub v) {
        return incr(new Vec2ub(), v);
    }

    public static Vec2ub incr(final Vec2ub v) {
        return incr(v, v);
    }

    public static Vec2ub incr(final Vec2ub res, final Vec2ub a) {
        res.x.value = (byte) ((a.x.value & 0xff) + 1);
        res.y.value = (byte) ((a.y.value & 0xff) + 1);
        return res;
    }

    public static Vec2ui incr_(final Vec2ui v) {
        return incr(new Vec2ui(), v);
    }

    public static Vec2ui incr(final Vec2ui v) {
        return incr(v, v);
    }

    public static Vec2ui incr(final Vec2ui res, final Vec2ui a) {
        res.x.value = a.x.value + 1;
        res.y.value = a.y.value + 1;
        return res;
    }

    public static Vec2ul incr_(final Vec2ul v) {
        return incr(new Vec2ul(), v);
    }

    public static Vec2ul incr(final Vec2ul v) {
        return incr(v, v);
    }

    public static Vec2ul incr(final Vec2ul res, final Vec2ul a) {
        res.x.value = a.x.value + 1;
        res.y.value = a.y.value + 1;
        return res;
    }

    public static Vec2us incr_(final Vec2us v) {
        return incr(new Vec2us(), v);
    }

    public static Vec2us incr(final Vec2us v) {
        return incr(v, v);
    }

    public static Vec2us incr(final Vec2us res, final Vec2us a) {
        res.x.value = (short) ((a.x.value & 0xffff) + 1);
        res.y.value = (short) ((a.y.value & 0xffff) + 1);
        return res;
    }

    public static Vec3 incr_(final Vec3 v) {
        return incr(new Vec3(), v);
    }

    public static Vec3 incr(final Vec3 v) {
        return incr(v, v);
    }

    public static Vec3 incr(final Vec3 res, final Vec3 a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        res.z = a.z + 1;
        return res;
    }

    public static Vec3b incr_(final Vec3b v) {
        return incr(new Vec3b(), v);
    }

    public static Vec3b incr(final Vec3b v) {
        return incr(v, v);
    }

    public static Vec3b incr(final Vec3b res, final Vec3b a) {
        res.x = (byte) (a.x + 1);
        res.y = (byte) (a.y + 1);
        res.z = (byte) (a.z + 1);
        return res;
    }

    public static Vec3d incr_(final Vec3d v) {
        return incr(new Vec3d(), v);
    }

    public static Vec3d incr(final Vec3d v) {
        return incr(v, v);
    }

    public static Vec3d incr(final Vec3d res, final Vec3d a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        res.z = a.z + 1;
        return res;
    }

    public static Vec3i incr_(final Vec3i v) {
        return incr(new Vec3i(), v);
    }

    public static Vec3i incr(final Vec3i v) {
        return incr(v, v);
    }

    public static Vec3i incr(final Vec3i res, final Vec3i a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        res.z = a.z + 1;
        return res;
    }

    public static Vec3l incr_(final Vec3l v) {
        return incr(new Vec3l(), v);
    }

    public static Vec3l incr(final Vec3l v) {
        return incr(v, v);
    }

    public static Vec3l incr(final Vec3l res, final Vec3l a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        res.z = a.z + 1;
        return res;
    }

    public static Vec3s incr_(final Vec3s v) {
        return incr(new Vec3s(), v);
    }

    public static Vec3s incr(final Vec3s v) {
        return incr(v, v);
    }

    public static Vec3s incr(final Vec3s res, final Vec3s a) {
        res.x = (short) (a.x + 1);
        res.y = (short) (a.y + 1);
        res.z = (short) (a.z + 1);
        return res;
    }

    public static Vec3ub incr_(final Vec3ub v) {
        return incr(new Vec3ub(), v);
    }

    public static Vec3ub incr(final Vec3ub v) {
        return incr(v, v);
    }

    public static Vec3ub incr(final Vec3ub res, final Vec3ub a) {
        res.x.value = (byte) ((a.x.value & 0xff) + 1);
        res.y.value = (byte) ((a.y.value & 0xff) + 1);
        res.z.value = (byte) ((a.z.value & 0xff) + 1);
        return res;
    }

    public static Vec3ui incr_(final Vec3ui v) {
        return incr(new Vec3ui(), v);
    }

    public static Vec3ui incr(final Vec3ui v) {
        return incr(v, v);
    }

    public static Vec3ui incr(final Vec3ui res, final Vec3ui a) {
        res.x.value = a.x.value + 1;
        res.y.value = a.y.value + 1;
        res.z.value = a.z.value + 1;
        return res;
    }

    public static Vec3ul incr_(final Vec3ul v) {
        return incr(new Vec3ul(), v);
    }

    public static Vec3ul incr(final Vec3ul v) {
        return incr(v, v);
    }

    public static Vec3ul incr(final Vec3ul res, final Vec3ul a) {
        res.x.value = a.x.value + 1;
        res.y.value = a.y.value + 1;
        res.z.value = a.z.value + 1;
        return res;
    }

    public static Vec3us incr_(final Vec3us v) {
        return incr(new Vec3us(), v);
    }

    public static Vec3us incr(final Vec3us v) {
        return incr(v, v);
    }

    public static Vec3us incr(final Vec3us res, final Vec3us a) {
        res.x.value = (short) ((a.x.value & 0xffff) + 1);
        res.y.value = (short) ((a.y.value & 0xffff) + 1);
        res.z.value = (short) ((a.z.value & 0xffff) + 1);
        return res;
    }

    public static Vec4 incr_(final Vec4 v) {
        return incr(new Vec4(), v);
    }

    public static Vec4 incr(final Vec4 v) {
        return incr(v, v);
    }

    public static Vec4 incr(final Vec4 res, final Vec4 a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        res.z = a.z + 1;
        res.w = a.w + 1;
        return res;
    }

    public static Vec4b incr_(final Vec4b v) {
        return incr(new Vec4b(), v);
    }

    public static Vec4b incr(final Vec4b v) {
        return incr(v, v);
    }

    public static Vec4b incr(final Vec4b res, final Vec4b a) {
        res.x = (byte) (a.x + 1);
        res.y = (byte) (a.y + 1);
        res.z = (byte) (a.z + 1);
        res.w = (byte) (a.w + 1);
        return res;
    }

    public static Vec4d incr_(final Vec4d v) {
        return incr(new Vec4d(), v);
    }

    public static Vec4d incr(final Vec4d v) {
        return incr(v, v);
    }

    public static Vec4d incr(final Vec4d res, final Vec4d a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        res.z = a.z + 1;
        res.w = a.w + 1;
        return res;
    }

    public static Vec4i incr_(final Vec4i v) {
        return incr(new Vec4i(), v);
    }

    public static Vec4i incr(final Vec4i v) {
        return incr(v, v);
    }

    public static Vec4i incr(final Vec4i res, final Vec4i a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        res.z = a.z + 1;
        res.w = a.w + 1;
        return res;
    }

    public static Vec4l incr_(final Vec4l v) {
        return incr(new Vec4l(), v);
    }

    public static Vec4l incr(final Vec4l v) {
        return incr(v, v);
    }

    public static Vec4l incr(final Vec4l res, final Vec4l a) {
        res.x = a.x + 1;
        res.y = a.y + 1;
        res.z = a.z + 1;
        res.w = a.w + 1;
        return res;
    }

    public static Vec4s incr_(final Vec4s v) {
        return incr(new Vec4s(), v);
    }

    public static Vec4s incr(final Vec4s v) {
        return incr(v, v);
    }

    public static Vec4s incr(final Vec4s res, final Vec4s a) {
        res.x = (short) (a.x + 1);
        res.y = (short) (a.y + 1);
        res.z = (short) (a.z + 1);
        res.w = (short) (a.w + 1);
        return res;
    }

    public static Vec4ub incr_(final Vec4ub v) {
        return incr(new Vec4ub(), v);
    }

    public static Vec4ub incr(final Vec4ub v) {
        return incr(v, v);
    }

    public static Vec4ub incr(final Vec4ub res, final Vec4ub a) {
        res.x.value = (byte) ((a.x.value & 0xff) + 1);
        res.y.value = (byte) ((a.y.value & 0xff) + 1);
        res.z.value = (byte) ((a.z.value & 0xff) + 1);
        res.w.value = (byte) ((a.w.value & 0xff) + 1);
        return res;
    }

    public static Vec4ui incr_(final Vec4ui v) {
        return incr(new Vec4ui(), v);
    }

    public static Vec4ui incr(final Vec4ui v) {
        return incr(v, v);
    }

    public static Vec4ui incr(final Vec4ui res, final Vec4ui a) {
        res.x.value = a.x.value + 1;
        res.y.value = a.y.value + 1;
        res.z.value = a.z.value + 1;
        res.w.value = a.w.value + 1;
        return res;
    }

    public static Vec4ul incr_(final Vec4ul v) {
        return incr(new Vec4ul(), v);
    }

    public static Vec4ul incr(final Vec4ul v) {
        return incr(v, v);
    }

    public static Vec4ul incr(final Vec4ul res, final Vec4ul a) {
        res.x.value = a.x.value + 1;
        res.y.value = a.y.value + 1;
        res.z.value = a.z.value + 1;
        res.w.value = a.w.value + 1;
        return res;
    }

    public static Vec4us incr_(final Vec4us v) {
        return incr(new Vec4us(), v);
    }

    public static Vec4us incr(final Vec4us v) {
        return incr(v, v);
    }

    public static Vec4us incr(final Vec4us res, final Vec4us a) {
        res.x.value = (short) ((a.x.value & 0xffff) + 1);
        res.y.value = (short) ((a.y.value & 0xffff) + 1);
        res.z.value = (short) ((a.z.value & 0xffff) + 1);
        res.w.value = (short) ((a.w.value & 0xffff) + 1);
        return res;
    }

    public static Vec2 decr_(final Vec2 v) {
        return decr(new Vec2(), v);
    }

    public static Vec2 decr(final Vec2 v) {
        return decr(v, v);
    }

    public static Vec2 decr(final Vec2 res, final Vec2 a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        return res;
    }

    public static Vec2b decr_(final Vec2b v) {
        return decr(new Vec2b(), v);
    }

    public static Vec2b decr(final Vec2b v) {
        return decr(v, v);
    }

    public static Vec2b decr(final Vec2b res, final Vec2b a) {
        res.x = (byte) (a.x - 1);
        res.y = (byte) (a.y - 1);
        return res;
    }

    public static Vec2d decr_(final Vec2d v) {
        return decr(new Vec2d(), v);
    }

    public static Vec2d decr(final Vec2d v) {
        return decr(v, v);
    }

    public static Vec2d decr(final Vec2d res, final Vec2d a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        return res;
    }

    public static Vec2i decr_(final Vec2i v) {
        return decr(new Vec2i(), v);
    }

    public static Vec2i decr(final Vec2i v) {
        return decr(v, v);
    }

    public static Vec2i decr(final Vec2i res, final Vec2i a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        return res;
    }

    public static Vec2l decr_(final Vec2l v) {
        return decr(new Vec2l(), v);
    }

    public static Vec2l decr(final Vec2l v) {
        return decr(v, v);
    }

    public static Vec2l decr(final Vec2l res, final Vec2l a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        return res;
    }

    public static Vec2s decr_(final Vec2s v) {
        return decr(new Vec2s(), v);
    }

    public static Vec2s decr(final Vec2s v) {
        return decr(v, v);
    }

    public static Vec2s decr(final Vec2s res, final Vec2s a) {
        res.x = (short) (a.x - 1);
        res.y = (short) (a.y - 1);
        return res;
    }

    public static Vec2ub decr_(final Vec2ub v) {
        return decr(new Vec2ub(), v);
    }

    public static Vec2ub decr(final Vec2ub v) {
        return decr(v, v);
    }

    public static Vec2ub decr(final Vec2ub res, final Vec2ub a) {
        res.x.value = (byte) ((a.x.value & 0xff) - 1);
        res.y.value = (byte) ((a.y.value & 0xff) - 1);
        return res;
    }

    public static Vec2ui decr_(final Vec2ui v) {
        return decr(new Vec2ui(), v);
    }

    public static Vec2ui decr(final Vec2ui v) {
        return decr(v, v);
    }

    public static Vec2ui decr(final Vec2ui res, final Vec2ui a) {
        res.x.value = a.x.value - 1;
        res.y.value = a.y.value - 1;
        return res;
    }

    public static Vec2ul decr_(final Vec2ul v) {
        return decr(new Vec2ul(), v);
    }

    public static Vec2ul decr(final Vec2ul v) {
        return decr(v, v);
    }

    public static Vec2ul decr(final Vec2ul res, final Vec2ul a) {
        res.x.value = a.x.value - 1;
        res.y.value = a.y.value - 1;
        return res;
    }

    public static Vec2us decr_(final Vec2us v) {
        return decr(new Vec2us(), v);
    }

    public static Vec2us decr(final Vec2us v) {
        return decr(v, v);
    }

    public static Vec2us decr(final Vec2us res, final Vec2us a) {
        res.x.value = (short) ((a.x.value & 0xffff) - 1);
        res.y.value = (short) ((a.y.value & 0xffff) - 1);
        return res;
    }

    public static Vec3 decr_(final Vec3 v) {
        return decr(new Vec3(), v);
    }

    public static Vec3 decr(final Vec3 v) {
        return decr(v, v);
    }

    public static Vec3 decr(final Vec3 res, final Vec3 a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        res.z = a.z - 1;
        return res;
    }

    public static Vec3b decr_(final Vec3b v) {
        return decr(new Vec3b(), v);
    }

    public static Vec3b decr(final Vec3b v) {
        return decr(v, v);
    }

    public static Vec3b decr(final Vec3b res, final Vec3b a) {
        res.x = (byte) (a.x - 1);
        res.y = (byte) (a.y - 1);
        res.z = (byte) (a.z - 1);
        return res;
    }

    public static Vec3d decr_(final Vec3d v) {
        return decr(new Vec3d(), v);
    }

    public static Vec3d decr(final Vec3d v) {
        return decr(v, v);
    }

    public static Vec3d decr(final Vec3d res, final Vec3d a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        res.z = a.z - 1;
        return res;
    }

    public static Vec3i decr_(final Vec3i v) {
        return decr(new Vec3i(), v);
    }

    public static Vec3i decr(final Vec3i v) {
        return decr(v, v);
    }

    public static Vec3i decr(final Vec3i res, final Vec3i a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        res.z = a.z - 1;
        return res;
    }

    public static Vec3l decr_(final Vec3l v) {
        return decr(new Vec3l(), v);
    }

    public static Vec3l decr(final Vec3l v) {
        return decr(v, v);
    }

    public static Vec3l decr(final Vec3l res, final Vec3l a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        res.z = a.z - 1;
        return res;
    }

    public static Vec3s decr_(final Vec3s v) {
        return decr(new Vec3s(), v);
    }

    public static Vec3s decr(final Vec3s v) {
        return decr(v, v);
    }

    public static Vec3s decr(final Vec3s res, final Vec3s a) {
        res.x = (short) (a.x - 1);
        res.y = (short) (a.y - 1);
        res.z = (short) (a.z - 1);
        return res;
    }

    public static Vec3ub decr_(final Vec3ub v) {
        return decr(new Vec3ub(), v);
    }

    public static Vec3ub decr(final Vec3ub v) {
        return decr(v, v);
    }

    public static Vec3ub decr(final Vec3ub res, final Vec3ub a) {
        res.x.value = (byte) ((a.x.value & 0xff) - 1);
        res.y.value = (byte) ((a.y.value & 0xff) - 1);
        res.z.value = (byte) ((a.z.value & 0xff) - 1);
        return res;
    }

    public static Vec3ui decr_(final Vec3ui v) {
        return decr(new Vec3ui(), v);
    }

    public static Vec3ui decr(final Vec3ui v) {
        return decr(v, v);
    }

    public static Vec3ui decr(final Vec3ui res, final Vec3ui a) {
        res.x.value = a.x.value - 1;
        res.y.value = a.y.value - 1;
        res.z.value = a.z.value - 1;
        return res;
    }

    public static Vec3ul decr_(final Vec3ul v) {
        return decr(new Vec3ul(), v);
    }

    public static Vec3ul decr(final Vec3ul v) {
        return decr(v, v);
    }

    public static Vec3ul decr(final Vec3ul res, final Vec3ul a) {
        res.x.value = a.x.value - 1;
        res.y.value = a.y.value - 1;
        res.z.value = a.z.value - 1;
        return res;
    }

    public static Vec3us decr_(final Vec3us v) {
        return decr(new Vec3us(), v);
    }

    public static Vec3us decr(final Vec3us v) {
        return decr(v, v);
    }

    public static Vec3us decr(final Vec3us res, final Vec3us a) {
        res.x.value = (short) ((a.x.value & 0xffff) - 1);
        res.y.value = (short) ((a.y.value & 0xffff) - 1);
        res.z.value = (short) ((a.z.value & 0xffff) - 1);
        return res;
    }

    public static Vec4 decr_(final Vec4 v) {
        return decr(new Vec4(), v);
    }

    public static Vec4 decr(final Vec4 v) {
        return decr(v, v);
    }

    public static Vec4 decr(final Vec4 res, final Vec4 a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        res.z = a.z - 1;
        res.w = a.w - 1;
        return res;
    }

    public static Vec4b decr_(final Vec4b v) {
        return decr(new Vec4b(), v);
    }

    public static Vec4b decr(final Vec4b v) {
        return decr(v, v);
    }

    public static Vec4b decr(final Vec4b res, final Vec4b a) {
        res.x = (byte) (a.x - 1);
        res.y = (byte) (a.y - 1);
        res.z = (byte) (a.z - 1);
        res.w = (byte) (a.w - 1);
        return res;
    }

    public static Vec4d decr_(final Vec4d v) {
        return decr(new Vec4d(), v);
    }

    public static Vec4d decr(final Vec4d v) {
        return decr(v, v);
    }

    public static Vec4d decr(final Vec4d res, final Vec4d a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        res.z = a.z - 1;
        res.w = a.w - 1;
        return res;
    }

    public static Vec4i decr_(final Vec4i v) {
        return decr(new Vec4i(), v);
    }

    public static Vec4i decr(final Vec4i v) {
        return decr(v, v);
    }

    public static Vec4i decr(final Vec4i res, final Vec4i a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        res.z = a.z - 1;
        res.w = a.w - 1;
        return res;
    }

    public static Vec4l decr_(final Vec4l v) {
        return decr(new Vec4l(), v);
    }

    public static Vec4l decr(final Vec4l v) {
        return decr(v, v);
    }

    public static Vec4l decr(final Vec4l res, final Vec4l a) {
        res.x = a.x - 1;
        res.y = a.y - 1;
        res.z = a.z - 1;
        res.w = a.w - 1;
        return res;
    }

    public static Vec4s decr_(final Vec4s v) {
        return decr(new Vec4s(), v);
    }

    public static Vec4s decr(final Vec4s v) {
        return decr(v, v);
    }

    public static Vec4s decr(final Vec4s res, final Vec4s a) {
        res.x = (short) (a.x - 1);
        res.y = (short) (a.y - 1);
        res.z = (short) (a.z - 1);
        res.w = (short) (a.w - 1);
        return res;
    }

    public static Vec4ub decr_(final Vec4ub v) {
        return decr(new Vec4ub(), v);
    }

    public static Vec4ub decr(final Vec4ub v) {
        return decr(v, v);
    }

    public static Vec4ub decr(final Vec4ub res, final Vec4ub a) {
        res.x.value = (byte) ((a.x.value & 0xff) - 1);
        res.y.value = (byte) ((a.y.value & 0xff) - 1);
        res.z.value = (byte) ((a.z.value & 0xff) - 1);
        res.w.value = (byte) ((a.w.value & 0xff) - 1);
        return res;
    }

    public static Vec4ui decr_(final Vec4ui v) {
        return decr(new Vec4ui(), v);
    }

    public static Vec4ui decr(final Vec4ui v) {
        return decr(v, v);
    }

    public static Vec4ui decr(final Vec4ui res, final Vec4ui a) {
        res.x.value = a.x.value - 1;
        res.y.value = a.y.value - 1;
        res.z.value = a.z.value - 1;
        res.w.value = a.w.value - 1;
        return res;
    }

    public static Vec4ul decr_(final Vec4ul v) {
        return decr(new Vec4ul(), v);
    }

    public static Vec4ul decr(final Vec4ul v) {
        return decr(v, v);
    }

    public static Vec4ul decr(final Vec4ul res, final Vec4ul a) {
        res.x.value = a.x.value - 1;
        res.y.value = a.y.value - 1;
        res.z.value = a.z.value - 1;
        res.w.value = a.w.value - 1;
        return res;
    }

    public static Vec4us decr_(final Vec4us v) {
        return decr(new Vec4us(), v);
    }

    public static Vec4us decr(final Vec4us v) {
        return decr(v, v);
    }

    public static Vec4us decr(final Vec4us res, final Vec4us a) {
        res.x.value = (short) ((a.x.value & 0xffff) - 1);
        res.y.value = (short) ((a.y.value & 0xffff) - 1);
        res.z.value = (short) ((a.z.value & 0xffff) - 1);
        res.w.value = (short) ((a.w.value & 0xffff) - 1);
        return res;
    }
}
